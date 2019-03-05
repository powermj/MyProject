package com.zhxg.yqzj.entities.v1;

import java.util.Date;

import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.serializer.JsonDateSerializer;

@Table(name = "zj_casebase_infomation")
public class CaseBaseInfo extends BaseEntity {

    /**
     * 案例库信息id（自增）
     */
    private Integer id;
    
    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String casebaseAbstract;

    /**
     * 所属行业
     */
    private String industry;

    /**
     * 案例类型
     */
    private String caseType;

    /**
     * 涉事单位
     */
    private String relatedUnits;

    /**
     * 首发媒体
     */
    private String firstMedia;

    /**
     * 处置方式
     */
    private String disposalMethods;

    /**
     * 事后跟进
     */
    private String followup;

    /**
     * 回应内容
     */
    private String responseContent;

    /**
     * 事件时间
     */
    @JsonSerialize(using = JsonDateSerializer.class)  
    private Date caseTime;
    
    
    /**
     * 所属分类id
     */
    private Integer classId;
    
    /**
     * 案例库下载地址
     */
    private String exportUrl;
      
    /**
     * 下载时间
     */
    @JsonSerialize(using = JsonDateSerializer.class)  
    private Date loadTime;
    
    /**
     * 涉事地域
     */
    private String relatedRegion;
    
    /**
     * 用户id
     */
    private String userid;
    
    /**
     * 下载状态
     */
    private Integer status;
    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCasebaseAbstract() {
		return casebaseAbstract;
	}

	public void setCasebaseaAbstract(String casebaseAbstract) {
		this.casebaseAbstract = casebaseAbstract;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getRelatedUnits() {
		return relatedUnits;
	}

	public void setRelatedUnits(String relatedUnits) {
		this.relatedUnits = relatedUnits;
	}

	public String getFirstMedia() {
		return firstMedia;
	}

	public void setFirstMedia(String firstMedia) {
		this.firstMedia = firstMedia;
	}

	public String getDisposalMethods() {
		return disposalMethods;
	}

	public void setDisposalMethods(String disposalMethods) {
		this.disposalMethods = disposalMethods;
	}

	public String getFollowup() {
		return followup;
	}

	public void setFollowup(String followup) {
		this.followup = followup;
	}

	public String getResponseContent() {
		return responseContent;
	}

	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}

	public Date getCaseTime() {
		return caseTime;
	}

	public void setCaseTime(Date caseTime) {
		this.caseTime = caseTime;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getExportUrl() {
		return exportUrl;
	}

	public void setExportUrl(String exportUrl) {
		this.exportUrl = exportUrl;
	}

    public Date getLoadTime() {
        return loadTime;
    }

    
    public void setLoadTime(Date loadTime) {
        this.loadTime = loadTime;
    }

    
    public void setCasebaseAbstract(String casebaseAbstract) {
        this.casebaseAbstract = casebaseAbstract;
    }

    
    public String getRelatedRegion() {
        return relatedRegion;
    }

    
    public void setRelatedRegion(String relatedRegion) {
        this.relatedRegion = relatedRegion;
    }

    
    public String getUserid() {
        return userid;
    }

    
    public void setUserid(String userid) {
        this.userid = userid;
    }

    
    public Integer getStatus() {
        return status;
    }

    
    public void setStatus(Integer status) {
        this.status = status;
    }
    

	
	
}
