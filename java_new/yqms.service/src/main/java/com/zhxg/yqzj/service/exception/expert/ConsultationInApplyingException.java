package com.zhxg.yqzj.service.exception.expert;

public class ConsultationInApplyingException extends ExpertException {

	/**
	* 
	*/
	private static final long serialVersionUID = 5642386106945836463L;

	/**
	 * 该专家已经在申请中异常
	 */
	protected static final String ERROR_CODE = "002";

	public ConsultationInApplyingException() {
		super(ERROR_CODE, "You have applied for advice from the expert.");
	}

	public ConsultationInApplyingException(String msg) {
		super(ERROR_CODE, msg);
	}
}
