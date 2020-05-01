package cn.trunch.auth.controller;

import cn.trunch.auth.entity.Message;
import cn.trunch.auth.service.LoginService;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "/login",description = "登录接口")
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @ApiOperation(value = "", notes = "成功返回用户信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", dataType = "String", example = "1"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "用户密码", dataType = "String", example = "123")
    })
    @RequestMapping(value = "")
    @ResponseBody
    public Message loginById(@ApiParam(name = "userId", value = "用户id", required = true) String userId,
                             @ApiParam(name = "password", value = "用户密码", required = true) String password) {
        return loginService.checkPassword(userId, password);
    }

    @ApiOperation(value = "getUser", notes = "获取用户信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", dataType = "String", example = "1")
    })
    @RequestMapping(value = "getUser")
    @ResponseBody
    public Message getUser(@ApiParam(name = "userId", value = "用户id", required = true) String userId) {
        return loginService.getUserInfo(userId);
    }

}
