package com.zhxg.framework.base.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: Restful结果返回Json结果类型
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.http.JsonResult.java
 * <p>
 * Author: azmiu
 * <p>
 * Create Date: 2017年2月27日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
@ApiIgnore
public class Result {

    @JsonInclude(Include.NON_NULL)
    @ApiModelProperty(value = "数据项", required = true)
    private Object data;
    @JsonInclude(Include.NON_DEFAULT)
    @ApiModelProperty(value = "当前页码", required = true)
    private int pageNum;
    @JsonInclude(Include.NON_DEFAULT)
    @ApiModelProperty(value = "每页条数", required = true)
    private int pageSize;
    @JsonInclude(Include.NON_DEFAULT)
    @ApiModelProperty(value = "共计条数", required = true)
    private long total;
    
    private int maxId;

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getMaxId() {
        return maxId;
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }
}
