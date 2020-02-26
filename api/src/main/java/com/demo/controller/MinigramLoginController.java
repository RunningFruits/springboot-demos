package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.bean.UserInfo;
import com.demo.constant.UserConstant;
import com.demo.model.JsonResult;
import com.demo.model.ResultCode;
import com.demo.service.UserInfoService;
import com.demo.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MinigramLoginController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/me/login")
    public JsonResult user_login(
            @RequestParam("code") String code,
            @RequestParam("userHead") String userHead,
            @RequestParam("userName") String userName,
            @RequestParam("userGender") String userGender,
            @RequestParam("userCity") String userCity,
            @RequestParam("userProvince") String userProvince
    ) {
        // 配置请求参数
        Map<String, String> param = new HashMap<>();
        param.put("appid", UserConstant.WX_LOGIN_APPID);
        param.put("secret", UserConstant.WX_LOGIN_SECRET);
        param.put("js_code", code);
        param.put("grant_type", UserConstant.WX_LOGIN_GRANT_TYPE);
        // 发送请求
        String wxResult = HttpClientUtil.doGet(UserConstant.WX_LOGIN_URL, param);
        JSONObject jsonObject = JSONObject.parseObject(wxResult);
        // 获取参数返回的
        String session_key = jsonObject.get("session_key").toString();
        String open_id = jsonObject.get("openid").toString();
        // 根据返回的user实体类，判断用户是否是新用户，不是的话，更新最新登录时间，是的话，将用户信息存到数据库
        UserInfo user = userInfoService.selectByOpenId(open_id);
        if (user != null) {
            user.setLastLogin(new Date());
            userInfoService.updateById(user);
        } else {
            UserInfo insert_user = new UserInfo();
            insert_user.setHeadIcon(userHead);
            insert_user.setName(userName);
            insert_user.setGender(userGender);
            insert_user.setLastLogin(new Date());
            insert_user.setCity(userCity);
            insert_user.setProvince(userProvince);
            insert_user.setOpenid(open_id);
            // 添加到数据库
            Boolean flag = userInfoService.insert(insert_user);
            if (!flag) {
                return new JsonResult(ResultCode.FAIL);
            }
        }
        // 封装返回小程序
        Map<String, String> result = new HashMap<>();
        result.put("session_key", session_key);
        result.put("open_id", open_id);
        return new JsonResult(ResultCode.SUCCESS, result);
    }

}
