package com.zhxg.yqzj.service.v1;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.AllReportData;
import com.zhxg.yqzj.service.exception.DataReport.DeleteReportInfoException;
import com.zhxg.yqzj.service.exception.DataReport.InsertReportInfoException;
import com.zhxg.yqzj.service.exception.DataReport.UpdateReportInfoException;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.service
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 报告数据池详细页面接口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.service.v1.ActiveMediaService.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年7月03日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public interface AllReportDataService extends BaseService<BaseEntity> {

    
    /**
     * 活跃媒体信息列表
     * @param params
     * @param pageInfo
     * @return
     */
    PageInfo<AllReportData> getAllReports(Map<String,Object> params,Pagination pageInfo);
    
    /**
     * 修改数据池信息倾向性
     * @param params
     * @return
     */
    int updateReportOrientations(Map<String,Object> params) throws UpdateReportInfoException; 
    
    /**
     * 修改数据池信息分类属性
     * @param params
     * @return
     */
    int updateReportClassifyIds(Map<String,Object> params) throws UpdateReportInfoException; 
    
    /**
     * 删除数据池信息
     * @param params
     * @return
     */
    int deleteReportInfo(Map<String,Object> params) throws DeleteReportInfoException;
    
    /**
     * 手动添加报告信息
     * @param params
     * @return
     */
    int insertReportBySelf(Map<String,Object> params) throws InsertReportInfoException;
    
    /**
     * 根据分类ID查询信息数量 
     * @param params
     * @return
     */
    int getReportByClassify(Map<String,Object> params);
    
    /**
     * 根据信息Id获取信息详情 
     * @param params
     * @return
     */
    AllReportData getInfoDetail(Map<String,Object> params);
}
