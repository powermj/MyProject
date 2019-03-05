package com.zhxg.fgw.entity.po;


import java.util.StringJoiner;

public class SysModule {

    private long moduleId;
    private long pid;
    private String moduleName;
    private String moduleUrl;
    private long flag;
    private long orderNo;


    public long getModuleId() {
        return moduleId;
    }

    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }


    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }


    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }


    public String getModuleUrl() {
        return moduleUrl;
    }

    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl;
    }


    public long getFlag() {
        return flag;
    }

    public void setFlag(long flag) {
        this.flag = flag;
    }


    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SysModule.class.getSimpleName() + "[", "]")
                .add("moduleId=" + moduleId)
                .add("pid=" + pid)
                .add("moduleName='" + moduleName + "'")
                .add("moduleUrl='" + moduleUrl + "'")
                .add("flag=" + flag)
                .add("orderNo=" + orderNo)
                .toString();
    }
}
