package com.zhxg.yqzj.service.impl.v1;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.framework.base.utils.HttpUtil;
import com.zhxg.framework.base.utils.PropertiesUtil;
import com.zhxg.yqzj.dao.v1.CaseBaseInfoDao;
import com.zhxg.yqzj.dao.v1.ConsultationDao;
import com.zhxg.yqzj.dao.v1.IndexDao;
import com.zhxg.yqzj.dao.v1.ReportDao;
import com.zhxg.yqzj.dao.v1.WarningDao;
import com.zhxg.yqzj.entities.v1.Index;
import com.zhxg.yqzj.service.v1.IndexService;

@Service
public class IndexServiceImpl extends BaseServiceImpl<Index> implements IndexService {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
	@Autowired
    private IndexDao indexDao;
	
	@Autowired
    private CaseBaseInfoDao caseBaseInfoDao;
	
	@Autowired
    private ConsultationDao consultationDao;
	
	@Autowired
	private ReportDao reportDao;
	
	@Autowired
    private WarningDao WarningDao;
	
    @Override
    protected BaseDao<Index> getBaseDao() {
        return this.indexDao;
    }

	@Override
	public Index getIndexStatistics(Map<String, Object> paramMap) throws SysException {
	    paramMap.put("ksTime", DateUtil.getDate(new Date()).replace("-", ""));
	   
	    Map<String, Integer>caseBaseCount = caseBaseInfoDao.getCaseBaseReportCount(paramMap);
	    Map<String, Integer> consultationRemainingTimes = consultationDao.getConsultationRemainingTimes(paramMap);
	    
	    List<Map<String, Integer>> expertReportCount = reportDao.getExpertReportCount(paramMap);
	    int reportTotal=0;
	    int reportCount=0;
	    for(int i=0;i<expertReportCount.size();i++){
	        Map<String, Integer> map = expertReportCount.get(i);
	        reportTotal+=map.get("total");
	        reportCount+=map.get("count");
	        
	    }    
	    List<Map<String, Integer>> waringCount = WarningDao.getWaringCount_self(paramMap);
		Index index = new Index();
		try{
			index.setArtificialWarning(waringCount.get(0).get("waringCount"));
			index.setArtificialWarningLimit(waringCount.get(0).get("waringTotal"));
			index.setCaseBase(caseBaseCount.get("caseBaseDownloadCount"));
			index.setCaseBaseLimit(caseBaseCount.get("caseBaseDownloadTotal"));
			index.setExpertConsultation(consultationRemainingTimes.get("count"));
			index.setExpertConsultationLimit(consultationRemainingTimes.get("total"));
			index.setExpertReport(reportCount);
			index.setExpertReportLimit(reportTotal);
		}catch(RuntimeException e){
		    logger.error(e.getMessage(),e);
			throw new SysException();
		}
		return index;
	}

	@Override
	public String getTopicStatisticsList(Map<String, String> params) throws SysException {
		try {
			return HttpUtil.post(PropertiesUtil.getProperties("application.properties", "ms.server.url")+"yqzjIndex/getTopicStatisticsList.do", params);
		} catch (Exception e) {
		    logger.error(e.getMessage(),e);
			throw new SysException();
		}
	}

}
