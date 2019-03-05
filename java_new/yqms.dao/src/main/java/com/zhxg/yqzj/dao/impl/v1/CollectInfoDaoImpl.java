package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.yqzj.dao.v1.CollectInfoDao;
import com.zhxg.yqzj.entities.v1.CollectInfo;

@Repository
public class CollectInfoDaoImpl extends BaseDaoImpl<CollectInfo> implements CollectInfoDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.CollectInfo.";

    @Override
    public List<CollectInfo> selectList(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "selectList", params);
    }

    @Override
    public int insertCollectInfo(Map<String, Object> params) {
        return this.sqlSessionTemplate.insert(NAME_SPACE + "insertCollectInfo", params);
    }

    @Override
    public int updateCollectInfo(Map<String, Object> params) {
        return this.sqlSessionTemplate.insert(NAME_SPACE + "updateCollectInfo", params);
    }

    @Override
    public int deleteCollectInfo(Map<String, Object> params) {
        return this.sqlSessionTemplate.delete(NAME_SPACE + "deleteCollectInfo", params);
    }

    @Override
    public int getCountByName(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getCountByName", params);
    }

    @Override
    public int getCountById(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getCountById", params);
    }

    @Override
    public List<CollectInfo> getAllCollectInfo(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getAllCollect", params);
    }

}
