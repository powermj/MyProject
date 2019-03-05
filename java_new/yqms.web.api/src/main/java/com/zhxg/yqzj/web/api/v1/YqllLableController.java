package com.zhxg.yqzj.web.api.v1;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.YqllLableInfo;
import com.zhxg.yqzj.service.v1.YqllLableService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/yqllLable")
public class YqllLableController extends BaseController<BaseEntity> {

    @Autowired
    private YqllLableService yqllLableService;
    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.yqllLableService;
    }

    /**
     * 获取当前客户行业下所有标签
     *
     * @throws SysException
     * @throws UserNoFoundException
     */
    @ResponseBody
    @RequestMapping(value = "/getAllLable", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "查询客户行业下所有标签", httpMethod = "GET", response = ApiResponseBody.class, notes = "getAllLable", produces = "application/json")
    public ApiResponseBody getAllLable(HttpServletRequest request)
            throws UserNoFoundException, SysException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        List<YqllLableInfo> allLableInfo = yqllLableService.getAllLableInfo(paramMap);
        return this.returnSuccess(allLableInfo);
    }
    
    /**
     * 获取当前客户行业下所有标签
     *
     * @throws SysException
     * @throws UserNoFoundException
     */
    @ResponseBody
    @RequestMapping(value = "/getRecommentLable", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "动态获取推荐标签", httpMethod = "GET", response = ApiResponseBody.class, notes = "getRecommentLable", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "eventWord", value = "事件关键词", required = true, paramType = "query", dataType = "String"),
    })
    public ApiResponseBody getRecommentLable(HttpServletRequest request,@RequestParam(value="eventWord",required=true) String eventWord)
            throws UserNoFoundException, SysException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("eventWord", eventWord);
        List<YqllLableInfo> recommentLable = yqllLableService.getRecommentLable(paramMap);
        return this.returnSuccess(recommentLable);
    }
    
}
