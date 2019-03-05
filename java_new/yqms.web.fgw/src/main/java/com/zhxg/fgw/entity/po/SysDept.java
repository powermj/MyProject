package com.zhxg.fgw.entity.po;


import java.util.Date;
import java.util.StringJoiner;

public class SysDept {

    private long deptId;
    private long pid;
    private String deptName;
    private long flag;
    private Date createTime;
    private Date modified;


    public long getDeptId() {
        return deptId;
    }

    public void setDeptId(long deptId) {
        this.deptId = deptId;
    }


    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }


    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }


    public long getFlag() {
        return flag;
    }

    public void setFlag(long flag) {
        this.flag = flag;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SysDept.class.getSimpleName() + "[", "]")
                .add("deptId=" + deptId)
                .add("pid=" + pid)
                .add("deptName='" + deptName + "'")
                .add("flag=" + flag)
                .add("createTime=" + createTime)
                .add("modified=" + modified)
                .toString();
    }
}
