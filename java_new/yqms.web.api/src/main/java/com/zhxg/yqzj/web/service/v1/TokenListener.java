package com.zhxg.yqzj.web.service.v1;

import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhxg.framework.base.constants.SysConstants;
import com.zhxg.framework.base.utils.PropertiesUtil;
import com.zhxg.framework.base.utils.RedisUtil;
import com.zhxg.sso.utils.RedisPoolUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class TokenListener extends JedisPubSub {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private final String ACCESSTOKEN_HEARTBEAT = "ACCESSTOKEN-HEARTBEAT";
    
    public TokenListener(){
        logger.debug("TokenListener 注入成功。");
    }

    public void init() {
        final TokenListener listener = new TokenListener();
        Jedis redisClient;
        try {
            redisClient = RedisPoolUtil.getJedisPool(PropertiesUtil.getValue("redis.service.url") + ":" + PropertiesUtil.getValue("redis.service.port"))
                    .getResource();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    redisClient.subscribe(listener, new String[] { ACCESSTOKEN_HEARTBEAT });
                }
            }).start();
        } catch (URISyntaxException e) {
            logger.error(e.getMessage(),e);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }

    }

    @Override
    public void onMessage(String channel, String message) {
        logger.info(channel + "," + message);
        JSONObject json = JSON.parseObject(message);
        if (ACCESSTOKEN_HEARTBEAT.equals(channel)) {// 刷新accessToken到期时间
            String accountId = json.getString("accountId");
            RedisUtil.setExpire(SysConstants.PRODUCT_PREFIX + accountId, SysConstants.ACCESSTOKEN_TIMEOUT,
                    TimeUnit.MINUTES);// 专家token设置30分钟有效期

        }

    }

}
