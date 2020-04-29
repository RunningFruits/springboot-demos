package com.code.demo.backend.api.code.demo.model;


import lombok.Data;

import java.util.Map;


@Data
public class JsonResult {

    private int code;
    private Map<String, Object> result;

    public JsonResult(int code) {
        this.code = code;
    }

    public JsonResult(int code, Map<String, Object> result) {
        this.code = code;
        this.result = result;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
