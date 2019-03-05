package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.yqzj.entities.v1.EventAnalysisData;
import com.zhxg.yqzj.entities.v1.TopicWeiBoAuthor;
import com.zhxg.yqzj.entities.v1.WeiboEntity;

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
public interface EventAnalysisDataDao extends BaseDao<EventAnalysisData> {

    public Map<String, Object> getCount_other(Map<String, Object> paramMap);
    
    public Map<String,Object> getSummary_other(Map<String, Object> paramMap);
    
    public List<EventAnalysisData> getFirstMediaInfo_other(Map<String, Object> paramMap);
    
    public List<EventAnalysisData> getEventVein_other(Map<String, Object> paramMap);

    public List<EventAnalysisData> getFirstInfo_other(Map<String, Object> paramMap);

    public int modifyModuleSummary(Map<String, Object> paramMap);

    public List<Map<String, Object>> getSourceTypeTrend_other(Map<String, Object> paramMap);
    
    public int selectMaxId_self(Map<String, Object> paramMap);

    public PageInfo<EventAnalysisData> selectEventAnalysisDataList_self(Map<String, Object> paramMap, Pagination pageInfo);

    public Map<String, Object> selectCustomCondition(Map<String, Object> paramMap);

    public List<Map<String, Object>> selectCustomConditionValue(Map<String, Object> paramMap);

    public List<Map<String, Object>> getOrientationStatistics_other(Map<String, Object> paramMap);

    public List<Map<String, Object>> getMediaAnalysis_other(Map<String, Object> paramMap);

    public List<Map<String, Object>> getWebNameAnalysis_other(Map<String, Object> paramMap);

    public List<Map<String, Object>> getWordsCloud_other(Map<String, Object> paramMap);
    
    public PageInfo<EventAnalysisData> selectExportDataList_other(Map<String, Object> paramMap, Pagination pageInfo);

    public Map<String, Object> getSummaryByModuleId(Map<String, Object> paramMap);

    public Map<String, Object> getTrendSummary_other(Map<String, Object> paramMap);

    public List<Map<String, Object>> getOrientationSourceType_other(Map<String, Object> paramMap);

    public Map<String, Object> getMediaAnalysisSummary_other(Map<String, Object> paramMap);

    public EventAnalysisData selectEventAnalysisDataDetail_self(Map<String, Object> paramMap);

    public Map<String, Object> getOrientationSummary_other(Map<String, Object> paramMap);
    
    public List<Map<String, Object>> selectCountGroupBySourceType_self(Map<String, Object> paramMap);
    
    public List<Map<String, Object>> selectCountGroupByWeiBo_self(Map<String, Object> paramMap);
    
    public List<Map<String, Object>> selectCountGroupByOrientation_self(Map<String, Object> paramMap);

	public List<TopicWeiBoAuthor> getNavyAnalysisCount_other(Map<String, Object> paramMap);

	public List<TopicWeiBoAuthor> getEmotionAnalysisCount_other(Map<String, Object> paramMap);

	public List<TopicWeiBoAuthor> getInfluenceRankingList_other(Map<String, Object> paramMap);

	public TopicWeiBoAuthor getMicroBlogAnalysisTitle_other(Map<String, Object> paramMap);

	public List<TopicWeiBoAuthor> getTableDataCount_other(Map<String, Object> paramMap);

	public List<TopicWeiBoAuthor> getKeyPointMicroBlogList_other(Map<String, Object> paramMap);

    public List<Map<String, Object>> exportWebName_other(Map<String, Object> paramMap);
    
    public List<EventAnalysisData> selectSameEventAnalysisDataList_self(Map<String, Object> paramMap);
    
    public PageInfo<EventAnalysisData> selectSameExportDataList_self(Map<String, Object> paramMap, Pagination pageInfo);

	public List<TopicWeiBoAuthor> getBloggerRegionList_other(Map<String, Object> paramMap);

    public Map<String, Integer> selectEventAnalysisrepeatCount_self(Map<String, Object> paramMap);

    public EventAnalysisData selectFirstWebName_self(Map<String, Object> paramMap);

    public List<WeiboEntity> getPropagatePath_other(Map<String, Object> paramMap);
    
    public Map<String, String> getTopicName(Map<String, Object> paramMap);

	public List<Map<String, Object>> getNetizenViewpoint_info(String eventId);

    public Map<String, Long> getWeiboAuthorMap_other(Map<String, Object> paramMap);
    
    public Map<String, Object> selectCountGroupByIsRepeat_self(Map<String, Object> paramMap);

    public List<Map<String, Object>> selectEventAnalysisRegionName(Map<String, Object> paramMap);

	public TopicWeiBoAuthor getEvenIdName(String eventId);

    public Map<String, Integer> selectEventAnalysisrepeatCount_other(Map<String, Object> paramMap);

    public List<EventAnalysisData> getAuthorArticles_other(Map<String, Object> paramMap);

    public EventAnalysisData selectEventAnalysisDataDetail_other(Map<String, Object> paramMap);

    public List<Map<String, Object>> getSourceTypeTrendByInterval_other(Map<String, Object> paramMap);

    public List<Map<String, Object>> getWeiboOrientationStatisticsByInterval_other(Map<String, Object> paramMap);

}
