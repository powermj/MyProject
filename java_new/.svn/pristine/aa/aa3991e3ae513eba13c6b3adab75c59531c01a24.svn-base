package com.zhxg.yqzj.app.api.v1;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.framework.base.utils.ParamsUtil;
import com.zhxg.yqzj.entities.v1.ShareInfo;
import com.zhxg.yqzj.service.v1.ShareInfoService;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqzj.web.api
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 信息分享接口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqzj.web.api.v1.ShareInfoController.java
 * <p>
 * Author: HAORAN
 * <p>
 * Create Date: 2018年7月21日
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
@RequestMapping("/app/shareInfo")
public class ShareInfoController extends BaseController<BaseEntity> {

    @Autowired
    ShareInfoService shareInfoService;

    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return this.shareInfoService;
    }

    @ResponseBody
    @RequestMapping(value = "/share", method = RequestMethod.POST)
    @ApiOperation(value = "信息分享", httpMethod = "POST", response = ApiResponseBody.class, notes = "appImportData", produces = "application/json")
    public ApiResponseBody appImportData(HttpServletRequest request,@RequestBody ShareInfo shareInfo)
            throws UserNoFoundException, SysException, IOException {
        // 获取公共信息
        Map<String, Object> paramMap = this.getUserInfo(request,shareInfo);
        paramMap.putAll(ParamsUtil.transToMAP(shareInfo,ShareInfo.class));
        ParamsUtil.transOtherToSelf(paramMap);
        Map<String,Object> result = shareInfoService.shareInfo(paramMap,request.getSession().getServletContext());
        return this.returnSuccess(result);
    }

    
}
