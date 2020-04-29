package com.code.demo.backend.pojo.system;

import com.code.demo.backend.entity.system.SystemAction;
import com.code.demo.backend.entity.system.SystemEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DTreePojo {

    private Long id;

    private Long parentId;

    private String title;

    //private String name;

    private List<DTreePojo> children = new ArrayList<DTreePojo>();

    private String href;

    private String checkArr = "0";

    public DTreePojo(){}

    public DTreePojo(SystemAction systemAction){
        this.id = systemAction.getId();
        this.parentId = systemAction.getParentId();
        this.title = systemAction.getDisplayName();
        this.href = systemAction.getName();
        //this.name = systemAction.getDisplayName();
    }

    public DTreePojo(SystemEnum systemEnum){
        this.id = systemEnum.getId();
        this.parentId = systemEnum.getParentId();
        this.title = systemEnum.getName();
        this.href = "";
        //this.name = systemAction.getDisplayName();
    }
}
