package com.zhxg.yqzj.web.api.v1;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.zhxg.framework.base.constants.SysConstants;
import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.framework.base.utils.HttpClientUtils;
import com.zhxg.framework.base.utils.HttpUtil;
import com.zhxg.framework.base.utils.RedisUtil;
import com.zhxg.sso.SsoManager;
import com.zhxg.yqzj.entities.v1.Account;
import com.zhxg.yqzj.service.exception.user.AccountNumberExceedingLimitException;
import com.zhxg.yqzj.service.exception.user.AccountUnavailableException;
import com.zhxg.yqzj.service.exception.user.CodeErrorException;
import com.zhxg.yqzj.service.exception.user.UserExpiredException;
import com.zhxg.yqzj.service.v1.LoginService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * CopyRright (c)2012-2016: haoran
 * <p>
 * Project: yqms.web.api
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户模块控制器
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.web.api.v1.UserController.java
 * <p>
 * Author: haoran
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
@RestController
@RequestMapping("/v1")
public class LoginController extends BaseController<Account> {

    @Autowired
    private LoginService loginService;
    
    @Autowired
    private SsoManager ssoManager;


    @Override
    protected BaseService<Account> getBaseService() {
        return this.loginService;
    }
    
    /**
     * 登录接口
     * @param consultation
     * @return
     * @throws UserNoFoundException
     * @throws AccountUnavailableException 
     */
    @ResponseBody
    @RequestMapping(value = "/ecloudLogin", method = RequestMethod.POST,consumes = "application/json")
    @ApiOperation(value = "模拟登录接口", httpMethod = "POST", response = ApiResponseBody.class, notes = "applyConsultation", produces = "application/json")
    public ApiResponseBody ecloudLogin(HttpServletRequest request) throws UserNoFoundException,UserExpiredException, AccountUnavailableException { 
        Map<String, String> params = new HashMap<>();
        params.put("random", Math.random()+"");
        params.put("grant_type", "password");
        params.put("client_id", "gclc-xgzk");
        params.put("client_secret", "FIJ539NV158F");
        params.put("username", "test_xgzk");
        params.put("password", "111111");
        String s= HttpUtil.post("http://oauth.lllnet.cn/sso/login", params);
        return this.returnSuccess(JSON.parse(s));
            
    }
    
    /**
     * 模拟加Cookie
     * @param consultation
     * @return
     * @throws UserNoFoundException
     * @throws AccountUnavailableException 
     */
    @ResponseBody
    @RequestMapping(value = "/ecloudAddCookie", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "模拟加Cookie", httpMethod = "GET", response = ApiResponseBody.class, notes = "applyConsultation", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "access_token", value = "access_token", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody addCookie(HttpServletResponse response,HttpServletRequest request,@RequestParam(value = "access_token", required = true)  String access_token) throws UserNoFoundException,UserExpiredException, AccountUnavailableException { 
        String s= HttpClientUtils.ecloudGet("http://xgzk.lllnet.cn/cookie/addCookie?&callback=&access_token="+access_token);
        return this.returnSuccess(JSON.parse(s));
            
    }
    
    /**
     * 登录接口
     * @param consultation
     * @return
     * @throws UserNoFoundException
     * @throws AccountUnavailableException 
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST,consumes = "application/json")
    @ApiOperation(value = "登录接口", httpMethod = "POST", response = ApiResponseBody.class, notes = "applyConsultation", produces = "application/json")
    public ApiResponseBody login(HttpServletRequest request) throws UserNoFoundException,UserExpiredException, AccountUnavailableException { 
		Account account = loginService.login(request);
		return this.returnSuccess(account);
    		
    }
    
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "登出接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "account logout", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "accountId", value = "账号ID", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "ssoAccountId", value = "认证中心账号ID", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getConsultationCount(HttpServletRequest request,@RequestParam(value = "accountId", required = true) String accountId
            ,@RequestParam(value = "ssoAccountId", required = true)  String ssoAccountId
            ) throws SysException, UserNoFoundException {
        //通知认证中心退出
        ssoManager.logOut(ssoAccountId, request);
        boolean result = false;
        //清除本地AccessToken
        Long l = RedisUtil.delete(SysConstants.ACCESSTOKEN_DB,SysConstants.PRODUCT_PREFIX + accountId);
        if(l>0){
            result = true;
        }else{
            result =  false;
        }
        return this.returnSuccess(result);
    }
    
    /**
     * 绑定手机号
     * @throws UserNoFoundException 
     * @throws CodeErrorException 
     * @throws SQLException 
     */
    @ResponseBody
    @RequestMapping(value = "/bindingCellPhoneNumber", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "绑定手机号接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "binding cell-phone number", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "loginName", value = "登录名", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "cellPhoneNumber", value = "手机号码", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "passWord", value = "密码", required = false, paramType = "query", dataType = "String")
    })
    public ApiResponseBody bindingCellPhoneNumber(HttpServletRequest request,@RequestParam(value = "loginName", required = true) String loginName
            ,@RequestParam(value = "cellPhoneNumber", required = true)  String cellPhoneNumber,@RequestParam(value = "passWord", required = false)  String passWord
            ) throws SysException, AccountNumberExceedingLimitException, UserNoFoundException {
        Map<String,Object> result = loginService.bindingCellPhoneNumber(loginName,cellPhoneNumber,passWord);
        return this.returnSuccess(result);
    }  
    
    /**
     * 解绑手机号
     * @throws UserNoFoundException 
     * @throws CodeErrorException 
     * @throws SQLException 
     */
    @ResponseBody
    @RequestMapping(value = "/unbindPhone", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "解绑手机号接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "unbind phone", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "telphone", value = "手机号码", required = true, paramType = "query", dataType = "String"),
    })
    public ApiResponseBody unbindPhone(HttpServletRequest request,
            @RequestParam(value = "userid",required = true) String userid,
            @RequestParam(value = "telphone", required = true) String telphone
            ) throws SysException,UserNoFoundException {
        String userId =userid;
        Map<String, Object> result = loginService.deleteTelphone(userId, telphone);
        if(result==null){
            return this.returnSuccess(null);
        }else{
            return this.returnStatus(result);
        }
    }   
}
