package com.code.demo.api.controller.sms;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;


@Component
@Slf4j
public class Consumer {

    @Resource(name = "smsUtils")
    private SmsUtils smsUtils;

    //Java消息服务
    @JmsListener(destination = "jmsListenerMethod")
    public void jmsListener(Map<String, String> map) {
        try {
            SendSmsResponse smsResponse = smsUtils.sendSms(map);
            log.info("code:" + smsResponse.getCode() + "message:" + smsResponse.getMessage());
        } catch (ClientException e) {
            e.printStackTrace();
        }
        log.info(JSON.toJSONString(map));
    }


}
