package com.code.demo.backend.entity.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class SystemUser {
    @Id
    @GeneratedValue(generator = "key_uuid_36")
    @GenericGenerator(name = "key_uuid_36", strategy = "uuid2")
    private String id;

    private String loginName;

    private String userName;

    @JsonIgnore
    private String password;

    private Timestamp createTime;

    private Timestamp modifyTime;

    // 备注
    private String remark;

    private boolean del = false;

    public SystemUser() {
    }

    public SystemUser(String loginName, String userName, String password, String remark) {
        this.loginName = loginName;
        this.userName = userName;
        this.password = password;
        this.remark = remark;
    }
}
