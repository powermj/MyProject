package com.zhxg.yqzj.service.exception.mobilecloud;

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
public class MobileCloudUserInfoLoseException extends MobileCloudException {
    
    /**
     * @Fields COLLECT_MODULE_CODE : 移动云用户信息异常码
     */
    public static final String MOBILECLOUD_USERERROR_CODE = "001";

    public MobileCloudUserInfoLoseException(String msg) {
        super(MOBILECLOUD_ERROR_CODE, msg);
    }

    public MobileCloudUserInfoLoseException() {

    }

}
