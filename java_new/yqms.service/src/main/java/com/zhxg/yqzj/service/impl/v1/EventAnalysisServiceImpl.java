package com.zhxg.yqzj.service.impl.v1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.exception.ParamsNullException;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.framework.base.utils.HttpUtil;
import com.zhxg.framework.base.utils.LogicExpressionUtil;
import com.zhxg.framework.base.utils.LogicExpressionUtil.ExpressionException;
import com.zhxg.framework.base.utils.PropertiesUtil;
import com.zhxg.framework.base.utils.RedisUtil;
import com.zhxg.framework.base.utils.UUIDUtils;
import com.zhxg.yqzj.dao.v1.EventAnalysisDao;
import com.zhxg.yqzj.entities.v1.EventAnalysis;
import com.zhxg.yqzj.service.exception.eventanalysis.EventNotExistException;
import com.zhxg.yqzj.service.exception.eventanalysis.NumberException;
import com.zhxg.yqzj.service.exception.eventanalysis.ParseExpressionException;
import com.zhxg.yqzj.service.exception.myfocus.RepeatOperateException;
import com.zhxg.yqzj.service.exception.user.UserExpiredException;
import com.zhxg.yqzj.service.v1.EventAnalysisDataService;
import com.zhxg.yqzj.service.v1.EventAnalysisService;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.service
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户模块服务层接口实现
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.service.impl.v1.UserServiceImpl.java
 * <p>
 * Author: azmiu
 * <p>
 * Create Date: 2017年2月28日
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
public class EventAnalysisServiceImpl extends BaseServiceImpl<EventAnalysis> implements EventAnalysisService {
    private final static Logger log = LoggerFactory.getLogger(EventAnalysisServiceImpl.class);
    private final static String USER_STATUS = "账号已停用";
    private static final String KM_ID_IS_NULL = "id不能为空";
    private static final String REPEAT_OPERATE = "信息已经添加过";
    private static final int ABSTRACT_LENGTH = 400;
    private static final int STATUS = 1;
    private static final int COUNT = 0;
    @Autowired
    private EventAnalysisDao eventAnalysisDao;
    
    @Autowired
    private EventAnalysisDataService eventAnalysisDataService;
    

    @Override
    protected BaseDao<EventAnalysis> getBaseDao() {
        return this.eventAnalysisDao;
    }

    @Override
    public List<EventAnalysis> selectEventAnalysisList(Map<String, Object> paramMap) {
        List<EventAnalysis> eventAnalysisList=this.eventAnalysisDao.selectEventAnalysisList(paramMap);
        for(EventAnalysis eventAnalysis:eventAnalysisList){
            String topicId=eventAnalysis.getUuid();
            paramMap.put("topicId", topicId); 
            paramMap.put("tableName", "U"+paramMap.get("userId")+".TOPIC_"+topicId); 
            paramMap.put("beginTime", eventAnalysis.getBeginTime()); 
            paramMap.put("endTime", eventAnalysis.getCloseTime()); 
            try {
                int count=this.eventAnalysisDao.selectEventAnalysisCount_self(paramMap);
                eventAnalysis.setInfoTotalCount(count); 
                Map<String, Object> sourceTypeNum = eventAnalysisDao.selectTopicSourceTypeNum_self(paramMap);
                eventAnalysis.setSourceTypeNumMap(sourceTypeNum);
            } catch (Exception e) {
                eventAnalysis.setInfoTotalCount(COUNT);
                e.printStackTrace();
                log.error(e.getMessage(),e);
            }
            
        }       
        return eventAnalysisList;
    }

    public void vlidateStatus(String userId) throws UserNoFoundException, UserExpiredException, NumberException {
        //允许设置的数量和用户状态
        Map<String,Object> userInfoMap=this.eventAnalysisDao.selectUserStatusAndEventAnalysisCount(userId);
        int topicCount=0;
        if(userInfoMap!=null&&userInfoMap.size()>0){
            String userStatus=userInfoMap.get("userStatus")+"";
            topicCount = (int) userInfoMap.get("topicCount");
            if("2".equals(userStatus)){
                throw new UserExpiredException(USER_STATUS);
            }
        }else{
            throw new UserNoFoundException();
        }
        //用户已经创建的数量
        int count=this.eventAnalysisDao.selectEventAnalysisCount(userId);
        if(count>=topicCount){
            throw new NumberException();
        }
    }
    
    @Override
    public int addEventAnalysis(EventAnalysis eventAnalysis,
            Map<String, Object> paramMap) throws UserNoFoundException, UserExpiredException, NumberException, ParseExpressionException {
        paramMap.put("userId", eventAnalysis.getUserId());        
        this.vlidateStatus(eventAnalysis.getUserId()+""); // 验证添加话题
        
        if("1".equals(eventAnalysis.getIsLogic())){
            try{
                LogicExpressionUtil.parseExpression(eventAnalysis.getExpression());
            }catch(ExpressionException e){
                throw new ParseExpressionException(e.getMessage());
            }
        }
        
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmmss");
        String nowTime=sim.format(rightNow.getTime());
        String uuid = UUIDUtils.create();
        
        paramMap.put("uuid", uuid);
        paramMap.put("topicName", eventAnalysis.getTopicName());
        paramMap.put("summary", eventAnalysis.getSummary());        
        String beginTime = eventAnalysis.getBeginTime();
        if (beginTime == null || "".equals(beginTime)) {
            rightNow.add(Calendar.DAY_OF_MONTH, -7);// 从7天前开始采集
            beginTime = sim.format(rightNow.getTime());
        }       
        paramMap.put("beginTime", beginTime);
        paramMap.put("nowTime", nowTime);
        
        String firstKeyWords = eventAnalysis.getFirstKeyWords();
        String theSecondKeyWords=eventAnalysis.getTheSecondKeyWords();
        if (theSecondKeyWords != null && !"".equals(theSecondKeyWords)) {
            firstKeyWords = firstKeyWords + "," + theSecondKeyWords;
        }
        firstKeyWords=firstKeyWords.replaceAll("\\s+", " ");
        paramMap.put("firstKeyWords", firstKeyWords);
        
        String closeTime = eventAnalysis.getCloseTime();
        if (closeTime == null || "".equals(closeTime)) {
            rightNow.add(Calendar.DAY_OF_MONTH, 14);// 默认采集7天
            closeTime = sim.format(rightNow.getTime());
        }
        paramMap.put("isNew", "1");
        Map<String,Object> eventAnalysisStatusMap=this.eventAnalysisDao.selectUserEventAnalysisStatus(paramMap);
        if(eventAnalysisStatusMap!=null&&eventAnalysisStatusMap.size()>0){
            String eventAnalysisStatus=(String) eventAnalysisStatusMap.get("value");
            if("1".equals(eventAnalysisStatus)){
                paramMap.put("isNew", "2"); 
            }
        }
        paramMap.put("closeTime", closeTime);
        
        paramMap.put("notWords", eventAnalysis.getNotWords());
        paramMap.put("expression", eventAnalysis.getExpression());
        paramMap.put("isLogic", eventAnalysis.getIsLogic());
        paramMap.put("faceImgUrl", eventAnalysis.getFaceImgUrl());

       int result = this.eventAnalysisDao.saveAddTopic(paramMap);
       paramMap.put("tableName", "TOPIC_"+uuid);
       result = this.eventAnalysisDao.createTopicTable_self(paramMap);
       paramMap.put("commontTableName", "TOPIC_COMMONT_"+uuid);
       paramMap.put("weiboAuthorTableName", "TOPIC_WEIBO_AUTHOR_"+uuid);
       paramMap.put("regionTableName", "TOPIC_REGION_"+uuid);
       result = this.eventAnalysisDao.createTopicCommontTable_self(paramMap);
       result = this.eventAnalysisDao.createTopicWeiboAuthorTable_self(paramMap);
       result = this.eventAnalysisDao.createTopicRegionTableTable_self(paramMap);
        
       String exclusionWords = eventAnalysis.getExclusionWords();

       String[] firstWords = null;
       String firstKeyWordsnew = eventAnalysis.getFirstKeyWords();
       if (firstKeyWordsnew != null && !"".equals(firstKeyWordsnew)) {
           firstWords = firstKeyWordsnew.split(",");
       }
       String[] secondWords = null;
       if (eventAnalysis.getTheSecondKeyWords() != null && !"".equals(eventAnalysis.getTheSecondKeyWords())) {
           secondWords = eventAnalysis.getTheSecondKeyWords().split(",");
       }

       if (firstWords != null && firstWords.length > 0) {
           for (int i = 0; i < firstWords.length; i++) {
               if (!"".equals(firstWords[i])) {
                   String keyUuid = UUIDUtils.create();
                   firstKeyWords= firstWords[i].replaceAll("\\s+", " ");
                   paramMap.put("firstKeyWordsnew", firstKeyWords);
                   paramMap.put("exclusionWords", exclusionWords);
                   paramMap.put("keyUuid", keyUuid);
                   paramMap.put("type", "1");
                   result = this.eventAnalysisDao.saveAddTopickeyword(paramMap);
               }
           }
       } 
       
        if (secondWords != null && secondWords.length > 0) {
            for (int i = 0; i < secondWords.length; i++) {
                if (!"".equals(secondWords[i])) {
                    String keyUuid = UUIDUtils.create();
                    String secondKeyWords = secondWords[i].replaceAll("\\s+", " ");
                    paramMap.put("firstKeyWordsnew", secondKeyWords);
                    paramMap.put("exclusionWords", exclusionWords);
                    paramMap.put("keyUuid", keyUuid);
                    paramMap.put("type", "2");
                    result = this.eventAnalysisDao.saveAddTopickeyword(paramMap);
                }
            }
        }
//        String replace = paramMap.get("firstKeyWordsnew").toString().replace(" ", "+");
//        paramMap.put("firstKeyWordsnew", replace);
        
        try {
            HttpUtil.post(PropertiesUtil.getProperties("application.properties", "ms.server.url")+"Redis!loadTopic.do?userId="+paramMap.get("userId")+"&userid="+paramMap.get("userId"), "");
            HttpUtil.post(PropertiesUtil.getProperties("application.properties", "ms.server.url")+"Redis!dataSupplementTopic.do?userId="+paramMap.get("userId")+"&userid="+paramMap.get("userId")
            +"&begin="+paramMap.get("beginTime")+"&end="+paramMap.get("closeTime")+"&topicid="+uuid, "");
            
            if (PropertiesUtil.getValue("API_WEIBO_USERID").contains(paramMap.get("userId")+"")) {
                new Thread(){
                    @Override
                    public void run(){
                        for(String words:firstKeyWordsnew.split(",")){
                            String[] KK_NAME=null;
                            String word1="";
                            if (words!=null&&!words.equals("")) {
                                KK_NAME = words.split(" ");
                                for(int i=0;i<KK_NAME.length;i++){
                                    if(i==0){
                                        word1 = KK_NAME[i];
                                    }else{
                                        word1 = word1+"<keyword>"+KK_NAME[i];
                                    }
                                }
                                EventAnalysisServiceImpl.this.getSchemeAddInterface(eventAnalysis.getTopicName(),word1);
                            }
                        }
                    }
                }.start();
            }
        } catch (Exception e) {
            this.logger.error(e.getMessage(),e);
        }      
        return result;
    }
    
    //演示方案添加专用接口
    private String getSchemeAddInterface(String name, String demoWords) {
        String result = "";
        try {
            Map apiMap = new HashMap();
            apiMap.put("add-demo-name", name);
            apiMap.put("add-demo-args", demoWords.trim());
            System.out.println("演示方案关键词："+demoWords.trim());

            result = HttpUtil.post(PropertiesUtil.getValue("API_WEIBO_DEVELOP"), apiMap);
            System.out.println("返回值："+result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<EventAnalysis> selectEventAnalysisInfo(String eventAnalysisId) {
        List<EventAnalysis> eventAnalysisInfo=this.eventAnalysisDao.selectEventAnalysisInfo(eventAnalysisId);
        return eventAnalysisInfo;
    }
    
    @Override
    public String compareEvent(EventAnalysis eventAnalysis, String dbhost){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        List<EventAnalysis> eventAnalysisInfo=this.eventAnalysisDao.selectEventAnalysisInfo(eventAnalysis.getUuid());
        Set<String> oldNotWords = new HashSet<>();
        Set<String> newNotWords = new HashSet<>();
        List<List<String>> oldWords = new ArrayList<>();
        List<List<String>> newWords = new ArrayList<>();
        if(eventAnalysisInfo.isEmpty()){
            return "0";
        }else{
            EventAnalysis oldEvent = eventAnalysisInfo.get(0);
            if(!oldEvent.getIsLogic().equals(eventAnalysis.getIsLogic())){
                return "0";
            }
            //否定词放入set
            for(EventAnalysis e:eventAnalysisInfo){
                if(e.getExclusionWords()!=null){
                    for(String w:e.getExclusionWords().split("\\s+")){
                        if(StringUtils.isNotBlank(w)){
                            oldNotWords.add(w);
                        }
                    }
                }
                if(e.getNotWords()!=null){
                    for(String w:e.getNotWords().split("\\s+")){
                        if(StringUtils.isNotBlank(w)){
                            oldNotWords.add(w);
                        }
                        
                    }
                }
                if(e.getName()!=null){
                    oldWords.add(Arrays.asList(e.getName().split("\\s+")));
                }
            }
            if(StringUtils.isNotBlank(eventAnalysis.getNotWords())){
                for(String w:eventAnalysis.getNotWords().split("\\s+")){
                    if(StringUtils.isNotBlank(w)){
                        newNotWords.add(w);
                    }
                }
            }
            if(StringUtils.isNotBlank(eventAnalysis.getExclusionWords())){
                for(String w:eventAnalysis.getExclusionWords().split("\\s+")){
                    if(StringUtils.isNotBlank(w)){
                        newNotWords.add(w);
                    }
                }
            }
            if(StringUtils.isNotBlank(eventAnalysis.getFirstKeyWords())){
                for(String s:eventAnalysis.getFirstKeyWords().split(",")){
                    newWords.add(Arrays.asList(s.split("\\s+")));
                }
            }
            if(eventAnalysis.getIsLogic().equals("1")){//高级事件比较表达式
                if(!eventAnalysis.getExpression().equals(oldEvent.getExpression())){
                    return "0";
                }
            }else{//普通事件比较词组
                Collections.sort(oldWords, new Comparator<List<String>>() {
                    @Override
                    public int compare(List<String> l1,List<String> l2) {
                        Collections.sort(l1);
                        Collections.sort(l2);
                        return l1.toString().compareTo(l2.toString());
                    }
                });
                Collections.sort(newWords, new Comparator<List<String>>() {
                    @Override
                    public int compare(List<String> l1, List<String> l2) {
                        Collections.sort(l1);
                        Collections.sort(l2);
                        return l1.toString().compareTo(l2.toString());
                    }
                });
                if (!oldWords.toString().equals(newWords.toString())) {
                    return "0";
                }
            }
            if(!newNotWords.equals(oldNotWords)){
                if(newNotWords.containsAll(oldNotWords)){
                    newNotWords.removeAll(oldNotWords);//新增的否定词同步到redis
                    //TODO add newNotWord to redis!
                    if(StringUtils.isNotBlank(dbhost)){
                        JSONObject json = new JSONObject();
                        json.put("KU_ID", eventAnalysis.getUserId());
                        json.put("KU_DBNAME", dbhost);
                        json.put("EVENT_ID", eventAnalysis.getUuid());
                        json.put("EVENT_NO_WORD", newNotWords);
                        json.put("DELETETIME", sdf.format(new Date()));
                        RedisUtil.lpush(4,"eventNoWords", json.toJSONString());
                    }
                }else{//新否定词不包含原否定词 ，删了重建
                    return "0";
                }
            }
            
            String begin="";
            String end="";
            try{
                Calendar oldBegin = Calendar.getInstance(); 
                oldBegin.setTime(sdf.parse(oldEvent.getBeginTime()));
                
                Calendar oldClose = Calendar.getInstance(); 
                oldClose.setTime(sdf.parse(oldEvent.getCloseTime()));
                
                Calendar newBegin = Calendar.getInstance(); 
                newBegin.setTime(sdf.parse(eventAnalysis.getBeginTime()));
                
                Calendar newClose = Calendar.getInstance(); 
                newClose.setTime(sdf.parse(eventAnalysis.getCloseTime()));
                
                Calendar now = Calendar.getInstance();
                if(newBegin.before(oldBegin)){//开始时间向前延伸
                    if(newBegin.before(now)){
                        begin = eventAnalysis.getBeginTime();
                    }
                    end = oldEvent.getBeginTime();
                } 
                if(newClose.after(oldClose)){
                    if(oldClose.before(now)){
                        if(newClose.before(now)){
                            end = eventAnalysis.getCloseTime();
                        }else{
                            end = sdf.format(now.getTime());
                        }
                        if(StringUtils.isNotBlank(begin)){
                            begin = oldEvent.getCloseTime();
                        }
                    }
                }
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            if(StringUtil.isNotEmpty(begin)&&StringUtil.isNotEmpty(end)){
                return begin+"#"+end;
            }
        }
        return "1";
    }

    @Override
    public int updateEventAnalysis(EventAnalysis eventAnalysis, Map<String, Object> paramMap) throws UserNoFoundException, UserExpiredException, NumberException, EventNotExistException, ParseExpressionException {
       String dealStr = this.compareEvent(eventAnalysis,paramMap.get("_DBHOST").toString());
       if("1".equals(eventAnalysis.getIsLogic())){
           try{
               LogicExpressionUtil.parseExpression(eventAnalysis.getExpression());
           }catch(ExpressionException e){
               throw new ParseExpressionException(e.getMessage());
           }
       }
       if(dealStr.equals("0")){
           //TODO delete and create
           this.updateEventAnalysis(null,eventAnalysis,paramMap);
           this.addEventAnalysis(eventAnalysis, paramMap);
           return 1;
       }
       int result= this.eventAnalysisDao.updateEventAnalysis(eventAnalysis);
       result= this.eventAnalysisDao.deleteEventAnalysisWords(eventAnalysis);

       Date date = new Date();
       SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
       String timeNow = format.format(date);

       String[] KeyWords = eventAnalysis.getFirstKeyWords().split(",");
       paramMap.put("uuid",eventAnalysis.getUuid());
       paramMap.put("nowTime",timeNow);
       paramMap.put("exclusionWords",eventAnalysis.getExclusionWords());
       paramMap.put("type","1");
       if (KeyWords.length > 0 && KeyWords[0].length() > 0) {
           for (int i = 0; i < KeyWords.length; i++) {
               paramMap.put("keyUuid",UUIDUtils.create());
               String keyWord=KeyWords[i].replaceAll("\\s+", " ");
               paramMap.put("firstKeyWordsnew",keyWord);
               result= this.eventAnalysisDao.saveAddTopickeyword(paramMap);
           }
       }
        try {
            HttpUtil.post(PropertiesUtil.getProperties("application.properties", "ms.server.url")+"Redis!loadTopic.do?userId="+eventAnalysis.getUserId()+"&userid="+eventAnalysis.getUserId(), "");
           if(!dealStr.equals("1")){
               HttpUtil.post(PropertiesUtil.getProperties("application.properties", "ms.server.url")+"Redis!dataSupplementTopic.do?userId="+eventAnalysis.getUserId()+"&userid="+eventAnalysis.getUserId()
               +"&begin="+dealStr.split("#")[0]+"&end="+dealStr.split("#")[1]+"&topicid="+eventAnalysis.getUuid(), "");
           }
//            HttpUtil.post(PropertiesUtil.getProperties("application.properties", "ms.server.url")+"Redis!dataSupplementTopic.do?userId="+eventAnalysis.getUserId()+"&userid="+eventAnalysis.getUserId()
//            +"&begin="+eventAnalysis.getBeginTime()+"&end="+eventAnalysis.getCloseTime()+"&topicid="+eventAnalysis.getUuid(), "");
        } catch (Exception e) {
            this.logger.error(e.getMessage(),e);
        }  
        return result;
        
    }

    @Override
    public int updateEventAnalysis(HttpServletRequest request,EventAnalysis eventAnalysis, Map<String, Object> paramMap) throws EventNotExistException {
        paramMap.put("userId", eventAnalysis.getUserId());
        paramMap.put("uuid", eventAnalysis.getUuid());    
        paramMap.put("eventId", eventAnalysis.getUuid()); 
        paramMap.put("beginTime", eventAnalysis.getBeginTime()); 
        paramMap.put("endTime", eventAnalysis.getEndTime()); 
        this.eventAnalysisDao.deleteEventAnalysisWords(paramMap);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        paramMap.put("deleteTime", sdf.format(new Date()));
        //查询用户是否是系统用户
        int result = 0;
        Map<String,Object> userInfoMap=this.eventAnalysisDao.selectUserStatusAndEventAnalysisCount(paramMap.get("_KUID")+"");
        if(userInfoMap!=null&&userInfoMap.size()>0){
            String userGenre=userInfoMap.get("userGenre")+"";
            if("3".equals(userGenre)||request==null){//系统用户直接删除,request==null 是编辑==删除并添加，不需要逻辑删
                String[] uuids = eventAnalysis.getUuid().split(",");
                paramMap.put("uuids", uuids);
                result = this.eventAnalysisDao.deletePastEventAnalysis(paramMap);
            }else{ //其他用户假删除
                result = this.eventAnalysisDao.deleteEventAnalysis(paramMap);
            }
        }
        if(result==0){
            throw new EventNotExistException();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userId", eventAnalysis.getUserId());
        map.put("topicId", eventAnalysis.getUuid());
        map.put("deleteTime", sdf.format(new Date()));        
        RedisUtil.setStr(3,"topic_delete_info", JSON.toJSONString(map));
        try {
            HttpUtil.post(PropertiesUtil.getProperties("application.properties", "ms.server.url")+"Redis!loadTopic.do?userId="+paramMap.get("userId"), "");
        } catch (Exception e) {
            this.logger.error(e.getMessage(),e);
        } 
        final ServletContext servletContext = request==null?null:request.getSession().getServletContext();
        new Thread(){
            @Override
            public void run(){
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    Map<String, Object> map = new HashMap<>();
                    map.put("userId", eventAnalysis.getUserId());
                    map.put("topicId", eventAnalysis.getUuid());
                    map.put("deleteTime", sdf.format(new Date()));        
                    RedisUtil.setStr(3,"topic_delete_info", JSON.toJSONString(map));
                    try {
                        HttpUtil.post(PropertiesUtil.getProperties("application.properties", "ms.server.url")+"Redis!loadTopic.do?userId="+paramMap.get("userId"), "");
                    } catch (Exception e) {
                        EventAnalysisServiceImpl.this.logger.error(e.getMessage(),e);
                    } 
                    //todo 调app生成快照接口
                    if(servletContext!=null){
                        String snapshotUrl = EventAnalysisServiceImpl.this.eventAnalysisDataService.getDeleteEventAnalysisSnapshot(paramMap);
                        eventAnalysis.setSnapshotUrl(snapshotUrl);
                        EventAnalysisServiceImpl.this.eventAnalysisDao.updateEventAnalysis(eventAnalysis);
                        String appSnapshotUrl = EventAnalysisServiceImpl.this.eventAnalysisDataService.createAppEventSnapshot(servletContext,paramMap);
                        eventAnalysis.setAppSnapshotUrl(appSnapshotUrl);
                        EventAnalysisServiceImpl.this.eventAnalysisDao.updateEventAnalysis(eventAnalysis);
                    }
                    paramMap.put("tableName", paramMap.get("_DBNAME")+".TOPIC_"+eventAnalysis.getUuid());
                    EventAnalysisServiceImpl.this.eventAnalysisDao.dropEventAnalysis_self(paramMap);
                    paramMap.put("tableName", paramMap.get("_DBNAME")+".TOPIC_WEIBO_AUTHOR_"+eventAnalysis.getUuid());
                    EventAnalysisServiceImpl.this.eventAnalysisDao.dropEventAnalysis_self(paramMap);
                    paramMap.put("tableName", paramMap.get("_DBNAME")+".TOPIC_COMMONT_"+eventAnalysis.getUuid());
                    EventAnalysisServiceImpl.this.eventAnalysisDao.dropEventAnalysis_self(paramMap);
                    paramMap.put("tableName", paramMap.get("_DBNAME")+".TOPIC_REGION_"+eventAnalysis.getUuid());
                    EventAnalysisServiceImpl.this.eventAnalysisDao.dropEventAnalysis_self(paramMap);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error(e.getMessage(),e);
                }
            }
        }.start();
        return STATUS;
    }

    @Override
    public List<EventAnalysis> selectRecommendEventAnalysisList() {
        List<EventAnalysis> eventAnalysis= this.eventAnalysisDao.selectRecommendEventAnalysisList();
        return eventAnalysis;
    }

    @Override
    public int selectEventAnalysisCount(String userId, String eventAnalysisId, Map<String, Object> paramsMap) {
        paramsMap.put("userId", userId);
        paramsMap.put("eventAnalysisId", eventAnalysisId);
        paramsMap.put("tableName", "U"+userId+".TOPIC_"+eventAnalysisId); 
        int count=this.eventAnalysisDao.selectEventAnalysisCount_self(paramsMap);
        return count;
    }

    @Override
    public PageInfo<EventAnalysis> selectDeleteEventAnalysisList(String userId, Pagination pageInfo) {
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("userId", userId);
        PageInfo<EventAnalysis> eventAnalysis= this.eventAnalysisDao.selectDeleteEventAnalysisList(paramMap, pageInfo);
        for(EventAnalysis event:eventAnalysis.getList()){
            event.setSnapshotUrl("http://" + PropertiesUtil.getValue("file.server.domain") + "/eventAnalysisSnapshot/" +event.getSnapshotUrl());
            event.setAppSnapshotUrl("http://" + PropertiesUtil.getValue("file.server.domain") + "/appeventIdpic/html/" +event.getAppSnapshotUrl());
        }
        return eventAnalysis;
    }

    @Override
    public int addAttention(EventAnalysis eventAnalysis, Map<String, Object> paramMap) throws ParamsNullException, RepeatOperateException, UserNoFoundException, SysException {
        String ids = eventAnalysis.getIds();//信息id       
        if(StringUtils.isEmpty(ids)){//关注id不能为空
            throw new ParamsNullException(KM_ID_IS_NULL);
        }
        boolean same = true; // 推荐事件分析shareid是分享过来的信息不标注已关注
        if(!paramMap.get("_KUID").equals(paramMap.get("_OTHER_KUID"))){  
            same = false;
        }
        List<String> idsList = new ArrayList<>();//ids集合
        
        //kmIds分隔成数组
        String[] kmIdsArray = "".equals(ids) ? null : ids.split(",");
        //添加到集合
        if(kmIdsArray != null && kmIdsArray.length > 0){
            for(String str : kmIdsArray){
                idsList.add(str);
            }
        }
        paramMap.put("kvIdList", idsList);
        paramMap.put("kyId", eventAnalysis.getClassId());
        paramMap.put("userId", eventAnalysis.getUserId());
        paramMap.put("tableName", paramMap.get("_DBNAME")+".WK_T_MYATTENTION_INFO");
        //查询关注中已经存在的信息
        List<Map<String, Object>> list = this.eventAnalysisDao.selectAttentionList_self(paramMap);
        
        if(list != null && !list.isEmpty()){
            //存在添加过的信息
            for(Map<String,Object> map : list){
                String kvId = String.valueOf(map.get("KV_UUID"));
                //从集合中删除
                idsList.remove(kvId);
            }
        }        
        //重复添加
        if(idsList == null || idsList.isEmpty()){
            throw new RepeatOperateException(REPEAT_OPERATE);
        }

        //设置去重后的Ids
        paramMap.put("kmIdList", idsList);
        this.eventAnalysisDao.deleteAttentionList_self(paramMap);
        paramMap.put("eventAnalysisId", eventAnalysis.getUuid());
        List<Map<String,Object>> eventAnalysisData=this.eventAnalysisDao.selectEventAnalysisList_other(paramMap);
        for(Map<String,Object> data:eventAnalysisData){
            String knAbstract=data.get("KN_ABSTRACT")+"";
            data.put("KN_ABSTRACT",knAbstract.length() > ABSTRACT_LENGTH ? knAbstract.substring(0, ABSTRACT_LENGTH) : knAbstract);
            data.put("keyId", UUIDUtils.getUuid());
            data.put("KV_INSERT_TIME", DateUtil.getLongDate());
            data.put("classId", eventAnalysis.getClassId());
            paramMap.putAll(data);
            paramMap.put("tableName", paramMap.get("_DBNAME")+".WK_T_MYATTENTION_INFO");
            this.eventAnalysisDao.insertAttentionSelective_self(paramMap);
            paramMap.put("tableName", paramMap.get("_DBNAME")+".WK_T_MYATTENTION_INFOCNT");
          //添加关注内容
            this.eventAnalysisDao.insertAttentionCntSelective_self(paramMap);            
        }
//        更改关注状态
        if(same){
            try {
                this.eventAnalysisDao.updateattention_self(paramMap);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage(),e);
            }
                        
        }
        return STATUS;
    }

    @Override
    public int updateOrientation(Map<String, Object> paramMap, EventAnalysis eventAnalysis) {
        String ids = eventAnalysis.getIds();
        String[] split = ids.split(",");
        List<String> idsList = new ArrayList<>();
        for(String id : split){
            idsList.add(id);
        }
        paramMap.put("idsList", idsList);
        paramMap.put("orientation", eventAnalysis.getOrientation());
        paramMap.put("eventAnalysisId", eventAnalysis.getUuid());
        int result=this.eventAnalysisDao.updateOrientation_self(paramMap);
        return result;
    }

    @Override
    public int updateRead(EventAnalysis eventAnalysis, Map<String, Object> paramMap) {
        String ids = eventAnalysis.getIds();
        String[] split = ids.split(",");
        List<String> idsList = new ArrayList<>();
        for(String id : split){
            idsList.add(id);
        }
        paramMap.put("idsList", idsList);
        paramMap.put("eventAnalysisId", eventAnalysis.getUuid());
        try {
            this.eventAnalysisDao.updateRead_self(paramMap);  
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(),e);
        }
        
        return STATUS;
    }

    @Override
    public int deleteEventAnalysisData(EventAnalysis eventAnalysis, Map<String, Object> paramMap) {
        String ids = eventAnalysis.getIds();
        String[] id = ids.split(",");
        paramMap.put("eventAnalysisId", eventAnalysis.getUuid());
        String type=eventAnalysis.getType();//1彻底删除 2还原（非精准变精准） 3精准变非精准
        paramMap.put("id", id);
        if("1".equals(type)){//直接删除              
            this.eventAnalysisDao.deleteEventAnalysisData_self(paramMap);           
        }else if("2".equals(type)){//还原（非精准变精准）
            paramMap.put("status", "1");
            this.eventAnalysisDao.updateEventAnalysisData_self(paramMap);
        }else{//精准变非精准
            paramMap.put("status", "x");
            this.eventAnalysisDao.updateEventAnalysisData_self(paramMap);
        } 
        return STATUS;
    }

    @Override
    public int addBriefing(EventAnalysis eventAnalysis, Map<String, Object> paramMap) throws RepeatOperateException, ParamsNullException {
        String ids = eventAnalysis.getIds();//信息id       
        if(StringUtils.isEmpty(ids)){//id不能为空
            throw new ParamsNullException(KM_ID_IS_NULL);
        }
        boolean same = true; // 推荐事件分析shareid是分享过来的信息不标注已加简报
        if(!paramMap.get("_KUID").equals(paramMap.get("_OTHER_KUID"))){  
            same = false;
        }
        List<String> idsList = new ArrayList<>();//ids集合
        
        //kmIds分隔成数组
        String[] kmIdsArray = "".equals(ids) ? null : ids.split(",");
        //添加到集合
        if(kmIdsArray != null && kmIdsArray.length > 0){
            for(String str : kmIdsArray){
                idsList.add(str);
            }
        }
        paramMap.put("kvIdList", idsList);
        paramMap.put("kyId", eventAnalysis.getClassId());
        paramMap.put("tableName", paramMap.get("_DBNAME")+".WK_T_YQJB_INFO");
          
        //查询简报中已经存在的信息
        List<Map<String, Object>> list = this.eventAnalysisDao.selectBriefingList_self(paramMap);
        
        if(list != null && !list.isEmpty()){
            //存在添加过的信息
            for(Map<String,Object> map : list){
                String kvId = String.valueOf(map.get("KV_UUID"));
                //从集合中删除
                idsList.remove(kvId);
            }
        }        
        //重复添加
        if(idsList == null || idsList.isEmpty()){
            throw new RepeatOperateException(REPEAT_OPERATE);
        }

        //设置去重后的Ids
        paramMap.put("kmIdList", idsList);
        paramMap.put("eventAnalysisId", eventAnalysis.getUuid());
        List<Map<String,Object>> eventAnalysisData=this.eventAnalysisDao.selectEventAnalysisList_other(paramMap);
        for(Map<String,Object> data:eventAnalysisData){
            String knAbstract=data.get("KN_ABSTRACT")+"";
            data.put("KN_ABSTRACT",knAbstract.length() > ABSTRACT_LENGTH ? knAbstract.substring(0, ABSTRACT_LENGTH) : knAbstract);
            data.put("keyId", UUIDUtils.getUuid());
            data.put("KV_INSERT_TIME", DateUtil.getLongDate());
            data.put("classId", eventAnalysis.getClassId());
            paramMap.putAll(data);
            
            this.eventAnalysisDao.insertBriefingSelective_self(paramMap); 
          //添加简报内容
            this.eventAnalysisDao.insertBriefingCntSelective_self(paramMap);            
        }
//        更改简报状态
        if(same){
            try {
                this.eventAnalysisDao.updateBriefing_self(paramMap);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage(),e);
            }
                        
        }
        return STATUS;
    }

    @Override
    public int deletePastEventAnalysis(Map<String, Object> eventAnalysis) {
        String uuid=eventAnalysis.get("uuid")+"";
        String[] uuids = uuid.split(",");
        eventAnalysis.put("uuids", uuids);
        int result=this.eventAnalysisDao.deletePastEventAnalysis(eventAnalysis);
        return result;
    }

    @Override
    public int updateEventAnalysisStatus(Map<String, Object> paramMap) {
        //查询用户是否设置过
        Map<String, Object> eventAnalysisStatus=this.eventAnalysisDao.selectEventAnalysisStatus(paramMap);
        int result=COUNT;
        if(eventAnalysisStatus!=null&&eventAnalysisStatus.size()>0){
          //有更改
            result=this.eventAnalysisDao.updateEventAnalysisStatus(paramMap); 
        }else{
          //没有添加
            result=this.eventAnalysisDao.addEventAnalysisStatus(paramMap); 
               
        }       
        return result;
    }

    @Override
    public Map<String, Object> selectEventAnalysisStatus(Map<String, Object> paramMap) {
        Map<String, Object> eventAnalysisStatus=this.eventAnalysisDao.selectEventAnalysisStatus(paramMap);
        return eventAnalysisStatus;
    }

    @Override
    public int updateOrientations(Map<String, Object> paramMap) {
        //获取信息的simHash
        List<String> simHashList = this.eventAnalysisDao.getSimhashList_self(paramMap);
        //修改所有simHash相同信息的倾向性
        paramMap.put("simHashList",simHashList);
        int result = this.eventAnalysisDao.updateOrientations_self(paramMap);
        return result;
    }

    @Override
    public Map<String, Object> selectUserEventAnalysisCount(String userId) {
        return this.eventAnalysisDao.selectUserEventAnalysisNum(userId);
    }

}
