package com.zhxg.framework.spring.interceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhxg.framework.base.constants.SysConstants;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.utils.RedisUtil;
import com.zhxg.framework.spring.support.CustomerContextHolder;
import com.zhxg.framework.spring.support.DataSource;
import com.zhxg.framework.spring.support.DynamicCreateDataSourceBean;

import net.sf.json.JSONObject;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <妯″潡绫荤紪鍙峰彲浠ュ紩鐢ㄧ郴缁熻璁′腑鐨勭被缂栧彿>
 * <p>
 * Comments: 鏁版嵁婧愭嫤鎴櫒-鏍规嵁dao灞傛帴鍙ｆ柟娉曞悕绉板悗缂�鍒ゆ柇鍒囨崲鏁版嵁婧�
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.spring.interceptor.DataSourceMethodInterceptor.java
 * <p>
 * Author: azmiu
 * <p>
 * Create Date: 2017骞�2鏈�28鏃�
 * <p>
 * Modified By: <淇敼浜轰腑鏂囧悕鎴栨嫾闊崇缉鍐�>
 * <p>
 * Modified Date: <淇敼鏃ユ湡>
 * <p>
 * Why & What is modified: <淇敼鍘熷洜鎻忚堪>
 * <p>
 * Version: v1.0
 */
public class DataSourceMethodInterceptor {

    protected Logger logger = LoggerFactory.getLogger(DataSourceMethodInterceptor.class);

    /**
     * @Fields REFSUFFIX : 闇�瑕佹煡璇釜浜簉ef搴撴柟娉曞悕缁撳熬
     */
    private final static String REF_SUFFIX = "_self";
    /**
     * @Fields INFOSUFFIX : 闇�瑕佹煡璇nfo搴撴柟娉曞悕缁撳熬
     */
    private final static String INFO_SUFFIX = "_info";
    /**
     * @Fields OTHERINFOSUFFIX : 闇�瑕佹煡璇粬浜簉ef搴撴柟娉曞悕缁撳熬
     */
    private final static String OTHE_RREF_SUFFIX = "_other";

    /**
     * @Fields TV_SUFFIX : 闇�瑕佹煡璇㈢爺鍒ゆ暟鎹簱
     */
    private final static String REGION_SUFFIX = "_region";
    
    /**
     * @Fields TV_SUFFIX : 
     */
    private final static String OFFICIAL_SUFFIX = "_official";
    
    private final static String LOG_SUFFIX = "_log";
    
    /**
     * @Fields
     */
    private AtomicInteger RETRY_COUNT = new AtomicInteger(0);

	/**
	 * 鍔ㄦ�佹暟鎹簮
	 *
	 * @param joinPoint
	 * @throws SysException
	 */
	@SuppressWarnings("unchecked")
	public void dynamicSetDataSoruce(JoinPoint joinPoint) throws SysException {
		try {
			String methodName = joinPoint.getSignature().getName();
			if (methodName.contains(REF_SUFFIX) || methodName.contains(INFO_SUFFIX)
					|| methodName.contains(OTHE_RREF_SUFFIX) || methodName.contains(REGION_SUFFIX)
					|| methodName.contains(OFFICIAL_SUFFIX) || methodName.contains(LOG_SUFFIX)) {
				Map<String, Object> userInfo = null;
				BaseEntity baseEntity = null;
				if (joinPoint.getArgs()[0] instanceof Map) {
					userInfo = (Map<String, Object>) joinPoint.getArgs()[0];
				}
				if (joinPoint.getArgs()[0] instanceof BaseEntity) {
					baseEntity = (BaseEntity) joinPoint.getArgs()[0];
				}

				if (methodName.endsWith(INFO_SUFFIX)) {
					CustomerContextHolder.setCustomerType(SysConstants._DB_LOCATIONSOURCE);
				} else if (methodName.endsWith(REF_SUFFIX)) {
					String dbHost =null;
					if(userInfo!=null) {
						dbHost=userInfo.get(SysConstants._DBHOST).toString();
					}
					if(baseEntity!=null) {
						dbHost=baseEntity.get_DBHOST();
					}
					this.checkDataSourceHandler(dbHost);
				} else if (methodName.endsWith(OTHE_RREF_SUFFIX)) {
					String dbHost =null;
					if(userInfo!=null) {
						dbHost=userInfo.get(SysConstants._OTHER_DBHOST).toString();
					}
					if(baseEntity!=null) {
						dbHost=baseEntity.get_OTHER_DBHOST(); 
					}
					this.checkDataSourceHandler(dbHost);
				} else if (methodName.endsWith(REGION_SUFFIX)) {
					CustomerContextHolder.setCustomerType(SysConstants._DB_REGIONSOURCE);
				} else if (methodName.endsWith(OFFICIAL_SUFFIX)) {
					CustomerContextHolder.setCustomerType(SysConstants._DB_OFFICIALWEBSITESOURCE);
				} else if (methodName.endsWith(LOG_SUFFIX)) {
					CustomerContextHolder.setCustomerType(SysConstants._DB_LOGSOURCE);
				}
			} else {
				CustomerContextHolder.setCustomerType(SysConstants._DB_USERCONFSOURCE);
			}
		} catch (Exception e) {
			this.logger.error("change the datasource error锛寋}" + e);
			throw new SysException();
		}
	}

    /**
     * 鏍规嵁鐢ㄦ埛涓汉搴撴槧灏勶紝鍔ㄦ�佸垏鎹㈡暟鎹簮
     *
     * @param dbHost
     * @throws SysException
     */
    public void checkDataSourceHandler(String dbHost) throws Exception {
        if (null != DynamicCreateDataSourceBean.datasources && !DynamicCreateDataSourceBean.datasources.isEmpty()) {
            if (DynamicCreateDataSourceBean.datasources.containsKey(dbHost)) {
                CustomerContextHolder.setCustomerType(dbHost);
            } else {
                this.RETRY_COUNT.getAndIncrement();
                this.reLoadingDatasource(dbHost);
            }
        } else {
            this.RETRY_COUNT.getAndIncrement();
            this.reLoadingDatasource(dbHost);
        }
    }

	/**
	 * 閲嶆柊鍔犺浇鏁版嵁婧愰厤缃�
	 * 
	 * @throws Exception
	 */
	public void reLoadingDatasource(String dbHost) throws Exception {
		if (this.RETRY_COUNT.get() > 2) {
			this.logger.error("reloading datasource over " + this.RETRY_COUNT + "锛宲lease check the datasource config");
			this.RETRY_COUNT.set(0);
			throw new SysException("reloading datasource timeout锛宲lease check the datasource config");
		} else {
			Map<Object, Object> cacheDatasources = RedisUtil.getHash("datasource");
			DataSource dataSource = null;
			DynamicCreateDataSourceBean dd = new DynamicCreateDataSourceBean();
			for (Entry<Object, Object> entry : cacheDatasources.entrySet()) {
				dataSource = (DataSource) JSONObject.toBean(JSONObject.fromObject(entry.getValue()), DataSource.class);
				DynamicCreateDataSourceBean.datasources.put(entry.getKey().toString(), dataSource);
			}
			dd.addDataSourceBeanToApp();
			this.checkDataSourceHandler(dbHost);
		}
	}

}
