package com.zhxg.fgw.controller.base;


import com.zhxg.fgw.utils.HttpHelper;


/**
 * 应用相关的基础类: 用于获取session里存放的信息及一些应用相关的常用方法
 */
public class BaseAppController extends HttpHelper {


    protected static Integer obj2Integer(Object obj, Integer def) {
        if (obj == null) {
            return def;
        }

        try {
            return Integer.parseInt(obj.toString());
        } catch (NumberFormatException e) {
            System.err.println("obj2Integer error, object: " + obj + ", error: " + e.getMessage());
        }

        return def;
    }

    protected static int obj2int(Object obj, int def) {
        return obj2Integer(obj, def);
    }

    protected static int obj2int(Object obj) {
        return obj2Integer(obj, 0);
    }

    protected static String obj2String(Object obj) {
        return obj == null ? null : obj.toString();
    }

    protected static String obj2Str(Object obj) {
        return obj == null ? "" : obj.toString();
    }

}
