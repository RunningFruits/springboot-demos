package com.wx.restaurant.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wx.restaurant.mybatis.model.DishDTO;
import com.wx.restaurant.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "dish", description = "菜品接口")
@RestController
@RequestMapping(value = "dish")
@Slf4j
public class DishController {

    @Autowired
    DishService dishService;

    @ApiOperation(value = "/list", notes = "调用获取菜单列表接口", httpMethod = "GET")
    @GetMapping("/list")
    public JSONArray list() {
        log.info(">>>>>>>>>>>>>>>>>调用获取菜单列表接口");
        List<DishDTO> dishDTOList = dishService.dishList();
        String str = JSONObject.toJSONString(dishDTOList);
        return JSONObject.parseArray(str);
    }

}
