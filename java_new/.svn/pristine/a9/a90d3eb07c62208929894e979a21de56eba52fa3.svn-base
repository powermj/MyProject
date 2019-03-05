package com.zhxg.yqzj.dao.impl.v1;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.yqzj.dao.v1.ConsultationDao;
import com.zhxg.yqzj.entities.v1.Consultation;

@Repository
public class ConsultationDaoImpl extends BaseDaoImpl<Consultation> implements ConsultationDao {

	private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.Consultation.";
	
	@Override
	public int applyConsultation(Consultation consultation) {
		return this.sqlSessionTemplate.insert(NAME_SPACE + "insertConsultation", consultation);
	}

	@Override
	public PageInfo<Consultation> listConsultation(Map<String, Object> paramMap, Pagination pageInfo) {
		return this.getPageList(NAME_SPACE + "listConsultation", pageInfo, paramMap);
	}

	@Override
	public Consultation getConsultationInApplying(Consultation consultation) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + "getConsultationInApplying", consultation);
	}

	@Override
	public Map<String,Integer> getConsultationRemainingTimes(Map<String, Object> paramMap) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + "getConsultationRemainingTimes",paramMap);
	}

	
}
