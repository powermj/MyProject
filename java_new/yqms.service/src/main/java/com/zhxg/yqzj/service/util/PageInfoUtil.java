package com.zhxg.yqzj.service.util;

import org.apache.poi.ss.formula.functions.T;

import com.github.pagehelper.PageInfo;

public class PageInfoUtil extends PageInfo<T>{
    
    public PageInfoUtil(PageInfo pageInfo,int maxId) {
        super();
        this.setEndRow(pageInfo.getEndRow());
        this.setFirstPage(pageInfo.getFirstPage());
        this.setHasNextPage(pageInfo.isHasNextPage());
        this.setHasPreviousPage(pageInfo.isHasPreviousPage());
        this.setIsFirstPage(pageInfo.isIsFirstPage());
        this.setIsLastPage(pageInfo.isIsLastPage());
        this.setLastPage(pageInfo.getLastPage());
        this.setList(pageInfo.getList());
        this.setNavigateFirstPage(pageInfo.getNavigateFirstPage());
        this.setNavigateLastPage(pageInfo.getNavigateFirstPage());
        this.setNavigatepageNums(pageInfo.getNavigatepageNums());
        this.setNavigatePages(pageInfo.getNavigatePages());
        this.setNextPage(pageInfo.getNextPage());
        this.setPageNum(pageInfo.getPageNum());
        this.setPages(pageInfo.getPages());
        this.setPageSize(pageInfo.getPageSize());
        this.setPrePage(pageInfo.getPrePage());
        this.setSize(pageInfo.getSize());
        this.setStartRow(pageInfo.getStartRow());
        this.setTotal(pageInfo.getTotal());
        this.maxId = maxId;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private int maxId;

    public int getMaxId() {
        return maxId;
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }
    
    
    
}
