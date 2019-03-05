package com.zhxg.framework.base.utils;

public class OrientationUtil {

    public static String getSourceName(String orientation) {
        String name = "";
        switch (orientation) {
            case "1": {
                name = "正面";
                break;
            }
            case "2": {
                name = "负面";
                break;
            }
            case "3": {
                name = "中性";
                break;
            }
        }
        return name;
    }

}
