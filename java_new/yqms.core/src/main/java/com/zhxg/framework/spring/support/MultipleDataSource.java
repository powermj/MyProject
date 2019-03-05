package com.zhxg.framework.spring.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class MultipleDataSource extends AbstractRoutingDataSource {

    protected Logger logger = LoggerFactory.getLogger(MultipleDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        this.logger.debug("get datasource:::" + CustomerContextHolder.getCustomerType());
        return CustomerContextHolder.getCustomerType();
    }
}
