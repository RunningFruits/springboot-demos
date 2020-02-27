package com.hosiang.dev.model;

public class EasyPOIModel {

    private Integer id;
    private String project;
    private String name;
    private String sex;
    private int age;

    public EasyPOIModel() {
    }

    public EasyPOIModel(Integer id, String project, String name, String sex, int age) {
        this.id = id;
        this.project = project;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
}
