package cn.huanzi.qch.springbootsecurity.web.pojo;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "user_answer")
public class UserAnswer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String openid;

    @Column(name = "parent_name")
    private String parentName;

    private String score;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;
}
