package com.code.demo.api.controller.sms;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;


@Component
public class Consumer {

    @Resource(name = "smsUtils")
    private SmsUtils smsUtils;

    //Java消息服务
    @JmsListener(destination = "jmsListenerMethod")
    public void jmsListener(Map<String, String> map) {
        try {
            SendSmsResponse smsResponse = smsUtils.sendSms(map);
            System.out.println("code:" + smsResponse.getCode() + "message:" + smsResponse.getMessage());
        } catch (ClientException e) {
            e.printStackTrace();
        }
        System.out.println(map);
    }


}
