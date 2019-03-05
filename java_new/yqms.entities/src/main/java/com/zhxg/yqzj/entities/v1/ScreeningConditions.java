package com.zhxg.yqzj.entities.v1;

import org.apache.commons.lang3.StringUtils;

import com.zhxg.framework.base.curd.impl.BaseEntity;

public class ScreeningConditions extends BaseEntity{

	/**
	 * 用户id
	 */
	private String userId;
	
	/**
     * 推荐用户id
     */
    private String eventAnalysisSharedUserId;
    
    /**
     * 认证类型  1大 V -1群众
     */
    private String verified;
    
    /**
     * 事件分析id
     */
    private String eventAnalysisId;
    
    /**
     * 自定义筛选id
     */
    private String customFilter;
    
    /**
     * 开始时间
     */
    private String beginTime;
    
    /**
     * 结束时间
     */
    private String endTime;
    
    /**
     * 媒体来源类型
     */
    private String sourcetype;
    
    /**
     * 信息状态 1正常 x过滤
     */
    private String infoState;
    
    /**
     * 倾向性 1正 2负 3中
     */
    private String orientation;
    
    /**
     * 微博类型 1原贴 2转发 3带回复转发
     */
    private String wechatInfoType;
    
    /**
     * 排序
     */
    private String order;
    
    /**
     * 1：已读 0：未读
     */
    private String isRead;
    
    /**
     * 热词
     */
    private String hotWords;
    
    /**
     * 是否去重 1不去重 2去重
     */
    private String isRepeat;
    
 
    private Integer pageSize;
    
 
    private Integer pageNum;
    
    /**
     *  信源地域ID
     */
    private String sourceRegion;
    
    
    /**
     * 信源等级
     */
    private int[] sourceLevel;
    
    private String webName;
    
    public String getWebName() {
        return webName;
    }


    public void setWebName(String webName) {
        this.webName = webName;
    }


    public String getSourceRegion() {
        return sourceRegion;
    }


    public void setSourceRegion(String sourceRegion) {
        this.sourceRegion = sourceRegion;
    }


    public int[] getSourceLevel() {
        return sourceLevel;
    }


    public void setSourceLevel(String sourceLevel) {
        if(StringUtils.isNotBlank(sourceLevel)){
            String[] levels= sourceLevel.split(",");
            this.sourceLevel = new int[levels.length];
            for(int i= 0 ;i<levels.length;i++){
                this.sourceLevel[i] = Integer.parseInt(levels[i]);
            }
        }
    }


    public String getUserId() {
        return userId;
    }

    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    
    public String getEventAnalysisSharedUserId() {
        return eventAnalysisSharedUserId;
    }

    
    public void setEventAnalysisSharedUserId(String eventAnalysisSharedUserId) {
        this.eventAnalysisSharedUserId = eventAnalysisSharedUserId;
    }

    
    public String getVerified() {
        return verified;
    }

    
    public void setVerified(String verified) {
        this.verified = verified;
    }

    
    public String getEventAnalysisId() {
        return eventAnalysisId;
    }

    
    public void setEventAnalysisId(String eventAnalysisId) {
        this.eventAnalysisId = eventAnalysisId;
    }

    
    public String getCustomFilter() {
        return customFilter;
    }

    
    public void setCustomFilter(String customFilter) {
        this.customFilter = customFilter;
    }

    
    public String getBeginTime() {
        return beginTime;
    }

    
    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime.replace("-", "").replace(" ", "");
    }

    
    public String getEndTime() {
        return endTime;
    }

    
    public void setEndTime(String endTime) {
        this.endTime = endTime.replace("-", "").replace(" ", "");
    }

    
    public String getSourcetype() {
        return sourcetype;
    }

    
    public void setSourcetype(String sourcetype) {
        this.sourcetype = sourcetype;
    }

    
    public String getInfoState() {
        return infoState;
    }

    
    public void setInfoState(String infoState) {
        this.infoState = infoState;
    }

    
    public String getOrientation() {
        return orientation;
    }

    
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    
    public String getWechatInfoType() {
        return wechatInfoType;
    }

    
    public void setWechatInfoType(String wechatInfoType) {
        this.wechatInfoType = wechatInfoType;
    }

    
    public String getOrder() {
        return order;
    }

    
    public void setOrder(String order) {
        this.order = order;
    }

    
    public String getIsRead() {
        return isRead;
    }

    
    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    
    public String getHotWords() {
        return hotWords;
    }

    
    public void setHotWords(String hotWords) {
        this.hotWords = hotWords;
    }

    
    public String getIsRepeat() {
        return isRepeat;
    }

    
    public void setIsRepeat(String isRepeat) {
        this.isRepeat = isRepeat;
    }


    
    public Integer getPageSize() {
        return pageSize;
    }


    
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    
    public Integer getPageNum() {
        return pageNum;
    }


    
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

	
	
	
	
	
}
