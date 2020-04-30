package com.code.demo.api.controller;

import com.code.demo.api.dataobject.model.User;
import com.code.demo.api.dataobject.ro.UserLoginRO;
import com.code.demo.api.dataobject.ro.UserRegisterRO;
import com.code.demo.api.dataobject.vo.ResultVO;
import com.code.demo.api.enums.ResultEnum;
import com.code.demo.api.service.UserService;
import com.code.demo.api.utils.ROValidUtil;
import com.code.demo.api.utils.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;


@Api(value = "/user",description = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param userLoginRO
     * @return
     */
    @ApiOperation(value="/login", notes="用户登录")
    @PostMapping("/login")
    public ResultVO login(@RequestBody UserLoginRO userLoginRO) {
        Map<String, Object> loginMap = userService.normalUserLogin(userLoginRO);
        if (loginMap.get("error") == null) {
            return ResultVOUtil.returnSuccess(loginMap);
        }
        return ResultVOUtil.returnFail((ResultEnum) loginMap.get("error"));
    }

    /**
     * 用户注册
     *
     * @param userRegisterRO
     * @return
     */
    @ApiOperation(value="/register", notes="用户注册")
    @PostMapping("/register")
    public ResultVO register(@Valid @RequestBody UserRegisterRO userRegisterRO, BindingResult result) {
        ROValidUtil.valid(result);
        User user = userService.normalUserRegister(userRegisterRO);
        if (user != null) {
            return ResultVOUtil.returnSuccess();
        } else {
            return ResultVOUtil.returnFail();
        }
    }

}
