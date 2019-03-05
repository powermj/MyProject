package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.yqzj.entities.v1.EventAnalysis;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.dao
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户模块持久层接口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.dao.v1.UserDao.java
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
public interface EventAnalysisDao extends BaseDao<EventAnalysis> {

    public List<EventAnalysis> selectEventAnalysisList(Map<String, Object> paramMap);

    public int selectEventAnalysisCount_self(Map<String, Object> paramMap);

    public int saveAddTopic(Map<String, Object> paramMap);

    public int createTopicTable_self(Map<String, Object> paramMap);

    public int saveAddTopickeyword(Map<String, Object> paramMap);

    public Map<String, Object> selectUserStatusAndEventAnalysisCount(String userId);

    public int selectEventAnalysisCount(String userId);

    public int createTopicCommontTable_self(Map<String, Object> paramMap);

    public int createTopicWeiboAuthorTable_self(Map<String, Object> paramMap);

    public List<EventAnalysis> selectEventAnalysisInfo(String eventAnalysisId);
    
    public EventAnalysis selectEventAnalysisById(String eventAnalysisId);

    public int updateEventAnalysis(EventAnalysis eventAnalysis);

    public int deleteEventAnalysisWords(EventAnalysis eventAnalysis);

    public int deleteEventAnalysis(Map<String, Object> paramMap);

    public int deleteEventAnalysisWords(Map<String, Object> paramMap);

    public int dropEventAnalysis_self(Map<String, Object> paramMap);

    public List<EventAnalysis> selectRecommendEventAnalysisList();

    public PageInfo<EventAnalysis> selectDeleteEventAnalysisList(Map<String, Object> paramMap, Pagination pageInfo);

    public int insertAttentionSelective_self(Map<String, Object> insertYqjbParams);

    public int insertAttentionCntSelective_self(Map<String, Object> data);

    public int updateOrientation_self(Map<String, Object> paramMap);
    
    public int updateOrientations_self(Map<String, Object> paramMap);

    public int updateRead_self(Map<String, Object> paramMap);

    public int deleteEventAnalysisData_self(Map<String, Object> paramMap);

    public List<Map<String, Object>> selectEventAnalysisList_other(Map<String, Object> paramMap);

    public List<Map<String, Object>> selectAttentionList_self(Map<String, Object> paramMap);

    public int updateattention_self(Map<String, Object> paramMap);

    public int insertBriefingSelective_self(Map<String, Object> paramMap);

    public int insertBriefingCntSelective_self(Map<String, Object> paramMap);

    public int updateBriefing_self(Map<String, Object> paramMap);

    public int deletePastEventAnalysis(Map<String, Object> paramMap);

    public int updateEventAnalysisData_self(Map<String, Object> paramMap);

    public int deleteAttentionList_self(Map<String, Object> paramMap);

    public List<Map<String, Object>> selectBriefingList_self(Map<String, Object> paramMap);

    public Map<String, Object> selectEventAnalysisStatus(Map<String, Object> paramMap);

    public int addEventAnalysisStatus(Map<String, Object> paramMap);

    public int updateEventAnalysisStatus(Map<String, Object> paramMap);

    public int createTopicRegionTableTable_self(Map<String, Object> paramMap);

    public Map<String, Object> selectUserEventAnalysisStatus(Map<String, Object> paramMap);
    
    List<String> getSimhashList_self(Map<String,Object> paramMap);
    
    public Map<String, Object> selectUserEventAnalysisNum(String userId);
    
    public Map<String,Object> selectTopicSourceTypeNum_self(Map<String,Object> paramMap);
    
}
