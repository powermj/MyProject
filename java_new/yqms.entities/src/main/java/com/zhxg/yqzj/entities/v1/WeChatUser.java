package com.zhxg.yqzj.entities.v1;

import com.zhxg.framework.base.curd.impl.BaseEntity;

public class WeChatUser extends BaseEntity {

    
    private int subscribe;//用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
    private String openid;
    private String nickname;
    private int sex  ;//用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private Long subscribe_time; //秒级时间戳
    private String subscribe_scene;//关注来源，搜索、扫码等
    
    private String userid;//秘书用户ID
    
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public int getSubscribe() {
        return subscribe;
    }
    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public int getSex() {
        return sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getHeadimgurl() {
        return headimgurl;
    }
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }
    public Long getSubscribe_time() {
        return subscribe_time;
    }
    public void setSubscribe_time(Long subscribe_time) {
        this.subscribe_time = subscribe_time;
    }
    public String getSubscribe_scene() {
        return subscribe_scene;
    }
    public void setSubscribe_scene(String subscribe_scene) {
        this.subscribe_scene = subscribe_scene;
    }
    
    
}
