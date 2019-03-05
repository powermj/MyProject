package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.yqzj.entities.v1.Report;
import com.zhxg.yqzj.entities.v1.ReportTemplate;

public interface ReportDao extends BaseDao<Report>{

	List<Report> getReportList_self(Map<String, Object> paramMap);

    List<Report> getExpertReportTreeList(Map<String, Object> paramMap);

    PageInfo<Report> getExpertReportList(Map<String, Object> paramMap, Pagination pageInfo);

    List<Map<String, Integer>> getExpertReportCount(Map<String, Object> paramMap);

	int insertExpertReport(Report report);

	List<Map<String, String>> getIsMyExpertReport(Map<String, Object> paramMap);

    ReportTemplate getReportTemplate(Map<String, Object> paramMap);

    int insertReportTemplate(Map<String, Object> paramMap);
    
    List<ReportTemplate> getAllReportTemplate(Map<String, Object> paramMap);
    
    int updateTemplate(Map<String, Object> paramMap);
    
    int deleteTemplate(Map<String, Object> paramMap);
    
    int updateReportFileInfo(Map<String,Object> paramMap);
    
    int deleteReportFileInfo(Map<String,Object> paramMap);
    
    int getTemplateCount(Map<String,Object> paramMap);
}
