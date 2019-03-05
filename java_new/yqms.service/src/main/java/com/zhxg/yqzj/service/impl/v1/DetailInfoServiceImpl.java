package com.zhxg.yqzj.service.impl.v1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.framework.base.utils.RegexTools;
import com.zhxg.yqzj.dao.v1.SubRelationDao;
import com.zhxg.yqzj.dao.v1.UserDao;
import com.zhxg.yqzj.dao.v1.ValidationRefDao;
import com.zhxg.yqzj.entities.v1.SubRelation;
import com.zhxg.yqzj.entities.v1.ValidationRef;
import com.zhxg.yqzj.service.util.DetailOperateUtil;
import com.zhxg.yqzj.service.v1.DetailInfoService;

@Service
public class DetailInfoServiceImpl extends BaseServiceImpl<BaseEntity> implements DetailInfoService {

    @Autowired
    ValidationRefDao validationRefDao;
    @Autowired
    UserDao userDao;
    @Autowired
    SubRelationDao subRelationDao;
    
    @Override
    protected BaseDao<BaseEntity> getBaseDao() {
        return this.validationRefDao;
    }

    @Override
    public Map<String, Object> getYqllDetailInfo(Map<String, Object> params){
        //获取详情页基本信息
        ValidationRef validationRef = validationRefDao.getDetailRefInfo_self(params);

        //获取相同信息
        String kvSimhash = validationRef.getKvSimhash();
        params.put("kvSimhash", kvSimhash);
        List<ValidationRef> sameInfoList = validationRefDao.getDetailSameInfo_self(params);
        //获取转载数 validationRefDao.getReloadNumber_self(params) - 1;
        int reloadNum = sameInfoList.size() -1;
        
        //获取kvctime跟当前时间进行对比 超过七天 查个人库 不足七天查info库
        String kvUuid = validationRef.getKvUuid();
        String krCtime = validationRef.getKrCtime();
        String nowTime = DateUtil.getLongDate();
        int intervalDays = DateUtil.getIntervalDays(krCtime, nowTime);
        String dbTableTime = null;
        if(StringUtils.isNotBlank(krCtime)){
            dbTableTime = krCtime.substring(0,8);
        }
        params.put("DBTABLETIME",dbTableTime);
        params.put("kvUuid", kvUuid);
        String detailInfoCnt = null;
        if(intervalDays <= 7){
            //查询info库获取详细内容
            detailInfoCnt = validationRefDao.getDetailInfoCnt_info(params);
        }else{
            //查询self库获取详细内容
            ValidationRef ref = validationRefDao.getDetailInfoCnt_self(params);
            detailInfoCnt = ref.getKvContent();
        }
        //获取专题Id
        List<ValidationRef> topIdList = validationRefDao.getDetailRelatedTopicId_self(params);
        List<SubRelation> relationTopicList = this.getRelationTopic(topIdList, params);
        //获取代理商Id
        String agentId = userDao.getAgentId(params);
        //将内容中的图片转换成html标签
        detailInfoCnt = DetailOperateUtil.changeImgToHtml(detailInfoCnt,agentId);
        //对标题信息及详细内容关键字进行标红
        Map<String, Object> returnMap = this.makedInfoToRed(detailInfoCnt, validationRef, sameInfoList);
        returnMap.put("DetailReloadNum", reloadNum);
        returnMap.put("relationTopicList", relationTopicList);
        return returnMap;
    }
    
    @Override
    public int updateYqllOrgiation(Map<String, Object> params) {
        //获取详情页基本信息
        List<String> simHashList = validationRefDao.getDetailRefInfos_self(params);
        //获取相同信息
        params.put("simHashList", simHashList);
        int result = validationRefDao.updateYqllOrgiation_self(params);
        return result;
    }

    /**
     * 对标题，内容 ，相同信息标题进行关键字标红 
     * @param infoContent
     * @param validationRef
     * @param sameInfoList
     */
    private Map<String, Object> makedInfoToRed(String detailInfoCnt,ValidationRef validationRef,List<ValidationRef> sameInfoList){
        //标红关键字
        String keyWord = validationRef.getKvKeyWord();
        //详情页标题，内容标红
        String detailInfoTitle = validationRef.getKvTitle();
        if(StringUtils.isNotBlank(detailInfoTitle) && StringUtils.isNotBlank(keyWord)){
            detailInfoTitle = DetailOperateUtil.makeRedForTitle(keyWord, detailInfoTitle);
            validationRef.setKvTitle(detailInfoTitle);
        }
        if(StringUtils.isNotBlank(detailInfoCnt) && StringUtils.isNotBlank(keyWord)){
            detailInfoCnt = DetailOperateUtil.makeRedForContent(keyWord, detailInfoCnt);
        }
        //相同信息标题标红
        for (ValidationRef sameInfo : sameInfoList) {
            String sameTitle = sameInfo.getKvTitle();
            sameTitle = DetailOperateUtil.makeRedForTitle(keyWord, sameTitle);
            sameInfo.setKvTitle(sameTitle);
        }
        Map<String, Object> returnMap = new HashMap<String,Object>();
        returnMap.put("DetailInfo", validationRef);
        returnMap.put("DetailSameInfo", sameInfoList);
        returnMap.put("DetailContent", detailInfoCnt);
        
        return returnMap;
    }

    /**
     * 获取事件所有所属专题 
     * @param relatedTopicList
     * @param params
     */
    public List<SubRelation> getRelationTopic(List<ValidationRef> relatedTopicList,Map<String, Object> params){
            params.put("krKeyWordIds", relatedTopicList);
            List<SubRelation> relationTopicList = subRelationDao.getDetailRelationTopic(params);
            for (SubRelation subRelation : relationTopicList) {
                String kkName = subRelation.getKkName();
                String address = this.getParentTopic(subRelation,kkName);
                subRelation.setRoute(address);
            }
           return relationTopicList; 
    }
    
    /**
     * 拼接专题路径 
     * @param subRelation
     * @param parentName
     * @return
     */
    private String getParentTopic(SubRelation subRelation,String parentName){
            //分类Id
            String kcUuid = subRelation.getKcUuid();
            //父类Id
            String ksPid = subRelation.getKsPid();
            //专题路径
            String route = "";
            if("0".equals(kcUuid)&&"0".equals(ksPid)){
                route = parentName;
            }
            if(StringUtils.isNotBlank(kcUuid)&& RegexTools.cheakKsId(kcUuid)){
                SubRelation parentSort = subRelationDao.getDetailParentTopic(kcUuid);
                if(parentSort == null){
                    route = parentName;
                }else{
                    String sortName = parentSort.getKkName();
                    sortName = sortName+">"+parentName;
                    route = this.getParentTopic(parentSort,sortName);
                }
                
            }
            if(StringUtils.isNotBlank(ksPid)&& RegexTools.cheakKsId(ksPid)){
                SubRelation parentTopic = subRelationDao.getDetailParentTopic(ksPid);
                if(parentTopic == null){
                    route = parentName;
                }else{
                    String topName = parentTopic.getKkName();
                    topName = topName +">"+parentName;
                    route = this.getParentTopic(parentTopic,topName);
                }
            }
            return route;
    }


}
