package com.zhxg.yqzj.service.impl.v1;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhxg.yqzj.dao.v1.VersionUpgradeDao;
import com.zhxg.yqzj.service.v1.VersionUpgradeService;



@Service
public class VersionUpgradeServiceImpl implements VersionUpgradeService {
    
    private static Logger logger = LoggerFactory.getLogger(VersionUpgradeServiceImpl.class);
    
    @Autowired
    private VersionUpgradeDao versionUpgradeDao;


    @Override
    public Map<String, Object> updateAppVersion(Map<String, Object> params) {
        Map<String, Object>  jsonMap = new HashMap<String, Object>();        
        Map<String, Object> versionMap = versionUpgradeDao.updateAppVersion(params);
        if(versionMap==null || versionMap.isEmpty()){
            jsonMap.put("message", "1");
            jsonMap.put("update", "0");  
        }else{
            String version = versionMap.get("KR_EDITIONNO").toString();
            String force = versionMap.get("KR_ISUPDATE").toString().equals("0")?"1":"2";
            String message = versionMap.get("KR_MESSAGE").toString();
            String url = versionMap.get("KR_URL").toString();
            String forceVersion = versionMap.get("KR_FORCE_VER")==null?"":versionMap.get("KR_FORCE_VER").toString();  
            if(!"".equals(forceVersion) && "2".equals(force)){
                if(url.endsWith("apk")){
                    jsonMap.put("updateFileName", "zhsq.apk");
                }
                jsonMap.put("updateFileUrl", url);               
                jsonMap.put("message", "更新提示：" + "\n" + message.replace("##", "\n"));               
                jsonMap.put("version", "V"+version);
                if(String.valueOf(params.get("version")).compareTo(forceVersion)<0){//强制更新   
                    jsonMap.put("update", force); // 0: 无新版本 1: 提示更新 2: 强制更新
                    
                }else if(String.valueOf(params.get("version")).compareTo(version)>=0){//0: 无新版本
                    jsonMap.put("message", "1");
                    jsonMap.put("update", "0"); // 0: 无新版本 1: 提示更新 2: 强制更新
                }else{
                    jsonMap.put("update", "1"); // 0: 无新版本 1: 提示更新 2: 强制更新
                }                
            }else{
                if (String.valueOf(params.get("version")).compareTo(version)<0) {
                    if(url.endsWith("apk")){
                        jsonMap.put("updateFileName", "zhsq.apk");
                    }
                    jsonMap.put("updateFileUrl", url);                 
                    jsonMap.put("message", "更新提示：" + "\n" + message.replace("##", "\n"));
                    jsonMap.put("version", "V"+version);
                    jsonMap.put("update", force); // 0: 无新版本 1: 提示更新 2: 强制更新
                } else {
                    jsonMap.put("message", "1");
                    jsonMap.put("update", "0");
                }  
            }
        }        
        return jsonMap;
    }

   

}
