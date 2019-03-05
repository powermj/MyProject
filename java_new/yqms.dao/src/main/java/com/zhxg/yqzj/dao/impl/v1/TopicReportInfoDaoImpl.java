package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.TopicReportInfoDao;
import com.zhxg.yqzj.entities.v1.TopicReportInfo;

@Repository
public class TopicReportInfoDaoImpl extends BaseDaoImpl<BaseEntity> implements TopicReportInfoDao {
    
    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.TopicReportInfo.";

    @Override
    public List<TopicReportInfo> getTopicByUuid_other(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getTopicByUuid", params);
    }
}
