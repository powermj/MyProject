package com.zhxg.yqzj.app.api.v1;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.framework.base.utils.ParamsUtil;
import com.zhxg.yqzj.entities.v1.AppImportData;
import com.zhxg.yqzj.entities.v1.ShareInfo;
import com.zhxg.yqzj.entities.v1.UsageStatistics;
import com.zhxg.yqzj.entities.v1.UsageStatisticsList;
import com.zhxg.yqzj.service.v1.AppImportDataService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import sun.misc.BASE64Decoder;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqzj.web.api
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: app上传附件
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
@RequestMapping("/app/import")
public class AppImportController extends BaseController<AppImportData> {

    @Autowired
    AppImportDataService appImportDataService;

    @Override
    protected BaseService<AppImportData> getBaseService() {
        return this.appImportDataService;
    }

    @ResponseBody
    @RequestMapping(value = "/appImportData", method = RequestMethod.POST)
    @ApiOperation(value = "APP导入接口", httpMethod = "POST", response = ApiResponseBody.class, notes = "appImportData", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appWords", value = "文字描述", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "appUserId", value = "用户Id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "model", value = "机型", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "edition", value = "版本信息", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "valid", value = "是否有效", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "appUserName", value = "用户帐户名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "images", value = "图片", required = false, paramType = "query", dataType = "String")
    })
    public ApiResponseBody appImportData(HttpServletRequest request,
            @RequestParam(value = "appWords", required = true) String appWords,
            @RequestParam(value = "appUserId", required = true) String appUserId,
            @RequestParam(value = "model", required = false) String model,
            @RequestParam(value = "edition", required = false) String edition,
            @RequestParam(value = "valid", required = false) String valid,
            @RequestParam(value = "appUserName", required = true) String appUserName,
            @RequestParam(value = "images", required = false) String[] images)
            throws UserNoFoundException, SysException, IOException {
        // 获取公共信息
        Map<String, Object> paramMap = this.getUserInfo(request);

        // 接收安卓参数
        String anzhuo = request.getParameter("androidImages");
        if (images == null && anzhuo != null) {
            // 获取图片
            if (!anzhuo.equals("[]")) {
                String subString = anzhuo.substring(1, anzhuo.lastIndexOf("]"));
                String stringArr = subString.replace("\"", "");
                images = stringArr.split(",");
            }
            // 获取中文文字
            appWords = changeToStr(appWords);
            appUserName = changeToStr(appUserName);
        }
        // 设置参数
        paramMap.put("appWords", appWords);
        paramMap.put("appUserId", appUserId);
        paramMap.put("model", model);
        paramMap.put("edition", edition);
        paramMap.put("appUserName", appUserName);
        paramMap.put("valid", "1");

        SimpleDateFormat tempDate = new SimpleDateFormat("yyyyMMddHHmmss");
        String datetime = tempDate.format(new java.util.Date());
        paramMap.put("inputDate", datetime);

        // 获取音频文件
        MultipartFile music = null;
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            music = multipartRequest.getFile("fid");
        }
        // 调用业务
        int result = appImportDataService.importAppData(paramMap, music, images);
        // JSONObject json = new JSONObject();
        // json.put("status", status);// 状态由service返回 success成功 falid失败
        return this.returnSuccess(result);
    }

    /**
     * 将安卓端传来的base64加密文字进行解码操作并转成字符串
     * 
     * @param str
     * @return androidStr
     */
    public String changeToStr(String str) {
        byte[] b = null;
        try {
            b = new BASE64Decoder().decodeBuffer(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String androidStr = new String(b);
        return androidStr;
    }
    
    @ResponseBody
    @RequestMapping(value = "/saveAppUsageStatistics", method = RequestMethod.POST)
    @ApiOperation(value = "APP使用情况监测", httpMethod = "POST", response = ApiResponseBody.class, notes = "saveAppUsageStatistics", produces = "application/json")
    public ApiResponseBody saveAppUsageStatistics(HttpServletRequest request,@RequestBody UsageStatisticsList usageStatisticsList)
            throws UserNoFoundException, SysException {
        // 获取公共信息
//        Map<String, Object> paramMap = this.getUserInfo(request,usageStatistics);
//        paramMap.putAll(ParamsUtil.transToMAP(usageStatistics,UsageStatistics.class));
//        ParamsUtil.transOtherToSelf(paramMap);
        appImportDataService.saveAppUsageStatistics(usageStatisticsList.getUsageStatistics());
        return this.returnSuccess(1);
    }
}
