package cn.trunch.auth.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "auth")
@Data
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    private String authToken;
    private Timestamp authTime;
    private String authIp;
    private String authAddress;
    private Integer authState;
    private long userId;

}
