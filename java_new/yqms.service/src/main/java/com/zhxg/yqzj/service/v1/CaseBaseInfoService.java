package com.zhxg.yqzj.service.v1;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.CaseBaseInfo;
import com.zhxg.yqzj.service.exception.casebase.CasebaseDownloadTimesRunOutException;

public interface CaseBaseInfoService extends BaseService<CaseBaseInfo> {

    PageInfo<CaseBaseInfo> getCaseBaseList(Map<String, Object> paramMap, Pagination pageInfo);

    List<CaseBaseInfo> getCaseBaseInfoDetail(Map<String, Object> paramMap);

    PageInfo<CaseBaseInfo> getLoadReportsList(Map<String, Object> paramMap, Pagination pageInfo);

    int downloadCaseBaseReport(CaseBaseInfo caseBaseInfo) throws CasebaseDownloadTimesRunOutException;

    Map<String, Integer> getCaseBaseReportCount(Map<String, Object> paramMap) throws SysException;


  

	

}
