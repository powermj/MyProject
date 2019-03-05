package com.zhxg.yqzj.app.api.v1;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.zhxg.yqzj.service.v1.OverseasService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/app/activeMedia")
public class OverseaAppController extends BaseController<BaseEntity> {

    @Autowired
    OverseasService overseasService;
    
    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.overseasService;
    }

    @ResponseBody
    @RequestMapping(value = "/getOverseaWebsite", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "获取境外网站列表", httpMethod = "GET", response = ApiResponseBody.class, notes = "getOverseaWebsite", produces = "application/json")
    public ApiResponseBody getAllSourceType(HttpServletRequest request) throws UserNoFoundException, SysException{
        Map<String, List<Map<String, String>>> OverseasWebsiteList = overseasService.getOverseasWebsite();
        return this.returnSuccess(OverseasWebsiteList);
    }
    
}
