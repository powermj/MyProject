package com.zhxg.yqzj.service.v1;

import java.util.List;
import java.util.Map;

import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.SolrExportCondition;
import com.zhxg.yqzj.entities.v1.UserMailExport;
import com.zhxg.yqzj.service.exception.solr.SolrAddEmailRepeatException;
import com.zhxg.yqzj.service.exception.solr.SolrAddExportEmailException;
import com.zhxg.yqzj.service.exception.solr.SolrException;
import com.zhxg.yqzj.service.exception.solr.SolrExportFileException;
import com.zhxg.yqzj.service.exception.solr.SolrOperateExportConditionException;

public interface SolrExportService extends BaseService<BaseEntity> {

    /**
     * 获取导出邮箱列表
     *
     * @return
     */
    List<UserMailExport> getExportEmail(Map<String, Object> params);

    /**
     * 添加导出邮箱
     *
     * @param params
     * @return
     */
    int setExportEmail(Map<String, Object> params)
            throws SolrAddExportEmailException, SolrAddEmailRepeatException;

    /**
     * 获取导出字段
     *
     * @param params
     * @return
     */
    List<Map<String, List<SolrExportCondition>>> getExportCondition(Map<String, Object> params);

    /**
     * 添加导出字段
     *
     * @param params
     * @return
     */
    int setExportCondition(Map<String, Object> params, SolrExportCondition[] solrExportCondition)
            throws SolrOperateExportConditionException;

    /**
     * 查询用户导出状态
     *
     * @param params
     * @return
     */
    String getExportStatus(Map<String, Object> params) throws Exception;

    /**
     * 删除用户下历史导出字段
     *
     * @param params
     * @return
     */
    int deleteExportCondition(Map<String, Object> params) throws SolrOperateExportConditionException;

    /**
     * 导出2007excel
     *
     * @param params
     * @return
     */
    int exportFile(Map<String, Object> params) throws SolrException, SolrExportFileException;
}
