package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.UserBaseInfoDao;
import com.zhxg.yqzj.entities.v1.UserAreaNewInfo;
import com.zhxg.yqzj.entities.v1.UserBaseInfo;

@Repository
public class UserBaseInfoDaoImpl extends BaseDaoImpl<BaseEntity> implements UserBaseInfoDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.UserBaseInfo.";

    @Override
    public List<UserBaseInfo> getUserBaseInfo(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE+"getUserBaseInfo",params);
    }

    @Override
    public int updateUserServiceInfo(Map<String, Object> params) {
        return this.sqlSessionTemplate.update(NAME_SPACE+"updateUserServiceInfo", params);
    }

    @Override
    public int updateUserInfo(Map<String, Object> params) {
        return this.sqlSessionTemplate.update(NAME_SPACE+"updateUserInfo",params);
    }

    @Override
    public int updateUserAreaNewInfo(Map<String, Object> params) {
        return this.sqlSessionTemplate.update(NAME_SPACE+"updateUserAreaNewInfo",params);
    }

    @Override
    public UserBaseInfo getUserBaseInfoByType(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getUserBaseInfoByType", params);
    }

    @Override
    public String getMobileCloudVersion(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getMobileCloudVersion",params);
    }

    @Override
    public int delFirstLoginInfo(Map<String, Object> params) {
        return this.sqlSessionTemplate.delete(NAME_SPACE+"delFirstLoginInfo",params);
    }

    @Override
    public int delFirstGuideInfo(Map<String, Object> params) {
        return this.sqlSessionTemplate.delete(NAME_SPACE+"delFirstGuideInfo",params);
    }

    @Override
    public String getMobileCloudDepartmentId(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getMobileCloudDepartmentId",params);
    }

    @Override
    public UserAreaNewInfo getUserAreaNewInfo(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getUserAreaNewInfo"+params);
    }

    @Override
    public String getMobileCloudSex(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getMobileCloudSex", params);
    }

    @Override
    public int updateUserVerKeyNum(Map<String, Object> params) {
        return this.sqlSessionTemplate.update(NAME_SPACE+"updateUserVerKeyNum", params);
    }

    @Override
    public String getMobileCloudAreaSet(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getMobileCloudAreaSet",params);
    }

    @Override
    public String getLableIndustryId(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getLableIndustryId",params);
    }
    
    @Override
    public String copySubjectInfo(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"copySubjectInfo",params);
    }

    @Override
    public int saveUserReceiveMail(Map<String, Object> params) {
        return this.sqlSessionTemplate.insert(NAME_SPACE+"saveUserReceiveMail", params);
    }

    @Override
    public int saveUserReceiveReportId(Map<String, Object> params) {
        return this.sqlSessionTemplate.insert(NAME_SPACE+"saveUserReceiveReportId",params);
    }
    
    @Override
    public String getUserDefaultTemplate(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getUserDefaultTemplate",params);
    }

    @Override
    public String getUserReceiveMail(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getUserReceiveMail", params);
    }

	@Override
	public int updateHotKeywordFilter(Map<String, Object> params) {
		return this.sqlSessionTemplate.update(NAME_SPACE+"updateHotKeywordFilter", params);
	}

	@Override
	public int insertHotKeywordFilter(Map<String, Object> params) {
		 return this.sqlSessionTemplate.insert(NAME_SPACE+"insertHotKeywordFilter", params);
	}

	@Override
	public String selectHotKeywordFilter(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE+"selectHotKeywordFilter", params);
	}

	@Override
	public int delHotKeywordFilter(Map<String, Object> params) {
		return this.sqlSessionTemplate.delete(NAME_SPACE+"delHotKeywordFilter", params);
	}
	
	@Override
    public int getUserWeekSubcribeStatus(Map<String, Object> paramMap) {
	    Object status = this.sqlSessionTemplate.selectOne(NAME_SPACE+"getUserWeekSubcribeStatus",paramMap);
        return status == null ? 0 : Integer.valueOf(status+"").intValue();
    }

    @Override
    public int saveUserWeekSubcribeStatus(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.insert(NAME_SPACE+"saveUserWeekSubcribeStatus",paramMap);
    }

    @Override
    public String getUserWeekReceviveTime(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getUserWeekReceviveTime",paramMap);
    }

    @Override
    public int saveUserWeekReceviveTime(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.insert(NAME_SPACE+"saveUserWeekReceviveTime",paramMap);
    }

    @Override
    public int saveUserWeekReceviveEmail(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.insert(NAME_SPACE+"saveUserWeekReceviveEmail",paramMap);
    }

    @Override
    public int saveUserWeekReceiveReportId(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.insert(NAME_SPACE+"saveUserWeekReceiveReportId",paramMap);
    }

    @Override
    public String getUserWeekReceiveMail(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getUserWeekReceiveMail", paramMap);
    }

    @Override
    public int saveUserWeekReportCondition(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.insert(NAME_SPACE+"saveUserWeekReportCondition", paramMap);
    }

    @Override
    public String getUserWeekReportCondition(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getUserWeekReportCondition", paramMap);
    }

    @Override
    public String getUserWeekDefaultTemplate(Map<String, Object> params) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE+"getUserWeekDefaultTemplate",params);
    }

	@Override
	public int deleteUserInfo(Map<String, Object> params) {
		return this.sqlSessionTemplate.delete(NAME_SPACE+"deleteUserInfo", params);
	}

	@Override
	public int modifyUserInfo(Map<String, Object> params) {
		return this.sqlSessionTemplate.update(NAME_SPACE+"modifyUserInfo", params);
	}

	@Override
	public int insertUserInfo(Map<String, Object> params) {
		return this.sqlSessionTemplate.insert(NAME_SPACE+"insertUserInfo", params);
	}

	@Override
	public String selectUserInfo(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE+"selectUserInfo", params);
	}
    
    
}
