package com.zhxg.yqzj.web.api.v1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.framework.base.utils.PageUtil;
import com.zhxg.yqzj.entities.v1.Index;
import com.zhxg.yqzj.entities.v1.Report;
import com.zhxg.yqzj.entities.v1.Warning;
import com.zhxg.yqzj.service.v1.IndexService;
import com.zhxg.yqzj.service.v1.ReportService;
import com.zhxg.yqzj.service.v1.WarningService;

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
@RequestMapping("/v1/index")
public class IndexController extends BaseController<Index>{

	@Autowired
	IndexService indexServic;
	
	@Autowired
	WarningService warningService;
	
	@Autowired
	ReportService reportService;

	@Override
    protected BaseService<Index> getBaseService() {
        return this.indexServic;
    }
	
	@ResponseBody
    @RequestMapping(value = "/statistics/{userid}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "根据用户ID获取首页统计信息", response = ApiResponseBody.class, notes = "get indexPage statistics by userid", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "userid", required = true, paramType = "path", dataType = "String"),
    })
    public ApiResponseBody getIndexStatistics(HttpServletRequest request,@PathVariable("userid") String userid)
            throws SysException, UserNoFoundException {
	    Map<String, Object> paramMap = getUserInfo(request);
	    paramMap.put("userid", userid);
        Index index = null;
        try {
        	index = this.indexServic.getIndexStatistics(paramMap);
        } catch (SysException se) {
            throw se;
        }
        return this.returnSuccess(index);
    }
	
	@ResponseBody
    @RequestMapping(value = "/waringList/{userid}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "根据用户ID获取预警信息列表", response = ApiResponseBody.class, notes = "get indexWaringList by userid", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "userid", required = true, paramType = "path", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", required = false, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getIndexWaringList(HttpServletRequest request,@PathVariable("userid") String userid)
            throws SysException, UserNoFoundException {
		Map<String, Object> paramMap = getUserInfo(request);
    	paramMap.put("userid", userid);
    	PageInfo<Warning> list = this.warningService.getIndexWaringList(paramMap,PageUtil.getPageInfo(request));
        return this.returnSuccess(list);
    }
	
	@ResponseBody
    @RequestMapping(value = "/getReportList/{userid}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "根据用户ID获取日报列表", response = ApiResponseBody.class, notes = "get getReportList by userid", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "userid", required = true, paramType = "path", dataType = "String"),
    })
    public ApiResponseBody getReportList(HttpServletRequest request,@PathVariable("userid") String userid)
            throws SysException, UserNoFoundException {
		Map<String, Object> paramMap = getUserInfo(request);
    	paramMap.put("userid", userid);
    	List<Report> list = this.reportService.getReportList(paramMap);
        return this.returnSuccess(list);
    }
	
	@ResponseBody
    @RequestMapping(value = "/getTopicStatisticsList/{userid}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取首页统计图", response = ApiResponseBody.class, notes = "get TopicStatisticsList by userid", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "userid", required = true, paramType = "path", dataType = "String"),
    })
    public ApiResponseBody getTopicStatisticsList(HttpServletRequest request,@PathVariable("userid") String userid)
            throws SysException, UserNoFoundException {
		Map<String,String> params = new HashMap<String,String>();
		params.put("userid", userid);
		String result =  indexServic.getTopicStatisticsList(params);
        return this.returnSuccess(result);
    }

}
