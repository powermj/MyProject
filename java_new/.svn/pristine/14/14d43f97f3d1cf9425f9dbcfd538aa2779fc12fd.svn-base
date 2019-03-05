package com.zhxg.framework.base.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.constants.SysConstants;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.http.Result;
import com.zhxg.framework.base.service.BaseService;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 控制层基础类
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.controller.BaseController.java
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
public abstract class BaseController<T extends BaseEntity> {

    /**
     * @Fields logger : 获得日志服务
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取基础服务
     * 
     * @return BaseService<T>
     */
    protected abstract BaseService<T> getBaseService();

    // TODO 由于数据库业务主键与物理主键混乱，基础的CURD操作有待调整
    // private String BASE_ENTITY_FIELD_ID = "id";
    // /**
    // * 添加单条数据
    // *
    // * @param t
    // * @return
    // */
    // @RequestMapping(value = "", method = RequestMethod.POST)
    // public @ResponseBody com.zhxg.framework.base.http.ResponseBody create(@RequestBody T t) {
    // com.zhxg.framework.base.http.ResponseBody responseBody = new com.zhxg.framework.base.http.ResponseBody();
    // Result result = new Result();
    // try{
    // T rt = this.getBaseService().create(t);
    // if (null != rt) {
    // result.setData(JSONObject.fromObject(rt));
    // responseBody.setResult(result);
    // responseBody = this.response(HttpStatusCode.HTTP_STATUS_CODE_200.getCODE(),
    // SysConstants.MSG_ADD_SUCCESS, result);
    // } else {
    // responseBody = this.response(HttpStatusCode.HTTP_STATUS_CODE_500.getCODE(),
    // SysConstants.MSG_ADD_ERROR, result);
    // }
    // } catch (Exception e) {
    // this.logger.error("{}" + e);
    // responseBody = this.response(HttpStatusCode.HTTP_STATUS_CODE_500.getCODE(),
    // SysConstants.MSG_ADD_ERROR, result);
    // }
    // return responseBody;
    // }
    //
    // /**
    // * 根据ID修改一条数据
    // *
    // * @param t
    // * @return
    // */
    // @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    // public @ResponseBody com.zhxg.framework.base.http.ResponseBody update(@RequestBody T t,
    // @PathVariable("id") String id) {
    // com.zhxg.framework.base.http.ResponseBody responseBody = new com.zhxg.framework.base.http.ResponseBody();
    // Result result = new Result();
    // try {
    // ReflectionUtils.setFieldValue(t, this.BASE_ENTITY_FIELD_ID, id);
    // int i = this.getBaseService().updateById(t);
    // if (1 == i) {
    // result.setData(JSONObject.fromObject(t));
    // responseBody.setResult(result);
    // responseBody = this.response(HttpStatusCode.HTTP_STATUS_CODE_200.getCODE(),
    // SysConstants.MSG_UPDATE_SUCCESS, result);
    // } else {
    // responseBody = this.response(HttpStatusCode.HTTP_STATUS_CODE_500.getCODE(),
    // SysConstants.MSG_UPDATE_ERROR, result);
    // }
    // } catch (Exception e) {
    // this.logger.error("{}" + e);
    // responseBody = this.response(HttpStatusCode.HTTP_STATUS_CODE_500.getCODE(),
    // SysConstants.MSG_UPDATE_ERROR, result);
    // }
    // return responseBody;
    // }
    //
    // /**
    // * 根据ID查询一条数据
    // *
    // * @param id
    // * @return
    // */
    // @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    // public @ResponseBody com.zhxg.framework.base.http.ResponseBody retrieveById(@PathVariable("id") String id) {
    // com.zhxg.framework.base.http.ResponseBody responseBody = new com.zhxg.framework.base.http.ResponseBody();
    // Result result = new Result();
    // try {
    // T rt = this.getBaseService().retrieveOneById(id);
    // if (null != rt) {
    // result.setData(JSONObject.fromObject(rt));
    // responseBody = this.response(HttpStatusCode.HTTP_STATUS_CODE_200.getCODE(),
    // SysConstants.MSG_SEARCH_SUCCESS, result);
    // } else {
    // responseBody = this.response(HttpStatusCode.HTTP_STATUS_CODE_500.getCODE(),
    // SysConstants.MSG_SEARCH_ERROR, result);
    // }
    // } catch (Exception e) {
    // this.logger.error("{}" + e);
    // responseBody.setStatus(HttpStatusCode.HTTP_STATUS_CODE_500.getCODE());
    // responseBody.setMsg(SysConstants.MSG_SEARCH_ERROR);
    // }
    // return responseBody;
    // }
    //
    // /**
    // * 根据ID删除一条数据
    // *
    // * @param id
    // * @return
    // */
    //
    //
    // @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    // public @ResponseBody com.zhxg.framework.base.http.ResponseBody delete(@PathVariable("id") String id) {
    // com.zhxg.framework.base.http.ResponseBody responseBody = new com.zhxg.framework.base.http.ResponseBody();
    // Result result = new Result();
    // try {
    // int i = this.getBaseService().deleteOneById(id);
    // if (1 == i) {
    // responseBody = this.response(HttpStatusCode.HTTP_STATUS_CODE_200.getCODE(),
    // SysConstants.MSG_DELETE_SUCCESS, result);
    // } else {
    // responseBody = this.response(HttpStatusCode.HTTP_STATUS_CODE_400.getCODE(),
    // SysConstants.MSG_DELETE_ERROR, result);
    // }
    // } catch (Exception e) {
    // this.logger.error("{}" + e);
    // responseBody = this.response(HttpStatusCode.HTTP_STATUS_CODE_500.getCODE(), SysConstants.MSG_DELETE_ERROR,
    // result);
    // }
    // return responseBody;
    // }

    /**
     * 公共处理request中的userid，根据userid获取该用户的dbName
     *
     * @param request
     * @return
     * @throws Exception
     */
    public Map<String, Object> getUserInfo(HttpServletRequest request) throws SysException, UserNoFoundException {
        Map<String, Object> retMap = null;
        try {
            String accountId = request.getHeader(SysConstants._USERID);
            String shareAccountId = request.getParameter("shareUserId");
            Map<String, Object> userIds = new HashMap<String, Object>();
            userIds.put("accountId", new String(Base64.decodeBase64(accountId)));
            userIds.put("shareAccountId", (shareAccountId == null ? "" : shareAccountId));
            this.logger.debug(this.getClass().getName() + ".getUserInfo:::changeDatasource params userInfo：" + userIds.toString());
            retMap = this.getBaseService().getUserInfoByUserIds(userIds);
            retMap.put(SysConstants._ACCOUNTID, new String(Base64.decodeBase64(accountId)));
            this.logger.debug(this.getClass().getName() + ".getUserInfo:::build params success");
        } catch (SysException sE) {
            this.logger.error(this.getClass().getName() + "getUserInfo:::build params error: " + sE.getMessage());
            throw sE;
        }
        return retMap;
    }
    
    /**
     * 公共处理request中的userid，根据userid获取该用户的dbName
     *
     * @param request
     * @return
     * @throws Exception
     */
    public Map<String, Object> getUserInfo(HttpServletRequest request,BaseEntity baseEntity) throws SysException, UserNoFoundException {
        Map<String, Object> retMap = null;
        try {
            String accountId = request.getHeader(SysConstants._USERID);
            String shareAccountId = request.getParameter("shareUserId");
            if(StringUtils.isBlank(shareAccountId)){
                shareAccountId = baseEntity.getShareUserId()+"";
            }
            Map<String, Object> userIds = new HashMap<String, Object>();
            userIds.put("accountId", new String(Base64.decodeBase64(accountId)));
            userIds.put("shareAccountId", (shareAccountId == null ? "" : shareAccountId));
            this.logger.debug(this.getClass().getName() + ".getUserInfo:::changeDatasource params userInfo：" + userIds.toString());
            retMap = this.getBaseService().getUserInfoByUserIds(userIds);
            retMap.put(SysConstants._ACCOUNTID, new String(Base64.decodeBase64(accountId)));
            this.logger.debug(this.getClass().getName() + ".getUserInfo:::build params success");
        } catch (SysException sE) {
            this.logger.error(this.getClass().getName() + "getUserInfo:::build params error: " + sE.getMessage());
            throw sE;
        }
        return retMap;
    }

    /**
     * 返回成功
     * 
     * @param t
     * @return
     */
    public ApiResponseBody returnSuccess(Object t) {
        ApiResponseBody responseBody = new ApiResponseBody();
        Result result = new Result();
        if (t instanceof PageInfo<?>) {
            if (!((PageInfo<?>) t).getList().isEmpty()) {
                result.setData(((PageInfo<?>) t).getList());
                result.setPageNum(((PageInfo<?>) t).getPageNum());
                result.setPageSize(((PageInfo<?>) t).getPageSize());
                result.setTotal(((PageInfo<?>) t).getTotal());
            }
        } else if(null != t) {
            result.setData(t);
        }
        responseBody.setResult(result);
        responseBody.setStatus(SysConstants._SUCCESS_CODE);
        responseBody.setMsg(SysConstants._SUCCESS_MSG);
        return responseBody;
    }
    
    public ApiResponseBody returnStatus(Map<String,Object> obj){
        ApiResponseBody responseBody = new ApiResponseBody();
        Result result = new Result();
        responseBody.setResult(result);
        responseBody.setStatus(String.valueOf(obj.get("result")));
        responseBody.setMsg(String.valueOf(obj.get("message")));
        return responseBody;
    }

}
