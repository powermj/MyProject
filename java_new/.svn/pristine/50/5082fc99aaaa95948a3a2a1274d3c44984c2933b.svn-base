package com.zhxg.yqzj.dao.impl.v1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.UserDao;
import com.zhxg.yqzj.entities.v1.Account;
import com.zhxg.yqzj.entities.v1.User;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.dao
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户模块持久层接口实现
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.dao.impl.v1.UserDaoImpl.java
 * <p>
 * Author: azmiu
 * <p>
 * Create Date: 2017年2月28日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<BaseEntity> implements UserDao {

    @Override
    public PageInfo<BaseEntity> getUsers(HashMap<String, Object> paramMap, Pagination pagination) {
        return this.getPageList("com.zhxg.yqzj.entities.v1.User.getUsers", pagination, paramMap);
    }

    @Override
    public User getUserByKuId(String kuId) {
        return this.sqlSessionTemplate.selectOne("com.zhxg.yqzj.entities.v1.User.getUserByKuId", kuId);
    }

	@Override
	public User getArtificialWarningSubject(String userid) {
		return this.sqlSessionTemplate.selectOne("com.zhxg.yqzj.entities.v1.User.getUserArtificialWaringSubjects", userid);
	}

    @Override
    public int addAccount(Account account) {
        return this.sqlSessionTemplate.insert("com.zhxg.yqzj.entities.v1.Account.addAccount", account);
    }

    @Override
    public Map<String,Object> getUserByKuLid(String kulid) {
        return this.sqlSessionTemplate.selectOne("com.zhxg.yqzj.entities.v1.User.getUser", kulid);
    }
    
    @Override
    public Map<String,Object> getUserByThirdId(String thirdId) {
        return this.sqlSessionTemplate.selectOne("com.zhxg.yqzj.entities.v1.User.getUserByThirdId", thirdId);
    }

    @Override
    public Map<String, Object> checkFirstLogin(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne("com.zhxg.yqzj.entities.v1.User.checkFirstLogin", paramMap);
    }

    @Override
    public int confirmAlert(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.insert("com.zhxg.yqzj.entities.v1.User.confirmAlert", paramMap);
    }

    @Override
    public String getAgentId(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne("com.zhxg.yqzj.entities.v1.User.getAgentId", paramMap);
    }

    @Override
    public List<Map<String,Object>> getNewUserByKuId(String kuId) {
        return this.sqlSessionTemplate.selectList("com.zhxg.yqzj.entities.v1.User.getNewUserByKuId",kuId);
    }
    
    @Override
    public Map<String, Object> selectCustomerId(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne("com.zhxg.yqzj.entities.v1.User.selectCustomerId", paramMap);
    }
    
    @Override
    public Map<String, Object> getAgentsAppInfo(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne("com.zhxg.yqzj.entities.v1.User.getAgentsAppInfo", paramMap);
    }

    @Override
    public int getUserSubcribeStatus(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne("com.zhxg.yqzj.entities.v1.User.getUserSubcribeStatus", paramMap);
    }

    @Override
    public String getUserReceiveTime(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne("com.zhxg.yqzj.entities.v1.User.getUserReceiveTime", paramMap);
    }

    @Override
    public int updateUserReceiveTime(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.update("com.zhxg.yqzj.entities.v1.User.updateUserReceiveTime", paramMap);
    }

    @Override
    public int setUserReportCondition(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.update("com.zhxg.yqzj.entities.v1.User.setUserReportCondition",paramMap);
    }

    @Override
    public String getUserReportCondition(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne("com.zhxg.yqzj.entities.v1.User.getUserReportCondition",paramMap);
    }

	@Override
	public int updateUserSubcribeStatus(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.update("com.zhxg.yqzj.entities.v1.User.updateUserSubcribeStatus",paramMap);
	}

}
