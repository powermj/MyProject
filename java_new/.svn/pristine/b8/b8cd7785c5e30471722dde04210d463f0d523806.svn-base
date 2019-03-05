package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.entities.v1.ValidationRef;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.dao
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 详情页基本信息接口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.dao.v1.UserDao.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年6月22日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public interface ValidationRefDao extends BaseDao<BaseEntity> {

    /**
     * 获取查询详情页基本信息的关键词(个人库)
     * @param paramMap
     * @return
     */
    ValidationRef getDetailRefInfo_self(Map<String, Object> paramMap);
    
    /**
     * 批量获取simHash修改倾向性
     * @param paramMap
     * @return
     */
    List<String> getDetailRefInfos_self(Map<String, Object> paramMap);
    
    /**
     * 通过simhash获取相同信息 最多100(个人库)
     * @param paramMap
     * @return
     */
    List<ValidationRef> getDetailSameInfo_self(Map<String, Object> paramMap);
    
    /**
     * 获取信息转载数
     * @param paramMap
     * @return
     */
    int getReloadNumber_self(Map<String, Object> paramMap);
    
    /**
     * 获取详细信息内容(7天内)
     * @param paramMap
     * @return
     */
    String getDetailInfoCnt_info(Map<String, Object> paramMap);
    
    ValidationRef getInfoContent_info(Map<String, Object> paramMap);
    
    /**
     * 获取详细信息内容(超出7天)
     * @param paramMap
     * @return
     */
    ValidationRef getDetailInfoCnt_self(Map<String, Object> paramMap);
    /**
     * 获取地域详细信息内容
     * @param paramMap
     * @return
     */
    String getDetailRegionInfoCnt_info(Map<String, Object> paramMap);
    
    /**
     * 获取专题id
     * @param paramMap
     * @return
     */
    List<ValidationRef> getDetailRelatedTopicId_self(Map<String, Object> paramMap);
    
    /**
     * 修改当前信息及相同信息倾向性
     * @param paramMap
     * @return
     */
    int updateYqllOrgiation_self(Map<String, Object> paramMap);
    
    /**
     * 获取详细分享人信息内容(7天内)
     * @param paramMap
     * @return
     */
    String getDetailShareInfoCnt_info(Map<String, Object> paramMap);
    
    ValidationRef getShareInfoContent_info(Map<String, Object> paramMap);
    
    /**
     * 获取分享人详细信息内容(超出7天)
     * @param paramMap
     * @return
     */
    String getDetailShareInfoCnt_other(Map<String, Object> paramMap);
}
