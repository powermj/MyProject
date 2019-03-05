package com.zhxg.yqzj.web.api.v1;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.exception.ParamsNullException;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.framework.base.utils.ParamsUtil;
import com.zhxg.yqzj.entities.v1.ChannelTV;
import com.zhxg.yqzj.service.v1.ChannelTVService;

import io.swagger.annotations.ApiOperation;


 
/**
 * <p>
 * CopyRright (c)2012-2016:	qinshuan
 * <p>
 * Project:					yqms.web.api
 * <p>
 * Module ID:				<模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments:				<对此类的描述>
 * <p>
 * JDK version used:		JDK1.8
 * <p>
 * NameSpace:				com.zhxg.yqzj.web.api.v1.ChannelTVController.java
 * <p>
 * Author:					qinshuan
 * <p>
 * Create Date:				2018年4月19日
 * <p>
 * Modified By:				<修改人中文名或拼音缩写>
 * <p>
 * Modified Date:			<修改日期>
 * <p>
 * Why & What is modified:	<修改原因描述>
 * <p>
 * Version:					v1.0
*/ 
@RestController
@RequestMapping("/v1/channelTV")
public class ChannelTVController extends BaseController<ChannelTV> {

    private static final String KRUUID_ISNULL = "kruuid is null";

    @Autowired
    private ChannelTVService channelTVService;    

    @Override
    protected BaseService<ChannelTV> getBaseService() {
        return this.channelTVService;
    }
    
    /**
     * 上传电视监控视频信息
     * @throws SysException 
     * @throws UserNoFoundException 
     */
    @ResponseBody
    @RequestMapping(value = "/uploadTVMonitoringInformation", method = RequestMethod.POST,consumes = "application/json")
    @ApiOperation(value = "上传电视监控视频信息接口", httpMethod = "POST", response = ApiResponseBody.class, notes = "Upload TV monitoring information", produces = "application/json")
    public ApiResponseBody uploadTVMonitoringInformation(HttpServletRequest request,@RequestBody ChannelTV channelTV) throws UserNoFoundException, SysException   { 
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.putAll(ParamsUtil.transToMAP(channelTV,ChannelTV.class));
        int result=this.channelTVService.uploadTVMonitoringInformation(paramMap);
        return this.returnSuccess(result);
            
    }

    /**
     * 删除电视监控信息
     * 
     * @throws SysException
     * @throws UserNoFoundException
     * @throws ParamsNullException
     */
    @ResponseBody
    @RequestMapping(value = "/deleteTVMonitoringInformation", method = RequestMethod.POST, consumes = "application/json")
    @ApiOperation(value = "删除电视监控视频信息接口", httpMethod = "POST", response = ApiResponseBody.class, notes = "Delete TV monitoring information", produces = "application/json")
    public ApiResponseBody deleteTVMonitoringInformation(HttpServletRequest request, @RequestBody ChannelTV channelTV) throws UserNoFoundException, SysException, ParamsNullException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        if (null != channelTV && StringUtils.isNotBlank(channelTV.getKruuid())) {
            paramMap.put("krUuid", channelTV.getKruuid());
        } else {
            throw new ParamsNullException(KRUUID_ISNULL);
        }
        int result = this.channelTVService.deleteTVMonitoringInformation(paramMap);
        return this.returnSuccess(result);

    }
}
