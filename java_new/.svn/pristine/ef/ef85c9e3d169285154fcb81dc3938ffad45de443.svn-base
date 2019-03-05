package com.zhxg.yqzj.service.impl.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhxg.framework.base.constants.SysConstants;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.ParameterException;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.framework.base.utils.RedisUtil;
import com.zhxg.framework.base.utils.UUIDUtils;
import com.zhxg.yqzj.dao.v1.SubjectDao;
import com.zhxg.yqzj.dao.v1.UserDao;
import com.zhxg.yqzj.entities.v1.Subject;
import com.zhxg.yqzj.entities.v1.User;
import com.zhxg.yqzj.service.exception.subject.KeywordExceedingLimitException;
import com.zhxg.yqzj.service.exception.subject.SubjectNameRepeatException;
import com.zhxg.yqzj.service.exception.user.PermissionDeniedException;
import com.zhxg.yqzj.service.v1.SubjectService;

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
public class SubjectServiceImpl extends BaseServiceImpl<BaseEntity> implements SubjectService {

    @Autowired
    private SubjectDao subjectDao;
    
    @Autowired
    private UserDao userDao;

    private final static Logger log = LoggerFactory.getLogger(SubjectServiceImpl.class);

    @Override
    protected BaseDao<BaseEntity> getBaseDao() {
        return this.subjectDao;
    }

    @Override
    public void addSubject(Subject subject) throws PermissionDeniedException, KeywordExceedingLimitException, SubjectNameRepeatException, ParameterException {
        checkUserStatus(subject.getUserid());
        checkSubjectName(subject);
        checkKeyWordLimit(subject);
        checkParams(subject);
        String uuid = insertSubject(subject);
        syncSubjectWord(subject.getUserid());
        dataSupplement(uuid);
        
    }

    /**
     * 数据补充
     * @param uuid
     */
    private void dataSupplement(String uuid) {
        // TODO Auto-generated method stub
        
    }

    /**
     * 词同步
     * @param userid
     */
    private void syncSubjectWord(int userid) {
        // TODO Auto-generated method stub
        
    }

    /**
     * 专题数据入库
     * @param subject
     * @return
     */
    private String insertSubject(Subject subject) {
        // TODO Auto-generated method stub
        String uuid = UUIDUtils.random();
        subject.getConfigure().setConfigUuid(uuid);
        int result1 = subjectDao.insertSubjectConfig(subject.getConfigure());
        //if result1 < 1 throw new sysException
        String uuid2 = UUIDUtils.random();
        subject.setUuid(uuid2);
        int result2 = subjectDao.insertSubject(subject);
        //if result2 < 1 throw new sysException
        return uuid2;
    }

    /**
     * 参数校验
     * @param subject
     * @throws ParameterException
     */
    private void checkParams(Subject subject) throws ParameterException {
        // TODO Auto-generated method stub
        if(true){
            throw new ParameterException();
        }
        
    }

    /**
     * 校验重名
     * @param subject
     * @throws SubjectNameRepeatException 
     */
    private void checkSubjectName(Subject subject) throws SubjectNameRepeatException {
        // TODO Auto-generated method stub
        if(true){
            throw new SubjectNameRepeatException();
        }
        
    }

    /**
     * 校验关键词数量
     * @param subject
     * @throws KeywordExceedingLimitException
     */
    private void checkKeyWordLimit(Subject subject) throws KeywordExceedingLimitException {
        // TODO Auto-generated method stub
        if(true){
            throw new KeywordExceedingLimitException();
        }
        
    }

    /**
     * 校验用户状态
     * @param userid
     * @throws PermissionDeniedException
     */
    private void checkUserStatus(int userid) throws PermissionDeniedException {
        //如果用户为停用状态 status >=2
        User user = userDao.getUserByKuId(userid+"");
        if(user.getUserState()>=SysConstants.EXPIRED_USER_STATE){
            throw new PermissionDeniedException();
        }
        
    }

	@Override
	public boolean changeUserClassify(Subject subject, String targetKcuuid) {
		subject.setUserClassifyId(targetKcuuid);
		Integer maxIndex = subjectDao.selectMaxIndexByKcuuid(subject);
		if(maxIndex== null)maxIndex=0;
		subject.setIndex_c(maxIndex+1);
		int result = subjectDao.updateSubjectKcuuid(subject);
	    if(result>0) 
	    	RedisUtil.delete(4, "SQL_CACHE_"+subject.getUserid());
        return 	result > 0? true : false;
	}

}
