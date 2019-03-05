package com.zhxg.yqzj.dao.impl.v1;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.yqzj.dao.v1.ChannelTVDao;
import com.zhxg.yqzj.entities.v1.ChannelTV;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.dao
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户模块持久层接口实现
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.dao.impl.v1.UserDaoImpl.java
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
@Repository
public class ChannelTVDaoImpl extends BaseDaoImpl<ChannelTV> implements ChannelTVDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.ChannelTV.";

    @Override
    public int uploadTVMonitoringInformation_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.insert(NAME_SPACE+"uploadTVMonitoringInformation", paramMap);
    }

    @Override
    public int deleteTVMonitoringInformation_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.update(NAME_SPACE + "deleteTVMonitoringInformation", paramMap);
    }
   
}
