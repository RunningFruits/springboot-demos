package com.demo.code.controller;

import com.demo.code.config.ServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("mainControl")
@Slf4j
public class MainControlController {

    @Autowired
    ServerConfig serverConfig;


}
