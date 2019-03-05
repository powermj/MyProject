package com.zhxg.yqzj.service.exception.report;

import com.zhxg.framework.base.exception.BusinessException;

public class ReportException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6833227594168293742L;
	
	/**
     * @Fields USER_SERVER_CODE : 异常码，报告模块，具体异常由具体异常类拼接
     */
    public static final String REPORT_MODULE_CODE = "003";

    public ReportException(String code, String msg) {
        super(REPORT_MODULE_CODE + code, msg);
    }

    public ReportException() {

    }

}
