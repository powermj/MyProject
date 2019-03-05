package com.zhxg.framework.base.utils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: Redis的工具类
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.utils.RedisUtil.java
 * <p>
 * Author: azmiu
 * <p>
 * Create Date: 2017年2月28日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
// TODO 需优化，采用redis pool 支持多redis-server
public class RedisPoolUtil {
    private static StringRedisTemplate stringRedisTemplate1;
    private static Logger logger = LoggerFactory.getLogger(RedisPoolUtil.class);
    
    private static Map<String,StringRedisTemplate> redisMap = new HashMap<>();

    /**
     * 根据指定key，获取指定字符串常量
     * 
     * @param key
     *            取值的key
     * @return key对应的值
     */
    public static String getStr(String key,String redisType) throws Exception {
        try {
            
            return redisMap.get(redisType).opsForValue().get(key);
        } catch (Exception e) {
            logger.error(RedisPoolUtil.class.getName() + ".getStr" + e,e);
            throw e;
        }
    }

    public static String getStr(int db,String key,String redisType) throws Exception {
        try {
            return redisMap.get(redisType).execute(new RedisCallback<String>() {  
                @Override
                public String doInRedis(RedisConnection redis) throws DataAccessException {
                    redis.select(db);
                    String value = "";
                    try {
                        if(redis.get(key.getBytes())!=null){
                            value =  new String(redis.get(key.getBytes()),"UTF-8");
                        }
                    } catch (UnsupportedEncodingException e) {
                        logger.error(e.getMessage(),e);
                    }catch(Exception e){
                        logger.error(e.getMessage(),e);
                    }
                    return value;    
                }  
            });
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(RedisPoolUtil.class.getName() + ".getStr" + e,e);
            throw e;
        }
    }

    /**
     * 对指定key，赋值value
     * 
     * @param key
     *            赋值的key
     * @param value
     *            赋值的value
     */
    public static void setStr(String key, String value,String redisType) {
        delete(key,redisType);
        redisMap.get(redisType).opsForValue().set(key, value);
    }

    public static void setStr(int db, String key, String value,String redisType) {
        redisMap.get(redisType).execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redis) throws DataAccessException {
                redis.select(db);
                redis.set(key.getBytes(), value.getBytes());
                return "success";
            }
        });
    }

    public static void setExpire(int db, String key, long timeout,String redisType) {
        redisMap.get(redisType).execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redis) throws DataAccessException {
                redis.select(db);
                return redis.expire(key.getBytes(), timeout);
            }
        });

    }

    /**
     * 设置key超时时间
     *
     * @param key
     *            赋值的key
     * @param timeout
     *            时间
     * @param unit
     *            延时操作单位
     */
    public static void setExpire(String key, long timeout, TimeUnit unit,String redisType) {
        redisMap.get(redisType).expire(key, timeout, unit);

    }

    public static Long getExpire(String key, TimeUnit unit,String redisType) {
        return redisMap.get(redisType).getExpire(key, unit);

    }

    public static Long getExpire(String key,String redisType) {
        return redisMap.get(redisType).getExpire(key);

    }

    /**
     * 删除指定key，在缓存中的对应值
     * 
     * @param key
     *            删除的key
     */
    public static void delete(String key,String redisType) {
        redisMap.get(redisType).delete(key);
    }
    
    public static Long delete(int db,String key,String redisType) {
        return redisMap.get(redisType).execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redis) throws DataAccessException {
                redis.select(db);
                return redis.del(key.getBytes());
            }
        });
    }

    /**
     * 将指定key赋值为指定的List
     * 
     * @param key
     *            要赋值的key
     * @param list
     *            要赋值的列表
     */
    public static void setList(String key, List<String> list,String redisType) {
        delete(key,redisType);
        String[] arr = new String[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        redisMap.get(redisType).opsForList().rightPushAll(key, arr);
    }

    /**
     * 获取指定key对应的List
     * 
     * @param key
     *            指定的key
     * @return key对应的列表
     */
    public static List<String> getList(String key,String redisType) {
        return redisMap.get(redisType).opsForList().range(key, 0, -1);
    }

    /**
     * 获取指定key对应的Hash
     * 
     * @param key
     *            指定的key
     * @return key对应的Hash
     */
    public static Map<Object, Object> getHash(String key,String redisType) {
        return redisMap.get(redisType).opsForHash().entries(key);
    }

    public static Map<String, String> getAllHash(int db, String hashKey,String redisType) {
        Map<byte[], byte[]> map = redisMap.get(redisType).execute(new RedisCallback<Map<byte[], byte[]>>() {
            @Override
            public Map<byte[], byte[]> doInRedis(RedisConnection redis) throws DataAccessException {
                redis.select(db);
                Map<byte[], byte[]> map = redis.hGetAll(hashKey.getBytes());
                return map;
            }
        });
        Map<String, String> result = new HashMap<>();
        for (byte[] key : map.keySet()) {
            try {
                result.put(new String(key, "UTF-8"), new String(map.get(key), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static StringRedisTemplate getStringRedisTemplate1() {
        return stringRedisTemplate1;
    }

    public static void setStringRedisTemplate1(StringRedisTemplate stringRedisTemplate) {
        RedisPoolUtil.stringRedisTemplate1 = stringRedisTemplate;
        redisMap.put("dataMap", stringRedisTemplate);
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        RedisPoolUtil.logger = logger;
    }
    
    
    public static Set<String> keys(int db, String pattern,String redisType) {
        Set<byte[]> set = redisMap.get(redisType).execute(new RedisCallback<Set<byte[]>>() {
            @Override
            public Set<byte[]> doInRedis(RedisConnection redis) throws DataAccessException {
                redis.select(db);
                Set<byte[]> map = redis.keys(pattern.getBytes());
                return map;
            }
        });
        Set<String> result = new HashSet<>();
        for (byte[] key : set) {
            try {
                result.add(new String(key, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
