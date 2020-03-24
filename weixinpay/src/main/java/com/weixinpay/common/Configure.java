package com.weixinpay.common;

public class Configure {

    //小程序ID
//	private static String appID = "你的小程序id";
    private static String appID = "wx78615aad1423ccf1";
    //	private static String secret = "你的小程序的secret";
    private static String secret = "2e89e83522c2dd868b67cbe3dcaf5f1f";

    //商户号
    //	private static String mch_id = "你的商户号";
    private static String mch_id = "1507760871";
    //	private static String key = "你的商户的api秘钥";
    private static String key = "zhuhaidchip2018companycontacters";


    public static String getSecret() {
        return secret;
    }

    public static void setSecret(String secret) {
        Configure.secret = secret;
    }

    public static String getKey() {
        return key;
    }

    public static void setKey(String key) {
        Configure.key = key;
    }

    public static String getAppID() {
        return appID;
    }

    public static void setAppID(String appID) {
        Configure.appID = appID;
    }

    public static String getMch_id() {
        return mch_id;
    }

    public static void setMch_id(String mch_id) {
        Configure.mch_id = mch_id;
    }

}
