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
import com.zhxg.yqzj.service.exception.DataReport.RegionReportInfoSaveException;
import com.zhxg.yqzj.service.exception.DataReport.SubjectReportInfoSaveException;
import com.zhxg.yqzj.service.v1.RegionReportInfoService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/app/regionReport")
public class RegionReportInfoAppController extends BaseController<BaseEntity> {

    @Autowired
    private RegionReportInfoService regionReportInfoService;
    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.regionReportInfoService;
    }

    /**
     * 添加用户自定义数据分类
     *
     * @param request
     * @param krUuids
     * @return
     * @throws UserNoFoundException
     * @throws SysException
     * @throws RegionReportInfoSaveException 
     */
    @ResponseBody
    @RequestMapping(value = "/saveRegionToReport", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "添加地域舆情数据到数据池", httpMethod = "GET", response = ApiResponseBody.class, notes = "saveRegionToReport", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "krUuids", value = "分类名称", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "provinceId", value = "省级ID", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "classifyId", value = "分类ID", required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code=2021003,message="地域舆情添加数据到数据池失败")
    })
    public ApiResponseBody saveRegionToReport(HttpServletRequest request,
            @RequestParam(value = "krUuids", required = true)  String krUuids,
            @RequestParam(value = "provinceId", required = true)  String provinceId,
            @RequestParam(value = "infoTime", required = true)  String infoTime,
            @RequestParam(value = "classifyId", required = true)  String classifyId
            ) throws UserNoFoundException, SysException, SubjectReportInfoSaveException, RegionReportInfoSaveException{
        Map<String, Object> paramMap = this.getUserInfo(request);
        String[] krUuidArr = null;
        if(StringUtils.isNotBlank(krUuids)){
            krUuidArr = krUuids.split(",");
        }else{
            throw new ParameterException("信息id不能为空"); 
        }
        paramMap.put("krUuidArr",krUuidArr);
        paramMap.put("krUuids",krUuids);
        paramMap.put("PROVINCEID",provinceId);
        paramMap.put("DBTABLETIME",infoTime);
        paramMap.put("classifyId",classifyId);
        Map<String, Integer> resultMap = regionReportInfoService.saveRegionToReport(paramMap);
        //保存专题
        return this.returnSuccess(resultMap);
    }
    
}
