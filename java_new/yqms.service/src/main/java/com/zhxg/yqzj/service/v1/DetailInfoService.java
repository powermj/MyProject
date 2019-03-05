package com.zhxg.yqzj.service.v1;

import java.util.Map;

import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.service.BaseService;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.service
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 详情页信息接口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.service.v1.UserService.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年6月20日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public interface DetailInfoService extends BaseService<BaseEntity> {

    /**
     * 获取舆情浏览详情页 
     *
     * @param params
     * @return
     */
    Map<String, Object> getYqllDetailInfo(Map<String, Object> params);
    
    /**
     * 修改舆情浏览倾向性
     *
     * @param params
     * @return
     */
    int updateYqllOrgiation(Map<String, Object> params);
        
}
