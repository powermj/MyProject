package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.yqzj.entities.v1.WkTValidationRef;
import com.zhxg.yqzj.entities.v1.WkTValidationRefExample;

public interface WkTValidationRefDao extends BaseDao<WkTValidationRef> {
	
	long countByExample_other(WkTValidationRefExample example);

	int deleteByExample_self(WkTValidationRefExample example);

	int deleteByPrimaryKey_other(String krUuid);

	int insert_other(WkTValidationRef record);

	int insertSelective_other(WkTValidationRef record);

	List<WkTValidationRef> selectByExample_other(WkTValidationRefExample example);

	WkTValidationRef selectByPrimaryKey_other(String krUuid);

	int updateByExampleSelective_other(@Param("record") WkTValidationRef record,
			@Param("example") WkTValidationRefExample example);

	int updateByExample_other(@Param("record") WkTValidationRef record, @Param("example") WkTValidationRefExample example);

	int updateByPrimaryKeySelective_other(WkTValidationRef record);

	int updateByPrimaryKey_other(WkTValidationRef record);

	PageInfo<WkTValidationRef> getRefUrlOrhashList_other(WkTValidationRef wkTValidationRef, Pagination pagination);
	
	
	PageInfo<WkTValidationRef> getGroupRankRefList_other(WkTValidationRef wkTValidationRef, Pagination pagination);

	int saveSearchCondition(Map<String, Object> map);

	String getSearchCondition(Integer msUserId, String kkId);

    int updateByExample_self(WkTValidationRefExample example);

	List<WkTValidationRef> getRefList_other(WkTValidationRef wkTValidationRef);

	int delRefByUrlOrHash_self(WkTValidationRef wkTValidationRef);

	int updateRefByUrlOrHash_self(WkTValidationRef wkTValidationRef);

	int getImportanceWeight(Integer msUserId);

	String getAgentId(Map<String, Object> transToMAP);

    PageInfo<WkTValidationRef> getRefUrlOrhashListByLimit_other(WkTValidationRef wkTValidationRef, Pagination pageInfo);

	String getSearchConditionInfo(Map<String, Object> paramMap);

	List<String> getKkIdByClassifyId(String classifyId);
//	/**
//	 * 测试
//	 * @param wkTValidationRef
//	 * @return
//	 */
//	WkTValidationRef getReturnRef_other(WkTValidationRef wkTValidationRef);

}