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
import com.zhxg.framework.base.exception.ParamsNullException;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.EventAnalysis;
import com.zhxg.yqzj.service.exception.eventanalysis.NumberException;
import com.zhxg.yqzj.service.exception.myfocus.MyFocusInfoNotFoundException;
import com.zhxg.yqzj.service.exception.myfocus.SendEmailErrorException;
import com.zhxg.yqzj.service.exception.user.UserExpiredException;
import com.zhxg.yqzj.service.v1.AddSendMailService;
import com.zhxg.yqzj.service.v1.EventAnalysisService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * <p>
 * CopyRright (c)2012-2016: qinshuan
 * <p>
 * Project:                 yqms.web.api
 * <p>
 * Module ID:               <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments:                <事件分析模块>
 * <p>
 * JDK version used:        JDK1.8
 * <p>
 * NameSpace:               com.zhxg.yqzj.web.api.v1.EventAnalysisController.java
 * <p>
 * Author:                  qinshuan
 * <p>
 * Create Date:             2018年3月6日
 * <p>
 * Modified By:             <修改人中文名或拼音缩写>
 * <p>
 * Modified Date:           <修改日期>
 * <p>
 * Why & What is modified:  <修改原因描述>
 * <p>
 * Version:                 v1.0
*/ 
@RestController
@RequestMapping("/v1/addSendMail")
public class AddSendMailController extends BaseController<EventAnalysis> {
    
    private static final String KM_ID_IS_NULL = "事件分析信息id不能为空";
    private static final String SEND_EMAIL_IS_NULL = "邮箱不能为空";

    @Autowired
    private EventAnalysisService eventAnalysisService; 
    
    @Autowired
    private AddSendMailService addSendMailService;
    
    @Override
    protected BaseService<EventAnalysis> getBaseService() {
        return this.eventAnalysisService;
    }
    
    /**
     * 上报邮箱
     * @throws SysException 
     * @throws UserNoFoundException 
     * @throws NumberException 
     * @throws UserExpiredException 
     */
    @ResponseBody
    @RequestMapping(value = "/sendEmail", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "上报邮箱", response = ApiResponseBody.class, notes = "post sendEmail", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "eventAnalysisId", value = "事件分析id", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "infoIds", value = "信息id串", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "email", value = "上报邮箱串", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "reason", value = "上报原因", required = false, paramType = "query", dataType = "String")       
    })
    public ApiResponseBody sendEmail(HttpServletRequest request,
            @RequestParam(value = "eventAnalysisId", required = true)  String eventAnalysisId,
            @RequestParam(value = "infoIds", required = true)  String infoIds,
            @RequestParam(value = "email", required = true)  String email,
            @RequestParam(value = "reason", required = false)  String reason
            ) throws SysException, UserNoFoundException, ParamsNullException, MyFocusInfoNotFoundException,SendEmailErrorException{
        Map<String, Object> paramMap = getUserInfo(request);
        paramMap.put("eventAnalysisId", eventAnalysisId);
        if(StringUtils.isEmpty(infoIds)){//信息id不能为空
            throw new ParamsNullException(KM_ID_IS_NULL);
        }
        if(StringUtils.isEmpty(email)){//邮箱不能为空
            throw new ParamsNullException(SEND_EMAIL_IS_NULL);
        }

        paramMap.put("infoIds", infoIds);
        paramMap.put("reason", reason);
        paramMap.put("email", email);
        addSendMailService.addSendEmail(paramMap);
        return this.returnSuccess(null);
    }

    
}
