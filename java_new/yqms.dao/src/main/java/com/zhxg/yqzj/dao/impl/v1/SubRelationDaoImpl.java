package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.SubRelationDao;
import com.zhxg.yqzj.entities.v1.SubRelation;

@Repository
public class SubRelationDaoImpl extends BaseDaoImpl<BaseEntity> implements SubRelationDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.SubRelation.";

    @Override
    public List<SubRelation> getDetailRelationTopic(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getDetailRelationTopic",paramMap);
    }

    @Override
    public SubRelation getDetailParentTopic(String ksId) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getDetailParentTopic",ksId);
    }

    @Override
    public List<SubRelation> getUserTopicList(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getUserTopicList", paramMap);
    }
}
