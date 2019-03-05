package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.NetWorkReportInfoDao;
import com.zhxg.yqzj.entities.v1.NetWorkReportInfo;

@Repository
public class NetWorkReportInfoDaoImpl extends BaseDaoImpl<BaseEntity> implements NetWorkReportInfoDao {
    
    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.NetWorkReportInfo.";

    @Override
    public List<NetWorkReportInfo> getNetWorkByKruuid_other(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getNetWorkByKruuid", params);
    }
}
