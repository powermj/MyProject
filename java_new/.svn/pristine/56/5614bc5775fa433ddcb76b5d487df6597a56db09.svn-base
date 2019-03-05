package com.zhxg.yqzj.service.v1;

import java.util.List;
import java.util.Map;

import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.ReportVariable;
import com.zhxg.yqzj.service.exception.DataReport.ReportClassifyRepeatException;
import com.zhxg.yqzj.service.exception.DataReport.ReportVariableDelException;
import com.zhxg.yqzj.service.exception.DataReport.ReportVariableSaveException;
import com.zhxg.yqzj.service.exception.DataReport.ReportVariableUpdateException;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.service
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 数据池数据分类业务接口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.service.v1.ReportClassifyService.java
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
public interface ReportVariableService extends BaseService<BaseEntity> {

    /**
     * 添加自定义变量
     * @return
     */
    int insertReportVariable(Map<String,Object> params) throws ReportVariableSaveException, ReportClassifyRepeatException;
    
    /**
     * 修改自定义变量
     *
     * @return
     */
    int updateReportVariable(Map<String,Object> params) throws ReportVariableUpdateException;
    
    /**
     * 删除自定义变量
     *
     * @return
     */
    int deleteReportVariableById(Map<String,Object> params) throws ReportVariableDelException;
    
    /**
     * 获取自定义变量
     *
     * @return
     */
    List<ReportVariable> getReportVariables(Map<String,Object> params);
    
    ReportVariable getReportVariable(Map<String,Object> params);
    
    
} 
