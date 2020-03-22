package com.init.demo.pojo.system;

import com.init.demo.entity.system.SystemAction;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreePojo {

    private Long id;

    private Long parentId;

    private String title;

    //private String name;

    private List<TreePojo> children = new ArrayList<TreePojo>();

    private String href;

    private boolean spread;

    private boolean checked;

    private boolean disabled;

    private String openType;

    public TreePojo(){}

    public TreePojo(SystemAction systemAction){
        this.id = systemAction.getId();
        this.parentId = systemAction.getParentId();
        this.title = systemAction.getDisplayName();
        this.href = systemAction.getName();
        this.openType = systemAction.getOpenType();
        //this.name = systemAction.getDisplayName();
    }
}
