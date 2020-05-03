package com.code.demo.api.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class HttpUtils {

    private static final Logger logger = LogManager.getLogger(HttpUtils.class);
    private static final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(20000).setConnectionRequestTimeout(10000)
            .setSocketTimeout(20000).build();

    /**
     * Http Get
     *
     * @param url    请求路径
     * @param params 参数
     * @return http响应状态及json结果
     */
    public static HttpResult doGet(String url, Map<String, String> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        url = contactUrl(url, params);
        HttpGet httpGet = new HttpGet(url.replace(" ", ""));
        return executeRequest(httpClient, httpGet);
    }


    public static StringBuffer httpsRequest(String requestUrl, String requestMethod, String output) throws IOException {
        URL url = new URL(requestUrl);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod(requestMethod);
        if (null != output) {
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(output.getBytes("UTF-8"));
            outputStream.close();
        }
        // 从输入流读取返回内容
        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = null;
        StringBuffer buffer = new StringBuffer();
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        inputStream = null;
        connection.disconnect();
        return buffer;
    }


    /**
     * Http Post
     *
     * @param url    请求路径
     * @param params 参数
     * @return http响应状态及json结果
     */
    public static HttpResult doPost(String url, Map<String, String> params, Map<String, String> headerMaps) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> pairs = new ArrayList<>();
        Set<String> keys = params.keySet();
        for (String key : keys) {
            String value = params.get(key);
            pairs.add(new BasicNameValuePair(key, value));
        }
        return executeRequest(httpClient, httpPost, pairs, headerMaps);
    }

    /**
     * Http Put
     *
     * @param url    请求路径
     * @param params 参数
     * @return http响应状态及json结果
     */
    public static HttpResult doPut(String url, Map<String, String> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        url = contactUrl(url, params);
        HttpPut httpPut = new HttpPut(url);
        return executeRequest(httpClient, httpPut);
    }

    /**
     * Http Delete
     *
     * @param url    请求路径
     * @param params 参数
     * @return http响应状态及json结果
     */
    public static HttpResult doDelete(String url, Map<String, String> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        url = contactUrl(url, params);
        HttpDelete httpDelete = new HttpDelete(url);
        return executeRequest(httpClient, httpDelete);
    }

    /**
     * 拼装url
     *
     * @param url    url
     * @param params 参数
     * @return
     */
    private static String contactUrl(String url, Map<String, String> params) {
        if (params != null) {
            String param = "";
            Set<String> keys = params.keySet();
            for (String key : keys) {
                String value = params.get(key);
                if (value == null || value.equals("null")) {
                    continue;
                }
                param += key + "=" + value + "&";
            }
            if (!param.equals("")) {
                url += "?" + param.substring(0, param.length() - 1);
            }
        }
        return url;
    }

    private static String contactUrl_(String url, Map<String, Object> params) {
        if (params != null) {
            String param = "";
            Set<String> keys = params.keySet();
            for (String key : keys) {
                Object value = params.get(key);
                if (value == null || value.equals("null")) {
                    continue;
                }
                param += key + "=" + value + "&";
            }
            if (!param.equals("")) {
                url += "?" + param.substring(0, param.length() - 1);
            }
        }
        return url;
    }

    public static String getFullPath(HttpServletRequest request) {
        String basePath = request.getRequestURL().toString();
        String queryString = request.getQueryString();
        if (StrKit.isBlank(queryString)) {
            queryString = "?" + queryString;
        } else {
            queryString = "";
        }

        return basePath + queryString;
    }

    /**
     * 执行GET/PUT/DELETE请求
     *
     * @param httpClient
     * @param request
     * @return
     */
    private static HttpResult executeRequest(CloseableHttpClient httpClient, HttpRequestBase request) {
        HttpResult result = null;
        request.setConfig(requestConfig);
        try {
            request.setHeader("Content-Type", "application/octet-stream");
            HttpResponse response = httpClient.execute(request);
            int code = response.getStatusLine().getStatusCode();
            result = new HttpResult();
            result.setStatus(code);
            if (code == HttpStatus.SC_OK) {
                result.setResponse(EntityUtils.toString(response.getEntity(), "utf-8"));
            }
        } catch (Exception e) {
            logger.error("=========GET/PUT/DELETE请求异常：" + request.getURI(), e);
            return result;
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error("=========GET/PUT/DELETE请求连接关闭异常：" + request.getURI(), e);
            }
        }
        logger.debug("========GET/PUT/DELETE请求响应：" + request.getURI() + "\n" + result);
        return result;
    }

    /**
     * 执行POST请求
     *
     * @param httpClient
     * @param request
     * @param pairs
     * @return
     */
    private static HttpResult executeRequest(CloseableHttpClient httpClient, HttpEntityEnclosingRequestBase request, List<NameValuePair> pairs, Map<String, String> headerMaps) {
        HttpResult result = null;
        request.setConfig(requestConfig);
        try {
            StringEntity entity;
            if (pairs.size() == 1 && pairs.get(0).getName().equals("json")) {
                entity = new StringEntity(pairs.get(0).getValue(), "UTF-8");
                entity.setContentType("application/json");
            } else {
                entity = new UrlEncodedFormEntity(pairs, "UTF-8");
            }
            request.setEntity(entity);
            Set<String> keys = headerMaps.keySet();
            for (String key : keys) {
                String value = headerMaps.get(key);
                request.setHeader(key, value);
            }
            HttpResponse response = httpClient.execute(request);
            int code = response.getStatusLine().getStatusCode();
            result = new HttpResult();
            result.setStatus(code);
            if (code == HttpStatus.SC_OK) {
                result.setResponse(EntityUtils.toString(response.getEntity(), "utf-8"));
//                result.setResponse(EntityUtils.toString(response.getEntity()));
            }
        } catch (Exception e) {
            logger.error("=========POST请求异常：" + request.getURI(), e);
            return result;
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error("=========POST请求连接关闭异常：" + request.getURI(), e);
            }
        }
        logger.debug("========POST请求响应：" + request.getURI() + "\n" + result);
        return result;
    }

    /**
     * 格式化 - json数据
     */
    public static RetKit fromJsonRetKit(HttpResult httpResult) {
        try {
            if (httpResult.getStatus() != 200) {
                return RetKit.fail();
            }
            JSONObject jsonObject = JSONObject.parseObject(httpResult.getResponse());
            String codeStr = jsonObject.getString("code");
            if (!("200").equals(codeStr)) {
                return RetKit.fail();
            }
            String dataStr = jsonObject.getString("data");
            JSONObject jsonArray = JSONArray.parseObject(dataStr);
            return RetKit.okData(jsonArray);
        } catch (Exception e) {
            return RetKit.fail();
        }
    }
}
