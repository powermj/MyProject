package com.zhxg.yqzj.service.exception.myfocus;
/**
 * <p>Description:关注的信息不存在异常 </p>
 * @author zyl
 * @date 2017年11月14日
 * @version 1.0
 */
public class MyFocusInfoNotFoundException extends MyFocusException{

    /**
     * 
     */
    private static final long serialVersionUID = 5678139082809283507L;

    protected static final String ERROR_CODE = "001";

    public MyFocusInfoNotFoundException() {
        super(ERROR_CODE, "关注信息不存在");
    }

    public MyFocusInfoNotFoundException(String msg) {
        super(ERROR_CODE, msg);
    }

}
