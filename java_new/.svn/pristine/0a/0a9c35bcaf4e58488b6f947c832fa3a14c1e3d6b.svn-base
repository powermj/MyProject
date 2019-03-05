package com.zhxg.yqzj.service.v1;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.Expert;

public interface ExpertService extends BaseService<Expert> {

	PageInfo<Expert> getExpertList(Map<String, Object> paramMap, Pagination pagination) throws SysException;

	List<Expert> getExpertInfodetail(Map<String, Object> paramMap);

    List<Expert> getConsultationType(Map<String, Object> paramMap);

    List<Expert> getAllExpertType(Map<String, Object> paramMap);

}
