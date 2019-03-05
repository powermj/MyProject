package com.zhxg.yqzj.app.api.v1;

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

import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.framework.base.utils.ParamsUtil;
import com.zhxg.framework.base.utils.SourceType;
import com.zhxg.yqzj.entities.v1.EventAnalysisData;
import com.zhxg.yqzj.entities.v1.EventAnalysisModuleSummary;
import com.zhxg.yqzj.entities.v1.TopicWeiBoAuthor;
import com.zhxg.yqzj.service.exception.user.UserExpiredException;
import com.zhxg.yqzj.service.v1.EventAnalysisDataService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * CopyRright (c)2012-2016: haoran
 * <p>
 * Project: yqms.web.api
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户模块控制器
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.web.api.v1.UserController.java
 * <p>
 * Author: haoran
 * <p>
 * Create Date: 2017年2月28日
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
@RequestMapping("/app/EventAnalysisDataApp")
public class EventAnalysisDataAppController extends BaseController<EventAnalysisData> {

	@Autowired
	private EventAnalysisDataService eventAnalysisService;

	@Override
	protected BaseService<EventAnalysisData> getBaseService() {
		return this.eventAnalysisService;
	}

	/**
	 * 获取数据总数接口
	 * 
	 * @param consultation
	 * @return
	 * @throws UserNoFoundException
	 * @throws SysException
	 */
	@ResponseBody
	@RequestMapping(value = "/getCount", method = RequestMethod.GET, consumes = "application/json")
	@ApiOperation(value = "获取数据总数接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "applyConsultation", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "eventId", value = "事件ID", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "beginTime", value = "事件开始时间", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "endTime", value = "事件结束时间", required = false, paramType = "query", dataType = "String") })
	public ApiResponseBody getCount(HttpServletRequest request,
			@RequestParam(value = "eventId", required = true) String eventId,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "endTime", required = false) String endTime)
			throws UserNoFoundException, SysException {
		Map<String, Object> paramMap = this.getUserInfo(request);
		paramMap.put("eventId", eventId);
		paramMap.put("beginTime", beginTime);
		paramMap.put("endTime", endTime);
		Map<String, Object> result = this.eventAnalysisService.getCount(paramMap);
		return this.returnSuccess(result);

	}

	/**
	 * 首发媒体接口
	 * 
	 * @param consultation
	 * @return
	 * @throws UserNoFoundException
	 * @throws SysException
	 */
	@ResponseBody
	@RequestMapping(value = "/getFirstMediaInfo", method = RequestMethod.GET, consumes = "application/json")
	@ApiOperation(value = "首发媒体接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "applyConsultation", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "eventId", value = "事件ID", required = true, paramType = "query", dataType = "String") })
	public ApiResponseBody getFirstMediaInfo(HttpServletRequest request,
			@RequestParam(value = "eventId", required = true) String eventId)
			throws UserNoFoundException, SysException {
		Map<String, Object> paramMap = this.getUserInfo(request);
		paramMap.put("eventId", eventId);
		List<EventAnalysisData> result = this.eventAnalysisService.getFirstMediaInfo(paramMap);
		return this.returnSuccess(result);

	}

	/**
	 * 事件脉络接口
	 * 
	 * @param consultation
	 * @return
	 * @throws UserNoFoundException
	 * @throws SysException
	 */
	@ResponseBody
	@RequestMapping(value = "/getEventVein", method = RequestMethod.GET, consumes = "application/json")
	@ApiOperation(value = "事件脉络接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "applyConsultation", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "eventId", value = "事件ID", required = true, paramType = "query", dataType = "String") })
	public ApiResponseBody getEventVein(HttpServletRequest request,
			@RequestParam(value = "eventId", required = true) String eventId)
			throws UserNoFoundException, SysException {
		Map<String, Object> paramMap = this.getUserInfo(request);
		paramMap.put("eventId", eventId);
		List<EventAnalysisData> result = this.eventAnalysisService.getEventVein(paramMap);
		return this.returnSuccess(result);

	}

	/**
	 * 修改事件分析模块摘要
	 * 
	 * @throws SysException
	 * @throws UserNoFoundException
	 * @throws UserExpiredException
	 */
	@ResponseBody
	@RequestMapping(value = "/modifyModuleSummary", method = RequestMethod.POST, consumes = "application/json")
	@ApiOperation(value = "修改事件分析模块摘要接口", httpMethod = "POST", response = ApiResponseBody.class, notes = "modifyModuleSummary", produces = "application/json")
	public ApiResponseBody modifyModuleSummary(HttpServletRequest request,
			@RequestBody EventAnalysisModuleSummary summary)
			throws UserNoFoundException, SysException, UserExpiredException {
		Map<String, Object> paramMap = this.getUserInfo(request);
		paramMap.putAll(ParamsUtil.transToMAP(summary, EventAnalysisModuleSummary.class));
		int result = this.eventAnalysisService.modifyModuleSummary(paramMap);
		return this.returnSuccess(result);

	}

	/**
	 * 根据模块ID获取模块摘要接口 模块ID<br>
	 * 1:事件概述，2:首发媒体，3:事件脉络 4:发展趋势，5:调性分析，6:关键词云 7:微博分析，8:媒体分析，9:观点分析
	 * 
	 * @throws SysException
	 * @throws UserNoFoundException
	 * @throws UserExpiredException
	 */
	@ResponseBody
	@RequestMapping(value = "/getSummaryByModuleId", method = RequestMethod.GET, consumes = "application/json")
	@ApiOperation(value = "获取模块摘要接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "getModuleSummary", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "eventId", value = "事件ID", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "moduleId", value = "模块ID", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "beginTime", value = "事件开始时间", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "endTime", value = "事件结束时间", required = true, paramType = "query", dataType = "String") })
	public ApiResponseBody getModuleSummary(HttpServletRequest request,
			@RequestParam(value = "eventId", required = true) String eventId,
			@RequestParam(value = "moduleId", required = true) String moduleId,
			@RequestParam(value = "beginTime", required = true) String beginTime,
			@RequestParam(value = "endTime", required = true) String endTime)
			throws UserNoFoundException, SysException, UserExpiredException {
		Map<String, Object> paramMap = this.getUserInfo(request);
		paramMap.put("eventId", eventId);
		paramMap.put("moduleId", moduleId);
		paramMap.put("beginTime", beginTime);
		paramMap.put("endTime", endTime);
		Map<String, Object> result = this.eventAnalysisService.getSummaryByModuleId(paramMap);
		return this.returnSuccess(result);

	}

	/**
	 * 发展趋势接口
	 * 
	 * @return
	 * @throws UserNoFoundException
	 * @throws SysException
	 */
	@ResponseBody
	@RequestMapping(value = "/getSourceTypeTrend", method = RequestMethod.GET, consumes = "application/json")
	@ApiOperation(value = "发展趋势接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "getSourceTypeTrend", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "eventId", value = "事件ID", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "beginTime", value = "事件开始时间", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "endTime", value = "事件结束时间", required = true, paramType = "query", dataType = "String") })
	public ApiResponseBody getSourceTypeTrend(HttpServletRequest request,
			@RequestParam(value = "eventId", required = true) String eventId,
			@RequestParam(value = "beginTime", required = true) String beginTime,
			@RequestParam(value = "endTime", required = true) String endTime)
			throws UserNoFoundException, SysException {
		Map<String, Object> paramMap = this.getUserInfo(request);
		paramMap.put("eventId", eventId);
		paramMap.put("beginTime", beginTime);
		paramMap.put("endTime", endTime);
		List<Map<String, Object>> result = this.eventAnalysisService.getSourceTypeTrend(paramMap);
		return this.returnSuccess(result);

	}

	/**
	 * 调性分析
	 * 
	 * @return
	 * @throws UserNoFoundException
	 * @throws SysException
	 */
	@ResponseBody
	@RequestMapping(value = "/getWeiboOrientationStatistics", method = RequestMethod.GET, consumes = "application/json")
	@ApiOperation(value = "微博调性分析", httpMethod = "GET", response = ApiResponseBody.class, notes = "getWeiboOrientationStatistics", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "eventId", value = "事件ID", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "beginTime", value = "事件开始时间", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "endTime", value = "事件结束时间", required = true, paramType = "query", dataType = "String") })
	public ApiResponseBody getWeiboOrientationStatistics(HttpServletRequest request,
			@RequestParam(value = "eventId", required = true) String eventId,
			@RequestParam(value = "beginTime", required = true) String beginTime,
			@RequestParam(value = "endTime", required = true) String endTime)
			throws UserNoFoundException, SysException {
		Map<String, Object> paramMap = this.getUserInfo(request);
		paramMap.put("eventId", eventId);
		paramMap.put("beginTime", beginTime);
		paramMap.put("endTime", endTime);
		paramMap.put("sourceType", SourceType.MICROBLOG.getType());
		List<Map<String, Object>> result = this.eventAnalysisService.getOrientationStatistics(paramMap);
		return this.returnSuccess(result);

	}

	/**
	 * 调性分析
	 * 
	 * @return
	 * @throws UserNoFoundException
	 * @throws SysException
	 */
	@ResponseBody
	@RequestMapping(value = "/getOrientationStatistics", method = RequestMethod.GET, consumes = "application/json")
	@ApiOperation(value = "调性分析", httpMethod = "GET", response = ApiResponseBody.class, notes = "getOrientationStatistics", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "eventId", value = "事件ID", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "beginTime", value = "事件开始时间", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "endTime", value = "事件结束时间", required = true, paramType = "query", dataType = "String") })
	public ApiResponseBody getOrientationStatistics(HttpServletRequest request,
			@RequestParam(value = "eventId", required = true) String eventId,
			@RequestParam(value = "beginTime", required = true) String beginTime,
			@RequestParam(value = "endTime", required = true) String endTime)
			throws UserNoFoundException, SysException {
		Map<String, Object> paramMap = this.getUserInfo(request);
		paramMap.put("eventId", eventId);
		paramMap.put("beginTime", beginTime);
		paramMap.put("endTime", endTime);
		List<Map<String, Object>> result = this.eventAnalysisService.getOrientationStatistics(paramMap);
		return this.returnSuccess(result);

	}

	/**
	 * 媒体分析
	 * 
	 * @return
	 * @throws UserNoFoundException
	 * @throws SysException
	 */
	@ResponseBody
	@RequestMapping(value = "/getMediaAnalysis", method = RequestMethod.GET, consumes = "application/json")
	@ApiOperation(value = "媒体分析", httpMethod = "GET", response = ApiResponseBody.class, notes = "getMediaAnalysis", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "eventId", value = "事件ID", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "beginTime", value = "事件开始时间", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "endTime", value = "事件结束时间", required = true, paramType = "query", dataType = "String") })
	public ApiResponseBody getMediaAnalysis(HttpServletRequest request,
			@RequestParam(value = "eventId", required = true) String eventId,
			@RequestParam(value = "beginTime", required = true) String beginTime,
			@RequestParam(value = "endTime", required = true) String endTime)
			throws UserNoFoundException, SysException {
		Map<String, Object> paramMap = getUserInfo(request);
		paramMap.put("eventId", eventId);
		paramMap.put("beginTime", beginTime);
		paramMap.put("endTime", endTime);
		List<Map<String, Object>> result = eventAnalysisService.getMediaAnalysis(paramMap);
		return this.returnSuccess(result);

	}

	/**
	 * 来源网站分析
	 * 
	 * @return
	 * @throws UserNoFoundException
	 * @throws SysException
	 */
	@ResponseBody
	@RequestMapping(value = "/getWebNameAnalysis", method = RequestMethod.GET, consumes = "application/json")
	@ApiOperation(value = "来源网站分析", httpMethod = "GET", response = ApiResponseBody.class, notes = "getWebNameAnalysis", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "eventId", value = "事件ID", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "beginTime", value = "事件开始时间", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "endTime", value = "事件结束时间", required = true, paramType = "query", dataType = "String") })
	public ApiResponseBody getWebNameAnalysis(HttpServletRequest request,
			@RequestParam(value = "eventId", required = true) String eventId,
			@RequestParam(value = "beginTime", required = true) String beginTime,
			@RequestParam(value = "endTime", required = true) String endTime)
			throws UserNoFoundException, SysException {
		Map<String, Object> paramMap = getUserInfo(request);
		paramMap.put("eventId", eventId);
		paramMap.put("beginTime", beginTime);
		paramMap.put("endTime", endTime);
		List<Map<String, Object>> result = eventAnalysisService.getWebNameAnalysis(paramMap);
		return this.returnSuccess(result);
	}

	/**
	 * 来源网站导出
	 * 
	 * @return
	 * @throws UserNoFoundException
	 * @throws SysException
	 */
	@ResponseBody
	@RequestMapping(value = "/exportWebName", method = RequestMethod.GET, consumes = "application/json")
	@ApiOperation(value = "来源网站导出", httpMethod = "GET", response = ApiResponseBody.class, notes = "getWebNameAnalysis", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "eventId", value = "事件ID", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "beginTime", value = "事件开始时间", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "endTime", value = "事件结束时间", required = true, paramType = "query", dataType = "String") })
	public ApiResponseBody exportWebName(HttpServletRequest request,
			@RequestParam(value = "eventId", required = true) String eventId,
			@RequestParam(value = "beginTime", required = true) String beginTime,
			@RequestParam(value = "endTime", required = true) String endTime)
			throws UserNoFoundException, SysException {
		Map<String, Object> paramMap = getUserInfo(request);
		paramMap.put("eventId", eventId);
		paramMap.put("beginTime", beginTime);
		paramMap.put("endTime", endTime);
		Map<String, Object> result = eventAnalysisService.exportWebName(paramMap);
		return this.returnSuccess(result);
	}

	/**
	 * 关键词云接口
	 *
	 * @param request
	 * @param eventId
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @throws UserNoFoundException
	 * @throws SysException
	 */
	@ResponseBody
	@RequestMapping(value = "/getMainWordsCloud", method = RequestMethod.GET, consumes = "application/json")
	@ApiOperation(value = "关键词云接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "applyConsultation", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "eventId", value = "事件ID", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "beginTime", value = "事件开始时间", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "endTime", value = "事件结束时间", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "top", value = "top值", required = false, paramType = "query", dataType = "int") })
	public ApiResponseBody getWordsCloud(HttpServletRequest request,
			@RequestParam(value = "eventId", required = true) String eventId,
			@RequestParam(value = "beginTime", required = true) String beginTime,
			@RequestParam(value = "endTime", required = true) String endTime,
			@RequestParam(value = "top", required = false, defaultValue = "20") int top)
			throws UserNoFoundException, SysException {
		Map<String, Object> paramMap = this.getUserInfo(request);
		paramMap.put("eventId", eventId);
		paramMap.put("beginTime", beginTime);
		paramMap.put("endTime", endTime);
		paramMap.put("top", top);
		List<Map<String, Object>> result = this.eventAnalysisService.getWordsCloud(paramMap);
		return this.returnSuccess(result);

	}

	/**
	 * 水军分析
	 * 
	 * @throws SysException
	 * @throws UserNoFoundException
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/getNavyAnalysisCount", method = RequestMethod.GET, consumes = "application/json")
	@ApiOperation(value = "水军分析", httpMethod = "GET", response = ApiResponseBody.class, notes = "getNavyAnalysisCount", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "eventId", value = "话题id", required = true, paramType = "query", dataType = "String") })
	public ApiResponseBody getNavyAnalysisCount(HttpServletRequest request,
			@RequestParam(value = "eventId", required = true) String eventId)
			throws UserNoFoundException, SysException {
		Map<String, Object> paramMap = getUserInfo(request);
		paramMap.put("uuid", eventId);
		List<TopicWeiBoAuthor> list = eventAnalysisService.getNavyAnalysisCount(paramMap);
		return this.returnSuccess(list);

	}

	/**
	 * 情感分析
	 * 
	 * @throws SysException
	 * @throws UserNoFoundException
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/getEmotionAnalysisCount", method = RequestMethod.GET, consumes = "application/json")
	@ApiOperation(value = "情感分析", httpMethod = "GET", response = ApiResponseBody.class, notes = "getEmotionAnalysisCount", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "eventId", value = "话题id", required = true, paramType = "query", dataType = "String") })
	public ApiResponseBody getEmotionAnalysisCount(HttpServletRequest request,
			@RequestParam(value = "eventId", required = true) String eventId)
			throws UserNoFoundException, SysException {
		Map<String, Object> paramMap = getUserInfo(request);
		paramMap.put("uuid", eventId);
		List<TopicWeiBoAuthor> list = eventAnalysisService.getEmotionAnalysisCount(paramMap);
		return this.returnSuccess(list);

	}

	/**
	 * 影响力排行
	 * 
	 * @throws SysException
	 * @throws UserNoFoundException
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/getInfluenceRankingList", method = RequestMethod.GET, consumes = "application/json")
	@ApiOperation(value = "影响力排行", httpMethod = "GET", response = ApiResponseBody.class, notes = "getInfluenceRankingList", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "eventId", value = "话题id", required = true, paramType = "query", dataType = "String") })
	public ApiResponseBody getInfluenceRankingList(HttpServletRequest request,
			@RequestParam(value = "eventId", required = true) String eventId)
			throws UserNoFoundException, SysException {
		Map<String, Object> paramMap = getUserInfo(request);
		paramMap.put("uuid", eventId);
		Map<String, Object> map = eventAnalysisService.getInfluenceRankingList(paramMap);
		return this.returnSuccess(map);

	}

	/**
	 * 微博分析
	 * 
	 * @throws SysException
	 * @throws UserNoFoundException
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/getMicroBlogAnalysisCount", method = RequestMethod.GET, consumes = "application/json")
	@ApiOperation(value = "微博分析", httpMethod = "GET", response = ApiResponseBody.class, notes = "getMicroBlogAnalysisCount", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "eventId", value = "话题id", required = true, paramType = "query", dataType = "String") })
	public ApiResponseBody getMicroBlogAnalysisCount(HttpServletRequest request,
			@RequestParam(value = "eventId", required = true) String eventId)
			throws UserNoFoundException, SysException {
		Map<String, Object> paramMap = getUserInfo(request);
		paramMap.put("uuid", eventId);
		Map<String, Object> map = eventAnalysisService.getMicroBlogAnalysisCount(paramMap);
		return this.returnSuccess(map);
	}

	/**
	 * 重点微博
	 * 
	 * @throws SysException
	 * @throws UserNoFoundException
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/getKeyPointMicroBlogList", method = RequestMethod.GET, consumes = "application/json")
	@ApiOperation(value = "重点微博", httpMethod = "GET", response = ApiResponseBody.class, notes = "getKeyPointMicroBlogList", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "eventId", value = "话题id", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "beginTime", value = "开始时间", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "String") })
	public ApiResponseBody getKeyPointMicroBlogList(HttpServletRequest request,
			@RequestParam(value = "eventId", required = true) String eventId,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "endTime", required = false) String endTime)
			throws UserNoFoundException, SysException {
		Map<String, Object> paramMap = getUserInfo(request);
		paramMap.put("uuid", eventId);
		paramMap.put("beginTime", beginTime);
		paramMap.put("endTime", endTime);
		Map<String, Object> map = eventAnalysisService.getKeyPointMicroBlogList(paramMap);
		return this.returnSuccess(map);
	}

	@ResponseBody
	@RequestMapping(value = "/getBloggerRegionList", method = RequestMethod.GET, consumes = "application/json")
	@ApiOperation(value = "博主地域", httpMethod = "GET", response = ApiResponseBody.class, notes = "getBloggerRegionList", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "eventId", value = "话题id", required = true, paramType = "query", dataType = "String") })
	public ApiResponseBody getBloggerRegionList(HttpServletRequest request,
			@RequestParam(value = "eventId", required = true) String eventId,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "endTime", required = false) String endTime)
			throws UserNoFoundException, SysException {
		Map<String, Object> paramMap = getUserInfo(request);
		paramMap.put("uuid", eventId);
		List<TopicWeiBoAuthor> map = eventAnalysisService.getBloggerRegionList(paramMap);
		return this.returnSuccess(map);
	}

	/**
	 * 传播路径
	 * 
	 * @throws SysException
	 * @throws UserNoFoundException
	 */
	@ResponseBody
	@RequestMapping(value = "/getPropagatePath", method = RequestMethod.GET, consumes = "application/json")
	@ApiOperation(value = "传播路径", httpMethod = "GET", response = ApiResponseBody.class, notes = "getPropagatePath", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "eventId", value = "话题id", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "beginTime", value = "开始时间", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "shareUserId", value = "推荐事件用户ID", required = false, paramType = "query", dataType = "String") })
	public ApiResponseBody getPropagatePath(HttpServletRequest request,
			@RequestParam(value = "eventId", required = true) String eventId,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "endTime", required = false) String endTime)
			throws UserNoFoundException, SysException {
		Map<String, Object> paramMap = getUserInfo(request);
		paramMap.put("eventId", eventId);
		paramMap.put("beginTime", beginTime);
		paramMap.put("endTime", endTime);
		Map<String, Object> list = eventAnalysisService.getPropagatePath(paramMap);
		return this.returnSuccess(list);
	}

	/**
	 * 网民观点
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getNetizenViewpoint", method = RequestMethod.GET, consumes = "application/json")
	@ApiOperation(value = "网民观点", httpMethod = "GET", response = ApiResponseBody.class, notes = "getPropagatePath", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "eventId", value = "话题id", required = true, paramType = "query", dataType = "String") })
	public ApiResponseBody getNetizenViewpoint(HttpServletRequest request,
			@RequestParam(value = "eventId", required = true) String eventId) {
		List<Map<String, Object>> list = eventAnalysisService.getNetizenViewpoint(eventId);
		return this.returnSuccess(list);
	}

}
