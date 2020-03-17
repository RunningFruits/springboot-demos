package cn.huanzi.qch.springbootsecurity.web.pojo;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "video")
public class Video implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;
    private String size;
    private String path;
    private String title;
    private Timestamp uploadTime;

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", size='" + size + '\'' +
                ", path='" + path + '\'' +
                ", title='" + title + '\'' +
                ", uploadTime=" + uploadTime +
                '}';
    }
}

