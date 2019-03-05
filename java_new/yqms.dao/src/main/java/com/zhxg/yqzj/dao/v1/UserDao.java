package com.zhxg.yqzj.dao.v1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseEntity;
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
public interface UserDao extends BaseDao<BaseEntity> {

    /**
     * 获取所有用户
     * 
     * @param pageInfo
     * @param paramMap
     * @return
     */
    public PageInfo<BaseEntity> getUsers(HashMap<String, Object> paramMap, Pagination pageInfo);

    /**
     * 根据kuId获取用户信息
     *
     * @param kuId
     * @return
     */
    public User getUserByKuId(String kuId);

	public User getArtificialWarningSubject(String kuId);

    public int addAccount(Account account);
    /**
     * 根据kuLId获取用户信息
     *
     * @param kuId
     * @return
     */
    public Map<String,Object> getUserByKuLid(String kulid);

    /**
     * 根据USERID获取是否为首次登陆
     * @param paramMap
     * @return
     */
    public Map<String, Object> checkFirstLogin(Map<String, Object> paramMap);

    /**
     * 确认弹窗
     * @param paramMap
     * @return
     */
    public int confirmAlert(Map<String, Object> paramMap);
    
    /**
     * 根据kuLId获取用户信息
     *
     * @param kuId
     * @return
     */
    public Map<String,Object> getUserByThirdId(String thirdId);
    
    /**
     * 根据kuid获取代理商id
     * @param paramMap
     * @return
     */
    public String getAgentId(Map<String,Object> paramMap);

    
    /**
     * 根据kuId获取最新用户信息
     *
     * @param kuId
     * @return
     */
    public List<Map<String,Object>> getNewUserByKuId(String kuId);
    
    /**
     * 根据USERID获取用户中心customerID
     * @param paramMap
     * @return
     */
    public Map<String, Object> selectCustomerId(Map<String, Object> paramMap);
    
    /**
     * 根据platform获取agentAppInfo
     * @param paramMap
     * @return
     */
    public Map<String, Object> getAgentsAppInfo(Map<String, Object> paramMap);
    
    /**
     * 获取用户是否订阅日报状态
     *
     * @param paramMap
     * @return
     */
    public int getUserSubcribeStatus(Map<String, Object> paramMap);
    
    /**
     * 获取用户订阅日报时间
     * @param paramMap
     * @return
     */
    public String getUserReceiveTime(Map<String, Object> paramMap);
    
    /**
     * 修改用户订阅日报时间
     * @param paramMap
     * @return
     */
    public int updateUserReceiveTime(Map<String, Object> paramMap);
    
    /**
     * 设置用户日报导出查询信息条件
     * @param paramMap
     * @return
     */
    public int setUserReportCondition(Map<String, Object> paramMap);
    
    /**
     * 获取用户日报导出条件
     * @param paramMap
     * @return
     */
    public String getUserReportCondition(Map<String,Object> paramMap);

	/**
	 * 修改日报订阅状态
	 * @param paramMap
	 * @return
	 */
	public int updateUserSubcribeStatus(Map<String, Object> paramMap);
    
}
