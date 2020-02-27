package com.hosiang.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(value = "com.huajie.config")
public class WebBoot {

	public static void main(String[] args) {
		SpringApplication.run(WebBoot.class, args);
	}
}
