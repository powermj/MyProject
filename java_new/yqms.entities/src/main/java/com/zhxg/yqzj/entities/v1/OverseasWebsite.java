package com.zhxg.yqzj.entities.v1;

import com.zhxg.framework.base.curd.impl.BaseEntity;

public class OverseasWebsite extends BaseEntity{
    
    private int id;
    
    private String webName;
    
    private String url;
    
    private String className;
    
    private int classid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }
    
    

}
