package com.zhxg.framework.base.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class PhantomJSUtil {
    
    public static void main(String[] args) {
        JSONArray arr = new JSONArray();
        Long s = 1524758400000L;
        for(int i = 0;i<10;i++){
            JSONObject json = new JSONObject();
            json.put("row", "正面");
            json.put("count", RandomUtils.nextInt(100));
            json.put("col", s+3600000l*i+"");
            arr.add(json);
        }
        for(int i = 0;i<10;i++){
            JSONObject json = new JSONObject();
            json.put("row", "负面");
            json.put("count", RandomUtils.nextInt(100));
            json.put("col", s+3600000l*i+"");
            arr.add(json);
        }
        for(int i = 0;i<10;i++){
            JSONObject json = new JSONObject();
            json.put("row", "中性");
            json.put("count", RandomUtils.nextInt(100));
            json.put("col", s+3600000l*i+"");
            arr.add(json);
        }
//        System.out.println(dealData(arr.toJSONString()));
        saveSurfModelUrlToImg(dealLineData(arr.toJSONString(),"倾向性"),"line");
    }
    
    public static String dealLineData(String arr,String title){
        JSONArray array = JSON.parseArray(arr);
        array.sort(Comparator.comparing(obj -> ((JSONObject) obj).getString("col")));
        Map<String,String> distinctMap = new HashMap<>();
        Map<String,List<String>> map = new HashMap<>();
        List<String> x = new ArrayList<String>();
        Set<String> line = new HashSet<String>();
        List<String> legend = new ArrayList<String>();
        for(Object obj : array){
            JSONObject json = (JSONObject) obj;
            if(distinctMap.get(json.getString("col")+"_"+json.getString("row"))==null){
                distinctMap.put(json.getString("col")+"_"+json.getString("row"), json.getString("count"));
            }else{
                continue;
            }
            if(!x.contains(json.getString("col"))){
                x.add(json.getString("col"));
            }
            
            if(line.add(json.getString("row"))){
                legend.add(json.getString("row"));
                List<String> list = new ArrayList<>();
                list.add(json.getString("count"));
                map.put(json.getString("row"), list);
            }else{
                List<String> list = map.get(json.getString("row"));
                list.add(json.getString("count"));
            }
        }
        JSONObject result = new JSONObject();
        JSONArray series = new JSONArray();
        for(String key:map.keySet()){
            JSONObject l = new JSONObject();
            l.put("name", key);
            l.put("type", "line");
            l.put("data", map.get(key));
            l.put("smooth", true);
            series.add(l);
        }
        result.put("title", title);
        result.put("series", series);
        result.put("legend", legend);
        result.put("xAxis", x);
        return result.toJSONString();
    }
    
    public static String dealBarData2(String arr,String title){
        JSONArray array = JSON.parseArray(arr);
        List<String> x = new ArrayList<String>();
        List<String> legend = new ArrayList<String>();
        List<String> list = new ArrayList<>();
        for(Object obj : array){
            JSONObject json = (JSONObject) obj;
            x.add(json.getString("row"));
            legend.add(json.getString("row"));
            list.add(json.getString("count"));
        }
        JSONObject result = new JSONObject();
        JSONArray series = new JSONArray();
        JSONObject l = new JSONObject();
        l.put("name", "媒体类型");
        l.put("type", "bar");
        l.put("data", list);
        series.add(l);
        
        result.put("title", title);
        result.put("series", series);
        result.put("legend", legend);
        result.put("xAxis", x);
        return result.toJSONString();
    }
    
    public static String dealBarData(String arr,String title){
        JSONArray array = JSON.parseArray(arr);
        array.sort(Comparator.comparing(obj -> ((JSONObject) obj).getString("col")));
        Map<String,List<String>> map = new HashMap<>();
        List<String> x = new ArrayList<String>();
        Set<String> line = new HashSet<String>();
        List<String> legend = new ArrayList<String>();
        Map<String,String> distinctMap = new HashMap<>();
        for(Object obj : array){
            JSONObject json = (JSONObject) obj;
            if(distinctMap.get(json.getString("col")+"_"+json.getString("row"))==null){
                distinctMap.put(json.getString("col")+"_"+json.getString("row"), json.getString("count"));
            }else{
                continue;
            }
            
            if(!x.contains(json.getString("col"))){
                x.add(json.getString("col"));
            }
            
            if(line.add(json.getString("row"))){
                legend.add(json.getString("row"));
                List<String> list = new ArrayList<>();
                list.add(json.getString("count"));
                map.put(json.getString("row"), list);
            }else{
                List<String> list = map.get(json.getString("row"));
                list.add(json.getString("count"));
            }
        }
        JSONObject result = new JSONObject();
        JSONArray series = new JSONArray();
        for(String key:map.keySet()){
            JSONObject l = new JSONObject();
            l.put("name", key);
            l.put("type", "bar");
            l.put("data", map.get(key));
            series.add(l);
        }
        result.put("title", title);
        result.put("series", series);
        result.put("legend", legend);
        result.put("xAxis", x);
        return result.toJSONString();
    }
    
    public static String dealPieData(String arr,String title){
        JSONArray array = JSON.parseArray(arr);
        List<String> legend = new ArrayList<String>();
        JSONObject result = new JSONObject();
        List<Map<String,Object>> dataList = new ArrayList<>();
        for(Object obj : array){
            JSONObject json = (JSONObject) obj;
            legend.add(json.getString("row"));
            Map<String,Object> data = new HashMap<>();
            data.put("name", json.getString("row"));
            data.put("value", json.getString("count"));
            dataList.add(data);
        }
        result.put("title", title);
        result.put("data", dataList);
        result.put("legend", legend);
        return result.toJSONString();
    }

    public static byte[] saveSurfModelUrlToImg(String surfData,String imgType) {
        PhantomJSDriver driver=getPhantomJSDriver();
        String lineTxt="";
        try{
         // 让浏览器访问空间主页
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get("http://192.168.16.190:81/appShareSnapshot/html/echarts.html");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            //设置surf数据到页面
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            //展示echarts
            js.executeScript("show"+imgType+"Img("+surfData+")");
            //加入一段休眠时间，防止js执行没完成就进行的 读取echart图片数据的功能。
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
            //获取echart图片数据
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            lineTxt=(String) js.executeScript("return returnEchartImg("+imgType+"Echarts)");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            driver.close();
            driver.quit();
        }
        return Base64Utils.decodeFromString(lineTxt.replace("data:image/png;base64,", ""));
    }

    private static PhantomJSDriver getPhantomJSDriver() {
        //设置必要参数
        DesiredCapabilities dcaps = new DesiredCapabilities();
        //ssl证书支持
        dcaps.setCapability("acceptSslCerts", true);
        //截屏支持
        dcaps.setCapability("takesScreenshot", true);
        //css搜索支持
        dcaps.setCapability("cssSelectorsEnabled", true);
        //js支持
        dcaps.setJavascriptEnabled(true);
        //驱动支持
        
        dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS, new String[] { "--logFile=none", "--logLevel=DEBUG" });
        
//        String osname = System.getProperties().getProperty("os.name");
//        if (osname.equals("Linux")) {//判断系统的环境win or Linux
            dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,"/usr/bin/phantomjs");
//        } else {
//            dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,"D:\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
//        }
        //创建无界面浏览器对象
        PhantomJSDriver driver = new PhantomJSDriver(dcaps);
        return driver;
    }
    
    public static String GenerateImage(String imgStr,String imgFilePath)  
    {   //对字节数组字符串进行Base64解码并生成图片  
        if (imgStr == null) //图像数据为空  
            return null;  
        try   
        {  
            //Base64解码  
            byte[] b = Base64Utils.decodeFromString(imgStr.replace("data:image/png;base64,", ""));  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return imgFilePath;  
        }   
        catch (Exception e)   
        {  
            return null;  
        }  
    }  

}
