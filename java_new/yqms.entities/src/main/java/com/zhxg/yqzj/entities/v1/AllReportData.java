package com.zhxg.yqzj.entities.v1;

import com.zhxg.framework.base.curd.impl.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.entities
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: <报告数据池实体类>
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqzj.entities.v1.AllReportData.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年7月30日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */

@ApiModel(value="报告数据池实体类",description="报告数据池实体类")
public class AllReportData extends BaseEntity {

    /**
     * 主键
     */
    @ApiModelProperty(value="主键",name="infoUuid",example="信息主键ID")
    private String infoUuid;
    /**
     * 标题
     */
    @ApiModelProperty(value="标题",name="title",example="信息标题")
    private String title;
    /**
     * 摘要
     */
    @ApiModelProperty(value="摘要",name="summary",example="信息摘要")
    private String summary;
    /**
     * 正文
     */
    @ApiModelProperty(value="正文",name="content",example="信息正文")
    private String content;
    
    /**
     * 带格式正文
     */
    @ApiModelProperty(value="带格式正文",name="contentxml",example="带格式信息正文")
    private String contentXml;
    
    /**
     * 来源网站
     */
    @ApiModelProperty(value="来源网站",name="webName",example="信息来源网站")
    private String webName;
    /**
     * 媒体类型
     */
    @ApiModelProperty(value="媒体类型",name="sourceType",example="信息媒体类型")
    private String sourceType;
    /**
     * 作者
     */
    @ApiModelProperty(value="作者",name="author",example="信息作者")
    private String author;
    /**
     * infoUrl
     */
    @ApiModelProperty(value="信息原链接",name="infoUrl",example="信息url")
    private String infoUrl;
    /**
     * 倾向性
     */
    @ApiModelProperty(value="倾向性",name="orientation",example="信息倾向性")
    private String orientation;
    /**
     * 发布时间
     */
    @ApiModelProperty(value="发布时间",name="publishTime",example="信息发布时间")
    private String publishTime;
    
    /**
     * 关键词
     */
    @ApiModelProperty(value="关键词",name="keyWord",example="信息关键词")
    private String keyWord;
    /**
     * 是否预警
     */
    @ApiModelProperty(value="是否预警",name="isWarning",example="信息是否预警")
    private int isWarning;
    
    /**
     * 是否关注
     */
    @ApiModelProperty(value="是否关注",name="isAttention",example="信息是否关注")
    private int isAttention;
    
    /**
     * 是否已读
     */
    @ApiModelProperty(value="是否已读",name="isRead",example="信息是否已读")
    private int isRead;
    
    /**
     * 访问数
     */
    @ApiModelProperty(value="访问数",name="visitCount",example="信息访问数")
    private int visitCount;
    
    /**
     * 评论数
     */
    @ApiModelProperty(value="评论数",name="replyCount",example="信息评论数")
    private int replyCount;
    
    /**
     * infoSimhash
     */
    @ApiModelProperty(value="simhash",name="infoSimhash",example="信息simhash")
    private String infoSimhash;
    /**
     * 头像url
     */
    @ApiModelProperty(value="头像url",name="authorPic",example="信息作者头像Url")
    private String authorPic;
    
    /**
     * 域名
     */
    @ApiModelProperty(value="域名",name="domain",example="信息域名")
    private String domain;
    
    /**
     * 图片url
     */
    @ApiModelProperty(value="图片url",name="imgUrl",example="信息图片Url")
    private String imgUrl;
    
    /**
     * 视频url
     */
    @ApiModelProperty(value="视频url",name="vedioUrl",example="信息视频Url")
    private String vedioUrl;
    
    /**
     * 重要度
     */
    @ApiModelProperty(value="重要度",name="importanceWeight",example="信息重要度")
    private String importanceWeight;
    
    /**
     * 扩展字段
     */
    @ApiModelProperty(value="扩展字段",name="extendField",example="信息扩展字段")
    private String extendField;
    /**
     * 入库时间
     */
    @ApiModelProperty(value="入库时间",name="enterTime",example="信息入库时间")
    private String enterTime;
    /**
     * 分类ID
     */
    @ApiModelProperty(value="分类ID",name="classifyId",example="信息分类ID")
    private Integer classifyId;
    
    /**
     * 分类ID
     */
    @ApiModelProperty(value="分类名称",name="classifyName",example="信息分类名称")
    private String classifyName;
    /**
     * 是否生成简报
     */
    @ApiModelProperty(value="是否生成简报",name="isExport",example="信息是否生成简报 1-生成 0-未生成")
    private String isExport;
    
    
    public String getInfoUuid() {
        return infoUuid;
    }

    
    public void setInfoUuid(String infoUuid) {
        this.infoUuid = infoUuid;
    }

    
    public String getInfoSimhash() {
        return infoSimhash;
    }

    
    public void setInfoSimhash(String infoSimhash) {
        this.infoSimhash = infoSimhash;
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getSummary() {
        return summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    
    public String getWebName() {
        return webName;
    }

    
    public void setWebName(String webName) {
        this.webName = webName;
    }

    public String getSourceType() {
        return sourceType;
    }
    
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getOrientation() {
        return orientation;
    }
    
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
   

    
    public String getPublishTime() {
        return publishTime;
    }


    
    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }


    public String getEnterTime() {
        return enterTime;
    }


    
    public void setEnterTime(String enterTime) {
        this.enterTime = enterTime;
    }


    public Integer getClassifyId() {
        return classifyId;
    }
    
    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }
    
    public String getIsExport() {
        return isExport;
    }
    
    public void setIsExport(String isExport) {
        this.isExport = isExport;
    }

    
    public String getInfoUrl() {
        return infoUrl;
    }
    
    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }
    
    public String getKeyWord() {
        return keyWord;
    }

    
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    
    public int getIsWarning() {
        return isWarning;
    }

    
    public void setIsWarning(int isWarning) {
        this.isWarning = isWarning;
    }

    
    public int getIsAttention() {
        return isAttention;
    }

    
    public void setIsAttention(int isAttention) {
        this.isAttention = isAttention;
    }

    
    public int getIsRead() {
        return isRead;
    }

    
    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    
    public int getVisitCount() {
        return visitCount;
    }

    
    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    
    public int getReplyCount() {
        return replyCount;
    }

    
    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getAuthorPic() {
        return authorPic;
    }

    
    public void setAuthorPic(String authorPic) {
        this.authorPic = authorPic;
    }

    
    public String getDomain() {
        return domain;
    }

    
    public void setDomain(String domain) {
        this.domain = domain;
    }

    
    public String getImgUrl() {
        return imgUrl;
    }

    
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    
    public String getVedioUrl() {
        return vedioUrl;
    }

    
    public void setVedioUrl(String vedioUrl) {
        this.vedioUrl = vedioUrl;
    }

    
    public String getImportanceWeight() {
        return importanceWeight;
    }

    
    public void setImportanceWeight(String importanceWeight) {
        this.importanceWeight = importanceWeight;
    }


    
    public String getExtendField() {
        return extendField;
    }


    
    public void setExtendField(String extendField) {
        this.extendField = extendField;
    }


    
    public String getClassifyName() {
        return classifyName;
    }


    
    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }


    
    public String getContentXml() {
        return contentXml;
    }


    
    public void setContentXml(String contentXml) {
        this.contentXml = contentXml;
    }
    
}
