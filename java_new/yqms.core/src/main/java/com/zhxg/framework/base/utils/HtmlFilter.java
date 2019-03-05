package com.zhxg.framework.base.utils;

import org.apache.poi.POIDocument;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Description: </p>
 *
 * @author zyl
 * @version 1.0
 * @date 2017年11月9日
 */
public class HtmlFilter {
    // 页面过滤
    public static String filter(String content) {

        String regEx_html = "<\\/?(?!strong|br|p|/p|font|/font|img|/img)[^>]*>";
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(content);
        content = m_html.replaceAll(""); // 过滤html标签

        return content;
    }

    public static String filterContent(String content) {

        // content=content.replaceAll("<!--.*?-->", "");解决去除单行注释
        content = content.replaceAll("<!--(.|\\s)*?-->", "");// 解决去除多行注释
        String regEx_html = "<\\/?(?!br|p|/p|font|/font|h|/h|ul|/ul|span|/span|img|/img)[^>]*>";
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(content);
        content = m_html.replaceAll(""); // 过滤html标签

        return content;
    }

    public static String delHTMLTag(String htmlStr) {
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
        Pattern p_style = Pattern
                .compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签
        return htmlStr.trim(); // 返回文本字符串
    }

    // 导出word专用过滤
    public static String filterWord(String Str) {
        // string regexstr = @"<[^>]*>";
        String regEx_html = "<[^>]*>";
        ;
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(Str);
        Str = m_html.replaceAll(""); // 过滤html标签

        Str = Str.replaceAll("<!--(.|\\s)*?-->", "");// 注释

        Str = Str.replaceAll("&nbsp;", "");
        Str = Str.replaceAll("&ldquo", "“");// 左引号
        Str = Str.replaceAll("&rdquo;", "”");// 右引号
        Str = Str.replaceAll("&middot;", "……");// 省略号
        Str = Str.replaceAll("&mdash;", "——");
        Str = Str.replace("&lt;", "<");
        Str = Str.replace("&gt;", ">");
        Str = Str.replace("&circ;", "^");
        Str = Str.replace("&tilde;", "~");
        Str = Str.replace("&ensp;", "[ ]");
        Str = Str.replace("&emsp;", "[ ]");
        Str = Str.replace("&thinsp;", "[ ]");
        Str = Str.replace("&ndash;", "–");
        Str = Str.replace("&lsquo;", "‘");
        Str = Str.replace("&rsquo;", "’");
        Str = Str.replace("&sbquo;", "‚");
        Str = Str.replace("&bdquo;", "„");
        Str = Str.replace("&dagger;", "");
        Str = Str.replace("&Dagger;", "");
        Str = Str.replace("&permil;", "");
        Str = Str.replace("&lsaquo;", "‹");
        Str = Str.replace("&rsaquo;", "›");
        Str = Str.replace("&euro;", "€");
        Str = Str.replace("&hellip;;", "…");
        Str = Str.replace("&permil;", "‰");
        Str = Str.replace("&hellip;;", "…");
        Str = Str.replace("&hellip;;", "…");
        Str = Str.replace("                 ", "<br />");

        return Str;
    }

    public static String filterall(String content) {
        String html = content;
        html = html.replaceAll("(?is)<!DOCTYPE.*?>", "");
        html = html.replaceAll("(?is)<!--.*?-->", ""); // remove html comment
        html = html.replaceAll("(?is)<script.*?>.*?</script>", ""); // remove
        // javascript
        html = html.replaceAll("(?is)<style.*?>.*?</style>", ""); // remove css
//poi添加链接字体会报错，URI targetURI = new URI(target);
//        html = html.replaceAll("&.{2,5};|&#.{2,5};", " "); // remove special
        // char
        html = html.replaceAll("(?is)<.*?>", "");
        return html;
    }

    public static String fiterHtmlTag(String str, String tag) {
        String regxp = "<\\s*" + tag + "\\s+([^>]*)\\s*>";
        Pattern pattern = Pattern.compile(regxp);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result1 = matcher.find();
        while (result1) {
            matcher.appendReplacement(sb, "");
            result1 = matcher.find();
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static void main(String[] args) {

        /*
         * Stringstr=
         * "   <p>　&nbsp;&nbsp;&nbsp;&nbsp;　中新河北网保定5月29日电 28日，保定清苑县与总投资20亿元的中国汽车零部件(保定)产业基地一期年产100万套电子电器项目正式签约。这标志着该县为打造保定先进装备制造业基地迈出坚实的步伐。       &nbsp;        </p><p>　"
         * ;
         *
         * String str3=HtmlFilter.filterall(str);
         *
         *
         * System.out.println("***************"+str3); String
         * str2=HtmlFilter.filterWord(str);
         */
        String str2 = "<em df>xgsdgsdgsd</em>";

        str2 = str2.replaceAll("<\\s*em\\s+([^>]*)\\s*>", "");
        str2 = str2.replaceAll("</em>", "");

        System.out.println("***************" + str2);

    }

    public static String fullWidth2halfWidth(String fullWidthStr) {
        if (null == fullWidthStr || fullWidthStr.length() <= 0) {
            return "";
        }
        char[] charArray = fullWidthStr.toCharArray();
        //对全角字符转换的char数组遍历
        for (int i = 0; i < charArray.length; ++i) {
            int charIntValue = (int) charArray[i];
            //如果符合转换关系,将对应下标之间减掉偏移量65248;如果是空格的话,直接做转换
            if (charIntValue >= 65281 && charIntValue <= 65374) {
                charArray[i] = (char) (charIntValue - 65248);
            } else if (charIntValue == 12288) {
                charArray[i] = (char) 32;
            }
        }
        return new String(charArray);
    }
}
