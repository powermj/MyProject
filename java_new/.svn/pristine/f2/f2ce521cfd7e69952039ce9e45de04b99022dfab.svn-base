package com.zhxg.yqzj.service.impl.v1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
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
import com.zhxg.framework.base.utils.EsRestClient;
import com.zhxg.framework.base.utils.ReplaceUtil;
import com.zhxg.yqzj.dao.v1.VideoInfoDao;
import com.zhxg.yqzj.entities.v1.InfoSource;
import com.zhxg.yqzj.entities.v1.VideoInfo;
import com.zhxg.yqzj.service.v1.InfoSourceService;
import com.zhxg.yqzj.service.v1.VideoInfoService;

@Service
public class VideoInfoServiceImpl extends BaseServiceImpl<VideoInfo> implements VideoInfoService {

	@Autowired
	private VideoInfoDao videoInfoDao;
	@Autowired
	private InfoSourceService infoSourceService;

	@Override
	protected BaseDao<VideoInfo> getBaseDao() {
		return this.videoInfoDao;
	}

	@Override
	public VideoInfo getDetailsInfo(Map<String, Object> paramMap) throws SysException {
		try {
			VideoInfo videoInfo = this.videoInfoDao.getDetailsInfo_self(paramMap);
			videoInfo.setSubjectName(this.videoInfoDao.getSubjectName(videoInfo.getSubjectId()));
            // 关键词标红
            if (1 == videoInfo.getIsCommentData()) {
                videoInfo.setCommentContent(ReplaceUtil.makeRedKeyWords(videoInfo.getCommentContent(), videoInfo.getKeyword().split(",")));
            } else {
                videoInfo.setInfoContent(ReplaceUtil.makeRedKeyWords(videoInfo.getInfoContent(), videoInfo.getKeyword().split(",")));
            }
			if (videoInfo.getIsCommentData() == 1) {
				JSONObject jsonObject = new JSONObject();
				JSONArray fieldJsonArray = new JSONArray();
				fieldJsonArray.add("followersCount");
				fieldJsonArray.add("avatar");
				fieldJsonArray.add("videoCount");
				fieldJsonArray.add("shareCount");
				fieldJsonArray.add("location");
				fieldJsonArray.add("videoUpCount");
				fieldJsonArray.add("user_name");
				jsonObject.put("_source", fieldJsonArray);
				JSONObject queryJsonObject = new JSONObject();
				JSONObject matchJsonObject = new JSONObject();
				matchJsonObject.put("user_key", videoInfo.getAuthorKey());
				queryJsonObject.put("match", matchJsonObject);
				jsonObject.put("query", queryJsonObject);
				RestClient restClient = EsRestClient.getInstance().getRestClient();
				String result="";
				try {
					HttpEntity entity = new NStringEntity(jsonObject.toString(), ContentType.APPLICATION_JSON);
					Response response = restClient.performRequest("POST", "/xsp_comment_data/xsp_comment_data/_search",
							Collections.singletonMap("pretty", "true"), entity);
					result = EsRestClient.readResposne(response);
				} catch (Exception e) {
					this.logger.error(e.getMessage(), e);
				}
				if(StringUtils.isNotBlank(result)) {
					JSONObject resultJson = JSON.parseObject(result);
					JSONObject resultHit = resultJson.getJSONObject("hits");
					JSONArray resultHits = resultHit.getJSONArray("hits");
					if (!(new JSONArray()).equals(resultHits)) {
						JSONObject JsonEntity = ((JSONObject) resultHits.get(0)).getJSONObject("_source");
						String userName = JsonEntity.getString("user_name");
						String avatar = JsonEntity.getString("avatar");
						Integer videoCount = JsonEntity.getInteger("videoCount");
						Integer shareCount = JsonEntity.getInteger("shareCount");
						String location = JsonEntity.getString("location");
						Integer videoUpCount = JsonEntity.getInteger("videoUpCount");
						Integer followersCount = JsonEntity.getInteger("followersCount");
						videoInfo.setAuthorValue(StringUtils.isNotBlank(userName) ? userName : "???");
						videoInfo.setAvatar(avatar);
						videoInfo.setVideoCount(videoCount == null ? 0 : videoCount);
						videoInfo.setShareCount(shareCount == null ? 0 : shareCount);
						videoInfo.setLocation(location);
						videoInfo.setVideoUpCount(videoUpCount == null ? 0 : videoUpCount);
						videoInfo.setFollowersCount(followersCount == null ? 0 : followersCount);
					} else {
						videoInfo.setAuthorValue("???");
						videoInfo.setAvatar("");
						videoInfo.setVideoCount(0);
						videoInfo.setShareCount(0);
						videoInfo.setLocation("");
						videoInfo.setVideoUpCount(0);
						videoInfo.setFollowersCount(0);
					}
				}else {
					videoInfo.setAuthorValue("???");
					videoInfo.setAvatar("");
					videoInfo.setVideoCount(0);
					videoInfo.setShareCount(0);
					videoInfo.setLocation("");
					videoInfo.setVideoUpCount(0);
					videoInfo.setFollowersCount(0);
				}
				
				if (null != restClient) {
					restClient.close();
				}
			}

			paramMap.put("isRead", 1);
			this.videoInfoDao.updateVideoInfo_self(paramMap);
			return videoInfo;
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
			throw new SysException();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<VideoInfo> getVideoList(Map<String, Object> paramMap, Pagination pageInfo) throws SysException {
		try {
            List<String> subjectIds = (List<String>) paramMap.get("subjectIdList");
            if (subjectIds == null || subjectIds.size() == 0) {
                // 所有专题id
                List<String> subjectIdList = this.videoInfoDao.getSubjectIdList(paramMap);
                if (subjectIdList != null && subjectIdList.size() > 0) {
                    paramMap.put("subjectIdList", subjectIdList);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add("-1");
                    paramMap.put("subjectIdList", list);
                }
            }
			Map<String, Integer> himHashCountMap = new HashMap<String, Integer>();
			List<String> simHashList = new ArrayList<String>();
			String simhash = (String) paramMap.get("simHash");
			PageInfo<VideoInfo> resultPage = null;
			if (StringUtils.equals(simhash, "1")) {
				PageInfo<Object> page = this.videoInfoDao.getSimHashList_self(paramMap, pageInfo);
				page.getList().forEach(videoInfo -> {
					simHashList.add(((VideoInfo) videoInfo).getSimHash());
				});
				paramMap.put("simHashList", simHashList);
				List<VideoInfo> videoList = this.videoInfoDao.getVideoList_self(paramMap);
				Map<String, VideoInfo> map = new LinkedHashMap<>();
				videoList.forEach(video -> {
					map.put(video.getSimHash(), video);
				});
				page.getList().clear();
				map.forEach((key, value) -> {
					page.getList().add(value);
				});
				page.getList().sort((a, b) -> {
					return ((VideoInfo) b).getReleaseTime().compareTo(((VideoInfo) a).getReleaseTime());
				});
				resultPage = (PageInfo<VideoInfo>) (PageInfo<?>) page;
			} else {
				PageInfo<VideoInfo> page = this.videoInfoDao.getNotSimHashList_self(paramMap, pageInfo);
				page.getList().forEach(videoInfo -> {
					simHashList.add(videoInfo.getSimHash());
				});
				resultPage = page;
			}
			if(null==simHashList || simHashList.size()<=0) {
				return resultPage;
			}
			paramMap.put("simHashList", simHashList);
			paramMap.put("simHashCount", "shc");
			List<VideoInfo> videoList = this.videoInfoDao.getSimHashListCount_self(paramMap);
			videoList.forEach(videoInfo -> {
				himHashCountMap.put(videoInfo.getSimHash(), videoInfo.getSimHashCount());
			});
			resultPage.getList().forEach(videoInfo -> {
				Integer himHashCount = himHashCountMap.get(videoInfo.getSimHash());
				videoInfo.setSimHashCount(null != himHashCount && himHashCount > 0 ? himHashCount - 1 : 0);
                // 关键词标红
                if (1 == videoInfo.getIsCommentData()) {
                    videoInfo.setCommentContent(ReplaceUtil.makeRedKeyWords(videoInfo.getCommentContent(), videoInfo.getKeyword().split(",")));
                } else {
                    videoInfo.setInfoContent(ReplaceUtil.makeRedKeyWords(videoInfo.getInfoContent(), videoInfo.getKeyword().split(",")));
                }
			});
			return resultPage;
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
			throw new SysException();
		}
	}

	@Override
	public int updateBatchWarning(Map<String, Object> paramMap) throws SysException {
		try {
			return this.videoInfoDao.updateBatchWarning_self(paramMap);
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
			throw new SysException();
		}
	}

	@Override
	public int deleteBatch(Map<String, Object> paramMap) throws SysException {
		try {
			paramMap.put("isWarning", 0);
			int res = this.videoInfoDao.deleteBatch_self(paramMap);
			paramMap.remove("isWarning");
			paramMap.put("isDelete", 1);
			res += this.videoInfoDao.updateVideoInfo_self(paramMap);
			return res;
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
			throw new SysException();
		}
	}

	@Override
	public int deleteBatchWarning(Map<String, Object> paramMap) throws SysException {
		try {
			paramMap.put("isWarning", 1);
			paramMap.put("isDelete", 1);
			int res = this.videoInfoDao.deleteBatch_self(paramMap);
			paramMap.remove("isDelete");
			paramMap.put("isWarning", 0);
			paramMap.put("isDeleteWhere", 0);
			paramMap.put("isWarningWhere", 1);
			res += this.videoInfoDao.updateVideoInfo_self(paramMap);
			return res;
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
			throw new SysException();
		}
	}

	@Override
	public Map<String, Object> getPlatformProportion(Map<String, Object> paramMap) throws SysException {
        @SuppressWarnings("unchecked")
        List<String> subjectIds = (List<String>) paramMap.get("subjectIdList");
        if (subjectIds == null || subjectIds.size() == 0) {
            // 所有专题id
            List<String> subjectIdList = this.videoInfoDao.getSubjectIdList(paramMap);
            if (subjectIdList != null && subjectIdList.size() > 0) {
                paramMap.put("subjectIdList", subjectIdList);
            } else {
                List<String> list = new ArrayList<>();
                list.add("-1");
                paramMap.put("subjectIdList", list);
            }
        }
		List<Map<String, Object>> res = new ArrayList<>();
		// 所有来源
		List<InfoSource> infoSourceList = this.infoSourceService.getInfoSourceList();
		// 各个平台数量
		List<Map<String, Object>> platformCount = this.videoInfoDao.getPlatformProportion_self(paramMap);
		for (InfoSource source : infoSourceList) {
			String name = source.getSourceName();
			String id = source.getSourceId() + "";
			Boolean flag = false;
			Map<String, Object> resMap = new HashMap<>();
			resMap.put("name", name);
			if (platformCount != null && platformCount.size() > 0) {
				flag = true;
				for (Map<String, Object> map : platformCount) {
					if (id.equals(map.get("id") + "")) {
						resMap.put("value", map.get("count"));
						res.add(resMap);
						flag = false;
						break;
					}
				}
			} else {
				resMap.put("value", 0);
				res.add(resMap);
			}
			if (flag) {
				resMap.put("value", 0);
				res.add(resMap);
			}

		}
		return this.getPieChartData(res);
	}

	public Map<String, Object> getPieChartData(List<Map<String, Object>> data) {
		Map<String, Object> result = new HashMap<>();
		List<Object> nameList = new ArrayList<>();
		List<Object> dataList = new ArrayList<>();
		for (Map<String, Object> mediaMap : data) {
			nameList.add(mediaMap.get("name"));
			dataList.add(mediaMap.get("value"));
		}
		result.put("nameData", JSON.toJSONString(nameList));
		result.put("data", JSON.toJSONString(dataList));
		return result;

	}

	@Override
	public Map<String, Object> getTrendOfPubOpinion(Map<String, Object> paramMap) {
        @SuppressWarnings("unchecked")
        List<String> subjectIds = (List<String>) paramMap.get("subjectIdList");
        if (subjectIds == null || subjectIds.size() == 0) {
            // 所有专题id
            List<String> subjectIdList = this.videoInfoDao.getSubjectIdList(paramMap);
            if (subjectIdList != null && subjectIdList.size() > 0) {
                paramMap.put("subjectIdList", subjectIdList);
            } else {
                List<String> list = new ArrayList<>();
                list.add("-1");
                paramMap.put("subjectIdList", list);
            }
        }
        Map<String, Object> result = new HashMap<>();
		if (paramMap.get("eTime") == null || "".equals(paramMap.get("eTime") + "") || paramMap.get("bTime") == null
				|| "".equals(paramMap.get("bTime") + "")) {
			// 查询信息的最大和最小发布时间
			Map<String, Object> maxAndMinTime = this.videoInfoDao.getMaxAndMinCTime_self(paramMap);
            if (maxAndMinTime != null && maxAndMinTime.size() > 0) {
                paramMap.put("eTime", maxAndMinTime.get("maxTime"));
                paramMap.put("bTime", maxAndMinTime.get("minTime"));
            } else {
                return result;
            }
		}

		List<Object> nameList = new ArrayList<>();
		List<Object> dataList = new ArrayList<>();
		List<Object> timeList = new ArrayList<>();
		@SuppressWarnings("unchecked")
		List<Integer> orientationList = (List<Integer>) paramMap.get("orientationList");
		for (Integer orientation : orientationList) {
			List<Integer> list = new ArrayList<>();
			if (1 == orientation) {
				nameList.add("正面");
				list.add(1);
				paramMap.put("orientationList", list);
			} else if (2 == orientation) {
				nameList.add("负面");
				list.add(2);
				paramMap.put("orientationList", list);
			} else {
				nameList.add("中性");
				list.add(3);
				paramMap.put("orientationList", list);
			}
			Map<String, Object> trendOfPubOpinionByOri = this.getTrendOfPubOpinionByOri(paramMap);
			timeList.add(trendOfPubOpinionByOri.get("timeList"));
			dataList.add(trendOfPubOpinionByOri.get("data"));
		}
		result.put("name", JSON.toJSONString(nameList));
		result.put("data", JSON.toJSONString(dataList));
		result.put("time", JSON.toJSONString(timeList.get(0)));
		return result;
	}

	public Map<String, Object> getTrendOfPubOpinionByOri(Map<String, Object> paramMap) {
        @SuppressWarnings("unchecked")
        List<String> subjectIds = (List<String>) paramMap.get("subjectIdList");
        if (subjectIds == null || subjectIds.size() == 0) {
            // 所有专题id
            List<String> subjectIdList = this.videoInfoDao.getSubjectIdList(paramMap);
            if (subjectIdList != null && subjectIdList.size() > 0) {
                paramMap.put("subjectIdList", subjectIdList);
            } else {
                List<String> list = new ArrayList<>();
                list.add("-1");
                paramMap.put("subjectIdList", list);
            }
        }
		List<Map<String, Object>> res = new ArrayList<>();
		Map<String, Object> param = this.getgroupType(paramMap);
		List<Map<String, Object>> result = this.videoInfoDao.getTrendOfPubOpinion_self(param);
		String eTime = paramMap.get("eTime") + "";
		String bTime = paramMap.get("bTime") + "";
		Calendar calendar = Calendar.getInstance();
		List<String> timeList = new ArrayList<>();

		if ("1".equals(paramMap.get("groupType") + "")) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy");
			try {
				Date eTimeParse = df.parse(eTime);
				calendar.setTime(eTimeParse);
				int eTimeYear = calendar.get(Calendar.YEAR);

				Date bTimeParse = df.parse(bTime);
				calendar.setTime(bTimeParse);
				int bTimeYear = calendar.get(Calendar.YEAR);

				timeList.add(df.format(calendar.getTime()));
				for (int i = 0; i < eTimeYear - bTimeYear; i++) {
					calendar.add(Calendar.YEAR, 1);
					timeList.add(df.format(calendar.getTime()));
				}
				res = this.getAllRes(res, timeList, result);

			} catch (ParseException e) {
				e.printStackTrace();
			}

		} else if ("2".equals(paramMap.get("groupType") + "")) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
			try {
				Date eTimeParse = df.parse(eTime);
				calendar.setTime(eTimeParse);
				int eTimeMonth = calendar.get(Calendar.MONTH);
				int eTimeYear = calendar.get(Calendar.YEAR);

				Date bTimeParse = df.parse(bTime);
				calendar.setTime(bTimeParse);
				int bTimeMonth = calendar.get(Calendar.MONTH);
				int bTimeYear = calendar.get(Calendar.YEAR);

				timeList.add(df.format(calendar.getTime()));
				for (int i = 0; i < (eTimeYear * 12 + eTimeMonth) - (bTimeYear * 12 + bTimeMonth); i++) {
					calendar.add(Calendar.MONTH, 1);
					timeList.add(df.format(calendar.getTime()));
				}
				res = this.getAllRes(res, timeList, result);

			} catch (ParseException e) {
				e.printStackTrace();
			}

		} else if ("3".equals(paramMap.get("groupType") + "")) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date eTimeParse = df.parse(eTime);
				calendar.setTime(eTimeParse);
				int eTimeDay = calendar.get(Calendar.DAY_OF_YEAR);

				Date bTimeParse = df.parse(bTime);
				calendar.setTime(bTimeParse);
				int bTimeDay = calendar.get(Calendar.DAY_OF_YEAR);

				timeList.add(df.format(calendar.getTime()));
				for (int i = 0; i < eTimeDay - bTimeDay; i++) {
					calendar.add(Calendar.DAY_OF_YEAR, 1);
					timeList.add(df.format(calendar.getTime()));
				}
				res = this.getAllRes(res, timeList, result);

			} catch (ParseException e) {
				e.printStackTrace();
			}

		} else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
			try {
				Date eTimeParse = df.parse(eTime);
				calendar.setTime(eTimeParse);
				int eTimeHour = calendar.get(Calendar.HOUR_OF_DAY);
				int eTimeDay = calendar.get(Calendar.DAY_OF_YEAR);

				Date bTimeParse = df.parse(bTime);
				calendar.setTime(bTimeParse);
				int bTimeHour = calendar.get(Calendar.HOUR_OF_DAY);
				int bTimeDay = calendar.get(Calendar.DAY_OF_YEAR);

				timeList.add(df.format(calendar.getTime()) + ":00");
				for (int i = 0; i < (eTimeDay * 24 + eTimeHour) - (bTimeDay * 24 + bTimeHour); i++) {
					calendar.add(Calendar.HOUR, 1);
					timeList.add(df.format(calendar.getTime()) + ":00");
				}
				res = this.getAllRes(res, timeList, result);

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Map<String, Object> resultMap = new HashMap<>();
		List<Object> nameList = new ArrayList<>();
		List<Object> dataList = new ArrayList<>();
		for (Map<String, Object> mediaMap : res) {
			nameList.add(mediaMap.get("name"));
			dataList.add(mediaMap.get("value"));
		}
		resultMap.put("timeList", nameList);
		resultMap.put("data", dataList);
		return resultMap;
	}

	public Map<String, Object> getgroupType(Map<String, Object> paramMap) {

		String eTime = paramMap.get("eTime") + "";
		String bTime = paramMap.get("bTime") + "";
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date eTimeParse = df.parse(eTime);
			calendar.setTime(eTimeParse);
			int eTimeYear = calendar.get(Calendar.YEAR);
			int eTimeMonth = calendar.get(Calendar.MONTH);
			int eTimeDay = calendar.get(Calendar.DAY_OF_YEAR);

			Date bTimeParse = df.parse(bTime);
			calendar.setTime(bTimeParse);
			int bTimeYear = calendar.get(Calendar.YEAR);
			int bTimeMonth = calendar.get(Calendar.MONTH);
			int bTimeDay = calendar.get(Calendar.DAY_OF_YEAR);
			// time<5day hour
			// 5day<=time<5month day
			// 5month<= time<5year month
			// 5year<=time year
			if (eTimeYear - bTimeYear >= 5) {
				// 开始时间和结束时间相差的年>=5 用年分组
				paramMap.put("groupType", 1);

			} else if (eTimeYear - bTimeYear < 5
					&& (eTimeYear * 12 + eTimeMonth) - (bTimeYear * 12 + bTimeMonth) >= 5) {
				// 开始时间和结束时间相差的年<5,月>=5 用月分组
				paramMap.put("groupType", 2);
			} else if ((eTimeYear * 12 + eTimeMonth) - (bTimeYear * 12 + bTimeMonth) < 5 && eTimeDay - bTimeDay >= 5) {
				// 开始时间和结束时间相差的月<5,天>=5 用天分组
				paramMap.put("groupType", 3);
			} else {
				// 开始时间和结束时间相差的天<5 用小时分组
				paramMap.put("groupType", 4);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return paramMap;

	}

	public List<Map<String, Object>> getAllRes(List<Map<String, Object>> res, List<String> timeList,
			List<Map<String, Object>> result) {
		for (String time : timeList) {
			Boolean flag = false;
			Map<String, Object> resMap = new HashMap<>();
			resMap.put("name", time);
			if (result != null && result.size() > 0) {
				flag = true;
				for (Map<String, Object> map : result) {
					if (time.equals(map.get("name") + "")) {
						resMap.put("value", map.get("value"));
						res.add(resMap);
						flag = false;
						break;
					}
				}
			} else {
				resMap.put("value", 0);
				res.add(resMap);
			}
			if (flag) {
				resMap.put("value", 0);
				res.add(resMap);
			}

		}
		return res;
	}

	@Override
	public Map<String, Object> getOriCount(Map<String, Object> paramMap) {
        @SuppressWarnings("unchecked")
        List<String> subjectIds = (List<String>) paramMap.get("subjectIdList");
        if (subjectIds == null || subjectIds.size() == 0) {
            // 所有专题id
            List<String> subjectIdList = this.videoInfoDao.getSubjectIdList(paramMap);
            if (subjectIdList != null && subjectIdList.size() > 0) {
                paramMap.put("subjectIdList", subjectIdList);
            } else {
                List<String> list = new ArrayList<>();
                list.add("-1");
                paramMap.put("subjectIdList", list);
            }
        }
		List<Map<String, Object>> result = this.videoInfoDao.getOriCount_self(paramMap);
		@SuppressWarnings("unchecked")
		List<Integer> orientationList = (List<Integer>) paramMap.get("orientationList");
		List<Map<String, Object>> oriList = new ArrayList<>();
		for (Integer ori : orientationList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", ori);
			if (1 == ori) {
				map.put("name", "正面");
			} else if (2 == ori) {
				map.put("name", "负面");
			} else {
				map.put("name", "中性");
			}
			oriList.add(map);
		}
		List<Map<String, Object>> res = new ArrayList<>();
		List<Map<String, Object>> allCount = this.getAllCount(res, oriList, result);
		return this.getPieChartData(allCount);
	}

	public List<Map<String, Object>> getAllCount(List<Map<String, Object>> res, List<Map<String, Object>> allList,
			List<Map<String, Object>> resultData) {
		for (Map<String, Object> nameMap : allList) {
			String name = nameMap.get("name") + "";
			String id = nameMap.get("id") + "";
			Boolean flag = false;
			Map<String, Object> resMap = new HashMap<>();
			resMap.put("name", name);
			if (resultData != null && resultData.size() > 0) {
				flag = true;
				for (Map<String, Object> map : resultData) {
					if (id.equals(map.get("id") + "")) {
						resMap.put("value", map.get("count"));
						res.add(resMap);
						flag = false;
						break;
					}
				}
			} else {
				resMap.put("value", 0);
				res.add(resMap);
			}
			if (flag) {
				resMap.put("value", 0);
				res.add(resMap);
			}

		}
		return res;
	}

	@Override
	public List<Map<String, Object>> getWordsCloud(Map<String, Object> paramMap) {
        @SuppressWarnings("unchecked")
        List<String> subjectIds = (List<String>) paramMap.get("subjectIdList");
        if (subjectIds == null || subjectIds.size() == 0) {
            // 所有专题id
            List<String> subjectIdList = this.videoInfoDao.getSubjectIdList(paramMap);
            if (subjectIdList != null && subjectIdList.size() > 0) {
                paramMap.put("subjectIdList", subjectIdList);
            } else {
                List<String> list = new ArrayList<>();
                list.add("-1");
                paramMap.put("subjectIdList", list);
            }
        }
		List<Map<String, Object>> result = this.videoInfoDao.getWordsCloud_self(paramMap);
		return result;
	}

}
