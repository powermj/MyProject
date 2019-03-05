package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.yqzj.dao.v1.WarningDao;
import com.zhxg.yqzj.entities.v1.Warning;

@Repository
public class WarningDaoImpl extends BaseDaoImpl<Warning> implements WarningDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.Warning.";

    @Override
    public PageInfo<Warning> getIndexWaringList_self(Map<String, Object> paramMap, Pagination pageInfo) {
        return this.getPageList(NAME_SPACE + "getIndexWaringList", pageInfo, paramMap);
    }

    @Override
    public List<Map<String, Integer>> getWaringCount_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getWaringCount", paramMap);
    }

    @Override
    public List<Map<String, Object>> selectList_self(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "selectList", params);
    }

    @Override
    public int insertYqyjSelective_self(Map<String, Object> params) {
        return this.sqlSessionTemplate.insert(NAME_SPACE + "insertYqyjSelective", params);
    }

    @Override
    public int getCountByUrl_self(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getCountByUrl", params);
    }
}
