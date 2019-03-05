package com.zhxg.yqzj.service.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import com.zhxg.framework.base.utils.PropertiesUtil;
import com.zhxg.framework.base.utils.RedisUtil;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: <对标题或内容关键信息进行标红>
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.utils.ExcelExportUtil.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年5月11日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public class DetailOperateUtil {

    public static final String COLOR = "#ff0000"; 
    
    /**
     * 对标题信息进行标红处理 
     * @param keyWord
     * @param kvTitle
     */
    public static String makeRedForTitle(String keyWord,String kvTitle){
        String[] redWord = keyWord.split(",");
        for (int j = 0; j < redWord.length; j++) {
            String key = redWord[j].trim();

            if (key.equals("")) {
                continue;
            }

            if (kvTitle != null && kvTitle.contains(key)) {
                kvTitle = kvTitle.replace(key, "<font color="+COLOR+">" + key+ "</font>");
            }
        }
        return kvTitle;
    }
    
    /**
     * 对内容信息进行标红处理 
     * @param keyWord
     * @param kvContent
     */
    public static String makeRedForContent(String keyWord,String kvContent){
        String[] redWord = keyWord.split(",");
        for (int j = 0; j < redWord.length; j++) {
            String key = redWord[j].trim();

            if (key.equals("")) {
                continue;
            }

            if (kvContent != null && kvContent.contains(key)) {
                kvContent = kvContent.replaceAll(key, "<font color="+COLOR+">" + key+ "</font>");
            }
        }
        return kvContent;
    }
    
    /**
     * 将内容中包含的图片替换成html标签 
     * @param kvContent
     * @throws Exception 
     */
    public static String changeImgToHtml(String kvContent,String agentId){
        String result = RegexReplace(kvContent, "(\r)( )+(\r)", "<br />");
        result = result.replace("[beginimg]", "</br><img src=\"");
        result = result.replace("[endimg]", "\" />");
        result = result.replaceAll("beginimg", "</br><img src=\"");
        result = result.replaceAll("endimg", "\" />");
        
        // 标签font-family后面的字体 关键词  含有空格的关键词都多加了单引号  现把单引号替换成双引号 
        result = replaceSomeKey(result);
        //对图片地址进行处理
        result = opreateImgUrl(result,agentId);
        
        System.out.println(result);
        
        return result;
    }
    
    public static String RegexReplace(String content, String rule,String replace) {
        String zc1 = "";

        if (isNullOrEmpty(content) || isNullOrEmpty(rule)) {
            return "";
        }

        Pattern p = Pattern.compile(rule);// Pattern.CASE_INSENSITIVE
        Matcher matcher = p.matcher(content);

        zc1 = matcher.replaceAll(replace);

		return zc1;
	}

	public static boolean isNullOrEmpty(String input) {
		return input == null || input.length() == 0;
	}

	/**
	 * 修改图片路径
	 * 
	 * @param result
	 * @return
	 * @throws Exception
	 */
	public static String opreateImgUrl(String content, String agentId) {
		// 处理内容自带图片
		Set<String> imgUrls = getImgStr(content);
		for (String imgUrl : imgUrls) {
			String imgHost = "";
			// 代理商需要更换域名
			if (StringUtils.isNotBlank(agentId)) {
				imgHost = RedisUtil.hget(10, "AGENT_IMG_HOST", agentId);
			}
			// 对网页图片原始路径进行加密
			String originalUrl = Base64.encodeBase64String(imgUrl.getBytes());

			if (StringUtils.isEmpty(imgHost)) {
				imgHost = "https://" + PropertiesUtil.getValue("img.server");
			}
			String currentUrl = imgHost + "/download/img/" + originalUrl.replace("/", "-").replace("\r\n", "");
			content = content.replace(imgUrl, currentUrl);
		}
		// 处理内容背景图
		Set<String> bgimgUrls = getBgImgStr(content);
		for (String imgUrl : bgimgUrls) {
			String imgHost = "";
			// 代理商需要更换域名
			if (StringUtils.isNotBlank(agentId)) {
				imgHost = RedisUtil.hget(10, "AGENT_IMG_HOST", agentId);
			}
			// 对网页图片原始路径进行加密
			String originalUrl = Base64.encodeBase64String(imgUrl.getBytes());

			if (StringUtils.isEmpty(imgHost)) {
				imgHost = "https://" + PropertiesUtil.getValue("img.server");
			}
			String currentUrl = imgHost + "/download/img/" + originalUrl.replace("/", "-").replace("\r\n", "");
			content = content.replace(imgUrl, currentUrl);
		}
		content = content.replace("data-src", "src");
		return content;
	}

	/**
	 * 得到网页中图片的地址
	 */
	public static Set<String> getImgStr(String content) {
		Set<String> contentPicUrl = new HashSet<>();
		String img = "";
		Pattern p_image;
		Matcher m_image;
		// String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
		String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
		p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
		m_image = p_image.matcher(content);
		while (m_image.find()) {
			// 得到<img />数据
			img = m_image.group();
			// 匹配<img>中的src数据
			Matcher match = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
			while (match.find()) {
				contentPicUrl.add(match.group(1));
			}
		}
		return contentPicUrl;
	}

	/**
	 * 获取背景图url
	 */
	public static Set<String> getBgImgStr(String content) {
		Set<String> contentBgImgUrls = new HashSet<>();
		String img = "";
		Pattern p_image;
		Matcher m_image;
		// String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
		String regEx_img = "url\\(\\s*\"?(.*?)(\"|\\)|\\s+)";
		p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
		m_image = p_image.matcher(content);
		while (m_image.find()) {
			// 得到<img />数据
			img = m_image.group(1);
			contentBgImgUrls.add(img);
		}
		return contentBgImgUrls;
	}
	public static String filterMedia(String content, String agentId) {
		if (StringUtils.isNotBlank(content)) {
			String[] contents = content.split(" ");
			content = contents[0];
			try {
				String imgHost = "";
				if (StringUtils.isNotBlank(agentId) && !"-1".equals(agentId)) {
					imgHost = RedisUtil.hget(10, "AGENT_IMG_HOST", agentId);
				}
				if (StringUtils.isEmpty(imgHost)) {
                    imgHost = PropertiesUtil.getValue("img.server");
				}
				String oldurl = Base64.encodeBase64String(content.getBytes());
				String newurl = imgHost + "/download/img/" + oldurl.replace("/", "-").replace("\r\n", "");
				content = content.replace(content, newurl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return content;
	}

	/**
	 * 对标题信息进行标红处理
	 * 
	 * @param keyWord
	 * @param kvTitle
	 */

	/**
	 * 替换当前已知的关键词
	 */
	public static String replaceSomeKey(String content) {
		content = content.replaceAll("'Helvetica Neue'", "\"Helvetica Neue\"");
		content = content.replaceAll("'PingFang SC'", "\"PingFang SC\"");
		content = content.replaceAll("'Hiragino Sans GB'", "\"Hiragino Sans GB\"");
		content = content.replaceAll("'Microsoft YaHei UI'", "\"Microsoft YaHei UI\"");
		content = content.replaceAll("'Microsoft YaHei'", "\"Microsoft YaHei\"");
		return content;
	}
}
