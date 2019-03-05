package com.zhxg.yqzj.entities.v1;

import javax.persistence.Table;

import com.zhxg.framework.base.curd.impl.BaseEntity;

@Table(name = "ms_source_type")
public class SourceType extends BaseEntity {
    
    /**
     * 媒体类型名称
     */
    private String sourceName;
    /**
     * 媒体类型代码
     */
    private String sourceCode;
    /**
     * 事件媒体类型计数
     */
    private Integer totalNum = 0;
    
    public String getSourceName() {
        return sourceName;
    }
    
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
    
    public String getSourceCode() {
        return sourceCode;
    }
    
    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }
    
    public Integer getTotalNum() {
        return totalNum;
    }
    
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }
    
    

}
