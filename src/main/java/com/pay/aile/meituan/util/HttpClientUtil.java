package com.pay.aile.meituan.util;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;

/**
 *
 * @Description: HttpClient
 * @see: HttpClientUtil 此处填写需要参考的类
 * @version 2017年7月12日 上午10:53:05
 * @author chao.wang
 */
public class HttpClientUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * @Description 一句话描述方法用法
     * @param method
     *            GET/POST
     * @param url
     *            请求地址
     * @param protocol
     *            HTTP/HTTPS
     * @param requestData
     *            json字符串
     * @param charset
     *            编码方式(一般为UTF-8)
     * @return
     * @see 需要参考的类或方法
     */
    public static String sendRequest(HttpMethod method, String url, String protocol, String requestData,
            String charset) {
        logger.info("Method sendRequest: url = {}, requestData = {}", url, requestData);
        Assert.notNull(method, "method 不能为空");
        HttpClientBuilder httpClientBuilder = null;
        CloseableHttpClient closeableHttpClient = null;
        HttpPost httpPost = null;
        HttpGet httpGet = null;
        HttpUriRequest request = null;
        String result = null;
        try {
            httpClientBuilder = HttpClientBuilder.create();
            RequestConfig config = RequestConfig.custom().setConnectTimeout(90 * 1000).setSocketTimeout(90 * 1000)
                    .build();
            httpClientBuilder.setDefaultRequestConfig(config);

            closeableHttpClient = httpClientBuilder.build();
            EntityBuilder entity = null;

            switch (method) {
            case GET:
                url = url + "?" + requestData;
                httpGet = new HttpGet(url);
                request = httpGet;
                break;
            case POST:
                httpPost = new HttpPost(url);
                entity = EntityBuilder.create();
                entity.setText(requestData);
                entity.setContentType(ContentType.APPLICATION_JSON);
                entity.setContentEncoding(charset);
                httpPost.setEntity(entity.build());
                request = httpPost;
                break;
            default:
                throw new IllegalArgumentException("method 必须为GET或者POST");
            }

            HttpResponse response = closeableHttpClient.execute(request);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                    logger.info("HttpEntity resEntity result :{}", result);
                } else {
                    logger.info("HttpEntity resEntity响应信息为空");
                }
            } else {
                logger.info("HttpResponse response 响应信息为空");
            }
        } catch (IOException ioe) {
            logger.error("Method sendRequest execute exception...");
        } finally {
            try {
                // 关闭流并释放资源
                if (closeableHttpClient != null) {
                    closeableHttpClient.close();
                }
            } catch (IOException e) {
                logger.error("Method sendRequest release resource exception...");
            }
        }
        return result;
    }

    /**
     * @Description 一句话描述方法用法
     * @param method
     * @param url
     * @param protocol
     * @param requestData
     * @param charset
     * @return
     * @see 需要参考的类或方法
     */
    public static String sendRequestMap(HttpMethod method, String url, String protocol, Map<String, Object> requestData,
            String charset) {
        logger.info("Method sendRequestMap: url = {}, requestData = {}", url, requestData);
        Assert.notNull(method, "method不能为空");
        HttpClientBuilder httpClientBuilder = null;
        CloseableHttpClient closeableHttpClient = null;
        HttpPost httpPost = null;
        HttpGet httpGet = null;
        HttpUriRequest request = null;
        String result = null;
        try {
            httpClientBuilder = HttpClientBuilder.create();
            RequestConfig config = RequestConfig.custom().setConnectTimeout(90 * 1000).setSocketTimeout(90 * 1000)
                    .build();
            httpClientBuilder.setDefaultRequestConfig(config);

            closeableHttpClient = httpClientBuilder.build();
            EntityBuilder entity = null;
            StringBuffer sb = new StringBuffer();
            String reqStr = "";
            if (requestData != null) {
                for (Entry<String, Object> e : requestData.entrySet()) {
                    sb.append(e.getKey());
                    sb.append("=");
                    sb.append(e.getValue());
                    sb.append("&");
                }
                reqStr = sb.substring(0, sb.length() - 1);
            }
            switch (method) {
            case GET:
                url = url + "?" + reqStr;
                httpGet = new HttpGet(url);
                request = httpGet;
                break;
            case POST:
                httpPost = new HttpPost(url);
                entity = EntityBuilder.create();
                entity.setText(reqStr);
                entity.setContentType(ContentType.APPLICATION_FORM_URLENCODED);
                entity.setContentEncoding(charset);
                httpPost.setEntity(entity.build());
                break;
            default:
                throw new IllegalArgumentException("method 必须为GET或者POST");
            }

            HttpResponse response = closeableHttpClient.execute(request);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                } else {
                    logger.info("HttpEntity resEntity响应信息为空");
                }
            } else {
                logger.info("HttpResponse response 响应信息为空");
            }
        } catch (IOException ioe) {
            logger.error("Method sendRequest execute exception...");
        } finally {
            try {
                // 关闭流并释放资源
                if (closeableHttpClient != null) {
                    closeableHttpClient.close();
                }
            } catch (IOException e) {
                logger.error("Method sendRequest release resource exception...");
            }
        }
        return result;
    }
}
