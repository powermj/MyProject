package com.zhxg.yqzj.entities.v1;

import java.util.Date;
import java.util.List;

import javax.persistence.Table;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.serializer.JsonDateSerializer;

@Table(name = "zj_expert_infomation")
public class Expert extends BaseEntity {
	
	/**
	 * 专家ID
	 */
	private Integer id;
	
	/**
	 * 专家名称
	 */
	private String name;
	
	/**
     * 专家昵称
     */
    private String nickname;
	
	
	/**
	 * 头像
	 */
	private String avatar;
	
	/**
	 * 此处为具体类型名称
	 * 专家类型  2：处置专家 3：研判专家 4：媒体专家 5：金融专家 6：律师 7：其他
	 */
	private String type;
	
	/**
	 * 专家摘要
	 */
	private String abstracts;
	
	/**
	 * 成功案例
	 */
	private String successCase;
	
	/**
	 * 地域
	 */
	private Integer region;
	
	/**
	 * 标签
	 */
	private String label;
	
	/**
	 * 简介
	 */
	private String introduction;
	
	/**
	 * 专家历任
	 */
	private String successive;
	
	/**
	 * 主要奖项和成果
	 */
	private String achievement;
	
	/**
	 * 推荐等级
	 */
	private Integer level;
	
	/**
	 * 创建时间
	 */
	@JsonSerialize(using = JsonDateSerializer.class)  
	private Date ctime;
	
	/**
	 * 用户针对该专家的咨询状态
	 */
	private Integer status;
	
	/**
	 * 专家状态 1 正常 0 删除
	 */
	private int state;
	
	/**
	 * 已申请该专家的咨询详情
	 */
	private String consultationDescribe;
	
	private String consultationType;
	
	/**
	 * 专家支持的咨询类型
	 */
	List<ConsultationType> supportConsultationType;
	
	/**
     * 用户针对该专家的咨询状态
     */
    private String regionName;

	public String getConsultationDescribe() {
		return consultationDescribe;
	}

	public void setConsultationDescribe(String consultationDescribe) {
		this.consultationDescribe = consultationDescribe;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

	public String getSuccessCase() {
		return successCase;
	}

	public void setSuccessCase(String successCase) {
		this.successCase = successCase;
	}

	public Integer getRegion() {
		return region;
	}

	public void setRegion(Integer region) {
		this.region = region;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}


    public String getIntroduction() {
        return introduction;
    }

    
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getSuccessive() {
		return successive;
	}

	public void setSuccessive(String successive) {
		this.successive = successive;
	}

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

    public String getConsultationType() {
        return consultationType;
    }

    
    public void setConsultationType(String consultationType) {
        this.consultationType = consultationType;
    }

    public Integer getStatus() {
        return status;
    }

    
    public void setStatus(Integer status) {
        this.status = status;
    }

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public List<ConsultationType> getSupportConsultationType() {
		return supportConsultationType;
	}

	public void setSupportConsultationType(List<ConsultationType> supportConsultationType) {
		this.supportConsultationType = supportConsultationType;
	}

    
    public String getRegionName() {
        return regionName;
    }

    
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    
	
}
