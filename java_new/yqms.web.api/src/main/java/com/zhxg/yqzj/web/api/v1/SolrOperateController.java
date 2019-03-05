package com.zhxg.yqzj.web.api.v1;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.ParamsNullException;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.framework.base.utils.ParamsUtil;
import com.zhxg.yqzj.entities.v1.SearchCondition;
import com.zhxg.yqzj.entities.v1.SolrEmail;
import com.zhxg.yqzj.entities.v1.SolrInfo;
import com.zhxg.yqzj.service.exception.eventanalysis.NumberException;
import com.zhxg.yqzj.service.exception.myfocus.MyFocusInfoNotFoundException;
import com.zhxg.yqzj.service.exception.myfocus.RepeatOperateException;
import com.zhxg.yqzj.service.exception.myfocus.SendEmailErrorException;
import com.zhxg.yqzj.service.exception.solr.WarningException;
import com.zhxg.yqzj.service.exception.user.UserExpiredException;
import com.zhxg.yqzj.service.v1.SolrOperateService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <p>
 * Project: yqms.web.api
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: <星光搜索模块>
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqzj.web.api.v1.EventAnalysisController.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年4月18日
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
@RequestMapping("/v1/solrOperate")
public class SolrOperateController extends BaseController<BaseEntity> {

    @Autowired
    SolrOperateService solrOperateService;

    private static final String KV_ID_IS_NULL = "信息id不能为空";
    private static final String REASON_IS_NULL = "原因不能为空";
    private static final String SEND_EMAIL_IS_NULL = "邮箱不能为空";
    private static final String MESSAGE_IS_NULL = "搜索关键词不能为空";

    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.solrOperateService;
    }

    @ResponseBody
    @RequestMapping(value = "/solrSearch", method = RequestMethod.POST, consumes = "application/json")
    @ApiOperation(value = "solr查询列表", response = ApiResponseBody.class, notes = "post solrSearch", produces = "application/json")
    @ApiResponses(value = {  
            @ApiResponse(code = 2000002, message = "搜索关键词不能为空"),
            @ApiResponse(code = 1008001, message = "搜索服务器连接失败") 
    })
    public ApiResponseBody solrSearch(HttpServletRequest request, @RequestBody SolrInfo info)
            throws UserNoFoundException, SysException, ParamsNullException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.putAll(ParamsUtil.transToMAP(info, SolrInfo.class));
        if (StringUtils.isEmpty(info.getMessage())) {// 搜索关键词不能为空
            throw new ParamsNullException(MESSAGE_IS_NULL);
        }
        Integer offset = null;
        Integer limit = null;
        String pageNum = info.getPageNum();
        String pageSize = info.getPageSize();
        if (!StringUtils.isEmpty(pageNum)) {
            offset = Integer.valueOf(pageNum);
        } else {
            offset = 1;
        }
        if (!StringUtils.isEmpty(pageSize)) {
            limit = Integer.valueOf(pageSize);
        } else {
            limit = 10;
        }
        Pagination pagination = new Pagination(offset, limit);
        PageInfo<T> pageInfo = solrOperateService.solrSearch(paramMap, pagination);
        return this.returnSuccess(JSONObject.toJSON(pageInfo));
    }

    /**
     * solr加简报
     *
     * @throws SysException
     * @throws UserNoFoundException
     * @throws ParamsNullException
     * @throws RepeatOperateException
     */
    @ResponseBody
    @RequestMapping(value = "/addBriefing", method = RequestMethod.POST, consumes = "application/json")
    @ApiOperation(value = "加简报接口", httpMethod = "POST", response = ApiResponseBody.class, notes = "post addBriefing", produces = "application/json")
    public ApiResponseBody addBriefing(HttpServletRequest request, @RequestBody SolrInfo info)
            throws UserNoFoundException, SysException, UserExpiredException, NumberException, RepeatOperateException,
            ParamsNullException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.putAll(ParamsUtil.transToMAP(info, SolrInfo.class));
        int result = solrOperateService.addBriefing(paramMap);
        return this.returnSuccess(result);
    }

    /**
     * solr加关注
     *
     * @throws SysException
     * @throws UserNoFoundException
     * @throws ParamsNullException
     * @throws RepeatOperateException
     */
    @ResponseBody
    @RequestMapping(value = "/addAttention", method = RequestMethod.POST, consumes = "application/json")
    @ApiOperation(value = "加关注接口", httpMethod = "POST", response = ApiResponseBody.class, notes = "post addAttention", produces = "application/json")
    public ApiResponseBody addAttention(HttpServletRequest request, @RequestBody SolrInfo info)
            throws UserNoFoundException, SysException, UserExpiredException, NumberException, RepeatOperateException,
            ParamsNullException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.putAll(ParamsUtil.transToMAP(info, SolrInfo.class));
        int result = solrOperateService.addAttention(paramMap);
        return this.returnSuccess(result);
    }

    /**
     * solr加预警
     *
     * @throws SysException
     * @throws UserNoFoundException
     * @throws ParamsNullException
     * @throws RepeatOperateException
     * @throws WarningException
     */
    @ResponseBody
    @RequestMapping(value = "/addWarning", method = RequestMethod.POST, consumes = "application/json")
    @ApiOperation(value = "加预警接口", httpMethod = "POST", response = ApiResponseBody.class, notes = "post addWarning", produces = "application/json")
    public ApiResponseBody addWarning(HttpServletRequest request, @RequestBody SolrInfo info)
            throws UserNoFoundException, SysException, UserExpiredException, NumberException, RepeatOperateException,
            ParamsNullException, WarningException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.putAll(ParamsUtil.transToMAP(info, SolrInfo.class));
        int result = solrOperateService.addWarning(paramMap);
        return this.returnSuccess(result);
    }

    @ResponseBody
    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "上报邮箱", response = ApiResponseBody.class, notes = "post sendEmail", produces = "application/json")
    public ApiResponseBody sendEmail(@RequestBody SolrEmail solrEmail, HttpServletRequest request) throws SysException,
            UserNoFoundException, ParamsNullException, MyFocusInfoNotFoundException, SendEmailErrorException {
        Map<String, Object> params = getUserInfo(request);
        params.putAll(ParamsUtil.transToMAP(solrEmail, SolrEmail.class));
        String kvIds = solrEmail.getKvIds(); // 关注信息id
        String reason = solrEmail.getReason();// 上报原因
        String email = solrEmail.getEmail(); // 邮箱地址，多个都好分隔开

        if (StringUtils.isEmpty(kvIds)) {// 信息id不能为空
            throw new ParamsNullException(KV_ID_IS_NULL);
        }
        if (StringUtils.isEmpty(reason)) {// 原因不能为空
            throw new ParamsNullException(REASON_IS_NULL);
        }
        if (StringUtils.isEmpty(email)) {// 邮箱不能为空
            throw new ParamsNullException(SEND_EMAIL_IS_NULL);
        }
        solrOperateService.addSendEmail(params);
        return this.returnSuccess(null);
    }

    @ResponseBody
    @RequestMapping(value = "/saveSearchCondition", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "保存筛选条件", response = ApiResponseBody.class, notes = "post saveSearchCondition", produces = "application/json")
    public ApiResponseBody saveSearchCondition(HttpServletRequest request, @RequestBody SearchCondition searchCondition)
            throws SysException, UserNoFoundException {
        Map<String, Object> params = getUserInfo(request);
        String kuId = params.get("_KUID").toString();
        searchCondition.setKuId(Integer.valueOf(kuId));
        params.putAll(ParamsUtil.transToMAP(searchCondition, SearchCondition.class));
        solrOperateService.saveSearchCondition(params);
        return this.returnSuccess(null);
    }

    @ResponseBody
    @RequestMapping(value = "/getSearchCondition", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取筛选条件", response = ApiResponseBody.class, notes = "getSearchCondition", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ksType", value = "筛选条件类型", required = true, paramType = "query", dataType = "String") })
    public ApiResponseBody getSearchCondition(HttpServletRequest request,
            @RequestParam(value = "ksType", required = true) String ksType)
            throws SysException, UserNoFoundException {
        Map<String, Object> params = getUserInfo(request);
        String kuId = params.get("_KUID").toString();
        params.put("kuId", Integer.valueOf(kuId));
        params.put("ksType", ksType);
        List<Map<String, Object>> coditionList = solrOperateService.getSearchCondition(params);
        return this.returnSuccess(coditionList);
    }

    @ResponseBody
    @RequestMapping(value = "/getDetails", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取详情页", response = ApiResponseBody.class, notes = "getSearchCondition", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "kvUuid", value = "信息ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "message", value = "搜索关键词", required = false, paramType = "query", dataType = "String") })
    public ApiResponseBody getDetails(HttpServletRequest request,
            @RequestParam(value = "kvUuid", required = true) String kvUuid,
            @RequestParam(value = "message", required = false) String message)
            throws SysException, UserNoFoundException, ParamsNullException {
        Map<String, Object> params = getUserInfo(request);
        if (StringUtils.isEmpty(kvUuid)) {// 信息id不能为空
            throw new ParamsNullException(KV_ID_IS_NULL);
        }
        params.put("kvUuid", kvUuid);
        params.put("message", message);
        List<Map<String, Object>> deailList = solrOperateService.getDetails(params);
        return this.returnSuccess(JSON.toJSON(deailList));
    }

    @ResponseBody
    @RequestMapping(value = "/getAbstract", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取预警推送摘要", response = ApiResponseBody.class, notes = "getSearchCondition", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "kvUuid", value = "信息ID", required = true, paramType = "query", dataType = "String") })
    public ApiResponseBody getAbstract(HttpServletRequest request,
            @RequestParam(value = "kvUuid", required = true) String kvUuid)
            throws SysException, UserNoFoundException, ParamsNullException {
        Map<String, Object> params = getUserInfo(request);
        if (StringUtils.isEmpty(kvUuid)) {// 信息id不能为空
            throw new ParamsNullException(KV_ID_IS_NULL);
        }
        params.put("kvUuid", kvUuid);
        String smbInfo = solrOperateService.getAbstract(params);
        return this.returnSuccess(JSON.toJSON(smbInfo));
    }
}
