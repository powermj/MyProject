package com.zhxg.yqzj.entities.v1;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

import com.zhxg.framework.base.curd.impl.BaseEntity;

import io.swagger.annotations.ApiModelProperty;

public class Condition extends BaseEntity {
    
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @ApiModelProperty(value="是否入报",name="isRepeat",example="0:未入报，1：已入报")
    protected int isExport;
    
    @ApiModelProperty(value="媒体类型",name="sourceType",example="多选逗号分隔")
    protected String[] sourceType;
    
    @ApiModelProperty(value="倾向性",name="orientation",example="1：正面，2：负面，3：中性。多选逗号分隔")
    protected String[] orientation;
    
    @ApiModelProperty(value="分类(标签)ID",name="classifyIds",example="分类(标签)ID，多选逗号分隔")
    protected String[] classifyIds;
    
    @ApiModelProperty(value="发布时间范围",name="publishTimeType",example="8-八小时内 24-二十四小时内 7-七天内")
    protected String publishTimeType;
    
    @ApiModelProperty(value="发布自定义起始时间",name="publishBeginTime",example="发布自定义开始时间(2018-08-17 15:55:47)")
    protected String publishBeginTime;
    
    @ApiModelProperty(value="发布自定义结束时间",name="publishEndTime",example="发布自定义结束时间(2018-08-17 15:55:47)")
    protected String publishEndTime;
    
    @ApiModelProperty(value="入报时间范围",name="enterTimeType",example="8-八小时内 24-二十四小时内 7-七天内")
    protected String enterTimeType;
    
    @ApiModelProperty(value="入报开始时间",name="enterBeginTime",example="入报开始时间(2018-08-17 15:55:47)")
    protected String enterBeginTime;
    
    @ApiModelProperty(value="入报结束时间",name="enterEndTime",example="入报结束时间(2018-08-17 15:55:47)")
    protected String enterEndTime;
    
//    @ApiModelProperty(value="近期时间",name="recentlyTime",example="近期时间，单位：小时。（如，近七天=7*24=168）")
//    private int recentlyTime;
//    
//    @ApiModelProperty(value="专题ID",name="subjectId",example="专题ID，多选逗号分隔")
//    private String[] subjectId;
    
    
    public String[] getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = StringUtils.isEmpty(sourceType)?null:sourceType.split(",");
    }

    
    public int getIsExport() {
        return isExport;
    }

    
    public void setIsExport(int isExport) {
        this.isExport = isExport;
    }

    public String[] getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = StringUtils.isEmpty(orientation)?null:orientation.split(",");
    }
    
    public String[] getClassifyIds() {
        return classifyIds;
    }

    
    public void setClassifyIds(String classifyIds) {
        this.classifyIds = StringUtils.isEmpty(classifyIds)?null:classifyIds.split(",");
    }

    public String getPublishTimeType() {
        return publishTimeType;
    }

    
    public void setPublishTimeType(String publishTimeType) {
        this.publishTimeType = publishTimeType;
        if(!"0".equals(publishTimeType) && !StringUtils.isBlank(publishTimeType)){
            int distance = Integer.parseInt(publishTimeType);
            if("7".equals(publishTimeType)){
                distance = distance*24;
            }
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
            publishEndTime = sdf.format(cal.getTime());
            cal.add(Calendar.HOUR, -distance);
            publishBeginTime = sdf.format(cal.getTime());
        }
    }

    
    public String getPublishBeginTime() {
        return publishBeginTime;
    }

    
    public void setPublishBeginTime(String publishBeginTime) {
        this.publishBeginTime = publishBeginTime;
    }

    
    public String getPublishEndTime() {
        return publishEndTime;
    }

    
    public void setPublishEndTime(String publishEndTime) {
        this.publishEndTime = publishEndTime;
    }

    
    public String getEnterTimeType() {
        return enterTimeType;
    }

    
    public void setEnterTimeType(String enterTimeType) {
        this.enterTimeType = enterTimeType;
        if(!"0".equals(enterTimeType)&& !StringUtils.isBlank(enterTimeType) ){
            int distance = Integer.parseInt(enterTimeType);
            if("7".equals(enterTimeType)){
                distance = distance*24;
            }
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
            enterEndTime = sdf.format(cal.getTime());
            cal.add(Calendar.HOUR, -distance);
            enterBeginTime = sdf.format(cal.getTime());
        }
    }
    
    public String getEnterBeginTime() {
        return enterBeginTime;
    }

    
    public void setEnterBeginTime(String enterBeginTime) {
        this.enterBeginTime = enterBeginTime;
    }

    
    public String getEnterEndTime() {
        return enterEndTime;
    }

    
    public void setEnterEndTime(String enterEndTime) {
        this.enterEndTime = enterEndTime;
    }
//    public void setRecentlyTime(int recentlyTime) {
//        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
//        endTime = sdf.format(cal.getTime());
//        cal.add(Calendar.HOUR, -recentlyTime);
//        beginTime = sdf.format(cal.getTime());
//        this.recentlyTime = recentlyTime;
//    }

    
    
    
}
