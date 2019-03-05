package com.zhxg.yqzj.web.api.v1;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.zhxg.yqzj.entities.v1.Consultation;
import com.zhxg.yqzj.entities.v1.Expert;
import com.zhxg.yqzj.service.exception.expert.ApplyConsultationException;
import com.zhxg.yqzj.service.exception.expert.ConsultationInApplyingException;
import com.zhxg.yqzj.service.exception.expert.ConsultationTimesRunOutException;
import com.zhxg.yqzj.service.v1.ConsultationService;
import com.zhxg.yqzj.service.v1.ExpertService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqzj.web.api
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 专家及咨询模块控制器
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqzj.web.api.v1.ExpertController.java
 * <p>
 * Author: azmiu
 * <p>
 * Create Date: 2017年8月24日
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
@RequestMapping("/v1/expert")
public class ExpertController extends BaseController<Expert> {

    @Autowired
    private ExpertService expertService;
    
    @Autowired
    private ConsultationService consultationService;


    @Override
    protected BaseService<Expert> getBaseService() {
        return this.expertService;
    }

    /**
     * 获取所有专家列表
     * @return json
     * @throws SysException 
     * @throws UserNoFoundException 
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取所有专家列表", httpMethod = "GET", response = ApiResponseBody.class, notes = "get experts", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageSize", value = "每页大小", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "pageNum", value = "当前页数", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "userid", value = "用户id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "type", value = "专家类型  2：处置专家 3：研判专家 4：媒体专家 5：金融专家 6：律师 7：其他", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "classtype", value = "专家分类  0：无 1：星光推荐", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "searchType", value = "搜索类型 1:全文检索  2：专家姓名 3：案例类型 4：地域 ", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "searchWord", value = "搜索词", required = false, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getExpertList(HttpServletRequest request,
    		@RequestParam(value = "type", required = false)  String type,
    		@RequestParam(value = "userid", required = false)  String userid,
    		@RequestParam(value = "classtype", required = false)  String classtype,
    		@RequestParam(value = "searchType", required = false)  String searchType,
    		@RequestParam(value = "searchWord", required = false)  String searchWord) throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("type", type);
        paramMap.put("userid", userid);
        paramMap.put("classtype", classtype);
        paramMap.put("searchType", searchType);
        paramMap.put("searchWord", searchWord);
        PageInfo<Expert> pageInfo = this.expertService.getExpertList(paramMap, PageUtil.getPageInfo(request));
        return this.returnSuccess(pageInfo);
    }
    /**
     * 获取专家详情
     * @return json
     * @throws SysException 
     * @throws UserNoFoundException 
     */
    @ResponseBody
    @RequestMapping(value = "/getExpertInfodetail", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取专家详情", httpMethod = "GET", response = ApiResponseBody.class, notes = "get expertInfodetail", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "expertid", value = "专家id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "userid", value = "userid", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody getExpertInfodetail(HttpServletRequest request,
            @RequestParam(value = "expertid", required = false)  String expertid,
            @RequestParam(value = "userid", required = false)  String userid) throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("expertid", expertid);
        paramMap.put("userid", userid);
        List<Expert> expert = this.expertService.getExpertInfodetail(paramMap);
        return this.returnSuccess(expert);
    }
    /**
     * 申请专家咨询
     * @param consultation
     * @return
     * @throws UserNoFoundException
     * @throws SysException
     * @throws ApplyConsultationException
     * @throws ConsultationInApplyingException 
     * @throws ConsultationTimesRunOutException 
     */
    @ResponseBody
    @RequestMapping(value = "/applyConsultation", method = RequestMethod.POST,consumes = "application/json")
    @ApiOperation(value = "申请专家咨询", httpMethod = "POST", response = ApiResponseBody.class, notes = "applyConsultation", produces = "application/json")
    public ApiResponseBody applyConsultation(@RequestBody Consultation consultation) throws UserNoFoundException, SysException, ApplyConsultationException, ConsultationInApplyingException, ConsultationTimesRunOutException { 
    	int result = consultationService.applyConsultation(consultation);
    	if(result>0){
    		return this.returnSuccess(null);
    	}else{
    		throw new ApplyConsultationException();
    	}
    }
    
    
    /**
     * 获取所有咨询列表
     * @return json
     * @throws SysException 
     * @throws UserNoFoundException 
     */
    @ResponseBody
    @RequestMapping(value = "/listConsultation/{userid}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取所有咨询列表", httpMethod = "GET", response = ApiResponseBody.class, notes = "get consultations", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageSize", value = "每页大小", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "pageNum", value = "当前页数", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "searchName", value = "专家名称搜索", required = false, paramType = "query", dataType = "String")
    })
    public ApiResponseBody listConsultation(HttpServletRequest request,@PathVariable("userid") String userid,
    		@RequestParam(value = "searchName", required = false)  String searchName) throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("searchName", searchName);
        paramMap.put("userid", userid);
        PageInfo<Consultation> pageInfo = this.consultationService.listConsultation(paramMap, PageUtil.getPageInfo(request));
        return this.returnSuccess(pageInfo);
    }
    
    /**
     * 获取已咨询条数及总条数
     * @return json
     * @throws SysException 
     * @throws UserNoFoundException 
     */
    @ResponseBody
    @RequestMapping(value = "/getConsultationCount/{userid}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取已咨询条数及总条数", httpMethod = "GET", response = ApiResponseBody.class, notes = "get consultation count", produces = "application/json")
    public ApiResponseBody getConsultationCount(HttpServletRequest request,@PathVariable("userid") String userid
    		) throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("userid", userid);
        Map<String,Integer> result = this.consultationService.getConsultationCount(paramMap);
        return this.returnSuccess(result);
    }
    
    
    /**
     * expired ! 专家支持的专家类型请自专家列表中取
     * 获取咨询类型
     * @return json
     * @throws SysException 
     * @throws UserNoFoundException 
     */
    @ResponseBody
    @RequestMapping(value = "/getConsultationType", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取咨询类型", httpMethod = "GET", response = ApiResponseBody.class, notes = "get consultationType", produces = "application/json")
   
    public ApiResponseBody getConsultationType(HttpServletRequest request) throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        List<Expert> expert = this.expertService.getConsultationType(paramMap);
        return this.returnSuccess(expert);
    } 
    
    
    /**
     * 获取所有专家类型
     * @return json
     * @throws SysException 
     * @throws UserNoFoundException 
     */
    @ResponseBody
    @RequestMapping(value = "/getAllExpertType", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取所有专家类型", httpMethod = "GET", response = ApiResponseBody.class, notes = "get allExpertType", produces = "application/json")
   
    public ApiResponseBody getAllExpertType(HttpServletRequest request) throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = getUserInfo(request);
        List<Expert> expert = this.expertService.getAllExpertType(paramMap);
        return this.returnSuccess(expert);
    } 
    
    

}
