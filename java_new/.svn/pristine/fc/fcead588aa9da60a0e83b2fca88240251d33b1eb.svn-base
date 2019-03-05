package com.zhxg.yqzj.web.api.v1;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.framework.base.utils.PageUtil;
import com.zhxg.yqzj.entities.v1.SourceType;
import com.zhxg.yqzj.entities.v1.TopicEventRegion;
import com.zhxg.yqzj.service.v1.ActiveMediaService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/activeMedia")
public class ActiveMediaController extends BaseController<BaseEntity> {

    @Autowired
    ActiveMediaService activeMediaService;
    
    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.activeMediaService;
    }

    @ResponseBody
    @RequestMapping(value = "/getAllSourceType", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "获取媒体类型及数量", httpMethod = "GET", response = ApiResponseBody.class, notes = "getAllSourceType", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "eventId", value = "事件ID", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "beginTime", value = "事件开始时间", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "endTime", value = "事件结束时间", required = true, paramType = "query", dataType = "String")})
    public ApiResponseBody getAllSourceType(HttpServletRequest request,
            @RequestParam(value = "eventId", required = true) String eventId,
            @RequestParam(value = "beginTime", required = false) String beginTime,
            @RequestParam(value = "endTime", required = false) String endTime) throws UserNoFoundException, SysException{
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("eventId", eventId);
        paramMap.put("beginTime", beginTime);
        paramMap.put("endTime", endTime);
        List<SourceType> allSourceType = activeMediaService.getAllSourceType(paramMap);
        return this.returnSuccess(allSourceType);
    }
    
    @ResponseBody
    @RequestMapping(value = "/getAllMediaInfo", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "活跃媒体列表", httpMethod = "GET", response = ApiResponseBody.class, notes = "getAllMediaInfo", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "eventId", value = "事件id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "sourceTypes", value = "媒体类型", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "regionIds", value = "地域id", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "beginTime", value = "事件开始时间", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "endTime", value = "事件结束时间", required = false, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getAllMediaInfo(HttpServletRequest request,
            @RequestParam(value = "eventId", required = true)  String eventId,
            @RequestParam(value = "sourceTypes", required = false)  String sourceTypes,
            @RequestParam(value = "regionIds", required = false)  String regionIds,
            @RequestParam(value = "beginTime", required = false) String beginTime,
            @RequestParam(value = "endTime", required = false) String endTime) throws UserNoFoundException, SysException { 
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("eventId", eventId);
        if(StringUtils.isNotBlank(sourceTypes)){
            String[] sourceTypesArr = sourceTypes.split(",");
            paramMap.put("sourceTypesArr",sourceTypesArr);
        }
        if(StringUtils.isNotBlank(regionIds)){
            String[] regionIdsArr = regionIds.split(",");
            paramMap.put("regionIdsArr", regionIdsArr);
        }
        paramMap.put("beginTime", beginTime);
        paramMap.put("endTime", endTime);
        
        PageInfo<TopicEventRegion> allMediaInfoList = activeMediaService.getAllMediaInfo(paramMap,PageUtil.getPageInfo(request));
        return this.returnSuccess(allMediaInfoList);
            
    }
    
    @ResponseBody
    @RequestMapping(value = "/exportAllMediaInfo", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "活跃媒体列表", httpMethod = "GET", response = ApiResponseBody.class, notes = "exportAllMediaInfo", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "eventId", value = "事件id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "beginTime", value = "事件开始时间", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "endTime", value = "事件结束时间", required = false, paramType = "query", dataType = "String")
    })
    public ApiResponseBody exportAllMediaInfo(HttpServletRequest request,
            @RequestParam(value = "eventId", required = true)  String eventId,
            @RequestParam(value = "beginTime", required = false) String beginTime,
            @RequestParam(value = "endTime", required = false) String endTime) throws UserNoFoundException, SysException { 
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("eventId", eventId);
        paramMap.put("beginTime", beginTime);
        paramMap.put("endTime", endTime);
        String path = activeMediaService.exportAllMediaInfo(paramMap);
        return this.returnSuccess(path);
            
    }
}
