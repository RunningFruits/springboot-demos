package com.wx.restaurant.controller;

import com.alibaba.fastjson.JSONObject;
import com.wx.restaurant.enums.ReserveStatusEnum;
import com.wx.restaurant.enums.StatusEnum;
import com.wx.restaurant.mybatis.model.Reserve;
import com.wx.restaurant.service.ReserveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "/reserve", description = "预约接口")
@RequestMapping("/reserve")
@RestController
@Slf4j
public class ReserveController {

    @Autowired
    ReserveService reserveService;

    /**
     * 用户预约接口
     *
     * @param data 小程序 openId 预约时间 reserveDate
     */
    @ApiOperation(value = "/insert", notes = "用户预约接口", httpMethod = "POST")
    @ApiImplicitParams({
            //提交的参数是这个对象的一个json
            @ApiImplicitParam(paramType = "body", dataType = "MessageParam", name = "data", value = "信息参数", required = true),
    })
    @PostMapping("/insert")
    public Map<String, Object> reserveSave(@RequestBody JSONObject data) {
        log.info(">>>>>>>>>>>>>>>调用用户预约接口");
        String str = JSONObject.toJSONString(data);
        Reserve reserve = JSONObject.parseObject(str, Reserve.class);
        return reserveService.reserveSave(reserve);
    }

    /**
     * 用户预约需等待人数接口
     *
     * @param reserveDate 预约时间
     */
    @ApiOperation(value = "/wait/count", notes = "用户预约需等待人数接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "reserve_date", value = "预约时间：yyyy-MM-dd", required = true),
    })
    @GetMapping("/wait/count")
    public Map<String, Object> waitCount(@RequestParam("reserve_date") String reserveDate) {
        log.info(">>>>>>>>>>>>>>调用用户预约需等待人数接口");
        Map<String, Object> result = new HashMap<>();
        Integer count = reserveService.reserveList(reserveDate, ReserveStatusEnum.WAIT.getIndex());
        result.put("count", count);
        result.put("status", StatusEnum.SUCCESS.getIndex());
        return result;
    }

}
