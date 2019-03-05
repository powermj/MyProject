package com.zhxg.yqzj.service.v1;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.Consultation;
import com.zhxg.yqzj.service.exception.expert.ConsultationInApplyingException;
import com.zhxg.yqzj.service.exception.expert.ConsultationTimesRunOutException;

public interface ConsultationService extends BaseService<Consultation> {

	int applyConsultation(Consultation consultation) throws SysException, ConsultationInApplyingException, ConsultationTimesRunOutException;

	PageInfo<Consultation> listConsultation(Map<String, Object> paramMap, Pagination pageInfo) throws SysException;

	Map<String, Integer> getConsultationCount(Map<String, Object> paramMap) throws SysException;

}
