package com.zhxg.framework.base.curd;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 分页对象
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.curd.Pagination.java
 * <p>
 * Author: azmiu
 * <p>
 * Create Date: 2017年3月31日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public class Pagination {
    
    private Integer pageSize;
    private Integer pageNum;

    public Pagination(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

}
