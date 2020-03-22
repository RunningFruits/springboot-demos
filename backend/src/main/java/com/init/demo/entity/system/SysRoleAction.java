package com.init.demo.entity.system;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class SysRoleAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long roleId;

    private Long actionId;

    public SysRoleAction(){}

    public SysRoleAction(Long roleId,Long actionId){
        this.roleId = roleId;
        this.actionId = actionId;
    }
}
