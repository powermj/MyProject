package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.MobileCloudRegionalInfoDao;
import com.zhxg.yqzj.entities.v1.MobileCloudRegionalInfo;

@Repository
public class MobileCloudRegionalInfoDaoImpl extends BaseDaoImpl<BaseEntity> implements MobileCloudRegionalInfoDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.MobileCloudRegionalInfo.";

    @Override
    public List<MobileCloudRegionalInfo> getAllRegionalInfo(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getAllRegionalInfo",params);
    }

    @Override
    public List<MobileCloudRegionalInfo> getSpecialRegionalInfo(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getSpecialRegionalInfo",params);
    }

}
