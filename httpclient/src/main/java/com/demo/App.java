package com.demo;

import com.demo.util.HttpClient;

import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        HttpClient client = new HttpClient();
        String result = doLogin();
        System.out.println(result);
    }

    public static String doLogin() {
        HttpClient client = new HttpClient();

        Map<String, Object> headerMap = new HashMap<String, Object>();
        headerMap.put("platform", "1");

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("phone", "18666147735");
        paramMap.put("password", "123456");
        paramMap.put("type", "1");

        String result = HttpClient.doPost("http://183.63.8.250:8081/dchiphotel/api/user/login", headerMap, paramMap);
        return result;
    }

}
