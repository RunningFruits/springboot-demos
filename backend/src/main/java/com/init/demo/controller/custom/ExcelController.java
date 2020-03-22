package com.init.demo.controller.custom;


import com.init.demo.service.custom.ExcelService;
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
    @RequestMapping(path = "/upload", method = RequestMethod.GET)
    public String goUpload() {
        //跳转到 templates 目录下的 upload.html
        return "custom/excel/upload";
    }

//    @ApiOperation(value = "导入Excel表", notes = "", httpMethod = "POST")
    @RequestMapping(path = "/upload/excel", method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartFile file) throws Exception {
        excelService.getExcel(file);
        return "上传成功";
    }

}
