package com.zhxg.yqzj.dao.impl.v1;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.UserAreaNewInfoDao;
import com.zhxg.yqzj.entities.v1.UserAreaNewInfo;

@Repository
public class UserAreaNewInfoDaoImpl extends BaseDaoImpl<BaseEntity> implements UserAreaNewInfoDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.UserAreaNewInfo.";

    @Override
    public UserAreaNewInfo getUserAreaNewInfo(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getUserAreaNewInfo",params);
    }
}
