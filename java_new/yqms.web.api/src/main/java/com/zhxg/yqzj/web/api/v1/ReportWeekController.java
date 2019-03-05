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
@RequestMapping("/v1/weekReport")
public class ReportWeekController extends BaseController<BaseEntity>{
	
	@Autowired
	ReportWeekService reportweekService;

	@Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.reportweekService;
    }
	
	@ResponseBody
    @RequestMapping(value = "/getAllWeekReportTemplate", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取用户下所有周报模板", response = ApiResponseBody.class, notes = "getAllWeekReportTemplate", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "type", value = "2--周报", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "templateType", value = "1--word，2--excel", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getAllReportTemplate(HttpServletRequest request,
            @RequestParam(value="userid",required = true) String userid,
            @RequestParam(value="type",required = true) String type,
            @RequestParam(value="templateType",required = true) String templateType)
            throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        paramMap.put("type", type);
        paramMap.put("templateType", templateType);
        paramMap.put("receiveReportType", "WEEK_REPORT_ID");
        List<ReportTemplate> allWeekReportTemplate = this.reportweekService.getAllWeekReportTemplate(paramMap,8);
        return this.returnSuccess(allWeekReportTemplate);
    }

	
	@ResponseBody
    @RequestMapping(value = "/getUserWeekSubcribeStatus", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "获取用户周报订阅状态", response = ApiResponseBody.class, notes = "getUserWeekSubcribeStatus", produces = "application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getUserWeekSubcribeStatus(HttpServletRequest request,
            @RequestParam(value="userid",required = true) String userid)
            throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        paramMap.put("subcribeStatusType", "WEEK_SUBCRIBE_STATUS");
        int result = this.reportweekService.getUserWeekSubcribeStatus(paramMap);
        return this.returnSuccess(result);
    }
            
    @ResponseBody
    @RequestMapping(value = "/saveUserWeekSubcribeStatus", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "设置用户周报订阅状态", response = ApiResponseBody.class, notes = "saveUserWeekSubcribeStatus", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "status", value = "周报订阅状态 1--订阅 0--未订阅", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code=2003021,message="设置用户周报订阅状态失败")
    })
    public ApiResponseBody saveUserWeekSubcribeStatus(HttpServletRequest request,
            @RequestParam(value = "status", required = true)  String status,
            @RequestParam(value="userid",required = true) String userid)
            throws SysException, UserNoFoundException, ReportException{
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("status", status);
        paramMap.put("userid",userid);
        paramMap.put("subcribeStatusType","WEEK_SUBCRIBE_STATUS");
        int result = this.reportweekService.saveUserWeekSubcribeStatus(paramMap);
        return this.returnSuccess(result);
    }
	
	@ResponseBody
    @RequestMapping(value = "/getUserWeekReceiveTime", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "获取用户周报接收时间", response = ApiResponseBody.class, notes = "getUserWeekReceiveTime", produces = "application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getUserWeekReceiveTime(HttpServletRequest request,
            @RequestParam(value="userid",required = true) String userid)
            throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        paramMap.put("receiveTimeType", "WEEK_RECEIVE_TIME");
        String result = this.reportweekService.getUserWeekReceiveTime(paramMap);
        return this.returnSuccess(result);
    }
	
	@ResponseBody
    @RequestMapping(value = "/getUserWeekReceiveMail", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取用户周报接收邮箱", response = ApiResponseBody.class, notes = "getUserWeekReceiveMail", produces = "application/json")
    public ApiResponseBody getUserWeekReceiveMail(HttpServletRequest request,
            @RequestParam(value="userid",required = true) String userid)
            throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        paramMap.put("receiveMailType", "WEEK_REPORT_RECEIVEMAIL");
        List<UserMailExport> userWeekReceiveEmail = this.reportweekService.getUserWeekReceiveEmail(paramMap);
        return this.returnSuccess(userWeekReceiveEmail);
    }
	
	
	@ResponseBody
    @RequestMapping(value = "/saveUserWeekReceiveInfo", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "保存接收周报设置信息", response = ApiResponseBody.class, notes = "saveUserWeekReceiveInfo", produces = "application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "receiveTime", value = "接收周报时间 week,HH:mm", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "receiveMail", value = "接收周报邮箱ID，多个用 ,分割", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code=2003022,message="设置用户接收周报设置信息失败")
    })
    public ApiResponseBody saveUserWeekReceiveInfo(HttpServletRequest request,
            @RequestParam(value = "receiveTime", required = true)  String receiveTime,
            @RequestParam(value = "receiveMail", required = true)  String receiveMail,
            @RequestParam(value="userid",required = true) String userid)
            throws SysException, UserNoFoundException, ReportException{
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        paramMap.put("receiveTime", receiveTime);
        paramMap.put("receiveMail", receiveMail);
        paramMap.put("receiveTimeType", "WEEK_RECEIVE_TIME");
        paramMap.put("receiveMailType", "WEEK_REPORT_RECEIVEMAIL");
        int result = this.reportweekService.saveUserWeekReceiveInfo(paramMap);
        return this.returnSuccess(result);
    }
	
	@ResponseBody
    @RequestMapping(value = "/setUserWeekDefaultTemplate", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "保存周报默认模板", response = ApiResponseBody.class, notes = "setUserWeekDefaultTemplate", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "templateId", value = "周报默认模板id,一个用户  只存在一个默认模板", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code=2003023,message="保存用户周报默认模板失败")
    })
    public ApiResponseBody setUserWeekDefaultTemplate(HttpServletRequest request,
            @RequestParam(value = "templateId", required = true)  String templateId,
            @RequestParam(value="userid",required = true) String userid)
            throws SysException, UserNoFoundException, ReportException{
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("templateId", templateId);
        paramMap.put("userid", userid);
        paramMap.put("receiveReportType", "WEEK_REPORT_ID");
        int result = this.reportweekService.setUserWeekDefaultTemplate(paramMap);
        return this.returnSuccess(result);
    }
	
	@ResponseBody
    @RequestMapping(value = "/getUserWeekDefaultTemplate", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取周报默认模板", response = ApiResponseBody.class, notes = "getUserDefaultTemplate", produces = "application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getUserWeekDefaultTemplate(HttpServletRequest request,
            @RequestParam(value="userid",required = true) String userid)
            throws SysException, UserNoFoundException, ReportException{
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        paramMap.put("receiveReportType", "WEEK_REPORT_ID");
        String templateId = this.reportweekService.getUserWeekDefaultTemplate(paramMap);
        return this.returnSuccess(templateId);
    }
	
	
	@ResponseBody
	@RequestMapping(value = "/setUserWeekReportCondition", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "保存用户周报导出条件", response = ApiResponseBody.class, notes = "setUserWeekReportCondition", produces = "application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "reportCondition", value = "周报导出条件", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code=2003024,message="设置用户导出周报条件失败")
    })
    public ApiResponseBody setUserWeekReportCondition(HttpServletRequest request,
            @RequestParam(value = "reportCondition", required = true)  String reportCondition,
            @RequestParam(value="userid",required = true) String userid)
            throws SysException, UserNoFoundException, ReportException{
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        String condition = StringEscapeUtils.unescapeHtml4(reportCondition);
        paramMap.put("condition", condition);
        paramMap.put("reportConditionType", "WEEK_REPORT_CONDITION");
        int result = this.reportweekService.setUserWeekReportCondition(paramMap);
        return this.returnSuccess(result);
    }
	
	@ResponseBody
    @RequestMapping(value = "/getUserWeekReportCondition", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "获取用户周报导出条件", response = ApiResponseBody.class, notes = "getUserWeekReportCondition", produces = "application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getUserWeekReportCondition(HttpServletRequest request,
            @RequestParam(value="userid",required = true) String userid)
            throws SysException, UserNoFoundException{
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        paramMap.put("reportConditionType", "WEEK_REPORT_CONDITION");
        String weekCondition = this.reportweekService.getUserWeekReportCondition(paramMap);
        return this.returnSuccess(weekCondition);
    }
	
}