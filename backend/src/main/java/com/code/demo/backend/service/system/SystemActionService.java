package com.code.demo.backend.service.system;


import com.code.demo.backend.entity.system.SystemAction;
import com.code.demo.backend.pojo.system.DTreePojo;
import com.code.demo.backend.pojo.system.TreePojo;
import com.code.demo.backend.repository.system.SysRoleActionRepository;
import com.code.demo.backend.repository.system.SystemActionRepository;
import com.code.demo.backend.utils.ResponseResult;
import com.code.demo.backend.utils.TreeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class SystemActionService {

    @Autowired
    private SystemActionRepository repository;

    @Autowired
    private SysRoleActionRepository sysRoleActionRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TreeUtil treeUtil;

    private List<Long> ids;

    // 注意：返回的是parentId的子树，不包括parentId的！！
    public List<TreePojo> changeToTree(Long parentId, String curtId) {
        List<SystemAction> systemActions;
        if (StringUtils.isEmpty(curtId))
            systemActions = repository.findAll();
        else
            systemActions = repository.findAllMenuByLoginId(curtId);
        List<TreePojo> treePojos = new ArrayList<>();
        for (SystemAction systemAction : systemActions) {
            treePojos.add(new TreePojo(systemAction));
        }
        if (treePojos.size() == 1)
            return treePojos;
        else {
            List<TreePojo> tree = treeUtil.makeTree(treePojos, parentId);
            return tree;
        }
    }

    // 注意：返回的是parentId的子树，不包括parentId的！！
    public List<DTreePojo> findAllAction() {
        List<SystemAction> systemActions = repository.findAll();
        List<DTreePojo> treePojos = new ArrayList<>();
        for (SystemAction systemAction : systemActions) {
            treePojos.add(new DTreePojo(systemAction));
        }
        if (treePojos.size() == 1)
            return treePojos;
        else {
            List<DTreePojo> dTree = treeUtil.makeDTree(treePojos, 0l);
            return dTree;
        }
    }


    public ResponseResult save(String add, String update, String delete) {
        ResponseResult responseResult = new ResponseResult();
        try {
            List<LinkedHashMap> addMapList = objectMapper.readValue(add, List.class);
            List<LinkedHashMap> deleteMapList = objectMapper.readValue(delete, List.class);
            List<LinkedHashMap> updateMapList = objectMapper.readValue(update, List.class);
            List<SystemAction> addList = new ArrayList<>();
            List<SystemAction> updateList = new ArrayList<>();
            List<SystemAction> deleteList = new ArrayList<>();
            for (LinkedHashMap linkedHashMap : addMapList) {
                SystemAction systemAction = objectMapper.readValue(objectMapper.writeValueAsString(linkedHashMap), SystemAction.class);
                addList.add(systemAction);
            }
            for (LinkedHashMap linkedHashMap : deleteMapList) {
                SystemAction systemAction = objectMapper.readValue(objectMapper.writeValueAsString(linkedHashMap), SystemAction.class);
                deleteList.add(systemAction);
            }
            for (LinkedHashMap linkedHashMap : updateMapList) {
                SystemAction systemAction = objectMapper.readValue(objectMapper.writeValueAsString(linkedHashMap), SystemAction.class);
                updateList.add(systemAction);
            }
            repository.saveAll(addList);
            repository.saveAll(updateList);
            repository.deleteAll(deleteList);
        } catch (Exception e) {
            e.printStackTrace();
            responseResult.error();
        }
        return responseResult;
    }

    public ResponseResult save(SystemAction systemAction) {
        try {
            if (systemAction.getParentId() == null)
                systemAction.setParentId(0l);
            if (systemAction.getId() == null) {
                // 获取最大的indexOf
                Integer maxIndexOf = repository.findMaxIndexOf(systemAction.getParentId());
                if (maxIndexOf == null) {
                    systemAction.setIndexOf(0);
                } else
                    systemAction.setIndexOf(maxIndexOf + 1);
            }
//            if (systemAction.getName().startsWith("/")) {
//                systemAction.setButton(false);
//            } else
//                systemAction.setButton(true);
            repository.save(systemAction);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseResult.error();
        }
        return ResponseResult.success();
    }

    @Transactional
    public ResponseResult deleteAction(Long id) {
        try {
            // 根据id找到这棵树
            List<TreePojo> treePojos = changeToTree(id, "");
            ids = new ArrayList<>();
            this.getIds(treePojos);
            // 删除子孙
            repository.deleteAllByIdIn(ids);
            // 删除自己
            repository.deleteById(id);
            // 解绑角色-动作
            sysRoleActionRepository.deleteByActionId();
            return ResponseResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error();
        }
    }

    // 找出这棵树中的所有id
    private void getIds(List<TreePojo> treePojos) {
        for (TreePojo pojo : treePojos) {
            ids.add(pojo.getId());
            this.getIds(pojo.getChildren());
        }
    }

    //  排序位置交换
    @javax.transaction.Transactional
    public ResponseResult swapSort(Long currentId, Long swapId) {
        try {
            repository.swapSort(currentId, swapId);
            return ResponseResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error();
        }
    }

    // 检测路径唯一性
    public ResponseResult checkName(String name, Long id) {
        if ("/".equals(name)) {
            return ResponseResult.success();
        } else {
            int count = 0;
            // 新增
            if (name.startsWith("/")) { // 菜单 非按钮
                return ResponseResult.success();
            } else {
                if (id == null) {
                    count = repository.countByName(name);
                } else {
                    count = repository.countByNameAndIdNot(name, id);
                }
                if (count == 0)
                    return ResponseResult.success();
                else
                    return ResponseResult.error();
            }

        }
    }

}
