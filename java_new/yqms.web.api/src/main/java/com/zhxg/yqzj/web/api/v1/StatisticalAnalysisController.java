package com.zhxg.yqzj.web.api.v1;


import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.service.v1.StatisticalAnalysisService;

public class StatisticalAnalysisController extends BaseController<BaseEntity>{
    
    private StatisticalAnalysisService statisticalAnalysisService;

    @Override
    protected BaseService<BaseEntity> getBaseService() {
        return statisticalAnalysisService;
    }
    

}
