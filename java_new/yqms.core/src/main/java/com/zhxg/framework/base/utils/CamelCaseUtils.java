package com.zhxg.framework.base.utils;


/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 驼峰命名与下划线命名互相转换
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.utils.CamelCaseUtils.java
 * <p>
 * Author: azmiu
 * <p>
 * Create Date: 2017年2月24日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public class CamelCaseUtils {

    private static final char SEPARATOR = '_';

    /**
     * 将驼峰转换为下划线
     * 
     * @param s 将要被转换的字符串
     * @return String
     * @throws Exception
     */
    public static String toUnderlineName(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i >= 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    if (i > 0) {
                        sb.append(SEPARATOR);
                    }
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * 将下划线转为驼峰
     * 
     * @param s 将要被转换的字符串
     * @return String
     * @throws Exception
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static void main(String[] args) {
        System.out.println(CamelCaseUtils.toUnderlineName("ISOCertifiedStaff"));
        System.out.println(CamelCaseUtils.toUnderlineName("CertifiedStaff"));
        System.out.println(CamelCaseUtils.toUnderlineName("UserID"));
        System.out.println(CamelCaseUtils.toCamelCase("iso_certified_staff"));
        System.out.println(CamelCaseUtils.toCamelCase("certified_staff"));
        System.out.println(CamelCaseUtils.toCamelCase("user_id"));
    }
}
