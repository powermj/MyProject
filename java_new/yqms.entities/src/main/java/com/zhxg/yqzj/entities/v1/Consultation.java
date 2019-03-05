package com.zhxg.yqzj.entities.v1;

import java.util.Date;

import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.serializer.JsonDateSerializer;

@Table(name = "zj_consultation")
public class Consultation extends BaseEntity {

	/**
	 * 咨询申请单ID（自增）
	 */
	private Integer id;
	
	/**
	 * 申请人userid
	 */
	private Integer userid;
	
	/**
     * 专家名称
     */
    private String name;
	/**
	 * 申请专家ID
	 */
	private Integer expertId;
	
	/**
	 * 咨询类型 1：电话咨询 2：视频会议 3：方案服务 4：现场支持， 多个逗号隔开
	 */
	private String type;
	
	/**
	 * 咨询描述
	 */
	private String describe;
	
	/**
     * 咨询事件名称
     */
    private String eventName;
    
    /**
     * 咨询关键词
     */
    private String keyword;
    
    /**
     * 咨询样例URL
     */
    private String samplesUrl;
    
    /**
     * 咨询样例文章
     */
    private String samplesArticle;
	
	/**
	 * 申请单处理状态
	 */
	private Integer status;
	
	/**
	 * 被申请专家的状态 1 正常 0 删除
	 */
	private Integer state;
	
	/**
	 * 申请时间
	 */
	@JsonSerialize(using = JsonDateSerializer.class)  
	private Date ctime;
	
	/**
	 * 交付时间
	 */
	@JsonSerialize(using = JsonDateSerializer.class)  
	private Date deliverTime;
	
	

	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getExpertId() {
		return expertId;
	}

	public void setExpertId(Integer expertId) {
		this.expertId = expertId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}


	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	
	public Date getDeliverTime() {
		return deliverTime;
	}

	public void setDeliverTime(Date deliverTime) {
		this.deliverTime = deliverTime;
	}

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }


    public String getEventName() {
        return eventName;
    }


    public void setEventName(String eventName) {
        this.eventName = eventName;
    }


    public String getKeyword() {
        return keyword;
    }


    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    public String getSamplesUrl() {
        return samplesUrl;
    }


    public void setSamplesUrl(String samplesUrl) {
        this.samplesUrl = samplesUrl;
    }


    public String getSamplesArticle() {
        return samplesArticle;
    }


    public void setSamplesArticle(String samplesArticle) {
        this.samplesArticle = samplesArticle;
    }
	
}
