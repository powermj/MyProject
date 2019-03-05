package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.SolrExportConditionDao;
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
 * NameSpace: com.zhxg.yqzj.dao.impl.v1.SolrExportConditionDaoImpl.java
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
@Repository
public class SolrExportConditionDaoImpl extends BaseDaoImpl<BaseEntity> implements SolrExportConditionDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.SolrExportCondition.";

    /**
     * 获取solr全部导出条件
     */
    @Override
    public List<SolrExportCondition> getExportCondition(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getExportCondition", params);
    }

    /**
     * 添加solr全部导出条件
     */
    @Override
    public int setExportCondition(Map<String, Object> params) {
        return this.sqlSessionTemplate.insert(NAME_SPACE + "setExportCondition", params);
    }

    /**
     * 删除用户下自定义导出字段
     */
    @Override
    public int deleteExportCondition(Map<String, Object> params) {
        return this.sqlSessionTemplate.delete(NAME_SPACE + "deleteExportCondition", params);
    }

    /**
     * 获取用户自定义导出字段
     */
    @Override
    public List<SolrExportCondition> getValidCondition(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getValidCondition", params);
    }

    /**
     * 获取全部导出字段
     */
    @Override
    public List<SolrExportCondition> getAllCondition(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getAllCondition", params);
    }

}
