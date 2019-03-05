package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.yqzj.dao.v1.CaseBaseInfoDao;
import com.zhxg.yqzj.entities.v1.CaseBaseInfo;

@Repository
public class CaseBaseInfoDaoImpl extends BaseDaoImpl<CaseBaseInfo> implements CaseBaseInfoDao {

	private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.CaseBaseInfo.";

    @Override
    public PageInfo<CaseBaseInfo> getCaseBaseList(Map<String, Object> paramMap, Pagination pageInfo) {
        return this.getPageList(NAME_SPACE + "getCaseBaseList", pageInfo, paramMap);
    }

    @Override
    public List<CaseBaseInfo> getCaseBaseInfoDetail(Map<String, Object> paramMap) {
      
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getCaseBaseInfoDetail", paramMap);
    }

    @Override
    public PageInfo<CaseBaseInfo> getLoadReportsList(Map<String, Object> paramMap, Pagination pageInfo) {
        return this.getPageList(NAME_SPACE + "getLoadReportsList", pageInfo, paramMap);
    }

    @Override
    public int downloadCaseBaseReport(CaseBaseInfo caseBaseInfo) {
        return this.sqlSessionTemplate.insert(NAME_SPACE + "insertCaseBaseReport", caseBaseInfo);
    }

	@Override
	public Map<String, Integer> getCaseBaseReportCount(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getCaseBaseDownloadCount",paramMap);
	}

    
	
	

	
}
