package com.zhxg.yqzj.service.impl.v1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.yqzj.dao.v1.CaseBaseInfoDao;
import com.zhxg.yqzj.entities.v1.CaseBaseInfo;
import com.zhxg.yqzj.service.exception.casebase.CasebaseDownloadTimesRunOutException;
import com.zhxg.yqzj.service.v1.CaseBaseInfoService;

@Service
public class CaseBaseInfoServiceImpl extends BaseServiceImpl<CaseBaseInfo> implements CaseBaseInfoService {

	@Autowired
	private CaseBaseInfoDao caseBaseInfoDao;
	

	@Override
    protected BaseDao<CaseBaseInfo> getBaseDao() {
        return this.caseBaseInfoDao;
    }


    @Override
    public PageInfo<CaseBaseInfo> getCaseBaseList(Map<String, Object> paramMap, Pagination pageInfo) {
        return caseBaseInfoDao.getCaseBaseList(paramMap,pageInfo);
    }


    @Override
    public List<CaseBaseInfo> getCaseBaseInfoDetail(Map<String, Object> paramMap) {
        return caseBaseInfoDao.getCaseBaseInfoDetail(paramMap);
    }


    @Override
    public PageInfo<CaseBaseInfo> getLoadReportsList(Map<String, Object> paramMap, Pagination pageInfo) {
        return caseBaseInfoDao.getLoadReportsList(paramMap,pageInfo);
    }


    @Override
    public int downloadCaseBaseReport(CaseBaseInfo caseBaseInfo) throws CasebaseDownloadTimesRunOutException {
    	Map<String, Object> paramMap = new HashMap<String,Object>();
    	paramMap.put("userid", caseBaseInfo.getUserid());
    	Map<String, Integer> limitMap = caseBaseInfoDao.getCaseBaseReportCount(paramMap);
    	int count = limitMap.get("caseBaseDownloadCount");
    	int total = limitMap.get("caseBaseDownloadTotal");
    	if(total>count){
    		return caseBaseInfoDao.downloadCaseBaseReport(caseBaseInfo);
    	}else{
    		throw new CasebaseDownloadTimesRunOutException();
    	}
        
    }


    @Override
    public Map<String, Integer> getCaseBaseReportCount(Map<String, Object> paramMap) throws SysException {
        Map<String, Integer> result = new HashMap<String, Integer>();
        try{
        	result = caseBaseInfoDao.getCaseBaseReportCount(paramMap);
        }catch(RuntimeException e){
            throw new SysException();
        }
        return result;
    }



}
