package com.zhxg.yqzj.entities.v1;

import java.util.List;

import javax.persistence.Table;

import com.zhxg.framework.base.curd.impl.BaseEntity;

@Table(name = "SOLR_T_COLLECTINFO")
public class CollectInfo extends BaseEntity {

    /**
     * 自增主键Id
     */
    private int id;
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 收藏夹Id
     */
    private String collectId;
    /**
     * 收藏夹名称
     */
    private String collectName;

    /**
     * 收藏夹内词
     */
    private List<CollectInfoCnt> collects;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    public String getCollectName() {
        return collectName;
    }

    public void setCollectName(String collectName) {
        this.collectName = collectName;
    }

    public List<CollectInfoCnt> getCollects() {
        return collects;
    }

    public void setCollects(List<CollectInfoCnt> collects) {
        this.collects = collects;
    }

}
