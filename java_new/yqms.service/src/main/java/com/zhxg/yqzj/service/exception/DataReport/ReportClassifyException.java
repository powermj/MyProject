package com.zhxg.yqzj.service.exception.DataReport;

import com.zhxg.framework.base.exception.BusinessException;

public class ReportClassifyException extends BusinessException {
    
    private static final long serialVersionUID = -6600185323601395497L;
    /**
     * @Fields CLASSIFY_OPERATE_ERR_CODE : 自定义数据分类异常码
     */
    public static final String CLASSIFY_OPERATE_ERR_CODE = "020";

    public ReportClassifyException(String code, String msg) {
        super(CLASSIFY_OPERATE_ERR_CODE + code, msg);
    }

    public ReportClassifyException() {

    }

}
