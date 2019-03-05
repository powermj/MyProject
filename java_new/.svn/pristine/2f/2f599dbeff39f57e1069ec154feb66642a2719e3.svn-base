package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.entities.v1.UserAreaNewInfo;
import com.zhxg.yqzj.entities.v1.UserBaseInfo;

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
 * NameSpace: com.zhxg.yqms.dao.v1.MobileCloudIndustryInfoDao.java
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
public interface UserBaseInfoDao extends BaseDao<BaseEntity> {

    /**
     * 获取移动云用户状态信息 
     * @param params
     * @return
     */
    List<UserBaseInfo> getUserBaseInfo(Map<String,Object> params);
    
    /**
     * 根据用户状态获取用户信息 
     * @param params
     * @return
     */
    UserBaseInfo getUserBaseInfoByType(Map<String,Object> params);
    
    /**
     * 修改用户信息
     * @param params
     * @return
     */
    int updateUserServiceInfo(Map<String,Object> params);
    /**
     * 修改用户名称,手机号码,用户类型
     * @param params
     * @return
     */
    int updateUserInfo(Map<String,Object> params);
    
    /**
     * 修改用户地域信息
     * @param params
     * @return
     */
    int updateUserAreaNewInfo(Map<String,Object> params);
    
    /**
     * 获取用户移动云秘书版本
     * @param params
     * @return
     */
    String getMobileCloudVersion(Map<String,Object> params);
    
    /**
     * 获取移动云秘书是否设置品牌词
     */
    /**
     * 获取用户移动云秘书版本
     * @param params
     * @return
     */
    String getMobileCloudAreaSet(Map<String,Object> params);
    
    /**
     * 删除用户第一次登陆信息
     * @param params
     * @return
     */
    int delFirstLoginInfo(Map<String,Object> params);
    
    /**
     * 删除用户进入引导所需信息
     * @param params
     * @return
     */
    int delFirstGuideInfo(Map<String,Object> params);
    
    /**
     * 获取用户移动云账户二级行业id
     * @param params
     * @return
     */
    String getMobileCloudDepartmentId(Map<String,Object> params);
    
    /**
     * 查询用户地域信息
     * @param params
     * @return
     */
    UserAreaNewInfo getUserAreaNewInfo(Map<String,Object> params);
    
    /**
     * 查询移动云用户类型
     * @param params
     * @return
     */
    String getMobileCloudSex(Map<String,Object> params);
    /**
     * 移动云试用转商用
     * @param params
     * @return
     */
    int updateUserVerKeyNum(Map<String,Object> params);
    /**
     * 获取标签行业id 
     *
     * @param params
     * @return
     */
    String getLableIndustryId(Map<String,Object> params);
    
    /**
     * copySubjectInfo
     *
     * @param params
     * @return
     */
    String copySubjectInfo(Map<String,Object> params);
    
    /**
     * 保存并修改用户默认日报导出邮箱
     * @param params
     * @return
     */
    int saveUserReceiveMail(Map<String,Object> params);
    
    /**
     * 保存并修改用户默认日报导出模板
     * @param params
     * @return
     */
    int saveUserReceiveReportId(Map<String,Object> params);
    
    /**
     * 保存并修改用户默认日报导出模板
     * @param params
     * @return
     */
    String getUserDefaultTemplate(Map<String,Object> params);
    
    
    
    /**
     * 获取用户默认的接收邮箱
     * @param params
     * @return
     */
    String getUserReceiveMail(Map<String,Object> params);
    
    int delHotKeywordFilter(Map<String,Object> params);
    
    int updateHotKeywordFilter(Map<String,Object> params);
    
    int insertHotKeywordFilter(Map<String,Object> params);
    
    String selectHotKeywordFilter(Map<String,Object> params);
    
    /**
     * 获取用户是否订阅周报状态
     *
     * @param paramMap
     * @return
     */
    int getUserWeekSubcribeStatus(Map<String, Object> paramMap);
    
    /**
     * 保存或修改用户周报订阅状态
     * @param paramMap
     * @return
     */
    int saveUserWeekSubcribeStatus(Map<String, Object> paramMap);
    
    /**
     * 获取用户周报接收时间
     * @param paramMap
     * @return
     */
    String getUserWeekReceviveTime(Map<String,Object> paramMap);
    
    /**
     * 保存或修改用户周报接收时间
     * @param paramMap
     * @return
     */
    int saveUserWeekReceviveTime(Map<String, Object> paramMap);
    
    /**
     * 保存或修改用户周报接收邮箱
     * @param paramMap
     * @return
     */
    int saveUserWeekReceviveEmail(Map<String, Object> paramMap);
    
    
    /**
     * 获取用户周报默认模板
     * @param params
     * @return
     */
    String getUserWeekDefaultTemplate(Map<String,Object> params);
    
    /**
     * 保存或修改用户默认模板
     * @param paramMap
     * @return
     */
    int saveUserWeekReceiveReportId(Map<String, Object> paramMap);
    
    /**
     * 获取用户周报接收邮箱
     * @param paramMap
     * @return
     */
    String getUserWeekReceiveMail(Map<String, Object> paramMap);
    
    /**
     * 保存用户周报导出条件
     * @param paramMap
     * @return
     */
    int saveUserWeekReportCondition(Map<String, Object> paramMap);
    
    /**
     * 获取用户周报导出条件
     * @param paramMap
     * @return
     */
    String getUserWeekReportCondition(Map<String, Object> paramMap);
    
    int deleteUserInfo(Map<String,Object> params);
    
    int modifyUserInfo(Map<String,Object> params);
    
    int insertUserInfo(Map<String,Object> params);
    
    String selectUserInfo(Map<String,Object> params);
    
}
