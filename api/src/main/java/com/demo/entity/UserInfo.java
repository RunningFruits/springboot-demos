package com.demo.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;


@Entity(name = "userinfo")
@Data
public class UserInfo implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String headIcon;
    private String gender;
    private Date lastLogin;
    private String city;
    private String province;
    private String openid;


}
