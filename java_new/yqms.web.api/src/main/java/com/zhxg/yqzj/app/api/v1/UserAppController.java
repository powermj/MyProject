package com.zhxg.yqzj.app.api.v1;

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
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.Account;
import com.zhxg.yqzj.service.exception.user.AccountAlreadyExistsException;
import com.zhxg.yqzj.service.exception.user.AccountUnavailabilityException;
import com.zhxg.yqzj.service.exception.user.CreateWPAccountFailedException;
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
 * 
 */
@RestController
@RequestMapping("/app/users")
public class UserAppController extends BaseController<BaseEntity> {

    @Autowired
    private UserService userService;


    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.userService;
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
        Account account = this.userService.getAccountInfo(accountId);
        return this.returnSuccess(account);
    }
    
    /**
     * 修改账号
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
        Map<String, Object> paramMap = this.getUserInfo(request);
        String accountId = paramMap.get(SysConstants._ACCOUNTID).toString();
        account.set_ACCOUNTID(accountId);
        int result=this.userService.modifyAccountApp(account,request);
        return this.returnSuccess(result);
    }
    
    @RequestMapping(value = "/copySubjectInfo", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "copySubjectInfo", httpMethod = "GET", response = ApiResponseBody.class, notes = "copySubjectInfo", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "客户ID", required = true, paramType = "query", dataType = "String"),
    })
    public ApiResponseBody copySubjectInfo(HttpServletRequest request,
            @RequestParam(value = "userid", required = true) String userid)
            throws SysException, UserNoFoundException, AccountAlreadyExistsException, AccountUnavailabilityException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("userid", userid);
        Map<String, Object> data = this.userService.copySubjectInfo(paramMap);
        return this.returnSuccess(data);

    }
    
}
