package com.wx.restaurant.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wx.restaurant.mybatis.model.Balance;
import com.wx.restaurant.mybatis.model.DishDTO;
import com.wx.restaurant.mybatis.model.ShopCart;
import com.wx.restaurant.service.BalanceService;
import com.wx.restaurant.service.DishService;
import com.wx.restaurant.service.ResTableService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @param
 * @author
 * @date 2018/8/11 17:41
 * <p>
 * TODO:测试接口 等项目完成后删掉
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    ResTableService resTableService;
    @Autowired
    DishService dishService;
    @Autowired
    BalanceService balanceService;

    @ApiOperation(value = "/table/get", notes = "", httpMethod = "GET")
    @GetMapping("/table/get")
    public JSONObject getTable() {
        String str = JSONObject.toJSONString(resTableService.getTable());
        return JSONObject.parseObject(str);
    }

    @ApiOperation(value = "/dish/list", notes = "菜单列表", httpMethod = "GET")
    @GetMapping("/dish/list")
    public JSONArray dishList() {
        List<DishDTO> dishDTOList = dishService.dishList();
        String str = JSONObject.toJSONString(dishDTOList);
        return JSONObject.parseArray(str);
    }

    @ApiOperation(value = "/balance/insert", notes = "加入购物车", httpMethod = "POST")
    @ApiImplicitParams({
            //提交的参数是这个对象的一个json
            @ApiImplicitParam(paramType = "body", dataType = "MessageParam", name = "data", value = "信息参数", required = true),
    })
    @PostMapping("/balance/insert")
    public Map<String, Object> balanceSave(@RequestBody JSONObject data) {
        Map<String, Object> map = new HashMap<>();
        Balance balance = new Balance();
        balance.setOpenId(data.getString("open_id"));
        balance.setTotalPrice(data.getDouble("total_price"));
        String str = data.getJSONArray("cartList").toJSONString();
        List<ShopCart> shopList = JSONObject.parseArray(str, ShopCart.class);
        balanceService.balanceSave(balance, shopList);
        map.put("data", balance);
        return map;
    }


}
