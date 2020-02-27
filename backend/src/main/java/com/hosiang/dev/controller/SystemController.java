package com.hosiang.dev.controller;

import java.util.Arrays;
import java.util.Map;

import com.hosiang.dev.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/system")
public class SystemController {

    @GetMapping("/")
    public String index() {
        return "extra";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/test")
    public String test(Map<String, Object> model) {
        model.put("text", "hello jsp");
        model.put("list", Arrays.asList(1, 2, 3, 4, 5));
        model.put("text", "hello jsp");
        return "orther/test";
    }
}
