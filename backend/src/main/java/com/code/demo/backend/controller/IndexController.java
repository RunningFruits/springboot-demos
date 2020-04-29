package com.code.demo.backend.controller;

import com.code.demo.backend.entity.system.SystemUser;
import com.code.demo.backend.utils.SessionUtil;
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
