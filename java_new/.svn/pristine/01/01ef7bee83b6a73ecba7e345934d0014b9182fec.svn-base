package com.zhxg.yqzj.app.api.v1;

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
import com.zhxg.yqzj.service.exception.DataReport.WarningReportInfoSaveException;
import com.zhxg.yqzj.service.v1.WarningReportInfoService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/app/warningReport")
public class WarningReportInfoAppController extends BaseController<BaseEntity> {

    @Autowired
    private WarningReportInfoService warningReportInfoService;
    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.warningReportInfoService;
    }

    /**
     * 添加用户自定义数据分类
     *
     * @param request
     * @param krUuids
     * @return
     * @throws UserNoFoundException
     * @throws SysException
     * @throws WarningReportInfoSaveException 
     */
    @ResponseBody
    @RequestMapping(value = "/saveWarningToReport", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "添加预警信息到数据池", httpMethod = "GET", response = ApiResponseBody.class, notes = "saveSubjectToReport", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ksUuids", value = "信息Id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "classifyId", value = "分类Id", required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code=2021002,message="推送预警添加数据到数据池失败")
    })
    public ApiResponseBody saveWarningToReport(HttpServletRequest request,
            @RequestParam(value = "ksUuids", required = true)  String ksUuids,
            @RequestParam(value = "classifyId", required = true)  String classifyId
            ) throws UserNoFoundException, SysException, WarningReportInfoSaveException{
        Map<String, Object> paramMap = this.getUserInfo(request);
        String[] ksUuidArr = null;
        if(StringUtils.isNotBlank(ksUuids)){
            ksUuidArr = ksUuids.split(",");
        }else{
            throw new ParameterException("信息id不能为空"); 
        }
        paramMap.put("ksUuidArr",ksUuidArr);
        paramMap.put("classifyId",classifyId);
        paramMap.put("ksUuids",ksUuids);
        Map<String, Integer> resultMap = warningReportInfoService.saveWarningToReport(paramMap);
        //保存专题
        return this.returnSuccess(resultMap);
    }
    
}
