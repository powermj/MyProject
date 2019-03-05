package com.zhxg.yqzj.service.exception.expert;

import com.zhxg.framework.base.exception.BusinessException;

public class ExpertException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6833227594168293742L;
	
	/**
     * @Fields USER_SERVER_CODE : 异常码，专家模块，具体异常由具体异常类拼接
     */
    public static final String EXPERT_MODULE_CODE = "002";

    public ExpertException(String code, String msg) {
        super(EXPERT_MODULE_CODE + code, msg);
    }

    public ExpertException() {

    }

}
