package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.WeChatDao;
import com.zhxg.yqzj.entities.v1.WeChatUser;

@Repository
public class WeChatDaoImpl extends BaseDaoImpl<BaseEntity> implements WeChatDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.WeChatUser.";

    @Override
    public List<WeChatUser> getWeChatList(Map<String,Object> param) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getWeChatList", param);
    }

    @Override
    public Map<String, Object> getWechatCount(String userid) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getWechatCount", userid);
    }

    @Override
    public Map<String, Object> getQQCount(String userid) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getQQCount", userid);
    }
    
    

}
