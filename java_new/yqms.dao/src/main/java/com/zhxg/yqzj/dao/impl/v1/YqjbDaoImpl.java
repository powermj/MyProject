package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhxg.yqzj.dao.v1.YqjbDao;

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
public class YqjbDaoImpl implements YqjbDao {

    @Autowired(required = true)
    protected SqlSession sqlSessionTemplate;

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.Yqjb.";

    @Override
    public List<Map<String, Object>> selectList_self(Map<String, Object> params) {
        return sqlSessionTemplate.selectList(NAME_SPACE + "selectList", params);
    }

    @Override
    public int insertYqjbSelective_self(Map<String, Object> params) {
        return sqlSessionTemplate.insert(NAME_SPACE + "insertYqjbSelective", params);
    }

    @Override
    public int insertYqjbCntSelective_self(Map<String, Object> params) {
        return sqlSessionTemplate.insert(NAME_SPACE + "insertYqjbCntSelective", params);
    }

    @Override
    public int deleteYqjb_self(Map<String, Object> params) {
        return sqlSessionTemplate.delete(NAME_SPACE + "deleteYqjb", params);
    }

    @Override
    public int deleteYqjbCnt_self(Map<String, Object> params) {
        return sqlSessionTemplate.delete(NAME_SPACE + "deleteYqjbCnt", params);
    }

    @Override
    public int deletejb_self(Map<String, Object> params) {
        return sqlSessionTemplate.delete(NAME_SPACE + "deletejb", params);
    }

    @Override
    public int deletejbCnt_self(Map<String, Object> params) {
        return sqlSessionTemplate.delete(NAME_SPACE + "deletejbCnt", params);
    }

    @Override
    public int getCountByUrl_self(Map<String, Object> params) {
        return sqlSessionTemplate.selectOne(NAME_SPACE + "getCountByUrl", params);
    }
}
