package com.zhxg.yqzj.service.v1;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.Account;
import com.zhxg.yqzj.entities.v1.AccountVO;
import com.zhxg.yqzj.service.exception.user.AccountAlreadyExistsException;
import com.zhxg.yqzj.service.exception.user.AccountNumberExceedingLimitException;
import com.zhxg.yqzj.service.exception.user.AccountUnavailabilityException;
import com.zhxg.yqzj.service.exception.user.CreateWPAccountFailedException;
import com.zhxg.yqzj.service.exception.user.PasswordErrorException;
import com.zhxg.yqzj.service.exception.user.PermissionDeniedException;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.service
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户模块服务层接口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.service.v1.UserService.java
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
public interface UserService extends BaseService<BaseEntity> {


    public int addAccount(Account account, HttpServletRequest request) throws SysException, AccountAlreadyExistsException, AccountUnavailabilityException, PermissionDeniedException, CreateWPAccountFailedException, AccountNumberExceedingLimitException;


    public Map<String, Object> checkAccount(String account,String accountId, HttpServletRequest request) throws AccountAlreadyExistsException, AccountUnavailabilityException;


    public PageInfo<Account> getAccountList(Map<String, Object> paramMap, Pagination pageInfo);
    
    /**
     * 更新账号顺序
     * @param account
     * @return
     */
    int updateAccountOrder(Account account,Map<String,Object> params) throws UserNoFoundException, SysException;


    public int modifyAccount(Account account, HttpServletRequest request) throws AccountAlreadyExistsException, AccountUnavailabilityException, PermissionDeniedException, SysException, CreateWPAccountFailedException;


    public Account getAccountInfo(String accountId);


    public boolean changePassword(AccountVO params, HttpServletRequest request) throws SysException, PasswordErrorException;


    public void sendCode(HttpServletRequest request, String phone) throws Exception;


    public void changeAccount(AccountVO params, HttpServletRequest request) throws Exception;


    public void validatePassword(HttpServletRequest request, String accountId, String password) throws Exception;


    int modifyAccountApp(Account account, HttpServletRequest request) throws AccountAlreadyExistsException,
            AccountUnavailabilityException, PermissionDeniedException, SysException, CreateWPAccountFailedException;
    
    public Map<String, Object> getUserByKuLid(String kuLid);
    
    public Map<String, Object> getUserByThirdId(String thirdId);


    /**
     * 校验是否需要弹窗（by userid）
     * @param paramMap
     * @return
     */
    public Map<String, Object> checkFirstLogin(Map<String, Object> paramMap);


    /**
     * 确认弹窗（by userid）
     * @param paramMap
     * @return
     */
    public Map<String, Object> confirmAlert(Map<String, Object> paramMap);
    
    
    /**
     * 试用转正式     套餐变更，日期变更
     * @param paramMap
     * @return
     */
    public Map<String, Object> trialTransition(Map<String, Object> paramMap);
    /**
     * 账号注销，停用
     * @param paramMap
     * @return
     */
    public Map<String, Object> cancelUser(Map<String, Object> paramMap);
    
    /**
     * copySubjectInfo
     * @param paramMap
     * @return
     */
    public Map<String, Object> copySubjectInfo(Map<String, Object> paramMap);
    
    /**
     * 添加热词屏蔽词
     * @param paramMap
     * @return
     */
    public Map<String, Object> addHotKeywordFilter(Map<String, Object> paramMap);
    
    /**
     * 删除热词屏蔽词
     * @param paramMap
     * @return
     */
    public Map<String, Object> delHotKeywordFilter(Map<String, Object> paramMap);
    
    /**
     * 查询热词屏蔽词
     * @param paramMap
     * @return
     */
    public Map<String, Object> getHotKeywordFilter(Map<String, Object> paramMap);
    
    /**
     * 添加全局否定词
     * @param paramMap
     * @return
     */
    public Map<String, Object> addGlobalNotKeyword(Map<String, Object> paramMap);
    
    /**
     * 查询全局否定词
     * @param paramMap
     * @return
     */
    public Map<String, Object> selectGlobalNotKeyword(Map<String, Object> paramMap);
    

}
