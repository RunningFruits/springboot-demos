package com.code.demo.api.controller;

import com.code.demo.api.dao.UserInfoRepository;
import com.code.demo.api.entity.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(value = "/user", description = "用户接口")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserInfoRepository userInfoRepository;

    @ApiOperation(value = "signup", notes = "用户注册接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userName", value = "验证码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "confirmPassword", value = "确认密码", required = true, dataType = "String"),
    })
    @RequestMapping("/signup")
    public ResponseEntity signup(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (password.equals(confirmPassword)) {
            UserInfo userInfo = new UserInfo();

            userInfo.setUsername(userName);
            userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
            userInfoRepository.save(userInfo);

            return ResponseEntity.ok("注册成功");
        } else {
            return ResponseEntity.status(400).body("注册失败");
        }
    }

    @RequestMapping("/login")
    public ResponseEntity login(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        String encode = bCryptPasswordEncoder.encode(password);

        UserInfo userInfo = userInfoRepository.findByUsernameAndPassword(userName, encode);
        if (userInfo != null) {
            return ResponseEntity.status(200).body("登录成功");
        }
        return ResponseEntity.status(400).body("登录失败");
    }

}
