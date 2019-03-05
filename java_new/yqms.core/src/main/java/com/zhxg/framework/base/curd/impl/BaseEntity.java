package com.zhxg.framework.base.curd.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 基础实体
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.curd.impl.BaseEntity.java
 * <p>
 * Author: azmiu
 * <p>
 * Create Date: 2017年2月28日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
@JsonInclude(Include.NON_NULL)
public class BaseEntity {

	@ApiModelProperty(hidden = true)
	protected String _DBNAME;

	@ApiModelProperty(hidden = true)
	protected String _KUID;

	@ApiModelProperty(hidden = true)
	protected String _ACCOUNTID;

	@ApiModelProperty(hidden = true)
	protected String _DBHOST;

	@ApiModelProperty(hidden = true)
	protected String shareUserId;
	
	@ApiModelProperty(hidden = true)
	protected String _OTHER_DBNAME;
	
	@ApiModelProperty(hidden = true)
	protected String _OTHER_DBHOST;

    public String get_DBNAME() {
        return _DBNAME;
    }

    public void set_DBNAME(String _DBNAME) {
        this._DBNAME = _DBNAME;
    }

    public String get_KUID() {
        return _KUID;
    }

    public void set_KUID(String _KUID) {
        this._KUID = _KUID;
    }

    public String get_ACCOUNTID() {
        return _ACCOUNTID;
    }

    public void set_ACCOUNTID(String _ACCOUNTID) {
        this._ACCOUNTID = _ACCOUNTID;
    }

    public String get_DBHOST() {
        return _DBHOST;
    }

    public void set_DBHOST(String _DBHOST) {
        this._DBHOST = _DBHOST;
    }

    public String getShareUserId() {
        return shareUserId;
    }

	public void setShareUserId(String shareUserId) {
		this.shareUserId = shareUserId;
	}

	public String get_OTHER_DBNAME() {
		return _OTHER_DBNAME;
	}

	public void set_OTHER_DBNAME(String _OTHER_DBNAME) {
		this._OTHER_DBNAME = _OTHER_DBNAME;
	}

	public String get_OTHER_DBHOST() {
		return _OTHER_DBHOST;
	}

	public void set_OTHER_DBHOST(String _OTHER_DBHOST) {
		this._OTHER_DBHOST = _OTHER_DBHOST;
	}

}
