package com.zhxg.yqzj.service.impl.v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.framework.base.utils.DateStyle;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.framework.base.utils.ParamsUtil;
import com.zhxg.yqzj.dao.v1.AllReportDataDao;
import com.zhxg.yqzj.dao.v1.NetWorkReportInfoDao;
import com.zhxg.yqzj.dao.v1.ValidationRefDao;
import com.zhxg.yqzj.entities.v1.AllReportData;
import com.zhxg.yqzj.entities.v1.NetWorkReportInfo;
import com.zhxg.yqzj.entities.v1.ValidationRef;
import com.zhxg.yqzj.service.exception.DataReport.NetWorkReportInfoSaveException;
import com.zhxg.yqzj.service.v1.NetWorkReportInfoService;

@Service
public class NetWorkReportInfoServiceImpl extends BaseServiceImpl<BaseEntity> implements NetWorkReportInfoService {

    @Autowired
    NetWorkReportInfoDao netWorkReportInfoDao;
    @Autowired
    AllReportDataDao allReportDataDao;
    @Autowired
    ValidationRefDao validationRefDao;
    
    private final String NETWORK_REPORT_INSERT_ERR = "网络情报添加数据到数据池失败";
    
    @Override
    protected BaseDao<BaseEntity> getBaseDao() {
        return this.netWorkReportInfoDao;
    }
    
    @Override
    public Map<String, Integer> saveNetWorkToReport(Map<String, Object> params) throws NetWorkReportInfoSaveException{
        //查询添加的数据信息
        //List<NetWorkReportInfo> netWorkList = netWorkReportInfoDao.getNetWorkByKruuid_other(params);
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
     * @throws NetWorkReportInfoSaveException 
     */
    private Map<String, Integer> getRepeatInfo(Map<String, Object> params) throws NetWorkReportInfoSaveException{
        List<NetWorkReportInfo> netWorkList = null;
        //添加数据到数据池
        Map<String, Integer> resultMap = new HashMap<String,Integer>();
        //查询重复信息
        String[] paramIdArr = params.get("krUuids").toString().split(",");
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
            for (String repeatUuid : repeatUuidList) {
                paramIdList.remove(repeatUuid);
            }
        }
        if(paramIdList != null && !paramIdList.isEmpty()){
            params.put("krUuidArr",paramIdList.toArray());
            netWorkList = netWorkReportInfoDao.getNetWorkByKruuid_other(params);
        }
        //添加信息到数据池
        int insertNum = 0;
        if(paramIdList != null && !paramIdList.isEmpty()){
            insertNum = this.insertInfoToReport(params, netWorkList);
        }
        resultMap.put("successResult", insertNum);
        resultMap.put("faildResult", repeatUuidList.size());
        return resultMap;
    }
    
    /**
     * 获取信息内容，设置默认值
     */
    private void getInfoContent(Map<String, Object> params,NetWorkReportInfo netWorkReportInfo){
        String kvUuid = netWorkReportInfo.getKvUuid();
        String krCtime = netWorkReportInfo.getPublishTime();
        String nowTime = DateUtil.getLongDate();
        int intervalDays = DateUtil.getIntervalDays(krCtime, nowTime);
        params.put("kvUuid", kvUuid);
        String dbTableTime = null;
        if(StringUtils.isNotBlank(krCtime)){
            String tableTime = DateUtil.StringToString(netWorkReportInfo.getPublishTime(), null,DateStyle.YYYYMMDDHHMMSS);
            dbTableTime = tableTime.substring(0,8);
        }
        params.put("DBTABLETIME",dbTableTime);
        String infoContent = "";
        if(intervalDays <= 7){
            //查询info库获取详细内容
            ValidationRef content = validationRefDao.getShareInfoContent_info(params);
            params.put("content", content.getKvContent());
            params.put("contentXml", content.getKvContentXml());
        }else{
            //查询self库获取详细内容
            infoContent = validationRefDao.getDetailShareInfoCnt_other(params);
            params.put("content", infoContent);
        }
        //入库时间
        params.put("enterTime", DateUtil.getLongDate());
        //是否导出过简报
        params.put("isExport", 0);
    }
    
    private int insertInfoToReport(Map<String, Object> params,List<NetWorkReportInfo> netWorkList ) throws NetWorkReportInfoSaveException{
        int successResult = 0;
        try {
            for (NetWorkReportInfo netWorkReportInfo : netWorkList) {
                netWorkReportInfo.setClassifyId(Integer.valueOf(params.get("classifyId").toString()));
                params.putAll(ParamsUtil.transToMAP(netWorkReportInfo, AllReportData.class));
                //获取信息content
                this.getInfoContent(params, netWorkReportInfo);
                int num = allReportDataDao.insertReportData_self(params);
                if (num != 0) {
                    successResult++;
                } 
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new NetWorkReportInfoSaveException(NETWORK_REPORT_INSERT_ERR);
        }
        return successResult;
    }
}
