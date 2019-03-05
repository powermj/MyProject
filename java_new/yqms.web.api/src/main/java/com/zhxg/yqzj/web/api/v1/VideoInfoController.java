package com.zhxg.yqzj.web.api.v1;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.zhxg.yqzj.entities.v1.InfoSource;
import com.zhxg.yqzj.entities.v1.VideoInfo;
import com.zhxg.yqzj.service.exception.report.ReportException;
import com.zhxg.yqzj.service.v1.InfoSourceService;
import com.zhxg.yqzj.service.v1.VideoInfoService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 小视频
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/v1/videoInfo")
public class VideoInfoController extends BaseController<VideoInfo> {
	@Autowired
	private VideoInfoService videoInfoService;

	@Autowired
	private InfoSourceService infoSourceService;

	@Override
	protected BaseService<VideoInfo> getBaseService() {
		return this.videoInfoService;
	}

	@ResponseBody
	@RequestMapping(value = "/getInfoSourceList", method = RequestMethod.GET, consumes = "application/json")
	@ApiOperation(value = "查询来源类型", httpMethod = "GET", response = ApiResponseBody.class, notes = "getInfoSourceList", produces = "application/json")
	public ApiResponseBody getInfoSourceList() throws UserNoFoundException, SysException {
		List<InfoSource> list = this.infoSourceService.getInfoSourceList();
		return this.returnSuccess(list);

	}

	@ResponseBody
	@RequestMapping(value = "/getDetailsInfo", method = RequestMethod.GET, consumes = "application/json")
	@ApiOperation(value = "信息详情页", httpMethod = "GET", response = ApiResponseBody.class, notes = "getDetailsInfo", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "信息ID", required = true, paramType = "query", dataType = "Integer") })
	public ApiResponseBody getDetailsInfo(HttpServletRequest request,
			@RequestParam(name = "id", required = true) Integer id) throws UserNoFoundException, SysException {
		Map<String, Object> paramMap = this.getUserInfo(request);
		paramMap.put("id", id);
		VideoInfo videoInfo = this.videoInfoService.getDetailsInfo(paramMap);
		return this.returnSuccess(videoInfo);
	}
	@ResponseBody
	@RequestMapping(value = "/getVideoList", method = RequestMethod.GET, consumes = "application/json")
	@ApiOperation(value = "获取小视频列表页查询", httpMethod = "GET", response = ApiResponseBody.class, notes = "getVideoList", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "信息主键ID", required = false, paramType = "query", dataType = "Integer"),
			@ApiImplicitParam(name = "sourceIdList", value = "来源类型 逗号分隔", required = false, paramType = "query", dataType = "Integer"),
			@ApiImplicitParam(name = "orientationList", value = "倾向性 逗号分隔", required = false, paramType = "query", dataType = "Integer"),
			@ApiImplicitParam(name = "bTime", value = "开始时间", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "eTime", value = "结束时间", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "isRead", value = "是否已读0否1是", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "isWarning", value = "是否预警0否1是", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "isCommentData", value = "是否是评论数据 0 否 1是", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "simHash", value = "是否去重0 否 1是 ;详情页相似信息实际simhash值 ", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "author", value = "作者", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "infoContent", value = "搜索内容", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "subjectIdList", value = "专题ID逗号分隔", required = false, paramType = "query", dataType = "String")})
	public ApiResponseBody getVideoList(HttpServletRequest request,@ModelAttribute VideoInfo videoInfo) throws UserNoFoundException, SysException {
  		Map<String, Object> paramMap = this.getUserInfo(request);
		paramMap.putAll(ParamsUtil.transToMAP(videoInfo, VideoInfo.class));
		PageInfo<VideoInfo> page=this.videoInfoService.getVideoList(paramMap,PageUtil.getPageInfo(request));
		return this.returnSuccess(page);
	}
	@ResponseBody
	@RequestMapping(value = "/updateBatchWarning", method = RequestMethod.POST, consumes = "application/json")
	@ApiOperation(value = "批量预警", httpMethod = "POST", response = ApiResponseBody.class, notes = "updateBatchWarning", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "idList", value = "信息ID 逗号分隔", required = true, paramType = "query", dataType = "Integer") })
	public ApiResponseBody updateBatchWarning(HttpServletRequest request,@RequestBody Map<String,Object> map) throws UserNoFoundException, SysException {
		Map<String, Object> paramMap = this.getUserInfo(request);
		paramMap.putAll(map);
		String idList=(String)paramMap.get("idList");
		if(idList.indexOf(",")>-1) {
			paramMap.put("idList", idList.split(","));
		}else {
			paramMap.put("idList", new String[] {idList});
		}
		int res=this.videoInfoService.updateBatchWarning(paramMap);
		return this.returnSuccess(res);
	}	
	@ResponseBody
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST, consumes = "application/json")
	@ApiOperation(value = "批量删除", httpMethod = "POST", response = ApiResponseBody.class, notes = "deleteBatch", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "idList", value = "信息ID 逗号分隔", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "subjectId", value = "专题ID", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "simHash", value = "simHash", required = false, paramType = "query", dataType = "String")})
	public ApiResponseBody deleteBatch(HttpServletRequest request,@RequestBody Map<String,Object> map) throws UserNoFoundException, SysException {
		Map<String, Object> paramMap = this.getUserInfo(request);
		paramMap.putAll(map);
		String idList=(String)paramMap.get("idList");
		String subjectId=(String)paramMap.get("subjectId");
		String simHash=(String)paramMap.get("simHash");
		if(StringUtils.isNotBlank(idList)) {
			if(idList.indexOf(",")>-1) {
				paramMap.put("idList", idList.split(","));
			}else {
				paramMap.put("idList", new String[] {idList});
			}
		}else {
			paramMap.remove("idList");
		}
		
		if(StringUtils.isBlank(idList) && StringUtils.isBlank(subjectId) && StringUtils.isBlank(simHash)) {
			throw  new SysException("参数为空");
		}
		int res=this.videoInfoService.deleteBatch(paramMap);
		return this.returnSuccess(res);
	}
	@ResponseBody
	@RequestMapping(value = "/deleteBatchWarning", method = RequestMethod.POST, consumes = "application/json")
	@ApiOperation(value = "批量删除预警", httpMethod = "POST", response = ApiResponseBody.class, notes = "deleteBatch", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "idList", value = "信息ID 逗号分隔", required = true, paramType = "query", dataType = "String")})
	public ApiResponseBody deleteBatchWarning(HttpServletRequest request,@RequestBody Map<String,Object> map) throws UserNoFoundException, SysException {
		Map<String, Object> paramMap = this.getUserInfo(request);
		paramMap.putAll(map);
		String idList=(String)paramMap.get("idList");
		if(StringUtils.isNotBlank(idList)) {
			if(idList.indexOf(",")>-1) {
				paramMap.put("idList", idList.split(","));
			}else {
				paramMap.put("idList", new String[] {idList});
			}
		}else {
			paramMap.remove("idList");
		}
		if(StringUtils.isBlank(idList)) {
			throw  new SysException("参数为空");
		}
		int res=this.videoInfoService.deleteBatchWarning(paramMap);
		return this.returnSuccess(res);
	}
	
	
	
	
    @ResponseBody
    @RequestMapping(value = "/getPlatformProportion", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "获取平台占比图", httpMethod = "GET", response = ApiResponseBody.class, notes = "getPlatformProportion", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sourceIdList", value = "来源类型 逗号分隔", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "orientationList", value = "倾向性 逗号分隔", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "bTime", value = "开始时间", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "eTime", value = "结束时间", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "subjectIdList", value = "专题ID", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "isCommentData", value = "是否是评论数据 0否 1是", required = false, paramType = "query", dataType = "String") })
    public ApiResponseBody getPlatformProportion(HttpServletRequest request, @ModelAttribute VideoInfo videoInfo)
            throws UserNoFoundException, SysException, ReportException {
        List<Integer> orientationList = videoInfo.getOrientationList();
        if (orientationList == null || orientationList.size() <= 0) {
            throw new ReportException("001", "倾向性不能为空");
        }
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.putAll(ParamsUtil.transToMAP(videoInfo, VideoInfo.class));
        Map<String, Object> result = this.videoInfoService.getPlatformProportion(paramMap);
        return this.returnSuccess(result);
    }

    @ResponseBody
    @RequestMapping(value = "/getTrendOfPubOpinion", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "获取舆情走势图", httpMethod = "GET", response = ApiResponseBody.class, notes = "getTrendOfPubOpinion", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sourceIdList", value = "来源类型 逗号分隔", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "orientationList", value = "倾向性 逗号分隔", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "bTime", value = "开始时间", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "eTime", value = "结束时间", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "subjectIdList", value = "专题ID", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "isCommentData", value = "是否是评论数据 0否 1是", required = false, paramType = "query", dataType = "String") })
    public ApiResponseBody getTrendOfPubOpinion(HttpServletRequest request, @ModelAttribute VideoInfo videoInfo)
            throws UserNoFoundException, SysException, ReportException {
        List<Integer> orientationList = videoInfo.getOrientationList();
        if (orientationList == null || orientationList.size() <= 0) {
            throw new ReportException("001", "倾向性不能为空");
        }
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.putAll(ParamsUtil.transToMAP(videoInfo, VideoInfo.class));
        Map<String, Object> result = this.videoInfoService.getTrendOfPubOpinion(paramMap);
        return this.returnSuccess(result);
    }
	
    @ResponseBody
    @RequestMapping(value = "/getOriCount", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "获取调性占比图", httpMethod = "GET", response = ApiResponseBody.class, notes = "getOriCount", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sourceIdList", value = "来源类型 逗号分隔", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "orientationList", value = "倾向性 逗号分隔", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "bTime", value = "开始时间", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "eTime", value = "结束时间", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "subjectIdList", value = "专题ID", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "isCommentData", value = "是否是评论数据 0否 1是", required = false, paramType = "query", dataType = "String") })
    public ApiResponseBody getOriCount(HttpServletRequest request, @ModelAttribute VideoInfo videoInfo)
            throws UserNoFoundException, SysException, ReportException {
        List<Integer> orientationList = videoInfo.getOrientationList();
        if (orientationList == null || orientationList.size() <= 0) {
            throw new ReportException("001", "倾向性不能为空");
        }
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.putAll(ParamsUtil.transToMAP(videoInfo, VideoInfo.class));
        Map<String, Object> result = this.videoInfoService.getOriCount(paramMap);
        return this.returnSuccess(result);
    }

    @ResponseBody
    @RequestMapping(value = "/getWordsCloud", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "获取关键词云图", httpMethod = "GET", response = ApiResponseBody.class, notes = "getWordsCloud", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sourceIdList", value = "来源类型 逗号分隔", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "orientationList", value = "倾向性 逗号分隔", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "bTime", value = "开始时间", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "eTime", value = "结束时间", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "subjectIdList", value = "专题ID", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "isCommentData", value = "是否是评论数据 0否 1是", required = false, paramType = "query", dataType = "String") })
    public ApiResponseBody getWordsCloud(HttpServletRequest request, @ModelAttribute VideoInfo videoInfo)
            throws UserNoFoundException, SysException, ReportException {
        List<Integer> orientationList = videoInfo.getOrientationList();
        if (orientationList == null || orientationList.size() <= 0) {
            throw new ReportException("001", "倾向性不能为空");
        }
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.putAll(ParamsUtil.transToMAP(videoInfo, VideoInfo.class));
        List<Map<String, Object>> result = this.videoInfoService.getWordsCloud(paramMap);
        return this.returnSuccess(result);
    }

}
