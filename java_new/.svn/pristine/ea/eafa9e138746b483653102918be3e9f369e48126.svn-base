package com.zhxg.yqzj.dao.impl.v1;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.LocationInfoDao;
import com.zhxg.yqzj.entities.v1.LocationInfo;

@Repository
public class LocationInfoImpl extends BaseDaoImpl<BaseEntity> implements LocationInfoDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.LocationInfo.";

    @Override
    public LocationInfo getLocationInfo(Integer regionId) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getLocationInfo", regionId);
    }
    
    
}
