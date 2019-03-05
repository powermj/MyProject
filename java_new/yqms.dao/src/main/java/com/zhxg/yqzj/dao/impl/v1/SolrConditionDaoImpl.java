package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.yqzj.dao.v1.SolrConditionDao;
import com.zhxg.yqzj.entities.v1.SearchCondition;

/**
 * <p>
 * Description: 筛选条件dao实现类
 * </p>
 * 
 * @author fujiqiu
 * @date 2018年4月23日
 * @version 1.0
 */
@Repository
public class SolrConditionDaoImpl extends BaseDaoImpl<SearchCondition> implements SolrConditionDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.SearchCondition.";

    @Override
    public List<Map<String, Object>> selectList(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "selectList", params);
    }

    @Override
    public int insertCondition(Map<String, Object> params) {
        return this.sqlSessionTemplate.insert(NAME_SPACE + "insertCondition", params);
    }
}
