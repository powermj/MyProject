package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.entities.v1.SolrExportCondition;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.dao
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: <对此类的描述>
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqzj.dao.v1.SolrExportEmailDao.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年5月8日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public interface SolrExportConditionDao extends BaseDao<BaseEntity> {

    /**
     * 获取最近保存的导出条件
     *
     * @return
     */
    List<SolrExportCondition> getExportCondition(Map<String, Object> params);

    /**
     * 添加全部导出条件
     *
     * @param params
     * @return
     */
    int setExportCondition(Map<String, Object> params);

    /**
     * 删除该用户下所有历史导出字段
     *
     * @param params
     * @return
     */
    int deleteExportCondition(Map<String, Object> params);

    /**
     * 获取用户下自定义有效字段
     *
     * @param params
     * @return
     */
    List<SolrExportCondition> getValidCondition(Map<String, Object> params);

    /**
     * 获取全部导出字段
     *
     * @param params
     * @return
     */
    List<SolrExportCondition> getAllCondition(Map<String, Object> params);
}
