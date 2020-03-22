package com.init.demo.pojo.system;

import com.init.demo.entity.system.SystemRole;
import com.init.demo.entity.system.SystemUser;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SelectPojo {
    private String name;

    private Object value;

    public SelectPojo(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public static List<SelectPojo> getRoles(List<SystemRole> roles) {
        List<SelectPojo> lists = new ArrayList<SelectPojo>();
        for (SystemRole role : roles) {
            lists.add(new SelectPojo(role.getDisplayName(), role.getId()));
        }
        return lists;
    }

    public static List<SelectPojo> getUsers(List<SystemUser> users) {
        List<SelectPojo> lists = new ArrayList<SelectPojo>();
        for (SystemUser user : users) {
            lists.add(new SelectPojo(user.getUserName(), user.getLoginName()));
        }
        return lists;
    }
}
