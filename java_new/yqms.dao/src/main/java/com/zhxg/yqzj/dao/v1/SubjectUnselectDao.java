package com.zhxg.yqzj.dao.v1;

import java.util.List;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.entities.v1.SubjectUnselect;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.dao
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户模块持久层接口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.dao.v1.UserDao.java
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
public interface SubjectUnselectDao extends BaseDao<BaseEntity> {
	
	public int saveSubjectUnselect(SubjectUnselect subjectUnselect);
	
	public int updateSubjectUnselect(SubjectUnselect subjectUnselect);
	
	public int delSubjectUnselect(SubjectUnselect subjectUnselect);
	
	public int delSubjectUnselectByParam(SubjectUnselect subjectUnselect);
	
	public List<SubjectUnselect> selectSubjectUnselectList(SubjectUnselect subjectUnselect);

}
