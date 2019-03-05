package com.zhxg.framework.base.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhxg.framework.spring.init.ApplicationProperties;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 替换内容工具类
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.utils.ReplaceUtil.java
 * <p>
 * Author: azmiu
 * <p>
 * Create Date: 2017年3月6日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public class ReplaceUtil {

    private final static Logger logger = LoggerFactory.getLogger(ReplaceUtil.class);

    /**
     * 正则替换
     *
     * @param content
     * @param rule
     * @param replace
     * @return
     */
    public static String regexReplace(String content, String rule, String replace) {
        if (StringUtils.isNotBlank(rule) || StringUtils.isNotBlank(replace)) {
            Pattern p = Pattern.compile(rule);
            Matcher matcher = p.matcher(content);
            return matcher.replaceAll(replace);
        } else {
            logger.error(ReplaceUtil.class.getName() + ":::转换异常!");
            return content;
        }
	}

    /**
     * 替换html标签
     *
     * @param content
     * @param objects
     * @return
     */
    public static String replaceHtmlTag(String content, Object... objects) {
        String res = regexReplace(content, "(\r)( )+(\r)", "<br />");
        res = res.replaceAll("<font color='#ff0000'>", "FONT BEGIN");
        res = res.replaceAll("</font>", "FONT END");
        res = regexReplace(res, "<.*?(?=[\u4e00-\u9fa5]|$)", " ");
        if (null == objects) {
            res = regexReplace(res, "(?<=[Z-a]).*?>", " ");
        }
        res = regexReplace(res, "(\\\\r\\\\n)+", "<br />");
        res = regexReplace(res, "(\\\\n)+", "<br />");
        res = regexReplace(res, "(\\\\r)+", "<br />");
        res = regexReplace(res, "(\\\\t)+", "<br />");
        res = regexReplace(res, "(\r\r)+", "<br />");
        res = regexReplace(res, "(\r\n) +", "<br />");
        res = regexReplace(res, "(\r\n)+", "<br />");
        res = regexReplace(res, "(\r)+", "<br />");
        res = regexReplace(res, "(\n)+", "<br />");
        res = regexReplace(res, "(\\r)( )+(\\r)", "<br />");
        res = regexReplace(res, "(\\r\\r)+", "<br />");
        res = regexReplace(res, "(\\r\\n) +", "<br />");
        res = regexReplace(res, "(\\r\\n)+", "<br />");
        res = regexReplace(res, "(\\r)+", "<br />");
        res = regexReplace(res, "(\\n)+", "<br />");
        res = regexReplace(res, "(\\t)+", "<br />");
        res = res.replaceAll("。 ", "。<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ");
        res = res.replaceAll("　　", "<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ");
        res = res.replaceAll("FONT BEGIN", "<font color='#ff0000'>");
        res = res.replaceAll("FONT END", "</font>");
        res = res.replace("[beginimg]", "</br><img src='");
        res = res.replace("[endimg]", "' />");
        res = res.replaceAll("beginimg", "</br><img src='");
        res = res.replaceAll("endimg", "' />");
        return res;
	}

    /**
     * 替换关键字为红色
     *
     * @param content
     * @param keywords
     * @return
     */
    public static String makeRedKeyWords(String content, String[] keywords) {
        for (String keyword : keywords) {
            if (StringUtils.isNotBlank(keyword)) {
                content = replaceKeyWords(content, keyword, "<font color='#FF0000'>" + keyword + "</font>");
            }
        }
        return content;
    }

    /**
     * 替换内容
     * 
     * @param content
     * @param target
     * @param replaceContent
     * @return
     */
    public static String replaceKeyWords(String content, String target, String replaceContent) {
        return content.replaceAll(target, replaceContent);
    }

    /**
     * 替换图片url
     *
     * @param content
     * @return
     */
    public static String replaceImgUrl(String content) {
        Pattern pattern = Pattern.compile("src='(.*?)' />", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);
        String imageUrl = "";
        while (matcher.find()) {
            imageUrl = matcher.group();
            String[] split = imageUrl.split("'");
            content = content.replace(split[1],
                    ApplicationProperties.getProperty("img.server")
                            + Base64.encodeBase64String(split[1].getBytes()).replace("/", "-").replace("\r\n", ""));
        }
        return content;
    }

    /**
     * 替换特殊符号
     *
     * @param content
     * @return
     */
    public static String replaceSpacileChar(String content) {
        String result = null;
        if (content == null) {
            return "";
        }
        result = content.replace("#", "");// '=','+','-','*' < >
        result = content.replace("=", "");
        result = content.replace("+", "");
        result = content.replace("-", "");
        result = content.replace("*", "");
        result = content.replace("<", "");
        result = content.replace(">", "");
        result = content.replace("$", "");
        content = content.replace("\0", " ");
        return result;
    }
}
