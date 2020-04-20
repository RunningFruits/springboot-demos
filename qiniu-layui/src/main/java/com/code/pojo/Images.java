package com.code.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Table(name = "images")
public class Images {
    @Id
    private Integer id;
    private String name;
    private String ip;
    private Long time;
    private Long size;
    @Transient
    private String vTime;
}
