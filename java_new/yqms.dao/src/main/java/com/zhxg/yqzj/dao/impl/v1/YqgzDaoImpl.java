package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhxg.yqzj.dao.v1.YqgzDao;

/**
 * <p>
 * Description: 舆情简报dao实现类
 * </p>
 * 
 * @author zyl
 * @date 2017年11月8日
 * @version 1.0
 */
@Repository
public class YqgzDaoImpl implements YqgzDao {

    @Autowired(required = true)
    protected SqlSession sqlSessionTemplate;

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.Yqgz.";

    @Override
    public List<Map<String, Object>> selectList_self(Map<String, Object> params) {
        return sqlSessionTemplate.selectList(NAME_SPACE + "selectList", params);
    }

    @Override
    public int insertYqgzSelective_self(Map<String, Object> params) {
        return sqlSessionTemplate.insert(NAME_SPACE + "insertYqgzSelective", params);
    }

    @Override
    public int insertYqgzCntSelective_self(Map<String, Object> params) {
        return sqlSessionTemplate.insert(NAME_SPACE + "insertYqgzCntSelective", params);
    }

    @Override
    public int deletegz_self(Map<String, Object> params) {
        return sqlSessionTemplate.delete(NAME_SPACE + "deletegz", params);
    }

    @Override
    public int deletegzCnt_self(Map<String, Object> params) {
        return sqlSessionTemplate.delete(NAME_SPACE + "deletegzCnt", params);
    }

    @Override
    public int getCountByUrl_self(Map<String, Object> params) {
        return sqlSessionTemplate.selectOne(NAME_SPACE + "getCountByUrl", params);
    }
}
