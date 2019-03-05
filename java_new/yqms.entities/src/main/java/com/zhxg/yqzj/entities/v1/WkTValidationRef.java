package com.zhxg.yqzj.entities.v1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

import com.zhxg.framework.base.curd.impl.BaseEntity;

public class WkTValidationRef extends BaseEntity {
	/**
	 * 当天
	 */
	public static final String TIMETYPE_DAY = "day";
	/**
	 * 近七天
	 */
	public static final String TIMETYPE_SEVENDAYS = "sevenDays";
	/**
	 * 自定义
	 */
	public static final String TIMETYPE_CUSTOMDAY = "customDay";

	private String startTime;

	private String endTime;

	private String krUuid;

	private String kvUuid;

	private String krInfotype;

	private String krKeywordid;

	private Integer krUid;

	private String krState;

	private String krCtime;

	private String krIslocal;

	private String kvFlag;

	private String arg1;

	private String arg2;

	private String arg3;

	private String keyword;

	@Field(value = "kvOrientation")
	private String orientation;

	private Integer isyj;

	private Integer isread;

	private Integer ismyattention;

	private Integer isfeedback;

	private String arg4;

	private String arg5;

	private Integer kvVisitcount;

	private Integer kvReply;

	@Field(value = "KV_SIMHASH")
	private String kvSimhash;

	@Field(value = "kvSite")
	private String kvWebname;

	private String kvCollecttime;

	private String kvSourcetype;

	private Integer kvHot;

	private Integer kvIstf;

	private Integer kvIsrd;

	private Integer isusermain;

	private Integer ismainsimhash;

	private String kvTitle;

	private String losttime;

	private Integer kvOrienLevel;

	private String noisestatus;

	private String kvAbstract;

	private String kvUrl;

	private String kvKeyword;

	private String kvWbauthorpic = "";

	private String kvAuthor;

	private String krGtime;

	@Field(value = "KV_ISPIC")
	private Byte kvIspic;

	@Field(value = "KV_ISVIDEO")
	private Byte kvIsvideo;

	@Field(value = "KV_IMGURL")
	private String kvImgurl;

	@Field(value = "KV_VEDEOURL")
	private String kvVedeourl;

	private String kvTitlematch;

	private Integer kvMustwordminindex;

	private Integer kvKeywordsminindex;

	private String kvOnlylocal;

	private String kvWeiboovertime;

	private String kvWeibosignlocalnoise;

	private String kvWeibotopicnoise;

	private String kvWeiboatnoise;

	private Date kvDtctime;

	private Date kvDttime;

	private String kvWebchannel;

	private String kvHotkeyword;

	private String kvDomain;

	private String kvSiteid;

	private String kvInformationstate;

	private String entityarea;

	private String entitypeople;

	private String entitymethanism;

	private Integer importanceWeight;

	private Object kvExtendField;

	private Boolean kvInputdatatype;

	private Integer isQueryKrUuid;

	private Integer isRepeat;

	private Integer orgId;

	private String timeType;

	private Integer region;

	private List<Integer> infoSourceLevelList = new ArrayList<Integer>();

	private List<Integer> wechatInfoTypeList = new ArrayList<Integer>();

	private List<String> kvSourcetypeList = new ArrayList<String>();

	private List<String> kkIdList = new ArrayList<String>();

	private List<String> orientationList = new ArrayList<String>();

	private List<String> kvSimhashList = new ArrayList<String>();

	private List<String> kvUrlList = new ArrayList<String>();
	
	private String tags;

	private String kvContent;
	private String KvTitleMakeRed;
	private String kvAbstractMakeRed;
	private String kvContentMakeRed;
	private Integer msUserId;
	private String ocr;
	private String classifyId;

	public String getKrUuid() {
		return this.krUuid;
	}

	public void setKrUuid(String krUuid) {
		this.krUuid = krUuid == null ? null : krUuid.trim();
	}

	public String getKvUuid() {
		return this.kvUuid;
	}

	public void setKvUuid(String kvUuid) {
		this.kvUuid = kvUuid == null ? null : kvUuid.trim();
	}

	public String getKrInfotype() {
		return this.krInfotype;
	}

	public void setKrInfotype(String krInfotype) {
		this.krInfotype = krInfotype == null ? null : krInfotype.trim();
	}

	public String getKrKeywordid() {
		return this.krKeywordid;
	}

	public void setKrKeywordid(String krKeywordid) {
		this.krKeywordid = krKeywordid == null ? null : krKeywordid.trim();
	}

	public Integer getKrUid() {
		return this.krUid;
	}

	public void setKrUid(Integer krUid) {
		this.krUid = krUid;
	}

	public String getKrState() {
		return this.krState;
	}

	public void setKrState(String krState) {
		this.krState = krState == null ? null : krState.trim();
	}

	public String getKrCtime() {
		return this.krCtime;
	}

	public void setKrCtime(String krCtime) {
		this.krCtime = krCtime == null ? null : krCtime.trim();
	}

	public String getKrIslocal() {
		return this.krIslocal;
	}

	public void setKrIslocal(String krIslocal) {
		this.krIslocal = krIslocal == null ? null : krIslocal.trim();
	}

	public String getKvFlag() {
		return this.kvFlag;
	}

	public void setKvFlag(String kvFlag) {
		this.kvFlag = kvFlag == null ? null : kvFlag.trim();
	}

	public String getArg1() {
		return this.arg1;
	}

	public void setArg1(String arg1) {
		this.arg1 = arg1 == null ? null : arg1.trim();
	}

	public String getArg2() {
		return this.arg2;
	}

	public void setArg2(String arg2) {
		this.arg2 = arg2 == null ? null : arg2.trim();
	}

	public String getArg3() {
		return this.arg3;
	}

	public void setArg3(String arg3) {
		this.arg3 = arg3 == null ? null : arg3.trim();
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword == null ? null : keyword.trim();
	}

	public String getOrientation() {
		return this.orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation == null ? null : orientation.trim();
	}

	public Integer getIsyj() {
		return this.isyj;
	}

	public void setIsyj(Integer isyj) {
		this.isyj = isyj;
	}

	public Integer getIsread() {
		return this.isread;
	}

	public void setIsread(Integer isread) {
		this.isread = isread;
	}

	public Integer getIsmyattention() {
		return this.ismyattention;
	}

	public void setIsmyattention(Integer ismyattention) {
		this.ismyattention = ismyattention;
	}

	public Integer getIsfeedback() {
		return this.isfeedback;
	}

	public void setIsfeedback(Integer isfeedback) {
		this.isfeedback = isfeedback;
	}

	public String getArg4() {
		return this.arg4;
	}

	public void setArg4(String arg4) {
		this.arg4 = arg4 == null ? null : arg4.trim();
	}

	public String getArg5() {
		return this.arg5;
	}

	public void setArg5(String arg5) {
		this.arg5 = arg5 == null ? null : arg5.trim();
	}

	public Integer getKvVisitcount() {
		return this.kvVisitcount;
	}

	public void setKvVisitcount(Integer kvVisitcount) {
		this.kvVisitcount = kvVisitcount;
	}

	public Integer getKvReply() {
		return this.kvReply;
	}

	public void setKvReply(Integer kvReply) {
		this.kvReply = kvReply;
	}

	public String getKvSimhash() {
		return this.kvSimhash;
	}

	public void setKvSimhash(String kvSimhash) {
		this.kvSimhash = kvSimhash == null ? null : kvSimhash.trim();
	}

	public String getKvWebname() {
		return this.kvWebname;
	}

	public void setKvWebname(String kvWebname) {
		this.kvWebname = kvWebname == null ? null : kvWebname.trim();
	}

	public String getKvCollecttime() {
		return this.kvCollecttime;
	}

	public void setKvCollecttime(String kvCollecttime) {
		this.kvCollecttime = kvCollecttime == null ? null : kvCollecttime.trim();
	}

	public String getKvSourcetype() {
		return this.kvSourcetype;
	}

	public void setKvSourcetype(String kvSourcetype) {
		this.kvSourcetype = kvSourcetype == null ? null : kvSourcetype.trim();
	}

	public Integer getKvHot() {
		return this.kvHot;
	}

	public void setKvHot(Integer kvHot) {
		this.kvHot = kvHot;
	}

	public Integer getKvIstf() {
		return this.kvIstf;
	}

	public void setKvIstf(Integer kvIstf) {
		this.kvIstf = kvIstf;
	}

	public Integer getKvIsrd() {
		return this.kvIsrd;
	}

	public void setKvIsrd(Integer kvIsrd) {
		this.kvIsrd = kvIsrd;
	}

	public Integer getIsusermain() {
		return this.isusermain;
	}

	public void setIsusermain(Integer isusermain) {
		this.isusermain = isusermain;
	}

	public Integer getIsmainsimhash() {
		return this.ismainsimhash;
	}

	public void setIsmainsimhash(Integer ismainsimhash) {
		this.ismainsimhash = ismainsimhash;
	}

	public String getKvTitle() {
		return this.kvTitle;
	}

	public void setKvTitle(String kvTitle) {
		this.kvTitle = kvTitle == null ? null : kvTitle.trim();
	}

	public String getLosttime() {
		return this.losttime;
	}

	public void setLosttime(String losttime) {
		this.losttime = losttime == null ? null : losttime.trim();
	}

	public Integer getKvOrienLevel() {
		return this.kvOrienLevel;
	}

	public void setKvOrienLevel(Integer kvOrienLevel) {
		this.kvOrienLevel = kvOrienLevel;
	}

	public String getNoisestatus() {
		return this.noisestatus;
	}

	public void setNoisestatus(String noisestatus) {
		this.noisestatus = noisestatus == null ? null : noisestatus.trim();
	}

	public String getKvAbstract() {
		return this.kvAbstract;
	}

	public void setKvAbstract(String kvAbstract) {
		this.kvAbstract = kvAbstract == null ? null : kvAbstract.trim();
	}

	public String getKvUrl() {
		return this.kvUrl;
	}

	public void setKvUrl(String kvUrl) {
		this.kvUrl = kvUrl == null ? null : kvUrl.trim();
	}

	public String getKvKeyword() {
		return this.kvKeyword;
	}

	public void setKvKeyword(String kvKeyword) {
		this.kvKeyword = kvKeyword == null ? null : kvKeyword.trim();
	}

	public String getKvWbauthorpic() {
		return this.kvWbauthorpic;
	}

	public void setKvWbauthorpic(String kvWbauthorpic) {
		this.kvWbauthorpic = kvWbauthorpic == null ? null : kvWbauthorpic.trim();
	}

	public String getKvAuthor() {
		return this.kvAuthor;
	}

	public void setKvAuthor(String kvAuthor) {
		this.kvAuthor = kvAuthor == null ? null : kvAuthor.trim();
	}

	public String getKrGtime() {
		return this.krGtime;
	}

	public void setKrGtime(String krGtime) {
		this.krGtime = krGtime == null ? null : krGtime.trim();
	}

	public Byte getKvIspic() {
		return this.kvIspic;
	}

	public void setKvIspic(Byte kvIspic) {
		this.kvIspic = kvIspic;
	}

	public Byte getKvIsvideo() {
		return this.kvIsvideo;
	}

	public void setKvIsvideo(Byte kvIsvideo) {
		this.kvIsvideo = kvIsvideo;
	}

	public String getKvImgurl() {
		return this.kvImgurl;
	}

	public void setKvImgurl(String kvImgurl) {
		this.kvImgurl = kvImgurl == null ? null : kvImgurl.trim();
	}

	public String getKvVedeourl() {
		return this.kvVedeourl;
	}

	public void setKvVedeourl(String kvVedeourl) {
		this.kvVedeourl = kvVedeourl == null ? null : kvVedeourl.trim();
	}

	public String getKvTitlematch() {
		return this.kvTitlematch;
	}

	public void setKvTitlematch(String kvTitlematch) {
		this.kvTitlematch = kvTitlematch == null ? null : kvTitlematch.trim();
	}

	public Integer getKvMustwordminindex() {
		return this.kvMustwordminindex;
	}

	public void setKvMustwordminindex(Integer kvMustwordminindex) {
		this.kvMustwordminindex = kvMustwordminindex;
	}

	public Integer getKvKeywordsminindex() {
		return this.kvKeywordsminindex;
	}

	public void setKvKeywordsminindex(Integer kvKeywordsminindex) {
		this.kvKeywordsminindex = kvKeywordsminindex;
	}

	public String getKvOnlylocal() {
		return this.kvOnlylocal;
	}

	public void setKvOnlylocal(String kvOnlylocal) {
		this.kvOnlylocal = kvOnlylocal == null ? null : kvOnlylocal.trim();
	}

	public String getKvWeiboovertime() {
		return this.kvWeiboovertime;
	}

	public void setKvWeiboovertime(String kvWeiboovertime) {
		this.kvWeiboovertime = kvWeiboovertime == null ? null : kvWeiboovertime.trim();
	}

	public String getKvWeibosignlocalnoise() {
		return this.kvWeibosignlocalnoise;
	}

	public void setKvWeibosignlocalnoise(String kvWeibosignlocalnoise) {
		this.kvWeibosignlocalnoise = kvWeibosignlocalnoise == null ? null : kvWeibosignlocalnoise.trim();
	}

	public String getKvWeibotopicnoise() {
		return this.kvWeibotopicnoise;
	}

	public void setKvWeibotopicnoise(String kvWeibotopicnoise) {
		this.kvWeibotopicnoise = kvWeibotopicnoise == null ? null : kvWeibotopicnoise.trim();
	}

	public String getKvWeiboatnoise() {
		return this.kvWeiboatnoise;
	}

	public void setKvWeiboatnoise(String kvWeiboatnoise) {
		this.kvWeiboatnoise = kvWeiboatnoise == null ? null : kvWeiboatnoise.trim();
	}

	public Date getKvDtctime() {
		return this.kvDtctime;
	}

	public void setKvDtctime(Date kvDtctime) {
		this.kvDtctime = kvDtctime;
	}

	public Date getKvDttime() {
		return this.kvDttime;
	}

	public void setKvDttime(Date kvDttime) {
		this.kvDttime = kvDttime;
	}

	public String getKvWebchannel() {
		return this.kvWebchannel;
	}

	public void setKvWebchannel(String kvWebchannel) {
		this.kvWebchannel = kvWebchannel == null ? null : kvWebchannel.trim();
	}

	public String getKvHotkeyword() {
		return this.kvHotkeyword;
	}

	public void setKvHotkeyword(String kvHotkeyword) {
		this.kvHotkeyword = kvHotkeyword == null ? null : kvHotkeyword.trim();
	}

	public String getKvDomain() {
		return this.kvDomain;
	}

	public void setKvDomain(String kvDomain) {
		this.kvDomain = kvDomain == null ? null : kvDomain.trim();
	}

	public String getKvSiteid() {
		return this.kvSiteid;
	}

	public void setKvSiteid(String kvSiteid) {
		this.kvSiteid = kvSiteid == null ? null : kvSiteid.trim();
	}

	public String getKvInformationstate() {
		return this.kvInformationstate;
	}

	public void setKvInformationstate(String kvInformationstate) {
		this.kvInformationstate = kvInformationstate == null ? null : kvInformationstate.trim();
	}

	public String getEntityarea() {
		return this.entityarea;
	}

	public void setEntityarea(String entityarea) {
		this.entityarea = entityarea == null ? null : entityarea.trim();
	}

	public String getEntitypeople() {
		return this.entitypeople;
	}

	public void setEntitypeople(String entitypeople) {
		this.entitypeople = entitypeople == null ? null : entitypeople.trim();
	}

	public String getEntitymethanism() {
		return this.entitymethanism;
	}

	public void setEntitymethanism(String entitymethanism) {
		this.entitymethanism = entitymethanism == null ? null : entitymethanism.trim();
	}

	public Integer getImportanceWeight() {
		return this.importanceWeight;
	}

	public void setImportanceWeight(Integer importanceWeight) {
		this.importanceWeight = importanceWeight;
	}

	public Object getKvExtendField() {
		return this.kvExtendField;
	}

	public void setKvExtendField(Object kvExtendField) {
		this.kvExtendField = kvExtendField;
	}

	public Boolean getKvInputdatatype() {
		return this.kvInputdatatype;
	}

	public void setKvInputdatatype(Boolean kvInputdatatype) {
		this.kvInputdatatype = kvInputdatatype;
	}

	public Integer getIsQueryKrUuid() {
		return this.isQueryKrUuid;
	}

	public void setIsQueryKrUuid(Integer isQueryKrUuid) {
		this.isQueryKrUuid = isQueryKrUuid;
	}

	public Integer getIsRepeat() {
		return this.isRepeat;
	}

	public void setIsRepeat(Integer isRepeat) {
		this.isRepeat = isRepeat;
	}

	public List<String> getKvSourcetypeList() {
		return this.kvSourcetypeList;
	}

	public void setKvSourcetypeList(List<String> kvSourcetypeList) {
		this.kvSourcetypeList = kvSourcetypeList;
	}

	public Integer getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public List<String> getKkIdList() {
		return this.kkIdList;
	}

	public void setKkIdList(List<String> kkIdList) {
		this.kkIdList = kkIdList;
	}

	public String getTimeType() {
		return this.timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public static String getTimetypeDay() {
		return TIMETYPE_DAY;
	}

	public static String getTimetypeSevendays() {
		return TIMETYPE_SEVENDAYS;
	}

	public static String getTimetypeCustomday() {
		return TIMETYPE_CUSTOMDAY;
	}

	public List<String> getOrientationList() {
		return this.orientationList;
	}

	public void setOrientationList(List<String> orientationList) {
		this.orientationList = orientationList;
	}

	public Integer getRegion() {
		return this.region;
	}

	public void setRegion(Integer region) {
		this.region = region;
	}

	public String getKvContent() {
		return this.kvContent;
	}

	public void setKvContent(String kvContent) {
		this.kvContent = kvContent;
	}

	public List<String> getKvSimhashList() {
		return this.kvSimhashList;
	}

	public void setKvSimhashList(List<String> kvSimhashList) {
		this.kvSimhashList = kvSimhashList;
	}

	public List<String> getKvUrlList() {
		return this.kvUrlList;
	}

	public void setKvUrlList(List<String> kvUrlList) {
		this.kvUrlList = kvUrlList;
	}

	public String getKvTitleMakeRed() {
		return this.KvTitleMakeRed;
	}

	public void setKvTitleMakeRed(String kvTitleMakeRed) {
		this.KvTitleMakeRed = kvTitleMakeRed;
	}

	public String getKvAbstractMakeRed() {
		return this.kvAbstractMakeRed;
	}

	public void setKvAbstractMakeRed(String kvAbstractMakeRed) {
		this.kvAbstractMakeRed = kvAbstractMakeRed;
	}

	public String getKvContentMakeRed() {
		return kvContentMakeRed;
	}

	public void setKvContentMakeRed(String kvContentMakeRed) {
		this.kvContentMakeRed = kvContentMakeRed;
	}

	public List<Integer> getWechatInfoTypeList() {
		return wechatInfoTypeList;
	}

	public void setWechatInfoTypeList(List<Integer> wechatInfoTypeList) {
		this.wechatInfoTypeList = wechatInfoTypeList;
	}

	public List<Integer> getInfoSourceLevelList() {
		return infoSourceLevelList;
	}

	public void setInfoSourceLevelList(List<Integer> infoSourceLevelList) {
		this.infoSourceLevelList = infoSourceLevelList;
	}

	public Integer getMsUserId() {
		return msUserId;
	}

	public void setMsUserId(Integer msUserId) {
		this.msUserId = msUserId;
	}

	public String getOcr() {
		return ocr;
	}

	public void setOcr(String ocr) {
		this.ocr = ocr;
	}

	public String getClassifyId() {
		return classifyId;
	}

	public void setClassifyId(String classifyId) {
		this.classifyId = classifyId;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}


}