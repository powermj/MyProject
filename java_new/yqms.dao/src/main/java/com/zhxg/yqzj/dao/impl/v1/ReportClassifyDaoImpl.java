package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.utils.MapResultHandler;
import com.zhxg.yqzj.dao.v1.ReportClassifyDao;
import com.zhxg.yqzj.entities.v1.ReportClassify;

@Repository
public class ReportClassifyDaoImpl extends BaseDaoImpl<BaseEntity> implements ReportClassifyDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.ReportClassify.";

    @Override
    public int insertReportClassify(Map<String, Object> params) {
        return this.sqlSessionTemplate.insert(NAME_SPACE+"insertReportClassify", params);
    }

    @Override
    public int updateReportClassifyName(Map<String, Object> params) {
        return this.sqlSessionTemplate.update(NAME_SPACE+"updateReportClassifyName", params);
    }

    @Override
    public int deleteReportClassifyById(Map<String, Object> params) {
        return this.sqlSessionTemplate.delete(NAME_SPACE+"deleteReportClassifyById", params);
    }

    @Override
    public List<ReportClassify> getReportClassifys(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getReportClassifys", params);
    }

    @Override
    public int getClassifyNum(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getClassifyNum",params);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<Integer, String> getReportClassifyName(Map<String, Object> params) {
        MapResultHandler resultHandler = new MapResultHandler();
        this.sqlSessionTemplate.select(NAME_SPACE+"getReportClassifyName", params,resultHandler);
        return resultHandler.getMappedResults();
    }



}
