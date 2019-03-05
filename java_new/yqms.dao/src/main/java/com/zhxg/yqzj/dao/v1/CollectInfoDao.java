package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.yqzj.entities.v1.CollectInfo;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.dao
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户模块持久层接口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.dao.v1.UserDao.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年4月24日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public interface CollectInfoDao extends BaseDao<CollectInfo> {

    /**
     * 获取收藏夹及内词
     *
     * @param params
     * @return
     */
    List<CollectInfo> selectList(Map<String, Object> params);

    /**
     * 添加收藏夹
     *
     * @param params
     * @return
     */
    int insertCollectInfo(Map<String, Object> params);

    /**
     * 修改收藏夹
     *
     * @param params
     * @return
     */
    int updateCollectInfo(Map<String, Object> params);

    /**
     * 删除收藏夹
     *
     * @param params
     * @return
     */
    int deleteCollectInfo(Map<String, Object> params);

    /**
     * 查询是否重名
     *
     * @param params
     * @return
     */
    int getCountByName(Map<String, Object> params);

    /**
     * 查询默认列表是否存在
     *
     * @param params
     * @return
     */
    int getCountById(Map<String, Object> params);

    /**
     * 查询用户下所有收藏
     *
     * @param params
     * @return
     */
    List<CollectInfo> getAllCollectInfo(Map<String, Object> params);
}
