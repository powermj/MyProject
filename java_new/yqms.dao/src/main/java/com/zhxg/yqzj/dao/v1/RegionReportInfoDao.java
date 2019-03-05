package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.entities.v1.RegionReportInfo;

public interface RegionReportInfoDao extends BaseDao<BaseEntity>{

    /**
     * 获取数据列表 
     * @param params
     * @return
     */
    List<RegionReportInfo> getRegionByKruuid_region(Map<String, Object> params);
    
}
