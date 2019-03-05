package com.zhxg.framework.base.interceptor;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zhxg.framework.base.constants.SysConstants;
import com.zhxg.framework.base.utils.Md5Utils;
import com.zhxg.framework.base.utils.RedisUtil;


/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 访问拦截器
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.filter.AuthorityTokenInterceptor.java
 * <p>
 * Author: azmiu
 * <p>
 * Create Date: 2017年3月17日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public class AppAuthorityTokenInterceptor implements HandlerInterceptor {

    /**
     * @Fields logger : 获得日志服务
     */
    protected Logger logger = LoggerFactory.getLogger(AppAuthorityTokenInterceptor.class);

    private static final String TOKEN_FIX = "userid_";
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {   
        // 访问令牌即目前session
        String accessToken = request.getHeader(SysConstants._ACCESSTOKEN);
        // 当前用户ID
        String accountId = request.getHeader(SysConstants._USERID);
        // request.setAttribute("accessToken", accessToken);
        if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(accountId)) {
            this.logger.error("access_token or userId is null");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print("{\"msg\":\"missing accessToken or userId\",\"status\":\"1000401\"}");
            return false;
//            throw new AuthorizationException("missing accessToken or userId");
        } else {
        	String userId =new String(Base64.decodeBase64(accountId)).toLowerCase().replace("u", "");
            String md5Tmp = Md5Utils.encoding(TOKEN_FIX + accessToken).toLowerCase();
            String cacheToken = null;
            try {
            	String key=userId+"_"+accessToken;
            	cacheToken=RedisUtil.getStr(key);
            	if(StringUtils.isBlank(cacheToken)){
            		cacheToken = RedisUtil.getStr(md5Tmp);
            	}else{
                    md5Tmp = key;
                }
            	//临时处理
//            	if(StringUtils.isBlank(cacheToken)){
//            	    Set<String> keys = RedisUtil.keys(2, "*_"+accessToken);
//            	    if(!keys.isEmpty()){
//            	        cacheToken=RedisUtil.getStr(keys.iterator().next());
//            	    }
//                }
            	if(request.getRequestURI().contains("selectEventAnalysisCount")
                        ||request.getRequestURI().contains("selectEventAnalysisDataList")
                        ||request.getRequestURI().contains("selectCountGroupBySourceType")
                        ||request.getRequestURI().contains("selectCountGroupByOrientation")
                        ||request.getRequestURI().contains("selectCountGroupByIsRepeat")
                        ||request.getRequestURI().contains("exportEventAnalysisDataList")
                        ||request.getRequestURI().contains("selectEventAnalysisDataDetail")
                        ||request.getRequestURI().contains("selectSameEventAnalysisDataList")){
                    return true;
                }
            	
            } catch (Exception e) {
                this.logger.error("invalid accessToken:::" + e);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().print("{\"msg\":\"missing accessToken or userId\",\"status\":\"1000401\"}");
                return false;
//                throw new AuthorizationException("missing accessToken or userId");
            }
            if (accessToken.equals(cacheToken)) {//秘书token校验方式
                RedisUtil.setExpire(md5Tmp, 7, TimeUnit.DAYS);//秘书token目前不加过期时间
                return true;
            } else {//如果秘书token未校验通过 使用专家token校验方式
            	try {
                    cacheToken = RedisUtil.getStr(SysConstants.ACCESSTOKEN_DB,SysConstants.PRODUCT_APP_PREFIX+new String(Base64.decodeBase64(accountId)));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            	if(cacheToken==null){
            	    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().print("{\"msg\":\"认证失败，请重新登录\",\"status\":\"1000402\"}");
                    return false;
//            	    throw new AuthorizationException(SysConstants._AUTHENTICATION_FAILED);
            	}
            	if (accessToken.equals(cacheToken)) {
            		return true;
            	}else{
            	  //异地登录 或 主动退出错误码 402
            	    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().print("{\"msg\":\"认证失败，请重新登录\",\"status\":\"1000402\"}");
                    return false;
//            	    throw new AuthorizationException(String.valueOf(HttpServletResponse.SC_PAYMENT_REQUIRED),SysConstants._AUTHENTICATION_FAILED);
            	}
               
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        logger.debug("nothing todo.");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        logger.debug("nothing todo.");
    }
}
