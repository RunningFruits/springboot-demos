package com.code.demo.http.controller;


import com.code.demo.http.service.StorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Api(value = "/ftp", description = "请求ftp服务器")
@RestController
@RequestMapping(value = "/ftp")
@Slf4j(topic = "请求ftp服务器")
public class FtpController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StorageService storageService;

    @Value("${ftp.remotePath}")
    String remoteDirPath;


    @ApiOperation(value = "/uploadFile", notes = "上传文件", httpMethod = "POST")
    @PostMapping("/uploadFile")
    public ResponseEntity uploadFile(
            @ApiParam(name = "file", required = true) @RequestParam(value = "file") MultipartFile file
    ) {
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
    public ResponseEntity uploadDir(
            @ApiParam(name = "uploadDirPath", required = true) @RequestParam(value = "uploadDirPath") String uploadDirPath
    ) {
        boolean resultFlag = storageService.uploadDir(uploadDirPath, remoteDirPath);
        if (resultFlag) {
            logger.info("Upload Local Dir: {} successful", uploadDirPath);
            return ResponseEntity.status(200).body(uploadDirPath);
        } else {
            logger.info("Upload Local Dir: {} failed", uploadDirPath);
            return ResponseEntity.status(400).body(uploadDirPath);
        }
    }

    @ApiOperation(value = "/downloadFile", notes = "下载文件", httpMethod = "POST")
    @PostMapping("/downloadFile")
    public ResponseEntity downloadFile(
            @ApiParam(name = "remoteFileName", required = true) @RequestParam(value = "remoteFileName") String remoteFileName,
            @ApiParam(name = "localFilePath", required = true) @RequestParam(value = "localFilePath") String localFilePath
    ) {
        boolean resultFlag = storageService.downloadFile(remoteDirPath, remoteFileName, localFilePath);
        if (resultFlag) {
            String file = remoteDirPath + "/" + remoteFileName;
            logger.info("download file: {} successful", file);

            return ResponseEntity.status(200).body(file);
        } else {
            String file = remoteDirPath + "/" + remoteFileName;
            logger.info("download file: {} failed", file);

            return ResponseEntity.status(400).body(file);
        }
    }

    @ApiOperation(value = "/downloadDir", notes = "下载目录", httpMethod = "POST")
    @PostMapping("/downloadDir")
    public ResponseEntity downloadDir(
            @ApiParam(name = "localDirPath", required = true) @RequestParam(value = "localDirPath") String localDirPath
    ) {
        boolean resultFlag = storageService.downloadDirectory(remoteDirPath, localDirPath);
        if (resultFlag) {
            logger.info("download directory: {} successful", remoteDirPath);

            return ResponseEntity.status(200).body(remoteDirPath);
        } else {
            logger.info("download directory: {} failed", remoteDirPath);

            return ResponseEntity.status(400).body(remoteDirPath);
        }
    }

    @ApiOperation(value = "/deleteFile", notes = "删除文件", httpMethod = "POST")
    @PostMapping("/deleteFile")
    public ResponseEntity deleteFile(
            @ApiParam(name = "fileName", required = true) @RequestParam(value = "fileName") String fileName
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
