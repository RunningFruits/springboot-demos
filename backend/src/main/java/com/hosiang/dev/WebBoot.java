package com.hosiang.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.huajie.config", "com.hosiang.dev"})
public class WebBoot {

    public static void main(String[] args) {
        SpringApplication.run(WebBoot.class, args);
    }
}
