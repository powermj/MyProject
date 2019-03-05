package com.zhxg.framework.base.exception;

public class ParameterException extends SysException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static String errorCode = "003";
	protected Object data;
	public static final String TYPE_CODE = "1";

	public ParameterException(String message) {
		super(errorCode, message);
	}
	
	public ParameterException() {
        super(errorCode, "参数非法");
    }
}
