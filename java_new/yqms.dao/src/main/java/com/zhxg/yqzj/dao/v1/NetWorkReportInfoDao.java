package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.entities.v1.NetWorkReportInfo;

public interface NetWorkReportInfoDao extends BaseDao<BaseEntity>{

    /**
     * 获取信息具体内容 
     * @param params
     * @return
     */
    List<NetWorkReportInfo> getNetWorkByKruuid_other(Map<String, Object> params);
    
}
