package com.zhxg.framework.base.utils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {
    
    protected static Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    private final static int TIME_OUT = 20000;

    public static String post(String url, Map<String, String> params) {
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(TIME_OUT).setConnectTimeout(TIME_OUT)
                .setSocketTimeout(TIME_OUT).build();
        CloseableHttpResponse response = null;
        try {
            List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
            for (String key : params.keySet()) {
                formparams.add(new BasicNameValuePair(key, params.get(key)));
            }
            post.setEntity(new UrlEncodedFormEntity(formparams, "UTF-8"));
            post.setConfig(config);
            response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity,"UTF-8");
            result = content;
            EntityUtils.consume(entity);
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage(),e);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
        }
        return result;
    }
    
    public static String post(String url, String body) {
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(TIME_OUT).setConnectTimeout(TIME_OUT)
                .setSocketTimeout(TIME_OUT).build();
        CloseableHttpResponse response = null;
        try {
            post.addHeader("Content-Type", "application/json");
            post.setEntity(new ByteArrayEntity(body.getBytes()));
            post.setConfig(config);
            response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity,"UTF-8");
            result = content;
            EntityUtils.consume(entity);
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage(),e);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
        }
        return result;
    }
    
    /**
     * 绕过验证
     *     
     * @return
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("SSLv3");

        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        sc.init(null, new TrustManager[] { trustManager }, null);
        return sc;
    }
    
    /**
     * 模拟请求
     * 
     * @param url        资源地址
     * @param map    参数列表
     * @param encoding    编码
     * @return
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    public static String httpsPost(String url, Map<String,Object> map,Map<String,String> heads,String encoding) {
        String body = "";
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        try{
            SSLContext sslcontext = createIgnoreVerifySSL();
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext))
                .build();
            PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            HttpClients.custom().setConnectionManager(connManager);
            client = HttpClients.custom().setConnectionManager(connManager).build();
            HttpPost httpsPost = new HttpPost(url);
            List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
            for(String key:map.keySet()){
                formparams.add(new BasicNameValuePair(key, map.get(key).toString()));
            }
            httpsPost.setEntity(new UrlEncodedFormEntity(formparams, encoding));
            
            httpsPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            httpsPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            if(heads!=null){
                for(String key:heads.keySet()){
                    httpsPost.setHeader(key, heads.get(key));
                } 
            }
            response = client.execute(httpsPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                body = EntityUtils.toString(entity, encoding);
            }
            EntityUtils.consume(entity);
        }catch(Exception e){
            e.printStackTrace();
        }finally
        {
            try {
                if(client!=null){
                    client.close(); 
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(response!=null){
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
        return body;
    }

}