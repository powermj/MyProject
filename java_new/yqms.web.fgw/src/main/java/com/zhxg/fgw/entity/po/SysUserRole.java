package com.zhxg.fgw.entity.po;


import java.util.Date;
import java.util.StringJoiner;

public class SysUserRole {

    private long id;
    private long userId;
    private long roleId;
    private Date createTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SysUserRole.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("userId=" + userId)
                .add("roleId=" + roleId)
                .add("createTime=" + createTime)
                .toString();
    }
}
