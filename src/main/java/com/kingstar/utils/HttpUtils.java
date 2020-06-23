package com.kingstar.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Description: 封装常用的HTTPClient工具
 * @Author: myl
 * @Date: 2020/6/15 23:29
 */
public class HttpUtils {
    /**
     * 声明HTTPClient连接池域
     */
    private static PoolingHttpClientConnectionManager connectionManager;

    static {
        connectionManager = new PoolingHttpClientConnectionManager();
        // 设置最大连接数
        connectionManager.setMaxTotal(120);
        // 设置每个主机的最大连接数
        connectionManager.setDefaultMaxPerRoute(60);
    }

    /**
     * get方式获取HTML页面内容
     *
     * @param url
     * @return java.lang.String
     * @author yongliang
     */
    public static String doGetHtml(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        return crawlHtml(httpGet);
    }

    /**
     * 爬取页面数据信息
     *
     * @param httpRequestBase
     * @return java.lang.String
     * @author yongliang
     */
    private static String crawlHtml(HttpRequestBase httpRequestBase) throws IOException {
        httpRequestBase.setHeader("User-Agent", StringUtil.getRandomUserAgent());
        // 加载请求设置
        httpRequestBase.setConfig(getConfig());
        CloseableHttpClient closeableHttpClient = getCloseableHttpClient();
        // 获取爬取响应结果
        CloseableHttpResponse response = null;
        try {
            response = closeableHttpClient.execute(httpRequestBase);
            if (response.getStatusLine().getStatusCode() == 200) {
                String htmlContent = EntityUtils.toString(response.getEntity(), "utf-8");
                return htmlContent;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                // 关闭资源连接
                response.close();
            }
        }
        return null;

    }

    /**
     * 获取资源可关闭性的HTTPClient
     *
     * @param
     * @return org.apache.http.impl.client.CloseableHttpClient
     * @author yongliang
     */
    private static CloseableHttpClient getCloseableHttpClient() {
        return HttpClients.custom().setConnectionManager(connectionManager).build();
    }


    /**
     * 设置请求相关信息
     *
     * @param
     * @return org.apache.http.client.config.RequestConfig
     * @author yongliang
     */
    private static RequestConfig getConfig() {
        return RequestConfig.custom()
//                .setCookieSpec(CookieSpecs.STANDARD)
                //创建连接的最长时间
                .setConnectTimeout(5000)
                //获取连接的最长时间
                .setConnectionRequestTimeout(5000)
                //数据传输的最长时间
                .setSocketTimeout(100000)
                .build();
    }

}
