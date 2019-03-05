package com.zhxg.yqzj.service.impl.v1;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.constants.Constant;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.ParamsNullException;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.framework.base.utils.HtmlFilter;
import com.zhxg.framework.base.utils.HttpUtil;
import com.zhxg.framework.base.utils.ParamsUtil;
import com.zhxg.framework.base.utils.PropertiesUtil;
import com.zhxg.framework.base.utils.StringUtil;
import com.zhxg.framework.base.utils.UUIDUtils;
import com.zhxg.yqzj.dao.v1.SolrConditionDao;
import com.zhxg.yqzj.dao.v1.SolrDao;
import com.zhxg.yqzj.dao.v1.UserDao;
import com.zhxg.yqzj.dao.v1.WarningDao;
import com.zhxg.yqzj.dao.v1.YqgzDao;
import com.zhxg.yqzj.dao.v1.YqjbDao;
import com.zhxg.yqzj.entities.v1.QueryResult;
import com.zhxg.yqzj.entities.v1.User;
import com.zhxg.yqzj.service.exception.myfocus.RepeatOperateException;
import com.zhxg.yqzj.service.exception.myfocus.SendEmailErrorException;
import com.zhxg.yqzj.service.exception.solr.SolrException;
import com.zhxg.yqzj.service.exception.solr.WarningException;
import com.zhxg.yqzj.service.v1.SolrOperateService;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.service
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: <对此类的描述>
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqzj.service.impl.v1.SolrOperateServiceImpl.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年4月26日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
@Service
public class SolrOperateServiceImpl extends BaseServiceImpl<BaseEntity> implements SolrOperateService {

    private static final String KM_ID_IS_NULL = "kvUuid不能为空";
    private static final String REPEAT_OPERATE = "添加信息存在重复，重复信息将被自动过滤";
    private static final int ABSTRACT_LENGTH = 400;
    private static final int TITLE_LENGTH = 200;
    private static final int KSINFO_LENGTH = 100;
    private static final String DB_ERROR_INSERT_YQJB = "添加简报信息失败";
    private static final String DB_ERROR_INSERT_YQJB_CNT = "添加简报内容失败";
    private static final String DB_ERROR_INSERT_YQGZ = "添加关注信息失败";
    private static final String DB_ERROR_INSERT_YQGZ_CNT = "添加关注内容失败";
    private static final String DB_ERROR_INSERT_YQYJ = "添加预警信息失败";
    private static final String DB_ERROR_INSERT_CONDITION = "添加筛选条件失败";
    private static final String SEND_EMAIL_ERROR = "邮件发送失败";
    private static final String SEND_WARNING_ERROR = "推送预警失败";
    private static final String VAGUE_SEARCH = "1";
    private static final String ACCURATE_SEARCH = "2";
    
    

    @Autowired
    private SolrDao solrDao;
    @Autowired
    private YqjbDao yqjbDao;
    @Autowired
    private YqgzDao yqgzDao;
    @Autowired
    private WarningDao warningDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private SolrConditionDao solrConditionDao;

    private final static Logger log = LoggerFactory.getLogger(SolrOperateServiceImpl.class);

    @Override
    protected BaseDao<BaseEntity> getBaseDao() {
        return this.solrDao;
    }

    /**
     * solr根据关键词搜索列表内容
     * @throws SysException 
     */
    @Override
    public PageInfo<T> solrSearch(Map<String, Object> paramMap, Pagination pageInfo) throws SysException {
        
//        User user = this.userDao.getUserByKuId(paramMap.get(SysConstants._KUID)+"");
        // 获取相关参数并编写solr查询语句
        StringBuffer solrSql = new StringBuffer(500);
        // 获取查询时间
        String beginTime = paramMap.get("beginTime").toString();
        String endTime = paramMap.get("endTime").toString();
        String searchType = paramMap.get("searchType").toString();
        String merge = paramMap.get("merge").toString();
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
//        if(user.getIsAbroadOpen()!=1){
//            solrSql.append(" && krIslocal:1 ");
//        }
        // 获取媒体类型
        String kvSourcetype = paramMap.get("kvSourcetype")==null?"":paramMap.get("kvSourcetype").toString();
        if (StringUtils.isNotBlank(kvSourcetype)) {
            solrSql.append(" && kvSourcetype:(");
            solrSql.append(kvSourcetype.replace(",", " || "));
            solrSql.append(")");
        }
        // 获取倾向性
        String kvOrientation = paramMap.get("kvOrientation")==null?"":paramMap.get("kvOrientation").toString();
        if (StringUtils.isNotBlank(kvOrientation)) {
            solrSql.append(" && kvOrientation:(");
            solrSql.append(kvOrientation.replace(",", " || "));
            solrSql.append(")");
        }
        // 获取微博类型
        String wechatInfoType = paramMap.get("wechatInfoType")==null?"":paramMap.get("wechatInfoType").toString();
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
        try{
//            if(searchType.equals(VAGUE_SEARCH)){
                JSONObject json = new JSONObject();
                json.put("content", message);
                String resultWords =HttpUtil.post(Constant.WORD_ANALYSIS_URL, json.toJSONString());
                resultWords = StringUtil.decodeUnicode(resultWords);
                JSONObject jsonWords = JSON.parseObject(resultWords);
                words = jsonWords.getJSONArray("result");  
//            }else{
//                words = (JSONArray) JSON.toJSON(message.split(" "));
//            }
            
        }catch(Exception e){
            log.error(e.getMessage(),e);
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

        // 调用solr查询方法查询信息
        String[] fileds = "kvUuid,kvOrientation,kvTitle,kvAbstract,kvCtime,kvAuthor,kvSite,kvUrl,wechatInfoType,krIsOcr"
                .split(",");
        String sql = "";
        if(solrSql.length()>3){
            sql = solrSql.substring(3);
        }
        String orderBy = "kvCtime";
        QueryResult result = null;
//        if("0".equals(merge)){
            result = this.solrDao.search(q.toString(),sql.toString(), fileds, orderBy, ORDER.desc, pageInfo.getPageNum() - 1,
                    pageInfo.getPageSize(),searchType);
//        }else{
//            result = this.solrDao.searchAndMerge(q.toString(),sql.toString(), fileds, orderBy, ORDER.desc, pageInfo.getPageNum() - 1,
//                    pageInfo.getPageSize(),searchType);
//        }

        // 标红信息
        markRed4solr(words, result.getResultlist());
        // 判断是否已经添加过简报，关注，预警
        List<Map<String, String>> solrList = result.getResultlist();
        HashMap<String, Object> searchMap = new HashMap<>(10);
        searchMap.putAll(paramMap);
        for (Map<String, String> map : solrList) {
            String url = map.get("kvUrl");
            searchMap.put("kvUrl", url);
            // 查询是否已经填过简报
            // int countBriefing = yqjbDao.getCountByUrl_self(searchMap);
            // 查询是否已经添加过关注
            int countAttention = this.yqgzDao.getCountByUrl_self(searchMap);
            // 查询是否已经添加过预警
            int countWarning = this.warningDao.getCountByUrl_self(searchMap);
            // 添加标志位
            // map.put("isBriefing", countBriefing == 0 ? "0" : "1");
            map.put("isAttention", countAttention == 0 ? "0" : "1");
            map.put("isWarning", countWarning == 0 ? "0" : "1");
        }
        // 分页类
        PageInfo<T> page = new PageInfo<T>();
        page.setList(result.getResultlist());
        page.setPageNum(pageInfo.getPageNum());
        page.setPageSize(pageInfo.getPageSize());
        page.setTotal(result.getTotalrecord());
        return page;

    }

    /**
     * solr添加简报
     * 
     * @throws ParamsNullException
     * @throws RepeatOperateException
     * @throws SysException
     */
    @Override
    public int addBriefing(Map<String, Object> paramMap)
            throws ParamsNullException, RepeatOperateException, SysException {
        int result = 0;
        // 获取事件id
        String kvUuid = paramMap.get("kvUuid").toString();
        // 获取简报类别Id
        String kyUuid = paramMap.get("kyUuid").toString();
        // 获取简报类型 3--全网搜索
        String kvType = paramMap.get("kvType").toString();
        if (StringUtils.isEmpty(kvUuid)) {
            throw new ParamsNullException(KM_ID_IS_NULL);
        }
        List<String> kvIdList = new ArrayList<>();
        // 添加简报id
        String[] uuidArr = kvUuid.split(",");
        // 添加到集合
        if (uuidArr != null && uuidArr.length > 0) {
            for (String str : uuidArr) {
                kvIdList.add(str);
            }
        }
        // 设置kvIds
        paramMap.put("kvIdList", kvIdList.toArray());
        // 查询简报简报中已经存在的信息
        paramMap.put("kyId", kyUuid);
        List<Map<String, Object>> yqjbList = this.yqjbDao.selectList_self(paramMap);

        if (yqjbList != null && !yqjbList.isEmpty()) {
            // 存在添加过的信息
            for (Map<String, Object> map : yqjbList) {
                String kvId = String.valueOf(map.get("KV_UUID"));
                // 从集合中删除
                kvIdList.remove(kvId);
            }
        }

        // 重复添加
        if (kvIdList == null || kvIdList.isEmpty()) {
            throw new RepeatOperateException(REPEAT_OPERATE);
        }

        // 设置去重后的kvIds
        paramMap.put("kvIdList", kvIdList);

        // 将去重后的List转换成字符串已,分割
        String kvIdStr = kvIdList.toString();
        String kvUuidStr = kvIdStr.substring(1, kvIdStr.lastIndexOf("]"));

        // 拼写solr查询语句
        StringBuffer solrSql = new StringBuffer(100);
        solrSql.append("kvUuid:(");
        solrSql.append(kvUuidStr.replace(",", " || "));
        solrSql.append(")");
        // 运行solr查询信息列表
        String[] fileds = "kvSourcetype,kvTitle,kvSource,kvUrl,kvCtime,kvVisitcount,kvReply,kvDkTime,kvAbstract,kvOrientation,kvSite,kvState,kvOrienLevel,kvUuid,kvContent"
                .split(",");
        String orderBy = "kvCtime";
        QueryResult list = this.solrDao.search(solrSql.toString(),"", fileds, orderBy, ORDER.desc, 0, uuidArr.length,ACCURATE_SEARCH);
        List<Map<String, String>> solrMapList = list.getResultlist();
        Date date = new Date();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyyMMddHHmmss");
        String datetime = tempDate.format(new java.util.Date());
        for (Map<String, String> map : solrMapList) {
            // 简报信息参数入库
            Map<String, Object> insertYqjbParams = new HashMap<>();
            String kmIdInsert = UUIDUtils.getUuid();
            insertYqjbParams.putAll(paramMap);
            insertYqjbParams.put("KM_UUID", kmIdInsert);
            insertYqjbParams.put("KY_UUID", kyUuid);
            insertYqjbParams.put("KV_SOURCETYPE", map.get("kvSourcetype"));
            insertYqjbParams.put("KV_ID", "0");
            // 处理标题文本
            String kvTitle = map.get("kvTitle");
            String titleInfo = this.filterEmoji(kvTitle);
            String subTitleInfo = this.subTextInfo(titleInfo, TITLE_LENGTH);

            insertYqjbParams.put("KV_TITLE", subTitleInfo);
            insertYqjbParams.put("KV_SOURCE", map.get("kvSource"));
            insertYqjbParams.put("KV_URL", map.get("kvUrl"));
            insertYqjbParams.put("KV_TIME", map.get("kvDkTime"));
            insertYqjbParams.put("KV_CTIME", map.get("kvCtime"));
            insertYqjbParams.put("KV_VISITCOUNT", map.get("kvVisitcount"));
            insertYqjbParams.put("KV_REPLY", map.get("kvReply"));
            // insertYqjbParams.put("KV_COLLECTION", map);
            // insertYqjbParams.put("KV_TRANSPORT", map);
            // 处理摘要文本
            String kvAbstract = map.get("kvAbstract");
            String abstractInfo = this.filterEmoji(kvAbstract);
            String subAbtractInfo = this.subTextInfo(abstractInfo, ABSTRACT_LENGTH);

            insertYqjbParams.put("KV_ABSTRACT", subAbtractInfo);
            insertYqjbParams.put("KV_ORIENTATION", map.get("kvOrientation"));
            insertYqjbParams.put("KV_FLAG", map.get("0"));
            insertYqjbParams.put("KV_SITE", map.get("kvSite"));
            insertYqjbParams.put("KV_STATE", "1");
            insertYqjbParams.put("KV_ORIEN_LEVEL", map.get("kvOrienLevel"));
            insertYqjbParams.put("KV_INSERT_TIME", datetime);
            insertYqjbParams.put("KV_UUID", map.get("kvUuid"));
            insertYqjbParams.put("KV_TYPE", Integer.valueOf(kvType));
            try {
                this.yqjbDao.insertYqjbSelective_self(insertYqjbParams);
            } catch (Exception e) {
                this.logger.error("-------------------" + e.getMessage());
                throw new SysException(DB_ERROR_INSERT_YQJB);
            }
            // 简报内容入库
            Map<String, Object> insertYqjbCntParams = new HashMap<>();
            insertYqjbCntParams.putAll(paramMap);
            insertYqjbCntParams.put("KM_UUID", kmIdInsert);
            insertYqjbCntParams.put("KV_CTIME", map.get("kvCtime"));
            String kvContent = map.get("kvContent");
            String contentInfo = this.filterEmoji(kvContent);
            insertYqjbCntParams.put("KV_CONTENT", contentInfo);

            try {
                result = this.yqjbDao.insertYqjbCntSelective_self(insertYqjbCntParams);
            } catch (Exception e) {
                // 删除
                Map<String, Object> deleteYqjbParams = new HashMap<>();
                deleteYqjbParams.putAll(paramMap);
                deleteYqjbParams.put("kmId", kmIdInsert);
                this.yqjbDao.deletejb_self(deleteYqjbParams);
                this.logger.error("-------------------" + e.getMessage());
                throw new SysException(DB_ERROR_INSERT_YQJB_CNT);
            }
        }
        return result;
    }

    /**
     * solr添加关注
     * 
     * @throws ParamsNullException
     * @throws RepeatOperateException
     * @throws SysException
     */
    @Override
    public int addAttention(Map<String, Object> paramMap)
            throws ParamsNullException, RepeatOperateException, SysException {
        int result = 0;
        // 获取事件id
        String kvUuid = paramMap.get("kvUuid").toString();
        String kuid = paramMap.get("_KUID").toString();
        String kvType = paramMap.get("kvType").toString();
        // 获取分类Id
        String kmId = paramMap.get("kmId").toString();
        if (StringUtils.isEmpty(kvUuid)) {
            throw new ParamsNullException(KM_ID_IS_NULL);
        }
        List<String> kvIdList = new ArrayList<>();
        // 关注id
        String[] uuidArr = kvUuid.split(",");
        // 添加到集合
        if (uuidArr != null && uuidArr.length > 0) {
            for (String str : uuidArr) {
                kvIdList.add(str);
            }
        }
        // 设置kvIds
        paramMap.put("kvIdList", kvIdList.toArray());
        // 查询关注中已经存在的信息
        List<Map<String, Object>> yqjbList = this.yqgzDao.selectList_self(paramMap);

        if (yqjbList != null && !yqjbList.isEmpty()) {
            // 存在添加过的信息
            for (Map<String, Object> map : yqjbList) {
                String kvId = String.valueOf(map.get("KV_UUID"));
                // 从集合中删除
                kvIdList.remove(kvId);
            }
        }

        // 重复添加
        if (kvIdList == null || kvIdList.isEmpty()) {
            throw new RepeatOperateException(REPEAT_OPERATE);
        }

        // 设置去重后的kvIds
        paramMap.put("kvIdList", kvIdList);

        // 将去重后的List转换成字符串已,分割
        String kvIdStr = kvIdList.toString();
        String kvUuidStr = kvIdStr.substring(1, kvIdStr.lastIndexOf("]"));

        // 拼写solr查询语句
        StringBuffer solrSql = new StringBuffer(100);
        solrSql.append("kvUuid:(");
        solrSql.append(kvUuidStr.replace(",", " || "));
        solrSql.append(")");
        // 运行solr查询信息列表
        String[] fileds = "kvSourcetype,kvTitle,kvSource,kvUrl,kvCtime,kvVisitcount,kvReply,kvDkTime,kvAbstract,kvOrientation,kvSite,kvState,kvOrienLevel,kvUuid,kvContent"
                .split(",");
        String orderBy = "kvCtime";
        QueryResult list = this.solrDao.search(solrSql.toString(),"", fileds, orderBy, ORDER.desc, 0, uuidArr.length,ACCURATE_SEARCH);
        List<Map<String, String>> solrMapList = list.getResultlist();
        String longDate = DateUtil.getLongDate();
        for (Map<String, String> map : solrMapList) {
            // 关注信息参数入库
            Map<String, Object> insertYqgzParams = new HashMap<>();
            String kmIdInsert = UUIDUtils.getUuid();
            insertYqgzParams.putAll(paramMap);
            insertYqgzParams.put("KM_UUID", kmIdInsert);
            insertYqgzParams.put("KU_ID", kuid);
            insertYqgzParams.put("KV_SOURCETYPE", map.get("kvSourcetype"));
            insertYqgzParams.put("KV_ID", "0");
            // 处理标题文本
            String kvTitle = map.get("kvTitle");
            String titleInfo = this.filterEmoji(kvTitle);
            String subTitleInfo = this.subTextInfo(titleInfo, TITLE_LENGTH);

            insertYqgzParams.put("KV_TITLE", subTitleInfo);
            insertYqgzParams.put("KV_SOURCE", map.get("kvSource"));
            insertYqgzParams.put("KV_URL", map.get("kvUrl"));
            insertYqgzParams.put("KV_TIME", map.get("kvDkTime"));
            insertYqgzParams.put("KV_CTIME", map.get("kvCtime"));
            insertYqgzParams.put("KV_VISITCOUNT", map.get("kvVisitcount"));
            insertYqgzParams.put("KV_REPLY", map.get("kvReply"));
            // insertYqjbParams.put("KV_COLLECTION", map);
            // insertYqjbParams.put("KV_TRANSPORT", map);
            // 处理摘要文本
            String kvAbstract = map.get("kvAbstract");
            String abstractInfo = this.filterEmoji(kvAbstract);
            String subAbtractInfo = this.subTextInfo(abstractInfo, ABSTRACT_LENGTH);

            insertYqgzParams.put("KV_ABSTRACT", subAbtractInfo);
            insertYqgzParams.put("KV_ORIENTATION", map.get("kvOrientation"));
            insertYqgzParams.put("KV_FLAG", map.get("0"));
            insertYqgzParams.put("KV_SITE", map.get("kvSite"));
            insertYqgzParams.put("KV_STATE", "1");
            insertYqgzParams.put("KV_ORIEN_LEVEL", map.get("kvOrienLevel"));
            insertYqgzParams.put("KV_INSERT_TIME", longDate);
            insertYqgzParams.put("KV_UUID", map.get("kvUuid"));
            insertYqgzParams.put("KM_ID", kmId);
            insertYqgzParams.put("KV_TYPE", Integer.valueOf(kvType));
            try {
                this.yqgzDao.insertYqgzSelective_self(insertYqgzParams);
            } catch (Exception e) {
                this.logger.error("-------------------" + e.getMessage());
                throw new SysException(DB_ERROR_INSERT_YQGZ);
            }
            // 关注内容入库
            Map<String, Object> insertYqgzCntParams = new HashMap<>();
            insertYqgzCntParams.putAll(paramMap);
            insertYqgzCntParams.put("KM_UUID", kmIdInsert);
            insertYqgzCntParams.put("KV_CTIME", map.get("kvCtime"));
            String kvContent = map.get("kvContent");
            String contentInfo = this.filterEmoji(kvContent);
            insertYqgzCntParams.put("KV_CONTENT", contentInfo);

            try {
                result = this.yqgzDao.insertYqgzCntSelective_self(insertYqgzCntParams);
            } catch (Exception e) {
                // 删除
                Map<String, Object> deleteYqgzParams = new HashMap<>();
                deleteYqgzParams.putAll(paramMap);
                deleteYqgzParams.put("kmId", kmIdInsert);
                this.yqgzDao.deletegz_self(deleteYqgzParams);
                this.logger.error("-------------------" + e.getMessage());
                throw new SysException(DB_ERROR_INSERT_YQGZ_CNT);
            }
        }
        return result;
    }

    /**
     * solr添加预警
     * 
     * @throws WarningException
     * @throws SendEmailErrorException
     */
    @Override
    public int addWarning(Map<String, Object> paramMap)
            throws ParamsNullException, RepeatOperateException, SysException, WarningException {
        int result = 0;
        String kuid = paramMap.get("_KUID").toString();
        // 获取事件id
        String kvUuid = paramMap.get("kvUuid").toString();
        // 获取类型
        String ksType = paramMap.get("ksType").toString();
        if (StringUtils.isEmpty(kvUuid)) {
            throw new ParamsNullException(KM_ID_IS_NULL);
        }
        List<String> kvIdList = new ArrayList<>();
        // 添预警id
        String[] uuidArr = kvUuid.split(",");
        // 添加到集合
        if (uuidArr != null && uuidArr.length > 0) {
            for (String str : uuidArr) {
                kvIdList.add(str);
            }
        }
        // 设置kvIds
        paramMap.put("kvIdList", kvIdList.toArray());
        // 设置kuid
        paramMap.put("kuId", kuid);
        // 查询预警中已经存在的信息
        List<Map<String, Object>> warningList = this.warningDao.selectList_self(paramMap);

        if (warningList != null && !warningList.isEmpty()) {
            // 存在添加过的信息
            for (Map<String, Object> map : warningList) {
                String kvId = String.valueOf(map.get("KV_UUID"));
                // 从集合中删除
                kvIdList.remove(kvId);
            }
        }

        // 重复添加
        if (kvIdList == null || kvIdList.isEmpty()) {
            throw new RepeatOperateException(REPEAT_OPERATE);
        }

        // 将去重后的List转换成字符串已,分割
        String kvIdStr = kvIdList.toString();
        String kvUuidStr = kvIdStr.substring(1, kvIdStr.lastIndexOf("]"));

        // 拼写solr查询语句
        StringBuffer solrSql = new StringBuffer(100);
        solrSql.append("kvUuid:(");
        solrSql.append(kvUuidStr.replace(",", " || "));
        solrSql.append(")");

        String[] fileds = "kvUuid,kvSite,kvSource,kvCtime,kvUrl,kvOrientation,kvOrienLevel,kvSourcetype,kvVisitcount,kvAuthor,kvContent,kvTitle,kvAbstract,kvDkTime,kvReply,kvState"
                .split(",");
        String orderBy = "kvCtime";
        QueryResult list = this.solrDao.search(solrSql.toString(),"", fileds, orderBy, ORDER.desc, 0, uuidArr.length,ACCURATE_SEARCH);
        List<Map<String, String>> solrMapList = list.getResultlist();

        String longDate = DateUtil.getLongDate();

        // 将solr数据入库
        for (Map<String, String> map : solrMapList) {
            // 关注信息参数入库
            Map<String, Object> insertYqyjParams = new HashMap<>();
            String kmIdInsert = UUIDUtils.getUuid();
            insertYqyjParams.putAll(paramMap);
            insertYqyjParams.put("KS_UUID", kmIdInsert);
            insertYqyjParams.put("KV_UUID", map.get("kvUuid"));
            insertYqyjParams.put("KU_ID", kuid);
            insertYqyjParams.put("KS_TYPE", ksType);
            // 编写预警信息
            String title = map.get("kvTitle");
            String titleInfo = this.filterEmoji(title);
            String subTitleInfo = this.subTextInfo(titleInfo, KSINFO_LENGTH);
            String ksInfo = "《舆情预警》:" + longDate + "," + "{" + map.get("kvSource") + "}" + "《" + subTitleInfo + "》";

            insertYqyjParams.put("KS_INFO", ksInfo);
            insertYqyjParams.put("KS_TIME", longDate);
            insertYqyjParams.put("KS_STATE", "1");
            insertYqyjParams.put("KS_FLAG", "0");
            insertYqyjParams.put("KS_CHECK", "1");
            insertYqyjParams.put("KS_CHECKDEL", "0");
            insertYqyjParams.put("KS_LATE", "0");
            insertYqyjParams.put("KS_AOTO", "3");
            insertYqyjParams.put("KV_ARG3", map.get("kvAuthor")==null?"":map.get("kvAuthor"));
            // insertYqyjParams.put("KS_SIMHASH", "0");
            // insertYqyjParams.put("KS_CALINFO", "0");
            // 处理标题信息
            String warTitleInfo = this.subTextInfo(titleInfo, KSINFO_LENGTH);
            insertYqyjParams.put("KS_TITLE", warTitleInfo);

            insertYqyjParams.put("KS_CTIME", map.get("kvCtime"));
            insertYqyjParams.put("KS_SOURCETYPE", map.get("kvSourcetype"));
            insertYqyjParams.put("KS_INFOTYPE", "0");
            insertYqyjParams.put("KS_KID", "1");
            // 处理摘要信息
            String kvAbstract = map.get("kvAbstract");
            String abstractInfo = this.filterEmoji(kvAbstract);
            String subAbstractInfo = this.subTextInfo(abstractInfo, ABSTRACT_LENGTH);

            insertYqyjParams.put("KV_ABSTRACT", subAbstractInfo);
            insertYqyjParams.put("KV_ORIENTATION", map.get("kvOrientation"));
            insertYqyjParams.put("KV_WEBNAME", map.get("kvSite"));
            insertYqyjParams.put("KS_URL", map.get("kvUrl"));
            // 处理内容信息
            String content = map.get("kvContent");
            String infoContent = this.filterEmoji(content);
            insertYqyjParams.put("KV_CONTENT", infoContent);

            Timestamp timeYj = new Timestamp(System.currentTimeMillis());
            insertYqyjParams.put("KV_DTCTIME", timeYj);
            insertYqyjParams.put("KV_DTTIME", timeYj);
            try {
                result = this.warningDao.insertYqyjSelective_self(insertYqyjParams);
            } catch (Exception e) {
                this.logger.error("-------------------" + e.getMessage());
                throw new SysException(DB_ERROR_INSERT_YQYJ);
            }
            User user = this.userDao.getUserByKuId(kuid);
            // 推送预警
            try {
                this.sendIphonePushNew(user, ksInfo, map);
            } catch (Exception e) {
                log.error("-------------------" + e.getMessage());
                throw new WarningException(SEND_WARNING_ERROR);
            }
        }
        return result;
    }

    @Override
    public int addSendEmail(Map<String, Object> params)
            throws SysException, SendEmailErrorException {
        // 上报原因
        String reason = ParamsUtil.nullDeal(params, "reason", "");
        // 邮箱地址，多个逗号分隔开
        String email = ParamsUtil.nullDeal(params, "email", "");
        // 信息id多个逗号分隔开
        String kvIds = ParamsUtil.nullDeal(params, "kvIds", "");
        // 邮件内容
        StringBuilder sb = new StringBuilder();
        String[] kvIdArr = kvIds.split(",");
        // 从solr查询数据
        StringBuffer solrSql = new StringBuffer(100);
        solrSql.append("kvUuid:(");
        solrSql.append(kvIds.replace(",", " || "));
        solrSql.append(")");

        String[] fileds = "kvUuid,kvSite,kvSource,kvCtime,kvUrl,kvOrientation,kvOrienLevel,kvSourcetype,kvVisitcount,kvAuthor,kvContent,kvTitle,kvAbstract,kvDkTime,kvReply,kvState"
                .split(",");
        String orderBy = "kvCtime";
        QueryResult list = this.solrDao.search(solrSql.toString(),"", fileds, orderBy, ORDER.desc, 0, kvIdArr.length,ACCURATE_SEARCH);
        List<Map<String, Object>> solrMapList = list.getResultlist();
        for (Map<String, Object> infoMap : solrMapList) {
            // 清空内容
            sb.setLength(0);
            // 标题
            String title = ParamsUtil.nullDeal(infoMap, "kvTitle", "");
            // 内容
            String content = ParamsUtil.nullDeal(infoMap, "kvContent", "");
            // url
            String url = ParamsUtil.nullDeal(infoMap, "kvUrl", "");
            // 站点
            String site = ParamsUtil.nullDeal(infoMap, "kvSite", "");
            // 时间
            String time = ParamsUtil.nullDeal(infoMap, "kvCtime", "");
            // 作者
            String author = ParamsUtil.nullDeal(infoMap, "kvSource", "");
            // 倾向性，1.正面，2.负面，3.中性
            String orientation = ParamsUtil.nullDeal(infoMap, "kvOrientation", "");
            // 转载数
            String transport = ParamsUtil.nullDeal(infoMap, "kvTransport", "0");

            if (!"".equals(title)) {
                title = HtmlFilter.filterWord(title);
                title = title.replace("\r\n", " ").replace("\r", " ").replaceAll("\\[beginimg\\](.*?)\\[endimg\\]", "")
                        .replace("<br />", " ").replace("<br/>", " ").replaceAll("beginimg(.*?)endimg", "");
            }
            if (!"".equals(content)) {
                content = HtmlFilter.filterWord(content);
                content = content.replace("\r\n", " ").replace("\r", " ")
                        .replaceAll("\\[beginimg\\](.*?)\\[endimg\\]", "").replace("<br />", " ").replace("<br/>", " ")
                        .replaceAll("beginimg(.*?)endimg", "");
            }

            sb.append("尊敬的领导：您好，监测到以下信息：\n");
            sb.append("    标题：").append(title).append("\n");
            sb.append("    作者：").append(author).append("\n");
            sb.append("    时间：").append(time).append("\n");
            sb.append("    网站：").append(site).append("\n");
            sb.append("    地址：").append(url).append("\n");
            sb.append("    属性：").append("3".equals(orientation) ? "中性" : "1".equals(orientation) ? "正面" : "负面")
                    .append("\n");
            sb.append("    转发数：").append(transport).append("\n");
            sb.append("    内容：").append(content).append("\n");
            sb.append("    上报原因：").append(reason);

            // 多个邮箱分隔成数组
            String[] emailArray = email.split(",");
            for (String str : emailArray) {
                try {
                    Map<String, String> map = new HashMap<>();
                    map.put("title", Base64.encodeBase64URLSafeString("舆情监测数据".getBytes("UTF-8")));
                    map.put("content", Base64.encodeBase64URLSafeString(sb.toString().getBytes("UTF-8")));
                    map.put("email", str);
                    // 内容和标题以base64编码格式，默认不编码
                    map.put("encode", "base64");
                    // 以纯文本邮件发送，默认为HTML格式
                    map.put("type", "text");

                    HttpUtil.post(Constant.PUSH_SERVER_URL, JSONObject.toJSONString(map));

                } catch (Exception e) {
                    log.error("-------------------" + e.getMessage());
                    throw new SendEmailErrorException(SEND_EMAIL_ERROR);
                }
            }
        }

        return 1;
    }

    /**
     * 保存筛选条件
     * 
     * @throws SysException
     */
    @Override
    public int saveSearchCondition(Map<String, Object> paramMap) throws SysException {
        // 调用dao将筛选条件保存到数据库
        int result = 0;
        try {
            result = this.solrConditionDao.insertCondition(paramMap);
        } catch (Exception e) {
            this.logger.error("-------------------" + e.getMessage());
            throw new SysException(DB_ERROR_INSERT_CONDITION);
        }
        return result;
    }

    /**
     * 获取筛选条件
     */
    @Override
    public List<Map<String, Object>> getSearchCondition(Map<String, Object> paramMap) {
        return this.solrConditionDao.selectList(paramMap);
    }

    /**
     * solr获取详情页
     * @throws SysException 
     */
    @Override
    public List<Map<String, Object>> getDetails(Map<String, Object> paramMap) throws SysException {
        // 获取信息Id
        String kvUuid = paramMap.get("kvUuid").toString();
        // 获取查询关键词
        String message = String.valueOf(paramMap.get("message"));
        // 查询solr
        StringBuffer solrSql = new StringBuffer(100);
        solrSql.append("kvUuid:");
        solrSql.append(kvUuid);

        String[] fileds = "kvUuid,kvSite,kvSource,kvCtime,kvUrl,kvOrientation,kvOrienLevel,kvSourcetype,kvVisitcount,kvAuthor,kvContent,kvTitle,kvAbstract,kvDkTime,kvReply,kvState,wechatInfoType"
                .split(",");
        String orderBy = "kvCtime";
        QueryResult list = this.solrDao.search(solrSql.toString(),"", fileds, orderBy, ORDER.desc, 0, 1,ACCURATE_SEARCH);
        // 标红信息
        JSONArray words = new JSONArray();
        try{
            JSONObject json = new JSONObject();
            json.put("content", message);
            String resultWords =HttpUtil.post(Constant.WORD_ANALYSIS_URL, json.toJSONString());
            resultWords = StringUtil.decodeUnicode(resultWords);
            JSONObject jsonWords = JSON.parseObject(resultWords);
            words = jsonWords.getJSONArray("result");  
        }catch(Exception e){
            log.error(e.getMessage(),e);
            throw new SysException();
        }
        
        markRed4solr(words, list.getResultlist());
        List<Map<String, Object>> detailList = list.getResultlist();
        // 判断是否已经添加过简报，关注，预警
        HashMap<String, Object> searchMap = new HashMap<>(10);
        searchMap.putAll(paramMap);
        for (Map<String, Object> map : detailList) {
            String url = map.get("kvUrl").toString();
            searchMap.put("kvUrl", url);
            // 查询是否已经填过简报
            // int countBriefing = yqjbDao.getCountByUrl_self(searchMap);
            // 查询是否已经添加过关注
            int countAttention = this.yqgzDao.getCountByUrl_self(searchMap);
            // 查询是否已经添加过预警
            int countWarning = this.warningDao.getCountByUrl_self(searchMap);
            // 添加标志位
            // map.put("isBriefing", countBriefing == 0 ? "0" : "1");
            map.put("isAttention", countAttention == 0 ? "0" : "1");
            map.put("isWarning", countWarning == 0 ? "0" : "1");
        }
        return detailList;
    }

    /**
     * 获取预警推送摘要
     * 
     * @throws SolrException
     */
    @Override
    public String getAbstract(Map<String, Object> paramMap) throws SolrException {
        // 获取信息Id
        String kvUuid = paramMap.get("kvUuid").toString();
        // 查询solr
        StringBuffer solrSql = new StringBuffer(100);
        solrSql.append("kvUuid:");
        solrSql.append(kvUuid);

        String[] fileds = "kvUuid,kvSite,kvSource,kvCtime,kvSourcetype,kvTitle".split(",");
        String orderBy = "kvCtime";
        QueryResult list;
        try {
            list = this.solrDao.search(solrSql.toString(),"", fileds, orderBy, ORDER.desc, 0, 1,ACCURATE_SEARCH);
        } catch (SysException e) {
            throw new SolrException();
        }
        List<Map<String, Object>> detailList = list.getResultlist();
        Map<String, Object> map = detailList.get(0);
        String smsInfo = this.getSMSInfo(map);

        return smsInfo;
    }

    /**
     * 拼接solr搜索关键词部分字符串
     *
     * @param query
     * @param words
     * @param matchRange
     * @return
     */
    public StringBuffer pieceTogetherMessage(StringBuffer query,
            String words, String matchRange) {
        if (!words.contains(" ")) {
            if ("content".equals(matchRange)) {
                query.append(" && (kvTitle:");
                query.append("\"" + translateString(words) + "\"");
                query.append(" || kvContent:");
                query.append("\"" + translateString(words) + "\"");
                query.append(")");
            }
            if ("title".equals(matchRange)) {
                query.append(" && kvTitle:");
                query.append("\"" + translateString(words) + "\"");
            }
        } else {
            String[] messages = words.split(" ");
            int length = messages.length;
            if ("content".equals(matchRange)) {
                query.append(" && (");
                query = this.pieceTogether2(query, messages);
                query.append(")");
            }
            if ("title".equals(matchRange)) {
                query.append(" && kvTitle:");
                query.append("\"" + translateString(messages[0]) + "\"");
                query = this.pieceTogether(query, messages, "kvTitle");
            }
        }
        return query;
    }

    /**
     * 拼接字符串
     *
     * @param str
     * @param messages
     * @param fieldName
     * @return
     */
    private StringBuffer pieceTogether(StringBuffer str, String[] messages,
            String fieldName) {
        for (int i = 1; i < messages.length; i++) {
            str.append(" && " + fieldName + ":");
            str.append("\"" + messages[i] + "\"");
        }
        return str;
    }

    /**
     * 拼接字符串
     *
     * @param str
     * @param messages
     * @return
     */
    private StringBuffer pieceTogether2(StringBuffer str, String[] messages) {
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
     * 处理关键词信息
     *
     * @param field
     * @return
     */
    public static String translateString(String field) {
        return field.replace("'", "\\'").replace("%", "\\%");
    }

    /**
     * 获取需要标红的信息
     *
     * @param words
     * @param list
     */
    public static void markRed4solr(JSONArray words, List list) {
        // 将标红字段串内的所有空格，制表符，换页符等空白字符换成空格

        for (int i = 0; i < list.size(); i++) {
            Map<String, String> map = ((Map<String, String>) list.get(i));

            String title = "";
            if (map.get("kvTitle") != null && !map.get("kvTitle").equals("")) {
                title = map.get("kvTitle").toString();
            }
            title = markRed4solr(title, words);
            map.put("kvTitle", title);

            String abstractStr = "";
            if (map.get("kvAbstract") != null && !map.get("kvAbstract").equals("")) {
                abstractStr = map.get("kvAbstract").toString();
            }
            abstractStr = markRed4solr(abstractStr, words);
            map.put("kvAbstract", abstractStr);

            String kvContent = "";
            if (map.get("kvContent") != null && !map.get("kvContent").equals("")) {
                kvContent = map.get("kvContent").toString();
            }
            kvContent = markRed4solr(kvContent, words);
            map.put("kvContent", kvContent);

        }
    }

    /**
     * 标红
     *
     * @param str
     * @param markStrs
     * @return
     */
    public static String markRed4solr(String str, JSONArray markStrs) {
        // 添加标红html标签
        for (Object m : markStrs) {
            // 判断关键词是否包含空字符串
            if (StringUtils.isBlank(m.toString()))
                continue;
            // 将信息标红
            String newS = "<font color='#fd433d'>" + m + "</font>";
            str = str.replace(m.toString(), newS);
        }
        return str;
    }

    /**
     * 推送预警信息
     *
     * @param user
     * @param ksInfo
     * @param infoMap
     * @throws SendEmailErrorException
     */
    public void sendIphonePushNew(User user, final String ksInfo, final Map<String, String> infoMap)
            throws SendEmailErrorException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(new Runnable() {

            @Override
            public void run() {
                String tryTime = user.getExpirationTime().toString();
                if (StringUtils.isNotBlank(tryTime)) {
                    String title1 = infoMap.get("kvTitle").replace("%", "").replace("=", "").replace("&", "");
                    JSONObject json = new JSONObject();
                    // 发送消息
                    json.put("method", "sendMessage");
                    // session 可以动态获取，目前传haoran即可，为不过期session
                    json.put("session", "haoran");
                    JSONObject params = new JSONObject();
                    // 用户ID
                    params.put("userid", user.getUserId());
                    // 用户名
                    params.put("username", user.getUserName());
                    // 产品ID 1：舆情秘书
                    params.put("productid", "1");
                    // 是否组播 1：组播，0：单播
                    params.put("multicast", "0");
                    // 1：自动推送，0 手动推送
                    params.put("autopush", "0");
                    // 1 通知 ，0：透传消息
                    params.put("messagetype", "0");
                    // 通知标题
                    params.put("title", title1);
                    // 角标 未读消息数
                    params.put("badge", "0");
                    // 通知内容
                    params.put("content", title1);
                    params.put("webname", infoMap.get("kvSite"));
                    params.put("keyword", "");
                    // 通知 自定义参数
                    JSONObject param = new JSONObject();
                    // 用户ID
                    param.put("userid", user.getUserId());
                    // 用户名
                    param.put("username", user.getUserName());
                    // 消息的uuid
                    param.put("uuid", infoMap.get("kvUuid"));
                    // 消息的uuid
                    param.put("kv_url", infoMap.get("kvUrl"));
                    param.put("type", "2");
                    params.put("params", param);
                    // 自定义消息 用于透传消息
                    JSONObject custommessage = new JSONObject();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    // 用户ID
                    custommessage.put("userid", user.getUserId());
                    // 用户名
                    custommessage.put("username", user.getUserName());
                    custommessage.put("time", format.format(new Date()));
                    custommessage.put("title", title1);
                    custommessage.put("type", "2");
                    custommessage.put("uuid", infoMap.get("kvUuid"));
                    // 消息的uuid
                    custommessage.put("kv_url", infoMap.get("kvUrl"));
                    params.put("custommessage", custommessage);
                    json.put("params", params);
                    try {
                        HttpUtil.post(
                                PropertiesUtil.getProperties("application.properties", "push.server.url") + "/send",
                                json.toString());
                    } catch (SysException e) {
                        log.error("-------------------" + e.getMessage());
                    }
                }
            }
        });
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     * 
     * @param source
     * @return
     */
    public String filterEmoji(String source) {
        if (StringUtils.isBlank(source)) {
            return source;
        }
        StringBuilder buf = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (this.isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                buf.append(codePoint);
            }
        }
        if (buf == null) {
            return source;
        } else {
            if (buf.length() == len) {
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }

    /**
     * 过滤emoji
     *
     * @param codePoint
     * @return
     */
    private boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * @Title: getSMSInfo
     *         拼写推送内容
     * @param hashMap
     *            单条info数据信息
     * @return String 返回类型
     */
    private String getSMSInfo(Map<String, Object> hashMap) {
        String kvTitle = hashMap.get("kvTitle") + "";
        String kvSource = hashMap.get("kvSource") + "";
        String kvSite = hashMap.get("kvSite") + "";
        String kvCtime = hashMap.get("kvCtime") + "";
        String kvSourcetype = hashMap.get("kvSourcetype") + "";

        StringBuffer smsinfo = new StringBuffer();
        // 拼写时间
        if (StringUtils.isNotBlank(kvCtime)) {
            kvCtime = kvCtime + "000000";
            smsinfo.append(Integer.parseInt(kvCtime.substring(4, 6)));
            smsinfo.append("月");
            smsinfo.append(kvCtime.substring(6, 8));
            smsinfo.append("日");
            smsinfo.append(kvCtime.substring(8, 10));
            smsinfo.append("时");
            smsinfo.append(kvCtime.substring(10, 12));
            smsinfo.append("分,");

        }
        if (StringUtils.isNotBlank(kvSite)) {
            if (kvSite.contains("-")) {
                kvSite = kvSite.substring(0, kvSite.indexOf("-"));
            }
            smsinfo.append("[");
            smsinfo.append(kvSite);
            smsinfo.append("]");
        } else {
            if (StringUtils.isNotBlank(kvSource)) {
                smsinfo.append("[");
                smsinfo.append(kvSource);
                smsinfo.append("]");
            }
        }
        smsinfo.append("出现预警信息，");
        // 拼写标题
        if (kvTitle.length() >= 100) {
            kvTitle = kvTitle.substring(0, 100) + "...";
        }
        // 微博只显示内容
        if ("04".equals(kvSourcetype)) {
            smsinfo.append("主要内容为:");
            smsinfo.append(kvTitle);
        } else {
            smsinfo.append("标题为:《");
            smsinfo.append(kvTitle);
            smsinfo.append("》");
        }
        String info = smsinfo.toString().replaceAll("\n", " ");
        // 以前65个字，现在修改成50个
        // if (info.length() > 200) {
        // if (kvSourcetype.equals("04")) {// 微博只显示内容
        // info = info.substring(0, 200);
        // } else {
        // info = info.substring(0, 200);
        // }
        // }

        return info;
    }

    /**
     * 处理文本过长
     *
     * @param title
     * @param length
     * @return
     */
    private String subTextInfo(String title, int length) {
        if (title.length() > length) {
            return title.substring(0, length);
        } else {
            return title;
        }
    }
}
