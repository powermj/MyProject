package com.zhxg.framework.spring.support;

public abstract class CustomerContextHolder {

    /**
     * 使用ThreadLocal保证线程安全
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setCustomerType(String customerType) {
        contextHolder.set(customerType);
    }

    public static String getCustomerType() {
        return contextHolder.get();
    }

    public static void clearCustomerType() {
        contextHolder.remove();
    }
}
