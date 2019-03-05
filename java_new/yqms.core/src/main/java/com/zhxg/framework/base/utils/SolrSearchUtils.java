package com.zhxg.framework.base.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhxg.framework.base.constants.Constant;
import com.zhxg.framework.base.exception.SysException;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: <solr查询语句拼写工具类>
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.utils.SolrSearchUtils.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年5月9日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public class SolrSearchUtils {

    private final static Logger log = LoggerFactory.getLogger(SolrSearchUtils.class);

    public static HashMap<String, Object> getSolrSearchSql(Map<String, Object> paramMap) throws SysException {
        // 获取相关参数并编写solr查询语句
        String searchType = paramMap.get("searchType").toString();
        StringBuffer solrSql = new StringBuffer(500);
        // 获取查询时间
        String beginTime = paramMap.get("beginTime").toString();
        String endTime = paramMap.get("endTime").toString();
        if (StringUtils.isNotBlank(beginTime) && StringUtils.isNotBlank(endTime)) {
            solrSql.append(" && kvCtime:[");
            solrSql.append(beginTime.replace("-", "").replace(" ", "")
                    .replace(":", ""));
            solrSql.append(" TO ");
            solrSql.append(endTime.replace("-", "").replace(" ", "")
                    .replace(":", ""));
            solrSql.append("]");
        } else if (StringUtils.isNotBlank(beginTime) && StringUtils.isBlank(endTime)) {
            solrSql.append(" && kvCtime:[");
            solrSql.append(beginTime.replace("-", "").replace(" ", "")
                    .replace(":", ""));
            solrSql.append(" TO * ]");

        }
//        int isAbroadOpen = Integer.parseInt(paramMap.get("isAbroadOpen").toString());
//        if(isAbroadOpen!=1){
//            solrSql.append(" && krIslocal:1 ");
//        }
        // 获取媒体类型
        String kvSourcetype = paramMap.get("kvSourcetype").toString();
        if (StringUtils.isNotBlank(kvSourcetype)) {
            solrSql.append(" && kvSourcetype:(");
            solrSql.append(kvSourcetype.replace(",", " || "));
            solrSql.append(")");
        }
        // 获取倾向性
        String kvOrientation = paramMap.get("kvOrientation").toString();
        if (StringUtils.isNotBlank(kvOrientation)) {
            solrSql.append(" && kvOrientation:(");
            solrSql.append(kvOrientation.replace(",", " || "));
            solrSql.append(")");
        }
        // 获取微博类型
        String wechatInfoType = paramMap.get("wechatInfoType").toString();
        if (StringUtils.isNotBlank(wechatInfoType)) {
            solrSql.append(" && wechatInfoType:(0 || ");
            solrSql.append(wechatInfoType.replace(",", " || "));
            solrSql.append(")");
        }
        
        // 图片识别
        String isOcr = paramMap.get("isOcr")==null?"":paramMap.get("isOcr").toString();
        if (StringUtils.isNotBlank(isOcr)) {
            solrSql.append(" && krIsOcr:"+isOcr);
        }
//        solrSql.append(" && krIsHistory:0 ");
        // 获取匹配范围
        String matchRange = paramMap.get("matchRange").toString();
        // 获取搜索框内关键词
        String message = paramMap.get("message").toString();

        StringBuffer q = new StringBuffer(500);
        JSONArray words = new JSONArray();
        try {
            JSONObject json = new JSONObject();
            json.put("content", message);
            String resultWords = HttpUtil.post(Constant.WORD_ANALYSIS_URL, json.toJSONString());
            resultWords = StringUtil.decodeUnicode(resultWords);
            JSONObject jsonWords = JSON.parseObject(resultWords);
            words = jsonWords.getJSONArray("result");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new SysException();
        }
        if ("content".equals(matchRange)) {
            if(searchType.equals("1")){
                for(String word:message.split(" ")){
                    if(q.length()==0){
                        q.append("kvContent:"+word);
                    }else{
                        q.append(" && "+"kvContent:"+word);
                    }
                }
            }else{
                for(String word:message.split(" ")){
                    word = "\""+word+"\"";
                    if(q.length()==0){
                        q.append("("+"kvTitle:"+word+" || "+"kvContent:"+word+")");
                    }else{
                        q.append(" && ("+"kvTitle:"+word+" || "+"kvContent:"+word+")");
                    }
                }
            }
         }else{
             for(String word:message.split(" ")){
                 if(!searchType.equals("1")){
                     word = "\""+word+"\"";
                 }
                 if(q.length()==0){
                     q.append("kvTitle:"+word);
                 }else{
                     q.append(" && "+"kvTitle:"+word);
                 }
             }
         }
        // 处理sql信息
        String sql = "";
        if (solrSql.length() > 3) {
            sql = solrSql.substring(3);
        }
        HashMap<String, Object> sqlMap = new HashMap<>();
        sqlMap.put("filterSql", sql);
        sqlMap.put("messageSql", q);
        return sqlMap;
    }

    /**
     * 拼接solr搜索关键词部分字符串
     *
     * @param query
     * @param message
     * @param matchRange
     * @return
     */
    public static StringBuffer pieceTogetherMessage(StringBuffer query,
            String message, String matchRange) {
        if (!message.contains(" ")) {
            if ("content".equals(matchRange)) {
                query.append(" && (kvTitle:");
                query.append("\"" + translateString(message) + "\"");
                query.append(" || kvContent:");
                query.append("\"" + translateString(message) + "\"");
                query.append(")");
            }
            if ("title".equals(matchRange)) {
                query.append(" && kvTitle:");
                query.append("\"" + translateString(message) + "\"");
            }
        } else {
            String[] messages = message.split(" ");
            if ("content".equals(matchRange)) {
                query.append(" && (");
                query = pieceTogether2(query, messages);
                query.append(")");
            }
            if ("title".equals(matchRange)) {
                query.append(" && kvTitle:");
                query.append("\"" + translateString(messages[0]) + "\"");
                query = pieceTogether(query, messages, "kvTitle");
            }
        }
        return query;
    }

    /**
     * 处理关键词信息
     *
     * @param field
     * @return
     */
    public static String translateString(String field) {
        return field.replace("'", "\\'").replace("%", "\\%");
    }

    /**
     * 拼接字符串
     *
     * @param str
     * @param messages
     * @return
     */
    private static StringBuffer pieceTogether2(StringBuffer str, String[] messages) {
        for (int i = 0; i < messages.length; i++) {
            if (i == 0) {
                str.append("(kvTitle:\"" + translateString(messages[i]) + "\" || kvContent:\""
                        + messages[i] + "\")");
            } else {
                str.append(" && (kvTitle:\"" + translateString(messages[i])
                        + "\" || kvContent:\"" + translateString(messages[i]) + "\")");
            }
        }
        return str;
    }

    /**
     * 拼接字符串
     *
     * @param str
     * @param messages
     * @param fieldName
     * @return
     */
    private static StringBuffer pieceTogether(StringBuffer str, String[] messages,
            String fieldName) {
        for (int i = 1; i < messages.length; i++) {
            str.append(" && " + fieldName + ":");
            str.append("\"" + messages[i] + "\"");
        }
        return str;
    }
}
