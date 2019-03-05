package com.zhxg.yqzj.web.service.v1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhxg.framework.base.constants.SysConstants;
import com.zhxg.framework.base.utils.RedisUtil;
import com.zhxg.sso.SsoService;
import com.zhxg.yqzj.dao.v1.AccountDao;
import com.zhxg.yqzj.entities.v1.Account;

@Component
public class SsoServiceImpl implements SsoService {
    
    @Autowired
    AccountDao accountDao;

    /* 
     * 获取在线用户的SSOAccountId集合，用于ssoServer心跳，刷新过期时间
     */
    @Override
    public Set<String> getVaildAccountIds() {
        Set<String> ssoAccounts = new HashSet<>();
        List<Account> accountList = this.accountDao.getVaildAccounts();
        Set<String> keys = RedisUtil.keys(SysConstants.ACCESSTOKEN_DB,SysConstants.PRODUCT_PREFIX+"*");
        for(String key:keys){
            // 使用时注意兼容专家APP的token监测
//            boolean exist = false;
//            for(Account account:accountList){
//                if(key.replace(SysConstants.PRODUCT_PREFIX, "").equals(account.getAccountId().toString())) {
//                    ssoAccounts.add(account.getSsoAccountId().toString());
//                    exist = true;
//                    break;
//                }
//            }
//            if(!exist){//删除过期用户accessToken
//                RedisUtil.delete(SysConstants.ACCESSTOKEN_DB,SysConstants.PRODUCT_PREFIX + key.replace(SysConstants.PRODUCT_PREFIX, ""));
//            }
        }
        return ssoAccounts;
    }

    /* 
     * 订阅消息，强制下线。如被顶号，修改密码
     */
    @Override
    public boolean clearAccessToken(String ssoAccountId) {
        Map<String, Object> params = new HashMap<>();
        params.put("accountId", ssoAccountId);
        Account account = this.accountDao.getAccount(params);
        if (account != null) {
            Long l = RedisUtil.delete(SysConstants.ACCESSTOKEN_DB,SysConstants.PRODUCT_PREFIX + account.getAccountId());
            if(l>0){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

}
