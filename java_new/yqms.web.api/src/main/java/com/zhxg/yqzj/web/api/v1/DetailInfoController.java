
package com.zhxg.yqzj.web.api.v1;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.ParameterException;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.service.v1.DetailInfoService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/detailInfo")
public class DetailInfoController extends BaseController<BaseEntity> {

    @Autowired
    DetailInfoService detailInfoService;
    
    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.detailInfoService;
    }

    @ResponseBody
    @RequestMapping(value = "/getYqllDetailInfo", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "获取舆情浏览详情页", httpMethod = "GET", response = ApiResponseBody.class, notes = "getYqllDetailInfo", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "krUuid", value = "事件id", required = true, paramType = "query", dataType = "String"),
    })
    public ApiResponseBody getYqllDetailInfo(HttpServletRequest request,@RequestParam(value = "krUuid",required = true) String krUuid) throws UserNoFoundException, SysException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("krUuid", krUuid);
        Map<String, Object> detailInfoMap = detailInfoService.getYqllDetailInfo(paramMap);
        return this.returnSuccess(detailInfoMap);
    }
    
    @ResponseBody
    @RequestMapping(value = "/updateYqllOrgiation", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "修改倾向性", httpMethod = "GET", response = ApiResponseBody.class, notes = "updateYqllOrgiation", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "krUuid", value = "事件id", required = true, paramType = "query", dataType = "String"),
    })
    public ApiResponseBody updateYqllOrgiation(HttpServletRequest request,@RequestParam(value = "krUuid",required = true) String krUuid,
            @RequestParam(value = "orientation",required = true) String orientation) throws UserNoFoundException, SysException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        if(StringUtils.isNotBlank(krUuid)){
            String[] krUuidArr = krUuid.split(",");
            paramMap.put("krUuidArr", krUuidArr);
        }else{
            throw new ParameterException();
        }
        paramMap.put("orientation", orientation);
        int result = detailInfoService.updateYqllOrgiation(paramMap);
        return this.returnSuccess(result);
    }
}
