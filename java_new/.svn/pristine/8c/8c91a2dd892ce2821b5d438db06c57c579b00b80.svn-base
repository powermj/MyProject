package com.zhxg.yqzj.web.api.v1;

import java.util.List;
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
import com.zhxg.yqzj.entities.v1.CollectInfo;
import com.zhxg.yqzj.service.exception.myfocus.RepeatOperateException;
import com.zhxg.yqzj.service.exception.solr.CollectException;
import com.zhxg.yqzj.service.v1.CollectInfoCntService;
import com.zhxg.yqzj.service.v1.CollectInfoService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/collect")
public class CollectInfoController extends BaseController<CollectInfo> {

    @Autowired
    CollectInfoService collectInfoService;
    @Autowired
    CollectInfoCntService collectInfoCntService;

    private static final String COLLECT_NAME_NOT_NULL = "收藏夹名称不能为空";
    private static final String COLLECTCNT_NAME_NOT_NULL = "收藏词名称不能为空";

    @Override
    protected BaseService<CollectInfo> getBaseService() {
        return this.collectInfoService;
    }

    /**
     * 添加收藏夹
     *
     * @throws SysException
     * @throws UserNoFoundException
     * @throws ParamsNullException
     * @throws RepeatOperateException
     * @throws CollectException
     */
    @ResponseBody
    @RequestMapping(value = "/addCollect", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "加收藏夹接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "addBriefing", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "collectName", value = "收藏夹名称", required = true, paramType = "query", dataType = "String") })
    public ApiResponseBody addCollect(HttpServletRequest request,
            @RequestParam(value = "collectName", required = true) String collectName)
            throws UserNoFoundException, SysException, RepeatOperateException, CollectException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        if (StringUtils.isBlank(collectName)) {
            // 列表名称不能为""
            throw new CollectException(COLLECT_NAME_NOT_NULL);
        }
        paramMap.put("collectName", collectName);
        int result = collectInfoService.insertCollectInfo(paramMap);
        return this.returnSuccess(result);
    }

    /**
     * 修改收藏夹
     *
     * @throws SysException
     * @throws UserNoFoundException
     * @throws CollectException
     * @throws ParamsNullException
     * @throws RepeatOperateException
     */
    @ResponseBody
    @RequestMapping(value = "/updateCollect", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "修改收藏夹接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "addBriefing", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "collectName", value = "收藏夹名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "collectId", value = "收藏夹ID", required = true, paramType = "query", dataType = "String") })
    public ApiResponseBody updateCollect(HttpServletRequest request,
            @RequestParam(value = "collectName", required = true) String collectName,
            @RequestParam(value = "collectId", required = true) String collectId)
            throws UserNoFoundException, SysException, CollectException, RepeatOperateException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        if (StringUtils.isBlank(collectName)) {
            // 列表名称不能为""
            throw new CollectException(COLLECT_NAME_NOT_NULL);
        }
        paramMap.put("collectName", collectName);
        paramMap.put("collectId", collectId);
        int result = collectInfoService.updateCollectInfo(paramMap);
        return this.returnSuccess(result);
    }

    /**
     * 删除收藏夹
     *
     * @throws SysException
     * @throws UserNoFoundException
     * @throws CollectException
     * @throws ParamsNullException
     * @throws RepeatOperateException
     */
    @ResponseBody
    @RequestMapping(value = "/deleteCollect", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "删除收藏夹接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "addBriefing", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "collectId", value = "收藏夹ID", required = true, paramType = "query", dataType = "String") })
    public ApiResponseBody deleteCollect(HttpServletRequest request,
            @RequestParam(value = "collectId", required = true) String collectId)
            throws UserNoFoundException, SysException, CollectException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("collectId", collectId);
        int result = collectInfoService.deleteCollectInfo(paramMap);
        return this.returnSuccess(result);
    }

    /**
     * 查询收藏夹及内词列表
     *
     * @throws SysException
     * @throws UserNoFoundException
     * @throws ParamsNullException
     * @throws RepeatOperateException
     */
    @ResponseBody
    @RequestMapping(value = "/selectCollect", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "查询收藏夹接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "selectCollect", produces = "application/json")
    public ApiResponseBody selectCollect(HttpServletRequest request)
            throws UserNoFoundException, SysException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        List<CollectInfo> collectList = collectInfoService.selectList(paramMap);
        return this.returnSuccess(collectList);
    }

    /**
     * 添加收藏夹内词
     *
     * @throws SysException
     * @throws UserNoFoundException
     * @throws ParamsNullException
     * @throws RepeatOperateException
     * @throws CollectException
     */
    @ResponseBody
    @RequestMapping(value = "/addContent", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "加收藏夹内词接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "addContent", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "collectId", value = "收藏夹ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "contentName", value = "收藏词名", required = true, paramType = "query", dataType = "String") })
    public ApiResponseBody addContent(HttpServletRequest request,
            @RequestParam(value = "contentName", required = true) String contentName,
            @RequestParam(value = "collectId", required = true) String collectId)
            throws UserNoFoundException, SysException, RepeatOperateException, CollectException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        if (StringUtils.isBlank(contentName)) {
            // 列表名称不能为""
            throw new CollectException(COLLECTCNT_NAME_NOT_NULL);
        }
        paramMap.put("contentName", contentName);
        paramMap.put("contentId", collectId);
        int result = collectInfoCntService.insertCollectInfoCnt(paramMap);
        return this.returnSuccess(result);
    }

    /**
     * 修改收藏词
     *
     * @throws SysException
     * @throws UserNoFoundException
     * @throws CollectException
     * @throws ParamsNullException
     * @throws RepeatOperateException
     */
    @ResponseBody
    @RequestMapping(value = "/updateContent", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "修改收藏夹内词接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "updateContent", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cntId", value = "收藏词ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "contentName", value = "收藏词名", required = true, paramType = "query", dataType = "String") })
    public ApiResponseBody updateContent(HttpServletRequest request,
            @RequestParam(value = "contentName", required = true) String contentName,
            @RequestParam(value = "cntId", required = true) String cntId)
            throws UserNoFoundException, SysException, CollectException, RepeatOperateException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        if (StringUtils.isBlank(contentName)) {
            // 列表名称不能为""
            throw new CollectException(COLLECTCNT_NAME_NOT_NULL);
        }
        paramMap.put("contentName", contentName);
        paramMap.put("cntId", cntId);
        int result = collectInfoCntService.updateCollectInfoCnt(paramMap);
        return this.returnSuccess(result);
    }

    /**
     * 删除收藏词
     *
     * @throws SysException
     * @throws UserNoFoundException
     * @throws ParamsNullException
     * @throws RepeatOperateException
     */
    @ResponseBody
    @RequestMapping(value = "/deleteContent", method = RequestMethod.GET, consumes = "application/json")
    @ApiOperation(value = "删除收藏夹内词接口", httpMethod = "GET", response = ApiResponseBody.class, notes = "deleteContent", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cntId", value = "收藏词ID", required = true, paramType = "query", dataType = "String") })
    public ApiResponseBody deleteContent(HttpServletRequest request,
            @RequestParam(value = "cntId", required = true) String cntId)
            throws UserNoFoundException, SysException {
        Map<String, Object> paramMap = this.getUserInfo(request);
        paramMap.put("cntId", cntId);
        int result = collectInfoCntService.deleteCollectInfoCnt(paramMap);
        return this.returnSuccess(result);
    }

}
