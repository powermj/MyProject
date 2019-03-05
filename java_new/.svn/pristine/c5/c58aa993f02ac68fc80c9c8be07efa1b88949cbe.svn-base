package com.zhxg.yqzj.web.api.v1;

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
import com.zhxg.yqzj.service.exception.DataReport.SubjectReportInfoSaveException;
import com.zhxg.yqzj.service.v1.SubjectReportInfoService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/subjectReport")
public class SubjectReportInfoController extends BaseController<BaseEntity> {

    @Autowired
    private SubjectReportInfoService subjectReportInfoService;
    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.subjectReportInfoService;
    }

    /**
     * 添加用户自定义数据分类
     *
     * @param request
     * @param krUuids
     * @return
     * @throws UserNoFoundException
     * @throws SysException
     * @throws SubjectReportInfoSaveException 
     */
    @ResponseBody
    @RequestMapping(value = "/saveSubjectToReport", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "添加舆情浏览数据到数据池", httpMethod = "GET", response = ApiResponseBody.class, notes = "saveSubjectToReport", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "krUuids", value = "信息ID", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "classifyId", value = "分类ID", required = true, paramType = "query", dataType = "Integer"),
    })
    @ApiResponses(value = {
            @ApiResponse(code=2021001,message="舆情浏览添加数据到数据池失败")
    })
    public ApiResponseBody saveSubjectToReport(HttpServletRequest request,
            @RequestParam(value = "krUuids", required = true)  String krUuids,
            @RequestParam(value = "classifyId", required = true)  String classifyId
            ) throws UserNoFoundException, SysException, SubjectReportInfoSaveException{
        Map<String, Object> paramMap = this.getUserInfo(request);
        String[] krUuidArr = null;
        if(StringUtils.isNotBlank(krUuids)){
            krUuidArr = krUuids.split(",");
        }else{
            throw new ParameterException("信息id不能为空"); 
        }
        paramMap.put("krUuidArr",krUuidArr);
        paramMap.put("classifyId",classifyId);
        paramMap.put("krUuids",krUuids);
        Map<String, Integer> resultMap = subjectReportInfoService.saveSubjectToReport(paramMap);
        //保存专题
        return this.returnSuccess(resultMap);
    }
    
}
