package com.zhxg.framework.base.exception;

public class PermissionDeniedException extends SysException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static String errorCode = "004";
	protected Object data;
	public static final String TYPE_CODE = "1";

	public PermissionDeniedException(String message) {
		super(errorCode, message);
	}
	
	public PermissionDeniedException() {
        super(errorCode, "权限不足");
    }
}
