package com.zhxg.yqzj.service.v1;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.exception.ParamsNullException;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.EventAnalysis;
import com.zhxg.yqzj.service.exception.eventanalysis.EventNotExistException;
import com.zhxg.yqzj.service.exception.eventanalysis.NumberException;
import com.zhxg.yqzj.service.exception.eventanalysis.ParseExpressionException;
import com.zhxg.yqzj.service.exception.myfocus.RepeatOperateException;
import com.zhxg.yqzj.service.exception.user.UserExpiredException;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.service
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户模块服务层接口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.service.v1.UserService.java
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
public interface EventAnalysisService extends BaseService<EventAnalysis> {

    public List<EventAnalysis> selectEventAnalysisList(Map<String, Object> params);

    public int addEventAnalysis(EventAnalysis eventAnalysis,
            Map<String, Object> paramMap) throws UserNoFoundException, UserExpiredException, NumberException, ParseExpressionException;

    public List<EventAnalysis> selectEventAnalysisInfo(String eventAnalysisId);

    public int updateEventAnalysis(EventAnalysis eventAnalysis, Map<String, Object> paramMap) throws UserNoFoundException, UserExpiredException, NumberException, EventNotExistException, ParseExpressionException;

    public int updateEventAnalysis(HttpServletRequest request,EventAnalysis eventAnalysis, Map<String, Object> paramMap) throws EventNotExistException;

    public List<EventAnalysis> selectRecommendEventAnalysisList();

    public int selectEventAnalysisCount(String userId, String eventAnalysisId, Map<String, Object> paramsMap);

    public PageInfo<EventAnalysis> selectDeleteEventAnalysisList(String userId, Pagination pageInfo);

    public int addAttention(EventAnalysis eventAnalysis, Map<String, Object> paramMap) throws ParamsNullException, RepeatOperateException, UserNoFoundException, SysException;

    public int updateOrientation(Map<String, Object> paramMap, EventAnalysis eventAnalysis);

    public int updateRead(EventAnalysis eventAnalysis, Map<String, Object> paramMap);

    public int deleteEventAnalysisData(EventAnalysis eventAnalysis, Map<String, Object> paramMap);

    public int addBriefing(EventAnalysis eventAnalysis, Map<String, Object> paramMap) throws RepeatOperateException, ParamsNullException;

    public int updateEventAnalysisStatus(Map<String, Object> paramMap);

    public Map<String, Object> selectEventAnalysisStatus(Map<String, Object> paramMap);

    public int deletePastEventAnalysis(Map<String, Object> paramMap);

    public int updateOrientations(Map<String, Object> paramMap);
    
    public Map<String, Object> selectUserEventAnalysisCount(String userId);

    public String compareEvent(EventAnalysis eventAnalysis, String dbhost);

}
