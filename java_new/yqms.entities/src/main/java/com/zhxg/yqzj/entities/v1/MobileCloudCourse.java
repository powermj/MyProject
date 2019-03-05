package com.zhxg.yqzj.entities.v1;

import java.util.List;

import com.zhxg.framework.base.curd.impl.BaseEntity;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.entities
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: <移动云舆情课程>
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqzj.entities.v1.MobileCloudSubjectInfo.java
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
public class MobileCloudCourse extends BaseEntity {

    /**
     * 课程自增id
     */
    private Integer id;
    /**
     * 课程标题
     */
    private String courseTitle;
    /**
     * 课程简介
     */
    private String courseSummary;
    /**
     * 课程讲师
     */
    private String courseLectuer;
    /**
     * 课程发布时间
     */
    private String courseReleaseTime;
    /**
     * 课件数量
     */
    private Integer courseNum;
    /**
     * 课程图片地址
     */
    private String coursePicUrl;
    /**
     * 课程讲师id
     */
    private String lectuerId;
    /**
     * 课程下所有课件
     */
    private List<MobileCloudCourseware> coursewares;
    /**
     * 课程所有讲师
     */
    private List<MobileCloudCourseLectuer> courseLectuers;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getCourseTitle() {
        return courseTitle;
    }
    
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
    
    public String getCourseSummary() {
        return courseSummary;
    }
    
    public void setCourseSummary(String courseSummary) {
        this.courseSummary = courseSummary;
    }
    
    public String getCourseReleaseTime() {
        return courseReleaseTime;
    }
    
    public void setCourseReleaseTime(String courseReleaseTime) {
        this.courseReleaseTime = courseReleaseTime;
    }
    
    public Integer getCourseNum() {
        return courseNum;
    }
    
    public void setCourseNum(Integer courseNum) {
        this.courseNum = courseNum;
    }
    
    public String getCoursePicUrl() {
        return coursePicUrl;
    }
    
    public void setCoursePicUrl(String coursePicUrl) {
        this.coursePicUrl = coursePicUrl;
    }
    
    public String getLectuerId() {
        return lectuerId;
    }

    
    public void setLectuerId(String lectuerId) {
        this.lectuerId = lectuerId;
    }

    
    public List<MobileCloudCourseware> getCoursewares() {
        return coursewares;
    }

    
    public void setCoursewares(List<MobileCloudCourseware> coursewares) {
        this.coursewares = coursewares;
    }

    
    public List<MobileCloudCourseLectuer> getCourseLectuers() {
        return courseLectuers;
    }

    
    public void setCourseLectuers(List<MobileCloudCourseLectuer> courseLectuers) {
        this.courseLectuers = courseLectuers;
    }

    
    public String getCourseLectuer() {
        return courseLectuer;
    }

    public void setCourseLectuer(String courseLectuer) {
        this.courseLectuer = courseLectuer;
    }
    
}
