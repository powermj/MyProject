package com.zhxg.yqzj.entities.v1;



import com.zhxg.framework.base.curd.impl.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="APP使用情况监测",description="APP使用情况监测")
public class UsageStatistics extends BaseEntity {
  
   /**
     * 主键
     */
    @ApiModelProperty(value="主键",name="id",example="1",hidden=true)
    private int id;
    
    @ApiModelProperty(value="app包名",name="packageName",example="app包名")
    private String packageName;
   
   /**
     * 产品名
     */
    @ApiModelProperty(value="产品名",name="label",example="产品名")
    private String label;
    
    /**
     * 使用次数
     */
    @ApiModelProperty(value="使用次数",name="frequency",example="使用次数")
    private int frequency;
   
   /**
     * 使用时长（秒）
     */
    @ApiModelProperty(value="使用时长（秒）",name="length",example="使用时长（秒）")
    private String length;
    
    /**
     * 监测日期（yyyy-MM-dd）
     */
    @ApiModelProperty(value="监测日期（yyyy-MM-dd）",name="date",example="监测日期（yyyy-MM-dd）")
    private String date;
    
    /**
     * 用户ID
     */
    @ApiModelProperty(value="用户ID",name="userId",example="用户ID")
    private String userId;
    
    /**
     * 设备名
     */
    @ApiModelProperty(value="设备名",name="deviceName",example="设备名")
    private String deviceName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userid) {
        this.userId = userid;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
    
}
