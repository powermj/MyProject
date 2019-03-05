package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.yqzj.entities.v1.TopicEventRegion;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.dao
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 获取媒体操作接口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.dao.v1.TopicEventRegionDao.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年7月04日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public interface TopicEventRegionDao extends BaseDao<TopicEventRegion> {

    /**
     * 获取活跃媒体类型个数 
     * @param params
     * @return
     */
    List<TopicEventRegion> getMediaSourceTypeNum_other(Map<String,Object> params);
    
    /**
     * 获取活跃媒体详细信息 
     * @param params
     * @return
     */
    PageInfo<TopicEventRegion> getAllMediaInfo_other(Map<String,Object> params,Pagination pageInfo);
    
    /**
     * 获取导出的所有活跃媒体信息
     * @param params
     * @return
     */
    List<TopicEventRegion> getAllMediaInfo_other(Map<String,Object> params);
}
