package com.zhxg.framework.base.exception;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户模块异常基类
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.exception.UserException.java
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
public class UserException extends BusinessException {

    /**
     * @Fields USER_SERVER_CODE : 异常码，用户模块，具体异常由具体异常类拼接
     */
    public static final String USER_MODULE_CODE = "001";

    public UserException(String code, String msg) {
        super(USER_MODULE_CODE + code, msg);
    }

    public UserException() {

    }
}
