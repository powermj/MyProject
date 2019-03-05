package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.TVReportInfoDao;
import com.zhxg.yqzj.entities.v1.TVReportInfo;

@Repository
public class TVReportInfoDaoImpl extends BaseDaoImpl<BaseEntity> implements TVReportInfoDao {
    
    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.TVReportInfo.";

    @Override
    public List<TVReportInfo> getTVByKsuuid_self(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getTVByKsuuid", params);
    }
}
