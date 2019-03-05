package com.zhxg.yqzj.service.impl.v1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.framework.base.utils.DateStyle;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.framework.base.utils.FreeMarkerUtil;
import com.zhxg.framework.base.utils.OrientationUtil;
import com.zhxg.framework.base.utils.PropertiesUtil;
import com.zhxg.framework.base.utils.SourceType;
import com.zhxg.framework.base.utils.SourceTypeUtil;
import com.zhxg.framework.base.utils.UploadFileUtil;
import com.zhxg.weibospread.AnalysisEventByWeibo;
import com.zhxg.weibospread.WeiboBean;
import com.zhxg.yqzj.dao.v1.EventAnalysisDao;
import com.zhxg.yqzj.dao.v1.EventAnalysisDataDao;
import com.zhxg.yqzj.entities.v1.EventAnalysis;
import com.zhxg.yqzj.entities.v1.EventAnalysisData;
import com.zhxg.yqzj.entities.v1.EventInfoWords;
import com.zhxg.yqzj.entities.v1.EventMedia;
import com.zhxg.yqzj.entities.v1.Line;
import com.zhxg.yqzj.entities.v1.ListCountInfo;
import com.zhxg.yqzj.entities.v1.Node;
import com.zhxg.yqzj.entities.v1.TopicWeiBoAuthor;
import com.zhxg.yqzj.entities.v1.WeiboEntity;
import com.zhxg.yqzj.service.util.DetailOperateUtil;
import com.zhxg.yqzj.service.v1.EventAnalysisDataService;

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
 * NameSpace: com.zhxg.yqms.service.impl.v1.LoginServiceImpl.java
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
public class EventAnalysisDataServiceImpl extends BaseServiceImpl<EventAnalysisData>
		implements EventAnalysisDataService {

	private final static int NUM = 0;
	private final static String STATUS = "1";
	private final static String STATUS2 = "2";
	private final static String CELEBRITY = "0,200,220";
	private final static String MEDIA = "4";
	private final static String GOVERNMENT = "1";
	private final static String INTEGRATE = "0,200,220,1,4,2,3,5,6,7,8";
	private final static String PIE = "pie";

	private final static int R = 11;
	private final static int r = 2;

	@Autowired
	private EventAnalysisDataDao eventAnalysisDao;
	
	@Autowired
    private EventAnalysisDao eventDao;

	@Override
	protected BaseDao<EventAnalysisData> getBaseDao() {
		return this.eventAnalysisDao;
	}

	@Override
	public Map<String, Object> getCount(Map<String, Object> paramMap) {
		Map<String, Object> countMap = this.eventAnalysisDao.getCount_other(paramMap);
		return countMap;
	}

	@Override
	public Map<String, Object> getSummary(Map<String, Object> paramMap) {
		Map<String, Object> summaryMap = this.eventAnalysisDao.getSummary_other(paramMap);
		return summaryMap;
	}
	
	private void setDefalutBeginTimeAndEndTime(String eventAnalysisId,Map<String, Object> paramMap){
	    try{
	        List<EventAnalysis> list = eventDao.selectEventAnalysisInfo(eventAnalysisId);
	        if(!list.isEmpty()){
	            EventAnalysis event = list.get(0);
	            if(!paramMap.containsKey("beginTime")||paramMap.get("beginTime")==null||StringUtils.isBlank(paramMap.get("beginTime").toString())){
	                paramMap.put("beginTime", event.getBeginTime());
	            }
	            if(!paramMap.containsKey("endTime")||paramMap.get("endTime")==null||StringUtils.isBlank(paramMap.get("endTime").toString())){
	                paramMap.put("endTime", event.getCloseTime());
	            }
	        }
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	    
	}

	@Override
	public PageInfo<EventAnalysisData> selectEventAnalysisDataList(Pagination pageInfo, Map<String, Object> paramMap) {
		// 媒体来源类型参数
		setParamSourceType(paramMap);
		// 设置自定义删选条件参数
		setParamCustomFilter(paramMap);

		PageInfo<EventAnalysisData> eventList = this.eventAnalysisDao.selectEventAnalysisDataList_self(paramMap,
				pageInfo);
		paramMap.remove("pageSize");
		paramMap.remove("pageNum");
		List<EventAnalysisData> list = eventList.getList();
		for (EventAnalysisData eventAnalysisData : list) {
			paramMap.put("infoSimhash", eventAnalysisData.getSimhash());
			Map<String, Integer> repeatCount = this.eventAnalysisDao.selectEventAnalysisrepeatCount_self(paramMap);
			if (repeatCount != null && repeatCount.size() > 0) {
				int repeat = Integer.parseInt(repeatCount.get("repeatCount") + "");
				eventAnalysisData.setRepeatCount(repeat);
			} else {
				eventAnalysisData.setRepeatCount(NUM);
			}

		}
		return eventList;
	}

	@Override
	public int selectMaxId(Map<String, Object> paramMap) {
		return eventAnalysisDao.selectMaxId_self(paramMap);
	}

	@Override
	public List<EventAnalysisData> getFirstMediaInfo(Map<String, Object> paramMap) {
	    setDefalutBeginTimeAndEndTime(paramMap.get("eventId").toString(),paramMap);
		List<EventAnalysisData> eventList = this.eventAnalysisDao.getFirstMediaInfo_other(paramMap);
		List<EventAnalysisData> resultList = new ArrayList<>();
		// 媒体来源去重
		Set<String> sourceTypeSet = new HashSet<>();
		for (EventAnalysisData event : eventList) {
			if (sourceTypeSet.add(event.getSourceType())) {
				resultList.add(event);
			}
		}
		return resultList;
	}

	@Override
	public List<Map<String, Object>> getWordsCloud(Map<String, Object> paramMap) {
		// 查询词云图
		List<Map<String, Object>> result = this.eventAnalysisDao.getWordsCloud_other(paramMap);
		if (null != result) {
			for (Map<String, Object> map : result) {
				map.put("date", DateUtil.StringToString(String.valueOf(paramMap.get("beginTime")),
						DateStyle.YYYYMMDDHHMMSS, DateStyle.MM_DD_CN));
			}
		}
		return result;
	}

	@Override
	public List<EventAnalysisData> getEventVein(Map<String, Object> paramMap) {
	    setDefalutBeginTimeAndEndTime(paramMap.get("eventId").toString(),paramMap);
		List<EventAnalysisData> eventList = this.eventAnalysisDao.getEventVein_other(paramMap);
//		for (EventAnalysisData eventAnalysisData : eventList) {
//			paramMap.put("infoSimhash", eventAnalysisData.getSimhash());
//			Map<String, Integer> repeatCount = this.eventAnalysisDao.selectEventAnalysisrepeatCount_other(paramMap);
//			if (repeatCount != null && repeatCount.size() > 0) {
//				int repeat = Integer.parseInt(repeatCount.get("repeatCount") + "");
//				eventAnalysisData.setRepeatCount(repeat);
//			} else {
//				eventAnalysisData.setRepeatCount(NUM);
//			}
//		}
		List<EventAnalysisData> firstList = this.eventAnalysisDao.getFirstInfo_other(paramMap);// 首发信息
		List<EventAnalysisData> resultList = new ArrayList<>();
		// simhash去重
		Set<String> simHashTypeSet = new HashSet<>();
		for (EventAnalysisData event : eventList) {
			if (simHashTypeSet.add(event.getSimhash())) {
				resultList.add(event);
			}
		}
		firstList.addAll(resultList);
		return firstList;
	}

	@Override
	public int modifyModuleSummary(Map<String, Object> paramMap) {
		return this.eventAnalysisDao.modifyModuleSummary(paramMap);
	}
	
    @Override
    public Map<String, Object> getSourceTypeTrendByInterval(Map<String, Object> paramMap) {
        Map<String, Object> result = new HashMap<>();
	    String groupType = paramMap.get("interval").toString();
	    setDefalutBeginTimeAndEndTime(paramMap.get("eventId").toString(),paramMap);
	    int interval = Calendar.DAY_OF_MONTH;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            Calendar beginTime = Calendar.getInstance();
            beginTime.setTime(sdf.parse(paramMap.get("beginTime") + ""));
            Calendar endTime = Calendar.getInstance();
            endTime.setTime(sdf.parse(paramMap.get("endTime") + ""));
            
            beginTime.set(Calendar.MILLISECOND, 0);
            beginTime.set(Calendar.SECOND, 0);
            beginTime.set(Calendar.MINUTE, 0);
            endTime.set(Calendar.MILLISECOND, 0);
            endTime.set(Calendar.SECOND, 0);
            endTime.set(Calendar.MINUTE, 0);
        
            Calendar oneMonthBefore = Calendar.getInstance();//当前时间
            if (endTime.before(oneMonthBefore)) {
                oneMonthBefore.setTime(sdf.parse(paramMap.get("endTime") + ""));
            } else {
                endTime = Calendar.getInstance();
            }
            if(groupType.equals("hour")){
                sdf2 = new SimpleDateFormat("yyyy-MM-dd HH");
                interval = Calendar.HOUR_OF_DAY;
            }else{
                beginTime.set(Calendar.HOUR_OF_DAY, 0);
                endTime.set(Calendar.HOUR_OF_DAY, 0);
            }
            
            List<String> xAxis = new ArrayList<>();
            
            while (!beginTime.after(endTime)) {
                String date = sdf2.format(beginTime.getTime());
                if(!xAxis.contains(date)){
                    xAxis.add(date);
                }
                beginTime.add(interval, 1);
            }
            Map<String,int[]> series = new LinkedHashMap<>();
            series.put("全部", new int[xAxis.size()]);
            SourceType[] ss = SourceType.values();
            for(SourceType s:ss){
                series.put(s.getSouceTypeName(), new int[xAxis.size()]);
            }
            
            List<Map<String, Object>> list = this.eventAnalysisDao.getSourceTypeTrendByInterval_other(paramMap);
            for (Map<String, Object> data : list) {
                String key = SourceTypeUtil.getSourceName(data.get("KN_TYPE").toString());
                int index = xAxis.indexOf(data.get("ctime").toString());
                series.get(key)[index] = Integer.parseInt(data.get("count").toString());
            }
            
            result.put("xAxis", xAxis);
            result.put("series", series);
            
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    return result;
	}
    
    
    @Override
    public Map<String, Object> getWeiboOrientationStatisticsByInterval(Map<String, Object> paramMap) {
        Map<String, Object> result = new HashMap<>();
        String groupType = paramMap.get("interval").toString();
        setDefalutBeginTimeAndEndTime(paramMap.get("eventId").toString(),paramMap);
        int interval = Calendar.DAY_OF_MONTH;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            Calendar beginTime = Calendar.getInstance();
            beginTime.setTime(sdf.parse(paramMap.get("beginTime") + ""));
            Calendar endTime = Calendar.getInstance();
            endTime.setTime(sdf.parse(paramMap.get("endTime") + ""));
            beginTime.set(Calendar.MILLISECOND, 0);
            beginTime.set(Calendar.SECOND, 0);
            beginTime.set(Calendar.MINUTE, 0);
            endTime.set(Calendar.MILLISECOND, 0);
            endTime.set(Calendar.SECOND, 0);
            endTime.set(Calendar.MINUTE, 0);
        
            Calendar oneMonthBefore = Calendar.getInstance();//当前时间
            if (endTime.before(oneMonthBefore)) {
                oneMonthBefore.setTime(sdf.parse(paramMap.get("endTime") + ""));
            } else {
                endTime = Calendar.getInstance();
            }
            if(groupType.equals("hour")){
                sdf2 = new SimpleDateFormat("yyyy-MM-dd HH");
                interval = Calendar.HOUR_OF_DAY;
            }else{
                beginTime.set(Calendar.HOUR_OF_DAY, 0);
                endTime.set(Calendar.HOUR_OF_DAY, 0);
            }    
            List<String> xAxis = new ArrayList<>();
            while (!beginTime.after(endTime)) {
                String date = sdf2.format(beginTime.getTime());
                if(!xAxis.contains(date)){
                    xAxis.add(date);
                }
                beginTime.add(interval, 1);
            }
            Map<String,int[]> series = new HashMap<>();
            series.put("正面", new int[xAxis.size()]);
            series.put("负面", new int[xAxis.size()]);
            series.put("中性", new int[xAxis.size()]);
            
            List<Map<String, Object>> list = this.eventAnalysisDao.getWeiboOrientationStatisticsByInterval_other(paramMap);
            for (Map<String, Object> data : list) {
                String key = data.get("orientation").toString();
                int index = xAxis.indexOf(data.get("ctime").toString());
                series.get(key)[index] = Integer.parseInt(data.get("count").toString());
            }
            result.put("xAxis", xAxis);
            result.put("series", series);
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
    

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getSourceTypeTrend(Map<String, Object> paramMap) {
	    setDefalutBeginTimeAndEndTime(paramMap.get("eventId").toString(),paramMap);
		List<Map<String, Object>> list = this.eventAnalysisDao.getSourceTypeTrend_other(paramMap);
		Set<String> sourceTypeSet = new LinkedHashSet<>();
		sourceTypeSet.add("全部");
		Map<String, Map<String, Map<String, String>>> map = new HashMap<>();
		for (Map<String, Object> data : list) {
			sourceTypeSet.add(SourceTypeUtil.getSourceName(data.get("KN_TYPE").toString()));
			if (map.get(SourceTypeUtil.getSourceName(data.get("KN_TYPE").toString())) == null) {
				map.put(SourceTypeUtil.getSourceName(data.get("KN_TYPE").toString()),
						new HashMap<String, Map<String, String>>());
			}
			String date = data.get("ctime").toString().substring(0, 8);
			String hour = data.get("ctime").toString().substring(8, 10);
			Map<String, Map<String, String>> subMap = map
					.get(SourceTypeUtil.getSourceName(data.get("KN_TYPE").toString()));
			if (subMap.get(date) == null) {
				subMap.put(date, new HashMap<String, String>());
			}
			Map<String, String> subSubMap = subMap.get(date);
			subSubMap.put(hour, data.get("count").toString());
		}
		List<Map<String, Object>> result = new ArrayList<>();
		for (String sourceType : sourceTypeSet) {
			Map<String, Object> sourceTypeJson = new HashMap<>();
			sourceTypeJson.put("sourceType", sourceType);
			result.add(sourceTypeJson);
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
			Calendar beginTime = Calendar.getInstance();
			beginTime.setTime(sdf.parse(paramMap.get("beginTime") + ""));
			Calendar endTime = Calendar.getInstance();
			endTime.setTime(sdf.parse(paramMap.get("endTime") + ""));
			Calendar oneMonthBefore = Calendar.getInstance();//当前时间
			if (endTime.before(oneMonthBefore)) {
			    oneMonthBefore.setTime(sdf.parse(paramMap.get("endTime") + ""));
			} else {
			    endTime = Calendar.getInstance();
			}
			//将开始时间限定在一年前
//			oneMonthBefore.add(Calendar.MONTH, -12);
//			if (beginTime.before(oneMonthBefore)) {
//			    beginTime = oneMonthBefore;
//			}
			Map<String, Map<String, Object>> tempMap = new HashMap<>();
			boolean hasHours = false;
			if(endTime.getTimeInMillis()-beginTime.getTimeInMillis()<=3600*24*1000){
			    hasHours = true;
			}
			while (beginTime.before(endTime)) {
				for (Map<String, Object> sourceTypeJson : result) {// 01
					int all = 0;
					int count = 0;
					List<Map<String, Object>> dataList = null;
					if (sourceTypeJson.get("data") == null) {
						dataList = new ArrayList<>();
						sourceTypeJson.put("data", dataList);
					} else {
						dataList = (List<Map<String, Object>>) sourceTypeJson.get("data");
					}
					String date = sdf2.format(beginTime.getTime());// 20180308
					Map<String, Object> dataMap = tempMap.get(sourceTypeJson.get("sourceType") + date);
					if (dataMap == null) {
						count = 0;
						dataMap = new HashMap<String, Object>();
						tempMap.put(sourceTypeJson.get("sourceType") + date, dataMap);
						dataList.add(dataMap);
					}
					dataMap.put("date", date);
					List<Map<String, Object>> hourList = new ArrayList<>();
					for (int h = 0; h < 24; h++) {
						Map<String, Object> hourData = new HashMap<>();
						String hour = h < 10 ? "0" + h : h + "";
						int num = 0;
						try {
							num = Integer.parseInt(map.get(sourceTypeJson.get("sourceType")).get(date).get(hour));
							count += num;
							all += num;
						} catch (Exception e) {
							// System.out.println("debug cannot find num!");
						}
						hourData.put("hour", hour + ":00");
						hourData.put("num", num);
						hourList.add(hourData);
					}
					if(hasHours){
					    dataMap.put("hours", hourList);
					}
					dataMap.put("count", count);
					sourceTypeJson.put("count", all);
				}
				beginTime.add(Calendar.HOUR, 1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(JSON.toJSONString(result));
		return result;

	}

	@Override
	public List<Map<String, Object>> getOrientationStatistics(Map<String, Object> paramMap) {
	    setDefalutBeginTimeAndEndTime(paramMap.get("eventId").toString(),paramMap);
		return this.eventAnalysisDao.getOrientationStatistics_other(paramMap);
	}

	@Override
	public List<Map<String, Object>> getMediaAnalysis(Map<String, Object> paramMap) {
	    setDefalutBeginTimeAndEndTime(paramMap.get("eventId").toString(),paramMap);
		List<Map<String, Object>> list = this.eventAnalysisDao.getMediaAnalysis_other(paramMap);
		for (Map<String, Object> map : list) {
			map.put("sourceTypeName", SourceTypeUtil.getSourceName(map.get("sourceType").toString()));
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getWebNameAnalysis(Map<String, Object> paramMap) {
	    setDefalutBeginTimeAndEndTime(paramMap.get("eventId").toString(),paramMap);
		return this.eventAnalysisDao.getWebNameAnalysis_other(paramMap);
	}

	@Override
    public Map<String, Object> exportWebName(Map<String, Object> paramMap) {
		String filename = UUID.randomUUID() + ".xlsx";
		try {
			List<Map<String, Object>> list = this.eventAnalysisDao.exportWebName_other(paramMap);
			for (Map<String, Object> map : list) {
				map.put("sourceType", SourceTypeUtil.getSourceName(map.get("sourceType").toString()));
			}
			String rootPath = this.getClass().getClassLoader().getResource("").getPath();
			Context context = new Context();
			context.putVar("dataList", list);
			context.putVar("index", 0);
			FileInputStream is = new FileInputStream(rootPath + "../../template/webname.xlsx");
			OutputStream os = UploadFileUtil.getInstance().getOut(filename);
			JxlsHelper jxlsHelper = JxlsHelper.getInstance();
			Transformer transformer = jxlsHelper.createTransformer(is, os);
			jxlsHelper.processTemplate(context, transformer);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String remotePath = "http://" + PropertiesUtil.getValue("file.server.host") + "/" + filename;
		JSONObject json = new JSONObject();
		json.put("path", remotePath);

		return json;
	}

	@Override
	public PageInfo<EventAnalysisData> selectExportDataList(Pagination pageInfo, Map<String, Object> paramMap) {
		return this.eventAnalysisDao.selectExportDataList_other(paramMap, pageInfo);
	}

	@Override
	public Map<String, Object> getSummaryByModuleId(Map<String, Object> paramMap) {
	    setDefalutBeginTimeAndEndTime(paramMap.get("eventId").toString(),paramMap);
		// 按模块获取默认摘要
		Map<String, Object> result = this.eventAnalysisDao.getSummaryByModuleId(paramMap);
		// 如未查询到默认摘要，则按照各模块规则生成摘要信息
		if (null == result || StringUtils.isBlank(String.valueOf(result.get("summary")))) {
			Map<String, Object> countResult = new HashMap<>();
			result = new HashMap<>();
			StringBuffer summary = new StringBuffer();
			int moduleId = Integer.valueOf(String.valueOf(paramMap.get("moduleId")));
			if (4 == moduleId) {
				// 获取发展趋势模块摘要
				countResult = this.eventAnalysisDao.getTrendSummary_other(paramMap);
				if (null != countResult) {
					// 格式化信息最多时间
					String mostDay = DateUtil.StringToString(String.valueOf(countResult.get("mostTime")),
							DateStyle.YYYYMMDD, DateStyle.YYYY_MM_DD);
					// 信息最大量
					String allCount = String.valueOf(countResult.get("allCount"));
					// 转换媒体类型
					String sourceTypeName = SourceTypeUtil.getSourceName(String.valueOf(countResult.get("sourceType")));
					// 最高媒体信息量
					String mostCount = String.valueOf(countResult.get("sourceTypeCount"));
					summary.append("在舆情发展趋势中，该事件舆论信息最多出现在");
					summary.append(mostDay);
					summary.append("，当日共产生");
					summary.append(allCount);
					summary.append("条舆情信息，其中");
					summary.append(sourceTypeName);
					summary.append("平台最为突出，最高达到");
					summary.append(mostCount);
					summary.append("条传播量，成为该事件的主要传播媒体。");
				}
			} else if (5 == moduleId) {
				// 获取事件的倾向性统计结果
				countResult = this.eventAnalysisDao.getOrientationSummary_other(paramMap);

				// 按倾向性数据量排序，正序排列
				// orientationResult = sortInOrientationList(orientationResult);
				// 得到倾向性数据量占比最高的信息
				// countResult = orientationResult.get(orientationResult.size()
				// - 1);

				// 根据倾向性统计舆情的媒体类型
				if (countResult != null) {
					paramMap.put("orientation", String.valueOf(countResult.get("mostOri")));

					List<Map<String, Object>> searcResult = this.eventAnalysisDao
							.getOrientationSourceType_other(paramMap);

					// 转换倾向性
					String orientation = OrientationUtil.getSourceName(String.valueOf(countResult.get("mostOri")));

					// 倾向性最多数据量的日期
					// String mostDay =
					// DateUtil.StringToString(String.valueOf(countResult.get("mostDay")),
					// DateStyle.YYYYMMDD, DateStyle.YYYY_MM_DD);
					// 倾向性最多数据量
					int negativeNum = Integer.valueOf(String.valueOf(countResult.get("mostCount")));

					// 最多的媒体类型
					String sourceTypeName = SourceTypeUtil
							.getSourceName(String.valueOf(searcResult.get(searcResult.size() - 1).get("sourceType")));
					// 最少的媒体类型
					String leastSourceTypeName = SourceTypeUtil
							.getSourceName(String.valueOf(searcResult.get(0).get("sourceType")));

					// 共有舆情数
					int allCount = Integer.valueOf(String.valueOf(searcResult.get(0).get("allCount")));

					// 计算比例
					// DecimalFormat df = new DecimalFormat("0.00");
					// String num = df.format(((float) negativeNum / allCount) *
					// 100);

					summary.append("在该事件发展进程中，");
					summary.append(orientation);
					summary.append("信息占据主阵营。");
					// summary.append(orientation);
					// summary.append("舆情量最多在");
					// summary.append(mostDay);
					// summary.append("，占比");
					// summary.append(num);
					// summary.append("%，其中，");
					// summary.append(sourceTypeName);
					// summary.append("是涉及");
					// summary.append(orientation);
					// summary.append("舆情最多的媒体，最少的是");
					// summary.append(leastSourceTypeName);
					// summary.append("平台，后续在事件的发展可重点关注");
					// summary.append(sourceTypeName);
					// summary.append("类型平台。");
				}

			} else if (6 == moduleId) {
				// 获取关键词云模块摘要，摘要获取top5即可
				paramMap.put("top", 5);
				List<Map<String, Object>> countResults = this.eventAnalysisDao.getWordsCloud_other(paramMap);
				if (null != countResults) {
					summary.append("通过对关键词云的分析，可以发现媒体和网民对于该事件主要关注");
					for (int i = 0; i < countResults.size(); i++) {
						Map<String, Object> map = countResults.get(i);
						summary.append(map.get("word"));
						if (i != (countResults.size() - 1)) {
							summary.append("、");
						}
					}
					summary.append("等信息。");
				}
			} else if (8 == moduleId) {
				// 获取媒体分析模块摘要
				countResult = this.eventAnalysisDao.getMediaAnalysisSummary_other(paramMap);
				if (null != countResult) {
					// 总数量
					int allCount = Integer.valueOf(String.valueOf(countResult.get("allCount")));
					// 最多的媒体类型
					String sourceTypeName = SourceTypeUtil
							.getSourceName(String.valueOf(countResult.get("mostSourceType")));
					// 最多媒体类型信息量
					int mostCount = Integer.valueOf(String.valueOf(countResult.get("mostCount")));
					// 计算比例
					DecimalFormat df = new DecimalFormat("0.00");
					String num = df.format(((float) mostCount / allCount) * 100);

					summary.append("从舆情媒体分析，各类媒体报道总数量为");
					summary.append(allCount);
					summary.append("，其中");
					summary.append(sourceTypeName);
					summary.append("占比为");
					summary.append(num);
					summary.append("%，在媒体中占比最高。");
				}
			} else if (7 == moduleId) {
				// 概述模块
				String summary7 = "";
				try {
					paramMap.put("uuid", paramMap.get("eventId"));
					paramMap.put(PIE, PIE);
					paramMap.put("title", "title");
					List<TopicWeiBoAuthor> list = this.eventAnalysisDao.getTableDataCount_other(paramMap);
					paramMap.remove("title");
					paramMap.remove(PIE);
					if (list.size() > 0) {
						int total = 0;
						StringBuffer sb = new StringBuffer();
						for (int i = 0; i < list.size(); i++) {
							TopicWeiBoAuthor topicWeiBoAuthor = list.get(i);
							total += topicWeiBoAuthor.getCount();
							if (i + 1 == list.size()) {
								sb.append(topicWeiBoAuthor.getType() + "为" + topicWeiBoAuthor.getCount() + "。");
							} else {
								sb.append(topicWeiBoAuthor.getType() + "为" + topicWeiBoAuthor.getCount() + "；");
							}
						}
						summary7 = "在微博分析中了解到，" + list.get(0).getType() + "参与影响力最大，传播受众人数为 " + total + "，其中，"
								+ sb.toString();
					} else {
						summary7 = "";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				summary.append(summary7);
			} else if (1 == moduleId) {
				// 概述模块
				Map<String, Object> summaryOther = this.eventAnalysisDao.getSummary_other(paramMap);
				String summarynew = "";
				if (summaryOther != null && !"".equals(summaryOther)) {
					summarynew = summaryOther.get("summary").toString().replaceAll("\\s", " ");
					if(summarynew.length()>200){
					    summarynew = summarynew.substring(0, 200);
					}
				}
				summary.append(summarynew);
			} else {
				// 其他模块陆续支持
			}
			result.put("summary", summary.toString().trim());
		}
		return result;
	}

	public static List<Map<String, Object>> sortInOrientationList(List<Map<String, Object>> list) {
		List<Map<String, Object>> newList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> newMap = new HashMap<>();
			Map<String, Object> map = list.get(i);
			int positive = Integer.valueOf(String.valueOf(map.get("positive")));
			int negative = Integer.valueOf(String.valueOf(map.get("negative")));
			int neutral = Integer.valueOf(String.valueOf(map.get("neutral")));
			String day = String.valueOf(map.get("date"));
			if (positive >= negative && positive >= neutral) {
				newMap.put("mostOri", "1");
				newMap.put("mostCount", positive);
			} else if (negative >= positive && negative >= neutral) {
				newMap.put("mostOri", "2");
				newMap.put("mostCount", negative);
			} else if (neutral >= positive && neutral >= negative) {
				newMap.put("mostOri", "3");
				newMap.put("mostCount", neutral);
			}
			newMap.put("mostDay", day);
			newList.add(newMap);
		}
		Collections.sort(newList, new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				int map1value = Integer.valueOf(String.valueOf(o1.get("mostCount")));
				int map2value = Integer.valueOf(String.valueOf(o2.get("mostCount")));
				return map1value - map2value;
			}
		});
		return newList;
	}

	@Override
	public EventAnalysisData selectEventAnalysisDataDetail(Map<String, Object> paramMap) {
	    EventAnalysisData eventAnalysisData = null;
	    if(paramMap.get("_OTHER_DBNAME") != null){
	        eventAnalysisData = this.eventAnalysisDao.selectEventAnalysisDataDetail_other(paramMap);
        }else{
            eventAnalysisData = this.eventAnalysisDao.selectEventAnalysisDataDetail_self(paramMap);
        }
		eventAnalysisData.setContent(DetailOperateUtil.changeImgToHtml(eventAnalysisData.getContent(),null));
		return eventAnalysisData;
	}

	@Override
	public List<TopicWeiBoAuthor> getNavyAnalysisCount(Map<String, Object> paramMap) {
	    setDefalutBeginTimeAndEndTime(paramMap.get("uuid").toString(),paramMap);
		try {
			return this.eventAnalysisDao.getNavyAnalysisCount_other(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<TopicWeiBoAuthor> getEmotionAnalysisCount(Map<String, Object> paramMap) {
	    setDefalutBeginTimeAndEndTime(paramMap.get("uuid").toString(),paramMap);
		try {
			return this.eventAnalysisDao.getEmotionAnalysisCount_other(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, Object> getInfluenceRankingList(Map<String, Object> paramMap) {
	    setDefalutBeginTimeAndEndTime(paramMap.get("uuid").toString(),paramMap);
		try {
			Map<String, Object> map = new HashMap<>();
			paramMap.put("verifiedType", INTEGRATE);
			map.put("integrateList", this.eventAnalysisDao.getInfluenceRankingList_other(paramMap));
			paramMap.put("verifiedType", GOVERNMENT);
			map.put("governmentList", this.eventAnalysisDao.getInfluenceRankingList_other(paramMap));
			paramMap.put("verifiedType", MEDIA);
			map.put("mediaList", this.eventAnalysisDao.getInfluenceRankingList_other(paramMap));
			paramMap.put("verifiedType", CELEBRITY);
			map.put("celebrityList", this.eventAnalysisDao.getInfluenceRankingList_other(paramMap));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paramMap;
	}

	@Override
	public Map<String, Object> getMicroBlogAnalysisCount(Map<String, Object> paramMap) {
	    setDefalutBeginTimeAndEndTime(paramMap.get("uuid").toString(),paramMap);
		try {
			Map<String, Object> map = new HashMap<>();
			paramMap.put("fansCount", 500000);
			map.put("fivehundredThousand", this.eventAnalysisDao.getTableDataCount_other(paramMap));
			paramMap.put("fansCount", 1000000);
			map.put("oneMillion", this.eventAnalysisDao.getTableDataCount_other(paramMap));
			paramMap.put("fansCount", 10000000);
			map.put("tenMillion", this.eventAnalysisDao.getTableDataCount_other(paramMap));
			paramMap.put(PIE, PIE);
			map.put("pieCount", this.eventAnalysisDao.getTableDataCount_other(paramMap));
			paramMap.remove(PIE);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paramMap;
	}

	@Override
	public List<Map<String, Object>> selectCountGroupBySourceType(Map<String, Object> paramMap) {
	    setDefalutBeginTimeAndEndTime(paramMap.get("eventAnalysisId").toString(),paramMap);
		// 自定义条件
		setParamCustomFilter(paramMap);

		return this.eventAnalysisDao.selectCountGroupBySourceType_self(paramMap);
	}

	@Override
	public List<Map<String, Object>> selectCountGroupByWeiBo(Map<String, Object> paramMap) {
	    setDefalutBeginTimeAndEndTime(paramMap.get("eventAnalysisId").toString(),paramMap);
		// 自定义条件
		// setParamCustomFilter(paramMap);

		return this.eventAnalysisDao.selectCountGroupByWeiBo_self(paramMap);
	}

	@Override
	public List<Map<String, Object>> selectCountGroupByOrientation(Map<String, Object> paramMap) {
	    setDefalutBeginTimeAndEndTime(paramMap.get("eventAnalysisId").toString(),paramMap);
		// 媒体来源类型参数
		setParamST(paramMap);
		// 自定义条件
		setParamCustomFilter(paramMap);

		return this.eventAnalysisDao.selectCountGroupByOrientation_self(paramMap);
	}

	/**
	 * 媒体来源类型参数
	 * 
	 * @param paramMap
	 */
	private void setParamSourceType(Map<String, Object> paramMap) {
		List<String> sourcetypeList = new ArrayList<>();
		if (paramMap.get("sourcetype") != null && !"".equals(paramMap.get("sourcetype"))) {
			String sourcetype = paramMap.get("sourcetype") == null ? "" : paramMap.get("sourcetype").toString();
			String[] split = sourcetype.split(",");
			for (String source : split) {
				sourcetypeList.add(source);
			}
		}

		if (!sourcetypeList.isEmpty()) {
			paramMap.put("sourcetype", sourcetypeList);
		} else {
			paramMap.put("sourcetype", null);
		}

	}

	/**
	 * 媒体来源类型参数
	 * 
	 * @param paramMap
	 */
	private void setParamST(Map<String, Object> paramMap) {
		List<String> sourcetypeList = new ArrayList<>();
		List<String> weiBoList = new ArrayList<>();
		if (paramMap.get("sourcetype") != null && !"".equals(paramMap.get("sourcetype"))) {
			String sourcetype = paramMap.get("sourcetype") == null ? "" : paramMap.get("sourcetype").toString();
			String[] split = sourcetype.split(",");
			for (String source : split) {
				if ("04".equals(source) || "08".equals(source)) {
					weiBoList.add(source);
				} else {
					sourcetypeList.add(source);
				}
			}
		}

		if (!sourcetypeList.isEmpty()) {
			paramMap.put("sourcetype", sourcetypeList);
		} else {
			paramMap.put("sourcetype", "");
		}

		if (!weiBoList.isEmpty()) {
			paramMap.put("weiBoList", weiBoList);
		} else {
			paramMap.put("weiBoList", "");
		}
	}

	/**
	 * 设置自定义删选条件参数
	 * 
	 * @param paramMap
	 */
	private void setParamCustomFilter(Map<String, Object> paramMap) {
		// 自定义筛选
		StringBuffer stms = new StringBuffer();
		String customFilter = paramMap.get("customFilter") == null ? "" : paramMap.get("customFilter").toString();
		if (customFilter != null && !customFilter.equals("")) {
			String[] customFilters = customFilter.split(",");
			for (int i = 0; i < customFilters.length; i++) {
				paramMap.put("customFiltersId", customFilters[i]);
				Map<String, Object> map = this.eventAnalysisDao.selectCustomCondition(paramMap);
				if (map != null && map.size() > 0) {
					String field = map.get("field").toString();
					String searchtype = map.get("searchtype").toString();
					List<Map<String, Object>> listValue = this.eventAnalysisDao.selectCustomConditionValue(paramMap);
					if (listValue != null && listValue.size() > 0) {
						stms.append(" and (");
						for (int j = 0; j < listValue.size(); j++) {
							Map<String, Object> mapValue = listValue.get(j);
							String value = mapValue.get("value").toString();
							if (searchtype.equals(STATUS)) {
								stms.append(field + " LIKE '%" + value + "%'");
							} else if (searchtype.equals(STATUS2)) {
								stms.append(field + " ='" + value + "'");
							}
							if (j != (listValue.size() - 1)) {
								stms.append(" or ");
							}
						}
						stms.append(" )");
					} else {
						stms.append("and (" + STATUS + "=" + STATUS2 + ")");
					}
				}
			}
		}
		paramMap.put("customFilter", stms.toString());
	}

	@Override
	public Map<String, Object> getKeyPointMicroBlogList(Map<String, Object> paramMap) {
	    setDefalutBeginTimeAndEndTime(paramMap.get("uuid").toString(),paramMap);
		try {
			Map<String, Object> map = new HashMap<>();
			paramMap.put("verifiedType", INTEGRATE);
			map.put("integrateList", this.eventAnalysisDao.getKeyPointMicroBlogList_other(paramMap));
			paramMap.put("verifiedType", GOVERNMENT);
			map.put("governmentList", this.eventAnalysisDao.getKeyPointMicroBlogList_other(paramMap));
			paramMap.put("verifiedType", MEDIA);
			map.put("mediaList", this.eventAnalysisDao.getKeyPointMicroBlogList_other(paramMap));
			paramMap.put("verifiedType", CELEBRITY);
			map.put("celebrityList", this.eventAnalysisDao.getKeyPointMicroBlogList_other(paramMap));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paramMap;
	}

	@Override
	public Map<String, Object> selectSameEventAnalysisDataList(Map<String, Object> paramMap) {
	    setDefalutBeginTimeAndEndTime(paramMap.get("eventAnalysisId").toString(),paramMap);
		List<EventAnalysisData> selectSameEventAnalysisDataList = this.eventAnalysisDao
				.selectSameEventAnalysisDataList_self(paramMap);
		EventAnalysisData firstWebName = this.eventAnalysisDao.selectFirstWebName_self(paramMap);
		paramMap.put("infoSimhash", paramMap.get("simhash"));
		Map<String, Integer> repeatCount = this.eventAnalysisDao.selectEventAnalysisrepeatCount_self(paramMap);
		Map<String, Object> map = new HashMap<>();
		map.put("sameEventAnalysisData", selectSameEventAnalysisDataList);
		map.put("firstWebName", firstWebName);
		map.put("repeatCount", repeatCount);
		return map;
	}

	@Override
	public List<TopicWeiBoAuthor> getBloggerRegionList(Map<String, Object> paramMap) {
	    setDefalutBeginTimeAndEndTime(paramMap.get("uuid").toString(),paramMap);
		try {
			return this.eventAnalysisDao.getBloggerRegionList_other(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PageInfo<EventAnalysisData> selectSameExportDataList(Pagination pageInfo, Map<String, Object> paramMap) {
	    setDefalutBeginTimeAndEndTime(paramMap.get("eventAnalysisId").toString(),paramMap);
		return this.eventAnalysisDao.selectSameExportDataList_self(paramMap, pageInfo);
	}

	/**
	 * 自行计算树结构-递归添加子节点
	 * 
	 * @param weibo
	 * @param list
	 * @return
	 */
	private boolean setChiledren(WeiboEntity weibo, List<WeiboEntity> list) {
		boolean result = false;
		for (WeiboEntity p : list) {
			if (p.getUrl().equals(weibo.getRetweeted_status_url())) {
				p.getChildrens().add(weibo);
				return true;
			}
			result = setChiledren(weibo, p.getChildrens());
		}
		return result;
	}

	/**
	 * 自行计算树结构
	 * 
	 * @param list
	 *            源列表
	 * @param rootlist
	 *            目标列表
	 * @return List<WeiboEntity>
	 */
	private List<WeiboEntity> frushList(List<WeiboEntity> list, List<WeiboEntity> rootlist) {
		if (list.isEmpty()) {
			return rootlist;
		}
		for (int i = 0; i < list.size(); i++) {
			WeiboEntity weibo = list.get(i);
			if (StringUtils.isEmpty(weibo.getRetweeted_status_url())) {
				rootlist.add(weibo);
				list.remove(i);
				i--;
			} else {
				setChiledren(weibo, rootlist);
				list.remove(i);
				i--;
			}
		}
		return rootlist;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getPropagatePath(Map<String, Object> paramMap) throws SysException {
	    setDefalutBeginTimeAndEndTime(paramMap.get("eventId").toString(),paramMap);
		Map<String, Object> chartMap = new HashMap<>();
		try {
			List<WeiboEntity> list = this.eventAnalysisDao.getPropagatePath_other(paramMap);
			Map<String, Long> weiboAuthorMap = this.eventAnalysisDao.getWeiboAuthorMap_other(paramMap);
			Map<String, String> topicMap = this.eventAnalysisDao.getTopicName(paramMap);
			String topicName = topicMap.get("KT_NAME");
			// ArrayList<WeiboBean> weiboList = new ArrayList<WeiboBean>();
			// // frushList(list,weiboList);
			// for (WeiboEntity weibo : list) {
			// WeiboBean weiboBean = new WeiboBean();
			// weiboBean.setAll_people(weibo.getAll_people());
			// weiboBean.setAuther(weibo.getAuther());
			// weiboBean.setFollowers_count(weibo.getFollowers_count());
			// weiboBean.setId(weibo.getId());
			// weiboBean.setRetweeted_status_url(weibo.getRetweeted_status_url());
			// weiboBean.setStatuses_count(weibo.getStatuses_count());
			// weiboBean.setTime(weibo.getTime());
			// weiboBean.setUrl(weibo.getUrl());
			// weiboBean.setRegion(weibo.getRegion());
			// weiboBean.setAvatarUrl(weibo.getAvatarUrl());
			// weiboList.add(weiboBean);
			// }
			List<Node> nodeList = new ArrayList<>();
			Node rootnode = new Node(0d, 0d, topicName, "0", 1000d);
			nodeList.add(rootnode);
			List<Line> lineList = new ArrayList<>();
			String strWeiboRelation = AnalysisEventByWeibo.TreeGenerator((ArrayList)list);

			JSONArray jsonArr = JSON.parseArray(strWeiboRelation);
			JSONObject jsonObj = jsonArr.getJSONObject(0);
			JSONArray jsonTop20 = jsonObj.getJSONArray("topN");
			int maxDeep = jsonObj.getInteger("maxDeep");
			JSONObject rootJson = jsonObj.getJSONObject("root");
			JSONArray rootNode = rootJson.getJSONArray("children");
			int cycle = 1;
			int nodeNum = getNodeNum(r, R);
			int i = 0;
			List<Map<String, Object>> mapList = new ArrayList<>();
			for (Object weibo : rootNode) {
				if (i > nodeNum) {
					cycle++;
					nodeNum = getNodeNum(r, R * cycle);
					i = 0;
				}
				JSONObject data = ((JSONObject) weibo).getJSONObject("data");
				WeiboEntity c = JSON.parseObject(data.toJSONString(), WeiboEntity.class);
				if ((((JSONObject) weibo).getJSONArray("children")).isEmpty()) {
					continue;
				}
				i++;
				// Map<String, Double> xy = getRandonXAndY(50); // 随机获取点坐标
				Map<String, Double> xy = getXAndY(0, 0, R * cycle * 3, i, nodeNum);
				// //以（0，0）为圆心 获取点坐标
				// Node node = new
				// Node(xy.get("x"),xy.get("y"),data.getString("auther"),data.getString("id"));
				Node node = new Node(xy.get("x"), xy.get("y"), c.getAuther(), c.getId(),
						(((JSONObject) weibo).getJSONArray("children")).size());
				Line line = new Line();
				line.setSourceID("0");
				line.setTargetID(c.getId());
				// nodeList.add(node);
				List<Node> subNodeList = new ArrayList<>();
				List<Line> subLineList = new ArrayList<>();
				subLineList.add(line);
				int count = 0;
				count = addChieldNode2((JSONObject) weibo, node, subNodeList, subLineList, count);
				Map<String, Object> m = new HashMap<>();
				m.put("subNodeList", subNodeList);
				m.put("subLineList", subLineList);
				m.put("count", count);
				m.put("node", node);
				mapList.add(m);
			}
			Collections.sort(mapList, new Comparator<Map<String, Object>>() {

				@Override
				public int compare(Map<String, Object> o1, Map<String, Object> o2) {
					String v1 = o1.get("count").toString();
					String v2 = o2.get("count").toString();
					if (Integer.valueOf(v1) > Integer.valueOf(v2)) {
						return -1;
					} else if (Integer.valueOf(v2) > Integer.valueOf(v1)) {
						return 1;
					} else {
						return 0;
					}
				}
			});
			int allCount = 0;
			int treeSize = 0;
			for (Map<String, Object> map : mapList) {
				int count = (int) map.get("count");
				treeSize++;
				allCount += count;
				if ((allCount < 6 || count > 1) & treeSize < 10) {
					// subNodeList,subLineList
					nodeList.add((Node) map.get("node"));
					nodeList.addAll((Collection<? extends Node>) map.get("subNodeList"));
					lineList.addAll((Collection<? extends Line>) map.get("subLineList"));
				}
			}
			for(int t=0;t<jsonTop20.size();t++){
			    JSONObject json = jsonTop20.getJSONObject(t);
			    paramMap.put("authorId", json.getString("authorId"));
			    List<EventAnalysisData> dataList = getAuthorArticles(paramMap);
			    json.put("statuses_count", dataList.size());
			}
			chartMap.put("node", nodeList);
			chartMap.put("line", lineList);
			chartMap.put("maxDeep", maxDeep);
			chartMap.put("top6", jsonTop20.size() > 10 ? jsonTop20.subList(0, 10) : jsonTop20);
			chartMap.put("allAuthor", weiboAuthorMap.get("allAuthor"));
			chartMap.put("allFans", weiboAuthorMap.get("allFans"));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return chartMap;
	}

	/**
	 * 递归添加子节点（适用于数据组提供jar包计算的树）
	 * 
	 * @param weibo
	 * @param node
	 * @param nodeList
	 * @param lineList
	 * @param count
	 * @return
	 */
	private int addChieldNode2(JSONObject weibo, Node node, List<Node> nodeList, List<Line> lineList, int count) {
		int cycle = 1;
		int nodeNum = getNodeNum(r, R * 0.8);
		int i = 0;
		for (Object c : weibo.getJSONArray("children")) {
			count++;
			if (i > nodeNum) {
				cycle++;
				nodeNum = getNodeNum(r, cycle * R * 0.8);
				i = 0;
			}
			i++;
			JSONObject data = ((JSONObject) c).getJSONObject("data");
			WeiboEntity cc = JSON.parseObject(data.toJSONString(), WeiboEntity.class);
			Map<String, Double> xy = getXAndY(node.getX(), node.getY(), R * 0.8 * cycle, i, nodeNum);
			// Map<String,Double> xy = getRandonXAndY(100);
			Node cnode = new Node(xy.get("x"), xy.get("y"), cc.getAuther(), cc.getId(),
					(((JSONObject) c).getJSONArray("children")).size());
			nodeList.add(cnode);
			Line line = new Line();
			line.setSourceID(((JSONObject) weibo).getJSONObject("data").getString("id"));
			line.setTargetID(cc.getId());
			lineList.add(line);
			count = addChieldNode2((JSONObject) c, cnode, nodeList, lineList, count);
		}
		return count;
	}

	/**
	 * 递归添加子节点（适用于自行计算的树）
	 * 
	 * @param weibo
	 * @param node
	 * @param nodeList
	 * @param lineList
	 */
	private void addChieldNode(WeiboEntity weibo, Node node, List<Node> nodeList, List<Line> lineList) {
		int cycle = 1;
		int nodeNum = getNodeNum(2, 10);
		int i = 0;
		for (WeiboEntity c : weibo.getChildrens()) {
			if (i > nodeNum) {
				cycle++;
				nodeNum = getNodeNum(2, 10 * cycle);
				i = 0;
			}
			i++;
			Map<String, Double> xy = getXAndY(node.getX(), node.getY(), 10 * cycle, i, nodeNum);
			// Map<String,Double> xy = getRandonXAndY(100);
			Node cnode = new Node(xy.get("x"), xy.get("y"), c.getAuther(), c.getId(), c.getChildrens().size());
			nodeList.add(cnode);
			Line line = new Line();
			line.setSourceID(weibo.getId());
			line.setTargetID(c.getId());
			lineList.add(line);
			addChieldNode(c, cnode, nodeList, lineList);
		}
	}

	/**
	 * @param r
	 *            两点间间隔
	 * @param R
	 *            点到圆心半径
	 * @return
	 */
	private int getNodeNum(double r, double R) {
		return (int) (2 / (Math.asin(r / R) / Math.PI));
	}

	/**
	 * 以(x,y)为圆心获取点坐标
	 * 
	 * @param x
	 *            圆心X轴坐标
	 * @param y
	 *            圆心y轴坐标
	 * @param R
	 *            点到圆心半径
	 * @param index
	 *            点的序号
	 * @param nodeNum
	 *            以R为半径的总点数
	 * @return Map<String,Double> 点的坐标
	 */
	private Map<String, Double> getXAndY(double x, double y, double R, int index, int nodeNum) {
		double X = x + R * Math.cos((360d / nodeNum) * index * Math.PI / 180d);
		double Y = y + R * Math.sin((360d / nodeNum) * index * Math.PI / 180d);
		Map<String, Double> map = new HashMap<>();
		map.put("x", X);
		map.put("y", Y);
		System.out.println(map);
		return map;
	}

	/**
	 * 随机获取点的坐标
	 * 
	 * @param r
	 *            点随机范围半径
	 * @return
	 */
	private Map<String, Double> getRandonXAndY(int r) {
		double X = RandomUtils.nextInt(2 * r) - r;
		double Y = RandomUtils.nextInt(2 * r) - r;
		Map<String, Double> map = new HashMap<>();
		map.put("x", X);
		map.put("y", Y);
		System.out.println(map);
		return map;
	}

	@Override
	public String getDeleteEventAnalysisSnapshot(Map<String, Object> paramMap) throws SysException {
		StringBuffer stringHtml = new StringBuffer();
		stringHtml.append("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\">"
				+ "<title>往期事件--快照</title><link rel=\"stylesheet\" href=\"\">"
				+ "<script type=\"text/javascript\" src=\"./lib/vue.js\"></script>"
				+ "<script src=\"./lib/jquery-1.8.3.min.js\"></script>"
				+ "<script src=\"./lib/echarts.min.js\"></script>"
				+ " <link rel=\"stylesheet\" href=\"./css/eventlist.css\">"
				+ "</head><body><div class=\"wary\" ><div class=\"centre_content\" id=\"wrap\" v-cloak>"
				+ "  <!--九大模块--><div class=\"evenAna_wrap\"><!--左侧导航--><div class=\"event_nav\" the-id=\"eventNav\">"
				+ "  <ul><li v-for=\"(item,index) in eventList\" @click=\"tab(item,index)\" :class=\"{'act':number==index}\">"
				+ " <div class=\"img\"><img :src=\"number==index?item.src_w:item.src\" alt=\"\">"
				+ " </div><div class=\"font\"><span :class=\"{'actFont':number==index}\">{{item.name}}</span>"
				+ "</div></li></ul></div><!--右侧内容--><div class=\"event_content\">"
				+ "<!--事件概述--><div class=\"event a\"  the-id=\"event_overview\" module-id='1'>"
				+ "<div class=\"event_text\"><p>事件概述</p></div><div class=\"detail_text\" the-id=\"detailText\">"
				+ "  <p class=\"textarea\" disabled=\"disabled\" the-id=\"editText\">");
		paramMap.put("moduleId", "1");
		Map<String, Object> summaryByModuleId = this.getSummaryByModuleId(paramMap);
		if (summaryByModuleId != null && summaryByModuleId.size() > 0) {
			stringHtml.append(summaryByModuleId.get("summary"));
		}
		stringHtml.append("</p></div></div>");
		stringHtml
				.append("<!--首发媒体--><div class=\"event b tableList\" the-id=\"event_media\" module-id='2'><div class=\"event_text\">"
						+ "<p>首发媒体</p></div><div class=\"table_wrap\"><table><thead><tr style=\"background: #f2f2f2;font-weight: bold\">"
						+ "<td style=\"width:104px;\">媒体类型</td><td style=\"width:150px;\">发布时间</td>"
						+ "<td style=\"width:630px;\">标题</td><td style=\"width:160px;\">信息来源</td></tr></thead><tbody>");
		// 首发媒体
		List<EventAnalysisData> firstMediaInfo = this.getFirstMediaInfo(paramMap);
		for (EventAnalysisData eventAnalysisData : firstMediaInfo) {
			stringHtml.append("<tr><td>" + eventAnalysisData.getSourceType() + "</td><td>"
					+ DateUtil.StringToString(eventAnalysisData.getCtime(), DateStyle.YYYYMMDDHHMMSS,
							DateStyle.YYYY_MM_DD_HH_MM)
					+ "</td><td style=\"padding: 0 10px\"><a target=\"_blank\" style=\"color:"
					+ "#1f96d2;cursor: pointer;\">" + eventAnalysisData.getTitle() + "</a></td><td>"
					+ eventAnalysisData.getWebname() + "</td></tr>");
		}
		stringHtml.append("</tbody></table></div></div>");

		// 事件脉络
		List<EventAnalysisData> eventVein = this.getEventVein(paramMap);
		stringHtml
				.append("<!--事件脉络--><div class=\"event c\" the-id=\"event_vein\" module-id='3'><div class=\"event_text\"><p>事件脉络</p>"
						+ "</div><div><div class=\"analyse-thead\"><p>开始时间</p>");
		if (eventVein != null && eventVein.size() > 0) {
			stringHtml.append("<p>" + eventVein.get(0).getCtime().substring(0, 4) + "</p>");
		}
		stringHtml.append("</div>");
		int m = 1;
		for (EventAnalysisData eventAnalysisData : eventVein) {
			stringHtml.append("<div class=\"analyse-body\"><div class=\"analyse-body-left\"><p>"
					+ DateUtil.StringToString(eventAnalysisData.getCtime(), DateStyle.YYYYMMDDHHMMSS,
							DateStyle.MM_DD_HH_MM_SS)
					+ "</p></div><div class=\"analyse-body-right\">"
					+ "<div><div style=\"width:26px;height:26px;position: absolute;left:-14px;top:38px;background:#fff;text-align: center;line-height: 30px;\">");
			if ((m % 5 + "").endsWith("1")) {
				stringHtml.append("<img src=\"./images/analyse_ioc2_01.png\" alt=\"\">");
			} else if ((m % 5 + "").endsWith("2")) {
				stringHtml.append("<img src=\"./images/analyse_ioc2_02.png\" alt=\"\">");
			} else if ((m % 5 + "").endsWith("3")) {
				stringHtml.append("<img src=\"./images/analyse_ioc2_03.png\" alt=\"\">");
			} else if ((m % 5 + "").endsWith("4")) {
				stringHtml.append("<img src=\"./images/analyse_ioc2_04.png\" alt=\"\">");
			} else if ((m % 5 + "").endsWith("0")) {
				stringHtml.append("<img src=\"./images/analyse_ioc2_06.png\" alt=\"\">");
			}

			stringHtml.append("</div><div class=\"info\"><i class=\"s\"></i><p class=\"p2\">"
					+ "<template><span class=\"analyse_info_col\">来源: </span><span class=\"analyse_info_sour\">"
					+ eventAnalysisData.getWebname()
					+ "</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</template>"
					+ "<template><span class=\"analyse_info_col\">相关词: </span><span class=\"analyse_info_sour\">"
					+ eventAnalysisData.getKeyword()
					+ "</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</template>"
					+ "<template><span class=\"analyse_info_col\">倾向性：</span><span class=\"analyse_info_sour\">"
					+ this.getOrientation(eventAnalysisData.getOrientation() + "")
					+ "</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</template>"
					+ "<template><span class=\"analyse_info_col\">相同文章数：</span><span class=\"analyse_info_sour\">"
					+ eventAnalysisData.getRepeatCount() + "</span></template></p><p class=\"p1\">"
					+ "<a target=\"_blank\">" + eventAnalysisData.getTitle() + "</a>");
			if (1 == m) {
				stringHtml.append("<span>首发</span>");
			}
			stringHtml.append("</p></div></div></div></div>");
			m++;
		}
		// 发展趋势
		paramMap.put("moduleId", "4");
		stringHtml
				.append("</div></div><!--发展趋势--><div class=\"event d\" the-id=\"event_trend\" module-id='4'><div class=\"event_text\"><p>发展趋势</p>"
						+ "</div><div class=\"detail_text\" the-id=\"detailText\"><p class=\"textarea\" disabled=\"disabled\" the-id=\"editText\">");
		Map<String, Object> eventTrendSummary = this.getSummaryByModuleId(paramMap);
		if (eventTrendSummary != null && eventTrendSummary.size() > 0) {
			stringHtml.append(eventTrendSummary.get("summary"));
		}
		stringHtml.append("</p></div><div id=\"developtrend\" style=\"width:100%;margin:0 auto\"></div></div>");

		// 调性分析
		paramMap.put("moduleId", "5");
		stringHtml
				.append("<!--调性分析--><div class=\"event e ech\" the-id=\"event_analyze\" module-id='5'><div class=\"event_text\"><p>调性分析</p>"
						+ "</div><div class=\"detail_text\" the-id=\"detailText\"><p class=\"textarea\" disabled=\"disabled\" the-id=\"editText\">");
		Map<String, Object> eventAnalyzeSummary = this.getSummaryByModuleId(paramMap);
		if (eventAnalyzeSummary != null && eventAnalyzeSummary.size() > 0) {
			stringHtml.append(eventAnalyzeSummary.get("summary"));
		}
		stringHtml
				.append("</p></div><div class=\"echart_wrap\"><div><p>调性分析</p><div id=\"eveAnalyze\" style=\"width:100%;margin:0 auto\"></div></div><div><p>情感占比分析</p>"
						+ "<div id=\"emotionScale\" style=\"width:100%;margin:0 auto\"></div></div></div></div>");

		// 关键词云
		paramMap.put("moduleId", "6");
		stringHtml
				.append("<!--关键词云--><div class=\"event f\" the-id=\"event_words\" module-id='6'><div class=\"event_text\"><p>关键词云</p></div>"
						+ "<template><div class=\"detail_text\" the-id=\"detailText\"><p class=\"textarea\" disabled=\"disabled\" the-id=\"editText\" data-id=\"edittext\">");
		Map<String, Object> eventWordsSummary = this.getSummaryByModuleId(paramMap);
		if (eventWordsSummary != null && eventWordsSummary.size() > 0) {
			stringHtml.append(eventWordsSummary.get("summary"));
		}
		stringHtml
				.append("</p></div><div class=\"slide\"><div class=\"slideshow\"><ul><li><div id=\"wordsCloud\" style=\"width:100%;height:360px;margin:0 auto;\" the-id=\"keyWordsCloud\"></div></li></ul></div></div>"
						+ "<div class=\"slide_info\"><p>事件高频词</p><ul>");
		paramMap.put("top", 20);
		List<Map<String, Object>> wordsCloud = this.getWordsCloud(paramMap);
		int size = 10;
		if(wordsCloud.size()<10){
		    size = wordsCloud.size();
		}
		if (wordsCloud != null && wordsCloud.size() > 0) {
			for (int i = 0; i < size; i++) {
				int j = i + 1;
				stringHtml.append("<li><a target=\"_blank\"><span>" + j + "&nbsp;" + wordsCloud.get(i).get("word")
						+ "</span><span>" + wordsCloud.get(i).get("count") + "次</span></a></li>");
			}
		}

		stringHtml.append("</ul></div></template></div>");

		// 微博分析
		paramMap.put("moduleId", "7");
		stringHtml
				.append("<!--微博分析--><div class=\"event ech g tableList\" the-id=\"blog_analyze\" module-id='7'><div class=\"event_text\">"
						+ "<p>微博分析</p></div><div class=\"detail_text\" the-id=\"detailText\"><p class=\"textarea\" disabled=\"disabled\" the-id=\"editText\">");
		Map<String, Object> blogAnalyzeSummary = this.getSummaryByModuleId(paramMap);
		if (blogAnalyzeSummary != null && blogAnalyzeSummary.size() > 0) {
			stringHtml.append(blogAnalyzeSummary.get("summary"));
		}
		stringHtml
				.append("</p></div><!--大V分布--><div class=\"blog_analyze\"><div><p>大V分布</p></div><div class=\"echart_wrap\"><div id=\"bigVSpread\" style=\"width:50%;height:280px;\"></div><div class=\"blog_spread_table\">"
						+ "<table><tr style=\"background:#f2f2f2\"><th style=\"width:150px\" rowspan=\"1\"><div class=\"out\"><b>类型</b><em>数量</em></div></th>");
		Map<String, Object> microBlogAnalysisCount = this.getMicroBlogAnalysisCount(paramMap);
		@SuppressWarnings("unchecked")
		List<TopicWeiBoAuthor> microBlogAnalysisCountList = (List<TopicWeiBoAuthor>) microBlogAnalysisCount
				.get("fivehundredThousand");
		for (TopicWeiBoAuthor topicWeiBoAuthor : microBlogAnalysisCountList) {
			stringHtml.append("<th style=\"width:140px\">" + topicWeiBoAuthor.getType() + "</th>");
		}
		if (microBlogAnalysisCountList != null && microBlogAnalysisCountList.size() > 0) {
			stringHtml.append("</tr><tr class=\"assessDetail\"><td>粉丝数>50W</td>");
		}
		for (TopicWeiBoAuthor topicWeiBoAuthor : microBlogAnalysisCountList) {
			stringHtml.append("<td>" + topicWeiBoAuthor.getCount() + "</td>");
		}
		@SuppressWarnings("unchecked")
		List<TopicWeiBoAuthor> oneMillion = (List<TopicWeiBoAuthor>) microBlogAnalysisCount.get("oneMillion");
		if (oneMillion != null && oneMillion.size() > 0) {
			stringHtml.append("</tr><tr class=\"assessDetail\"><td>粉丝数>100W</td>");
		}
		for (TopicWeiBoAuthor topicWeiBoAuthor : oneMillion) {
			stringHtml.append("<td>" + topicWeiBoAuthor.getCount() + "</td>");
		}
		@SuppressWarnings("unchecked")
		List<TopicWeiBoAuthor> tenMillion = (List<TopicWeiBoAuthor>) microBlogAnalysisCount.get("tenMillion");
		if (tenMillion != null && tenMillion.size() > 0) {
			stringHtml.append("</tr><tr class=\"assessDetail\"><td>粉丝数>1000W</td>");
		}
		for (TopicWeiBoAuthor topicWeiBoAuthor : tenMillion) {
			stringHtml.append("<td>" + topicWeiBoAuthor.getCount() + "</td>");
		}
		stringHtml.append("</tr><tr></tr></table></div></div></div>");
		// 影响力排行

		Map<String, Object> influenceRankingList = this.getInfluenceRankingList(paramMap);
		stringHtml
				.append("<!--影响力排行--><div class=\"blog_analyze\"><div><p>影响力排行</p></div><div class=\"table_wrap\"><ul>"
						+ "<li v-for=\"(item,index) in blogAnalyze.affectedRank.nav\" @click=\"blogTab(item,index)\" :class=\"{'act':blogMark==index}\">{{item.name}}</li>"
						+ "</ul><table><thead><tr style=\"background: #f2f2f2;font-weight: bold\" v-if=\"\">"
						+ "<td style=\"width:120px\">序号</td><td style=\"width:280px\">名称</td>"
						+ "<td style=\"width:160px\">粉丝数</td><td style=\"width:160px\">事件中发帖数</td>"
						+ "</tr></thead><tbody>");

		// 影响力排行数据
		@SuppressWarnings("unchecked")
		List<TopicWeiBoAuthor> governmentList = (List<TopicWeiBoAuthor>) influenceRankingList.get("governmentList");

		@SuppressWarnings("unchecked")
		List<TopicWeiBoAuthor> mediaList = (List<TopicWeiBoAuthor>) influenceRankingList.get("mediaList");

		@SuppressWarnings("unchecked")
		List<TopicWeiBoAuthor> celebrityList = (List<TopicWeiBoAuthor>) influenceRankingList.get("celebrityList");
		if (governmentList != null && governmentList.size() > 0) {
			stringHtml
					.append("<tr v-for=\"(item,index) in blogAnalyze.affectedRank.govermentRank\" v-if=\"blogAnalyze.affectedRank.govermentRank.length>0 && blogMark ==0\">"
							+ "<td>{{index+1}}</td><td>{{item.name}}</td><td>{{item.fansCount}}</td><td>{{item.statusesCount}}</td></tr>");
		}
		if (mediaList != null && mediaList.size() > 0) {
			stringHtml
					.append("<tr v-for=\"(item,index) in blogAnalyze.affectedRank.mediaRank\" v-if=\"blogAnalyze.affectedRank.mediaRank.length>0 && blogMark ==1\">"
							+ "<td>{{index+1}}</td><td>{{item.name}}</td><td>{{item.fansCount}}</td><td>{{item.statusesCount}}</td></tr>");
		}
		if (celebrityList != null && celebrityList.size() > 0) {
			stringHtml
					.append("<tr v-for=\"(item,index) in blogAnalyze.affectedRank.starRank\" v-if=\"blogAnalyze.affectedRank.starRank.length>0 && blogMark ==2\">"
							+ "<td>{{index+1}}</td><td>{{item.name}}</td><td>{{item.fansCount}}</td><td>{{item.statusesCount}}</td></tr>");
		}
		stringHtml.append("</tbody></table></div></div>");

		// 传播途径
		Map<String, Object> propagatePath = this.getPropagatePath(paramMap);
		stringHtml.append("<div class=\"blog_analyze\" style=\"position: relative\"><div><p>传播途径</p></div><template v-if=\"'<{$eventcount}>' != '0'\">"
                + "<div class=\"echart_force_wrap\"><div class=\"force_photo\"><div id=\"graph-container\" style=\"width:100%;height:500px;\"></div></div>"
                + "<div class=\"force_introduce\"><ul class=\"introduce_total\"><li><p>最大信息层级</p><p>");
		if (propagatePath != null && propagatePath.size() > 0) {
			stringHtml.append(propagatePath.get("maxDeep"));
		}
		stringHtml.append("</p></li><li><p>总转发人数</p><p>");
		if (propagatePath != null && propagatePath.size() > 0) {
			stringHtml.append(propagatePath.get("allAuthor"));
		}
		stringHtml.append("</p></li><li><p>覆盖微博用户</p><p>");
		if (propagatePath != null && propagatePath.size() > 0) {
			stringHtml.append(propagatePath.get("allFans"));
		}
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> top6List = (List<Map<String, Object>>) propagatePath.get("top6");
		stringHtml.append("</p></li></ul><div class=\"main_trans\"><p>爆发点（主要传播人）</p>"
		        + "<ul v-if=\"blogAnalyze.transRoute.length>0\"><li v-for=\"item in blogAnalyze.transRoute\">"
		        + "<div class=\"topsix_top\"><div class=\"top_left\"><img :src=\"item.avatarUrl\" alt=\"\" v-if=\"item.avatarUrl\" @click=\"turnauthorid(item)\">"
		        + "<img src=\"eventAnalysisSnapshot/images/default.png\" @click=\"turnauthorid(item)\" alt=\"\" v-else=\"\"  ></div><div class=\"top_right\"><p @click=\"turnauthorid(item)\">{{item.auther}}</p>"
		        + "<p><span>粉丝数:<em>{{item.followers_count}}</em></span><span>事件发帖数：{{item.statuses_count}}</span>"
		        + "</p></div></div><div class=\"topsix_mid\"><p><span :title=\"item.kn_abstract\">"
		        + "<template v-if=\"item.kn_abstract.length>120\">{{item.kn_abstract.substr(0,120)+'...'}}</template>"
		        + "<template v-else=\"\">{{item.kn_abstract}}</template></span><a :href=\"item.url\" target=\"_blank\">查看原文</a></p></div>"
		        + "<div class=\"topsix_bot\"><p><span>转发层级:</span><span>{{item.depth}}</span></p><p><span>转发数:</span><span>{{item.childrenSize}}</span>"
		        + "</p></div></li></ul><ul v-else=\"\" style=\"background: #fff\"><li class=\"noData noDataBreowe\" style=\"margin:100px auto\">"
		        + "<img src=\"__PUBLIC__/images/comold/nodata.png\"><p>暂无数据</p></li></ul></div></div></div></template><template v-else=\"\">"
		        + "<div class=\"noData noDataBreowe\"><img src=\"__PUBLIC__/images/comold/nodata.png\"><p>暂无数据</p></div></template>"
		        + "</div>");
		// 博主地域
		stringHtml
				.append(" <!--博主地域--><div class=\"blog_analyze\"><div><p>博主地域</p></div><div class=\"echart_wrap map\">"
						+ "<div id=\"bloggerArea\" style=\"width:60%;height:400px;\"></div><div class=\"blogger_are_table\">"
						+ "<table><tr style=\"background: #f2f2f2;font-weight: bold\"><td>地域</td><td>信息数</td></tr>");

		List<TopicWeiBoAuthor> getBloggerRegionList = this.getBloggerRegionList(paramMap);
		if (getBloggerRegionList != null && getBloggerRegionList.size() > 0) {
			stringHtml
					.append("<tr v-for=\"(item,index) in blogAnalyze.bloggerArea\" v-if=\"blogAnalyze.bloggerArea.length>0\">"
							+ "<td>{{item.name}}</td><td>{{item.value}}</td></tr>");
		}
		stringHtml.append("</table></div></div></div>");
		// 水军和情感分析
		stringHtml
				.append("<!--水军和情感分析--><div class=\"echart_wrap\"><div><p>水军分析</p><div id=\"netizenAnalysis\" style=\"width:100%;height:320px;margin: 0 auto\"></div>"
						+ "</div><div><p>情感分析</p><div id=\"emotionAnalysis\" style=\"width:100%;height:320px;margin: 0 auto\"></div></div></div></div>");

		// 媒体分析
		paramMap.put("moduleId", "8");
		stringHtml
				.append("<!--媒体分析--><div class=\"event h ech\" the-id=\"media_analyze\" module-id='8'><div class=\"event_text\"><p>媒体分析</p>"
						+ "</div><div class=\"detail_text\" the-id=\"detailText\"><p class=\"textarea\" disabled=\"disabled\" the-id=\"editText\">");
		Map<String, Object> mediaAnalyzeSummary = this.getSummaryByModuleId(paramMap);
		if (mediaAnalyzeSummary != null && mediaAnalyzeSummary.size() > 0) {
			stringHtml.append(mediaAnalyzeSummary.get("summary"));
		}
		stringHtml.append("</p></div><div class=\"echart_wrap\"><div><p>媒体来源</p>");
		stringHtml
				.append("<div id=\"mediaAnalysis\" style=\"width:100%;margin: 0 auto\"></div></div><div><p><span>媒体活跃度</span>"
						+ "</p><div id=\"mediaActive\" style=\"width:100%;margin: 0 auto\"></div></div></div></div>");
		// 观点分析
		paramMap.put("moduleId", "9");
		stringHtml
				.append("<!--观点分析--><div class=\"event g tableList i\" the-id=\"point_analyze\" module-id='9'><div class=\"event_text\">"
						+ "<p>观点分析</p></div><div class=\"detail_text\" the-id=\"detailText\"><p class=\"textarea\" disabled=\"disabled\" the-id=\"editText\">");
		Map<String, Object> pointAnalyzeSummary = this.getSummaryByModuleId(paramMap);
		if (pointAnalyzeSummary != null && pointAnalyzeSummary.size() > 0) {
			stringHtml.append(pointAnalyzeSummary.get("summary"));
		}
		Map<String, Object> keyPointMicroBlogMap = this.getKeyPointMicroBlogList(paramMap);
		@SuppressWarnings("unchecked")
		List<TopicWeiBoAuthor> governmentWeiBoList = (List<TopicWeiBoAuthor>) keyPointMicroBlogMap
				.get("governmentList");
		@SuppressWarnings("unchecked")
		List<TopicWeiBoAuthor> mediaWebBoList = (List<TopicWeiBoAuthor>) keyPointMicroBlogMap.get("mediaList");
		@SuppressWarnings("unchecked")
		List<TopicWeiBoAuthor> celebrityWeiBoList = (List<TopicWeiBoAuthor>) keyPointMicroBlogMap.get("celebrityList");
		stringHtml.append("</p><div class=\"text\" the-id=\"edit\"></div></div><!--网民观点-->"
				+ "<div class=\"blog_analyze\"><div><p>网民观点</p></div><div class=\"echart_wrap\"><div id=\"peoplePoint\" style=\"width:100%;height:300px;\"></div>"
				+ "</div></div><!--重点微博--><div class=\"blog_analyze\"><div><p>重点微博</p></div><div class=\"blog_analyze_content table_wrap\">"
				+ "<ul><li v-for=\"(item,index) in pointView.importentBlog.nav\" @click=\"pointTab(item,index)\" :class=\"{'act':pointMark==index}\">{{item.name}}</li></ul>");
		if (governmentWeiBoList != null && governmentWeiBoList.size() > 0) {
			stringHtml.append("<div class=\"point_table\">"
					+ "<div class=\"point_table_wrap\" v-for=\"(item,index) in pointView.importentBlog.govermentblog\" v-if=\"pointView.importentBlog.govermentblog.length>0 && pointMark == 0\">"
					+ "<div class=\"point_title\"><p><a href=\"\"  target=\"_blank\" style=\"color:#38a9e1;\">{{item.name}}</a></p><p>"
					+ "<span style=\"color:#999;\">{{item.ctime}}</span><span><a href=\"\"  target=\"_blank\" style=\"color:#feb364\">原文</a></span>"
					+ "</p></div><div class=\"point_detail\"><p :title=\"item.title\">{{item.subtitle}}</p></div></div>");
		}

		if (mediaWebBoList != null && mediaWebBoList.size() > 0) {
			stringHtml
					.append("<div class=\"point_table_wrap\" v-for=\"(item,index) in pointView.importentBlog.mediablog\" v-if=\"pointView.importentBlog.mediablog.length>0 && pointMark == 1\">"
							+ "<div class=\"point_title\"><p><a target=\"_blank\" style=\"color:#38a9e1;\">{{item.name}}</a></p><p>"
							+ "<span style=\"color:#999;\">{{item.ctime}}</span><span><a target=\"_blank\" style=\"color:#feb364\">原文</a></span></p>"
							+ "</div><div class=\"point_detail\"><p :title=\"item.title\">{{item.subtitle}}</p></div></div>");
		}

		if (celebrityWeiBoList != null && celebrityWeiBoList.size() > 0) {
			stringHtml
					.append("<div class=\"point_table_wrap\" v-for=\"(item,index) in pointView.importentBlog.starblog\" v-if=\"pointView.importentBlog.starblog.length>0 && pointMark == 2\">"
							+ "<div class=\"point_title\"><p><a target=\"_blank\" style=\"color:#38a9e1;\">{{item.name}}</a></p><p>"
							+ "<span style=\"color:#999;\">{{item.ctime}}</span><span><a target=\"_blank\" style=\"color:#feb364\">原文</a></span>"
							+ "</p></div><div class=\"point_detail\"><p :title=\"item.title\">{{item.subtitle}}</p></div>");
		}

		stringHtml.append("</div></div></div></div></div></div></div></div></div></body>");
		stringHtml
				.append("<script src=\"./lib/echarts/build/dist/echarts.js\"></script><script>require.config({paths: {echarts: './lib/echarts/build/dist'}});"
						+ "var evenWrap = new Vue({el:'#wrap',data:{eventList:["
						+ "{name:'事件概述',src:'./images/overview.png',src_w:'./images/overview_w.png',id:1},"
						+ "{name:'首发媒体',src:'./images/eveMedia.png',src_w:'./images/eveMedia_w.png',id:2},"
						+ "{name:'事件脉络',src:'./images/eveVein.png',src_w:'./images/eveVein_w.png',id:3},"
						+ "{name:'发展趋势',src:'./images/eveTrend.png',src_w:'./images/eveTrend_w.png',id:4},"
						+ "{name:'调性分析',src:'./images/eveAnalyze.png',src_w:'./images/eveAnalyze_w.png',id:5},"
						+ "{name:'关键词云',src:'./images/eveWords.png',src_w:'./images/eveWords_w.png',id:6},"
						+ "{name:'微博分析',src:'./images/blogAnalyze.png',src_w:'./images/blogAnalyze_w.png',id:7},"
						+ "{name:'媒体分析',src:'./images/mediaAnalyze.png',src_w:'./images/mediaAnalyze_w.png',id:8},"
						+ "{name:'观点分析',src:'./images/pointAnalyze.png',src_w:'./images/pointAnalyze_w.png',id:9},],"
						+ "number:0,eventMedia:[],eventWords:{imgArray:[],timer:null,eventInfoWords:[],},mark:0,blogAnalyze:{bigVSpread:[],affectedRank:{nav:["
						+ "{name:'政府',id:1},{name:'媒体',id:2},{name:'名人',id:3}],govermentRank:[");

		// 影响力排行数据
		for (TopicWeiBoAuthor topicWeiBoAuthor : governmentList) {
			stringHtml.append("{name:'" + topicWeiBoAuthor.getName() + "',fansCount:" + topicWeiBoAuthor.getFansCount()
					+ ",statusesCount:" + topicWeiBoAuthor.getStatusesCount() + "},");
		}
		stringHtml.append("],mediaRank:[");
		for (TopicWeiBoAuthor topicWeiBoAuthor : mediaList) {
			stringHtml.append("{name:'" + topicWeiBoAuthor.getName() + "',fansCount:" + topicWeiBoAuthor.getFansCount()
					+ ",statusesCount:" + topicWeiBoAuthor.getStatusesCount() + "},");
		}

		stringHtml.append("],starRank:[");
		for (TopicWeiBoAuthor topicWeiBoAuthor : celebrityList) {
			stringHtml.append("{name:'" + topicWeiBoAuthor.getName() + "',fansCount:" + topicWeiBoAuthor.getFansCount()
					+ ",statusesCount:" + topicWeiBoAuthor.getStatusesCount() + "},");
		}

		// 传播途径数据
		stringHtml.append("]},transRoute:[");

		for (Map<String, Object> weibo : top6List) {
			String region = weibo.get("region") == null || "".equals(weibo.get("region")) ? "未知"
					: weibo.get("region").toString();
			String avatarUrl = weibo.get("avatarUrl") == null || "".equals(weibo.get("avatarUrl"))
					? "./images/analyse_ioc1.png" : weibo.get("avatarUrl").toString();
			stringHtml.append("{avatarUrl:'" + avatarUrl + "',authorId:'" + weibo.get("authorId") + "',url:'" + weibo.get("url") + "',auther:'" 
					+ weibo.get("auther")+"',kn_abstract:'" + weibo.get("kn_abstract") + "',followers_count:'"
					+ weibo.get("followers_count") + "',time:'" + weibo.get("time") + "',region:'" + region + "',depth:"
					+ weibo.get("depth") + ",statuses_count:" + weibo.get("statuses_count") + ",childrenSize:"
					+ weibo.get("childrenSize") + "},");
		}
		stringHtml.append("],bloggerArea:[");

		for (TopicWeiBoAuthor topicWeiBoAuthor : getBloggerRegionList) {
			stringHtml.append(
					"{name: '" + topicWeiBoAuthor.getRegion() + "', value: " + topicWeiBoAuthor.getCount() + "},");
		}
		stringHtml.append(
				"]},blogMark:0,pointView:{importentBlog:{nav:[{name:'政府',id:1},{name:'媒体',id:2},{name:'名人',id:3}],govermentblog:[");
		// 政府

		for (TopicWeiBoAuthor topicWeiBoAuthor : governmentWeiBoList) {
			stringHtml.append("{name:'" + topicWeiBoAuthor.getName() + "',ctime:'" + topicWeiBoAuthor.getCtime()
					+ "',subtitle:'" + topicWeiBoAuthor.getTitle() + "'},");
		}
		// 媒体
		stringHtml.append("],mediablog:[");

		for (TopicWeiBoAuthor topicWeiBoAuthor : mediaWebBoList) {
			stringHtml.append("{name:'" + topicWeiBoAuthor.getName() + "',ctime:'" + topicWeiBoAuthor.getCtime()
					+ "',subtitle:'" + topicWeiBoAuthor.getTitle() + "'},");
		}
		// 名人
		stringHtml.append("],starblog:[");

		for (TopicWeiBoAuthor topicWeiBoAuthor : celebrityWeiBoList) {
			stringHtml.append("{name:'" + topicWeiBoAuthor.getName() + "',ctime:'" + topicWeiBoAuthor.getCtime()
					+ "',subtitle:'" + topicWeiBoAuthor.getTitle() + "'},");
		}
		stringHtml
				.append("]}},pointMark:0},methods:{tab:function (item,index) {var overview = $('[the-id=event_overview]').offset().top;"
						+ "var eveMedia = $('[the-id=event_media]').offset().top;var eveVein = $('[the-id=event_vein]').offset().top;"
						+ "var eveTrend = $('[the-id=event_trend]').offset().top;var eveAnalyze = $('[the-id=event_analyze]').offset().top;"
						+ "var eveWords = $('[the-id=event_words]').offset().top;var blogAnalyze = $('[the-id=blog_analyze]').offset().top;"
						+ "var mediaAnalyze = $('[the-id=media_analyze]').offset().top;var pointAnalyze = $('[the-id=point_analyze]').offset().top;"
						+ "switch(item.id){" + "case 1:" + "document.documentElement.scrollTop = overview;break;"
						+ "case 2:" + "document.documentElement.scrollTop = eveMedia;break;" + "case 3:"
						+ "document.documentElement.scrollTop = eveVein;break;" + "case 4:"
						+ "document.documentElement.scrollTop = eveTrend;break;" + "case 5:"
						+ "document.documentElement.scrollTop = eveAnalyze;break;" + "case 6:"
						+ "document.documentElement.scrollTop = eveWords;break;" + "case 7:"
						+ "document.documentElement.scrollTop = blogAnalyze;break;" + "case 8:"
						+ "document.documentElement.scrollTop = mediaAnalyze;break;" + "case 9:"
						+ "document.documentElement.scrollTop = pointAnalyze;break;}},changeScrollTop:function(){var _this = this; "
						+ "window.addEventListener('scroll', function () { "
						+ "var scrollTop =  window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop; "
						+ "scrollTop = scrollTop + 1; " + "var overview = $('[the-id=event_overview]').offset().top;"
						+ "var eveMedia = $('[the-id=event_media]').offset().top; "
						+ "var eveVein = $('[the-id=event_vein]').offset().top; "
						+ "var eveTrend = $('[the-id=event_trend]').offset().top; "
						+ "var eveAnalyze = $('[the-id=event_analyze]').offset().top; "
						+ "var eveWords = $('[the-id=event_words]').offset().top; "
						+ "var blogAnalyze = $('[the-id=blog_analyze]').offset().top; "
						+ "var mediaAnalyze = $('[the-id=media_analyze]').offset().top; "
						+ "var pointAnalyze = $('[the-id=point_analyze]').offset().top; "
						+ "var eventNav = $('[the-id=eventNav]'); " + "if(scrollTop>=overview){ "
						+ "eventNav.css({'position':'fixed','top':'0'}) " + "}else{ "
						+ "eventNav.css({'position':'absolute','top':overview}) " + "} " + "switch(true) " + "{ "
						+ "case scrollTop>=overview && scrollTop<eveMedia: " + " _this.number = 0;break; "
						+ "case scrollTop>=eveMedia && scrollTop<eveVein: " + " _this.number = 1;break; "
						+ "case scrollTop>=eveVein && scrollTop<eveTrend: " + "_this.number = 2;break; "
						+ "case scrollTop>=eveTrend && scrollTop<eveAnalyze: " + " _this.number = 3;break; "
						+ " case scrollTop>=eveAnalyze && scrollTop<eveWords: " + " _this.number = 4;break; "
						+ "case scrollTop>=eveWords && scrollTop<blogAnalyze: " + "_this.number = 5;break; "
						+ "case scrollTop>=blogAnalyze && scrollTop<mediaAnalyze: " + "_this.number = 6;break; "
						+ "case scrollTop>=mediaAnalyze && scrollTop<pointAnalyze: " + "_this.number = 7;break; "
						+ "case scrollTop>=pointAnalyze: " + " _this.number = 8;break;};})}, "
						+ "blogTab:function(item,index){ " + " switch (item.id) " + " { " + "case 1: "
						+ "this.blogMark = 0;break; " + "case 2: " + " this.blogMark = 1;break; " + "case 3: "
						+ " this.blogMark = 2;break; " + "} " + "}," + "pointTab:function (item,index) { "
						+ "switch (item.id) " + " { " + " case 1: " + " this.pointMark = 0;break; " + "case 2: "
						+ " this.pointMark = 1;break; " + "case 3: " + "this.pointMark = 2;break; " + " } " + " },  "
						+ " timeTrans:function(time){"
						+ "return time.substring(0,4)+\"-\"+time.substring(4,6)+\"-\"+time.substring(6,8)+\" \"+time.substring(8,10)+\":\"+time.substring(10,12)+\":\"+time.substring(12,14);"
						+ "},turnauthorid:function (item){window.open('https://weibo.com/'+item.authorId)}," + "drawEchart:function () { " + "var _this = this; " + "require([ " + "'echarts', "
						+ "'echarts/chart/bar', " + "'echarts/chart/line', " + "'echarts/chart/pie', "
						+ "'echarts/chart/map', " + " 'echarts/chart/force', " + "'echarts/chart/wordCloud', "
						+ "],loadFinish);");

		//
		List<Map<String, Object>> sourceTypeTrend = this.getSourceTypeTrend(paramMap);
		String sourceTypeData = "";
		String date = "";
		int i = 0;
		for (Map<String, Object> sourceTypeTrendMap : sourceTypeTrend) {
			String sourceType = (String) sourceTypeTrendMap.get("sourceType");
			sourceTypeData += "'" + sourceType + "',";
			String data = "";
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> dataList = (List<Map<String, Object>>) sourceTypeTrendMap.get("data");
			if (dataList != null) {
				if (dataList.size() == 1) {// 按小时展示
					@SuppressWarnings("unchecked")
					List<Map<String, Object>> hoursDataList = (List<Map<String, Object>>) dataList.get(0).get("hours");

					for (Map<String, Object> hoursDataMap : hoursDataList) {
						if (0 == i) {
							date += "'" + hoursDataMap.get("hour") + "',";
						}
						data += hoursDataMap.get("num") + ",";
					}
					sourceTypeTrendMap.put("sourceTypeData", getsubString(data));
				} else {
					for (Map<String, Object> map : dataList) {// 按天展示
						if (0 == i) {
							date += "'" + map.get("date") + "',";
						}
						data += map.get("count") + ",";
					}
					sourceTypeTrendMap.put("sourceTypeData", getsubString(data));
				}
			}
			i++;
		}
		sourceTypeData = getsubString(sourceTypeData);
		date = getsubString(date);
		stringHtml
				.append("function loadFinish(ec){var developtrend = ec.init($('#developtrend').get(0));developtrendOption = {"
						+ "legend: {data:[" + sourceTypeData
						+ "]},tooltip : {trigger: 'axis'},toolbox: {show : false,feature : {saveAsImage : {show: true}}},"
						+ "calculable : true,dataZoom:[{dataZoomIndex: 1,type: 'inside',}],xAxis : [{type : 'category',boundaryGap : false,"
						+ "data : [" + date
						+ "]}],yAxis : [{type : 'value',axisLabel : {formatter: '{value}'}}],dataZoom : {show : true,realtime : true},series:[");

		String lineData = "";
		for (Map<String, Object> sourceTypeTrendMap : sourceTypeTrend) {
			lineData += "{name:'" + sourceTypeTrendMap.get("sourceType") + "',smooth:true,type:'line',data:["
					+ sourceTypeTrendMap.get("sourceTypeData") + "]},";
		}
		stringHtml.append(getsubString(lineData));
		stringHtml.append("]};developtrend.setOption(developtrendOption);");
		// 调性分析
		List<Map<String, Object>> orientationStatistics = this.getOrientationStatistics(paramMap);
		String orientationStatisticsDate = "";
		String orientationStatisticsNegative = "";
		String orientationStatisticsNeutral = "";
		String orientationStatisticsPositive = "";
		int orientationNegative = NUM;
		int orientationNeutral = NUM;
		int orientationPositive = NUM;
		for (Map<String, Object> map : orientationStatistics) {
			orientationStatisticsDate += "'" + map.get("date") + "',";
			orientationStatisticsNegative += map.get("negative") + ",";
			orientationStatisticsNeutral += map.get("neutral") + ",";
			orientationStatisticsPositive += map.get("positive") + ",";
			orientationNegative += Integer.parseInt(map.get("negative") + "");
			orientationNeutral += Integer.parseInt(map.get("neutral") + "");
			orientationPositive += Integer.parseInt(map.get("positive") + "");
		}
		stringHtml
				.append("var eveAnalyze = ec.init($('#eveAnalyze').get(0));var emotionScale = ec.init($('#emotionScale').get(0));"
						+ "eveAnalyzeoption = {legend: {data:['正面','中性','负面']},itemStyle: {" + " normal: {"
						+ "  color: function(params) {" + "       var colorList = ["
						+ "           '#00b2e6','#ffa741','#ed3e4a'" + "       ];"
						+ "       return colorList[params.dataIndex]" + "    }," + "    label:{" + "        show:true,"
						+ "        position:'outer'," + "        formatter:'{b} : {d}%'" + "     }" + "  }"
						+ "},tooltip : {trigger: 'axis'},toolbox: {show : false,feature : {"
						+ "saveAsImage : {show: true}}},calculable : true,dataZoom:[{dataZoomIndex: 1,type: 'inside',}],xAxis : [{type : 'category',"
						+ "boundaryGap : false,data : [" + getsubString(orientationStatisticsDate)
						+ "]}],yAxis : [{type : 'value',axisLabel : {"
						+ "formatter: '{value}'}}],dataZoom : {show : true,realtime : true},series:["
						+ "{name:'正面',smooth:true,type:'line',data:[" + getsubString(orientationStatisticsPositive)
						+ "]}," + "{name:'中性',smooth:true,type:'line',data:["
						+ getsubString(orientationStatisticsNeutral) + "]},"
						+ "{name:'负面',smooth:true,type:'line',data:[" + getsubString(orientationStatisticsNegative)
						+ "]}" + "]};eveAnalyze.setOption(eveAnalyzeoption);");
		stringHtml
				.append("emotionscaleoption = {tooltip : {trigger: 'item',formatter: \"{a} <br/>{b} : {c} ({d}%)\"},legend: {"
						+ "orient : 'vertical',x : 'left',data:['正面','中性','负面']},toolbox: {show : false,feature : {saveAsImage : {show: true}"
						+ "}},calculable : true,series : [{name:'情感占比分析',type:'pie',radius :  [0, 110],center: ['55%', '55%'],"
						+ "itemStyle: {normal: {color: function(params) {var colorList = ['#00b2e6','#ffa741','#ed3e4a'];return colorList[params.dataIndex]"
						+ "},label:{show:true,position:'outer',formatter:'{b} : {d}%'}}},data:[{name:'正面',value:"
						+ orientationPositive + "},{name:'中性',value:" + orientationNeutral + "}," + "{name:'负面',value:"
						+ orientationNegative + "}]}]};emotionScale.setOption(emotionscaleoption);");

		// 关键词云

		stringHtml
				.append("var hotwords = ec.init($('#wordsCloud').get(0));hotoption = {tooltip: {show: true,},animation:false,toolbox: {show : false,"
						+ "feature : {saveAsImage : {show: true}}},series: [{type: 'wordCloud',center: ['50%', '50%'],autoSize: {enable: true,minSize: 12},"
						+ "size: ['80%', '80%'],textPadding: 0,gridSize: 2,sizeRange: [30, 50],shape: 'pentagon',drawOutOfBound: false,textRotation : [0, 0],"
						+ "itemStyle: {normal: {color: function () {return 'rgb(' + [Math.round(Math.random() * 160),Math.round(Math.random() * 160),"
						+ "Math.round(Math.random() * 160)].join(',') + ')';}},emphasis: {labelLine: {show: true,lineStyle: {shadowColor: '#333'}}}},"
						+ "axis:{type:'log',show:false},data:[");
		for (Map<String, Object> word : wordsCloud) {
			stringHtml.append("{name:'" + word.get("word") + "',value:" + word.get("count") + "},");
		}
		stringHtml.append("]}]};hotwords.setOption(hotoption);");
		// 媒体分析
		stringHtml
				.append("var bigVSpread = ec.init($('#bigVSpread').get(0));var transRoute = echarts.init($('#graph-container').get(0));"
						+ "var bloggerArea = ec.init($('#bloggerArea').get(0));var netizenAnalysis = ec.init($('#netizenAnalysis').get(0));"
						+ "var emotionAnalysis = ec.init($('#emotionAnalysis').get(0));bigVSpreadoption = {tooltip : {trigger: 'item',formatter: \"{a} <br/>{b} : {c} ({d}%)\"},"
						+ "legend: {orient : 'vertical',x : 'left',data:['企业','政府','机构','名人','网站','媒体']},toolbox: {show : false,feature : {saveAsImage : {show: true}}},"
						+ "calculable : true,series : [{name:'大V分布',type:'pie',radius :  [0, 110],center: ['55%', '55%'],itemStyle: {normal: {color: function(params) {"
						+ "var colorList = ['#00b2e6','#ed3e4a','#ffa741'];return colorList[params.dataIndex]},label:{show:true,position:'outer',formatter:'{b} : {d}%'}}},data:[");
		@SuppressWarnings("unchecked")
		List<TopicWeiBoAuthor> pieCount = (List<TopicWeiBoAuthor>) microBlogAnalysisCount.get("pieCount");
		for (TopicWeiBoAuthor topicWeiBoAuthor : pieCount) {
			stringHtml.append("{name:'" + topicWeiBoAuthor.getType() + "',value:" + topicWeiBoAuthor.getCount() + "},");
		}
		stringHtml.append("]}]};bigVSpread.setOption(bigVSpreadoption);");
		stringHtml.append("var nodes = [");
		@SuppressWarnings("unchecked")
		List<Node> nodeList = (List<Node>) propagatePath.get("node");
		for (Node node : nodeList) {
			stringHtml.append("{size:" + node.getSize() + ",x:" + node.getX() + ",y:" + node.getY() + ",color:'"
					+ node.getColor() + "',id:'" + node.getId()+ "',label:'" + node.getLabel() + "'},");
		}
		stringHtml.append("];var edges = [");
		@SuppressWarnings("unchecked")
		List<Line> lineList = (List<Line>) propagatePath.get("line");
		for (Line line : lineList) {
			stringHtml.append("{size:" + line.getSize() + ",sourceID:'" + line.getSourceID() + "',targetID:'"
					+ line.getTargetID() + "'},");
		}
		stringHtml
				.append("];transRouteOption = {series : [{type: 'graph',layout: 'none',nodes: nodes.map(function (node) {return {x: node.x,y: node.y,"
						+ "id: node.id,name: node.label,symbolSize: node.size,itemStyle: {normal: {color: node.color}}};}),edges: edges.map(function (edge) {"
						+ "return {source: edge.sourceID,target: edge.targetID};}),label: {},roam: true,focusNodeAdjacency: true,lineStyle: {normal: {width: 0.5,"
						+ "curveness: 0.3,opacity: 0.7}}}]};");

		stringHtml.append(
				"transRoute.setOption(transRouteOption);bloggerAreaoption = {tooltip : {trigger: 'item'},toolbox: {show : false,feature : {saveAsImage : {show: true}}},dataRange: {min: 0,max: ");
		if (getBloggerRegionList != null && getBloggerRegionList.size() > 0) {
			stringHtml.append(getBloggerRegionList.get(0).getCount());
		} else {
			stringHtml.append("1000");
		}
		stringHtml.append(",x: 'left',y: 'bottom'");

		stringHtml
				.append(",calculable : true},series : [{name: '博主地域',type: 'map',mapType: 'china',roam: false,itemStyle:{normal:{label:{show:true}},"
						+ "emphasis:{label:{show:true}}},data:[");
		// List<TopicWeiBoAuthor>
		// bloggerRegionList=this.getBloggerRegionList(paramMap);
		for (TopicWeiBoAuthor topicWeiBoAuthor : getBloggerRegionList) {
			stringHtml.append(
					"{name: '" + topicWeiBoAuthor.getRegion() + "', value: " + topicWeiBoAuthor.getCount() + "},");
		}
		stringHtml
				.append("]}]};bloggerArea.setOption(bloggerAreaoption);netizenAnalysisoption = {tooltip : {trigger: 'item',formatter: \"{a} <br/>{b} : {c} ({d}%)\""
						+ "},legend: {orient : 'vertical',x : 'left',data:['真实用户','水军']},toolbox: {show : false, feature : { saveAsImage : {show: true} } }, "
						+ "calculable : true, series : [ { name:'水军分析', type:'pie', radius : '55%', center: ['50%', '50%'], itemStyle: { normal: { color: "
						+ "function(params) { var colorList = [ '#ed3e4a','#ffa741' ]; return colorList[params.dataIndex] }, "
						+ "label:{ show:true, position:'outer', formatter:\"{b} : {d}%\" } } }, data:[");
		List<TopicWeiBoAuthor> navyAnalysisCount = this.getNavyAnalysisCount(paramMap);
		for (TopicWeiBoAuthor TopicWeiBoAuthor : navyAnalysisCount) {
			String userType = (TopicWeiBoAuthor.getUserType() != null && 0 == TopicWeiBoAuthor.getUserType()) ? "真实用户"
					: "水军";
			stringHtml.append("{name:'" + userType + "',value:" + TopicWeiBoAuthor.getCount() + "},");
		}
		stringHtml
				.append(" ] } ] };netizenAnalysis.setOption(netizenAnalysisoption);emotionAnalysisoption = { tooltip : { trigger: 'item', "
						+ "formatter: \"{a} <br/>{b} : {c} ({d}%)\" }, legend: { orient : 'vertical', x : '80%', y:'120px', data:['正面','中性','负面'] }, "
						+ "toolbox: { show : false, feature : { saveAsImage : {show: true} } }, calculable : true, series : [ { name:'情感分析', type:'pie', "
						+ "radius : [0, 110], center: ['40%', '50%'], itemStyle: { normal: { color: function(params) { var colorList = ["
						+ " '#00b2e6','#ffa741','#ed3e4a' ]; return colorList[params.dataIndex] }, label:{ show:true, position:'outer', formatter:'{b} : {d}%' } } }, data:[");
		List<TopicWeiBoAuthor> emotionAnalysisCount = this.getEmotionAnalysisCount(paramMap);
		for (TopicWeiBoAuthor topicWeiBoAuthor : emotionAnalysisCount) {
			if ("1".equals(topicWeiBoAuthor.getOrientation())) {
				stringHtml.append("{name:'" + this.getOrientation(topicWeiBoAuthor.getOrientation()) + "',value:"
						+ topicWeiBoAuthor.getCount() + "},");
				break;
			}
		}
		for (TopicWeiBoAuthor topicWeiBoAuthor : emotionAnalysisCount) {
			if ("3".equals(topicWeiBoAuthor.getOrientation())) {
				stringHtml.append("{name:'" + this.getOrientation(topicWeiBoAuthor.getOrientation()) + "',value:"
						+ topicWeiBoAuthor.getCount() + "},");
				break;
			}
		}
		for (TopicWeiBoAuthor topicWeiBoAuthor : emotionAnalysisCount) {
			if ("2".equals(topicWeiBoAuthor.getOrientation())) {
				stringHtml.append("{name:'" + this.getOrientation(topicWeiBoAuthor.getOrientation()) + "',value:"
						+ topicWeiBoAuthor.getCount() + "},");
				break;
			}
		}
		stringHtml
				.append("] } ] }; emotionAnalysis.setOption(emotionAnalysisoption); var mediaSource = ec.init($('#mediaAnalysis').get(0)); var mediaActive = "
						+ "ec.init($('#mediaActive').get(0)); mediaSourceoption = { tooltip : { trigger: 'item', formatter: \"{a} <br/>{b} : {c} ({d}%)\" }, "
						+ "legend: { orient : 'vertical', x : 'left', data:['网媒','论坛','博客','微博','报刊','微信','视频','APP','其他'] }, toolbox: { show : false, feature : "
						+ "{ saveAsImage : {show: true} } }, calculable : true, series : [ { name:'媒体来源', type:'pie', radius :  [30, 110], center: ['55%', '55%'], "
						+ " itemStyle: { normal: { label:{ show:true, position:'outer', formatter:\"{b} : {d}%\" } } }, data:[");
		List<Map<String, Object>> mediaAnalysisList = this.getMediaAnalysis(paramMap);
		for (Map<String, Object> map : mediaAnalysisList) {
			stringHtml.append("{name:'" + map.get("sourceTypeName") + "',value:" + map.get("count") + "},");
		}
		List<Map<String, Object>> webNameAnalysis = this.getWebNameAnalysis(paramMap);
		JSONArray webnames = new JSONArray();
		JSONArray values = new JSONArray();
		for (Map<String, Object> map : webNameAnalysis) {
			webnames.add(map.get("webName"));
			values.add(map.get("count"));
		}
		stringHtml.append("] } ] }; " + "mediaActiveoption = {"
				+ "  color:['#00b3e6','#468aca','#0c98ce','#2fc7d0','#34b26c','#f7b61b','#ff7d06','#8959a2','#834f00','#a30101'],"
				+ "  tooltip : {" + "      show:true," + "      trigger: 'axis'," + "      axisPointer : { "
				+ "         type : 'shadow'  " + "     }" + "  }," + "  calculable : true," + "  grid : {"
				+ "      borderWidth:0" + "   }," + "  xAxis : [ {" + "      type : 'category'," + "     data :"
				+ webnames.toJSONString() + "," + "     axisLabel : {" + "        rotate : 45," + "       textStyle : {"
				+ "          color : '#333'," + "          fontSize : 12," + "          fontStyle : 'normal',"
				+ "                           fontFamily : '微软雅黑',"
				+ "                           fontWeight : 'lighter'" + "                       },"
				+ "                       margin : 6," + "                       formatter: function(value) {"
				+ "                           var subString = \"\";" + "                           subString = value;"
				+ "                           if(value.length > 4){"
				+ "                               subString = value.substring(0,4)+\"…\";"
				+ "                           }" + "                           return subString;"
				+ "                       }" + "                   }," + "                   axisLine : {"
				+ "                       show : true,"
				+ "                       lineStyle : {color : '#b5b5b5',width:0.5}" + "                   },"
				+ "                   axisTick:{show:false}," + "                   splitLine:{show:false}"
				+ "               } ]," + "               yAxis : [" + "                   {"
				+ "                       type : 'value'," + "                       axisLine : {"
				+ "                           show : true,"
				+ "                           lineStyle : {color : '#b5b5b5',width:0.5}" + "                       }"
				+ "                   }" + "               ]," + "               series : [" + "                   {"
				+ "                       name:'条数'," + "                       type:'bar',"
				+ "                       itemStyle: {" + "                           normal: {"
				+ "                               barBorderRadius:[15,15,0,0],"
				+ "                               color: function(params) {"
				+ "                                   var colorList = ["
				+ "                                       '#00b2e6','#458aca','#0b97ce','#2ec5d2','#33b16c',"
				+ "                                       '#f9b21f','#ff7c05','#8957a1','#834e00','#a40000'"
				+ "                                   ];"
				+ "                                   return colorList[params.dataIndex]"
				+ "                               }," + "                               label: {"
				+ "                                                 }" + "                           },"
				+ "                           emphasis: {"
				+ "                               barBorderRadius:[10,10,0,0]" + "                           }"
				+ "                       }," + "                       barMaxWidth:20,"
				+ "                       data:" + values.toJSONString() + "," + "                       labelLine:{"
				+ "                           normal:{" + "                               show:false"
				+ "                           }" + "                       }" + "                   }"
				+ "               ]," + "               animation:false" + "           };"
				+ " mediaSource.setOption(mediaSourceoption); mediaActive.setOption(mediaActiveoption); "
				+ "var peoplePoint = ec.init($('#peoplePoint').get(0)); peoplPointoption = { tooltip : { trigger: 'item', formatter: \"{a} <br/>{b} : {c} ({d}%)\" }, "
				+ "legend: { orient : 'vertical', x : '65%', y: '60px', data:[");
		// 网民观点数据
		List<Map<String, Object>> viewPointList = getNetizenViewpoint(paramMap.get("eventId").toString());
		for (Map<String, Object> map : viewPointList) {
			stringHtml.append("'" + map.get("name") + "',");
		}
		// stringHtml.append("'银监会整顿规范吸收公款存款行为','最厉害的应该是地方性银行和股份制商业银行吧','现在有的银行都是你亲戚塞我行','还有那些村镇银行还哪来的存款','大家对银行也这么多可爱啊','某家一行3000万存款转正？？？','你该举报国有银行','然而挡不住银行拉存款'");
		stringHtml
				.append("] }, toolbox: { show : false, feature : { saveAsImage : {show: true} } }, calculable : true, series : [ { name:'网民观点', "
						+ "type:'pie', radius :  [25, 100], center: ['25%', '50%'], itemStyle: { normal: { label:{ show:true, position:'outer', "
						+ "formatter:\"{b} : {d}%\" } } }, data:[");
		// 网民观点数据
		// stringHtml.append("{name:'银监会整顿规范吸收公款存款行为',value:37.7},
		// {name:'最厉害的应该是地方性银行和股份制商业银行吧',value:8.8},
		// {name:'现在有的银行都是你亲戚塞我行',value:4.1},
		// {name:'还有那些村镇银行还哪来的存款',value:3.29}, {name:'大家对银行也这么多可爱啊',value:2.5},
		// {name:'某家一行3000万存款转正？？？',value:1.0}, {name:'你该举报国有银行',value:2.1},
		// {name:'然而挡不住银行拉存款',value:4.1}");
		for (Map<String, Object> map : viewPointList) {
			stringHtml.append("{name:'" + map.get("name") + "',value:" + map.get("value") + "},");
		}
		stringHtml.append("] } ] }; peoplePoint.setOption(peoplPointoption); window.addEventListener(\"resize\", "
				+ "function () { $('#developtrend').get(0).style.width = 100+'%';  $('#eveAnalyze').get(0).style.width = 100+'%';"
				+ "$('#emotionScale').get(0).style.width = 100+'%';$('#mediaAnalysis').get(0).style.width = 100+'%';"
				+ "$('#mediaActive').get(0).style.width = 100+'%';$('#bigVSpread').get(0).style.width = 50+'%';"
				+ "$('#bloggerArea').get(0).style.width = 60+'%';$('#netizenAnalysis').get(0).style.width = 100+'%';"
				+ "$('#emotionAnalysis').get(0).style.width = 100+'%';$('#peoplePoint').get(0).style.width = 100+'%';"
				+ "developtrend.resize();eveAnalyze.resize();emotionScale.resize();mediaSource.resize();mediaActive.resize();"
				+ "bigVSpread.resize();bloggerArea.resize();netizenAnalysis.resize();emotionAnalysis.resize();peoplePoint.resize()"
				+ "}) } } }, mounted(){ this.changeScrollTop(); this.drawEchart() } }) </script> </html>");
		String filename = UUID.randomUUID() + ".html";
		PrintStream printStream = new PrintStream(UploadFileUtil.getInstance().getEventAnalysisSnapshotOut(filename));
		printStream.println(stringHtml.toString());
		return filename;
	}

	@Override
	public Map<String, Object> selectCountGroupByIsRepeat(Map<String, Object> paramMap) {
	    setDefalutBeginTimeAndEndTime(paramMap.get("eventAnalysisId").toString(),paramMap);
		// 媒体来源类型参数
		setParamST(paramMap);
		// 自定义条件
		setParamCustomFilter(paramMap);

		return this.eventAnalysisDao.selectCountGroupByIsRepeat_self(paramMap);
	}

	public String getsubString(String String) {
		String result = "";
		if (String != null && !"".equals(String) && String.endsWith(",")) {
			result = String.substring(0, String.length() - 1);
		}
		return result;

	}

	public String getOrientation(String String) {
		String result = "";
		if ("1".equals(String)) {
			result = "正面";
		} else if ("2".equals(String)) {
			result = "负面";
		} else {
			result = "中性";
		}
		return result;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getNetizenViewpoint(String eventId) {
		List<Map<String, Object>> retList = new ArrayList<>();
		List<Map<String, Object>> list = null;
		try {
			list = this.eventAnalysisDao.getNetizenViewpoint_info(eventId);
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		}
		list.forEach(map -> {
			String VIEWPOINT = (String) map.get("VIEWPOINT");
			net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(VIEWPOINT);
			jsonArray.forEach(obj -> {
				Map<String, Object> retMap = new HashMap<>();
				net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) obj;
				jsonObject.keys().forEachRemaining(key -> {
					String value = null;
					try {
						value = (String) jsonObject.get(key);
					} catch (Exception e) {
						value = "0";
					}
					retMap.put("name", key);
					retMap.put("value", Integer.parseInt((String) value));
					retList.add(retMap);
				});

			});
		});
		retList.sort((o1, o2) -> {
			Integer v1 = (Integer) o1.get("value");
			Integer v2 = (Integer) o2.get("value");
			return v2.compareTo(v1);
		});
		if (retList.size() > 10) {
			return retList.subList(0, 10);
		} else {
			return retList;
		}

	}

	@Override
	public List<Map<String, Object>> selectEventAnalysisRegionName(Map<String, Object> paramMap) {
		List<Map<String, Object>> eventAnalysisRegionName = this.eventAnalysisDao
				.selectEventAnalysisRegionName(paramMap);
		return eventAnalysisRegionName;
	}

	/**
	 * 创建手机端事件分析照片
	 * 
	 * @return 文件名称
	 * @throws SysException
	 * @throws FileNotFoundException
	 */
	@Override
	public String createAppEventSnapshot(ServletContext servletContext, Map<String, Object> paramMap)
			throws SysException {
		Map<String, Object> map = new HashMap<>();
		paramMap.put("uuid", paramMap.get("eventId"));
		paramMap.put("moduleId", 1);
		Map<String, Object> sbmMap = this.getSummaryByModuleId(paramMap);
		Map<String, Object> firstSummaryMap = new HashMap<>();
		firstSummaryMap.put("summary", this.subTitle(sbmMap.get("summary").toString()));
		firstSummaryMap.put("sumtitle", sbmMap.get("summary"));

		paramMap.put("moduleId", 4);
		sbmMap = this.getSummaryByModuleId(paramMap);
		Map<String, Object> developtrendSummaryMap = new HashMap<>();
		developtrendSummaryMap.put("summary", this.subTitle(sbmMap.get("summary").toString()));
		developtrendSummaryMap.put("sumtitle", sbmMap.get("summary"));

		paramMap.put("moduleId", 5);
		sbmMap = this.getSummaryByModuleId(paramMap);
		Map<String, Object> eveAnalyzeSummaryMap = new HashMap<>();
		eveAnalyzeSummaryMap.put("summary", this.subTitle(sbmMap.get("summary").toString()));
		eveAnalyzeSummaryMap.put("sumtitle", sbmMap.get("summary"));

		paramMap.put("moduleId", 6);
		sbmMap = this.getSummaryByModuleId(paramMap);
		Map<String, Object> wordsCloudSummaryMap = new HashMap<>();
		wordsCloudSummaryMap.put("summary", this.subTitle(sbmMap.get("summary").toString()));
		wordsCloudSummaryMap.put("sumtitle", sbmMap.get("summary"));

		paramMap.put("moduleId", 7);
		sbmMap = this.getSummaryByModuleId(paramMap);
		Map<String, Object> bigVSpreadSummaryMap = new HashMap<>();
		bigVSpreadSummaryMap.put("summary", this.subTitle(sbmMap.get("summary").toString()));
		bigVSpreadSummaryMap.put("sumtitle", sbmMap.get("summary"));

		paramMap.put("moduleId", 8);
		sbmMap = this.getSummaryByModuleId(paramMap);
		Map<String, Object> mediaAnalysisSummaryMap = new HashMap<>();
		mediaAnalysisSummaryMap.put("summary", this.subTitle(sbmMap.get("summary").toString()));
		mediaAnalysisSummaryMap.put("sumtitle", sbmMap.get("summary"));
		sbmMap.remove("moduleId");

		map.put("firstSummary", JSON.toJSONString(firstSummaryMap));
		map.put("developtrendSummary", JSON.toJSONString(developtrendSummaryMap));
		map.put("eveAnalyzeSummary", JSON.toJSONString(eveAnalyzeSummaryMap));
		map.put("wordsCloudSummary", JSON.toJSONString(wordsCloudSummaryMap));
		map.put("bigVSpreadSummary", JSON.toJSONString(bigVSpreadSummaryMap));
		map.put("mediaAnalysisSummary", JSON.toJSONString(mediaAnalysisSummaryMap));

		Map<String, Object> countMap = this.getCount(paramMap);
		// 总数
		Long eventCount = (Long) countMap.get("count");
		/**
		 * 首发媒体
		 */
		map.put("eventCount", eventCount);
		List<EventAnalysisData> result = this.getFirstMediaInfo(paramMap);
		List<EventMedia> emList = new ArrayList<>();
		for (EventAnalysisData eventAnalysisData : result) {
			EventMedia em = new EventMedia();
			em.setSourceType(eventAnalysisData.getSourceType());
			em.setCtime(
					DateUtil.DateToString(DateUtil.StringToDate(eventAnalysisData.getCtime(), DateStyle.YYYYMMDDHHMMSS),
							DateStyle.YYYY_MM_DD_HH_MM_SS));
			em.setTitle(eventAnalysisData.getTitle());
			em.setUrl(eventAnalysisData.getUrl());
			em.setWebname(eventAnalysisData.getWebname());
			emList.add(em);
		}
		map.put("eventMedia", JSON.toJSONString(emList));
		/**
		 * 事件脉络
		 */
		List<EventAnalysisData> veinList = this.getEventVein(paramMap);
		List<ListCountInfo> lsiList = new ArrayList<>();
		for (EventAnalysisData eventAnalysisData : veinList) {
			ListCountInfo lsi = new ListCountInfo();
			lsi.setCtime(
					DateUtil.DateToString(DateUtil.StringToDate(eventAnalysisData.getCtime(), DateStyle.YYYYMMDDHHMMSS),
							DateStyle.YYYY_MM_DD_HH_MM_SS));
			lsi.setKeyword(eventAnalysisData.getKeyword());
			lsi.setOrientationDesc(eventAnalysisData.getOrientationDesc());
			lsi.setRepeatCount(eventAnalysisData.getRepeatCount());
			lsi.setSourceType(eventAnalysisData.getSourceType());
			lsi.setTitle(eventAnalysisData.getTitle());
			lsi.setUrl(eventAnalysisData.getUrl());
			lsi.setWebname(eventAnalysisData.getWebname());
			lsiList.add(lsi);
		}
		map.put("listCountInfo", JSON.toJSONString(lsiList));
		/**
		 * 事件词云
		 */
		paramMap.put("top", 20);
		List<Map<String, Object>> wordsList = this.getWordsCloud(paramMap);
		List<EventInfoWords> eiwList = new ArrayList<>();
		for (Map<String, Object> wordsMap : wordsList) {
			EventInfoWords eiw = new EventInfoWords();
			String word = (String) wordsMap.get("word");
			int count = ((Long) wordsMap.get("count")).intValue();
			eiw.setWord(word);
			eiw.setCount(count);
			eiw.setName(word);
			eiw.setValue(count);
			eiwList.add(eiw);
		}
		map.put("eventInfoWords", JSON.toJSONString(eiwList));
		/**
		 * 影响力排行
		 */

		Map<String, Object> influenceRankingMap = this.getInfluenceRankingList(paramMap);
		List<Map<String, Object>> govermentRankList = new ArrayList<>();
		List<TopicWeiBoAuthor> governmentList = (List<TopicWeiBoAuthor>) influenceRankingMap.get("governmentList");
		if (governmentList != null) {
			for (TopicWeiBoAuthor topicWeiBoAuthor : governmentList) {
				Map<String, Object> govermentMap = new HashMap<String, Object>();
				govermentMap.put("name", topicWeiBoAuthor.getName());
				govermentMap.put("fansCount", topicWeiBoAuthor.getFansCount());
				govermentMap.put("statusesCount", topicWeiBoAuthor.getStatusesCount());
				govermentRankList.add(govermentMap);
			}
		}

		List<Map<String, Object>> mediaRankList = new ArrayList<>();
		List<TopicWeiBoAuthor> mediaList = (List<TopicWeiBoAuthor>) influenceRankingMap.get("mediaList");
		if (mediaList != null) {
			for (TopicWeiBoAuthor topicWeiBoAuthor : mediaList) {
				Map<String, Object> mediaMap = new HashMap<String, Object>();
				mediaMap.put("name", topicWeiBoAuthor.getName());
				mediaMap.put("fansCount", topicWeiBoAuthor.getFansCount());
				mediaMap.put("statusesCount", topicWeiBoAuthor.getStatusesCount());
				mediaRankList.add(mediaMap);
			}
		}

		List<Map<String, Object>> starRankList = new ArrayList<>();
		List<TopicWeiBoAuthor> celebrityList = (List<TopicWeiBoAuthor>) influenceRankingMap.get("celebrityList");
		if (celebrityList != null) {
			for (TopicWeiBoAuthor topicWeiBoAuthor : celebrityList) {
				Map<String, Object> celebrityMap = new HashMap<String, Object>();
				celebrityMap.put("name", topicWeiBoAuthor.getName());
				celebrityMap.put("fansCount", topicWeiBoAuthor.getFansCount());
				celebrityMap.put("statusesCount", topicWeiBoAuthor.getStatusesCount());
				starRankList.add(celebrityMap);
			}
		}

		map.put("govermentRank", JSON.toJSONString(govermentRankList));
		map.put("mediaRank", JSON.toJSONString(mediaRankList));
		map.put("starRank", JSON.toJSONString(starRankList));
		/**
		 * 主要转播人
		 */
		Map<String, Object> propagatePathMap = this.getPropagatePath(paramMap);
		if (propagatePathMap != null && propagatePathMap.size() > 0) {
			Object jsonArray = propagatePathMap.get("top6");
			map.put("topSix", JSON.toJSONString(jsonArray));
			map.put("node", JSON.toJSONString(propagatePathMap.get("node")));
			map.put("edges", JSON.toJSONString(propagatePathMap.get("line")));
			map.put("maxDeep", JSON.toJSONString(propagatePathMap.get("maxDeep")));
			map.put("allAuthor", JSON.toJSONString(propagatePathMap.get("allAuthor")));
			map.put("allFans", JSON.toJSONString(propagatePathMap.get("allFans")));
		} else {
			map.put("topSix", 100);
			map.put("node", "");
			map.put("edges", "");
			map.put("maxDeep", "");
			map.put("allAuthor", "");
			map.put("allFans", "");
		}

		/**
		 * 博主地域
		 */
		try {
			List<TopicWeiBoAuthor> bloggerRegionList = this.eventAnalysisDao.getBloggerRegionList_other(paramMap);
			for (TopicWeiBoAuthor topicWeiBoAuthor : bloggerRegionList) {
				topicWeiBoAuthor.setName(topicWeiBoAuthor.getRegion());
				topicWeiBoAuthor.setRegion(null);
				topicWeiBoAuthor.setValue(topicWeiBoAuthor.getCount());
				topicWeiBoAuthor.setCount(null);
			}
			if(null!=bloggerRegionList && bloggerRegionList.size()>0){
				bloggerRegionList.sort((a, b) -> {
					return b.getCount().compareTo(a.getCount());
				});
				map.put("bloggerMaxCount",bloggerRegionList.get(0).getCount());
			}else{
				map.put("bloggerMaxCount",0);	
			}
			map.put("bloggerArea", JSON.toJSONString(bloggerRegionList));
		} catch (Exception e) {
			map.put ("bloggerArea", JSON.toJSONString(new ArrayList<>()));
		}

		/**
		 * 重点微博
		 */
		Map<String, Object> keyPointMicroBlogList = this.getKeyPointMicroBlogList(paramMap);
		if (keyPointMicroBlogList != null) {
			List<TopicWeiBoAuthor> keyGovernmentList = (List<TopicWeiBoAuthor>) keyPointMicroBlogList
					.get("governmentList");
			List<TopicWeiBoAuthor> keyMediaList = (List<TopicWeiBoAuthor>) keyPointMicroBlogList.get("mediaList");
			List<TopicWeiBoAuthor> keyCelebrityList = (List<TopicWeiBoAuthor>) keyPointMicroBlogList
					.get("celebrityList");
			map.put("govermentblog", JSON.toJSONString(keyGovernmentList));
			map.put("mediablog", JSON.toJSONString(keyMediaList));
			map.put("starblog", JSON.toJSONString(keyCelebrityList));
		} else {
			map.put("govermentblog", "");
			map.put("mediablog", "");
			map.put("starblog", "");
		}

		/**
		 * 发展趋势
		 */
		List<Map<String, Object>> sourceTypeTrend = this.getSourceTypeTrend(paramMap);
		String sourceTypeData = "";
		String date = "";
		int i = 0;
		for (Map<String, Object> sourceTypeTrendMap : sourceTypeTrend) {
			String sourceType = (String) sourceTypeTrendMap.get("sourceType");
			sourceTypeData += "" + sourceType + ",";
			String data = "";
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> dataList = (List<Map<String, Object>>) sourceTypeTrendMap.get("data");
			if (dataList != null) {
				if (dataList.size() == 1) {// 按小时展示
					@SuppressWarnings("unchecked")
					List<Map<String, Object>> hoursDataList = (List<Map<String, Object>>) dataList.get(0).get("hours");

					for (Map<String, Object> hoursDataMap : hoursDataList) {
						if (0 == i) {
							date += "" + hoursDataMap.get("hour") + ",";
						}
						data += hoursDataMap.get("num") + ",";
					}
					sourceTypeTrendMap.put("sourceTypeData", getsubString(data));
				} else {
					for (Map<String, Object> m : dataList) {// 按天展示
						if (0 == i) {
							date += "" + m.get("date") + ",";
						}
						data += m.get("count") + ",";
					}
					sourceTypeTrendMap.put("sourceTypeData", getsubString(data));
				}
			}
			i++;
		}
		sourceTypeData = getsubString(sourceTypeData);
		date = getsubString(date);

		List<String> sourceTypeList = new ArrayList<String>();
		List<String> dateList = new ArrayList<String>();

		String[] sts = sourceTypeData.split(",");
		for (String string : sts) {
			sourceTypeList.add(string);
		}
		String[] dates = date.split(",");
		for (String string : dates) {
			if(string.length()==5){
				dateList.add(string);
			}else{
				dateList.add(DateUtil.DateToString(DateUtil.StringToDate(string, DateStyle.YYYYMMDD), DateStyle.MMDD));
			}
		}
		List<JSONObject> seriesJsonObject = new ArrayList<JSONObject>();
		for (Map<String, Object> sourceTypeTrendMap : sourceTypeTrend) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", sourceTypeTrendMap.get("sourceType"));
			jsonObject.put("smooth", true);
			jsonObject.put("type", "line");
			String std = (String) sourceTypeTrendMap.get("sourceTypeData");
			String[] stds = std.split(",");
			JSONArray jay = new JSONArray();
			for (String string : stds) {
				jay.add(string);
			}
			jsonObject.put("data", jay);
			seriesJsonObject.add(jsonObject);
		}
		map.put("developtrendoption_legend_data", JSON.toJSONString(sourceTypeList));
		map.put("developtrendoption_xAxis_data", JSON.toJSONString(dateList));
		map.put("developtrendoption_xAxis_series", JSON.toJSONString(seriesJsonObject));

		// 调性分析
		List<Map<String, Object>> orientationStatistics = this.getOrientationStatistics(paramMap);
		int orientationNegative = NUM;
		int orientationNeutral = NUM;
		int orientationPositive = NUM;
		JSONArray orientationStatisticsDate = new JSONArray();
		JSONArray orientationStatisticsNegative = new JSONArray();
		JSONArray orientationStatisticsNeutral = new JSONArray();
		JSONArray orientationStatisticsPositive = new JSONArray();
		for (Map<String, Object> osMap : orientationStatistics) {
			orientationStatisticsDate.add(DateUtil.DateToString(DateUtil.StringToDate((String) osMap.get("date"), DateStyle.YYYYMMDD), DateStyle.MMDD));
			orientationStatisticsNegative.add(osMap.get("negative"));
			orientationStatisticsNeutral.add(osMap.get("negative"));
			orientationStatisticsPositive.add(osMap.get("positive"));
			orientationNegative += Integer.parseInt(osMap.get("negative") + "");
			orientationNeutral += Integer.parseInt(osMap.get("neutral") + "");
			orientationPositive += Integer.parseInt(osMap.get("positive") + "");
		}

		List<JSONObject> eveAnalyzeoptionListPositive = new ArrayList<JSONObject>();
		JSONObject seriesEveAnalyzeoptionPositive = new JSONObject();
		seriesEveAnalyzeoptionPositive.put("name", "正面");
		seriesEveAnalyzeoptionPositive.put("smooth", true);
		seriesEveAnalyzeoptionPositive.put("type", "line");
		seriesEveAnalyzeoptionPositive.put("data", orientationStatisticsPositive);
		eveAnalyzeoptionListPositive.add(seriesEveAnalyzeoptionPositive);

		List<JSONObject> eveAnalyzeoptionListNegative = new ArrayList<JSONObject>();
		JSONObject seriesEveAnalyzeoptionNegative = new JSONObject();
		seriesEveAnalyzeoptionNegative.put("name", "负面");
		seriesEveAnalyzeoptionNegative.put("smooth", true);
		seriesEveAnalyzeoptionNegative.put("type", "line");
		seriesEveAnalyzeoptionNegative.put("data", orientationStatisticsPositive);
		eveAnalyzeoptionListNegative.add(seriesEveAnalyzeoptionNegative);

		List<JSONObject> eveAnalyzeoptionListNeutral = new ArrayList<JSONObject>();
		JSONObject seriesEveAnalyzeoptionNeutral = new JSONObject();
		seriesEveAnalyzeoptionNeutral.put("name", "中性");
		seriesEveAnalyzeoptionNeutral.put("smooth", true);
		seriesEveAnalyzeoptionNeutral.put("type", "line");
		seriesEveAnalyzeoptionNeutral.put("data", orientationStatisticsPositive);
		eveAnalyzeoptionListNeutral.add(seriesEveAnalyzeoptionNeutral);

		map.put("eveAnalyzeoption_xAxis_data", JSON.toJSONString(orientationStatisticsDate));
		map.put("eveAnalyzeoption_xAxis_series", JSON.toJSONString(eveAnalyzeoptionListPositive));
		// 情感占比
		List<JSONObject> eldata = new ArrayList<JSONObject>();
		JSONObject eldPositive = new JSONObject();
		eldPositive.put("name", "正面");
		eldPositive.put("value", orientationPositive);
		eldata.add(eldPositive);

		JSONObject eldNegative = new JSONObject();
		eldNegative.put("name", "负面");
		eldNegative.put("value", orientationNegative);
		eldata.add(eldNegative);

		JSONObject eldNeutral = new JSONObject();
		eldNeutral.put("name", "中性");
		eldNeutral.put("value", orientationNeutral);
		eldata.add(eldNeutral);
		map.put("emotionscaleoption_series_data", JSON.toJSONString(eldata));
		/**
		 * 微博分析
		 */
		Map<String, Object> microBlogAnalysisCount = this.getMicroBlogAnalysisCount(paramMap);
		if (microBlogAnalysisCount.get("pieCount") != null) {
			List<TopicWeiBoAuthor> pieTopicWeiBo = (List<TopicWeiBoAuthor>) microBlogAnalysisCount.get("pieCount");
			if (pieTopicWeiBo != null) {
				for (TopicWeiBoAuthor topicWeiBoAuthor : pieTopicWeiBo) {
					topicWeiBoAuthor.setName(topicWeiBoAuthor.getType());
					topicWeiBoAuthor.setType(null);
					topicWeiBoAuthor.setValue(topicWeiBoAuthor.getCount());
					topicWeiBoAuthor.setCount(null);
				}
			}
			map.put("bigVSpreadoption_series_data", JSON.toJSONString(pieTopicWeiBo));
		} else {
			map.put("bigVSpreadoption_series_data", "");
		}

		/**
		 * 水军分析
		 */
		List<TopicWeiBoAuthor> navyAnalysisCount = this.getNavyAnalysisCount(paramMap);
		List<Map<String, Object>> navyAnalysisList = new ArrayList<Map<String, Object>>();
		if (navyAnalysisCount != null) {
			for (TopicWeiBoAuthor TopicWeiBoAuthor : navyAnalysisCount) {
				Map<String, Object> navyAnalysisMap = new HashMap<String, Object>();
				String userType = (TopicWeiBoAuthor.getUserType() != null && 0 == TopicWeiBoAuthor.getUserType())
						? "真实用户" : "水军";
				navyAnalysisMap.put("name", userType);
				navyAnalysisMap.put("value", TopicWeiBoAuthor.getCount());
				navyAnalysisList.add(navyAnalysisMap);
			}
		}

		map.put("netizenAnalysisoption_series_data", JSON.toJSONString(navyAnalysisList));
		/**
		 * 情感分析
		 */
		List<TopicWeiBoAuthor> emotionAnalysisCount = this.getEmotionAnalysisCount(paramMap);
		List<Map<String, Object>> emotionAnalysisList = new ArrayList<Map<String, Object>>();
		if (emotionAnalysisCount != null) {
			for (TopicWeiBoAuthor topicWeiBoAuthor : emotionAnalysisCount) {
				Map<String, Object> emotionAnalysisMap = new HashMap<String, Object>();
				emotionAnalysisMap.put("name", this.getOrientation(topicWeiBoAuthor.getOrientation()));
				emotionAnalysisMap.put("value", topicWeiBoAuthor.getCount());
				emotionAnalysisList.add(emotionAnalysisMap);
			}
		}

		map.put("emotionAnalysis_series_data", JSON.toJSONString(emotionAnalysisList));
		/**
		 * 媒体来源
		 */
		List<Map<String, Object>> mediaAnalysisList = this.getMediaAnalysis(paramMap);
		List<Map<String, Object>> mediaSourceList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> mediaMap : mediaAnalysisList) {
			Map<String, Object> mediaAnalysisMap = new HashMap<String, Object>();
			mediaAnalysisMap.put("name", mediaMap.get("sourceTypeName"));
			mediaAnalysisMap.put("value", mediaMap.get("count"));
			mediaSourceList.add(mediaAnalysisMap);
		}
		map.put("mediaSourceoption_series_data", JSON.toJSONString(mediaSourceList));
		/**
		 * 媒体活跃度
		 */
		List<Map<String, Object>> webNameAnalysis = this.getWebNameAnalysis(paramMap);
		JSONArray webnames = new JSONArray();
		JSONArray values = new JSONArray();
		for (Map<String, Object> webNameMap : webNameAnalysis) {
			JSONObject valueObject = new JSONObject();
			webnames.add(webNameMap.get("webName"));
			valueObject.put("name", webNameMap.get("webName"));
			valueObject.put("value", webNameMap.get("count"));
			values.add(valueObject);
		}
		map.put("mediaActiveoption_xAxis_data", JSON.toJSONString(webnames));
		map.put("mediaActiveoption_series_data", JSON.toJSONString(values));
		/**
		 * 网民观点
		 */
		List<Map<String, Object>> viewPointList = getNetizenViewpoint(paramMap.get("eventId").toString());
		map.put("peoplePoint_series_data", JSON.toJSONString(viewPointList));
		map.put("ktName", StringUtils
				.trimToEmpty(this.eventAnalysisDao.getEvenIdName(paramMap.get("eventId").toString()).getKtName()));
		List<JSONObject> netList = new ArrayList<JSONObject>();
		for (Map<String, Object> viewPointMap : viewPointList) {
			JSONObject jo = new JSONObject();
			String name = (String) viewPointMap.get("name");
			jo.put("name", name);
			netList.add(jo);
		}
		map.put("peoplePoint_legend_data", JSON.toJSONString(netList));
		OutputStream os = null;
		try {
			String fileName = UUID.randomUUID().toString().replace("-", "") + ".html";
			String path = "/appeventIdpic/html/" + fileName;
			os = UploadFileUtil.getInstance().getOut(path);
			FreeMarkerUtil.createFile(servletContext, "sharepage.ftl", map, os);
			return fileName;
		} catch (Exception e) {
			throw new SysException(e);
		} finally {
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					throw new SysException(e);
				}
			}
		}

	}

	private String subTitle(String title) {
		if (StringUtils.trimToEmpty(title).length() > 180) {
			return title.substring(0, 180) + "...";
		} else {
			return title;
		}

	}

    @Override
    public List<EventAnalysisData> getAuthorArticles(Map<String, Object> paramMap) {
        setDefalutBeginTimeAndEndTime(paramMap.get("eventId").toString(),paramMap);
        List<EventAnalysisData> list = this.eventAnalysisDao.getAuthorArticles_other(paramMap);
        return list;
    }

}
