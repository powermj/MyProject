package com.zhxg.yqzj.service.impl.v1;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.framework.base.utils.HtmlFilter;
import com.zhxg.framework.base.utils.UUIDUtil;
import com.zhxg.yqzj.dao.v1.AllReportDataDao;
import com.zhxg.yqzj.dao.v1.ReportClassifyDao;
import com.zhxg.yqzj.entities.v1.AllReportData;
import com.zhxg.yqzj.entities.v1.ReportClassify;
import com.zhxg.yqzj.service.exception.DataReport.DeleteReportInfoException;
import com.zhxg.yqzj.service.exception.DataReport.InsertReportInfoException;
import com.zhxg.yqzj.service.exception.DataReport.UpdateReportInfoException;
import com.zhxg.yqzj.service.util.DetailOperateUtil;
import com.zhxg.yqzj.service.v1.AllReportDataService;

@Service
public class AllReportDataServiceImpl extends BaseServiceImpl<BaseEntity> implements AllReportDataService {

    @Autowired
    AllReportDataDao allReportDataDao;
    @Autowired
    ReportClassifyDao reportClassifyDao;
    
    private static final String UPDATE_DATA_ERR = "修改数据信息操作失败";
    private static final String DELETE_DATA_ERR = "删除数据信息操作失败";
    private static final String INSERT_DATA_ERR = "添加数据信息操作失败";
    
    @Override
    protected BaseDao<BaseEntity> getBaseDao() {
        return this.reportClassifyDao;
    }
    
    /**
     * 查询数据池列表
     */
    @Override
    public PageInfo<AllReportData> getAllReports(Map<String, Object> params, Pagination pageInfo) {
        //处理查询参数
        this.operateParamArr(params);
        this.operateParamPublishTime(params);
        this.operateParamEnterTime(params);
        //获取用户下数据分类
        List<ReportClassify> reportClassifys = reportClassifyDao.getReportClassifys(params);
        //查询数据列表
        PageInfo<AllReportData> allReportsList = allReportDataDao.getAllReports_self(params,pageInfo);
        //替换分类名称
        this.changeClassifyName(allReportsList.getList(), reportClassifys);
        return allReportsList;
    }

    /**
     * 修改数据倾向性
     * @throws UpdateReportInfoSaveException 
     */
    @Override
    public int updateReportOrientations(Map<String, Object> params) throws UpdateReportInfoException {
        int result = 0;
        try {
            result = allReportDataDao.updateReportOrientations_self(params);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new UpdateReportInfoException(UPDATE_DATA_ERR); 
        }
        return result;
    }
    
    /**
     * 修改数据分类属性
     * @throws UpdateReportInfoSaveException 
     */
    @Override
    public int updateReportClassifyIds(Map<String, Object> params) throws UpdateReportInfoException {
        int result = 0;
        try {
            result = allReportDataDao.updateReportClassifyIds_self(params);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new UpdateReportInfoException(UPDATE_DATA_ERR); 
        }
        return result;
    }
    
    /**
     * 删除数据信息
     * @throws DeleteReportInfoSaveException 
     */
    @Override
    public int deleteReportInfo(Map<String, Object> params) throws DeleteReportInfoException {
        int result = 0;
        try {
            result = allReportDataDao.deleteReportDatas_self(params);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new DeleteReportInfoException(DELETE_DATA_ERR);
        }
        return result;
    }
    
    @Override
    public int insertReportBySelf(Map<String, Object> params) throws InsertReportInfoException {
        int result = 0;
        try {
            String infoUuid = UUIDUtil.getUuid();
            params.put("infoUuid", infoUuid);
            result = allReportDataDao.insertReportData_self(params);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new InsertReportInfoException(INSERT_DATA_ERR);
        }
        return result;
    }
    
    @Override
    public int getReportByClassify(Map<String, Object> params) {
        int result = allReportDataDao.getReportByClassify_self(params);
        return result;
    }
    
    /**
     * 处理查询数组参数 
     * @param params
     */
    private void operateParamArr(Map<String, Object> params){
        
        if(params.get("sourceTypes") != null && StringUtils.isNotBlank(params.get("sourceTypes")+"")){
            String sourceTypes = params.get("sourceTypes")+"";
            String[] sourceTypeArr = sourceTypes.split(",");
            params.put("sourceTypeArr", sourceTypeArr);
        }
        if(params.get("orientations") != null && StringUtils.isNotBlank(params.get("orientations")+"")){
            String orientations = params.get("orientations")+"";
            String[] orientationArr = orientations.split(",");
            params.put("orientationArr", orientationArr);
        }
        if(params.get("classifyIds") != null && StringUtils.isNotBlank(params.get("classifyIds")+"")){
            String classifyIds = params.get("classifyIds")+"";
            String[] classifyIdArr = classifyIds.split(",");
            params.put("classifyIdArr", classifyIdArr);
        }
    }
    /**
     * 处理发布时间参数 
     * @param params
     */
    private void operateParamPublishTime(Map<String, Object> params){
        String publishTimeType = params.get("publishTimeType")+"";
        
        //8小时
        if("8".equals(publishTimeType)){
            String endTime = DateUtil.getLongDate();
            String beginTime = DateUtil.getBeforeDayByHour(-8);
            params.put("publishBeginTime", beginTime);
            params.put("publishEndTime", endTime);
        }
        //24小时
        if("24".equals(publishTimeType)){
            String endTime = DateUtil.getLongDate();
            String beginTime = DateUtil.getBeforeDayByHour(-24);
            params.put("publishBeginTime", beginTime);
            params.put("publishEndTime", endTime);
        }
        //7天内
        if("7".equals(publishTimeType)){
            String endTime = DateUtil.getLongDate();
            String beginTime = DateUtil.getBeforeDayByHour(-168);
            params.put("publishBeginTime", beginTime);
            params.put("publishEndTime", endTime);
        }
    }
    
    /**
     * 处理入报时间参数 
     * @param params
     */
    private void operateParamEnterTime(Map<String, Object> params){
        String enterTimeType = params.get("enterTimeType")+"";
        
        //8小时
        if("8".equals(enterTimeType)){
            String endTime = DateUtil.getLongDate();
            String beginTime = DateUtil.getBeforeDayByHour(-8);
            params.put("enterBeginTime", beginTime);
            params.put("enterEndTime", endTime);
        }
        //24小时
        if("24".equals(enterTimeType)){
            String endTime = DateUtil.getLongDate();
            String beginTime = DateUtil.getBeforeDayByHour(-24);
            params.put("enterBeginTime", beginTime);
            params.put("enterEndTime", endTime);
        }
        //7天内
        if("7".equals(enterTimeType)){
            String endTime = DateUtil.getLongDate();
            String beginTime = DateUtil.getBeforeDayByHour(-168);
            params.put("enterBeginTime", beginTime);
            params.put("enterEndTime", endTime);
        }
    }
    
    private void changeClassifyName(List<AllReportData> allReportList,List<ReportClassify> reportClassifys){
        for (AllReportData report : allReportList) {
            for (ReportClassify classify : reportClassifys) {
                if(report.getClassifyId().equals(classify.getId())){
                    report.setClassifyName(classify.getClassifyName());
                    break;
                }else if(report.getClassifyId()==0){
                    report.setClassifyName("未分类");
                    break;
                }
            }
        }
        
    }

    @Override
    public AllReportData getInfoDetail(Map<String, Object> params) {
        AllReportData infoDetail = allReportDataDao.getInfoDetail_self(params);
        String content = "";
        if(infoDetail.getContentXml()!=null){
            content = infoDetail.getContentXml();
        }else{
            content = infoDetail.getContent();
        }
        content = HtmlFilter.filterContent(content);
        content = content.replace("\r\n", " ").replace("\r", " ");
        content = content.replaceAll("\\[beginimg\\](.*?)\\[endimg\\]", "");
        content = content.replace("<br />", " ").replace("<br/>", " ");
        content = content.replaceAll("beginimg(.*?)endimg", "");
        content = content.replaceAll("\0", " ");
        
        if(StringUtils.isNotBlank(infoDetail.getKeyWord())){
            content = DetailOperateUtil.makeRedForContent(infoDetail.getKeyWord(), content);
            String title  = DetailOperateUtil.makeRedForTitle(infoDetail.getKeyWord(),infoDetail.getTitle());
            infoDetail.setTitle(title);
        }
        infoDetail.setContent(content);
        return infoDetail;
    }

}
