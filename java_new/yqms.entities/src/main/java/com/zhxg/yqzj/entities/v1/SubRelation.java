package com.zhxg.yqzj.entities.v1;

import com.zhxg.framework.base.curd.impl.BaseEntity;


public class SubRelation extends BaseEntity{
    private String ksId;
    private String ksPid;
    private String ksSid;
    private String kcUuid;
    private String kkName;
    private String isParent;
    private String kkType;
    private String route;
    private String tag;
    
    public String getKsId() {
        return ksId;
    }
    
    public void setKsId(String ksId) {
        this.ksId = ksId;
    }
    
    public String getKsPid() {
        return ksPid;
    }
    
    public void setKsPid(String ksPid) {
        this.ksPid = ksPid;
    }
    
    public String getKsSid() {
        return ksSid;
    }
    
    public void setKsSid(String ksSid) {
        this.ksSid = ksSid;
    }
    
    public String getKkName() {
        return kkName;
    }
    
    public void setKkName(String kkName) {
        this.kkName = kkName;
    }
    
    public String getIsParent() {
        return isParent;
    }
    
    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }
    
    public String getKkType() {
        return kkType;
    }
    
    public void setKkType(String kkType) {
        this.kkType = kkType;
    }

    
    public String getKcUuid() {
        return kcUuid;
    }

    
    public void setKcUuid(String kcUuid) {
        this.kcUuid = kcUuid;
    }

    
    public String getRoute() {
        return route;
    }

    
    public void setRoute(String route) {
        this.route = route;
    }

    
    public String getTag() {
        return tag;
    }

    
    public void setTags(String tag) {
        this.tag = tag;
    }
    
}
