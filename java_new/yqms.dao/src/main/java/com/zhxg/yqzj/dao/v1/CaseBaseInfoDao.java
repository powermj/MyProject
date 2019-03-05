package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.yqzj.entities.v1.CaseBaseInfo;

public interface CaseBaseInfoDao extends BaseDao<CaseBaseInfo>{

    PageInfo<CaseBaseInfo> getCaseBaseList(Map<String, Object> paramMap, Pagination pageInfo);

    List<CaseBaseInfo> getCaseBaseInfoDetail(Map<String, Object> paramMap);

    PageInfo<CaseBaseInfo> getLoadReportsList(Map<String, Object> paramMap, Pagination pageInfo);

    int downloadCaseBaseReport(CaseBaseInfo caseBaseInfo);

	Map<String, Integer> getCaseBaseReportCount(Map<String, Object> paramMap);

	

}
