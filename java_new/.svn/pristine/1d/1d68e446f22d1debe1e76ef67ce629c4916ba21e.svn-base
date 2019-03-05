package com.zhxg.framework.base.utils;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.zhxg.framework.base.exception.SysException;


public class WechatUtil {
    
    public static String getAccessToken() throws SysException{
        String accessToken;
        try {
            accessToken = RedisUtil.getStr(14, "wechat_access_token");
            if(StringUtils.isEmpty(accessToken)){
                String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx641f544aa3542a0d&secret=e4b419137489cd069b5f476a065d7873";
                String accessTokenJsonStr = HttpClientUtils.sendHttpGet(accessTokenUrl);
                com.alibaba.fastjson.JSONObject json = JSON.parseObject(accessTokenJsonStr);
                accessToken = json.getString("access_token");
                RedisUtil.setStr(14, "wechat_access_token", accessToken);
                RedisUtil.setExpire(14, "wechat_access_token", 7000);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SysException();
        }
        return accessToken;
    }

}
