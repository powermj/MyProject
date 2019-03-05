package com.zhxg.framework.base.utils;

public class SourceTypeUtil {

    public static String getSourceName(String sourceType) {
        String name = sourceType;
        switch (sourceType) {
            case "1":
            case "01": {
                name = "网媒";
                break;
            }
            case "2":
            case "02": {
                name = "论坛";
                break;
            }
            case "3":
            case "03": {
                name = "博客";
                break;
            }
            case "4":
            case "8":
            case "08":
            case "04": {
                name = "微博";
                break;
            }
            case "5":
            case "05": {
                name = "报刊";
                break;
            }
            case "6":
            case "06": {
                name = "微信";
                break;
            }
            case "7":
            case "07": {
                name = "视频";
                break;
            }
            case "9":
            case "09": {
                name = "APP";
                break;
            }
            case "10": {
                name = "评论";
                break;
            }
            case "11":{
                name = "小视频";
            }
            case "99": {
                name = "其他";
                break;
            }
        }
        return name;
    }

    public static String getSolrSourceName(String sourceType) {
        String name = sourceType;
        switch (sourceType) {
            case "1": {
                name = "网媒";
                break;
            }
            case "2": {
                name = "论坛";
                break;
            }
            case "3": {
                name = "博客";
                break;
            }
            case "4": {
                name = "微博";
                break;
            }
            case "5": {
                name = "报刊";
                break;
            }
            case "6": {
                name = "微信";
                break;
            }
            case "7": {
                name = "视频";
                break;
            }
            case "8": {
                name = "微博";
                break;
            }
            case "9": {
                name = "APP";
                break;
            }
            case "10": {
                name = "评论";
                break;
            }
            case "11":{
                name = "小视频";
            }
            case "99": {
                name = "其他";
                break;
            }
        }
        return name;
    }

}
