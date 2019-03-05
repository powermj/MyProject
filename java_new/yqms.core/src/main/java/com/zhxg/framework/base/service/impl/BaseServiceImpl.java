package com.zhxg.framework.base.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhxg.framework.base.constants.SysConstants;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.service.BaseService;



/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 基础服务实现
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.service.impl.BaseServiceImpl.java
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
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 获得基础持久层
     * 
     * @return BaseDao<T>
     */
    protected abstract BaseDao<T> getBaseDao();


    @Override
    public int create(T t) {
        return this.getBaseDao().create(t);
    }
    @Override
    public int updateById(T t) {
        return this.getBaseDao().updateById(t);
    }

    @Override
    public T retrieveOneById(String id) {
        return this.getBaseDao().retrieveOneById(id);
    }

    @Override
    public int deleteOneById(String id) {
        return this.getBaseDao().deleteOneById(id);
    }

    @Override
    public Map<String, Object> getUserInfoByUserIds(Map<String, Object> accountIds)
            throws SysException, UserNoFoundException {
        List<String> accountIdList = new ArrayList<String>();
        String accountId = accountIds.get("accountId").toString();
        String shareAccountId = accountIds.get("shareAccountId").toString();
        List<HashMap<String, Object>> accountList = new ArrayList<HashMap<String, Object>>();
        try{
            if(accountId.contains("U")){
                accountId = accountId.replace("U", "");
                accountIdList.add(accountId);
                accountIdList.add(shareAccountId);
                accountList = this.getBaseDao().getAccountInfoByUserIds(accountIdList);
            }else{
                accountIdList.add(accountId);
                accountList = this.getBaseDao().getAccountInfoByAccountIds(accountIdList);
                
                List<String> shareAccountIdList = new ArrayList<String>();
                shareAccountIdList.add(shareAccountId);
                List<HashMap<String, Object>> shareAccountList = this.getBaseDao().getAccountInfoByUserIds(shareAccountIdList);
                accountList.addAll(shareAccountList);
            }
        } catch (RuntimeException re) {
            this.logger.error(this.getClass().getName() + ".getAccountInfoByAccountIds:::" + re,re);
            throw new SysException();
        }
        //|| ((StringUtils.isNotBlank(accountId) && !accountId.equals(shareAccountId) && StringUtils.isNotBlank(shareAccountId)) && accountList.size() != 2)
        if (accountList.isEmpty() ) {
            this.logger.error("the accountId：　" + accountId + " or shareAccountId: " + shareAccountId + SysConstants._NO_FOUND);
            throw new UserNoFoundException();
        }
        HashMap<String, Object> reMap = new HashMap<String, Object>();
        for (HashMap<String, Object> account : accountList) {
            if (accountId.equals(account.get("accountId").toString())) {
                reMap.put(SysConstants._KUID, account.get("userId").toString());
                reMap.put(SysConstants._DBHOST, account.get("dbHost"));
                reMap.put(SysConstants._DBNAME, "U" + account.get("userId").toString());
            }
            if (shareAccountId.equals(account.get("accountId").toString())) {
                reMap.put(SysConstants._OTHER_KUID, account.get("accountId").toString());
                reMap.put(SysConstants._OTHER_DBHOST, account.get("dbHost"));
                reMap.put(SysConstants._OTHER_DBNAME, "U" + account.get("accountId").toString());
            }
        }
        return reMap;
//        return this.paramsHandler(reMap);
    }

    /**
     * 请求参数转换处理
     *
     * @param paramMap
     * @return
     */
    public HashMap<String, Object> paramsHandler(HashMap<String, Object> paramMap) {
        HashMap<String, Object> temp = new HashMap<String, Object>();
        // shareUserId如果存在，则缓存并覆盖原有userId，应对即需要查询个人库，又需要查询分享人个人库
        if (paramMap.containsKey(SysConstants._OTHER_KUID)) {
            temp.put(SysConstants._KUID, paramMap.get(SysConstants._OTHER_KUID));
            temp.put(SysConstants._DBHOST, paramMap.get(SysConstants._OTHER_DBHOST));
            temp.put(SysConstants._DBNAME, paramMap.get(SysConstants._OTHER_DBNAME));
            temp.put(SysConstants._OTHER_KUID, paramMap.get(SysConstants._KUID));
            temp.put(SysConstants._OTHER_DBHOST, paramMap.get(SysConstants._DBHOST));
            temp.put(SysConstants._OTHER_DBNAME, paramMap.get(SysConstants._DBNAME));
        } else {
            temp = paramMap;
        }
        return temp;
    }
}
