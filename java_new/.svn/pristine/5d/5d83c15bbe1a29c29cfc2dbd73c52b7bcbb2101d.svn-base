package com.zhxg.yqzj.web.api.v1;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.zhxg.yqzj.entities.v1.SubjectUnselect;
import com.zhxg.yqzj.service.exception.eventanalysis.NumberException;
import com.zhxg.yqzj.service.exception.eventanalysis.ParseExpressionException;
import com.zhxg.yqzj.service.exception.user.UserExpiredException;
import com.zhxg.yqzj.service.v1.SubjectUnselectService;

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
@RequestMapping("/v1/subjectUnselect")
public class SubjectUnselectController extends BaseController<BaseEntity> {

    @Autowired
    private SubjectUnselectService subjectUnselectService;


    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.subjectUnselectService;
    }
    
    /**
     * 添加未选分类  或专题
     * @throws SysException 
     * @throws UserNoFoundException 
     * @throws NumberException 
     * @throws UserExpiredException 
     * @throws ParseExpressionException 
     */
    @ResponseBody
    @RequestMapping(value = "/addSubjectUnselect", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "添加未选分类或专题", httpMethod = "GET", response = ApiResponseBody.class, notes = "changeUserClassify", produces = "application/json")
    public ApiResponseBody addSubjectUnselect(HttpServletRequest request,
    		@RequestParam(value = "id", required = false)  String id,
            @RequestParam(value = "userid", required = true)  Integer userid,
            @RequestParam(value = "pid", required = true)  String pid,
            @RequestParam(value = "subids", required = false)  String subids,
            @RequestParam(value = "source", required = true)  String source) throws UserNoFoundException, SysException, UserExpiredException, NumberException, ParseExpressionException  { 
    	SubjectUnselect subjectUnselect = new SubjectUnselect();
    	if(!StringUtils.isEmpty(id)){
    		subjectUnselect.setId(Integer.parseInt(id));
    	}
    	subjectUnselect.setUserId(userid);
    	subjectUnselect.setPid(pid);
    	subjectUnselect.setSubids(subids);
    	subjectUnselect.setSource(source);
    	boolean result = subjectUnselectService.saveSubjectUnselect(subjectUnselect);
        return this.returnSuccess(result);            
    }
    
    /**
     * 查询未选分类或者专题
     * @throws SysException 
     * @throws UserNoFoundException 
     * @throws NumberException 
     * @throws UserExpiredException 
     * @throws ParseExpressionException 
     */
    @ResponseBody
    @RequestMapping(value = "/selectSubjectUnselect", method = RequestMethod.GET,consumes = "application/json")
    @ApiOperation(value = "查询未选分类或者专题", httpMethod = "GET", response = ApiResponseBody.class, notes = "changeUserClassify", produces = "application/json")
    public ApiResponseBody selectSubjectUnselect(HttpServletRequest request,
    		@RequestParam(value = "userid", required = true)  String userid,
            @RequestParam(value = "pid", required = true)  String pid,
            @RequestParam(value = "source", required = true)  String source) throws UserNoFoundException, SysException, UserExpiredException, NumberException, ParseExpressionException  { 
    	SubjectUnselect subjectUnselect = new SubjectUnselect();
    	subjectUnselect.setUserId(Integer.parseInt(userid));
    	subjectUnselect.setPid(pid);
    	subjectUnselect.setSource(source);
    	List<SubjectUnselect> list = subjectUnselectService.selectSubjectUnselectList(subjectUnselect);
        return this.returnSuccess(list);            
    }
    
}
