package com.zhxg.yqzj.entities.v1;

import java.util.Date;

import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.serializer.JsonDateSerializer;

@Table(name = "YQZB_T_TOPIC_MODULE_SUMMARY")
public class EventAnalysisModuleSummary extends BaseEntity {
	
	private int id;

	private int moduleId;
	
	private String eventId;
	
	private String summary;
	
	@JsonSerialize(using = JsonDateSerializer.class) 
	private Date ctime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
	
	
    
    
}
