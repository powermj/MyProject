package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.MobileCloudIndustryInfoDao;
import com.zhxg.yqzj.entities.v1.MobileCloudIndustryInfo;

@Repository
public class MobileCloudIndustryInfoDaoImpl extends BaseDaoImpl<BaseEntity> implements MobileCloudIndustryInfoDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.MobileCloudIndustryInfo.";


    @Override
    public List<MobileCloudIndustryInfo> getAllIndustryInfo(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getAllIndustryInfo",params);
    }

}
