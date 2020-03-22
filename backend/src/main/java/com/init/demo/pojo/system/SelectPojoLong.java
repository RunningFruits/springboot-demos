package com.init.demo.pojo.system;

import com.init.demo.entity.system.SystemRole;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SelectPojoLong {
    private String name;

    private Long value;

    public SelectPojoLong(String name, Long value) {
        this.name = name;
        this.value = value;
    }

    public static List<SelectPojoLong> getRoles(List<SystemRole> roles) {
        List<SelectPojoLong> lists = new ArrayList<SelectPojoLong>();
        for (SystemRole role : roles) {
            lists.add(new SelectPojoLong(role.getDisplayName(),role.getId()));
        }
        return lists;
    }
}
