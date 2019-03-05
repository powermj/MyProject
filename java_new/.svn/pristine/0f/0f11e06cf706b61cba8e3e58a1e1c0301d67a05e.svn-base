package com.zhxg.yqzj.web.api.v1;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.constants.SysConstants;
import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.exception.BusinessException;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.framework.base.utils.DateStyle;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.framework.base.utils.Md5Utils;
import com.zhxg.framework.base.utils.PageUtil;
import com.zhxg.framework.base.utils.PropertiesUtil;
import com.zhxg.framework.base.utils.UploadFileUtil;
import com.zhxg.yqzj.entities.v1.CustomHomePage;
import com.zhxg.yqzj.entities.v1.EventAnalysis;
import com.zhxg.yqzj.entities.v1.Warning;
import com.zhxg.yqzj.service.v1.CustomHomePageService;
import com.zhxg.yqzj.service.v1.EventAnalysisService;
import com.zhxg.yqzj.service.v1.WarningService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/customHomePage")
public class CustomHomePageController extends BaseController<CustomHomePage> {

	@Autowired
	private CustomHomePageService customHomePageService;
	@Autowired
	private WarningService warningService;
	@Autowired
	private EventAnalysisService eventAnalysisService;

	@Override
	protected BaseService<CustomHomePage> getBaseService() {
		return this.customHomePageService;
	}

	@ResponseBody
	@RequestMapping(value = "/getCustomHomePage", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "获取自定义头部数据（包括导航栏）", httpMethod = "GET", response = ApiResponseBody.class, notes = "getCustomHomePage", produces = "application/json")
	public ApiResponseBody getCustomHomePage(HttpServletRequest request) throws Exception {
		Map<String, Object> paramMap = this.getUserInfo(request);
		JSONArray list = this.customHomePageService.getCustomHomePage(paramMap);
		return this.returnSuccess(list);
	}

	@ResponseBody
	@RequestMapping(value = "/getCustomTailData", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "获取自定义尾部数据", httpMethod = "GET", response = ApiResponseBody.class, notes = "getCustomTailData", produces = "application/json")
	public ApiResponseBody getCustomTailData(HttpServletRequest request) throws Exception {
		Map<String, Object> paramMap = this.getUserInfo(request);
		JSONArray list = this.customHomePageService.getCustomTailData(paramMap);
		return this.returnSuccess(list);
	}

	@ResponseBody
	@RequestMapping(value = "/restoreDefaultHeaderData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "恢复默认头部数据", httpMethod = "POST", response = ApiResponseBody.class, notes = "restoreDefaultHeaderData", produces = "application/json")
	public ApiResponseBody restoreDefaultHeaderData(HttpServletRequest request)
			throws SysException, UserNoFoundException {
		Map<String, Object> paramap = this.getUserInfo(request);
		int result = this.customHomePageService.restoreDefaultHeaderData(paramap);
		return this.returnSuccess(result);
	}

	@ResponseBody
	@RequestMapping(value = "/restoreDefaultTailData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "恢复默认尾部数据", httpMethod = "POST", response = ApiResponseBody.class, notes = "restoreDefaultTailData", produces = "application/json")
	public ApiResponseBody restoreDefaultTailData(HttpServletRequest request)
			throws SysException, UserNoFoundException {
		Map<String, Object> paramap = this.getUserInfo(request);
		int result = this.customHomePageService.restoreDefaultTailData(paramap);
		return this.returnSuccess(result);
	}

	@ResponseBody
	@RequestMapping(value = "/updateTailData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "修改自定义尾部数据", httpMethod = "POST", response = ApiResponseBody.class, notes = "updateTailData", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "tailData", value = "尾部数据", required = true, paramType = "query", dataType = "String") })
	public ApiResponseBody updateTailData(HttpServletRequest request, @RequestBody Map<String, Object> map)
			throws SysException, UserNoFoundException {
		Map<String, Object> paramap = this.getUserInfo(request);
		paramap.putAll(map);
		int result = this.customHomePageService.updateTailData(paramap);
		return this.returnSuccess(result);

	}

	@ResponseBody
	@RequestMapping(value = "/getSourceTypeCount", method = RequestMethod.GET)
	@ApiOperation(value = "今日媒体类型统计", httpMethod = "GET", response = ApiResponseBody.class, notes = "getHeadLinesList", produces = "application/json")
	public ApiResponseBody getSourceTypeCount(HttpServletRequest request) throws SysException, UserNoFoundException {
		Map<String, Object> paramMap = this.getUserInfo(request);
		List<Map<String, Object>> list = this.customHomePageService.getSourceTypeCount(paramMap);
		return this.returnSuccess(list);
	}

	@ResponseBody
	@RequestMapping(value = "/getSubjectByUserId", method = RequestMethod.GET)
	@ApiOperation(value = "获取用户专题", httpMethod = "GET", response = ApiResponseBody.class, notes = "getSubjectByUserId", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "subjectNum", value = "返回专题个数", required = true, paramType = "query", dataType = "Integer") })
	public ApiResponseBody getSubjectByUserId(HttpServletRequest request,
			@RequestParam(value = "subjectNum", required = true) Integer subjectNum)
			throws SysException, UserNoFoundException {
		Map<String, Object> paramMap = this.getUserInfo(request);
		paramMap.put("subjectNum", subjectNum);
		List<Map<String, Object>> list = this.customHomePageService.getSubjectByUserId(paramMap);
		return this.returnSuccess(list);
	}

	@ResponseBody
	@RequestMapping(value = "/updateHeaderData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "修改自定义头部数据", httpMethod = "POST", response = ApiResponseBody.class, notes = "updateHeaderData", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "logo", value = "logo图片", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "ioc", value = "页头图片", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "label", value = "标签页名称", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "name", value = "用户名称", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "welcome", value = "欢迎语", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "pic", value = "帮助、系统信息、联系客服、系统日志图标顺序及隐藏", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "qq", value = "客服qq号", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "nav", value = "系统导航栏", required = true, paramType = "query", dataType = "String") })
	public ApiResponseBody updateHeaderData(HttpServletRequest request, @RequestBody Map<String, Object> map)
			throws SysException, BusinessException {
		Map<String, Object> paramap = this.getUserInfo(request);
		int result = this.customHomePageService.updateHeaderData(paramap, map);
		return this.returnSuccess(result);
	}

	@ResponseBody
	@RequestMapping(value = "/getHeadLinesList", method = RequestMethod.GET)
	@ApiOperation(value = "站点头条", httpMethod = "GET", response = ApiResponseBody.class, notes = "getHeadLinesList", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "type", value = "网站类型", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "pageNum", value = "当前页数", required = false, paramType = "query", dataType = "String") })
	public ApiResponseBody getHeadLinesList(HttpServletRequest request,
			@RequestParam(name = "type", required = false) String type) throws SysException, UserNoFoundException {
		PageInfo<Map<String, Object>> page = this.customHomePageService.getHeadLinesList(PageUtil.getPageInfo(request),
				type);
		page.getList().forEach(map -> {
			Date pushTime = (Date) map.get("pushTime");
			map.put("pushTime", DateUtil.DateToString(pushTime, DateStyle.YYYYMMDDHHMMSS));
		});
		return this.returnSuccess(page);
	}

	@ResponseBody
	@RequestMapping(value = "/uploadHeaderImage", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ApiOperation(value = "上传图片", httpMethod = "POST", response = ApiResponseBody.class, notes = "uploadHeaderImage", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "files", value = "上传图片", required = true, paramType = "query", dataType = "File") })
	public ApiResponseBody uploadHeaderImage(HttpServletRequest request,
			@RequestParam(name = "files", required = true) MultipartFile[] files)
			throws SysException, BusinessException {
		String path = SysConstants.HOMEPAGE_FOLDER_NAME;
		String betaPath = SysConstants.BETA_HOMEPAGE_FOLDER_NAME;

		String imageName = "";
		for (MultipartFile multipartFile : files) {
			String fileName = multipartFile.getOriginalFilename();
			if (multipartFile.isEmpty()) {
				break;
			}
			String extUpp = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			String newFileName = Md5Utils.encoding(UUID.randomUUID().toString());
			try {
				String ora = PropertiesUtil.getValue("environmental");
				if ("beta".equals(ora)) {
					File dest = new File("/var/www/html/upload/betaagentlogo/" + newFileName + "." + extUpp);
					multipartFile.transferTo(dest);
					imageName = betaPath + newFileName + "." + extUpp;
				} else if ("prod".equals(ora)) {
					UploadFileUtil.getInstance().uploadFile(multipartFile.getInputStream(), path,
							newFileName + "." + extUpp, PropertiesUtil.getValue("SMB_URL"));
					imageName = path + newFileName + "." + extUpp;
				}
			} catch (IOException e) {
				this.logger.error("--自定义导航上传logo图片失败--" + e.getMessage(), e);
				throw new BusinessException();
			}
		}
		return this.returnSuccess(imageName);
	}

	@ResponseBody
	@RequestMapping(value = "/getCustomHomePageData", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "获取自定义首页数据", httpMethod = "GET", response = ApiResponseBody.class, notes = "getCustomHomePageData", produces = "application/json")
	public ApiResponseBody getCustomHomePageData(HttpServletRequest request) throws Exception {
		Map<String, Object> paramMap = this.getUserInfo(request);
		Map<String, Object> result = this.customHomePageService.getCustomHomePageData(paramMap);
		return this.returnSuccess(result);
	}

	@ResponseBody
	@RequestMapping(value = "/updateHomePageData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "保存自定义首页数据", httpMethod = "POST", response = ApiResponseBody.class, notes = "updateHomePageData", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "homePageData", value = "自定义首页数据", required = true, paramType = "query", dataType = "String") })
	public ApiResponseBody updateHomePageData(HttpServletRequest request, @RequestBody Map<String, Object> map)
			throws SysException, UserNoFoundException {
		Map<String, Object> paramap = this.getUserInfo(request);
		paramap.putAll(map);
		int result = this.customHomePageService.updateHomePageData(paramap);
		return this.returnSuccess(result);

	}

	@ResponseBody
	@RequestMapping(value = "/waringList", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "根据用户ID获取预警信息列表", response = ApiResponseBody.class, notes = "waringList", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "pageNum", value = "当前页数", required = false, paramType = "query", dataType = "String") })
	public ApiResponseBody waringList(HttpServletRequest request) throws SysException, UserNoFoundException {
		Map<String, Object> paramMap = this.getUserInfo(request);
		paramMap.put("userid", paramMap.get(SysConstants._KUID));
		PageInfo<Warning> list = this.warningService.getIndexWaringList(paramMap, PageUtil.getPageInfo(request));
		return this.returnSuccess(list);
	}

	@ResponseBody
	@RequestMapping(value = "/getSubjectTree", method = RequestMethod.GET)
	@ApiOperation(value = "获取专题树接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "getSubjectTree")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "type", value = "返回数据类型：1简单树，2复杂树", required = true, paramType = "query", dataType = "Integer") })
	public ApiResponseBody getSubjectTree(HttpServletRequest request,
			@RequestParam(value = "type", required = true) String type) throws UserNoFoundException, SysException {
		Map<String, Object> paramMap = this.getUserInfo(request);
		paramMap.put("type", type);
		Map<String, Object> result = this.customHomePageService.getSubjectTree(paramMap);
		return this.returnSuccess(result);
	}

	@ResponseBody
	@RequestMapping(value = "/restoreHomePageData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "恢复首页自定义", httpMethod = "POST", response = ApiResponseBody.class, notes = "restoreHomePageData", produces = "application/json")
	public ApiResponseBody restoreHomePageData(HttpServletRequest request) throws SysException, UserNoFoundException {
		Map<String, Object> paramap = this.getUserInfo(request);
		int result = this.customHomePageService.restoreHomePageData(paramap);
		return this.returnSuccess(result);
	}

	/**
	 * 推荐事件分析列表
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/selectRecommendEventAnalysisList", method = RequestMethod.GET)
	@ApiOperation(value = "推荐事件分析列表", httpMethod = "GET", response = ApiResponseBody.class, notes = "select EventAnalysisInfo", produces = "application/json")
	public ApiResponseBody selectRecommendEventAnalysisList(HttpServletRequest request) {
		List<EventAnalysis> eventAnalysis = this.eventAnalysisService.selectRecommendEventAnalysisList();
		if (null != eventAnalysis && eventAnalysis.size() > 4) {
			eventAnalysis = eventAnalysis.subList(0, 4);
		}
		return this.returnSuccess(eventAnalysis);

	}
}
