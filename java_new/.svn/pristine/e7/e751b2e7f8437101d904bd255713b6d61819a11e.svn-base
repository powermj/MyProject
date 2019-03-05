package com.zhxg.fgw.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

/**
 * 本地化项目启动配置
 */
@Configuration
public class ContextFgw {

    /**
     * 配置jdbcTemplate指向(用户信息)配置库
     * @param dataSource - "dbUserConfSource"在"yqms.web.api"下的application-context-web-api.xml里定义
     * @return -
     */
    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("dbUserConfSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource, true);
    }

    /**
     * 配置NamedParameterJdbcTemplate指向(用户信息)配置库
     * @param dataSource - "dbUserConfSource"在"yqms.web.api"下的application-context-web-api.xml里定义
     * @return -
     */
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Qualifier("dbUserConfSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

}
