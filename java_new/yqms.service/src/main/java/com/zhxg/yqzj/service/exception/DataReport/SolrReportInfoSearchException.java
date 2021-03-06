package com.zhxg.yqzj.service.exception.DataReport;

/**
 * <p>
 * CopyRright (c)2012-2016: haoran
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 自定义分类保存异常
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.exception.SolrReportInfoSearchException.java
 * <p>
 * Author: haoran
 * <p>
 * Create Date: 2017年3月23日
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
public class SolrReportInfoSearchException extends AddReportInfoException {
    
    /**
     * @Fields CLASSIFY_OPERATE_ERR_CODE : solr信息查询信息操作异常码
     */
    public static final String SOLR_ADD_ERR_CODE = "009";

    public SolrReportInfoSearchException(String msg) {
        super(SOLR_ADD_ERR_CODE, msg);
    }

    public SolrReportInfoSearchException() {

    }

}
