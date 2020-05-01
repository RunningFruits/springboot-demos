package cn.trunch.auth.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String password;
    private String name;
    private String avatar;
    private String phone;


}
