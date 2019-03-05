package com.zhxg.yqzj.service.v1;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.CustomHomePage;
 

public interface CustomHomePageService extends BaseService<CustomHomePage> {

    JSONArray getCustomHomePage(Map<String, Object> paramMap) throws Exception;

    int restoreDefaultHeaderData(Map<String, Object> paramap);

    int restoreDefaultTailData(Map<String, Object> paramap);

    JSONArray getCustomTailData(Map<String, Object> paramMap);

    int updateTailData(Map<String, Object> paramap);

	List<Map<String, Object>> getSourceTypeCount(Map<String, Object> paramMap) throws SysException;

	List<Map<String, Object>> getSubjectByUserId(Map<String, Object> paramMap) throws SysException;

    int updateHeaderData(Map<String, Object> paramap, Map<String, Object> map);

    PageInfo<Map<String, Object>> getHeadLinesList(Pagination pagination, String type) throws SysException;

    Map<String, Object> getCustomHomePageData(Map<String, Object> paramMap);

    int updateHomePageData(Map<String, Object> paramap);

    Map<String, Object> getSubjectTree(Map<String, Object> paramMap);

    int restoreHomePageData(Map<String, Object> paramap);
}
