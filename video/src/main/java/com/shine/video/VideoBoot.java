package com.shine.video;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@SpringBootApplication
public class VideoBoot {

    public static void main(String[] args) {
        SpringApplication.run(VideoBoot.class, args);
    }

}
