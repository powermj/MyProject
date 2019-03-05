package com.zhxg.yqzj.entities.v1;

import com.zhxg.framework.base.curd.impl.BaseEntity;

public class Subject extends BaseEntity{
	
	/**
	 * 专题ID
	 */
	private String uuid;
	
	/**
	 * 专题设置表ID
	 */
	private String relationId;
	
	/**
	 * 父专题ID
	 */
	private String pid;
	
	/**
	 * 用户ID
	 */
	private int userid;
	
	/**
	 * 专题名称
	 */
	private String subjectName;
	
	/**
	 * 是否有子专题 0有子专题 1无子专题
	 */
	private int isParent;// 无用字段最好删除--by haoran
	
	/**
	 * 所属分类ID
	 */
	private int classifyId;
	
	/**
	 * 顶级专题及子专题顺序
	 */
	private int index_s;
	
	/**
	 * 分类下专题及成员专题顺序
	 */
	private int index_c;
	
	/**
	 * 专题类型 1 专题 2 分类 3 地域专题 
	 */
	private int type;
	
	/**
	 * 是否分享   0否  1是
	 */
	private int share;
	
	/**
	 * 专题属性 0 正常 1负面 2正面 3 噪音 4情感专题
	 */
	private int attribute;
	
	
	/**
	 * 子分类有没有专题 0 无  1有
	 */
	private int haveSubSubject;//无用字段最好删除--by haoran
	
	
	/**
	 * 是否置顶
	 */
	private int isTop;
	
	/**
	 * 仅匹配子专题 1 是 0 否
	 */
	private int onlyMatchSubSubject;
	
	/**
	 * 0 普通专题 ，1 被分享的专题
	 */
	private int isSharedSubject;
	
	/**
	 * 是否包含子地域 0 否 1是
	 */
	private int containSubRegion;
	
	/**
	 * 是否被收藏  0否   1是
	 */
	private int isCollectioned;
	
	/**
	 * 专题类型 01 普通 08 正面 09 负面 10 噪音 13 行业动态 14 竞品 15 友商 16 招标 17 政策法规 18 电视监控
	 */
	private String subjectType;
	
	/**
	 * 专题状态 1 正常 0 禁用
	 */
	private int state;
	
	/**
	 * 引用专题ID
	 */
	private String refSubjectId;
	
	/**
	 * 重要度定向限制[{"id":3871,"name":"A"},{"id":3872,"name":"B"}]
	 */
	private String filterDomain;
	
	/**
	 * 地域定向限制信息源[{"id":1,"name":"河北"},{"id":2,"name":"石家庄"}]
	 */
	private String filterRegion;
	
	
	private SubjectConfigure configure;
	
	private String userClassifyId;
	
	public SubjectConfigure getConfigure() {
        return configure;
    }

    public void setConfigure(SubjectConfigure configure) {
        this.configure = configure;
    }

    public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getIsParent() {
        return isParent;
    }

    public void setIsParent(int isParent) {
        this.isParent = isParent;
    }

    public int getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(int classifyId) {
        this.classifyId = classifyId;
    }

    public int getIndex_s() {
        return index_s;
    }

    public void setIndex_s(int index_s) {
        this.index_s = index_s;
    }

    public int getIndex_c() {
        return index_c;
    }

    public void setIndex_c(int index_c) {
        this.index_c = index_c;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public int getAttribute() {
        return attribute;
    }

    public void setAttribute(int attribute) {
        this.attribute = attribute;
    }

    public int getHaveSubSubject() {
        return haveSubSubject;
    }

    public void setHaveSubSubject(int haveSubSubject) {
        this.haveSubSubject = haveSubSubject;
    }

    public int getIsTop() {
        return isTop;
    }

    public void setIsTop(int isTop) {
        this.isTop = isTop;
    }

    public int getOnlyMatchSubSubject() {
        return onlyMatchSubSubject;
    }

    public void setOnlyMatchSubSubject(int onlyMatchSubSubject) {
        this.onlyMatchSubSubject = onlyMatchSubSubject;
    }

    public int getIsSharedSubject() {
        return isSharedSubject;
    }

    public void setIsSharedSubject(int isSharedSubject) {
        this.isSharedSubject = isSharedSubject;
    }

    public int getContainSubRegion() {
        return containSubRegion;
    }

    public void setContainSubRegion(int containSubRegion) {
        this.containSubRegion = containSubRegion;
    }

    public int getIsCollectioned() {
        return isCollectioned;
    }

    public void setIsCollectioned(int isCollectioned) {
        this.isCollectioned = isCollectioned;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRefSubjectId() {
        return refSubjectId;
    }

    public void setRefSubjectId(String refSubjectId) {
        this.refSubjectId = refSubjectId;
    }

    public String getFilterDomain() {
        return filterDomain;
    }

    public void setFilterDomain(String filterDomain) {
        this.filterDomain = filterDomain;
    }

    public String getFilterRegion() {
        return filterRegion;
    }

    public void setFilterRegion(String filterRegion) {
        this.filterRegion = filterRegion;
    }

	public String getUserClassifyId() {
		return userClassifyId;
	}

	public void setUserClassifyId(String userClassifyId) {
		this.userClassifyId = userClassifyId;
	}

}
