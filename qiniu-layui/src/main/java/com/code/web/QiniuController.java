package com.code.web;

import com.code.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

@Controller
public class QiniuController {

    @Autowired
    private ImagesService imagesService;

    @RequestMapping("")
    public String lock() {
        return "lock";
    }

    @RequestMapping("index")
    public String index(Model model) {
        imagesService.query(model);
        return "index";
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(@RequestParam(value = "ids[]") Integer[] ids) {
        imagesService.deleteByIds(Arrays.asList(ids));
        return "1";
    }

    @RequestMapping("upload")
    @ResponseBody
    public String upload(MultipartFile file, HttpServletRequest request) throws IOException {
        imagesService.upload(file, request);
        return "1";
    }
}
