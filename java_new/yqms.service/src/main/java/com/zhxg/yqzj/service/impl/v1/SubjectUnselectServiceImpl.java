package com.zhxg.yqzj.service.impl.v1;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.yqzj.dao.v1.SubjectUnselectDao;
import com.zhxg.yqzj.entities.v1.SubjectUnselect;
import com.zhxg.yqzj.service.v1.SubjectUnselectService;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.service
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户模块服务层接口实现
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.service.impl.v1.UserServiceImpl.java
 * <p>
 * Author: azmiu
 * <p>
 * Create Date: 2017年2月28日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
@Service
public class SubjectUnselectServiceImpl extends BaseServiceImpl<BaseEntity> implements SubjectUnselectService {
	
    @Autowired
    private SubjectUnselectDao subjectUnselectDao;
	
	@Override
	protected BaseDao<BaseEntity> getBaseDao() {
		return this.subjectUnselectDao;
	}

	@Override
	public boolean saveSubjectUnselect(SubjectUnselect subjectUnselect) {
		int result = 0;
		if(subjectUnselect.getId()!=null&&subjectUnselect.getId()!=0){
			if(!StringUtils.isEmpty(subjectUnselect.getSubids())){
				result = subjectUnselectDao.updateSubjectUnselect(subjectUnselect);
			}else{
				result = subjectUnselectDao.delSubjectUnselect(subjectUnselect);
			}
		}else{
			if("0".equals(subjectUnselect.getPid())&&StringUtils.isEmpty(subjectUnselect.getSubids())){
				result = subjectUnselectDao.delSubjectUnselectByParam(subjectUnselect);
			}else{
				result = subjectUnselectDao.saveSubjectUnselect(subjectUnselect);
			}
			
		}
		return result==1?true:false;
	}

	@Override
	public List<SubjectUnselect> selectSubjectUnselectList(SubjectUnselect subjectUnselect) {
		return subjectUnselectDao.selectSubjectUnselectList(subjectUnselect);
	}
 
}
