package com.zhxg.framework.spring.support;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;

import com.zhxg.framework.base.utils.RedisUtil;
import com.zhxg.framework.spring.init.ApplicationProperties;

import net.sf.json.JSONObject;

@SuppressWarnings("rawtypes")
public class DynamicCreateDataSourceBean implements ApplicationContextAware, ApplicationListener {

    private static final Logger logger = LoggerFactory.getLogger(DynamicCreateDataSourceBean.class);

    private static ConfigurableApplicationContext app;

    public static HashMap<String, DataSource> datasources = new HashMap<String, DataSource>();

    @Autowired
    public MultipleDataSource multipleDataSource;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        // 在容器初始化完成后，再初始化datasource
        if (event instanceof ContextRefreshedEvent) {
            this.init();
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        app = (ConfigurableApplicationContext) arg0;
    }

    /**
     * 初始化方法
     *
     */
    public void init() {
        logger.info("******start loading datasources******");
        try{
            this.initDatasources();
        } catch (Exception ex) {
            logger.error("init datasource error，{}" + ex);
            return;
        }
        logger.info("******loading datasources success******");
    }

    /**
     * 初始化datasources
     *
     * @throws Exception
     */
    public void initDatasources() throws Exception {
        DataSource dataSource = null;
        // get datasources from redis
        Map<String, String> cacheDatasources = RedisUtil.getAllHash(Integer.parseInt(ApplicationProperties.getProperty("datasource.db")),"datasource");
        for (Entry<String, String> entry : cacheDatasources.entrySet()) {
            dataSource = (DataSource) JSONObject.toBean(JSONObject.fromObject(entry.getValue()), DataSource.class);
            datasources.put(entry.getKey().toString(), dataSource);
        }
        this.addDataSourceBeanToApp();
    }
    
    /**
     * 创建bean并加载到spring applicationContext上下文中
     */
    public void addDataSourceBeanToApp() throws Exception {
        try {
            DefaultListableBeanFactory acf = (DefaultListableBeanFactory) app
                    .getAutowireCapableBeanFactory();

            String DATASOURCE_BEAN_CLASS = "org.apache.commons.dbcp.BasicDataSource";
            BeanDefinitionBuilder bdb;
            // this.app.getBean("dbUserConfSource");
            Iterator<Entry<String, DataSource>> iter = datasources.entrySet().iterator();
            Map<Object, Object> targetDataSources = new LinkedHashMap<Object, Object>();
            // 动态创建数据源bean 并将bean注册到applicationContext中去
            while (iter.hasNext()) {
                Entry<String, DataSource> entry = iter.next();
                DataSource ds = entry.getValue();
                // 创建bean
                bdb = BeanDefinitionBuilder.rootBeanDefinition(DATASOURCE_BEAN_CLASS);
                bdb.getBeanDefinition().setAttribute("id", entry.getKey());
                bdb.setDestroyMethodName("close");
                bdb.addPropertyValue("driverClassName", ds.getDriverClassName());
                bdb.addPropertyValue("url", ds.getUrl());
                bdb.addPropertyValue("username", ds.getUserName());
                bdb.addPropertyValue("password", ds.getPassword());
                bdb.addPropertyValue("initialSize", ds.getInitialSize());
                bdb.addPropertyValue("maxActive", ds.getMaxActive());
                bdb.addPropertyValue("maxIdle", ds.getMaxIdle());
                bdb.addPropertyValue("minIdle", ds.getMinIdle());
                bdb.addPropertyValue("logAbandoned", ds.isLogAbandoned());
                bdb.addPropertyValue("removeAbandoned", ds.isRemoveAbandoned());
                bdb.addPropertyValue("removeAbandonedTimeout", ds.getRemoveAbandonedTimeout());
                bdb.addPropertyValue("maxWait", ds.getMaxWait());
                bdb.addPropertyValue("testOnBorrow", ds.isTestOnBorrow());
                bdb.addPropertyValue("testOnReturn", ds.isTestOnReturn());
                bdb.addPropertyValue("testWhileIdle", ds.isTestWhileIdle());
                bdb.addPropertyValue("minEvictableIdleTimeMillis", ds.getMinEvictableIdleTimeMillis());
                bdb.addPropertyValue("timeBetweenEvictionRunsMillis", ds.getTimeBetweenEvictionRunsMillis());
                // 注册bean
                acf.registerBeanDefinition(entry.getKey(), bdb.getBeanDefinition());
                targetDataSources.put(entry.getKey(), app.getBean(entry.getKey()));
            }
            if (null == this.multipleDataSource) {
                this.multipleDataSource = (MultipleDataSource) app.getBean("multipleDataSource");
            }
            targetDataSources.put("dbLocationSource", app.getBean("dbLocationSource"));
            targetDataSources.put("dbRegionSource", app.getBean("dbRegionSource"));
            targetDataSources.put("dbOfficialwebsiteSource", app.getBean("dbOfficialwebsiteSource"));
            targetDataSources.put("logSource", app.getBean("logSource"));
            this.multipleDataSource.setTargetDataSources(targetDataSources);
            this.multipleDataSource.afterPropertiesSet();
        } catch (Exception ex) {
            logger.error("create Dynamic DatasourceBean error，{} " + ex);
            throw ex;
        }
    }
}

