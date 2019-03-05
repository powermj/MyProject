package com.zhxg.fgw.service.impl;
import com.zhxg.fgw.entity.po.SysRole;
import com.zhxg.fgw.entity.vo.SysRoleVo;
import com.zhxg.fgw.service.RoleService;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Stream;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    public JdbcTemplate jdbcTemplate;

    @Override
    public List<SysRole> findAll() {
        return jdbcTemplate.query("select * from sys_role", BeanPropertyRowMapper.newInstance(SysRole.class));
    }

    @Override
    @Transactional
    public void addRole(String roleName, String roleDesc, String modules) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            int idx = 1;
            PreparedStatement ps = connection.prepareStatement("insert into sys_role(role_name, role_desc, flag, create_time) VALUES (?, ?, 1, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(idx++, roleName);
            ps.setString(idx++, roleDesc);
            ps.setDate(idx, new java.sql.Date(System.currentTimeMillis()));
            return ps;
        }, keyHolder);

        long roleId = keyHolder.getKey().longValue();

        addRoleModules(roleId, modules);
    }

    @Override
    @Transactional
    public void modifyRole(long roleId,String roleName, String roleDesc, String modules) {

        //更新角色表
        jdbcTemplate.update("update sys_role set role_name = ? , role_desc = ? where role_id = ?", roleName, roleDesc, roleId);

        //删除角色_权限关联表中原有的数据
        jdbcTemplate.update("delete from sys_role_module where role_id = ?", roleId);

        //插入角色更新的权限信息
        addRoleModules(roleId, modules);
    }

    /**
     * 插入角色更新的权限信息
     */
    private void addRoleModules(long roleId, String modules) {
        Date now = new Date();
        Stream.of(modules.split(","))
                .map(Integer::parseInt)
                .forEach(moduleId -> jdbcTemplate.update("insert into sys_role_module(role_id, module_id, create_time) VALUES (" + roleId + ", " + moduleId + ", ?)", now));
    }


    @Override
    @Transactional
    public void deleteRole(long roleId) {
        //更新角色表
        jdbcTemplate.update("delete from sys_role where role_id = ?",roleId);
        //删除角色_权限关联表中原有的数据
        jdbcTemplate.update("delete from sys_role_module where role_id = ?",roleId);

    }

    @Override
    public SysRole getRoleInfoList(long roleId) {
        //从sys_role表中查询数据
        List<SysRoleVo> query = jdbcTemplate.query("select * from sys_role where role_id = ? ", new BeanPropertyRowMapper<>(SysRoleVo.class), roleId);
        //从sys_role_module关联表中获取权限信息
        List<SysRoleVo> query1 = jdbcTemplate.query("select module_id moduleIds from sys_role_module where role_id = ? ", new BeanPropertyRowMapper<>(SysRoleVo.class), roleId);
        List<String> list = new ArrayList<>();
        for (SysRoleVo sysRole : query1) {
            String s = sysRole.getModuleIds().get(0);
            list.add(s);
        }
        SysRoleVo sysRole = query.get(0);
        sysRole.setModuleIds(list);
        return sysRole;
    }
}
