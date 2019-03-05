package com.zhxg.yqzj.service.impl.v1;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.yqzj.dao.v1.ConsultationDao;
import com.zhxg.yqzj.entities.v1.Consultation;
import com.zhxg.yqzj.service.exception.expert.ConsultationInApplyingException;
import com.zhxg.yqzj.service.exception.expert.ConsultationTimesRunOutException;
import com.zhxg.yqzj.service.v1.ConsultationService;

@Service
public class ConsultationServiceImpl extends BaseServiceImpl<Consultation> implements ConsultationService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ConsultationDao consultationDao;
	

	@Override
    protected BaseDao<Consultation> getBaseDao() {
        return this.consultationDao;
    }

	@Override
	public int applyConsultation(Consultation consultation) throws SysException, ConsultationInApplyingException, ConsultationTimesRunOutException {
		Consultation consultationInApplying = consultationDao.getConsultationInApplying(consultation);
		if(consultationInApplying!=null){
			throw new ConsultationInApplyingException();
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", consultation.getUserid());
		Map<String, Integer> map = consultationDao.getConsultationRemainingTimes(paramMap);
		if(map==null||map.get("remainingTimes")>0){
			return consultationDao.applyConsultation(consultation);
		}else{
			throw new ConsultationTimesRunOutException();
		}
		
	}
	
	@Override
	public PageInfo<Consultation> listConsultation(Map<String, Object> paramMap, Pagination pageInfo) throws SysException {
		PageInfo<Consultation> page;
        try{
        	page = this.consultationDao.listConsultation(paramMap, pageInfo);
        }catch(RuntimeException e){
            logger.error(e.getMessage(),e);
        	throw new SysException();
        }
		return page;
	}

	@Override
	public Map<String, Integer> getConsultationCount(Map<String, Object> paramMap) throws SysException {
		Map<String, Integer> result = new HashMap<String, Integer>();
		try{
			result = consultationDao.getConsultationRemainingTimes(paramMap);
		}catch(RuntimeException e){
		    logger.error(e.getMessage(),e);
			throw new SysException();
		}
		return result;
	}

}
