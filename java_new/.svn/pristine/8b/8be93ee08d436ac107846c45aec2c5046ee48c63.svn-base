package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.yqzj.dao.v1.AllReportDataDao;
import com.zhxg.yqzj.entities.v1.AllReportData;
import com.zhxg.yqzj.entities.v1.Info;

@Repository
public class AllReportDataDaoImpl extends BaseDaoImpl<AllReportData> implements AllReportDataDao {
    
    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.AllReportData.";

    @Override
    public PageInfo<AllReportData> getAllReports_self(Map<String, Object> params,Pagination pageInfo) {
        return this.getPageList( NAME_SPACE+"getAllReports",pageInfo, params);
    }

    @Override
    public int updateReportOrientations_self(Map<String, Object> params) {
        return this.sqlSessionTemplate.update(NAME_SPACE+"updateReportOrientations", params);
    }

    @Override
    public int deleteReportDatas_self(Map<String, Object> params) {
        return this.sqlSessionTemplate.delete(NAME_SPACE+"deleteReportDatas", params);
    }

    @Override
    public int insertReportData_self(Map<String, Object> params) {
        return this.sqlSessionTemplate.insert(NAME_SPACE+"insertReportData", params);
    }
    
    @Override
    public List<String> getRepeatUuid_self(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getRepeatUuid", params);
    }

    @Override
    public int updateReportClassifyIds_self(Map<String, Object> params) {
        return this.sqlSessionTemplate.update(NAME_SPACE+"updateReportClassifyIds",params);
    }

    @Override
    public int getReportByClassify_self(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getReportByClassify", params);
    }

    @Override
    public List<String> getReportIdByClassify_self(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getReportIdByClassify", params);
    }

    @Override
    public List<Info> getDataForExport_self(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getDataForExport",params);
    }

    @Override
    public List<Map<String, Object>> getEchartDataList_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getEchartDataList",paramMap);
    }

    @Override
    public Map<String, Object> getEchartDataDistance_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getEchartDataDistance",paramMap);
    }

    @Override
    public AllReportData getInfoDetail_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getInfoDetail", paramMap);
    }

    @Override
    public int setInfoStatus_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.update(NAME_SPACE+"setInfoStatus", paramMap);
    }
	
}
