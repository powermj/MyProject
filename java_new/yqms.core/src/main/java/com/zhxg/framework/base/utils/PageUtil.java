package com.zhxg.framework.base.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.zhxg.framework.base.curd.Pagination;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 分页工具类
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.utils.PageUtil.java
 * <p>
 * Author: azmiu
 * <p>
 * Create Date: 2017年3月28日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public class PageUtil {

    /**
     * 构造分页参数
     * 默认查询第一页，每页10条
     * 
     * @param request
     * @return
     */
    public static Pagination getPageInfo(HttpServletRequest request) {
        Integer offset = null;
        Integer limit = null;

        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");

        if (!StringUtils.isEmpty(pageNum)) {
            offset = Integer.valueOf(pageNum);
        } else {
            offset = 0;
        }
        if (!StringUtils.isEmpty(pageSize)) {
            limit = Integer.valueOf(pageSize);
        } else {
            limit = 10;
        }
        return new Pagination(offset, limit);
    }
}
