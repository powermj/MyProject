package com.zhxg.yqzj.web.api.v1;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.constants.SysConstants;
import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.ParamsNullException;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.framework.base.utils.PageUtil;
import com.zhxg.framework.base.utils.ParamsUtil;
import com.zhxg.yqzj.entities.v1.Account;
import com.zhxg.yqzj.entities.v1.AccountVO;
import com.zhxg.yqzj.service.exception.user.AccountAlreadyExistsException;
import com.zhxg.yqzj.service.exception.user.AccountNumberExceedingLimitException;
import com.zhxg.yqzj.service.exception.user.AccountUnavailabilityException;
import com.zhxg.yqzj.service.exception.user.CreateWPAccountFailedException;
import com.zhxg.yqzj.service.exception.user.PasswordErrorException;
import com.zhxg.yqzj.service.exception.user.PermissionDeniedException;
import com.zhxg.yqzj.service.v1.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
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
@RestController
@RequestMapping("/v1/users")
public class UserController extends BaseController<BaseEntity> {

    @Autowired
    private UserService userService;
    private static final String USER_ID_IS_NULL = "登录的用户id不能为空";
    private static final String SORT_ACCOUNT_ID_IS_NULL = "排序的账号id不能为空";
    private static final String ORDER_IS_NULL = "排序值不能为空";


    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.userService;
    }
    
    /**
     * 添加账号
     * @param account
     * @return
     * @throws SysException 
     * @throws UserNoFoundException 
     * @throws AccountUnavailabilityException 
     * @throws AccountAlreadyExistsException 
     * @throws PermissionDeniedException 
     * @throws CreateWPAccountFailedException 
     * @throws AccountNumberExceedingLimitException 
     */
    @RequestMapping(value = "/addAccount", method = RequestMethod.POST,consumes = "application/json")
    @ApiOperation(value = "添加账号接口", httpMethod = "POST", response = ApiResponseBody.class, notes = "addAccount", produces = "application/json")
    public ApiResponseBody addAccount(HttpServletRequest request,@RequestBody Account account) throws SysException, UserNoFoundException,
            AccountAlreadyExistsException, AccountUnavailabilityException, PermissionDeniedException, CreateWPAccountFailedException, AccountNumberExceedingLimitException { 
        Map<String, Object> paramMap = getUserInfo(request);
        String accountId = paramMap.get(SysConstants._ACCOUNTID).toString();
        account.set_ACCOUNTID(accountId);
        userService.addAccount(account,request);
        return this.returnSuccess(null);
            
    }
    
    /**
     * 校验账号
     * @param account
     * @return
     * @throws SysException 
     * @throws UserNoFoundException 
     * @throws AccountAlreadyExistsException 
     * @throws AccountUnavailabilityException 
     */
    @RequestMapping(value = "/checkAccount", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "校验账号接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "addAccount", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "account", value = "账号登录名", required = true, paramType = "query", dataType = "String"),
    })
    public ApiResponseBody checkAccount(HttpServletRequest request,@RequestParam(value = "account", required = true)  String account) throws SysException, UserNoFoundException, AccountAlreadyExistsException, AccountUnavailabilityException { 
        Map<String, Object> paramMap = getUserInfo(request);
        String accountId = paramMap.get(SysConstants._ACCOUNTID).toString();
        Map<String, Object> data = userService.checkAccount(account,accountId,request);
        return this.returnSuccess(data);
            
    }
    
    
    
    /**
     * 获取账号列表
     * @param request
     * @return
     * @throws SysException
     * @throws UserNoFoundException
     */
    @ResponseBody
    @RequestMapping(value = "/getAccountList", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取账号列表", response = ApiResponseBody.class, notes = "get AccountList", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "searchWord", value = "搜索词", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "searchState", value = "搜索状态", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "pageSize", value = "每页大小", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "pageNum", value = "当前页数", required = false, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getAccountList(HttpServletRequest request,@RequestParam(value = "searchWord", required = false)  String searchWord
            ,@RequestParam(value = "searchState", required = false)  String searchState)
            throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("searchWord", searchWord);
        paramMap.put("searchState", searchState);
        PageInfo<Account> pageInfo = userService.getAccountList(paramMap, PageUtil.getPageInfo(request));
        return this.returnSuccess(pageInfo);
    }
    
    /**
     * 获取账号信息
     * @param request
     * @return
     * @throws SysException
     * @throws UserNoFoundException
     */
    @ResponseBody
    @RequestMapping(value = "/getAccountInfo", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取账号信息", response = ApiResponseBody.class, notes = "get Account info", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "accountId", value = "账号ID", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getAccountInfo(HttpServletRequest request,@RequestParam(value = "accountId", required = true)  String accountId)
            throws SysException, UserNoFoundException {
        Account account = userService.getAccountInfo(accountId);
        return this.returnSuccess(account);
    }
    
    /**
     * 添加账号
     * @param account
     * @return
     * @throws SysException 
     * @throws UserNoFoundException 
     * @throws AccountUnavailabilityException 
     * @throws AccountAlreadyExistsException 
     * @throws PermissionDeniedException 
     * @throws CreateWPAccountFailedException 
     */
    @RequestMapping(value = "/modifyAccount", method = RequestMethod.POST,consumes = "application/json")
    @ApiOperation(value = "修改账号接口", httpMethod = "POST", response = ApiResponseBody.class, notes = "addAccount", produces = "application/json")
    public ApiResponseBody modifyAccount(HttpServletRequest request,@RequestBody Account account) throws SysException, UserNoFoundException,
            AccountAlreadyExistsException, AccountUnavailabilityException, PermissionDeniedException, CreateWPAccountFailedException { 
        Map<String, Object> paramMap = getUserInfo(request);
        String accountId = paramMap.get(SysConstants._ACCOUNTID).toString();
        account.set_ACCOUNTID(accountId);
        userService.modifyAccount(account,request);
        return this.returnSuccess(null);
    }
    
    @ResponseBody
    @RequestMapping(value = "/sortAccount", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "人员排序", response = ApiResponseBody.class, notes = "post sortAccount", produces = "application/json")
    public ApiResponseBody addYqjb(@RequestBody Account account,HttpServletRequest request) throws UserNoFoundException, ParamsNullException,SysException{
        Map<String,Object> params = getUserInfo(request);
        int sortAccountId = account.getAccountId();//排序的账号id
        int order = account.getOrder();//排序值
        String userId = ParamsUtil.nullDeal(params, "_KUID", "");//当前登录的用户id
        
        if("".equals(userId)){//用户id不能为空
            throw new ParamsNullException(USER_ID_IS_NULL);
        }
        if(sortAccountId == 0){//排序的用户id不能为空
            throw new ParamsNullException(SORT_ACCOUNT_ID_IS_NULL);
        }
        if(order == 0){//排序值不能为空
            throw new ParamsNullException(ORDER_IS_NULL);
        }
        int rowsCount = userService.updateAccountOrder(account,params);
        return this.returnSuccess(rowsCount);
    }
    
    
    /**
     * @param request
     * @param params
     * @return
     * @throws SysException
     * @throws UserNoFoundException
     * @throws PasswordErrorException 
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST,consumes = "application/json")
    @ApiOperation(value = "修改密码接口", httpMethod = "POST", response = ApiResponseBody.class, notes = "changePassword", produces = "application/json")
    public ApiResponseBody changePassword(HttpServletRequest request,@RequestBody AccountVO params) throws SysException, UserNoFoundException, PasswordErrorException { 
        userService.changePassword(params,request);
        return this.returnSuccess(null);
    }
    
    /**
     * @param request
     * @param params
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/changeAccount", method = RequestMethod.POST,consumes = "application/json")
    @ApiOperation(value = "修改账号手机接口", httpMethod = "POST", response = ApiResponseBody.class, notes = "changeAccount", produces = "application/json")
    public ApiResponseBody changeAccount(HttpServletRequest request,@RequestBody AccountVO params) throws Exception { 
        userService.changeAccount(params,request); 
        return this.returnSuccess(null);
    }
    
    
    /**
     * @param request
     * @param params
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/sendCode", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "发送验证码接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "changePassword", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "phone", value = "账号ID", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody sendCode(HttpServletRequest request,@RequestParam(value = "phone", required = true)  String phone) throws Exception { 
        userService.sendCode(request,phone);
        return this.returnSuccess(null);
    }
    
    @RequestMapping(value = "/validatePassword", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "验证密码接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "validatePassword", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody validatePassword(HttpServletRequest request,@RequestParam(value = "password", required = true)  String password) throws Exception { 
        Map<String, Object> paramMap = getUserInfo(request);
        String accountId = paramMap.get(SysConstants._ACCOUNTID).toString();
        userService.validatePassword(request,accountId,password);
        return this.returnSuccess(null);
    }
    
    
    @RequestMapping(value = "/checkAlert", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "校验账号是否需要弹窗接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "addAccount", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "客户ID", required = true, paramType = "query", dataType = "String"),
    })
    public ApiResponseBody checkFirstLogin(HttpServletRequest request,@RequestParam(value = "userid", required = true)  String userid) throws SysException, UserNoFoundException, AccountAlreadyExistsException, AccountUnavailabilityException { 
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        Map<String, Object> data = userService.checkFirstLogin(paramMap);
        return this.returnSuccess(data);
            
    }
    
    @RequestMapping(value = "/confirmAlert", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "确认弹窗接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "addAccount", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "客户ID", required = true, paramType = "query", dataType = "String"),
    })
    public ApiResponseBody confirmAlert(HttpServletRequest request,@RequestParam(value = "userid", required = true)  String userid) throws SysException, UserNoFoundException, AccountAlreadyExistsException, AccountUnavailabilityException { 
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        Map<String, Object> data = userService.confirmAlert(paramMap);
        return this.returnSuccess(data);
            
    }
    
    @RequestMapping(value = "/copySubjectInfo", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "copySubjectInfo", httpMethod = "GET", response = ApiResponseBody.class, notes = "copySubjectInfo", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "客户ID", required = true, paramType = "query", dataType = "String"),
    })
    public ApiResponseBody copySubjectInfo(HttpServletRequest request,@RequestParam(value = "userid", required = true)  String userid) throws SysException, UserNoFoundException, AccountAlreadyExistsException, AccountUnavailabilityException { 
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        Map<String, Object> data = userService.copySubjectInfo(paramMap);
        return this.returnSuccess(data);
            
    }
    /**
     * 获取用户热词屏蔽词接口
     * @param request
     * @param userid
     * @return
     * @throws SysException
     * @throws UserNoFoundException
     * @throws AccountAlreadyExistsException
     * @throws AccountUnavailabilityException
     */
    @RequestMapping(value = "/getHotKeywordFilter", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "getHotKeywordFilter", httpMethod = "GET", response = ApiResponseBody.class, notes = "getHotKeywordFilter", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "客户ID", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getHotKeywordFilter(HttpServletRequest request,@RequestParam(value = "userid", required = true)  String userid) throws SysException, UserNoFoundException, AccountAlreadyExistsException, AccountUnavailabilityException { 
        Map<String, Object> paramMap = new HashMap<String,Object>();
        paramMap.put("userid", userid);
        Map<String, Object> data = userService.getHotKeywordFilter(paramMap);
        return this.returnSuccess(data);
            
    }
    
    /**
     * 保存用户热词屏蔽词接口
     * @param request
     * @param userid
     * @param hotKeyword
     * @return
     * @throws SysException
     * @throws UserNoFoundException
     * @throws AccountAlreadyExistsException
     * @throws AccountUnavailabilityException
     */
    @RequestMapping(value = "/saveHotKeywordFilter", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "saveHotKeywordFilter", httpMethod = "GET", response = ApiResponseBody.class, notes = "saveHotKeywordFilter", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "客户ID", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody saveHotKeywordFilter(HttpServletRequest request,
    		@RequestParam(value = "userid", required = true)  String userid,
    		@RequestParam(value = "hotKeyword", required = true)  String hotKeyword) throws SysException, UserNoFoundException, AccountAlreadyExistsException, AccountUnavailabilityException { 
        Map<String, Object> paramMap = new HashMap<String,Object>();
        paramMap.put("userid", userid);
        Map<String, Object> data = null;
        if(StringUtils.isEmpty(hotKeyword)){
        	data = userService.delHotKeywordFilter(paramMap);
        }else{
        	paramMap.put("hotKeyword", hotKeyword);
        	data = userService.addHotKeywordFilter(paramMap);
        }
        return this.returnSuccess(data);
            
    }
    
    /**
     * 获取全局否定词接口
     * @param request
     * @param userid
     * @return
     */
    @RequestMapping(value = "/getGlobalNotKeyword", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "getGlobalNotKeyword", httpMethod = "GET", response = ApiResponseBody.class, notes = "getGlobalNotKeyword", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "客户ID", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getGlobalNotKeyword(HttpServletRequest request,@RequestParam(value = "userid", required = true)  String userid) throws SysException, UserNoFoundException, AccountAlreadyExistsException, AccountUnavailabilityException { 
        Map<String, Object> paramMap = new HashMap<String,Object>();
        paramMap.put("userid", userid);
        Map<String, Object> data = userService.selectGlobalNotKeyword(paramMap);
        return this.returnSuccess(data);
            
    }
    
    /**
     * 添加,或者修改全局否定词
     * @param request
     * @param userid
     * @param notKeyword
     * @param operate   A append:  追加    O  over:覆盖
     * @return
     */
    @RequestMapping(value = "/saveGlobalNotKeyword", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "saveGlobalNotKeyword", httpMethod = "GET", response = ApiResponseBody.class, notes = "saveGlobalNotKeyword", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "客户ID", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody saveGlobalNotKeyword(HttpServletRequest request,
    		@RequestParam(value = "userid", required = true)  String userid,
    		@RequestParam(value = "notKeyword", required = true)  String notKeyword,
    		@RequestParam(value = "operate", required = true)  String operate
    		) throws SysException, UserNoFoundException, AccountAlreadyExistsException, AccountUnavailabilityException { 
    	if(!("A".equals(operate)||"O".equals(operate))){
    		operate = "O";
    	}
        Map<String, Object> paramMap = new HashMap<String,Object>();
        paramMap.put("userid", userid);
        paramMap.put("value", notKeyword);
        paramMap.put("operate", operate);
        Map<String, Object> data = userService.addGlobalNotKeyword(paramMap);
        return this.returnSuccess(data);
            
    }
    
}
