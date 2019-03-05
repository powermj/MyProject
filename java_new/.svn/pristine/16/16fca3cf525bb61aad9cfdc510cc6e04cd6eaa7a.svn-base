package com.zhxg.framework.spring.init;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 存储系统配置参数的类
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.spring.init.ApplicationProperties.java
 * <p>
 * Author: azmiu
 * <p>
 * Create Date: 2017年3月2日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public class ApplicationProperties {

    private final static Logger logger = LoggerFactory.getLogger(ApplicationProperties.class);

    private static Properties props = null;

    public static void init(Properties properties) {
        props = properties;
    }

    /**
     * 获取应用配置参数
     * 
     * @param key
     *            参数对应的key
     * @return
     *         获取的参数值
     * @throws Throwable
     */
    public static String getProperty(String key) {
        if (null == props) {
            logger.error(ApplicationProperties.class.getName() + ":::properties load failed");
            return "";
        }
        return props.getProperty(key);
    }
}
