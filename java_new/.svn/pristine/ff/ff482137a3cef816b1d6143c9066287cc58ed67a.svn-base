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
import com.zhxg.yqzj.dao.v1.OverseasDao;
import com.zhxg.yqzj.entities.v1.OverseasWebsite;
import com.zhxg.yqzj.service.v1.OverseasService;

@Service
public class OverseasServiceImpl extends BaseServiceImpl<BaseEntity> implements OverseasService {

    @Autowired
    OverseasDao OverseasDao;
    
    @Override
    protected BaseDao<BaseEntity> getBaseDao() {
        return this.OverseasDao;
    }
    
    @Override
    public Map<String, List<Map<String, String>>> getOverseasWebsite(){
        Map<String,List<Map<String,String>>> map = new HashMap<>();
        List<OverseasWebsite> list = this.OverseasDao.getOverseasWebsite();
        for(OverseasWebsite site:list){
            List<Map<String,String>> siteList ;
            if(!map.containsKey(site.getClassName())){
                siteList = new ArrayList<>();
                map.put(site.getClassName(), siteList);
            }else{
                siteList = map.get(site.getClassName());
            }
            Map<String,String> siteMap = new HashMap<>();
            siteMap.put("webName", site.getWebName());
            siteMap.put("url", site.getUrl());
            siteList.add(siteMap);
        }
        return map;
    }

    
}
