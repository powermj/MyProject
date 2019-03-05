package com.zhxg.yqzj.service.impl.v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.constants.SysConstants;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.framework.base.utils.HttpUtil;
import com.zhxg.framework.base.utils.PropertiesUtil;
import com.zhxg.framework.base.utils.RedisUtil;
import com.zhxg.framework.base.utils.StringUtil;
import com.zhxg.sso.SSOAccount;
import com.zhxg.sso.SsoManager;
import com.zhxg.sso.TokenErrorException;
import com.zhxg.sso.exception.AccountExistsException;
import com.zhxg.sso.exception.AccountUnavailableException;
import com.zhxg.sso.exception.SsoCodeErrorException;
import com.zhxg.sso.exception.SsoPasswordErrorException;
import com.zhxg.sso.exception.SsoSystemException;
import com.zhxg.yqzj.dao.v1.AccountDao;
import com.zhxg.yqzj.dao.v1.UserBaseInfoDao;
import com.zhxg.yqzj.dao.v1.UserDao;
import com.zhxg.yqzj.entities.v1.Account;
import com.zhxg.yqzj.entities.v1.AccountVO;
import com.zhxg.yqzj.entities.v1.UserConfig;
import com.zhxg.yqzj.service.exception.user.AccountAlreadyExistsException;
import com.zhxg.yqzj.service.exception.user.AccountNumberExceedingLimitException;
import com.zhxg.yqzj.service.exception.user.AccountUnavailabilityException;
import com.zhxg.yqzj.service.exception.user.CodeErrorException;
import com.zhxg.yqzj.service.exception.user.CreateWPAccountFailedException;
import com.zhxg.yqzj.service.exception.user.PasswordErrorException;
import com.zhxg.yqzj.service.exception.user.PermissionDeniedException;
import com.zhxg.yqzj.service.v1.UserService;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.service
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户模块服务层接口实现
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.service.impl.v1.UserServiceImpl.java
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
@Service
public class UserServiceImpl extends BaseServiceImpl<BaseEntity> implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private SsoManager ssoManager;
    
    @Autowired
    private UserBaseInfoDao userBaseInfoDao;

    private static final String USER_NOT_FOUND = "该账户下暂无人员信息";
    private static final String SORT_USER_NOT_FOUND = "排序的用户不存在";
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    protected BaseDao<BaseEntity> getBaseDao() {
        return this.userDao;
    }

    @Override
    public int addAccount(Account account, HttpServletRequest request)
            throws AccountAlreadyExistsException, AccountUnavailabilityException, PermissionDeniedException, SysException, CreateWPAccountFailedException, AccountNumberExceedingLimitException {
        Map<String, Object> accountMap = null;
        int result = 0;
        try {
            // 获取登录账号信息
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("accountId", account.get_ACCOUNTID());
            Account loginAccount = accountDao.getAccountByAccountId(params);
            if (loginAccount.getIdentity() != SysConstants.MANAGER_ACCOUNT_CODE) {
                // 没权限
                throw new PermissionDeniedException();
            }
            //获取配置信息中账号数量限制
            int limit = 0;
            for (UserConfig config : loginAccount.getUser().getConfigInfo()) {
                if (config.getKey().equals("ACCOUNT_LIMIT")) {
                    limit = Integer.parseInt(config.getValue()) ;
                    break;
                }
            }
            //获取当前有效账号数量
            List<Account> list = accountDao.getSortAccountListByUserId(loginAccount.getUserid());
            if(limit<=list.size()){
                throw new AccountNumberExceedingLimitException();
            }
            
            // 获取登录账号的SSO_CUSTOMER_ID
            String customerId = "";
            for (UserConfig config : loginAccount.getUser().getConfigInfo()) {
                if (config.getKey().equals("SSO_CUSTOMER_ID")) {
                    customerId = config.getValue();
                    break;
                }
            }
            // 验证账号
            accountMap = ssoManager.checkAccount(customerId, account.getAccount(), request);
            String product = "YQZJ,ZHWP";
            if (accountMap != null && !StringUtil.isEmpty(accountMap.get("product"))) {
                if (accountMap.get("product").toString().contains("YQZJ")||
                        accountMap.get("product").toString().contains("ZHWP")) {
                    // 账号已存在
                    throw new AccountAlreadyExistsException();
                }
                // 已经有网评了 不再开通
                if (accountMap.get("product").toString().contains("ZHWP")) {
                    product = "YQZJ";
                }
                account.setName(StringUtil.dealNull(accountMap.get("name")));
                account.setPhone(StringUtil.dealNull(accountMap.get("telphone")));
                account.setEmail(StringUtil.dealNull(accountMap.get("mail")));
                account.setWechat(StringUtil.dealNull(accountMap.get("wechat")));
            }

            SSOAccount ssoAccount = new SSOAccount();
            ssoAccount.setCustomerId(customerId);
            ssoAccount.setName(account.getName());
            ssoAccount.setPassword(account.getPassword());
            ssoAccount.setProduct(product);
            ssoAccount.setTelphone(account.getPhone());
            SSOAccount resultSsoAccount = ssoManager.addAccount(ssoAccount, request);
            if (product.contains("ZHWP")) {// 调用何灵芝接口新建网评员
                try{
                    createWPAccount(ssoAccount.getTelphone(), resultSsoAccount.getAccountId(), loginAccount.getSsoAccountId() + "");
                }catch (CreateWPAccountFailedException e) {
                    try {
                        resultSsoAccount.setProduct("YQZJ,ZHWP");
                        ssoManager.deleteAccount(request,resultSsoAccount);
                    } catch (SsoSystemException e1) {
                        logger.error(e1.getMessage(), e1);
                    }
                    throw new CreateWPAccountFailedException();
                }
                
            }
            int order = accountDao.getMaxOrder(loginAccount.getUserid()) + 1;
            account.setAccount(account.getPhone());
            account.setSsoAccountId(Integer.parseInt(resultSsoAccount.getAccountId()));
            account.setTemplate(SysConstants.ACCOUNT_TEMPLATE_EXPERT);// 专家模板
            account.setIdentity(SysConstants.COMMON_ACCOUNT_CODE);// 普通用户
            account.setState(SysConstants.ACCOUNT_STATE_NORMAL);// 状态正常
            account.setUserid(loginAccount.getUserid());
            account.setOrder(order);
            if(StringUtils.isEmpty(account.getPassword())){
                account.setPassword("111111");
            }
            result = accountDao.saveAccount(account);
            accountDao.clearHistoryAccount(account);//清理被删除的账号
        }  catch (AccountExistsException e) {
            throw new AccountAlreadyExistsException();
        } catch (AccountUnavailableException e) {
            throw new AccountUnavailabilityException();
        } catch (TokenErrorException e) {
            logger.error("creat account error!!", e);
            throw new SysException();
        }
        return result;
    }

    /**
     * 创建网评账号
     * 
     * @return
     * @throws SysException
     */
    private boolean createWPAccount(String account, String ssoAccountId, String pssoid) throws CreateWPAccountFailedException, SysException {
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("username", account+"#"+System.currentTimeMillis());
            param.put("phone", account);
            param.put("sso_account_id", ssoAccountId);
            param.put("pssoid", pssoid);
            String wpResult = HttpUtil.httpsPost(PropertiesUtil.getValue("zhwp.host")+"addUser", param,null,"UTF-8");
            JSONObject wpJson = JSONObject.parseObject(wpResult);
            if (!"1".equals(wpJson.getString("code"))) {
                logger.error("创建网评账号失败！！[" + wpResult + "]");
                throw new CreateWPAccountFailedException(wpJson.getString("message"));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SysException();
        }
        return true;
    }
    
    
    /**
     * 删除网评账号
     * @param ssoAccountId
     * @return
     * @throws SysException
     */
    private boolean deleteWPAccount(String ssoAccountId) throws SysException {
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("sso_account_id", ssoAccountId);
            String wpResult = HttpUtil.httpsPost(PropertiesUtil.getValue("zhwp.host")+"deleteUser", param,null,"UTF-8");
            JSONObject wpJson = JSONObject.parseObject(wpResult);
            if (!"1".equals(wpJson.getString("code"))) {
                logger.error("删除网评账号失败！！[" + wpResult + "]");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SysException();
        }
        return true;
    }
    
    

    @Override
    public Map<String, Object> checkAccount(String account, String accountId, HttpServletRequest request) throws AccountAlreadyExistsException, AccountUnavailabilityException {
        Map<String, Object> accountMap = null;
        try {
            // 获取登录账号信息
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("accountId", accountId);
            Account loginAccount = accountDao.getAccountByAccountId(params);
            // 获取登录账号的SSO_CUSTOMER_ID
            String customerId = "";
            for (UserConfig config : loginAccount.getUser().getConfigInfo()) {
                if (config.getKey().equals("SSO_CUSTOMER_ID")) {
                    customerId = config.getValue();
                    break;
                }
            }
            accountMap = ssoManager.checkAccount(customerId, account, request);
            if (accountMap != null && !StringUtil.isEmpty(accountMap.get("product"))) {
                if (accountMap.get("product").toString().contains("YQZJ")||
                        accountMap.get("product").toString().contains("ZHWP")) {
                    // 账号已存在
                    throw new AccountAlreadyExistsException();
                }
            }else{
                accountMap = null;
            }
        } catch (AccountExistsException e) {
            throw new AccountAlreadyExistsException();
        } catch (AccountUnavailableException e) {
            throw new AccountUnavailabilityException();
        }
        return accountMap;
    }

    @Override
    public int modifyAccount(Account account, HttpServletRequest request)
            throws AccountAlreadyExistsException, AccountUnavailabilityException, PermissionDeniedException, SysException, CreateWPAccountFailedException {
        int result = 0;
        try {
            // 获取登录账号信息
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("accountId", account.get_ACCOUNTID());
            Account loginAccount = accountDao.getAccountByAccountId(params);
            if (loginAccount.getIdentity() != SysConstants.MANAGER_ACCOUNT_CODE && !account.getAccountId().equals(loginAccount.getAccountId())) {
                // 没权限
                throw new PermissionDeniedException();
            }
            
            // 需要修改用户中心
            if (!StringUtils.isEmpty(account.getPassword()) || !StringUtils.isEmpty(account.getName()) ||
                    !StringUtils.isEmpty(account.getPhone())|| account.getState() == SysConstants.DELETE_ACCOUNT_STATE) {
                // 获取登录账号的SSO_CUSTOMER_ID
                String customerId = "";
                for (UserConfig config : loginAccount.getUser().getConfigInfo()) {
                    if (config.getKey().equals("SSO_CUSTOMER_ID")) {
                        customerId = config.getValue();
                        break;
                    }
                }

                Map<String, Object> params2 = new HashMap<String, Object>();
                params2.put("accountId", account.getAccountId());
                Account modifyAccount = accountDao.getAccountByAccountId(params2);
                SSOAccount ssoAccount = new SSOAccount();
                ssoAccount.setAccountId(modifyAccount.getSsoAccountId() + "");
                ssoAccount.setCustomerId(customerId);
                ssoAccount.setName(account.getName());
                ssoAccount.setPassword(account.getPassword());
                if(StringUtil.isEmpty(account.getPhone())){
                    ssoAccount.setTelphone(loginAccount.getAccount());
                }else{
                    ssoAccount.setTelphone(account.getPhone());
                }
                
                //删除账号
                if (account.getState() == SysConstants.DELETE_ACCOUNT_STATE) {
                    account.setUserid(loginAccount.getUserid());
                    accountDao.moveUpBelow(account);//顺序调整
                    account.setOrder(0);
                    try {
                        ssoAccount.setProduct("YQZJ,ZHWP");
                        ssoManager.deleteAccount(request,ssoAccount);
                    } catch (SsoSystemException e) {
                        log.error(e.getMessage(), e);
                    }
                    deleteWPAccount(ssoAccount.getAccountId());
                }else{// ssoAccount有ID 作为修改接口使用
                    SSOAccount resultSsoAccount = ssoManager.addAccount(ssoAccount, request);
                    if (resultSsoAccount == null) {
                        log.info("用户中心修改信息失败！");
                    }
                }
                if(SysConstants.STOP_ACCOUNT_STATE == account.getState()){
                    //清除本地AccessToken
                    RedisUtil.delete(SysConstants.ACCESSTOKEN_DB,SysConstants.PRODUCT_PREFIX + account.getAccountId());
                }
                
            }
            result = accountDao.updateAccount(account);

        } catch (AccountExistsException e) {
            throw new AccountAlreadyExistsException();
        } catch (AccountUnavailableException e) {
            throw new AccountUnavailabilityException();
        } catch (TokenErrorException e) {
            logger.error("creat account error!!", e);
            throw new SysException();
        }
        return result;
    }
    
    
    @Override
    public int modifyAccountApp(Account account, HttpServletRequest request)
            throws AccountAlreadyExistsException, AccountUnavailabilityException, PermissionDeniedException, SysException, CreateWPAccountFailedException {
            int result = 0;     
            // 获取登录账号信息
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("accountId", account.get_ACCOUNTID());
            Account loginAccount = accountDao.getAccountByAccountId(params);
            if (loginAccount.getIdentity() != SysConstants.MANAGER_ACCOUNT_CODE && !account.getAccountId().equals(loginAccount.getAccountId())) {
                // 没权限
                throw new PermissionDeniedException();
            }
            
            // 需要修改用户中心
            if (!StringUtils.isEmpty(account.getPassword()) || !StringUtils.isEmpty(account.getName()) ||
                    !StringUtils.isEmpty(account.getPhone())|| account.getState() == SysConstants.DELETE_ACCOUNT_STATE) {
                // 获取登录账号的SSO_CUSTOMER_ID
                String customerId = "";
                for (UserConfig config : loginAccount.getUser().getConfigInfo()) {
                    if (config.getKey().equals("SSO_CUSTOMER_ID")) {
                        customerId = config.getValue();
                        break;
                    }
                }                
            }
            result = accountDao.updateAccount(account);
        return result;
    }

    @Override
    public PageInfo<Account> getAccountList(Map<String, Object> paramMap, Pagination pageInfo) {
        return accountDao.getAccountList(paramMap, pageInfo);
    }

    @Override
    public int updateAccountOrder(Account account, Map<String, Object> params) throws UserNoFoundException, SysException {
        int sortAccountId = account.getAccountId();// 排序的账号id
        int userId = Integer.parseInt(String.valueOf(params.get("_KUID")));// 登录的用户id
        int order = account.getOrder();// 排序值

        // 查询当前账户下面的人员数量
        int userAccount = accountDao.getAccountCountByUserId(userId);
        // 该账号下暂无人员信息
        if (userAccount == 0) {
            throw new UserNoFoundException(USER_NOT_FOUND);
        }
        // order超范围更正
        order = order < 1 ? 1 : order > userAccount ? userAccount : order;

        // 查询排序用户的信息
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("accountId", sortAccountId);
        Account curAccount = accountDao.getAccountByAccountId(queryParams);
        if (curAccount == null) {// 排序用户不存在
            throw new UserNoFoundException(SORT_USER_NOT_FOUND);
        }

        // 排序用户的当前顺序
        int curOrder = curAccount.getOrder();

        // 查询当前用户下面的所有账户，升序排列
        List<Account> sortAccountList = accountDao.getSortAccountListByUserId(userId);
        if (sortAccountList == null || sortAccountList.isEmpty()) {
            throw new UserNoFoundException(USER_NOT_FOUND);
        }

        // 纠正当前顺序，如果当前顺序为空或者小于1，查找在数据库列表中的位置
        curOrder = curOrder < 1 ? getOrder(sortAccountList, sortAccountId) : curOrder;

        // 当期位置和排序后的位置一样，无需排序
        if (curOrder == order) {
            return 1;
        }

        int rowCount = 0;
        List<Account> updateList = new ArrayList<>();// 批量更新的列表
        // 更新用户的新位置
        Account update = new Account();
        update.setAccountId(curAccount.getAccountId());
        update.setOrder(order);
        updateList.add(update);
        // 当前位置小于将更新的位置，从当前位置的下一个元素开始，到新位置结束，依次更新位置为当前位置减一
        if (curOrder < order) {
            for (int i = curOrder + 1; i <= order; i++) {
                update = new Account();
                update.setAccountId(sortAccountList.get(i - 1).getAccountId());
                update.setOrder(i - 1);
                updateList.add(update);
            }
        } else {// 当前位置大于将更新的位置，从更新的位置开始，到当前位置的前一个位置结束，依次更新位置为当前位置加一
            for (int i = order; i <= curOrder - 1; i++) {
                update = new Account();
                update.setAccountId(sortAccountList.get(i - 1).getAccountId());
                update.setOrder(i + 1);
                updateList.add(update);
            }
        }

        try {
            // 批量更新
            rowCount = accountDao.updateAccountOrderBunch(updateList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new SysException();
        }
        return rowCount;
    }

    private int getOrder(List<Account> sortAccountList, int accountId) {
        for (int i = 0; i < sortAccountList.size(); i++) {
            if (sortAccountList.get(i).getAccountId() == accountId)
                return i + 1;
        }
        return 1;
    }

    @Override
    public Account getAccountInfo(String accountId) {
        Map<String, Object> params = new HashMap<>();
        params.put("accountId", accountId);
        return accountDao.getAccountByAccountId(params);
    }

    @Override
    public boolean changePassword(AccountVO params, HttpServletRequest request) throws SysException, PasswordErrorException {
        Map<String, Object> params2 = new HashMap<String, Object>();
        params2.put("accountId", params.getAccountId());
        Account modifyAccount = accountDao.getAccountByAccountId(params2);
        try {
            ssoManager.changePassword(modifyAccount.getSsoAccountId() + "", params.getOldPassword(), params.getNewPassword(), request);
        } catch (TokenErrorException e) {
            logger.error("creat account error!!", e);
            throw new SysException();
        } catch (SsoPasswordErrorException e) {
            throw new PasswordErrorException();
        } catch (SsoSystemException e) {
            logger.error(e.getMessage(), e);
            throw new SysException();
        }
        return true;
    }

    @Override
    public void sendCode(HttpServletRequest request, String phone) throws Exception {
        try {
            ssoManager.sendCode(request, phone);
        }  catch (AccountExistsException e) {
            throw new AccountAlreadyExistsException("新手机号与原手机号相同。");
        } catch (AccountUnavailableException e) {
            throw new AccountUnavailabilityException("该账号已存在。");
        } catch (SsoSystemException e) {
            logger.error("creat account error!!", e);
            throw new SysException();
        } catch (TokenErrorException e) {
            logger.error("creat account error!!", e);
            throw new SysException();
        }
    }

    @Override
    public void changeAccount(AccountVO params, HttpServletRequest request) throws Exception {
        try {
            ssoManager.changeAccount(request, params.getAccountId() + "", params.getPhone(), params.getCode());
        } catch (SsoCodeErrorException e) {
            throw new CodeErrorException();
        } catch (AccountExistsException e) {
            throw new AccountAlreadyExistsException("新手机号与原手机号相同。");
        } catch (AccountUnavailableException e) {
            throw new AccountUnavailabilityException("该账号已存在。");
        } catch (SsoSystemException e) {
            logger.error("creat account error!!", e);
            throw new SysException();
        } catch (TokenErrorException e) {
            logger.error("creat account error!!", e);
            throw new SysException();
        }
        Account account = new Account();
        account.setEmail(SysConstants.NO_CHANGE_VALUE);
        account.setWechat(SysConstants.NO_CHANGE_VALUE);
        account.setAccountId(params.getAccountId());
        account.setAccount(params.getPhone());
        account.setPhone(params.getPhone());
        accountDao.updateAccount(account);
    }

    @Override
    public void validatePassword(HttpServletRequest request, String accountId, String password ) throws Exception {
        Map<String, Object> params2 = new HashMap<String, Object>();
        params2.put("accountId", accountId);
        Account modifyAccount = accountDao.getAccountByAccountId(params2);
        try {
            ssoManager.validatePassword(request, modifyAccount.getSsoAccountId() + "", password);
        } catch (TokenErrorException e) {
            logger.error("creat account error!!", e);
            throw new SysException();
        } catch (SsoPasswordErrorException e) {
            throw new PasswordErrorException();
        } catch (SsoSystemException e) {
            logger.error(e.getMessage(), e);
            throw new SysException();
        }

    }

    @Override
    public Map<String, Object> getUserByKuLid(String kuLid) {
        return userDao.getUserByKuLid(kuLid);
    }
    
    @Override
    public Map<String, Object> getUserByThirdId(String thirdId) {
        return userDao.getUserByThirdId(thirdId);
    }

    @Override
    public Map<String, Object> checkFirstLogin(Map<String, Object> paramMap) {
        Map<String, Object> map = userDao.checkFirstLogin(paramMap);
        Map<String,Object> result = new HashMap<>();
        if(map!=null&&!map.isEmpty()){
            result.put("firstLogin", 0);
        }else{
            result.put("firstLogin", 1);
        }
        return result;
    }

    @Override
    public Map<String, Object> confirmAlert(Map<String, Object> paramMap) {
        int result = userDao.confirmAlert(paramMap);
        Map<String, Object> map = new HashMap<>();
        map.put("result", result);
        return map;
    }
    
    @Override
    public Map<String, Object> trialTransition(Map<String, Object> paramMap) {
        int result = userBaseInfoDao.updateUserVerKeyNum(paramMap);
        Map<String, Object> map = new HashMap<>();
        map.put("result", result);
        return map;
    }

    @Override
    public Map<String, Object> cancelUser(Map<String, Object> paramMap) {
        
        return null;
    }

    @Override
    public Map<String, Object> copySubjectInfo(Map<String, Object> paramMap) {
        String result = userBaseInfoDao.copySubjectInfo(paramMap);
        Map<String, Object> map = new HashMap<>();
        map.put("result", result);
        return map;
    }

	@Override
	public Map<String, Object> addHotKeywordFilter(Map<String, Object> paramMap) {
		String hotKeyword = userBaseInfoDao.selectHotKeywordFilter(paramMap);
		int result = 0;
		if(org.springframework.util.StringUtils.isEmpty(hotKeyword)){
			result = userBaseInfoDao.insertHotKeywordFilter(paramMap);
		}else{
			result = userBaseInfoDao.updateHotKeywordFilter(paramMap);
		}
		Map<String, Object> map = new HashMap<>();
	    map.put("result", result);
	    return map;
	}

	@Override
	public Map<String, Object> delHotKeywordFilter(Map<String, Object> paramMap) {
		String delHotKeyword = (String) paramMap.get("hotKeyword");
		int result = 0;
		if(StringUtils.isEmpty(delHotKeyword)){
			result = userBaseInfoDao.delHotKeywordFilter(paramMap);
		}
		Map<String, Object> map = new HashMap<>();
	    map.put("result", result);
	    return map;
	}

	@Override
	public Map<String, Object> getHotKeywordFilter(Map<String, Object> paramMap) {
		String hotKeyword = userBaseInfoDao.selectHotKeywordFilter(paramMap);
		Map<String, Object> map = new HashMap<>();
	    map.put("result", hotKeyword);
	    return map;
	}

	@Override
	public Map<String, Object> addGlobalNotKeyword(Map<String, Object> paramMap) {
		Integer result = 0;
		paramMap.put("type", "GLOBAL_NOT_KEYWORD");
		String paramValue = (String)paramMap.get("value");
		if(StringUtils.isEmpty(paramValue)){
			result = userBaseInfoDao.deleteUserInfo(paramMap);
			Map<String, Object> map = new HashMap<>();
		    map.put("result", result);
		    return map;
		}
		String value = userBaseInfoDao.selectUserInfo(paramMap);
		if(StringUtils.isEmpty(value)){
			result = userBaseInfoDao.insertUserInfo(paramMap);
		}else{
			if("O".equals(paramMap.get("operate"))){
				String[] keywords = paramValue.split(",");				
				StringBuffer sb =new StringBuffer(",");
				for(String key:keywords){
					if(sb.indexOf(","+key+",")==-1)
						sb.append(key).append(",");
				}
				String lastValue = sb.substring(1, sb.length()-1);
				paramMap.put("value", lastValue);
				result = userBaseInfoDao.modifyUserInfo(paramMap);
			}else if("A".equals(paramMap.get("operate"))){
				boolean isExist = value.startsWith(paramValue+",")||value.endsWith(","+paramValue)||value.indexOf(","+paramValue+",")!=-1;
				if(!isExist){
					value = value+","+paramMap.get("value");
					paramMap.put("value", value);
					result = userBaseInfoDao.modifyUserInfo(paramMap);
				}
			}
		}
		Map<String, Object> map = new HashMap<>();
	    map.put("result", result);
	    return map;
	}

	@Override
	public Map<String, Object> selectGlobalNotKeyword(Map<String, Object> paramMap) {
		paramMap.put("type", "GLOBAL_NOT_KEYWORD");
		String result = userBaseInfoDao.selectUserInfo(paramMap);
		Map<String, Object> map = new HashMap<>();
	    map.put("result", result);
	    return map;
	}

}
