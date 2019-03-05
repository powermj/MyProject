package com.zhxg.yqzj.service.exception.solr;

/**
 * <p>
 * Description:导出状态异常
 * </p>
 * 
 * @author zyl
 * @date 2017年11月14日
 * @version 1.0
 */
public class SolrExportStatusException extends SolrOperationException {

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = -7527730104998685144L;

    protected static final String ERROR_CODE = "019";

    public SolrExportStatusException() {
        super(ERROR_CODE, "文件正在导出，请耐心等待");
    }

    public SolrExportStatusException(String msg) {
        super(ERROR_CODE, msg);
    }

}
