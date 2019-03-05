package com.zhxg.yqzj.service.impl.v1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.framework.base.utils.UUIDUtils;
import com.zhxg.yqzj.dao.v1.CollectInfoCntDao;
import com.zhxg.yqzj.dao.v1.CollectInfoDao;
import com.zhxg.yqzj.entities.v1.CollectInfoCnt;
import com.zhxg.yqzj.service.exception.myfocus.RepeatOperateException;
import com.zhxg.yqzj.service.v1.CollectInfoCntService;

@Service
public class CollectInfoCntServiceImpl extends BaseServiceImpl<CollectInfoCnt> implements CollectInfoCntService {

    private static final String REPEAT_OPERATE = "该搜索词已收藏，请勿重复操作";
    private static final String COLLECT_CONTENT_ADD_ERROR = "收藏夹内词添加失败";
    private static final String COLLECT_CONTENT_UPDATE_ERROR = "收藏夹内词修改失败";
    private static final String COLLECT_CONTENT_DELETE_ERROR = "收藏夹内词删除失败";

    @Autowired
    CollectInfoCntDao collectInfocntDao;
    @Autowired
    CollectInfoDao collectInfoDao;

    @Override
    protected BaseDao<CollectInfoCnt> getBaseDao() {
        return this.collectInfocntDao;
    }

    @Override
    public List<CollectInfoCnt> selectList(Map<String, Object> params) {
        List<CollectInfoCnt> collectList = collectInfocntDao.selectList(params);
        return collectList;
    }

    private final static Logger log = LoggerFactory.getLogger(CollectInfoCntServiceImpl.class);

    /**
     * 添加收藏词
     * 
     * @throws RepeatOperateException
     * @throws SysException
     */
    @Override
    public int insertCollectInfoCnt(Map<String, Object> params) throws RepeatOperateException, SysException {
        int result = 0;
        // 判断是否为默认列表
        String collectId = params.get("contentId").toString();
        String defaultId = "000000";
        if (defaultId.equals(collectId)) {
            // 判断默认列表是否已经存在若不存在则先添加默认列表文件夹
            params.put("collectId", collectId);
            int countId = collectInfoDao.getCountById(params);
            if (countId == 0) {
                HashMap<String, Object> collectMap = new HashMap<String, Object>();
                collectMap.put("collectId", collectId);
                collectMap.put("collectName", "默认列表");
                collectMap.put("_KUID", params.get("_KUID").toString());
                try {
                    collectInfoDao.insertCollectInfo(collectMap);
                } catch (Exception e) {
                    log.error("-------------------" + e.getMessage());
                    throw new SysException(COLLECT_CONTENT_ADD_ERROR);
                }
            }
        }
        // 获取内词uuid
        String cntId = UUIDUtils.getUuid();
        params.put("cntId", cntId);
        // 查询是否有重复的内词名称
        int countName = collectInfocntDao.getCountByName(params);
        if (countName != 0) {
            throw new RepeatOperateException(REPEAT_OPERATE);
        }
        // 添加收藏夹
        try {
            result = collectInfocntDao.insertCollectInfoCnt(params);
        } catch (Exception e) {
            log.error("-------------------" + e.getMessage());
            throw new SysException(COLLECT_CONTENT_ADD_ERROR);
        }
        return result;
    }

    /**
     * 修改收藏词
     * 
     * @throws SysException
     * @throws RepeatOperateException
     */
    @Override
    public int updateCollectInfoCnt(Map<String, Object> params) throws SysException, RepeatOperateException {
        int result = 0;
        try {
            result = collectInfocntDao.updateCollectInfoCnt(params);
        } catch (Exception e) {
            log.error("-------------------" + e.getMessage());
            throw new SysException(COLLECT_CONTENT_UPDATE_ERROR);
        }
        return result;
    }

    /**
     * 删除收藏词
     */
    @Override
    public int deleteCollectInfoCnt(Map<String, Object> params) throws SysException {
        int result = 0;
        try {
            result = collectInfocntDao.deleteCollectInfoCnt(params);
        } catch (Exception e) {
            log.error("-------------------" + e.getMessage());
            throw new SysException(COLLECT_CONTENT_DELETE_ERROR);
        }
        return result;
    }

}
