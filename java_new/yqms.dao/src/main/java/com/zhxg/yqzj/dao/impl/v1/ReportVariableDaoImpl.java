package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.ReportVariableDao;
import com.zhxg.yqzj.entities.v1.ReportVariable;

@Repository
public class ReportVariableDaoImpl extends BaseDaoImpl<BaseEntity> implements ReportVariableDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.ReportVariable.";

    @Override
    public int insertReportVariable(Map<String, Object> params) {
        return this.sqlSessionTemplate.insert(NAME_SPACE+"insertReportVariable", params);
    }

    @Override
    public int updateReportVariable(Map<String, Object> params) {
        return this.sqlSessionTemplate.update(NAME_SPACE+"updateReportVariable", params);
    }

    @Override
    public int deleteReportVariableById(Map<String, Object> params) {
        return this.sqlSessionTemplate.delete(NAME_SPACE+"deleteReportVariableById", params);
    }

    @Override
    public List<ReportVariable> getReportVariables(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getReportVariables", params);
    }

    @Override
    public int getVariableNum(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getVariableNum", params);
    }

    @Override
    public ReportVariable getReportVariable(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getReportVariable", params);
    }

    @Override
    public int updateInitValue(Map<String, Object> params) {
        return this.sqlSessionTemplate.update(NAME_SPACE+"updateInitValue", params);
    }

    



}
