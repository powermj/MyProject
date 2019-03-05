package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.yqzj.dao.v1.TopicEventRegionDao;
import com.zhxg.yqzj.entities.v1.TopicEventRegion;

@Repository
public class TopicEventRegionDaoImpl extends BaseDaoImpl<TopicEventRegion> implements TopicEventRegionDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.TopicEventRegion.";

    /**
     * 获取活跃媒体类型数量
     */
    @Override
    public List<TopicEventRegion> getMediaSourceTypeNum_other(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getMediaSourceTypeNum",params);
    }

    /**
     * 获取活跃媒体详细信息
     */
    @Override
    public PageInfo<TopicEventRegion> getAllMediaInfo_other(Map<String, Object> params,Pagination pageInfo) {
        return this.getPageList(NAME_SPACE+"getAllMediaInfo", pageInfo, params);
    }

    @Override
    public List<TopicEventRegion> getAllMediaInfo_other(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getAllMediaInfo",params);
    }
}
