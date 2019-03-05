package com.zhxg.yqzj.service.exception.DataReport;

/**
 * <p>
 * CopyRright (c)2012-2016: haoran
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 自定义分类删除异常
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.exception.MobileCloudDelUserInfoException.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年7月23日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
@SuppressWarnings("serial")
public class ReportClassifyDelException extends ReportClassifyException {
    
    /**
     * @Fields CLASSIFY_OPERATE_ERR_CODE : 自定义数据分类操作异常码
     */
    public static final String CLASSIFY_OPERATE_ERR_CODE = "002";

    public ReportClassifyDelException(String msg) {
        super(CLASSIFY_OPERATE_ERR_CODE, msg);
    }

    public ReportClassifyDelException() {

    }

}
