package com.zhxg.yqzj.entities.v1;

import com.zhxg.framework.base.curd.impl.BaseEntity;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.entities
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: <移动云推荐专题主体词实体类>
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqzj.entities.v1.MobileCloudSubjectInfo.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年7月22日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public class MobileCloudEventInfo extends BaseEntity {

    /**
     * 自增主键
     */
    private Integer id;
    /**
     * 二级行业id
     */
    private String departmentId;
    /**
     * 主体词
     */
    private String subjectWord;
    /**
     * 专题名称
     */
    private String thematicName;
    /**
     * 事件词
     */
    private String eventWord;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getDepartmentId() {
        return departmentId;
    }
    
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    
    
    public String getThematicName() {
        return thematicName;
    }

    
    public void setThematicName(String thematicName) {
        this.thematicName = thematicName;
    }

    
    public String getEventWord() {
        return eventWord;
    }

    
    public void setEventWord(String eventWord) {
        this.eventWord = eventWord;
    }

    
    public String getSubjectWord() {
        return subjectWord;
    }

    
    public void setSubjectWord(String subjectWord) {
        this.subjectWord = subjectWord;
    }
}
