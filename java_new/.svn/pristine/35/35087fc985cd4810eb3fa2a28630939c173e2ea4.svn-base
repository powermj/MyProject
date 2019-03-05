package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.yqzj.dao.v1.VideoInfoDao;
import com.zhxg.yqzj.entities.v1.VideoInfo;

@Repository
public class VideoInfoDaoimpl extends BaseDaoImpl<VideoInfo> implements VideoInfoDao {

	private static final String NAME_SPACE = "com.zhxg.yqzj.entities.v1.VideoInfo.";

	@Override
	public VideoInfo getDetailsInfo_self(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getDetailsInfo", paramMap);
	}

	@Override
	public PageInfo<Object> getSimHashList_self(Map<String, Object> paramMap, Pagination pageInfo) {
		PageHelper.startPage(pageInfo);
		List<Object> list = this.sqlSessionTemplate.selectList(NAME_SPACE + "getSimHashList", paramMap);
		return new PageInfo<Object>(list);
	}
	@Override
	public List<VideoInfo> getSimHashListCount_self(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "getSimHashList", paramMap);
	}

	@Override
	public PageInfo<VideoInfo> getNotSimHashList_self(Map<String, Object> paramMap, Pagination pageInfo) {
		return this.getPageList(NAME_SPACE + "getNotSimHashList", pageInfo, paramMap);
	}

	@Override
	public List<VideoInfo> getVideoList_self(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.selectList(NAME_SPACE + "getNotSimHashList", paramMap);
	}

	@Override
	public int updateBatchWarning_self(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.update(NAME_SPACE + "updateBatchWarning", paramMap);
	}

	@Override
	public int deleteBatch_self(Map<String, Object> paramMap) {
		return this.sqlSessionTemplate.delete(NAME_SPACE + "deleteBatch", paramMap);
	}

	@Override
	public String getSubjectName(String subjectId) {
		return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getSubjectName", subjectId);
	}

    @Override
    public List<Map<String, Object>> getPlatformProportion_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getPlatformProportion", paramMap);
    }

    @Override
    public Map<String, Object> getMaxAndMinCTime_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectOne(NAME_SPACE + "getMaxAndMinCTime", paramMap);
    }

    @Override
    public List<Map<String, Object>> getTrendOfPubOpinion_self(Map<String, Object> param) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getTrendOfPubOpinion", param);
    }

    @Override
    public List<Map<String, Object>> getOriCount_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getOriCount", paramMap);
    }

    @Override
    public List<Map<String, Object>> getWordsCloud_self(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getWordsCloud", paramMap);
    }
    @Override
    public int updateVideoInfo_self(Map<String, Object> paramMap) {
    	return this.sqlSessionTemplate.update(NAME_SPACE + "updateVideoInfo", paramMap);
    }

    @Override
    public List<String> getSubjectIdList(Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList(NAME_SPACE + "getSubjectIdList", paramMap);
    }
}
