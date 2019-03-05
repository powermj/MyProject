package com.zhxg.yqzj.service.impl.v1;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.yqzj.dao.v1.UserDao;
import com.zhxg.yqzj.dao.v1.WarningDao;
import com.zhxg.yqzj.entities.v1.User;
import com.zhxg.yqzj.entities.v1.Warning;
import com.zhxg.yqzj.service.v1.WarningService;

@Service
public class WarningServiceImpl extends BaseServiceImpl<Warning> implements WarningService {

	@Autowired
	private WarningDao WarningDao;
	
	@Autowired
	private UserDao userDao;

	@Override
    protected BaseDao<Warning> getBaseDao() {
        return this.WarningDao;
    }

	@Override
	public PageInfo<Warning> getIndexWaringList(Map<String, Object> paramMap, Pagination pageInfo) throws SysException {
	    paramMap.put("ksTime", DateUtil.getDate(new Date()).replace("-", ""));
		User user = userDao.getArtificialWarningSubject(paramMap.get("userid").toString());
		if(user.getArtificialWaringSubjects().isEmpty()){
			paramMap.put("isArtificial",false);
		}else{
			paramMap.put("isArtificial",true);
		}
        
        PageInfo<Warning> page;
        try{
        	page = this.WarningDao.getIndexWaringList_self(paramMap, pageInfo);
        }catch(RuntimeException e){
        	System.out.println(e.getMessage());
        	throw new SysException();
        }
        
		return page;
	}
}
