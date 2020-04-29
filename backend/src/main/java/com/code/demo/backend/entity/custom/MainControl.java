package com.code.demo.backend.entity.custom;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "main_control")
public class MainControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mac;
    private String location;
    private String longitude;
    private String latitude;
    private Date createTime;

}
