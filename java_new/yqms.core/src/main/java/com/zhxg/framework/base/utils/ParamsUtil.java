package com.zhxg.framework.base.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.lang.StringUtils;

import com.zhxg.framework.base.constants.SysConstants;

/**
 * <p>
 * Description:
 * </p>
 * 
 * @author zyl
 * @date 2017年11月8日
 * @version 1.0
 */
public class ParamsUtil {
    /**
     * 将request中的参数放入map中
     * @param parameterMap
     * @return
     */
    public static Map<String,Object> transToMAP(Map<String,Object> parameterMap){
        // 返回值Map
        Map<String,Object> returnMap = new HashMap<>();
        Iterator<Map.Entry<String, Object>> entries = parameterMap.entrySet().iterator();
        Map.Entry<String, Object> entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry<String, Object>) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return  returnMap;
    }
    
    public static void transOtherToSelf(Map<String,Object> paramMap){
        if(!StringUtils.isBlank((String)paramMap.get("shareUserId"))){
            paramMap.put(SysConstants._KUID, (String)paramMap.get(SysConstants._OTHER_KUID));
            paramMap.put(SysConstants._DBHOST, (String)paramMap.get(SysConstants._OTHER_DBHOST));
            paramMap.put(SysConstants._DBNAME, (String)paramMap.get(SysConstants._OTHER_DBNAME));
        }
    }
    
    /**
     * 将request中的参数放入map中
     * @param <T>
     * @param parameterMap
     * @return
     */
    public static <T> Map<String,Object> transToMAP(Object object,Class<T> clazz){
        // 返回值Map
        Map<String,Object> returnMap = new HashMap<>();
        List<Field> fieldList = new ArrayList<>() ;
        Class<?> cc = clazz;
        while (cc != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
            if(cc.getName().equals("com.zhxg.framework.base.curd.impl.BaseEntity")){
                break;
            }
            fieldList.addAll(Arrays.asList(cc.getDeclaredFields()));
            cc = (Class<?>) cc.getSuperclass(); //得到父类,然后赋给自己
        }

		for (Field filed : fieldList) {
			filed.setAccessible(true);
			String key = filed.getName();
			Object value = "";
			try {
				value = filed.get(object) == null ? null : filed.get(object);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			returnMap.put(key, value);
		}
		return returnMap;
	}

	public static String nullDeal(Map<String, Object> params, String key, String defaultValue) {
		if (params == null || params.get(key) == null || "".equals(params.get(key))) {
			return StringUtils.isNotBlank(defaultValue) ? defaultValue : "";
		}
		return String.valueOf(params.get(key));
	}

	public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass) {
		try {
			if (map == null) {
				return null;
			}
			T obj = beanClass.newInstance();
			BeanUtilsBean.getInstance().getConvertUtils().register(new SqlDateConverter(null), Date.class);
			BeanUtils.populate(obj, map);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
