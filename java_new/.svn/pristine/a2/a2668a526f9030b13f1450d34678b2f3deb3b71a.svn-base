package com.zhxg.yqzj.service.impl.v1;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
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
import com.zhxg.framework.base.utils.ChineseCharecterUtil;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.framework.base.utils.PropertiesUtil;
import com.zhxg.framework.base.utils.UploadFileUtil;
import com.zhxg.yqzj.dao.v1.AllReportDataDao;
import com.zhxg.yqzj.dao.v1.ReportClassifyDao;
import com.zhxg.yqzj.dao.v1.ReportDao;
import com.zhxg.yqzj.dao.v1.ReportFileInfoDao;
import com.zhxg.yqzj.dao.v1.ReportVariableDao;
import com.zhxg.yqzj.dao.v1.SolrExportEmailDao;
import com.zhxg.yqzj.dao.v1.SubRelationDao;
import com.zhxg.yqzj.dao.v1.UserBaseInfoDao;
import com.zhxg.yqzj.dao.v1.UserDao;
import com.zhxg.yqzj.entities.v1.Report;
import com.zhxg.yqzj.entities.v1.ReportFileInfo;
import com.zhxg.yqzj.entities.v1.ReportTemplate;
import com.zhxg.yqzj.entities.v1.ReportVariable;
import com.zhxg.yqzj.entities.v1.SubRelation;
import com.zhxg.yqzj.entities.v1.UserMailExport;
import com.zhxg.yqzj.service.exception.DataReport.ReportClassifyDelException;
import com.zhxg.yqzj.service.exception.DataReport.ReportClassifyRepeatException;
import com.zhxg.yqzj.service.exception.DataReport.ReportClassifySaveException;
import com.zhxg.yqzj.service.exception.DataReport.ReportClassifyUpdateException;
import com.zhxg.yqzj.service.exception.report.ReportException;
import com.zhxg.yqzj.service.report.ReportChartComponent;
import com.zhxg.yqzj.service.report.ReportCustomListComponent;
import com.zhxg.yqzj.service.report.ReportExcelTemplate;
import com.zhxg.yqzj.service.report.ReportListComponent;
import com.zhxg.yqzj.service.report.ReportSignatureComponent;
import com.zhxg.yqzj.service.report.ReportTextComponent;
import com.zhxg.yqzj.service.v1.ReportService;

import jcifs.smb.SmbFile;
import sun.misc.BASE64Decoder;

@Service
public class ReportServiceImpl extends BaseServiceImpl<Report> implements ReportService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserBaseInfoDao userBaseInfoDao;
    @Autowired
    private SolrExportEmailDao emailDao;
    @Autowired
    private SubRelationDao relationDao;
    @Autowired
    private ReportDao reportDao;
    @Autowired
    private AllReportDataDao allReportDao;
    @Autowired
    private ReportVariableDao reportVariabeDao;
    @Autowired
    private ReportFileInfoDao reportFileInfoDao;
    @Autowired
    private ReportClassifyDao reportClassifyDao;

    private final String WORD_DOWNLOAD_PATH = "nas/reportData/";
    private final String PIC_DOWNLOAD_PATH = "nas/reportImg/";
    private final String INSERT_FILEINFO_ERR = "添加报告信息失败";
    private final String INSERT_TEMPLATE_ERR = "添加报告模板失败";
    private final String INSERT_VARIABLE_ERR = "添加变量默认值失败";
    private final String UADATE_TEMPLATE_ERR = "修改报告模板失败";
    private final String UPDATE_FILEINFO_ERR = "修改报告信息失败";
    private final String DELETE_TEMPLATE_ERR = "删除报告模板失败";
    private final String DELETE_FILEINFO_ERR = "删除报告信息失败";
    private final String TEMPLATE_REPEAT_ERR = "简报模板名称重复";
    private final String TEMPLATE_DEFAULT_ERR = "默认模板设置失败";
    private final String SAVE_USER_RECEIVEINFO = "保存用户接收日报信息失败";
    private final String SAVE_USER_CONDITION = "保存用户导出日报条件失败";
    private final String SAVE_IMG_ERR = "上传模板图片失败";

    @Override
    protected BaseDao<Report> getBaseDao() {
        return this.reportDao;
    }

    @Override
    public List<Report> getReportList(Map<String, Object> paramMap) throws SysException {
        List<Report> list;
        try {
            list = this.reportDao.getReportList_self(paramMap);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            throw new SysException();
        }
        return list;
    }

    @Override
    public List<Report> getExpertReportTreeList(Map<String, Object> paramMap) throws SysException {
        List<Report> list;
        try {
            list = this.reportDao.getExpertReportTreeList(paramMap);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            throw new SysException();
        }
        return list;
    }

    @Override
    public PageInfo<Report> getExpertReportList(Map<String, Object> paramMap, Pagination pageInfo) throws SysException {
        PageInfo<Report> list;
        try {
            list = this.reportDao.getExpertReportList(paramMap, pageInfo);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            throw new SysException();
        }
        return list;
    }

    @Override
    public List<Map<String, Integer>> getExpertReportCount(Map<String, Object> paramMap) {
        return this.reportDao.getExpertReportCount(paramMap);
    }

    @Override
    public int applyExpertReport(Report report) {
        return this.reportDao.insertExpertReport(report);
    }

    @Override
    public Map<String, String> getIsMyExpertReport(Map<String, Object> paramMap) {
        Map<String, String> map = new HashMap<>();
        List<Map<String, String>> isMyExpertReport = this.reportDao.getIsMyExpertReport(paramMap);
        if (isMyExpertReport != null && isMyExpertReport.size() > 0) {
            map.put("isMyExpertReport", "1");
        } else {
            map.put("isMyExpertReport", "0");
        }
        return map;

    }

    @Override
    public ReportTemplate getReportTemplate(Map<String, Object> paramMap) {
        return this.reportDao.getReportTemplate(paramMap);
    }

    @Override
    public PageInfo<ReportFileInfo> getReportFileInfos(Map<String, Object> paramMap, Pagination pageInfo) {
        return this.reportFileInfoDao.getReportFileInfos(paramMap, pageInfo);
    }
    
    
    @Override
    public int exportReport(Map<String, Object> paramMap) throws ReportClassifySaveException, ReportClassifyUpdateException{
        int result = 0;
        ReportTemplate template = getReportTemplate(paramMap);
        int type = template.getTemplateType();
        if(1 == type){
            result = this.exportWord(paramMap,template,type);
        }else{
            result = this.exportExcel(paramMap,template,type);
        }
        
        return result;
    }
    
    
    public int exportWord(Map<String, Object> paramMap,ReportTemplate template,int templateType) throws ReportClassifySaveException, ReportClassifyUpdateException {
        int result = 0;
        XWPFDocument doc = new XWPFDocument();
        //新建用户直接使用系统模板导出是需要增加系统默认变量值
        int variableNum = reportVariabeDao.getVariableNum(paramMap);
        if(variableNum==0){
            this.saveVariableDefault(paramMap);
        }
        ReportVariable reportVariable = reportVariabeDao.getReportVariable(paramMap);
        JSONObject templaleJson = template.getTemplate();
        JSONArray modules = templaleJson.getJSONArray("modules");
        modules.sort(new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((JSONObject) o1).getInteger("index") - ((JSONObject) o2).getInteger("index");
            }
        });
        for (Object module : modules) {
            switch (((JSONObject) module).getString("moduleType")) {
            case "text": {
                ReportTextComponent textComponent = JSON.toJavaObject((JSONObject) module, ReportTextComponent.class);
                textComponent.draw(doc, reportVariable);
                break;
            }
            case "reportAuthor": {
                ReportTextComponent textComponent = JSON.toJavaObject((JSONObject) module, ReportTextComponent.class);
                textComponent.drawAuthor(doc);
                break;
            }
            case "list": {
                ReportListComponent listComponent = JSON.toJavaObject((JSONObject) module, ReportListComponent.class);
                listComponent.setAllReportDao(allReportDao);
                listComponent.setParamMap(paramMap);
                listComponent.draw(doc);
                break;
            }
            case "chart": {
                ReportChartComponent chartComponent = JSON.toJavaObject((JSONObject) module, ReportChartComponent.class);
                chartComponent.setAllReportDao(allReportDao);
                chartComponent.setReportClassifyDao(reportClassifyDao);
                chartComponent.setParamMap(paramMap);
                chartComponent.draw(doc);
                break;
            }
            case "hastransverse": {
                ReportTextComponent textComponent = JSON.toJavaObject((JSONObject) module, ReportTextComponent.class);
                textComponent.drawHasTransverse(doc);
                break;
            }
            case "customSummary": {
                ReportCustomListComponent customSummary = JSON.toJavaObject((JSONObject) module, ReportCustomListComponent.class);
                customSummary.setAllReportDao(allReportDao);
                customSummary.setParamMap(paramMap);
                customSummary.drawCustomSummary(doc);
                break;
            }
            case "customList": {
                ReportCustomListComponent customList = JSON.toJavaObject((JSONObject) module, ReportCustomListComponent.class);
                customList.setAllReportDao(allReportDao);
                customList.setParamMap(paramMap);
                customList.drawCustomList(doc);
                break;
            }
            case "signature":{
                ReportSignatureComponent signatureComponent = JSON.toJavaObject((JSONObject)module, ReportSignatureComponent.class);
                signatureComponent.draw(doc);
                break;
            }
            
            }

        }

        OutputStream os = null;
        try {
             //String uuid = UUIDUtil.getUuid();
             //os = new FileOutputStream("D:\\"+uuid+".docx");
            os = this.getFilePath(paramMap,templateType);
            doc.write(os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                doc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 保存报告信息
            this.updateReportVariable(reportVariable, paramMap);
            result = this.saveReportInfo(paramMap);
        }
        return result;
    }
    
    public int exportExcel(Map<String, Object> paramMap,ReportTemplate template,int templateType) throws ReportClassifySaveException{
        int result = 0;
        
        XSSFWorkbook workBook = new XSSFWorkbook();
        
        JSONObject templaleJson = template.getTemplate();
        ReportExcelTemplate excelTemplate = JSON.toJavaObject(templaleJson, ReportExcelTemplate.class);
        excelTemplate.setAllReportDao(allReportDao);
        excelTemplate.setReportClassifyDao(reportClassifyDao);
        excelTemplate.setParamMap(paramMap);
        excelTemplate.draw(workBook);
        
        OutputStream os = null;
        try {
             //String uuid = UUIDUtil.getUuid();
             //os = new FileOutputStream("D:\\"+uuid+".xlsx");
            os = this.getFilePath(paramMap,templateType);
            workBook.write(os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                workBook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            result = this.saveReportInfo(paramMap);
        }
        return result;
    }
    
    
    
    private OutputStream getFilePath(Map<String, Object> paramMap,int templateType) {
        String nowTime = DateUtil.getLongDate();
        paramMap.put("reportFileName", nowTime);
        String filePath = "- -";
        if(1 == templateType){
            filePath = WORD_DOWNLOAD_PATH + paramMap.get("_KUID") + "/" + nowTime + ".docx";
        }else{
            filePath = WORD_DOWNLOAD_PATH + paramMap.get("_KUID") + "/" + nowTime + ".xlsx";
        }
        
        String downLoadUrl = "http://" + PropertiesUtil.getValue("file.server.domain") + "/" + filePath;
        paramMap.put("downLoadUrl", downLoadUrl);
        OutputStream out = UploadFileUtil.getInstance().getOut(filePath);
        return out;
    }

    private int saveReportInfo(Map<String, Object> paramMap) throws ReportClassifySaveException {
        int result = 0;
        try {
            result = reportFileInfoDao.insertReportFileInfo(paramMap);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ReportClassifySaveException(INSERT_FILEINFO_ERR);
        }
        return result;
    }

    private int updateReportVariable(ReportVariable reportVariable, Map<String, Object> params) throws ReportClassifyUpdateException {
        int result = 0;
        params.put("initValue", reportVariable.getValue());
        params.put("initTotal", reportVariable.getTotal());
        params.put("variableId", reportVariable.getId());
        try {
            result = reportVariabeDao.updateInitValue(params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ReportClassifyUpdateException(UADATE_TEMPLATE_ERR);
        }
        return result;
    }

    @Override
    public int insertReportTemplate(Map<String, Object> paramMap) throws ReportClassifySaveException, ReportClassifyRepeatException {
        int result = 0;
        int templateCount = reportDao.getTemplateCount(paramMap);
        if (templateCount != 0) {
            throw new ReportClassifyRepeatException(TEMPLATE_REPEAT_ERR);
        }
        try {
            int variableNum = reportVariabeDao.getVariableNum(paramMap);
            if(variableNum==0){
                this.saveVariableDefault(paramMap);
            }
            this.saveImgToServer(paramMap);
            result = reportDao.insertReportTemplate(paramMap);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ReportClassifySaveException(INSERT_TEMPLATE_ERR);
        }
        return result;
    }

    @Override
    public List<ReportTemplate> getAllReportTemplate(Map<String, Object> paramMap) {
        List<ReportTemplate> templateList = reportDao.getAllReportTemplate(paramMap);
        String templateId = userBaseInfoDao.getUserDefaultTemplate(paramMap);
        if(StringUtils.isBlank(templateId)){
            if("1".equals(paramMap.get("type")) && "1".equals(paramMap.get("templateType"))){
                List<ReportTemplate> list = new ArrayList<>();
                for (ReportTemplate reportTemplate : templateList) {
                    if(1 ==reportTemplate.getReportSelected() && 7 != reportTemplate.getId()){
                        break;
                    }
                    if(7 == reportTemplate.getId() && 1 !=reportTemplate.getReportSelected()){
                        reportTemplate.setReportSelected(1);
                        list.add(reportTemplate);
                    }
                }
                templateList.removeAll(list);
                list.addAll(templateList);
                return list;
            }
        }
            return templateList;
    }

    @Override
    public int updateReportTemplate(Map<String, Object> paramMap) throws ReportClassifyUpdateException, ReportClassifyRepeatException {
        int result = 0;
        int templateCount = reportDao.getTemplateCount(paramMap);
        if (templateCount != 0) {
            throw new ReportClassifyRepeatException(TEMPLATE_REPEAT_ERR);
        }
        try {
            if("1".equals(paramMap.get("templateType"))){
                this.saveImgToServer(paramMap);
            }
            result = reportDao.updateTemplate(paramMap);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ReportClassifyUpdateException(UADATE_TEMPLATE_ERR);
        }
        return result;
    }

    @Override
    public int deleteReportTemplate(Map<String, Object> paramMap) throws ReportClassifyDelException {
        int result = 0;
        try {
            result = reportDao.deleteTemplate(paramMap);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ReportClassifyDelException(DELETE_TEMPLATE_ERR);
        }
        return result;
    }

    @Override
    public int updateReportFileInfo(Map<String, Object> paramMap) throws ReportClassifyUpdateException {
        int result = 0;
        try {
            result = reportDao.updateReportFileInfo(paramMap);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ReportClassifyUpdateException(UPDATE_FILEINFO_ERR);
        }
        return result;
    }

    @Override
    public int deleteReportFileInfo(Map<String, Object> paramMap) throws ReportClassifyDelException {
        int result = 0;
        try {
            result = reportDao.deleteReportFileInfo(paramMap);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ReportClassifyDelException(DELETE_FILEINFO_ERR);
        }
        return result;
    }


    @Override
    public String batchDownloadFile(Map<String, Object> paramMap) {
        String selectFile = paramMap.get("filePath").toString();
        String[] selectFilePath = selectFile.split(",");
        String basePath = "";
        // 写入服务器的zip路径
        String nowTime = DateUtil.getLongDate();
        String zipName = WORD_DOWNLOAD_PATH + paramMap.get("_KUID") + "/" + nowTime + ".zip";
        String zipPath = "http://" + PropertiesUtil.getValue("file.server.domain") + "/" + zipName;

        InputStream is = null;
        BufferedInputStream bis = null;
        ZipOutputStream zos = new ZipOutputStream(UploadFileUtil.getInstance().getOut(zipName));
        try {
            for (String sourcefilename : selectFilePath) {
                SmbFile sourceFile = UploadFileUtil.getInstance().getIn(sourcefilename);
                zos.setEncoding("utf-8");
                zos.putNextEntry(new ZipEntry(basePath + sourceFile.getName()));
                is = sourceFile.getInputStream();
                bis = new BufferedInputStream(is);
                byte[] buf = new byte[1024];
                int length = -1;
                while ((length = bis.read(buf)) != -1) {
                    zos.write(buf, 0, length);
                    zos.flush();
                }
                zos.closeEntry();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            try {
                zos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return zipPath;
    }

    public void saveVariableDefault(Map<String,Object> paramMap) throws ReportClassifySaveException{
        try {
                this.operateParams(paramMap);
                paramMap.put("initValue", 1);
                paramMap.put("initTotal", 1);
                reportVariabeDao.insertReportVariable(paramMap);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ReportClassifySaveException(INSERT_VARIABLE_ERR); 
        }
    }
    //保存模板截图到文件服务器
    public void saveImgToServer(Map<String,Object> paramMap) throws ReportClassifySaveException{
        String templateImg = paramMap.get("templateImg")+"";
        templateImg = templateImg.replace("data:image/png;base64,", "");
        // 文件位置
        String imgName = PIC_DOWNLOAD_PATH + paramMap.get("_KUID") + "/" + DateUtil.getLongDate() + ".png";
        OutputStream out = null;
        // Base64解码
        try {
            @SuppressWarnings("restriction")
            byte[] b = new BASE64Decoder().decodeBuffer(templateImg);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 获取输出流
            out = UploadFileUtil.getInstance().getOut(imgName);
            //out = new FileOutputStream("D://"+UUIDUtil.getUuid()+".png");
            out.write(b);
            out.flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new ReportClassifySaveException(SAVE_IMG_ERR);
        } finally{
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String imgPath = "http://" + PropertiesUtil.getValue("file.server.domain") + "/" + imgName;
        paramMap.put("templateImg", imgPath);
    }
    
    private void operateParams(Map<String,Object> params){
        switch (Integer.valueOf(params.get("initType").toString())){
            case 1:
                params.put("initName", "day");
                params.put("initTimeStamp", System.currentTimeMillis()+"");
                params.put("timeInterval", 3600*24*1000+"");
                break;
            case 2:
                params.put("initName", "week");
                params.put("initTimeStamp", System.currentTimeMillis()+"");
                params.put("timeInterval", 7*3600*24*1000+"");
                break;
            case 3:
                params.put("initName", "auto");
                params.put("initTimeStamp", System.currentTimeMillis()+"");
                params.put("timeInterval", 0);
                break;
        }
    }

    @Override
    public int inserTemplateExcel(Map<String, Object> paramMap) throws ReportClassifySaveException, ReportClassifyRepeatException {
        int result = 0;
        int templateCount = reportDao.getTemplateCount(paramMap);
        if (templateCount != 0) {
            throw new ReportClassifyRepeatException(TEMPLATE_REPEAT_ERR);
        }
        try {
            result = reportDao.insertReportTemplate(paramMap);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ReportClassifySaveException(INSERT_TEMPLATE_ERR);
        }
        return result;
    }


    @Override
    public int getUserSubcribeStatus(Map<String, Object> paramMap) {
        return this.userDao.getUserSubcribeStatus(paramMap);
    }

    @Override
    public String getUserReceiveTime(Map<String, Object> paramMap) {
        return this.userDao.getUserReceiveTime(paramMap);
    }

    @Override
    public int saveUserReceiveInfo(Map<String, Object> paramMap) throws ReportException {
        int result = 0;
        try {
            //修改用户接收日报时间
            result = userDao.updateUserReceiveTime(paramMap);
            //添加or修改用户接收日报邮箱
            result = userBaseInfoDao.saveUserReceiveMail(paramMap);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ReportException("010", SAVE_USER_RECEIVEINFO);
        }
        return result;
    }

    @Override
    public int setUserDefaultTemplate(Map<String, Object> paramMap) throws ReportException {
        int result = 0;
        try {
            //添加or修改用户日报默认模板
            result = userBaseInfoDao.saveUserReceiveReportId(paramMap);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ReportException("011", TEMPLATE_DEFAULT_ERR);
        }
        return result;
    }
    
    @Override
    public String getUserDefaultTemplate(Map<String, Object> paramMap) throws ReportException {
        String result = "";
        try {
            //添加or修改用户日报默认模板
            result = userBaseInfoDao.getUserDefaultTemplate(paramMap);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ReportException("011", TEMPLATE_DEFAULT_ERR);
        }
        return result;
    }

    @Override
    public List<UserMailExport> getUserReceiveEmail(Map<String, Object> paramMap) {
        List<UserMailExport> emailList = null;
        String defaultMail = userBaseInfoDao.getUserReceiveMail(paramMap);
        //标记选中邮箱
        if(StringUtils.isNotBlank(defaultMail)){
            String[] emailIdArr = defaultMail.split(",");
            paramMap.put("emailIdArr", emailIdArr);
            emailList = emailDao.getUserDefaultEmail(paramMap);
            for (String emailId : emailIdArr) {
                for (UserMailExport userMail : emailList) {
                    if(emailId.equals(userMail.getKmUuid())){
                        userMail.setKmSelected("1");
                    }
                }
            }
        }else{
            emailList = emailDao.getUserDefaultEmail(paramMap);
        }
        return emailList;
    }

    @Override
    public List<SubRelation> getUserTopicList(Map<String, Object> paramMap) {
        List<SubRelation> userTopicList = relationDao.getUserTopicList(paramMap);
        for (SubRelation subRelation : userTopicList) {
            String kkName = subRelation.getKkName();
            String tags = ChineseCharecterUtil.getUpperCase(kkName, false);
            if(StringUtils.isNotBlank(tags)){
                subRelation.setTags(tags.substring(0,1));
            }
        }
        return userTopicList;
    }

    @Override
    public int setUserReportCondition(Map<String, Object> paramMap) throws ReportException {
        int result = 0;
        try {
            result = userDao.setUserReportCondition(paramMap);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ReportException("012", SAVE_USER_CONDITION);
        }
        return result;
    }

    @Override
    public String getUserReportCondition(Map<String, Object> paramMap) {
        String condition = userDao.getUserReportCondition(paramMap);
        JSONObject json = JSON.parseObject(condition);
        if(json.containsKey("KR_KEYWORDID")){
            json.put("subRelationId", json.get("KR_KEYWORDID").toString().split(","));
        }
        if(json.containsKey("KV_ORIENTATION")){
            json.put("orientation", json.get("KV_ORIENTATION").toString().split(","));
        }
        if(json.containsKey("KV_SOURCETYPE")){
            json.put("sourceType", json.get("KV_SOURCETYPE").toString().split(","));
        }
        return json.toJSONString();
    }

	@Override
	public int updateUserSubcribeStatus(Map<String, Object> paramMap) throws ReportException {
		int result = 0;
        try {
            result = userDao.updateUserSubcribeStatus(paramMap);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ReportException("012", SAVE_USER_CONDITION);
        }
        return result;
	}
    
}
