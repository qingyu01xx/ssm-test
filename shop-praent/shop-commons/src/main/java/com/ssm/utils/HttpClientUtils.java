package com.ssm.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.*;

public class HttpClientUtils {
    private static final String CHARSET = "utf-8";

    /**
     * @param url    路径  url
     * @param params 请求参数   key是属性  value:是值
     * @return
     */
    public static String get(String url, Map<String, String> params) {
        //1.创建 `HttpClient` 对象    HttpClients提供的静态方法createDefault()
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        String responseText = null;
        //2.设置参数
        //设置url路径
        LinkedList<NameValuePair> list = getParame(params);

        try {
            //创建一个URIBuilder独享 并指定要请求的路径
            URIBuilder uriBuilder = new URIBuilder(url);
            //设置参数
            uriBuilder.setParameters(list);
            //URI jdk提供的一个累  URL 和URI
            //3.创建HttpGet对象
            HttpGet httpGet = new HttpGet(uriBuilder.build());

            //4.调用 `HttpClient` 对象的 `execute(HttpUriRequest request)` 发送请求，该方法返回一个 `HttpResponse`。
            httpResponse = httpClient.execute(httpGet);

            //所有的api接口 返回的数据全部在CloseableHttpResponse 对象里面
            //5.调用 `HttpResponse` 的 `getAllHeaders()`、`getHeaders(String name)`
            //   等方法可获取服务器的响应头；调用 `HttpResponse` 的 `getEntity()` 方法可获取 `HttpEntity` 对象，
            // 该对象包装了服务器的响应内容。程序可通过该对象获取服务器的响应内容。

            //getEntity()方法获得 HttpEntity对象  该对象封装了响应数据
            HttpEntity entity = httpResponse.getEntity();
            //EntityUtils toString方法可以快速获取响应内容

            responseText = EntityUtils.toString(entity, CHARSET);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            //先开的后关
            close(httpClient, httpResponse);
            return responseText;
        }

    }

    /**
     * @param url
     * @return
     */
    public static String get(String url) {
        //1.创建 `HttpClient` 对象    HttpClients提供的静态方法createDefault()
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        String responseText = null;
        //2.设置参数
        //设置url路径
        //3.创建HttpGet对象
        HttpGet httpGet = new HttpGet(url);
        try {
            //4.调用 `HttpClient` 对象的 `execute(HttpUriRequest request)` 发送请求，该方法返回一个 `HttpResponse`。
            httpResponse = httpClient.execute(httpGet);
            //getEntity()方法获得 HttpEntity对象  该对象封装了响应数据
            HttpEntity entity = httpResponse.getEntity();
            //EntityUtils toString方法可以快速获取响应内容
            responseText = EntityUtils.toString(entity, CHARSET);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            //先开的后关
            close(httpClient, httpResponse);
            return responseText;
        }
    }

    /**
     * POST
     *
     * @param url
     * @param params
     * @return
     */
    public static String post(String url, Map<String, String> params) {
        //1.创建CloseableHttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        String responseText = null;

        //2.创建HttpPost 对象 并指定路径
        HttpPost httpPost = new HttpPost(url);

        LinkedList<NameValuePair> list = getParame(params);
        try {
            //设置参数   制定编码
            httpPost.setEntity(new UrlEncodedFormEntity(list, CHARSET));
            //设置请求头等
            //  httpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
            //4.发送请求
            httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {//响应状态吗是200
                //5.取数实体内容
                HttpEntity entity = httpResponse.getEntity();
                responseText = EntityUtils.toString(entity, CHARSET);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6释放资源
            //先开的后关
            close(httpClient, httpResponse);
            return responseText;
        }

    }


    public static String delete(String url) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        String responseText = null;

        CloseableHttpResponse httpResponse = null;
        HttpDelete httpDelete = new HttpDelete(url);
        try {
            httpResponse = httpClient.execute(httpDelete);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                responseText = EntityUtils.toString(httpResponse.getEntity(), CHARSET);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6释放资源
            //先开的后关
            close(httpClient, httpResponse);
            return responseText;
        }

    }

    public static String put(String url, Map<String, String> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        String responseText = null;

        HttpPut httpPut = new HttpPut(url);
        LinkedList<NameValuePair> list = getParame2(params);

        try {
            //设置参数
            httpPut.setEntity(new UrlEncodedFormEntity(list, CHARSET));
            //请求
            httpResponse = httpClient.execute(httpPut);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                //打印实体
                responseText = EntityUtils.toString(httpResponse.getEntity(), CHARSET);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(httpClient, httpResponse);
            return responseText;
        }


    }

    public static LinkedList<NameValuePair> getParame(Map<String, String> params) {
        LinkedList<NameValuePair> list = new LinkedList<>();
        if (params != null && params.size() > 0) {
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            Iterator<Map.Entry<String, String>> it = entrySet.iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        return list;
    }

    public static LinkedList<NameValuePair> getParame2(Map<String, String> params) {
        LinkedList<NameValuePair> list = new LinkedList<>();
        if (params != null && params.size() > 0) {
            Set<String> keySet = params.keySet();
            Iterator<String> it = keySet.iterator();
            while (it.hasNext()) {
                String key = it.next();
                list.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        return list;
    }

    /**
     * 释放资源
     *
     * @param httpClient   连接对象
     * @param httpResponse 响应对象
     */
    public static void close(CloseableHttpClient httpClient, CloseableHttpResponse httpResponse) {
        if (httpResponse != null) {
            try {
                httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
