package com.zhxg.yqzj.entities.v1;

import com.zhxg.framework.base.curd.impl.BaseEntity;

public class EventAnalysisData extends BaseEntity {
    
    private String infoid;
    
    private String sourceType;
    
    private String ctime;
    
    private String collectTime;
     
    private String webname;
    
    private String title;
    
    private String url;
    
    private String author;
    
    private String weiboAuthorId;
    
    private int orientation;
    
    private String referUrl;
    
    private String summary;
    
    private String content;
    
    private String keyword;
    
    private String hotword;
    
    private String simhash;
    
    private String infoState;
    
    private int forwardCount;
    
    private int replyCount;
    
    private int visitCount;

    private int importanceWeight;
    
    private int isWarning;
    
    private int isRead;
    
    private int isAttention;
    
    private int reReport;
    
    private String extendField;
    
    private String infoContent;
    
    private String mainContent;
    
    private String prevAuthor;
    
    private String allAuthor;
    
    private int repeatCount;
    
    private int isBriefing;
    
    private int isReport;
    
    private String orientationDesc;
    
    private int id;
    
    private String regionId;
    
    private String regionName;
    
    private String verified;
    
    
    public int getRepeatCount() {
        return this.repeatCount;
    }

    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    public String getInfoid() {
        return this.infoid;
    }

    public void setInfoid(String infoid) {
        this.infoid = infoid;
    }

    public String getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(String sourceType) {
        switch(sourceType){
            case"01":{
                this.sourceType = "网媒";
                break;
            }
            case"02":{
                this.sourceType = "论坛";
                break;
            }
            case"03":{
                this.sourceType = "博客";
                break;
            }
            case"04":{
                this.sourceType = "微博";
                break;
            }
            case"05":{
                this.sourceType = "报刊";
                break;
            }
            case"06":{
                this.sourceType = "微信";
                break;
            }
            case"07":{
                this.sourceType = "视频";
                break;
            }
            case"09":{
                this.sourceType = "APP";
                break;
            }
            case"10":{
                this.sourceType = "评论";
                break;
            }
            case"99":{
                this.sourceType = "其他";
                break;
            }
        }
        
    }

    public String getCtime() {
        return this.ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getCollectTime() {
        return this.collectTime;
    }

    public void setCollectTime(String collectTime) {
        this.collectTime = collectTime;
    }

    public String getWebname() {
        return this.webname;
    }

    public void setWebname(String webname) {
        this.webname = webname;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getWeiboAuthorId() {
        return this.weiboAuthorId;
    }

    public void setWeiboAuthorId(String weiboAuthorId) {
        this.weiboAuthorId = weiboAuthorId;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public String getReferUrl() {
        return this.referUrl;
    }

    public void setReferUrl(String referUrl) {
        this.referUrl = referUrl;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getHotword() {
        return this.hotword;
    }

    public void setHotword(String hotword) {
        this.hotword = hotword;
    }

    public String getSimhash() {
        return this.simhash;
    }

    public void setSimhash(String simhash) {
        this.simhash = simhash;
    }

    public String getInfoState() {
        return this.infoState;
    }

    public void setInfoState(String infoState) {
        this.infoState = infoState;
    }

    public int getForwardCount() {
        return this.forwardCount;
    }

    public void setForwardCount(int forwardCount) {
        this.forwardCount = forwardCount;
    }

    public int getReplyCount() {
        return this.replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getVisitCount() {
        return this.visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public int getReReport() {
        return this.reReport;
    }

    public void setReReport(int reReport) {
        this.reReport = reReport;
    }

    public int getImportanceWeight() {
        return this.importanceWeight;
    }

    public void setImportanceWeight(int importanceWeight) {
        this.importanceWeight = importanceWeight;
    }

    public int getIsWarning() {
        return this.isWarning;
    }

    public void setIsWarning(int isWarning) {
        this.isWarning = isWarning;
    }

    public int getIsRead() {
        return this.isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public int getIsAttention() {
        return this.isAttention;
    }

    public void setIsAttention(int isAttention) {
        this.isAttention = isAttention;
    }

    public String getExtendField() {
        return this.extendField;
    }

    public void setExtendField(String extendField) {
        this.extendField = extendField;
    }

    public String getInfoContent() {
        return this.infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }

    public String getMainContent() {
        return this.mainContent;
    }

    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }

    public String getPrevAuthor() {
        return this.prevAuthor;
    }

    public void setPrevAuthor(String prevAuthor) {
        this.prevAuthor = prevAuthor;
    }

    public String getAllAuthor() {
        return this.allAuthor;
    }

    public void setAllAuthor(String allAuthor) {
        this.allAuthor = allAuthor;
    }

    
    public int getIsBriefing() {
        return this.isBriefing;
    }

    
    public void setIsBriefing(int isBriefing) {
        this.isBriefing = isBriefing;
    }

    
    public int getIsReport() {
        return this.isReport;
    }

    
    public void setIsReport(int isReport) {
        this.isReport = isReport;
    }
    
    
    public int getId() {
        return this.id;
    }

    
    public void setId(int id) {
        this.id = id;
    }
    
    
    public String getRegionId() {
        return this.regionId;
    }

    
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
    
    
    public String getRegionName() {
        return this.regionName;
    }

    
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getVerified() {
        return this.verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getOrientationDesc() {
        switch(this.orientation){
            case 1: this.orientationDesc="正面";break;
            case 2: this.orientationDesc="负面";break;
            case 3: this.orientationDesc="中性";break;
            default: this.orientationDesc = "中性";
        }
        return this.orientationDesc;
    }

    public void setOrientationDesc(String orientationDesc) {
        this.orientationDesc = orientationDesc;
    }
    
}
