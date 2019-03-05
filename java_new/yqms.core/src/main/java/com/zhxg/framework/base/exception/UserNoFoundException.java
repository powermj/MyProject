package com.zhxg.framework.base.exception;

import com.zhxg.framework.base.exception.UserException;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户不存在异常控制
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.exception.UserNoFoundException.java
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
public class UserNoFoundException extends UserException {

    protected static final String USERNOFOUND = "001";

    public UserNoFoundException() {
        super(USERNOFOUND, "账号不存在。");
    }

    public UserNoFoundException(String msg) {
        super(USERNOFOUND, msg);
    }
}
