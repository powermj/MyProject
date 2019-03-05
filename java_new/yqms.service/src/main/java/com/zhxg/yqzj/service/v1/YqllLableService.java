package com.zhxg.yqzj.service.v1;

import java.util.List;
import java.util.Map;

import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.YqllLableInfo;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.service
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 移动云业务接口
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
public interface YqllLableService extends BaseService<BaseEntity> {

    /**
     * 获取行业下所有标签
     *
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
