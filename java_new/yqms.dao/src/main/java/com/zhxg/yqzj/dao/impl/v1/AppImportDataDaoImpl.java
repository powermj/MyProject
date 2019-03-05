package com.zhxg.yqzj.dao.impl.v1;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.yqzj.dao.v1.AppImportDataDao;
import com.zhxg.yqzj.entities.v1.AppImportData;
import com.zhxg.yqzj.entities.v1.UsageStatistics;

@Repository
public class AppImportDataDaoImpl extends BaseDaoImpl<AppImportData> implements AppImportDataDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.AppImportData.";

    /**
     * 将app端数据添加导库中
     */
    @Override
    public int insertAppData(Map<String, Object> params) {
        return this.sqlSessionTemplate.insert(NAME_SPACE + "insertAppData", params);
    }

    @Override
    public int saveAppUsageStatistics(UsageStatistics usageStatistics) {
        return this.sqlSessionTemplate.insert(NAME_SPACE + "saveAppUsageStatistics", usageStatistics);
    }

}
