package com.zhxg.yqzj.service.exception.solr;

import com.zhxg.framework.base.exception.SysException;

public class SolrException extends SysException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6833227594168293142L;
	
	/**
     * @Fields CASEBASE_MODULE_CODE : 异常码，solr模块，具体异常由具体异常类拼接
     */
    public static final String SOLR_MODULE_CODE = "008001";


    public SolrException() {
        super(SOLR_MODULE_CODE , "搜索服务器连接失败。");
    }

}
