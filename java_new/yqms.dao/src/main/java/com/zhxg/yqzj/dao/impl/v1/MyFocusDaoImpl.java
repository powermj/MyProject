package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhxg.yqzj.dao.v1.MyFocusDao;

/**
 * <p>Description: </p>
 * @author zyl
 * @date 2017年11月8日
 * @version 1.0
 */
@Repository
public class MyFocusDaoImpl implements MyFocusDao {
    @Autowired(required = true)
    protected SqlSession sqlSessionTemplate;
    
    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.MyFocus.";

    @Override
    public List<Map<String, Object>> selectList_self(Map<String, Object> params) {
        return sqlSessionTemplate.selectList(NAME_SPACE + "selectList", params);
    }

    @Override
    public List<Map<String, Object>> selectBasicList_self(Map<String, Object> params) {
        return sqlSessionTemplate.selectList(NAME_SPACE + "selectBasicList", params);
    }

    @Override
    public String selectContent_self(Map<String, Object> params) {
        return sqlSessionTemplate.selectOne(NAME_SPACE + "selectContent", params);
    }
}
