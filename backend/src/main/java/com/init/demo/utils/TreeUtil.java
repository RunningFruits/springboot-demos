package com.init.demo.utils;

import com.init.demo.pojo.system.DTreePojo;
import com.init.demo.pojo.system.TreePojo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreeUtil {
    public List<TreePojo> makeTree(List<TreePojo> treePojoList, Long parentId) {
        List<TreePojo> tree = makeTree2(treePojoList, parentId);
        return tree;
    }

    private List<TreePojo> makeTree2(List<TreePojo> treePojoList, long pId) { // 子类
        List<TreePojo> children = treePojoList.stream().filter(x -> x.getParentId() == pId)
                .collect(Collectors.toList()); // 后辈中的非子类
        List<TreePojo> successor = treePojoList.stream().filter(x -> x.getParentId() != pId)
                .collect(Collectors.toList());
        children.forEach(x -> {
            makeTree2(successor, x.getId()).forEach(y -> x.getChildren().add(y));
        });
        return children;
    }

    public List<DTreePojo> makeDTree(List<DTreePojo> dTreePojoList, Long parentId) {
        List<DTreePojo> tree = makeDTree2(dTreePojoList, parentId);
        return tree;
    }

    private List<DTreePojo> makeDTree2(List<DTreePojo> dTreePojoList, long pId) { // 子类
        List<DTreePojo> children = dTreePojoList.stream().filter(x -> x.getParentId() == pId)
                .collect(Collectors.toList()); // 后辈中的非子类
        List<DTreePojo> successor = dTreePojoList.stream().filter(x -> x.getParentId() != pId)
                .collect(Collectors.toList());
        children.forEach(x -> {
            makeDTree2(successor, x.getId()).forEach(y -> x.getChildren().add(y));
        });
        return children;
    }

    // 参数是一颗已经整理好的树，已经包含了孩子
    public List<TreePojo> findByParentId(List<TreePojo> initTree, Long parentId){
        for (TreePojo treePojo : initTree) {
            if (treePojo.getId() == parentId)
                return treePojo.getChildren();
            else {
                return findByParentId(treePojo.getChildren(),parentId);
            }
        }
        return null;
    }
}
