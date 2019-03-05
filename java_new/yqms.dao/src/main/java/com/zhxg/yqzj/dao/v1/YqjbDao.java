package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Description: 舆情简报dao接口
 * </p>
 * 
 * @author zyl
 * @date 2017年11月8日
 * @version 1.0
 */
public interface YqjbDao {

    /**
     * 舆情简报列表
     * 
     * @param params
     * @return
     */
    List<Map<String, Object>> selectList_self(Map<String, Object> params);

    int insertYqjbSelective_self(Map<String, Object> params);

    int insertYqjbCntSelective_self(Map<String, Object> params);

    /**
     * 删除简报
     *
     * @param params
     * @return
     */
    int deleteYqjb_self(Map<String, Object> params);

    /**
     * 删除简报内容
     *
     * @param params
     * @return
     */
    int deleteYqjbCnt_self(Map<String, Object> params);

    /**
     * 删除简报
     *
     * @param params
     * @return
     */
    int deletejb_self(Map<String, Object> params);

    /**
     * 删除简报内容
     *
     * @param params
     * @return
     */
    int deletejbCnt_self(Map<String, Object> params);

    /**
     * 查询简报是否添加过
     * 
     * @param params
     * @return
     */
    int getCountByUrl_self(Map<String, Object> params);

}
