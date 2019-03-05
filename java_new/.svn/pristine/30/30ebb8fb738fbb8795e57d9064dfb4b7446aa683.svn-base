package com.zhxg.yqzj.web.api.v1;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.ParameterException;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.framework.base.utils.PageUtil;
import com.zhxg.framework.base.utils.ParamsUtil;
import com.zhxg.yqzj.entities.v1.AllReportData;
import com.zhxg.yqzj.service.exception.DataReport.DeleteReportInfoException;
import com.zhxg.yqzj.service.exception.DataReport.InsertReportInfoException;
import com.zhxg.yqzj.service.exception.DataReport.UpdateReportInfoException;
import com.zhxg.yqzj.service.v1.AllReportDataService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/reportData")
public class AllReportDataController extends BaseController<BaseEntity> {

    @Autowired
    AllReportDataService allReportDataService;
    
    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.allReportDataService;
    }

    
    @ResponseBody
    @RequestMapping(value = "/getAllReports", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "简报数据池列表", httpMethod = "GET", response = ApiResponseBody.class, notes = "getAllReports", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "sourceTypes", value = "媒体类型", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "orientations", value = "倾向性", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "classifyIds", value = "信息分类", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "isExport", value = "是否入报", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "orderType", value = "排序方式", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "publishTimeType", value = "发布时间范围", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "publishBeginTime", value = "发布自定义起始时间", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "publishEndTime", value = "发布自定义结束时间", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "enterTimeType", value = "入报时间范围", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "enterBeginTime", value = "入报开始时间", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "enterEndTime", value = "入报结束时间", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = false, paramType = "query", dataType = "String")
        
    })
    public ApiResponseBody getAllReports(HttpServletRequest request,
            @RequestParam(value = "sourceTypes", required = false)  String sourceTypes,
            @RequestParam(value = "orientations", required = false)  String orientations,
            @RequestParam(value = "classifyIds", required = false)  String classifyIds,
            @RequestParam(value = "isExport", required = false)  String isExport,
            @RequestParam(value = "publishTimeType", required = false)  String publishTimeType,
            @RequestParam(value = "publishBeginTime", required = false)  String publishBeginTime,
            @RequestParam(value = "publishEndTime", required = false)  String publishEndTime,
            @RequestParam(value = "enterTimeType", required = false)  String enterTimeType,
            @RequestParam(value = "enterBeginTime", required = false)  String enterBeginTime,
            @RequestParam(value = "enterEndTime", required = false)  String enterEndTime,
            @RequestParam(value = "orderType", required = false)  String orderType
            ) throws UserNoFoundException, SysException { 
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("sourceTypes", sourceTypes);
        paramMap.put("orientations", orientations);
        paramMap.put("classifyIds", classifyIds);
        paramMap.put("isExport", isExport);
        paramMap.put("publishTimeType", publishTimeType);
        paramMap.put("publishBeginTime", publishBeginTime);
        paramMap.put("publishEndTime", publishEndTime);
        paramMap.put("enterTimeType", enterTimeType);
        paramMap.put("enterBeginTime", enterBeginTime);
        paramMap.put("enterEndTime", enterEndTime);
        paramMap.put("orderType", orderType);
        PageInfo<AllReportData> allReportsList = allReportDataService.getAllReports(paramMap, PageUtil.getPageInfo(request));
        return this.returnSuccess(allReportsList);
            
    }
    
    @ResponseBody
    @RequestMapping(value = "/updateReportOrientations", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "更改报告信息倾向性", httpMethod = "GET", response = ApiResponseBody.class, notes = "updateReportOrientations", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "orientation", value = "倾向性", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "infoUuids", value = "信息ID", required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses(value = {
                 @ApiResponse(code=2022001,message = "修改信息倾向性失败")
    })
    public ApiResponseBody updateReportOrientations(HttpServletRequest request,
            @RequestParam(value = "orientation", required = true) String orientation,
            @RequestParam(value = "infoUuids", required = true) String infoUuids) throws UserNoFoundException, SysException, UpdateReportInfoException { 
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("orientation", orientation);
        String[] infoUuidArr = null;
        if(StringUtils.isNotBlank(infoUuids)){
            infoUuidArr = infoUuids.split(",");
        }else{
            throw new ParameterException("信息id不能为空"); 
        }
        paramMap.put("infoUuidArr", infoUuidArr);
        int result = allReportDataService.updateReportOrientations(paramMap);
        return this.returnSuccess(result);
            
    }
    
    @ResponseBody
    @RequestMapping(value = "/updateReportClassifyIds", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "更改报告信息分类", httpMethod = "GET", response = ApiResponseBody.class, notes = "updateReportClassifyIds", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "classifyId", value = "分类ID", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "infoUuids", value = "信息ID", required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code=2022001,message = "修改信息分类失败")
    })
    public ApiResponseBody updateReportClassifyIds(HttpServletRequest request,
            @RequestParam(value = "classifyId", required = true) String classifyId,
            @RequestParam(value = "infoUuids", required = true) String infoUuids) throws UserNoFoundException, SysException, UpdateReportInfoException { 
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("classifyId", classifyId);
        String[] infoUuidArr = null;
        if(StringUtils.isNotBlank(infoUuids)){
            infoUuidArr = infoUuids.split(",");
        }else{
            throw new ParameterException("信息id不能为空"); 
        }
        paramMap.put("infoUuidArr", infoUuidArr);
        int result = allReportDataService.updateReportClassifyIds(paramMap);
        return this.returnSuccess(result);
            
    }
    
    @ResponseBody
    @RequestMapping(value = "/deleteReportInfo", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "删除报告信息", httpMethod = "GET", response = ApiResponseBody.class, notes = "deleteReportInfo", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "infoUuids", value = "信息ID", required = true, paramType = "query", dataType = "String"),
    })
    @ApiResponses(value = {
            @ApiResponse(code=2022002,message = "删除信息失败")
    })
    public ApiResponseBody deleteReportInfo(HttpServletRequest request,
            @RequestParam(value = "infoUuids", required = false) String infoUuids) throws UserNoFoundException, SysException, DeleteReportInfoException { 
        Map<String, Object> paramMap = this.getUserInfo(request);
        String[] infoUuidArr = null;
        if(StringUtils.isNotBlank(infoUuids)){
            infoUuidArr = infoUuids.split(",");
        }else{
            throw new ParameterException("信息id不能为空"); 
        }
        paramMap.put("infoUuidArr", infoUuidArr);
        int result = allReportDataService.deleteReportInfo(paramMap);
        return this.returnSuccess(result);
            
    }
    
    @ResponseBody
    @RequestMapping(value = "/insertReportBySelf", method = RequestMethod.POST,consumes = "application/json")
    @ApiOperation(value = "手动添加报告信息", response = ApiResponseBody.class, notes = "post insertReportBySelf", produces = "application/json")
    @ApiResponses(value={
            @ApiResponse(code=2022003,message="手动添加报告信息失败")
    })
    public ApiResponseBody insertReportBySelf(HttpServletRequest request,
            @RequestBody AllReportData reportInfo) throws UserNoFoundException, SysException, InsertReportInfoException { 
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.putAll(ParamsUtil.transToMAP(reportInfo, AllReportData.class));
        int result = allReportDataService.insertReportBySelf(paramMap);
        return this.returnSuccess(result);
            
    }
    
    @ResponseBody
    @RequestMapping(value = "/getReportByClassify", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "查询分类下是否存在信息", httpMethod = "GET", response = ApiResponseBody.class, notes = "getReportByClassify", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "classifyId", value = "分类ID", required = true, paramType = "query", dataType = "String"),
    })
    public ApiResponseBody getReportByClassify(HttpServletRequest request,
            @RequestParam(value = "classifyId", required = false) String classifyId) throws UserNoFoundException, SysException, DeleteReportInfoException { 
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("classifyId", classifyId);
        int result = allReportDataService.getReportByClassify(paramMap);
        return this.returnSuccess(result);
            
    }
    
    @ResponseBody
    @RequestMapping(value = "/getInfoDetail", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "获取信息详情", httpMethod = "GET", response = ApiResponseBody.class, notes = "getInfoDetail", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "infoUuid", value = "信息ID", required = true, paramType = "query", dataType = "String"),
    })
    public ApiResponseBody getInfoDetail(HttpServletRequest request,
            @RequestParam(value = "infoUuid", required = true) String infoUuid) throws UserNoFoundException, SysException{ 
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("infoUuid", infoUuid);
        AllReportData allReportData = allReportDataService.getInfoDetail(paramMap);
        return this.returnSuccess(allReportData);
            
    }
}
