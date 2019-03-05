package com.zhxg.yqzj.service.impl.v1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.framework.base.utils.HttpClientUtils;
import com.zhxg.framework.base.utils.RedisUtil;
import com.zhxg.framework.base.utils.WechatUtil;
import com.zhxg.yqzj.dao.v1.WeChatDao;
import com.zhxg.yqzj.entities.v1.WeChatUser;
import com.zhxg.yqzj.service.v1.WeChatService;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.service
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户模块服务层接口实现
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.service.impl.v1.UserServiceImpl.java
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
@Service
public class WeChatServiceImpl extends BaseServiceImpl<BaseEntity> implements WeChatService {

    @Autowired
    private WeChatDao weChatDao;
    
    @Override
    protected BaseDao<BaseEntity> getBaseDao() {
        return weChatDao;
    }

    @Override
    public String getQrcode(String userid) throws SysException {
        return "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+getQrcode(userid,0);
    }
    
    private String getQrcode(String userid,int deep) throws SysException{
        String qrcodeUrl;
        try {
            qrcodeUrl = RedisUtil.getStr(14, "qrcodeUrl_"+userid);
            if(StringUtils.isEmpty(qrcodeUrl)){
                String accessToken = WechatUtil.getAccessToken();
                String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+accessToken;
                JSONObject json = new JSONObject();
                json.put("expire_seconds", 604800);
                json.put("action_name", "QR_SCENE");
                JSONObject action_info = new JSONObject();
                JSONObject scene = new JSONObject();
                scene.put("scene_id", userid);
                action_info.put("scene", scene);
                json.put("action_info", action_info);
                String accessTokenJsonStr = HttpClientUtils.sendHttpPost(url, json.toJSONString());
                if((accessTokenJsonStr.contains("42001")||accessTokenJsonStr.contains("40001"))&&deep<3){
                    RedisUtil.delete(14, "wechat_access_token");
                    qrcodeUrl = getQrcode(userid,deep++);
                }
                com.alibaba.fastjson.JSONObject ticketJson = JSON.parseObject(accessTokenJsonStr);
                String tikcet = ticketJson.getString("ticket");
                qrcodeUrl =  tikcet;
                RedisUtil.setStr(14,"qrcodeUrl_"+userid , qrcodeUrl);;
                RedisUtil.setExpire(14, "qrcodeUrl_"+userid, 600000);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SysException();
        }
        return qrcodeUrl;
    }

    @Override
    public List<WeChatUser> getWeChatList(String userid) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("userid", userid);
        List<WeChatUser> list = weChatDao.getWeChatList(param);
        return list;
    }

    @Override
    public Map<String,Object> getWechatCount(String userid) {
        return weChatDao.getWechatCount(userid);
    }

    @Override
    public Map<String,Object> getQQCount(String userid) {
        return weChatDao.getQQCount(userid);
    }
    
}
