package com.zhxg.fgw.service;

import com.zhxg.fgw.entity.po.SysRole;
import com.zhxg.fgw.entity.po.SysUser;

import java.util.List;
import java.util.Map;

public interface UserService {

//    /**
//     * 查询用户列表
//     * @return
//     */
//    List<SysUser> findAllByDeptId(int deptId);

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    Map<String, Object> queryUserById(int userId);

    /**
     * 给用户授权
     * @param userId
     * @param roleId
     */
    void authorization(int userId, String roleId);

    /**
     * 根据deptId查询子部门和员工
     * @param deptId
     * @return
     */
    Map listDeptAndUser(int deptId);

    /**
     * 根据昵称查询用户
     * @param nickName
     * @return
     */
    List<SysUser> findUser(String nickName);
}
