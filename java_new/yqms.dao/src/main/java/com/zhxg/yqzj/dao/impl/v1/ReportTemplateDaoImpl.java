package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.ReportTemplateDao;
import com.zhxg.yqzj.entities.v1.ReportTemplate;

@Repository
public class ReportTemplateDaoImpl  extends BaseDaoImpl<BaseEntity> implements ReportTemplateDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.ReportTemplate.";
    @Override
    public List<ReportTemplate> getAllWeekReportTemplate(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getAllWeekReportTemplate", paramMap);
    }

}
