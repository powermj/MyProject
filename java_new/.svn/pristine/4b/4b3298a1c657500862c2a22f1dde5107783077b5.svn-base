package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.MobileCloudCourseDao;
import com.zhxg.yqzj.entities.v1.MobileCloudCourse;

@Repository
public class MobileCloudCourseDaoImpl extends BaseDaoImpl<BaseEntity> implements MobileCloudCourseDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.MobileCloudCourse.";

    @Override
    public List<MobileCloudCourse> getAllCourse(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getAllCourse",params);
    }

    @Override
    public List<MobileCloudCourse> getCourseList(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getCourseList",params);
    }

    @Override
    public List<MobileCloudCourse> getAllCoursewareInfo(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getAllCoursewareInfo",params);
    }

    
}
