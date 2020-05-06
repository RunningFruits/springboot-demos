package com.code.demo.httpclient.entity;


import lombok.Data;

@Data
public class ResultMap {

    private Integer status;
    private String msg;
    private Object data;

}
