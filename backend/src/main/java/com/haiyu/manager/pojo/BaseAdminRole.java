package com.haiyu.manager.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "base_admin_role")
@Data
public class BaseAdminRole implements java.io.Serializable {
    /**
     * 权限角色ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 角色描述
     */
    @Column(name = "role_desc")
    private String roleDesc;

    /**
     * 权限
     */
    private String permissions;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private String updateTime;

    /**
     * 1：有效
     * 0：无效
     */
    @Column(name = "role_status")
    private Integer roleStatus;
}
