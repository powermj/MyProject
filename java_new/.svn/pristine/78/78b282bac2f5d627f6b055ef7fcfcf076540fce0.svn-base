package com.zhxg.yqzj.service.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.yqzj.dao.v1.CaseBaseDao;
import com.zhxg.yqzj.entities.v1.CaseBaseClassify;
import com.zhxg.yqzj.service.v1.CaseBaseService;

@Service
public class CaseBaseServiceImpl extends BaseServiceImpl<CaseBaseClassify> implements CaseBaseService {

	@Autowired
	private CaseBaseDao caseBaseDao;
	

	@Override
    protected BaseDao<CaseBaseClassify> getBaseDao() {
        return this.caseBaseDao;
    }


    @Override
    public List<CaseBaseClassify> getcaseBaseClassificationTree(Map<String, Object> paramMap) {     
        return caseBaseDao.getcaseBaseClassificationTree(paramMap);
    }

	

}
