package com.zhxg.yqzj.service.v1;

import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.ParameterException;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.Subject;
import com.zhxg.yqzj.service.exception.subject.KeywordExceedingLimitException;
import com.zhxg.yqzj.service.exception.subject.SubjectNameRepeatException;
import com.zhxg.yqzj.service.exception.user.PermissionDeniedException;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.service
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户模块服务层接口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.service.v1.UserService.java
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
public interface SubjectService extends BaseService<BaseEntity> {

    public void addSubject(Subject subject) throws PermissionDeniedException, KeywordExceedingLimitException, SubjectNameRepeatException, ParameterException;
    
    public boolean changeUserClassify(Subject subject,String targetKcuuid);

}
