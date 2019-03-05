package com.zhxg.yqzj.service.impl.v1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhxg.framework.base.constants.SysConstants;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.ParamsNullException;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.framework.base.utils.RedisUtil;
import com.zhxg.framework.base.utils.SolrSearchUtils;
import com.zhxg.framework.base.utils.UUIDUtil;
import com.zhxg.yqzj.dao.v1.SolrDao;
import com.zhxg.yqzj.dao.v1.SolrExportConditionDao;
import com.zhxg.yqzj.dao.v1.SolrExportEmailDao;
import com.zhxg.yqzj.dao.v1.UserDao;
import com.zhxg.yqzj.entities.v1.QueryResult;
import com.zhxg.yqzj.entities.v1.SolrExportCondition;
import com.zhxg.yqzj.entities.v1.User;
import com.zhxg.yqzj.entities.v1.UserMailExport;
import com.zhxg.yqzj.service.exception.solr.SolrAddEmailRepeatException;
import com.zhxg.yqzj.service.exception.solr.SolrAddExportEmailException;
import com.zhxg.yqzj.service.exception.solr.SolrException;
import com.zhxg.yqzj.service.exception.solr.SolrExportFileException;
import com.zhxg.yqzj.service.exception.solr.SolrExportStatusException;
import com.zhxg.yqzj.service.exception.solr.SolrOperateExportConditionException;
import com.zhxg.yqzj.service.util.ExcelExportUtil;
import com.zhxg.yqzj.service.v1.SolrExportService;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.service
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: <对此类的描述>
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.yqzj.service.impl.v1.SolrExportServiceImpl.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年5月8日
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
public class SolrExportServiceImpl extends BaseServiceImpl<BaseEntity> implements SolrExportService {

    @Autowired
    SolrExportEmailDao solrExportEmailDao;
    @Autowired
    SolrExportConditionDao solrExportConditionDao;
    @Autowired
    SolrDao solrDao;
    @Autowired
    private UserDao userDao;

    @Override
    protected BaseDao<BaseEntity> getBaseDao() {
        return this.solrExportEmailDao;
    }

    private static final String EXPORTEMAIL_ADD_ERROR = "邮箱添加失败";
    private static final String EXPORTCONDITION_ADD_ERROR = "添加导出字段失败";
    private static final String EXPORTCONDITION_DELETE_ERROR = "删除导出字段失败";
    private static final String EXPORTEMAIL_REPEAT_ERROR = "邮箱已存在，请勿重复添加";
    private static final String EXPORT_STATUS_ERROR = "文件正在导出，请稍等";
    private static final String EXPORT_FILE_ERROR = "文件导出失败";

    private final static Logger log = LoggerFactory.getLogger(SolrExportServiceImpl.class);

    /**
     * 获取导出邮箱列表
     */
    @Override
    public List<UserMailExport> getExportEmail(Map<String, Object> params) {
        // 获取个人信息邮箱
        String userEmail = solrExportEmailDao.getUserEmail(params);
        // 获取导出邮箱列表
        List<UserMailExport> listEmail = solrExportEmailDao.getExportEmail(params);
        // 去除重复邮箱
        this.removeRepeat(listEmail, userEmail);
        return listEmail;
    }

    /**
     * 去除重复的邮箱
     */
    private void removeRepeat(List<UserMailExport> listEmail, String userEmail) {
        if (StringUtils.isNotBlank(userEmail)) {
            for (UserMailExport userMailExport : listEmail) {
                String kmEmail = userMailExport.getKmEmail();
                if (userEmail.equals(kmEmail)) {
                    listEmail.remove(userMailExport);
                    break;
                }
            }
            UserMailExport userMailExport = new UserMailExport();
            userMailExport.setKmEmail(userEmail);
            listEmail.add(0, userMailExport);
        }
    }

    /**
     * 添加导出邮箱
     * 
     * @throws SolrAddExportEmailException
     * @throws SolrAddEmailRepeatException
     */
    @Override
    public int setExportEmail(Map<String, Object> params)
            throws SolrAddExportEmailException, SolrAddEmailRepeatException {
        int result = 0;
        // 查询邮箱是否存在
        int countByEmail = solrExportEmailDao.getCountByEmail(params);
        if (countByEmail != 0) {
            throw new SolrAddEmailRepeatException(EXPORTEMAIL_REPEAT_ERROR);
        }
        String kmUuid = UUIDUtil.getUuid();
        String kmTime = DateUtil.getLongDate();
        params.put("kmUuid", kmUuid);
        params.put("kmTime", kmTime);
        params.put("kmSelected", "0");
        try {
            result = solrExportEmailDao.setExportEmail(params);
        } catch (Exception e) {
            log.error("-------------------" + e.getMessage());
            throw new SolrAddExportEmailException(EXPORTEMAIL_ADD_ERROR);
        }

        return result;
    }

    /**
     * 获取筛选条件
     */
    @Override
    public List<Map<String, List<SolrExportCondition>>> getExportCondition(Map<String, Object> params) {
        List<SolrExportCondition> exportCondition = solrExportConditionDao.getExportCondition(params);
        // 将有效字段与无效字段拆分
        List<Map<String, List<SolrExportCondition>>> splitCondition = this.splitCondition(exportCondition);
        return splitCondition;
    }

    /**
     * 添加筛选条件
     * 
     * @throws SolrOperateExportConditionException
     * @throws ParamsNullException
     */
    @Override
    public int setExportCondition(Map<String, Object> params, SolrExportCondition[] solrExportCondition)
            throws SolrOperateExportConditionException {
        int result = 0;
        try {
            // 添加导出字段 先删除用户下历史导出字段
            result = solrExportConditionDao.deleteExportCondition(params);
            // 设置参数
            List<SolrExportCondition> conditionList = this.arrTOList(solrExportCondition);
            // 拼接无效字段
            this.addInvalidField(conditionList, params);
            params.put("conditionList", conditionList);
            result = solrExportConditionDao.setExportCondition(params);
        } catch (Exception e) {
            log.error("-------------------" + e.getMessage());
            throw new SolrOperateExportConditionException(EXPORTCONDITION_ADD_ERROR);
        }
        return result;
    }

    /**
     * 查询用户导出状态
     * 
     * @throws Exception
     */
    @Override
    public String getExportStatus(Map<String, Object> params) throws Exception {
        String kuId = String.valueOf(params.get("_KUID"));
        String key = "exportStatus" + kuId;
        String status = RedisUtil.getStr(key);
        if (StringUtils.isNotBlank(status)) {
            // 若不为空说明程序正在导出
            throw new SolrExportStatusException(EXPORT_STATUS_ERROR);
        }
        return status;
    }

    /**
     * 将数组转换成集合，方便添加数据
     *
     * @param solrExportCondition
     * @return
     */
    public List<SolrExportCondition> arrTOList(SolrExportCondition[] solrExportCondition) {
        List<SolrExportCondition> conditionList = new ArrayList<SolrExportCondition>();
        for (SolrExportCondition exportCondition : solrExportCondition) {
            conditionList.add(exportCondition);
        }
        return conditionList;
    }

    /**
     * 删除历史导出字段
     * 
     * @throws SolrOperateExportConditionException
     */
    @Override
    public int deleteExportCondition(Map<String, Object> params) throws SolrOperateExportConditionException {
        int result = 0;
        try {
            result = solrExportConditionDao.deleteExportCondition(params);
        } catch (Exception e) {
            log.error("-------------------" + e.getMessage());
            throw new SolrOperateExportConditionException(EXPORTCONDITION_DELETE_ERROR);
        }
        return result;
    }

    /**
     * 导出excel
     * 
     * @throws SolrException
     * @throws SolrExportFileException
     * @throws IOException
     */
    @Override
    public int exportFile(Map<String, Object> params) throws SolrException, SolrExportFileException {
        this.solrExportExcel(params);
        return 0;
    }

    /**
     * 获取solr数据
     *
     * @param paramMap
     * @return
     * @throws SysException
     */
    private List<Map<String, Object>> getSolrList(Map<String, Object> paramMap) throws SysException {
        // 拼写solr查询语句
//        User user = userDao.getUserByKuId(paramMap.get(SysConstants._KUID)+"");
//        paramMap.put("isAbroadOpen", user.getIsAbroadOpen());
        HashMap<String, Object> sqlMap = SolrSearchUtils.getSolrSearchSql(paramMap);
        String filterSql = String.valueOf(sqlMap.get("filterSql"));
        String messageSql = String.valueOf(sqlMap.get("messageSql"));
        String searchType = String.valueOf(paramMap.get("searchType"));
        // 调用solr查询方法查询信息
        String[] fileds = "kvUuid,kvOrientation,kvTitle,kvSite,kvUrl,kvAuthor,kvAbstract,kvVisitcount,kvReply,kvSourcetype,kvCtime,kvDkTime,kvAbstract"
                .split(",");
        String orderBy = "kvCtime";
        QueryResult result = solrDao.search(messageSql, filterSql, fileds, orderBy, ORDER.desc, 0, 2000,searchType);
        List<Map<String, Object>> resultlist = result.getResultlist();
        return resultlist;
    }

    /**
     * 将有效与无效导出字段分开
     *
     * @param exportCondition
     * @return
     */
    private List<Map<String, List<SolrExportCondition>>> splitCondition(List<SolrExportCondition> conditionList) {
        List<Map<String, List<SolrExportCondition>>> endList = new ArrayList<>();
        HashMap<String, List<SolrExportCondition>> validMap = new HashMap<>();
        HashMap<String, List<SolrExportCondition>> invalidMap = new HashMap<>();
        List<SolrExportCondition> invalidList = new ArrayList<>();

        for (SolrExportCondition solrExportCondition : conditionList) {
            String valid = solrExportCondition.getValid();

            if ("0".equals(valid)) {
                invalidList.add(solrExportCondition);
            }

        }
        conditionList.removeAll(invalidList);
        validMap.put("valid", conditionList);
        invalidMap.put("invalid", invalidList);
        endList.add(validMap);
        endList.add(invalidMap);
        return endList;
    }

    /**
     * 将无效字段拼接到集合中
     *
     * @param conditionList
     */
    private void addInvalidField(List<SolrExportCondition> conditionList, Map<String, Object> params) {
        List<SolrExportCondition> allFieldList = solrExportConditionDao.getAllCondition(params);
        List<SolrExportCondition> removeList = new ArrayList<>();

        for (SolrExportCondition shortCondition : conditionList) {
            String fieldId = shortCondition.getFieldId();
            for (SolrExportCondition longCondition : allFieldList) {
                String field = longCondition.getFieldId();
                if (fieldId.equals(field)) {
                    removeList.add(longCondition);
                    break;
                }
            }
        }
        allFieldList.removeAll(removeList);
        String userId = String.valueOf(params.get("_KUID"));
        int startNum = conditionList.size() + 1;
        for (int i = 0; i < allFieldList.size(); i++) {
            SolrExportCondition solrExportCondition = allFieldList.get(i);
            solrExportCondition.setUserId(userId);
            solrExportCondition.setValid("0");
            solrExportCondition.setSequences(String.valueOf(startNum + i));
        }
        conditionList.addAll(allFieldList);
    }

    /**
     * 转换导出字段格式
     *
     * @param conditionList
     * @return
     */
    private List<String> conditionToList(List<SolrExportCondition> conditionList) {
        List<String> validList = new ArrayList<>();
        for (SolrExportCondition solrExportCondition : conditionList) {
            validList.add(solrExportCondition.getFieldName());
        }
        return validList;
    }

    /**
     * 转换导出字段格式
     *
     * @param conditionList
     * @return
     */
    private List<String> keyToList(List<SolrExportCondition> conditionList) {
        List<String> keyList = new ArrayList<>();
        for (SolrExportCondition solrExportCondition : conditionList) {
            keyList.add(solrExportCondition.getSolrId());
        }
        return keyList;
    }

    /**
     * 另起线程查询并导出文件
     *
     * @param params
     * @throws SolrException
     */
    private void solrExportExcel(Map<String, Object> params) throws SolrException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(new Runnable() {

            @Override
            public void run() {
                
                List<Map<String, Object>> solrList = null;
                try {
                    solrList = getSolrList(params);
                    // 获取用户自定义导出字段
                    List<SolrExportCondition> validCondition = null;
                    validCondition = solrExportConditionDao.getValidCondition(params);
                    if (validCondition.size() == 0) {
                        validCondition = solrExportConditionDao.getAllCondition(params);
                    }
                    // 改造导出参数放边导出
                    List<String> validList = conditionToList(validCondition);
                    List<String> keyList = keyToList(validCondition);
                    // 导出文件到服务器并发送邮件
                    ExcelExportUtil.exportExcel(solrList, validList, keyList, params);
                } catch (IOException e) {
                    log.error("-------------------" + e.getMessage());
                } catch (SolrException e) {
                    log.error("-------------------" + e.getMessage());
                } catch (SysException e) {
                    log.error("-------------------" + e.getMessage());
                }
            }
        });
    }
}
