package com.zhxg.yqzj.dao.v1;

import java.util.List;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.yqzj.entities.v1.InfoSource;

public interface InfoSourceDao extends BaseDao<InfoSource> {

	public List<InfoSource> getInfoSourceList();

}
