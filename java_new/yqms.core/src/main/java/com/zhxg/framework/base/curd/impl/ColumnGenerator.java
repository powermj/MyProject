package com.zhxg.framework.base.curd.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;

import org.apache.commons.lang.StringUtils;

import com.zhxg.framework.base.utils.CamelCaseUtils;

public class ColumnGenerator {

    /**
     * get mapping for columns of db table and entity props
     * 
     * @param entityClass
     * @return
     *         mapping for columns of db table and entity props.
     *         the key is column name and the value is entity prop name.
     * @throws Exception
     */
    public static Map<String, String> getDBColumn(Class<?> entityClass) {
        Map<String, String> currentColumnFieldNames = new HashMap<String, String>();
        String fieldName = null;
        String columnName = null;
        // 取得所有EntityClass字段
        final Field[] fields = entityClass.getDeclaredFields();
        for (final Field field : fields) {
            if (!field.isAnnotationPresent(Column.class)) {
                // only handle the prop with column annotation
                continue;
            }
            fieldName = field.getName();
            // get the column name in @column annotation
            final Column column = field.getAnnotation(Column.class);
            if (null != column) {
                columnName = column.name();
            } 
            columnName = (StringUtils.isBlank(columnName) ? StringUtils.upperCase(CamelCaseUtils.toUnderlineName(fieldName)) : columnName);
            currentColumnFieldNames.put(columnName, fieldName);
        }
        return currentColumnFieldNames;
    }

    private ColumnGenerator() {
    }
}
