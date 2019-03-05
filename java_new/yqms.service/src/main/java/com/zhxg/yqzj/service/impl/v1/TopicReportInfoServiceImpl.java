package com.zhxg.yqzj.service.impl.v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.framework.base.utils.ParamsUtil;
import com.zhxg.yqzj.dao.v1.AllReportDataDao;
import com.zhxg.yqzj.dao.v1.TopicReportInfoDao;
import com.zhxg.yqzj.entities.v1.AllReportData;
import com.zhxg.yqzj.entities.v1.TopicReportInfo;
import com.zhxg.yqzj.service.exception.DataReport.TopicReportInfoSaveException;
import com.zhxg.yqzj.service.v1.TopicReportInfoService;

@Service
public class TopicReportInfoServiceImpl extends BaseServiceImpl<BaseEntity> implements TopicReportInfoService {

    @Autowired
    TopicReportInfoDao toicReportInfoDao;
    @Autowired
    AllReportDataDao allReportDataDao;
    
    private final String TOPIC_REPORT_INSERT_ERR = "话题事件添加数据到数据池失败";
    
    @Override
    protected BaseDao<BaseEntity> getBaseDao() {
        return this.toicReportInfoDao;
    }
    
    @Override
    public Map<String, Integer> saveTopicToReport(Map<String, Object> params) throws TopicReportInfoSaveException{
        //查询添加的数据信息
        //List<TopicReportInfo> topicInfoList = toicReportInfoDao.getTopicByUuid_self(params);
        //去除重复信息并添加信息到数据池
        Map<String, Integer> resultMap = this.getRepeatInfo(params);
        return resultMap;
    }
    
    /**
     * 去除重复信息 
     *
     * @param params
     * @param resultMap
     * @return
     * @throws TopicReportInfoSaveException 
     */
    private Map<String, Integer> getRepeatInfo(Map<String, Object> params) throws TopicReportInfoSaveException{
        List<TopicReportInfo> topicInfoList = null;
        //添加数据到数据池
        Map<String, Integer> resultMap = new HashMap<String,Integer>();
        //查询重复信息
        String[] paramIdArr = params.get("uuids").toString().split(",");
        params.put("infoUuidArr", paramIdArr);
        List<String> repeatUuidList = allReportDataDao.getRepeatUuid_self(params);
        List<String> paramIdList = new ArrayList<>();
        // 添加到集合
        if (paramIdArr != null && paramIdArr.length > 0) {
            for (String str : paramIdArr) {
                paramIdList.add(str);
            }
        }
        //去掉重复id
        if (repeatUuidList != null && !repeatUuidList.isEmpty()) {
            for (String kruuid : repeatUuidList) {
                paramIdList.remove(kruuid);
            }
        }
        if(paramIdList != null && !paramIdList.isEmpty()){
            params.put("uuidArr",paramIdList.toArray());
            topicInfoList = toicReportInfoDao.getTopicByUuid_other(params);
        }
        //添加信息到数据池
        int insertNum = 0;
        if(paramIdList != null && !paramIdList.isEmpty()){
            insertNum = this.insertInfoToReport(params, topicInfoList);
        }
        resultMap.put("successResult", insertNum);
        resultMap.put("faildResult", repeatUuidList.size());
        return resultMap;
    }
    
    /**
     * 添加预警到数据池
     *
     * @param params
     * @param warningInfoList
     * @return
     * @throws TopicReportInfoSaveException 
     */
    private int insertInfoToReport(Map<String, Object> params,List<TopicReportInfo> topicInfoList) throws TopicReportInfoSaveException{
        int successResult = 0;
        try {
            for (TopicReportInfo topicReportInfo : topicInfoList) {
                topicReportInfo.setClassifyId(Integer.valueOf(params.get("classifyId").toString()));
                params.putAll(ParamsUtil.transToMAP(topicReportInfo, AllReportData.class));
                this.setInfoContent(params, topicReportInfo);
                int num = allReportDataDao.insertReportData_self(params);
                if (num != 0) {
                    successResult++;
                } 
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new TopicReportInfoSaveException(TOPIC_REPORT_INSERT_ERR);
        }
        return successResult;
    }
    
    /**
     * 设置默认值
     */
    private void setInfoContent(Map<String, Object> params,TopicReportInfo topicReportInfo){
        params.put("authorPic", null);
        params.put("imgUrl", null);
        params.put("vedioUrl", null);
        //params.put("classifyId", 0);
        params.put("enterTime", DateUtil.getLongDate());
        params.put("isExport", 0);
    }
}
