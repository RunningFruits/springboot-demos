package com.code.demo.websocket.controller;

import com.alibaba.fastjson.JSONObject;
import com.code.demo.websocket.config.ServerConfig;
import com.code.demo.websocket.websocket.WebSocketServer;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RestController
@RequestMapping("device")
@Slf4j
public class DeviceCmdController {

    @Autowired
    ServerConfig serverConfig;

    @ApiOperation(value = "sayHello", notes = "sayHello测试接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "msg", value = "消息文本", required = true, dataType = "String"),
    })
    @RequestMapping("/sayHello")
    public ResponseEntity<String> sayHello(HttpServletRequest request) throws IOException {
        String msg = request.getParameter("msg");
        if (StringUtils.isNotEmpty(msg)) {
            log.info("msg from client:" + msg);
        }
        return ResponseEntity.ok("Hello,too");
    }

    @ApiOperation(value = "/{device}/{cmd}/{toUserId}", notes = "sayHello测试接口", httpMethod = "POST")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "query", name = "msg", value = "消息文本", required = true, dataType = "String"),
//    })
    @RequestMapping("/{device}/{cmd}/{toUserId}")
    public ResponseEntity<String> deviceCmd(
            @ApiParam(name = "device", required = true) @PathVariable String device,
            @ApiParam(name = "cmd", required = true) @PathVariable String cmd,
            @ApiParam(name = "toUserId", required = true) @PathVariable String toUserId
    ) throws IOException {

        JSONObject msg = new JSONObject();
        msg.put("device", device);
        msg.put("cmd", cmd);

        WebSocketServer.sendInfo(msg.toJSONString(), toUserId);
        return ResponseEntity.ok("MSG SEND SUCCESS");
    }


}
