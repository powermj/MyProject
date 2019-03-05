package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.YqllLableDao;
import com.zhxg.yqzj.entities.v1.YqllLableInfo;

@Repository
public class YqllLableDaoImpl extends BaseDaoImpl<BaseEntity> implements YqllLableDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.YqllLableInfo.";

    @Override
    public List<YqllLableInfo> getAllLableInfo(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getAllLableInfo",params);
    }

    @Override
    public List<YqllLableInfo> getRecommentLable(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getRecommentLable",params);
    }
    
}
