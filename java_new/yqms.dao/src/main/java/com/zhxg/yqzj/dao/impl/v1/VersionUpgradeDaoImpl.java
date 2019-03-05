package com.zhxg.yqzj.dao.impl.v1;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhxg.yqzj.dao.v1.VersionUpgradeDao;

@Repository
public class VersionUpgradeDaoImpl implements VersionUpgradeDao {
    @Autowired(required = true)
    protected SqlSession sqlSessionTemplate;
	private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.VersionUpgrade.";

    @Override
    public Map<String, Object> updateAppVersion(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE + "updateAppVersion",params);
    }
	
}
