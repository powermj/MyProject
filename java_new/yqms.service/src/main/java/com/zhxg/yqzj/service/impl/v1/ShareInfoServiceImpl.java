package com.zhxg.yqzj.service.impl.v1;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.framework.base.utils.DateStyle;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.framework.base.utils.FreeMarkerUtil;
import com.zhxg.framework.base.utils.OrientationUtil;
import com.zhxg.framework.base.utils.PropertiesUtil;
import com.zhxg.framework.base.utils.RegexTools;
import com.zhxg.framework.base.utils.SourceTypeUtil;
import com.zhxg.framework.base.utils.UploadFileUtil;
import com.zhxg.yqzj.dao.v1.EventAnalysisDataDao;
import com.zhxg.yqzj.dao.v1.ShareInfoDao;
import com.zhxg.yqzj.dao.v1.UserDao;
import com.zhxg.yqzj.dao.v1.ValidationRefDao;
import com.zhxg.yqzj.dao.v1.WarningReportInfoDao;
import com.zhxg.yqzj.entities.v1.EventAnalysisData;
import com.zhxg.yqzj.entities.v1.ShareInfo;
import com.zhxg.yqzj.entities.v1.ValidationRef;
import com.zhxg.yqzj.entities.v1.WarningReportInfo;
import com.zhxg.yqzj.service.v1.DetailInfoService;
import com.zhxg.yqzj.service.v1.EventAnalysisDataService;
import com.zhxg.yqzj.service.v1.ShareInfoService;

@Service
public class ShareInfoServiceImpl extends BaseServiceImpl<BaseEntity> implements ShareInfoService {

    @Autowired
    ShareInfoDao shareInfoDao;
    
    @Autowired
    DetailInfoService detailInfoServiceImpl;
    
    @Autowired
    EventAnalysisDataService eventAnalysisDataServiceImpl;
    
    @Autowired
    EventAnalysisDataDao eventAnalysisDataDaoImpl;
    
    @Autowired
    WarningReportInfoDao warningReportInfoDaoImpl;
    
    @Autowired
    ValidationRefDao validationRefDao;
    
    @Autowired
    private UserDao userDao;
    
    @Override
    protected BaseDao<BaseEntity> getBaseDao() {
        return this.shareInfoDao;
    }

    @Override
    public Map<String, Object> shareInfo(Map<String, Object> paramMap, ServletContext servletContext) throws SysException {
        Map<String, Object> map = new HashMap<>();
        if(paramMap.get("type").equals(1)){
            Map<String, Object> refMap = this.detailInfoServiceImpl.getYqllDetailInfo(paramMap);
            @SuppressWarnings("unchecked")
            List<ValidationRef>  sameInfoList = (List<ValidationRef>) refMap.get("DetailSameInfo");
            ValidationRef info = (ValidationRef) refMap.get("DetailInfo");
            ValidationRef firstInfo = info;
            if(sameInfoList.size()>0){
                firstInfo = sameInfoList.get(sameInfoList.size()-1);
            }
            map.put("content", RegexTools.GetHtmlStyleByContent(refMap.get("DetailContent").toString().replaceAll("\r|\n|\r\n", "<br/>").replaceAll("'|\"", "&quot;").trim()));
            map.put("sourceType",SourceTypeUtil.getSourceName(info.getKvSourceType()));
            map.put("time", DateUtil.StringToString(info.getKrCtime(), DateStyle.YYYYMMDDHHMMSS,DateStyle.YYYY_MM_DD_HH_MM_SS));
            map.put("titmeAttribute",OrientationUtil.getSourceName(info.getOrientation()) );
            map.put("title", info.getKvTitle().replace("<font color=#ff0000>", "").replace("</font>", ""));
            map.put("forwardNum", refMap.get("DetailReloadNum"));
            map.put("firstStart", "【"+firstInfo.getKvSite()+"】  "+DateUtil.StringToString(firstInfo.getKrCtime(), DateStyle.YYYYMMDDHHMMSS,DateStyle.YYYY_MM_DD_HH_MM_SS));
            map.put("keyWords", JSON.toJSONString(info.getKvKeyWord().split(",")));
            map.put("url", info.getKvUrl());
        }else if(paramMap.get("type").equals(2)){
            paramMap.put("eventId", paramMap.get("pid"));
            paramMap.put("eventAnalysisId", paramMap.get("pid"));
            paramMap.put("infoId", paramMap.get("krUuid"));
            EventAnalysisData data = this.eventAnalysisDataServiceImpl.selectEventAnalysisDataDetail(paramMap);
            EventAnalysisData firstInfo =data;
            List<EventAnalysisData> firstInfoList = this.eventAnalysisDataDaoImpl.getFirstInfo_other(paramMap);
            if(firstInfoList.size()>0){
                firstInfo = firstInfoList.get(0);
            }
            map.put("content", RegexTools.GetHtmlStyleByContent(data.getContent().replaceAll("\r|\n|\r\n", "<br/>").replaceAll("'|\"", "&quot;").trim()));
            map.put("sourceType",data.getSourceType());
            map.put("time", data.getCtime());
            map.put("titmeAttribute",OrientationUtil.getSourceName(data.getOrientation()+"") );
            map.put("title", data.getTitle());
            map.put("forwardNum", data.getRepeatCount());
            map.put("firstStart", "【"+firstInfo.getWebname()+"】  "+DateUtil.StringToString(firstInfo.getCtime(), DateStyle.YYYYMMDDHHMMSS,DateStyle.YYYY_MM_DD_HH_MM_SS));
            map.put("keyWords", JSON.toJSONString(data.getKeyword().split(",")));
            map.put("url", data.getUrl());
        }else if(paramMap.get("type").equals(3)){
            paramMap.put("ksUuidArr",new String[]{paramMap.get("krUuid")+""});
            List<WarningReportInfo> infoList = this.warningReportInfoDaoImpl.getWarningByKsuuid_self(paramMap);
            if(infoList == null || infoList.isEmpty()){
                throw new SysException();
            }
            WarningReportInfo info = infoList.get(0);
            
            String kvSimhash = info.getInfoSimhash();
            paramMap.put("kvSimhash", kvSimhash);
            List<ValidationRef> sameInfoList = this.validationRefDao.getDetailSameInfo_self(paramMap);
            ValidationRef firstInfo = null;
            if(sameInfoList.size()>0){
                firstInfo = sameInfoList.get(sameInfoList.size()-1);
            }
            
            map.put("content", RegexTools.GetHtmlStyleByContent(info.getContent().replaceAll("\r|\n|\r\n", "<br/>").replaceAll("'|\"", "&quot;").trim()));
            map.put("sourceType",SourceTypeUtil.getSourceName(info.getSourceType()));
            map.put("time", DateUtil.StringToString(info.getPublishTime(),DateStyle.YYYY_MM_DD_HH_MM_SS));
            map.put("titmeAttribute",OrientationUtil.getSourceName(info.getOrientation()) );
            map.put("title", info.getTitle().replace("<font color=#ff0000>", "").replace("</font>", ""));
            map.put("forwardNum", sameInfoList.size());
            if(firstInfo!=null){
                map.put("firstStart", "【"+firstInfo.getKvSite()+"】  "+ DateUtil.StringToString(firstInfo.getKrCtime(), DateStyle.YYYYMMDDHHMMSS,DateStyle.YYYY_MM_DD_HH_MM_SS));
            }else{
                map.put("firstStart", "【"+info.getWebName()+"】  "+ DateUtil.StringToString(info.getPublishTime(),DateStyle.YYYY_MM_DD_HH_MM_SS));
            }
            map.put("keyWords", JSON.toJSONString(info.getKeyWord().split(",")));
            map.put("url", info.getInfoUrl());
        }
        Object platformObj = paramMap.get("platform");
        if(platformObj == null || "21".equals(platformObj.toString()) ){
            map.put("appName","舆情秘书");            
            map.put("fbAddress","https://dl.istarshine.com/yqms/FBaddress/yqmsfb.html");
            map.put("picInfo", "yqms_logo.png");
            map.put("logoPath", "http://www.ewrwefg.com/appShareSnapshot/images/yqms_logo.png");
        }else{
            Map<String, Object> agentsAppInfo = userDao.getAgentsAppInfo(paramMap);
            if(!CollectionUtils.isEmpty(agentsAppInfo)){
                map.put("appName", agentsAppInfo.get("appName"));            
                map.put("fbAddress", agentsAppInfo.get("fbAddress"));
                Object picInfo = agentsAppInfo.get("picInfo");
                if(picInfo!=null){
                    JSONObject picInfoJson = JSONObject.parseObject(picInfo.toString());
                    Object appCircular = picInfoJson.get("app_circular");
                    if(appCircular!=null){
                        JSONObject appCircularJson = (JSONObject)appCircular;
                        Integer keyInt = appCircularJson.size();
                        keyInt=keyInt>2?keyInt-2:keyInt;
                        String key = String.valueOf(keyInt);
                        String pic = appCircularJson.getString(key);
                        map.put("picInfo", pic);
                        String logoPath ="https://support.istarshine.com/Public/agentapppic/"+agentsAppInfo.get("aid")+"/app_circular/"+pic;
                        map.put("logoPath",logoPath);
                    }
                }
            }
        }
        
        ShareInfo shareInfo = JSON.parseObject(JSON.toJSONString(paramMap), ShareInfo.class);
        OutputStream os = null;
        String path = "";
        String fileName = "";
        try {
            fileName = UUID.randomUUID().toString().replace("-", "") + ".html";
            path = "/appShareSnapshot/html/" + fileName;
            os = UploadFileUtil.getInstance().getOut(path);
            FreeMarkerUtil.createFile(servletContext, "snapshot.ftl", map, os);
            shareInfo.setSnapshotUrl(path);
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
        try {
            this.shareInfoDao.insertShareInfo(shareInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,Object> result = new HashMap<>();
        String url = shareInfo.getUrl();//Base64.encodeBase64String(shareInfo.getUrl().getBytes()).replace("/", "-").replace("\r\n", "");
        result.put("url", PropertiesUtil.getValue("file.server.domain")+path);
        return result;
    }

    public static void main(String[] args) {
        String content = "<div class=\"article-content\">\n                    <p style=\"text-align: center; text-indent: 0;\"><img src=\"http://upload.qianlong.com/2018/1030/1540896547261.jpeg\" border=\"0\" alt=\"WechatIMG10763\" data-bd-imgshare-binded=\"1\"></p>\n<p style=\"text-align: center; text-indent: 0;\"><span>活动现场(朝阳区供图 千龙网发)</span></p>\n<p>10月30日，北京CBD首次发布楼宇品质分级评价标准。</p>\n<p>千龙网北。</p>\n<p>据区域。</p>\n<p>投入运营在2年以内的楼宇项目，也可以参与“新兴项目”的评审认定。</p>\n<p>“楼宇品质评价标准研究小组”成员包括CBD内负责区域建设的相关政府部门，从事LEED、BOMA、WELL、绿色建筑标识等评价体系研究的专家，以及数名商业地产界的专家，政府机构、第三方测评机构与地产行业专家的联合测评更具权威性。经过初审、复审、终审、现场走访等多个环节的审核，今年国贸中心、北京嘉里中心、英皇集团中心、北京银泰中心、环球金融中心、财富金融中心、华贸中心、嘉铭中心（排名不分先后）共计8个楼宇从50多个参评项目中脱颖而出，获超甲级楼宇授牌。</p>\n<p>北京CBD管委会相关负责人介绍，六星、五星等标识牌类似国际上通行的酒店分级，让入驻企业可以直观了解楼宇的建设管理品质，引导CBD楼宇转型升级，提供更优质的服务。北京CBD作为楼宇经济发展的时代先锋，坚持创新引领，对标国际一流标准，创新编制了《北京CBD楼宇品质分级评价标准》，引导区域楼宇产业转型升级，助推经济实现高质量发展。</p>\n<p style=\"text-align: center; text-indent: 0;\"><img src=\"http://upload.qianlong.com/2018/1030/1540896646275.jpeg\" border=\"0\" alt=\"WechatIMG10770\" data-bd-imgshare-binded=\"1\"></p>\n<p style=\"text-align: center; text-indent: 0;\"><span>颁奖现场(朝阳区供图 千龙网发)</span></p>\n<p>10月30日，国贸中心、北京银泰中心等首批8个楼宇获颁六星级超甲级楼宇标识牌。</p>                </div>\n ";
        System.out.println(RegexTools.GetHtmlStyleByContent(content.replaceAll("\r|\n|\r\n", "<br/>").replaceAll("\"", "&quot;").trim()));
        System.out.println(RegexTools.GetHtmlStyleByContent(content.replaceAll("\r|\n|\r\n", "<br/>")));
    }
    

}
