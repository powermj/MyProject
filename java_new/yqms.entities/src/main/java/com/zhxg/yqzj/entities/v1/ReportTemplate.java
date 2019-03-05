package com.zhxg.yqzj.entities.v1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhxg.framework.base.curd.impl.BaseEntity;

public class ReportTemplate extends BaseEntity {
    
    private int id;
    
    private int userid;
    
    private int type;
    
    private JSONObject template;
    
    private String templateName;
    
    private String templateImg;

    private int templateType;
    
    private int reportSelected;
    
    public int getReportSelected() {
        return reportSelected;
    }

    public void setReportSelected(int reportSelected) {
        this.reportSelected = reportSelected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public JSONObject getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = JSON.parseObject(template);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    
    public String getTemplateName() {
        return templateName;
    }

    
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    
    public String getTemplateImg() {
        return templateImg;
    }

    
    public void setTemplateImg(String templateImg) {
        this.templateImg = templateImg;
    }

    
    public int getTemplateType() {
        return templateType;
    }

    
    public void setTemplateType(int templateType) {
        this.templateType = templateType;
    }

    

}
