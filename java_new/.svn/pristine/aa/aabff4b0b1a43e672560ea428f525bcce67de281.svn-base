package com.zhxg.yqzj.dao.impl.v1;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.yqzj.dao.v1.ReportFileInfoDao;
import com.zhxg.yqzj.entities.v1.ReportFileInfo;

@Repository
public class ReportFileInfoDaoImpl extends BaseDaoImpl<ReportFileInfo> implements ReportFileInfoDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.ReportFileInfo.";

    @Override
    public PageInfo<ReportFileInfo> getReportFileInfos(Map<String, Object> params, Pagination pageInfo) {
        return this.getPageList( NAME_SPACE+"getReportFileInfos",pageInfo, params);
    }

    @Override
    public int insertReportFileInfo(Map<String, Object> params) {
        return this.sqlSessionTemplate.insert(NAME_SPACE+"insertReportFileInfo", params);
    }


    



}
