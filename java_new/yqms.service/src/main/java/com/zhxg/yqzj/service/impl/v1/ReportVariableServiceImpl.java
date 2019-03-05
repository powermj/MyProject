package com.zhxg.yqzj.service.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.yqzj.dao.v1.ReportVariableDao;
import com.zhxg.yqzj.entities.v1.ReportVariable;
import com.zhxg.yqzj.service.exception.DataReport.ReportClassifyRepeatException;
import com.zhxg.yqzj.service.exception.DataReport.ReportVariableDelException;
import com.zhxg.yqzj.service.exception.DataReport.ReportVariableSaveException;
import com.zhxg.yqzj.service.exception.DataReport.ReportVariableUpdateException;
import com.zhxg.yqzj.service.v1.ReportVariableService;

@Service
public class ReportVariableServiceImpl extends BaseServiceImpl<BaseEntity> implements ReportVariableService {

    @Autowired
    ReportVariableDao reportVariableDao;

    private final String VARIABLE_INSERT_ERR = "添加用户自定义变量失败";
    private final String VARIABLE_DELETE_ERR = "删除用户自定义变量失败";
    private final String VARIABLE_UPDATE_ERR = "修改用户自定义变量失败";
    //private final String VARIABLE_REPEAT_ERR = "用户自定义变量重复";
    @Override
    protected BaseDao<BaseEntity> getBaseDao() {
        return this.reportVariableDao;
    }
    
    @Override
    public int insertReportVariable(Map<String, Object> params) throws ReportVariableSaveException, ReportClassifyRepeatException {
        int result = 0;
        try {
            this.operateParams(params);
            result = reportVariableDao.insertReportVariable(params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ReportVariableSaveException(VARIABLE_INSERT_ERR);
        }
        return result;
    }
    @Override
    public int updateReportVariable(Map<String, Object> params) throws ReportVariableUpdateException {
        int result = 0;
        try {
            result = reportVariableDao.updateReportVariable(params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ReportVariableUpdateException(VARIABLE_UPDATE_ERR);
        }
        return result;
    }
    @Override
    public int deleteReportVariableById(Map<String, Object> params) throws ReportVariableDelException {
        int result = 0;
        try {
            result = reportVariableDao.deleteReportVariableById(params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ReportVariableDelException(VARIABLE_DELETE_ERR);
        }
        return result;
    }
    @Override
    public List<ReportVariable> getReportVariables(Map<String, Object> params) {
        List<ReportVariable> reportVariables = reportVariableDao.getReportVariables(params);
        return reportVariables;
    }
    
    @Override
    public ReportVariable getReportVariable(Map<String, Object> params) {
        return reportVariableDao.getReportVariable(params);
    }
    
    private void operateParams(Map<String,Object> params){
       switch (Integer.valueOf((String) params.get("initType"))){
           case 1:
               params.put("initName", "day");
               params.put("initTimeStamp", System.currentTimeMillis()+"");
               params.put("timeInterval", 3600*24*1000+"");
               break;
           case 2:
               params.put("initName", "week");
               params.put("initTimeStamp", System.currentTimeMillis()+"");
               params.put("timeInterval", 7*3600*24*1000+"");
               break;
           case 3:
               params.put("initName", "auto");
               params.put("initTimeStamp", System.currentTimeMillis()+"");
               params.put("timeInterval", 0);
               break;
       }
    }

}
