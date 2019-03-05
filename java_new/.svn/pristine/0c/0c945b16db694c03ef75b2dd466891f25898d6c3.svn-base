package com.zhxg.yqzj.app.api.v1;

import java.util.HashMap;
import java.util.Map;

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
import com.zhxg.yqzj.service.v1.VersionUpgradeService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @param
 * @date 2017年6月30日上午9:58:31 
 *
 * @return 
 */

@RestController
@RequestMapping("/app/versionUpgrade")
public class VersionUpgradeAppController extends BaseController<BaseEntity>{

    @Autowired
    private VersionUpgradeService versionUpgradeService;


    @ResponseBody
    @RequestMapping(value = "/updateAppVersion", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "app版本更新", response = ApiResponseBody.class, notes = "update App Version", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "账号ID", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "version", value = "版本号", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "platform", value = "产品ID", required = true, paramType = "query", dataType = "String")
    })
    public ApiResponseBody updateAppVersion(HttpServletRequest request,@RequestParam(value = "userId", required = true)  String userId,
            @RequestParam(value = "version", required = true)  String version,
            @RequestParam(value = "platform", required = true)  String platform)
            throws SysException, UserNoFoundException {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        paramMap.put("version", version);
        paramMap.put("platform", platform);
        Map<String, Object> map = versionUpgradeService.updateAppVersion(paramMap);
        if(map == null){
            return returnSuccess(null);
        }
        return this.returnSuccess(map);
    }

    @Override
    protected BaseService<BaseEntity> getBaseService() {
        // TODO Auto-generated method stub
        return null;
    }

}
