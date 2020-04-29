package com.code.demo.backend.controller.system;

import com.code.demo.backend.annotation.OperationLog;
import com.code.demo.backend.entity.system.SystemAction;
import com.code.demo.backend.pojo.system.TreePojo;
import com.code.demo.backend.repository.system.SystemActionRepository;
import com.code.demo.backend.service.system.SystemActionService;
import com.code.demo.backend.utils.ResponseResult;
import com.code.demo.backend.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/systemAction")
public class SystemActionController {

    @Autowired
    private SystemActionRepository repository;

    @Autowired
    private SystemActionService service;

    @Autowired
    private SessionUtil sessionUtil;


    @GetMapping(value = "")
    public String systemAction() {
        return "system/menuManager";
    }

    // 获取该用户的顶级菜单
    @ResponseBody
    @PostMapping(value = "/findTopMenuList")
    public List<SystemAction> findTopMenuList() {
        return repository.findTopMenuByLoginId(sessionUtil.getCurrentUser().getId());
    }

    // 获取该用户的所有菜单（不包括按钮）
    @ResponseBody
    @PostMapping(value = "/findAllMenuList")
    public List<SystemAction> findAllMenuList() {
        return repository.findAllMenuByLoginId(sessionUtil.getCurrentUser().getId());
    }

    // 获取该用户的所有菜单（不包括按钮）,并正序排列
    @ResponseBody
    @PostMapping(value = "/findAllMenuByLoginIdOrderByIndexOf")
    public List<SystemAction> findAllMenuByLoginIdOrderByIndexOf() {
        return repository.findAllMenuByLoginIdOrderByIndexOf(sessionUtil.getCurrentUser().getId());
    }

    @ResponseBody
    @PostMapping(value = "/findMenuList")
    public List<SystemAction> findMenuList() {
        return repository.findMenuByLoginId(sessionUtil.getCurrentUser().getId());
    }

    @ResponseBody
    @GetMapping(value = "/findMenuListByParentAndUser/{parentId}")
    public List<TreePojo> findMenuListByParentAndUser(@PathVariable("parentId") Long parentId) {
        return service.changeToTree(parentId, sessionUtil.getCurrentUser().getId());
    }

    @ResponseBody
    @PostMapping(value = "/findButtonList")
    public List<SystemAction> findButtonList() {
        return repository.findButtonByLoginId(sessionUtil.getCurrentUser().getId());
    }

    /**
     * 菜单树
     *
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/findAllActionToDTree")
    public ResponseResult findAllActionToDTree() {
        return ResponseResult.success(service.findAllAction());
    }

    /**
     * 菜单列表
     *
     * @param parentId
     * @return
     */
    @ResponseBody
    @GetMapping("/listByParent")
    public ResponseResult listByParent(@RequestParam(required = false) Long parentId) {
        List<SystemAction> menuList = repository.findAllByParentIdOrderByIndexOf(parentId);
        return ResponseResult.success(menuList);
    }

    @OperationLog("保存菜单")
    @ResponseBody
    @RequestMapping(value = "/save")
    public ResponseResult save(@RequestBody SystemAction systemAction) {
        return service.save(systemAction);
    }

    @OperationLog("删除菜单")
    @ResponseBody
    @GetMapping(value = "/deleteAction/{id}")
    public ResponseResult deleteAction(@PathVariable("id") Long id) {
        return service.deleteAction(id);
    }

    /**
     * 交换排序
     *
     * @param currentId
     * @param swapId
     * @return
     */
    @OperationLog("菜单排序")
    @ResponseBody
    @PostMapping("/swap")
    public ResponseResult swapSort(Long currentId, Long swapId) {
        return service.swapSort(currentId, swapId);
    }

    // 路径名唯一
    @ResponseBody
    @PostMapping(value = "/checkName")
    public ResponseResult checkName(@RequestParam(value = "name") String name, @RequestParam(value = "id") Long id) {
        return service.checkName(name, id);
    }
}
