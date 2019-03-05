package com.zhxg.yqzj.web.api.v1;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
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
import com.zhxg.yqzj.entities.v1.ReportTemplate;
import com.zhxg.yqzj.entities.v1.UserMailExport;
import com.zhxg.yqzj.service.exception.report.ReportException;
import com.zhxg.yqzj.service.v1.ReportWeekService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqzj.web.api
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 首页模块控制器
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqzj.web.api.v1.IndexController.java
 * <p>
 * Author: azmiu
 * <p>
 * Create Date: 2017年8月24日
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
@RequestMapping("/v1/monthReport")
public class ReportMonthController extends BaseController<BaseEntity>{
	
	@Autowired
	ReportWeekService reportweekService;

	@Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.reportweekService;
    }
	
	@ResponseBody
    @RequestMapping(value = "/getAllMonthReportTemplate", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取用户下所有月报模板", response = ApiResponseBody.class, notes = "getAllMonthReportTemplate", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "type", value = "4--月报", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "templateType", value = "1--word，2--excel", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getAllMonthReportTemplate(HttpServletRequest request,
            @RequestParam(value="userid",required = true) String userid,
            @RequestParam(value="type",required = true) String type,
            @RequestParam(value="templateType",required = true) String templateType)
            throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        paramMap.put("type", type);
        paramMap.put("templateType", templateType);
        paramMap.put("receiveReportType", "MONTH_REPORT_ID");
        List<ReportTemplate> allMonthReportTemplate = this.reportweekService.getAllWeekReportTemplate(paramMap,9);
        return this.returnSuccess(allMonthReportTemplate);
    }

	
	@ResponseBody
    @RequestMapping(value = "/getUserMonthSubcribeStatus", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "获取用户月报订阅状态", response = ApiResponseBody.class, notes = "getUserMonthSubcribeStatus", produces = "application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getUserMonthSubcribeStatus(HttpServletRequest request,
            @RequestParam(value="userid",required = true) String userid)
            throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        paramMap.put("subcribeStatusType", "MONTH_SUBCRIBE_STATUS");
        int result = this.reportweekService.getUserWeekSubcribeStatus(paramMap);
        return this.returnSuccess(result);
    }
            
    @ResponseBody
    @RequestMapping(value = "/saveUserMonthSubcribeStatus", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "设置用户月报订阅状态", response = ApiResponseBody.class, notes = "saveUserMonthSubcribeStatus", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "status", value = "月报订阅状态 1--订阅 0--未订阅", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code=2003021,message="设置用户月报订阅状态失败")
    })
    public ApiResponseBody saveUserMonthSubcribeStatus(HttpServletRequest request,
            @RequestParam(value = "status", required = true)  String status,
            @RequestParam(value="userid",required = true) String userid)
            throws SysException, UserNoFoundException, ReportException{
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("status", status);
        paramMap.put("userid",userid);
        paramMap.put("subcribeStatusType", "MONTH_SUBCRIBE_STATUS");
        int result = this.reportweekService.saveUserWeekSubcribeStatus(paramMap);
        return this.returnSuccess(result);
    }
	
	@ResponseBody
    @RequestMapping(value = "/getUserMonthReceiveTime", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "获取用户月报接收时间", response = ApiResponseBody.class, notes = "getUserMonthReceiveTime", produces = "application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getUserMonthReceiveTime(HttpServletRequest request,
            @RequestParam(value="userid",required = true) String userid)
            throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        paramMap.put("receiveTimeType", "MONTH_RECEIVE_TIME");
        String result = this.reportweekService.getUserWeekReceiveTime(paramMap);
        return this.returnSuccess(result);
    }
	
	@ResponseBody
    @RequestMapping(value = "/getUserMonthReceiveMail", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取用户月报接收邮箱", response = ApiResponseBody.class, notes = "getUserMonthReceiveMail", produces = "application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getUserMonthReceiveMail(HttpServletRequest request,
            @RequestParam(value="userid",required = true) String userid)
            throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        paramMap.put("receiveMailType", "MONTH_REPORT_RECEIVEMAIL");
        List<UserMailExport> userMonthReceiveEmail = this.reportweekService.getUserWeekReceiveEmail(paramMap);
        return this.returnSuccess(userMonthReceiveEmail);
    }
	
	
	@ResponseBody
    @RequestMapping(value = "/saveUserMonthReceiveInfo", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "保存接收月报设置信息", response = ApiResponseBody.class, notes = "saveUserMonthReceiveInfo", produces = "application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "receiveTime", value = "接收月报时间 week,HH:mm", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "receiveMail", value = "接收月报邮箱ID，多个用 ,分割", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code=2003022,message="设置用户接收月报设置信息失败")
    })
    public ApiResponseBody saveUserMonthReceiveInfo(HttpServletRequest request,
            @RequestParam(value = "receiveTime", required = true)  String receiveTime,
            @RequestParam(value = "receiveMail", required = true)  String receiveMail,
            @RequestParam(value="userid",required = true) String userid)
            throws SysException, UserNoFoundException, ReportException{
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        paramMap.put("receiveTime", receiveTime);
        paramMap.put("receiveMail", receiveMail);
        paramMap.put("receiveTimeType", "MONTH_RECEIVE_TIME");
        paramMap.put("receiveMailType", "MONTH_REPORT_RECEIVEMAIL");
        int result = this.reportweekService.saveUserWeekReceiveInfo(paramMap);
        return this.returnSuccess(result);
    }
	
	@ResponseBody
    @RequestMapping(value = "/setUserMonthDefaultTemplate", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "保存月报默认模板", response = ApiResponseBody.class, notes = "setUserMonthDefaultTemplate", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "templateId", value = "月报默认模板id,一个用户  只存在一个默认模板", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code=2003023,message="保存用户月报默认模板失败")
    })
    public ApiResponseBody setUserMonthDefaultTemplate(HttpServletRequest request,
            @RequestParam(value = "templateId", required = true)  String templateId,
            @RequestParam(value="userid",required = true) String userid)
            throws SysException, UserNoFoundException, ReportException{
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("templateId", templateId);
        paramMap.put("userid", userid);
        paramMap.put("receiveReportType", "MONTH_REPORT_ID");
        int result = this.reportweekService.setUserWeekDefaultTemplate(paramMap);
        return this.returnSuccess(result);
    }
	
	@ResponseBody
    @RequestMapping(value = "/getUserMonthDefaultTemplate", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取月报默认模板", response = ApiResponseBody.class, notes = "getUserMonthDefaultTemplate", produces = "application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getUserMonthDefaultTemplate(HttpServletRequest request,
            @RequestParam(value="userid",required = true) String userid)
            throws SysException, UserNoFoundException, ReportException{
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        paramMap.put("receiveReportType", "MONTH_REPORT_ID");
        String templateId = this.reportweekService.getUserWeekDefaultTemplate(paramMap);
        return this.returnSuccess(templateId);
    }
	
	
	@ResponseBody
	@RequestMapping(value = "/setUserMonthReportCondition", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "保存用户月报导出条件", response = ApiResponseBody.class, notes = "setUserMonthReportCondition", produces = "application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "reportCondition", value = "月报导出条件", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code=2003024,message="设置用户导出月报条件失败")
    })
    public ApiResponseBody setUserMonthReportCondition(HttpServletRequest request,
            @RequestParam(value = "reportCondition", required = true)  String reportCondition,
            @RequestParam(value="userid",required = true) String userid)
            throws SysException, UserNoFoundException, ReportException{
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        String condition = StringEscapeUtils.unescapeHtml4(reportCondition);
        paramMap.put("condition", condition);
        paramMap.put("reportConditionType", "MONTH_REPORT_CONDITION");
        int result = this.reportweekService.setUserWeekReportCondition(paramMap);
        return this.returnSuccess(result);
    }
	
	@ResponseBody
    @RequestMapping(value = "/getUserMonthReportCondition", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "获取用户月报导出条件", response = ApiResponseBody.class, notes = "getUserMonthReportCondition", produces = "application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getUserMonthReportCondition(HttpServletRequest request,
            @RequestParam(value="userid",required = true) String userid)
            throws SysException, UserNoFoundException{
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        paramMap.put("reportConditionType", "MONTH_REPORT_CONDITION");
        String mothCondition = this.reportweekService.getUserWeekReportCondition(paramMap);
        return this.returnSuccess(mothCondition);
    }
	
}