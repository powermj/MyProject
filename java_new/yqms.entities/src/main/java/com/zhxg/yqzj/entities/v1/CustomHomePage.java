package com.zhxg.yqzj.entities.v1;

import com.zhxg.framework.base.curd.impl.BaseEntity;

public class CustomHomePage extends BaseEntity {
	
    /**
     * 标签页图标
     */
    private String ico;

    /**
     * logo图片
     */
    private String logo;
	
    /**
     * 标签页名称
     */
    private String label;

    /**
     * 用户名称
     */
    private String name;
	
    /**
     * 欢迎语
     */
    private String welcome;

    /**
     * 帮助、系统信息、联系客服、系统日志图标顺序及隐藏
     */
    private String pic;
	
    /**
     * 客服qq号
     */
    private String qq;

    /**
     * 系统导航栏
     */
    private String nav;

    public String getIco() {
        return this.ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWelcome() {
        return this.welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public String getPic() {
        return this.pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getQq() {
        return this.qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getNav() {
        return this.nav;
    }

    public void setNav(String nav) {
        this.nav = nav;
    }
	
	
	
    
}
