package com.code.demo.qiniulayui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.event.EventListener;

import java.io.IOException;

@SpringBootApplication
public class QiniuLayuiBoot extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(QiniuLayuiBoot.class);
    }

    @EventListener({ApplicationReadyEvent.class})
    public void applicationReadyEvent() {
        System.out.println("应用已经准备就绪 ... 启动浏览器");
        String url = "http://localhost:80/swagger-ui.html";
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(QiniuLayuiBoot.class, args);
    }

}
