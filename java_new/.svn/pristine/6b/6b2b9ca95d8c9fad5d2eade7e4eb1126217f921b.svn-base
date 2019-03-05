package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.ValidationRefDao;
import com.zhxg.yqzj.entities.v1.ValidationRef;

@Repository
public class ValidationRefDaoImpl extends BaseDaoImpl<BaseEntity> implements ValidationRefDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.ValidationRef.";
    
    @Override
    public ValidationRef getDetailRefInfo_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getDetailRefInfo_self", paramMap);
    }
    
    @Override
    public List<String> getDetailRefInfos_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getDetailRefInfos", paramMap);
    }
    
    @Override
    public List<ValidationRef> getDetailSameInfo_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getDetailSameInfo_self",paramMap);
    }

    @Override
    public int getReloadNumber_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getReloadNumber_self",paramMap);
    }

    @Override
    public String getDetailInfoCnt_info(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getDetailInfoCnt_info",paramMap);
    }

    @Override
    public ValidationRef getDetailInfoCnt_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getDetailInfoCnt_self",paramMap);
    }

    @Override
    public List<ValidationRef> getDetailRelatedTopicId_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getDetailRelatedTopicId_self",paramMap);
    }

    @Override
    public int updateYqllOrgiation_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.update(NAME_SPACE+"updateYqllOrgiation", paramMap);
    }

    @Override
    public String getDetailRegionInfoCnt_info(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getDetailRegionInfoCnt", paramMap);
    }

    @Override
    public String getDetailShareInfoCnt_info(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getDetailShareInfoCnt_info", paramMap);
    }

    @Override
    public String getDetailShareInfoCnt_other(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getDetailShareInfoCnt_other", paramMap);
    }

    @Override
    public ValidationRef getInfoContent_info(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getInfoContent_info", paramMap);
    }

    @Override
    public ValidationRef getShareInfoContent_info(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getShareInfoContent_info", paramMap);
    }


}
