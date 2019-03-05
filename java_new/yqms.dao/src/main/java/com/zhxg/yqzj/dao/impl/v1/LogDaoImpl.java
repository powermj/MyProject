package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.LogDao;
import com.zhxg.yqzj.entities.v1.Log;

@Repository
public class LogDaoImpl extends BaseDaoImpl<BaseEntity> implements LogDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.Log.";

    @Override
    public List<Map<String, Object>> gettest_log() {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "gettest", null);
    }

    @Override
    public List<Log> getYqllListByKruuid_other(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getYqllListByKruuid", paramMap);
    }

    @Override
    public List<Log> getWarningListByKsuuid_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getWarningListByKsuuid", paramMap);
    }

    @Override
    public int insertLog_log(Log log) {
        return this.sqlSessionTemplate.insert(NAME_SPACE + "insertLog", log);
    }

    @Override
    public String getDetailInfoCnt_info(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getDetailInfoCnt_info", paramMap);
    }

    @Override
    public String getDetailInfoCnt_other(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getDetailInfoCnt_other", paramMap);
    }

}
