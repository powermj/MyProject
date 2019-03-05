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
public interface YqgzDao {

    /**
     * 舆情简报列表
     * 
     * @param params
     * @return
     */
    List<Map<String, Object>> selectList_self(Map<String, Object> params);

    int insertYqgzSelective_self(Map<String, Object> params);

    int insertYqgzCntSelective_self(Map<String, Object> params);

    /**
     * 删除关注信息
     *
     * @param params
     * @return
     */
    int deletegz_self(Map<String, Object> params);

    /**
     * 删除关注内容
     *
     * @param params
     * @return
     */
    int deletegzCnt_self(Map<String, Object> params);

    /**
     * 查询关注是否添加过
     * 
     * @param params
     * @return
     */
    int getCountByUrl_self(Map<String, Object> params);
}
