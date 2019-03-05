package com.zhxg.yqzj.service.impl.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.yqzj.dao.v1.ReportTemplateDao;
import com.zhxg.yqzj.dao.v1.SolrExportEmailDao;
import com.zhxg.yqzj.dao.v1.UserBaseInfoDao;
import com.zhxg.yqzj.entities.v1.ReportTemplate;
import com.zhxg.yqzj.entities.v1.UserMailExport;
import com.zhxg.yqzj.service.exception.report.ReportException;
import com.zhxg.yqzj.service.v1.ReportWeekService;

@Service
public class ReportWeekServiceImpl extends BaseServiceImpl<BaseEntity> implements ReportWeekService {

    @Autowired
    private ReportTemplateDao reportTemplateDao;
    @Autowired
    private UserBaseInfoDao userBaseInfoDao;
    @Autowired
    private SolrExportEmailDao emailDao;
    
    @Override
    protected BaseDao<BaseEntity> getBaseDao() {
        return this.userBaseInfoDao;
    }
    
    private final String SAVE_RECEIVEINFO_ERROR = "保存或修改用户周报接收信息失败";
    private final String SAVE_SUBCRIBE_ERROR = "保存或修改用户周报订阅状态失败";
    private final String SAVE_DEFAULTID_ERROR = "保存或修改用户默认导出模板失败";
    private final String SAVE_CONDITION_ERROR = "保存或修改用户周到导出条件失败";
    
    @Override
    public List<ReportTemplate> getAllWeekReportTemplate(Map<String, Object> paramMap,int defaultTemplateId) {
        List<ReportTemplate> templateList = reportTemplateDao.getAllWeekReportTemplate(paramMap);
        String templateId = userBaseInfoDao.getUserWeekDefaultTemplate(paramMap);
        if(StringUtils.isBlank(templateId)){
            if(("2".equals(paramMap.get("type"))||"4".equals(paramMap.get("type"))) && "1".equals(paramMap.get("templateType"))){
                List<ReportTemplate> list = new ArrayList<>();
                for (ReportTemplate reportTemplate : templateList) {
                    if(1 ==reportTemplate.getReportSelected() && defaultTemplateId != reportTemplate.getId()){
                        break;
                    }
                    if(defaultTemplateId == reportTemplate.getId() && 1 !=reportTemplate.getReportSelected()){
                        reportTemplate.setReportSelected(1);
                        list.add(reportTemplate);
                    }
                }
                templateList.removeAll(list);
                list.addAll(templateList);
                return list;
            }
        }
            return templateList;
    }

    @Override
    public int getUserWeekSubcribeStatus(Map<String, Object> paramMap) {
        return this.userBaseInfoDao.getUserWeekSubcribeStatus(paramMap);
    }
    
    @Override
    public int saveUserWeekSubcribeStatus(Map<String, Object> paramMap) throws ReportException {
        int result = 0;
        try {
            result = userBaseInfoDao.saveUserWeekSubcribeStatus(paramMap);
        } catch (Exception e) {
            logger.info(e.getMessage(),e);
            throw new ReportException("021",SAVE_SUBCRIBE_ERROR);
        }
        return result;
    }

    @Override
    public String getUserWeekReceiveTime(Map<String, Object> paramMap) {
        return this.userBaseInfoDao.getUserWeekReceviveTime(paramMap);
    }

    @Override
    public int saveUserWeekReceiveInfo(Map<String, Object> paramMap) throws ReportException {
        int result = 0;
        try {
            result = userBaseInfoDao.saveUserWeekReceviveTime(paramMap);
            result = userBaseInfoDao.saveUserWeekReceviveEmail(paramMap);
        } catch (Exception e) {
           logger.info(e.getMessage(),e);
           throw new ReportException("022",SAVE_RECEIVEINFO_ERROR);
        }
        return result;
    }

    @Override
    public int setUserWeekDefaultTemplate(Map<String, Object> paramMap) throws ReportException {
        int result = 0;
        try {
            result = userBaseInfoDao.saveUserWeekReceiveReportId(paramMap);
        } catch (Exception e) {
            logger.info(e.getMessage(),e);
            throw new ReportException("023", SAVE_DEFAULTID_ERROR);
        }
        return result;
    }

    @Override
    public String getUserWeekDefaultTemplate(Map<String, Object> paramMap) throws ReportException {
        return this.userBaseInfoDao.getUserWeekDefaultTemplate(paramMap);
    }

    @Override
    public List<UserMailExport> getUserWeekReceiveEmail(Map<String, Object> paramMap) {
        List<UserMailExport> emailList = null;
        String defaultMail = userBaseInfoDao.getUserWeekReceiveMail(paramMap);
        //标记选中邮箱
        if(StringUtils.isNotBlank(defaultMail)){
            String[] emailIdArr = defaultMail.split(",");
            paramMap.put("emailIdArr", emailIdArr);
            emailList = emailDao.getUserDefaultEmail(paramMap);
            for (String emailId : emailIdArr) {
                for (UserMailExport userMail : emailList) {
                    if(emailId.equals(userMail.getKmUuid())){
                        userMail.setKmSelected("1");
                    }
                }
            }
        }else{
            emailList = emailDao.getUserDefaultEmail(paramMap);
        }
        return emailList;
    }

    @Override
    public int setUserWeekReportCondition(Map<String, Object> paramMap) throws ReportException {
        int result = 0;
        try {
            result = this.userBaseInfoDao.saveUserWeekReportCondition(paramMap);
        } catch (Exception e) {
            logger.info(e.getMessage(),e);
            throw new ReportException("024", SAVE_CONDITION_ERROR);
        }
        return result;
    }

    @Override
    public String getUserWeekReportCondition(Map<String, Object> paramMap) {
        return this.userBaseInfoDao.getUserWeekReportCondition(paramMap);
    }
}
