package com.demo.controller;

import com.demo.bean.UserInfo;
import com.demo.dao.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserInfoDao userInfoDao;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/addRegister")
    public String register(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");

        if (password.equals(password2)) {
            UserInfo user = new UserInfo();
            user.setUsername(userName);
            user.setPassword(password);
            userInfoDao.save(user);
            return "index";
        } else {
            return "register";
        }
    }

    @RequestMapping("/addlogin")
    public String addLogin(HttpServletRequest request, ModelMap modelMap) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        UserInfo userInfo = userInfoDao.findByUsernameAndPassword(userName, password);
        modelMap.addAttribute("user", userInfo);
        String str = "";
        if (userInfo != null) {
            str = "index";
        } else {
            str = "login";
        }
        return str;
    }

}
