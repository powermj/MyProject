package com.zhxg.yqzj.service.util;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.zhxg.framework.base.constants.Constant;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.framework.base.utils.HttpUtil;
import com.zhxg.framework.base.utils.PropertiesUtil;
import com.zhxg.framework.base.utils.RedisUtil;
import com.zhxg.framework.base.utils.SourceTypeUtil;
import com.zhxg.framework.base.utils.UploadFileUtil;
import com.zhxg.yqzj.entities.v1.TopicEventRegion;
import com.zhxg.yqzj.service.exception.myfocus.SendEmailErrorException;
import com.zhxg.yqzj.service.exception.solr.SolrExportFileException;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: <导出excel>
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.utils.ExcelExportUtil.java
 * <p>
 * Author: fujiqiu
 * <p>
 * Create Date: 2018年5月11日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public class ExcelExportUtil {

    private static final String SEND_EMAIL_ERROR = "邮件发送失败";
    private static final String EXPORT_MEDIA_TITLE = "媒体列表";
    private static final String EXPORT_MEDIA_COLOUMN = "序号,媒体名称,文章数量,媒体类型,地域";
    private static final String ERROR_MEDIA_URL = "info.zjktx.cn";
    private static final String COM_MEDIA_URL = "mp.weixin.qq.com";
    private final static Logger log = LoggerFactory.getLogger(ExcelExportUtil.class);

    /**
     * 导出2007excel
     *
     * @param solrList
     * @param validCondition
     * @return
     * @throws IOException
     * @throws SolrExportFileException
     */
    @SuppressWarnings({ "unused" })
    public static String exportExcel(List<Map<String, Object>> solrList, List<String> validConditonList,
            List<String> keyList, Map<String, Object> params)
            throws IOException {
        // 创建2007工作簿
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 创建工作表
        XSSFSheet workSheet = workBook.createSheet("sheet1");
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

        // 创建标题行
        XSSFRow titleRow = workSheet.createRow(0);
        titleRow.setHeightInPoints(25f);
        // 创建单元格
        XSSFCell titleCell = null;
        // 写入导出字段
        Map<String, String> titleMap = null;
        for (int i = 0; i < validConditonList.size(); i++) {
            String title = validConditonList.get(i);
            setCellWidth(workSheet, title, i);
            titleCell = titleRow.createCell(i);
            titleCell.setCellValue(title);
            titleCell.setCellStyle(titleType);
        }
        // 写入导出内容
        XSSFRow contentRow = null;
        XSSFCell contentCell = null;
        for (int i = 0; i < solrList.size(); i++) {
            int rowNum = 1;
            Map<String, Object> contentMap = solrList.get(i);
            rowNum += i;
            contentRow = workSheet.createRow(rowNum);
            contentRow.setHeightInPoints(25f);
            // 写入导出字段对应内容
            for (int j = 0; j < keyList.size(); j++) {
                contentCell = contentRow.createCell(j);
                String solrId = keyList.get(j);
                if ("kvSerial".equals(solrId)) {
                    // workSheet.setColumnWidth(j, 9);
                    contentCell.setCellValue(Integer.valueOf(rowNum));
                    contentCell.setCellStyle(contentType);
                } else if ("kvOrientation".equals(solrId)) {
                    // 倾向性
                    String kvOrientation = String.valueOf(contentMap.get(solrId));
                    contentCell.setCellValue(
                            "3".equals(kvOrientation) ? "中性" : "1".equals(kvOrientation) ? "正面" : "负面");
                    contentCell.setCellStyle(contentType);
                } else if ("kvTitle".equals(solrId)) {
                    // 标题添加超链接
                    String url = String.valueOf(contentMap.get("kvUrl"));
                    String title = String.valueOf(contentMap.get(solrId));
                    addLinkToTitle(title, url, contentCell);
                    contentCell.setCellStyle(leftType);
                } else if ("kvSite".equals(solrId)) {
                    // 来源
                    String site = String.valueOf(contentMap.get(solrId));
                    contentCell.setCellValue(site);
                    contentCell.setCellStyle(contentType);
                } else if ("kvUrl".equals(solrId)) {
                    // 添加超链接
                    String url = String.valueOf(contentMap.get(solrId));
                    addCellLink(url, contentCell);
                    contentCell.setCellStyle(leftType);
                } else if ("kvAuthor".equals(solrId)) {
                    // 作者
                    String author = String.valueOf(contentMap.get(solrId));
                    contentCell.setCellValue(author);
                    contentCell.setCellStyle(contentType);
                } else if ("kvVisitcount".equals(solrId)) {
                    // 浏览数
                    String kvVisitcount = String.valueOf(contentMap.get(solrId));
                    contentCell.setCellValue(Integer.valueOf(kvVisitcount));
                    contentCell.setCellStyle(contentType);
                } else if ("kvReply".equals(solrId)) {
                    // 回复数
                    String kvReply = String.valueOf(contentMap.get("kvReply"));
                    contentCell.setCellValue(Integer.valueOf(kvReply));
                    contentCell.setCellStyle(contentType);
                } else if ("kvSourcetype".equals(solrId)) {
                    // 媒体类型
                    String kvSourcetype = String.valueOf(contentMap.get(solrId));
                    String solrSourceName = SourceTypeUtil.getSolrSourceName(kvSourcetype);
                    contentCell.setCellValue(solrSourceName);
                    contentCell.setCellStyle(contentType);
                } else if ("kvCtime".equals(solrId) || "kvDkTime".equals(solrId)) {
                    // 发布时间||抓取时间
                    String oldTime = String.valueOf(contentMap.get(solrId));
                    String newTime = getNewTime(oldTime);
                    contentCell.setCellValue(newTime);
                    contentCell.setCellStyle(contentType);
                } else if ("kvAbstractLength".equals(solrId)) {
                    int abstractLength = String.valueOf(contentMap.get("kvAbstract")).length();
                    contentCell.setCellValue(Integer.valueOf(abstractLength));
                    contentCell.setCellStyle(contentType);
                } else {
                    String content = String.valueOf(contentMap.get(solrId));
                    contentCell.setCellValue(content);
                    contentCell.setCellStyle(abstractType);
                }
                // 添加一列空字符串 防止文本超出单元格
                if (j == keyList.size() - 1) {
                    contentCell = contentRow.createCell(keyList.size());
                    contentCell.setCellValue(" ");
                }
            }
        }
        String userId = String.valueOf(params.get("_KUID"));
        String key = "exportStatus" + userId;
        boolean exportStatus = true;
        String longDatemm = DateUtil.getLongDatemm();
        String fileName = "全网搜索" + "_" + longDatemm + ".xlsx";
        String serverPath = userId + "/" + "excel" + "/" + fileName;
        OutputStream out = UploadFileUtil.getInstance().getOut(serverPath);
        try {
            workBook.write(out);
        } catch (Exception e) {
            log.error("-------------------" + e.getMessage());
            exportStatus = false;
        } finally {
            try {
                out.close();
                workBook.close();
                // 导出文件完毕之后删除定时key
                RedisUtil.delete(key);
                // 发送邮件
                String downloadAddress = "http://" + PropertiesUtil.getValue("file.server.domain") + "/"
                        + serverPath;
                // 设置a标签
                String downloadLink = "<a href='" + downloadAddress + "'>" + downloadAddress + "</a>";
                String email = String.valueOf(params.get("email"));
                sendEmail(email, downloadLink, exportStatus);
            } catch (IOException e) {
                log.error("-------------------" + e.getMessage());
            } catch (SendEmailErrorException e) {
                log.error("-------------------" + e.getMessage());
            }
        }
        return null;
    }


    /**
     * 活跃媒体导出excel2007 
     * @return
     * @throws IOException 
     */
    public static String exportExcelForActiveMedia(List<TopicEventRegion> exportMediaInfoList,Map<String, Object> params) {
        //创建2007工作簿
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 创建工作表
        XSSFSheet workSheet = workBook.createSheet("sheet1");
        workSheet.setColumnWidth(0, 25*256);
        workSheet.setColumnWidth(1, 25*256);
        workSheet.setColumnWidth(2, 25*256);
        workSheet.setColumnWidth(3, 25*256);
        workSheet.setColumnWidth(4, 37*256);
        //内容样式
        XSSFCellStyle contentType = workBook.createCellStyle();
        contentType.setAlignment(HorizontalAlignment.CENTER);
        contentType.setVerticalAlignment(VerticalAlignment.CENTER);
        //标题合并五个单元格
        workSheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
        XSSFRow titleRow = workSheet.createRow(0);
        titleRow.setHeight((short)(28*20));
        XSSFCell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(EXPORT_MEDIA_TITLE);
        titleCell.setCellStyle(contentType);
        
        //写入具体内容
        int rowNum = 1;
        int coloumnNum = 0;
        String[] coloumnNames = EXPORT_MEDIA_COLOUMN.split(",");
        XSSFRow coloumnNameRow = workSheet.createRow(rowNum);
        coloumnNameRow.setHeight((short)(28*20));
        for (String coloumnName : coloumnNames) {
            XSSFCell coloumnRowTitle = coloumnNameRow.createCell(coloumnNum++);
            coloumnRowTitle.setCellValue(coloumnName);
            coloumnRowTitle.setCellStyle(contentType);
        }
        int num = 1;
        for (TopicEventRegion topicEventRegion : exportMediaInfoList) {
            //将导出内容转换成数组
            String[] contentArr = changeContentToArr(num,topicEventRegion);
            //创建行
            XSSFRow contentRow = workSheet.createRow(++rowNum);
            contentRow.setHeight((short)(28*20));
            XSSFCell content = null;
            for(int i = 0 ;i<contentArr.length-1;i++){
                //写入内容
                content = contentRow.createCell(i);
                if(i==0){
                    content.setCellValue(Integer.valueOf(contentArr[i]));
                    content.setCellStyle(contentType);
                    continue;
                }
                if(i==1){
                    addLinkToSite(contentArr[i], contentArr[contentArr.length-1], content);
                    content.setCellStyle(contentType);
                    continue;
                }
                if(i==2){
                    content.setCellValue(Integer.valueOf(contentArr[i]));
                    content.setCellStyle(contentType);
                    continue;
                }
                content.setCellValue(contentArr[i]);
                content.setCellStyle(contentType);
            }
            num++;
        }
        
        String userId = String.valueOf(params.get("_KUID"));
        String longDatemm = DateUtil.getLongDatemm();
        String fileName = "活跃媒体" + "_" + longDatemm + ".xlsx";
        String serverPath = userId + "/" + "excel" + "/" + fileName;
        OutputStream out = UploadFileUtil.getInstance().getOut(serverPath);
        try {
            workBook.write(out);
        } catch (IOException e) {
            log.error("----------exportForActiveMedia-----------" + e.getMessage());
        } finally{
            try {
                out.close();
                workBook.close();
            } catch (IOException e) {
                log.error("----------exportForActiveMedia-----------" + e.getMessage());
            }
        }
        return "http://" + PropertiesUtil.getValue("file.server.domain") + "/" + serverPath;
    }
    
    /**
     * 添加超链接
     *
     * @param url
     * @param contentCell
     */
    public static void addCellLink(String url, XSSFCell contentCell) {
        if (url.length() > 255 || url.indexOf("{") > 0) {
            contentCell.setCellValue(url);
        } else {
            contentCell.setCellFormula("HYPERLINK(\"" + url + "\")");
        }
    }

    /**
     * 标题添加超链接
     *
     * @param title
     * @param url
     * @param contentCell
     */
    public static void addLinkToTitle(String title, String url, XSSFCell contentCell) {
        // 替换标题中的特殊字符
        String newTitle = title.replaceAll("＂", "");
        if (newTitle.length() > 200) {
            newTitle = newTitle.substring(0, 200);
        }
        // 超链接添加
        if (url.length() > 255 || url.indexOf("{") > 0) {
            contentCell.setCellValue(newTitle);
        } else {
            contentCell.setCellFormula("HYPERLINK(\"" + url + "\",\"" + newTitle.replace("\"", "") + "\")");
        }
    }
    /**
     * 媒体名称添加域名链接
     *
     * @param title
     * @param url
     * @param contentCell
     */
    public static void addLinkToSite(String title, String url, XSSFCell contentCell) {
        if(url.contains(ERROR_MEDIA_URL)){
            url = COM_MEDIA_URL;
        }
        String comUrl = "http://"+url;
        // 替换标题中的特殊字符
        if (title.length() > 200) {
            title = title.substring(0, 200);
        }
        // 超链接添加
        if (url.length() > 255 || url.indexOf("{") > 0) {
            contentCell.setCellValue(title);
        } else {
            contentCell.setCellFormula("HYPERLINK(\"" + comUrl + "\",\"" + title.replace("\"", "") + "\")");
        }
    }

    /**
     * 发送邮件
     *
     * @param email
     * @param downloadAddress
     * @throws SendEmailErrorException
     */
    public static void sendEmail(String email, String downloadAddress, Boolean exportStatus)
            throws SendEmailErrorException {
        // 邮件内容
        StringBuilder sb = new StringBuilder();
        if (exportStatus) {
            sb.append("尊敬的用户：您好，全部数据导出成功！\n");
            sb.append("    下载地址：").append(downloadAddress).append("\n");
            sb.append("    如果链接不能点击，请复制地址到浏览器，然后直接打开。").append("\n");
        } else {
            sb.append("尊敬的用户：您好，全部数据导出失败，请重新导出！\n");
        }
        // 多个邮箱分隔成数组
        String[] emailArray = email.split(",");
        for (String str : emailArray) {
            try {
                Map<String, String> map = new HashMap<>();
                map.put("title", Base64.encodeBase64URLSafeString("舆情监测数据".getBytes("UTF-8")));
                map.put("content", Base64.encodeBase64URLSafeString(sb.toString().getBytes("UTF-8")));
                map.put("email", str);
                // 内容和标题以base64编码格式，默认不编码
                map.put("encode", "base64");
                // 以纯文本邮件发送，默认为HTML格式
                // map.put("type", "text");

                HttpUtil.post(Constant.PUSH_SERVER_URL, JSONObject.toJSONString(map));

            } catch (Exception e) {
                throw new SendEmailErrorException(SEND_EMAIL_ERROR);
            }
        }
    }

    /**
     * 不同列设置不同列宽
     *
     * @param sheet
     * @param i
     */
    private static void setCellWidth(XSSFSheet sheet, String title, int i) {
        // 序号，浏览数，回复数
        if ("序号".equals(title) || "浏览数".equals(title) || "回复数".equals(title)) {
            sheet.setColumnWidth(i, 9 * 256);
            // 倾向性，媒体类型，摘要字数
        } else if ("倾向性".equals(title) || "媒体类型".equals(title) || "摘要字数".equals(title)) {
            sheet.setColumnWidth(i, 11 * 256);
            // 作者，来源
        } else if ("作者".equals(title) || "来源".equals(title)) {
            sheet.setColumnWidth(i, 21 * 256);
            // 标题，原文链接，新闻摘要
        } else if ("标题".equals(title) || "原文链接".equals(title)) {
            sheet.setColumnWidth(i, 46 * 256);
        } else if ("发布时间".equals(title) || "抓取时间".equals(title)) {
            sheet.setColumnWidth(i, 21 * 256);
            // 新闻摘要
        } else if ("新闻摘要".equals(title)) {
            sheet.setColumnWidth(i, 31 * 256);
        }
    }

    /**
     * 转换时间格式
     *
     * @param oldTime
     * @return
     */
    private static String getNewTime(String time) {
        String newTime = null;
        try {
            SimpleDateFormat oldFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Date oldTime = oldFormat.parse(time);
            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            newTime = newFormat.format(oldTime);
        } catch (ParseException e) {
            log.error("-------------------" + e.getMessage());
        }
        return newTime;
    }
    /**
     * 将导出内容转换成数组方便导出 
     * @param topicEventRegion
     * @return
     */
    private static String[] changeContentToArr(int num,TopicEventRegion topicEventRegion){
        //获取活跃媒体导出内容
        String knSite = topicEventRegion.getKnSite();
        String knTypeName = topicEventRegion.getKnTypeName();
        String siteNum = String.valueOf(topicEventRegion.getSiteNum());
        String regionName = topicEventRegion.getRegionName();
        String webNameDomain = topicEventRegion.getWebNameDomain();
        String serialNo = String.valueOf(num);
        String [] contentArr = {serialNo,knSite,siteNum,knTypeName,regionName,"",webNameDomain};
        return contentArr;
    }

}
