package com.init.demo.entity.system;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class SystemAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String memo;
    private int indexOf;
    private String displayName;
    private String name;
    private Long parentId;
    private boolean button;
    private String openType = "";
    @Transient
    private List<SystemAction> children = new ArrayList<SystemAction>();

    public SystemAction(){}

//    private void setButton(){
//        if(this.name.startsWith("/"))
//            this.button = false;
//        else this.button = true;
//    }

}
