package com.zhxg.yqzj.dao.impl.v1;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhxg.yqzj.dao.v1.TopicDao;

/**
 * <p>Description: </p>
 * @author zyl
 * @date 2017年11月9日
 * @version 1.0
 */
@Repository
public class TopicDaoImpl implements TopicDao {
    @Autowired(required = true)
    protected SqlSession sqlSessionTemplate;
    
    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.Topic.";
    @Override
    public int insertSelective(Map<String, Object> params) {
        return sqlSessionTemplate.insert(NAME_SPACE + "insertSelective", params);
    }

    @Override
    public int updateByPrimaryKeySelective(Map<String, Object> params) {
        return sqlSessionTemplate.update(NAME_SPACE + "updateByPrimaryKeySelective", params);
    }

    @Override
    public int deleteByPrimaryKey(String ktUid) {
        return sqlSessionTemplate.delete(NAME_SPACE + "updateByPrimaryKeySelective", ktUid);
    }

    @Override
    public int createTable_self(Map<String, Object> params) {
        return sqlSessionTemplate.update(NAME_SPACE + "createTable",params);
    }

    @Override
    public int saveTopicKeyWords(Map<String, Object> params) {
        return sqlSessionTemplate.insert(NAME_SPACE + "saveTopicKeyWords",params);
    }

    @Override
    public int updateTopicKeyWords(Map<String, Object> params) {
        return sqlSessionTemplate.insert(NAME_SPACE + "updateTopicKeyWords",params);
    }

}
