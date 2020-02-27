package com.hosiang.dev.controller;


import com.demo.utils.FtpUtil;
import com.hosiang.dev.config.FtpConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/ftp")
@Slf4j(topic = "请求ftp服务器")
public class FtpController {

    @Autowired
    FtpConfig ftpConfig;

    @GetMapping("/upload")
    public String upload() {
        String fileName = "uploadfile.txt";
        boolean result = FtpUtil.ftpUpload(fileName, ftpConfig.getUrl(), ftpConfig.getPort(), ftpConfig.getUsername(), ftpConfig.getPassword(), ftpConfig.getLocalDir(), ftpConfig.getRemotePath());
        if (result) {
            log.info("=======上传文件" + fileName + "成功=======");
        } else {
            log.info("=======上传文件" + fileName + "失败=======");
        }
        return result ? "上传成功" : "上传失败";
    }

    @GetMapping("/download")
    public String download() {
        String fileName = "welcome.txt";
        boolean result = FtpUtil.ftpDownload(fileName, ftpConfig.getUrl(), ftpConfig.getPort(), ftpConfig.getUsername(), ftpConfig.getPassword(), ftpConfig.getRemotePath(), ftpConfig.getLocalDir());
        if (result) {
            log.info("=======下载文件" + fileName + "成功=======");
        } else {
            log.info("=======下载文件" + fileName + "失败=======");
        }
        return result ? "下载成功" : "下载失败";
    }


}