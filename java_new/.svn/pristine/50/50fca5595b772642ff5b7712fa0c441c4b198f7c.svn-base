package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.AttentionReportInfoDao;
import com.zhxg.yqzj.entities.v1.AttentionReportInfo;

@Repository
public class AttentionReportInfoDaoImpl extends BaseDaoImpl<BaseEntity> implements AttentionReportInfoDao {
    
    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.AttentionReportInfo.";

    @Override
    public List<AttentionReportInfo> getAttentionByKmuuid_self(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getAttentionByKmuuid", params);
    }
}
