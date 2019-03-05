package com.zhxg.yqzj.entities.v1;

import com.zhxg.framework.base.curd.impl.BaseEntity;

public class ConsultationType  extends BaseEntity {

	/**
	 * 咨询类型ID
	 */
	private int id;
	
	/**
	 * 咨询类型
	 */
	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
