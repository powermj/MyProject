package com.zhxg.yqzj.service.util;

public class MobileCloudVersionControlUtil {

    public static int getKeyWordNum(String customerType,String version) {
        int keyWordNum = 0;
        //政府
        if("1".equals(customerType)){
            switch (version) {
                case "B1": {
                    keyWordNum = 300;
                    break;
                }
                case "B2": {
                    keyWordNum = 500;
                    break;
                }
                case "B3": {
                    keyWordNum = 1000;
                    break;
                }
                case "B4": {
                    keyWordNum = 100;
                    break;
                }
                default:{
                    keyWordNum = 100;
                }
            } 
        }
        //企业
        if("4".equals(customerType)){
            switch (version) {
                case "B1": {
                    keyWordNum = 5;
                    break;
                }
                case "B2": {
                    keyWordNum = 10;
                    break;
                }
                case "B3": {
                    keyWordNum = 20;
                    break;
                }
                case "B4": {
                    keyWordNum = 2;
                    break;
                }
                default:{
                    keyWordNum = 2;
                }
            }  
        }
        
        return keyWordNum;
    }

  

}
