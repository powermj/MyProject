package com.zhxg.yqzj.service.impl.v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zhxg.framework.base.constants.Constant;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.framework.base.utils.HtmlFilter;
import com.zhxg.framework.base.utils.HttpUtil;
import com.zhxg.framework.base.utils.ParamsUtil;
import com.zhxg.framework.base.utils.UUIDUtils;
import com.zhxg.yqzj.dao.v1.MyFocusDao;
import com.zhxg.yqzj.dao.v1.TopicDao;
import com.zhxg.yqzj.dao.v1.UserDao;
import com.zhxg.yqzj.dao.v1.YqjbDao;
import com.zhxg.yqzj.service.exception.myfocus.MyFocusInfoNotFoundException;
import com.zhxg.yqzj.service.exception.myfocus.RepeatOperateException;
import com.zhxg.yqzj.service.exception.myfocus.SendEmailErrorException;
import com.zhxg.yqzj.service.v1.MyFocusService;

/**
 * <p>Description: 我的关注接口实现类</p>
 * @author zyl
 * @date 2017年11月8日
 * @version 1.0
 */
@Service
public class MyFocusServiceImpl extends BaseServiceImpl<BaseEntity> implements MyFocusService {
    
    @Autowired
    private YqjbDao yqjbDao;
    
    @Autowired
    private MyFocusDao myfocusDao;
    
    @Autowired
    private TopicDao topicDao;
    
    @Autowired
    private UserDao userDao;
    
    private static final String FOCUS_INFO_NOT_EXIST = "关注信息不存在";
    private static final String DB_ERROR_INSERT_YQJB = "添加简报数据库操作失败";
    private static final String DB_ERROR_INSERT_YQJB_CNT = "添加简报内容数据库操作失败";
    private static final String REPEAT_OPERATE = "信息已经添加过";
    private static final String SEND_EMAIL_ERROR = "邮件发送失败";
    private static final int ABSTRACT_LENGTH = 400;
    

    @Override
    protected BaseDao<BaseEntity> getBaseDao() {
        return userDao;
    }
    private final static Logger log = LoggerFactory.getLogger(MyFocusServiceImpl.class);
    
    @Override
    public int addYqjb(Map<String, Object> params) throws SysException, MyFocusInfoNotFoundException, RepeatOperateException {
        String kmIds = ParamsUtil.nullDeal(params, "kmIds", "");//关注信息id
        String kyId = ParamsUtil.nullDeal(params, "kyId", "");//简报id
        Map<String,Map<String,Object>> infoMap = new HashMap<>();//信息详情
        params.put("KY_UUID", kyId);//转换参数名称
        
        List<String> kmIdList = new ArrayList<>();//kmIds集合
        List<String> kvIdList = new ArrayList<>();//kvIds集合
        
        //kmIds分隔成数组
        String[] kmIdsArray = "".equals(kmIds) ? null : kmIds.split(",");
        //添加到集合
        if(kmIdsArray != null && kmIdsArray.length > 0){
            for(String str : kmIdsArray){
                kmIdList.add(str);
            }
        }
        params.put("kmIdList", kmIdList);
        
        //查询关注信息列表，不包括内容
        List<Map<String,Object>> focusBasicList = myfocusDao.selectBasicList_self(params);
        if(focusBasicList == null || focusBasicList.isEmpty()){
            throw new MyFocusInfoNotFoundException(FOCUS_INFO_NOT_EXIST);
        }
        for(Map<String,Object> map :focusBasicList) {
            String kvId = ParamsUtil.nullDeal(map, "KV_UUID", "");
            kvIdList.add(kvId);
            
            //保存信息到  infoMap中
            infoMap.put(kvId, map);
        }

        //设置kvIds
        params.put("kvIdList", kvIdList);
        //查询简报简报中已经存在的信息
        List<Map<String, Object>> yqjbList = yqjbDao.selectList_self(params);
        
        if(yqjbList != null && !yqjbList.isEmpty()){
            //存在添加过的信息
            for(Map<String,Object> map : yqjbList){
                String kvId = String.valueOf(map.get("KV_UUID"));
                //从集合中删除
                kvIdList.remove(kvId);
            }
        }
        
        //重复添加
        if(kvIdList == null || kvIdList.isEmpty()){
            throw new RepeatOperateException(REPEAT_OPERATE);
        }

        //设置去重后的kvIds
        params.put("kvIdList", kvIdList);
        
        int rowCount = 0;
        for(String str : kvIdList){
            Map<String,Object> data = infoMap.get(str);
            String kmId = ParamsUtil.nullDeal(data, "KM_UUID", "");
            String kvStatus = ParamsUtil.nullDeal(data, "KV_STATE", "");
            String kvAbstract = ParamsUtil.nullDeal(data, "KV_ABSTRACT", "");
            String kvcTime = ParamsUtil.nullDeal(data, "KV_CTIME", "");
            String kmIdInsert = UUIDUtils.getUuid();
            
            //添加简报
            Map<String,Object> insertYqjbParams = new HashMap<>();
            insertYqjbParams.putAll(params);
            insertYqjbParams.putAll(data);
            insertYqjbParams.put("KM_UUID",kmIdInsert);//设置新的kmId
            insertYqjbParams.put("KV_STATE", "".equals(kvStatus) ? "1" : kvStatus);
            insertYqjbParams.put("KV_ORIEN_LEVEL", "1");
            insertYqjbParams.put("KV_ABSTRACT",kvAbstract.length() > ABSTRACT_LENGTH ? kvAbstract.substring(0, ABSTRACT_LENGTH) : kvAbstract);
            insertYqjbParams.put("KV_FLAG", "0");
            insertYqjbParams.put("KV_INSERT_TIME",DateUtil.getLongDate());
            insertYqjbParams.put("KV_ID", "0");
            try {
                yqjbDao.insertYqjbSelective_self(insertYqjbParams);
            } catch (Exception e) {
                log.error("-------------------" + e.getMessage());
                throw new SysException(DB_ERROR_INSERT_YQJB);
            }
            
            //添加简报内容
            Map<String,Object> insertYqjbCntParams = new HashMap<>();
            insertYqjbCntParams.putAll(params);
            insertYqjbCntParams.put("KM_UUID",kmIdInsert);
            insertYqjbCntParams.put("KV_CTIME",kvcTime);
            //查询信息内容
            Map<String,Object> selectContentParams = new HashMap<>();
            selectContentParams.putAll(params);
            selectContentParams.put("kmId", kmId);
            insertYqjbCntParams.put("KV_CONTENT",myfocusDao.selectContent_self(selectContentParams));
            
            try {
                rowCount = yqjbDao.insertYqjbCntSelective_self(insertYqjbCntParams);
            } catch (Exception e) {
                //删除
                Map<String,Object> deleteYqjbParams = new HashMap<>();
                deleteYqjbParams.putAll(params);
                deleteYqjbParams.put("kmId", kmIdInsert);
                yqjbDao.deleteYqjb_self(deleteYqjbParams);
                
                log.error("-------------------" + e.getMessage());
                throw new SysException(DB_ERROR_INSERT_YQJB_CNT);
            }
        }
        return rowCount;
    }

    @Override
    public int addSendEmail(Map<String, Object> params) throws SysException, MyFocusInfoNotFoundException, SendEmailErrorException {
        String reason = ParamsUtil.nullDeal(params, "reason", "");//上报原因
        String email = ParamsUtil.nullDeal(params, "email", ""); //邮箱地址，多个逗号分隔开
        String kmIds = ParamsUtil.nullDeal(params, "kmIds", ""); //信息id多个逗号分隔开
        StringBuilder sb = new StringBuilder();//邮件内容
        
        List<String> kmIdList = new ArrayList<>();//kvuuids集合
        //kv_uuid分隔成数组
        String[] kmIdsArray = "".equals(kmIds) ? null : kmIds.split(",");
        //添加到集合
        if(kmIdsArray != null && kmIdsArray.length > 0){
            for(String str : kmIdsArray){
                kmIdList.add(str);
            }
        }
        //查询参数
        params.put("kmIdList", kmIdList);
        
        //从关注表中查询信息和正文
        List<Map<String, Object>> dataList = myfocusDao.selectList_self(params);
        //信息不存在  返回
        if(dataList == null || dataList.isEmpty()){
            throw new MyFocusInfoNotFoundException(FOCUS_INFO_NOT_EXIST);
        }
        for(Map<String,Object> infoMap : dataList){
            sb.setLength(0);//清空内容
            String title = ParamsUtil.nullDeal(infoMap, "KV_TITLE", "");//标题
            String content = ParamsUtil.nullDeal(infoMap, "KV_CONTENT", "");//内容
            String url = ParamsUtil.nullDeal(infoMap, "KV_URL", "");//url
            String site = ParamsUtil.nullDeal(infoMap, "KV_SITE", "");//站点
            String time = ParamsUtil.nullDeal(infoMap, "KV_CTIME", "");//时间
            String author = ParamsUtil.nullDeal(infoMap, "KV_SOURCE", "");//作者
            String orientation = ParamsUtil.nullDeal(infoMap, "KV_ORIENTATION", "");//倾向性，1.正面，2.负面，3.中性
            String transport = ParamsUtil.nullDeal(infoMap, "KV_TRANSPORT", "0");//转载数
            
            if(!"".equals(title)){
                title = HtmlFilter.filterWord(title);
                title = title.replace("\r\n", " ").replace("\r", " ").replaceAll("\\[beginimg\\](.*?)\\[endimg\\]", "").replace("<br />", " ").replace("<br/>", " ").replaceAll("beginimg(.*?)endimg", "");
            }
            if(!"".equals(content)){
                content = HtmlFilter.filterWord(content);
                content = content.replace("\r\n", " ").replace("\r", " ").replaceAll("\\[beginimg\\](.*?)\\[endimg\\]", "").replace("<br />", " ").replace("<br/>", " ").replaceAll("beginimg(.*?)endimg", "");
            }
            
            sb.append("尊敬的领导：您好，监测到以下信息：\n");
            sb.append("    标题：").append(title).append("\n");
            sb.append("    作者：").append(author).append("\n");
            sb.append("    时间：").append(time).append("\n");
            sb.append("    网站：").append(site).append("\n");
            sb.append("    地址：").append(url).append("\n");
            sb.append("    属性：").append("3".equals(orientation) ? "中性" : "1".equals(orientation) ? "正面" : "负面" ).append("\n");
            sb.append("    转发数：").append(transport).append("\n");
            sb.append("    内容：").append(content).append("\n");
            sb.append("    上报原因：").append(reason);
            
            //多个邮箱分隔成数组
            String[] emailArray = email.split(",");
            for(String str : emailArray){
                try {
                    Map<String,String> map = new HashMap<>();
                    map.put("title", Base64.encodeBase64URLSafeString("舆情监测数据".getBytes("UTF-8")));
                    map.put("content", Base64.encodeBase64URLSafeString(sb.toString().getBytes("UTF-8")));
                    map.put("email", str);
                    map.put("encode", "base64"); //内容和标题以base64编码格式，默认不编码
                    map.put("type", "text"); //以纯文本邮件发送，默认为HTML格式
                    
                    HttpUtil.post(Constant.PUSH_SERVER_URL,JSONObject.toJSONString(map));
                    
                } catch (Exception e) {
                    log.error("-------------------" + e.getMessage());
                    throw new SendEmailErrorException(SEND_EMAIL_ERROR);
                }
            }
        }
        
        return 1;
    }
    
    @Override
    public Map<String, Object> saveTopic(Map<String, Object> params) throws SysException {
        Map<String,Object> resultMap = new HashMap<>();
        
        String userId = ParamsUtil.nullDeal(params, "_KUID", "");//用户id
        String ktName = ParamsUtil.nullDeal(params, "ktName", "");//话题名称
        String ktSummary = ParamsUtil.nullDeal(params, "ktSummary", "");//话题概述
        String ktBeginTime = ParamsUtil.nullDeal(params, "ktBeginTime", "");//话题开始时间
        String ktEndTime = ParamsUtil.nullDeal(params, "ktEndTime", "");//话题结束时间
        String kkName1 = ParamsUtil.nullDeal(params, "kkName1", "");//关键词
        String kkName2 = ParamsUtil.nullDeal(params, "kkName2", "");//微博关键字
        String kkNote = ParamsUtil.nullDeal(params, "kkNote", "");//排除词
        String ktUuid = UUIDUtils.getUuid();//32位uuid
        Map<String,Object> insertParams = new HashMap<>();
        insertParams.put("KT_UUID", ktUuid);
        insertParams.put("KU_ID",userId);//用户id
        insertParams.put("KT_NAME",ktName);//话题名称
        insertParams.put("KT_SUMMARY",ktSummary);//话题描述
        insertParams.put("KT_BEGIN",ktBeginTime.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));//采集开始时间
//        insertParams.put("KK_END",DateUtil.getLongDate());//当前时间
        insertParams.put("KK_END",ktEndTime.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));//当前时间
        insertParams.put("KK_CLOSETIME",DateUtil.formatDate(DateUtil.toDate(DateUtil.getAddDateStr(DateUtil.getNowStrDate(), 6)), "yyyyMMdd")+"235959");//事件结束时间，第七天
        insertParams.put("KK_NAME",(kkName1 + "," + kkName2).replaceAll("\\s+", ""));//关键词+微博关键字
        insertParams.put("KT_STATUS","0");//状态：  0.未开始,1.启动,2.结束,3.停止
        insertParams.put("KT_TIME",DateUtil.getLongDate());//创建时间
        insertParams.put("KT_ISNEW","1");
        //添加话题
        int rowCount = topicDao.insertSelective(insertParams);
        if(rowCount == 0){
            throw new SysException();
        }
        
        //创建数据表
        params.put("KtUUid", ktUuid);
        topicDao.createTable_self(params);
        
        //保存话题关键词
        if(!"".equals(kkName1)){
            String[] kkName1Array = kkName1.split(",");
            for(String str : kkName1Array){
                saveTopicKeyWords(UUIDUtils.getUuid(),ktUuid,str.replaceAll("\\s+", " "),kkNote,DateUtil.getLongDate(),"1");
            }
        }
        //保存话题微博关键词
        if(!"".equals(kkName2)){
            String[] kkName2Array = kkName2.split(",");
            for(String str : kkName2Array){
                saveTopicKeyWords(UUIDUtils.getUuid(),ktUuid,str.replaceAll("\\s+", " "),kkNote,DateUtil.getLongDate(),"2");
            }
        }
        
        //词同步
        HttpUtil.post(Constant.WORD_SYNCH_URL + "/Redis!addRedisByUserid.do?userid=" + userId, new HashMap<>());
        
        return resultMap;
    }

    /**
     * @param kkUuid  话题关键词id
     * @param ktUuid  话题id
     * @param kkName  关键词
     * @param kkNote  排除词
     * @param kkCtime 添加时间
     * @param kkType  1.普通关键词，2.微博关键词
     * @return
     */
    private int saveTopicKeyWords(String kkUuid,String ktUuid,String kkName,String kkNote,String kkCtime,String kkType){
        Map<String,Object> insertParams1 = new HashMap<>();
        insertParams1.put("KK_UUID", kkUuid);
        insertParams1.put("KT_UUID", ktUuid);
        insertParams1.put("KK_NAME", kkName.replaceAll("\\s+", " "));
        insertParams1.put("KK_NOT", kkNote);
        insertParams1.put("KK_CTIME", kkCtime);
        insertParams1.put("KK_TYPE",kkType);
        return topicDao.saveTopicKeyWords(insertParams1);
    }
    public static void main(String[] args) {
//        System.out.println(DateUtil.formatDate(DateUtil.toDate(DateUtil.getAddDateStr(DateUtil.getNowStrDate(), 6)), "yyyyMMdd"));
    }
}
