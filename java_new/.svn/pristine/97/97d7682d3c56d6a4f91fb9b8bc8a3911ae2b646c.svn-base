package com.zhxg.yqzj.web.api.v1;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.constants.SysConstants;
import com.zhxg.framework.base.controller.BaseController;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.exception.UserNoFoundException;
import com.zhxg.framework.base.http.ApiResponseBody;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.framework.base.utils.DateStyle;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.framework.base.utils.PageUtil;
import com.zhxg.framework.base.utils.ParamsUtil;
import com.zhxg.yqzj.entities.v1.SearchConditionInfo;
import com.zhxg.yqzj.entities.v1.WkTValidationRef;
import com.zhxg.yqzj.service.v1.WkTValidationRefService;

@RestController
@RequestMapping("/v1/wkTValidationRef")
public class WkTValidationRefController extends BaseController<WkTValidationRef> {
	@Autowired
	private WkTValidationRefService wkTValidationRefService;

	@Override
	protected BaseService<WkTValidationRef> getBaseService() {
		return this.wkTValidationRefService;
	}

	@ResponseBody
	@RequestMapping(value = "/getRefList", method = RequestMethod.GET)
	public ApiResponseBody getRefList(HttpServletRequest request, @ModelAttribute WkTValidationRef wkTValidationRef)
			throws UserNoFoundException, SysException {
		Map<String, Object> paramMap = this.getUserInfo(request);
		String userId = StringUtils.trimToEmpty(paramMap.get(SysConstants._OTHER_KUID) + "");
		if (StringUtils.isBlank(userId)) {
			userId = StringUtils.trimToEmpty(paramMap.get(SysConstants._KUID) + "");
		}
		logger.info("用户id:" + userId);
		paramMap.putAll(ParamsUtil.transToMAP(wkTValidationRef, WkTValidationRef.class));
		wkTValidationRef = ParamsUtil.mapToObject(paramMap, WkTValidationRef.class);
		paramMap.put("orgId", wkTValidationRef.getOrgId());
		paramMap.put("userId", userId);
		if (StringUtils.isNotBlank(wkTValidationRef.getClassifyId())) {
			List<String> kkidList = this.wkTValidationRefService.getKkIdByClassifyId(wkTValidationRef.getClassifyId());
			if (null != kkidList && kkidList.size() > 0) {
				wkTValidationRef.setKkIdList(kkidList);
			} else {
				return this.returnSuccess(new PageInfo<WkTValidationRef>());
			}
		}
		String condition = this.wkTValidationRefService.getSearchConditionInfo(paramMap);
		if (StringUtils.isNotBlank(condition)) {
			SearchConditionInfo searchConditionInfo = JSON.parseObject(condition, SearchConditionInfo.class);
			this.addSearchCondition(wkTValidationRef, searchConditionInfo);
		}
		PageInfo<WkTValidationRef> page = this.wkTValidationRefService.getRefList(wkTValidationRef,
				PageUtil.getPageInfo(request), userId);
		return this.returnSuccess(page);
	}

	private void addSearchCondition(WkTValidationRef wkTValidationRef, SearchConditionInfo searchConditionInfo) {
		try {
//			if(StringUtils.isNotBlank(searchConditionInfo.getKkid())) {
//				wkTValidationRef.getKkIdList().add(searchConditionInfo.getKkid());
//			}
			if (WkTValidationRef.TIMETYPE_DAY.equals(searchConditionInfo.getTime())) {
				wkTValidationRef.setTimeType(WkTValidationRef.TIMETYPE_DAY);
			} else if ("week".equals(searchConditionInfo.getTime())) {
				wkTValidationRef.setTimeType(WkTValidationRef.TIMETYPE_SEVENDAYS);
			} else if ("24".equals(searchConditionInfo.getTime())) {
				Date date = new Date();
				Date hourDate = DateUtil.addHour(date, -24);
				wkTValidationRef.setStartTime(DateUtil.DateToString(hourDate, DateStyle.YYYYMMDDHHMMSS));
				wkTValidationRef.setEndTime(DateUtil.DateToString(date, DateStyle.YYYYMMDDHHMMSS));
			} else {
				Date btime = DateUtil.StringToDate(searchConditionInfo.getBtime(), DateStyle.YYYY_MM_DD_HH_MM_SS);
				Date etime = DateUtil.StringToDate(searchConditionInfo.getEtime(), DateStyle.YYYY_MM_DD_HH_MM_SS);
				wkTValidationRef.setStartTime(DateUtil.DateToString(btime, DateStyle.YYYYMMDDHHMMSS));
				wkTValidationRef.setEndTime(DateUtil.DateToString(etime, DateStyle.YYYYMMDDHHMMSS));
			}
			String sourceType = searchConditionInfo.getKV_SOURCETYPE();
			sourceType = sourceType.endsWith(",") ? sourceType.substring(0, sourceType.length() - 1) : sourceType;
			if (StringUtils.isNotBlank(sourceType)) {
				String[] sourceTypes = sourceType.split(",");
				wkTValidationRef.getKvSourcetypeList().addAll(Arrays.asList(sourceTypes));
				wkTValidationRef.getKvSourcetypeList().remove("xx");
			}
			String ori = searchConditionInfo.getKV_ORIENTATION();
			if (StringUtils.equals("0", ori)) {
				wkTValidationRef.setIsyj(1);
			} else {
				ori = ori.endsWith(",") ? ori.substring(0, ori.length() - 1) : ori;
				if (StringUtils.isNotBlank(ori)) {
					String[] orientations = ori.split(",");
					wkTValidationRef.getOrientationList().addAll(Arrays.asList(orientations));
					wkTValidationRef.getOrientationList().remove("4");
				}
			}
			if (searchConditionInfo.getWechatInfoType().endsWith(",")) {
				String[] wechatInfoTypes = searchConditionInfo.getWechatInfoType().split(",");
				for (String str : wechatInfoTypes) {
					try {
						wkTValidationRef.getWechatInfoTypeList().add(Integer.parseInt(str));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}
			if (searchConditionInfo.getInfoSourceLevel().endsWith(",")) {
				String[] infoSourceLevels = searchConditionInfo.getInfoSourceLevel().split(",");
				for (String infoSourceLevel : infoSourceLevels) {
					try {
						wkTValidationRef.getInfoSourceLevelList().add(Integer.parseInt(infoSourceLevel));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}
			if (StringUtils.isNotBlank(searchConditionInfo.getCities())) {
				wkTValidationRef.setRegion(Integer.parseInt(searchConditionInfo.getCities()));
			}
			wkTValidationRef.setOcr(searchConditionInfo.getIsOcr());
			wkTValidationRef.setKrState(searchConditionInfo.getKR_STATE());
			String repeat = searchConditionInfo.getHaveRepeat();
			repeat = StringUtils.equals("0", repeat) ? "1" : "0";
			wkTValidationRef.setIsRepeat(Integer.parseInt(repeat));
			String isread = searchConditionInfo.getIS_READ();
			isread = StringUtils.equals("", isread) ? "-1" : isread;
			wkTValidationRef.setIsread(Integer.parseInt(isread));
			wkTValidationRef.setKvTitlematch(searchConditionInfo.getKV_TITLEMATCH());
			wkTValidationRef.setKvOnlylocal(searchConditionInfo.getKV_ONLYLOCAL());
			if (StringUtils.isNotBlank(searchConditionInfo.getKV_MUSTWORDMININDEX())) {
				wkTValidationRef.setKvMustwordminindex(Integer.parseInt(searchConditionInfo.getKV_MUSTWORDMININDEX()));
			}
			if (StringUtils.isNotBlank(searchConditionInfo.getKV_KEYWORDSMININDEX())) {
				wkTValidationRef.setKvKeywordsminindex(Integer.parseInt(searchConditionInfo.getKV_KEYWORDSMININDEX()));
			}
			wkTValidationRef.setKvWeiboovertime(searchConditionInfo.getKV_WEIBOOVERTIME());
			wkTValidationRef.setKvWeibosignlocalnoise(searchConditionInfo.getKV_WEIBOSIGNLOCALNOISE());
			wkTValidationRef.setKvWeibotopicnoise(searchConditionInfo.getKV_WEIBOTOPICNOISE());
			wkTValidationRef.setKvWeiboatnoise(searchConditionInfo.getKV_WEIBOATNOISE());

			if (StringUtils.isNotBlank(searchConditionInfo.getTags())) {
				final String regex = "((\\w{32},|\\d*,)?)*(\\w{32}|\\d*)";
				Pattern p = Pattern.compile(regex, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
				Matcher matcher = p.matcher(searchConditionInfo.getTags());
				boolean b = matcher.matches();
				if (b) {
					wkTValidationRef.setTags(searchConditionInfo.getTags());
				}
			}
		} catch (NumberFormatException e) {
			throw e;
		}
	}

}
