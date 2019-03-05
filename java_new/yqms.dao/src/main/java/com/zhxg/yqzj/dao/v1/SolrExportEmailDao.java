package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.entities.v1.UserMailExport;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.dao
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: <对此类的描述>
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqzj.dao.v1.SolrExportEmailDao.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年5月8日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public interface SolrExportEmailDao extends BaseDao<BaseEntity> {

    /**
     * 获取导出邮箱列表
     *
     * @return
     */
    List<UserMailExport> getExportEmail(Map<String, Object> params);
    
    /**
     * 获取导出邮箱列表
     *
     * @return
     */
    List<UserMailExport> getUserDefaultEmail(Map<String, Object> params);

    /**
     * 获取个人邮箱
     *
     * @param userid
     * @return
     */
    String getUserEmail(Map<String, Object> params);

    /**
     * 添加导出邮箱
     *
     * @param params
     * @return
     */
    int setExportEmail(Map<String, Object> params);

    /**
     * 查询邮箱是否添加
     *
     * @param params
     * @return
     */
    int getCountByEmail(Map<String, Object> params);
}
