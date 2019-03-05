package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.yqzj.entities.v1.Warning;

public interface WarningDao extends BaseDao<Warning> {

    PageInfo<Warning> getIndexWaringList_self(Map<String, Object> paramMap, Pagination pageInfo);

    List<Map<String, Integer>> getWaringCount_self(Map<String, Object> paramMap);

    /**
     * 获取预警列表
     *
     * @param params
     * @return
     */
    List<Map<String, Object>> selectList_self(Map<String, Object> params);

    /**
     * 添加预警信息
     *
     * @param params
     * @return
     */
    int insertYqyjSelective_self(Map<String, Object> params);

    /**
     * 查询预警是否添加过
     * 
     * @param params
     * @return
     */
    int getCountByUrl_self(Map<String, Object> params);
}
