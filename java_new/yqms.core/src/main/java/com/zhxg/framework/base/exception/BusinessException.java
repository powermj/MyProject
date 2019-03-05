package com.zhxg.framework.base.exception;

import com.alibaba.fastjson.JSONObject;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 业务异常基类
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.exception.BusinessException.java
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
@SuppressWarnings("serial")
public class BusinessException extends Exception {

    protected String errorCode;
    protected JSONObject data;

    /**
     * @Fields SERVER_CODE : 异常码，业务级异常为2开头，具体异常由具体异常类拼接
     */
    public static final String TYPE_CODE = "2";

    public BusinessException(String errorCode, String message, JSONObject data, Throwable e) {
        super(message, e);
        this.errorCode = errorCode;
        this.data = data;
    }

    public BusinessException(String errorCode, String message, JSONObject data) {
        this(errorCode, message, data, null);
    }

    public BusinessException(String errorCode, String message) {
        this(TYPE_CODE + errorCode, message, null, null);
    }

    public BusinessException() {

    }

    public BusinessException(Throwable e) {
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
