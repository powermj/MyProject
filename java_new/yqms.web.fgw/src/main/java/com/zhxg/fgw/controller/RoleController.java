package com.zhxg.fgw.controller;

import com.alibaba.fastjson.JSON;
import com.zhxg.fgw.Result;
import com.zhxg.fgw.controller.base.BaseAppController;
import com.zhxg.fgw.entity.po.SysRole;
import com.zhxg.fgw.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.zhxg.fgw.Result.fail;
import static com.zhxg.fgw.Result.ok;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/api/role")
public class RoleController extends BaseAppController {
    @Resource
    private RoleService roleService;

    /**
     * 角色列表
     * @return -
     */
    @ApiOperation(value = "角色列表", notes = "角色列表")
    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public Result list() {
        return ok(roleService.findAll());
    }

    @ApiOperation(value = "添加角色", notes = "添加角色")
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@ApiParam(required = true, value = "角色名称") String roleName,
                      @ApiParam(required = true, value = "描述") String roleDesc,
                      @ApiParam(required = true, value = "权限，逗号分割", example = "1, 2") String modules) {
        if (StringUtils.isBlank(roleName)) {
            return fail("角色名称不能为空");
        }
        if (StringUtils.isBlank(modules)) {
            return fail("未设置权限");
        }

        roleService.addRole(roleName, roleDesc, modules);
        return ok();
    }

    /**
     * 显示角色详情信息
     * @return -
     */
    @ApiOperation(value = "显示角色信息", notes = "显示角色信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result roleInfoList(@ApiParam(required = true,value = "角色id") @PathVariable("id") long roleId){
        SysRole sysRole = roleService.getRoleInfoList(roleId);
        return ok(JSON.toJSONString(sysRole));
    }

    /**
     * 修改角色
     * @return -
     */
    @ApiOperation(value = "修改角色", notes = "修改角色")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result modifyRole(@ApiParam(required = true, value = "角色id") @PathVariable("id") long roleId,
                             @ApiParam(required = true, value = "角色名称") String roleName,
                             @ApiParam(required = true, value = "描述") String roleDesc,
                             @ApiParam(required = true, value = "权限，逗号分割", example = "1, 2") String modules){
        if (StringUtils.isBlank(modules)){
            return fail("权限不能为空");
        }
        roleService.modifyRole(roleId,roleName, roleDesc, modules);
        return ok();

    }

    /**
     * 删除角色
     * @return -
     */
    @ApiOperation(value = "删除角色", notes = "删除角色")
    @RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
    public Result deleteRole(@ApiParam(required = true, value = "角色id") @PathVariable("id") long roleId){

        roleService.deleteRole(roleId);
        return ok();

    }
}
