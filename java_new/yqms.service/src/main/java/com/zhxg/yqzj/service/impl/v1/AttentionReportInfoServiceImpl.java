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
import com.zhxg.yqzj.dao.v1.AttentionReportInfoDao;
import com.zhxg.yqzj.entities.v1.AllReportData;
import com.zhxg.yqzj.entities.v1.AttentionReportInfo;
import com.zhxg.yqzj.service.exception.DataReport.AttentionReportInfoSaveException;
import com.zhxg.yqzj.service.v1.AttentionReportInfoService;

@Service
public class AttentionReportInfoServiceImpl extends BaseServiceImpl<BaseEntity> implements AttentionReportInfoService {

    @Autowired
    AttentionReportInfoDao attentionReportInfoDao;
    @Autowired
    AllReportDataDao allReportDataDao;
    
    private final String ATTENTION_REPORT_INSERT_ERR = "话题事件添加数据到数据池失败";
    
    @Override
    protected BaseDao<BaseEntity> getBaseDao() {
        return this.attentionReportInfoDao;
    }
    
    @Override
    public Map<String, Integer> saveAttentionToReport(Map<String, Object> params) throws AttentionReportInfoSaveException{
        //查询添加的数据信息
        //List<AttentionReportInfo> attentionInfoList = attentionReportInfoDao.getAttentionByKmuuid_self(params);
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
     * @throws AttentionReportInfoSaveException 
     */
    private Map<String, Integer> getRepeatInfo(Map<String, Object> params) throws AttentionReportInfoSaveException{
        List<AttentionReportInfo> attentionInfoList = null;
        //添加数据到数据池
        Map<String, Integer> resultMap = new HashMap<String,Integer>();
        //查询重复信息
        String[] paramIdArr = params.get("kmUuids").toString().split(",");
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
            params.put("kmUuidArr",paramIdList.toArray());
            attentionInfoList = attentionReportInfoDao.getAttentionByKmuuid_self(params);
        }
        //添加信息到数据池
        int insertNum = 0;
        if(paramIdList != null && !paramIdList.isEmpty()){
            insertNum = this.insertInfoToReport(params, attentionInfoList);
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
     * @throws AttentionReportInfoSaveException 
     */
    private int insertInfoToReport(Map<String, Object> params,List<AttentionReportInfo> attentionInfoList) throws AttentionReportInfoSaveException{
        int successResult = 0;
        try {
            for (AttentionReportInfo attentionReportInfo : attentionInfoList) {
                attentionReportInfo.setClassifyId(Integer.valueOf(params.get("classifyId").toString()));
                params.putAll(ParamsUtil.transToMAP(attentionReportInfo, AllReportData.class));
                this.setInfoContent(params, attentionReportInfo);
                int num = allReportDataDao.insertReportData_self(params);
                if (num != 0) {
                    successResult++;
                } 
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new AttentionReportInfoSaveException(ATTENTION_REPORT_INSERT_ERR);
        }
        return successResult;
    }
    
    /**
     * 设置默认值
     */
    private void setInfoContent(Map<String, Object> params,AttentionReportInfo attentionReportInfo){
        params.put("isWarning", 0);
        params.put("isAttention", 1);
        params.put("isRead", 0);
        params.put("infoSimhash", null);
        params.put("authorPic", null);
        params.put("domain", null);
        params.put("imgUrl", null);
        params.put("vedioUrl", null);
        params.put("importanceWeight", "0");
        params.put("extendField", null);
        //params.put("classifyId", 0);
        params.put("enterTime", DateUtil.getLongDate());
        params.put("isExport", 0);
    }
}
