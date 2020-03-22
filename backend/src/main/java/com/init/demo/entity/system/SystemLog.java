package com.init.demo.entity.system;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class SystemLog {
    @Id
    @GeneratedValue(generator = "key_uuid_36")
    @GenericGenerator(name = "key_uuid_36", strategy = "uuid2")
    private String id;
    private String userId;
    private String userName;
    private String operation;
    private int time;
    private String method;
    private String params;
    private String ip;
    private Timestamp createTime;
}
