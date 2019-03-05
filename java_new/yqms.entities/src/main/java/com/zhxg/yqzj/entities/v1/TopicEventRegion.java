package com.zhxg.yqzj.entities.v1;

import com.zhxg.framework.base.curd.impl.BaseEntity;


public class TopicEventRegion extends BaseEntity {

    
    private Integer serialNo;
    /**
     * 关联事件id
     */
    private String uuId;
    /**
     * 媒体来源
     */
    private String knSite;
    /**
     * 文章来源域名
     */
    private String webNameDomain;
    /**
     * 媒体来源数量
     */
    private Integer siteNum;
    /**
     * 媒体类型
     */
    private String knType;
    /**
     * 媒体类型中文名称
     */
    private String knTypeName;
    /**
     * 媒体类型数量
     */
    private Integer typeNum;
    /**
     * 地域
     */
    private String regionId;
    /**
     * 地域名称
     */
    private String regionName;
    
    
    
    
    public String getRegionName() {
        return regionName;
    }


    
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }


    public Integer getSerialNo() {
        return serialNo;
    }

    
    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public String getUuId() {
        return uuId;
    }
    
    public void setUuId(String uuId) {
        this.uuId = uuId;
    }
    
    public String getKnSite() {
        return knSite;
    }
    
    public void setKnSite(String knSite) {
        this.knSite = knSite;
    }
    
    public Integer getSiteNum() {
        return siteNum;
    }
    
    public void setSiteNum(Integer siteNum) {
        this.siteNum = siteNum;
    }
    
    public String getKnType() {
        return knType;
    }
    
    public void setKnType(String knType) {
        this.knType = knType;
    }
    
    public Integer getTypeNum() {
        return typeNum;
    }
    
    public void setTypeNum(Integer typeNum) {
        this.typeNum = typeNum;
    }
    
    public String getRegionId() {
        return regionId;
    }
    
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }


    
    public String getKnTypeName() {
        return knTypeName;
    }


    
    public void setKnTypeName(String knTypeName) {
        this.knTypeName = knTypeName;
    }



    
    public String getWebNameDomain() {
        return webNameDomain;
    }



    
    public void setWebNameDomain(String webNameDomain) {
        this.webNameDomain = webNameDomain;
    }
    
    
}
