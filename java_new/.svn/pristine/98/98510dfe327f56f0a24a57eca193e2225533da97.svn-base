package com.zhxg.yqzj.service.impl.v1;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.constants.SysConstants;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.framework.base.utils.DateStyle;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.framework.base.utils.HeadLinesType;
import com.zhxg.framework.base.utils.RedisUtil;
import com.zhxg.framework.base.utils.SourceType;
import com.zhxg.yqzj.dao.v1.CustomHomePageDao;
import com.zhxg.yqzj.entities.v1.CustomHomePage;
import com.zhxg.yqzj.service.v1.CustomHomePageService;

@Service
public class CustomHomePageServiceImpl extends BaseServiceImpl<CustomHomePage> implements CustomHomePageService {

	@Autowired
	private CustomHomePageDao customHomePageDao;

	@Override
	protected BaseDao<CustomHomePage> getBaseDao() {
		return this.customHomePageDao;
	}

	@Override
	public JSONArray getCustomHomePage(Map<String, Object> paramMap) throws Exception {
		JSONArray result = new JSONArray();
		String userdefinedhomedatatb = "";
		try {
			userdefinedhomedatatb = RedisUtil.hget(9, "userid" + paramMap.get("_KUID"), "userdefinedhomedatatb");
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
		}

		if (StringUtils.isBlank(userdefinedhomedatatb)) {
			paramMap.put("dataType", 1);
			paramMap.put("type", "1");// 除了导航栏的头部数据
			List<Map<String, Object>> userHomePageData = this.customHomePageDao
					.getUserCustomHomePageData_self(paramMap);
			result = JSONArray.parseArray(JSON.toJSONString(userHomePageData));
			JSONArray userNavData = this.getUserNavData(paramMap);
			JSONObject jSONObject = new JSONObject();
			jSONObject.put("nav", userNavData);
			result.add(jSONObject);
		} else {
			JSONObject parseObject = JSONObject.parseObject(userdefinedhomedatatb);
			JSONArray userNavData = this.getUserNavData(paramMap);
			parseObject.put("nav", userNavData);
			result.add(parseObject);
		}
		return result;
	}

	public JSONArray getUserNavData(Map<String, Object> paramMap) throws Exception {
		// 先查redis
		String userdefinedhomenavdata = "";
		try {
			userdefinedhomenavdata = RedisUtil.hget(9, "userid" + paramMap.get("_KUID"), "userdefinedhomenavdata");
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
		}

		// 然后个人库
		JSONArray userTbData = new JSONArray();
		if (StringUtils.isNotBlank(userdefinedhomenavdata)) {
			userTbData = JSONArray.parseArray(userdefinedhomenavdata);
		} else {
			paramMap.put("dataType", 1);
			paramMap.put("type", "nav");
			List<Map<String, Object>> userCustomHomePageData = this.customHomePageDao
					.getUserCustomHomePageData_self(paramMap);
			if (userCustomHomePageData == null || userCustomHomePageData.size() <= 0) {
				// 然后usernav,suerclassfy,subrelation（包括置顶专题，置顶分类）
				userCustomHomePageData = this.customHomePageDao.getUserNavData(paramMap);
				if (userCustomHomePageData == null || userCustomHomePageData.size() <= 0) {
					// 然后系统导航表
					userCustomHomePageData = this.customHomePageDao.getUserBarNewData(paramMap);
				}
				userTbData = JSONArray.parseArray(JSON.toJSONString(userCustomHomePageData));
			} else {
				Object object = userCustomHomePageData.get(0).get("KD_VALUE");
				userTbData = JSONArray.parseArray(object.toString());
			}
		}
		// 获取用户基本信息
		Map<String, Object> userInfo = this.customHomePageDao.getUserInfo(paramMap);
		List<Map<String, Object>> userBaseInfo = this.customHomePageDao.userBaseInfo(paramMap);
		for (Map<String, Object> map : userBaseInfo) {
			userInfo.put(map.get("type") + "", map.get("value") + "");
		}

		if (!"4".equals(userInfo.get("userSex") + "")) {
			this.delUserNavData(userTbData, "友商分析");
			this.delUserNavData(userTbData, "客户挖掘");
			this.delUserNavData(userTbData, "招标");
			this.delUserNavData(userTbData, "政策法规");
			this.delUserNavData(userTbData, "行业动态");
		}
		if (!"1".equals(userInfo.get("answer") + "") && !"1".equals(userInfo.get("weiXinCheck") + "")) {
			this.delUserNavData(userTbData, "社交监控");
		}
		// if (!"1".equals(userInfo.get("homeQuery") + "")) {
		this.delUserNavData(userTbData, "主页巡查");
		// }
		if (!"1".equals(userInfo.get("tvIsOpen") + "")) {
			this.delUserNavData(userTbData, "电视监控");
		}
		if (!"1".equals(userInfo.get("company") + "")) {
			this.delUserNavData(userTbData, "境外网站");
		}
		if (!"1".equals(userInfo.get("openSmallVideo") + "")) {
			this.delUserNavData(userTbData, "小视频");
		}
		// 报告模板
		this.choiceTemplate(userTbData, paramMap, userInfo);

		try {
			Date d1 = DateUtils.parseDate("20180503180000", new String[] { "yyyyMMddHHmmss" });
			Date d2 = DateUtils.parseDate(userInfo.get("regDate") + "", new String[] { "yyyyMMddHHmmss" });
			if (d2.getTime() > d1.getTime()) {
				this.delUserNavData(userTbData, "话题挖掘");
			} else {
                if ("1".equals(userInfo.get("KU_EVENTANALYSISSTATUS") + "")) {
					this.delUserNavData(userTbData, "事件分析");
				} else {
					this.delUserNavData(userTbData, "话题挖掘");
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// app
		if (!"1".equals(paramMap.get("app") + "")) {
			this.delUserNavData(userTbData, "关联站点");
		}
		for (int i = 0; i < userTbData.size(); i++) {
			@SuppressWarnings("unchecked")
			Map<String, Object> mapnav = (Map<String, Object>) userTbData.get(i);
			Object datamapnav = mapnav.get("appModuleImageUrl");
			if (datamapnav == null) {
				mapnav.put("appModuleImageUrl", "");
				mapnav.put("appModuleImageUpdateTime", "");
				mapnav.put("appModuleImageLastUpdateTime", "");
			}
		}

		return userTbData;
	}

	public JSONArray delUserNavData(JSONArray userTbData, String navName) {
		for (int i = 0; i < userTbData.size(); i++) {
			Map<?, ?> mapsdata = (Map<?, ?>) userTbData.get(i);
			String name = mapsdata.get("name").toString();
			if (name.equals(navName)) {
				userTbData.remove(i);
				--i;
			}
		}
		return userTbData;
	}

	/**
	 * 选择模板模块
	 * 
	 * @param list
	 */
	private void choiceTemplate(JSONArray userTbData, Map<String, Object> paramMap, Map<String, Object> userInfo) {
		try {
			String type = userInfo.get("userSex").toString();
			String IS_NEW_TEMPLATE = userInfo.get("IS_NEW_TEMPLATE") == null ? ""
					: userInfo.get("IS_NEW_TEMPLATE").toString();
			for (int i = 0; i < userTbData.size(); i++) {
				Map<?, ?> mapsdata = (Map<?, ?>) userTbData.get(i);
				String name = (String) mapsdata.get("name");
				if (!"4".equals(type) && !"0".equals(IS_NEW_TEMPLATE) && name.equals("网参报告")) {
					userTbData.remove(i);
					--i;
				} else if (("4".equals(type) || "0".equals(IS_NEW_TEMPLATE)) && name.equals("舆情报告")) {
					userTbData.remove(i);
					--i;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int restoreDefaultHeaderData(Map<String, Object> paraMap) {
		try {
			RedisUtil.delete(9, "userid" + paraMap.get("_KUID"), "userdefinedhomedatatb");
			RedisUtil.delete(9, "userid" + paraMap.get("_KUID"), "userdefinedhomenavdata");
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
		}
        // 清除置顶设置
        this.customHomePageDao.delUserNav(paraMap);
        this.customHomePageDao.updateUserClassfy(paraMap);
        this.customHomePageDao.updateSubrelation(paraMap);

		paraMap.put("type", 1); // 头部数据
		int result = this.customHomePageDao.restoreDefaultData_self(paraMap);
		return result;
	}

	@Override
	public int restoreDefaultTailData(Map<String, Object> paraMap) {
		try {
			RedisUtil.delete(9, "userid" + paraMap.get("_KUID"), "userdefinedhomedatawb");
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
		}

		paraMap.put("type", 2); // 尾部数据
		int result = this.customHomePageDao.restoreDefaultData_self(paraMap);
		return result;
	}

	@Override
	public JSONArray getCustomTailData(Map<String, Object> paramMap) {
		String userdefinedhomedatawb = "";
		try {
			userdefinedhomedatawb = RedisUtil.hget(9, "userid" + paramMap.get("_KUID"), "userdefinedhomedatawb");
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
		}

		JSONArray result = new JSONArray();
		if (StringUtils.isNotBlank(userdefinedhomedatawb)) {
			JSONObject parseObject = JSONObject.parseObject(userdefinedhomedatawb);
			result.add(parseObject);
		} else {
			paramMap.put("dataType", 2);// 尾部数据
			List<Map<String, Object>> userHomePageData = this.customHomePageDao
					.getUserCustomHomePageData_self(paramMap);
			if (userHomePageData != null && userHomePageData.size() > 0) {
				for (Map<String, Object> map : userHomePageData) {
					JSONObject parseObject = JSONObject.parseObject(map.get("KD_VALUE").toString());
					result.add(parseObject);
				}
			}
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getSourceTypeCount(Map<String, Object> paramMap) throws SysException {
		try {
			int userId = Integer.parseInt(paramMap.get(SysConstants._KUID) + "");
			List<Map<String, Object>> subjectList = this.customHomePageDao.getSubjectListByUserId(userId);
			paramMap.put("subjectList", subjectList);
			Date date = new Date();
			String startTime = DateUtil.DateToString(date, DateStyle.YYYYMMDD);
			String endTime = DateUtil.DateToString(DateUtil.addDay(date, 1), DateStyle.YYYYMMDD);
			paramMap.put("startTime", startTime + SysConstants.BTIME_HHMMSS);
			paramMap.put("endTime", endTime + SysConstants.BTIME_HHMMSS);
			List<Map<String, Object>> sourceTypeList = this.customHomePageDao.getSourceTypeCount_self(paramMap);
			for (SourceType st : SourceType.values()) {
				boolean b = true;
				for (Map<String, Object> sourceTypeMap : sourceTypeList) {
					String sourceType = (String) sourceTypeMap.get("sourceType");
					if (st.getType().equals(sourceType)) {
						sourceTypeMap.put("sourceTypeName", st.getSouceTypeName());
						b = false;
						break;
					}
				}
				if (b) {
					Map<String, Object> sourceTypeMap = new HashMap<String, Object>();
					sourceTypeMap.put("sourceType", st.getType());
					sourceTypeMap.put("sourceTypeName", st.getSouceTypeName());
					sourceTypeMap.put("positiveCount", 0);
					sourceTypeMap.put("negativeCount", 0);
					sourceTypeMap.put("neutralCount", 0);
					sourceTypeList.add(sourceTypeMap);
				}

			}
			return sourceTypeList;
		} catch (Exception e) {
			e.printStackTrace();
			this.logger.error(e.getMessage(), e);
			throw new SysException(e);
		}
	}

	@Override
	public List<Map<String, Object>> getSubjectByUserId(Map<String, Object> paramMap) throws SysException {
		try {
			int userId = Integer.parseInt(paramMap.get(SysConstants._KUID) + "");
			List<Map<String, Object>> subjectList = this.customHomePageDao.getSubjectListByUserId(userId);
			int subjectNum = Integer.parseInt(paramMap.get("subjectNum") + "");
			List<Map<String, Object>> result = new ArrayList<>();
			if (subjectList != null && subjectList.size() > 0) {
				for (int i = 0; i < subjectList.size(); i++) {
					if (i < subjectNum) {
						result.add(subjectList.get(i));
					}
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			this.logger.error(e.getMessage(), e);
			throw new SysException(e);
		}
	}

	@Override
	public int updateTailData(Map<String, Object> paramap) {
		paramap.put("type", 2); // 尾部数据
		this.customHomePageDao.restoreDefaultData_self(paramap);
		Object object = paramap.get("tailData");
		JSONObject tailData = JSONObject.parseObject(JSON.toJSONString(object));
		Map<String, Object> map = tailData;
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			paramap.put("dataType", entry.getKey());
			paramap.put("value", JSON.toJSONString(entry.getValue()));
			this.customHomePageDao.insertCustomData_self(paramap);
		}
		RedisUtil.hset(9, "userid" + paramap.get("_KUID"), "userdefinedhomedatawb", tailData.toString());
		return 1;
	}

	@Override
	public int updateHeaderData(Map<String, Object> paramap, Map<String, Object> map) {
		paramap.put("type", 1); // 头部数据
		// paramap.put("notImage", 1); // 头部数据
		this.customHomePageDao.restoreDefaultData_self(paramap);
		JSONObject object = new JSONObject();

		if (map.get("logo") == null || "".equals(map.get("logo") + "")) {
			object.put("name", "__PUBLIC__/images/comold/mslogo.png");
			map.put("logo", object.toString());
		}
		if (map.get("ioc") == null || "".equals(map.get("ioc") + "")) {
			object.put("name", "__PUBLIC__/images/iooc.ico");
			map.put("ioc", object.toString());
		}
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			paramap.put("dataType", entry.getKey());
			paramap.put("value", JSON.toJSONString(entry.getValue()));
			this.customHomePageDao.insertCustomData_self(paramap);
		}
		RedisUtil.hset(9, "userid" + paramap.get("_KUID"), "userdefinedhomenavdata", JSON.toJSONString(map.get("nav")));
		map.remove("nav", map.get("nav"));
		RedisUtil.hset(9, "userid" + paramap.get("_KUID"), "userdefinedhomedatatb", JSON.toJSON(map).toString());
		return 1;
	}

	@Override
	public PageInfo<Map<String, Object>> getHeadLinesList(Pagination pagination, String type) throws SysException {
		try {
			String name="";
			for (HeadLinesType headLinesType : HeadLinesType.values()) {
				if(headLinesType.getType().equals(type)) {
					name=headLinesType.getTypeName();
					break;
				}
			}
			return this.customHomePageDao.getHeadLinesList(pagination,name);
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
			throw new SysException(e);
		}
	}

	@Override
	public Map<String, Object> getCustomHomePageData(Map<String, Object> paramMap) {
		paramMap.put("kdType", "custom_home_page_data"); // 首页布局数据
		List<Map<String, Object>> userHomePageData = this.customHomePageDao.getUserCustomHomePageData_self(paramMap);
		Map<String, Object> result = new HashMap<>();
		if (userHomePageData != null && userHomePageData.size() > 0) {
			result.put("areaArr", userHomePageData.get(0).get("KD_VALUE"));

		} else {
			result.put("areaArr", "");
		}
		return result;
	}

	@Override
	public int updateHomePageData(Map<String, Object> paramap) {
		paramap.put("kdType", "custom_home_page_data"); // 首页布局数据
		List<Map<String, Object>> userHomePageData = this.customHomePageDao.getUserCustomHomePageData_self(paramap);
		paramap.put("dataType", "custom_home_page_data");
		paramap.put("value", paramap.get("homePageData"));
        paramap.put("type", 3);
		int result = 0;
		if (userHomePageData != null && userHomePageData.size() > 0) {
			result = this.customHomePageDao.updateCustomData_self(paramap);
		} else {
			result = this.customHomePageDao.insertCustomData_self(paramap);
		}

		return result;
	}

    @Override
    public Map<String, Object> getSubjectTree(Map<String, Object> paramMap) {
        List<Map<String, Object>> subjectTree = this.customHomePageDao.getSubjectTree(paramMap);
        List<Map<String, Object>> subjectTreeList = new ArrayList<>();
        if ("2".equals(paramMap.get("type") + "")) {
            subjectTreeList = this.getSonList("0", subjectTree, false);
        } else if ("1".equals(paramMap.get("type") + "")) {
            subjectTreeList = this.getList("0", subjectTree, false);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("subjectTree", subjectTreeList);
        return result;
    }

    private List<Map<String, Object>> getSonList(String pid, List<Map<String, Object>> list, boolean onlySon) {
        List<Map<String, Object>> sonlist = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> map : list) {
            if (map.get("pId").equals(pid)) {
                sonlist.add(map);
                if (!onlySon) {
                    List<Map<String, Object>> sublist = this.getSonList(map.get("id") + "", list, onlySon);
                    map.put("children", sublist);
                }
            }
        }
        return sonlist;
    }

    private List<Map<String, Object>> getList(String pid, List<Map<String, Object>> list, boolean onlySon) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> map : list) {
            if (map.get("pId").equals(pid)) {
                List<Map<String, Object>> sublist = this.getList(map.get("id") + "", list, onlySon);
                result.add(map);
                if (!onlySon) {
                    result.addAll(sublist);
                }
            }
        }
        return result;
    }

    @Override
    public int restoreHomePageData(Map<String, Object> paramap) {

        paramap.put("type", 3); // 首页自定义数据
        int result = this.customHomePageDao.restoreDefaultData_self(paramap);
        return result;
    }
}
