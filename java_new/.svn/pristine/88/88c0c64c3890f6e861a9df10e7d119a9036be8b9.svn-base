package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.yqzj.entities.v1.AllReportData;
import com.zhxg.yqzj.entities.v1.Info;

public interface AllReportDataDao extends BaseDao<AllReportData>{

    /**
     * 获取数据列表 
     * @param params
     * @return
     */
    PageInfo<AllReportData> getAllReports_self(Map<String, Object> params,Pagination pageInfo);
    
    /**
     * 批量修改信息倾向性 
     *
     * @param params
     * @return
     */
    int updateReportOrientations_self(Map<String, Object> params);
    
    /**
     * 批量修改信息分类属性 
     *
     * @param params
     * @return
     */
    int updateReportClassifyIds_self(Map<String, Object> params);
    
    /**
     * 批量删除数据信息
     * @param params
     * @return
     */
    int deleteReportDatas_self(Map<String, Object> params);
    
    /**
     * 添加数据信息
     *
     * @param params
     * @return
     */
    int insertReportData_self(Map<String, Object> params);
    
    /**
     * 获取重复信息ID 
     * @param params
     * @return
     */
    List<String> getRepeatUuid_self(Map<String, Object> params);
    
    /**
     * 根据分类ID查询信息数量 
     * @param params
     * @return
     */
    int getReportByClassify_self(Map<String,Object> params);
    
    /**
     * 获取分类下信息ID
     * @param params
     * @return
     */
    List<String> getReportIdByClassify_self(Map<String,Object> params);
    
    /**
     * 导出查询 
     * @param params
     * @return
     */
    List<Info> getDataForExport_self(Map<String,Object> params);

    /**
     * 图表数据查询
     * @param paramMap
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> getEchartDataList_self(Map<String, Object> paramMap);

    /**
     * 计算数据时间差
     * @param paramMap
     * @return
     */
    Map<String, Object> getEchartDataDistance_self(Map<String, Object> paramMap);
    
    /**
     * 获取数据详情 
     * @param paramMap
     * @return
     */
    AllReportData getInfoDetail_self(Map<String,Object> paramMap);
    
    /**
     * 修改信息是否入报
     * @param paramMap
     * @return
     */
    int setInfoStatus_self(Map<String,Object> paramMap);

}
