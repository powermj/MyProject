package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.entities.v1.SubRelation;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.dao
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 专题基本信息接口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.dao.v1.SubRelationDao.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年6月26日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public interface SubRelationDao extends BaseDao<BaseEntity> {
    
    /**
     * 获取事件所属专题
     * @param paramMap
     * @return
     */
    List<SubRelation> getDetailRelationTopic(Map<String, Object> paramMap);
    
    /**
     * 获取事件路径
     * @param ksId
     * @return
     */
    SubRelation getDetailParentTopic(String ksId);
    
    /**
     * 获取用户下所有专题
     * @param paramMap
     * @return
     */
    List<SubRelation> getUserTopicList(Map<String, Object> paramMap);
    
}
