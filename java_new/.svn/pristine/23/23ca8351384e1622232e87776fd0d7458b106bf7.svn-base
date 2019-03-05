package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.yqzj.dao.v1.CollectInfoCntDao;
import com.zhxg.yqzj.entities.v1.CollectInfoCnt;

@Repository
public class CollectInfoCntDaoImpl extends BaseDaoImpl<CollectInfoCnt> implements CollectInfoCntDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.CollectInfoCnt.";

    @Override
    public List<CollectInfoCnt> selectList(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "selectList", params);
    }

    @Override
    public int insertCollectInfoCnt(Map<String, Object> params) {
        return this.sqlSessionTemplate.insert(NAME_SPACE + "insertCollectInfoCnt", params);
    }

    @Override
    public int updateCollectInfoCnt(Map<String, Object> params) {
        return this.sqlSessionTemplate.insert(NAME_SPACE + "updateCollectInfoCnt", params);
    }

    @Override
    public int deleteCollectInfoCnt(Map<String, Object> params) {
        return this.sqlSessionTemplate.delete(NAME_SPACE + "deleteCollectInfoCnt", params);
    }

    @Override
    public int getCountByName(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getCountByName", params);
    }

    @Override
    public int deleteCollectInfoCntAll(Map<String, Object> params) {
        return this.sqlSessionTemplate.delete(NAME_SPACE + "deleteCollectInfoCntAll", params);
    }

}
