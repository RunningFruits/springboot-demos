package com.hosiang.dev.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 50)
    public String id;

    //用户编码
    @Column(name = "code", length = 50)
    public String userCode;

    //用户头像
    @Column(name = "headimg", length = 50)
    public String userHeadimg;

    //用户昵称
    @Column(name = "nickname", length = 50)
    public String userNickname;

    //用户名称
    @Column(name = "name", length = 50)
    public String userName;

    //用户密码
    @Column(name = "password", length = 50)
    public String userPassword;

    //用户性别
    @Column(name = "sex")
    public Integer userSex;

    //创建时间
    @Temporal(TemporalType.DATE)
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date createTime;

    //修改时间
    @Temporal(TemporalType.DATE)
    @Column(name = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date updateTime;

}
