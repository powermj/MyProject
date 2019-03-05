package com.zhxg.yqzj.service.impl.v1;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhxg.framework.base.constants.SysConstants;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.framework.base.utils.HttpUtil;
import com.zhxg.framework.base.utils.Md5Utils;
import com.zhxg.framework.base.utils.PropertiesUtil;
import com.zhxg.framework.base.utils.RedisUtil;
import com.zhxg.framework.base.utils.UUIDUtils;
import com.zhxg.sso.SsoManager;
import com.zhxg.yqzj.dao.v1.AccountDao;
import com.zhxg.yqzj.dao.v1.UserDao;
import com.zhxg.yqzj.entities.v1.Account;
import com.zhxg.yqzj.entities.v1.ManageUser;
import com.zhxg.yqzj.entities.v1.UserConfig;
import com.zhxg.yqzj.service.exception.user.AccountNumberExceedingLimitException;
import com.zhxg.yqzj.service.exception.user.AccountUnavailableException;
import com.zhxg.yqzj.service.exception.user.CodeErrorException;
import com.zhxg.yqzj.service.exception.user.UserExpiredException;
import com.zhxg.yqzj.service.v1.LoginService;


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
 * NameSpace: com.zhxg.yqms.service.impl.v1.LoginServiceImpl.java
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
public class LoginServiceImpl extends BaseServiceImpl<Account> implements LoginService {

    @Autowired
    private AccountDao accountDao;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private SsoManager ssoManager;
    
    private static final int STATE = 2;
    private static final int PERMITBINGDINGNUMBER = 30;
    private static final int TID = 8;
    private static final int ISIDENTITY = 2;
    private static final String PASSWORD = "111111";
    private static final String PRODUCT = "YQMS";
    private static final int PRODUCTTYPE = 2;
    private static final String ACCESSTOKEN = "a24c2147dbdb4fde881a83ccee62e256";
    private static final String Android = "aa=aa";
    
    
    
    
    
    @Override
    protected BaseDao<Account> getBaseDao() {
        return accountDao;
    }

	@Override
	public Account login(HttpServletRequest request) throws UserNoFoundException, UserExpiredException, AccountUnavailableException {
	  //通过认证中心获取ssoAccountID
        String ssoInfoStr = ssoManager.getSsoAccountId(request);
        JSONObject ssoInfo= JSON.parseObject(ssoInfoStr);
        String ssoAccountID = ssoInfo.getString("accountId");
        String appUrl = ssoInfo.getString("appUrl");
        String loginType = ssoInfo.getString("loginType");
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("accountId", ssoAccountID);
		Account loginAccount =accountDao.getAccount(params);
		if(loginAccount==null||loginAccount.getUser()==null){
		    
            throw new UserNoFoundException();
        }
		String accessToken = UUIDUtils.getUuid();
		if("MANAGER".equals(loginType)){
		    //后台登录 tonken另存
		    RedisUtil.setStr(SysConstants.ACCESSTOKEN_DB,SysConstants.PRODUCT_MANAGER_PREFIX+loginAccount.getAccountId(), accessToken);
	        RedisUtil.setExpire(SysConstants.ACCESSTOKEN_DB,SysConstants.PRODUCT_MANAGER_PREFIX+loginAccount.getAccountId(), SysConstants.ACCESSTOKEN_TIMEOUT_L);
		}else{
		    RedisUtil.setStr(SysConstants.ACCESSTOKEN_DB,SysConstants.PRODUCT_PREFIX+loginAccount.getAccountId(), accessToken);
	        RedisUtil.setExpire(SysConstants.ACCESSTOKEN_DB,SysConstants.PRODUCT_PREFIX+loginAccount.getAccountId(), SysConstants.ACCESSTOKEN_TIMEOUT_L);
		}
		
		loginAccount.setAccessToken(accessToken);
		loginAccount.getUser().setTemplate(loginAccount.getTemplate());//用账号模板替换掉客户模板
		if(loginAccount.getState()==0){
		    throw new AccountUnavailableException();//
		}
        //判断账号是否到期 获取专家到期时间和状态，默认为秘书到期时间和状态
        Calendar cl = Calendar.getInstance();
        cl.add(Calendar.DAY_OF_MONTH, -1);
        Date date = loginAccount.getUser().getExpirationTime();
        int state = loginAccount.getUser().getUserState();
        List<UserConfig> infoList = loginAccount.getUser().getConfigInfo();
        for(UserConfig config : infoList){
            if(config.getKey().equals("EXPERT_EXPIRE_TIME")){
                date = new Date(Long.parseLong(config.getValue())*1000);
            }
            if(config.getKey().equals("EXPERT_USER_STATE")){
                try {
                    state = Integer.parseInt(config.getValue());
                } catch (NumberFormatException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        if(date.before(cl.getTime())||state>=SysConstants.EXPIRED_USER_STATE){
            throw new UserExpiredException();
        }
        
        /**
         * 查询已关联账户
         */
        List<ManageUser> manageUserList=accountDao.getManageUserList(loginAccount.getUserid());
        if(manageUserList!=null&&manageUserList.size()>0){
            loginAccount.getUser().setManage(true);
            loginAccount.getUser().setManageUserList(manageUserList);            
        }else{
            loginAccount.getUser().setManage(false);
        }
        
        //更新最后登录时间
        loginAccount.setLoginTime(new Date());
        accountDao.updateAccount(loginAccount);
        //返回值添加appUrl
        loginAccount.setAppUrl(appUrl);
		return loginAccount;
	}
	

    @Override
    public Account loginApp(HttpServletRequest request) throws UserNoFoundException, UserExpiredException, AccountUnavailableException {
      //通过认证中心获取ssoAccountID
        String ssoInfoStr = ssoManager.getSsoAccountIdForPhone(request, "AM");
        JSONObject ssoInfo= JSON.parseObject(ssoInfoStr);
        String ssoAccountID = ssoInfo.getString("accountId");
        String appUrl = ssoInfo.getString("appUrl");
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("accountId", ssoAccountID);
        Account loginAccount =accountDao.getAccount(params);
        if(loginAccount==null||loginAccount.getUser()==null){
            throw new UserNoFoundException();
        }
        String accessToken = UUIDUtils.getUuid();
        RedisUtil.setStr(SysConstants.ACCESSTOKEN_DB,SysConstants.PRODUCT_APP_PREFIX+loginAccount.getAccountId(), accessToken);
        
        loginAccount.setAccessToken(accessToken);
        loginAccount.getUser().setTemplate(loginAccount.getTemplate());//用账号模板替换掉客户模板
//        if(loginAccount.getState()==0){
//            throw new AccountUnavailableException();//
//        }
//        //判断账号是否到期 获取专家到期时间和状态，默认为秘书到期时间和状态
//        Calendar cl = Calendar.getInstance();
//        cl.add(Calendar.DAY_OF_MONTH, -1);
//        Date date = loginAccount.getUser().getExpirationTime();
//        int state = loginAccount.getUser().getUserState();
//        List<UserConfig> infoList = loginAccount.getUser().getConfigInfo();
//        for(UserConfig config : infoList){
//            if(config.getKey().equals("EXPERT_EXPIRE_TIME")){
//                date = new Date(Long.parseLong(config.getValue())*1000);
//            }
//            if(config.getKey().equals("EXPERT_USER_STATE")){
//                try {
//                    state = Integer.parseInt(config.getValue());
//                } catch (NumberFormatException e) {
//                    logger.error(e.getMessage(), e);
//                }
//            }
//        }
//        if(date.before(cl.getTime())||state>=SysConstants.EXPIRED_USER_STATE){
//            throw new UserExpiredException();
//        }
        //更新最后登录时间
        loginAccount.setLoginTime(new Date());
        accountDao.updateAccount(loginAccount);
        //返回值添加appUrl
        loginAccount.setAppUrl(appUrl);
        
        //添加app主色调和主题图片       
        Map<String,Object> newParams = new HashMap<String, Object>();
        newParams.put("accountId", loginAccount.getAccountId());
        newParams.put("shareAccountId", "");      
        try {
            Map<String,Object> paramsnew = getUserInfoByUserIds(newParams);
            List<Map<String, Object>> list=accountDao.appUserThemeImage_self(paramsnew);
            if (list != null && list.size() > 0) {
                for (Map<String, Object> map : list) {
                    String KD_VALUE = (String) map.get("KD_VALUE");
                    if (KD_VALUE.contains("，，，")) {
                        String[] split = KD_VALUE.split("，，，");
                        loginAccount.setAppUserThemeImage(split[0]);
                        loginAccount.setAppUserThemeImagetime(split[1]);
                    } else {
                        loginAccount.setAppMainColour(KD_VALUE);
                    }

                }
            }
        } catch (SysException e) {
            logger.error(e.getMessage(), e);
        }  
        
        
        /**
         * 查询已关联账户
         */
        List<ManageUser> manageUserList=accountDao.getManageUserList(loginAccount.getUserid());
        if(manageUserList!=null&&manageUserList.size()>0){
            loginAccount.getUser().setManage(true);
            loginAccount.getUser().setManageUserList(manageUserList);            
        }else{
            loginAccount.getUser().setManage(false);
        }
        loginAccount.setAppUrl(appUrl);
        return loginAccount;
    }

    @Override
    public int applyForTrial(String mName, String tel, String title) {
        
        Map<String,Object> map=new HashMap<>();
        title=title.replace(Android, "");
        map.put("mName", mName);
        map.put("tel", tel);
        map.put("title", title);
        map.put("addtime", Integer.valueOf(String.valueOf(System.currentTimeMillis() / 1000)));
        map.put("tid", TID);
        map.put("source", STATE);
        List<Map<String,Object>> trialMessage=accountDao.getTrialMessage_official(map);//_official
        if(trialMessage!=null&&trialMessage.size()>0){
            return STATE;
        }
        int result=accountDao.applyForTrial_official(map);
        return result;
    }

    @Override
    public Map<String, Object> bindingCellPhoneNumber(String loginName, String cellPhoneNumber, String passWord) throws AccountNumberExceedingLimitException, UserNoFoundException{
      
        
//        Map<String, Object> telmap = new HashMap<>();
//        telmap.put("telphone", cellPhoneNumber);
//        telmap.put("smsCode", smsCode);
//        String verifyCodeMessge="";
//        //验证手机号
//        try {
//            verifyCodeMessge = HttpUtil.httpsPost(PropertiesUtil.getProperties("application.properties", "sso.server.url")+"/usercenter/YqmsAppController/verifyCode",  telmap, headsMap, "UTF-8");
//        } catch (SysException e) {
//            logger.error(e.getMessage(), e);
//        }
//        if(!"".equals(verifyCodeMessge)){
//            Map customer = (Map) JSONObject.parse(verifyCodeMessge);           
//            String status= customer.get("status")==null?"":customer.get("status").toString();
//            if(!"1".equals(status)){
//                throw new CodeErrorException();
//                }         
//         }
        
        Map<String,Integer> userIDMap=accountDao.getUserId(loginName);
        if(userIDMap==null){
            throw new UserNoFoundException(); 
        }
        //允许绑定个数
        Map<String,String> permitBindingNumberMap=accountDao.getPermitBindingNumber(Integer.parseInt(userIDMap.get("id")+""));
        //已经绑定个数
        int alreadyBindingNumber=accountDao.getAccountCountByUserId(Integer.parseInt(userIDMap.get("id")+""));
        //验证账号绑定个数
        int permitBindingNumber=PERMITBINGDINGNUMBER;
        if(permitBindingNumberMap!=null&&permitBindingNumberMap.size()>0){
            permitBindingNumber=Integer.parseInt(permitBindingNumberMap.get("permitNumber"));
        }
        if(alreadyBindingNumber>=permitBindingNumber){
            throw new AccountNumberExceedingLimitException();
        }
        
        // 查询用户设置的地域  行业  用户类型 
        Map<String,Object> userAreaMap=accountDao.getUserArea(Integer.parseInt(userIDMap.get("id")+""));
        Map<String,String> headsMap = new HashMap<>();
        headsMap.put("accessToken", ACCESSTOKEN);
        Map<String, Object> map = new HashMap<>();
        map.put("customerName", loginName);
        map.put("telphone", cellPhoneNumber);
        map.put("appKey", PRODUCT);
        map.put("clientType", "AM");
        map.put("niceName", loginName);
        map.put("customerType", userAreaMap.get("usergenre"));
        map.put("province", userAreaMap.get("provinceId"));
        map.put("city", userAreaMap.get("cityId"));
        map.put("county", userAreaMap.get("countyId"));
        map.put("accNumLimit", permitBindingNumber);
        map.put("password", passWord==null?"":passWord);
        Map<String,Object> industryMap=accountDao.getIndustry(Integer.parseInt(userAreaMap.get("industry")+""));
        if(industryMap!=null&&industryMap.size()>0){
            industryMap.get("pId");
            //一级行业
            map.put("industryTypeTop", industryMap.get("pId"));
            //二级行业
            map.put("industryTypeTwo", userAreaMap.get("industry"));
        }else{
            map.put("industryTypeTop", userAreaMap.get("industry"));
        }        
        String customermessge ="";
        //调创建客户账号接口
        try {
            customermessge = HttpUtil.httpsPost(PropertiesUtil.getProperties("application.properties", "sso.server.url")+"/usercenter/YqmsAppController/bindTelphone", map, headsMap, "UTF-8");
        } catch (SysException e) {
            logger.error(e.getMessage(), e);
        }     
        Map<String, Object> customerMap=new HashMap<>(); 
        if(!"".equals(customermessge)){
           Map customer = (Map) JSONObject.parse(customermessge);           
           String status=  (String) customer.get("status");
           if("1".equals(status)){
               customer = (Map) customer.get("result");
               customerMap = (Map) customer.get("data");                
           }else{
               return customer;
           }         
        }
        int result=1;
        if(customerMap!=null&&customerMap.size()>0){
            
            //向userbaseinfo表添加记录 SSO_YQMSCUSTOMER_ID
            int count=accountDao.getYqmsCountByCustomerId(userIDMap.get("id")+"");
            if(0==count){
                Map<String,Object> userMap= new HashMap<>();
                userMap.put("userId", userIDMap.get("id"));
                userMap.put("customerId", customerMap.get("customerId"));
                userMap.put("type", "SSO_YQMSCUSTOMER_ID");
                result = accountDao.saveCustomerId(userMap);  
            }
           
            //向ms_account表添加记录
            count=accountDao.getYqmsAccountCountByssoId(customerMap.get("accountId")+"");
            if(0==count){
                int maxOrder = accountDao.getMaxOrder(Integer.parseInt(userIDMap.get("id")+""));
                Account account = new Account();       
                account.setUserid(Integer.parseInt(userIDMap.get("id")+""));
                account.setSsoAccountId(Integer.parseInt(customerMap.get("accountId")+""));
                account.setName(loginName);
                account.setAccount(cellPhoneNumber);
                account.setPassword(PASSWORD);
                account.setState(Integer.parseInt(userAreaMap.get("status")+"")==2?0:1);
                account.setPhone(cellPhoneNumber);
                account.setTemplate(userAreaMap.get("template")+"");
                account.setIdentity(ISIDENTITY);
                account.setOrder(maxOrder+1);
                account.setProductType(PRODUCTTYPE);
                result = accountDao.saveAccount(account);   
            }                             
        }
        Map<String, Object> resultMap=new HashMap<>();
        resultMap.put("status", result);
        resultMap.put("token", customerMap.get("token"));
        resultMap.put("bindedApp", customerMap.get("bindedApp")); 
        return resultMap;       
    }

    @Override
    public void deletePhoneList(String macid) {
        accountDao.deletePhoneList(macid);
        
    }

    @Override
    public void deletePhoneMacid(String macid) {
        accountDao.deletePhoneMacid(macid);
        
    }

    @Override
    public Map<String, Object> getUserInfo(Map<String, Object> paramMap) throws CodeErrorException {
      String password=Md5Utils.encoding(paramMap.get("userid")+"_"+SysConstants.USER_TOKEN);
      if(!password.equals(paramMap.get("accessToken"))){
          throw new CodeErrorException();
      }
      return accountDao.getUserInfo(paramMap);
    }

    @Override
    public Map<String, Object> deleteTelphone(String userId, String telphone) {
        //1,通过userId 和telphone查询用户中心customerId 和  accountId;
        
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("userid", userId);
        Map<String,Object> customerIdMap = userDao.selectCustomerId(paramMap);
        if(customerIdMap==null||StringUtils.isEmpty(customerIdMap.get("customerId"))){
            Map<String, Object> result = new HashMap<String,Object>();
            result.put("result", "4001");
            result.put("message", "账号未绑定手机号");
            return result;
        }
        paramMap.put("telphone", telphone);
        Account account = accountDao.getAccountByPhone(paramMap);
        if(account==null||account.getSsoAccountId()==null||account.getSsoAccountId()==0){
            Map<String, Object> result = new HashMap<String,Object>();
            result.put("result", "4001");
            result.put("message", "账号未绑定手机号");
            return result;
        }
        
        Map<String,String> headsMap = new HashMap<>();
        headsMap.put("accessToken", ACCESSTOKEN);
        Map<String, Object> map = new HashMap<>();
        map.put("accountId", account.getSsoAccountId());
        map.put("customerId", customerIdMap.get("customerId"));
        map.put("product", PRODUCT);
        String resultMes = "";
        //调创建客户账号接口
        try {
            resultMes = HttpUtil.httpsPost(PropertiesUtil.getProperties("application.properties", "sso.server.url")+"/usercenter/SsoManagerController/deleteAccountApp", map, headsMap, "UTF-8");
        } catch (SysException e) {
            logger.error(e.getMessage(), e);
        } 
        //删除手机号
        if(!StringUtils.isEmpty(resultMes)){
            JSONObject json = JSONObject.parseObject(resultMes);
            if("1".equals(json.getString("status"))){
                Account param = new Account();
                param.setAccountId(account.getAccountId());
                accountDao.delAccountByAccountId(param);
            }else{
                Map<String, Object> result = new HashMap<String,Object>();
                result.put("result", json.getString("status"));
                result.put("message",json.getString("msg"));
                return result;
            }
        }       
        return null;
    }
}
