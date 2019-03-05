package com.zhxg.framework.base.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;


public class MapResultHandler implements ResultHandler {
    @SuppressWarnings("rawtypes")
    private final Map mappedResults = new HashMap();

    @SuppressWarnings("unchecked")
    @Override
    public void handleResult(ResultContext context) {
        @SuppressWarnings("rawtypes")
        Map map = (Map)context.getResultObject();
        mappedResults.put(map.get("key"), map.get("value"));
    }
    
    public Map getMappedResults() {
        return mappedResults;
    }

}
