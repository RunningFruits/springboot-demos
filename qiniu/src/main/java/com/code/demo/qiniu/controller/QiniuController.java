package com.code.demo.qiniu.controller;


import com.code.demo.qiniu.props.QiNiuProperties;
import com.code.demo.qiniu.utils.QiniuUtils;
import com.qiniu.storage.model.DefaultPutRet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Api(value = "/qiniu", description = "七牛云图片接口")
@RestController
@RequestMapping("/qiniu")
@Slf4j
public class QiniuController {

    @Autowired
    private QiniuUtils qiniuUtils;

    @Autowired
    QiNiuProperties qiNiuProperties;

    @ApiOperation(value = "uploadFile", notes = "HttpServletRequest方式上传一个或者多个图片文件",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "file", value = "文件", required = true, dataType = "file"),
    })
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity uploadFile(HttpServletRequest request) throws Exception {
        List<String> filePathList = new ArrayList<>();

        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();

            while (iter.hasNext()) {
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                String fileName = file.getOriginalFilename();
                String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;

                DefaultPutRet putRet = qiniuUtils.upload(file.getBytes(), newFileName);
                filePathList.add(qiNiuProperties.getBucketUrl() + putRet.key);
            }
        }

        return ResponseEntity.status(200).body(filePathList);
    }


//    /**
//     * @param files
//     * @return
//     * @throws Exception
//     */
//    @ApiOperation(value = "uploadFiles", notes = "MultipartFile方式上传多个图片文件",httpMethod = "POST")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "form", name = "files", value = "文件", required = true, dataType = "file"),
//    })
//    @RequestMapping(value = "uploadFiles", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity uploadFiles(@RequestParam(value = "files", required = false) MultipartFile[] files) throws Exception {
//        List<String> filePathList = new ArrayList<>();
//
//        if (files != null && files.length > 0) {
//            for (int i = 0; i < files.length; i++) {
//                MultipartFile file = files[i];
//                String fileName = file.getOriginalFilename();
//                //返回对象
//                log.info("上传文件" + fileName);
//                DefaultPutRet putRet = qiniuUtils.upload(file.getBytes(), fileName);
//                filePathList.add(qiNiuProperties.getBucketUrl() + putRet.key);
//            }
//        }
//        return ResponseEntity.status(200).body(filePathList);
//    }


}
