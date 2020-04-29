package com.code.demo.backend.controller;

import com.code.demo.backend.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminManager")
public class AdminManagerController {
    @Autowired
    private SessionUtil sessionUtil;


    @GetMapping("")
    public String index() {
        return "adminManager";
    }

}
