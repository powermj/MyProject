package com.zhxg.yqzj.service.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.yqzj.dao.v1.AllReportDataDao;
import com.zhxg.yqzj.dao.v1.ReportClassifyDao;
import com.zhxg.yqzj.entities.v1.ReportClassify;
import com.zhxg.yqzj.service.exception.DataReport.ReportClassifyDelException;
import com.zhxg.yqzj.service.exception.DataReport.ReportClassifyRepeatException;
import com.zhxg.yqzj.service.exception.DataReport.ReportClassifySaveException;
import com.zhxg.yqzj.service.exception.DataReport.ReportClassifyUpdateException;
import com.zhxg.yqzj.service.v1.ReportClassifyService;

@Service
public class ReportClassifyServiceImpl extends BaseServiceImpl<BaseEntity> implements ReportClassifyService {

    @Autowired
    ReportClassifyDao reportClassifyDao;
    @Autowired
    AllReportDataDao allReportDataDao;

    private final String CLASSIFY_INSERT_ERR = "添加用户自定义数据分类失败";
    private final String CLASSIFY_DELETE_ERR = "删除用户自定义数据分类失败";
    private final String CLASSIFY_UPDATE_ERR = "修改用户自定义数据分类失败";
    private final String CLASSIFY_REPEAT_ERR = "用户自定义数据分类名称重复";
    @Override
    protected BaseDao<BaseEntity> getBaseDao() {
        return this.reportClassifyDao;
    }
    
    /**
     * 添加自定义数据分类
     * @throws ReportClassifySaveException 
     * @throws ReportClassifyRepeatException 
     */
    @Override
    public int insertReportClassify(Map<String, Object> params) throws ReportClassifySaveException, ReportClassifyRepeatException {
        int result = 0;
        //查询分类名称是否已经存在
        int classifyNum = reportClassifyDao.getClassifyNum(params);
        if(classifyNum != 0){
            throw new ReportClassifyRepeatException(CLASSIFY_REPEAT_ERR);
        }
        try {
            result = reportClassifyDao.insertReportClassify(params);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ReportClassifySaveException(CLASSIFY_INSERT_ERR);
        }
        return result;
    }

    /**
     * 修改自定义数据分类
     * @throws ReportClassifyUpdateException 
     * @throws ReportClassifyRepeatException 
     */
    @Override
    public int updateReportClassifyName(Map<String, Object> params) throws ReportClassifyUpdateException, ReportClassifyRepeatException {
        int result = 0;
        //查询分类名称是否已经存在
        int classifyNum = reportClassifyDao.getClassifyNum(params);
        if(classifyNum != 0){
            throw new ReportClassifyRepeatException(CLASSIFY_REPEAT_ERR);
        }
        try {
            result = reportClassifyDao.updateReportClassifyName(params);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ReportClassifyUpdateException(CLASSIFY_UPDATE_ERR);
        }
        return result;
    }

    /**
     * 删除自定义数据分类
     * @throws ReportClassifyDelException 
     */
    @Override
    public int deleteReportClassifyById(Map<String, Object> params) throws ReportClassifyDelException {
        int result = 0;
        try {
            result = reportClassifyDao.deleteReportClassifyById(params);
            List<String> reportId = allReportDataDao.getReportIdByClassify_self(params);
            if(reportId != null && !reportId.isEmpty()){
                params.put("infoUuidArr", reportId.toArray());
                params.put("classifyId", 0);
                allReportDataDao.updateReportClassifyIds_self(params);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ReportClassifyDelException(CLASSIFY_DELETE_ERR);
        }
        return result;
    }

    /**
     * 获取数据分类列表
     */
    @Override
    public List<ReportClassify> getReportClassifys(Map<String, Object> params) {
        List<ReportClassify> reportClassifys = reportClassifyDao.getReportClassifys(params);
        return reportClassifys;
    }

}
