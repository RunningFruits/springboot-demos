package com.init.demo.controller;

import com.init.demo.entity.system.SystemUser;
import com.init.demo.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private SessionUtil sessionUtil;

    @GetMapping("")
    public String index() {
        return "index";
    }

    @ResponseBody
    @GetMapping("/getUser")
    public SystemUser getCurrentUser() {
        return sessionUtil.getCurrentUser();
    }
}
