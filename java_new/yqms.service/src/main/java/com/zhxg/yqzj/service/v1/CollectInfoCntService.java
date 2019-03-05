package com.zhxg.yqzj.service.v1;

import java.util.List;
import java.util.Map;

import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.CollectInfoCnt;
import com.zhxg.yqzj.service.exception.myfocus.RepeatOperateException;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.service
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: app导入附件信息接口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.service.v1.UserService.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年4月25日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public interface CollectInfoCntService extends BaseService<CollectInfoCnt> {

    /**
     * 获取收藏夹及内词
     *
     * @param params
     * @return
     */
    List<CollectInfoCnt> selectList(Map<String, Object> params);

    /**
     * 添加收藏夹内词
     *
     * @param params
     * @return
     */
    int insertCollectInfoCnt(Map<String, Object> params) throws RepeatOperateException, SysException;

    /**
     * 修改收藏夹内词
     *
     * @param params
     * @return
     */
    int updateCollectInfoCnt(Map<String, Object> params) throws SysException, RepeatOperateException;

    /**
     * 删除收藏夹内词
     *
     * @param params
     * @return
     */
    int deleteCollectInfoCnt(Map<String, Object> params) throws SysException;
}
