package com.wx.restaurant.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.wx.restaurant.enums.StatusEnum;
import com.wx.restaurant.util.HttpRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.xerces.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;

@Api(value = "/wxlogin", description = "用户优惠券接口")
@RestController
@RequestMapping("/wxlogin")
@Slf4j
public class WxLoginController {

    @Value(value = "${appid}")
    String appid;
    @Value(value = "${secret}")
    String secret;
    @Value(value = "${grant_type}")
    String grant_type;

    /**
     * 获取小程序openid
     *
     * @param code 小程序code换取openid和sessionKey
     */
    @ApiOperation(value = "/open_id/get", notes = "获取小程序openid", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "code", value = "code", required = true, dataType = "String"),
    })
    @GetMapping("/open_id/get")
    @ResponseBody
    public Object getOpenId(@RequestParam("code") String code) {
        log.info(">>>>>>>>>>>>>>调用获取小程序openid接口");
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=" + grant_type;
        log.info(url);
        String data = HttpRequest.get(url);
        return JSON.parse(data);
    }

    /**
     * 小程序授权手机号解密 此接口本项目展示没用
     *
     * @param encrypdata
     * @param ivdata
     * @param sessionkey
     */
    @ApiOperation(value = "/user_phone/get", notes = "小程序授权手机号解密", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "encrypdata", value = "encrypdata", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "sessionkey", value = "sessionkey", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "ivdata", value = "ivdata", required = true, dataType = "String"),
    })
    @GetMapping("/user_phone/get")
    @ResponseBody
    public JSONObject getUserPhone(@RequestParam("encrypdata") String encrypdata,
                                   @RequestParam("ivdata") String ivdata,
                                   @RequestParam("sessionkey") String sessionkey) {
        log.info(">>>>>>>>>>>>>>小程序授权手机号解密");
        JSONObject result = new JSONObject();
        byte[] encrypData = Base64.decode(encrypdata);
        byte[] ivData = Base64.decode(ivdata);
        byte[] sessionKey = Base64.decode(sessionkey);
        String str = "";
        try {
            str = decrypt(sessionKey, ivData, encrypData);
            result.put("data", str);
            result.put("status", StatusEnum.SUCCESS.getIndex());
        } catch (Exception e) {
            result.put("errmsg", e);
            result.put("status", StatusEnum.FAIL.getIndex());
        }
        log.info(str);
        return result;

    }

    /**
     * 解密后的手机号
     *
     * @param key
     * @param iv
     * @param encData
     * @return
     * @throws Exception
     */
    private static String decrypt(byte[] key, byte[] iv, byte[] encData) throws Exception {
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        //解析解密后的字符串
        return new String(cipher.doFinal(encData), "UTF-8");
    }

}
