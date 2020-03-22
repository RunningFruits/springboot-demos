package com.init.demo.service.system;

import com.init.demo.entity.system.SysRoleAction;
import com.init.demo.entity.system.SystemRole;
import com.init.demo.repository.system.SysRoleActionRepository;
import com.init.demo.repository.system.SysUserRoleRepository;
import com.init.demo.repository.system.SystemRoleRepository;
import com.init.demo.utils.PagingResult;
import com.init.demo.utils.ResponseResult;
import com.init.demo.utils.TableQueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SystemRoleService {

    @Autowired
    private SystemRoleRepository roleRepository;

    @Autowired
    private SysUserRoleRepository sysUserRoleRepository;

    @Autowired
    private SysRoleActionRepository sysRoleActionRepository;

    @Autowired
    private TableQueryUtil tableQueryUtil;

    public PagingResult<SystemRole> findSystemRoles(PageRequest pageRequest, Map advQuery, String keyword) {
        return tableQueryUtil.findAll(advQuery, keyword, pageRequest, SystemRole.class, roleRepository);
    }

    public Long[] getMenusByRoleId(Long roleId) {
        return roleRepository.getMenusByRoleId(roleId);
    }

    // 保存授权
    @Transactional
    public ResponseResult grantMenu(Long roleId, Long[] menuIds) {
        try {
            sysRoleActionRepository.deleteByRoleId(roleId);
            if (menuIds != null && menuIds.length != 0) {
                List<SysRoleAction> sysRoleActionList = new ArrayList<SysRoleAction>();
                for (int i = 0; i < menuIds.length; i++) {
                    sysRoleActionList.add(new SysRoleAction(roleId, menuIds[i]));
                }
                sysRoleActionRepository.saveAll(sysRoleActionList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ResponseResult.error();
        }
        return ResponseResult.success();
        // TODO
        //clearRoleAuthCache(roleId);
    }

    public ResponseResult save(SystemRole role) {
        try {
            roleRepository.save(role);
            return ResponseResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error();
        }
    }

    // 删除该角色 删除角色-动作 删除人员-角色
    @Transactional
    public ResponseResult deleteRole(Long id) {
        try {
            roleRepository.deleteById(id);
            sysRoleActionRepository.deleteByRoleId(id);
            sysUserRoleRepository.deleteByRoleId(id);
            return ResponseResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error();
        }
    }


    public ResponseResult checkRoleName(String roleName, Long id) {
        int count = 0;
        // 新增
        if (StringUtils.isEmpty(id)) {
            count = roleRepository.countSystemRoleByRoleName(roleName);
        } else {
            count = roleRepository.countSystemRoleByRoleNameAndIdNot(roleName, id);
        }
        if (count == 0)
            return ResponseResult.success();
        else
            return ResponseResult.error();
    }

    public ResponseResult checkDisplayName(String displayName, Long id) {
        int count = 0;
        // 新增
        if (StringUtils.isEmpty(id)) {
            count = roleRepository.countSystemRoleByDisplayName(displayName);
        } else {
            count = roleRepository.countSystemRoleByDisplayNameAndIdNot(displayName, id);
        }
        if (count == 0)
            return ResponseResult.success();
        else
            return ResponseResult.error();
    }

}
