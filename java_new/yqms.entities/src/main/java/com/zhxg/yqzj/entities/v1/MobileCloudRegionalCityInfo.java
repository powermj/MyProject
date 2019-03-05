package com.zhxg.yqzj.entities.v1;

import java.util.List;

import com.zhxg.framework.base.curd.impl.BaseEntity;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.entities
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: <移动云地域信息实体类>
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqzj.entities.v1.MobileCloudRegionalCityInfo.java
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
public class MobileCloudRegionalCityInfo extends BaseEntity {

     private Integer cityId;
     
     private String cityName;
     
     private List<MobileCloudRegionalCountyInfo> countys;

    
    public Integer getCityId() {
        return cityId;
    }

    
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    
    public String getCityName() {
        return cityName;
    }

    
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    
    public List<MobileCloudRegionalCountyInfo> getCountys() {
        return countys;
    }


    
    public void setCountys(List<MobileCloudRegionalCountyInfo> countys) {
        this.countys = countys;
    }
    
}
