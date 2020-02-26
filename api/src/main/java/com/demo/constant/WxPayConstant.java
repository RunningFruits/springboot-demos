package com.demo.constant;

public class WxPayConstant {
    public static final String DOMAIN = "";

    public static final String APP_ID = "";

    public static final String APP_SECRET = "";

    public static final String APP_KEY = "";

    public static final String MCH_ID = "";  //商户号

    public static final String URL_UNIFIED_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    public static final String URL_NOTIFY = WxPayConstant.DOMAIN + "/wxpay/views/payInfo.jsp";

    public static final String TIME_FORMAT = "yyyyMMddHHmmss";

    public static final int TIME_EXPIRE = 2;  //单位是day
}
