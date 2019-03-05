package com.zhxg.yqzj.service.impl.v1;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.yqzj.dao.v1.ExpertDao;
import com.zhxg.yqzj.entities.v1.Expert;
import com.zhxg.yqzj.service.v1.ExpertService;

@Service
public class ExpertServiceImpl extends BaseServiceImpl<Expert> implements ExpertService {

	@Autowired
	private ExpertDao expertDao;
	

	@Override
    protected BaseDao<Expert> getBaseDao() {
        return this.expertDao;
    }

	@Override
	public PageInfo<Expert> getExpertList(Map<String, Object> paramMap, Pagination pageInfo) throws SysException {
        PageInfo<Expert> page;
        try{
        	page = this.expertDao.getExpertList(paramMap, pageInfo);
        }catch(RuntimeException e){
        	System.out.println(e.getMessage());
        	throw new SysException();
        }
		return page;
	}

    @Override
    public List<Expert> getExpertInfodetail(Map<String, Object> paramMap) {
        
        return this.expertDao.getExpertInfodetail(paramMap);
    }

    @Override
    public List<Expert> getConsultationType(Map<String, Object> paramMap) {
        return this.expertDao.getConsultationType(paramMap);
    }

    @Override
    public List<Expert> getAllExpertType(Map<String, Object> paramMap) {
        return this.expertDao.getAllExpertType(paramMap);
    }

}
