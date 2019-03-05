package com.zhxg.yqzj.service.v1;

import java.util.Map;

import javax.servlet.ServletContext;

import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.BaseService;

public interface ShareInfoService extends BaseService<BaseEntity> {

    Map<String, Object> shareInfo(Map<String, Object> paramMap, ServletContext servletContext) throws SysException;


}
