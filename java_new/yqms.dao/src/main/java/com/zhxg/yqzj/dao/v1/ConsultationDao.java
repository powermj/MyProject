package com.zhxg.yqzj.dao.v1;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.yqzj.entities.v1.Consultation;

public interface ConsultationDao extends BaseDao<Consultation>{

	int applyConsultation(Consultation consultation);

	PageInfo<Consultation> listConsultation(Map<String, Object> paramMap, Pagination pageInfo);

	Consultation getConsultationInApplying(Consultation consultation);

	Map<String, Integer> getConsultationRemainingTimes(Map<String, Object> paramMap);

}
