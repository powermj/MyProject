package com.zhxg.yqzj.dao.v1;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.yqzj.entities.v1.VideoInfo;

public interface VideoInfoDao extends BaseDao<VideoInfo> {


	public PageInfo<Object> getSimHashList_self(Map<String, Object> paramMap, Pagination pageInfo);

	public VideoInfo getDetailsInfo_self(Map<String, Object> paramMap);

	public PageInfo<VideoInfo> getNotSimHashList_self(Map<String, Object> paramMap, Pagination pageInfo);

	public List<VideoInfo> getVideoList_self(Map<String, Object> paramMap);

	public int updateBatchWarning_self(Map<String, Object> paramMap);

	public int deleteBatch_self(Map<String, Object> paramMap);

	public String getSubjectName(String subjectId);

    public List<Map<String, Object>> getPlatformProportion_self(Map<String, Object> paramMap);

    public Map<String, Object> getMaxAndMinCTime_self(Map<String, Object> paramMap);

    public List<Map<String, Object>> getTrendOfPubOpinion_self(Map<String, Object> param);

    public List<Map<String, Object>> getOriCount_self(Map<String, Object> paramMap);

    public List<Map<String, Object>> getWordsCloud_self(Map<String, Object> paramMap);

    public int updateVideoInfo_self(Map<String, Object> paramMap);

	public List<VideoInfo> getSimHashListCount_self(Map<String, Object> paramMap);

    public List<String> getSubjectIdList(Map<String, Object> paramMap);

}
