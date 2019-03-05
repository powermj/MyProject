package com.zhxg.yqzj.service.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.yqzj.dao.v1.UserBaseInfoDao;
import com.zhxg.yqzj.dao.v1.YqllLableDao;
import com.zhxg.yqzj.entities.v1.YqllLableInfo;
import com.zhxg.yqzj.service.v1.YqllLableService;

@Service
public class YqllLableServiceImpl extends BaseServiceImpl<BaseEntity> implements YqllLableService {

    @Autowired
    YqllLableDao yqllLabledao;
    @Autowired
    UserBaseInfoDao userBaseInfoDao;
    @Override
    protected BaseDao<BaseEntity> getBaseDao() {
        return yqllLabledao;
    }

   /**
    * 获取所有行业标签
    */
    @Override
    public List<YqllLableInfo> getAllLableInfo(Map<String, Object> params) {
        //获取当前用户所属行业
        String userIndustry = userBaseInfoDao.getMobileCloudDepartmentId(params);
        params.put("kcId", userIndustry);
        String lableIndustryId = userBaseInfoDao.getLableIndustryId(params);
        params.put("lableIndustry", lableIndustryId);
        //获取行业下所属所有标签
        List<YqllLableInfo> allLableInfo = yqllLabledao.getAllLableInfo(params);
        return allLableInfo;
    }

    @Override
    public List<YqllLableInfo> getRecommentLable(Map<String, Object> params) {
        //获取当前用户所属行业
        //String userIndustry = userBaseInfoDao.getMobileCloudDepartmentId(params);
        //params.put("kcId", userIndustry);
        //String lableIndustryId = userBaseInfoDao.getLableIndustryId(params);
        //params.put("lableIndustry", lableIndustryId);
        List<YqllLableInfo> recommentLable = yqllLabledao.getRecommentLable(params);
        return recommentLable;
    }
    

}
