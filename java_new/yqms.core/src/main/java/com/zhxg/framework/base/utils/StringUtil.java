package com.zhxg.framework.base.utils;

import org.apache.log4j.Logger;


public class StringUtil {
    public static Logger logger = Logger.getLogger(StringUtil.class);
    public final static String NEWLINE = "\r";
    public final static String BLANK_SPACE = " ";
    private static final String BLANK = "";
    public final static String UTF_8 = "UTF-8";

    public static boolean isEmpty(String s) {
        return s == null || BLANK.compareTo(s) == 0;
    }

    public static boolean isEmpty(Object str) {
        return str == null || BLANK.equals(str);
    }

    public static int getInt(String str, int defaultVal) {
        if (str != null) {
            return Integer.valueOf(str);
        }
        return defaultVal;
    }

    public static String dealNull(String str) {
        return str != null ? str.trim() : BLANK;
    }

    public static String dealNull(Object obj) {
        String str = BLANK;
        if (obj != null) {
            if (obj instanceof String) {
                str = (String) obj;
            } else {
                str = obj.toString();
            }
        }
        return str;
    }
    
    
    public static String decodeUnicode(String theString) {    
        char aChar;    
        int len = theString.length();    
        StringBuffer outBuffer = new StringBuffer(len);    
        for (int x = 0; x < len;) {    
            aChar = theString.charAt(x++);    
            if (aChar == '\\') {    
                aChar = theString.charAt(x++);    
                if (aChar == 'u') {    
                    // Read the xxxx    
                    int value = 0;    
                    for (int i = 0; i < 4; i++) {    
                        aChar = theString.charAt(x++);    
                        switch (aChar) {    
                        case '0':    
                        case '1':    
                        case '2':    
                        case '3':    
                        case '4':    
                        case '5':    
                        case '6':    
                        case '7':    
                        case '8':    
                        case '9':    
                            value = (value << 4) + aChar - '0';    
                            break;    
                        case 'a':    
                        case 'b':    
                        case 'c':    
                        case 'd':    
                        case 'e':    
                        case 'f':    
                            value = (value << 4) + 10 + aChar - 'a';    
                            break;    
                        case 'A':    
                        case 'B':    
                        case 'C':    
                        case 'D':    
                        case 'E':    
                        case 'F':    
                            value = (value << 4) + 10 + aChar - 'A';    
                            break;    
                        default:    
                            throw new IllegalArgumentException(    
                                    "Malformed   \\uxxxx   encoding.");    
                        }    
        
                    }    
                    outBuffer.append((char) value);    
                } else {    
                    if (aChar == 't')    
                        aChar = '\t';    
                    else if (aChar == 'r')    
                        aChar = '\r';    
                    else if (aChar == 'n')    
                        aChar = '\n';    
                    else if (aChar == 'f')    
                        aChar = '\f';    
                    outBuffer.append(aChar);    
                }    
            } else    
                outBuffer.append(aChar);    
        }    
        return outBuffer.toString();    
    }  
}
