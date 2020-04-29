package com.code.demo.backend.controller.system;

import com.code.demo.backend.entity.system.SystemRole;
import com.code.demo.backend.repository.system.SystemRoleRepository;
import com.code.demo.backend.utils.PageRequestUtil;
import com.code.demo.backend.utils.PagingResult;
import com.code.demo.backend.utils.ResponseResult;
import com.code.demo.backend.annotation.OperationLog;
import com.code.demo.backend.controller.BaseController;
import com.code.demo.backend.pojo.system.SelectPojo;
import com.code.demo.backend.service.system.SystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/systemRole")
public class SystemRoleController extends BaseController {
    @Autowired
    private PageRequestUtil pageRequestUtil;

    @Autowired
    private SystemRoleRepository repository;

    @Autowired
    private SystemRoleService service;

    @GetMapping(value = "")
    public String systemRolePage() {
        return "system/roleManager";
    }

    // 获取所有角色
    @ResponseBody
    @GetMapping(value = "/findAllRoleListForSelect")
    public List<SelectPojo> findAllRoleListForSelect() {
        List<SystemRole> list = repository.findAll();
        return SelectPojo.getRoles(list);
    }

    @ResponseBody
    @GetMapping(value = "/findAll")
    public PagingResult<SystemRole> findAll(@RequestParam Map<String, Object> params) {
        Map map = getMap(params);
        Map advQuery = (Map) map.get("advQuery");
        PageRequest pageRequest = pageRequestUtil.genericPageRequestByRequest(map);
        return service.findSystemRoles(pageRequest, advQuery, "");
    }

    /**
     * 获取角色拥有的菜单
     */
    @GetMapping("/{roleId}/own/menu")
    @ResponseBody
    public ResponseResult getRoleOwnMenu(@PathVariable("roleId") Long roleId) {
        return ResponseResult.success(service.getMenusByRoleId(roleId));
    }

    @OperationLog("为角色授予菜单")
    @PostMapping("/{roleId}/grant/menu")
    @ResponseBody
    public ResponseResult grantMenu(@PathVariable("roleId") Long roleId, @RequestParam(value = "menuIds[]", required = false) Long[] menuIds) {
        return service.grantMenu(roleId, menuIds);
    }

    @GetMapping("/getUserRoles/{id}")
    @ResponseBody
    public Long[] getUserRoles(@PathVariable("id") String id) {
        return repository.getUserRoles(id);
    }

    @OperationLog("保存角色信息")
    @ResponseBody
    @PostMapping(value = "/save")
    public ResponseResult save(@RequestBody SystemRole systemRole) {
        return service.save(systemRole);
    }

    @OperationLog("删除角色信息")
    @ResponseBody
    @GetMapping(value = "/deleteRole/{id}")
    public ResponseResult deleteRole(@PathVariable("id") Long id) {
        return service.deleteRole(id);
    }

    /**
     * 检验角色名称是否唯一
     *
     * @param roleName
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/checkRoleName")
    public ResponseResult checkRoleName(@RequestParam(value = "roleName") String roleName, @RequestParam(value = "id") Long id) {
        return service.checkRoleName(roleName, id);

    }


    /**
     * 检验角色显示名称是否唯一
     *
     * @param displayName
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/checkDisplayName")
    public ResponseResult checkDisplayName(@RequestParam(value = "displayName") String displayName, @RequestParam(value = "id") Long id) {
        return service.checkDisplayName(displayName, id);
    }

}



