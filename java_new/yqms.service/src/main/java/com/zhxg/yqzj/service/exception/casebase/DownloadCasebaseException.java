package com.zhxg.yqzj.service.exception.casebase;

public class DownloadCasebaseException extends CasebaseException {

	/**
	* 
	*/
	private static final long serialVersionUID = 5642586106945836462L;

	/**
	 * 申请专家失败异常码
	 */
	protected static final String ERROR_CODE = "001";

	public DownloadCasebaseException() {
		super(ERROR_CODE, "download casebase failed.");
	}

	public DownloadCasebaseException(String msg) {
		super(ERROR_CODE, msg);
	}
}
