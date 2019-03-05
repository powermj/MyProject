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
import com.zhxg.framework.base.utils.SourceTypeUtil;
import com.zhxg.yqzj.dao.v1.EventAnalysisDao;
import com.zhxg.yqzj.dao.v1.LocationInfoDao;
import com.zhxg.yqzj.dao.v1.SourceTypeDao;
import com.zhxg.yqzj.dao.v1.TopicEventRegionDao;
import com.zhxg.yqzj.entities.v1.EventAnalysis;
import com.zhxg.yqzj.entities.v1.LocationInfo;
import com.zhxg.yqzj.entities.v1.SourceType;
import com.zhxg.yqzj.entities.v1.TopicEventRegion;
import com.zhxg.yqzj.service.util.ExcelExportUtil;
import com.zhxg.yqzj.service.v1.ActiveMediaService;

@Service
public class ActiveMediaServiceImpl extends BaseServiceImpl<BaseEntity> implements ActiveMediaService {

    @Autowired
    SourceTypeDao sourceTypeDao;
    @Autowired
    TopicEventRegionDao topicEventRegionDao;
    @Autowired
    LocationInfoDao locationInfoDao;
    @Autowired
    EventAnalysisDao eventAnalysisDao;
    
    @Override
    protected BaseDao<BaseEntity> getBaseDao() {
        return this.sourceTypeDao;
    }

    @Override
    public List<SourceType> getAllSourceType(Map<String, Object> params) {
        //获取所有媒体类型
        List<SourceType> sourceTypeList = sourceTypeDao.getAllSourceType(params);
        //获取事件时间段
        this.getTimeForTopic(params);
        //获取当前事件关联媒体类型数量
        List<TopicEventRegion> mediaSourceTypeNumList = topicEventRegionDao.getMediaSourceTypeNum_other(params);
        this.setSourceTypeNum(sourceTypeList,mediaSourceTypeNumList);
        return sourceTypeList;
    }
    
    @Override
    public PageInfo<TopicEventRegion> getAllMediaInfo(Map<String,Object> params,Pagination pageInfo){
        //获取事件时间段
        this.getTimeForTopic(params);
        //获取当前时间所有关联文章信息
        PageInfo<TopicEventRegion> allMediaInfoList = topicEventRegionDao.getAllMediaInfo_other(params, pageInfo);
        //对文章媒体类型及地域信息进行中文转换
        this.setSerailNo(allMediaInfoList, pageInfo);
        return allMediaInfoList;
    }
    
    @Override
    public String exportAllMediaInfo(Map<String, Object> params){
        //获取事件时间段
        this.getTimeForTopic(params);
        //获取所有活跃媒体导出信息
        List<TopicEventRegion> exportMediaInfoList = topicEventRegionDao.getAllMediaInfo_other(params);
        //准备导出信息
        this.prepareExportInfo(exportMediaInfoList);
        //导出文件
        String path = ExcelExportUtil.exportExcelForActiveMedia(exportMediaInfoList,params);
        return path;
    }
    
    /**
     * 将各媒体类型数量置入集合
     * @param sourceType
     */
    public void setSourceTypeNum(List<SourceType> sourceTypeList,List<TopicEventRegion> mediaSourceTypeNumList){
        for (TopicEventRegion topicEventRegion : mediaSourceTypeNumList) {
            String knType = topicEventRegion.getKnType();
            Integer typeNum = topicEventRegion.getTypeNum();
            for (SourceType SourceType : sourceTypeList) {
                String sourceCode = SourceType.getSourceCode();
                if(sourceCode.contains(knType)){
                    SourceType.setTotalNum(typeNum);
                }
            }
            
        }
    }
    /**
     * 信息列表序号 +媒体类型代码，地域代码转换为中文信息
     * @param allMediaInfoList
     * @param pageInfo
     */
    public void setSerailNo(PageInfo<TopicEventRegion> allMediaInfoList,Pagination pageInfo){
        Integer pageNum = pageInfo.getPageNum() == 0?0:pageInfo.getPageNum()-1;
        Integer pageSize = pageInfo.getPageSize();
        Integer beginNo = pageNum * pageSize;
        List<TopicEventRegion> pageMediaInfoList = allMediaInfoList.getList();
        for (TopicEventRegion topicEventRegion : pageMediaInfoList) {
            ++beginNo;
            topicEventRegion.setSerialNo(beginNo);
            String knType = topicEventRegion.getKnType();
            String sourceName = SourceTypeUtil.getSourceName(knType);
            topicEventRegion.setKnTypeName(sourceName);
            String regionId = topicEventRegion.getRegionId();
            String regionName = "- -";
            if(StringUtils.isNotBlank(regionId)){
                regionName = this.setRegionName(Integer.valueOf(regionId),"");
            }
            topicEventRegion.setRegionName(regionName);
            
        }
    }
    /**
     * 拼接地域名称 
     * @param regionId
     */
    public String setRegionName(Integer regionId,String name){
        LocationInfo locationInfo = locationInfoDao.getLocationInfo(regionId);
        if(locationInfo == null){
            return "- -";
        }
        String regionName = locationInfo.getName();;
        String paramName = name;
        Integer parentId = locationInfo.getParentId();
        if(parentId == 1 || parentId == 0){
            return regionName+paramName;
        }else{
            paramName =  "-"+regionName ;
            regionName = this.setRegionName(parentId, paramName);
        }
        return regionName;
    }
    
    /**
     * 准备导出信息
     * @param exportMediaInfoList
     * @return
     */
    public void prepareExportInfo(List<TopicEventRegion> exportMediaInfoList){
        for (TopicEventRegion topicEventRegion : exportMediaInfoList) {
            String knType = topicEventRegion.getKnType();
            String sourceName = SourceTypeUtil.getSourceName(knType);
            topicEventRegion.setKnTypeName(sourceName);
            String regionId = topicEventRegion.getRegionId();
            String regionName = "未知";
            if(StringUtils.isNotBlank(regionId)){
                regionName = this.setRegionName(Integer.valueOf(regionId),"");
            }
            topicEventRegion.setRegionName(regionName);
        }
    }
    
    /**
     * 获取事件的开始时间与结束时间
     * @param params
     * @return
     */
    private Map<String,Object> getTimeForTopic(Map<String,Object> params){
        String eventAnalysisId = String.valueOf(params.get("eventId"));
        //获取事件时间段
        List<EventAnalysis> eventAnalysis = eventAnalysisDao.selectEventAnalysisInfo(eventAnalysisId);
        EventAnalysis info = eventAnalysis.get(0);
        String topicBtime = info.getBeginTime();
        String toicEtime = info.getCloseTime();
        
        if(params.get("beginTime") == null){
            params.put("beginTime", topicBtime);
        }
        if(params.get("endTime") == null){
            params.put("endTime", toicEtime);
        }
        
        return params;
    }
}
