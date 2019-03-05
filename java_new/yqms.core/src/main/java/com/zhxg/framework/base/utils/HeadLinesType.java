package com.zhxg.framework.base.utils;

public enum HeadLinesType {

    NEWS("01", "人民"),
    FORUM("02", "新华"),
    BLOG("03", "凤凰"),
    MICROBLOG("04", "新浪"),
    FLATMEDIA("05", "腾讯"),
    WECHAT("06", "搜狐"),
    VIDEO("07", "网媒");
    String typeName;
    String type;
    HeadLinesType(String type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
