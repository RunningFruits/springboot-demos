package com.code.demo.opencv.controller;

import com.code.demo.opencv.entity.FaceEntity;
import com.code.demo.opencv.service.FaceDetectionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;


@RestController
@RequestMapping(value = "/faceDetect")
public class FaceDetectController {

    @Autowired
    private FaceDetectionService faceDetectionService;

    @ApiOperation(value = "/image", httpMethod = "POST", notes = "验证图片")
    @ResponseBody
    @RequestMapping(value = "/image", method = RequestMethod.POST, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity image(
            @ApiParam(required = true, name = "file", value = "图片文件")
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (!validateImage(file)) {
            return ResponseEntity.status(HttpStatus.OK).body(new byte[1]);
        }
        ResponseEntity responseEntity = new ResponseEntity(faceDetectionService.detectFace(file).toImage(), HttpStatus.OK);
        return responseEntity;
    }

    @ApiOperation(value = "/json", httpMethod = "POST", notes = "验证图片")
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity json(
            @ApiParam(required = true, name = "file", value = "图片文件")
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (!validateImage(file)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());
        }
        ResponseEntity responseEntity = new ResponseEntity(faceDetectionService.detectFace(file).toList(), HttpStatus.OK);
        return responseEntity;
    }


    private Boolean validateImage(MultipartFile image) {
        return image.getContentType().equals("image/jpeg");
    }


}
