package cn.huanzi.qch.springbootsecurity.util;


import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class SmsUtil {

    /**
     * @Description 验证阿里云开发者身份
     */
    DefaultProfile profile = DefaultProfile.getProfile("default", "LTAI41s7UdycZniy", "HngXYiuYNWWPfBjCbHNQZuwhqtwDjQ");
    IAcsClient client = new DefaultAcsClient(profile);

    public String SendMessage(String PhoneNumbers) {
        CommonRequest request = new CommonRequest();
        //request.setSysProtocol(ProtocolType.HTTPS);
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", PhoneNumbers);//接受验证码的手机号
        request.putQueryParameter("SignName", "北极光游戏平台");//签名
        request.putQueryParameter("TemplateCode", "SMS_149100495");//模板代码
        request.putQueryParameter("TemplateParam", "{code:" + ((int) (Math.random() * 9000 + 1000)) + "}");//用户定义的验证码内容
        try {
            CommonResponse response = client.getCommonResponse(request);
            String returnstr = response.getData();
            System.out.println(returnstr);
            JSONObject returnjsonstr = JSONObject.parseObject(returnstr);
            return returnjsonstr.getString("Message");//返回的信息
        } catch (ServerException e) {
            return e.getErrMsg();
        } catch (ClientException e) {
            return e.getErrMsg();
        }
    }


    //查询发送信息
    public String QuerySendDetails(String PhoneNumber, String SendDate, String PageSize, String CurrentPage) {
        CommonRequest request = new CommonRequest();
        //request.setSysProtocol(ProtocolType.HTTPS);
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("QuerySendDetails");
        request.putQueryParameter("PhoneNumber", PhoneNumber);
        request.putQueryParameter("SendDate", SendDate);
        request.putQueryParameter("PageSize", PageSize);
        request.putQueryParameter("CurrentPage", CurrentPage);
        try {
            CommonResponse response = client.getCommonResponse(request);
            String returnstr = response.getData();
            System.out.println(returnstr);
            JSONObject returnjsonstr = JSONObject.parseObject(returnstr);
            return returnjsonstr.getString("Message");
        } catch (ServerException e) {
            return e.getErrMsg();
        } catch (ClientException e) {
            return e.getErrMsg();
        }
    }


}

