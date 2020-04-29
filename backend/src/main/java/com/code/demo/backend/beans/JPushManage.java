package com.code.demo.backend.beans;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import com.code.demo.backend.utils.JPushUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JPushManage {

    public static final String pushPath = "xyaq";

    //在极光注册上传应用的 appKey 和 masterSecret
    public static final String appKey = "c49d3e9f16f34f761307a7f5";//必填，例如466f7032ac604e02fb7bda89
    public static final String masterSecret = "38f27564bafb9ddd269b6b53";//必填，每个应用都对应一个masterSecret

    public Integer sendPush(List<String> aliasList) {
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());
        return JPushUtil.sendToAliasList(jpushClient, aliasList, "这是一个IOS推送测试", "extra_key", "extra_value", "测试");
    }

}
