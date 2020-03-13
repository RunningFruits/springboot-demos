package com.haiyu.manager.model;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.util.Date;

@ExcelTarget("employee")//目标对象
public class POIEmployee {

    @Excel(name = "编号")//该字段对应的表头设置
    private Long id;

    @Excel(name = "用户名")
    private String username;

    @Excel(name = "邮箱", width = 30)
    private String email;

    @Excel(name = "年龄")
    private Integer age;

    @Excel(name = "性别", replace = {"男_true", "女_false"})
    private Boolean gender;

    @Excel(name = "头像", type = 2, width = 5, height = 10, savePath = "img/upload/")
    private String headImage;

    @Excel(name = "生日", format = "yyyy-MM-dd", width = 20)
    private Date birthday = new Date();

    public POIEmployee() {
    }

    public POIEmployee(Long id, String username, String email, Integer age, Boolean gender, String headImage) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.headImage = headImage;
    }


    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "POIEmployee{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", headImage='" + headImage + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}

