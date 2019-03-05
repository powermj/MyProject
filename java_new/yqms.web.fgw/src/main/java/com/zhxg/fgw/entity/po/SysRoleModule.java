package com.zhxg.fgw.entity.po;


import java.util.Date;
import java.util.StringJoiner;

public class SysRoleModule {

    private long id;
    private long roleId;
    private long moduleId;
    private Date createTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }


    public long getModuleId() {
        return moduleId;
    }

    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SysRoleModule.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("roleId=" + roleId)
                .add("moduleId=" + moduleId)
                .add("createTime=" + createTime)
                .toString();
    }
}
