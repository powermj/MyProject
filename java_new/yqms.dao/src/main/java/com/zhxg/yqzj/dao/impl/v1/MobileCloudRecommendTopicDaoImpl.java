package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.MobileCloudRecommendTopicDao;
import com.zhxg.yqzj.entities.v1.MobileCloudRecommendTopic;



@Repository
public class MobileCloudRecommendTopicDaoImpl extends BaseDaoImpl<BaseEntity> implements MobileCloudRecommendTopicDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.MobileCloudRecommendTopic.";

    
    @Override
    public List<MobileCloudRecommendTopic> getAllRecommendTopic(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getAllRecommendTopic",params);
    }

}
