package com.zhxg.yqzj.service.exception.eventanalysis;


/**
 * <p>
 * CopyRright (c)2012-2016: haoran
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
public class ParseExpressionException extends EventAnalysisException {

    protected static final String CODE = "003";

    public ParseExpressionException() {
        super(CODE, "表达式解析失败！");
    }

    public ParseExpressionException(String msg) {
        super(CODE, msg);
    }
}
