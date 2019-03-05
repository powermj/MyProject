package com.zhxg.framework.base.utils;

public class JxlsUtils {
    
    /**
     * 20180312173737 转换成  2018-03-12 17:37
     * @param date
     * @return
     */
    public String formatDate(String date){
        StringBuffer stringBuffer = new StringBuffer(date);
        stringBuffer.insert(4,"-").insert(7, "-").insert(10," ").insert(13,":");
        return stringBuffer.substring(0, 16);
    }
    
}
