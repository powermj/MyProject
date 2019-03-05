package com.zhxg.yqzj.service.impl.v1;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.framework.base.utils.UUIDUtils;
import com.zhxg.yqzj.dao.v1.ChannelTVDao;
import com.zhxg.yqzj.entities.v1.ChannelTV;
import com.zhxg.yqzj.service.v1.ChannelTVService;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.service
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户模块服务层接口实现
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.service.impl.v1.UserServiceImpl.java
 * <p>
 * Author: azmiu
 * <p>
 * Create Date: 2017年2月28日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
@Service
public class ChannelTVServiceImpl extends BaseServiceImpl<ChannelTV> implements ChannelTVService {
    
    @Autowired
    private ChannelTVDao channelTVDao;
    
    @Override
    protected BaseDao<ChannelTV> getBaseDao() {
        return this.channelTVDao;
    }

    @Override
    public int uploadTVMonitoringInformation(Map<String, Object> paramMap) {
    
        paramMap.put("kruuid", UUIDUtils.create());
        paramMap.put("kvuuid", UUIDUtils.create());
        paramMap.put("simhash", paramMap.get("title").hashCode());
        paramMap.put("webname", "电视节目-"+paramMap.get("channel"));
        paramMap.put("arg2",paramMap.get("column"));
        paramMap.put("author",paramMap.get("column")==null||"".equals(paramMap.get("column"))?paramMap.get("channel"):paramMap.get("column"));
        paramMap.put("webChannel", paramMap.get("channel"));
        paramMap.put("type", "2");
        String keyword="";
        if(paramMap.get("keyword")!=null&&!"".equals(paramMap.get("keyword"))){
            String[] keywords=paramMap.get("keyword").toString().replace(" ", ",").split(",");
            for(String word:keywords){
                if(!"".equals(word)){
                    keyword+=word+",";
                }
            }
        }
        if(keyword.endsWith(",")){
            keyword=keyword.substring(0, keyword.length()-1);
        }
        paramMap.put("keyword", keyword);
        String ctime=(String) paramMap.get("cTime");       
        paramMap.put("losttime", ctime.substring(0, 8));
        paramMap.put("cTime", ctime);
        return this.channelTVDao.uploadTVMonitoringInformation_self(paramMap);
    }

    @Override
    public int deleteTVMonitoringInformation(Map<String, Object> paramMap) {
        return this.channelTVDao.deleteTVMonitoringInformation_self(paramMap);
    }
}
