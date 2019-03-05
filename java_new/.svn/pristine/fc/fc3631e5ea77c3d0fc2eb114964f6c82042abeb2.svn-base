package com.zhxg.yqzj.app.api.v1;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.ParameterException;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.service.exception.DataReport.TopicReportInfoSaveException;
import com.zhxg.yqzj.service.v1.TopicReportInfoService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/app/topicReport")
public class TopicReportInfoAppController extends BaseController<BaseEntity> {

    @Autowired
    private TopicReportInfoService topicReportInfoService;
    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.topicReportInfoService;
    }

    /**
     * 添加用户自定义数据分类
     *
     * @param request
     * @param krUuids
     * @return
     * @throws UserNoFoundException
     * @throws SysException
     * @throws TopicReportInfoSaveException 
     */
    @ResponseBody
    @RequestMapping(value = "/saveTopicToReport", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "添加事件信息到数据池", httpMethod = "GET", response = ApiResponseBody.class, notes = "saveTopicToReport", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "uuids", value = "信息ID", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "enentId", value = "事件ID", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "classifyId", value = "分类ID", required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code=2021006,message="事件分析添加数据到数据池失败")
    })
    public ApiResponseBody saveTopicToReport(HttpServletRequest request,
            @RequestParam(value = "uuids", required = true)  String uuids,
            @RequestParam(value = "eventId", required = true)  String eventId,
            @RequestParam(value = "classifyId", required = true)  String classifyId
            ) throws UserNoFoundException, SysException, TopicReportInfoSaveException{
        Map<String, Object> paramMap = this.getUserInfo(request);
        String[] uuidArr = null;
        if(StringUtils.isNotBlank(uuids)){
            uuidArr = uuids.split(",");
        }else{
            throw new ParameterException("信息id不能为空"); 
        }
        paramMap.put("uuidArr",uuidArr);
        paramMap.put("uuids",uuids);
        paramMap.put("eventId",eventId);
        paramMap.put("classifyId",classifyId);
        Map<String, Integer> resultMap = topicReportInfoService.saveTopicToReport(paramMap);
        //保存专题
        return this.returnSuccess(resultMap);
    }
    
}
