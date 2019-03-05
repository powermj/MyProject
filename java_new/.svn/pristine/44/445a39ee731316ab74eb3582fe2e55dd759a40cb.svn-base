package com.zhxg.yqzj.service.impl.v1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.yqzj.dao.v1.LogDao;
import com.zhxg.yqzj.entities.v1.Log;
import com.zhxg.yqzj.service.v1.LogService;

@Service
public class LogServiceImpl extends BaseServiceImpl<BaseEntity> implements LogService {
    
    @Autowired
    LogDao logDao;
    
    @Override
    protected BaseDao<BaseEntity> getBaseDao() {
        return this.logDao;
    } 
    
    @Override
    public void saveCopyLink(Map<String, Object> paramMap) {
        // List<Map<String, Object>> logtest = this.logDao.gettest_log();
        List<Log> list = null;
        if ("1".equals(paramMap.get("infoType") + "")) {
            list = this.logDao.getYqllListByKruuid_other(paramMap);
            for (Log log : list) {
                String kvUuid = log.getKvUuid();
                Date publishTime = log.getPublishTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String tableTime = sdf.format(publishTime);
                paramMap.put("dbTableTime", tableTime);
                paramMap.put("kvUuid", kvUuid);
                String detailInfoCnt = null;
                // 查询info库获取详细内容
                try {
                    detailInfoCnt = this.logDao.getDetailInfoCnt_info(paramMap);
                } catch (Exception e) {
                    detailInfoCnt = null;
                }
                // 查询self库获取详细内容
                if (detailInfoCnt == null || detailInfoCnt.length() <= 0) {
                    detailInfoCnt = this.logDao.getDetailInfoCnt_other(paramMap);
                }
                log.setContent(detailInfoCnt);
                log.setType(1);
                
            }
        } else if ("2".equals(paramMap.get("infoType") + "")) {
            list = this.logDao.getWarningListByKsuuid_self(paramMap);
            list.get(0).setType(2);
        }
        // 加日志
        for (Log log : list) {
            log.setOperationUserId(
                    paramMap.get("operationUserId") == null || "".equals(paramMap.get("operationUserId") + "") ? 0
                    : Integer.parseInt(paramMap.get("operationUserId") + ""));
            log.setOperationUserIp(paramMap.get("operationUserIp") + "");
            log.setOperationUserName(paramMap.get("operationUserName") + "");
            log.setFunctionType(Integer.parseInt(paramMap.get("functionType") + ""));
            this.logDao.insertLog_log(log);
        }

    }
    
}
