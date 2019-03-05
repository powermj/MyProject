package com.zhxg.yqzj.entities.v1;

import javax.persistence.Table;

import com.zhxg.framework.base.curd.impl.BaseEntity;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.entities
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户配置实体
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.entities.v1.UserConfig.java
 * <p>
 * Author: azmiu
 * <p>
 * Create Date: 2017年3月9日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
@Table(name = "WK_T_USERBASEINFO")
public class UserConfig extends BaseEntity {

    /**
     * @Fields userId : 客户ID
     */
    private String userId;

    /**
     * 用户配置KEY
     */
    private String key;
    
    /**
     * 用户配置VALUE
     */
    private String value;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
    
    


}
