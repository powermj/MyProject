package com.zhxg.yqzj.entities.v1;


import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.serializer.JsonDateSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="信息分享实体类",description="信息分享实体类")
public class ShareInfo extends BaseEntity {
  
   /**
     * 主键
     */
    @ApiModelProperty(value="主键",name="id",example="1",hidden=true)
    private int id;
    
    @ApiModelProperty(value="信息ID",name="userid",example="信息ID（不是KV_UUID）")
    private String krUuid;
   
   /**
     * 分享人ID
     */
    @ApiModelProperty(value="分享人ID",name="userid",example="分享人ID")
    private int userid;
    
    /**
     * 信息所属ID
     */
    @ApiModelProperty(value="信息所属人ID",name="infoUserid",example="信息所属人ID")
    private int infoUserid;
   
   /**
     * 信息URL
     */
    @ApiModelProperty(value="信息URL",name="url",example="信息URL")
    private String url;
   
   /**
     * 1 专题 ，2 事件
     */
    @ApiModelProperty(value="信息类型：1 专题 ，2 事件，3 预警",name="type",example="信息类型：1 专题 ，2 事件，3 预警")
    private int type;
    
    /**
     * 专题/事件ID
     */
    @ApiModelProperty(value="专题/事件ID",name="pid",example="专题/事件ID")
    private String pid;
    
    
    /**
     * 分享时间
     */
    @ApiModelProperty(value="分享时间",name="time",example="2018-7-21 17:18:49",hidden=true)
    @JsonSerialize(using = JsonDateSerializer.class)  
    private Date time;
    
    /**
     * 快照URL
     */
    @ApiModelProperty(value="快照URL",name="snapshotUrl",example="http://www.baidu.com/xxx.html",hidden=true)
    private String snapshotUrl;
    
    /**
     * 1 正常 ，0 删除
     */
    @ApiModelProperty(value="state:1 正常 ，0 删除",name="state",example="1",hidden=true)
    private int state = 1;
    
    /**
     * WK_T_APPEDITION.KA_UUID
     */
    @ApiModelProperty(value="21:舆情秘书",name="platform",example="21",hidden=true)
    private String platform;    
    
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
    

    public int getInfoUserid() {
        return infoUserid;
    }

    public void setInfoUserid(int infoUserid) {
        this.infoUserid = infoUserid;
        this.shareUserId = infoUserid+"";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSnapshotUrl() {
        return snapshotUrl;
    }

    public void setSnapshotUrl(String snapshotUrl) {
        this.snapshotUrl = snapshotUrl;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getKrUuid() {
        return krUuid;
    }

    public void setKrUuid(String krUuid) {
        this.krUuid = krUuid;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
