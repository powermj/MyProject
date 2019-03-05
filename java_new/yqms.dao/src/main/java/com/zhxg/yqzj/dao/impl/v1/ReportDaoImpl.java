package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.constants.SysConstants;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.yqzj.dao.v1.ReportDao;
import com.zhxg.yqzj.entities.v1.Report;
import com.zhxg.yqzj.entities.v1.ReportTemplate;

@Repository
public class ReportDaoImpl extends BaseDaoImpl<Report> implements ReportDao {

	private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.Report.";
	
	private static final String EXPERT_NAME_SPACE = "com.zhxg.yqzj.entities.v1.ExpertReport.";
	
	//private static final String REPORT_TEMPLATE_SPACE = "com.zhxg.yqzj.entities.v1.ReportTemplate.";
	
	@Override
	public List<Report> getReportList_self(Map<String, Object> paramMap) {
		return sqlSessionTemplate.selectList(NAME_SPACE + "getReportList", paramMap);
	}

    @Override
    public List<Report> getExpertReportTreeList(Map<String, Object> paramMap) {
        return sqlSessionTemplate.selectList(EXPERT_NAME_SPACE + "getExpertReportTreeList", paramMap);
    }

    @Override
    public PageInfo<Report> getExpertReportList(Map<String, Object> paramMap, Pagination pageInfo) {  
        return this.getPageList(EXPERT_NAME_SPACE + "getExpertReportList", pageInfo, paramMap);
    }

	@Override
	public List<Map<String, Integer>> getExpertReportCount(Map<String, Object> paramMap) {
		return sqlSessionTemplate.selectList(EXPERT_NAME_SPACE + "getExpertReportCount", paramMap);
	}

	@Override
	public int insertExpertReport(Report report) {
		int result = 0;
		result = sqlSessionTemplate.insert(EXPERT_NAME_SPACE + "insertExpertReport", report);
		if(result>0&&report.getReportclassid().equals(SysConstants.EXPERT_REPORT_TYPE)){
			int lastId = this.getInsertId();
			report.setId(lastId+"");
			result = sqlSessionTemplate.insert(EXPERT_NAME_SPACE + "insertExpertSpecialReport", report);
		}
		return result;
	}

    @Override
    public List<Map<String, String>> getIsMyExpertReport(Map<String, Object> paramMap) {
        return sqlSessionTemplate.selectList(EXPERT_NAME_SPACE + "getIsMyExpertReport", paramMap);
    }

    @Override
    public ReportTemplate getReportTemplate(Map<String, Object> paramMap) {
        return sqlSessionTemplate.selectOne(NAME_SPACE + "getReportTemplateList", paramMap);
    }

    @Override
    public int insertReportTemplate(Map<String, Object> paramMap) {
        return sqlSessionTemplate.insert(NAME_SPACE+"insertReportTemplate", paramMap);
    }

    @Override
    public List<ReportTemplate> getAllReportTemplate(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getAllReportTemplate", paramMap);
    }

    @Override
    public int updateTemplate(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.update(NAME_SPACE+"updateTemplate", paramMap);
    }

    @Override
    public int deleteTemplate(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.delete(NAME_SPACE+"deleteTemplate", paramMap);
    }

    @Override
    public int updateReportFileInfo(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.update(NAME_SPACE+"updateReportFileInfo",paramMap);
    }

    @Override
    public int deleteReportFileInfo(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.delete(NAME_SPACE+"deleteReportFileInfo", paramMap);
    }

    @Override
    public int getTemplateCount(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getTemplateCount", paramMap);
    }
    
}
