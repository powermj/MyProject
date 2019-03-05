package com.zhxg.yqzj.app.api.v1;


import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhxg.framework.base.constants.SysConstants;
import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.framework.base.utils.HttpUtil;
import com.zhxg.framework.base.utils.ParamsUtil;
import com.zhxg.framework.base.utils.PropertiesUtil;
import com.zhxg.framework.base.utils.RedisUtil;
import com.zhxg.yqzj.entities.v1.Account;
import com.zhxg.yqzj.service.exception.user.AccountNumberExceedingLimitException;
import com.zhxg.yqzj.service.exception.user.AccountUnavailableException;
import com.zhxg.yqzj.service.exception.user.CodeErrorException;
import com.zhxg.yqzj.service.exception.user.UserExpiredException;
import com.zhxg.yqzj.service.v1.LoginService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

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
@RequestMapping("/app")
public class LoginAppController extends BaseController<Account> {

    @Autowired
    private LoginService loginService;
    
    @Override
    protected BaseService<Account> getBaseService() {
        return this.loginService;
    }
    
    private static final String PRODUCTID = "1";
    
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
		Account account = loginService.loginApp(request);
		return this.returnSuccess(account);
    		
    }
    
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "退出接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "account logout", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "accountId", value = "账号ID", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "ssoAccountId", value = "认证中心账号ID", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "macid", value = "手机macid", required = false, paramType = "query", dataType = "String")
    })
    public ApiResponseBody logout(HttpServletRequest request,@RequestParam(value = "accountId", required = true) String accountId
            ,@RequestParam(value = "ssoAccountId", required = true)  String ssoAccountId
            ,@RequestParam(value = "userid", required = true)  String userid
            ,@RequestParam(value = "macid", required = false)  String macid
            ) throws SysException, UserNoFoundException {
        boolean result = false;
        //清除本地AccessToken
        Long l = RedisUtil.delete(SysConstants.ACCESSTOKEN_DB,SysConstants.PRODUCT_APP_PREFIX + accountId);
        //清除手机macid
        loginService.deletePhoneList(macid);
        loginService.deletePhoneMacid(macid);
        // 调郝然注销接口
        Map<String, Object> map = new HashMap<>();
        map.put("productid", PRODUCTID);
        map.put("userid", userid);
        map.put("macid", macid);
        map.put("session", "haoran");
        final JSONObject jsonstr = JSONObject.fromObject(map);

        new Thread() {
            @Override
            public void run() {
                try {
                    HttpUtil.post(PropertiesUtil.getProperties("application.properties", "push.server.url")+"/logoff", jsonstr.toString());
                } catch (SysException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        if(l>0){
            result = true;
        }else{
            result =  false;
        }
        return this.returnSuccess(result);
    }
    
    
    
    @ResponseBody
    @RequestMapping(value = "/logoff", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "注销推送接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "account logoff", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "macid", value = "手机macid", required = false, paramType = "query", dataType = "String")
    })
    public ApiResponseBody logoff(HttpServletRequest request
            ,@RequestParam(value = "userid", required = true)  String userid
            ,@RequestParam(value = "macid", required = false)  String macid
            ) throws SysException, UserNoFoundException {
        boolean result = true;
        //清除手机macid
        loginService.deletePhoneList(macid);
        loginService.deletePhoneMacid(macid);
        // 调郝然注销接口
        Map<String, Object> map = new HashMap<>();
        map.put("productid", PRODUCTID);
        map.put("userid", userid);
        map.put("macid", macid);
        map.put("session", "haoran");
        final JSONObject jsonstr = JSONObject.fromObject(map);

        new Thread() {
            @Override
            public void run() {
                try {
                    HttpUtil.post(PropertiesUtil.getProperties("application.properties", "push.server.url")+"/logoff", jsonstr.toString());
                } catch (SysException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return this.returnSuccess(result);
    }
    
    
    
    /**
     * 申请试用
     * @throws SQLException 
     * @throws UnsupportedEncodingException 
     */
    @ResponseBody
    @RequestMapping(value = "/applyForTrial", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "申请试用接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "apply for trial", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "mName", value = "姓名", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "tel", value = "手机号码", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "title", value = "单位名称", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody applyForTrial(HttpServletRequest request,@RequestParam(value = "mName", required = true) String mName
            ,@RequestParam(value = "tel", required = true)  String tel,@RequestParam(value = "title", required = true)  String title
            ) throws SysException, UserNoFoundException, SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        int result = loginService.applyForTrial(mName,tel,title);
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
     * 获取用户名密码
     * @throws CodeErrorException 
     */
    @ResponseBody
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "", httpMethod = "POST", response = ApiResponseBody.class, notes = "", produces = "application/json")
    public ApiResponseBody getUserInfo(HttpServletRequest request,@RequestBody Account account
            ) throws SysException, CodeErrorException  {
        Map<String, Object> paramMap = new HashMap<>();;
        paramMap.putAll(ParamsUtil.transToMAP(account,Account.class));
        Map<String,Object> result = loginService.getUserInfo(paramMap);
        return this.returnSuccess(result);
    }
    
}
