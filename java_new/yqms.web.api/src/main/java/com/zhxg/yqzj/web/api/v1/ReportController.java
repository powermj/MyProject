package com.zhxg.yqzj.web.api.v1;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.framework.base.utils.PageUtil;
import com.zhxg.framework.base.utils.ParamsUtil;
import com.zhxg.yqzj.entities.v1.ExportReportCondition;
import com.zhxg.yqzj.entities.v1.Report;
import com.zhxg.yqzj.entities.v1.ReportFileInfo;
import com.zhxg.yqzj.entities.v1.ReportTemplate;
import com.zhxg.yqzj.entities.v1.SubRelation;
import com.zhxg.yqzj.entities.v1.UserMailExport;
import com.zhxg.yqzj.service.exception.DataReport.ReportClassifyDelException;
import com.zhxg.yqzj.service.exception.DataReport.ReportClassifyRepeatException;
import com.zhxg.yqzj.service.exception.DataReport.ReportClassifySaveException;
import com.zhxg.yqzj.service.exception.DataReport.ReportClassifyUpdateException;
import com.zhxg.yqzj.service.exception.expert.ApplyConsultationException;
import com.zhxg.yqzj.service.exception.report.ApplyExpertReportException;
import com.zhxg.yqzj.service.exception.report.ReportException;
import com.zhxg.yqzj.service.report.ReportChartComponent;
import com.zhxg.yqzj.service.v1.ReportService;

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
@RequestMapping("/v1/report")
public class ReportController extends BaseController<Report>{
	
	@Autowired
	ReportService reportService;

	@Override
    protected BaseService<Report> getBaseService() {
        return this.reportService;
    }
	
	@ResponseBody
    @RequestMapping(value = "/getChartType", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取简报模板图表类型", response = ApiResponseBody.class, notes = "getReportTemplate", produces = "application/json")
    public ApiResponseBody getChartType(HttpServletRequest request) {
        return this.returnSuccess(ReportChartComponent.getChartType());
    }
	
	@ResponseBody
    @RequestMapping(value = "/getAllReportTemplate", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取用户下所有简报模板", response = ApiResponseBody.class, notes = "getAllReportTemplate", produces = "application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "type", value = "1--日报，2--周报，3--简报，4--月报，5--年报", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "templateType", value = "1--word，2--excel", required = false, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getAllReportTemplate(HttpServletRequest request,
            @RequestParam(value="type",required = false) String type,
            @RequestParam(value="templateType",required = false) String templateType)
            throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("type", type == null ? 3:type);
        paramMap.put("templateType", templateType == null ? 1:templateType);
        List<ReportTemplate> allReportTemplate = this.reportService.getAllReportTemplate(paramMap);
        return this.returnSuccess(allReportTemplate);
    }
	
	
	@ResponseBody
    @RequestMapping(value = "/getReportTemplate", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取简报模板", response = ApiResponseBody.class, notes = "getReportTemplate", produces = "application/json")
    public ApiResponseBody getReportTemplate(HttpServletRequest request,@RequestParam(value = "templateId", required = true)  String templateId)
            throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("templateId", templateId);
        ReportTemplate remplate = this.reportService.getReportTemplate(paramMap);
        return this.returnSuccess(remplate);
    }
	
	@ResponseBody
    @RequestMapping(value = "/updateReportTemplate", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "修改简报模板", response = ApiResponseBody.class, notes = "updateReportTemplate", produces = "application/json")
	@ApiResponses(value = {
            @ApiResponse(code=2020003,message="修改简报模板失败")
    })
    public ApiResponseBody updateReportTemplate(HttpServletRequest request,
            @RequestBody Map<String,Object> templateMap)
            throws SysException, UserNoFoundException, ReportClassifyUpdateException, ReportClassifyRepeatException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("templateId", templateMap.get("templateId"));
        paramMap.put("type", templateMap.get("type"));
        paramMap.put("templateName", templateMap.get("templateName"));
        paramMap.put("templateImg", templateMap.get("templateImg"));
        paramMap.put("templateType", templateMap.get("templateType"));
        String templateString = StringEscapeUtils.unescapeHtml4(templateMap.get("template").toString()).replace("&nbsp;", " ");
        //String jsonString = JSONObject.toJSONString(templateMap.get("template"));
        paramMap.put("template", templateString);
        int result = this.reportService.updateReportTemplate(paramMap);
        return this.returnSuccess(result);
    }
	
	@ResponseBody
    @RequestMapping(value = "/deleteReportTemplate", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "删除简报模板", response = ApiResponseBody.class, notes = "deleteReportTemplate", produces = "application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "templateId", value = "模板ID，多个逗号分割", required = true, paramType = "query", dataType = "String"),
    })
	@ApiResponses(value = {
            @ApiResponse(code=2020002,message="删除简报模板类失败")
    })
    public ApiResponseBody deleteReportTemplate(HttpServletRequest request,
            @RequestParam(value = "templateId", required = true)  String templateId)
            throws SysException, UserNoFoundException, ReportClassifyDelException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("templateIdArr", templateId.split(","));
        int result = this.reportService.deleteReportTemplate(paramMap);
        return this.returnSuccess(result);
    }
	
	
	@ResponseBody
    @RequestMapping(value = "/getReportFileInfo", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取简报文件信息", response = ApiResponseBody.class, notes = "getReportFileInfo", produces = "application/json")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "enterStartTime", value = "报告入库开始时间", required = false, paramType = "query", dataType = "String"),
	    @ApiImplicitParam(name = "enterEndTime", value = "报告入库结束时间", required = false, paramType = "query", dataType = "String"),
	    @ApiImplicitParam(name = "reportType", value = "报告类型 1--日报，2--周报，3--简报，4--月报，5--年报", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = false, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getReportFileInfo(HttpServletRequest request,
            @RequestParam(value = "enterStartTime",required = false) String enterStartTime,
            @RequestParam(value = "enterEndTime",required = false) String enterEndTime,
            @RequestParam(value = "reportType",required = false) String reportType)
            throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("enterStartTime", enterStartTime);
        paramMap.put("enterEndTime", enterEndTime);
        paramMap.put("reportType",reportType == null ? "3":reportType);
        PageInfo<ReportFileInfo> reportFileInfos = this.reportService.getReportFileInfos(paramMap, PageUtil.getPageInfo(request));
        return this.returnSuccess(reportFileInfos);
    }
	
	@ResponseBody
    @RequestMapping(value = "/updateReportFileInfo", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "修改简报文件信息", response = ApiResponseBody.class, notes = "updateReportFileInfo", produces = "application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "reportFileId", value = "报告id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "reportFileName", value = "报告名称", required = true, paramType = "query", dataType = "String")
    })
	@ApiResponses(value = {
            @ApiResponse(code=2020003,message="修改用户自定义数据分类失败")
    })
    public ApiResponseBody updateReportFileInfo(HttpServletRequest request,
            @RequestParam(value = "reportFileId", required = true)  String reportFileId,
            @RequestParam(value = "reportFileName", required = true)  String reportFileName)
            throws SysException, UserNoFoundException, ReportClassifyUpdateException{
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("reportFileId", reportFileId);
        paramMap.put("reportFileName", reportFileName);
        int result = this.reportService.updateReportFileInfo(paramMap);
        return this.returnSuccess(result);
    }
	
	@ResponseBody
    @RequestMapping(value = "/deleteReportFileInfo", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "删除简报文件信息", response = ApiResponseBody.class, notes = "deleteReportFileInfo", produces = "application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "reportFileId", value = "报告id，多个,分割", required = true, paramType = "query", dataType = "String")
    })
	@ApiResponses(value = {
            @ApiResponse(code=2020002,message="删除简报文件信息失败")
    })
    public ApiResponseBody deleteReportFileInfo(HttpServletRequest request,
            @RequestParam(value = "reportFileId", required = true)  String reportFileId)
            throws SysException, UserNoFoundException, ReportClassifyDelException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("reportFileIdArr", reportFileId.split(","));
        int result = this.reportService.deleteReportFileInfo(paramMap);
        return this.returnSuccess(result);
    }
	
	@ResponseBody
    @RequestMapping(value = "/exportReport", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "导出简报", response = ApiResponseBody.class, notes = "exportReport", produces = "application/json")
	@ApiResponses(value = {
            @ApiResponse(code=2020001,message="添加报告文件信息失败"),
            @ApiResponse(code=2020003,message="更新报告期数失败")
    })
    public ApiResponseBody exportReport(HttpServletRequest request, @RequestBody ExportReportCondition condition)
            throws SysException, UserNoFoundException, ReportClassifySaveException, ReportClassifyUpdateException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.putAll(ParamsUtil.transToMAP(condition,ExportReportCondition.class));
        int result = this.reportService.exportReport(paramMap);
        return this.returnSuccess(result);
    }
	
	
	@ResponseBody
    @RequestMapping(value = "/saveReportTemplate", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "保存简报模板", response = ApiResponseBody.class, notes = "saveReportTemplate", produces = "application/json")
	@ApiResponses(value = {
            @ApiResponse(code=2020001,message="添加报告模板失败")
    })
    public ApiResponseBody saveReportTemplate(HttpServletRequest request,@RequestBody Map<String,Object> templateMap)
            throws SysException, UserNoFoundException, ReportClassifySaveException, ReportClassifyRepeatException{
	    int result = 0;
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("type", templateMap.get("type"));
        paramMap.put("initType", templateMap.get("type"));
        paramMap.put("templateName", templateMap.get("templateName"));
        paramMap.put("templateImg", templateMap.get("templateImg"));
        paramMap.put("templateType", templateMap.get("templateType")==null?"1":templateMap.get("templateType"));
        String templateString = StringEscapeUtils.unescapeHtml4(templateMap.get("template").toString()).replace("&nbsp;", " ");
        //String jsonString = JSONObject.toJSONString(templateMap.get("template"));
        paramMap.put("template", templateString);
        if(Integer.valueOf(paramMap.get("templateType")+"")==2){
            result = this.reportService.inserTemplateExcel(paramMap);
        }else{
            result = this.reportService.insertReportTemplate(paramMap);
        }
        return this.returnSuccess(result);
    }
	
	
	@ResponseBody
    @RequestMapping(value = "/getExpertReportTreeList", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取报告分类树", response = ApiResponseBody.class, notes = "get expertReportTreeList", produces = "application/json")
	   @ApiImplicitParams({
           @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String")
   })
    public ApiResponseBody getExpertReportTreeList(HttpServletRequest request)
            throws SysException, UserNoFoundException {
		Map<String, Object> paramMap = getUserInfo(request);
    	List<Report> list = this.reportService.getExpertReportTreeList(paramMap);
        return this.returnSuccess(list);
    }
	
	
	@ResponseBody
    @RequestMapping(value = "/getExpertReportList", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取报告列表", response = ApiResponseBody.class, notes = "get expertReportList", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "classid", value = "报告分类id", required = false, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getExpertReportList(HttpServletRequest request,@RequestParam(value = "userid", required = true)  String userid,
            @RequestParam(value = "classid", required = false)  String classid)
            throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        paramMap.put("classid", classid);
        PageInfo<Report> pageInfo = this.reportService.getExpertReportList(paramMap, PageUtil.getPageInfo(request));
        return this.returnSuccess(pageInfo);
    }
	
	@ResponseBody
    @RequestMapping(value = "/getExpertReportCount", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取报告条数", response = ApiResponseBody.class, notes = "reportTypeId:用户类型ID,total:购买数量,count:已消耗条数", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String"),
    })
    public ApiResponseBody getExpertReportCount(HttpServletRequest request,@RequestParam(value = "userid", required = true)  String userid)
            throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        List<Map<String, Integer>> result = this.reportService.getExpertReportCount(paramMap);
        return this.returnSuccess(result);
    }
	
	@ResponseBody
    @RequestMapping(value = "/applyExpertReport", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "申请专家报告", response = ApiResponseBody.class, notes = "申请专家报告", produces = "application/json")
    public ApiResponseBody applyExpertReport(@RequestBody Report report)
            throws SysException, UserNoFoundException, ApplyConsultationException, ApplyExpertReportException {
        int result = this.reportService.applyExpertReport(report);
        if(result > 0){
        	return this.returnSuccess(null);
        }else{
        	throw new ApplyExpertReportException();
        }
    }
	
	@ResponseBody
    @RequestMapping(value = "/getIsMyExpertReport", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "验证报告是否是该用户的", response = ApiResponseBody.class, notes = "get isMyExpertReport", produces = "application/json")
	@ApiImplicitParams({
         @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String"),
         @ApiImplicitParam(name = "reportName", value = "报告名称", required = true, paramType = "query", dataType = "String")
     })
	public ApiResponseBody getIsMyExpertReport(HttpServletRequest request,@RequestParam(value = "userid", required = true)  String userid,
	        @RequestParam(value = "reportName", required = true)  String reportName)
            throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        paramMap.put("reportName", reportName);
        Map<String, String> map= this.reportService.getIsMyExpertReport(paramMap);
        
        return this.returnSuccess(map);
    }
	
	@ResponseBody
    @RequestMapping(value = "/batchDownloadFile", method = RequestMethod.GET, produces = "application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "filePath", value = "文件路径，多个,分割", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody batchDownloadFile(HttpServletRequest request,@RequestParam(value = "filePath",required = true) String filePath)
            throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("filePath", filePath);
        String zipPath = this.reportService.batchDownloadFile(paramMap);
        return this.returnSuccess(zipPath);
    }

	
	@ResponseBody
    @RequestMapping(value = "/getUserSubcribeStatus", method = RequestMethod.GET, produces = "application/json")
    public ApiResponseBody getUserSubcribeStatus(HttpServletRequest request)
            throws SysException, UserNoFoundException, ReportClassifyUpdateException {
        Map<String, Object> paramMap = getUserInfo(request);
        int result = this.reportService.getUserSubcribeStatus(paramMap);
        return this.returnSuccess(result);
    }
	
	@ResponseBody
    @RequestMapping(value = "/updateUserSubcribeStatus", method = RequestMethod.GET, produces = "application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "status", value = "1 开，0 关", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody updateUserSubcribeStatus(HttpServletRequest request,@RequestParam(value = "status",required = true) String status)
            throws SysException, UserNoFoundException, ReportClassifyUpdateException, ReportException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("status", status);
        int result = this.reportService.updateUserSubcribeStatus(paramMap);
        return this.returnSuccess(result);
    }
	
	
	@ResponseBody
    @RequestMapping(value = "/getUserReceiveTime", method = RequestMethod.GET, produces = "application/json")
    public ApiResponseBody getUserReceiveTime(HttpServletRequest request)
            throws SysException, UserNoFoundException, ReportClassifyUpdateException {
        Map<String, Object> paramMap = getUserInfo(request);
        String result = this.reportService.getUserReceiveTime(paramMap);
        return this.returnSuccess(result);
    }
	
	
	@ResponseBody
    @RequestMapping(value = "/saveUserReceiveInfo", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "保存接收日报信息", response = ApiResponseBody.class, notes = "saveUserReceiveInfo", produces = "application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "receiveTime", value = "接收日报时间", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "receiveMail", value = "接收日报邮箱ID，多个用 ,分割", required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code=2003010,message="设置用户接收日报信息失败")
    })
    public ApiResponseBody saveUserReceiveInfo(HttpServletRequest request,
            @RequestParam(value = "receiveTime", required = true)  String receiveTime,
            @RequestParam(value = "receiveMail", required = true)  String receiveMail)
            throws SysException, UserNoFoundException, ReportException{
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("receiveTime", receiveTime);
        paramMap.put("receiveMail", receiveMail);
        int result = this.reportService.saveUserReceiveInfo(paramMap);
        return this.returnSuccess(result);
    }
	
	@ResponseBody
    @RequestMapping(value = "/setUserDefaultTemplate", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "保存日报默认模板", response = ApiResponseBody.class, notes = "saveUserReceiveInfo", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "templateId", value = "日报默认模板id,一个用户只存在一个默认模板", required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code=2003011,message="设置用户接收日报信息失败")
    })
    public ApiResponseBody setUserDefaultTemplate(HttpServletRequest request,
            @RequestParam(value = "templateId", required = true)  String templateId)
            throws SysException, UserNoFoundException, ReportException{
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("templateId", templateId);
        int result = this.reportService.setUserDefaultTemplate(paramMap);
        return this.returnSuccess(result);
    }
	
	@ResponseBody
    @RequestMapping(value = "/getUserDefaultTemplate", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取日报默认模板", response = ApiResponseBody.class, notes = "getUserDefaultTemplate", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code=2003011,message="设置用户接收日报信息失败")
    })
    public ApiResponseBody getUserDefaultTemplate(HttpServletRequest request)
            throws SysException, UserNoFoundException, ReportException{
        Map<String, Object> paramMap = getUserInfo(request);
        String templateId = this.reportService.getUserDefaultTemplate(paramMap);
        return this.returnSuccess(templateId);
    }
	
	
	@ResponseBody
    @RequestMapping(value = "/getUserReceiveEmail", method = RequestMethod.GET, produces = "application/json")
    public ApiResponseBody getUserReceiveEmail(HttpServletRequest request)
            throws SysException, UserNoFoundException{
        Map<String, Object> paramMap = getUserInfo(request);
        List<UserMailExport> userEmail = this.reportService.getUserReceiveEmail(paramMap);
        return this.returnSuccess(userEmail);
    }
	
	@ResponseBody
    @RequestMapping(value = "/getUserSubRelationList", method = RequestMethod.GET, produces = "application/json")
    public ApiResponseBody getUserSubRelationList(HttpServletRequest request)
            throws SysException, UserNoFoundException{
        Map<String, Object> paramMap = getUserInfo(request);
        List<SubRelation> userTopicList = this.reportService.getUserTopicList(paramMap);
        return this.returnSuccess(userTopicList);
    }
	
	
	
	
	@ResponseBody
    @RequestMapping(value = "/setUserReportCondition", method = RequestMethod.GET, produces = "application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "reportCondition", value = "日报导出条件", required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code=2003012,message="设置用户接收查询日报信息条件失败")
    })
    public ApiResponseBody setUserReportCondition(HttpServletRequest request,
            @RequestParam(value = "reportCondition", required = true)  String reportCondition)
            throws SysException, UserNoFoundException, ReportException{
        Map<String, Object> paramMap = getUserInfo(request);
        String condition = StringEscapeUtils.unescapeHtml4(reportCondition);
        paramMap.put("condition", condition);
        int result = this.reportService.setUserReportCondition(paramMap);
        return this.returnSuccess(result);
    }
	
	@ResponseBody
    @RequestMapping(value = "/getUserReportCondition", method = RequestMethod.GET, produces = "application/json")
    public ApiResponseBody getUserReportCondition(HttpServletRequest request)
            throws SysException, UserNoFoundException{
        Map<String, Object> paramMap = getUserInfo(request);
        String dayCondition = this.reportService.getUserReportCondition(paramMap);
        return this.returnSuccess(dayCondition);
    }
	
}
