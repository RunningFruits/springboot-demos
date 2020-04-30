package com.code.demo.websocket.wxjspay.controller;

import com.code.demo.websocket.wxjspay.constant.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/")
public class IndexController {

    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("payURL", Constant.PAY_URL);
        return "index";
    }

}
