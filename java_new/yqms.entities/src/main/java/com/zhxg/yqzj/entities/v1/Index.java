package com.zhxg.yqzj.entities.v1;

import com.zhxg.framework.base.curd.impl.BaseEntity;

public class Index extends BaseEntity{

	/**
	 * 人工预警数量
	 */
	private Integer artificialWarning;
	
	/**
	 * 人工预警数量限制
	 */
	private Integer artificialWarningLimit;
	
	/**
	 * 专家咨询数量
	 */
	private Integer expertConsultation;
	
	/**
	 * 专家咨询数量限制
	 */
	private Integer expertConsultationLimit;
	
	/**
	 * 专家报告数量
	 */
	private Integer expertReport;
	
	/**
	 * 专家报告数量限制
	 */
	private Integer expertReportLimit;
	
	/**
	 * 案例库数量
	 */
	private Integer caseBase;
	
	/**
	 * 案例库数量限制
	 */
	private Integer caseBaseLimit;

	public Integer getArtificialWarning() {
		return artificialWarning;
	}

	public void setArtificialWarning(Integer artificialWarning) {
		this.artificialWarning = artificialWarning;
	}

	public Integer getArtificialWarningLimit() {
		return artificialWarningLimit;
	}

	public void setArtificialWarningLimit(Integer artificialWarningLimit) {
		this.artificialWarningLimit = artificialWarningLimit;
	}

	public Integer getExpertConsultation() {
		return expertConsultation;
	}

	public void setExpertConsultation(Integer expertConsultation) {
		this.expertConsultation = expertConsultation;
	}

	public Integer getExpertConsultationLimit() {
		return expertConsultationLimit;
	}

	public void setExpertConsultationLimit(Integer expertConsultationLimit) {
		this.expertConsultationLimit = expertConsultationLimit;
	}

	public Integer getExpertReport() {
		return expertReport;
	}

	public void setExpertReport(Integer expertReport) {
		this.expertReport = expertReport;
	}

	public Integer getExpertReportLimit() {
		return expertReportLimit;
	}

	public void setExpertReportLimit(Integer expertReportLimit) {
		this.expertReportLimit = expertReportLimit;
	}

	public Integer getCaseBase() {
		return caseBase;
	}

	public void setCaseBase(Integer caseBase) {
		this.caseBase = caseBase;
	}

	public Integer getCaseBaseLimit() {
		return caseBaseLimit;
	}

	public void setCaseBaseLimit(Integer caseBaseLimit) {
		this.caseBaseLimit = caseBaseLimit;
	}
	
	
	
	
	
}
