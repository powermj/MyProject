package com.zhxg.yqzj.entities.v1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhxg.framework.base.serializer.JsonDateSerializer;
import com.zhxg.framework.base.utils.HtmlFilter;
import com.zhxg.framework.base.utils.OrientationUtil;
import com.zhxg.framework.base.utils.SourceTypeUtil;

public class Info {
    
    public static Map<String,String> fieldNameMap = new HashMap<>();
    
    {
        fieldNameMap.put("${title}", "title");
        fieldNameMap.put("${summary}", "summary");
        fieldNameMap.put("${publishTime}", "publishTime");
        fieldNameMap.put("${enterTime}", "enterTime");
        fieldNameMap.put("${orientation}", "orientation");
        fieldNameMap.put("${keyword}", "keyword");
        fieldNameMap.put("${visitCount}", "visitCount");
        fieldNameMap.put("${commentNum}", "replyCount");
        fieldNameMap.put("${webName}", "webName");
        fieldNameMap.put("${sourceType}", "sourceType");
        fieldNameMap.put("${content}", "content");
        fieldNameMap.put("${infoUrl}", "infoUrl");
        fieldNameMap.put("${author}", "author");
        fieldNameMap.put("${domain}", "domain");
    }
    
//    public Info(){
//        
//        Field[] fields = this.getClass().getDeclaredFields();
//        for(Field field:fields){
//            try {
//                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), this.getClass());
//                Method rM = pd.getReadMethod();//获得读方法  
//                String value = rM.invoke(this)+"";
//            } catch (IntrospectionException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IllegalArgumentException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } 
//        }
//        
//    }
    /**
     * 主键
     */
    private String id;
    
    /**
     * 信息主键
     */
    private String infoUuid;
    
    /**
     * 倾向性 1 正面 2 负面 3 中性
     */
    private String orientation;
    
    /**
     * 关键词
     */
    private String keyword;
    
    /**
     * 发布时间
     */
    @JsonSerialize(using = JsonDateSerializer.class)  
    private Date publishTime ;
    
    /**
     * 采集时间
     */
    @JsonSerialize(using = JsonDateSerializer.class)  
    private Date enterTime;
    
    /**
     * 是否预警
     */
    private int isWarning;
    
    /**
     * 是否关注
     */
    private int isAttention;
    
    /**
     * 是否已读
     */
    private int isRead;
    
    /**
     * 访问数
     */
    private int visitCount;
    
    /**
     * 评论数
     */
    private int replyCount;
    
    /**
     * simhash
     */
    private String infoSimhash;
    
    /**
     * 来源网站
     */
    private String webName;
    
    /**
     * 媒体类型
     */
    private String sourceType;
    
    /**
     * 标题
     */
    private String title;
    
    /**
     * 摘要
     */
    private String summary;
    
    /**
     * 内容
     */
    private String content;
    
    /**
     * url
     */
    private String infoUrl;
    
    /**
     * 作者
     */
    private String author;
    
    /**
     * 头像url
     */
    private String authorPic;
    
    /**
     * 域名
     */
    private String domain;
    
    /**
     * 图片url
     */
    private String imgUrl;
    
    /**
     * 视频url
     */
    private String vedioUrl;
    
    /**
     * 数据分类ID
     */
    private Integer classifyId;
    
    /**
     * 重要度
     */
    private Double importanceWeight;
    
    /**
     * 扩展字段
     */
    private JSONObject extendField;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
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

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = SourceTypeUtil.getSourceName(sourceType);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public Double getImportanceWeight() {
        return importanceWeight;
    }

    public void setImportanceWeight(Double importanceWeight) {
        this.importanceWeight = importanceWeight;
    }

    public JSONObject getExtendField() {
        return extendField;
    }
    
    

    
    public String getInfoUuid() {
        return infoUuid;
    }

    
    public void setInfoUuid(String infoUuid) {
        this.infoUuid = infoUuid;
    }

    
    public Date getEnterTime() {
        return enterTime;
    }

    
    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    
    public String getInfoSimhash() {
        return infoSimhash;
    }

    
    public void setInfoSimhash(String infoSimhash) {
        this.infoSimhash = infoSimhash;
    }

    
    public String getWebName() {
        return webName;
    }

    
    public void setWebName(String webName) {
        this.webName = webName;
    }

    
    public String getSummary() {
        return summary;
    }

    
    public void setSummary(String summary) {
        this.summary = summary;
    }

    
    public String getInfoUrl() {
        return infoUrl;
    }

    
    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }

    public void setExtendField(JSONObject extendField) {
        this.extendField = extendField;
    }
    
    public Integer getClassifyId() {
        return classifyId;
    }

    
    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public String getValueByField(String field, String dateFormat){
        Object obj = ((JSONObject)JSON.toJSON(this)).get(field);
        if(obj == null){
            return "无";
        }else if(obj instanceof Date){
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            return sdf.format(obj).toString();
        }else{
            if("content".equals(field)){
                String content = HtmlFilter.filterWord(obj+"");
                content = content.replace("\r\n", " ").replace("\r", " ");
                content = content.replaceAll("\\[beginimg\\](.*?)\\[endimg\\]", "");
                content = content.replace("<br />", " ").replace("<br/>", " ");
                content = content.replaceAll("beginimg(.*?)endimg", "");
                obj = content.replaceAll("\0", " ");
            }
            if("orientation".equals(field)){
                String orienName = obj+"";
                obj = OrientationUtil.getSourceName(orienName);
            }
            return obj+""; 
        }
        
    }
    
}
