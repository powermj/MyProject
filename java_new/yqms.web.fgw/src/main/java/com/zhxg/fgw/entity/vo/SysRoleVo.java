package com.zhxg.fgw.entity.vo;

import com.zhxg.fgw.entity.po.SysRole;

import java.util.List;

public class SysRoleVo extends SysRole {
    public List<String> getModuleIds() {
        return moduleIds;
    }

    public void setModuleIds(List<String> moduleIds) {
        this.moduleIds = moduleIds;
    }

    private List<String> moduleIds;
}
