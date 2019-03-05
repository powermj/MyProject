package com.zhxg.yqzj.dao.v1;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.yqzj.entities.v1.ReportFileInfo;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.dao
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户模块持久层接口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.dao.v1.UserDao.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年4月24日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public interface ReportFileInfoDao extends BaseDao<ReportFileInfo> {

    /**
     * 获取数据列表 
     * @param params
     * @return
     */
    PageInfo<ReportFileInfo> getReportFileInfos(Map<String, Object> params,Pagination pageInfo);
    
    /**
     * 添加报告文件信息
     * @param params
     * @return
     */
    int insertReportFileInfo(Map<String,Object> params);
}
