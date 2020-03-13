package com.haiyu.manager.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "base_admin_user")
@Data
public class BaseAdminUser implements java.io.Serializable{
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 系统用户名称
     */
    @Column(name = "sys_user_name")
    private String sysUserName;

    /**
     * 系统用户密码
     */
    @Column(name = "sys_user_pwd")
    private String sysUserPwd;

    /**
     * 角色
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 手机号
     */
    @Column(name = "user_phone")
    private String userPhone;

    /**
     * 登记时间
     */
    @Column(name = "reg_time")
    private String regTime;

    /**
     * 状态（0：无效；1：有效）
     */
    @Column(name = "user_status")
    private Integer userStatus;

}
