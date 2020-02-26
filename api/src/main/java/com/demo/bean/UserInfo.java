package com.demo.bean;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lihj
 * @create 2018-01-03 18:16
 **/

@Entity(name = "userinfo")
@Data
public class UserInfo implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String openId;
    private String name;
    private String password;
    private String headIcon;
    private String gender;
    private Date lastLogin;
    private String city;
    private String province;
    private String openid;


}
