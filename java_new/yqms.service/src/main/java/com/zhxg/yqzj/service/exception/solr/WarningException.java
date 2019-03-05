package com.zhxg.yqzj.service.exception.solr;

import com.zhxg.framework.base.exception.BusinessException;

public class WarningException extends BusinessException {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 195495667038139805L;
    /**
     * @Fields COLLECT_MODULE_CODE : 异常码，SOLR收藏夹，具体异常由具体异常类拼接
     */
    public static final String COLLECT_MODULE_CODE = "008016";

    public WarningException(String msg) {
        super(COLLECT_MODULE_CODE, msg);
    }

    public WarningException() {

    }

}
