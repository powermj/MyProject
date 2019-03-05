package com.zhxg.framework.spring.init;

import java.util.HashMap;
import java.util.Map;

public class TrigonometricUtil {
    
    public static void main(String[] args) {
        getXAndY(0,0,10,10,31);
    }
    
    public static int getNodeNum(double r,double R){
        return (int) (2/(Math.asin(r/R)/Math.PI));
    }
    
    public static Map<String,Double> getXAndY(double x,double y,double R,int index,int nodeNum){ 
        double X =x +  R * Math.cos((360/nodeNum)*index * Math.PI / 180);
        double Y = y +R * Math.sin((360/nodeNum)*index *Math.PI /180);
        Map<String,Double> map= new HashMap<>();
        map.put("x", X);
        map.put("y", Y);
        System.out.println(map);
        return map;
    }

}
