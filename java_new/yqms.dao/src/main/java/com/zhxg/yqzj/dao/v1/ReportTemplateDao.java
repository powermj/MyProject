package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.zhxg.yqzj.entities.v1.ReportTemplate;

public interface ReportTemplateDao{

    /**
     * 获取所有周报模板
     * @param paramMap
     * @return
     */
    List<ReportTemplate> getAllWeekReportTemplate(Map<String, Object> paramMap);
}
