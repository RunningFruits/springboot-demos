package com.code.demo.websocket.controller;

import com.code.demo.websocket.config.ServerConfig;
import com.code.demo.websocket.entity.MainControl;
import com.code.demo.websocket.service.MainControlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;


@RestController
@RequestMapping("mainControl")
@Slf4j
public class MainControlController {

    @Autowired
    ServerConfig serverConfig;

    @Autowired
    MainControlService service;

    @ResponseBody
    @PostMapping(value = "/report")
    public Object report(@RequestParam Map<String, Object> params) {
        MainControl mainControl = new MainControl();

        mainControl.setMac(params.get("mac").toString());
        mainControl.setIp(params.get("ip").toString());
        mainControl.setLocation(params.get("longitude").toString());
        mainControl.setLatitude(params.get("latitude").toString());
        mainControl.setLocation(params.get("location").toString());
        mainControl.setCreateTime(new Date());
        return service.report(mainControl);
    }


}
