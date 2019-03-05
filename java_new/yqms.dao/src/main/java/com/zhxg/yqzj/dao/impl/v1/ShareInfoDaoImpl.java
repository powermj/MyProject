package com.zhxg.yqzj.dao.impl.v1;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.yqzj.dao.v1.ShareInfoDao;
import com.zhxg.yqzj.entities.v1.ShareInfo;

/**
 * <p>
 * CopyRright (c)2012-2016: HAORAN
 * <p>
 * Project: yqms.dao
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 信息分享接口实现
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqms.dao.impl.v1.ShareInfoDaoImpl.java
 * <p>
 * Author: HAORAN
 * <p>
 * Create Date: 2018年7月21日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
@Repository
public class ShareInfoDaoImpl extends BaseDaoImpl<BaseEntity> implements ShareInfoDao {

    private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.ShareInfo.";

    @Override
    public Map<String, Object> getSubjectInfoDetail_other(Map<String, Object> paramMap) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int insertShareInfo(ShareInfo shareInfo) {
        int result = 0;
        result = sqlSessionTemplate.insert(NAME_SPACE + "insertShareInfo", shareInfo);
        return result;
    }


}
