package com.zhxg.framework.base.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpsUtil {
    /**
     * 发送HTTPS  POST请求
     * 
     * @param 要访问的HTTPS地址,POST访问的参数Map对象
     * @return  返回响应值
     * */
    public static final String httpsPost(String url, Map<String, String> params) {
        String responseContent = null;
        HttpClient httpClient = new DefaultHttpClient();
        //创建TrustManager
        X509TrustManager xtm = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        //这个好像是HOST验证
        X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
            public void verify(String arg0, SSLSocket arg1) throws IOException {}
            public void verify(String arg0, String[] arg1, String[] arg2) throws SSLException {}
            public void verify(String arg0, X509Certificate arg1) throws SSLException {}
        };
        try {
            //TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
            SSLContext ctx = SSLContext.getInstance("TLS");
            //使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
            ctx.init(null, new TrustManager[] { xtm }, null);
            //创建SSLSocketFactory
            SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
            socketFactory.setHostnameVerifier(hostnameVerifier);
            //通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上
            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", socketFactory, 443));
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> formParams = new ArrayList<NameValuePair>(); // 构建POST请求的表单参数
            for (Map.Entry<String, String> entry : params.entrySet()) {
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity(); // 获取响应实体
            if (entity != null) {
                responseContent = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            httpClient.getConnectionManager().shutdown();
        }
        return responseContent;
    }
    
    
    public static final String post(String url, Map<String, String> params) {
        String responseContent = null;
        HttpClient httpClient = new DefaultHttpClient();
        //创建TrustManager
        X509TrustManager xtm = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        //这个好像是HOST验证
        X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
            public void verify(String arg0, SSLSocket arg1) throws IOException {}
            public void verify(String arg0, String[] arg1, String[] arg2) throws SSLException {}
            public void verify(String arg0, X509Certificate arg1) throws SSLException {}
        };
        try {
            //TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
            SSLContext ctx = SSLContext.getInstance("TLS");
            //使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
            ctx.init(null, new TrustManager[] { xtm }, null);
            //创建SSLSocketFactory
            SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
            socketFactory.setHostnameVerifier(hostnameVerifier);
            //通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上
            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", socketFactory, 443));
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            if(params!=null){
                List<NameValuePair> formParams = new ArrayList<NameValuePair>(); // 构建POST请求的表单参数
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));
            }
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity(); // 获取响应实体
            if (entity != null) {
                responseContent = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            httpClient.getConnectionManager().shutdown();
        }
        return responseContent;
    }
    
/*    public static void main(String[] args) {
//        String url = "https://221.176.54.92:9081/restful/services/oauth2/authorization";
//        String clientId = "1001";
//        StringBuffer urlsb = new StringBuffer(url);
//        urlsb.append("?grant_type=authorization_code");
//        urlsb.append("&client_id=").append(clientId);
//        urlsb.append("&scope=&redirect_uri=");
//        urlsb.append("&code=").append("8286ddc4-4b92-4fbf-857e-a1d3171620ae");
//        String responseBody = HttpsUtil.post(urlsb.toString(), null);
//        System.out.println(responseBody);
        
        String userInfoUrl = "https://221.176.54.92:9081/restful/services/user/info";
          userInfoUrl=userInfoUrl+"?access_token=df5b35d2-7a51-4675-ac35-31523536f182&userid=2139";
          String userInfo =  HttpsUtil.httpsGet(userInfoUrl, null); //HttpUtil.post(userInfoUrl, param, heads);//
          System.out.println(userInfo);
    }*/
    
    /** 
     * 发送get请求 
     * @param url       链接地址 
     * @param charset   字符编码，若为null则默认utf-8 
     * @return 
     */  
    public static String httpsGet(String url,String charset){  
        if(null == charset){  
            charset = "utf-8";  
        }  
        HttpClient httpClient = null;  
        HttpGet httpGet= null;  
        String result = null;  
          
        try {  
            httpClient = new SSLClient();  
            httpGet = new HttpGet(url);  
            httpGet.addHeader("Content-Type", "application/json"); 
            HttpResponse response = httpClient.execute(httpGet);  
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity,charset);  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
        return result;  
    }  


}

class SSLClient extends DefaultHttpClient{    
    public SSLClient() throws Exception{    
        super();    
        SSLContext ctx = SSLContext.getInstance("TLS");    
        X509TrustManager tm = new X509TrustManager() {    
                @Override    
                public void checkClientTrusted(X509Certificate[] chain,    
                        String authType) throws CertificateException {    
                }    
                @Override    
                public void checkServerTrusted(X509Certificate[] chain,    
                        String authType) throws CertificateException {    
                }    
                @Override    
                public X509Certificate[] getAcceptedIssuers() {    
                    return null;    
                }    
        };    
        ctx.init(null, new TrustManager[]{tm}, null);    
        SSLSocketFactory ssf = new SSLSocketFactory(ctx,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);    
        ClientConnectionManager ccm = this.getConnectionManager();    
        SchemeRegistry sr = ccm.getSchemeRegistry();    
        sr.register(new Scheme("https", 443, ssf));    
    }    
} 
