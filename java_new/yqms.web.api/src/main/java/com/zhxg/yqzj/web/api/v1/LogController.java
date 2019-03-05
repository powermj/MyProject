package com.zhxg.yqzj.web.api.v1;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.service.v1.LogService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/log")
public class LogController extends BaseController<BaseEntity> {

    @Autowired
    LogService logService;
    
    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.logService;
    }
    
    @ResponseBody
    @RequestMapping(value = "saveCopyLink", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "保存复制链接的信息", httpMethod = "POST", response = ApiResponseBody.class, notes = "saveCopyLink", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid", value = "uuid", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "userid", value = "userid", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "eventId", value = "事件分析id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "infoType", value = "1 专题 、2预警", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "operationUserIp", value = "操作人IP", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "operationUserId", value = "操作人id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "operationUserName", value = "操作人名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "functionType", value = "功能类型   1复制链接 2复制信息", required = true, paramType = "query", dataType = "Integer")
    })
    public ApiResponseBody saveCopyLink(HttpServletRequest request, @RequestBody Map<String, Object> map)
            throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.putAll(map);
        this.logService.saveCopyLink(paramMap);
        return this.returnSuccess(null);
    }
    
}
