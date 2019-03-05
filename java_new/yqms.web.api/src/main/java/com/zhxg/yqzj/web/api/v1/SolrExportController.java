package com.zhxg.yqzj.web.api.v1;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.ParamsNullException;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.framework.base.utils.ParamsUtil;
import com.zhxg.framework.base.utils.RedisUtil;
import com.zhxg.yqzj.entities.v1.SolrExportCondition;
import com.zhxg.yqzj.entities.v1.SolrInfo;
import com.zhxg.yqzj.entities.v1.UserMailExport;
import com.zhxg.yqzj.service.exception.solr.SolrAddEmailRepeatException;
import com.zhxg.yqzj.service.exception.solr.SolrAddExportEmailException;
import com.zhxg.yqzj.service.exception.solr.SolrExportFileException;
import com.zhxg.yqzj.service.exception.solr.SolrOperateExportConditionException;
import com.zhxg.yqzj.service.v1.SolrExportService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.web.api
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: <对此类的描述>
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqzj.web.api.v1.SolrExportController.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年5月8日
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
@RequestMapping("/v1/solrExport")
public class SolrExportController extends BaseController<BaseEntity> {

    @Autowired
    SolrExportService solrExportService;

    private static final String MESSAGE_IS_NULL = "搜索关键词不能为空";
    private static final String SEND_EMAIL_IS_NULL = "邮箱地址不能为空";

    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.solrExportService;
    }

    @ResponseBody
    @RequestMapping(value = "/getExportEmail", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "查询邮箱列表", httpMethod = "GET", response = ApiResponseBody.class, notes = "getExportEmail", produces = "application/json")
    public ApiResponseBody getExportEmail(HttpServletRequest request)
            throws UserNoFoundException, SysException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        List<UserMailExport> exportEmail = solrExportService.getExportEmail(paramMap);
        return this.returnSuccess(exportEmail);
    }

    @ResponseBody
    @RequestMapping(value = "/setExportEmail", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "添加导出邮箱", httpMethod = "GET", response = ApiResponseBody.class, notes = "setExportEmail", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "kmEmail", value = "邮箱地址", required = true, paramType = "query", dataType = "String") })
    public ApiResponseBody setExportEmail(HttpServletRequest request,
            @RequestParam(value = "kmEmail", required = true) String kmEmail)
            throws UserNoFoundException, SysException, SolrAddExportEmailException, SolrAddEmailRepeatException,
            ParamsNullException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("kmEmail", kmEmail);
        // 邮箱不能为空
        if (StringUtils.isEmpty(kmEmail)) {
            throw new ParamsNullException(SEND_EMAIL_IS_NULL);
        }
        int result = solrExportService.setExportEmail(paramMap);
        return this.returnSuccess(result);
    }

    @ResponseBody
    @RequestMapping(value = "/getExportCondition", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "获取全部导出字段", httpMethod = "GET", response = ApiResponseBody.class, notes = "getExportCondition", produces = "application/json")
    public ApiResponseBody getExportCondition(HttpServletRequest request)
            throws UserNoFoundException, SysException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        List<Map<String, List<SolrExportCondition>>> exportCondition = solrExportService.getExportCondition(paramMap);
        return this.returnSuccess(exportCondition);
    }

    @ResponseBody
    @RequestMapping(value = "/setExportCondition", method = RequestMethod.POST, consumes = "application/json")
    @ApiOperation(value = "添加全部导出字段", httpMethod = "POST", response = ApiResponseBody.class, notes = "setExportCondition", produces = "application/json")
    public ApiResponseBody setExportCondition(HttpServletRequest request,
            @RequestBody SolrExportCondition[] solrExportCondition)
            throws UserNoFoundException, SysException, SolrOperateExportConditionException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        int result = solrExportService.setExportCondition(paramMap, solrExportCondition);
        return this.returnSuccess(result);
    }

    @ResponseBody
    @RequestMapping(value = "/getExportStatus", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "获取全部导出条件", httpMethod = "GET", response = ApiResponseBody.class, notes = "getExportStatus", produces = "application/json")
    public ApiResponseBody getExportStatus(HttpServletRequest request)
            throws Exception {
        Map<String, Object> paramMap = this.getUserInfo(request);
        String exportStatus = solrExportService.getExportStatus(paramMap);
        return this.returnSuccess(exportStatus);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExportCondition", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "删除用户下导出字段历史", httpMethod = "GET", response = ApiResponseBody.class, notes = "deleteExportCondition", produces = "application/json")
    public ApiResponseBody deleteExportCondition(HttpServletRequest request)
            throws Exception {
        Map<String, Object> paramMap = this.getUserInfo(request);
        int result = solrExportService.deleteExportCondition(paramMap);
        return this.returnSuccess(result);
    }

    @ResponseBody
    @RequestMapping(value = "/exportFile", method = RequestMethod.POST, consumes = "application/json")
    @ApiOperation(value = "全网搜索导出excel", response = ApiResponseBody.class, notes = "post exportFile", produces = "application/json")
    public ApiResponseBody exportFile(HttpServletRequest request, @RequestBody SolrInfo info)
            throws UserNoFoundException, SysException, ParamsNullException, SolrExportFileException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.putAll(ParamsUtil.transToMAP(info, SolrInfo.class));
        // 搜索关键词不能为空
        if (StringUtils.isEmpty(info.getMessage())) {
            throw new ParamsNullException(MESSAGE_IS_NULL);
        }
        // 邮箱地址，多个都好分隔开
        String email = info.getEmail();
        // 邮箱不能为空
        if (StringUtils.isEmpty(email)) {
            throw new ParamsNullException(SEND_EMAIL_IS_NULL);
        }
        // redis设置定时key 防止用户频繁操作导出
        String userId = String.valueOf(paramMap.get("_KUID"));
        String key = "exportStatus" + userId;
        String longDate = DateUtil.getLongDate();
        RedisUtil.setStr(key, longDate);
        RedisUtil.setExpire(key, 180, TimeUnit.SECONDS);
        int result = solrExportService.exportFile(paramMap);
        return this.returnSuccess(result);
    }

}
