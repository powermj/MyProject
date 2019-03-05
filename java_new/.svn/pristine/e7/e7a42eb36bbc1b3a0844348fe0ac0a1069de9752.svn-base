package com.zhxg.yqzj.service.impl.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.yqzj.dao.v1.InfoSourceDao;
import com.zhxg.yqzj.entities.v1.InfoSource;
import com.zhxg.yqzj.service.v1.InfoSourceService;

@Service
public class InfoSourceServiceImpl extends BaseServiceImpl<InfoSource> implements InfoSourceService {

	@Autowired
	private InfoSourceDao infoSourceDao;

	@Override
	protected BaseDao<InfoSource> getBaseDao() {
		return this.infoSourceDao;
	}

	@Override
	public List<InfoSource> getInfoSourceList() throws SysException {
		try {
			return infoSourceDao.getInfoSourceList();
		} catch (Exception e) {
			 this.logger.error(e.getMessage(), e);;
			 throw new SysException();
		}
	}

}
