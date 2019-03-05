package com.zhxg.framework.base.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRelation;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHeight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGrid;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGridCol;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextScale;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHeightRule;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHint;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;


public class AnalyzePOIExportUtil {
    // 事件分析 文章名称
    private static final String MONITOR_NAME = "事件分析报告";

    // 事件分析对应模块图片KEY
    private static final String DEVELOPMENT_TREND_ONE_PIC = "developmentTrendOnePic";// 发展趋势图1
    private static final String TONAL_ANALYSIS_ONEPIC = "tonalAnalysisOnePic";// 调性分析图1
    private static final String TONAL_ANALYSIS_TWOPIC = "tonalAnalysisTwoPic";// 调性分析图2
    private static final String KEY_WORD_ONEPIC = "keyWordOnePic";// 关键词云图1
    private static final String MICROBLOG_BIGV_ONEPIC = "microblogBigvOnePic";// 微博分析--大V分布图1
    private static final String MICROBLOG_Region_ONEPIC = "microblogRegionOnePic";// 微博分析--地域图1
    private static final String MICROBLOG_Navy_ONEPIC = "microblogNavyOnePic";// 微博分析--水军图1
    private static final String MICROBLOG_Emotion_ONEPIC = "microblogEmotionOnePic";// 微博分析--情感图1
    private static final String MEDIA_SOURCE_ONEPIC = "mediaSourceOnePic";// 媒体分析--媒体来源图1
    private static final String MEDIA_ACTIVITY_ONEPIC = "mediaActivityOnePic";// 媒体分析--媒体活跃度图1
    private static final String VIEWPOINT_NETIZEN_ONEPIC = "viewpointNetizenOnePic";// 观点分析--网民观点图1
    private static String[] surveyAnalyze = {"调性分析", "情感占比分析"};

    public static void createEventContext(String fileName,
                                          Map<String, String> imgMap,
                                          String[] event_monitor_basedata, String event_summary_info, String[][] first_media_table,
                                          String[][] event_vein_table, String development_trend_base, String tonal_analysis_base,
                                          String key_word_base, String[] key_word_table, String microblog_analysis_base,
                                          String[][] microblog_bigv_table, String[][] microblog_government_table, String[][] microblog_media_table,
                                          String[][] microblog_celebrity_table, String[] microblog_spred_baseData, String[][] microblog_spred_table,
                                          String[][] microblog_region_leftTable, String[][] microblog_region_rightTable, String media_analysis_base,
                                          List<Map<String, Object>> mediaList, String[][] viewpoint_government_table,
                                          String[][] viewpoint_media_table,
                                          String[][] viewpoint_celebrity_table, String media_active_base, String viewpoint_netizen_base)
            throws Exception {
        XWPFDocument doc = new XWPFDocument();
        String firstLevelFontSize = "28";
        String generalFontSize = "20";
        //页面设置
        setPage(doc);
        //文档标题
        addParagraph(doc, 0, MONITOR_NAME, ParagraphAlignment.CENTER, TextAlignment.CENTER, "ff3300", null, "36", null, "290", "450", true);
        //文档开头监测基本信息
        if (event_monitor_basedata != null) {
            for (String content :
                    event_monitor_basedata) {
                addParagraph(doc, 0, content, ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, "24", null, null, "310", false);
            }
        }
        //一．事件概述
        addParagraph(doc, 0, "一．事件概述", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, firstLevelFontSize, "320", "220", "310", true);
        //概述
        if (StringUtils.isNotBlank(event_summary_info)) {
            int i = event_summary_info.indexOf("传播范围：");
            if (i >= 0) {
                addParagraph(doc, 400, event_summary_info.substring(0, i), ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "180", null, "250", false);
                addParagraph(doc, 400, event_summary_info.substring(i), ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "180", null, "250", false);
            } else {
                addParagraph(doc, 400, event_summary_info, ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "180", null, "250", false);
            }
        }
        //二．首发媒体
        addParagraph(doc, 0, "二．首发媒体", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, firstLevelFontSize, "320", "310", "310", true);
        //首发媒体表
        if (first_media_table != null) {
            createContextTable(doc, first_media_table, first_media_table.length, 5, new String[]{"1047", "1057", "3140", "1047", "4176"}, 4, 4);
        }
        //三．事件脉络
        addParagraph(doc, 0, "三．事件脉络", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, firstLevelFontSize, "320", "310", "310", true);
        //事件脉络表
        if (event_vein_table != null) {
            createContextTable(doc, event_vein_table, event_vein_table.length, 7, new String[]{"523", "1057", "4186", "1047", "1570", "1047", "1037"}, 2, 7);
        }
        //四．发展趋势
        addParagraph(doc, 0, "四．发展趋势", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, firstLevelFontSize, "320", "310", "310", true);
        //发展趋势概述
        if (StringUtils.isNotBlank(development_trend_base)) {
            addParagraph(doc, 400, development_trend_base, ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "180", null, "250", false);
        }
        //发展趋势图片
        if (imgMap != null) {
            addImage(doc, imgMap.get(DEVELOPMENT_TREND_ONE_PIC), 524.0D, 179.0D);
        }
        //调性分析
        addParagraph(doc, 0, "五．调性分析", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, firstLevelFontSize, "320", "310", "310", true);
        //调性分析概述
        if (StringUtils.isNotBlank(tonal_analysis_base)) {
            addParagraph(doc, 400, tonal_analysis_base, ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "180", null, "250", false);
        }
        //调性分析图表
        if (imgMap != null && (imgMap.get(TONAL_ANALYSIS_ONEPIC) != null || imgMap.get(TONAL_ANALYSIS_TWOPIC) != null)) {
            String[] cellwidths = new String[]{"5234", "5233"};
            XWPFTable table = createMergeTable(doc, "10467", 2, 2, cellwidths, false, -1, false);
            addSurveyAnalyzeContext(table, surveyAnalyze, cellwidths);
            //调性分析图片
            addTableImage(imgMap.get(TONAL_ANALYSIS_ONEPIC), 261.0D, 116.0D, table, 1, 0);
            addTableImage(imgMap.get(TONAL_ANALYSIS_TWOPIC), 260.0D, 116.0D, table, 1, 1);
        }
        //关键词云
        addParagraph(doc, 0, "六．关键词云", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, firstLevelFontSize, "320", "310", "310", true);
        //关键词云概述
        if (key_word_base != null) {
            addParagraph(doc, 400, key_word_base, ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "180", null, "250", false);
        }
        //关键词云图表
        if (key_word_table != null) {
//            XWPFTable table = createMergeTable(doc, key_word_table.length / 2 + 1, 3, new String[]{"7325", "1571", "1571"}, false, 0, true);
//            if (imgMap != null)
//                addTableImage(imgMap.get(KEY_WORD_ONEPIC), 262.0D, 159.0D, table, 0, 0);
            if (imgMap != null) {
                addImage(doc, imgMap.get(KEY_WORD_ONEPIC), 262.0D, 147.0D);
            }
            String[] widths = new String[]{"1900", "1900"};
            XWPFTable table = createMergeTable(doc, "3800", key_word_table.length / 2 + 1, 2, widths, false, -1, false);
            addKeyWordTableContext(table, key_word_table, widths);
        }
        //微博分析
        addParagraph(doc, 0, "七．微博分析", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, firstLevelFontSize, "320", "220", "310", true);

        //传播范围
        if (StringUtils.isNotBlank(microblog_analysis_base)) {
            addParagraph(doc, 400, microblog_analysis_base, ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "180", null, "250", false);
        }
        //大V分布
        addParagraph(doc, 0, "（一）大V分布", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "320", "220", "310", true);
        //大V分布图表
        if (microblog_bigv_table != null) {
            if (imgMap != null) {
                addImage(doc, imgMap.get(MICROBLOG_BIGV_ONEPIC), 262.0D, 119.0D);
            }
            String[] widths = new String[]{"1500", "1350", "1350", "1350"};
            XWPFTable table = createMergeTable(doc, "5550", microblog_bigv_table.length, 4, widths, false, -1, false);
            addBigVTableContent(table, microblog_bigv_table, widths);
//            XWPFTable table = createMergeTable(doc, microblog_bigv_table.length, 4, new String[]{ "1500", "1350", "1350", "1350"}, false, -1, false);
//            if (imgMap != null)
//                addTableImage(imgMap.get(MICROBLOG_BIGV_ONEPIC), 262.0D, 131.0D, table, 0, 0);
        }
        //影响力排行
        addParagraph(doc, 0, "影响力排行", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "320", "220", "310", true);
        //政府
        if (microblog_government_table != null) {
            addParagraph(doc, 0, "1.政府", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "320", "220", "310", true);
            createContextTable(doc, microblog_government_table, microblog_government_table.length, 4, new String[]{"2616", "2616", "2616", "2616"}, -1, -1);
        }
        //媒体
        if (microblog_media_table != null) {
            addParagraph(doc, 0, "2.媒体", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "320", "220", "310", true);
            createContextTable(doc, microblog_media_table, microblog_media_table.length, 4, new String[]{"2616", "2616", "2616", "2616"}, -1, -1);
        }
        //名人
        if (microblog_celebrity_table != null) {
            addParagraph(doc, 0, "3.名人", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "320", "220", "310", true);
            createContextTable(doc, microblog_celebrity_table, microblog_celebrity_table.length, 4, new String[]{"2616", "2616", "2616", "2616"}, -1, -1);
        }
        //影响力概述
        if (microblog_spred_baseData != null) {
            for (String str :
                    microblog_spred_baseData) {
                addParagraph(doc, 400, str, ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "180", null, "250", false);
            }
        }
        //传播路径
        addParagraph(doc, 0, "（二）传播路径", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "320", "220", "310", true);
        //传播路径表格
        if (microblog_spred_table != null) {
            createContextTable(doc, microblog_spred_table, microblog_spred_table.length, 8, new String[]{"582", "1168", "930", "1740", "3953", "582", "930", "582"}, -1, -1);
        }
        //博主地域
        addParagraph(doc, 0, "（三）博主地域", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "320", "220", "310", true);
        //博主地域图
        if (imgMap != null) {
            addImage(doc, imgMap.get(MICROBLOG_Region_ONEPIC), 524.0D, 295.0D);
        }
        //博主地域表格
        int rowSize1 = microblog_region_leftTable == null ? 0 : microblog_region_leftTable.length;
        int rowSize2 = microblog_region_rightTable == null ? 0 : microblog_region_rightTable.length;
        XWPFTable table = null;
        String[] widths = null;
        if (rowSize1 != 0 || rowSize2 != 0) {
            widths = new String[]{"1396", "1396", "1396", "2091", "1396", "1396", "1396"};
            table = createMergeTable(doc, "10467", rowSize1 > rowSize2 ? rowSize1 : rowSize2, 7, widths, false, 3, false);
        }
        if (rowSize1 != 0) addBloggerLocalContext(table, microblog_region_leftTable, 0, widths);
        if (rowSize2 != 0) addBloggerLocalContext(table, microblog_region_rightTable, 1, widths);

        //水军分析
        addParagraph(doc, 0, "（四）水军分析", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "320", "220", "310", true);
        //水军分析图
        if (imgMap != null) {
            addImage(doc, imgMap.get(MICROBLOG_Navy_ONEPIC), 524.0D, 261.0D);
        }
        //情感分析
        addParagraph(doc, 0, "（五）情感分析", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "320", "220", "310", true);
        //情感分析图
        if (imgMap != null) {
            addImage(doc, imgMap.get(MICROBLOG_Emotion_ONEPIC), 524.0D, 261.0D);
        }
        //媒体分析
        addParagraph(doc, 0, "八．媒体分析", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, firstLevelFontSize, "320", "220", "310", true);
        //媒体分析概述
        if (StringUtils.isNotBlank(media_analysis_base)) {
            addParagraph(doc, 400, media_analysis_base, ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "180", null, "250", false);
        }
        //媒体来源
        addParagraph(doc, 0, "（一）媒体来源", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "320", "220", "310", true);
        //媒体来源图
        if (imgMap != null) {
            addImage(doc, imgMap.get(MEDIA_SOURCE_ONEPIC), 524.0D, 238.0D);
        }
        //活跃媒体
        addParagraph(doc, 0, "（二）活跃媒体", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "320", "220", "310", true);
        //活跃媒体概述
        if (StringUtils.isNotBlank(media_active_base)) {
            addParagraph(doc, 400, media_active_base, ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "180", null, "250", false);
        }
        //活跃媒体图
        if (imgMap != null) {
            addImage(doc, imgMap.get(MEDIA_ACTIVITY_ONEPIC), 524.0D, 227.0D);
        }
        //活跃媒体表
        if (mediaList != null) {
            createActiveMediaContextTable(doc, mediaList, mediaList.size() + 1, 5, new String[]{"2093", "2093", "2093", "2093", "2095"}, -1);
        }
        //观点分析
        addParagraph(doc, 0, "九．观点分析", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, firstLevelFontSize, "320", "220", "310", true);
        //网民观点
        addParagraph(doc, 0, "(一)网民观点", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "320", "220", "310", true);
        //网民观点概述
        if (StringUtils.isNotBlank(viewpoint_netizen_base)) {
            addParagraph(doc, 400, viewpoint_netizen_base, ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "180", null, "250", false);
        }
        //网民观点图片
        if (imgMap != null) {
            addImage(doc, imgMap.get(VIEWPOINT_NETIZEN_ONEPIC), 524.0D, 141.0D);
        }
        //重点微博
        addParagraph(doc, 0, "(二)重点微博", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "320", "220", "310", true);
        //政府表格
        if (viewpoint_government_table != null) {
            addParagraph(doc, 0, "1.政府", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "320", "220", "310", true);
            createContextTable(doc, viewpoint_government_table, viewpoint_government_table.length, 5, new String[]{"1163", "1163", "1163", "4655", "2323"}, 3, 5);
        }
        //媒体表格
        if (viewpoint_media_table != null) {
            addParagraph(doc, 0, "2.媒体", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "320", "220", "310", true);
            createContextTable(doc, viewpoint_media_table, viewpoint_media_table.length, 5, new String[]{"1163", "1163", "1163", "4655", "2323"}, 3, 5);
        }
        //名人表格
        if (viewpoint_celebrity_table != null) {
            addParagraph(doc, 0, "3.名人", ParagraphAlignment.LEFT, TextAlignment.AUTO, null, null, generalFontSize, "320", "220", "310", true);
            createContextTable(doc, viewpoint_celebrity_table, viewpoint_celebrity_table.length, 5, new String[]{"1163", "1163", "1163", "4655", "2323"}, 3, 5);
        }
        saveDocument(doc, fileName);

    }

    private static void createContextTable(XWPFDocument doc, String[][] array, int rowSize, int colSize, String[] colWidths, int colorCol, int linkIndex) throws FileNotFoundException {
        XWPFTable table = createTable(doc, rowSize, "10467", colSize, colWidths);
        setTableCellMargin(table, 0, 30, 0, 30);
        addGeneralTableContext(table, array, colorCol, colWidths, linkIndex);
    }


    private static void createActiveMediaContextTable(XWPFDocument doc, List<Map<String, Object>> mediaList, int rowSize, int colSize, String[] colWidths, int colorCol) throws FileNotFoundException {
        XWPFTable table = createTable(doc, rowSize, "10467", colSize, colWidths);
        setTableCellMargin(table, 0, 30, 0, 30);
        String[] arr = new String[]{"sourceType", "webName", "articleCount", "isOverseas", "domain"};
        String[] titleArr = new String[]{"媒体类型", "媒体名称", "文章数量", "地域", "域名"};
        List<XWPFTableRow> rows = table.getRows();
        int rowFlag = 0;
        for (XWPFTableRow row :
                rows) {
            Map<String, Object> map = null;
            if (rowFlag != 0) {
                map = mediaList.get(rowFlag - 1);
            }
//            setRowHeight(row, "365", STHeightRule.AT_LEAST);
            List<XWPFTableCell> cells = row.getTableCells();
            int cellFlag = 0;
            for (XWPFTableCell cell :
                    cells) {
                setCellWidthAndVAlign(cell, colWidths[cellFlag], STTblWidth.DXA, STVerticalJc.TOP);
                XWPFParagraph paragraph = getCellFirstParagraph(cell);
                setParagraphSpacingInfo(paragraph, true, "50", "50", "0", "0", false, null,
                        STLineSpacingRule.EXACT);
                XWPFRun run = getOrAddParagraphFirstRun(paragraph, false, false);
                if (rowFlag == 0) {
                    setParagraphRunFontInfo(paragraph, run, titleArr[cellFlag], "宋体", "Times New Roman",
                            "20", false, false, false, null, null, null, 0, 0, 0);
                } else {
                    setParagraphRunFontInfo(paragraph, run, map.get(arr[cellFlag]).toString(), "宋体", "Times New Roman",
                            "20", false, false, false, null, null, null, 0, 0, 0);
                }
                cellFlag++;
            }
            rowFlag++;
        }
    }

    /**
     * 普通表格中插入文本
     */
    private static void addGeneralTableContext(XWPFTable table, String[][] array, int colorCol, String[] colWidths, int linkIndex) {
        List<XWPFTableRow> rows = table.getRows();
        int rowFlag = 0;
        for (XWPFTableRow row :
                rows) {
            String[] arr = array[rowFlag];
//            setRowHeight(row, "365", STHeightRule.AT_LEAST);
            List<XWPFTableCell> cells = row.getTableCells();
            int cellFlag = 0;
            for (XWPFTableCell cell :
                    cells) {
                setCellWidthAndVAlign(cell, colWidths[cellFlag], STTblWidth.DXA, STVerticalJc.TOP);
                XWPFParagraph paragraph = getCellFirstParagraph(cell);
                setParagraphSpacingInfo(paragraph, true, "50", "50", "0", "0", false, null,
                        STLineSpacingRule.EXACT);
                XWPFRun run = getOrAddParagraphFirstRun(paragraph, false, false);
                String shdColor = null;
                String fontColor = null;
                if (cellFlag == colorCol && rowFlag != 0) {
                    fontColor = "0000FF";
                    String text=arr[cellFlag];
                    if(text.length()>35){
                        text=text.substring(0,35);
                    }
                    String link = arr[linkIndex];
                    if (link.length() > 255 || link.indexOf("{") > 0) {
                        setParagraphRunFontInfo(paragraph, run, text, "宋体", "Times New Roman",
                                "20", false, false, false, fontColor, shdColor, null, 0, 0, 0);
                    } else {
                        try {
                            String id = paragraph
                                    .getDocument()
                                    .getPackagePart()
                                    .addExternalRelationship(link,
                                            XWPFRelation.HYPERLINK.getRelation())
                                    .getId();
                            // Append the link and bind it to the relationship
                            CTHyperlink cLink = paragraph.getCTP().addNewHyperlink();
                            cLink.setId(id);

                            // Create the linked text
                            CTText ctText = CTText.Factory.newInstance();
                            ctText.setStringValue(text);
                            CTR ctr = CTR.Factory.newInstance();
                            CTRPr rpr = ctr.addNewRPr();

                            // 设置超链接样式
                            CTColor color = CTColor.Factory.newInstance();
                            color.setVal(fontColor);
                            rpr.setColor(color);
                            // rpr.addNewU().setVal(STUnderline.SINGLE);

                            // 设置字体
                            CTFonts fonts = rpr.isSetRFonts() ? rpr.getRFonts() : rpr.addNewRFonts();
                            fonts.setAscii("宋体");
                            fonts.setEastAsia("宋体");
                            fonts.setHAnsi("宋体");

                            // 设置字体大小
                            CTHpsMeasure sz = rpr.isSetSz() ? rpr.getSz() : rpr.addNewSz();
                            sz.setVal(new BigInteger("20"));

                            ctr.setTArray(new CTText[] { ctText });
                            // Insert the linked text into the link
                            cLink.setRArray(new CTR[] { ctr });
                        } catch (Exception e) {
                            setParagraphRunFontInfo(paragraph, run, text, "宋体", "Times New Roman",
                                    "20", false, false, false, fontColor, shdColor, null, 0, 0, 0);
                        }

                    }
                } else {
                    setParagraphRunFontInfo(paragraph, run, arr[cellFlag], "宋体", "Times New Roman",
                            "20", false, false, false, fontColor, shdColor, null, 0, 0, 0);
                }
                cellFlag++;
            }
            rowFlag++;
            if (rowFlag > array.length - 1) break;
        }
    }


    /**
     * 向调研分析表格中插入文本
     */
    private static void addBigVTableContent(XWPFTable table, String[][] array, String[] widths) {
        List<XWPFTableRow> rows = table.getRows();
        int rowFlag = 0;
        for (XWPFTableRow row :
                rows) {
            List<XWPFTableCell> tableCells = row.getTableCells();
            String[] arr = array[rowFlag];
            int cellFlag = 0;
            for (XWPFTableCell cell :
                    tableCells) {
                setCellWidthAndVAlign(cell, widths[cellFlag], STTblWidth.DXA, STVerticalJc.TOP);
                XWPFParagraph paragraph = getCellFirstParagraph(cell);
                setParagraphSpacingInfo(paragraph, true, "10", "10", "0", "0", false, null,
                        STLineSpacingRule.EXACT);
                XWPFRun run = getOrAddParagraphFirstRun(paragraph, false, false);
                setParagraphRunFontInfo(paragraph, run, arr[cellFlag], "宋体", "Times New Roman",
                        "20", false, false, false, null, null, null, 0, 0, 0);
                cellFlag++;
            }
            rowFlag++;
        }
    }


    /**
     * 向大V分布表格中插入文本
     */
    private static void addSurveyAnalyzeContext(XWPFTable table, String[] array, String[] widths) {
        List<XWPFTableRow> rows = table.getRows();
        int rowFlag = 0;
        for (XWPFTableRow row :
                rows) {
            List<XWPFTableCell> tableCells = row.getTableCells();
            int cellFlag = 0;
            for (XWPFTableCell cell :
                    tableCells) {
                setCellWidthAndVAlign(cell, widths[cellFlag], STTblWidth.DXA, STVerticalJc.TOP);
                XWPFParagraph paragraph = getCellFirstParagraph(cell);
                setParagraphSpacingInfo(paragraph, true, "10", "10", "0", "0", false, null,
                        STLineSpacingRule.EXACT);
                XWPFRun run = getOrAddParagraphFirstRun(paragraph, false, false);
                setParagraphRunFontInfo(paragraph, run, array[cellFlag], "宋体", "Times New Roman",
                        "24", false, false, false, null, null, null, 0, 0, 0);
                cellFlag++;
            }
            rowFlag++;
            if (rowFlag == 1) break;
        }
    }

    /**
     * 向博主地域表格中插入文本
     */
    private static void addBloggerLocalContext(XWPFTable table, String[][] array, int flag, String[] widths) {
        List<XWPFTableRow> rows = table.getRows();
        int rowFlag = 0;
        for (XWPFTableRow row :
                rows) {
//            if (rowFlag > array.length - 1) return;
            boolean boo = false;
            if (rowFlag > array.length - 1) {
                boo = true;
            }
            List<XWPFTableCell> tableCells = row.getTableCells();
            String[] arr = null;
            if (!boo) arr = array[rowFlag];
            int cellFlag = 0;
            for (XWPFTableCell cell :
                    tableCells) {

                if (cellFlag == 3) {
                    setCellWidthAndVAlign(cell, widths[cellFlag], STTblWidth.DXA, STVerticalJc.TOP);
                    XWPFParagraph paragraph = getCellFirstParagraph(cell);
                    setParagraphSpacingInfo(paragraph, true, "10", "10", "0", "0", false, null,
                            STLineSpacingRule.EXACT);
                    XWPFRun run = getOrAddParagraphFirstRun(paragraph, false, false);
                    setParagraphRunFontInfo(paragraph, run, "        ", "宋体", "Times New Roman",
                            "20", false, false, false, null, null, null, 0, 0, 0);
                }
                if (flag == 0) {
                    if (cellFlag >= 3) {
                        break;
                    }
                } else {
                    if (cellFlag <= 3) {
                        cellFlag++;
                        continue;
                    }
                }
                setCellWidthAndVAlign(cell, widths[cellFlag], STTblWidth.DXA, STVerticalJc.TOP);
                XWPFParagraph paragraph = getCellFirstParagraph(cell);
                setParagraphSpacingInfo(paragraph, true, "10", "10", "0", "0", false, null,
                        STLineSpacingRule.EXACT);
                XWPFRun run = getOrAddParagraphFirstRun(paragraph, false, false);
                setParagraphRunFontInfo(paragraph, run, boo ? "        " : arr[flag == 0 ? cellFlag : cellFlag - 4], "宋体", "Times New Roman",
                        "20", false, false, false, null, null, null, 0, 0, 0);
                cellFlag++;
            }
            rowFlag++;
        }
    }


    private static void addKeyWordTableContext(XWPFTable table, String[] array, String[] widths) {
        List<XWPFTableRow> rows = table.getRows();
        int rowFlag = 0;
        int arrFlag = 0;
        for (XWPFTableRow row :
                rows) {
            List<XWPFTableCell> tableCells = row.getTableCells();
            int cellFlag = 0;
            for (XWPFTableCell cell :
                    tableCells) {
                setCellWidthAndVAlign(cell, widths[cellFlag], STTblWidth.DXA, STVerticalJc.TOP);
                XWPFParagraph paragraph = getCellFirstParagraph(cell);
                setParagraphSpacingInfo(paragraph, true, "10", "10", "0", "0", false, null,
                        STLineSpacingRule.EXACT);
                XWPFRun run = getOrAddParagraphFirstRun(paragraph, false, false);
                if (rowFlag == 0) {
                    setParagraphRunFontInfo(paragraph, run, cellFlag == 0 ? "事件高频词" : "出现次数", "宋体", "Times New Roman",
                            "24", false, false, false, null, null, null, 0, 0, 0);
                } else {
                    setParagraphRunFontInfo(paragraph, run, array[arrFlag++], "宋体", "Times New Roman",
                            "20", false, false, false, null, null, null, 0, 0, 0);
                }
                cellFlag++;
            }
            rowFlag++;
        }
    }

    /***
     *创建需要合并单元格的表格
     */

    private static XWPFTable createMergeTable(XWPFDocument doc, String tableWidth, int rowSize, int colSize, String[] colWidths, boolean cellMerge, int mergeCell, boolean hideBorderLine) throws FileNotFoundException {
        XWPFTable table = createTable(doc, rowSize, tableWidth, colSize, colWidths);
        setTableCellMargin(table, 0, 0, 0, 0);
        if (cellMerge)
            mergeCellsByCell(table, 0, 1, colSize - 1);
        if (mergeCell >= 0)
            mergeCellsByRow(table, mergeCell, 0, rowSize - 1);
        if (hideBorderLine)
            hideBorderLine(table);
        return table;
    }

    /**
     * 创建表
     */
    private static XWPFTable createTable(XWPFDocument doc, int rowSize, String tableWidth, int colSize, String[] colWidths) {
        XWPFTable table = doc.createTable(rowSize, colSize);
        setTableBorders(table, STBorder.SINGLE, "3", "auto", "0");
        setTableWidthAndHAlign(table, tableWidth, STJc.CENTER);
        setTableGridCol(table, colWidths);
        return table;
    }

    /**
     * 隐藏表格边框
     */
    private static void hideBorderLine(XWPFTable table) {
        List<XWPFTableRow> rows = table.getRows();
        int flag = 0;
        for (XWPFTableRow row :
                rows) {
            CTTcPr cellCTTcPr = getCellCTTcPr(row.getCell(0));
            CTTcBorders ctTcPrTcBorders = getCTTcPrTcBorders(cellCTTcPr);
            if (flag == 0) {
                ctTcPrTcBorders.addNewLeft().setVal(STBorder.NIL);
                ctTcPrTcBorders.addNewTop().setVal(STBorder.NIL);
            } else if (flag == rows.size() - 1) {
                ctTcPrTcBorders.addNewLeft().setVal(STBorder.NIL);
                ctTcPrTcBorders.addNewBottom().setVal(STBorder.NIL);
            } else {
                ctTcPrTcBorders.addNewLeft().setVal(STBorder.NIL);
            }
            flag++;
        }
    }

    /**
     * 设置表格总宽度与水平对齐方式
     */
    private static void setTableWidthAndHAlign(XWPFTable table, String width,
                                               STJc.Enum enumValue) {
        CTTblPr tblPr = getTableCTTblPr(table);
        CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr
                .addNewTblW();
        if (enumValue != null) {
            CTJc cTJc = tblPr.addNewJc();
            cTJc.setVal(enumValue);
        }
        tblWidth.setW(new BigInteger(width));
        tblWidth.setType(STTblWidth.DXA);
    }


    /**
     * 设置表格的边框
     */
    private static void setTableBorders(XWPFTable table, STBorder.Enum borderType,
                                        String size, String color, String space) {
        CTTblPr tblPr1 = getTableCTTblPr(table);
        CTTblBorders borders = tblPr1.isSetTblBorders() ? tblPr1.getTblBorders()
                : tblPr1.addNewTblBorders();
        CTBorder hBorder = borders.isSetInsideH() ? borders.getInsideH()
                : borders.addNewInsideH();
        hBorder.setVal(borderType);
        hBorder.setSz(new BigInteger(size));
        hBorder.setColor(color);
        hBorder.setSpace(new BigInteger(space));

        CTBorder vBorder = borders.isSetInsideV() ? borders.getInsideV()
                : borders.addNewInsideV();
        vBorder.setVal(borderType);
        vBorder.setSz(new BigInteger(size));
        vBorder.setColor(color);
        vBorder.setSpace(new BigInteger(space));

        CTBorder lBorder = borders.isSetLeft() ? borders.getLeft() : borders
                .addNewLeft();
        lBorder.setVal(borderType);
        lBorder.setSz(new BigInteger(size));
        lBorder.setColor(color);
        lBorder.setSpace(new BigInteger(space));

        CTBorder rBorder = borders.isSetRight() ? borders.getRight() : borders
                .addNewRight();
        rBorder.setVal(borderType);
        rBorder.setSz(new BigInteger(size));
        rBorder.setColor(color);
        rBorder.setSpace(new BigInteger(space));

        CTBorder tBorder = borders.isSetTop() ? borders.getTop() : borders
                .addNewTop();
        tBorder.setVal(borderType);
        tBorder.setSz(new BigInteger(size));
        tBorder.setColor(color);
        tBorder.setSpace(new BigInteger(space));

        CTBorder bBorder = borders.isSetBottom() ? borders.getBottom()
                : borders.addNewBottom();
        bBorder.setVal(borderType);
        bBorder.setSz(new BigInteger(size));
        bBorder.setColor(color);
        bBorder.setSpace(new BigInteger(space));
    }

    /**
     * 设置行高
     */
    private static void setRowHeight(XWPFTableRow row, String hight,
                                     STHeightRule.Enum heightEnum) {
        CTTrPr trPr = getRowCTTrPr(row);
        CTHeight trHeight;
        if (trPr.getTrHeightList() != null && trPr.getTrHeightList().size() > 0) {
            trHeight = trPr.getTrHeightList().get(0);
        } else {
            trHeight = trPr.addNewTrHeight();
        }
        trHeight.setVal(new BigInteger(hight));
        if (heightEnum != null) {
            trHeight.setHRule(heightEnum);
        }
    }


    /**
     * 获取Row的CTTrPr,不存在则新建
     */
    private static CTTrPr getRowCTTrPr(XWPFTableRow row) {
        CTRow ctRow = row.getCtRow();
        return ctRow.isSetTrPr() ? ctRow.getTrPr() : ctRow.addNewTrPr();
    }

    /**
     * 设置列宽和垂直对齐方式
     */
    private static void setCellWidthAndVAlign(XWPFTableCell cell, String width,
                                              STTblWidth.Enum typeEnum, STVerticalJc.Enum vAlign) {
        CTTcPr tcPr = getCellCTTcPr(cell);
        CTTblWidth tcw = tcPr.isSetTcW() ? tcPr.getTcW() : tcPr.addNewTcW();
        if (width != null) {
            tcw.setW(new BigInteger(width));
        }
        /*if (typeEnum != null) {
            tcw.setType(typeEnum);
        }
        if (vAlign != null) {
            CTVerticalJc vJc = tcPr.isSetVAlign() ? tcPr.getVAlign() : tcPr
                    .addNewVAlign();
            vJc.setVal(vAlign);
        }*/
    }

    /**
     * 获取table的CTTblPr
     */
    private static CTTcBorders getCTTcPrTcBorders(CTTcPr cellCTTcPr) {
        return cellCTTcPr.getTcBorders() == null ? cellCTTcPr.addNewTcBorders() : cellCTTcPr.getTcBorders();
    }

    /**
     * 获取table的CTTblPr
     */
    private static CTTblPr getTableCTTblPr(XWPFTable table) {
        CTTbl ctTbl = table.getCTTbl();
        return ctTbl.getTblPr() == null ? ctTbl.addNewTblPr() : ctTbl.getTblPr();
    }

    /**
     * 设置单元格内部内容与边框的距离
     */
    private static void setTableCellMargin(XWPFTable table, int top, int left,
                                           int bottom, int right) {
        table.setCellMargins(top, left, bottom, right);
    }

    /**
     * 设置表格列宽
     */
    private static void setTableGridCol(XWPFTable table, String[] colWidths) {
        CTTbl ctTbl = table.getCTTbl();
        CTTblGrid tblGrid = ctTbl.getTblGrid() != null ? ctTbl.getTblGrid()
                : ctTbl.addNewTblGrid();
        for (String colWidth : colWidths) {
            CTTblGridCol gridCol = tblGrid.addNewGridCol();
            gridCol.setW(new BigInteger(colWidth));
        }
    }

    /**
     * 添加图片
     */
    private static void addImage(XWPFDocument doc, String imagePath, double width, double height) throws Exception {
        if (StringUtils.isBlank(imagePath)) {
            return;
        }
        XWPFParagraph p = doc.createParagraph();
        setParagraphAlignInfo(p, ParagraphAlignment.CENTER, TextAlignment.AUTO);
        setParagraphSpacingInfo(p, true, "300", "350", null, null, true, null,
                STLineSpacingRule.EXACT);
        XWPFRun run = p.createRun();
        run.addPicture(new FileInputStream(imagePath), 6, "", Units.toEMU(width), Units.toEMU(height));
    }


    // word跨行并单元格
    private static void mergeCellsByRow(XWPFTable table, int col, int fromRow, int toRow) {
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
            if (rowIndex == fromRow) {
                // The first merged cell is set with RESTART merge value
                getCellCTTcPr(cell).addNewVMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                getCellCTTcPr(cell).addNewVMerge().setVal(STMerge.CONTINUE);
            }
        }
    }


    /**
     * 跨列合并
     */
    private static void mergeCellsByCell(XWPFTable table, int row, int fromCell,
                                         int toCell) {
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
            if (cellIndex == fromCell) {
                // The first merged cell is set with RESTART merge value
                getCellCTTcPr(cell).addNewHMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one,are set with CONTINUE
                getCellCTTcPr(cell).addNewHMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

    /**
     * 得到Cell的CTTcPr,不存在则新建
     */
    private static CTTcPr getCellCTTcPr(XWPFTableCell cell) {
        CTTc cttc = cell.getCTTc();
        return cttc.isSetTcPr() ? cttc.getTcPr() : cttc.addNewTcPr();
    }

    /**
     * 添加表格图片
     */
    private static void addTableImage(String imagePath, double width, double height, XWPFTable table, int row, int col) throws Exception {
        if (StringUtils.isBlank(imagePath)) {
            return;
        }
        XWPFTableCell cell = table.getRow(row).getCell(col);
//        XWPFRun run = cell.addParagraph().createRun();
        XWPFParagraph paragraph = getCellFirstParagraph(cell);
        setParagraphAlignInfo(paragraph, ParagraphAlignment.CENTER, TextAlignment.AUTO);
        XWPFRun run = getOrAddParagraphFirstRun(paragraph, false, false);
//        run.addBreak();
        run.addPicture(new FileInputStream(imagePath), 6, "", Units.toEMU(width), Units.toEMU(height));
    }


    /**
     * 页面设置
     */
    private static void setPage(XWPFDocument doc) {
        CTBody body = doc.getDocument().getBody();
        if (!body.isSetSectPr()) {
            body.addNewSectPr();
        }
        CTSectPr sectPr = body.getSectPr();
        if (!sectPr.isSetPgSz()) {
            sectPr.addNewPgSz();
        }
        // 设置页面大小  当前A4大小
        CTPageSz pageSize = sectPr.getPgSz();
        pageSize.setW(BigInteger.valueOf(11907));
        pageSize.setH(BigInteger.valueOf(16840));
        pageSize.setOrient(STPageOrientation.PORTRAIT);
        CTPageMar pageMar = sectPr.addNewPgMar();
        //设置页边距
        pageMar.setLeft(BigInteger.valueOf(720));
        pageMar.setTop(BigInteger.valueOf(720));
        pageMar.setRight(BigInteger.valueOf(720));
        pageMar.setBottom(BigInteger.valueOf(720));
    }

    /**
     * 添加段落
     */
    private static void addParagraph(XWPFDocument doc, int textIndent, String content, ParagraphAlignment paragraphAlignment, TextAlignment textAlignment, String fontColor, String shdColor, String fontSize, String before, String after, String line, boolean isBlod) {
        XWPFParagraph paragraph = doc.createParagraph();
        if (textIndent != 0) {
            paragraph.setFirstLineIndent(textIndent);
        }
//        设置段落间距信息,一行=100 一磅=20
        setParagraphSpacingInfo(paragraph, true, before, after, null, null, true, line,
                STLineSpacingRule.EXACT);
        // 居中
        setParagraphAlignInfo(paragraph, paragraphAlignment,
                textAlignment);
        XWPFRun run = getOrAddParagraphFirstRun(paragraph, false, false);
        setParagraphRunFontInfo(paragraph, run, content, "宋体", "Times New Roman",
                fontSize, isBlod, false, false, fontColor, shdColor, null, 0, 0, 0);
    }

    /**
     * 保存文档
     */
    private static void saveDocument(XWPFDocument document, String savePath)
            throws Exception {
//        FileOutputStream fos = new FileOutputStream(savePath);
        OutputStream out = UploadFileUtil.getInstance().getOut(savePath);
        document.write(out);
        out.close();
    }

    /**
     * 设置段落对齐
     */
    private static void setParagraphAlignInfo(XWPFParagraph p,
                                              ParagraphAlignment pAlign, TextAlignment vAlign) {
        if (pAlign != null) {
            p.setAlignment(pAlign);
        }
        if (vAlign != null) {
            p.setVerticalAlignment(vAlign);
        }
    }

    /**
     * 设置段落间距信息,一行=100 一磅=20
     */
    private static void setParagraphSpacingInfo(XWPFParagraph p, boolean isSpace,
                                                String before, String after, String beforeLines, String afterLines,
                                                boolean isLine, String line, STLineSpacingRule.Enum lineValue) {
        CTPPr pPPr = getParagraphCTPPr(p);
        CTSpacing pSpacing = pPPr.getSpacing() != null ? pPPr.getSpacing()
                : pPPr.addNewSpacing();
        if (isSpace) {
            // 段前磅数
            if (before != null) {
                pSpacing.setBefore(new BigInteger(before));
            }
            // 段后磅数
            if (after != null) {
                pSpacing.setAfter(new BigInteger(after));
            }
            // 段前行数
            if (beforeLines != null) {
                pSpacing.setBeforeLines(new BigInteger(beforeLines));
            }
            // 段后行数
            if (afterLines != null) {
                pSpacing.setAfterLines(new BigInteger(afterLines));
            }
        }
        // 间距
        if (isLine) {
            if (line != null) {
                pSpacing.setLine(new BigInteger(line));
            }
            if (lineValue != null) {
                pSpacing.setLineRule(lineValue);
            }
        }
    }


    /**
     * 得到单元格第一个Paragraph
     */
    private static XWPFParagraph getCellFirstParagraph(XWPFTableCell cell) {
        XWPFParagraph p;
        if (cell.getParagraphs() != null && cell.getParagraphs().size() > 0) {
            p = cell.getParagraphs().get(0);
        } else {
            p = cell.addParagraph();
        }
        return p;
    }

    /**
     * 得到段落CTPPr
     */
    private static CTPPr getParagraphCTPPr(XWPFParagraph p) {
        CTPPr pPPr = null;
        if (p.getCTP() != null) {
            if (p.getCTP().getPPr() != null) {
                pPPr = p.getCTP().getPPr();
            } else {
                pPPr = p.getCTP().addNewPPr();
            }
        }
        return pPPr;
    }

    /**
     * 获取paragraph第一个XWPFRun
     */
    private static XWPFRun getOrAddParagraphFirstRun(XWPFParagraph p, boolean isInsert,
                                                     boolean isNewLine) {
        XWPFRun pRun;
        if (isInsert) {
            pRun = p.createRun();
        } else {
            if (p.getRuns() != null && p.getRuns().size() > 0) {
                pRun = p.getRuns().get(0);
            } else {
                pRun = p.createRun();
            }
        }
        if (isNewLine) {
            pRun.addBreak();
        }
        return pRun;
    }


    /**
     * 得到XWPFRun的CTRPr
     */
    private static CTRPr getRunCTRPr(XWPFParagraph p, XWPFRun run) {
        CTRPr pRpr;
        if (run.getCTR() != null) {
            pRpr = run.getCTR().getRPr();
            if (pRpr == null) {
                pRpr = run.getCTR().addNewRPr();
            }
        } else {
            pRpr = p.getCTP().addNewR().addNewRPr();
        }
        return pRpr;
    }

    /**
     * 设置段落文本样式(高亮与底纹显示效果不同)设置字符间距信息(CTSignedTwipsMeasure)
     * : SUPERSCRIPT上标 SUBSCRIPT下标
     *
     * @param position     :字符间距位置：>0提升 <0降低=磅值*2 如3磅=6
     * @param spacingValue :字符间距间距 >0加宽 <0紧缩 =磅值*20 如2磅=40
     * @param indent       :字符间距缩进 <100 缩
     */
    private static void setParagraphRunFontInfo(XWPFParagraph p, XWPFRun run,
                                                String content, String cnFontFamily, String enFontFamily,
                                                String fontSize, boolean isBlod, boolean isItalic,
                                                boolean isStrike, String fontColor, String shdColor,
                                                STShd.Enum shdStyle, int position, int spacingValue, int indent) {
        CTRPr pRpr = getRunCTRPr(p, run);
        if (!StringUtils.isEmpty(content)) {
            if (content.contains("\n")) {
                String[] lines = content.split("\n");
                run.setText(lines[0], 0); // set first line into XWPFRun
                for (int i = 1; i < lines.length; i++) {
                    // add break and insert new text
                    run.addBreak();
                    run.setText(lines[i]);
                }
            } else {
                run.setText(content, 0);
            }
        }
        // 设置字体
        CTFonts fonts = pRpr.isSetRFonts() ? pRpr.getRFonts() : pRpr
                .addNewRFonts();
        if (!StringUtils.isEmpty(enFontFamily)) {
            fonts.setAscii(enFontFamily);
            fonts.setHAnsi(enFontFamily);
        }
        if (!StringUtils.isEmpty(cnFontFamily)) {
            fonts.setEastAsia(cnFontFamily);
            fonts.setHint(STHint.EAST_ASIA);
        }
        // 设置字体大小
        CTHpsMeasure sz = pRpr.isSetSz() ? pRpr.getSz() : pRpr.addNewSz();
        sz.setVal(new BigInteger(fontSize));
        CTHpsMeasure szCs = pRpr.isSetSzCs() ? pRpr.getSzCs() : pRpr
                .addNewSzCs();
        szCs.setVal(new BigInteger(fontSize));
        // 设置字体样式
        // 加粗
        if (isBlod) {
            run.setBold(isBlod);
        }

        // 倾斜
        if (isItalic) {
            run.setItalic(isItalic);
        }
        // 删除线
        if (isStrike) {
            run.setStrike(isStrike);
        }
        if (fontColor != null) {
            run.setColor(fontColor);


        }
        CTShd shd;
        if (shdStyle != null || shdColor != null) {
            shd = pRpr.isSetShd() ? pRpr.getShd() : pRpr.addNewShd();
            if (shdStyle != null) shd.setVal(shdStyle);
            if (shdColor != null) {
                shd.setColor(shdColor);
                shd.setFill(shdColor);
            }
        }
        // 设置文本位置
        if (position != 0) {
            run.setTextPosition(position);
        }
        if (spacingValue != 0) {
            // 设置字符间距信息
            CTSignedTwipsMeasure ctSTwipsMeasure = pRpr.isSetSpacing() ? pRpr
                    .getSpacing() : pRpr.addNewSpacing();
            ctSTwipsMeasure
                    .setVal(new BigInteger(String.valueOf(spacingValue)));
        }
        if (indent != 0) {
            CTTextScale paramCTTextScale = pRpr.isSetW() ? pRpr.getW() : pRpr
                    .addNewW();
            paramCTTextScale.setVal(indent);
        }
    }
}