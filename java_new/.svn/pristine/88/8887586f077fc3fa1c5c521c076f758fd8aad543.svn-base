package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.yqzj.dao.v1.EventAnalysisDataDao;
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
 * Comments: 用户模块持久层接口实现
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.dao.impl.v1.UserDaoImpl.java
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
@Repository
public class EventAnalysisDataDaoImpl extends BaseDaoImpl<EventAnalysisData> implements EventAnalysisDataDao {

	private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.EventAnalysisData.";
	private static final String NAME_SPACE_AUTHOR = "com.zhxg.yqzj.entities.v1.TopicWeiBoAuthor.";

	@Override
	public Map<String, Object> getCount_other(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getCount", params);
	}

	@Override
	public Map<String, Object> getSummary_other(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getSummary", paramMap);
	}

	@Override
	public List<EventAnalysisData> getFirstMediaInfo_other(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "getFirstMediaInfo", paramMap);
	}

	@Override
	public List<EventAnalysisData> getEventVein_other(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "getEventVein", paramMap);
	}

	@Override
	public List<EventAnalysisData> getFirstInfo_other(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "getFirstInfo", paramMap);
	}

	@Override
	public int modifyModuleSummary(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.insert(NAME_SPACE + "saveOrUpdateModuleSummary", paramMap);
	}

	@Override
	public List<Map<String, Object>> getSourceTypeTrend_other(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "getSourceTypeTrend", paramMap);
	}
	
	@Override
    public List<Map<String, Object>> getSourceTypeTrendByInterval_other(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getSourceTypeTrendByInterval", paramMap);
    }
	
	@Override
    public List<Map<String, Object>> getWeiboOrientationStatisticsByInterval_other(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getWeiboOrientationStatisticsByInterval", paramMap);
    }
	
	@Override
	public int selectMaxId_self(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE + "selectMaxId", paramMap);
	}

	@Override
	public PageInfo<EventAnalysisData> selectEventAnalysisDataList_self(Map<String, Object> paramMap,
			Pagination pageInfo) {
		return this.getPageList(NAME_SPACE + "selectEventAnalysisDataList", pageInfo, paramMap);
	}

	@Override
	public Map<String, Object> selectCustomCondition(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE + "selectCustomCondition", paramMap);
	}

	@Override
	public List<Map<String, Object>> selectCustomConditionValue(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "selectCustomConditionValue", paramMap);
	}

	@Override
	public List<Map<String, Object>> getOrientationStatistics_other(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "getOrientationStatistics", paramMap);
	}

	@Override
	public List<Map<String, Object>> getMediaAnalysis_other(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "getMediaAnalysis", paramMap);
	}

	@Override
	public List<Map<String, Object>> getWebNameAnalysis_other(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "getWebNameAnalysis", paramMap);
	}

	@Override
	public List<Map<String, Object>> exportWebName_other(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "exportWebName", paramMap);
	}

	@Override
	public List<Map<String, Object>> getWordsCloud_other(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "getWordsCloud", paramMap);
	}

	@Override
	public PageInfo<EventAnalysisData> selectExportDataList_other(Map<String, Object> paramMap, Pagination pageInfo) {
		return this.getPageList(NAME_SPACE + "selectExportDataList", pageInfo, paramMap);
	}

	@Override
	public Map<String, Object> getOrientationSummary_other(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getOrientationSummary", paramMap);
	}

	@Override
	public List<Map<String, Object>> selectCountGroupBySourceType_self(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "selectCountGroupBySourceType", paramMap);
	}

	@Override
	public List<Map<String, Object>> selectCountGroupByWeiBo_self(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "selectCountGroupByWeiBo", paramMap);
	}

	@Override
	public Map<String, Object> getSummaryByModuleId(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getSummaryByModuleId", paramMap);
	}

	@Override
	public Map<String, Object> getTrendSummary_other(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getTrendSummary", paramMap);
	}

	@Override
	public List<Map<String, Object>> getOrientationSourceType_other(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "getOrientationSourceType", paramMap);
	}

	@Override
	public Map<String, Object> getMediaAnalysisSummary_other(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getMediaAnalysisSummary", paramMap);
	}

	@Override
	public EventAnalysisData selectEventAnalysisDataDetail_self(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE + "selectEventAnalysisDataDetail", paramMap);
	}

	@Override
	public List<TopicWeiBoAuthor> getNavyAnalysisCount_other(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE_AUTHOR + "getNavyAnalysisCount", paramMap);
	}

	@Override
	public List<TopicWeiBoAuthor> getEmotionAnalysisCount_other(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE_AUTHOR + "getEmotionAnalysisCount", paramMap);
	}

	@Override
	public List<TopicWeiBoAuthor> getInfluenceRankingList_other(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE_AUTHOR + "getInfluenceRankingList", paramMap);
	}

	@Override
	public TopicWeiBoAuthor getMicroBlogAnalysisTitle_other(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE_AUTHOR + "getMicroBlogAnalysisTitle", paramMap);
	}

	@Override
	public List<TopicWeiBoAuthor> getTableDataCount_other(Map<String, Object> paramMap) {
		List<TopicWeiBoAuthor> list = this.sqlSessionTemplate.selectList(NAME_SPACE_AUTHOR + "getTableDataCount",
				paramMap);
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				TopicWeiBoAuthor topicWeiBoAuthor = list.get(i);
				if ("-1".equals(topicWeiBoAuthor.getType())) {
					list.remove(i);
				}
			}
		}
		return list;
	}

	@Override
	public List<TopicWeiBoAuthor> getKeyPointMicroBlogList_other(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE_AUTHOR + "getKeyPointMicroBlogList", paramMap);
	}

	@Override
	public List<Map<String, Object>> selectCountGroupByOrientation_self(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "selectCountGroupByOrientation", paramMap);
	}

	@Override
	public List<EventAnalysisData> selectSameEventAnalysisDataList_self(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "selectSameEventAnalysisDataList", paramMap);
	}

	@Override
	public List<TopicWeiBoAuthor> getBloggerRegionList_other(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE_AUTHOR + "getBloggerRegionList", paramMap);
	}

	@Override
	public PageInfo<EventAnalysisData> selectSameExportDataList_self(Map<String, Object> paramMap,
			Pagination pageInfo) {
		return this.getPageList(NAME_SPACE + "selectSameExportDataList", pageInfo, paramMap);
	}

	@Override
	public Map<String, Integer> selectEventAnalysisrepeatCount_self(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE + "selectEventAnalysisrepeatCount", paramMap);
	}

	@Override
	public EventAnalysisData selectFirstWebName_self(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE + "selectFirstWebName", paramMap);
	}

	@Override
	public List<WeiboEntity> getPropagatePath_other(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "getPropagatePath", paramMap);
	}
	
	@Override
    public Map<String, String> getTopicName(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getTopicName", paramMap);
    }
	
	@Override
	public Map<String, Long> getWeiboAuthorMap_other(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getWeiboAuthorMap", paramMap);
	}

	@Override
	public Map<String, Object> selectCountGroupByIsRepeat_self(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE + "selectCountGroupByIsRepeat", paramMap);
	}

	@Override
	public List<Map<String, Object>> getNetizenViewpoint_info(String eventId) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE_AUTHOR + "getNetizenViewpoint", eventId);
	}

	@Override
	public List<Map<String, Object>> selectEventAnalysisRegionName(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "selectEventAnalysisRegionName", paramMap);
	}

	@Override
	public TopicWeiBoAuthor getEvenIdName(String eventId) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE_AUTHOR + "getEvenIdName", eventId);
	}

    @Override
    public Map<String, Integer> selectEventAnalysisrepeatCount_other(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE + "eventAnalysisrepeatCount", paramMap);
    }

    @Override
    public List<EventAnalysisData> getAuthorArticles_other(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getAuthorArticles", paramMap);
    }

    @Override
    public EventAnalysisData selectEventAnalysisDataDetail_other(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE + "selectEventAnalysisDataDetail_other", paramMap);
    }

}
