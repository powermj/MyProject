package com.zhxg.yqzj.service.impl.v1;

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
import com.zhxg.yqzj.entities.v1.CollectInfo;
import com.zhxg.yqzj.service.exception.myfocus.RepeatOperateException;
import com.zhxg.yqzj.service.exception.solr.CollectException;
import com.zhxg.yqzj.service.v1.CollectInfoService;

@Service
public class CollectInfoServiceImpl extends BaseServiceImpl<CollectInfo> implements CollectInfoService {

    private static final String REPEAT_OPERATE = "分类名称不能重复";
    private static final String COLLECT_ADD_ERROR = "收藏夹添加失败";
    private static final String COLLECT_UPDATE_ERROR = "收藏夹修改失败";
    private static final String COLLECT_DELETE_ERROR = "收藏夹删除失败";
    private static final String DEFAULT_COLLECT_UPDATE_ERROR = "默认收藏夹不可修改";
    private static final String DEFAULT_COLLECT_DELETE_ERROR = "默认收藏夹不可删除";

    @Autowired
    CollectInfoDao collectInfoDao;

    @Autowired
    CollectInfoCntDao collectInfoCntDao;

    @Override
    protected BaseDao<CollectInfo> getBaseDao() {
        return this.collectInfoDao;
    }

    private final static Logger log = LoggerFactory.getLogger(CollectInfoServiceImpl.class);

    @Override
    public List<CollectInfo> selectList(Map<String, Object> params) {
        // 获取用户下所有收藏夹
        List<CollectInfo> collectList = collectInfoDao.getAllCollectInfo(params);
        this.sortList(collectList);
        return collectList;
    }

    /**
     * 添加收藏夹
     * 
     * @throws RepeatOperateException
     * @throws SysException
     */
    @Override
    public int insertCollectInfo(Map<String, Object> params) throws RepeatOperateException, SysException {
        // 查询名称是否已存在
        int nameCount = collectInfoDao.getCountByName(params);
        if (nameCount != 0) {
            throw new RepeatOperateException(REPEAT_OPERATE);
        }
        String collectId = UUIDUtils.getUuid();
        params.put("collectId", collectId);
        // 添加收藏夹
        int result = 0;
        try {
            result = collectInfoDao.insertCollectInfo(params);
        } catch (Exception e) {
            log.error("-------------------" + e.getMessage());
            throw new SysException(COLLECT_ADD_ERROR);
        }
        return result;
    }

    /**
     * 修改收藏夹
     * 
     * @throws SysException
     * @throws CollectException
     * @throws RepeatOperateException
     */
    @Override
    public int updateCollectInfo(Map<String, Object> params)
            throws SysException, CollectException, RepeatOperateException {
        int result = 0;
        String collectId = params.get("collectId").toString();
        String defaultId = "000000";
        if (defaultId.equals(collectId)) {
            // 默认列表不可修改
            throw new CollectException(DEFAULT_COLLECT_UPDATE_ERROR);
        }
        // 查询名称是否已存在
        int nameCount = collectInfoDao.getCountByName(params);
        if (nameCount != 0) {
            throw new RepeatOperateException(REPEAT_OPERATE);
        }
        try {
            result = collectInfoDao.updateCollectInfo(params);
        } catch (Exception e) {
            log.error("-------------------" + e.getMessage());
            throw new SysException(COLLECT_UPDATE_ERROR);
        }
        return result;
    }

    @Override
    public int deleteCollectInfo(Map<String, Object> params) throws SysException, CollectException {
        int result = 0;
        String collectId = params.get("collectId").toString();
        String defaultId = "000000";
        if (defaultId.equals(collectId)) {
            // 默认列表不可删除
            throw new CollectException(DEFAULT_COLLECT_DELETE_ERROR);
        }
        try {
            // 删除收藏夹下所有对应的内词
            collectInfoCntDao.deleteCollectInfoCntAll(params);
            // 删除收藏夹
            result = collectInfoDao.deleteCollectInfo(params);
        } catch (Exception e) {
            log.error("-------------------" + e.getMessage());
            throw new SysException(COLLECT_DELETE_ERROR);
        }
        return result;
    }

    /**
     * 对列表进行排序
     *
     * @param collectList
     * @return
     */
    private List<CollectInfo> sortList(List<CollectInfo> collectList) {
        boolean defCollectid = false;
        CollectInfo defCollectInfo = null;
        // 判断集合中是否包含默认列表
        for (CollectInfo collectInfo : collectList) {
            String collectId = collectInfo.getCollectId();
            defCollectid = "000000".equals(collectId);
            // 如果包含默认列表，将其排在最上面
            if (defCollectid) {
                defCollectInfo = collectInfo;
                collectList.remove(collectInfo);
                collectList.add(0, defCollectInfo);
                break;
            }

        }
        return collectList;
    }

}
