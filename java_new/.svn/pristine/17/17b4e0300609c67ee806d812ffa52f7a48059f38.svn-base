package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.SourceTypeDao;
import com.zhxg.yqzj.entities.v1.SourceType;

@Repository
public class SourceTypeDaoImpl extends BaseDaoImpl<BaseEntity> implements SourceTypeDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.SourceType.";
    
    /**
     * 获取所有媒体类型
     */
    @Override
    public List<SourceType> getAllSourceType(Map<String,Object> params) {
       return this.sqlSessionTemplate.selectList(NAME_SPACE+"getAllSourceType",params);
    }
}
