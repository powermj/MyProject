package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.yqzj.entities.v1.SearchCondition;

/**
 * <p>
 * Description: 筛选条件dao接口
 * </p>
 * 
 * @author fujiqiu
 * @date 2018年4月23日
 * @version 1.0
 */
public interface SolrConditionDao extends BaseDao<SearchCondition> {

    /**
     * 获取筛选条件
     * 
     * @param params
     * @return
     */
    List<Map<String, Object>> selectList(Map<String, Object> params);

    /**
     * 添加筛选条件
     *
     * @param params
     * @return
     */
    int insertCondition(Map<String, Object> params);

}
