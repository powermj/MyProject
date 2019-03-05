package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.entities.v1.MobileCloudRegionalInfo;

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
public interface MobileCloudRegionalInfoDao extends BaseDao<BaseEntity> {

    /**
     * 获取移动云地域信息
     * @param params
     * @return
     */
    List<MobileCloudRegionalInfo> getAllRegionalInfo(Map<String,Object> params);
    
    
    /**
     * 获取推荐专题地域词 
     * @param params
     * @return
     */
    List<MobileCloudRegionalInfo> getSpecialRegionalInfo(Map<String,Object> params);
    
   
}
