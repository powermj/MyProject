package com.zhxg.yqzj.entities.v1;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.serializer.JsonDateSerializer;

import io.swagger.annotations.ApiModelProperty;

public class ReportFileInfo extends BaseEntity {

    @ApiModelProperty(value="主键",name="id",example="1")
    private int id;
    
    @ApiModelProperty(value="用户id",name="userId",example="用户账户id")
    private int userId;

    @ApiModelProperty(value="报告名称",name="reportFileName",example="报告名称")
    private String reportFileName;

    @ApiModelProperty(value="报告生成时间",name="enterTime",example="报告生成时间")
    @JsonSerialize(using = JsonDateSerializer.class)  
    private Date enterTime;
    
    @ApiModelProperty(value="报告下载地址",name="downLoadUrl",example="报告下载地址")
    private String downLoadUrl;

    
    public int getId() {
        return id;
    }

    
    public void setId(int id) {
        this.id = id;
    }

    
    public int getUserId() {
        return userId;
    }

    
    public void setUserId(int userId) {
        this.userId = userId;
    }

    
    public String getReportFileName() {
        return reportFileName;
    }

    
    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName;
    }

    
    public Date getEnterTime() {
        return enterTime;
    }

    
    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }


    
    public String getDownLoadUrl() {
        return downLoadUrl;
    }


    
    public void setDownLoadUrl(String downLoadUrl) {
        this.downLoadUrl = downLoadUrl;
    }

    
}
