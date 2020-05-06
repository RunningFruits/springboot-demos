package com.code.demo.backend.service.custom;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.code.demo.backend.entity.model.Params4BuildQrCode;
import com.code.demo.backend.utils.HttpClient;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class MiniProgramQrCodeService {

    public static String getAccessToken() {
        String getAccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=_APPID_&secret=_APP_SECRET_";
        getAccessTokenUrl = getAccessTokenUrl.replace("_APPID_", "wx78615aad1423ccf1")
                .replace("_APP_SECRET_", "2e89e83522c2dd868b67cbe3dcaf5f1f");

        JSONObject jsonObject = JSON.parseObject(HttpClient.doGet(getAccessTokenUrl));
        String accessToken = jsonObject.get("access_token").toString();
        return accessToken;
    }


    public static String getQrCodeImg(String accessToken, Params4BuildQrCode params) throws IOException {
        URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");// 提交模式
        httpURLConnection.setRequestProperty("Content-Type", "application/json");

        // 发送POST请求必须设置如下两行
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        // 获取URLConnection对象对应的输出流
        PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());

        // 发送请求参数
        JSONObject paramJson = new JSONObject();
        paramJson.put("path", "pages/scenicSpot/detail/detail?companyId=" + params.getCompanyId());
        paramJson.put("width", params.getWidth());
        paramJson.put("auto_color", "false");
        paramJson.put("line_color", "{\"r\":0,\"g\":0,\"b\":0}");

        printWriter.write(paramJson.toString());
        // flush输出流的缓冲
        printWriter.flush();

        //开始获取数据
        BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        //buff用于存放循环读取的临时数据
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = bis.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        ByteArrayInputStream inputStream = new ByteArrayInputStream(swapStream.toByteArray());
        BufferedImage image = ImageIO.read(inputStream);

        /**
         * 裁剪原图  目前访问微信 微信返回的是 470*535 像素 170620
         */
        BufferedImage subImage = image.getSubimage(0, 0, image.getWidth(), image.getHeight());
        BufferedImage qrCodeImg = new BufferedImage(430, 430, BufferedImage.TYPE_INT_BGR);
        Graphics2D g = (Graphics2D) qrCodeImg.getGraphics();
        g.drawImage(subImage, 0, 0, 430, 430, null); //画图
        g.dispose();
        qrCodeImg.flush();

        String fileName = UUID.randomUUID().toString() + System.currentTimeMillis() + (".jpg");
        String remotePath = "/home/images/";
        File qrCodeFile = new File(remotePath + fileName);
        ImageIO.write(qrCodeImg, "jpg", new File(remotePath + fileName));
//        String upload = QiniuUtil.upload(qrCodeFile);
//        qrCodeFile.delete();
//        return upload;
        return "";
    }

}
