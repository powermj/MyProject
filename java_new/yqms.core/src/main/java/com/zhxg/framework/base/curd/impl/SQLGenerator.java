package com.zhxg.framework.base.curd.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhxg.framework.base.utils.DateStyle;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.framework.base.utils.UUIDUtils;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: SQL生成器
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.curd.impl.SQLGenerator.java
 * <p>
 * Author: azmiu
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
class SQLGenerator<T> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String columnStrForCreate;
    private final String tableName;
    // mapping for columns of db table and entity props, excludes props in base entity
    private Map<String, String> propAndColumnsMapping;
    private Map<String, String> propAndColumnsMappingForInsert;
    private Map<String, String> propAndColumnsMappingForUpdate;

    private String BASE_ENTITY_FIELD_ID = "id";

    private String BASE_COLUMN_FIELD_ID = "ID";

    public SQLGenerator(Map<String, String> propAndColumnsMapping, String tableName, String pkName,
            String seq) {
        super();
        this.propAndColumnsMapping = propAndColumnsMapping;
        // mapping for update, excludes id prop
        this.propAndColumnsMappingForUpdate = new HashMap<String, String>(propAndColumnsMapping);
        // add id prop 
        this.propAndColumnsMapping.put(this.BASE_COLUMN_FIELD_ID, this.BASE_ENTITY_FIELD_ID);
        // mapping for insert, includes all six props in base entity
        this.propAndColumnsMappingForInsert = new HashMap<String, String>(propAndColumnsMapping);
        // generate base create(query) columns string
        this.columnStrForCreate = StringUtils.join(this.propAndColumnsMappingForInsert.keySet(), ",");
        this.tableName = tableName;
    }

    /**
     * generate create sql
     * 
     * @param t
     *            entity
     * @return String SQL
     */
    protected String sqlInsert(T t) {
        this.setBaseFields(t);
        final List<Object> values = this.getFieldValues(t, this.propAndColumnsMappingForInsert);
        final StringBuilder sqlBuild = new StringBuilder();
        sqlBuild.append("INSERT INTO ");
        sqlBuild.append(this.tableName);
        sqlBuild.append(" (");
        sqlBuild.append(this.columnStrForCreate);
        sqlBuild.append(" ) VALUES ( ");
        sqlBuild.append(StringUtils.join(values, ","));
        sqlBuild.append(" )");
        final String sql = sqlBuild.toString();
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("生成的insert SQL是: " + sql);
        }
        return sql;
    }

    /**
     * 生成更新对象SQL
     * 
     * @param t
     *            泛型
     * @return String SQL
     */
    public String sqlUpdateById(T t) {
        // this.setBaseFields(t);
        final List<String> values = this.getFieldValuesUpdate(t, this.propAndColumnsMappingForUpdate);
        final StringBuilder sql_build = new StringBuilder();
        sql_build.append("UPDATE ");
        sql_build.append(this.tableName);
        sql_build.append(" SET ");
        sql_build.append(StringUtils.join(values, ","));
        sql_build.append(" WHERE ");
        sql_build.append("ID");
        sql_build.append(" = '");
        sql_build.append(this.getFieldIdValues(t));
        sql_build.append("'");
        final String sql = sql_build.toString();
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("生成的update SQL: " + sql);
        }
        return sql;
    }

    /**
     * 生成根据ID查询单条对象SQL
     * 
     * @param id
     * @return String SQL
     */
    public String sqlRetrieveById(Object id) {
        final StringBuilder sql_build = new StringBuilder();
        sql_build.append("SELECT ");
        sql_build.append(this.columnStrForCreate);
        sql_build.append(" FROM ");
        sql_build.append(this.tableName);
        sql_build.append(" WHERE ID = ");
        sql_build.append(this.handleValue(id));
        final String sql = sql_build.toString();
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("生成的retrieve SQL: " + sql);
        }
        return sql;

    }

    /**
     * generate delete sql
     * 
     * @param id
     * @return String SQL
     */
    public String sqlDeleteById(Object id) {
        final StringBuilder sql_build = new StringBuilder();
        sql_build.append("DELETE FROM ");
        sql_build.append(this.tableName);
        sql_build.append(" WHERE ID =");
        sql_build.append(this.handleValue(id));
        final String sql = sql_build.toString();
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("生成的delete SQL为: " + sql);
        }
        return sql;
    }

    /**
     * 根据columnFieldNames顺序，获取字段值
     * 
     * @param t
     *            实体值
     * @param columnFieldNames
     *            数据库表->列
     * @return List<Object>
     */
    private List<Object> getFieldValues(T t, Map<String, String> propAndColumnsMapping) {
        final List<Object> values = new LinkedList<Object>();
        for (String column : propAndColumnsMapping.values()) {
            Object value = ReflectionUtils.obtainFieldValue(t, column);
            values.add(this.handleValue(value));
        }
        return values;
    }

    /**
     * 根据值类型不同，处理字段值
     * 
     * @param value
     *            要被处理的值
     * @return Object
     */
    private Object handleValue(Object value) {
        if (value instanceof String) {
            value = "\'" + value + "\'";
        } else if (value instanceof Date) {
            final Date date = (Date) value;
            final String dateStr = DateUtil.DateToString(date, DateStyle.YYYY_MM_DD_HH_MM_SS);
            // TODO 现在只是处理MYSQL数据库，将来增加oracle
            value = "TIMESTAMP('" + dateStr + "')";
        } else if (value instanceof Boolean) {
            final Boolean v = (Boolean) value;
            value = v ? 1 : 0;
        } else if ((null == value) || StringUtils.isBlank(value.toString())) {
            value = "null";
        }
        return value;
    }

    /**
     * 处理更新SQL字段的值
     * 
     * @param t
     *            实体值
     * @param columnFieldNames
     *            数据库表->列
     * @return List<String>
     */
    private List<String> getFieldValuesUpdate(T t, Map<String, String> propAndColumnsMapping) {
        final List<String> colVals = new LinkedList<String>();
        for (String column : propAndColumnsMapping.keySet()) {
            if (!this.BASE_COLUMN_FIELD_ID.equals(column)) {
                final Object value = ReflectionUtils.obtainFieldValue(t, propAndColumnsMapping.get(column));
                colVals.add(column + "=" + this.handleValue(value));
            }
        }
        return colVals;
    }

    /**
     * handle common fields in base entity
     * 
     * @param t
     * @return
     */
    private T setBaseFields(T t) {
        ReflectionUtils.setFieldValue(t, this.BASE_ENTITY_FIELD_ID, UUIDUtils.create());
        return t;
    }

    /**
     * 在泛型中，获取ID值
     * 
     * @param t
     *            带值实体类
     * @return Object
     */
    private Object getFieldIdValues(T t) {
        return ReflectionUtils.obtainFieldValue(t, this.BASE_ENTITY_FIELD_ID);
    }


    /**
     * handle the result(map) by sql.
     * it will return an object instance of template T
     * 
     * @param map
     *            the result map
     * @return
     */
    protected T entityReflection(Map<String, Object> map, Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (final InstantiationException e) {
            this.logger.error(
                    "封装查询结果时，实例化对象(" + clazz.getPackage() + "." + clazz.getName() + ")时，出现异常！", e);
        } catch (final IllegalAccessException e) {
            this.logger.error(
                    "封装查询结果时，实例化对象(" + clazz.getPackage() + "." + clazz.getName() + ")时，出现异常！", e);
        }

        for (final Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            key = this.propAndColumnsMappingForInsert.get(key); // create的mapping最全
            final Object val = entry.getValue();
            ReflectionUtils.invokeSetterMethod(t, key, val);
        }
        return t;
    }
}
