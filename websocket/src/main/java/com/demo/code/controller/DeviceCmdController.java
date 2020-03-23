package com.demo.code.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.code.config.ServerConfig;
import com.demo.code.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RestController
@RequestMapping("device")
@Slf4j
public class DeviceCmdController {

    @Autowired
    ServerConfig serverConfig;

    @RequestMapping("/sayHello")
    public ResponseEntity<String> sayHello(HttpServletRequest request) throws IOException {
        String msg = request.getParameter("msg");
        if (StringUtils.isNotEmpty(msg)) {
            log.info("msg from client:" + msg);
        }
        return ResponseEntity.ok("Hello,too");
    }

    @RequestMapping("/{device}/{cmd}/{toUserId}")
    public ResponseEntity<String> deviceCmd(@PathVariable String device, @PathVariable String cmd, @PathVariable String toUserId) throws IOException {

        JSONObject msg = new JSONObject();
        msg.put("device", device);
        msg.put("cmd", cmd);

        WebSocketServer.sendInfo(msg.toJSONString(), toUserId);
        return ResponseEntity.ok("MSG SEND SUCCESS");
    }


}
