package com.code.demo.fileupload.entity;

import com.code.demo.fileupload.utils.MapTools;
import lombok.Data;

import java.util.Map;

@Data
public class ResultMap {

    private Integer status = 200;
    private String msg;
    private Object data;

    public Map<String, Object> toMap() {
        return MapTools.getObjectToMap(this);
    }

}
