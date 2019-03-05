package com.zhxg.yqzj.dao.v1;

import java.util.Map;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.yqzj.entities.v1.ChannelTV;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.dao
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户模块持久层接口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.dao.v1.UserDao.java
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
public interface ChannelTVDao extends BaseDao<ChannelTV> {

    public int uploadTVMonitoringInformation_self(Map<String, Object> paramMap);

    /**
     * 删除电视监控信息
     *
     * @param paramMap
     * @return
     */
    public int deleteTVMonitoringInformation_self(Map<String, Object> paramMap);
    
}
