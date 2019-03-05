package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.entities.v1.SourceType;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.dao
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 获取所有媒体类型接口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.dao.v1.UserDao.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年7月03日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public interface SourceTypeDao extends BaseDao<BaseEntity> {

    
    /**
     * 获取所有媒体类型
     * @param paramMap
     * @return
     */
    List<SourceType> getAllSourceType(Map<String,Object> params);
   
}
