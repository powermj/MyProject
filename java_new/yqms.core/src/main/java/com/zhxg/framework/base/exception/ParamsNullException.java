package com.zhxg.framework.base.exception;



/**
 * <p>Description: 参数为空异常类</p>
 * @author zyl
 * @date 2017年11月14日
 * @version 1.0
 */
public class ParamsNullException extends BusinessException {
    /**
     * 
     */
    private static final long serialVersionUID = -4045800347959928961L;
    /**
     * @Fields USER_SERVER_CODE : 异常码，参数为空，具体异常由具体异常类拼接
     */
    public static final String DEFAULT_CODE = "000";
    public static final String ERROR_CODE = "002";

    public ParamsNullException(String msg) {
        super(DEFAULT_CODE + ERROR_CODE, msg);
    }

    public ParamsNullException() {

    }
}
