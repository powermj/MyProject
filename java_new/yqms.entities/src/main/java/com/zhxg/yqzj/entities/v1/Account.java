package com.zhxg.yqzj.entities.v1;

import java.util.Date;

import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.serializer.JsonDateSerializer;

import io.swagger.annotations.ApiModelProperty;

@Table(name = "ms_account")
public class Account extends BaseEntity {
	
	/**
	 * 账户登录后获取的token
	 */
    @ApiModelProperty(value="accessToken",name="accessToken",example="accessToken")
	private String accessToken;

	/**
	 * 账户ID
	 */
    @ApiModelProperty(value="账号ID",name="accountId",example="账号ID（不是userid）")
	private Integer accountId;
	
	/**
	 * 所属客户ID
	 */
    @ApiModelProperty(value="用户ID",name="userid",example="userid")
	private Integer userid;
	
	/**
	 * 统一认证服务器账号ID
	 */
	private Integer ssoAccountId;
	
	/**
	 * 账户名 目前可用于登录
	 */
	@ApiModelProperty(hidden=true)
	private String account;
	
	/**
	 * 账户密码
	 */
	@ApiModelProperty(value="账号密码",name="password",example="账号密码/可为空")
	private String password = "";
	
	/**
	 * 账户昵称
	 */
	@ApiModelProperty(value="账户昵称",name="name",example="账户昵称/可为空")
	private String name="";
	
	@ApiModelProperty(value="微信",name="wechat",example="微信/可为空")
	private String wechat="";
	
	/**
	 * 创建时间
	 */
	@JsonSerialize(using = JsonDateSerializer.class)  
	@ApiModelProperty(hidden=true)
	private Date ctime;
	
	/**
     * 登录时间
     */
    @JsonSerialize(using = JsonDateSerializer.class)  
    @ApiModelProperty(hidden=true)
    private Date loginTime;
	
	/**
	 * 手机号
	 */
    @ApiModelProperty(value="手机号",name="phone",example="手机号/必传")
	private String phone="";
	
	/**
	 * 邮箱
	 */
    @ApiModelProperty(value="邮箱",name="email",example="邮箱/可为空")
	private String email="";
	
	/**
     * 模板
     */
    @ApiModelProperty(hidden=true)
    private String template;
    
    /**
     * 身份 1 管理员 ，2 普通用户
     */
    private Integer identity;
    
    /**
     * 状态 默认为1 正常，0 禁用，-1 删除
     */
    @ApiModelProperty(value="状态",name="state",example="默认为1 正常，0 禁用，-1 删除（传-1则删除账号）")
    private Integer state;
    
	
	/**
	 * 所属客户
	 */
    @ApiModelProperty(hidden=true)
	private User user;
	
	/**
	 * 登录成功跳转地址
	 */
	@ApiModelProperty(hidden=true)
	private String appUrl;
	
	/**
	 * 排序
	 */
	@ApiModelProperty(value="序号",name="order",example="序号")
	private int order;
	
	/**
     * app主题背景图片
     */
	@ApiModelProperty(hidden=true)
    private String appUserThemeImage;
    
    /**
     * app主题更换时间
     */
	@ApiModelProperty(hidden=true)
    private String appUserThemeImagetime;
    
    /**
     * App主色调
     */
	@ApiModelProperty(hidden=true)
    private String appMainColour;
	
    /**
     * 产品类型 1：舆情专家 2： 舆情秘书
     */
	@ApiModelProperty(hidden=true)
    private Integer productType;
    
	
	

	public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Integer getSsoAccountId() {
        return ssoAccountId;
    }

    public void setSsoAccountId(Integer ssoAccountId) {
        this.ssoAccountId = ssoAccountId;
    }

    public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    
    public String getAppUserThemeImage() {
        return appUserThemeImage;
    }

    
    public void setAppUserThemeImage(String appUserThemeImage) {
        this.appUserThemeImage = appUserThemeImage;
    }

    
    public String getAppUserThemeImagetime() {
        return appUserThemeImagetime;
    }

    
    public void setAppUserThemeImagetime(String appUserThemeImagetime) {
        this.appUserThemeImagetime = appUserThemeImagetime;
    }

    
    public String getAppMainColour() {
        return appMainColour;
    }

    
    public void setAppMainColour(String appMainColour) {
        this.appMainColour = appMainColour;
    }

    
    public Integer getProductType() {
        return productType;
    }

    
    public void setProductType(Integer productType) {
        this.productType = productType;
    }

}
