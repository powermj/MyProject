package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.yqzj.entities.v1.Expert;

public interface ExpertDao extends BaseDao<Expert>{

	PageInfo<Expert> getExpertList(Map<String, Object> paramMap, Pagination pageInfo);

	List<Expert> getExpertInfodetail(Map<String, Object> paramMap);

    List<Expert> getConsultationType(Map<String, Object> paramMap);

    List<Expert> getAllExpertType(Map<String, Object> paramMap);

}
