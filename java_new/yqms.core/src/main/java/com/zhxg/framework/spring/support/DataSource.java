package com.zhxg.framework.spring.support;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 数据源
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.spring.interceptor.DataSource.java
 * <p>
 * Author: azmiu
 * <p>
 * Create Date: 2017年5月26日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public class DataSource {

    // TODO 补充注释
    private String driverClassName = "com.mysql.jdbc.Driver";
    private String url ;
    private String userName;
    private String password;
    private long initialSize = 1;
    private long maxActive = 10;
    private long maxIdle = 1;
    private long minIdle = 1;
    private boolean logAbandoned = true;
    private boolean removeAbandoned = true;
    private long removeAbandonedTimeout = 1000;
    private long maxWait = 1000;
    private boolean testOnBorrow = true;
    private boolean testOnReturn = true;
    private boolean testWhileIdle = true;
    private long minEvictableIdleTimeMillis = 1000;
    private long timeBetweenEvictionRunsMillis = 3600000;

    public String getDriverClassName() {
        return this.driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getInitialSize() {
        return this.initialSize;
    }

    public void setInitialSize(long initialSize) {
        this.initialSize = initialSize;
    }

    public long getMaxActive() {
        return this.maxActive;
    }

    public void setMaxActive(long maxActive) {
        this.maxActive = maxActive;
    }

    public long getMaxIdle() {
        return this.maxIdle;
    }

    public void setMaxIdle(long maxIdle) {
        this.maxIdle = maxIdle;
    }

    public long getMinIdle() {
        return this.minIdle;
    }

    public void setMinIdle(long minIdle) {
        this.minIdle = minIdle;
    }

    public boolean isLogAbandoned() {
        return this.logAbandoned;
    }

    public void setLogAbandoned(boolean logAbandoned) {
        this.logAbandoned = logAbandoned;
    }

    public boolean isRemoveAbandoned() {
        return this.removeAbandoned;
    }

    public void setRemoveAbandoned(boolean removeAbandoned) {
        this.removeAbandoned = removeAbandoned;
    }

    public long getRemoveAbandonedTimeout() {
        return this.removeAbandonedTimeout;
    }

    public void setRemoveAbandonedTimeout(long removeAbandonedTimeout) {
        this.removeAbandonedTimeout = removeAbandonedTimeout;
    }

    public long getMaxWait() {
        return this.maxWait;
    }

    public void setMaxWait(long maxWait) {
        this.maxWait = maxWait;
    }

    public boolean isTestOnBorrow() {
        return this.testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return this.testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public boolean isTestWhileIdle() {
        return this.testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public long getMinEvictableIdleTimeMillis() {
        return this.minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public long getTimeBetweenEvictionRunsMillis() {
        return this.timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }
}
