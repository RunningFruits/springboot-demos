package com.code.demo.ftpupload.controller;


import com.code.demo.ftpupload.service.StorageService;
import com.code.demo.ftpupload.utils.FtpUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.URLDecoder;

import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/ftp")
@Slf4j(topic = "请求ftp服务器")
public class FtpController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StorageService storageService;

    private String remoteDirPath = "/tmp/images/";


    /**
     * 上传文件
     */
    @GetMapping("/uploadFile")
    public void uploadFile(@RequestParam(value = "file") MultipartFile file) {
        String remoteFileName = file.getName();
        String uploadFilePath = null;
        try {
            uploadFilePath = file.getResource().getFile().getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean resultFlag = storageService.uploadFile(remoteDirPath, remoteFileName, uploadFilePath);
        if (resultFlag) {
            logger.info("Upload Local File: {} successful", uploadFilePath);
        } else {
            logger.info("Upload Local File: {} failed", uploadFilePath);
        }
    }

    /**
     * 上传目录
     */
    @GetMapping("/uploadDir")
    public void uploadDir() {
        String uploadDirPath = null;
        try {
            uploadDirPath = ResourceUtils.getFile("classpath:data").getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        boolean resultFlag = storageService.uploadDir(uploadDirPath, remoteDirPath);
        if (resultFlag) {
            logger.info("Upload Local Dir: {} successful", uploadDirPath);
        } else {
            logger.info("Upload Local Dir: {} failed", uploadDirPath);
        }
    }

    /**
     * 下载文件
     */
    @GetMapping("/downloadFile")
    public void downloadFile(@RequestParam(value = "file") MultipartFile file) {
        String remoteFileName = "中文测试.txt";
        String localFilePath = "F://aa";
        boolean resultFlag = storageService.downloadFile(remoteDirPath, remoteFileName, localFilePath);
        if (resultFlag) {
            logger.info("download file: {} successful", remoteDirPath + "/" + remoteFileName);
        } else {
            logger.info("download file: {} failed", remoteDirPath + "/" + remoteFileName);
        }
    }

    /**
     * 下载目录
     */
    @GetMapping("/downloadDir")
    public void downloadDir() {
        String localDirPath = "F://bb";
        boolean resultFlag = storageService.downloadDirectory(remoteDirPath, localDirPath);
        if (resultFlag) {
            logger.info("download directory: {} successful", remoteDirPath);
        } else {
            logger.info("download directory: {} failed", remoteDirPath);
        }
    }

    /**
     * 删除文件
     */
    @GetMapping("/deleteFile")
    public void deleteFile() {
        String remoteFilePath = remoteDirPath + "中文测试.txt";
        boolean resultFlag = storageService.deleteFile(remoteFilePath);
        if (resultFlag) {
            logger.info("delete file: {} successful", remoteFilePath);
        } else {
            logger.info("delete file: {} failed", remoteFilePath);
        }
    }

    /**
     * 递归删除目录
     */
    @GetMapping("/deleteDir")
    public void deleteDir() {
        boolean resultFlag = storageService.deleteDirectory(remoteDirPath);
        if (resultFlag) {
            logger.info("delete directory: {} successful", remoteDirPath);
        } else {
            logger.info("delete directory: {} failed", remoteDirPath);
        }
    }

}
