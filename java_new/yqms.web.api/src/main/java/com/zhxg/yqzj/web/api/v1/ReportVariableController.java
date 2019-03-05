package com.zhxg.yqzj.web.api.v1;

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
import com.zhxg.yqzj.entities.v1.ReportVariable;
import com.zhxg.yqzj.service.exception.DataReport.ReportClassifyRepeatException;
import com.zhxg.yqzj.service.exception.DataReport.ReportVariableDelException;
import com.zhxg.yqzj.service.exception.DataReport.ReportVariableSaveException;
import com.zhxg.yqzj.service.exception.DataReport.ReportVariableUpdateException;
import com.zhxg.yqzj.service.exception.mobilecloud.MobileCloudDelUserInfoException;
import com.zhxg.yqzj.service.v1.ReportVariableService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/reportVariable")
public class ReportVariableController extends BaseController<BaseEntity> {

    @Autowired
    private ReportVariableService reportVariableService;
    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.reportVariableService;
    }

  
    @ResponseBody
    @RequestMapping(value = "/getReportVariable", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "查询用户变量", httpMethod = "GET", response = ApiResponseBody.class, notes = "getReportVariable", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "initType", value = "周期类型", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getReportVariable(HttpServletRequest request,
            @RequestParam(value = "initType", required = true) String initType)
            throws UserNoFoundException, SysException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("initType", initType);
        ReportVariable reportVariable = reportVariableService.getReportVariable(paramMap);
        return this.returnSuccess(reportVariable);
    }
    
   
    @ResponseBody
    @RequestMapping(value = "/setReportVariable", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "添加用户自定义周期变量", httpMethod = "GET", response = ApiResponseBody.class, notes = "setReportVariable", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "initType", value = "周期类型", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "initValue", value = "初始周期数", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "initTotal", value = "初始总周期数", required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code=2022001,message="添加用户自定义周期变量失败"),
            @ApiResponse(code=2022004,message="用户自定义周期变量重复添加")
    })
    public ApiResponseBody setReportVariable(HttpServletRequest request,
            @RequestParam(value = "initType", required = true)  String initType,
            @RequestParam(value = "initValue", required = true)  String initValue,
            @RequestParam(value = "initTotal", required = true)  String initTotal
            ) throws UserNoFoundException, SysException, ReportVariableSaveException, ReportClassifyRepeatException{
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("initType",initType);
        paramMap.put("initValue",initValue);
        paramMap.put("initTotal",initTotal);
        int result = reportVariableService.insertReportVariable(paramMap);
        //保存专题
        return this.returnSuccess(result);
    }
    
   
    @ResponseBody
    @RequestMapping(value = "/deleteReportVariableById", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "删除用户周期变量", httpMethod = "GET", response = ApiResponseBody.class, notes = "deleteReportVariableById", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "变量ID", required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code=2022002,message="删除用户自定义数据分类失败")
    })
    public ApiResponseBody deleteReportVariableById(HttpServletRequest request,
            @RequestParam(value = "id", required = true)  String id)
            throws UserNoFoundException, SysException, MobileCloudDelUserInfoException, ReportVariableDelException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("id", id);
        int result = reportVariableService.deleteReportVariableById(paramMap);
        return this.returnSuccess(result);
    }
    
   
    @ResponseBody
    @RequestMapping(value = "/updateReportVariable", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "修改用户周期变量", httpMethod = "GET", response = ApiResponseBody.class, notes = "updateReportVariable", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "变量Id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "initValue", value = "周期初始值", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "initTotal", value = "周期总初始值", required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code=2022003,message="修改用户自定义周期变量失败")
    })
    public ApiResponseBody updateReportVariable(HttpServletRequest request,
            @RequestParam(value = "id", required = true)  String id,
            @RequestParam(value = "initValue", required = true)  String initValue,
            @RequestParam(value = "initTotal", required = true)  String initTotal)
            throws UserNoFoundException, SysException, MobileCloudDelUserInfoException, ReportVariableUpdateException{
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("id", id);
        paramMap.put("initValue", initValue);
        paramMap.put("initTotal", initTotal);
        int result = reportVariableService.updateReportVariable(paramMap);
        return this.returnSuccess(result);
    }
}
