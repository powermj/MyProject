package com.zhxg.yqzj.service.v1;


import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.Account;
import com.zhxg.yqzj.service.exception.user.AccountNumberExceedingLimitException;
import com.zhxg.yqzj.service.exception.user.AccountUnavailableException;
import com.zhxg.yqzj.service.exception.user.CodeErrorException;
import com.zhxg.yqzj.service.exception.user.UserExpiredException;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.service
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 用户模块服务层接口
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.service.v1.UserService.java
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
public interface LoginService extends BaseService<Account> {


	public Account login(HttpServletRequest request) throws UserNoFoundException, UserExpiredException, AccountUnavailableException;

	public Account loginApp(HttpServletRequest request)throws UserNoFoundException, UserExpiredException, AccountUnavailableException;

    public int applyForTrial(String mName, String tel, String title) throws SQLException;

    public Map<String, Object> bindingCellPhoneNumber(String loginName, String cellPhoneNumber, String passWord) throws AccountNumberExceedingLimitException,UserNoFoundException;

    public void deletePhoneList(String macid);

    public void deletePhoneMacid(String macid);

    public Map<String, Object> getUserInfo(Map<String, Object> paramMap) throws CodeErrorException;
    
    public Map<String, Object> deleteTelphone(String userId,String telphone);

}
