package cn.resulte.controller;

import cn.resulte.xunfei.XunfeiToVoice;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Api("接口")
@Controller
@Slf4j
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @ResponseBody
    @PostMapping("/convert")
    public String convert(HttpServletRequest request, HttpServletResponse response) {
        String message = request.getParameter("message");
        String index = request.getParameter("index");

        String path = null;
        try {
            path = XunfeiToVoice.changeToVoice(message, Integer.parseInt(index));
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("path:");
        log.info(path);
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        return path;
    }

}
