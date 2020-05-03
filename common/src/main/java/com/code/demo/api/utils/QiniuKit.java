package com.code.demo.api.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.Base64;
import com.qiniu.util.UrlSafeBase64;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QiniuKit {

    // 设置好账号的ACCESS_KEY和SECRET_KEY
    static String ACCESS_KEY = "";
    static String SECRET_KEY = "";

    // 地区：http://up-z2.qiniu.com/
    static String bucketZone = "http://up-z2.qiniu.com/";

    // 正式域名
    public static String domain = "tour.z-chip.com";
    // 要上传的正式空间
    static String bucketName = "";


    // 密钥配置
    static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    // 创建上传对象
    static UploadManager uploadManager = new UploadManager(new Configuration());

    // 简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public static String getUpToken() {
        return auth.uploadToken(bucketName);
    }

    // 获取domain
    public static String getDomain() {
        return domain;
    }

    // 获取bucketZone
    public static String getBucketZone() {
        return bucketZone;
    }

    // 删除：key为文件名
    public static void delete(String key) throws QiniuException {
        BucketManager bucketManager = new BucketManager(auth, new Configuration());
        bucketManager.delete(bucketName, key);
    }

    public static String upload(File fileName) throws IOException {
        try {
            // 调用put方法上传
            String f = fileName.getName();
            String randomName = StrKit.getRandomUUID().toString().replace("-", "");
            // UUID.randomUUID().toString().replace("-", "");
            String name = randomName + "." + f.substring(f.lastIndexOf(".") + 1);
            uploadManager.put(fileName, name, getUpToken());
            return name;

        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                // 响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                // ignore
            }
        }
        return null;
    }

    public static String uploadFacePicture(File file, String fileName) throws IOException {
        try {
            uploadManager.put(file, fileName, getUpToken());
            return fileName;
        } catch (QiniuException e) {
            return null;
        }
    }

    public static String put64image(byte[] bytes) throws Exception {
        String randomName = "schoolCamp_" + StrKit.getRandomUUID().toString().replace("-", "") + ".png";
        String file64 = Base64.encodeToString(bytes, 0);
        String url = "http://upload-z2.qiniup.com/putb64/-1/key/" + UrlSafeBase64.encodeToString(randomName);
        // 非华东空间需要根据注意事项 1 修改上传域名
        RequestBody rb = RequestBody.create(null, file64);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/octet-stream")
                .addHeader("Authorization", "UpToken " + getUpToken())
                .post(rb)
                .build();
        OkHttpClient client = new OkHttpClient();
        okhttp3.Response response = client.newCall(request).execute();
        System.out.println(response);
        return randomName;
    }

    public static Map<String, String> getToken() {
        String upToken = auth.uploadToken(bucketZone + bucketName);
        Map<String, String> map = new HashMap<String, String>();
        map.put("uptoken", upToken);
        return map;
    }

}
