package com.code.demo.backend.service.system;


import com.code.demo.backend.entity.system.SysUserRole;
import com.code.demo.backend.entity.system.SystemRole;
import com.code.demo.backend.entity.system.SystemUser;
import com.code.demo.backend.repository.system.SysUserRoleRepository;
import com.code.demo.backend.repository.system.SystemRoleRepository;
import com.code.demo.backend.repository.system.SystemUserRepository;
import com.code.demo.backend.utils.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SystemUserService {

    @Autowired
    private TableQueryUtil tableQueryUtil;

    @Autowired
    private SystemUserRepository repository;

    @Autowired
    private SysUserRoleRepository sysUserRoleRepository;

    @Autowired
    private SystemRoleRepository roleRepository;

    @Autowired
    private SystemLogService logService;

    @Autowired
    private SessionUtil sessionUtil;



    public PagingResult<SystemUser> findSystemUsers(PageRequest pageRequest, Map advQuery, String keyword) {
        return tableQueryUtil.findAll(advQuery, keyword, pageRequest, SystemUser.class, repository);
    }

    public ResponseResult save(SystemUser user) {
        try {
            // 新建
            if (StringUtils.isEmpty(user.getId())) {
                // 设置默认密码123
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
                String enPassword = encoder.encode(GlobalParams.INIT_PASSWORD);
                user.setPassword(enPassword);
            } else {
                SystemUser systemUser = repository.findSystemUserByIdAndDel(user.getId(), false);
                user.setPassword(systemUser.getPassword());
            }
            repository.save(user);
            return ResponseResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error();
        }
    }

    public ResponseResult resetPassword(String id) {
        SystemUser systemUser = repository.findSystemUserByIdAndDel(id, false);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
        String enPassword = encoder.encode(GlobalParams.INIT_PASSWORD);
        systemUser.setPassword(enPassword);
        repository.save(systemUser);
        return ResponseResult.success();
    }

    // 保存人员-角色关系
    @Transactional
    public ResponseResult saveUserRols(String userId, Long[] userRole) {
        List<SysUserRole> sysUserRoles = new ArrayList<>();
        if (userRole.length > 0) {
            Long[] idList = new Long[userRole.length];
            int flag = 0;
            int count = 0;
            String message = "";
            String roleNames = "";
            for (Long roleId : userRole) {
                sysUserRoles.add(new SysUserRole(userId, roleId));
                idList[count] = roleId;
                count++;
            }
            List<SystemRole> roleList = roleRepository.findAllByIdIn(idList);
            for (SystemRole roles : roleList) {
                if (roles.getRoleName().equals("admin") || roles.getRoleName().equals("security") || roles.getRoleName().equals("auditor")) {
                    message += roles.getDisplayName() + "、";
                    flag++;
                }
                roleNames += roles.getDisplayName() + "、";
            }
            //三员不能兼任
            if (flag > 1) {
                return ResponseResult.error(WebUtil.getStringEnd(message));
            }
            sysUserRoleRepository.deleteByUserId(userId);
            sysUserRoleRepository.saveAll(sysUserRoles);
            //保存操作日志 start
            SystemUser user = repository.findSystemUserByIdAndDel(userId, false);
            String title = "保存人员角色关系";
            String content = "保存" + user.getUserName() + "(" + user.getLoginName() + ")的角色为：【" + WebUtil.getStringEnd(roleNames) + "】";
            logService.saveLog(title, content);
            //保存操作日志 end
        }
        return ResponseResult.success();
    }

    // 将enable改为0
    public ResponseResult deleteUser(String id) {
        try {
            repository.deleteByUserId(id);
            return ResponseResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error();
        }
    }

    // 检测登录名唯一性
    public ResponseResult checkLoginName(String loginName, String id) {
        int count = 0;
        // 新增
        if (StringUtils.isEmpty(id)) {
            count = repository.countByLoginNameAndDel(loginName, false);
        } else {
            count = repository.countByLoginNameAndDelAndIdNot(loginName, false, id);
        }
        if (count == 0)
            return ResponseResult.success();
        else
            return ResponseResult.error();
    }

    // 检测工号唯一性
//    public ResponseResult checkOeCode(String oeCode, String id) {
//        int count = 0;
//        // 新增
//        if (StringUtils.isEmpty(id)) {
//            count = repository.countByOeCodeAndDel(oeCode, false);
//        } else {
//            count = repository.countByOeCodeAndDelAndIdNot(oeCode, false, id);
//        }
//        if (count == 0)
//            return ResponseResult.success();
//        else
//            return ResponseResult.error();
//    }

    public ResponseResult updatePassword(String id, String passwordOld, String passwordNew) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        SystemUser systemUser = repository.findSystemUserByIdAndDel(id, false);
        if (!encoder.matches(passwordOld, systemUser.getPassword())) {
            return ResponseResult.error("旧密码输入错误！");
        }
        String enPassword = encoder.encode(passwordNew);
        systemUser.setPassword(enPassword);
        repository.save(systemUser);
        return ResponseResult.success();
    }

    @Transactional
    public ResponseResult saveFile(MultipartFile file) {
        BufferedInputStream bf = null;
        try {
            String suffixName = WebUtil.getSuffixName(file);
            if (suffixName.indexOf("xls") == -1) {
                return ResponseResult.error("请上传excel模板文件！");
            }
            bf = new BufferedInputStream(file.getInputStream());
            XSSFWorkbook workbook = new XSSFWorkbook(bf);
            XSSFSheet sheet = workbook.getSheetAt(0);
            List<SystemUser> systemUserList = new ArrayList<>();
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                XSSFRow row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                } else {
                    // login_name	user_name	password	authority_level	authority_code	remark
                    String loginName = row.getCell(0).toString();
                    String userName = row.getCell(1).toString();
                    String password = row.getCell(2).toString();
                    String authorityLevel = row.getCell(3).toString();
                    String authorityCode = row.getCell(4).toString();
                    String remark = row.getCell(5).toString();
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
                    String enPassword = encoder.encode(password);
                    SystemUser systemUser = new SystemUser(loginName, userName, enPassword, remark);
                    systemUserList.add(systemUser);
                }
            }
            // 保存人员
            Object saveObj = repository.saveAll(systemUserList);
            ArrayList<SystemUser> systemUsers = (ArrayList<SystemUser>) saveObj;
            // 保存人员 角色信息
            List<SysUserRole> sysUserRoles = new ArrayList<>();
            List<SystemRole> roleList = roleRepository.findAll();
            for (SystemUser info : systemUsers) {
                long roleId = this.findRoleId(roleList, info.getRemark());
                if (roleId != 0l) {
                    SysUserRole sysUserRole = new SysUserRole(info.getId(), roleId);
                    sysUserRoles.add(sysUserRole);
                }
            }
            if (roleList.size() > 0) {
                sysUserRoleRepository.saveAll(sysUserRoles);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error();
        }
        return ResponseResult.success();
    }

    private long findRoleId(List<SystemRole> roleList, String displayName) {
        for (SystemRole role : roleList) {
            if (role.getDisplayName().equals(displayName))
                return role.getId();
        }
        return 0l;
    }

    // 获取当前用户的所有居委
//    public List<Community> getCurtCommunities() {
//        SystemUser systemUser = sessionUtil.getCurrentUser();
//        String authorityLevel = systemUser.getAuthorityLevel();
//        String authorityCode = systemUser.getAuthorityCode();
//        List<Community> communityList = new ArrayList<>();
//        // 区人员
//        if (GlobalParams.AUTHORITY_LEVEL_DISTRICT.equals(authorityLevel)) {
//            // 找出所有居委
////            communityList = communityRepository.findAll();
//        } else if (GlobalParams.AUTHORITY_LEVEL_STREET.equals(authorityLevel)) { // 街道人员
//            // 找出所有居委
//            communityList = communityRepository.findAllByStreetCode(authorityCode);
//        } else {
//            Community community = communityRepository.findByCommunityName(authorityCode);
//            communityList.add(community);
//        }
//        return communityList;
//    }
}
