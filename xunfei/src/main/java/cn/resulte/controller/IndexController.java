package cn.resulte.controller;

import cn.resulte.xunfei.XunfeiToVoice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        log.info(index);

        String path = null;
        try {
            path = XunfeiToVoice.changeToVoice(message, Integer.parseInt(index));
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info(path);
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        /*
        try {
            PrintWriter out = response.getWriter();
            out.println(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        return path;
    }

}
