package com.zhxg.yqzj.service.v1;


import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.EventAnalysisData;
import com.zhxg.yqzj.entities.v1.TopicWeiBoAuthor;

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
public interface EventAnalysisDataService extends BaseService<EventAnalysisData> {
    
    public Map<String,Object> getCount(Map<String, Object> paramMap);
    
    public Map<String,Object> getSummary(Map<String, Object> paramMap);
    
    public List<EventAnalysisData> getFirstMediaInfo(Map<String, Object> paramMap);
    
    public List<EventAnalysisData> getEventVein(Map<String, Object> paramMap);

    public int modifyModuleSummary(Map<String, Object> paramMap);

    public List<Map<String, Object>> getSourceTypeTrend(Map<String, Object> paramMap);
    
    public int selectMaxId(Map<String, Object> paramMap);

    public PageInfo<EventAnalysisData> selectEventAnalysisDataList(Pagination pageInfo, Map<String, Object> paramMap);
    
    public List<Map<String, Object>> selectCountGroupBySourceType(Map<String, Object> paramMap);
    
    public List<Map<String, Object>> selectCountGroupByWeiBo(Map<String, Object> paramMap);
    
    public List<Map<String, Object>> selectCountGroupByOrientation(Map<String, Object> paramMap);
    
    public Map<String, Object> selectCountGroupByIsRepeat(Map<String, Object> paramMap);

    public List<Map<String, Object>> getOrientationStatistics(Map<String, Object> paramMap);

    public List<Map<String, Object>> getMediaAnalysis(Map<String, Object> paramMap);

    public List<Map<String, Object>> getWebNameAnalysis(Map<String, Object> paramMap);

    public List<Map<String, Object>> getWordsCloud(Map<String, Object> paramMap);
    
    public PageInfo<EventAnalysisData> selectExportDataList(Pagination pageInfo, Map<String, Object> paramMap);

    public Map<String, Object> getSummaryByModuleId(Map<String, Object> paramMap);

    public EventAnalysisData selectEventAnalysisDataDetail(Map<String, Object> paramMap);

	public List<TopicWeiBoAuthor> getNavyAnalysisCount(Map<String, Object> paramMap);

	public List<TopicWeiBoAuthor> getEmotionAnalysisCount(Map<String, Object> paramMap);

	public Map<String, Object> getInfluenceRankingList(Map<String, Object> paramMap);

	public Map<String, Object> getMicroBlogAnalysisCount(Map<String, Object> paramMap);

	public Map<String, Object> getKeyPointMicroBlogList(Map<String, Object> paramMap);

    public Map<String, Object> exportWebName(Map<String, Object> paramMap);
    /**
     * 查询相同信息
     * @param pageInfo
     * @param paramMap
     * @return
     */
    public Map<String,Object> selectSameEventAnalysisDataList(Map<String, Object> paramMap);
    
    public PageInfo<EventAnalysisData> selectSameExportDataList(Pagination pageInfo,Map<String, Object> paramMap);

	public List<TopicWeiBoAuthor> getBloggerRegionList(Map<String, Object> paramMap);

    public Map<String, Object> getPropagatePath(Map<String, Object> paramMap) throws SysException;

	public List<Map<String, Object>> getNetizenViewpoint(String eventId);

    public String getDeleteEventAnalysisSnapshot(Map<String, Object> paramMap) throws SysException;

    public List<Map<String, Object>> selectEventAnalysisRegionName(Map<String, Object> paramMap);

    public String createAppEventSnapshot(ServletContext servletContext,Map<String, Object> paramMap) throws SysException;

    public List<EventAnalysisData> getAuthorArticles(Map<String, Object> paramMap);

    public Map<String, Object> getSourceTypeTrendByInterval(Map<String, Object> paramMap);

    public Map<String, Object> getWeiboOrientationStatisticsByInterval(Map<String, Object> paramMap);

}
