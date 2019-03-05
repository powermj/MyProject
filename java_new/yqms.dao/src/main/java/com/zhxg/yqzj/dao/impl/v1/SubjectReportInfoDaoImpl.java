package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.SubjectReportInfoDao;
import com.zhxg.yqzj.entities.v1.SubjectReportInfo;

@Repository
public class SubjectReportInfoDaoImpl extends BaseDaoImpl<BaseEntity> implements SubjectReportInfoDao {
    
    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.SubjectReportInfo.";

    @Override
    public List<SubjectReportInfo> getSubjectByKruuid_self(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getSubjectByKruuid", params);
    }
}
