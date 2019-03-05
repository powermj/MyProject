package com.zhxg.yqzj.entities.v1;

import java.util.Date;
import java.util.List;

import com.zhxg.framework.base.curd.impl.BaseEntity;

public class VideoInfo extends BaseEntity {
	private Integer id;
	private String infoId;
	private String originalLink;
	private Integer infoSource;
	private String infoContent;
	private String keyword;
	private Integer isRead;
	private Integer isWarning;
	private Integer orientation;
	private String subjectId;
	private String subjectName;
	private String coverImageLink;
	private String videoOriginalLink;
	private Integer isCommentData;
	private Integer frequencyOfPlay;
	private Integer numberOfPoints;
	private Date releaseTime;
	private Date acquisitionTime;
	private String author;
	private String authorHeadLink;
	private Integer authorFansCount;
	private Integer authorNumberOfWorks;
	private String simHash;
	private Integer simHashCount;
	private List<Integer> sourceIdList;
	private List<Integer> orientationList;
	private String eTime;
	private String bTime;
	private String insertBtime;
	private String insertEtime;
	private Boolean isRepeat;
	private Integer isDelete;
	private List<String> subjectIdList;
	private String commentContent;
	private String authorKey;
	private String authorValue;
	private String searchContent;
	private Integer followersCount;
	private String avatar;
	private Integer videoCount;
	private Integer shareCount;
	private String location;
	private Integer videoUpCount;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInfoId() {
		return this.infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	public String getOriginalLink() {
		return this.originalLink;
	}

	public void setOriginalLink(String originalLink) {
		this.originalLink = originalLink;
	}

	public Integer getInfoSource() {
		return this.infoSource;
	}

	public void setInfoSource(Integer infoSource) {
		this.infoSource = infoSource;
	}

	public String getInfoContent() {
		return this.infoContent;
	}

	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getIsRead() {
		return this.isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public Integer getIsWarning() {
		return this.isWarning;
	}

	public void setIsWarning(Integer isWarning) {
		this.isWarning = isWarning;
	}

	public Integer getOrientation() {
		return this.orientation;
	}

	public void setOrientation(Integer orientation) {
		this.orientation = orientation;
	}

	public String getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getCoverImageLink() {
		return this.coverImageLink;
	}

	public void setCoverImageLink(String coverImageLink) {
		this.coverImageLink = coverImageLink;
	}

	public String getVideoOriginalLink() {
		return this.videoOriginalLink;
	}

	public void setVideoOriginalLink(String videoOriginalLink) {
		this.videoOriginalLink = videoOriginalLink;
	}

	public Integer getIsCommentData() {
		return this.isCommentData;
	}

	public void setIsCommentData(Integer isCommentData) {
		this.isCommentData = isCommentData;
	}

	public Integer getFrequencyOfPlay() {
		return this.frequencyOfPlay;
	}

	public void setFrequencyOfPlay(Integer frequencyOfPlay) {
		this.frequencyOfPlay = frequencyOfPlay;
	}

	public Integer getNumberOfPoints() {
		return this.numberOfPoints;
	}

	public void setNumberOfPoints(Integer numberOfPoints) {
		this.numberOfPoints = numberOfPoints;
	}

	public Date getReleaseTime() {
		return this.releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public Date getAcquisitionTime() {
		return this.acquisitionTime;
	}

	public void setAcquisitionTime(Date acquisitionTime) {
		this.acquisitionTime = acquisitionTime;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorHeadLink() {
		return this.authorHeadLink;
	}

	public void setAuthorHeadLink(String authorHeadLink) {
		this.authorHeadLink = authorHeadLink;
	}

	public Integer getAuthorFansCount() {
		return this.authorFansCount;
	}

	public void setAuthorFansCount(Integer authorFansCount) {
		this.authorFansCount = authorFansCount;
	}

	public Integer getAuthorNumberOfWorks() {
		return this.authorNumberOfWorks;
	}

	public void setAuthorNumberOfWorks(Integer authorNumberOfWorks) {
		this.authorNumberOfWorks = authorNumberOfWorks;
	}

	public List<Integer> getSourceIdList() {
		return this.sourceIdList;
	}

	public void setSourceIdList(List<Integer> sourceIdList) {
		this.sourceIdList = sourceIdList;
	}

	public List<Integer> getOrientationList() {
		return this.orientationList;
	}

	public void setOrientationList(List<Integer> orientationList) {
		this.orientationList = orientationList;
	}

	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String geteTime() {
		return this.eTime;
	}

	public void seteTime(String eTime) {
		this.eTime = eTime;
	}

	public String getbTime() {
		return this.bTime;
	}

	public void setbTime(String bTime) {
		this.bTime = bTime;
	}

	public String getSimHash() {
		return this.simHash;
	}

	public void setSimHash(String simHash) {
		this.simHash = simHash;
	}

	public Boolean getIsRepeat() {
		return this.isRepeat;
	}

	public void setIsRepeat(Boolean isRepeat) {
		this.isRepeat = isRepeat;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getSimHashCount() {
		return this.simHashCount;
	}

	public void setSimHashCount(Integer simHashCount) {
		this.simHashCount = simHashCount;
	}

	public String getSearchContent() {
		return this.searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

	public List<String> getSubjectIdList() {
		return subjectIdList;
	}

	public void setSubjectIdList(List<String> subjectIdList) {
		this.subjectIdList = subjectIdList;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getAuthorKey() {
		return authorKey;
	}

	public void setAuthorKey(String authorKey) {
		this.authorKey = authorKey;
	}

	public String getAuthorValue() {
		return authorValue;
	}

	public void setAuthorValue(String authorValue) {
		this.authorValue = authorValue;
	}

	public Integer getFollowersCount() {
		return followersCount;
	}

	public void setFollowersCount(Integer followersCount) {
		this.followersCount = followersCount;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getVideoCount() {
		return videoCount;
	}

	public void setVideoCount(Integer videoCount) {
		this.videoCount = videoCount;
	}

	public Integer getShareCount() {
		return shareCount;
	}

	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getVideoUpCount() {
		return videoUpCount;
	}

	public void setVideoUpCount(Integer videoUpCount) {
		this.videoUpCount = videoUpCount;
	}

	public String getInsertBtime() {
		return insertBtime;
	}

	public void setInsertBtime(String insertBtime) {
		this.insertBtime = insertBtime;
	}

	public String getInsertEtime() {
		return insertEtime;
	}

	public void setInsertEtime(String insertEtime) {
		this.insertEtime = insertEtime;
	}

}
