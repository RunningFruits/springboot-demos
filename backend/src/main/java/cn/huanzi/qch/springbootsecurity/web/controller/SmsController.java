package cn.huanzi.qch.springbootsecurity.web.controller;


import cn.huanzi.qch.springbootsecurity.util.SmsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/message")
public class SmsController {

    @RequestMapping("/send")
    public String send() {
        return "sms/index";
    }

    @RequestMapping(value = "/sendCode",method = RequestMethod.POST)
    public void sendCode(String number, HttpServletResponse response) {
        SmsUtil messageUtil = new SmsUtil();
        try {
            response.getWriter().write(messageUtil.SendMessage(number));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

