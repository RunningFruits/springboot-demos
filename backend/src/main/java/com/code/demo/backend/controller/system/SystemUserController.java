package com.code.demo.backend.controller.system;

import com.code.demo.backend.annotation.OperationLog;
import com.code.demo.backend.entity.system.SystemUser;
import com.code.demo.backend.utils.PageRequestUtil;
import com.code.demo.backend.utils.PagingResultSql;
import com.code.demo.backend.utils.ResponseResult;
import com.code.demo.backend.controller.BaseController;
import com.code.demo.backend.dao.SystemUserDao;
import com.code.demo.backend.service.system.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@RequestMapping("/systemUser")
public class SystemUserController extends BaseController {
    @Autowired
    private PageRequestUtil pageRequestUtil;

    @Autowired
    private SystemUserService service;


    @Autowired
    private SystemUserDao dao;

    @GetMapping(value = "")
    public String systemUserPage() {
        return "system/userManager";
    }

    @ResponseBody
    @GetMapping(value = "/findAll")
    public PagingResultSql<SystemUser> findAll(@RequestParam Map<String, Object> params) {
        Map map = getMap(params);
        Map advQuery = (Map) map.get("advQuery");
        advQuery.put("del", 0);
        PageRequest pageRequest = pageRequestUtil.genericPageRequestByRequest(map);
//        return service.findSystemUsers(pageRequest, advQuery, "");

        String sql = "select * from system_user";
        PagingResultSql<SystemUser> aaa = dao.getPageResult(sql, SystemUser.class, pageRequest);
        return aaa;
    }


    @OperationLog("重置密码")
    @ResponseBody
    @GetMapping(value = "/resetPassword/{id}")
    public ResponseResult resetPassword(@PathVariable("id") String id) {
        return service.resetPassword(id);
    }

    @GetMapping(value = "/editPasswordPage")
    public String editPasswordPage() {
        return "system/editPassword";
    }

    @ResponseBody
    @PostMapping(value = "/updatePassword")
    public ResponseResult updatePassword(@RequestBody SystemUser systemUser) {
        return service.updatePassword(systemUser.getId(), systemUser.getUserName(), systemUser.getRemark());
    }


    @OperationLog("保存人员信息")
    @ResponseBody
    @PostMapping(value = "/save")
    public ResponseResult save(@RequestBody SystemUser systemUser) {
        return service.save(systemUser);
    }

    /**
     * 保存人员角色关系
     * 保存日志 手动写入
     */
    @ResponseBody
    @PostMapping(value = "/saveUserRoles")
    public ResponseResult saveUserRoles(@RequestParam(value = "userId") String userId, @RequestParam(value = "userRole") Long[] userRole) {
        return service.saveUserRols(userId, userRole);
    }

    @OperationLog("删除人员")
    @ResponseBody
    @GetMapping(value = "/deleteUser/{id}")
    public ResponseResult deleteUser(@PathVariable("id") String id) {
        return service.deleteUser(id);
    }

    @GetMapping(value = "/kindEditor")
    public String kindEditor() {
        return "business/kindeditorTest";
    }

    @ResponseBody
    @PostMapping(value = "/checkLoginName")
    public ResponseResult checkLoginName(@RequestParam(value = "loginName") String loginName, @RequestParam(value = "id") String id) {
        return service.checkLoginName(loginName, id);
    }

    @ResponseBody
    @PostMapping(value = "/uploadFiles")
    public ResponseResult uploadFiles(@RequestParam(value = "file") MultipartFile file) {
        return service.saveFile(file);
    }

//    @ResponseBody
//    @PostMapping(value = "/checkOeCode")
//    public ResponseResult checkOeCode(@RequestParam(value = "oeCode") String oeCode, @RequestParam(value = "id") String id) {
//        return service.checkOeCode(oeCode, id);
//    }

}
