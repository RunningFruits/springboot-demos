package com.code.demo.ftpupload.controller;


import com.code.demo.ftpupload.service.StorageService;
import com.code.demo.ftpupload.utils.FtpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.URLDecoder;

import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "/ftp", description = "请求ftp服务器")
@RestController
@RequestMapping(value = "/ftp")
@Slf4j(topic = "请求ftp服务器")
public class FtpController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StorageService storageService;

    private String remoteDirPath = "/tmp/images/";


    @ApiOperation(value = "/uploadFile", notes = "上传文件", httpMethod = "POST")
    @PostMapping("/uploadFile")
    public ResponseEntity uploadFile(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(400).body("文件不能为空！");
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        log.info(fileName + "-->" + size);

        File dest = new File(remoteDirPath + "/" + fileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }

        try {
            file.transferTo(dest); //保存文件

            boolean resultFlag = storageService.uploadFile(remoteDirPath, fileName, dest.getAbsolutePath());

            return ResponseEntity.status(200).body(resultFlag);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            return ResponseEntity.status(400).body("文件上传失败！");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            return ResponseEntity.status(400).body("文件上传失败！");
        }
    }

    @ApiOperation(value = "/uploadDir", notes = "上传目录", httpMethod = "POST")
    @PostMapping("/uploadDir")
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

    @ApiOperation(value = "/downloadFile", notes = "下载文件", httpMethod = "POST")
    @PostMapping("/downloadFile")
    public void downloadFile(
            @RequestParam(value = "remoteFileName") String remoteFileName,
            @RequestParam(value = "localFilePath") String localFilePath
    ) {
        boolean resultFlag = storageService.downloadFile(remoteDirPath, remoteFileName, localFilePath);
        if (resultFlag) {
            logger.info("download file: {} successful", remoteDirPath + "/" + remoteFileName);
        } else {
            logger.info("download file: {} failed", remoteDirPath + "/" + remoteFileName);
        }
    }

    @ApiOperation(value = "/downloadDir", notes = "下载目录", httpMethod = "POST")
    @PostMapping("/downloadDir")
    public void downloadDir(
            @RequestParam(value = "localDirPath") String localDirPath
    ) {
        boolean resultFlag = storageService.downloadDirectory(remoteDirPath, localDirPath);
        if (resultFlag) {
            logger.info("download directory: {} successful", remoteDirPath);
        } else {
            logger.info("download directory: {} failed", remoteDirPath);
        }
    }

    @ApiOperation(value = "/deleteFile", notes = "删除文件", httpMethod = "POST")
    @PostMapping("/deleteFile")
    public ResponseEntity deleteFile(
            @RequestParam(value = "fileName") String fileName
    ) {
        String remoteFilePath = remoteDirPath + fileName;
        boolean resultFlag = storageService.deleteFile(remoteFilePath);
        if (resultFlag) {
            logger.info("delete file: {} successful", remoteFilePath);
            return ResponseEntity.status(200).body(remoteFilePath + "删除成功！");
        } else {
            logger.info("delete file: {} failed", remoteFilePath);
            return ResponseEntity.status(400).body(remoteFilePath + "删除失败！");
        }
    }


    @ApiOperation(value = "/deleteDir", notes = "递归删除目录", httpMethod = "POST")
    @PostMapping("/deleteDir")
    public ResponseEntity deleteDir() {
        boolean resultFlag = storageService.deleteDirectory(remoteDirPath);
        if (resultFlag) {
            logger.info("delete directory: {} successful", remoteDirPath);
            return ResponseEntity.status(200).body("删除成功！");
        } else {
            logger.info("delete directory: {} failed", remoteDirPath);
            return ResponseEntity.status(400).body("删除失败！");
        }
    }


}
