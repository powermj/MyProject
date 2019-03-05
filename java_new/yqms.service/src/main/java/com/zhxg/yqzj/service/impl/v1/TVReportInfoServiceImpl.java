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
import com.zhxg.yqzj.dao.v1.TVReportInfoDao;
import com.zhxg.yqzj.entities.v1.AllReportData;
import com.zhxg.yqzj.entities.v1.TVReportInfo;
import com.zhxg.yqzj.service.exception.DataReport.TVReportInfoSaveException;
import com.zhxg.yqzj.service.exception.solr.WarningException;
import com.zhxg.yqzj.service.v1.TVReportInfoService;

@Service
public class TVReportInfoServiceImpl extends BaseServiceImpl<BaseEntity> implements TVReportInfoService {

    @Autowired
    TVReportInfoDao tvReportInfoDao;
    @Autowired
    AllReportDataDao allReportDataDao;
    
    private final String TV_REPORT_INSERT_ERR = "电视监测添加数据到数据池失败";
    
    @Override
    protected BaseDao<BaseEntity> getBaseDao() {
        return this.tvReportInfoDao;
    }
    
    @Override
    public Map<String, Integer> saveTVToReport(Map<String, Object> params) throws TVReportInfoSaveException{
        //查询添加的数据信息
        //List<TVReportInfo> TVinfoList = tvReportInfoDao.getTVByKsuuid_self(params);
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
     * @throws TVReportInfoSaveException 
     * @throws WarningException 
     */
    private Map<String, Integer> getRepeatInfo(Map<String, Object> params) throws TVReportInfoSaveException{
        List<TVReportInfo> TVinfoList = null;
        //添加数据到数据池
        Map<String, Integer> resultMap = new HashMap<String,Integer>();
        //查询重复信息
        String[] paramIdArr = params.get("ksUuids").toString().split(",");
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
            params.put("ksUuidArr",paramIdList.toArray());
            TVinfoList = tvReportInfoDao.getTVByKsuuid_self(params);
        }
        //添加信息到数据池
        int insertNum = 0;
        if(paramIdList != null && !paramIdList.isEmpty()){
            insertNum = this.insertInfoToReport(params, TVinfoList);
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
     * @throws WarningException
     * @throws TVReportInfoSaveException 
     */
    private int insertInfoToReport(Map<String, Object> params,List<TVReportInfo> TVinfoList) throws  TVReportInfoSaveException{
        int successResult = 0;
        try {
            for (TVReportInfo tvReportInfo : TVinfoList) {
                tvReportInfo.setClassifyId(Integer.valueOf(params.get("classifyId").toString()));
                params.putAll(ParamsUtil.transToMAP(tvReportInfo, AllReportData.class));
                this.setInfoContent(params, tvReportInfo);
                int num = allReportDataDao.insertReportData_self(params);
                if (num != 0) {
                    successResult++;
                } 
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new TVReportInfoSaveException(TV_REPORT_INSERT_ERR);
        }
        return successResult;
    }
    
    /**
     * 设置默认值
     */
    private void setInfoContent(Map<String, Object> params,TVReportInfo tvReportInfo){
        
        params.put("isWarning", 0);
        params.put("isAttention", 0);
        params.put("visitCount", 0);
        params.put("replyCount", 0);
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
