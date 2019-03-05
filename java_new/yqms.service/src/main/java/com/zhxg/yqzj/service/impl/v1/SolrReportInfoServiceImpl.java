package com.zhxg.yqzj.service.impl.v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.yqzj.dao.v1.AllReportDataDao;
import com.zhxg.yqzj.dao.v1.SolrDao;
import com.zhxg.yqzj.entities.v1.QueryResult;
import com.zhxg.yqzj.service.exception.DataReport.SolrReportInfoSaveException;
import com.zhxg.yqzj.service.exception.DataReport.SolrReportInfoSearchException;
import com.zhxg.yqzj.service.v1.SolrReportInfoService;

@Service
public class SolrReportInfoServiceImpl extends BaseServiceImpl<BaseEntity> implements SolrReportInfoService {

    @Autowired
    AllReportDataDao allReportDataDao;
    @Autowired
    private SolrDao solrDao;
    
    private final String SOLR_REPORT_INSERT_ERR = "SOLR添加数据到数据池失败";
    private final String SOLR_REPORT_SEARCH_ERR = "SOLR查询信息数据失败";
    
    @Override
    protected BaseDao<BaseEntity> getBaseDao() {
        return this.solrDao;
    }
    
    @Override
    public Map<String, Integer> saveSolrToReport(Map<String, Object> params) throws SolrReportInfoSaveException, SolrReportInfoSearchException{
        //查询添加的数据信息
        //List<Map<String, Object>> solrInfoList = this.getSolrInfo(params);
        //去除重复信息并添加信息到数据池
        Map<String, Integer> resultMap = this.getRepeatInfo(params);
        return resultMap;
    }
    
    /**
     * solr查询详细信息  
     * @param params
     * @return
     * @throws SolrReportInfoSaveException 
     * @throws SolrReportInfoSearchException 
     */
    private List<Map<String,Object>> getSolrInfo(Map<String, Object> params) throws SolrReportInfoSaveException, SolrReportInfoSearchException{
        //编写solr查询语句
        StringBuffer solrSql = new StringBuffer(500);
        String kvUuids = params.get("kvUuids").toString();
        if (StringUtils.isNotBlank(kvUuids)) {
            solrSql.append("kvUuid:(");
            solrSql.append(kvUuids.replace(",", " || "));
            solrSql.append(")");
        }
        String[] fileds = "kvUuid,kvTitle,kvAbstract,kvContent,kvSite,kvSourcetype,kvAuthor,kvUrl,kvOrientation,kvCtime,simHash".split(",");
        QueryResult solrList = null;
        try {
            solrList = solrDao.search(solrSql.toString(),solrSql.toString(), fileds, "kvCtime", ORDER.desc, 0,0,null);
        } catch (SysException e) {
            logger.error(e.getMessage(), e);
            throw new SolrReportInfoSearchException(SOLR_REPORT_SEARCH_ERR);
        }
        List<Map<String,Object>> resultlist = solrList.getResultlist();
        return resultlist;
    }
    
    
    /**
     * 去除重复信息 
     *
     * @param params
     * @param resultMap
     * @return
     * @throws SolrReportInfoSaveException 
     * @throws SolrReportInfoSearchException 
     */
    private Map<String, Integer> getRepeatInfo(Map<String, Object> params) throws SolrReportInfoSaveException, SolrReportInfoSearchException{
        List<Map<String, Object>> solrInfoList = null;
        //添加数据到数据池
        Map<String, Integer> resultMap = new HashMap<String,Integer>();
        //查询重复信息
        String[] paramIdArr = params.get("kvUuids").toString().split(",");
        params.put("infoUuidArr", paramIdArr);
        List<String> repeatUuidList = allReportDataDao.getRepeatUuid_self(params);
        List<String> paramIdList = new ArrayList<>();
        // 添加到集合
        if (paramIdArr != null && paramIdArr.length > 0) {
            for (String str : paramIdArr) {
                paramIdList.add(str);
            }
        }
        //去掉重复id
        if (repeatUuidList != null && !repeatUuidList.isEmpty()) {
            for (String kruuid : repeatUuidList) {
                paramIdList.remove(kruuid);
            }
        }
        
        if(paramIdList != null && !paramIdList.isEmpty()){
            String kvUuids = "";
            for (String kvUuid : paramIdList) {
                kvUuids += kvUuid +",";
            }
            kvUuids = kvUuids.substring(0, kvUuids.lastIndexOf(","));
            params.put("kvUuids",kvUuids);
            solrInfoList = this.getSolrInfo(params);
        }
        //添加信息到数据池
        int insertNum = 0;
        if(paramIdList != null && !paramIdList.isEmpty()){
            insertNum = this.insertInfoToReport(params, solrInfoList);
        }
        resultMap.put("successResult", insertNum);
        resultMap.put("faildResult", repeatUuidList.size());
        return resultMap;
    }
    
    /**
     * 添加到数据池
     *
     * @param params
     * @param warningInfoList
     * @return
     * @throws SolrReportInfoSaveException 
     */
    private int insertInfoToReport(Map<String, Object> params,List<Map<String, Object>> solrInfoList) throws SolrReportInfoSaveException{
        int successResult = 0;
        try {
            for (Map<String, Object> map : solrInfoList) {
                this.setInfoContent(params, map);
                int num = allReportDataDao.insertReportData_self(params);
                if (num != 0) {
                    successResult++;
                } 
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new SolrReportInfoSaveException(SOLR_REPORT_INSERT_ERR);
        }
        return successResult;
    }
    
    
    /**
     * 设置默认值
     */
    private void setInfoContent(Map<String, Object> params,Map<String, Object> map){
        params.put("infoUuid", map.get("kvUuid")+"");
        params.put("title", this.subTextInfo(map.get("kvTitle")+"", 40));
        params.put("summary", this.subTextInfo(map.get("kvAbstract")+"", 200));
        params.put("content", map.get("kvContent")+"");
        params.put("webName", map.get("kvSite")+"");
        params.put("sourceType", this.changeSourceType(map.get("kvSourcetype").toString()));
        params.put("author", map.get("kvAuthor")+"");
        params.put("infoUrl", map.get("kvUrl")+"");
        params.put("orientation", "5".equals(map.get("kvOrientation").toString())?"2":map.get("kvOrientation"));
        params.put("publishTime", map.get("kvCtime")+"");
        params.put("infoSimhash", this.changeTextInfo(map.get("simHash")+""));
        params.put("keyWord", " ");
        params.put("isWarning", 0);
        params.put("isAttention", 0);
        params.put("isRead", 0);
        params.put("visitCount", 0);
        params.put("replyCount", 0);
        params.put("authorPic", " ");
        params.put("domain", " ");
        params.put("imgUrl", " ");
        params.put("vedioUrl", " ");
        params.put("importanceWeight", "0");
        params.put("extendField", null);
        //params.put("classifyId", 0);
        params.put("enterTime", DateUtil.getLongDate());
        params.put("isExport", 0);
    }
    
    /**
     * 处理文本过长
     *
     * @param title
     * @param length
     * @return
     */
    private String subTextInfo(String text, int length) {
        if (text.length() > length) {
            return text.substring(0, length);
        } else {
            return text;
        }
    }
    
    /**
     * 处理文本特殊字符
     *
     * @param text
     * @return
     */
    private String changeTextInfo(String text){
        if(text.contains("[") || text.contains("]")){
            text = text.replace("[", ""); 
            text = text.replace("]", "");
        }
        return text;
    }
    
    private String changeSourceType(String source){
        
        if(source.length() == 1 && !"8".equals(source)){
            return 0+source;
        }else if("8".equals(source)){
            return "04";
        }else{
            return source;
        }
    }
}
