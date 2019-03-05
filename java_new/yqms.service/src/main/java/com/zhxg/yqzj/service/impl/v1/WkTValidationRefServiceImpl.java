package com.zhxg.yqzj.service.impl.v1;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.constants.SysConstants;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.framework.base.utils.DateStyle;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.framework.base.utils.ParamsUtil;
import com.zhxg.yqzj.dao.v1.WkTValidationRefDao;
import com.zhxg.yqzj.entities.v1.WkTValidationRef;
import com.zhxg.yqzj.entities.v1.WkTValidationRefExample;
import com.zhxg.yqzj.service.util.DetailOperateUtil;
import com.zhxg.yqzj.service.v1.WkTValidationRefService;

@Service
public class WkTValidationRefServiceImpl extends BaseServiceImpl<WkTValidationRef> implements WkTValidationRefService {

	@Autowired
	private WkTValidationRefDao wkTValidationRefDao;

	@Override
	protected BaseDao<WkTValidationRef> getBaseDao() {
		return this.wkTValidationRefDao;
	}

	@Override
	public PageInfo<WkTValidationRef> getRefList(WkTValidationRef wkTValidationRef, Pagination pageInfo, String userId)
			throws SysException {
		try {
			// 判断时间
			Date date = new Date();
			if (WkTValidationRef.TIMETYPE_DAY.equals(wkTValidationRef.getTimeType())) {
				String startTime = DateUtil.DateToString(date, DateStyle.YYYYMMDD) + SysConstants.BTIME_HHMMSS;
				String endTime = DateUtil.DateToString(date, DateStyle.YYYYMMDDHHMMSS);
				wkTValidationRef.setStartTime(startTime);
				wkTValidationRef.setEndTime(endTime);
			} else if (WkTValidationRef.TIMETYPE_SEVENDAYS.equals(wkTValidationRef.getTimeType())) {
				String startTime = DateUtil.DateToString(DateUtil.addDay(date, -6), DateStyle.YYYYMMDD)
						+ SysConstants.BTIME_HHMMSS;
				String endTime = DateUtil.DateToString(date, DateStyle.YYYYMMDDHHMMSS);
				wkTValidationRef.setStartTime(startTime);
				wkTValidationRef.setEndTime(endTime);
			}
			Map<String, WkTValidationRef> map = new HashMap<>();
			long start = System.currentTimeMillis();
			this.logger.info("用户id:" + userId + "开始查询simHashOrURLByLimit");
			int num = 0;
			PageInfo<WkTValidationRef> page = null;
			while (map.size() < pageInfo.getPageSize() && num < 20) {
				pageInfo.setPageNum(num);
				page = this.wkTValidationRefDao.getRefUrlOrhashListByLimit_other(wkTValidationRef, pageInfo);
				if (page.getTotal() < page.getSize()) {
					break;
				}
				if (1 == wkTValidationRef.getIsRepeat()) {
					if (null != page && null != page.getList() && !page.getList().isEmpty()) {
						for (int i = page.getList().size() - 1; i >= 0; i--) {
							if (map.size() < pageInfo.getPageSize()) {
								map.put(page.getList().get(i).getKvSimhash(), page.getList().get(i));
							} else {
								break;
							}
						}
					}
				} else {
					if (null != page && null != page.getList() && !page.getList().isEmpty()) {
						for (int i = page.getList().size() - 1; i >= 0; i--) {
							if (map.size() < pageInfo.getPageSize()) {
								map.put(page.getList().get(i).getKvUrl(), page.getList().get(i));
							} else {
								break;
							}
						}
					}
				}
				num++;
			}

			long end = System.currentTimeMillis();
			this.logger.info("用户id:" + userId + "查询simHashOrURLByLimit耗时" + (end - start));
			String agentId = this.wkTValidationRefDao.getAgentId(ParamsUtil.transToMAP(wkTValidationRef, WkTValidationRef.class));
			page.getList().clear();
			for (WkTValidationRef value : map.values()) {
				// 查询相同信息数量
//				WkTValidationRefExample example = new WkTValidationRefExample();
//				try {
//					BeanUtils.copyProperties(example, wkTValidationRef);
//				} catch (Exception e) {
//					this.logger.error("---查询条件对象复制错误---" + e.getMessage(), e);
//				}
//				example.createCriteria().andKvSimhashEqualTo(value.getKvSimhash());
//				long count = this.wkTValidationRefDao.countByExample_other(example);
//				if (count > 0) {
//					count -= 1;
//				}
//				value.setKvHot(Integer.valueOf(count + ""));

				value.setKvTitleMakeRed(DetailOperateUtil.makeRedForTitle(value.getKvKeyword(), value.getKvTitle()));
//				value.setKvAbstractMakeRed(DetailOperateUtil.makeRedForTitle(value.getKeyword(), value.getKvAbstract()));
				value.setKvImgurl(DetailOperateUtil.filterMedia(value.getKvImgurl(), agentId));
				value.setKvWbauthorpic(DetailOperateUtil.filterMedia(value.getKvWbauthorpic(), agentId));
				value.setKvTitle(null);
				value.setKvExtendField(null);
				// 把最终查询结果添加到PageInfo对象
				page.getList().add(value);
			}
			end = System.currentTimeMillis();
			this.logger.info("用户id:" + userId + "查询相同信息条数和信息关键字标红耗时" + (end - start));
			start = System.currentTimeMillis();
			this.logger.info("用户id:" + userId + "结果排序");
			page.getList().sort((a, b) -> {
				return b.getKrCtime().compareTo(a.getKrCtime());
			});
			page.getList().forEach(value -> {
				// 格式化日期
				Date krCtime = DateUtil.StringToDate(value.getKrCtime(), DateStyle.YYYYMMDDHHMMSS);
				value.setKrCtime(DateUtil.DateToString(krCtime, DateStyle.YYYY_MM_DD_HH_MM));

			});
			end = System.currentTimeMillis();
			this.logger.info("用户id:" + userId + "结果排序耗时 " + (end - start));
			this.logger.info("用户id:" + userId + "返回查询结果");
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			this.logger.error("---查询个人信息REF表-----" + e.getMessage(), e);
			throw new SysException(e);
		}
	}

	@Override
	public String getSearchConditionInfo(Map<String, Object> paramMap) throws SysException {
		try {
			return wkTValidationRefDao.getSearchConditionInfo(paramMap);
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
			throw new SysException(e);
		}
	}

	@Override
	public List<String> getKkIdByClassifyId(String classifyId) throws SysException {
		try {
			return wkTValidationRefDao.getKkIdByClassifyId(classifyId);
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
			throw new SysException();
		}
	}
}
