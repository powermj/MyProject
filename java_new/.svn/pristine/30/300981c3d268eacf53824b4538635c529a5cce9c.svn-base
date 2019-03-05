package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.yqzj.dao.v1.InfoSourceDao;
import com.zhxg.yqzj.entities.v1.InfoSource;

@Repository
public class InfoSourceDaoImpl extends BaseDaoImpl<InfoSource> implements InfoSourceDao {
	private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.InfoSource.";

	@Override
	public List<InfoSource> getInfoSourceList() {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "getInfoSourceList");
	}
}
