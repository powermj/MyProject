package com.zhxg.yqzj.service.exception.solr;

/**
 * <p>
 * Description:关注的信息不存在异常
 * </p>
 * 
 * @author zyl
 * @date 2017年11月14日
 * @version 1.0
 */
public class SolrAddExportEmailException extends SolrOperationException {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 4372662288919471390L;

    protected static final String ERROR_CODE = "016";

    public SolrAddExportEmailException() {
        super(ERROR_CODE, "solr添加信息失败");
    }

    public SolrAddExportEmailException(String msg) {
        super(ERROR_CODE, msg);
    }

}
