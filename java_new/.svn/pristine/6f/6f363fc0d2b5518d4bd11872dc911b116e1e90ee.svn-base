package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.yqzj.dao.v1.ExpertDao;
import com.zhxg.yqzj.entities.v1.Expert;

@Repository
public class ExpertDaoImpl extends BaseDaoImpl<Expert> implements ExpertDao {

	private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.Expert.";
	
	@Override
	public PageInfo<Expert> getExpertList(Map<String, Object> paramMap, Pagination pageInfo) {
		return this.getPageList(NAME_SPACE + "getList", pageInfo, paramMap);
	}

    @Override
    public List<Expert> getExpertInfodetail(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getExpertInfodetail", paramMap);
    }

    @Override
    public List<Expert> getConsultationType(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getConsultationType", paramMap);
    }

    @Override
    public List<Expert> getAllExpertType(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getAllExpertType", paramMap);
    }

	
}
