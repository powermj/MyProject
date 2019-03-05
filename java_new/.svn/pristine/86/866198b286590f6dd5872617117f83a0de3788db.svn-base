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
import com.zhxg.yqzj.dao.v1.RegionReportInfoDao;
import com.zhxg.yqzj.dao.v1.ValidationRefDao;
import com.zhxg.yqzj.entities.v1.AllReportData;
import com.zhxg.yqzj.entities.v1.RegionReportInfo;
import com.zhxg.yqzj.service.exception.DataReport.RegionReportInfoSaveException;
import com.zhxg.yqzj.service.v1.RegionReportInfoService;

@Service
public class RegionReportInfoServiceImpl extends BaseServiceImpl<BaseEntity> implements RegionReportInfoService {

    @Autowired
    RegionReportInfoDao regionReportInfoDao;
    @Autowired
    AllReportDataDao allReportDataDao;
    @Autowired
    ValidationRefDao validationRefDao;
    
    private final String REGION_REPORT_INSERT_ERR = "地域舆情添加数据到数据池失败";
    
    @Override
    protected BaseDao<BaseEntity> getBaseDao() {
        return this.regionReportInfoDao;
    }
    
    @Override
    public Map<String, Integer> saveRegionToReport(Map<String, Object> params) throws RegionReportInfoSaveException{
        //查询添加的数据信息
        //List<RegionReportInfo> regionReportList = regionReportInfoDao.getRegionByKruuid_region(params);
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
     * @throws RegionReportInfoSaveException 
     */
    private Map<String, Integer> getRepeatInfo(Map<String, Object> params) throws RegionReportInfoSaveException{
        List<RegionReportInfo> regionReportList = null;
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
            regionReportList = regionReportInfoDao.getRegionByKruuid_region(params);
        }
        //添加信息到数据池
        int insertNum = 0;
        if(paramIdList != null && !paramIdList.isEmpty()){
            insertNum = this.insertInfoToReport(params, regionReportList);
        }
        resultMap.put("successResult", insertNum);
        resultMap.put("faildResult", repeatUuidList.size());
        return resultMap;
    }
    
    /**
     * 获取信息内容，设置默认值
     */
    private void getInfoContent(Map<String, Object> params,RegionReportInfo regiontReportInfo){
        String kvUuid = regiontReportInfo.getKvUuid();
        params.put("kvUuid", kvUuid);
        //查询info库获取详细内容 地域不存在个人
        String infoContent = validationRefDao.getDetailRegionInfoCnt_info(params);
        //信息内容
        params.put("content", infoContent);
        params.put("domain", null);
        //信息分类
        //params.put("classifyId", 0);
        //入库时间
        params.put("enterTime", DateUtil.getLongDate());
        //是否导出过简报
        params.put("isExport", 0);
    }
    
    private int insertInfoToReport(Map<String, Object> params,List<RegionReportInfo> regionReportList) throws RegionReportInfoSaveException{
        int successResult = 0;
        try {
            for (RegionReportInfo regionReportInfo : regionReportList) {
                regionReportInfo.setClassifyId(Integer.valueOf(params.get("classifyId").toString()));
                params.putAll(ParamsUtil.transToMAP(regionReportInfo, AllReportData.class));
                //获取信息content
                this.getInfoContent(params, regionReportInfo);
                int num = allReportDataDao.insertReportData_self(params);
                if (num != 0) {
                    successResult++;
                } 
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new RegionReportInfoSaveException(REGION_REPORT_INSERT_ERR);
        }
        return successResult;
    }
}
