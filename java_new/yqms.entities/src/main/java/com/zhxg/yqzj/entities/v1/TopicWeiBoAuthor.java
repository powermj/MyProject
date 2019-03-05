package com.zhxg.yqzj.entities.v1;

import com.zhxg.framework.base.curd.impl.BaseEntity;

public class TopicWeiBoAuthor extends BaseEntity {
	private String id;
	private String name;
	private String type;
	private Integer gender;
	private String region;
	private Integer verified;
	private Integer verifiedType;
	private String verifiedIdentity;
	private Integer verifiedLevel;
	private Integer userType;
	private String importance;
	private String influence;
	private Integer friendsCount;
	private Integer fansCount;
	private Integer statusesCount;
	private Integer count;
	private String orientation;
	private Integer verifiedName;
	private String avatarUrl;
	private String ctime ;
	private String title;
	private String url ;
	private String content;
	private String infoid;
	private String site;
	private String transport;
	private Integer value;
	private String ktName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Integer getVerified() {
		return verified;
	}
	public void setVerified(Integer verified) {
		this.verified = verified;
	}
	public Integer getVerifiedType() {
		return verifiedType;
	}
	public void setVerifiedType(Integer verifiedType) {
		this.verifiedType = verifiedType;
	}
	public String getVerifiedIdentity() {
		return verifiedIdentity;
	}
	public void setVerifiedIdentity(String verifiedIdentity) {
		this.verifiedIdentity = verifiedIdentity;
	}
	public Integer getVerifiedLevel() {
		return verifiedLevel;
	}
	public void setVerifiedLevel(Integer verifiedLevel) {
		this.verifiedLevel = verifiedLevel;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getImportance() {
		return importance;
	}
	public void setImportance(String importance) {
		this.importance = importance;
	}
	public String getInfluence() {
		return influence;
	}
	public void setInfluence(String influence) {
		this.influence = influence;
	}
	public Integer getFriendsCount() {
		return friendsCount;
	}
	public void setFriendsCount(Integer friendsCount) {
		this.friendsCount = friendsCount;
	}
	public Integer getFansCount() {
		return fansCount;
	}
	public void setFansCount(Integer fansCount) {
		this.fansCount = fansCount;
	}
	public Integer getStatusesCount() {
		return statusesCount;
	}
	public void setStatusesCount(Integer statusesCount) {
		this.statusesCount = statusesCount;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = (count==null?0:count);
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	public Integer getVerifiedName() {
		return verifiedName;
	}
	public void setVerifiedName(Integer verifiedName) {
		this.verifiedName = verifiedName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getInfoid() {
		return infoid;
	}
	public void setInfoid(String infoid) {
		this.infoid = infoid;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getTransport() {
		return transport;
	}
	public void setTransport(String transport) {
		this.transport = transport;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getKtName() {
		return ktName;
	}
	public void setKtName(String ktName) {
		this.ktName = ktName;
	}

}
