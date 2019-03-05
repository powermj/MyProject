package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.entities.v1.MobileCloudCourse;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.dao
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 获取舆情课程详细信息
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.dao.v1.MobileCloudRegionalInfoDao.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年7月21日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public interface MobileCloudCourseDao extends BaseDao<BaseEntity> {

    /**
     * 获取舆情课程列表
     * @param params
     * @return
     */
    List<MobileCloudCourse> getAllCourse(Map<String,Object> params);
    
    
    /**
     * 获取舆情课程及舆情课件 
     * @param params
     * @return
     */
    List<MobileCloudCourse> getCourseList(Map<String,Object> params);
    
    /**
     * 获取舆情课件列表
     * @param params
     * @return
     */
    List<MobileCloudCourse> getAllCoursewareInfo(Map<String,Object> params);
    
   
}
