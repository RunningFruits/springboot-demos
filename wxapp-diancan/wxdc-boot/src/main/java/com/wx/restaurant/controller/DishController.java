package com.wx.restaurant.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wx.restaurant.mybatis.model.DishDTO;
import com.wx.restaurant.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "/dish", description = "菜品接口")
@RestController
public class DishController {

    @Autowired
    DishService dishService;

    /**
     * 获取菜单列表接口
     */
    @ApiOperation(value = "signup", notes = "用户注册接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userName", value = "验证码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "confirmPassword", value = "确认密码", required = true, dataType = "String"),
    })
    @GetMapping("/dish/list")
    public JSONArray dishList() {
        System.out.println(">>>>>>>>>>>>>>>>>调用获取菜单列表接口");
        List<DishDTO> dishDTOList = dishService.dishList();
        String str = JSONObject.toJSONString(dishDTOList);
        return JSONObject.parseArray(str);
    }
}
