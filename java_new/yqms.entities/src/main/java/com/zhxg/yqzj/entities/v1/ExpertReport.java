package com.zhxg.yqzj.entities.v1;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.serializer.JsonDateSerializer;

public class ExpertReport extends BaseEntity {

	/**
	 * 报表ID
	 */
	private int id;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 下载地址
	 */
	private String address;
	
	/**
	 * 生成时间
	 */
	@JsonSerialize(using = JsonDateSerializer.class)  
	private Date time;
	
	/**
     * 提交时间
     */
	@JsonSerialize(using = JsonDateSerializer.class)  
    private Date subtime;
	
	/**
	 * 所属用户ID
	 */
	private int userid;
	
	/**
	 * TODO [后期补充各种类型注释]
	 * 报表类型
	 */
	private String type;
	
	/**
     * 报告分类id
     */
	private Integer reportclassid;
	
	/**
	 * 专报关键词
	 */
	private String keyWords;
	
	/**
	 * 专报样例url
	 */
	private String sampleUrl;
	
	/**
	 * 专报样例文章
	 */
	private String sampleArticle;
	
	
	/**
     * 专报样例名称
     */
    private String caseName;
	
	
	/**
	 * 专报需求
	 */
	private String specialRequirements;

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getSampleUrl() {
		return sampleUrl;
	}

	public void setSampleUrl(String sampleUrl) {
		this.sampleUrl = sampleUrl;
	}

	public String getSampleArticle() {
		return sampleArticle;
	}

	public void setSampleArticle(String sampleArticle) {
		this.sampleArticle = sampleArticle;
	}

	public String getSpecialRequirements() {
		return specialRequirements;
	}

	public void setSpecialRequirements(String specialRequirements) {
		this.specialRequirements = specialRequirements;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

    
    public Date getSubtime() {
        return subtime;
    }

    
    public void setSubtime(Date subtime) {
        this.subtime = subtime;
    }

    
    public Integer getReportclassid() {
        return reportclassid;
    }

    
    public void setReportclassid(Integer reportclassid) {
        this.reportclassid = reportclassid;
    }

    
    public String getCaseName() {
        return caseName;
    }

    
    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }
	
	
}
