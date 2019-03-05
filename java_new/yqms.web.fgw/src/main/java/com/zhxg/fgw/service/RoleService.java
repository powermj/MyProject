package com.zhxg.fgw.service;

import com.zhxg.fgw.entity.po.SysRole;

import java.util.List;

public interface RoleService {
    List<SysRole> findAll();

    /**
     * 添加角色
     * @param roleName -
     * @param roleDesc -
     * @param modules - "moduleId1, moduleId2, moduleId3, ..."
     */
    void addRole(String roleName, String roleDesc, String modules);

    /**
     * 修改角色
     * @param roleName -
     * @param roleDesc -
     * @param modules -
     */
    void modifyRole(long roleId, String roleName, String roleDesc, String modules);

    /**
     * 删除角色
     * @param roleId -
     */
    void deleteRole(long roleId);

    /**
     * 显示角色详情
     * @param roleId -
     * @return -
     */
    SysRole getRoleInfoList(long roleId);
}
