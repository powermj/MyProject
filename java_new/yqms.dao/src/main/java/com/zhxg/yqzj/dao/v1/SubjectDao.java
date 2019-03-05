package com.zhxg.yqzj.dao.v1;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.entities.v1.Subject;
import com.zhxg.yqzj.entities.v1.SubjectConfigure;

/**
 * <p>
 * CopyRright (c)2012-2016: haoran
 * <p>
 * Project: yqms.dao
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户模块持久层接口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.dao.v1.SubjectDao.java
 * <p>
 * Author: haoran
 * <p>
 * Create Date: 2018年6月28日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public interface SubjectDao extends BaseDao<BaseEntity> {

    int insertSubjectConfig(SubjectConfigure configure);

    int insertSubject(Subject subject);
    
    int updateSubjectKcuuid(Subject subject);
    
    Integer selectMaxIndexByKcuuid(Subject subject);

}
