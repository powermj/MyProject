package com.zhxg.yqzj.entities.v1;

import com.zhxg.framework.base.curd.impl.BaseEntity;

import io.swagger.annotations.ApiModelProperty;

public class AccountVO extends BaseEntity {

    @ApiModelProperty(value="账号ID",name="accountId",example="账号ID（不是userid）")
    private Integer accountId;
    
    @ApiModelProperty(value="oldPassword",name="oldPassword",example="oldPassword")
    private String oldPassword;
    
    @ApiModelProperty(value="newPassword",name="newPassword",example="newPassword")
    private String newPassword;
    
    @ApiModelProperty(value="phone",name="phone",example="phone")
    private String phone;
    
    @ApiModelProperty(value="验证码",name="phone",example="验证码")
    private String code;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    
    
}
