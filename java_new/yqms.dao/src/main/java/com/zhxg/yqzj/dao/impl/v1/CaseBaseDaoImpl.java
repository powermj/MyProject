package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.yqzj.dao.v1.CaseBaseDao;
import com.zhxg.yqzj.entities.v1.CaseBaseClassify;

@Repository
public class CaseBaseDaoImpl extends BaseDaoImpl<CaseBaseClassify> implements CaseBaseDao {

	private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.CaseBaseClassify.";

    @Override
    public List<CaseBaseClassify> getcaseBaseClassificationTree(Map<String, Object> paramMap) {
        
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getcaseBaseClassificationTree", paramMap);
    }
	
	

	
}
