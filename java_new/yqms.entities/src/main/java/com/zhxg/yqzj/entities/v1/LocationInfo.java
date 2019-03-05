package com.zhxg.yqzj.entities.v1;

import com.zhxg.framework.base.curd.impl.BaseEntity;

public class LocationInfo extends BaseEntity {
	
    /**
     * 地域id
     */
	private Integer id;
	/**
	 * 地域名称
	 */
	private String name;
	/**
	 * 父地域id
	 */
	private Integer parentId;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getParentId() {
        return parentId;
    }
    
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
	
	

}
