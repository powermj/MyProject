package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.WarningReportInfoDao;
import com.zhxg.yqzj.entities.v1.WarningReportInfo;

@Repository
public class WarningReportInfoDaoImpl extends BaseDaoImpl<BaseEntity> implements WarningReportInfoDao {
    
    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.WarningReportInfo.";

    @Override
    public List<WarningReportInfo> getWarningByKsuuid_self(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getWarningByKsuuid", params);
    }
}
