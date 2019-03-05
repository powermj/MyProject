package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.entities.v1.ReportClassify;

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
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年4月24日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public interface ReportClassifyDao extends BaseDao<BaseEntity> {

    /**
     * 添加数据分类
     * @return
     */
    int insertReportClassify(Map<String,Object> params);
    
    /**
     * 修改数据分类名称
     *
     * @return
     */
    int updateReportClassifyName(Map<String,Object> params);
    
    /**
     * 删除数据分类
     *
     * @return
     */
    int deleteReportClassifyById(Map<String,Object> params);
    
    /**
     * 获取数据分类列表
     *
     * @return
     */
    List<ReportClassify> getReportClassifys(Map<String,Object> params);
    
    /**
     * 查看数据分类名称是否已经存在
     *
     * @param params
     * @return
     */
    int getClassifyNum(Map<String,Object> params);
    
    /**
     * 获取用户下分类名称
     * 
     * @param params
     * @return
     */
    Map<Integer,String> getReportClassifyName(Map<String,Object> params);
}
