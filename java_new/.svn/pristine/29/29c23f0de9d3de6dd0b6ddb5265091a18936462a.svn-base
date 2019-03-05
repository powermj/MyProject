package com.zhxg.yqzj.service.v1;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.BaseService;
import com.zhxg.yqzj.entities.v1.VideoInfo;

public interface VideoInfoService extends BaseService<VideoInfo>{
	/**
	 *  信息详情页
	 * @param paramMap
	 * @return
	 * @throws SysException 
	 */
	public VideoInfo getDetailsInfo(Map<String, Object> paramMap) throws SysException;
	/**
	 *  小视频信息列表页
	 * @param paramMap
	 * @param pagination 
	 * @return
	 * @throws SysException 
	 */
	public PageInfo<VideoInfo> getVideoList(Map<String, Object> paramMap, Pagination pageInfo) throws SysException;
	/**
	 * 批量预警
	 * @param paramMap
	 * @return
	 * @throws SysException 
	 */
	public int updateBatchWarning(Map<String, Object> paramMap) throws SysException;
	/**
	 * 批量删除
	 * @param paramMap
	 * @return
	 * @throws SysException 
	 */
	public int deleteBatch(Map<String, Object> paramMap) throws SysException;

    public Map<String, Object> getPlatformProportion(Map<String, Object> paramMap) throws SysException;

    public Map<String, Object> getTrendOfPubOpinion(Map<String, Object> paramMap);

    public Map<String, Object> getOriCount(Map<String, Object> paramMap);

    public List<Map<String, Object>> getWordsCloud(Map<String, Object> paramMap);
	public int deleteBatchWarning(Map<String, Object> paramMap) throws SysException;

}
