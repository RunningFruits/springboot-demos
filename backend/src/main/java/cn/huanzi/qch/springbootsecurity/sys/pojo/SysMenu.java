package cn.huanzi.qch.springbootsecurity.sys.pojo;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_menu")
@Data
public class SysMenu implements Serializable {
    @Id
    private String menuId;//菜单id

    private String menuName;//菜单名称

    private String menuPath;//菜单路径

    private String menuParentId;//上级id

}
