package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.yqzj.dao.v1.EventAnalysisDao;
import com.zhxg.yqzj.entities.v1.EventAnalysis;

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
public class EventAnalysisDaoImpl extends BaseDaoImpl<EventAnalysis> implements EventAnalysisDao {

    @Override
    public List<EventAnalysis> selectEventAnalysisList(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList("com.zhxg.yqzj.entities.v1.EventAnalysis.selectEventAnalysisList", paramMap);       
    }

    @Override
    public int selectEventAnalysisCount_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne("com.zhxg.yqzj.entities.v1.EventAnalysis.selectEventAnalysisInfoCount", paramMap);
    }

    @Override
    public int saveAddTopic(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.insert("com.zhxg.yqzj.entities.v1.EventAnalysis.saveAddTopic", paramMap);
    }

    @Override
    public int createTopicTable_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.insert("com.zhxg.yqzj.entities.v1.EventAnalysis.createTopicTable", paramMap);
    }

    @Override
    public int saveAddTopickeyword(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.insert("com.zhxg.yqzj.entities.v1.EventAnalysis.saveAddTopickeyword", paramMap);
    }

    @Override
    public Map<String, Object> selectUserStatusAndEventAnalysisCount(String userId) {
        return this.sqlSessionTemplate.selectOne("com.zhxg.yqzj.entities.v1.EventAnalysis.selectUserStatusAndEventAnalysisCount", userId);
    }

    @Override
    public int selectEventAnalysisCount(String userId) {
        return this.sqlSessionTemplate.selectOne("com.zhxg.yqzj.entities.v1.EventAnalysis.selectEventAnalysisCount", userId);
    }

    @Override
    public int createTopicCommontTable_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.insert("com.zhxg.yqzj.entities.v1.EventAnalysis.createTopicCommontTable", paramMap);
    }

    @Override
    public int createTopicWeiboAuthorTable_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.insert("com.zhxg.yqzj.entities.v1.EventAnalysis.createTopicWeiboAuthorTable", paramMap);
    }

    @Override
    public List<EventAnalysis> selectEventAnalysisInfo(String eventAnalysisId) {
        return this.sqlSessionTemplate.selectList("com.zhxg.yqzj.entities.v1.EventAnalysis.selectEventAnalysisInfo", eventAnalysisId);   
    }
    
    @Override
    public EventAnalysis selectEventAnalysisById(String eventAnalysisId) {
        return this.sqlSessionTemplate.selectOne("com.zhxg.yqzj.entities.v1.EventAnalysis.selectEventAnalysisInfo", eventAnalysisId);   
    }

    @Override
    public int updateEventAnalysis(EventAnalysis eventAnalysis) {
        return sqlSessionTemplate.insert("com.zhxg.yqzj.entities.v1.EventAnalysis.updateEventAnalysis",eventAnalysis);
    }

    @Override
    public int deleteEventAnalysisWords(EventAnalysis eventAnalysis) {
        return sqlSessionTemplate.delete("com.zhxg.yqzj.entities.v1.EventAnalysis.deleteEventAnalysisWords", eventAnalysis);
    }

    @Override
    public int deleteEventAnalysis(Map<String, Object> paramMap) {
        return sqlSessionTemplate.update("com.zhxg.yqzj.entities.v1.EventAnalysis.deleteEventAnalysis", paramMap);
    }

    @Override
    public int deleteEventAnalysisWords(Map<String, Object> paramMap) {
        return sqlSessionTemplate.delete("com.zhxg.yqzj.entities.v1.EventAnalysis.deleteEventAnalysisWords", paramMap);
    }

    @Override
    public int dropEventAnalysis_self(Map<String, Object> paramMap) {
        return sqlSessionTemplate.update("com.zhxg.yqzj.entities.v1.EventAnalysis.dropEventAnalysisInfo", paramMap);
    }

    @Override
    public List<EventAnalysis> selectRecommendEventAnalysisList() {
        return this.sqlSessionTemplate.selectList("com.zhxg.yqzj.entities.v1.EventAnalysis.selectRecommendEventAnalysisList", null);  
    }

    @Override
    public PageInfo<EventAnalysis> selectDeleteEventAnalysisList(Map<String, Object> paramMap, Pagination pageInfo) {      
        return this.getPageList("com.zhxg.yqzj.entities.v1.EventAnalysis.selectDeleteEventAnalysisList", pageInfo, paramMap);
    }

    @Override
    public int insertAttentionSelective_self(Map<String, Object> insertYqjbParams) {
        return this.sqlSessionTemplate.insert("com.zhxg.yqzj.entities.v1.EventAnalysis.insertAttentionSelective", insertYqjbParams);        
    }

    @Override
    public int insertAttentionCntSelective_self(Map<String, Object> data) {
        return this.sqlSessionTemplate.insert("com.zhxg.yqzj.entities.v1.EventAnalysis.insertAttentionCntSelective", data);  
    }

    @Override
    public int updateOrientation_self(Map<String, Object> paramMap) {
        return sqlSessionTemplate.update("com.zhxg.yqzj.entities.v1.EventAnalysis.updateOrientation", paramMap);
    }

    @Override
    public int updateRead_self(Map<String, Object> paramMap) {
        return sqlSessionTemplate.update("com.zhxg.yqzj.entities.v1.EventAnalysis.updateRead", paramMap);
    }

    @Override
    public int deleteEventAnalysisData_self(Map<String, Object> paramMap) {
        return sqlSessionTemplate.update("com.zhxg.yqzj.entities.v1.EventAnalysis.deleteEventAnalysisData", paramMap);
    }

    @Override
    public List<Map<String, Object>> selectEventAnalysisList_other(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList("com.zhxg.yqzj.entities.v1.EventAnalysis.selectEventAnalysisDataList", paramMap); 
    }

    @Override
    public List<Map<String, Object>> selectAttentionList_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList("com.zhxg.yqzj.entities.v1.EventAnalysis.selectAttentionList", paramMap);
    }

    @Override
    public int updateattention_self(Map<String, Object> paramMap) {
        return sqlSessionTemplate.update("com.zhxg.yqzj.entities.v1.EventAnalysis.updateattention", paramMap);
        
    }

    @Override
    public int insertBriefingSelective_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.insert("com.zhxg.yqzj.entities.v1.EventAnalysis.insertBriefingSelective", paramMap);
    }

    @Override
    public int insertBriefingCntSelective_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.insert("com.zhxg.yqzj.entities.v1.EventAnalysis.insertBriefingCntSelective", paramMap);
    }

    @Override
    public int updateBriefing_self(Map<String, Object> paramMap) {
        return sqlSessionTemplate.update("com.zhxg.yqzj.entities.v1.EventAnalysis.updateBriefing", paramMap);
    }

    @Override
    public int deletePastEventAnalysis(Map<String, Object> paramMap) {
        return sqlSessionTemplate.update("com.zhxg.yqzj.entities.v1.EventAnalysis.deletePastEventAnalysis", paramMap);
    }

    @Override
    public int updateEventAnalysisData_self(Map<String, Object> paramMap) {
        return sqlSessionTemplate.update("com.zhxg.yqzj.entities.v1.EventAnalysis.updateEventAnalysisData", paramMap);
        
    }

    @Override
    public int deleteAttentionList_self(Map<String, Object> paramMap) {
        return sqlSessionTemplate.update("com.zhxg.yqzj.entities.v1.EventAnalysis.deleteAttentionList", paramMap);
    }

    @Override
    public List<Map<String, Object>> selectBriefingList_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList("com.zhxg.yqzj.entities.v1.EventAnalysis.selectBriefingList", paramMap);
    }

    @Override
    public Map<String, Object> selectEventAnalysisStatus(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne("com.zhxg.yqzj.entities.v1.EventAnalysis.selectEventAnalysisStatus", paramMap);
    }

    @Override
    public int addEventAnalysisStatus(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.insert("com.zhxg.yqzj.entities.v1.EventAnalysis.addEventAnalysisStatus", paramMap);
    }

    @Override
    public int updateEventAnalysisStatus(Map<String, Object> paramMap) {
        return sqlSessionTemplate.update("com.zhxg.yqzj.entities.v1.EventAnalysis.updateEventAnalysisStatus", paramMap);
    }
    
    @Override
    public int createTopicRegionTableTable_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.insert("com.zhxg.yqzj.entities.v1.EventAnalysis.createTopicRegionTableTable", paramMap);
    }

    @Override
    public Map<String, Object> selectUserEventAnalysisStatus(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne("com.zhxg.yqzj.entities.v1.EventAnalysis.selectUserEventAnalysisStatus", paramMap);
    }

    @Override
    public int updateOrientations_self(Map<String, Object> paramMap) {
        return sqlSessionTemplate.update("com.zhxg.yqzj.entities.v1.EventAnalysis.updateOrientations", paramMap);
    }

    @Override
    public List<String> getSimhashList_self(Map<String, Object> paramMap) {
        return sqlSessionTemplate.selectList("com.zhxg.yqzj.entities.v1.EventAnalysis.getSimhashList", paramMap);
    }
    
    @Override
    public Map<String, Object> selectUserEventAnalysisNum(String userId) {
        return this.sqlSessionTemplate.selectOne("com.zhxg.yqzj.entities.v1.EventAnalysis.selectUserEventAnalysisNum", userId);
    }

    @Override
    public Map<String, Object> selectTopicSourceTypeNum_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne("com.zhxg.yqzj.entities.v1.EventAnalysis.selectTopicSourceTypeNum", paramMap);
    }

}
