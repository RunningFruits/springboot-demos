package com.code.demo.backend.service.system;

import com.code.demo.backend.entity.system.SystemEnum;
import com.code.demo.backend.pojo.system.DTreePojo;
import com.code.demo.backend.repository.system.SystemEnumRepository;
import com.code.demo.backend.utils.ResponseResult;
import com.code.demo.backend.utils.TableQueryUtil;
import com.code.demo.backend.utils.TreeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SystemEnumService {

    @Autowired
    private TableQueryUtil tableQueryUtil;

    @Autowired
    private SystemEnumRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TreeUtil treeUtil;

    private List<Long> ids;

    public List<DTreePojo> changeToDTree(Long parentId) {
        List<SystemEnum> systemEnums = repository.findAll();
        List<DTreePojo> treePojos = new ArrayList<>();
        for (SystemEnum systemEnum : systemEnums) {
            treePojos.add(new DTreePojo(systemEnum));
        }
        if (treePojos.size() == 1)
            return treePojos;
        else {
            List<DTreePojo> tree = treeUtil.makeDTree(treePojos, parentId);
            return tree;
        }
    }


    public ResponseResult save(SystemEnum systemEnum) {
        try {
            repository.save(systemEnum);
        } catch (Exception e) {
            return ResponseResult.error();
        }
        return ResponseResult.success();
    }

    @Transactional
    public ResponseResult deleteEnum(Long id) {
        try {
            // 根据id找到这棵树
            List<DTreePojo> dtreePojos = changeToDTree(id);
            ids = new ArrayList<>();
            this.getIds(dtreePojos);
            // 删除子孙
            repository.deleteAllByIdIn(ids);
            // 删除自己
            repository.deleteById(id);
            return ResponseResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error();
        }
    }

    // 找出这棵树中的所有id 不包括自己
    private void getIds(List<DTreePojo> dtreePojos) {
        for (DTreePojo pojo : dtreePojos) {
            ids.add(pojo.getId());
            this.getIds(pojo.getChildren());
        }
    }

    public Integer findValue(List<SystemEnum> list, String name) {
        for (SystemEnum info : list) {
            if (name.equals(info.getName()))
                return info.getValue();
        }
        return null;
    }

}
