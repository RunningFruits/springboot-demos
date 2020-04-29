package com.code.demo.api.model;

import java.util.Map;

public class JsonResult {

    private int code;
    private Map<String, String> result;

    public JsonResult(int code) {
        this.code = code;
    }

    public JsonResult(int code, Map<String, String> result) {
        this.code = code;
        this.result = result;
    }
}
