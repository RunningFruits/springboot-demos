package com.code.demo.api.controller.sms;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(value = "sms", description = "短信接口")
@RestController("sms")
@Slf4j
public class SmsController {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @ApiOperation(value = "send", notes = "发送接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "mobile", value = "手机号", required = true, dataType = "String")
    })
    @RequestMapping("send")
    public String send(@RequestParam(name = "mobile", required = true) String mobile) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("mobile", mobile);
        map.put("template_code", "SMS_152545288");
        map.put("sign_name", "wzdoo");
        map.put("param", "{\"name\":\"隔壁老王\",\"code\":\"520520\"}");
        jmsMessagingTemplate.convertAndSend("jmsListenerMethod", map);
        log.info("发送成功！");
        return "发送成功！";
    }


}
