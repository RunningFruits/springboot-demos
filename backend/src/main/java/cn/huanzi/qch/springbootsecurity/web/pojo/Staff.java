package cn.huanzi.qch.springbootsecurity.web.pojo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Staff {

    @Id
    Integer staffId;
    String staffName;
    String switchTpl;
    int staffAge;
    String staffCardId;

}
