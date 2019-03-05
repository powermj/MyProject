package com.zhxg.yqzj.service.v1;

import java.util.Map;

import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.service.exception.myfocus.MyFocusInfoNotFoundException;
import com.zhxg.yqzj.service.exception.myfocus.RepeatOperateException;
import com.zhxg.yqzj.service.exception.myfocus.SendEmailErrorException;

/**
 * <p>Description: 我的关注接口</p>
 * @author zyl
 * @date 2017年11月8日
 * @version 1.0
 */
public interface MyFocusService extends BaseService<BaseEntity> {
    /**
     * 加入简报
     * @param params
     * @return
     */
    int addYqjb(Map<String,Object> params) throws SysException,MyFocusInfoNotFoundException, RepeatOperateException;
    
    int addSendEmail(Map<String,Object> params) throws SysException,MyFocusInfoNotFoundException,SendEmailErrorException;

    /**
     * 加入话题
     * @param params
     * @return
     */
    Map<String, Object> saveTopic(Map<String, Object> params) throws SysException;
}
