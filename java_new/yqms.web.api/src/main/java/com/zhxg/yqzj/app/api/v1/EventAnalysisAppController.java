package com.zhxg.yqzj.app.api.v1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.solr.common.StringUtils;
import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.constants.SysConstants;
import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.exception.ParamsNullException;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.http.Result;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.framework.base.utils.JxlsUtils;
import com.zhxg.framework.base.utils.LogicExpressionUtil;
import com.zhxg.framework.base.utils.PageUtil;
import com.zhxg.framework.base.utils.ParamsUtil;
import com.zhxg.framework.base.utils.PropertiesUtil;
import com.zhxg.framework.base.utils.UploadFileUtil;
import com.zhxg.framework.base.utils.LogicExpressionUtil.ExpressionException;
import com.zhxg.yqzj.entities.v1.EventAnalysis;
import com.zhxg.yqzj.entities.v1.EventAnalysisData;
import com.zhxg.yqzj.entities.v1.ScreeningConditions;
import com.zhxg.yqzj.service.exception.eventanalysis.EventNotExistException;
import com.zhxg.yqzj.service.exception.eventanalysis.NumberException;
import com.zhxg.yqzj.service.exception.eventanalysis.ParseExpressionException;
import com.zhxg.yqzj.service.exception.myfocus.RepeatOperateException;
import com.zhxg.yqzj.service.exception.user.UserExpiredException;
import com.zhxg.yqzj.service.util.PageInfoUtil;
import com.zhxg.yqzj.service.v1.EventAnalysisDataService;
import com.zhxg.yqzj.service.v1.EventAnalysisService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * <p>
 * CopyRright (c)2012-2016: qinshuan
 * <p>
 * Project:                 yqms.web.api
 * <p>
 * Module ID:               <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments:                <事件分析模块>
 * <p>
 * JDK version used:        JDK1.8
 * <p>
 * NameSpace:               com.zhxg.yqzj.web.api.v1.EventAnalysisController.java
 * <p>
 * Author:                  qinshuan
 * <p>
 * Create Date:             2018年3月6日
 * <p>
 * Modified By:             <修改人中文名或拼音缩写>
 * <p>
 * Modified Date:           <修改日期>
 * <p>
 * Why & What is modified:  <修改原因描述>
 * <p>
 * Version:                 v1.0
*/ 
@RestController
@RequestMapping("/app/eventAnalysisApp")
public class EventAnalysisAppController extends BaseController<EventAnalysis> {

    @Autowired
    private EventAnalysisService eventAnalysisService;    
    @Autowired
    private EventAnalysisDataService eventAnalysisDataService;  

    @Override
    protected BaseService<EventAnalysis> getBaseService() {
        return this.eventAnalysisService;
    }
    
    /**
     * 添加事件分析
     * @throws SysException 
     * @throws UserNoFoundException 
     * @throws NumberException 
     * @throws UserExpiredException 
     * @throws ParseExpressionException 
     */
    @ResponseBody
    @RequestMapping(value = "/addEventAnalysis", method = RequestMethod.POST,consumes = "application/json")
    @ApiOperation(value = "添加事件分析接口", httpMethod = "POST", response = ApiResponseBody.class, notes = "addEventAnalysis", produces = "application/json")
    public ApiResponseBody addEventAnalysis(HttpServletRequest request,@RequestBody EventAnalysis eventAnalysis) throws UserNoFoundException, SysException, UserExpiredException, NumberException, ParseExpressionException  { 
        Map<String, Object> paramMap = getUserInfo(request);
        int result = this.eventAnalysisService.addEventAnalysis(eventAnalysis,paramMap);
        return this.returnSuccess(result);
            
    }
    
    /**
     * 事件分析列表和事件分析信息数量
     */
    @ResponseBody
    @RequestMapping(value = "/selectEventAnalysisList", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "事件分析列表", httpMethod = "GET", response = ApiResponseBody.class, notes = "select eventAnalysisList", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "type", value = "事件类型:为空或0 普通，1 高级，all 所有", required = false, paramType = "query", dataType = "String")
    })
    public ApiResponseBody selectEventAnalysisList(HttpServletRequest request,@RequestParam(value = "userId", required = true)  String userId
            ,@RequestParam(value = "type", required = false)  String type) throws SysException, UserNoFoundException { 
        Map<String,Object> params = getUserInfo(request);
        params.put("userId", userId);
        if(StringUtils.isEmpty(type)){
            params.put("type", "0");//0 普通事件
        }else{
          //所有事件
        }
        List<EventAnalysis> eventAnalysis = eventAnalysisService.selectEventAnalysisList(params);
        return this.returnSuccess(eventAnalysis);
            
    }
    
    /**
     * 推荐事件分析信息数量
     
     */
    @ResponseBody
    @RequestMapping(value = "/selectEventAnalysisCount", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "事件分析信息数量", httpMethod = "GET", response = ApiResponseBody.class, notes = "selectEventAnalysisCount", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "eventAnalysisId", value = "事件分析id", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody selectEventAnalysisCount(HttpServletRequest request,@RequestParam(value = "userId", required = true)  String userId,
            @RequestParam(value = "eventAnalysisId", required = true)  String eventAnalysisId) throws SysException, UserNoFoundException {
        Map<String,Object> paramsMap = getUserInfo(request);
        int eventAnalysisCount = eventAnalysisService.selectEventAnalysisCount(userId,eventAnalysisId,paramsMap);
        return this.returnSuccess(eventAnalysisCount);
            
    }
    
    /**
     * 修改保存事件分析
     * @throws SysException 
     * @throws UserNoFoundException 
     * @throws NumberException 
     * @throws UserExpiredException 
     * @throws EventNotExistException 
     * @throws ParseExpressionException 
     */
    @ResponseBody
    @RequestMapping(value = "/updateEventAnalysis", method = RequestMethod.POST,consumes = "application/json")
    @ApiOperation(value = "修改保存事件分析", httpMethod = "POST", response = ApiResponseBody.class, notes = "updateEventAnalysis", produces = "application/json")   
    public ApiResponseBody updateEventAnalysis(HttpServletRequest request,@RequestBody EventAnalysis eventAnalysis) throws UserNoFoundException, SysException, UserExpiredException, NumberException, EventNotExistException, ParseExpressionException  {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.putAll(ParamsUtil.transToMAP(eventAnalysis,EventAnalysis.class));
        int result = this.eventAnalysisService.updateEventAnalysis(eventAnalysis,paramMap);
        return this.returnSuccess(result);
            
    }
    
    /**
     * 比较事件是否改变：0 改变了 需要删了重建，1没改变，begin-end：需要补充这个时段的数据
     * @throws SysException 
     * @throws UserNoFoundException 
     * @throws NumberException 
     * @throws UserExpiredException 
     * @throws EventNotExistException 
     */
    @ResponseBody
    @RequestMapping(value = "/compareEventAnalysis", method = RequestMethod.POST,consumes = "application/json")
    @ApiOperation(value = "修改保存事件分析", httpMethod = "POST", response = ApiResponseBody.class, notes = "updateEventAnalysis", produces = "application/json")   
    public ApiResponseBody compareEvent(HttpServletRequest request,@RequestBody EventAnalysis eventAnalysis) throws UserNoFoundException, UserExpiredException, NumberException, SysException, EventNotExistException  {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.putAll(ParamsUtil.transToMAP(eventAnalysis,EventAnalysis.class));
        String result = this.eventAnalysisService.compareEvent(eventAnalysis, null);
        return this.returnSuccess(result);
            
    }
    
    /**
     * 事件分析展示
     
     */
    @ResponseBody
    @RequestMapping(value = "/selectEventAnalysisInfo", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "事件分析展示", httpMethod = "GET", response = ApiResponseBody.class, notes = "select EventAnalysisInfo", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "eventAnalysisId", value = "事件分析id", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody selectEventAnalysisInfo(HttpServletRequest request,@RequestParam(value = "eventAnalysisId", required = true)  String eventAnalysisId) { 
        List<EventAnalysis> eventAnalysis = eventAnalysisService.selectEventAnalysisInfo(eventAnalysisId);
        if(!eventAnalysis.isEmpty()){
            EventAnalysis event = eventAnalysis.get(0);
            if("1".equals(event.getIsLogic())){
                event.setExpressionAsArray(LogicExpressionUtil.parseExpression(event.getExpression()));
            }
        }
        return this.returnSuccess(eventAnalysis);
            
    }
    
    /**
     * 删除事件分析
     * @throws SysException 
     * @throws UserNoFoundException 
     * @throws EventNotExistException 
     * @throws NumberException 
     * @throws UserExpiredException 
     */
    @ResponseBody
    @RequestMapping(value = "/deleteEventAnalysis", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "删除事件分析", httpMethod = "GET", response = ApiResponseBody.class, notes = "deleteEventAnalysis", produces = "application/json")   
    @ApiImplicitParams({
        @ApiImplicitParam(name = "eventId", value = "事件分析id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "beginTime", value = "事件分析id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "endTime", value = "事件分析id", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody deleteEventAnalysis(HttpServletRequest request,@RequestParam(value = "eventId", required = true)  String eventId,
            @RequestParam(value = "beginTime", required = true)  String beginTime,
            @RequestParam(value = "endTime", required = true)  String endTime) throws UserNoFoundException, SysException, EventNotExistException  {
        Map<String, Object> paramMap = getUserInfo(request);
        EventAnalysis eventAnalysis=new EventAnalysis();
        eventAnalysis.setUuid(eventId);
        eventAnalysis.setBeginTime(beginTime);
        eventAnalysis.setEndTime(endTime);
        int result = this.eventAnalysisService.updateEventAnalysis(request,eventAnalysis,paramMap);
        return this.returnSuccess(result);
            
    }
    
    /**
     * 推荐事件分析列表
     
     */
    @ResponseBody
    @RequestMapping(value = "/selectRecommendEventAnalysisList", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "推荐事件分析列表", httpMethod = "GET", response = ApiResponseBody.class, notes = "select EventAnalysisInfo", produces = "application/json")
    public ApiResponseBody selectRecommendEventAnalysisList(HttpServletRequest request) { 
        List<EventAnalysis> eventAnalysis = eventAnalysisService.selectRecommendEventAnalysisList();
        return this.returnSuccess(eventAnalysis);
            
    }
    
    /**
     * 往期事件列表
     
     */
    @ResponseBody
    @RequestMapping(value = "/selectDeleteEventAnalysisList", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "往期事件列表", httpMethod = "GET", response = ApiResponseBody.class, notes = "select DeleteEventAnalysisList", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody selectDeleteEventAnalysisList(HttpServletRequest request,@RequestParam(value = "userId", required = true)  String userId) { 
        PageInfo<EventAnalysis> eventAnalysis = eventAnalysisService.selectDeleteEventAnalysisList(userId,PageUtil.getPageInfo(request));
        return this.returnSuccess(eventAnalysis);
            
    }
    
    /**
     * 事件分析数据列表页
     * @throws SysException 
     * @throws UserNoFoundException     
     */
    @ResponseBody
    @RequestMapping(value = "/selectEventAnalysisDataList", method = RequestMethod.POST,consumes = "application/json")
    @ApiOperation(value = "事件分析列表页", httpMethod = "POST", response = ApiResponseBody.class, notes = "select eventAnalysisDataList", produces = "application/json")
    public ApiResponseBody selectEventAnalysisDataList(HttpServletRequest request,@RequestBody ScreeningConditions screeningConditions
            ) throws UserNoFoundException, SysException {
        
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.putAll(ParamsUtil.transToMAP(screeningConditions,ScreeningConditions.class));
        if(screeningConditions.getEventAnalysisSharedUserId()!=null&&!"".equals(screeningConditions.getEventAnalysisSharedUserId())){
            paramMap.put("userId", screeningConditions.getEventAnalysisSharedUserId());
        } 
        int maxId = eventAnalysisDataService.selectMaxId(paramMap);
        paramMap.put("searchId", maxId);
        Pagination pagination = PageUtil.getPageInfo(request);
        pagination.setPageNum(screeningConditions.getPageNum());
        pagination.setPageSize(screeningConditions.getPageSize());
        PageInfo<EventAnalysisData> pageInfo = eventAnalysisDataService.selectEventAnalysisDataList(pagination,paramMap);
        PageInfoUtil pageInfoUtil = new PageInfoUtil(pageInfo,maxId);
        
        ApiResponseBody responseBody = new ApiResponseBody();
        Result result = new Result();
        if (!(pageInfoUtil.getList().isEmpty())) {
            result.setData(pageInfoUtil.getList());
            result.setPageNum(pageInfoUtil.getPageNum());
            result.setPageSize(pageInfoUtil.getPageSize());
            result.setTotal(pageInfoUtil.getTotal());
        }
        result.setMaxId(maxId);
        responseBody.setResult(result);
        responseBody.setStatus(SysConstants._SUCCESS_CODE);
        responseBody.setMsg(SysConstants._SUCCESS_MSG);
        return responseBody;
            
    }
    
    /**
     * 根据媒体类型分组统计条数
     * @throws SysException 
     * @throws UserNoFoundException 
     */
    @ResponseBody
    @RequestMapping(value = "/selectCountGroupBySourceType", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "根据媒体类型分组统计条数", httpMethod = "GET", response = ApiResponseBody.class, notes = "select countGroupBySourceType", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "eventAnalysisSharedUserId", value = "推荐用户id", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "verified", value = "认证类型  1大 V -1群众", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "eventAnalysisId", value = "事件分析id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "customFilter", value = "自定义筛选id", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "beginTime", value = "开始时间", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "sourcetype", value = "媒体来源类型 ", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "infoState", value = "信息状态 1正常 x过滤 ", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "orientation", value = "倾向性 1正 2负 3中", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "wechatInfoType", value = "微博类型 1原贴 2转发 3带回复转发", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "isRead", value = "1：已读 0：未读", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "hotWords", value = "热词", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "isRepeat", value = "是否去重 1不去重 2去重", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "searchId", value = "查询截止id", required = false, paramType = "query", dataType = "String")
    })
    public ApiResponseBody selectCountGroupBySourceType(HttpServletRequest request,@RequestParam(value = "userId", required = true)  String userId,
            @RequestParam(value = "eventAnalysisSharedUserId", required = false)  String eventAnalysisSharedUserId,@RequestParam(value = "verified", required = false)  String verified,
            @RequestParam(value = "eventAnalysisId", required = true)  String eventAnalysisId,@RequestParam(value = "customFilter", required = false)  String customFilter,
            @RequestParam(value = "beginTime", required = false)  String beginTime,@RequestParam(value = "endTime", required = false)  String endTime,
            @RequestParam(value = "infoState", required = false)  String infoState,@RequestParam(value = "orientation", required = false)  String orientation,
            @RequestParam(value = "sourcetype", required = false)  String sourcetype,@RequestParam(value = "isRead", required = false)  String isRead,
            @RequestParam(value = "wechatInfoType", required = false)  String wechatInfoType,@RequestParam(value = "order", required = false)  String order,
            @RequestParam(value = "hotWords", required = false)  String hotWords,
            @RequestParam(value = "isRepeat", required = false)  String isRepeat,
            @RequestParam(value = "searchId", required = false)  String searchId) throws UserNoFoundException, SysException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userId", userId);
        if(eventAnalysisSharedUserId!=null&&!"".equals(eventAnalysisSharedUserId)){
            paramMap.put("userId", eventAnalysisSharedUserId);
        }    
        paramMap.put("eventAnalysisId", eventAnalysisId);
        paramMap.put("customFilter", customFilter);
        paramMap.put("beginTime", beginTime);
        paramMap.put("endTime", endTime);
        paramMap.put("infoState", infoState);
        paramMap.put("sourcetype", sourcetype);
        paramMap.put("orientation", orientation);
        paramMap.put("order", order);
        paramMap.put("isRead", isRead);
        paramMap.put("hotWords", hotWords);
        paramMap.put("isRepeat", isRepeat);
        paramMap.put("searchId", searchId);
        List<Map<String, Object>> result = eventAnalysisDataService.selectCountGroupBySourceType(paramMap);
        if(!StringUtils.isEmpty(sourcetype)
                &&sourcetype.indexOf("04")!=-1
                &&(!StringUtils.isEmpty(verified)||!StringUtils.isEmpty(wechatInfoType))){
            paramMap.put("verified", verified);
            paramMap.put("wechatInfoType", wechatInfoType);
            List<Map<String, Object>> weiboResult = eventAnalysisDataService.selectCountGroupByWeiBo(paramMap);
            if(!CollectionUtils.isEmpty(weiboResult)){
                Map<String, Object> map04 = null;
                Map<String, Object> map08 = null;
                for(Map<String, Object> m:result){
                    if("04".equals(m.get("sourceType"))){
                        map04 = m;
                    }
                    if("08".equals(m.get("sourceType"))){
                        map08 = m;
                    }
                }
                if(map04!=null){
                    result.remove(map04);
                }
                if(map08!=null){
                    result.remove(map08);
                }
                result.addAll(weiboResult);
            }
        }
        return this.returnSuccess(result);
    }
    
    /**
     * 根据倾向性分组统计条数
     * @throws SysException 
     * @throws UserNoFoundException 
     */
    @ResponseBody
    @RequestMapping(value = "/selectCountGroupByOrientation", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "根据倾向性分组统计条数", httpMethod = "GET", response = ApiResponseBody.class, notes = "select CountGroupByOrientation", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "eventAnalysisSharedUserId", value = "推荐用户id", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "verified", value = "认证类型  1大 V -1群众", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "eventAnalysisId", value = "事件分析id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "customFilter", value = "自定义筛选id", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "beginTime", value = "开始时间", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "sourcetype", value = "媒体来源类型 ", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "infoState", value = "信息状态 1正常 x过滤 ", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "orientation", value = "倾向性 1正 2负 3中", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "wechatInfoType", value = "微博类型 1原贴 2转发 3带回复转发", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "isRead", value = "1：已读 0：未读", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "hotWords", value = "热词", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "isRepeat", value = "是否去重 1不去重 2去重", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "searchId", value = "查询截止id", required = false, paramType = "query", dataType = "String")
    })
    public ApiResponseBody selectCountGroupByOrientation(HttpServletRequest request,@RequestParam(value = "userId", required = true)  String userId,
            @RequestParam(value = "eventAnalysisSharedUserId", required = false)  String eventAnalysisSharedUserId,@RequestParam(value = "verified", required = false)  String verified,
            @RequestParam(value = "eventAnalysisId", required = true)  String eventAnalysisId,@RequestParam(value = "customFilter", required = false)  String customFilter,
            @RequestParam(value = "beginTime", required = false)  String beginTime,@RequestParam(value = "endTime", required = false)  String endTime,
            @RequestParam(value = "infoState", required = false)  String infoState,@RequestParam(value = "orientation", required = false)  String orientation,
            @RequestParam(value = "sourcetype", required = false)  String sourcetype,@RequestParam(value = "isRead", required = false)  String isRead,
            @RequestParam(value = "wechatInfoType", required = false)  String wechatInfoType,@RequestParam(value = "order", required = false)  String order,
            @RequestParam(value = "hotWords", required = false)  String hotWords,
            @RequestParam(value = "isRepeat", required = false)  String isRepeat,
            @RequestParam(value = "searchId", required = false)  String searchId) throws UserNoFoundException, SysException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userId", userId);
        if(eventAnalysisSharedUserId!=null&&!"".equals(eventAnalysisSharedUserId)){
            paramMap.put("userId", eventAnalysisSharedUserId);
        }    
        paramMap.put("verified", verified);
        paramMap.put("eventAnalysisId", eventAnalysisId);
        paramMap.put("customFilter", customFilter);
        paramMap.put("beginTime", beginTime);
        paramMap.put("endTime", endTime);
        paramMap.put("infoState", infoState);
        paramMap.put("sourcetype", sourcetype);
        paramMap.put("orientation", orientation);
        paramMap.put("wechatInfoType", wechatInfoType);
        paramMap.put("order", order);
        paramMap.put("isRead", isRead);
        paramMap.put("hotWords", hotWords);
        paramMap.put("isRepeat", isRepeat);
        paramMap.put("searchId", searchId);
        List<Map<String, Object>> result = eventAnalysisDataService.selectCountGroupByOrientation(paramMap);
        return this.returnSuccess(result);
    }
    
    
    /**
     * 根据条件计算去重和不去重的数量
     * @throws SysException 
     * @throws UserNoFoundException 
     */
    @ResponseBody
    @RequestMapping(value = "/selectCountGroupByIsRepeat", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "根据条件计算去重和不去重的数量", httpMethod = "GET", response = ApiResponseBody.class, notes = "select CountGroupByOrientation", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "eventAnalysisSharedUserId", value = "推荐用户id", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "verified", value = "认证类型  1大 V -1群众", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "eventAnalysisId", value = "事件分析id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "customFilter", value = "自定义筛选id", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "beginTime", value = "开始时间", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "sourcetype", value = "媒体来源类型 ", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "infoState", value = "信息状态 1正常 x过滤 ", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "orientation", value = "倾向性 1正 2负 3中", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "wechatInfoType", value = "微博类型 1原贴 2转发 3带回复转发", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "isRead", value = "1：已读 0：未读", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "hotWords", value = "热词", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "isRepeat", value = "是否去重 1不去重 2去重", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "searchId", value = "查询截止id", required = false, paramType = "query", dataType = "String")
    })
    public ApiResponseBody selectCountGroupByIsRepeat(HttpServletRequest request,@RequestParam(value = "userId", required = true)  String userId,
            @RequestParam(value = "eventAnalysisSharedUserId", required = false)  String eventAnalysisSharedUserId,@RequestParam(value = "verified", required = false)  String verified,
            @RequestParam(value = "eventAnalysisId", required = true)  String eventAnalysisId,@RequestParam(value = "customFilter", required = false)  String customFilter,
            @RequestParam(value = "beginTime", required = false)  String beginTime,@RequestParam(value = "endTime", required = false)  String endTime,
            @RequestParam(value = "infoState", required = false)  String infoState,@RequestParam(value = "orientation", required = false)  String orientation,
            @RequestParam(value = "sourcetype", required = false)  String sourcetype,@RequestParam(value = "isRead", required = false)  String isRead,
            @RequestParam(value = "wechatInfoType", required = false)  String wechatInfoType,@RequestParam(value = "order", required = false)  String order,
            @RequestParam(value = "hotWords", required = false)  String hotWords,
            @RequestParam(value = "isRepeat", required = false)  String isRepeat,
            @RequestParam(value = "searchId", required = false)  String searchId) throws UserNoFoundException, SysException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userId", userId);
        if(eventAnalysisSharedUserId!=null&&!"".equals(eventAnalysisSharedUserId)){
            paramMap.put("userId", eventAnalysisSharedUserId);
        }    
        paramMap.put("verified", verified);
        paramMap.put("eventAnalysisId", eventAnalysisId);
        paramMap.put("customFilter", customFilter);
        paramMap.put("beginTime", beginTime);
        paramMap.put("endTime", endTime);
        paramMap.put("infoState", infoState);
        paramMap.put("sourcetype", sourcetype);
        paramMap.put("orientation", orientation);
        paramMap.put("wechatInfoType", wechatInfoType);
        paramMap.put("order", order);
        paramMap.put("isRead", isRead);
        paramMap.put("hotWords", hotWords);
        paramMap.put("isRepeat", isRepeat);
        paramMap.put("searchId", searchId);
        Map<String, Object> result = eventAnalysisDataService.selectCountGroupByIsRepeat(paramMap);
        return this.returnSuccess(result);
    }
    
    
    /**
     * 加关注
     * @throws SysException 
     * @throws UserNoFoundException 
     * @throws ParamsNullException 
     * @throws RepeatOperateException 
     */
    @ResponseBody
    @RequestMapping(value = "/addAttention", method = RequestMethod.POST,consumes = "application/json")
    @ApiOperation(value = "加关注接口", httpMethod = "POST", response = ApiResponseBody.class, notes = "addAttention", produces = "application/json")
    public ApiResponseBody addAttention(HttpServletRequest request, @RequestBody EventAnalysis eventAnalysis) throws UserNoFoundException, SysException, UserExpiredException, NumberException, RepeatOperateException, ParamsNullException  { 
        Map<String, Object> paramMap = getUserInfo(request);
        int result = this.eventAnalysisService.addAttention(eventAnalysis,paramMap);
        return this.returnSuccess(result);
            
    }
    
    /**
     * 批量导出或全部导出
     * topicIds 不为空 为批量导出
     * topicIds 为空   为全部导出
     * @throws SysException 
     * @throws UserNoFoundException 
     */
    @ResponseBody
    @RequestMapping(value = "/exportEventAnalysisDataList", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "事件分析批量导出或全部导出", httpMethod = "GET", response = ApiResponseBody.class, notes = "export eventAnalysisDataList", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "infoIds", value = "事件信息id串,批量导出", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "eventAnalysisSharedUserId", value = "推荐用户id", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "verified", value = "认证类型  1大 V -1群众", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "eventAnalysisId", value = "事件分析id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "customFilter", value = "自定义筛选id", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "beginTime", value = "开始时间", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "sourcetype", value = "媒体来源类型 ", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "infoState", value = "信息状态 1正常 x过滤 ", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "orientation", value = "倾向性 1正 2负 3中", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "wechatInfoType", value = "微博类型 1原贴 2转发 3带回复转发", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "order", value = "排序", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "isRead", value = "1：已读 0：未读", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "isRepeat", value = "是否去重 1不去重 2去重", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "hotWords", value = "热词", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "String")
    })
    public ApiResponseBody exportEventAnalysisDataList(HttpServletRequest request,HttpServletResponse response,HttpSession session,
            @RequestParam(value = "infoIds", required = false)  String infoIds,
            @RequestParam(value = "userId", required = true)  String userId,
            @RequestParam(value = "eventAnalysisSharedUserId", required = false)String eventAnalysisSharedUserId,
            @RequestParam(value = "verified", required = false)  String verified,
            @RequestParam(value = "eventAnalysisId", required = true)  String eventAnalysisId,
            @RequestParam(value = "customFilter", required = false)  String customFilter,
            @RequestParam(value = "beginTime", required = false)  String beginTime,
            @RequestParam(value = "endTime", required = false)  String endTime,
            @RequestParam(value = "sourcetype", required = false)  String sourcetype,
            @RequestParam(value = "infoState", required = false)  String infoState,
            @RequestParam(value = "orientation", required = false)  String orientation,
            @RequestParam(value = "wechatInfoType", required = false)  String wechatInfoType,
            @RequestParam(value = "order", required = false)  String order,
            @RequestParam(value = "isRead", required = false)  String isRead,
            @RequestParam(value = "isRepeat", required = false)  String isRepeat,
            @RequestParam(value = "hotWords", required = false)  String hotWords,
            @RequestParam(value = "pageNum", required = false)  String pageNum,
            @RequestParam(value = "pageSize", required = false)  String pageSize) throws UserNoFoundException, SysException {
       
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userId", userId);
        if(eventAnalysisSharedUserId!=null&&!"".equals(eventAnalysisSharedUserId)){
            paramMap.put("userId", eventAnalysisSharedUserId);
        }     
        paramMap.put("eventAnalysisId", eventAnalysisId);        
        if(!StringUtils.isEmpty(infoIds)){
            paramMap.put("infoIds", infoIds.split(","));
        }else {
            paramMap.put("verified", verified);
            paramMap.put("customFilter", customFilter);
            paramMap.put("beginTime", beginTime);
            paramMap.put("endTime", endTime);
            paramMap.put("infoState", infoState);
            paramMap.put("sourcetype", sourcetype);
            paramMap.put("orientation", orientation);
            paramMap.put("wechatInfoType", wechatInfoType);
            paramMap.put("order", order);
            paramMap.put("isRead", isRead);
            paramMap.put("hotWords", hotWords);
            paramMap.put("isRepeat", isRepeat);
        }
        
        Pagination pagination = new Pagination(0, 20000);
        PageInfo<EventAnalysisData> pageInfo = eventAnalysisDataService.selectEventAnalysisDataList(pagination,paramMap);
        
        List<EventAnalysisData> list = pageInfo.getList();        
        String realPath=session.getServletContext().getRealPath("/");
        realPath = realPath.substring(0, realPath.lastIndexOf("webapps")+8);
               
        String templateDir = PropertiesUtil.getValue("export.template.path");
        String templateFile = "/eventTemplate.xls";
        String templateFilePath = realPath+templateDir+templateFile;
        String filename = UUID.randomUUID()+".xls";        
        List<EventAnalysis> eventAnalysisList = eventAnalysisService.selectEventAnalysisInfo(eventAnalysisId);
        EventAnalysis eventAnalysis =null;
        if(!CollectionUtils.isEmpty(eventAnalysisList)){
            eventAnalysis = eventAnalysisList.get(0);
        }
        try {            
            Context context = new Context();
            context.putVar("dataList", list);
            context.putVar("index", 0);
            context.putVar("eventAnalysis", eventAnalysis);
            FileInputStream is = new FileInputStream(templateFilePath);
            OutputStream os = UploadFileUtil.getInstance().getOut(filename);
//            JxlsHelper.getInstance().processTemplate(is, os, context);
            JxlsHelper jxlsHelper = JxlsHelper.getInstance();
            Transformer transformer  = jxlsHelper.createTransformer(is, os);
            JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator)transformer.getTransformationConfig().getExpressionEvaluator();
            Map<String, Object> funcs = new HashMap<String, Object>();
            funcs.put("utils", new JxlsUtils());    //添加自定义功能
            evaluator.getJexlEngine().setFunctions(funcs);
            jxlsHelper.processTemplate(context, transformer);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String remotePath = "http://" + PropertiesUtil.getValue("file.server.host") + "/" + filename;
        JSONObject json = new JSONObject();
        json.put("path", remotePath);
        return this.returnSuccess(json);
    }
    
    /**
     * 修改倾向性
     * @throws SysException 
     * @throws UserNoFoundException
     * @throws ParamsNullException 
     */
    @ResponseBody
    @RequestMapping(value = "/updateOrientation", method = RequestMethod.POST,consumes = "application/json")
    @ApiOperation(value = "修改倾向性", httpMethod = "POST", response = ApiResponseBody.class, notes = "updateOrientation", produces = "application/json")
    public ApiResponseBody updateOrientation(HttpServletRequest request, @RequestBody EventAnalysis eventAnalysis) throws UserNoFoundException, SysException  { 
        Map<String, Object> paramMap = getUserInfo(request);
        this.eventAnalysisService.updateOrientation(paramMap,eventAnalysis);
        return this.returnSuccess(null);
            
    }
    
    /**
     * 标已读
     * @throws SysException 
     * @throws UserNoFoundException
     * @throws ParamsNullException 
     */
    @ResponseBody
    @RequestMapping(value = "/updateRead", method = RequestMethod.POST,consumes = "application/json")
    @ApiOperation(value = "标已读", httpMethod = "POST", response = ApiResponseBody.class, notes = "updateRead", produces = "application/json")
    public ApiResponseBody updateRead(HttpServletRequest request, @RequestBody EventAnalysis eventAnalysis) throws UserNoFoundException, SysException  { 
        Map<String, Object> paramMap = getUserInfo(request);        
        this.eventAnalysisService.updateRead(eventAnalysis,paramMap);
        return this.returnSuccess(null);
            
    }
    
    
    
    
    /**
     * 删除信息
     * @throws SysException 
     * @throws UserNoFoundException
     * @throws ParamsNullException 
     */
    @ResponseBody
    @RequestMapping(value = "/deleteEventAnalysisData", method = RequestMethod.POST,consumes = "application/json")
    @ApiOperation(value = "删除信息", httpMethod = "POST", response = ApiResponseBody.class, notes = "deleteEventAnalysisData", produces = "application/json")
    public ApiResponseBody deleteEventAnalysisData(HttpServletRequest request, @RequestBody EventAnalysis eventAnalysis) throws UserNoFoundException, SysException, ParamsNullException  { 
        Map<String, Object> paramMap = getUserInfo(request);        
        int result = this.eventAnalysisService.deleteEventAnalysisData(eventAnalysis,paramMap);
        return this.returnSuccess(result);
            
    }
    
    /**
     * 加简报
     * @throws SysException 
     * @throws UserNoFoundException 
     * @throws ParamsNullException 
     * @throws RepeatOperateException 
     */
    @ResponseBody
    @RequestMapping(value = "/addBriefing", method = RequestMethod.POST,consumes = "application/json")
    @ApiOperation(value = "加简报接口", httpMethod = "POST", response = ApiResponseBody.class, notes = "addBriefing", produces = "application/json")
    public ApiResponseBody addBriefing(HttpServletRequest request,@RequestBody EventAnalysis eventAnalysis) throws UserNoFoundException, SysException, UserExpiredException, NumberException, RepeatOperateException, ParamsNullException  { 
        Map<String, Object> paramMap = getUserInfo(request);
        int result = this.eventAnalysisService.addBriefing(eventAnalysis,paramMap);
        return this.returnSuccess(result);           
    }
    
    /**
     * 删除往期事件分析
     * @throws SysException 
     * @throws UserNoFoundException 
     * @throws NumberException 
     * @throws UserExpiredException 
     */
    @ResponseBody
    @RequestMapping(value = "/deletePastEventAnalysis", method = RequestMethod.POST,consumes = "application/json")
    @ApiOperation(value = "删除往期事件分析", httpMethod = "POST", response = ApiResponseBody.class, notes = "deletePastEventAnalysis", produces = "application/json")   
    public ApiResponseBody deletePastEventAnalysis(HttpServletRequest request,@RequestBody EventAnalysis eventAnalysis) throws UserNoFoundException, SysException   {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.putAll(ParamsUtil.transToMAP(eventAnalysis,EventAnalysis.class));
        this.eventAnalysisService.deletePastEventAnalysis(paramMap);
        return this.returnSuccess(null);
            
    }
    
    
    /**
     * 事件分析信息详情
     * @throws SysException 
     * @throws UserNoFoundException 
     
     */
    @ResponseBody
    @RequestMapping(value = "/selectEventAnalysisDataDetail", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "事件分析信息详情", httpMethod = "GET", response = ApiResponseBody.class, notes = "selectEventAnalysisDataDetail", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "eventAnalysisId", value = "事件分析id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "infoId", value = "信息id", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody selectEventAnalysisDataDetail(HttpServletRequest request,@RequestParam(value = "eventAnalysisId", required = true)  String eventAnalysisId,
            @RequestParam(value = "infoId", required = true)  String infoId) throws UserNoFoundException, SysException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("eventAnalysisId", eventAnalysisId);
        paramMap.put("infoId", infoId);
        EventAnalysisData eventAnalysisData = eventAnalysisDataService.selectEventAnalysisDataDetail(paramMap);
        return this.returnSuccess(eventAnalysisData);
            
    }

    /**
     * 添加或更改用户用的是事件分析或话题挖掘
     * @throws SysException 
     * @throws UserNoFoundException 
     */
    @ResponseBody
    @RequestMapping(value = "/updateEventAnalysisStatus", method = RequestMethod.POST,consumes = "application/json")
    @ApiOperation(value = "添加或更改用户用的是事件分析或话题挖掘", httpMethod = "POST", response = ApiResponseBody.class, notes = "updateEventAnalysisStatus", produces = "application/json")   
    public ApiResponseBody updateEventAnalysisStatus(HttpServletRequest request,@RequestBody EventAnalysis eventAnalysis) throws UserNoFoundException, SysException   {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("status", eventAnalysis.getStatus());
        int result=this.eventAnalysisService.updateEventAnalysisStatus(paramMap);
        return this.returnSuccess(result);
            
    }
    
    /**
     * 获取事件分析状态
     * @throws SysException 
     * @throws UserNoFoundException 
     
     */
    @ResponseBody
    @RequestMapping(value = "/selectEventAnalysisStatus", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "获取事件分析状态", httpMethod = "GET", response = ApiResponseBody.class, notes = "selectEventAnalysisStatus", produces = "application/json")
    public ApiResponseBody selectEventAnalysisStatus(HttpServletRequest request) throws UserNoFoundException, SysException {
        Map<String, Object> paramMap = getUserInfo(request);
        Map<String,Object> eventAnalysisStatus = eventAnalysisService.selectEventAnalysisStatus(paramMap);
        return this.returnSuccess(eventAnalysisStatus);
            
    }
    
    /**
     * 相同信息列表   
     */
    @ResponseBody
    @RequestMapping(value = "/selectSameEventAnalysisDataList", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "相同信息列表", httpMethod = "GET", response = ApiResponseBody.class, notes = "select eventAnalysisList", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "eventAnalysisId", value = "事件id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "eventAnalysisInfoId", value = "信息id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "simhash", value = "信息simhash", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody selectSameEventAnalysisDataList(HttpServletRequest request,
            @RequestParam(value = "eventAnalysisId", required = true)  String eventAnalysisId,
            @RequestParam(value = "eventAnalysisInfoId", required = true)  String eventAnalysisInfoId,
            @RequestParam(value = "simhash", required = true)  String simhash) throws SysException, UserNoFoundException { 
        Map<String,Object> params = getUserInfo(request);
        params.put("eventAnalysisId", eventAnalysisId);
        params.put("simhash", simhash);
        params.put("eventAnalysisInfoId", eventAnalysisInfoId);
        Map<String,Object> eventAnalysis = eventAnalysisDataService.selectSameEventAnalysisDataList(params);
        return this.returnSuccess(eventAnalysis);
            
    }
    
    /**
     * 往期事件分析快照   
     */
    @ResponseBody
    @RequestMapping(value = "/getDeleteEventAnalysisSnapshot", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "往期事件分析快照 ", httpMethod = "GET", response = ApiResponseBody.class, notes = "get DeleteEventAnalysis Snapshot", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "eventAnalysisId", value = "事件id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "beginTime", value = "事件开始时间", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "endTime", value = "事件结束时间", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "shareUserId", value = "推荐事件用户ID", required = false, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getDeleteEventAnalysisSnapshot(HttpServletRequest request,
            @RequestParam(value = "eventId", required = true)  String eventId, 
            @RequestParam(value = "beginTime", required = true) String beginTime,
            @RequestParam(value = "endTime", required = true) String endTime) throws SysException, UserNoFoundException { 
        Map<String,Object> paramMap = getUserInfo(request);
        paramMap.put("eventId", eventId);
        paramMap.put("beginTime", beginTime);
        paramMap.put("endTime", endTime);
        String eventAnalysis = eventAnalysisDataService.createAppEventSnapshot(request.getSession().getServletContext(),paramMap);
        return this.returnSuccess(eventAnalysis);
            
    }
    
    
    /**
     * 事件分析快照   
     */
    @ResponseBody
    @RequestMapping(value = "/getEventAnalysisSnapshot", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "获取事件分析快照 ", httpMethod = "GET", response = ApiResponseBody.class, notes = "get EventAnalysis Snapshot", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "eventAnalysisId", value = "事件id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "beginTime", value = "事件开始时间", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "endTime", value = "事件结束时间", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "shareUserId", value = "推荐事件用户ID", required = false, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getEventAnalysisSnapshot(HttpServletRequest request,
            @RequestParam(value = "eventId", required = true)  String eventId, 
            @RequestParam(value = "beginTime", required = true) String beginTime,
            @RequestParam(value = "endTime", required = true) String endTime) throws SysException, UserNoFoundException { 
        Map<String,Object> paramMap = getUserInfo(request);
        paramMap.put("eventId", eventId);
        paramMap.put("beginTime", beginTime);
        paramMap.put("endTime", endTime);
        String eventAnalysis = "http://" + PropertiesUtil.getValue("file.server.domain") + "/appeventIdpic/html/" +eventAnalysisDataService.createAppEventSnapshot(request.getSession().getServletContext(),paramMap);
        return this.returnSuccess(eventAnalysis);
            
    }
    
    /**
     * 导出相同信息
     * @throws SysException 
     * @throws UserNoFoundException 
     */
    @ResponseBody
    @RequestMapping(value = "/exportSameEventAnalysisDataList", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "事件分析批量导出或全部导出", httpMethod = "GET", response = ApiResponseBody.class, notes = "export SameEventAnalysisDataList", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "eventAnalysisId", value = "事件id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "eventAnalysisInfoId", value = "信息id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "simhash", value = "信息simhash", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody exportSameEventAnalysisDataList(HttpServletRequest request,HttpServletResponse response,HttpSession session,
            @RequestParam(value = "eventAnalysisId", required = true)  String eventAnalysisId,
            @RequestParam(value = "eventAnalysisInfoId", required = true)  String eventAnalysisInfoId,
            @RequestParam(value = "simhash", required = true)  String simhash) throws UserNoFoundException, SysException {
       
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("eventAnalysisId", eventAnalysisId);
        paramMap.put("eventAnalysisInfoId", eventAnalysisInfoId);
        paramMap.put("simhash", simhash);

        Pagination pagination = new Pagination(0, 20000);
        PageInfo<EventAnalysisData> pageInfo = eventAnalysisDataService.selectSameExportDataList(pagination,paramMap);
        
        List<EventAnalysisData> list = pageInfo.getList();        
        String realPath=session.getServletContext().getRealPath("/");
        realPath = realPath.substring(0, realPath.lastIndexOf("webapps")+8);
               
        String templateDir = PropertiesUtil.getValue("export.template.path");
        String templateFile = "/eventTemplate.xls";
        String templateFilePath = realPath+templateDir+templateFile;
        String filename = UUID.randomUUID()+".xls";        
        List<EventAnalysis> eventAnalysisList = eventAnalysisService.selectEventAnalysisInfo(eventAnalysisId);
        EventAnalysis eventAnalysis =null;
        if(!CollectionUtils.isEmpty(eventAnalysisList)){
            eventAnalysis = eventAnalysisList.get(0);
        }
        try {            
            Context context = new Context();
            context.putVar("dataList", list);
            context.putVar("index", 0);
            context.putVar("eventAnalysis", eventAnalysis);
            FileInputStream is = new FileInputStream(templateFilePath);
            OutputStream os = UploadFileUtil.getInstance().getOut(filename);
            JxlsHelper jxlsHelper = JxlsHelper.getInstance();
            Transformer transformer  = jxlsHelper.createTransformer(is, os);
            JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator)transformer.getTransformationConfig().getExpressionEvaluator();
            Map<String, Object> funcs = new HashMap<String, Object>();
            funcs.put("utils", new JxlsUtils());    //添加自定义功能
            evaluator.getJexlEngine().setFunctions(funcs);
            jxlsHelper.processTemplate(context, transformer);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String remotePath = "http://" + PropertiesUtil.getValue("file.server.host") + "/" + filename;
        JSONObject json = new JSONObject();
        json.put("path", remotePath);
        return this.returnSuccess(json);
    }
    
    /**
     * 高级表达式解析
     * @throws ParseExpressionException 
     */
    @ResponseBody
    @RequestMapping(value = "/parseExpression", method = {RequestMethod.GET,RequestMethod.POST},consumes = "application/json")
    @ApiOperation(value = "高级表达式解析", httpMethod = "GET", response = ApiResponseBody.class, notes = "parseExpression", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "expression", value = "高级表达式", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody parseExpression(HttpServletRequest request,@RequestParam(value = "expression", required = true)  String expression) throws ParseExpressionException { 
        JSONArray result = null;
        try{
            result = LogicExpressionUtil.parseExpression(expression);
        }catch(ExpressionException e){
            throw new ParseExpressionException(e.getMessage());
        }
        return this.returnSuccess(result);
            
    }
}
