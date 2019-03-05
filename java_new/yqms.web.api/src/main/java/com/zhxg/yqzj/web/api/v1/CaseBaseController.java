package com.zhxg.yqzj.web.api.v1;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.zhxg.yqzj.entities.v1.CaseBaseClassify;
import com.zhxg.yqzj.entities.v1.CaseBaseInfo;
import com.zhxg.yqzj.service.exception.casebase.CasebaseDownloadTimesRunOutException;
import com.zhxg.yqzj.service.exception.casebase.DownloadCasebaseException;
import com.zhxg.yqzj.service.exception.expert.ApplyConsultationException;
import com.zhxg.yqzj.service.v1.CaseBaseInfoService;
import com.zhxg.yqzj.service.v1.CaseBaseService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqzj.web.api
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 案例库模块控制器
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqzj.web.api.v1.CaseBaseController.java
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
@RequestMapping("/v1/caseBase")
public class CaseBaseController extends BaseController<CaseBaseClassify> {

    @Autowired
    private CaseBaseService caseBaseService;
    
    @Autowired
    private CaseBaseInfoService caseBaseInfoService;


    @Override
    protected BaseService<CaseBaseClassify> getBaseService() {
        return this.caseBaseService;
    }


    
    
    /**
     * 获取案例库分类树
     * @return json
     * @throws SysException 
     * @throws UserNoFoundException 
     */
    @ResponseBody
    @RequestMapping(value = "/getCaseBaseClassificationTree", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取案例库分类树", httpMethod = "GET", response = ApiResponseBody.class, notes = "get caseBaseClassificationTree", produces = "application/json")
    public ApiResponseBody getcaseBaseClassificationTree(HttpServletRequest request) throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        List<CaseBaseClassify> caseBase = this.caseBaseService.getcaseBaseClassificationTree(paramMap);
        return this.returnSuccess(caseBase);
    }
    
    /**
     * 获取案例库列表页
     * @return json
     * @throws SysException 
     * @throws UserNoFoundException 
     */
    @ResponseBody
    @RequestMapping(value = "/getCaseBaseList", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取案例库列表页", httpMethod = "GET", response = ApiResponseBody.class, notes = "get caseBaseList", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "userid"),
        @ApiImplicitParam(name = "pageSize", value = "每页大小", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "pageNum", value = "当前页数", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "classId", value = "分类id", required = false, paramType = "query", dataType = "classId"),
        @ApiImplicitParam(name = "searchWord", value = "搜索词", required = false, paramType = "query", dataType = "searchWord")
    })
    public ApiResponseBody getCaseBaseList(HttpServletRequest request,@RequestParam(value = "userid", required = false)  String userid,
            @RequestParam(value = "classId", required = false)  String classId,
            @RequestParam(value = "searchWord", required = false)  String searchWord) throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        paramMap.put("classId", classId);
        paramMap.put("searchWord", searchWord);
        PageInfo<CaseBaseInfo> pageInfo = this.caseBaseInfoService.getCaseBaseList(paramMap, PageUtil.getPageInfo(request));
        return this.returnSuccess(pageInfo);
    }
    
    
    /**
     * 获取案例库详情页
     * @return json
     * @throws SysException 
     * @throws UserNoFoundException 
     */
    @ResponseBody
    @RequestMapping(value = "/getCaseBaseInfoDetail", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取案例库详情页", httpMethod = "GET", response = ApiResponseBody.class, notes = "get caseBaseInfoDetail", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "userid"),
        @ApiImplicitParam(name = "infoid", value = "案例信息id", required = true, paramType = "query", dataType = "String")
        
    })
    public ApiResponseBody getCaseBaseInfoDetail(HttpServletRequest request,@RequestParam(value = "userid", required = false)  String userid,@RequestParam(value = "infoid", required = true)  String infoid) throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("infoid", infoid);
        paramMap.put("userid", userid);
        List<CaseBaseInfo> caseBaseInfo = this.caseBaseInfoService.getCaseBaseInfoDetail(paramMap);
        return this.returnSuccess(caseBaseInfo);
    }
    
    
    /**
     * 获取已下载案例列表页
     * @return json
     * @throws SysException 
     * @throws UserNoFoundException 
     */
    @ResponseBody
    @RequestMapping(value = "/getLoadReportsList", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取已下载案例列表页", httpMethod = "GET", response = ApiResponseBody.class, notes = "get loadReportsList", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageSize", value = "每页大小", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "pageNum", value = "当前页数", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getLoadReportsList(HttpServletRequest request,@RequestParam(value = "userid", required = true)  String userid
    		) throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        PageInfo<CaseBaseInfo> pageInfo = this.caseBaseInfoService.getLoadReportsList(paramMap, PageUtil.getPageInfo(request));
        return this.returnSuccess(pageInfo);
    }
    
    
    
    /**
     * 下载案例库报告
     * @return
     * @throws UserNoFoundException
     * @throws SysException
     * @throws ApplyConsultationException
     * @throws CasebaseDownloadTimesRunOutException 
     * @throws DownloadCasebaseException 
     */
    @ResponseBody
    @RequestMapping(value = "/downloadCaseBaseReport", method = RequestMethod.POST,consumes = "application/json")
    @ApiOperation(value = "下载案例库报告", httpMethod = "POST", response = ApiResponseBody.class, notes = "downloadCaseBaseReport", produces = "application/json")
    public ApiResponseBody downloadCaseBaseReport(@RequestBody CaseBaseInfo caseBaseInfo) throws UserNoFoundException, SysException, ApplyConsultationException, CasebaseDownloadTimesRunOutException, DownloadCasebaseException { 
        int result = caseBaseInfoService.downloadCaseBaseReport(caseBaseInfo);
        if(result>0){
            return this.returnSuccess(null);
        }else{
            throw new DownloadCasebaseException();
        }
    }
    
    
    /**
     * 获取已下载数及总数
     * @return json
     * @throws SysException 
     * @throws UserNoFoundException 
     */
    @ResponseBody
    @RequestMapping(value = "/getCaseBaseReportCount", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取已下载数及总数", httpMethod = "GET", response = ApiResponseBody.class, notes = "get consultation count", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getCaseBaseReportCount(HttpServletRequest request,@RequestParam(value = "userid", required = true)  String userid
            ) throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        Map<String,Integer> result = caseBaseInfoService.getCaseBaseReportCount(paramMap);
        return this.returnSuccess(result);
    }
}
