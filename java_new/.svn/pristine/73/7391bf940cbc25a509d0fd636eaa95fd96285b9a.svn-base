package com.zhxg.yqzj.service.report;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.zhxg.framework.base.utils.OrientationUtil;
import com.zhxg.framework.base.utils.ParamsUtil;
import com.zhxg.yqzj.dao.v1.AllReportDataDao;
import com.zhxg.yqzj.dao.v1.ReportClassifyDao;
import com.zhxg.yqzj.entities.v1.Info;

public class ReportExcelTemplate {

    /**
     * 样式
     */
    private ReportStyle style;
    
    /**
     * 分组类型 0 不分组 1 媒体类型 2 倾向性 3 内容分类  4 顶级专题
     */
    private int groupType;
    
    /**
     * 分组字段 用于分sheet
     */
    private List<ReportGroupField> groupFields;
    
    
    /**
     * 字段列表
     */
    private List<ReportField> fields;
    
    private AllReportDataDao allReportDao;
    
    private Map<String,Object> paramMap;
    
    private ReportClassifyDao reportClassifyDao;


    public ReportStyle getStyle() {
        return style;
    }


    public void setStyle(ReportStyle style) {
        this.style = style;
    }


    public int getGroupType() {
        return groupType;
    }


    public void setGroupType(int groupType) {
        this.groupType = groupType;
    }


    public List<ReportGroupField> getGroupFields() {
        return groupFields;
    }


    public void setGroupFields(List<ReportGroupField> groupFields) {
        this.groupFields = groupFields;
    }


    public List<ReportField> getFields() {
        return fields;
    }


    public void setFields(List<ReportField> fields) {
        this.fields = fields;
    }
    
    public AllReportDataDao getAllReportDao() {
        return allReportDao;
    }


    
    public void setAllReportDao(AllReportDataDao allReportDao) {
        this.allReportDao = allReportDao;
    }


    
    public Map<String, Object> getParamMap() {
        return paramMap;
    }


    
    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    
    
    
    public ReportClassifyDao getReportClassifyDao() {
        return reportClassifyDao;
    }


    
    public void setReportClassifyDao(ReportClassifyDao reportClassifyDao) {
        this.reportClassifyDao = reportClassifyDao;
    }


    private List<Info> getList(Map<String,Object> params){
        return allReportDao.getDataForExport_self(params);
    } 
    
    private Map<Integer,String> getClassifyName(Map<String,Object> params){
        Map<Integer, String> classifyMap = reportClassifyDao.getReportClassifyName(params);
        classifyMap.put(0, "未分类");
        return classifyMap;
    }
    
    private Object[] getSelectParamArr(){
        List<String> paramList = new ArrayList<String>();
        for (ReportGroupField field : groupFields) {
            if(field.getSelected() == 1){
                paramList.add(field.getId());
            }
        }
        return paramList.toArray();
    }
    
    
    private Map<String,String> getSelectParamMap(){
        HashMap<String,String> map = new HashMap<>();
        for (ReportGroupField field : groupFields) {
            if(field.getSelected() == 1){
                map.put(field.getId(), field.getAlais());
            }
        }
        return map;
    }
    
    public void draw(XSSFWorkbook workBook){
        List<Object> sortArr = null;
        Map<Integer, String> classifyMap = this.getClassifyName(paramMap);
        List<Info> dataList = null;
        if(groupType == 0){
            dataList = this.getList(paramMap);
            XSSFSheet sheet = workBook.createSheet();
            drawExcel(workBook,sheet,dataList,classifyMap);
        }else{
            Object[] sortSelect = this.getSelectParamArr();
            Map<String, String> sortMap = this.getSelectParamMap();
            String key = 1 == groupType ? "sourceType" : 2 == groupType ? "orientation" : "classifyIds";
            Object obj = paramMap.get(key);
            if(obj == null){
                for (Object selectKey : sortSelect) {
                    sortArr = new ArrayList<>();
                    sortArr.add(selectKey);
                    paramMap.put(key, sortArr.toArray());
                    dataList = getList(paramMap);
                    XSSFSheet sheet = this.createSheetByName(workBook, classifyMap, selectKey,sortMap);
                    this.drawExcel(workBook, sheet, dataList, classifyMap);
                }
            }else{
                Object[] paramArr = (Object[])obj;
                for (Object param : paramArr) {
                    for (Object selectKey : sortSelect) {
                        if(param.equals(selectKey)){
                            sortArr = new ArrayList<>();
                            sortArr.add(selectKey);
                            paramMap.put(key, sortArr.toArray());
                            dataList = getList(paramMap);
                            XSSFSheet sheet = this.createSheetByName(workBook, classifyMap, selectKey,sortMap);
                            this.drawExcel(workBook, sheet, dataList, classifyMap);
                           }
                        }
                    }
                }   
            }
        
        }
    
    public void drawExcel(XSSFWorkbook workBook,XSSFSheet sheet,List<Info> dataList,Map<Integer, String> classifyMap){
        // 字体样式
        XSSFFont titleFont = workBook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 11);
        titleFont.setFontName("仿宋_GB2312");

        // 字体样式
        XSSFFont contentFont = workBook.createFont();
        contentFont.setFontHeightInPoints((short) 11);
        contentFont.setFontName("仿宋_GB2312");
        // 标题样式 垂直居中
        XSSFCellStyle titleType = workBook.createCellStyle();
        titleType.setAlignment(HorizontalAlignment.CENTER);
        titleType.setVerticalAlignment(VerticalAlignment.CENTER);
        titleType.setBorderTop(BorderStyle.THIN);
        titleType.setBorderBottom(BorderStyle.THIN);
        titleType.setBorderLeft(BorderStyle.THIN);
        titleType.setBorderRight(BorderStyle.THIN);
        titleType.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleType.setFillForegroundColor((short) 22);
        titleType.setFont(titleFont);

        // 内容样式 垂直居中
        XSSFCellStyle contentType = workBook.createCellStyle();
        contentType.setAlignment(HorizontalAlignment.CENTER);
        contentType.setVerticalAlignment(VerticalAlignment.CENTER);
        contentType.setBorderTop(BorderStyle.THIN);
        contentType.setBorderBottom(BorderStyle.THIN);
        contentType.setBorderLeft(BorderStyle.THIN);
        contentType.setBorderRight(BorderStyle.THIN);
        contentType.setFont(contentFont);

        // 内容样式 左对齐
        XSSFCellStyle leftType = workBook.createCellStyle();
        leftType.setAlignment(HorizontalAlignment.LEFT);
        leftType.setVerticalAlignment(VerticalAlignment.CENTER);
        leftType.setBorderTop(BorderStyle.THIN);
        leftType.setBorderBottom(BorderStyle.THIN);
        leftType.setBorderLeft(BorderStyle.THIN);
        leftType.setBorderRight(BorderStyle.THIN);
        leftType.setFont(contentFont);

        // 摘要样式 自动换行
        XSSFCellStyle abstractType = workBook.createCellStyle();
        abstractType.setAlignment(HorizontalAlignment.CENTER);
        abstractType.setVerticalAlignment(VerticalAlignment.CENTER);
        abstractType.setBorderTop(BorderStyle.THIN);
        abstractType.setBorderBottom(BorderStyle.THIN);
        abstractType.setBorderLeft(BorderStyle.THIN);
        abstractType.setBorderRight(BorderStyle.THIN);
        // 自动换行
        abstractType.setWrapText(true);
        abstractType.setFont(contentFont);
        
        
        //编写自定义标题
        int rownum = 0;
        XSSFRow titleRow = sheet.createRow(rownum++);
        titleRow.setHeightInPoints(25f);
        int cellnum = 0;
        for (ReportField field : fields) {
           if(field.getSelected() == 1){
               this.setCellWidth(sheet, field.getFieldKey(), cellnum);
               XSSFCell titleCell = titleRow.createCell(cellnum++);
               titleCell.setCellValue(field.getAlais());
               titleCell.setCellStyle(titleType);
           }
        }
        int selectNum = this.getSelectedNum();
        
        //编写信息内容
        int infoIndex = 1;
        for (Info info : dataList) {
            XSSFRow infoRow = sheet.createRow(rownum++);
            infoRow.setHeight((short)(23*20));
            int infocellNum = 0;
            for (ReportField field : fields) {
                if(field.getSelected() == 1){
                   Map<String, Object> infoMap = ParamsUtil.transToMAP(info, Info.class);
                   XSSFCell infoCell = infoRow.createCell(infocellNum++);
                   String fieldKey = field.getFieldKey();
                   switch(fieldKey){
                       case "index" :{
                           infoCell.setCellValue(infoIndex++);
                           infoCell.setCellStyle(contentType);
                           break;
                       }
                       case "infoUrl" :{
                           this.addCellLink(infoMap.get(field.getFieldKey())+"", infoCell);
                           infoCell.setCellStyle(leftType);
                           break;
                       }
                       
                       case "publishTime":{
                           this.addCellTime(infoMap.get(field.getFieldKey()), infoCell);
                           infoCell.setCellStyle(contentType);
                           break;
                       }
                       
                       case "webName": 
                       case "title": 
                       case "summary": 
                       case "keyword": {
                           infoCell.setCellValue(infoMap.get(field.getFieldKey())+""); 
                           infoCell.setCellStyle(leftType);
                           break;
                       }
                       
                       case "visitCount":
                       case "replyCount":{
                           infoCell.setCellValue(Integer.valueOf(infoMap.get(field.getFieldKey())+""));
                           infoCell.setCellStyle(contentType);
                           break;
                       }
                       
                       case "classifyId":{
                           infoCell.setCellValue(classifyMap.get(Integer.valueOf(infoMap.get(field.getFieldKey())+"")));
                           infoCell.setCellStyle(contentType);
                           break;
                       }
                       
                       case "orientation":{
                           infoCell.setCellValue(OrientationUtil.getSourceName(infoMap.get(field.getFieldKey())+""));
                           infoCell.setCellStyle(contentType);
                           break;
                       }
                       
                       default:{
                           infoCell.setCellValue(infoMap.get(field.getFieldKey())+"");
                           infoCell.setCellStyle(contentType);
                       }
                   }
                   
                }
               if(infocellNum == selectNum){
                   XSSFCell infoCell = infoRow.createCell(infocellNum);
                   infoCell.setCellValue(" ");
               } 
            }
        }
        //将信息修改为已入报
        this.setInfoStatus(dataList);
    }
    
    /**
     * 不同列设置不同列宽
     *
     * @param sheet
     * @param i
     */
    private  void setCellWidth(XSSFSheet sheet, String title, int i) {
        // 序号，浏览数，回复数
        if ("index".equals(title) || "visitCount".equals(title) || "replyCount".equals(title)) {
            sheet.setColumnWidth(i, 9 * 256);
            // 倾向性，媒体类型，摘要字数
        } else if ("orientation".equals(title) || "sourceType".equals(title)) {
            sheet.setColumnWidth(i, 11 * 256);
            // 作者，来源
        } else if ("author".equals(title) || "webName".equals(title)||"keyword".equals(title)||"classifyId".equals(title)) {
            sheet.setColumnWidth(i, 21 * 256);
            // 标题，原文链接，新闻摘要
        } else if ("title".equals(title) || "infoUrl".equals(title)) {
            sheet.setColumnWidth(i, 46 * 256);
        } else if ("publishTime".equals(title) || "抓取时间".equals(title)) {
            sheet.setColumnWidth(i, 21 * 256);
            // 新闻摘要
        } else if ("summary".equals(title)) {
            sheet.setColumnWidth(i, 31 * 256);
        }
    }
    
    /**
     * 添加超链接
     *
     * @param url
     * @param contentCell
     */
    public void addCellLink(String url, XSSFCell infoCell) {
        if (url.length() > 255 || url.indexOf("{") > 0) {
            infoCell.setCellValue(url);
        } else {
            infoCell.setCellFormula("HYPERLINK(\"" + url + "\")");
        }
    }
    
    public void addCellTime(Object time,XSSFCell infoCell){
        try {
            SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
            Date date = format.parse(time.toString());
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String newTime = format1.format(date);
            infoCell.setCellValue(newTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
    }
    
    private XSSFSheet createSheetByName(XSSFWorkbook workBook,Map<Integer, String> classifyMap,Object selectKey,Map<String, String> sortMap){
        XSSFSheet sheet = null;
        String name = "- -";
        switch(groupType){
            case 1:{
                name = sortMap.get(selectKey);
                sheet = workBook.createSheet(name);
                break;
            }
            case 2:{
                name = sortMap.get(selectKey);
                sheet = workBook.createSheet(name);
                break;
            }
            case 3:{
                name = sortMap.get(selectKey);
                sheet = workBook.createSheet(name);
                break;
            }
        }
        return sheet;
    }
    
    private void setInfoStatus(List<Info> dataList){
        if(dataList.size()>0){
            List<String> infoUuidList = new ArrayList<>(dataList.size());
            
            for (Info info : dataList) {
                infoUuidList.add(info.getInfoUuid());
            }
            paramMap.put("infoUuidArr", infoUuidList.toArray()); 
            allReportDao.setInfoStatus_self(paramMap);
        }
    }
    
    
    private int getSelectedNum(){
        int selectedNum =0;
        for (ReportField reportField : fields) {
            if(reportField.getSelected() == 1){
                selectedNum++;
            }
        }
        return selectedNum;
    }
}
