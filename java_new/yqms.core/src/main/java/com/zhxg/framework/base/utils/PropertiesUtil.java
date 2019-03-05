package com.zhxg.framework.base.utils;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.zhxg.framework.base.exception.SysException;

public class PropertiesUtil {

    private final static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private PropertiesUtil() {
    }

    /**
     * 获取指定文件的指定属性
     * 
     * @param absUri
     * @param propertieKey
     * @return
     * @throws Exception
     */
    public static String getProperties(String absUri, String propertieKey) throws SysException {
        Resource resource = null;
        Properties properties = null;
        try {
            resource = new ClassPathResource(absUri);
            properties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            logger.error(PropertiesUtil.class.getName() + ".getProperties:::" + e);
            throw new SysException(absUri+" not found!");
        }
        return properties.getProperty(propertieKey);
    }
    
    public static String getValue(String propertieKey){
        Resource resource = null;
        Properties properties = null;
        try {
            resource = new ClassPathResource("application.properties");
            properties = PropertiesLoaderUtils.loadProperties(resource);
            return properties.getProperty(propertieKey);
        } catch (IOException e) {
            logger.error(PropertiesUtil.class.getName() + ".getProperties:::" + e);
        }
        return null;
    }

    /**
     * 加载指定的properties配置文件
     * 
     * @param URI
     * @return
     * @throws Exception
     */
    public static Properties loadProperties(String URI) {
        Resource resource = null;
        Properties properties = null;
        try {
            resource = new ClassPathResource(URI);
            properties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            logger.error(PropertiesUtil.class.getName() + ".getProperties:::配置文件加载异常，{}" + e);
        }
        return properties;
    }
    public static void main(String[] args) {
//        System.out.println(getValue("ALPHA_LOGIC_UPDATE_KEY"));
    }
}
