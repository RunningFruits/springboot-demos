package com.code.demo.fileupload.controller;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Api(value = "file", description = "文件上传")
@RestController
@RequestMapping("file")
@Slf4j
public class FileController {

    private String host = "http://brightereyer.com:8080/fileupload"; // 上传后的路径;
    private String remotePath = "/tmp/images/"; // 上传后的路径;

    @ApiOperation(value = "list", notes = "文件列表")
    @PostMapping(value = "list")
    public ResponseEntity list() {
        try {
            File imageDir = ResourceUtils.getFile(remotePath);

            return ResponseEntity.status(200).body(imageDir.list());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(200).body("文件列表");
    }

    /**
     * 实现文件上传
     */
    @ApiOperation(value = "uploadFile", notes = "文件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "file", value = "文件", required = true, dataType = "file")
    })
    @PostMapping(value = "uploadFile")
    public ResponseEntity uploadFile(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(400).body("文件不能为空！");
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        log.info(fileName + "-->" + size);

        File dest = new File(remotePath + "/" + fileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return ResponseEntity.status(200).body("文件上传成功！");
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
//            e.printStackTrace();
            return ResponseEntity.status(400).body("文件上传失败！");
        } catch (IOException e) {
            // TODO Auto-generated catch block
//            e.printStackTrace();
            return ResponseEntity.status(400).body("文件上传失败！");
        }
    }

    @ApiOperation(value = "uploadFiles", notes = "多文件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "files", value = "文件", required = true, dataType = "file")
    })
    @PostMapping(value = "uploadFiles")
    @ResponseBody
    public ResponseEntity uploadFiles(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("files");
        if (files.isEmpty()) {
            return ResponseEntity.status(400).body("文件上传失败！");
        }
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            int size = (int) file.getSize();
            log.info(fileName + "-->" + size);

            if (file.isEmpty()) {
                return ResponseEntity.status(400).body("文件上传失败！");
            } else {
                File dest = new File(remotePath + "/" + fileName);
                if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
//                    e.printStackTrace();
                    return ResponseEntity.status(400).body("文件上传失败！");
                }
            }
        }
        return ResponseEntity.status(200).body("文件上传成功！");
    }

    @ApiOperation(value = "download", notes = "文件下载")
    @RequestMapping("download")
    public String download(HttpServletResponse response,
                           @ApiParam(value = "outName", required = true) @RequestParam(value = "outName") String outName,
                           @ApiParam(value = "outPath", required = true) @RequestParam(value = "outPath") String outPath
    ) throws UnsupportedEncodingException {
        String filename = outName;
        String filePath = outPath;

        File file = new File(filePath + "/" + filename);
        if (file.exists()) { //判断文件父目录是否存在
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(filename, "UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            log.info("----------file download---" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return "";
    }

}
