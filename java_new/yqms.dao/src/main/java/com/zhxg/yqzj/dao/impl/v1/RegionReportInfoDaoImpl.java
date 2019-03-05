package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.RegionReportInfoDao;
import com.zhxg.yqzj.entities.v1.RegionReportInfo;

@Repository
public class RegionReportInfoDaoImpl extends BaseDaoImpl<BaseEntity> implements RegionReportInfoDao {
    
    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.RegionReportInfo.";

    @Override
    public List<RegionReportInfo> getRegionByKruuid_region(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getRegionByKruuid", params);
    }
}
