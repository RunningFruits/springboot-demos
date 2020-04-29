package com.code.demo.backend.controller.custom;

import com.code.demo.backend.pojo.custom.PushBean;
import com.code.demo.backend.service.custom.JiGuangPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class JiguangController {

    @Autowired
    JiGuangPushService jiGuangPushService;

    /**
     * 群推，广播
     *
     * @param title   推送标题
     * @param content 推送内容
     * @return
     */
    @PostMapping("/pushAll")
    public ResponseEntity pushAll(@RequestParam String title, @RequestParam String content) {
        PushBean pushBean = new PushBean();
        pushBean.setTitle(title);
        pushBean.setAlert(content);
        boolean flag = jiGuangPushService.pushAndroid(pushBean);
        return ResponseEntity.ok(flag);
    }

}
