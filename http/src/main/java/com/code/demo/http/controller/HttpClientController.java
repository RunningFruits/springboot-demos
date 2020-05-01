package com.code.demo.http.controller;


import com.code.demo.http.service.HttpAPIService;
import com.code.demo.http.util.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Api(value = "", description = "get和post请求")
@RestController
@Slf4j
public class HttpClientController {

    @Resource
    private HttpAPIService httpAPIService;

    @ApiOperation(value = "get", notes = "get请求", httpMethod = "GET")
    @PostMapping("get")
    public String get() throws Exception {
        String str = httpAPIService.doGet("http://www.baidu.com");
        System.out.println(str);
        return "hello";
    }

    @ApiOperation(value = "post", notes = "post请求", httpMethod = "POST")
    @RequestMapping("post")
    public ResponseEntity post() throws Exception {
        Map<String,Object> map = new HashMap<>();
        HttpResult httpResult = httpAPIService.doPost("http://www.baidu.com",map);
        return ResponseEntity.status(200).body(httpResult);
    }

}
