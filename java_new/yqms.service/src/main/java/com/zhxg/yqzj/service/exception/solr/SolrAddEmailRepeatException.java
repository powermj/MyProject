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
public class SolrAddEmailRepeatException extends SolrOperationException {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = -231257277266485069L;

    protected static final String ERROR_CODE = "017";

    public SolrAddEmailRepeatException() {
        super(ERROR_CODE, "solr添加信息重复");
    }

    public SolrAddEmailRepeatException(String msg) {
        super(ERROR_CODE, msg);
    }

}
