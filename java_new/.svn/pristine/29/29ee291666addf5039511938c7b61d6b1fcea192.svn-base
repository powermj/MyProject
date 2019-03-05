package com.zhxg.yqzj.app.api.v1;

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
import com.zhxg.yqzj.service.exception.DataReport.SolrReportInfoSaveException;
import com.zhxg.yqzj.service.exception.DataReport.SolrReportInfoSearchException;
import com.zhxg.yqzj.service.v1.SolrReportInfoService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/app/solrReport")
public class SolrReportInfoAppController extends BaseController<BaseEntity> {

    @Autowired
    private SolrReportInfoService solrReportInfoService;
    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.solrReportInfoService;
    }

    /**
     * 添加solr信息
     *
     * @param request
     * @param krUuids
     * @return
     * @throws UserNoFoundException
     * @throws SysException
     * @throws SolrReportInfoSearchException 
     * @throws SolrReportInfoSaveException 
     */
    @ResponseBody
    @RequestMapping(value = "/saveSolrToReport", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "添加solr信息到数据池", httpMethod = "GET", response = ApiResponseBody.class, notes = "saveSolrToReport", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "kvUuids", value = "信息Id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "classifyId", value = "分类Id", required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code=2021008,message="solr添加数据到数据池失败"),
            @ApiResponse(code=2021009,message="solr查询数据失败")
    })
    public ApiResponseBody saveSolrToReport(HttpServletRequest request,
            @RequestParam(value = "kvUuids", required = true)  String kvUuids,
            @RequestParam(value = "classifyId", required = true)  String classifyId
            ) throws UserNoFoundException, SysException, SolrReportInfoSaveException, SolrReportInfoSearchException{
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("kvUuids",kvUuids);
        paramMap.put("classifyId",classifyId);
        Map<String, Integer> resultMap = solrReportInfoService.saveSolrToReport(paramMap);
        //保存专题
        return this.returnSuccess(resultMap);
    }
    
}
