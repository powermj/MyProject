package com.zhxg.yqzj.dao.v1;

import java.util.Map;

/**
 * <p>Description: 话题dao接口</p>
 * @author zyl
 * @date 2017年11月9日
 * @version 1.0
 */
public interface TopicDao {
    //添加话题
    int insertSelective(Map<String,Object> params);
    //更新话题
    int updateByPrimaryKeySelective(Map<String,Object> params);
    //删除话题
    int deleteByPrimaryKey(String ktUid);
    //个人库创建专题数据表
    int createTable_self(Map<String, Object> params);
    //保存话题关键词
    int saveTopicKeyWords(Map<String,Object> params);
    //更新话题关键词
    int updateTopicKeyWords(Map<String,Object> params);
}
