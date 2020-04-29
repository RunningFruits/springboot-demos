package com.code.demo.backend.entity.system;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// 用户角色关系表
@Data
@Entity
public class SysUserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private Long roleId;

    public SysUserRole() {
    }

    public SysUserRole(String userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
