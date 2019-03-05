package com.zhxg.framework.base.curd.impl;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;


/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 持久层接口实现
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.curd.impl.BaseDaoImpl.java
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
@Repository("BaseDaoImpl")
public abstract class BaseDaoImpl<T extends BaseEntity> implements BaseDao<T> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String BASE_MAPPER_NAMESPACE = "com.zhxg.framework.base.curd.impl.BaseDaoImpl.";
    @Autowired(required = true)
    protected SqlSession sqlSessionTemplate;
    private final String entityName; // entity name for template T
    private final Class<T> entityClazz; // entity class for template T
    private String seq; // ?
    private String tableName; // table name in db
    private static final String SQL_LAST_INSERT_ID = "getLastInsertId";
    private static final String SQL_KEY_CREATE = "create";
    private static final String SQL_KEY_UPDATEBYID = "updateById";
    private static final String SQL_KEY_READONEBYID = "readOneById";
    private static final String SQL_KEY_DELONEBYID = "delOneById";
    private static final String SQL_KEY_USERINFOBYID = "getUserInfoByAccountIds";

    private static final String SQL_KEY_USERINFOBYUSERID = "getUserInfoByUserIds";
    
    private String baseEntityPropNameId = "id"; // name of prop id in base entity

    private volatile SQLGenerator<T> sqlGenerator; // sql generator for some base/common some
    
    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        super();
        // get class for entity
        final Type genType = this.getClass().getGenericSuperclass();
        final Type[] params = ((java.lang.reflect.ParameterizedType) genType).getActualTypeArguments();
        this.entityClazz = (Class<T>) params[0];
        this.entityName = this.entityClazz.getPackage() + "." + this.entityClazz.getName();
        final Table table = this.entityClazz.getAnnotation(Table.class);
        if (null == table) {
            this.logger.error("Dao初始化错误：实体类-" + this.entityName + "未用@Table注解标识！");
        } else {
            this.tableName = table.name();
        }
        if (null == this.sqlGenerator) {
            this.sqlGenerator = new SQLGenerator<T>(ColumnGenerator.getDBColumn(this.entityClazz), this.tableName,
                    this.baseEntityPropNameId, this.seq);
        }
    }
    
    @Override
    public int getInsertId() {
    	Map<String,Integer> map = this.sqlSessionTemplate.selectOne(this.BASE_MAPPER_NAMESPACE + SQL_LAST_INSERT_ID);
    	return map.get("id");
    }

	@Override
	public int create(T t) {
		return this.sqlSessionTemplate.insert(this.BASE_MAPPER_NAMESPACE + SQL_KEY_CREATE,
				this.sqlGenerator.sqlInsert(t));
	}

    @Override
    public int updateById(T t) {
        return this.sqlSessionTemplate.update(this.BASE_MAPPER_NAMESPACE + SQL_KEY_UPDATEBYID,
                this.sqlGenerator.sqlUpdateById(t));
    }

	@Override
	public T retrieveOneById(Object id) {
		final Map<String, Object> resultMap = this.sqlSessionTemplate
				.selectOne(this.BASE_MAPPER_NAMESPACE + SQL_KEY_READONEBYID, this.sqlGenerator.sqlRetrieveById(id));
		return this.sqlGenerator.entityReflection(resultMap, this.entityClazz);
	}

    @Override
    public int deleteOneById(Object id) {
        return this.sqlSessionTemplate.delete(this.BASE_MAPPER_NAMESPACE + SQL_KEY_DELONEBYID,
                this.sqlGenerator.sqlDeleteById(id));
    }

    @Override
    public List<HashMap<String, Object>> getAccountInfoByAccountIds(List<String> userIdList) {
        return this.sqlSessionTemplate.selectList(this.BASE_MAPPER_NAMESPACE + SQL_KEY_USERINFOBYID, userIdList);
    }
    
    @Override
    public List<HashMap<String, Object>> getAccountInfoByUserIds(List<String> userIdList) {
        return this.sqlSessionTemplate.selectList(this.BASE_MAPPER_NAMESPACE + SQL_KEY_USERINFOBYUSERID, userIdList);
    }

	@Override
	public PageInfo<T> getPageList(String sqlKey, Pagination pagination, Object obj) {
		PageHelper.startPage(pagination);
		List<T> list = this.sqlSessionTemplate.selectList(sqlKey, obj);
		return new PageInfo<T>(list);
	}

}
