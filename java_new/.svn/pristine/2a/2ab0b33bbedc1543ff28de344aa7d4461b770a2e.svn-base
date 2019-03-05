package com.zhxg.yqzj.service.v1;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.Report;
import com.zhxg.yqzj.entities.v1.ReportFileInfo;
import com.zhxg.yqzj.entities.v1.ReportTemplate;
import com.zhxg.yqzj.entities.v1.SubRelation;
import com.zhxg.yqzj.entities.v1.UserMailExport;
import com.zhxg.yqzj.service.exception.DataReport.ReportClassifyDelException;
import com.zhxg.yqzj.service.exception.DataReport.ReportClassifyRepeatException;
import com.zhxg.yqzj.service.exception.DataReport.ReportClassifySaveException;
import com.zhxg.yqzj.service.exception.DataReport.ReportClassifyUpdateException;
import com.zhxg.yqzj.service.exception.report.ReportException;

public interface ReportService extends BaseService<Report> {

	List<Report> getReportList(Map<String, Object> paramMap) throws SysException;

    List<Report> getExpertReportTreeList(Map<String, Object> paramMap) throws SysException;

    PageInfo<Report> getExpertReportList(Map<String, Object> paramMap, Pagination pageInfo) throws SysException;

    List<Map<String, Integer>> getExpertReportCount(Map<String, Object> paramMap);

	int applyExpertReport(Report report);

	Map<String, String> getIsMyExpertReport(Map<String, Object> paramMap);

    ReportTemplate getReportTemplate(Map<String, Object> paramMap);
    
    List<ReportTemplate> getAllReportTemplate(Map<String, Object> paramMap);

    /**
     * 导出报告 
     * @param paramMap
     * @return
     * @throws ReportClassifySaveException
     */
    int exportReport(Map<String, Object> paramMap) throws ReportClassifySaveException, ReportClassifyUpdateException;
    /**
     * 获取报告文件信息列表 
     * @param paramMap
     * @param pageInfo
     * @return
     */
    PageInfo<ReportFileInfo> getReportFileInfos(Map<String, Object> paramMap, Pagination pageInfo);
    /**
     * 添加报告模板
     * @param paramMap
     * @return
     * @throws ReportClassifySaveException
     */
    int insertReportTemplate(Map<String, Object> paramMap) throws ReportClassifySaveException, ReportClassifyRepeatException;
    
    /**
     * 修改简报模板信息 
     * @param paramMap
     * @return
     */
    int updateReportTemplate(Map<String,Object> paramMap)  throws ReportClassifyUpdateException , ReportClassifyRepeatException;
    
    /**
     * 删除简报模板 
     * @param paramMap
     * @return
     */
    int deleteReportTemplate(Map<String,Object> paramMap)  throws ReportClassifyDelException ;
    
    /**
     *  修改报告信息
     * @param paramMap
     * @return
     */
    int updateReportFileInfo(Map<String,Object> paramMap)  throws ReportClassifyUpdateException;
    
    /**
     *  删除报告信息
     * @param paramMap
     * @return
     */
    int deleteReportFileInfo(Map<String,Object> paramMap)  throws ReportClassifyDelException ;
    
    /**
     * 批量打包下载 
     *
     * @param paramMap
     * @return
     */
    String batchDownloadFile(Map<String,Object> paramMap);
    
    /**
     * 
     * 添加excel模板
     * @param paramMap
     * @return
     * @throws ReportClassifySaveException
     * @throws ReportClassifyRepeatException
     */
    int inserTemplateExcel(Map<String,Object> paramMap) throws ReportClassifySaveException , ReportClassifyRepeatException;
    /**
     * 获取用户订阅日报状态
     *
     * @param paramMap
     * @return
     */
    int getUserSubcribeStatus(Map<String,Object> paramMap);
    
    /**
     * 获取用户接收日报时间
     * @param paramMap
     * @return
     */
    String getUserReceiveTime(Map<String,Object> paramMap);
    
    /**
     * 保存用户接收日报信息
     * @param paramMap
     * @return
     */
    int saveUserReceiveInfo(Map<String,Object> paramMap)throws ReportException;
    
    /**
     * 设置用户默认日报模板
     * @param paramMap
     * @return
     * @throws ReportException
     */
    int setUserDefaultTemplate(Map<String,Object> paramMap)throws ReportException;
    
    /**
     * 获取用户默认日报模板
     * @param paramMap
     * @return
     * @throws ReportException
     */
    String getUserDefaultTemplate(Map<String,Object> paramMap)throws ReportException;
    
    /**
     * 获取用户接收邮箱
     * @param paramMap
     * @return
     */
    List<UserMailExport> getUserReceiveEmail(Map<String,Object> paramMap);
    
    /**
     * 获取用户所有专题
     * @param paramMap
     * @return
     */
    List<SubRelation> getUserTopicList(Map<String,Object> paramMap);
    
    /**
     * 设置用户日报导出查询信息条件
     * @param paramMap
     * @return
     */
    int setUserReportCondition(Map<String,Object> paramMap)throws ReportException;
    
    /**
     * 
     * 获取用户日报导出查询条件
     * @param paramMap
     * @return
     */
    String getUserReportCondition(Map<String,Object> paramMap);

	/**
	 * 修改日报订阅状态
	 * @param paramMap
	 * @return
	 * @throws ReportException 
	 */
	int updateUserSubcribeStatus(Map<String, Object> paramMap) throws ReportException;
}
