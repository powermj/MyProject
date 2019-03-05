package com.zhxg.yqzj.entities.v1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.utils.DateUtil;

import io.swagger.annotations.ApiModelProperty;

public class ReportVariable extends BaseEntity {

    /**
     * 主键
     */
    @ApiModelProperty(value="主键",name="id",example="1")
    private int id;
    
    @ApiModelProperty(value="用户id",name="userId",example="用户账户id")
    private int userId;

    @ApiModelProperty(value="变量名",name="initName",example="变量名")
    private String initName;

    @ApiModelProperty(value="变量类型",name="initType",example="1 日报，2 周报,3 自增")
    private int initType;

    @ApiModelProperty(value="报告期数初始值",name="initValue",example="1")
    private int initValue;
    
    @ApiModelProperty(value="报告总期数初始值",name="initTotal",example="1")
    private int initTotal;

    @ApiModelProperty(value="起始时间戳（用于周期变量）",name="initTimeStamp",example="起始时间戳（150123456789）")
    private Long initTimeStamp;

    @ApiModelProperty(value="变量增长周期（用于周期变量）",name="timtInterval",example="变量增长周期（3600*24*1000）")
    private Long timeInterval;
    
    @ApiModelProperty(value="变时间差（用于日期变量）",name="timeDifference",example="±3600*24*1000")
    private Long timeDifference;
    
    @ApiModelProperty(value="日期格式（用于日期变量）",name="dateForamt",example="yyyy-MM-dd HH:ss:mm")
    private String dateForamt = "yyyy-MM-dd HH:ss:mm";

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

    public String getInitName() {
        return initName;
    }

    
    public void setInitName(String initName) {
        this.initName = initName;
    }

    
    public int getInitType() {
        return initType;
    }

    
    public void setInitType(int initType) {
        this.initType = initType;
    }

    public int getInitValue() {
        return initValue;
    }

    
    public void setInitValue(int initValue) {
        this.initValue = initValue;
    }

    public int getInitTotal() {
        return initTotal;
    }

    
    public void setInitTotal(int initTotal) {
        this.initTotal = initTotal;
    }

    public Long getInitTimeStamp() {
        return initTimeStamp;
    }

    public void setInitTimeStamp(Long initTimeStamp) {
        this.initTimeStamp = initTimeStamp;
    }

    public Long getTimeInterval() {
        return timeInterval;
    }

    
    public void setTimeInterval(Long timeInterval) {
        this.timeInterval = timeInterval;
    }

    public Long getTimeDifference() {
        return timeDifference;
    }

    public void setTimeDifference(Long timeDifference) {
        this.timeDifference = timeDifference;
    }

    public String getDateForamt() {
        return dateForamt;
    }

    public void setDateForamt(String dateForamt) {
        this.dateForamt = dateForamt;
    }

    public String getValue() {

        switch (initType) {
            case 1: {
                int dayValue = this.getDayValue() + initTotal;
                return dayValue+"";
            }
            case 2: {
                return (initValue+(System.currentTimeMillis()-initTimeStamp)/timeInterval)+"";
            }
            case 3: {
                return initValue++ +"";
            }
            case 4: {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MILLISECOND, timeDifference.intValue());
                SimpleDateFormat sdf = new SimpleDateFormat(dateForamt);
                return sdf.format(cal.getTime());
            }
        }
        return "";
    }
    
    public String getTotal() {

        switch (initType) {
            case 1: {
                int dayValue = this.getDayValue() + initTotal;
                return dayValue+"";
            }
            case 2: {
                return (initTotal+(System.currentTimeMillis()-initTimeStamp)/timeInterval)+"";
            }
            case 3: {
                return initTotal++ +"";
            }
            case 4: {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MILLISECOND, timeDifference.intValue());
                SimpleDateFormat sdf = new SimpleDateFormat(dateForamt);
                return sdf.format(cal.getTime());
            }
        }
        return "";
    }
    //计算日报期数  按自然天
    private int getDayValue(){
        int days = 1;
        try {
            Date date = new Date(initTimeStamp);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dayDate = sdf.format(date);
            String nowDate = DateUtil.getLongDatedd();
            Date date3 = sdf.parse(dayDate);
            Date date4 = sdf.parse(nowDate);
            days = (int) ((date4.getTime() - date3.getTime()) / timeInterval);
        } catch (Exception e) {
            return days;
        }
        return days;
    }
}
