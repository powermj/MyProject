package com.zhxg.framework.base.exception;

import com.alibaba.fastjson.JSONObject;
import com.zhxg.framework.base.constants.SysConstants;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 系统异常基类
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.exception.SysException.java
 * <p>
 * Author: azmiu
 * <p>
 * Create Date: 2017年3月23日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public class SysException extends Exception {

    private static final long serialVersionUID = 1905122041950251207L;

    private String errorCode;
    private JSONObject data;

    /**
     * @Fields SERVER_CODE : 异常码，系统级异常为1开头，具体异常由具体异常类拼接
     */
    public static final String TYPE_CODE = "1";

    public static final String MODULE_CODE = "000";

    public static final String SYSTEM_ERR_CODE = "001";

    private static final String DEFAULT_CODE = TYPE_CODE + MODULE_CODE;

    public SysException(String errorCode, String message, JSONObject data, Throwable e) {
        super(message, e);
        this.errorCode = errorCode;
        this.data = data;
    }

    public SysException(String message) {
        this(DEFAULT_CODE + SYSTEM_ERR_CODE, message, null, null);
    }

    public SysException(String errorCode, String message, JSONObject data) {
        this(DEFAULT_CODE + errorCode, message, data, null);
    }

    public SysException(String errorCode, String message) {
        this(DEFAULT_CODE + errorCode, message, null, null);
    }

    public SysException() {
        this(DEFAULT_CODE + SYSTEM_ERR_CODE, SysConstants._SYS_ERROR, null, null);
    }

    public SysException(Throwable e) {
        super(e);
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public JSONObject getData() {
        return this.data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }
}
