package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.SubjectUnselectDao;
import com.zhxg.yqzj.entities.v1.SubjectUnselect;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.dao
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户模块持久层接口实现
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.dao.impl.v1.UserDaoImpl.java
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
@Repository
public class SubjectUnselectDaoImpl extends BaseDaoImpl<BaseEntity> implements SubjectUnselectDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.SubjectUnselect.";
  
    @Override
    public int saveSubjectUnselect(SubjectUnselect subjectUnselect) {
        return this.sqlSessionTemplate.insert(NAME_SPACE + "insertSubjectUnselect", subjectUnselect);
    }

	@Override
	public int updateSubjectUnselect(SubjectUnselect subjectUnselect) {
		return this.sqlSessionTemplate.update(NAME_SPACE + "updateSubjectUnselect", subjectUnselect);
	}

	@Override
	public int delSubjectUnselect(SubjectUnselect subjectUnselect) {
		return this.sqlSessionTemplate.delete(NAME_SPACE + "delSubjectUnselect", subjectUnselect);
	}

	@Override
	public int delSubjectUnselectByParam(SubjectUnselect subjectUnselect) {
		return this.sqlSessionTemplate.delete(NAME_SPACE + "delSubjectUnselectByParam", subjectUnselect);
	}

	@Override
	public List<SubjectUnselect> selectSubjectUnselectList(SubjectUnselect subjectUnselect) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "selectSubjectUnselectList", subjectUnselect);
	}
    
    

}
