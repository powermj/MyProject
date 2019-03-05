package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.MobileCloudEventInfoDao;
import com.zhxg.yqzj.entities.v1.MobileCloudEventInfo;

@Repository
public class MobileCloudEventInfoDaoImpl extends BaseDaoImpl<BaseEntity> implements MobileCloudEventInfoDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.MobileCloudEventInfo.";

    @Override
    public List<MobileCloudEventInfo> getEventWord(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getEventWord", params);
    }
}
