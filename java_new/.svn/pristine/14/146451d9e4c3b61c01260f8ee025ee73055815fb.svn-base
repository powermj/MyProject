package com.zhxg.yqzj.service.v1;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.SourceType;
import com.zhxg.yqzj.entities.v1.TopicEventRegion;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.service
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 活跃媒体详细页面接口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.service.v1.ActiveMediaService.java
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
public interface ActiveMediaService extends BaseService<BaseEntity> {

    /**
     * 获取舆情浏览详情页 
     *
     * @param params
     * @return
     */
    List<SourceType> getAllSourceType(Map<String,Object> params);
    
    /**
     * 活跃媒体信息列表
     * @param params
     * @param pageInfo
     * @return
     */
    PageInfo<TopicEventRegion> getAllMediaInfo(Map<String,Object> params,Pagination pageInfo);
    
    /**
     * 导出所有活跃媒体信息
     * @param params
     * @return
     */
    String exportAllMediaInfo(Map<String,Object> params);
        
}
