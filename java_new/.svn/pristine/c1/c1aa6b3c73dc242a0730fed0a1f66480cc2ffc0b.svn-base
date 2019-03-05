package com.zhxg.yqzj.dao.impl.v1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.yqzj.dao.v1.WkTValidationRefDao;
import com.zhxg.yqzj.entities.v1.WkTValidationRef;
import com.zhxg.yqzj.entities.v1.WkTValidationRefExample;

@Repository
public class WkTValidationRefDaoImpl extends BaseDaoImpl<WkTValidationRef> implements WkTValidationRefDao {
	private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.WkTValidationRef.";

	@Override
	public long countByExample_other(WkTValidationRefExample example) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE + "countByExample", example);
	}

	@Override
	public int deleteByExample_self(WkTValidationRefExample example) {
		return this.sqlSessionTemplate.delete(NAME_SPACE + "deleteByExample", example);
	}

	@Override
	public int deleteByPrimaryKey_other(String krUuid) {
		return this.sqlSessionTemplate.delete(NAME_SPACE + "deleteByPrimaryKey", krUuid);
	}

	@Override
	public int insert_other(WkTValidationRef record) {
		return this.sqlSessionTemplate.insert(NAME_SPACE + "insert", record);
	}

	@Override
	public int insertSelective_other(WkTValidationRef record) {
		return this.sqlSessionTemplate.insert(NAME_SPACE + "insertSelective", record);
	}

	@Override
	public List<WkTValidationRef> selectByExample_other(WkTValidationRefExample example) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "selectByExample", example);
	}

	@Override
	public WkTValidationRef selectByPrimaryKey_other(String krUuid) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE + "selectByPrimaryKey", krUuid);
	}

	@Override
	public int updateByExampleSelective_other(WkTValidationRef record, WkTValidationRefExample example) {
		Map<String, Object> map = new HashMap<>();
		map.put("record", record);
		map.put("example", example);
		return this.sqlSessionTemplate.update(NAME_SPACE + "updateByExampleSelective", map);
	}

	@Override
	public int updateByExample_other(WkTValidationRef record, WkTValidationRefExample example) {
		Map<String, Object> map = new HashMap<>();
		map.put("record", record);
		map.put("example", example);
		return this.sqlSessionTemplate.update(NAME_SPACE + "updateByExample", map);
	}

	@Override
	public int updateByPrimaryKeySelective_other(WkTValidationRef record) {
		return this.sqlSessionTemplate.update(NAME_SPACE + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey_other(WkTValidationRef record) {
		return this.sqlSessionTemplate.update(NAME_SPACE + "updateByPrimaryKey", record);
	}

	@Override
	public PageInfo<WkTValidationRef> getRefUrlOrhashList_other(WkTValidationRef wkTValidationRef,
			Pagination pagination) {
		return this.getPageList(NAME_SPACE + "getRefUrlOrhashList", pagination, wkTValidationRef);
	}

	@Override
	public PageInfo<WkTValidationRef> getGroupRankRefList_other(WkTValidationRef wkTValidationRef,
			Pagination pagination) {
		return this.getPageList(NAME_SPACE + "getGroupRankRefList", pagination, wkTValidationRef);
	}

	@Override
	public int getImportanceWeight(Integer msUserId) {
		try {
			String importanceWeight = this.sqlSessionTemplate.selectOne(NAME_SPACE + "getImportanceWeight", msUserId);
			return Integer.parseInt(importanceWeight);
		} catch (NumberFormatException e) {
			this.logger.error(e.getMessage(), e);
			return 20;
		}
	}

	@Override
	public int saveSearchCondition(Map<String, Object> map) {
		return this.sqlSessionTemplate.insert(NAME_SPACE + "saveSearchCondition", map);
	}

	@Override
	public String getSearchCondition(Integer msUserId, String kkId) {
		Map<String, Object> map = new HashMap<>();
		map.put("msUserId", msUserId);
		map.put("kkId", kkId);
		return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getSearchCondition", map);
	}

	@Override
	public int updateByExample_self(WkTValidationRefExample example) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("record", example);
		return this.sqlSessionTemplate.update(NAME_SPACE + "updateByExample", map);
	}

	@Override
	public List<WkTValidationRef> getRefList_other(WkTValidationRef wkTValidationRef) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "getRefList", wkTValidationRef);
	}

	@Override
	public int delRefByUrlOrHash_self(WkTValidationRef wkTValidationRef) {
		return this.sqlSessionTemplate.delete(NAME_SPACE + "delRefByUrlOrHash", wkTValidationRef);
	}

	@Override
	public int updateRefByUrlOrHash_self(WkTValidationRef wkTValidationRef) {
		return this.sqlSessionTemplate.update(NAME_SPACE + "updateRefByUrlOrHash", wkTValidationRef);
	}

	@Override
	public String getAgentId(Map<String, Object> transToMAP) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getAgentId", transToMAP);
	}

    @Override
    public PageInfo<WkTValidationRef> getRefUrlOrhashListByLimit_other(WkTValidationRef wkTValidationRef,
            Pagination pageInfo) {
        return this.getPageList(NAME_SPACE + "getRefUrlOrhashListByLimit_other", pageInfo, wkTValidationRef);
    }

	@Override
	public String getSearchConditionInfo(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getSearchConditionInfo", paramMap);
	}
	@Override
	public List<String> getKkIdByClassifyId(String classifyId) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE+"getKkIdByClassifyId", classifyId);
	}

}
