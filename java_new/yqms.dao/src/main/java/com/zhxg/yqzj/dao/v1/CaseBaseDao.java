package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.yqzj.entities.v1.CaseBaseClassify;

public interface CaseBaseDao extends BaseDao<CaseBaseClassify>{

    List<CaseBaseClassify> getcaseBaseClassificationTree(Map<String, Object> paramMap);

	

}
