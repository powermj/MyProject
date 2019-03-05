package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.yqzj.dao.v1.CustomHomePageDao;
import com.zhxg.yqzj.entities.v1.CustomHomePage;

@Repository
public class CustomHomePageDaoImpl extends BaseDaoImpl<CustomHomePage> implements CustomHomePageDao {

	private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.CustomHomePage.";

	@Override
	public List<Map<String, Object>> getUserCustomHomePageData_self(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "getUserCustomHomePageData_self", paramMap);
	}

	@Override
	public List<Map<String, Object>> getUserNavData(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "getUserNavData", paramMap);
	}

	@Override
	public List<Map<String, Object>> getUserBarNewData(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "getUserBarNewData", paramMap);
	}

	@Override
	public List<Map<String, Object>> userBaseInfo(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "userBaseInfo", paramMap);
	}

	@Override
	public Map<String, Object> getUserInfo(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getUserInfo", paramMap);
	}

	@Override
	public int restoreDefaultData_self(Map<String, Object> paraMap) {
		return this.sqlSessionTemplate.delete(NAME_SPACE + "restoreDefaultData_self", paraMap);
	}

	@Override
	public int insertCustomData_self(Map<String, Object> paramap) {
		return this.sqlSessionTemplate.insert(NAME_SPACE + "insertCustomData_self", paramap);
	}

	@Override
	public PageInfo<Map<String, Object>> getHeadLinesList(Pagination pagination,String name) {
		PageHelper.startPage(pagination);
		List<Map<String, Object>> list = this.sqlSessionTemplate.selectList(NAME_SPACE + "getHeadLinesList",name);
		return new PageInfo<Map<String, Object>>(list);
	}

	@Override
	public List<Map<String, Object>> getSubjectListByUserId(int userId) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "getSubjectListByUserId", userId);
	}

	@Override
	public List<Map<String, Object>> getSourceTypeCount_self(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "getSourceTypeCount", map);
	}

	@Override
	public int updateCustomData_self(Map<String, Object> paramap) {
		return this.sqlSessionTemplate.update(NAME_SPACE + "updateCustomData_self", paramap);
	}

	@Override
	public List<Map<String, Object>> getSubjectTree(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "getSubjectTree", paramMap);
	}

    @Override
    public void delUserNav(Map<String, Object> paraMap) {
        this.sqlSessionTemplate.delete(NAME_SPACE + "delUserNav", paraMap);
    }

    @Override
    public void updateUserClassfy(Map<String, Object> paraMap) {
        this.sqlSessionTemplate.update(NAME_SPACE + "updateUserClassfy", paraMap);
    }

    @Override
    public void updateSubrelation(Map<String, Object> paraMap) {
        this.sqlSessionTemplate.update(NAME_SPACE + "updateSubrelation", paraMap);
    }
}
