package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.yqzj.dao.v1.AccountDao;
import com.zhxg.yqzj.entities.v1.Account;
import com.zhxg.yqzj.entities.v1.ManageUser;

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
public class AccountDaoImpl extends BaseDaoImpl<Account> implements AccountDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.Account.";

    /* 
     * 登录接口用
     */
    @Override
    public Account getAccount(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getAccount", params);
    }

    @Override
    public List<Account> getVaildAccounts() {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getVaildAccounts");
    }

    @Override
    public Account getAccountByAccountId(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getAccountByAccountId", params);
    }

    @Override
    public int saveAccount(Account account) {
        return this.sqlSessionTemplate.insert(NAME_SPACE + "insertAccount", account);
    }

    @Override
    public PageInfo<Account> getAccountList(Map<String, Object> paramMap, Pagination pageInfo) {
        return this.getPageList(NAME_SPACE + "getAccountList", pageInfo, paramMap);
    }

    @Override
    public int getAccountCountByUserId(int userId) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getAccountCountByUserId",userId);
    }

    @Override
    public List<Account> getSortAccountListByUserId(int userId) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getSortAccountListByUserId", userId);
    }

    @Override
    public int updateAccountOrderBunch(List<Account> accounts) {
        return this.sqlSessionTemplate.update(NAME_SPACE + "updateAccountOrderBunch", accounts);
    }

    @Override
    public int updateAccount(Account account) {
        return this.sqlSessionTemplate.update(NAME_SPACE + "updateAccount", account);
    }

    @Override
    public int getMaxOrder(Integer userid) {
        Map<String, Integer>  map= this.sqlSessionTemplate.selectOne(NAME_SPACE + "getMaxOrder", userid);
        return map==null?0:map.get("maxOrder");
    }

    @Override
    public int moveUpBelow(Account account) {
        return this.sqlSessionTemplate.update(NAME_SPACE + "moveUpBelow", account);
        
    }

    @Override
    public int clearHistoryAccount(Account account) {
        return this.sqlSessionTemplate.update(NAME_SPACE + "clearHistoryAccount", account);
    }

    @Override
    public List<Map<String, Object>> appUserThemeImage_self(Map<String, Object> params) {
        return sqlSessionTemplate.selectList(NAME_SPACE + "appUserThemeImage", params);
    }

    @Override
    public List<ManageUser> getManageUserList(int userId) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getManageUserList", userId);
    }

    @Override
    public List<Map<String, Object>> getTrialMessage_official(Map<String, Object> map) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getTrialMessage", map);
    }

    @Override
    public int applyForTrial_official(Map<String, Object> map) {
        return this.sqlSessionTemplate.update(NAME_SPACE + "applyForTrial", map);
    }

    @Override
    public int modifyPassword(String cellPhoneNumber) {
        return this.sqlSessionTemplate.update(NAME_SPACE + "modifyPassword", cellPhoneNumber);
    }

    @Override
    public Map<String, String> getPermitBindingNumber(int userId) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getPermitBindingNumber", userId);
    }

    @Override
    public Map<String, Integer> getUserId(String loginName) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getUserId", loginName);
    }

    @Override
    public Map<String, Object> getUserArea(int userId) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getUserArea", userId);
    }

    @Override
    public int saveCustomerId(Map<String, Object> userMap) {
        return this.sqlSessionTemplate.insert(NAME_SPACE + "insertCustomerId", userMap);
    }

    @Override
    public Map<String, Object> getIndustry(int industry) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getIndustry", industry);
    }

    @Override
    public int getYqmsCountByCustomerId(String userId) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getYqmsCountByCustomerId",userId);
    }

    @Override
    public int getYqmsAccountCountByssoId(String ssoAccountId) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getYqmsAccountCountByssoId",ssoAccountId);
    }

    @Override
    public void deletePhoneList(String macid) {
        this.sqlSessionTemplate.update(NAME_SPACE + "deletePhoneList", macid);
        
    }

    @Override
    public void deletePhoneMacid(String macid) {
        this.sqlSessionTemplate.update(NAME_SPACE + "deletePhoneMacid", macid);
        
    }

    @Override
    public Map<String, Object> getUserInfo(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getUserInfo", paramMap);
    }

    @Override
    public Account getAccountByPhone(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getAccountByPhone", params);
    }

    @Override
    public int delAccountByAccountId(Account account) {
        return this.sqlSessionTemplate.delete(NAME_SPACE + "delAccountByAccountId", account);
    }


}
