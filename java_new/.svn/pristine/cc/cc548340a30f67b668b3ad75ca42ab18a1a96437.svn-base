package com.zhxg.framework.base.utils;

import java.util.List;
/**
 * <p>Description: solr分页类</p>
 * @author zyl
 * @date 2017年11月9日
 * @version 1.0
 */
public class Page<T> {
    private int pageNum;
    private int pageSize;
    private int total;
    private List<T> beans;
    
    public Page(int pageNum,int pageSize,int total,List<T> beans){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.beans = beans;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getBeans() {
        return beans;
    }

    public void setBeans(List<T> beans) {
        this.beans = beans;
    }
}
