package com.code.demo.backend.controller.custom;


import com.code.demo.backend.service.custom.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    //跳转到上传文件的页面
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String goUpload() {
        return "custom/excel/upload";
    }


    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartFile file) throws Exception {
        excelService.getExcel(file);
        return "上传成功";
    }

}
