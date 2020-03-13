package com.haiyu.manager.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "base_admin_permission")
@Data
public class BaseAdminPermission implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父菜单id
     */
    private Integer pid;

    /**
     * 描述
     */
    private String descpt;

    /**
     * 菜单url
     */
    private String url;

    /**
     * 添加时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private String updateTime;

    /**
     * 删除标志（0:删除 1：存在）
     */
    @Column(name = "del_flag")
    private Integer delFlag;


}
