package com.zhxg.yqzj.app.api.v1;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.service.v1.ExportReportService;

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
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年4月04日
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
@RequestMapping("/app/exportReport")
public class ExportReportAppController extends BaseController {

    @Autowired
    ExportReportService exportReportService;

    @Override
    protected BaseService getBaseService() {
        return this.exportReportService;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/exportReportInfo", method = RequestMethod.POST, consumes = "application/json")
    public ApiResponseBody exportReportInfo(HttpServletRequest request,
            @RequestBody(required = true) Map<String, String> map)
            throws UserNoFoundException, SysException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        String path = exportReportService.exportReportInfo(request, map, paramMap);// 事件分析导出接口
        if (StringUtils.isNotBlank(path)) {
            return this.returnSuccess(path);
        } else {
            return this.returnStatus(null);
        }

    }
}
