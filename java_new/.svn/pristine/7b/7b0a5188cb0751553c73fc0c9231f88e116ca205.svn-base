package com.zhxg.framework.base.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * HTTP 请求工具类
 * 
 * @author 785462
 *
 */
public class HttpClientUtils {
    
    private static Logger logger = Logger.getLogger(HttpClientUtils.class);
    
    private static final int TIMEOUT = 15000;
    
    private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT).setConnectTimeout(TIMEOUT)
            .setConnectionRequestTimeout(TIMEOUT).build();

    /**
     * 发送HTTP POST请求,支持带多个String参数
     * 
     * @param url
     *            链接
     * @param paramMap
     *            参数
     */
    public static String sendHttpPost(String url, Map<String, String> paramMap){
        CloseableHttpClient httpclient = HttpClientUtils.getHttpClient();
        return sendHttpPost(url, paramMap, httpclient);
    }

    /**
     * 发送HTTP POST请求,支持多个参数(注：多个参数需拼接)
     * 
     * @param url
     *            链接
     * @param params
     *            参数(格式:key1=value1&key2=value2)
     */
    public static String sendHttpPost(String url, String params){
        CloseableHttpClient httpclient = HttpClientUtils.getHttpClient();
        return sendHttpPost(url, params, httpclient);
    }

    /**
     * 发送HTTP POST请求,支持带一个文件参数
     * 
     * @param url
     *            链接
     * @param file
     *            文件
     * @throws IOException 
     */
    public static String sendHttpPost(String url, File file){
        CloseableHttpClient httpclient = HttpClientUtils.getHttpClient();
        try {
            HttpPost httpPost = new HttpPost(url);

            InputStreamEntity reqEntity = new InputStreamEntity(new FileInputStream(file), -1,
                    ContentType.APPLICATION_OCTET_STREAM);
            reqEntity.setChunked(true);

            httpPost.setEntity(reqEntity);

            return sendHttpPost(httpPost, httpclient);
            
        } catch (IOException e) {
            processIOException(e);
        }
        return null;
        
    }

    /**
     * 发送HTTP POST请求(客户端采用二进制流发送,服务端采用二进制流接收)
     * 
     * @param url
     *            链接
     * @param binaryStreamsStr
     *            参数
     */
    public static String sendHttpPostByStream(String url, String binaryStreamsStr){
        
        CloseableHttpClient httpclient = HttpClientUtils.getHttpClient();

        HttpPost httpPost = new HttpPost(url);

        HttpEntity reqEntity = new ByteArrayEntity(binaryStreamsStr.getBytes(Consts.UTF_8),
                ContentType.APPLICATION_JSON);

        httpPost.setEntity(reqEntity);

        return sendHttpPost(httpPost, httpclient);

    }

    /**
     * 发送POST请求,支持带多个String参数和多个文件参数
     * 
     * @param url
     *            链接
     * @param paraMap
     *            参数集合
     * @param fileMap
     *            文件集合
     */
    public static String sendHttpPostByFile(String url, Map<String, String> paraMap, Map<String, File> fileMap){
        CloseableHttpClient httpclient = HttpClientUtils.getHttpClient();

        HttpPost httpPost = new HttpPost(url);

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        for (Entry<String, String> entry : paraMap.entrySet()) {
            builder.addPart(entry.getKey(), new StringBody(entry.getValue(), ContentType.TEXT_PLAIN));
        }
        for (Entry<String, File> entry : fileMap.entrySet()) {
            builder.addPart(entry.getKey(), new FileBody(entry.getValue()));
        }
        HttpEntity reqEntity = builder.build();
        httpPost.setEntity(reqEntity);
        
        return sendHttpPost(httpPost, httpclient);

    }

    /**
     * 发送HTTP GET请求,不带参数(注：可将参数加在url后面)
     * 
     * @param url
     *            链接
     */
    public static String sendHttpGet(String url){
        
        CloseableHttpClient httpclient = HttpClientUtils.getHttpClient();

        return sendHttpGet(url, httpclient);
        
    }
    
    /**
     * 发送HTTP GET请求,不带参数(注：可将参数加在url后面)
     * 
     * @param url
     *            链接
     */
    public static String ecloudGet(String url){
        
        CloseableHttpClient httpclient = HttpClientUtils.getHttpClient();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String s = EntityUtils.toString(entity, Consts.UTF_8);
            JSONObject json= JSON.parseObject(s.replace("(", "").replace(");", ""));
            String accesstoken = response.getHeaders("Set-Cookie")[0].getValue().replace("access_token=", "");
            String buid = response.getHeaders("Set-Cookie")[1].getValue().replace("BUID=", "");
            json.put("access_token", accesstoken.substring(0, accesstoken.indexOf(";")));
            json.put("BUID", buid.substring(0, buid.indexOf(";")));
            return json.toJSONString();
        } catch (IOException e) {
            processIOException(e);
        }
        return null;
        
    }

    /**
     * 发送HTTP GET请求,不带参数,返回byte数组
     * 
     * @param url
     *            链接
     */
    public static byte[] sendHttpGetResByte(String url){
        
        CloseableHttpClient httpclient = HttpClientUtils.getHttpClient();

        return sendHttpGetResByte(url, httpclient);

    }

    /**
     * 发送HTTP GET请求,支持多个参数(注：多个参数需拼接)
     * 
     * @param url
     *            链接
     * @param params
     *            参数(格式:key1=value1&key2=value2)
     */
    public static String sendHttpGet(String url, String params){
        CloseableHttpClient httpclient = HttpClientUtils.getHttpClient();
        return sendHttpGet(url, params, httpclient);
    }

    /**
     * 发送HTTPS GET请求,支持多个参数(注：多个参数需拼接)
     * 
     * @param url
     *            链接
     * @param params
     *            参数(格式:key1=value1&key2=value2)
     */
    public static String sendHttpsGet(String url, String params) throws IOException {
        CloseableHttpClient httpclient = HttpClientUtils.getHttpsClient(url);
        return sendHttpGet(url, params, httpclient);
    }

    /**
     * 发送HTTPS GET请求,不带参数(注：可将参数加在url后面)
     * 
     * @param url
     *            链接
     */
    public static String sendHttpsGet(String url){
        try {
            CloseableHttpClient httpclient = HttpClientUtils.getHttpsClient(url);
            return sendHttpGet(url, httpclient);
        } catch (IOException e) {
            processIOException(e);
        }
        return null;
    }

    /**
     * 发送HTTPS POST请求,支持带多个String参数
     * 
     * @param url
     *            链接
     * @param paramMap
     *            参数
     */
    public static String sendHttpsPost(String url, Map<String, String> paramMap) throws IOException {
        CloseableHttpClient httpclient = HttpClientUtils.getHttpsClient(url);
        return sendHttpPost(url, paramMap, httpclient);
    }

    /**
     * 发送HTTPS POST请求,支持多个参数(注：多个参数需拼接)
     * 
     * @param url
     *            链接
     * @param params
     *            参数(格式:key1=value1&key2=value2)
     */
    public static String sendHttpsPost(String url, String params) throws IOException {
        CloseableHttpClient httpclient = HttpClientUtils.getHttpsClient(url);
        return sendHttpPost(url, params, httpclient);
    }

    /**
     * 发送HTTP GET请求
     */
    private static String sendHttpGet(String url, String params, CloseableHttpClient httpclient){
        
        StringBuilder sb = new StringBuilder().append(url).append("?").append(params);
        
        return sendHttpGet(sb.toString(), httpclient);

    }

    /**
     * 发送HTTP POST请求
     */
    private static String sendHttpPost(String url, Map<String, String> paramMap, CloseableHttpClient httpclient){
        try {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nvps = new ArrayList<>();

            for (Entry<String, String> entry : paramMap.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }

            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            return sendHttpPost(httpPost, httpclient);
        } catch (IOException e) {
            processIOException(e);
        }
        return null;
    }

    /**
     * 发送HTTP POST请求
     */
    private static String sendHttpPost(String url, String params, CloseableHttpClient httpclient) {
        
        HttpPost httpPost = new HttpPost(url);

        httpPost.setEntity(new StringEntity(params, Consts.UTF_8));

        return sendHttpPost(httpPost, httpclient);
      
    }

    /**
     * 获取HttpClient
     */
    private static CloseableHttpClient getHttpClient() {
        return HttpClients.createDefault();
    }

    /**
     * 获取HTTPS HttpClient
     */
    private static CloseableHttpClient getHttpsClient(String url) throws IOException {
        
        PublicSuffixMatcher publicSuffixMatcher = PublicSuffixMatcherLoader.load(new URL(url));

        DefaultHostnameVerifier hostnameVerifier = new DefaultHostnameVerifier(publicSuffixMatcher);

        return HttpClients.custom().setSSLHostnameVerifier(hostnameVerifier).build();

    }

    /**
     * 发送HTTP POST请求
     */
    private static String sendHttpPost(HttpPost httpPost, CloseableHttpClient httpclient){
        httpPost.setConfig(requestConfig);
        try {
            CloseableHttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, Consts.UTF_8);
        } catch (IOException e) {
            processIOException(e);
        }
        return null;
    }

    /**
     * 发送HTTP GET请求
     */
    private static String sendHttpGet(String url, CloseableHttpClient httpclient){
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, Consts.UTF_8);
        } catch (IOException e) {
            processIOException(e);
        }
        return null;
    }

    /**
     * 发送HTTP GET请求
     */
    private static byte[] sendHttpGetResByte(String url, CloseableHttpClient httpclient){
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        byte[] result = null;
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toByteArray(entity);
        } catch (IOException e) {
            processIOException(e);
        }
        return result;
    }
    
    private static void processIOException(IOException e) {
        logger.error(e.getMessage(),e);
        Throwable[] suppressed = e.getSuppressed();
        for (int i = 0; i < suppressed.length; i++) {
            logger.error("suppressed exception: " + suppressed[i].toString());
        }
    }
    
    
    public static InputStream getInputStream(String filePath) {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
 
        try {
            URL url =  new URL(filePath);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            // 设置网络连接超时时间
            httpURLConnection.setConnectTimeout(3000);
            // 设置应用程序要从网络连接读取数据
            httpURLConnection.setDoInput(true);
 
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                // 从服务器返回一个输入流
                inputStream = httpURLConnection.getInputStream();
 
            }
 
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
 
        return inputStream;
 
    }

}
