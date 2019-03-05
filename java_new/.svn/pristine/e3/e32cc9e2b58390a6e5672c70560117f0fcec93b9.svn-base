package com.zhxg.yqzj.dao.impl.v1;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.MobileCloudSubjectInfoDao;
import com.zhxg.yqzj.entities.v1.MobileCloudSubjectInfo;

@Repository
public class MobileCloudSubjectInfoDaoImpl extends BaseDaoImpl<BaseEntity> implements MobileCloudSubjectInfoDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.MobileCloudSubjectInfo.";

    @Override
    public MobileCloudSubjectInfo getSubjectWord(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getSubjectWord", params);
    }


}
