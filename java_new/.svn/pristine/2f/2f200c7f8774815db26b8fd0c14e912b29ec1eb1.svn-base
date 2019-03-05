package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.OverseasDao;
import com.zhxg.yqzj.entities.v1.OverseasWebsite;

@Repository
public class OverseasDaoImpl extends BaseDaoImpl<BaseEntity> implements OverseasDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.OverseasWebsite.";

    @Override
    public List<OverseasWebsite> getOverseasWebsite() {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getOverseasWebsite");
    }


}
