package com.zhxg.yqzj.service.v1;

import java.util.Map;

import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.service.exception.DataReport.TopicReportInfoSaveException;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.service
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 舆情浏览数据添加数据池接口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.service.v1.TopicReportInfoService.java
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
public interface TopicReportInfoService extends BaseService<BaseEntity> {

    /**
     * 保存事件数据到数据池
     *
     * @return
     */
    Map<String, Integer> saveTopicToReport(Map<String,Object> params)  throws TopicReportInfoSaveException;
    
} 
