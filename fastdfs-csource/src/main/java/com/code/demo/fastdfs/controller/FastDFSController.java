package com.code.demo.fastdfs.controller;


import com.code.demo.fastdfs.utils.fastdfs.FastDFSClient;
import com.code.demo.fastdfs.utils.fastdfs.FastDFSFile;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/fdfs")
public class FastDFSController {
    private static Logger logger = LoggerFactory.getLogger(FastDFSController.class);

    @ApiOperation(value = "/upload", notes = "上传图片", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "file", value = "文件", required = true, dataType = "file"),
    })
    @PostMapping("/upload")
    public ResponseEntity singleFileUpload(@RequestParam("file") MultipartFile file) {
        Map<String, Object> resultMap = new HashMap<>();
        if (file.isEmpty()) {
            resultMap.put("message", "请选择一个文件上传！");
            return ResponseEntity.status(400).body(resultMap);
        }
        try {
            String path = saveFile(file);
            resultMap.put("message", "成功上传： '" + file.getOriginalFilename() + "'");
            resultMap.put("path", "文件路径 url '" + path + "'");
            return ResponseEntity.ok(resultMap);
        } catch (Exception e) {
            logger.error("上传文件失败！", e);
            resultMap.put("message", "上传文件失败！");
            return ResponseEntity.status(400).body(resultMap);
        }
    }


    /**
     * 保存文件
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public String saveFile(MultipartFile multipartFile) throws IOException {
        String[] fileAbsolutePath = {};
        String fileName = multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream = multipartFile.getInputStream();
        if (inputStream != null) {
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
        try {
            fileAbsolutePath = FastDFSClient.upload(file);  //上传到 fastdfs
        } catch (Exception e) {
            logger.error("上传文件异常!", e);
        }
        if (fileAbsolutePath == null) {
            logger.error("上传文件失败,请重试!");
        }
        String path = FastDFSClient.getTrackerUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
        return path;
    }


}
