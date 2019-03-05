package com.zhxg.framework.base.service;

import java.util.Map;

import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserException;
import com.zhxg.framework.base.exception.UserNoFoundException;

public interface BaseService<T extends BaseEntity> {

    /**
     * 新增对象
     * 
     * @param t
     *            被新增的对象
     * @return T 被保存的完整对象
     */
    public int create(T t);

    
    /**
     * 根据ID更新数据
     *
     * @param t
     * @return
     */
    public int updateById(T t);

    /**
     * 根据ID查询单条数据
     * 
     * @param id
     * @return T
     */
    public T retrieveOneById(String id);

    /**
     * 根据ID删除单条数据
     * 
     * @param id
     * @return int
     */
    public int deleteOneById(String id);
    

    /**
     * 根据userIds获取用户信息
     *
     * @param userIds
     * @return
     * @throws UserException
     * @throws SysException
     */
    public Map<String, Object> getUserInfoByUserIds(Map<String, Object> userIds)
            throws UserNoFoundException, SysException;

}
