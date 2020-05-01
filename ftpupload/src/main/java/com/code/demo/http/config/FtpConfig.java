package com.code.demo.http.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;

import javax.servlet.MultipartConfigElement;

@Getter
@Component
public class FtpConfig {

    /**
     * ftp服务器地址
     */
    @Value("${ftp.host}")
    private String host;

    /**
     * ftp服务器端口
     */
    @Value("${ftp.port}")
    private int port;

    /**
     * ftp服务器用户名
     */
    @Value("${ftp.username}")
    private String username;

    /**
     * ftp服务器密码
     */
    @Value("${ftp.password}")
    private String password;

    /**
     * ftp服务器存放文件的路径
     */
    @Value("${ftp.remotePath}")
    private String remotePath;

//    /**
//     * 本地需要上传的文件的路径
//     */
//    @Value("${ftp.localDir}")
//    private String localDir;

//    /**
//     * 下载文件时，存放在本地的路径
//     */
//    @Value("${ftp.downDir}")
//    private String downDir;

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation("/app/tmp");
        return factory.createMultipartConfig();
    }

}
