package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.SolrExportEmailDao;
import com.zhxg.yqzj.entities.v1.UserMailExport;

@Repository
public class SolrExportEmailDaoImpl extends BaseDaoImpl<BaseEntity> implements SolrExportEmailDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.UserMailExport.";

    /**
     * 获取导出邮箱列表
     */
    @Override
    public List<UserMailExport> getExportEmail(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getExportEmail", params);
    }

    @Override
    public String getUserEmail(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getUserEmail", params);
    }

    @Override
    public int setExportEmail(Map<String, Object> params) {
        return this.sqlSessionTemplate.insert(NAME_SPACE + "setExportEmail", params);
    }

    @Override
    public int getCountByEmail(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getCountByEmail", params);
    }

    @Override
    public List<UserMailExport> getUserDefaultEmail(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getUserDefaultEmail", params);
    }

}
