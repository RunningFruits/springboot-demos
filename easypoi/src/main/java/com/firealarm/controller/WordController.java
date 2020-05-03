package com.firealarm.controller;

import com.firealarm.utils.ExportWordUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("word")
public class WordController {

    @RequestMapping("export")
    public void export(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = new HashMap<>();
        params.put("title", "这是标题");
        params.put("name", "李四");
        //这里是我说的一行代码
        ExportWordUtils.exportWord("word/export.docx", "G:/test", "aaa.docx", params, request, response);
    }

}
