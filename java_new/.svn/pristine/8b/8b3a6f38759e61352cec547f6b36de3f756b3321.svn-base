package com.zhxg.fgw.controller;

import com.alibaba.fastjson.JSON;
import com.zhxg.fgw.Result;
import com.zhxg.fgw.controller.base.BaseAppController;
import com.zhxg.fgw.entity.po.SysUser;
import com.zhxg.fgw.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static com.zhxg.fgw.Result.ok;

@Api(tags = "用户管理")
@RestController("rhUserController")
@RequestMapping("/api/user")
public class UserController extends BaseAppController {
    @Resource
    private UserService userService;

//    /**
//     * 用户列表
//     * @return -
//     */
//    @ApiOperation(value = "用户列表", notes = "用户列表")
//    @RequestMapping(value = "/list/{deptId}",method = RequestMethod.GET)
//    public Result list(@ApiParam(required = true , value = "部门id") @PathVariable("deptId") int deptId) {
//        return ok(userService.findAllByDeptId(deptId));
//    }

    /**
     * 去往授权页面
     * @return -
     */
    @ApiOperation(value = "去往授权", notes = "去往授权")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result toAuthorization(@ApiParam(required = true,value = "用户id")  @PathVariable("id") int userId){
        //根据id查询用户和所有角色名称
        Map<String, Object> resultMap = userService.queryUserById(userId);
        return ok(JSON.toJSONString(resultMap));
    }

    /**
     * 给用户授权
     * @return -
     */
    @ApiOperation(value = "用户授权",notes = "用户授权")
    @RequestMapping(value = "/{userId}",method = RequestMethod.PUT)
    public Result authorization(@ApiParam(required = true,value = "用户id") @PathVariable("userId") int userId,
                                @ApiParam(required = true,value = "角色id")String roleId ){
            userService.authorization(userId,roleId);
            return ok();

    }

    /**
     * 组织架构
     * @param deptId
     * @return
     */
    @ApiOperation(value = "组织架构",notes = "组织架构")
    @RequestMapping(value = "/dept/{deptId}",method = RequestMethod.GET)
    public Result listDeptAndUser(@ApiParam(required = true,value = "部门id") @PathVariable("deptId") int deptId){
        Map map = userService.listDeptAndUser(deptId);
        return ok(JSON.toJSONString(map));
    }

    /**
     * 条件查询用户
     * @param nickName
     * @return
     */
    @ApiOperation(value = "根据登录账号或者昵称查询用户",notes = "根据登录账号或者昵称查询用户")
    @RequestMapping(value = "/findUser/{nickName}",method = RequestMethod.GET)
    public Result findUser(@ApiParam(required = true,value = "用户昵称") @PathVariable("nickName") String nickName){
        List<SysUser> sysUserList = userService.findUser(nickName);
        return null;
    }
}
