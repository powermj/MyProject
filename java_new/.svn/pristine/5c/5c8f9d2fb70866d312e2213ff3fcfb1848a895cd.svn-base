package com.zhxg.yqzj.web.api.v1;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhxg.framework.base.constants.SysConstants;
import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.MobileCloudCourse;
import com.zhxg.yqzj.entities.v1.MobileCloudIndustryInfo;
import com.zhxg.yqzj.entities.v1.MobileCloudRecommendTopic;
import com.zhxg.yqzj.entities.v1.MobileCloudRegionalInfo;
import com.zhxg.yqzj.service.exception.mobilecloud.MobileCloudDelUserInfoException;
import com.zhxg.yqzj.service.exception.mobilecloud.MobileCloudSaveSpecialInfoException;
import com.zhxg.yqzj.service.exception.mobilecloud.MobileCloudUpdateUserInfoException;
import com.zhxg.yqzj.service.exception.mobilecloud.MobileCloudUserInfoLoseException;
import com.zhxg.yqzj.service.v1.MobileCloudService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/mobileCloud")
public class MobileCloudController extends BaseController<BaseEntity> {

    @Autowired
    private MobileCloudService mobileCloudService;
    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.mobileCloudService;
    }

    /**
     * 查询行业信息
     *
     * @throws SysException
     * @throws UserNoFoundException
     */
    @ResponseBody
    @RequestMapping(value = "/getIndustryInfo", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "查询行业信息", httpMethod = "GET", response = ApiResponseBody.class, notes = "getIndustryInfo", produces = "application/json")
    public ApiResponseBody getIndustryInfo(HttpServletRequest request)
            throws UserNoFoundException, SysException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        List<MobileCloudIndustryInfo> allIndustryInfo = mobileCloudService.getAllIndustryInfo(paramMap);
        return this.returnSuccess(allIndustryInfo);
    }


    /**
     * 查询地域信息
     *
     * @throws SysException
     * @throws UserNoFoundException
     */
    @ResponseBody
    @RequestMapping(value = "/getRegionalInfo", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "查询地域信息", httpMethod = "GET", response = ApiResponseBody.class, notes = "getRegionalInfo", produces = "application/json")
    public ApiResponseBody getRegionalInfo(HttpServletRequest request)
            throws UserNoFoundException, SysException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        List<MobileCloudRegionalInfo> allRegionalInfo = mobileCloudService.getAllRegionalInfo(paramMap);
        return this.returnSuccess(allRegionalInfo);
    }

    /**
     * 查询推荐专题信息
     *
     * @throws SysException
     * @throws UserNoFoundException
     */
    @ResponseBody
    @RequestMapping(value = "/getRecommentTopic", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "查询专题信息", httpMethod = "GET", response = ApiResponseBody.class, notes = "getRecommentTopic", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "departmentId", value = "客户二级行业Id", required = false, paramType = "query", dataType = "String"),
    })
    public ApiResponseBody getRecommentTopic(HttpServletRequest request,@RequestParam(value = "departmentId", required = false)  String departmentId)
            throws UserNoFoundException, SysException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("departmentId", departmentId);
        List<MobileCloudRecommendTopic> allRecommendTopic = mobileCloudService.getAllRecommendTopic(paramMap);
        return this.returnSuccess(allRecommendTopic);
    }
    
    @ResponseBody
    @RequestMapping(value = "/setSpecialInfo", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "添加推荐专题", httpMethod = "GET", response = ApiResponseBody.class, notes = "setSpecialInfo", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "customerName", value = "客户名称", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "phoneNumber", value = "手机号", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "customerType", value = "用户类型", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "departmentId", value = "客户二级行业Id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "provinceId", value = "所属地域省Id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "cityId", value = "所属地域市Id", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "level", value = "所属地域等级", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "brandWord", value = "品牌词", required = false, paramType = "query", dataType = "String")
    })
    public ApiResponseBody setSpecialInfo(HttpServletRequest request,
            @RequestParam(value = "customerName", required = true)  String customerName,
            @RequestParam(value = "phoneNumber", required = false)  String phoneNumber,
            @RequestParam(value = "customerType", required = true)  String customerType,
            @RequestParam(value = "departmentId", required = true)  String departmentId,
            @RequestParam(value = "provinceId", required = true)  String provinceId,
            @RequestParam(value = "cityId", required = false)  String cityId,
            @RequestParam(value = "level", required = false)  String level,
            @RequestParam(value = "brandWord", required = false)  String brandWord)
            throws UserNoFoundException, SysException, MobileCloudUserInfoLoseException, 
            MobileCloudUpdateUserInfoException, MobileCloudDelUserInfoException, MobileCloudSaveSpecialInfoException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        String accesstoken = request.getHeader(SysConstants._ACCESSTOKEN);
        paramMap.put("customerName", customerName);
        paramMap.put("phoneNumber", phoneNumber);
        paramMap.put("customerType", customerType);
        paramMap.put("departmentId", departmentId);
        paramMap.put("provinceId", provinceId);
        paramMap.put("cityId", cityId);
        paramMap.put("level", level);
        paramMap.put("brandWord",brandWord);
        paramMap.put("accesstoken", accesstoken);
        //保存专题
        List<Map<String, Object>> backInfo = mobileCloudService.setSpecialInfo(paramMap);
        return this.returnSuccess(backInfo);
    }
    
    /**
     * 删除用户进入引导信息
     *
     * @throws SysException
     * @throws UserNoFoundException
     * @throws MobileCloudDelUserInfoException 
     */
    @ResponseBody
    @RequestMapping(value = "/delFirstGuideInfo", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "删除用户进入引导信息", httpMethod = "GET", response = ApiResponseBody.class, notes = "delFirstGuideInfo", produces = "application/json")
    public ApiResponseBody delFirstGuideInfo(HttpServletRequest request)
            throws UserNoFoundException, SysException, MobileCloudDelUserInfoException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        int result = mobileCloudService.delFirstGuideInfo(paramMap);
        return this.returnSuccess(result);
    }
    
    @ResponseBody
    @RequestMapping(value = "/setSpecialInfoForInstall", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "系统设置添加推荐专题", httpMethod = "GET", response = ApiResponseBody.class, notes = "setSpecialInfoForInstall", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "customerType", value = "用户类型", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "departmentId", value = "客户二级行业Id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "provinceId", value = "所属地域省Id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "cityId", value = "所属地域市Id", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "specialTopicId", value = "专题Id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "brandWord", value = "品牌词", required = false, paramType = "query", dataType = "String")
    })
    public ApiResponseBody setSpecialInfoForInstall(HttpServletRequest request,
            @RequestParam(value = "customerType", required = true)  String customerType,
            @RequestParam(value = "departmentId", required = true)  String departmentId,
            @RequestParam(value = "provinceId", required = true)  String provinceId,
            @RequestParam(value = "cityId", required = false)  String cityId,
            @RequestParam(value = "specialTopicId", required = true)  String specialTopicId,
            @RequestParam(value = "brandWord", required = false)  String brandWord)
            throws UserNoFoundException, SysException, MobileCloudUserInfoLoseException, 
            MobileCloudUpdateUserInfoException, MobileCloudDelUserInfoException, MobileCloudSaveSpecialInfoException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        String accesstoken = request.getHeader(SysConstants._ACCESSTOKEN);
        paramMap.put("departmentId", departmentId);
        paramMap.put("provinceId", provinceId);
        paramMap.put("cityId", cityId);
        String[] specialTopicIds = specialTopicId.split(",");
        paramMap.put("specialTopicIdArr",specialTopicIds);
        paramMap.put("brandWord",brandWord);
        paramMap.put("accesstoken", accesstoken);
        //保存专题
        List<Map<String, Object>> backInfo = mobileCloudService.setSpecialInfoForInstall(paramMap);
        return this.returnSuccess(backInfo);
    }
    
    /**
     * 获取舆情课程列表
     *
     * @throws SysException
     * @throws UserNoFoundException
     */
    @ResponseBody
    @RequestMapping(value = "/getCourseList", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "获取舆情课程列表", httpMethod = "GET", response = ApiResponseBody.class, notes = "getCourseList", produces = "application/json")
    public ApiResponseBody getCourseList(HttpServletRequest request)
            throws UserNoFoundException, SysException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        List<MobileCloudCourse> courseList = mobileCloudService.getCourseList(paramMap);
        return this.returnSuccess(courseList);
    }
    
    /**
     * 获取某个舆情课程所有信息
     *
     * @throws SysException
     * @throws UserNoFoundException
     */
    @ResponseBody
    @RequestMapping(value = "/getAllCourse", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "获取某个课程信息信息", httpMethod = "GET", response = ApiResponseBody.class, notes = "getAllCourse", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "courseId", value = "课程ID", required = false, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getAllCourse(HttpServletRequest request,
            @RequestParam(value = "courseId", required = true)  String courseId)
            throws UserNoFoundException, SysException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("courseId", courseId);
        List<MobileCloudCourse> allCourse = mobileCloudService.getAllCourse(paramMap);
        return this.returnSuccess(allCourse);
    }
    
    /**
     * 获取某个舆情课程所有信息
     *
     * @throws SysException
     * @throws UserNoFoundException
     */
    @ResponseBody
    @RequestMapping(value = "/getAllCoursewareInfo", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "获取某个课件详细信息", httpMethod = "GET", response = ApiResponseBody.class, notes = "getAllCoursewareInfo", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "courseId", value = "课程ID", required = false, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getAllCoursewareInfo(HttpServletRequest request,
            @RequestParam(value = "courseId", required = true)  String courseId)
            throws UserNoFoundException, SysException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("courseId", courseId);
        List<MobileCloudCourse> allCourse = mobileCloudService.getAllCoursewareInfo(paramMap);
        return this.returnSuccess(allCourse);
    }
}
