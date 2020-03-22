package com.init.demo.entity.custom;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "excel_test")
@Data
public class Excel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String age;

    @Column
    private String address;

}
