package com.zhxg.yqzj.service.exception.myfocus;
/**
 * <p>Description:重复操作异常 </p>
 * @author zyl
 * @date 2017年11月14日
 * @version 1.0
 */
public class RepeatOperateException extends MyFocusException {

    /**
     * 
     */
    private static final long serialVersionUID = -6138131799098031342L;
    
    
    /**
     * @Fields USER_SERVER_CODE : 异常码，参数为空，具体异常由具体异常类拼接
     */
    public static final String ERROR_CODE = "003";

    public RepeatOperateException(String msg) {
        super(ERROR_CODE, msg);
    }

    public RepeatOperateException() {
        super(ERROR_CODE, "重复操作");
    }

}
