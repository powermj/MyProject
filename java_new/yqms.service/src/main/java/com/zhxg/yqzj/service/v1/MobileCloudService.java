package com.zhxg.yqzj.service.v1;

import java.util.List;
import java.util.Map;

import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.MobileCloudCourse;
import com.zhxg.yqzj.entities.v1.MobileCloudIndustryInfo;
import com.zhxg.yqzj.entities.v1.MobileCloudRecommendTopic;
import com.zhxg.yqzj.entities.v1.MobileCloudRegionalInfo;
import com.zhxg.yqzj.service.exception.mobilecloud.MobileCloudDelUserInfoException;
import com.zhxg.yqzj.service.exception.mobilecloud.MobileCloudSaveSpecialInfoException;
import com.zhxg.yqzj.service.exception.mobilecloud.MobileCloudUpdateUserInfoException;
import com.zhxg.yqzj.service.exception.mobilecloud.MobileCloudUserInfoLoseException;

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
public interface MobileCloudService extends BaseService<BaseEntity> {

    /**
     * 获取行业信息
     *
     * @param params
     * @return
     */
    List<MobileCloudIndustryInfo> getAllIndustryInfo(Map<String,Object> params);
    
    /**
     * 获取地域信息 
     *
     * @param params
     * @return
     */
    List<MobileCloudRegionalInfo> getAllRegionalInfo(Map<String,Object> params);
    
    /**
     * 获取行业所属专题信息 
     *
     * @param params
     * @return
     */
    List<MobileCloudRecommendTopic> getAllRecommendTopic(Map<String,Object> params);
    
    /**
     * 添加推荐专题 
     *
     * @param params
     * @return
     */
    List<Map<String, Object>> setSpecialInfo(Map<String,Object> params)throws MobileCloudUserInfoLoseException,
    MobileCloudUpdateUserInfoException, MobileCloudDelUserInfoException, MobileCloudSaveSpecialInfoException;
    
    /**
     * 删除用户进入引导所需信息
     * @param params
     * @return
     */
    int delFirstGuideInfo(Map<String,Object> params)throws MobileCloudDelUserInfoException;
    
    /**
     * 专题设置页面保存推荐专题
     * @param params
     * @return
     */
    List<Map<String, Object>> setSpecialInfoForInstall(Map<String, Object> params)throws MobileCloudSaveSpecialInfoException;
    
    /**
     * 获取舆情课程列表
     * @param params
     * @return
     */
    List<MobileCloudCourse> getAllCourse(Map<String,Object> params);
    
    
    /**
     * 获取舆情课程及舆情课件 
     * @param params
     * @return
     */
    List<MobileCloudCourse> getCourseList(Map<String,Object> params);
    
    /**
     * 获取舆情课件列表
     * @param params
     * @return
     */
    List<MobileCloudCourse> getAllCoursewareInfo(Map<String,Object> params);
    
} 
