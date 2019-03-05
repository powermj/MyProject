package com.zhxg.yqzj.service.exception.subject;

public class SubjectNameRepeatException extends SubjectException {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    protected static final String ERROR_CODE = "001";

    public SubjectNameRepeatException() {
        super(ERROR_CODE, "专题名称重复!");
    }

    public SubjectNameRepeatException(String msg) {
        super(ERROR_CODE, msg);
    }
}
