package com.zhxg.yqzj.app.api.v1;

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
import com.zhxg.yqzj.entities.v1.ReportClassify;
import com.zhxg.yqzj.service.exception.DataReport.ReportClassifyDelException;
import com.zhxg.yqzj.service.exception.DataReport.ReportClassifyRepeatException;
import com.zhxg.yqzj.service.exception.DataReport.ReportClassifySaveException;
import com.zhxg.yqzj.service.exception.DataReport.ReportClassifyUpdateException;
import com.zhxg.yqzj.service.exception.mobilecloud.MobileCloudDelUserInfoException;
import com.zhxg.yqzj.service.v1.ReportClassifyService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/app/reportClassify")
public class ReportClassifyAppController extends BaseController<BaseEntity> {

    @Autowired
    private ReportClassifyService reportClassifyService;
    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.reportClassifyService;
    }

    /**
     * 查询推荐专题信息
     *
     * @throws SysException
     * @throws UserNoFoundException
     */
    @ResponseBody
    @RequestMapping(value = "/getReportClassifys", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "查询数据分类", httpMethod = "GET", response = ApiResponseBody.class, notes = "getReportClassifys", produces = "application/json")
    public ApiResponseBody getReportClassifys(HttpServletRequest request)
            throws UserNoFoundException, SysException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        List<ReportClassify> reportClassifys = reportClassifyService.getReportClassifys(paramMap);
        return this.returnSuccess(reportClassifys);
    }
    
    /**
     * 添加用户自定义数据分类
     *
     * @param request
     * @param classifyName
     * @return
     * @throws UserNoFoundException
     * @throws SysException
     * @throws ReportClassifySaveException 
     * @throws ReportClassifyRepeatException 
     */
    @ResponseBody
    @RequestMapping(value = "/setReportClassify", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "添加用户自定义数据分类", httpMethod = "GET", response = ApiResponseBody.class, notes = "setReportClassify", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "classifyName", value = "分类名称", required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code=2020001,message="添加用户自定义数据分类失败"),
            @ApiResponse(code=2020004,message="用户自定义数据分类名称重复")
    })
    public ApiResponseBody setReportClassify(HttpServletRequest request,
            @RequestParam(value = "classifyName", required = true)  String classifyName) throws UserNoFoundException, SysException, ReportClassifySaveException, ReportClassifyRepeatException{
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("classifyName",classifyName.trim());
        int result = reportClassifyService.insertReportClassify(paramMap);
        //保存专题
        return this.returnSuccess(result);
    }
    
    /**
     * 删除用户自定义数据分类 
     *
     * @param request
     * @return
     * @throws UserNoFoundException
     * @throws SysException
     * @throws MobileCloudDelUserInfoException
     * @throws ReportClassifyDelException 
     * @throws ReportClassifyRepeatException 
     */
    @ResponseBody
    @RequestMapping(value = "/deleteReportClassifyById", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "删除用户自定义数据分类", httpMethod = "GET", response = ApiResponseBody.class, notes = "deleteReportClassifyById", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "classifyId", value = "分类ID", required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code=2020002,message="删除用户自定义数据分类失败")
    })
    public ApiResponseBody deleteReportClassifyById(HttpServletRequest request,
            @RequestParam(value = "classifyId", required = true)  String classifyId)
            throws UserNoFoundException, SysException, MobileCloudDelUserInfoException, ReportClassifyDelException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("classifyId", classifyId);
        int result = reportClassifyService.deleteReportClassifyById(paramMap);
        return this.returnSuccess(result);
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/updateReportClassifyName", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "修改数据自定义数据分类", httpMethod = "GET", response = ApiResponseBody.class, notes = "updateReportClassifyName", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "classifyId", value = "分类ID", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "classifyName", value = "分类名称", required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code=2020003,message="修改用户自定义数据分类失败"),
            @ApiResponse(code=2020004,message="用户自定义数据分类名称重复")
    })
    public ApiResponseBody updateReportClassifyName(HttpServletRequest request,
            @RequestParam(value = "classifyId", required = true)  String classifyId,
            @RequestParam(value = "classifyName", required = true)  String classifyName)
            throws UserNoFoundException, SysException, MobileCloudDelUserInfoException, ReportClassifyUpdateException, ReportClassifyRepeatException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("classifyId", classifyId);
        paramMap.put("classifyName", classifyName.trim());
        int result = reportClassifyService.updateReportClassifyName(paramMap);
        return this.returnSuccess(result);
    }
}
