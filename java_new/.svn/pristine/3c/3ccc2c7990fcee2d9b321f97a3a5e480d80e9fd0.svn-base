package com.zhxg.yqzj.entities.v1;

import java.util.Date;

import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.serializer.JsonDateSerializer;

@Table(name = "zj_casebase_classification")
public class CaseBaseClassify extends BaseEntity {

	/**
	 * 案例库分类id（自增）
	 */
	private Integer id;
	
	/**
     * 父id
     */
    private Integer pId;
    
    /**
     * 分类名称
     */
    private String name;
    
    /**
     * 排序字段
     */
    private int level;
    
    /**
     * 创建时间
     */
    @JsonSerialize(using = JsonDateSerializer.class)  
    private Date ctime;

    
    public Integer getId() {
        return id;
    }

    
    public void setId(Integer id) {
        this.id = id;
    }

 
	
    public Integer getpId() {
        return pId;
    }


    
    public void setpId(Integer pId) {
        this.pId = pId;
    }


    
    public String getName() {
        return name;
    }


    
    public void setName(String name) {
        this.name = name;
    }


    public Date getCtime() {
		return ctime;
	}


	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}


    public int getLevel() {
        return level;
    }


    public void setLevel(int level) {
        this.level = level;
    }
	
}
