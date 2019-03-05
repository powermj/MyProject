package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
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
 * Comments: 用户模块持久层接口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.dao.v1.UserDao.java
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
public interface AccountDao extends BaseDao<Account> {

    /**
     * 根据kuId获取用户信息
     *
     * @param Account
     * @return Account
     */
    public Account getAccount(Map<String, Object> params);
    /**
     * 根据kuId获取用户信息
     *
     * @param Account
     * @return Account
     */
    public Account getAccountByPhone(Map<String, Object> params);

    public List<Account> getVaildAccounts();

    public Account getAccountByAccountId(Map<String, Object> params);

    public int saveAccount(Account account);

    public PageInfo<Account> getAccountList(Map<String, Object> paramMap, Pagination pageInfo);
    
    /**
     * 查询账号下面的人员数量
     * @param userId
     * @return
     */
    public int getAccountCountByUserId(int userId);
    /**
     * 获取排序后的账号列表
     * @param userId 
     * @return
     */
    List<Account> getSortAccountListByUserId(int userId);
    /**
     * 批量更新账号顺序
     * @param accounts
     * @return
     */
    int updateAccountOrderBunch(List<Account> accounts);

    public int updateAccount(Account account);
    
    public int delAccountByAccountId(Account account);

    public int getMaxOrder(Integer integer);

    public int moveUpBelow(Account account);

    /**
     * 清理被删除的账号
     * @param account
     * @return
     */
    public int clearHistoryAccount(Account account);

    public List<Map<String, Object>> appUserThemeImage_self(Map<String, Object> params);

    public List<ManageUser> getManageUserList(int userId);

    public int applyForTrial_official(Map<String, Object> map);

    public List<Map<String, Object>> getTrialMessage_official(Map<String, Object> map);

    public int modifyPassword(String cellPhoneNumber);

    public Map<String, String> getPermitBindingNumber(int userId);

    public Map<String, Integer> getUserId(String loginName);

    public Map<String, Object> getUserArea(int userId);

    public int saveCustomerId(Map<String, Object> userMap);

    public Map<String, Object> getIndustry(int industry);

    public int getYqmsCountByCustomerId(String userId);

    public int getYqmsAccountCountByssoId(String ssoAccountId);

    public void deletePhoneList(String macid);

    public void deletePhoneMacid(String macid);

    public Map<String, Object> getUserInfo(Map<String, Object> paramMap);

}
