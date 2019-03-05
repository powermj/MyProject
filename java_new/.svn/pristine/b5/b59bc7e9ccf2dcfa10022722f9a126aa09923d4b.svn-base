package com.zhxg.yqzj.web.api.v1;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.WeChatUser;
import com.zhxg.yqzj.service.v1.WeChatService;

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
@RequestMapping("/v1/wechat")
public class WeChatController extends BaseController<BaseEntity> {

    @Autowired
    private WeChatService weChatService;


    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.weChatService;
    }
    
    @RequestMapping(value = "/getQrcode", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "获取二维码", httpMethod = "GET", response = ApiResponseBody.class, notes = "subscribe", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "账号ID", required = true, paramType = "query", dataType = "String"),
    })
    public ApiResponseBody getQrcode(HttpServletRequest request,@RequestParam(value = "userid", required = true)  String userid) throws SysException { 
        String url = weChatService.getQrcode(userid);
        return this.returnSuccess(url);
            
    }
    
    @RequestMapping(value = "/getWeChatList", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "获取关注列表", httpMethod = "GET", response = ApiResponseBody.class, notes = "subscribe", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "账号ID", required = true, paramType = "query", dataType = "String"),
    })
    public ApiResponseBody getWeChatList(HttpServletRequest request,@RequestParam(value = "userid", required = true)  String userid) throws SysException { 
        List<WeChatUser> list = weChatService.getWeChatList(userid);
        return this.returnSuccess(list);
            
    }
    
    @RequestMapping(value = "/getWeChatCount", method = {RequestMethod.GET,RequestMethod.POST},consumes = "application/json")
    @ApiOperation(value = "获取用户微信数量", httpMethod = "GET", response = ApiResponseBody.class, notes = "subscribe", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "账号ID", required = true, paramType = "query", dataType = "String"),
    })
    public ApiResponseBody getWeChatCount(HttpServletRequest request,@RequestParam(value = "userid", required = true)  String userid) throws SysException { 
        Map<String,Object> result = weChatService.getWechatCount(userid);
        return this.returnSuccess(result);
            
    }
    
    @RequestMapping(value = "/getQQCount", method = {RequestMethod.GET,RequestMethod.POST},consumes = "application/json")
    @ApiOperation(value = "获取用户QQ数量", httpMethod = "GET", response = ApiResponseBody.class, notes = "subscribe", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "账号ID", required = true, paramType = "query", dataType = "String"),
    })
    public ApiResponseBody getQQCount(HttpServletRequest request,@RequestParam(value = "userid", required = true)  String userid) throws SysException { 
        Map<String,Object> result = weChatService.getQQCount(userid);
        return this.returnSuccess(result);
            
    }
    
}
