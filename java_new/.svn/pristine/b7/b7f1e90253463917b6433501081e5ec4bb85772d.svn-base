package com.zhxg.yqzj.service.v1;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.ParamsNullException;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.service.exception.myfocus.MyFocusInfoNotFoundException;
import com.zhxg.yqzj.service.exception.myfocus.RepeatOperateException;
import com.zhxg.yqzj.service.exception.myfocus.SendEmailErrorException;
import com.zhxg.yqzj.service.exception.solr.SolrException;
import com.zhxg.yqzj.service.exception.solr.WarningException;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.service
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: app导入附件信息接口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.service.v1.UserService.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2017年2月28日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public interface SolrOperateService extends BaseService<BaseEntity> {

    /**
     * 星光搜索获取列表
     *
     * @param paramMap
     * @param pageInfo
     * @return
     * @throws SolrException
     * @throws SysException 
     */
    PageInfo<T> solrSearch(Map<String, Object> paramMap, Pagination pageInfo) throws SolrException, SysException;

    /**
     * solr添加简报
     * 
     * @param paramMap
     * @return
     * @throws ParamsNullException
     * @throws RepeatOperateException
     * @throws SysException
     */
    int addBriefing(Map<String, Object> paramMap) throws ParamsNullException, RepeatOperateException, SysException;

    /**
     * solr添加关注
     *
     * @param paramMap
     * @return
     * @throws ParamsNullException
     * @throws RepeatOperateException
     * @throws SysException
     * @throws WarningException
     */
    int addWarning(Map<String, Object> paramMap)
            throws ParamsNullException, RepeatOperateException, SysException, WarningException;

    /**
     * solr添加预警
     *
     * @param paramMap
     * @return
     * @throws ParamsNullException
     * @throws RepeatOperateException
     * @throws SysException
     */
    int addAttention(Map<String, Object> paramMap)
            throws ParamsNullException, RepeatOperateException, SysException;

    /**
     * solr上报邮箱
     *
     * @param params
     * @return
     * @throws SysException
     * @throws MyFocusInfoNotFoundException
     * @throws SendEmailErrorException
     */
    int addSendEmail(Map<String, Object> params)
            throws SysException, MyFocusInfoNotFoundException, SendEmailErrorException;

    /**
     * 保存solr筛选条件
     *
     * @param paramMap
     * @return
     */
    int saveSearchCondition(Map<String, Object> paramMap) throws SysException;

    /**
     * 获取筛选条件
     *
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getSearchCondition(Map<String, Object> paramMap);

    /**
     * 获取详情页信息
     *
     * @param paramMap
     * @return
     * @throws SolrException
     * @throws SysException 
     */
    List<Map<String, Object>> getDetails(Map<String, Object> paramMap) throws SolrException, SysException;

    /**
     * 获取预警摘要
     *
     * @param paramMap
     * @return
     * @throws SolrException
     */
    String getAbstract(Map<String, Object> paramMap) throws SolrException;
}
