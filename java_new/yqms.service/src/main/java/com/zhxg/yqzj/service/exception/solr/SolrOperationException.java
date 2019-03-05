package com.zhxg.yqzj.service.exception.solr;

import com.zhxg.framework.base.exception.BusinessException;

/**
 * <p>
 * Description:全网搜索solr导出异常
 * </p>
 * 
 * @author zyl
 * @date 2017年11月14日
 * @version 1.0
 */
public class SolrOperationException extends BusinessException {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = -334245429472542547L;
    /**
     * @Fields USER_SERVER_CODE : 异常码，solr操作，具体异常由具体异常类拼接
     */
    public static final String SOLR_MODULE_CODE = "008";

    public SolrOperationException(String code, String msg) {
        super(SOLR_MODULE_CODE + code, msg);
    }

    public SolrOperationException() {

    }
}
