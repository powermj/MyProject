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
import com.zhxg.yqzj.service.exception.DataReport.AttentionReportInfoSaveException;
import com.zhxg.yqzj.service.v1.AttentionReportInfoService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/app/attentionReport")
public class AttentionReportInfoAppController extends BaseController<BaseEntity> {

    @Autowired
    private AttentionReportInfoService attentionReportInfoService;
    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.attentionReportInfoService;
    }

    /**
     * 添加用户自定义数据分类
     *
     * @param request
     * @param krUuids
     * @return
     * @throws UserNoFoundException
     * @throws SysException
     * @throws AttentionReportInfoSaveException 
     */
    @ResponseBody
    @RequestMapping(value = "/saveAttentionToReport", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "添加关注信息到数据池", httpMethod = "GET", response = ApiResponseBody.class, notes = "saveAttentionToReport", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "kmUuids", value = "信息ID", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "classifyId", value = "分类ID", required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code=2021007,message="我的关注添加数据到数据池失败")
    })
    public ApiResponseBody saveAttentionToReport(HttpServletRequest request,
            @RequestParam(value = "kmUuids", required = true)  String kmUuids,
            @RequestParam(value = "classifyId", required = true)  String classifyId
            ) throws UserNoFoundException, SysException, AttentionReportInfoSaveException{
        Map<String, Object> paramMap = this.getUserInfo(request);
        String[] kmUuidArr = null;
        if(StringUtils.isNotBlank(kmUuids)){
            kmUuidArr = kmUuids.split(",");
        }else{
            throw new ParameterException("信息id不能为空"); 
        }
        paramMap.put("kmUuidArr",kmUuidArr);
        paramMap.put("kmUuids",kmUuids);
        paramMap.put("classifyId",classifyId);
        Map<String, Integer> resultMap = attentionReportInfoService.saveAttentionToReport(paramMap);
        //保存专题
        return this.returnSuccess(resultMap);
    }
    
}
