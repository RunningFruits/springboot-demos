package com.code.demo.api.service;

import com.code.demo.api.JwtApiBootTests;
import com.code.demo.api.dataobject.ro.UserRegisterRO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends JwtApiBootTests {

    @Autowired
    UserService userService;

    @Test
    public void normalUserRegister() throws Exception {
        UserRegisterRO userRegisterRO = new UserRegisterRO();
        userRegisterRO.setUsername("张三");
        userRegisterRO.setPassword("1234568dasflasd8ya2asda");
        userService.normalUserRegister(userRegisterRO);
    }

}
