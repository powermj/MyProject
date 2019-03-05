package com.zhxg.yqzj.entities.v1;

import javax.persistence.Table;

import com.zhxg.framework.base.curd.impl.BaseEntity;

@Table(name = "SOLR_T_COLLECTINFOCNT")
public class CollectInfoCnt extends BaseEntity {

    /**
     * 自增主键ID
     */
    private int id;
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 对应收藏夹Id
     */
    private String contentId;
    /**
     * 对应收藏夹内词Id
     */
    private String cntId;
    /**
     * 收藏内词名称
     */
    private String contentName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getCntId() {
        return cntId;
    }

    public void setCntId(String cntId) {
        this.cntId = cntId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
