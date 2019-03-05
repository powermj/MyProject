package com.zhxg.yqzj.entities.v1;

import com.zhxg.framework.base.curd.impl.BaseEntity;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.entities
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: <移动云行业信息实体类>
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqzj.entities.v1.MobileCloudIndustryDepartmentInfo.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年7月21日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public class UserBaseInfo extends BaseEntity {

    /**
     * 自增主键id
     */
    private Integer kuUuid;
    /**
     * 用户id
     */
    private Integer kuId;
    /**
     * 状态类型
     */
    private String kuType;
    /**
     * 类型对应值
     */
    private String kuValue;
    
    public Integer getKuUuid() {
        return kuUuid;
    }
    
    public void setKuUuid(Integer kuUuid) {
        this.kuUuid = kuUuid;
    }
    
    public Integer getKuId() {
        return kuId;
    }
    
    public void setKuId(Integer kuId) {
        this.kuId = kuId;
    }
    
    public String getKuType() {
        return kuType;
    }
    
    public void setKuType(String kuType) {
        this.kuType = kuType;
    }
    
    public String getKuValue() {
        return kuValue;
    }
    
    public void setKuValue(String kuValue) {
        this.kuValue = kuValue;
    }
    
    

    
    
}
