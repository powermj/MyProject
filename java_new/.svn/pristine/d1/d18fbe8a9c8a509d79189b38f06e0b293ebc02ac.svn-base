package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.entities.v1.YqllLableInfo;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.dao
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 获取标签
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.dao.v1.YqllLableDao.java
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
public interface YqllLableDao extends BaseDao<BaseEntity> {

    /**
     * 获取舆情课程列表
     * @param params
     * @return
     */
    List<YqllLableInfo> getAllLableInfo(Map<String,Object> params);
    
    /**
     * 动态获取推荐标签
     *
     * @param params
     * @return
     */
    List<YqllLableInfo> getRecommentLable(Map<String,Object> params);
}
