package com.zhxg.yqzj.service.v1;

import java.util.List;
import java.util.Map;

import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.ReportTemplate;
import com.zhxg.yqzj.entities.v1.UserMailExport;
import com.zhxg.yqzj.service.exception.report.ReportException;

public interface ReportWeekService extends BaseService<BaseEntity> {
    /**
     * 获取用户所有周报模板
     * @param paramMap
     * @return
     */
    List<ReportTemplate> getAllWeekReportTemplate(Map<String, Object> paramMap,int defaultTemplateId);
    /**
     * 获取用户订阅周报状态
     *
     * @param paramMap
     * @return
     */
    int getUserWeekSubcribeStatus(Map<String,Object> paramMap);
    
    /**
     * 保存用户订阅状态 
     * @param paramMap
     * @return
     */
    int saveUserWeekSubcribeStatus(Map<String,Object> paramMap) throws ReportException ;
    
    /**
     * 获取用户接收周报时间
     * @param paramMap
     * @return
     */
    String getUserWeekReceiveTime(Map<String,Object> paramMap);
    
    /**
     * 保存用户接收周报信息
     * @param paramMap
     * @return
     */
    int saveUserWeekReceiveInfo(Map<String,Object> paramMap)throws ReportException;
    
    /**
     * 设置用户默认周报模板
     * @param paramMap
     * @return
     * @throws ReportException
     */
    int setUserWeekDefaultTemplate(Map<String,Object> paramMap)throws ReportException;
    
    /**
     * 获取用户默认周报模板
     * @param paramMap
     * @return
     * @throws ReportException
     */
    String getUserWeekDefaultTemplate(Map<String,Object> paramMap)throws ReportException;
    
    /**
     * 获取用户接收邮箱
     * @param paramMap
     * @return
     */
    List<UserMailExport> getUserWeekReceiveEmail(Map<String,Object> paramMap);
    
    /**
     * 设置用户周报导出查询信息条件
     * @param paramMap
     * @return
     */
    int setUserWeekReportCondition(Map<String,Object> paramMap)throws ReportException;
    
    /**
     * 
     * 获取用户周报导出查询条件
     * @param paramMap
     * @return
     */
    String getUserWeekReportCondition(Map<String,Object> paramMap);

}
