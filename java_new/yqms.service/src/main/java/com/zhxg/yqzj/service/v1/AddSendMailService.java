package com.zhxg.yqzj.service.v1;

import java.util.Map;

import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.service.exception.myfocus.MyFocusInfoNotFoundException;

/**
 * <p>Description: 我的关注接口</p>
 * @author xdf
 * @date 2017年11月8日
 * @version 1.0
 */
public interface AddSendMailService extends BaseService<BaseEntity> {
    /**
     * 添加上报
     * @param params
     * @return
     */
    int addSendEmail(Map<String,Object> params) throws SysException,MyFocusInfoNotFoundException;

}
