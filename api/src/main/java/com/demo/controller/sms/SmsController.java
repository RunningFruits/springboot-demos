package com.demo.controller.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController("sms")
public class SmsController {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("/send")
    public String send() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("mobile", "18900513972");
        map.put("template_code", "SMS_152545288");
        map.put("sign_name", "wzdoo");
        map.put("param", "{\"name\":\"隔壁老王\",\"code\":\"520520\"}");

        jmsMessagingTemplate.convertAndSend("jmsListenerMethod", map);

        System.out.println("发送成功！");
        return "发送成功！";
    }


}
