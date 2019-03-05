package com.zhxg.yqzj.service.exception.mobilecloud;

import com.zhxg.framework.base.exception.BusinessException;

public class MobileCloudException extends BusinessException {
    
    private static final long serialVersionUID = 2314021560214118307L;
    
    /**
     * @Fields COLLECT_MODULE_CODE : 移动云业务异常码
     */
    public static final String MOBILECLOUD_ERROR_CODE = "019";

    public MobileCloudException(String code, String msg) {
        super(MOBILECLOUD_ERROR_CODE + code, msg);
    }

    public MobileCloudException() {

    }

}
