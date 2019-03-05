package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.yqzj.entities.v1.CustomHomePage;


public interface CustomHomePageDao extends BaseDao<CustomHomePage> {

    List<Map<String, Object>> getUserCustomHomePageData_self(Map<String, Object> paramMap);

    List<Map<String, Object>> getUserNavData(Map<String, Object> paramMap);

    List<Map<String, Object>> getUserBarNewData(Map<String, Object> paramMap);

    Map<String, Object> getUserInfo(Map<String, Object> paramMap);

    List<Map<String, Object>> userBaseInfo(Map<String, Object> paramMap);

    int restoreDefaultData_self(Map<String, Object> paraMap);

    int insertCustomData_self(Map<String, Object> paramap);

	List<Map<String, Object>> getSubjectListByUserId(int userId);

	List<Map<String, Object>> getSourceTypeCount_self(Map<String, Object> map);

    PageInfo<Map<String, Object>> getHeadLinesList(Pagination pagination,String name);

    int updateCustomData_self(Map<String, Object> paramap);

    List<Map<String, Object>> getSubjectTree(Map<String, Object> paramMap);

    void delUserNav(Map<String, Object> paraMap);

    void updateUserClassfy(Map<String, Object> paraMap);

    void updateSubrelation(Map<String, Object> paraMap);

}
