package com.zhxg.yqzj.web.api.v1;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.Subject;
import com.zhxg.yqzj.service.exception.eventanalysis.NumberException;
import com.zhxg.yqzj.service.exception.eventanalysis.ParseExpressionException;
import com.zhxg.yqzj.service.exception.user.UserExpiredException;
import com.zhxg.yqzj.service.v1.SubjectService;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.web.api
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户模块控制器
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.web.api.v1.UserController.java
 * <p>
 * Author: azmiu
 * <p>
 * Create Date: 2017年2月28日
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
@RequestMapping("/v1/subject")
public class SubjectController extends BaseController<BaseEntity> {

    @Autowired
    private SubjectService subjectService;


    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.subjectService;
    }
    
    /**
     * 修改专题分类接口
     * @throws SysException 
     * @throws UserNoFoundException 
     * @throws NumberException 
     * @throws UserExpiredException 
     * @throws ParseExpressionException 
     */
    @ResponseBody
    @RequestMapping(value = "/changeUserClassify", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "修改专题分类接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "changeUserClassify", produces = "application/json")
    public ApiResponseBody addEventAnalysis(HttpServletRequest request,
    		@RequestParam(value = "userid", required = true)  String userid,
            @RequestParam(value = "subjectId", required = true)  String subjectId,
            @RequestParam(value = "targetClassifyId", required = true)  String targetClassifyId) throws UserNoFoundException, SysException, UserExpiredException, NumberException, ParseExpressionException  { 
        Subject subject = new Subject();
        subject.setUserid(Integer.parseInt(userid));
        subject.setUuid(subjectId);
        boolean result = subjectService.changeUserClassify(subject, targetClassifyId);
        return this.returnSuccess(result);
            
    }
    
}
