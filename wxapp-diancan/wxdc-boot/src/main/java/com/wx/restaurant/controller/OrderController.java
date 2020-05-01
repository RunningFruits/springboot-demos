package com.wx.restaurant.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wx.restaurant.enums.StatusEnum;
import com.wx.restaurant.mybatis.model.Balance;
import com.wx.restaurant.mybatis.model.ShopCart;
import com.wx.restaurant.service.BalanceService;
import com.wx.restaurant.util.RandomCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "/balance", description = "订单接口")
@RequestMapping("/balance")
@RestController
@Slf4j
public class OrderController {

    @Autowired
    BalanceService balanceService;

    /**
     * 用户下单接口
     *
     * @param data 购物列表数据,openid,购物总价
     */
    @ApiOperation(value = "/insert", notes = "用户下单接口", httpMethod = "POST")
    @ApiImplicitParams({
            //提交的参数是这个对象的一个json
            @ApiImplicitParam(paramType = "body", dataType = "MessageParam", name = "data", value = "信息参数", required = true),
    })
    @RequestMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> balanceSave(@RequestBody JSONObject data) {
        Map<String, Object> result = new HashMap<>();
        try {

//            //赋值小程序openid
//            balance.setOpenId(data.getString("open_id"));
//            //赋值购物总价
//            balance.setTotalPrice(data.getDouble("total_price"));

            String strBalance = JSONObject.toJSONString(data);
            Balance balance = JSONObject.parseObject(strBalance, Balance.class);
            //转化购物车列表
            String str = data.getJSONArray("cartList").toJSONString();
            List<ShopCart> shopList = JSONObject.parseArray(str, ShopCart.class);
            log.info(">>>>>>>>>>>>>>>>>>>>调用用户下单接口");
            balanceService.balanceSave(balance, shopList);
            result.put("status", StatusEnum.SUCCESS.getIndex());
            result.put("order_id", balance.getId());
        } catch (Exception e) {
            result.put("status", StatusEnum.FAIL.getIndex());
            result.put("errmsg", "下单失败");
        }
        return result;
    }

    /**
     * 用户付款接口  TODO:同时要增加销量等信息
     *
     * @param balanceId 订单号
     */
    @ApiOperation(value = "/update", notes = "用户付款接口", httpMethod = "GET")
    @ApiImplicitParams({
            //提交的参数是这个对象的一个json
            @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "order_id", value = "订单id", required = true),
    })
    @GetMapping("/update")
    public Map<String, Object> balanceSave(@RequestParam("order_id") Integer balanceId) {
        Map<String, Object> result = new HashMap<>();
        try {
            log.info(">>>>>>>>>>>>>>>>>>>>调用用户付款接口");
            balanceService.balanceUpdate(balanceId);
            result.put("status", StatusEnum.SUCCESS.getIndex());
            //付款成功则随机生成一个三位数的订单编号
            result.put("dining_id", RandomCode.getRandomCode());
        } catch (Exception e) {
            result.put("status", StatusEnum.FAIL.getIndex());
            result.put("errmsg", "付款失败");
        }
        return result;
    }

    /**
     * 用户历史订单接口
     *
     * @param openId 小程序openid
     */
    @ApiOperation(value = "/list", notes = "用户历史订单接口", httpMethod = "GET")
    @ApiImplicitParams({
            //提交的参数是这个对象的一个json
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "open_id", value = "open_id", required = true),
    })
    @GetMapping("/list")
    public JSONArray balanceSave(@RequestParam("open_id") String openId) {
        log.info(">>>>>>>>>>>>>>>>>调用用户历史订单接口");
        List<Balance> balanceList = balanceService.balanceList(openId);
        String str = JSONObject.toJSONString(balanceList);
        return JSONObject.parseArray(str);
    }

}
