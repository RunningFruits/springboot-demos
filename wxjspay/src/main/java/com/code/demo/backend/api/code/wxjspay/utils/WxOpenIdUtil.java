package com.code.demo.backend.api.code.wxjspay.utils;


import com.alibaba.fastjson.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


public class WxOpenIdUtil {

    public static final String APP_SECRET = "";
    public static final String APPID = "";

    public static String GET_OPENID = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    /*通过code获取用户openid*/
    public static JSONObject getOpenid(String code) throws IOException {
        JSONObject jsonObject = null;
        String path = GET_OPENID.replace("APPID", APPID).replace("SECRET", APP_SECRET).replace("CODE", code);
        StringBuffer buffer = new StringBuffer();
        URL url = new URL(path);
        HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
        httpUrlConn.setRequestMethod("POST");
        httpUrlConn.setDoOutput(true);
        httpUrlConn.setDoInput(true);
        httpUrlConn.setUseCaches(false);
        // 将返回的输入流转换成字符串
        InputStream inputStream = httpUrlConn.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        // 释放资源
        inputStream.close();
        inputStream = null;
        httpUrlConn.disconnect();
        jsonObject = JSONObject.parseObject(buffer.toString());
        return jsonObject;
    }

}
