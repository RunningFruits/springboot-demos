package com.wx.restaurant.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wx.restaurant.enums.StatusEnum;
import com.wx.restaurant.mybatis.model.Coupon;
import com.wx.restaurant.mybatis.model.MyCoupon;
import com.wx.restaurant.service.CouponService;
import com.wx.restaurant.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "/index/coupon", description = "用户优惠券接口")
@RestController
@RequestMapping("/index/coupon")
@Slf4j
public class IndexController {

    @Autowired
    CouponService couponService;
    @Autowired
    UserService userService;

    /**
     * 获取优惠券接口
     */
    @ApiOperation(value = "/list", notes = "调用获取优惠券接口", httpMethod = "GET")
    @GetMapping("/list")
    public JSONArray list() {
        log.info(">>>>>>>>>>>>>>>>>调用获取优惠券接口");
        List<Coupon> couponList = couponService.couponList();
        String str = JSONObject.toJSONString(couponList);
        return JSONObject.parseArray(str);
    }

    /**
     * 用户领取优惠券接口
     *
     * @param openid 小程序openid
     * @param yhqid  优惠券id
     */
    @ApiOperation(value = "/receive", notes = "用户领取优惠券接口", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "openid", value = "openid", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "yhqid", value = "yhqid", required = true, dataType = "String"),
    })
    @GetMapping("/receive")
    public Map<String, Object> receive(
            @RequestParam("openid") String openid,
            @RequestParam("yhqid") Integer yhqid
    ) {
        Map<String, Object> result = new HashMap<>();
        //调用获取我的某种优惠券数量  TODO:此接口可以修改成count
        List<MyCoupon> myCouponList = userService.userList(openid, yhqid);
        if (myCouponList != null && myCouponList.size() >= 3) {
            result.put("msg", "同一种优惠券最多只能领取三张");
            result.put("status", StatusEnum.FAIL.getIndex());
        } else {
            try {
                log.info(">>>>>>>>>>>>>>>>>调用用户领取优惠券接口");
                userService.userSave(openid, yhqid);
                result.put("msg", "领取成功");
                result.put("status", StatusEnum.SUCCESS.getIndex());
            } catch (Exception e) {
                result.put("msg", "领取失败");
                result.put("status", StatusEnum.FAIL.getIndex());
            }
        }
        return result;
    }
}
