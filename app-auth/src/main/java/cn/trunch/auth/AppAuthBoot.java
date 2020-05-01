package cn.trunch.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.trunch.auth.dao")
public class AppAuthBoot {

    public static void main(String[] args) {
        SpringApplication.run(AppAuthBoot.class, args);
    }

}
