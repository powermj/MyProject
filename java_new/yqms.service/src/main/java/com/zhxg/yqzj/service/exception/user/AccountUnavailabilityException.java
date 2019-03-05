package com.zhxg.yqzj.service.exception.user;

import com.zhxg.framework.base.exception.UserException;

/**
 * <p>
 * CopyRright (c)2012-2016: haoran
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 账号不可用异常控制
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.exception.UserNoFoundException.java
 * <p>
 * Author: haoran
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
public class AccountUnavailabilityException extends UserException {

    protected static final String ERROR_CODE = "005";

    public AccountUnavailabilityException() {
        super(ERROR_CODE, "账号已被其他客户占用!");
    }

    public AccountUnavailabilityException(String msg) {
        super(ERROR_CODE, msg);
    }
}
