package com.init.demo.entity.system;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SystemEnum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer value;

    @Column(unique = true)
    private String remark;

    private Long parentId;
}
