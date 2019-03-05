package com.zhxg.framework.base.curd;

import java.util.HashMap;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.impl.BaseEntity;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 持久层接口基础类
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.curd.BaseDao.java
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
public interface BaseDao<T extends BaseEntity> {

    /**
     * 新增对象
     * 
     * @param t
     *            被新增的对象
     * @return T 被保存的完整对象
     */
    public int create(T t);

    /**
     * 根据ID更新一条数据
     *
     * @param t
     * @return
     */
    public int updateById(T t);

    /**
     * 根据Id查询一条数据
     * 
     * @param id
     * @return T
     */
    public T retrieveOneById(Object id);

    /**
     * 根据ID删除一条数据
     * 
     * @param id
     * @return int
     */
    public int deleteOneById(Object id);

    /**
     * 查询分页列表
     *
     * @param sqlKey
     *            查询分页的sql
     * @param pageInfo
     *            分页信息
     * @param paramMap
     *            查询参数信息
     * @return
     */
    public PageInfo<T> getPageList(String sqlKey, Pagination pagination,Object obj);


    /**
     * 根据用户kuId获取用户信息
     *
     * @param userIdList
     * @return
     */
    public List<HashMap<String, Object>> getAccountInfoByAccountIds(List<String> userIdList);

	int getInsertId();

    public List<HashMap<String, Object>> getAccountInfoByUserIds(List<String> accountIdList);
}
