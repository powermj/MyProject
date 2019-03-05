package com.zhxg.yqzj.service.v1;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.WkTValidationRef;

public interface WkTValidationRefService extends BaseService<WkTValidationRef> {
	/**
	 * 查询专题列表
	 * 
	 * @param wkTValidationRef
	 * @param pagination
	 * @param userId 
	 * @return
	 * @throws SysException
	 */
	PageInfo<WkTValidationRef> getRefList(WkTValidationRef wkTValidationRef, Pagination pagination, String userId) throws SysException;
	String getSearchConditionInfo(Map<String, Object> paramMap) throws SysException;

	List<String> getKkIdByClassifyId(String classifyId) throws SysException;

}
