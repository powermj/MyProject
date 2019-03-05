package com.zhxg.framework.base.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * <p>
 * CopyRright (c)2012-2016: 高宇
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: redis 操作数据库配置类
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.utils.JedisCacheClient.java
 * <p>
 * Author: 高宇
 * <p>
 * Create Date: 2017年6月15日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public class JedisCacheClient {

    private static Logger logger = LoggerFactory.getLogger(JedisCacheClient.class);
    private static JedisPool jedisPool;
    private static JedisCacheClient jedisCacheClient;

    static {
        logger.debug("init JedisCacheClient......");
        @SuppressWarnings("resource")
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("/conf/application-redis.xml");
        synchronized (RedisUtil.class) {
            jedisPool = (JedisPool) ctx.getBean("jedisPool");
        }
        logger.debug("init redisUtil success");
    }

    public static JedisCacheClient getInstance() {
        if (null == jedisCacheClient) {
            jedisCacheClient = new JedisCacheClient();
        }
        return jedisCacheClient;
    }

    /**
     * setVExpire(设置key值，同时设置失效时间 秒)
     * @Title: setVExpire
     * @param @param key
     * @param @param value
     * @param @param seconds
     * @param index 具体数据库
     * @return void
     * @throws
     */

    public void setExpire(String key, String value, int seconds, int index) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(index);
            jedis.set(key, value);
            jedis.expire(key, seconds);

        } catch (Exception e) {
            logger.error("setV初始化jedis异常：" + e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
        } finally {
            closeJedis(jedis);
        }

    }

    public Long getExpire(String key, int index) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            jedis.select(index);
            
            return jedis.ttl(key);
        } catch (Exception e) {
            logger.error("setV初始化jedis异常：" + e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
        } finally {
            closeJedis(jedis);
        }
        return null;

    }

    public void deleteKey(String key, int index) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(index);
            jedis.del(key);
        } catch (Exception e) {
            logger.error("setV初始化jedis异常：" + e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
        } finally {
            closeJedis(jedis);
        }
    }

    /**
     * (存入redis数据)
     * @Title: setV
     * @param @param key
     * @param @param value
     * @param index 具体数据库
     * @return void
     * @throws
     */
    public void setStr(String key, String value, int index) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(index);
            jedis.set(key, value);
        } catch (Exception e) {
            logger.error("setV初始化jedis异常：" + e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
        } finally {
            closeJedis(jedis);
        }

    }

    /**
     * getV(获取redis数据信息)
     * @Title: getV
     * @param @param key
     * @param index 具体数据库 0:常用数据存储 3：session数据存储
     * @param @return
     * @return V
     * @throws
     */
    @SuppressWarnings("unchecked")
    public <V> V getV(String key, int index) {
        String value = "";
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(index);
            value = jedis.get(key);
        } catch (Exception e) {
            logger.error("getV初始化jedis异常：" + e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
        } finally {
            closeJedis(jedis);
        }
        return (V) JSONObject.toBean(JSONObject.fromObject(value));
    }

    /**
     * getVString(返回json字符串)
     * @Title: getVString
     * @param @param key
     * @param @param index
     * @param @return
     * @return String
     * @throws
     */
    public String getVStr(String key, int index) {
        String value = "";
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(index);
            value = jedis.get(key);
        } catch (Exception e) {
            logger.error("getVString初始化jedis异常：" + e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
        } finally {
            closeJedis(jedis);
        }
        return value;
    }

    /**
     * Push(存入 数据到队列中)
     * @Title: Push
     * @param @param key
     * @param @param value
     * @return void
     * @throws
     */
    public <V> void Push(String key, V value) {
        String json = JSONObject.fromObject(value).toString();
        Jedis jedis = null;
        try {
            logger.info("存入 数据到队列中");
            jedis = jedisPool.getResource();
            jedis.select(15);
            jedis.lpush(key, json);
        } catch (Exception e) {
            logger.error("Push初始化jedis异常：", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
        } finally {
            closeJedis(jedis);
        }
    }

    /**
     * Push(存入 数据到队列中)
     * @Title: PushV
     * @param key
     * @param value
     * @param dBNum
     * @return void
     * @throws
     */
    public void PushV(String key, String value, int dBNum) {
        Jedis jedis = null;
        try {
            logger.info("存入 数据到队列中");
            jedis = jedisPool.getResource();
            jedis.select(dBNum);
            jedis.lpush(key, value);
        } catch (Exception e) {
            logger.error("Push初始化jedis异常：", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
        } finally {
            closeJedis(jedis);
        }
    }

    /**
     * Push(存入 数据到队列中)
     * @Title: Push
     * @param @param key
     * @param @param value
     * @return void
     * @throws
     */
    public void PushEmail(String key, String value) {
        Jedis jedis = null;
        try {
            logger.info("存入 数据到队列中");
            jedis = jedisPool.getResource();
            jedis.select(15);
            jedis.lpush(key, value);
        } catch (Exception e) {
            logger.error("Push初始化jedis异常：", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
        } finally {
            closeJedis(jedis);
        }
    }

    /**
     * Pop(从队列中取值)
     * @Title: Pop
     * @param @param key
     * @param @return
     * @return V
     * @throws
     */
    @SuppressWarnings("unchecked")
    public <V> V Pop(String key) {
        String value = "";
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(15);
            value = jedis.rpop(key);
        } catch (Exception e) {
            logger.error("Pop初始化jedis异常：", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
        } finally {
            closeJedis(jedis);
        }
        return (V) value;
    }

    /**
     * expireKey(限时存入redis服务器)
     * @Title: expireKey
     * @param @param key
     * @param @param seconds
     * @return void
     * @throws
     */
    public void expireKey(String key, int seconds) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(3);
            jedis.expire(key, seconds);
        } catch (Exception e) {
            logger.error("Pop初始化jedis异常：", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
        } finally {
            closeJedis(jedis);
        }

    }

    /**
     * closeJedis(释放redis资源)
     * @Title: closeJedis
     * @param @param jedis
     * @return void
     * @throws
     */
    public void closeJedis(Jedis jedis) {
        try {
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
        } catch (Exception e) {
            logger.error("释放资源异常：" , e);
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        JedisCacheClient.logger = logger;
    }

    public static JedisPool getJedisPool() {
        return jedisPool;
    }

    public static void setJedisPool(JedisPool jedisPool) {
        JedisCacheClient.jedisPool = jedisPool;
    }

    public static JedisCacheClient getJedisCacheClient() {
        return jedisCacheClient;
    }

    public static void setJedisCacheClient(JedisCacheClient jedisCacheClient) {
        JedisCacheClient.jedisCacheClient = jedisCacheClient;
    }


}
