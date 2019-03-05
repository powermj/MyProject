package com.zhxg.yqzj.service.exception.DataReport;

import com.zhxg.framework.base.exception.BusinessException;

public class ReportVariableException extends BusinessException {
    
    private static final long serialVersionUID = -6600185323601395497L;
    /**
     * @Fields CLASSIFY_OPERATE_ERR_CODE : 自定义数据分类异常码
     */
    public static final String VARIABLE_OPERATE_ERR_CODE = "022";

    public ReportVariableException(String code, String msg) {
        super(VARIABLE_OPERATE_ERR_CODE + code, msg);
    }

    public ReportVariableException() {

    }

}
