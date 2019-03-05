package com.zhxg.yqzj.entities.v1;

import java.util.Date;
import java.util.List;
import javax.persistence.Table;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.serializer.JsonDateSerializer;

/**
 * <p>
 * CopyRright (c)2012-2016: haoran
 * <p>
 * Project: yqms.entities
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户实体
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.entities.v1.User.java
 * <p>
 * Author: haoran
 * <p>
 * Create Date: 2017年2月28日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
@Table(name = "WK_T_USER")
public class User extends BaseEntity {

    /**
     * @Fields kuId : 客户ID，用户唯一标示
     */
    private Integer userId;

    /**
     * @Fields kuName : 客户名称
     */
    private String userName;
    
    
    /**
     * @Fields kuName : 客户名称
     */
    @JsonSerialize(using = JsonDateSerializer.class)  
    private Date registDate;
    
    

    /**
     * @Fields kuDbName : 数据库地址
     */
    private String dbHost;
    
    
    private Integer sex;

    /**
     * 生日
     */
    private String birthday;
    
    /**
     * 客户邮箱
     */
    private String email;
    
    /**
     * 客户电话
     */
    private String phone;
    
    /**
     * 代理商ID
     */
    private Integer agentId;
    
    /**
     * 客户等级
     */
    private Integer level;
    
    /**
     * TODO 待定
     */
    @JsonSerialize(using = JsonDateSerializer.class)  
    private Date ltime;
    
    /**
     * TODO 待定
     */
    @JsonSerialize(using = JsonDateSerializer.class)  
    private Date rtime;
    
    /**
     * 最后登录地址(IP)
     */
    private String lastAddr;
    
    /**
     * TODO 初步判断为是否在线，不过状态不应该持久化
     */
    private String online;
    
    /**
     * TODO 待定
     */
    private Integer ltimes;
    
    /**
     * TODO 待定
     */
    private String style;
    
    /**
     * TODO 待定
     */
    private String limit;
    
    
    /**
     * 销售员
     */
    private String salesPerson;
    
    /**
     * 客户选择的主题
     */
    private String template;
    
    /**
     * 是否开通境外 1 是，0 否
     */
    private Integer isAbroadOpen;
    
    /**
     * 待定 应该是是否开通某种数据来源
     */
    private Integer searchOpen;
    
    //以下为USERSERVICE表信息

    /**
     * TODO 待定
     */
    private Integer mailCount;
    
    /**
     * TODO 待定
     */
    private Integer phoneCount;
    
    /**
     * 话题数量限制
     */
    private Integer topicCount;
    
    /**
     * TODO 待定
     */
    private Integer tradeCount;
    
    /**
     * 是否开启日报
     */
    private Integer dayReportOpen;
    
    /**
     * 客户状态 0 正式 ，1 试用 ，2 停用
     */
    private Integer userState;
    
    /**
     * 数据保存天数
     */
    private Integer saveDays;
    
    /**
     * 客户到期时间（专家）
     */
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date expireTime;
    
    
    /**
     * 客户到期时间(秘书，这个单词不对)
     */
    @JsonSerialize(using = JsonDateSerializer.class)  
    private Date expirationTime;
    
    /**
     * 关键词数量限制
     */
    private Integer keyWordCount;
    
    
    /**
     * 日报生成时间（小时）
     */
    private String dayReportTime;
    
    /**
     * 预警词数量限制
     */
    private Integer warningWordCount;
    
    /**
     * 是否开通微博预警
     */
    private Integer weiboWarningOpen;
    
    /**
     * 预警去重时间
     */
    private Integer warningDistinctHours;
    
    /**
     * 客户QQ号
     */
    private String qq;
    
    /**
     * 是否开通普通预警
     */
    private Integer commonWariningOpen;
    
    
    /**
     * 日报是否发送邮箱
     */
    private Integer sendReportOpen;
    
    /**
     * 是否开通精准
     */
    private Integer preciseOpen;
    
    /**
     * 是否开通全部导出
     */
    private Integer allExportOpen;
    
    /**
     * 日报模板ID
     */
    private String reportTemplate;
    
    /**
     * 微信
     */
    private String weixin;
    
    /**
     * 排序时间 0 发布时间 1 采集时间
     */
    private Integer orderTimeType;
    
    /**
     * 是否开启噪音
     */
    private Integer noiseOpen;
    
    /**
     * 是否开启系统噪音
     */
    private Integer systemNoiseOpen;
    
    /**
     * 数据来源-汽车是否开启
     */
    private Integer dataTypeCarOpen;
    
    /**
     * 数据来源-金融是否开启
     */
    private Integer dataTypeFinanceOpen;
    
    /**
     * 数据来源-金融是否开启
     */
    private Integer dataTypeTravelOpen;
    
    /**
     * 专题设置是否开启
     */
    private Integer subjectSetOpen;
    
    /**
     * 模板设置是否开启
     */
    private Integer templateSetOpen;
    
    /**
     * 专题分类管理是否开启
     */
    private Integer subjectManagerOpen;
    
    /**
     * 邮箱是否通过验证
     */
    private Integer isValidEmail;
    
    /**
     * 是否开启地域数据
     */
    private Integer locationInfoOpen;
    
    /**
     * 是否开启藏文 蒙文
     */
    private Integer isTibetan;
    
    /**
     * 是否开启外文
     */
    private Integer isForlanguage;
    
    /**
     * 用户类型 0用户 1员工 2测试 3 系统用户 4 代理商用户
     */
    private Integer userGenre;
    
    /**
     * 是否开启预警弹窗
     */
    private Integer alertWinOpen;
    
    /**
     * 是否推送微信日报
     */
    private Integer weixinPushOpen;
    
    /**
     * 关联的OAID
     */
    private Integer oaId;
    
    /**
     * 电视监控剩余下载视频次数
     */
    private Integer tvLastDownloadNum;
    
    /**
     * 关键词数量，跟上面的keywordCount有啥区别我也不知道
     */
    private Integer keyWordNum;
    
    /**
     * 电视监控关键词个数
     */
    private Integer tvKeyWordNum;
    
    
    /**
     * 电视监控下载视频次数
     */
    private Integer tvDownloadNum;
    
    /**
     * 是否开启电视监控
     */
    private Integer tvOpen;
    
    
    /**
     * 自定义日报条件（json）
     */
    private String reportCondition;
    
    /**
     * 是否开启专题间距
     */
    private Integer specialSpacingOpen;
    
    /**
     * 是否开启话题数据补采功能
     */
    private Integer topicRefreshOpen;
    
    /**
     * 是否开启专题补采功能
     */
    private Integer subjectRecollectOpen;
    
    /**
     * 每天最大数据量
     */
    private Integer everydayMaxDataCount;
    
    /**
     * 专题最大数量限制
     */
    private Integer subjectMaxDataCount;
    
    /**
     * 是否保存过期数据
     */
    private Integer isSaveOverDueData;
    
    /**
     * 是否开启微博评论
     */
    private Integer weiboCommentOpen;
    
    /**
     * 是否开启大V评论
     */
    private Integer bigVCommentOpen;
    
    /**
     * 该用户是否有网评设置
     */
    private Integer commentOpen;
    
    /**
     * 数据迁移状态
     */
    private Integer migr;
    
    /**
     * 用户模板类型 1国资委
     */
    private Integer templateType;
    
    /**
     * 用户所在OA集团用户组
     */
    private String groupId;
    
    /**
     * 是否开通系统密码
     */
    private Integer isOpenSysPass;
    
    /**
     * 推送数量限制
     */
    private Integer tuiSongCount;
    
    
    /**
     * @Fields configInfo : 配置信息
     *         一对多
     */
    private List<UserConfig> configInfo;

    /**
     * @Fields specials : 用户人工预警的专题列表
     *         一对多
     */
    private List<Subject> artificialWaringSubjects;
    
    /**
     * @Fields configInfo : 查询已关联账户
     *         一对多
     */
    private List<ManageUser> manageUserList;
    
    
    /**
     * 是否是管理员（大概）
     */
    private boolean manage;
    
    
    public String getCommonSubjectSendOpen() {
        return commonSubjectSendOpen;
    }

    public void setCommonSubjectSendOpen(String commonSubjectSendOpen) {
        this.commonSubjectSendOpen = commonSubjectSendOpen;
    }

    //补充字段 注释暂无
    /**
     * 专题设置推送是否开启
     */
    private String commonSubjectSendOpen;
    private String nickname;
    private String state;
    private String autoShow;
    private String autoEnter;
    private String bindType;
    private String bindAddr;
    private String question;
    private String answer;
    private String oldLid;
    private String refName;
    private String jzqb;
    private String sysPassword;
    private String type;
    private String blog;
    private String phoneCount_t;
    private String showAll;
    private String insertInfo;
    private String showAllSub;
    private String single;
    private String reportTitle;
    private String areaSet;
    private String showSource;
    private String sendInfo;
    private String bbsAllInfo;
    private String size;
    private String showAbs;
    private String openZS;
    private String test;
    private String zsDate;
    private String userTrend;
    private String localBBSWarning;
    private String sourceType;
    private String userDateType;
    private String openedZSUser;
    private String sound;
    private String zfWords;
    private String setReport;
    private String showReport;
    private String setAbstract;
    private String classify;
    private String orderSubjectTJ;
    private String zsTime;
    private String syTime;
    private String tryTime;
    private String repeat;
    private String homeQuery;
    private String weixinCheck;
    private String openHighSet;
    private Integer weixinNumCount;
    private Integer weixinGroupCount;
    private Integer qqcount;
    private String zqRubbish;
    private String warningTime;
    private String sale;
    private String maint;
    private String isZSQY;
    private String isWF;
    private String version;
    private String changeVersion;
    private String bbsView;
    
    
    public String getBbsView() {
        return bbsView;
    }

    public void setBbsView(String bbsView) {
        this.bbsView = bbsView;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAutoShow() {
        return autoShow;
    }

    public void setAutoShow(String autoShow) {
        this.autoShow = autoShow;
    }

    public String getAutoEnter() {
        return autoEnter;
    }

    public void setAutoEnter(String autoEnter) {
        this.autoEnter = autoEnter;
    }

    public String getBindType() {
        return bindType;
    }

    public void setBindType(String bindType) {
        this.bindType = bindType;
    }

    public String getBindAddr() {
        return bindAddr;
    }

    public void setBindAddr(String bindAddr) {
        this.bindAddr = bindAddr;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOldLid() {
        return oldLid;
    }

    public void setOldLid(String oldLid) {
        this.oldLid = oldLid;
    }

    public String getRefName() {
        return refName;
    }

    public void setRefName(String refName) {
        this.refName = refName;
    }

    public String getJzqb() {
        return jzqb;
    }

    public void setJzqb(String jzqb) {
        this.jzqb = jzqb;
    }

    public String getSysPassword() {
        return sysPassword;
    }

    public void setSysPassword(String sysPassword) {
        this.sysPassword = sysPassword;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getPhoneCount_t() {
        return phoneCount_t;
    }

    public void setPhoneCount_t(String phoneCount_t) {
        this.phoneCount_t = phoneCount_t;
    }

    public String getShowAll() {
        return showAll;
    }

    public void setShowAll(String showAll) {
        this.showAll = showAll;
    }

    public String getInsertInfo() {
        return insertInfo;
    }

    public void setInsertInfo(String insertInfo) {
        this.insertInfo = insertInfo;
    }

    public String getShowAllSub() {
        return showAllSub;
    }

    public void setShowAllSub(String showAllSub) {
        this.showAllSub = showAllSub;
    }

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public String getAreaSet() {
        return areaSet;
    }

    public void setAreaSet(String areaSet) {
        this.areaSet = areaSet;
    }

    public String getShowSource() {
        return showSource;
    }

    public void setShowSource(String showSource) {
        this.showSource = showSource;
    }

    public String getSendInfo() {
        return sendInfo;
    }

    public void setSendInfo(String sendInfo) {
        this.sendInfo = sendInfo;
    }

    public String getBbsAllInfo() {
        return bbsAllInfo;
    }

    public void setBbsAllInfo(String bbsAllInfo) {
        this.bbsAllInfo = bbsAllInfo;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getShowAbs() {
        return showAbs;
    }

    public void setShowAbs(String showAbs) {
        this.showAbs = showAbs;
    }

    public String getOpenZS() {
        return openZS;
    }

    public void setOpenZS(String openZS) {
        this.openZS = openZS;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getZsDate() {
        return zsDate;
    }

    public void setZsDate(String zsDate) {
        this.zsDate = zsDate;
    }

    public String getUserTrend() {
        return userTrend;
    }

    public void setUserTrend(String userTrend) {
        this.userTrend = userTrend;
    }

    public String getLocalBBSWarning() {
        return localBBSWarning;
    }

    public void setLocalBBSWarning(String localBBSWarning) {
        this.localBBSWarning = localBBSWarning;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getUserDateType() {
        return userDateType;
    }

    public void setUserDateType(String userDateType) {
        this.userDateType = userDateType;
    }

    public String getOpenedZSUser() {
        return openedZSUser;
    }

    public void setOpenedZSUser(String openedZSUser) {
        this.openedZSUser = openedZSUser;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getZfWords() {
        return zfWords;
    }

    public void setZfWords(String zfWords) {
        this.zfWords = zfWords;
    }

    public String getSetReport() {
        return setReport;
    }

    public void setSetReport(String setReport) {
        this.setReport = setReport;
    }

    public String getShowReport() {
        return showReport;
    }

    public void setShowReport(String showReport) {
        this.showReport = showReport;
    }

    public String getSetAbstract() {
        return setAbstract;
    }

    public void setSetAbstract(String setAbstract) {
        this.setAbstract = setAbstract;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getOrderSubjectTJ() {
        return orderSubjectTJ;
    }

    public void setOrderSubjectTJ(String orderSubjectTJ) {
        this.orderSubjectTJ = orderSubjectTJ;
    }

    public String getZsTime() {
        return zsTime;
    }

    public void setZsTime(String zsTime) {
        this.zsTime = zsTime;
    }

    public String getSyTime() {
        return syTime;
    }

    public void setSyTime(String syTime) {
        this.syTime = syTime;
    }

    public String getTryTime() {
        return tryTime;
    }

    public void setTryTime(String tryTime) {
        this.tryTime = tryTime;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getHomeQuery() {
        return homeQuery;
    }

    public void setHomeQuery(String homeQuery) {
        this.homeQuery = homeQuery;
    }

    public String getWeixinCheck() {
        return weixinCheck;
    }

    public void setWeixinCheck(String weixinCheck) {
        this.weixinCheck = weixinCheck;
    }

    public String getOpenHighSet() {
        return openHighSet;
    }

    public void setOpenHighSet(String openHighSet) {
        this.openHighSet = openHighSet;
    }

    public Integer getWeixinNumCount() {
        return weixinNumCount;
    }

    public void setWeixinNumCount(Integer weixinNumCount) {
        this.weixinNumCount = weixinNumCount;
    }

    public Integer getWeixinGroupCount() {
        return weixinGroupCount;
    }

    public void setWeixinGroupCount(Integer weixinGroupCount) {
        this.weixinGroupCount = weixinGroupCount;
    }

    public Integer getQqcount() {
        return qqcount;
    }

    public void setQqcount(Integer qqcount) {
        this.qqcount = qqcount;
    }

    public String getZqRubbish() {
        return zqRubbish;
    }

    public void setZqRubbish(String zqRubbish) {
        this.zqRubbish = zqRubbish;
    }

    public String getWarningTime() {
        return warningTime;
    }

    public void setWarningTime(String warningTime) {
        this.warningTime = warningTime;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getMaint() {
        return maint;
    }

    public void setMaint(String maint) {
        this.maint = maint;
    }

    public String getIsZSQY() {
        return isZSQY;
    }

    public void setIsZSQY(String isZSQY) {
        this.isZSQY = isZSQY;
    }

    public String getIsWF() {
        return isWF;
    }

    public void setIsWF(String isWF) {
        this.isWF = isWF;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getChangeVersion() {
        return changeVersion;
    }

    public void setChangeVersion(String changeVersion) {
        this.changeVersion = changeVersion;
    }

    public List<Subject> getArtificialWaringSubjects() {
		return artificialWaringSubjects;
	}

	public void setArtificialWaringSubjects(List<Subject> artificialWaringSubjects) {
		this.artificialWaringSubjects = artificialWaringSubjects;
	}

    public List<UserConfig> getConfigInfo() {
		return configInfo;
	}

	public void setConfigInfo(List<UserConfig> configInfo) {
		this.configInfo = configInfo;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public Integer getSearchOpen() {
		return searchOpen;
	}

	public void setSearchOpen(Integer searchOpen) {
		this.searchOpen = searchOpen;
	}

	public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDbHost() {
        return this.dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Date getLtime() {
		return ltime;
	}

	public void setLtime(Date ltime) {
		this.ltime = ltime;
	}

	public Date getRtime() {
		return rtime;
	}

	public void setRtime(Date rtime) {
		this.rtime = rtime;
	}

	public String getLastAddr() {
		return lastAddr;
	}

	public void setLastAddr(String lastAddr) {
		this.lastAddr = lastAddr;
	}

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	public Integer getLtimes() {
		return ltimes;
	}

	public void setLtimes(Integer ltimes) {
		this.ltimes = ltimes;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Integer getIsAbroadOpen() {
		return isAbroadOpen;
	}

	public void setIsAbroadOpen(Integer isAbroadOpen) {
		this.isAbroadOpen = isAbroadOpen;
	}

	public Integer getMailCount() {
		return mailCount;
	}

	public void setMailCount(Integer mailCount) {
		this.mailCount = mailCount;
	}

	public Integer getPhoneCount() {
		return phoneCount;
	}

	public void setPhoneCount(Integer phoneCount) {
		this.phoneCount = phoneCount;
	}

	public Integer getTopicCount() {
		return topicCount;
	}

	public void setTopicCount(Integer topicCount) {
		this.topicCount = topicCount;
	}

	public Integer getTradeCount() {
		return tradeCount;
	}

	public void setTradeCount(Integer tradeCount) {
		this.tradeCount = tradeCount;
	}

	public Integer getDayReportOpen() {
		return dayReportOpen;
	}

	public void setDayReportOpen(Integer dayReportOpen) {
		this.dayReportOpen = dayReportOpen;
	}

	public Integer getUserState() {
		return userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
	}

	public Integer getSaveDays() {
		return saveDays;
	}

	public void setSaveDays(Integer saveDays) {
		this.saveDays = saveDays;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public Integer getKeyWordCount() {
		return keyWordCount;
	}

	public void setKeyWordCount(Integer keyWordCount) {
		this.keyWordCount = keyWordCount;
	}

	public String getDayReportTime() {
		return dayReportTime;
	}

	public void setDayReportTime(String dayReportTime) {
		this.dayReportTime = dayReportTime;
	}

	public Integer getWarningWordCount() {
		return warningWordCount;
	}

	public void setWarningWordCount(Integer warningWordCount) {
		this.warningWordCount = warningWordCount;
	}

	public Integer getWeiboWarningOpen() {
		return weiboWarningOpen;
	}

	public void setWeiboWarningOpen(Integer weiboWarningOpen) {
		this.weiboWarningOpen = weiboWarningOpen;
	}

	public Integer getWarningDistinctHours() {
		return warningDistinctHours;
	}

	public void setWarningDistinctHours(Integer warningDistinctHours) {
		this.warningDistinctHours = warningDistinctHours;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Integer getCommonWariningOpen() {
		return commonWariningOpen;
	}

	public void setCommonWariningOpen(Integer commonWariningOpen) {
		this.commonWariningOpen = commonWariningOpen;
	}

	public Integer getSendReportOpen() {
		return sendReportOpen;
	}

	public void setSendReportOpen(Integer sendReportOpen) {
		this.sendReportOpen = sendReportOpen;
	}

	public Integer getPreciseOpen() {
		return preciseOpen;
	}

	public void setPreciseOpen(Integer preciseOpen) {
		this.preciseOpen = preciseOpen;
	}

	public Integer getAllExportOpen() {
		return allExportOpen;
	}

	public void setAllExportOpen(Integer allExportOpen) {
		this.allExportOpen = allExportOpen;
	}

	public String getReportTemplate() {
		return reportTemplate;
	}

	public void setReportTemplate(String reportTemplate) {
		this.reportTemplate = reportTemplate;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public Integer getOrderTimeType() {
		return orderTimeType;
	}

	public void setOrderTimeType(Integer orderTimeType) {
		this.orderTimeType = orderTimeType;
	}

	public Integer getNoiseOpen() {
		return noiseOpen;
	}

	public void setNoiseOpen(Integer noiseOpen) {
		this.noiseOpen = noiseOpen;
	}

	public Integer getSystemNoiseOpen() {
		return systemNoiseOpen;
	}

	public void setSystemNoiseOpen(Integer systemNoiseOpen) {
		this.systemNoiseOpen = systemNoiseOpen;
	}

	public Integer getDataTypeCarOpen() {
		return dataTypeCarOpen;
	}

	public void setDataTypeCarOpen(Integer dataTypeCarOpen) {
		this.dataTypeCarOpen = dataTypeCarOpen;
	}

	public Integer getDataTypeFinanceOpen() {
		return dataTypeFinanceOpen;
	}

	public void setDataTypeFinanceOpen(Integer dataTypeFinanceOpen) {
		this.dataTypeFinanceOpen = dataTypeFinanceOpen;
	}

	public Integer getDataTypeTravelOpen() {
		return dataTypeTravelOpen;
	}

	public void setDataTypeTravelOpen(Integer dataTypeTravelOpen) {
		this.dataTypeTravelOpen = dataTypeTravelOpen;
	}

	public Integer getSubjectSetOpen() {
		return subjectSetOpen;
	}

	public void setSubjectSetOpen(Integer subjectSetOpen) {
		this.subjectSetOpen = subjectSetOpen;
	}

	public Integer getTemplateSetOpen() {
		return templateSetOpen;
	}

	public void setTemplateSetOpen(Integer templateSetOpen) {
		this.templateSetOpen = templateSetOpen;
	}

	public Integer getSubjectManagerOpen() {
		return subjectManagerOpen;
	}

	public void setSubjectManagerOpen(Integer subjectManagerOpen) {
		this.subjectManagerOpen = subjectManagerOpen;
	}

	public Integer getIsValidEmail() {
		return isValidEmail;
	}

	public void setIsValidEmail(Integer isValidEmail) {
		this.isValidEmail = isValidEmail;
	}

	public Integer getLocationInfoOpen() {
		return locationInfoOpen;
	}

	public void setLocationInfoOpen(Integer locationInfoOpen) {
		this.locationInfoOpen = locationInfoOpen;
	}

	public Integer getIsTibetan() {
		return isTibetan;
	}

	public void setIsTibetan(Integer isTibetan) {
		this.isTibetan = isTibetan;
	}

	public Integer getIsForlanguage() {
		return isForlanguage;
	}

	public void setIsForlanguage(Integer isForlanguage) {
		this.isForlanguage = isForlanguage;
	}

	public Integer getUserGenre() {
		return userGenre;
	}

	public void setUserGenre(Integer userGenre) {
		this.userGenre = userGenre;
	}

	public Integer getAlertWinOpen() {
		return alertWinOpen;
	}

	public void setAlertWinOpen(Integer alertWinOpen) {
		this.alertWinOpen = alertWinOpen;
	}

	public Integer getWeixinPushOpen() {
		return weixinPushOpen;
	}

	public void setWeixinPushOpen(Integer weixinPushOpen) {
		this.weixinPushOpen = weixinPushOpen;
	}

	public Integer getOaId() {
		return oaId;
	}

	public void setOaId(Integer oaId) {
		this.oaId = oaId;
	}

	public Integer getTvLastDownloadNum() {
		return tvLastDownloadNum;
	}

	public void setTvLastDownloadNum(Integer tvLastDownloadNum) {
		this.tvLastDownloadNum = tvLastDownloadNum;
	}

	public Integer getKeyWordNum() {
		return keyWordNum;
	}

	public void setKeyWordNum(Integer keyWordNum) {
		this.keyWordNum = keyWordNum;
	}

	public Integer getTvKeyWordNum() {
		return tvKeyWordNum;
	}

	public void setTvKeyWordNum(Integer tvKeyWordNum) {
		this.tvKeyWordNum = tvKeyWordNum;
	}

	public Integer getTvDownloadNum() {
		return tvDownloadNum;
	}

	public void setTvDownloadNum(Integer tvDownloadNum) {
		this.tvDownloadNum = tvDownloadNum;
	}

	public Integer getTvOpen() {
		return tvOpen;
	}

	public void setTvOpen(Integer tvOpen) {
		this.tvOpen = tvOpen;
	}

	public String getReportCondition() {
		return reportCondition;
	}

	public void setReportCondition(String reportCondition) {
		this.reportCondition = reportCondition;
	}

	public Integer getSpecialSpacingOpen() {
		return specialSpacingOpen;
	}

	public void setSpecialSpacingOpen(Integer specialSpacingOpen) {
		this.specialSpacingOpen = specialSpacingOpen;
	}

	public Integer getTopicRefreshOpen() {
		return topicRefreshOpen;
	}

	public void setTopicRefreshOpen(Integer topicRefreshOpen) {
		this.topicRefreshOpen = topicRefreshOpen;
	}

	public Integer getSubjectRecollectOpen() {
		return subjectRecollectOpen;
	}

	public void setSubjectRecollectOpen(Integer subjectRecollectOpen) {
		this.subjectRecollectOpen = subjectRecollectOpen;
	}

	public Integer getEverydayMaxDataCount() {
		return everydayMaxDataCount;
	}

	public void setEverydayMaxDataCount(Integer everydayMaxDataCount) {
		this.everydayMaxDataCount = everydayMaxDataCount;
	}

	public Integer getSubjectMaxDataCount() {
		return subjectMaxDataCount;
	}

	public void setSubjectMaxDataCount(Integer subjectMaxDataCount) {
		this.subjectMaxDataCount = subjectMaxDataCount;
	}

	public Integer getIsSaveOverDueData() {
		return isSaveOverDueData;
	}

	public void setIsSaveOverDueData(Integer isSaveOverDueData) {
		this.isSaveOverDueData = isSaveOverDueData;
	}

	public Integer getWeiboCommentOpen() {
		return weiboCommentOpen;
	}

	public void setWeiboCommentOpen(Integer weiboCommentOpen) {
		this.weiboCommentOpen = weiboCommentOpen;
	}

	public Integer getBigVCommentOpen() {
		return bigVCommentOpen;
	}

	public void setBigVCommentOpen(Integer bigVCommentOpen) {
		this.bigVCommentOpen = bigVCommentOpen;
	}

	public Integer getCommentOpen() {
		return commentOpen;
	}

	public void setCommentOpen(Integer commentOpen) {
		this.commentOpen = commentOpen;
	}

	public Integer getMigr() {
		return migr;
	}

	public void setMigr(Integer migr) {
		this.migr = migr;
	}

	public Integer getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Integer getIsOpenSysPass() {
		return isOpenSysPass;
	}

	public void setIsOpenSysPass(Integer isOpenSysPass) {
		this.isOpenSysPass = isOpenSysPass;
	}

	public Integer getTuiSongCount() {
		return tuiSongCount;
	}

	public void setTuiSongCount(Integer tuiSongCount) {
		this.tuiSongCount = tuiSongCount;
	}

    
    public Date getExpireTime() {
        return expireTime;
    }

    
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    
    public List<ManageUser> getManageUserList() {
        return manageUserList;
    }

    
    public void setManageUserList(List<ManageUser> manageUserList) {
        this.manageUserList = manageUserList;
    }

    
    public boolean isManage() {
        return manage;
    }

    
    public void setManage(boolean manage) {
        this.manage = manage;
    }

    
}
