package com.lzp.controller;


import com.lzp.service.HttpAPIService;
import com.lzp.util.HttpResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HttpClientController {

    @Resource
    private HttpAPIService httpAPIService;

    @RequestMapping("get")
    public String get() throws Exception {
        String str = httpAPIService.doGet("http://www.baidu.com");
        System.out.println(str);
        return "hello";
    }

    @RequestMapping("post")
    public String post() throws Exception {
        Map<String,Object> map = new HashMap<>();
        HttpResult httpResult = httpAPIService.doPost("http://www.baidu.com",map);
        return "hello";
    }

}
