package com.code.demo.wxjspay.controller;


import com.code.demo.wxjspay.constant.WxPayConstant;
import com.code.demo.wxjspay.utils.wxpay.PayInfo;
import com.code.demo.wxjspay.utils.wxpay.WxPayUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(value = "/wxpage", description = "微信小程序支付")
@RestController
@Slf4j
public class PayController {

    @ApiOperation(value = "用户提交 答题结果、分数", notes = "用户提交 答题结果、分数")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "openid", value = "openid", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "parentName", value = "家长名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "score", value = "分数", required = true, dataType = "Integer")
    })
    @ResponseBody
    @RequestMapping(value = "/prepay", produces = "text/html;charset=UTF-8")
    public String prePay(
            @ApiParam(name = "code", required = true) String code,
            ModelMap model,
            HttpServletRequest request) {

        String content = null;
        Map map = new HashMap();
        ObjectMapper mapper = new ObjectMapper();

        boolean result = true;
        String info = "";

        log.error("======================================================");
        log.error("code: " + code);

        String openId = getOpenId(code);
        if (StringUtils.isBlank(openId)) {
            result = false;
            info = "获取到openId为空";
        } else {
            openId = openId.replace("\"", "").trim();

            String clientIP = IpUtil.getIpAddr(request);

            log.error("openId: " + openId + ", clientIP: " + clientIP);

            String randomNonceStr = RandomUtil.getRandomPsw(32);
            String prepayId = unifiedOrder(openId, clientIP, randomNonceStr);

            log.error("prepayId: " + prepayId);

            if (StringUtils.isBlank(prepayId)) {
                result = false;
                info = "出错了，未获取到prepayId";
            } else {
                map.put("prepayId", prepayId);
                map.put("nonceStr", randomNonceStr);
            }
        }

        try {
            map.put("result", result);
            map.put("info", info);
            content = mapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }


    private String getOpenId(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + WxPayConstant.APP_ID + "&secret=" + WxPayConstant.APP_SECRET + "&js_code=" + code + "&grant_type=authorization_code";

        HttpUtils httpUtil = new HttpUtils();
        try {

            HttpResult httpResult = httpUtil.doGet(url, null);

            if (httpResult.getStatus() == 200) {

                JsonParser jsonParser = new JsonParser();
                JsonObject obj = (JsonObject) jsonParser.parse(httpResult.getResponse());

                log.error("getOpenId: " + obj.toString());

                if (obj.get("errcode") != null) {
                    log.error("getOpenId returns errcode: " + obj.get("errcode"));
                    return "";
                } else {
                    return obj.get("openid").toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 调用统一下单接口
     *
     * @param openId
     */
    private String unifiedOrder(String openId, String clientIP, String randomNonceStr) {
        try {
            String url = WxPayConstant.URL_UNIFIED_ORDER;

            PayInfo payInfo = createPayInfo(openId, clientIP, randomNonceStr);
            String md5 = getSign(payInfo);
            payInfo.setSign(md5);

            log.error("md5 value: " + md5);

            String xml = WxPayUtil.payInfoToXML(payInfo);
            xml = xml.replace("__", "_").replace("<![CDATA[1]]>", "1");
            log.error(xml);

            StringBuffer buffer = HttpUtils.httpsRequest(url, "POST", xml);
            log.error("unifiedOrder request return body: \n" + buffer.toString());
            Map<String, String> result = WxPayUtil.parseXml(buffer.toString());


            String return_code = result.get("return_code");
            if (StringUtils.isNotBlank(return_code) && return_code.equals("SUCCESS")) {

                String return_msg = result.get("return_msg");
                if (StringUtils.isNotBlank(return_msg) && !return_msg.equals("OK")) {
                    //log.error("统一下单错误！");
                    return "";
                }

                String prepay_Id = result.get("prepay_id");
                return prepay_Id;

            } else {
                return "";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    private PayInfo createPayInfo(String openId, String clientIP, String randomNonceStr) {
        Date date = new Date();
        String timeStart = DateUtil.format(date, WxPayConstant.TIME_FORMAT);
        String timeExpire = DateUtil.formatTime(DateUtil.addDay(date, WxPayConstant.TIME_EXPIRE), WxPayConstant.TIME_FORMAT);

        String randomOrderId = WxPayUtil.getRandomOrderId();

        PayInfo payInfo = new PayInfo();
        payInfo.setAppid(WxPayConstant.APP_ID);
        payInfo.setMch_id(WxPayConstant.MCH_ID);
        payInfo.setDevice_info("WEB");
        payInfo.setNonce_str(randomNonceStr);
        payInfo.setSign_type("MD5");  //默认即为MD5
        payInfo.setBody("JSAPI支付测试");
        payInfo.setAttach("支付测试");
        payInfo.setOut_trade_no(randomOrderId);
        payInfo.setTotal_fee(1);
        payInfo.setSpbill_create_ip(clientIP);
        payInfo.setTime_start(timeStart);
        payInfo.setTime_expire(timeExpire);
        payInfo.setNotify_url(WxPayConstant.URL_NOTIFY);
        payInfo.setTrade_type("JSAPI");
        payInfo.setLimit_pay("no_credit");
        payInfo.setOpenid(openId);

        return payInfo;
    }

    private String getSign(PayInfo payInfo) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("appid=" + payInfo.getAppid())
                .append("&attach=" + payInfo.getAttach())
                .append("&body=" + payInfo.getBody())
                .append("&device_info=" + payInfo.getDevice_info())
                .append("&limit_pay=" + payInfo.getLimit_pay())
                .append("&mch_id=" + payInfo.getMch_id())
                .append("&nonce_str=" + payInfo.getNonce_str())
                .append("&notify_url=" + payInfo.getNotify_url())
                .append("&openid=" + payInfo.getOpenid())
                .append("&out_trade_no=" + payInfo.getOut_trade_no())
                .append("&sign_type=" + payInfo.getSign_type())
                .append("&spbill_create_ip=" + payInfo.getSpbill_create_ip())
                .append("&time_expire=" + payInfo.getTime_expire())
                .append("&time_start=" + payInfo.getTime_start())
                .append("&total_fee=" + payInfo.getTotal_fee())
                .append("&trade_type=" + payInfo.getTrade_type())
                .append("&key=" + WxPayConstant.APP_KEY);

        log.error("排序后的拼接参数：" + sb.toString());

        return WxPayUtil.getMD5(sb.toString().trim()).toUpperCase();
    }


}


