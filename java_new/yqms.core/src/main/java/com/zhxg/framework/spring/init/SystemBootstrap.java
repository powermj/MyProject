package com.zhxg.framework.spring.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhxg.framework.base.constants.SysConstants;
import com.zhxg.framework.base.utils.PropertiesUtil;
/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: web应用初始化入口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.spring.init.SystemBootstrap.java
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
public class SystemBootstrap {

    private final Logger logger = LoggerFactory.getLogger(SystemBootstrap.class);

    /**
     * 系统初始化入口
     */
    public void systemInit() {
        try{
            this.initProperties();
        } catch (Exception ex) {
            this.logger.error("loading properties error，{}" + ex);
        }
    }

    /**
     * properties配置文件初始化
     */
    public void initProperties() {
        this.logger.info("******start loading properties******");
        // 加载应用配置
        ApplicationProperties.init(PropertiesUtil.loadProperties(SysConstants._APPLICATION_SYS_FILE_PATH));
        this.logger.info("******loading properties success******");
    }
}
