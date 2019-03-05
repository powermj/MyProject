package com.zhxg.yqzj.entities.v1;

import java.util.Map;

import javax.persistence.Table;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.zhxg.framework.base.curd.impl.BaseEntity;

import io.swagger.annotations.ApiModelProperty;

@Table(name = "YQZB_T_TOPIC")
public class EventAnalysis extends BaseEntity {
	
	private String uuid;

	private Integer userId;
	
	private String topicName;
	
	private String beginTime;
	
	private String endTime;
	
	private String closeTime;
	
	private String interval;
	
	private String status;
	
	private String name;
	
	private String creatTime;
	
	private String summary;
	
    private Integer isShared;
    
    private String sharedTime;
    
    private String cancelSharedTime;
    
    private String words;
    
    //时间信息数量
    private Integer infoTotalCount;
    //第一个关键词
    private String firstKeyWords="";
    //第二个关键词
    private String  theSecondKeyWords;
    //排除词
    private String   exclusionWords;
    
    private String   type;
    
    private Integer   topicSharedUserId;
    
    private String ids;
    //关注或简报分类id
    private String classId;
    
    private Integer  orientation;
    
    //pc快照url
    private String snapshotUrl;
    
    //app快照url
    private String appSnapshotUrl;
    
    private Map<String,Object> sourceTypeNumMap;
    
    // 封面图片链接
    private String faceImgUrl;

    /**
     * 表达式
     */
    @ApiModelProperty(value="高级表达式",name="userid",example="高级表达式")
    private String expression;
    
    /**
     * 是否为高级事件
     */
    @ApiModelProperty(value="是否为高级事件",name="userid",example="0 普通事件（默认）,1 高级事件")
    private String isLogic = "0";
    
    /**
     * 否定词
     */
    @ApiModelProperty(value="否定词",name="userid",example="否定词（多个词空格分隔）")
    private String notWords;
    
    /**
     * 表达式
     */
    @ApiModelProperty(value="高级表达式",name="userid",example="高级表达式")
    private JSONArray expressionAsArray;
    
    public JSONArray getExpressionAsArray() {
        return this.expressionAsArray;
    }


    public void setExpressionAsArray(JSONArray expressionAsArray) {
        this.expressionAsArray = expressionAsArray;
    }


    public String getExpression() {
        return this.expression;
    }


    public void setExpression(String expression) {
        this.expression = StringEscapeUtils.unescapeHtml(expression);
    }


    public String getIsLogic() {
        return this.isLogic;
    }


    public void setIsLogic(String isLogic) {
        this.isLogic = StringUtils.isBlank(isLogic)?"0":isLogic;
    }


    public String getNotWords() {
        return this.notWords;
    }


    public void setNotWords(String notWords) {
        this.notWords = notWords;
    }


    public String getAppSnapshotUrl() {
        return this.appSnapshotUrl;
    }

    
    public void setAppSnapshotUrl(String appSnapshotUrl) {
        this.appSnapshotUrl = appSnapshotUrl;
    }

    public String getSnapshotUrl() {
        return this.snapshotUrl;
    }

    public void setSnapshotUrl(String snapshotUrl) {
        this.snapshotUrl = snapshotUrl;
    }

    public String getUuid() {
        return this.uuid;
    }

    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    
    public Integer getUserId() {
        return this.userId;
    }

    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    
    public String getTopicName() {
        return this.topicName;
    }

    
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    
    public String getBeginTime() {
        return this.beginTime;
    }

    
    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    
    public String getEndTime() {
        return this.endTime;
    }

    
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    
    public String getCloseTime() {
        return this.closeTime;
    }

    
    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    
    public String getInterval() {
        return this.interval;
    }

    
    public void setInterval(String interval) {
        this.interval = interval;
    }

    
    public String getStatus() {
        return this.status;
    }

    
    public void setStatus(String status) {
        this.status = status;
    }

    
    public String getName() {
        return this.name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public String getCreatTime() {
        return this.creatTime;
    }

    
    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    
    public Integer getIsShared() {
        return this.isShared;
    }

    
    public void setIsShared(Integer isShared) {
        this.isShared = isShared;
    }

    
    public String getSharedTime() {
        return this.sharedTime;
    }

    
    public void setSharedTime(String sharedTime) {
        this.sharedTime = sharedTime;
    }

    
    public String getCancelSharedTime() {
        return this.cancelSharedTime;
    }

    
    public void setCancelSharedTime(String cancelSharedTime) {
        this.cancelSharedTime = cancelSharedTime;
    }

    
    public String getWords() {
        return this.words;
    }

    
    public void setWords(String words) {
        this.words = words;
    }


    
    public String getSummary() {
        return this.summary;
    }


    
    public void setSummary(String summary) {
        this.summary = summary;
    }


    
    public Integer getInfoTotalCount() {
        return this.infoTotalCount;
    }


    
    public void setInfoTotalCount(Integer infoTotalCount) {
        this.infoTotalCount = infoTotalCount;
    }


    
    public String getFirstKeyWords() {
        return this.firstKeyWords;
    }


    
    public void setFirstKeyWords(String firstKeyWords) {
        this.firstKeyWords = firstKeyWords;
    }


    
    public String getTheSecondKeyWords() {
        return this.theSecondKeyWords;
    }


    
    public void setTheSecondKeyWords(String theSecondKeyWords) {
        this.theSecondKeyWords = theSecondKeyWords;
    }


    
    public String getExclusionWords() {
        return this.exclusionWords;
    }


    
    public void setExclusionWords(String exclusionWords) {
        this.exclusionWords = exclusionWords;
    }


    
    public String getType() {
        return this.type;
    }


    
    public void setType(String type) {
        this.type = type;
    }


    
    public Integer getTopicSharedUserId() {
        return this.topicSharedUserId;
    }


    
    public void setTopicSharedUserId(Integer topicSharedUserId) {
        this.topicSharedUserId = topicSharedUserId;
    }


    
    public String getIds() {
        return this.ids;
    }


    
    public void setIds(String ids) {
        this.ids = ids;
    }


    
    public String getClassId() {
        return this.classId;
    }


    
    public void setClassId(String classId) {
        this.classId = classId;
    }


    
    public Integer getOrientation() {
        return this.orientation;
    }


    
    public void setOrientation(Integer orientation) {
        this.orientation = orientation;
    }

    public Map<String, Object> getSourceTypeNumMap() {
        return this.sourceTypeNumMap;
    }

    public void setSourceTypeNumMap(Map<String, Object> sourceTypeNumMap) {
        this.sourceTypeNumMap = sourceTypeNumMap;
    }

    public String getFaceImgUrl() {
        return this.faceImgUrl;
    }

    public void setFaceImgUrl(String faceImgUrl) {
        this.faceImgUrl = faceImgUrl;
    }
   
    
    
}
