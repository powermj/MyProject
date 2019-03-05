package com.zhxg.yqzj.service.v1;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.AppImportData;
import com.zhxg.yqzj.entities.v1.UsageStatistics;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.service
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: app导入附件信息接口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.service.v1.UserService.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2017年2月28日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public interface AppImportDataService extends BaseService<AppImportData> {

    public int importAppData(Map<String, Object> dataMap, MultipartFile file, String[] picArr);

    public int saveAppUsageStatistics(List<UsageStatistics> usageStatisticsList);
}
