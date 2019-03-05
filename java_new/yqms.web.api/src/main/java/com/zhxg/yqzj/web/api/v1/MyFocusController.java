package com.zhxg.yqzj.web.api.v1;

import io.swagger.annotations.ApiOperation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.ParamsNullException;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.framework.base.utils.ParamsUtil;
import com.zhxg.yqzj.entities.v1.MyFocus;
import com.zhxg.yqzj.service.exception.myfocus.MyFocusInfoNotFoundException;
import com.zhxg.yqzj.service.exception.myfocus.RepeatOperateException;
import com.zhxg.yqzj.service.exception.myfocus.SendEmailErrorException;
import com.zhxg.yqzj.service.v1.MyFocusService;

/**
 * <p>Description: </p>
 * @author zyl
 * @date 2017年11月8日
 * @version 1.0
 */
@Controller
@RequestMapping("/v1/myFocus")
public class MyFocusController extends BaseController<BaseEntity>{
    @Autowired
    private MyFocusService myFocusService;
    
    private static final String KM_ID_IS_NULL = "关注信息id不能为空";
    private static final String KY_ID_IS_NULL = "简报id不能为空";
    private static final String REASON_IS_NULL = "原因不能为空";
    private static final String SEND_EMAIL_IS_NULL = "邮箱不能为空";
    
    @ResponseBody
    @RequestMapping(value = "/addYqjb", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "添加到简报", response = ApiResponseBody.class, notes = "post addYqjb", produces = "application/json")
    public ApiResponseBody addYqjb(@RequestBody MyFocus myFocus,HttpServletRequest request) throws UserNoFoundException, ParamsNullException, MyFocusInfoNotFoundException, RepeatOperateException, SysException{
        Map<String,Object> params = getUserInfo(request);
        params.putAll(ParamsUtil.transToMAP(myFocus,MyFocus.class));
        String kmIds = myFocus.getKmIds();//关注信息id
        String kyId = myFocus.getKyId();//简报id
        
        if(StringUtils.isEmpty(kmIds)){//关注id不能为空
            throw new ParamsNullException(KM_ID_IS_NULL);
        }
        if(StringUtils.isEmpty(kyId)){//简报id不能为空
            throw new ParamsNullException(KY_ID_IS_NULL);
        }
        myFocusService.addYqjb(params);
        return this.returnSuccess(null);
    }
    
//    @ResponseBody
//    @RequestMapping(value = "/addTopic", method = RequestMethod.POST, produces = "application/json")
//    @ApiOperation(value = "添加到话题", response = ApiResponseBody.class, notes = "post addTopic", produces = "application/json")
//    public ApiResponseBody saveTopic(@RequestBody Topic topic,HttpServletRequest request) throws SysException, UserNoFoundException{
//        Map<String,Object> resultMap = new HashMap<>();
//        Map<String,Object> params = getUserInfo(request);
//        params.putAll(ParamsUtil.transToMAP(topic,Topic.class));
//        
//        String ktName = topic.getKtName();//话题名称
//        String ktSummary = topic.getKtSummary();//话题概述
//        String kkName1 = topic.getKkName1();//关键词
//        String ktBeginTime = topic.getKtBeginTime();//话题开始时间
//        String ktEndTime = topic.getKtEndTime();//话题结束时间
//        
//        if(StringUtils.isEmpty(ktName)){
//            resultMap.put("result", CodeEnum.TERRITORY_KT_NAME_IS_NULL.getCode());
//            resultMap.put("message", CodeEnum.TERRITORY_KT_NAME_IS_NULL.getMsg());
//            return this.returnStatus(resultMap);
//        }
//        if(StringUtils.isEmpty(ktSummary)){
//            resultMap.put("result", CodeEnum.TERRITORY_KT_SUMMARY_IS_NULL.getCode());
//            resultMap.put("message", CodeEnum.TERRITORY_KT_SUMMARY_IS_NULL.getMsg());
//            return this.returnStatus(resultMap);
//        }
//        if(StringUtils.isEmpty(kkName1)){
//            resultMap.put("result", CodeEnum.TERRITORY_KK_NAME1_IS_NULL.getCode());
//            resultMap.put("message", CodeEnum.TERRITORY_KK_NAME1_IS_NULL.getMsg());
//            return this.returnStatus(resultMap);
//        }
//        if(StringUtils.isEmpty(ktBeginTime)){
//            resultMap.put("result", CodeEnum.TERRITORY_KT_BEGINTIME_IS_NULL.getCode());
//            resultMap.put("message", CodeEnum.TERRITORY_KT_BEGINTIME_IS_NULL.getMsg());
//            return this.returnStatus(resultMap);
//        }
//        if(StringUtils.isEmpty(ktEndTime)){
//            resultMap.put("result", CodeEnum.TERRITORY_KT_ENDTIME_IS_NULL.getCode());
//            resultMap.put("message", CodeEnum.TERRITORY_KT_ENDTIME_IS_NULL.getMsg());
//            return this.returnStatus(resultMap);
//        }
//        return this.returnStatus(myFocusService.saveTopic(params));
//    }
    
    @ResponseBody
    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "上报邮箱", response = ApiResponseBody.class, notes = "post sendEmail", produces = "application/json")
    public ApiResponseBody sendEmail(@RequestBody MyFocus myFocus,HttpServletRequest request) throws SysException, UserNoFoundException, ParamsNullException, MyFocusInfoNotFoundException,SendEmailErrorException{
        Map<String,Object> params = getUserInfo(request);
        params.putAll(ParamsUtil.transToMAP(myFocus,MyFocus.class));
        String kmIds = myFocus.getKmIds(); //关注信息id
        String reason = myFocus.getReason();//上报原因
        String email = myFocus.getEmail(); //邮箱地址，多个都好分隔开
        
        if(StringUtils.isEmpty(kmIds)){//信息id不能为空
            throw new ParamsNullException(KM_ID_IS_NULL);
        }
        if(StringUtils.isEmpty(reason)){//原因不能为空
            throw new ParamsNullException(REASON_IS_NULL);
        }
        if(StringUtils.isEmpty(email)){//邮箱不能为空
            throw new ParamsNullException(SEND_EMAIL_IS_NULL);
        }
        myFocusService.addSendEmail(params);
        return this.returnSuccess(null);
    }
    
    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.myFocusService;
    }
}
