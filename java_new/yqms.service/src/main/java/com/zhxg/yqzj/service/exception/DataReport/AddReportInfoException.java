package com.zhxg.yqzj.service.exception.DataReport;

import com.zhxg.framework.base.exception.BusinessException;

public class AddReportInfoException extends BusinessException {
    
    private static final long serialVersionUID = 7837861770457285293L;
    /**
     * @Fields CLASSIFY_OPERATE_ERR_CODE : 舆情浏览添加数据池异常码
     */
    public static final String SUBJECT_OPERATE_ERR_CODE = "021";

    public AddReportInfoException(String code, String msg) {
        super(SUBJECT_OPERATE_ERR_CODE + code, msg);
    }

    public AddReportInfoException() {

    }

}
