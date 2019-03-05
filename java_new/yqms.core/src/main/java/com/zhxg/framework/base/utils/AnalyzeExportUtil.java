package com.zhxg.framework.base.utils;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.lowagie.text.Anchor;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;

public class AnalyzeExportUtil {

    // 趋势分析
    private static final String TREND_INFO = "trend_info"; // 声量趋势
    private static final String TREND_INFO_TOTAL = "trend_info_total"; // 声量对比
    private static final String MEDIA_TABLEDATA = "media_tabledata"; // 媒体趋势
    private static final String PRAISE_TABLEDATA = "praise_tabledata";// 口碑词
    private static final String KEYWORD_TABLE_TABLEDATA = "keyword_table_tabledata";// 关键词
    private static final String OVERALL_TREND_TABLE = "overall_trend_table";// 整体趋势

    // 事件分析 文章名称
    private static final String MONITORNAME = "事件分析报告";// monitorname

    // 事件分析对应模块图片KEY
    private static final String DEVELOPMENT_TREND_ONEPIC = "developmentTrendOnePic";// 发展趋势图1
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

    private static final int TABLE_TD_WIDTH = 25;
    private static final int TABLE_BG_COLOR = 220;
    private static final int TABLE_WIDTH = 100;
    private static final int TABLE_PADDING = 5;

    // 事件分析
    @SuppressWarnings("deprecation")
    public static void createEventContext(HttpServletRequest request, String monitorName, String fileName,
            Map<String, String> imgMap,
            String[] event_monitor_basedata, String event_summary_info, String[][] first_media_table,
            String[][] event_vein_table, String development_trend_base, String tonal_analysis_base,
            String key_word_base, String[] key_word_table, String microblog_analysis_base,
            String[][] microblog_bigv_table, String[][] microblog_government_table, String[][] microblog_media_table,
            String[][] microblog_celebrity_table, String[] microblog_spred_baseData, String[][] microblog_spred_table,
            String[][] microblog_region_leftTable, String[][] microblog_region_rightTable, String media_analysis_base,
            List<Map<String, Object>> mediaList, String[][] viewpoint_government_table,
            String[][] viewpoint_media_table,
            String[][] viewpoint_celebrity_table,String media_active_base,String viewpoint_netizen_base)
            throws Exception {
        // 创建文档
        Document document = new Document(PageSize.A4);// A4纸
        RtfWriter2.getInstance(document, UploadFileUtil.getInstance().getOut(fileName));
        // RtfWriter2.getInstance(document, new FileOutputStream("D:\\测试报告.doc"));
        float contextWidth = ITextUtil.getContextWidth(document);

        // 打开文档开始编写
        document.open();
        
        HeaderFooter footer = new HeaderFooter(new Phrase(" - "), new Phrase(" - "));   
        footer.setAlignment(Rectangle.ALIGN_CENTER);   
       
        document.setFooter(footer);

        // 正文标题 小二 加粗 红色
        Font titleFont = new Font();
        titleFont.setSize(18);
        titleFont.setStyle(Font.BOLD);
        titleFont.setColor(Color.RED);

        // 段落一级标题 四号
        Font firstFont = new Font();
        firstFont.setSize(14);
        firstFont.setStyle(Font.BOLD);
        // 段落二级标题 10 加粗
        Font secondFont = new Font();
        secondFont.setSize(10);
        secondFont.setStyle(Font.BOLD);

        // 监控信息字体
        Font thirdFont = new Font();
        thirdFont.setSize(12);

        // 正文
        Font contextFont = new Font();
        contextFont.setSize(10);
        
        //超链接
        Font linkFont = new Font();
        linkFont.setColor(Color.BLUE);
        linkFont.setSize(10);
        

        // 换一行
        String changeOne = "";
        String changeTwo = "\n";
        Paragraph changeLine = new Paragraph(changeOne);
        Paragraph changeTwoLine = new Paragraph(changeTwo);

        // 图片
        String contextString = "";
        Paragraph picContext = new Paragraph(contextString);
        // 正文格式左对齐
        picContext.setAlignment(Element.ALIGN_CENTER);
        picContext.setFont(contextFont);
        // 离上一段落（标题）空的行数
        picContext.setSpacingBefore(0);
        // 设置第一行空的列数
        picContext.setFirstLineIndent(0);

        // 图片距离上文的间距
        String picString = "";
        // 开始编写正文
        Paragraph title = new Paragraph(MONITORNAME);// 文章名称
        // 设置标题格式对齐方式
        title.setAlignment(Element.ALIGN_CENTER);
        title.setFont(titleFont);

        document.add(title);
        document.add(changeLine);

        if (event_monitor_basedata != null) {
            // 监控基本信息：
            for (String basedata : event_monitor_basedata) {
                Paragraph context = new Paragraph(basedata);
                // 离上一段落（标题）空的行数
                context.setSpacingBefore(1);
                // 正文格式左对齐
                context.setAlignment(Element.ALIGN_LEFT);
                // context.setFont(contextFont);
                // 离上一段落（标题）空的行数
                context.setSpacingBefore(0);
                // 设置第一行空的列数
                context.setFirstLineIndent(0);
                // 设置字体
                context.setFont(thirdFont);
                // 将设置置入文档
                document.add(context);
            }
        }

        // 事件概述
        document.add(changeLine);
        Paragraph p1 = new Paragraph("一．事件概述");// 标题
        p1.setFont(firstFont);
        document.add(p1);
        if (StringUtils.isNotBlank(event_summary_info)) {// 概述信息
            Paragraph paraSummaryInfo = new Paragraph(event_summary_info);
            // 设置离上一行间距
            paraSummaryInfo.setSpacingBefore(10);
            // 首行缩进
            paraSummaryInfo.setFirstLineIndent(20f);
            // 设置字体
            paraSummaryInfo.setFont(contextFont);
            document.add(paraSummaryInfo);
        }

        // 首发媒体 表格
        document.add(changeLine);
        Paragraph p2 = new Paragraph("二．首发媒体");
        p2.setFont(firstFont);
        document.add(p2);
        if (first_media_table != null) {
            // 创建表格并设置属性
            Table mediaTable = new Table(5);
            int[] width = { 10, 10, 30, 10, 40 };
            mediaTable.setWidths(width);// 设置每列所占比例
            mediaTable.setWidth(100); // 占页面宽度 90%
            mediaTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
            mediaTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            mediaTable.setAutoFillEmptyCells(true); // 自动填满
            mediaTable.setPadding(5);// 衬距
            mediaTable.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));
            for (int i = 0; i < first_media_table.length; i++) {
                for (int j = 0; j < first_media_table[i].length; j++) {
                    if (i == 0) {
                        Paragraph paragraph = new Paragraph(first_media_table[i][j]);
                        paragraph.setFont(contextFont);
                        Cell cell = new Cell(paragraph);
                        mediaTable.addCell(cell);
                    } else {
                        if(j != 4){
                            Paragraph paragraph = new Paragraph(first_media_table[i][j]);
                            paragraph.setFont(contextFont);
                            Cell cell = new Cell(paragraph);
                            mediaTable.addCell(cell);
                        }else{
                            String link = first_media_table[i][4];
                            String url = first_media_table[i][4];
                             if (link.length() > 255 || link.indexOf("{") > 0) {
                                Paragraph paragraph = new Paragraph(link);
                                paragraph.setFont(contextFont);
                                Cell cell = new Cell(paragraph);
                                mediaTable.addCell(cell);
                            } else {
                                Anchor anchor = insertContentisHyperlink(link, 10, "0", "0", url);
                                anchor.setFont(linkFont);
                                mediaTable.addCell(anchor);
                            }
                        }
                    }

                }
            }
            document.add(mediaTable);
        }

        // 事件脉络表格
        document.add(changeLine);
        Paragraph p3 = new Paragraph("三．事件脉络");
        p3.setFont(firstFont);
        document.add(p3);
        if (event_vein_table != null) {
            // 创建表格并设置属性
            Table veinTable = new Table(7);
            int[] width = { 5, 10, 40, 10, 15, 10, 10 };
            veinTable.setWidths(width);// 设置每列所占比例
            veinTable.setWidth(100); // 占页面宽度 90%
            veinTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
            veinTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            veinTable.setAutoFillEmptyCells(true); // 自动填满
            veinTable.setPadding(5);// 衬距
            veinTable.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));
            for (int i = 0; i < event_vein_table.length; i++) {
                for (int j = 0; j < event_vein_table[i].length - 1; j++) {
                    if (i == 0) {
                        Paragraph paragraph = new Paragraph(event_vein_table[i][j]);
                        paragraph.setFont(contextFont);
                        Cell cell = new Cell(paragraph);
                        veinTable.addCell(cell);
                    } else {
                        if (j != 2) {
                            Paragraph paragraph = new Paragraph(event_vein_table[i][j]);
                            paragraph.setFont(contextFont);
                            Cell cell = new Cell(paragraph);
                            veinTable.addCell(cell);
                        } else {
                            String link = event_vein_table[i][7];
                            String name = event_vein_table[i][2];
                            if (link.length() > 255 || link.indexOf("{") > 0) {
                                Paragraph paragraph = new Paragraph(name);
                                paragraph.setFont(contextFont);
                                Cell cell = new Cell(paragraph);
                                veinTable.addCell(cell);
                            } else {
                                Anchor anchor = insertContentisHyperlink(name, 10, "0", "0", link);
                                anchor.setFont(linkFont);
                                veinTable.addCell(anchor);
                            }
                        }
                    }
                }
            }
            document.add(veinTable);
        }

        // 发展趋势
        document.add(changeLine);
        Paragraph p4 = new Paragraph("四．发展趋势");
        p4.setFont(firstFont);
        document.add(p4);
        // 趋势概述信息
        if (StringUtils.isNotBlank(development_trend_base)) {
            Paragraph developmentTrendBase = new Paragraph(development_trend_base);
            // 设置离上一行间距
            developmentTrendBase.setSpacingBefore(10);
            // 首行缩进
            developmentTrendBase.setFirstLineIndent(20f);
            // 设置字体
            developmentTrendBase.setFont(contextFont);
            document.add(developmentTrendBase);
        }
        // 趋势图片
        Image png = null;
        ExportPicUtil.addDocument(document, png, DEVELOPMENT_TREND_ONEPIC, contextWidth, imgMap, 1f);

        // 调性分析
        document.add(changeLine);
        Paragraph p5 = new Paragraph("五．调性分析");
        p5.setFont(firstFont);
        document.add(p5);
        if (StringUtils.isNotBlank(tonal_analysis_base)) {
            Paragraph tonalAnalysisBase = new Paragraph(tonal_analysis_base);
            // 设置离上一行间距
            tonalAnalysisBase.setSpacingBefore(10);
            // 首行缩进
            tonalAnalysisBase.setFirstLineIndent(20f);
            // 设置字体
            tonalAnalysisBase.setFont(contextFont);
            document.add(tonalAnalysisBase);
        }
        // 调性分析图片 左右排版
        // 获取张图片
        Image onePic = null;
        Image analyOnePic = ExportPicUtil.getImg(onePic, TONAL_ANALYSIS_ONEPIC, imgMap, 1f);
        Image twoPic = null;
        Image analyTwoPic = ExportPicUtil.getImg(onePic, TONAL_ANALYSIS_TWOPIC, imgMap, 1f);
        if (analyOnePic != null || analyTwoPic != null) {
            // 1.创建一个两列的表格
            Table allTable1 = new Table(2);
            int[] width = { 50, 50 };
            allTable1.setWidths(width);
            allTable1.setWidth(100);
            allTable1.getDefaultCell().setBorderWidth(0);
            // 获取两个图片
            // 3.插入图片
            ITextUtil.setScaleAbsolute(analyOnePic, 261.5f, 1f);
            ITextUtil.setScaleAbsolute(analyTwoPic, 261.5f, 1f);
            // 左右表格
            Table leftTable = new Table(1);
            Table rightTable = new Table(1);
            // 置入
            Cell titleCell = new Cell("调性分析");
            titleCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            leftTable.addCell(titleCell);
            Cell imageCell1 = new Cell();
            imageCell1.addElement(analyOnePic);
            imageCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            imageCell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            leftTable.addCell(imageCell1);
            allTable1.insertTable(leftTable);
            allTable1.setAutoFillEmptyCells(true);
            Cell titleCell2 = new Cell("情感占比分析");
            titleCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            rightTable.addCell(titleCell2);
            Cell imageCell2 = new Cell();
            imageCell2.addElement(analyTwoPic);
            imageCell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            imageCell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            rightTable.addCell(imageCell2);
            allTable1.insertTable(rightTable);
            allTable1.setAutoFillEmptyCells(true);
            document.add(allTable1);
        }
        // 关键词云
        document.add(changeLine);
        Paragraph p6 = new Paragraph("六．关键词云");
        p6.setFont(firstFont);
        document.add(p6);
        // 关键词云概述
        if (StringUtils.isNotBlank(key_word_base)) {
            Paragraph keyWordBase = new Paragraph(key_word_base);
            // 设置离上一行间距
            keyWordBase.setSpacingBefore(10);
            // 首行缩进
            keyWordBase.setFirstLineIndent(20f);
            // 设置字体
            keyWordBase.setFont(contextFont);
            document.add(keyWordBase);
        }
        // 词云图片 + 词云表格左右排版
        if (key_word_table != null) {
            // 1.创建一个两列的表格
            Table allTable = new Table(2);
            int[] width = { 70, 30 };
            allTable.setWidths(width);
            allTable.setWidth(100);
            allTable.getDefaultCell().setBorderWidth(0);
            // 2.创建右边数据表格
            Table rightTable = new Table(2);
            // 3.插入图片
            Image png3 = null;
            Image keyWordPic = ExportPicUtil.getImg(png3, KEY_WORD_ONEPIC, imgMap, 1f);
            ITextUtil.setScaleAbsolute(keyWordPic, 261.5f, 1f);
            Cell imageCell = new Cell();
            imageCell.addElement(keyWordPic);
            imageCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            imageCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            imageCell.setBorderWidth(0);
            allTable.addCell(imageCell);
            // 4.右侧表格
            Cell titleCell = new Cell("事件高频词");
            titleCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            titleCell.setColspan(2);
            rightTable.addCell(titleCell);
            // 录入数据
            for (int i = 0; i < key_word_table.length; i++) {
                Paragraph paragraph = new Paragraph(key_word_table[i]);
                paragraph.setFont(contextFont);
                Cell cell = new Cell(paragraph);
                rightTable.addCell(cell);
            }
            // allTable.addCell(new Cell(rightTable));
            allTable.insertTable(rightTable);
            allTable.setAutoFillEmptyCells(true);
            document.add(allTable);
        }

        // 微博分析
        document.add(changeLine);
        Paragraph p7 = new Paragraph("七．微博分析");
        p7.setFont(firstFont);
        document.add(p7);
        // 概述信息
        if (StringUtils.isNotBlank(microblog_analysis_base)) {
            Paragraph microblogAnalysisBase = new Paragraph(microblog_analysis_base);
            // 设置离上一行间距
            microblogAnalysisBase.setSpacingBefore(10);
            // 首行缩进
            microblogAnalysisBase.setFirstLineIndent(20f);
            // 设置字体
            microblogAnalysisBase.setFont(contextFont);
            document.add(microblogAnalysisBase);
        }
        // 大V分布 图片与表格左右排版
        document.add(changeLine);
        Paragraph p8 = new Paragraph("（一）大V分布");
        p8.setFont(secondFont);
        document.add(p8);
        // 获取大V图片
        Image bigVOne = null;
        Image bigVPic = ExportPicUtil.getImg(bigVOne, MICROBLOG_BIGV_ONEPIC, imgMap, 1f);
        if (microblog_bigv_table != null) {
            // 1.创建一个两列的表格
            Table allTable = new Table(2);
            int[] width = { 50, 50 };
            allTable.setWidths(width);
            allTable.setWidth(100);
            allTable.getDefaultCell().setBorderWidth(0);
            allTable.setAutoFillEmptyCells(true);
            // 2.创建右边数据表格
            Table rightTable = new Table(4);
            // 3.插入图片
            ITextUtil.setScaleAbsolute(bigVPic, 261.5f, 1f);
            Cell imageCell = new Cell();
            imageCell.addElement(bigVPic);
            imageCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            imageCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            allTable.addCell(imageCell);
            // 右侧表格录入数据
            rightTable.setWidth(100); // 占页面宽度 90%
            rightTable.setAlignment(Element.ALIGN_LEFT);// 居中显示
            rightTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            rightTable.setAutoFillEmptyCells(true); // 自动填满
            rightTable.setPadding(5);// 衬距
            rightTable.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));
            for (int i = 0; i < microblog_bigv_table.length; i++) {
                for (int j = 0; j < microblog_bigv_table[i].length; j++) {
                    Paragraph paragraph = new Paragraph(microblog_bigv_table[i][j]);
                    paragraph.setFont(contextFont);
                    Cell cell = new Cell(paragraph);
                    rightTable.addCell(cell);
                }
            }
            allTable.insertTable(rightTable);
            document.add(allTable);
        }
        // 影响力排行
        document.add(changeLine);
        Paragraph p9 = new Paragraph("影响力排行");
        p9.setFont(secondFont);
        document.add(p9);
        // 1.政府
        if (microblog_government_table != null) {
            Paragraph title1 = new Paragraph("1.政府");
            title1.setFont(secondFont);
            document.add(title1);
            // 创建表格并设置属性
            Table governmentTable = new Table(4);
            int[] width = { 20, 20, 20, 20 };
            governmentTable.setWidths(width);// 设置每列所占比例
            governmentTable.setWidth(100); // 占页面宽度 90%
            governmentTable.setAlignment(Element.ALIGN_LEFT);// 居中显示
            governmentTable.setAutoFillEmptyCells(true); // 自动填满
            governmentTable.setPadding(5);// 衬距
            governmentTable.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));
            for (int i = 0; i < microblog_government_table.length; i++) {
                for (int j = 0; j < microblog_government_table[i].length; j++) {
                    Paragraph paragraph = new Paragraph(microblog_government_table[i][j]);
                    paragraph.setFont(contextFont);
                    Cell cell = new Cell(paragraph);
                    governmentTable.addCell(cell);
                }
            }
            document.add(governmentTable);
        }
        // 2.媒体
        if (microblog_media_table != null) {
            Paragraph title2 = new Paragraph("2.媒体");
            title2.setFont(secondFont);
            document.add(title2);
            // 创建表格并设置属性
            Table mediaTable = new Table(4);
            int[] width = { 20, 20, 20, 20 };
            mediaTable.setWidths(width);// 设置每列所占比例
            mediaTable.setWidth(100); // 占页面宽度 90%
            mediaTable.setAlignment(Element.ALIGN_LEFT);// 左对齐显示
            mediaTable.setAutoFillEmptyCells(true); // 自动填满
            mediaTable.setPadding(5);// 衬距
            mediaTable.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));
            for (int i = 0; i < microblog_media_table.length; i++) {
                for (int j = 0; j < microblog_media_table[i].length; j++) {
                    Paragraph paragraph = new Paragraph(microblog_media_table[i][j]);
                    paragraph.setFont(contextFont);
                    Cell cell = new Cell(paragraph);
                    mediaTable.addCell(cell);
                }
            }
            document.add(mediaTable);
        }
        // 3.名人
        if (microblog_celebrity_table != null) {
            Paragraph title3 = new Paragraph("3.名人");
            title3.setFont(secondFont);
            document.add(title3);
            // 创建表格并设置属性
            Table celebrityTable = new Table(4);
            int[] width = { 20, 20, 20, 20 };
            celebrityTable.setWidths(width);// 设置每列所占比例
            celebrityTable.setWidth(100); // 占页面宽度 90%
            celebrityTable.setAlignment(Element.ALIGN_LEFT);// 居中显示
            celebrityTable.setAutoFillEmptyCells(true); // 自动填满
            celebrityTable.setPadding(5);// 衬距
            celebrityTable.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));
            for (int i = 0; i < microblog_celebrity_table.length; i++) {
                for (int j = 0; j < microblog_celebrity_table[i].length; j++) {
                    Paragraph paragraph = new Paragraph(microblog_celebrity_table[i][j]);
                    paragraph.setFont(contextFont);
                    Cell cell = new Cell(paragraph);
                    celebrityTable.addCell(cell);
                }
            }
            document.add(celebrityTable);
        }

        // 传播路径
        document.add(changeLine);
        Paragraph p10 = new Paragraph("（二）传播路径");
        p10.setFont(secondFont);
        document.add(p10);
        // 传播概述信息
        if (microblog_spred_baseData != null) {
            for (String basedata : microblog_spred_baseData) {
                Paragraph context = new Paragraph(basedata);
                // 离上一段落（标题）空的行数
                context.setSpacingBefore(1);
                // 正文格式左对齐
                context.setAlignment(Element.ALIGN_LEFT);
                // context.setFont(contextFont);
                // 离上一段落（标题）空的行数
                context.setSpacingBefore(0);
                // 设置第一行空的列数
                context.setFirstLineIndent(0);
                // 设置字体
                context.setFont(contextFont);
                // 将设置置入文档
                document.add(context);
            }
        }
        // 传播表格
        if (microblog_spred_table != null) {
            // 创建表格并设置属性
            Table spredTable = new Table(8);
            int[] width = { 5, 10, 8, 15, 34, 5, 8, 5 };
            spredTable.setWidths(width);// 设置每列所占比例
            spredTable.setWidth(100); // 占页面宽度 90%
            spredTable.setAlignment(Element.ALIGN_LEFT);// 居中显示
            spredTable.setAutoFillEmptyCells(true); // 自动填满
            spredTable.setPadding(5);// 衬距
            spredTable.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));
            for (int i = 0; i < microblog_spred_table.length; i++) {
                for (int j = 0; j < microblog_spred_table[i].length; j++) {
                    Paragraph paragraph = new Paragraph(microblog_spred_table[i][j]);
                    paragraph.setFont(contextFont);
                    Cell cell = new Cell(paragraph);
                    spredTable.addCell(cell);
                }
            }
            document.add(spredTable);
        }
        // 博主地域
        // 添加图片
        contextString = "\n（三）博主地域 \n";
        Image png5 = null;
        ExportPicUtil.addDocument(contextString, picContext, secondFont, document, png5, MICROBLOG_Region_ONEPIC,
                contextWidth, imgMap, 1f);
        contextString = "\n ";
        picContext = new Paragraph(contextString);
        picContext.setAlignment(Element.ALIGN_CENTER);
        document.add(picContext);
        // 地域表格
        if (microblog_region_leftTable != null && microblog_region_rightTable != null) {
            // 创建总表格
            Table allTable = new Table(3);
            int[] width2 = { 40, 20, 40 };
            allTable.setWidths(width2);
            allTable.setWidth(100);
            allTable.getDefaultCell().setBorderWidth(1);
            allTable.setAutoFillEmptyCells(true);
            // 创建左表格并录入数据
            Table leftTable = new Table(3);
            int[] leftWith = { 20, 20, 20 };
            leftTable.setWidths(leftWith);// 设置每列所占比例
            leftTable.setWidth(100); // 占页面宽度 90%
            leftTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
            leftTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            leftTable.setAutoFillEmptyCells(true); // 自动填满
            leftTable.setPadding(5);// 衬距
            leftTable.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));
            for (int i = 0; i < microblog_region_leftTable.length; i++) {
                for (int j = 0; j < microblog_region_leftTable[i].length; j++) {
                    Paragraph paragraph = new Paragraph(microblog_region_leftTable[i][j]);
                    paragraph.setFont(contextFont);
                    Cell cell = new Cell(paragraph);
                    leftTable.addCell(cell);
                }
            }
            allTable.insertTable(leftTable);
            // 添加合并列
            Cell centerCell = new Cell();
            centerCell.setRowspan(1);
            allTable.addCell(centerCell);
            // 创建右表格并录入数据
            Table rightTable = new Table(3);
            int[] rightWidth = { 20, 20, 20 };
            rightTable.setWidths(rightWidth);// 设置每列所占比例
            rightTable.setWidth(100); // 占页面宽度 90%
            rightTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
            rightTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            rightTable.setAutoFillEmptyCells(true); // 自动填满
            rightTable.setPadding(5);// 衬距
            rightTable.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));
            for (int i = 0; i < microblog_region_rightTable.length; i++) {
                for (int j = 0; j < microblog_region_rightTable[i].length; j++) {
                    Paragraph paragraph = new Paragraph(microblog_region_rightTable[i][j]);
                    paragraph.setFont(contextFont);
                    Cell cell = new Cell(paragraph);
                    rightTable.addCell(cell);
                }
            }
            allTable.insertTable(rightTable);
            document.add(allTable);
        }
        // 添加图片
        contextString = "\n（四）水军分析 \n";
        Image png6 = null;
        ExportPicUtil.addDocument(contextString, picContext, secondFont, document, png6, MICROBLOG_Navy_ONEPIC,
                contextWidth, imgMap, 1f);
        contextString = "\n ";
        picContext = new Paragraph(contextString);
        picContext.setAlignment(Element.ALIGN_CENTER);
        document.add(picContext);
        // 添加图片
        contextString = "\n（五）情感分析 \n";
        Image png7 = null;
        ExportPicUtil.addDocument(contextString, picContext, secondFont, document, png7, MICROBLOG_Emotion_ONEPIC,
                contextWidth, imgMap, 1f);
        contextString = "\n ";
        picContext = new Paragraph(contextString);
        picContext.setAlignment(Element.ALIGN_CENTER);
        document.add(picContext);
        // 媒体分析
        document.add(changeLine);
        Paragraph p12 = new Paragraph("八．媒体分析");
        p12.setFont(firstFont);
        document.add(p12);
        // 概述信息 media_analysis_table
        if (StringUtils.isNotBlank(media_analysis_base)) {
            Paragraph mediaAnalysisBase = new Paragraph(media_analysis_base);
            // 设置离上一行间距
            mediaAnalysisBase.setSpacingBefore(10);
            // 首行缩进
            mediaAnalysisBase.setFirstLineIndent(20f);
            // 设置字体
            mediaAnalysisBase.setFont(contextFont);
            document.add(mediaAnalysisBase);
        }
        // 添加图片
        contextString = "\n（一）媒体来源 \n";
        Image png8 = null;
        ExportPicUtil.addDocument(contextString, picContext, secondFont, document, png8, MEDIA_SOURCE_ONEPIC,
                contextWidth, imgMap, 1f);
        contextString = "\n ";
        picContext = new Paragraph(contextString);
        picContext.setAlignment(Element.ALIGN_CENTER);
        document.add(picContext);
        // 添加图片
        contextString = "\n（二）活跃媒体\n";
        Paragraph context = new Paragraph(contextString);
        context.setFont(secondFont);
        context.setAlignment(Element.ALIGN_LEFT);
        document.add(context);
        //活跃媒体  media_active_base
        if(StringUtils.isNotBlank(media_active_base)){
            Paragraph mediaActiveBase = new Paragraph(media_active_base);
            // 设置离上一行间距
            //mediaActiveBase.setSpacingBefore(10);
            // 首行缩进
            mediaActiveBase.setFirstLineIndent(20f);
            mediaActiveBase.setSpacingAfter(10);
            // 设置字体
            mediaActiveBase.setFont(contextFont);
            document.add(mediaActiveBase);
        }
        
        Image png9 = null;
        ExportPicUtil.addDocument( document, png9, MEDIA_ACTIVITY_ONEPIC,contextWidth, imgMap, 1f);
        contextString = "\n ";
        picContext = new Paragraph(contextString);
        picContext.setAlignment(Element.ALIGN_CENTER);
        document.add(picContext);
        // 媒体分析表格
        if (mediaList != null) {
            Paragraph paragraph = null;
            Cell cell = null;
            // 创建表格并设置属性
            Table mediaTable = new Table(5);
            int[] width = { 20, 20, 20, 20, 20 };
            mediaTable.setWidths(width);// 设置每列所占比例
            mediaTable.setWidth(100); // 占页面宽度 90%
            mediaTable.setAlignment(Element.ALIGN_LEFT);// 居中显示
            mediaTable.setAutoFillEmptyCells(true); // 自动填满
            mediaTable.setPadding(5);// 衬距
            mediaTable.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));
            paragraph = new Paragraph("媒体类型");
            paragraph.setFont(contextFont);
            cell = new Cell(paragraph);
            mediaTable.addCell(cell);
            paragraph = new Paragraph("媒体名称");
            paragraph.setFont(contextFont);
            cell = new Cell(paragraph);
            mediaTable.addCell(cell);
            paragraph = new Paragraph("文章数量");
            paragraph.setFont(contextFont);
            cell = new Cell(paragraph);
            mediaTable.addCell(cell);
            paragraph = new Paragraph("地域");
            paragraph.setFont(contextFont);
            cell = new Cell(paragraph);
            mediaTable.addCell(cell);
            paragraph = new Paragraph("域名");
            paragraph.setFont(contextFont);
            cell = new Cell(paragraph);
            mediaTable.addCell(cell);

            for (Map<String, Object> map : mediaList) {
                paragraph = new Paragraph(map.get("sourceType").toString());
                paragraph.setFont(contextFont);
                cell = new Cell(paragraph);
                mediaTable.addCell(cell);

                paragraph = new Paragraph(map.get("webName").toString());
                paragraph.setFont(contextFont);
                cell = new Cell(paragraph);
                mediaTable.addCell(cell);

                paragraph = new Paragraph(map.get("articleCount").toString());
                paragraph.setFont(contextFont);
                cell = new Cell(paragraph);
                mediaTable.addCell(cell);

                paragraph = new Paragraph(map.get("isOverseas").toString());
                paragraph.setFont(contextFont);
                cell = new Cell(paragraph);
                mediaTable.addCell(cell);

                paragraph = new Paragraph(map.get("domain").toString());
                paragraph.setFont(contextFont);
                cell = new Cell(paragraph);
                mediaTable.addCell(cell);
                // 域名加超链接 但是不能访问
                // String link = map.get("domain").toString();
                // if (link.length() > 255 || link.indexOf("{") > 0) {
                // paragraph = new Paragraph(map.get("domain").toString());
                // paragraph.setFont(contextFont);
                // cell = new Cell(paragraph);
                // mediaTable.addCell(cell);
                // } else {
                // Anchor anchor = insertContentisHyperlink(link, 10, "1", "1", link);
                // mediaTable.addCell(anchor);
                // }

            }
            document.add(mediaTable);
        }
        // 观点分析
        document.add(changeLine);
        Paragraph p13 = new Paragraph("九、观点分析");
        p13.setFont(firstFont);
        document.add(p13);
        // 添加图片
        contextString = "\n(一)网民观点\n";
        Paragraph viewPointContext = new Paragraph(contextString);
        viewPointContext.setFont(secondFont);
        viewPointContext.setAlignment(Element.ALIGN_LEFT);
        document.add(viewPointContext);
        // 网民观点 viewpoint_netizen_base
        if(StringUtils.isNotBlank(viewpoint_netizen_base)){
            Paragraph viewpointNnetizenBase = new Paragraph(viewpoint_netizen_base);
            // 设置离上一行间距
            //viewpointNnetizenBase.setSpacingBefore(10);
            // 首行缩进
            viewpointNnetizenBase.setFirstLineIndent(20f);
            viewpointNnetizenBase.setSpacingAfter(10);
            // 设置字体
            viewpointNnetizenBase.setFont(contextFont);
            document.add(viewpointNnetizenBase);
        } 
        Image png10 = null;
        ExportPicUtil.addDocument( document, png10, VIEWPOINT_NETIZEN_ONEPIC,contextWidth, imgMap, 1f);
        contextString = "\n ";
        picContext = new Paragraph(contextString);
        picContext.setAlignment(Element.ALIGN_CENTER);
        document.add(picContext);
        // 重点微博表格
        document.add(changeLine);
        Paragraph p14 = new Paragraph("(二)重点微博");
        p14.setFont(secondFont);
        document.add(p14);
        // 政府表格
        if (viewpoint_government_table != null) {
            Paragraph title1 = new Paragraph("1.政府");
            title1.setFont(secondFont);
            document.add(title1);
            // 创建表格并设置属性
            Table governmentTable = new Table(5);
            int[] width = { 10, 10, 10, 40, 20 };
            governmentTable.setWidths(width);// 设置每列所占比例
            governmentTable.setWidth(100); // 占页面宽度 90%
            governmentTable.setAlignment(Element.ALIGN_LEFT);// 居中显示
            governmentTable.setAutoFillEmptyCells(true); // 自动填满
            governmentTable.setPadding(5);// 衬距
            governmentTable.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));
            for (int i = 0; i < viewpoint_government_table.length; i++) {
                for (int j = 0; j < viewpoint_government_table[i].length - 1; j++) {
                    if (i == 0) {
                        Paragraph paragraph = new Paragraph(viewpoint_government_table[i][j]);
                        paragraph.setFont(contextFont);
                        Cell cell = new Cell(paragraph);
                        governmentTable.addCell(cell);
                    } else {
                        if (j != 3) {
                            Paragraph paragraph = new Paragraph(viewpoint_government_table[i][j]);
                            paragraph.setFont(contextFont);
                            Cell cell = new Cell(paragraph);
                            governmentTable.addCell(cell);
                        } else if (i != 0) {
                            String link = viewpoint_government_table[i][5];
                            String name = viewpoint_government_table[i][3];
                            if (link.length() > 255 || link.indexOf("{") > 0) {
                                Paragraph paragraph = new Paragraph(viewpoint_government_table[i][j]);
                                paragraph.setFont(contextFont);
                                Cell cell = new Cell(paragraph);
                                governmentTable.addCell(cell);
                            } else {
                                Anchor anchor = insertContentisHyperlink(name, 10, "0", "0", link);
                                anchor.setFont(linkFont);
                                governmentTable.addCell(anchor);
                            }
                        }
                    }

                }
            }
            document.add(governmentTable);
        }
        // 媒体表格
        if (viewpoint_media_table != null) {
            Paragraph title2 = new Paragraph("2.媒体");
            title2.setFont(secondFont);
            document.add(title2);
            // 创建表格并设置属性
            Table mediaTable = new Table(5);
            int[] width = { 10, 10, 10, 40, 20 };
            mediaTable.setWidths(width);// 设置每列所占比例
            mediaTable.setWidth(100); // 占页面宽度 90%
            mediaTable.setAlignment(Element.ALIGN_LEFT);// 居中显示
            mediaTable.setAutoFillEmptyCells(true); // 自动填满
            mediaTable.setPadding(5);// 衬距
            mediaTable.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));
            for (int i = 0; i < viewpoint_media_table.length; i++) {
                for (int j = 0; j < viewpoint_media_table[i].length - 1; j++) {
                    if (i == 0) {
                        Paragraph paragraph = new Paragraph(viewpoint_media_table[i][j]);
                        paragraph.setFont(contextFont);
                        Cell cell = new Cell(paragraph);
                        mediaTable.addCell(cell);
                    } else {
                        if (j != 3) {
                            Paragraph paragraph = new Paragraph(viewpoint_media_table[i][j]);
                            paragraph.setFont(contextFont);
                            Cell cell = new Cell(paragraph);
                            mediaTable.addCell(cell);
                        } else {
                            String link = viewpoint_media_table[i][5];
                            String name = viewpoint_media_table[i][3];
                            if (link.length() > 255 || link.indexOf("{") > 0) {
                                Paragraph paragraph = new Paragraph(viewpoint_media_table[i][j]);
                                paragraph.setFont(contextFont);
                                Cell cell = new Cell(paragraph);
                                mediaTable.addCell(cell);
                            } else {
                                Anchor anchor = insertContentisHyperlink(name, 10, "0", "0", link);
                                anchor.setFont(linkFont);
                                mediaTable.addCell(anchor);
                            }
                        }
                    }
                }
            }
            document.add(mediaTable);
        }
        // 名人表格
        if (viewpoint_celebrity_table != null) {
            Paragraph title3 = new Paragraph("3.名人");
            title3.setFont(secondFont);
            document.add(title3);
            // 创建表格并设置属性
            Table celebrityTable = new Table(5);
            int[] width = { 10, 10, 10, 40, 20 };
            celebrityTable.setWidths(width);// 设置每列所占比例
            celebrityTable.setWidth(100); // 占页面宽度 90%
            celebrityTable.setAlignment(Element.ALIGN_LEFT);// 居中显示
            celebrityTable.setAutoFillEmptyCells(true); // 自动填满
            celebrityTable.setPadding(5);// 衬距
            celebrityTable.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));
            for (int i = 0; i < viewpoint_celebrity_table.length; i++) {
                for (int j = 0; j < viewpoint_celebrity_table[i].length - 1; j++) {
                    if (i == 0) {
                        Paragraph paragraph = new Paragraph(viewpoint_celebrity_table[i][j]);
                        paragraph.setFont(contextFont);
                        Cell cell = new Cell(paragraph);
                        celebrityTable.addCell(cell);
                    } else {
                        if (j != 3) {
                            Paragraph paragraph = new Paragraph(viewpoint_celebrity_table[i][j]);
                            paragraph.setFont(contextFont);
                            Cell cell = new Cell(paragraph);
                            celebrityTable.addCell(cell);
                        } else {
                            String link = viewpoint_celebrity_table[i][5];
                            String name = viewpoint_celebrity_table[i][3];
                            if (link.length() > 255 || link.indexOf("{") > 0) {
                                Paragraph paragraph = new Paragraph(viewpoint_celebrity_table[i][j]);
                                paragraph.setFont(contextFont);
                                Cell cell = new Cell(paragraph);
                                celebrityTable.addCell(cell);
                            } else {
                                Anchor anchor = insertContentisHyperlink(name, 10, "0", "0", link);
                                anchor.setFont(linkFont);
                                celebrityTable.addCell(anchor);
                            }
                        }
                    }
                }
            }
            document.add(celebrityTable);
        }
        document.close();
    }

    // 趋势分析
    @SuppressWarnings("deprecation")
    public static void createTrendContext(HttpServletRequest request, String monitorName, String fileName,
            Map<String, String> imgMap,
            String[] trend_info_tabledata, String[] trend_info_total_totaldata)
            throws Exception {

        // 生成路径
        /*
         * String projectPath = request.getRealPath("/");
         * projectPath = projectPath.substring(0,projectPath.indexOf("webapps") + 8) + "export";
         * File file = new File(projectPath);
         * if (!file.exists()) {
         * file.mkdir();
         * }
         */
        Document document = new Document(PageSize.A4);
        RtfWriter2.getInstance(document, UploadFileUtil.getInstance().getOut(fileName));
        float contextWidth = ITextUtil.getContextWidth(document);

        document.open();
        /*
         * // 设置中文字体
         * BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
         * // 标题字体风格
         * Font titleFont = new Font(bfChinese, 18, Font.BOLD, new Color(255, 0, 0));
         * // 正文字体风格
         * Font contextFont = new Font(bfChinese, 10, Font.NORMAL);
         * Font contextTitleFont = new Font(bfChinese, 14, Font.BOLD);
         */
        Font titleFont = new Font();
        titleFont.setSize(18);
        titleFont.setStyle(Font.BOLD);
        Font contextFont = new Font();
        contextFont.setSize(16);
        contextFont.setStyle(Font.BOLD);
        Font contextTitleFont = new Font();
        contextTitleFont.setSize(16);
        contextTitleFont.setStyle(Font.BOLD);
        Paragraph title = new Paragraph(monitorName + " —— 趋势分析");
        // 设置标题格式对齐方式
        title.setAlignment(Element.ALIGN_CENTER);
        title.setFont(titleFont);
        document.add(title);
        String contextString = "";
        Paragraph context = new Paragraph(contextString);
        // 正文格式左对齐
        context.setAlignment(Element.ALIGN_CENTER);
        context.setFont(contextFont);
        // 离上一段落（标题）空的行数
        context.setSpacingBefore(0);
        // 设置第一行空的列数
        context.setFirstLineIndent(0);
        document.add(context);
        // 添加图片
        contextString = "\n声量趋势 \n\n";

        Image png = null;
        ExportPicUtil.addDocument(contextString, context, contextTitleFont, document, png,
                TREND_INFO, contextWidth, imgMap, 1f);

        contextString = "\n    ";
        context = new Paragraph(contextString);
        context.setAlignment(Element.ALIGN_CENTER);
        document.add(context);

        // 添加表格
        if (trend_info_tabledata != null) {
            Table table = new Table(4);
            int[] width = { 20, 20, 20, 20 };
            table.setWidths(width);// 设置每列所占比例
            table.setWidth(100); // 占页面宽度 90%

            table.setAlignment(Element.ALIGN_CENTER);// 居中显示
            table.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            table.setAutoFillEmptyCells(true); // 自动填满
            // table.setBorderWidth(1); //边框宽度
            // table.setBorderColor(new Color(0, 125, 255)); //边框颜色
            table.setPadding(5);// 衬距
            // table.setSpacing(3);//即单元格之间的间距
            // table.setBorder(2);//边框
            table.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));// 背景颜色

            // 表格的主体
            for (int i = 0; i < trend_info_tabledata.length; i++) {
                String data = trend_info_tabledata[i];
                String[] celldata = data.split("xaax");

                for (int j = 0; j < celldata.length; j++) {
                    table.addCell(celldata[j]);
                }
            }
            document.add(table);
        }

        // 第二张图片
        contextString = "\n声量对比 \n\n";

        Image png2 = null;
        ExportPicUtil.addDocument(contextString, context, contextTitleFont, document, png2,
                TREND_INFO_TOTAL, contextWidth, imgMap, 1f);

        contextString = "\n    ";
        context = new Paragraph(contextString);
        context.setAlignment(Element.ALIGN_CENTER);
        document.add(context);

        // 添加表格
        if (trend_info_total_totaldata != null) {
            Table table2 = new Table(3);
            int[] width2 = { 30, 35, 35 };
            table2.setWidths(width2);// 设置每列所占比例
            table2.setWidth(100); // 占页面宽度 90%

            table2.setAlignment(Element.ALIGN_CENTER);// 居中显示
            table2.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            table2.setAutoFillEmptyCells(true); // 自动填满
            table2.setPadding(5);// 衬距
            table2.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));// 背景颜色

            // 表格的主体
            for (int i = 0; i < trend_info_total_totaldata.length; i++) {
                String data = trend_info_total_totaldata[i];
                String[] celldata = data.split("xaax");

                for (int j = 0; j < celldata.length; j++) {
                    table2.addCell(celldata[j]);
                }
            }
            document.add(table2);
        }

        document.close();
    }

    // 内容分析
    private static final String AFFECTIONS_INFO = "affections_info"; // 情感趋势
    private static final String AFFECTIONS_INFO_TOTAL = "affections_info_total"; // 情感分布
    private static final String HOTWORDS_TOPTWENTY = "hotwords_toptwenty"; // 热词
    private static final String HOTWORDS_TOPFIVE_GROUPBYDATE = "hotwords_topfive_groupbydate";// 热词趋势

    // 内容分析
    @SuppressWarnings("deprecation")
    public static void createContentContext(HttpServletRequest request, String monitorName, String fileName,
            Map<String, String> imgMap,
            String[] affections_info_tabledata, String[] affections_info_total_totaldata,
            String[] hotwords_toptwenty_tabledata, String[] hotwords_topfive_groupbydate_tabledata)
            throws Exception {

        // 生成路径
        // String projectPath = request.getRealPath("/");
        // projectPath = projectPath.substring(0,projectPath.indexOf("webapps") + 8) + "export";
        //
        // File file = new File(projectPath);
        // if (!file.exists()) {
        // file.mkdir();
        // }
        Document document = new Document(PageSize.A4);
        RtfWriter2.getInstance(document, UploadFileUtil.getInstance().getOut(fileName));
        float contextWidth = ITextUtil.getContextWidth(document);

        document.open();
        /*
         * // 设置中文字体
         * BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
         * // 标题字体风格
         * Font titleFont = new Font(bfChinese, 18, Font.BOLD, new Color(255, 0, 0));
         * // 正文字体风格
         * Font contextFont = new Font(bfChinese, 10, Font.NORMAL);
         * Font contextTitleFont = new Font(bfChinese, 14, Font.BOLD);
         */
        Font titleFont = new Font();
        titleFont.setSize(18);
        titleFont.setStyle(Font.BOLD);
        Font contextFont = new Font();
        contextFont.setSize(16);
        contextFont.setStyle(Font.BOLD);
        Font contextTitleFont = new Font();
        contextTitleFont.setSize(16);
        contextTitleFont.setStyle(Font.BOLD);
        Paragraph title = new Paragraph(monitorName + " —— 内容分析");
        // 设置标题格式对齐方式
        title.setAlignment(Element.ALIGN_CENTER);
        title.setFont(titleFont);
        document.add(title);
        String contextString = "";
        Paragraph context = new Paragraph(contextString);
        // 正文格式左对齐
        context.setAlignment(Element.ALIGN_CENTER);
        context.setFont(contextFont);
        // 离上一段落（标题）空的行数
        context.setSpacingBefore(0);
        // 设置第一行空的列数
        context.setFirstLineIndent(0);
        document.add(context);
        // 添加图片
        contextString = "\n情感趋势 \n\n";

        Image png = null;
        ExportPicUtil.addDocument(contextString, context, contextTitleFont, document, png,
                AFFECTIONS_INFO, contextWidth, imgMap, 1f);

        contextString = "\n    ";
        context = new Paragraph(contextString);
        context.setAlignment(Element.ALIGN_CENTER);
        document.add(context);

        // 添加表格
        if (affections_info_tabledata != null) {
            Table table = new Table(4);
            int[] width = { 25, 25, 25, 25 };
            table.setWidths(width);// 设置每列所占比例
            table.setWidth(100); // 占页面宽度 90%

            table.setAlignment(Element.ALIGN_CENTER);// 居中显示
            table.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            table.setAutoFillEmptyCells(true); // 自动填满
            // table.setBorderWidth(1); //边框宽度
            // table.setBorderColor(new Color(0, 125, 255)); //边框颜色
            table.setPadding(5);// 衬距
            // table.setSpacing(3);//即单元格之间的间距
            // table.setBorder(2);//边框
            table.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));// 背景颜色

            // 表格的主体
            for (int i = 0; i < affections_info_tabledata.length; i++) {
                String data = affections_info_tabledata[i];
                String[] celldata = data.split("xaax");

                for (int j = 0; j < celldata.length; j++) {
                    table.addCell(celldata[j]);
                }
            }
            document.add(table);
        }

        // 第二张图片
        contextString = "\n情感分布 \n\n";

        Image png2 = null;
        ExportPicUtil.addDocument(contextString, context, contextTitleFont, document, png2,
                AFFECTIONS_INFO_TOTAL, contextWidth, imgMap, 1f);

        contextString = "\n    ";
        context = new Paragraph(contextString);
        context.setAlignment(Element.ALIGN_CENTER);
        document.add(context);

        // 添加表格
        if (affections_info_total_totaldata != null) {
            Table table2 = new Table(4);
            int[] width2 = { 20, 20, 20, 40 };
            table2.setWidths(width2);// 设置每列所占比例
            table2.setWidth(100); // 占页面宽度 90%

            table2.setAlignment(Element.ALIGN_CENTER);// 居中显示
            table2.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            table2.setAutoFillEmptyCells(true); // 自动填满
            table2.setPadding(5);// 衬距
            table2.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));// 背景颜色

            // 表格的主体
            for (int i = 0; i < affections_info_total_totaldata.length; i++) {
                String data = affections_info_total_totaldata[i];
                String[] celldata = data.split("xaax");

                for (int j = 0; j < celldata.length; j++) {
                    table2.addCell(celldata[j]);
                }
            }
            document.add(table2);
        }

        // 第三张图片
        contextString = "\n热词 \n\n";

        Image png3 = null;
        ExportPicUtil.addDocument(contextString, context, contextTitleFont, document, png3,
                HOTWORDS_TOPTWENTY, contextWidth, imgMap, 1f);

        contextString = "\n    ";
        context = new Paragraph(contextString);
        context.setAlignment(Element.ALIGN_CENTER);
        document.add(context);

        // 添加表格
        if (hotwords_toptwenty_tabledata != null) {
            Table table3 = new Table(3);
            int[] width3 = { 30, 30, 40 };
            table3.setWidths(width3);// 设置每列所占比例
            table3.setWidth(100); // 占页面宽度 90%

            table3.setAlignment(Element.ALIGN_CENTER);// 居中显示
            table3.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            table3.setAutoFillEmptyCells(true); // 自动填满
            table3.setPadding(5);// 衬距
            table3.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));// 背景颜色

            // 表格的主体
            for (int i = 0; i < hotwords_toptwenty_tabledata.length; i++) {
                String data = hotwords_toptwenty_tabledata[i];
                String[] celldata = data.split("xaax");

                for (int j = 0; j < celldata.length; j++) {
                    table3.addCell(celldata[j]);
                }
            }
            document.add(table3);
        }

        // 第四张图片
        contextString = "\n热词趋势 \n\n";

        Image png4 = null;
        ExportPicUtil.addDocument(contextString, context, contextTitleFont, document, png4,
                HOTWORDS_TOPFIVE_GROUPBYDATE, contextWidth, imgMap, 1f);

        contextString = "\n    ";
        context = new Paragraph(contextString);
        context.setAlignment(Element.ALIGN_CENTER);
        document.add(context);

        // 添加表格
        if (hotwords_topfive_groupbydate_tabledata != null) {
            Table table4 = new Table(6);
            int[] width4 = { 25, 15, 15, 15, 15, 15 };
            table4.setWidths(width4);// 设置每列所占比例
            table4.setWidth(100); // 占页面宽度 90%

            table4.setAlignment(Element.ALIGN_CENTER);// 居中显示
            table4.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            table4.setAutoFillEmptyCells(true); // 自动填满
            table4.setPadding(5);// 衬距
            table4.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));// 背景颜色

            // 表格的主体
            for (int i = 0; i < hotwords_topfive_groupbydate_tabledata.length; i++) {
                String data = hotwords_topfive_groupbydate_tabledata[i];
                String[] celldata = data.split("xaax");

                for (int j = 0; j < celldata.length; j++) {
                    table4.addCell(celldata[j]);
                }
            }
            document.add(table4);
        }

        document.close();
    }

    // 媒体分析
    private static final String SOURCETYPE_INFO = "sourcetype_info"; // 媒体趋势
    private static final String SOURCETYPE_INFO_TOTAL = "sourcetype_info_total"; // 媒体趋势
    private static final String ACTIVEWEB_TOPTEN = "activeweb_topten"; // 活跃媒体

    // 媒体分析
    @SuppressWarnings("deprecation")
    public static void createMediaContext(HttpServletRequest request, String monitorName, String fileName,
            Map<String, String> imgMap,
            String[] sourcetype_info_tabledata, String[] sourcetype_info_total_tabledata,
            String[] activeweb_topten_tabledata)
            throws Exception {

        // 生成路径
        /*
         * String projectPath = request.getRealPath("/");
         * projectPath = projectPath.substring(0,projectPath.indexOf("webapps") + 8) + "export";
         * File file = new File(projectPath);
         * if (!file.exists()) {
         * file.mkdir();
         * }
         */
        Document document = new Document(PageSize.A4);
        RtfWriter2.getInstance(document, UploadFileUtil.getInstance().getOut(fileName));
        float contextWidth = ITextUtil.getContextWidth(document);

        document.open();
        /*
         * // 设置中文字体
         * BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
         * // 标题字体风格
         * Font titleFont = new Font(bfChinese, 18, Font.BOLD, new Color(255, 0, 0));
         * // 正文字体风格
         * Font contextFont = new Font(bfChinese, 10, Font.NORMAL);
         * Font contextTitleFont = new Font(bfChinese, 14, Font.BOLD);
         */
        Font titleFont = new Font();
        titleFont.setSize(18);
        titleFont.setStyle(Font.BOLD);
        Font contextFont = new Font();
        contextFont.setSize(16);
        contextFont.setStyle(Font.BOLD);
        Font contextTitleFont = new Font();
        contextTitleFont.setSize(16);
        contextTitleFont.setStyle(Font.BOLD);
        Paragraph title = new Paragraph(monitorName + " —— 媒体分析");
        // 设置标题格式对齐方式
        title.setAlignment(Element.ALIGN_CENTER);
        title.setFont(titleFont);
        document.add(title);
        String contextString = "";
        Paragraph context = new Paragraph(contextString);
        // 正文格式左对齐
        context.setAlignment(Element.ALIGN_CENTER);
        context.setFont(contextFont);
        // 离上一段落（标题）空的行数
        context.setSpacingBefore(0);
        // 设置第一行空的列数
        context.setFirstLineIndent(0);
        document.add(context);
        // 添加图片
        contextString = "\n媒体趋势 \n\n";

        Image png = null;
        ExportPicUtil.addDocument(contextString, context, contextTitleFont, document, png,
                SOURCETYPE_INFO, contextWidth, imgMap, 1f);

        contextString = "\n    ";
        context = new Paragraph(contextString);
        context.setAlignment(Element.ALIGN_CENTER);
        document.add(context);

        // 动态生成表格列数
        if (sourcetype_info_tabledata != null) {
            int length = sourcetype_info_tabledata[0].split("xaax").length;
            // 添加表格
            Table table = new Table(length);

            // 动态生成宽度
            int[] width = new int[length];
            for (int i = 0; i < length; i++) {
                width[i] = 100 / length;
            }

            table.setWidths(width);// 设置每列所占比例
            table.setWidth(100); // 占页面宽度 90%

            table.setAlignment(Element.ALIGN_CENTER);// 居中显示
            table.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            table.setAutoFillEmptyCells(true); // 自动填满
            // table.setBorderWidth(1); //边框宽度
            // table.setBorderColor(new Color(0, 125, 255)); //边框颜色
            table.setPadding(5);// 衬距
            // table.setSpacing(3);//即单元格之间的间距
            // table.setBorder(2);//边框
            table.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));// 背景颜色

            // 表格的主体
            for (int i = 0; i < sourcetype_info_tabledata.length; i++) {
                String data = sourcetype_info_tabledata[i];
                String[] celldata = data.split("xaax");

                for (int j = 0; j < celldata.length; j++) {
                    table.addCell(celldata[j]);
                }
            }
            document.add(table);
        }

        // 第二张图片
        contextString = "\n媒体分布 \n\n";

        Image png2 = null;
        ExportPicUtil.addDocument(contextString, context, contextTitleFont, document, png2,
                SOURCETYPE_INFO_TOTAL, contextWidth, imgMap, 1f);

        contextString = "\n    ";
        context = new Paragraph(contextString);
        context.setAlignment(Element.ALIGN_CENTER);
        document.add(context);

        // 添加表格
        if (sourcetype_info_total_tabledata != null) {
            int length = sourcetype_info_total_tabledata[0].split("xaax").length;
            // 添加表格
            Table table2 = new Table(length);

            // 动态生成宽度
            int[] width2 = new int[length];
            for (int i = 0; i < length; i++) {
                width2[i] = 100 / length;
            }
            table2.setWidths(width2);// 设置每列所占比例
            table2.setWidth(100); // 占页面宽度 90%

            table2.setAlignment(Element.ALIGN_CENTER);// 居中显示
            table2.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            table2.setAutoFillEmptyCells(true); // 自动填满
            table2.setPadding(5);// 衬距
            table2.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));// 背景颜色

            // 表格的主体
            for (int i = 0; i < sourcetype_info_total_tabledata.length; i++) {
                String data = sourcetype_info_total_tabledata[i];
                String[] celldata = data.split("xaax");

                for (int j = 0; j < celldata.length; j++) {
                    table2.addCell(celldata[j]);
                }
            }
            document.add(table2);
        }

        // 第三张图片
        contextString = "\n活跃媒体 \n\n";

        Image png3 = null;
        ExportPicUtil.addDocument(contextString, context, contextTitleFont, document, png3,
                ACTIVEWEB_TOPTEN, contextWidth, imgMap, 1f);

        contextString = "\n    ";
        context = new Paragraph(contextString);
        context.setAlignment(Element.ALIGN_CENTER);
        document.add(context);

        // 添加表格
        if (activeweb_topten_tabledata != null) {
            Table table3 = new Table(3);
            int[] width3 = { 30, 30, 40 };
            table3.setWidths(width3);// 设置每列所占比例
            table3.setWidth(100); // 占页面宽度 90%

            table3.setAlignment(Element.ALIGN_CENTER);// 居中显示
            table3.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            table3.setAutoFillEmptyCells(true); // 自动填满
            table3.setPadding(5);// 衬距
            table3.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));// 背景颜色

            // 表格的主体
            for (int i = 0; i < activeweb_topten_tabledata.length; i++) {
                String data = activeweb_topten_tabledata[i];
                String[] celldata = data.split("xaax");

                for (int j = 0; j < celldata.length; j++) {
                    table3.addCell(celldata[j]);
                }
            }
            document.add(table3);
        }

        document.close();
    }

    private static final String WEIBO_INFO = "weibo_info"; // 微博概览
    private static final String BLOGGER_TYPE = "blogger_type"; // 博主类型
    private static final String WEIBO_TREND = "weibo_trend"; // 微博趋势
    private static final String WEIBO_AFFECTIONS_INFO = "weibo_affections_info"; // 微博情感分布
    private static final String WEIBO_HOTWORDS = "weibo_hotwords"; // 微博热词
    // private static final String HOT_BLOGGER = "hot_blogger"; // 热门博主
    // 微博分析

    public static void createWeiboContext(HttpServletRequest request, String monitorName, String fileName,
            Map<String, String> imgMap,
            String[] weibo_info_tabledata, String[] blogger_type_tabledata, String[] weibo_trend_tabledata,
            String[] weibo_affections_info_tabledata, String[] weibo_hotwords_tabledata, String[] hot_blogger_tabledata)
            throws Exception {

        // 生成路径
        /*
         * String projectPath = request.getRealPath("/");
         * projectPath = projectPath.substring(0,projectPath.indexOf("webapps") + 8) + "export";
         * File file = new File(projectPath);
         * if (!file.exists()) {
         * file.mkdir();
         * }
         */
        Document document = new Document(PageSize.A4);
        RtfWriter2.getInstance(document, UploadFileUtil.getInstance().getOut(fileName));
        float contextWidth = ITextUtil.getContextWidth(document);

        document.open();
        /*
         * // 设置中文字体
         * BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
         * // 标题字体风格
         * Font titleFont = new Font(bfChinese, 18, Font.BOLD, new Color(255, 0, 0));
         * // 正文字体风格
         * Font contextFont = new Font(bfChinese, 10, Font.NORMAL);
         * Font contextTitleFont = new Font(bfChinese, 14, Font.BOLD);
         */
        Font titleFont = new Font();
        titleFont.setSize(18);
        titleFont.setStyle(Font.BOLD);
        Font contextFont = new Font();
        contextFont.setSize(16);
        contextFont.setStyle(Font.BOLD);
        Font contextTitleFont = new Font();
        contextTitleFont.setSize(16);
        contextTitleFont.setStyle(Font.BOLD);
        Paragraph title = new Paragraph(monitorName + " —— 微博分析");
        // 设置标题格式对齐方式
        title.setAlignment(Element.ALIGN_CENTER);
        title.setFont(titleFont);
        document.add(title);
        String contextString = "";
        Paragraph context = new Paragraph(contextString);
        // 正文格式左对齐
        context.setAlignment(Element.ALIGN_CENTER);
        context.setFont(contextFont);
        // 离上一段落（标题）空的行数
        context.setSpacingBefore(0);
        // 设置第一行空的列数
        context.setFirstLineIndent(0);
        document.add(context);
        // 添加图片
        contextString = "\n博主地域分布 \n\n";

        Image png = null;
        ExportPicUtil.addDocument(contextString, context, contextTitleFont, document, png,
                WEIBO_INFO, contextWidth, imgMap, 1f);

        contextString = "\n    ";
        context = new Paragraph(contextString);
        context.setAlignment(Element.ALIGN_CENTER);
        document.add(context);

        // 添加表格
        if (weibo_info_tabledata != null) {
            Table table = new Table(2);
            int[] width = { 30, 70 };
            table.setWidths(width);// 设置每列所占比例
            table.setWidth(100); // 占页面宽度 90%

            table.setAlignment(Element.ALIGN_CENTER);// 居中显示
            table.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            table.setAutoFillEmptyCells(true); // 自动填满
            // table.setBorderWidth(1); //边框宽度
            // table.setBorderColor(new Color(0, 125, 255)); //边框颜色
            table.setPadding(5);// 衬距
            // table.setSpacing(3);//即单元格之间的间距
            // table.setBorder(2);//边框
            table.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));// 背景颜色

            // 表格的主体
            for (int i = 0; i < weibo_info_tabledata.length; i++) {
                String data = weibo_info_tabledata[i];
                String[] celldata = data.split("xaax");

                for (int j = 0; j < celldata.length; j++) {
                    table.addCell(celldata[j]);
                }
            }
            document.add(table);
        }

        // 第二张图片
        contextString = "\n博主类型 \n\n";

        Image png2 = null;
        ExportPicUtil.addDocument(contextString, context, contextTitleFont, document, png2,
                BLOGGER_TYPE, contextWidth, imgMap, 1f);

        contextString = "\n    ";
        context = new Paragraph(contextString);
        context.setAlignment(Element.ALIGN_CENTER);
        document.add(context);

        // 添加表格
        if (blogger_type_tabledata != null) {
            Table table2 = new Table(4);
            int[] width2 = { 20, 20, 20, 40 };
            table2.setWidths(width2);// 设置每列所占比例
            table2.setWidth(100); // 占页面宽度 90%

            table2.setAlignment(Element.ALIGN_CENTER);// 居中显示
            table2.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            table2.setAutoFillEmptyCells(true); // 自动填满
            table2.setPadding(5);// 衬距
            table2.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));// 背景颜色

            // 表格的主体
            for (int i = 0; i < blogger_type_tabledata.length; i++) {
                String data = blogger_type_tabledata[i];
                String[] celldata = data.split("xaax");

                for (int j = 0; j < celldata.length; j++) {
                    table2.addCell(celldata[j]);
                }
            }
            document.add(table2);
        }

        // 第三张图片
        contextString = "\n微博趋势 \n\n";

        Image png3 = null;
        ExportPicUtil.addDocument(contextString, context, contextTitleFont, document, png3,
                WEIBO_TREND, contextWidth, imgMap, 1f);

        contextString = "\n    ";
        context = new Paragraph(contextString);
        context.setAlignment(Element.ALIGN_CENTER);
        document.add(context);

        // 添加表格
        if (weibo_trend_tabledata != null) {
            Table table3 = new Table(5);
            int[] width3 = { 20, 20, 20, 20, 20 };
            table3.setWidths(width3);// 设置每列所占比例
            table3.setWidth(100); // 占页面宽度 90%

            table3.setAlignment(Element.ALIGN_CENTER);// 居中显示
            table3.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            table3.setAutoFillEmptyCells(true); // 自动填满
            table3.setPadding(5);// 衬距
            table3.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));// 背景颜色

            // 表格的主体
            for (int i = 0; i < weibo_trend_tabledata.length; i++) {
                String data = weibo_trend_tabledata[i];
                String[] celldata = data.split("xaax");

                for (int j = 0; j < celldata.length; j++) {
                    table3.addCell(celldata[j]);
                }
            }
            document.add(table3);
        }

        // 第四张图片
        contextString = "\n情感分布 \n\n";

        Image png4 = null;
        ExportPicUtil.addDocument(contextString, context, contextTitleFont, document, png4,
                WEIBO_AFFECTIONS_INFO, contextWidth, imgMap, 1f);

        contextString = "\n    ";
        context = new Paragraph(contextString);
        context.setAlignment(Element.ALIGN_CENTER);
        document.add(context);

        // 添加表格
        if (weibo_affections_info_tabledata != null) {
            Table table4 = new Table(4);
            int[] width4 = { 20, 20, 20, 40 };
            table4.setWidths(width4);// 设置每列所占比例
            table4.setWidth(100); // 占页面宽度 90%

            table4.setAlignment(Element.ALIGN_CENTER);// 居中显示
            table4.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            table4.setAutoFillEmptyCells(true); // 自动填满
            table4.setPadding(5);// 衬距
            table4.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));// 背景颜色

            // 表格的主体
            for (int i = 0; i < weibo_affections_info_tabledata.length; i++) {
                String data = weibo_affections_info_tabledata[i];
                String[] celldata = data.split("xaax");

                for (int j = 0; j < celldata.length; j++) {
                    table4.addCell(celldata[j]);
                }
            }
            document.add(table4);
        }

        // 第五张图片
        contextString = "\n微博热词 \n\n";

        Image png5 = null;
        ExportPicUtil.addDocument(contextString, context, contextTitleFont, document, png5,
                WEIBO_HOTWORDS, contextWidth, imgMap, 1f);

        contextString = "\n    ";
        context = new Paragraph(contextString);
        context.setAlignment(Element.ALIGN_CENTER);
        document.add(context);

        // 添加表格
        if (weibo_hotwords_tabledata != null) {
            Table table5 = new Table(3);
            int[] width5 = { 30, 30, 40 };
            table5.setWidths(width5);// 设置每列所占比例
            table5.setWidth(100); // 占页面宽度 90%

            table5.setAlignment(Element.ALIGN_CENTER);// 居中显示
            table5.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            table5.setAutoFillEmptyCells(true); // 自动填满
            table5.setPadding(5);// 衬距
            table5.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));// 背景颜色

            // 表格的主体
            for (int i = 0; i < weibo_hotwords_tabledata.length; i++) {
                String data = weibo_hotwords_tabledata[i];
                String[] celldata = data.split("xaax");

                for (int j = 0; j < celldata.length; j++) {
                    table5.addCell(celldata[j]);
                }
            }
            document.add(table5);
        }

        // 第六张图片
        contextString = "\n热门博主 \n\n";
        context = new Paragraph(contextString);
        context.setFont(contextTitleFont);
        context.setAlignment(Element.ALIGN_LEFT);
        document.add(context);
        /*
         * Image png6 = null;
         * ExportPicUtil.addDocument(contextString, context, contextTitleFont, document, png6,
         * HOT_BLOGGER, contextWidth, imgMap, 1f);
         * contextString = "\n    ";
         * context = new Paragraph(contextString);
         * context.setAlignment(Element.ALIGN_CENTER);
         * document.add(context);
         */

        // 添加表格
        if (hot_blogger_tabledata != null) {
            Table table6 = new Table(5);
            int[] width6 = { 10, 30, 20, 20, 20 };
            table6.setWidths(width6);// 设置每列所占比例
            table6.setWidth(100); // 占页面宽度 90%

            table6.setAlignment(Element.ALIGN_CENTER);// 居中显示
            table6.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            table6.setAutoFillEmptyCells(true); // 自动填满
            table6.setPadding(5);// 衬距
            table6.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));// 背景颜色

            // 表格的主体
            for (int i = 0; i < hot_blogger_tabledata.length; i++) {
                String data = hot_blogger_tabledata[i];
                String[] celldata = data.split("xaax");

                for (int j = 0; j < celldata.length; j++) {
                    table6.addCell(celldata[j]);
                }
            }
            document.add(table6);
        }
        document.close();
    }

    // 传播趋势
    @SuppressWarnings("deprecation")
    public static void createSpreadTrendContext(HttpServletRequest request, String monitorName, String fileName,
            Map<String, String> imgMap, Map<String, String[]> tables)
            throws Exception {

        // 生成路径
        /*
         * String projectPath = request.getRealPath("/");
         * projectPath = projectPath.substring(0,projectPath.indexOf("webapps") + 8) + "export";
         * File file = new File(projectPath);
         * if (!file.exists()) {
         * file.mkdir();
         * }
         */
        Document document = new Document(PageSize.A4);
        RtfWriter2.getInstance(document, UploadFileUtil.getInstance().getOut(fileName));
        float contextWidth = ITextUtil.getContextWidth(document);

        document.open();
        /*
         * // 设置中文字体
         * BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
         * // 标题字体风格
         * Font titleFont = new Font(bfChinese, 18, Font.BOLD, new Color(255, 0, 0));
         * // 正文字体风格
         * Font contextFont = new Font(bfChinese, 10, Font.NORMAL);
         * Font contextTitleFont = new Font(bfChinese, 14, Font.BOLD);
         */
        Font titleFont = new Font();
        titleFont.setSize(18);
        titleFont.setStyle(Font.BOLD);
        Font contextFont = new Font();
        contextFont.setSize(16);
        contextFont.setStyle(Font.BOLD);
        Font contextTitleFont = new Font();
        contextTitleFont.setSize(16);
        contextTitleFont.setStyle(Font.BOLD);
        Paragraph title = new Paragraph("重点监测 ——" + monitorName);
        // 设置标题格式对齐方式
        title.setAlignment(Element.ALIGN_CENTER);
        title.setFont(titleFont);
        document.add(title);
        String contextString = "";
        Paragraph context = new Paragraph(contextString);
        // 正文格式左对齐
        context.setAlignment(Element.ALIGN_CENTER);
        context.setFont(contextFont);
        // 离上一段落（标题）空的行数
        context.setSpacingBefore(0);
        // 设置第一行空的列数
        context.setFirstLineIndent(0);
        document.add(context);
        // 添加图片
        contextString = "\n整体趋势 ";

        Image png = null;
        ExportPicUtil.addDocument(contextString, context, contextTitleFont, document, png,
                "overall_trend_image", contextWidth, imgMap, 1f);

        if (tables.get(OVERALL_TREND_TABLE) != null && tables.get(OVERALL_TREND_TABLE).length > 0) {
            int overallTrendTableLen = tables.get(OVERALL_TREND_TABLE)[0].split("xaax").length;
            // 添加表格
            Table table = new Table(overallTrendTableLen);
            int[] width = { TABLE_TD_WIDTH, TABLE_TD_WIDTH, TABLE_TD_WIDTH, TABLE_TD_WIDTH };
            table.setWidths(width);// 设置每列所占比例
            table.setWidth(TABLE_WIDTH); // 占页面宽度 90%

            table.setAlignment(Element.ALIGN_CENTER);// 居中显示
            table.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            table.setAutoFillEmptyCells(true); // 自动填满
            // table.setBorderWidth(1); //边框宽度
            // table.setBorderColor(new Color(0, 125, 255)); //边框颜色
            table.setPadding(TABLE_PADDING);// 衬距
            // table.setSpacing(3);//即单元格之间的间距
            // table.setBorder(2);//边框
            table.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));// 背景颜色

            // 表格的主体
            for (int i = 0; i < tables.get(OVERALL_TREND_TABLE).length; i++) {
                String data = tables.get(OVERALL_TREND_TABLE)[i];
                String[] celldata = data.split("xaax");

                for (int j = 0; j < celldata.length; j++) {
                    table.addCell(celldata[j]);
                }
            }
            document.add(table);
        }

        // 第二张图片
        contextString = "\n关键词云 ";

        Image png2 = null;
        ExportPicUtil.addDocument(contextString, context, contextTitleFont, document, png2,
                "keyword_image", contextWidth, imgMap, 1f);
        if (tables.get(KEYWORD_TABLE_TABLEDATA) != null && tables.get(KEYWORD_TABLE_TABLEDATA).length > 0) {
            int keywordTableLen = tables.get(KEYWORD_TABLE_TABLEDATA)[0].split("xaax").length;
            // 添加表格
            Table table2 = new Table(keywordTableLen);
            int[] width2 = { TABLE_TD_WIDTH, TABLE_TD_WIDTH };
            table2.setWidths(width2);// 设置每列所占比例
            table2.setWidth(TABLE_WIDTH); // 占页面宽度 90%

            table2.setAlignment(Element.ALIGN_CENTER);// 居中显示
            table2.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            table2.setAutoFillEmptyCells(true); // 自动填满
            table2.setPadding(TABLE_PADDING);// 衬距
            table2.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));// 背景颜色

            // 表格的主体
            for (int i = 0; i < tables.get(KEYWORD_TABLE_TABLEDATA).length; i++) {
                String data = tables.get(KEYWORD_TABLE_TABLEDATA)[i];
                String[] celldata = data.split("xaax");

                for (int j = 0; j < celldata.length; j++) {
                    table2.addCell(celldata[j]);
                }
            }
            document.add(table2);
        }

        // 第三张图片
        contextString = "\n口碑词 ";

        Image png3 = null;
        ExportPicUtil.addDocument(contextString, context, contextTitleFont, document, png3,
                "praise_image", contextWidth, imgMap, 1f);
        if (tables.get(PRAISE_TABLEDATA) != null && tables.get(PRAISE_TABLEDATA).length > 0) {
            int len = tables.get(PRAISE_TABLEDATA)[0].split("xaax").length;
            // 添加表格
            Table table3 = new Table(len);
            int[] width3 = { TABLE_TD_WIDTH, TABLE_TD_WIDTH };
            table3.setWidths(width3);// 设置每列所占比例
            table3.setWidth(TABLE_WIDTH); // 占页面宽度 90%

            table3.setAlignment(Element.ALIGN_CENTER);// 居中显示
            table3.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            table3.setAutoFillEmptyCells(true); // 自动填满
            table3.setPadding(TABLE_PADDING);// 衬距
            table3.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));// 背景颜色

            // 表格的主体
            for (int i = 0; i < tables.get(PRAISE_TABLEDATA).length; i++) {
                String data = tables.get(PRAISE_TABLEDATA)[i];
                String[] celldata = data.split("xaax");

                for (int j = 0; j < celldata.length; j++) {
                    table3.addCell(celldata[j]);
                }
            }
            document.add(table3);
        }

        // 第四张图片
        contextString = "\n媒体趋势 ";

        Image png4 = null;
        ExportPicUtil.addDocument(contextString, context, contextTitleFont, document, png4,
                "media_image", contextWidth, imgMap, 1f);
        if (tables.get(MEDIA_TABLEDATA) != null && tables.get(MEDIA_TABLEDATA).length > 0) {
            // 添加表格
            int len = tables.get(MEDIA_TABLEDATA)[0].split("xaax").length;
            Table table4 = new Table(len);
            table4.setWidth(TABLE_WIDTH); // 占页面宽度 90%

            table4.setAlignment(Element.ALIGN_CENTER);// 居中显示
            table4.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            table4.setAutoFillEmptyCells(true); // 自动填满
            table4.setPadding(TABLE_PADDING);// 衬距
            table4.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));// 背景颜色

            // 表格的主体
            for (int i = 0; i < tables.get(MEDIA_TABLEDATA).length; i++) {
                String data = tables.get(MEDIA_TABLEDATA)[i];
                String[] celldata = data.split("xaax");

                for (int j = 0; j < celldata.length; j++) {
                    table4.addCell(celldata[j]);
                }
            }
            document.add(table4);
        }
        document.close();
    }

    // 消费评论
    private static final String COMMENT_TREND = "comment_trend"; // 评论趋势
    private static final String COMMENT_DISTRIBUTION = "comment_distribution"; // 评论分布
    private static final String KOUBEI_INFO = "koubei_info"; // 主要口碑
    private static final String KOUBEI_ANALYZE = "koubei_analyze"; // 口碑分析

    public static void createCommonContext(HttpServletRequest request, String monitorName, String fileName,
            Map<String, String> imgMap,
            String[] comment_trend_tabledata, String[] comment_distribution_tabledata,
            String[] koubei_infodata, String[] koubei_analyze_tabledata)
            throws Exception {
        // 生成路径
        // String projectPath = request.getRealPath("/");
        // projectPath = projectPath.substring(0,projectPath.indexOf("webapps") + 8) + "export";
        //
        // File file = new File(projectPath);
        // if (!file.exists()) {
        // file.mkdir();
        // }
        Document document = new Document(PageSize.A4);
        RtfWriter2.getInstance(document, UploadFileUtil.getInstance().getOut(fileName));
        float contextWidth = ITextUtil.getContextWidth(document);

        document.open();
        /*
         * // 设置中文字体
         * BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
         * // 标题字体风格
         * Font titleFont = new Font(bfChinese, 18, Font.BOLD, new Color(255, 0, 0));
         * // 正文字体风格
         * Font contextFont = new Font(bfChinese, 10, Font.NORMAL);
         * Font contextTitleFont = new Font(bfChinese, 14, Font.BOLD);
         */
        Font titleFont = new Font();
        titleFont.setSize(18);
        titleFont.setStyle(Font.BOLD);
        Font contextFont = new Font();
        contextFont.setSize(16);
        contextFont.setStyle(Font.BOLD);
        Font contextTitleFont = new Font();
        contextTitleFont.setSize(16);
        contextTitleFont.setStyle(Font.BOLD);
        Paragraph title = new Paragraph(monitorName + " —— 消费评论");
        // 设置标题格式对齐方式
        title.setAlignment(Element.ALIGN_CENTER);
        title.setFont(titleFont);
        document.add(title);
        String contextString = "";
        Paragraph context = new Paragraph(contextString);
        // 正文格式左对齐
        context.setAlignment(Element.ALIGN_CENTER);
        context.setFont(contextFont);
        // 离上一段落（标题）空的行数
        context.setSpacingBefore(0);
        // 设置第一行空的列数
        context.setFirstLineIndent(0);
        document.add(context);
        // 添加图片
        contextString = "\n评论趋势 \n\n";

        Image png = null;
        ExportPicUtil.addDocument(contextString, context, contextTitleFont, document, png,
                COMMENT_TREND, contextWidth, imgMap, 1f);

        contextString = "\n    ";
        context = new Paragraph(contextString);
        context.setAlignment(Element.ALIGN_CENTER);
        document.add(context);

        // 添加表格
        if (comment_trend_tabledata != null) {
            int length = comment_trend_tabledata[0].split("xaax").length;
            // 添加表格
            Table table = new Table(length);

            // 动态生成宽度
            int[] width = new int[length];
            for (int i = 0; i < length; i++) {
                width[i] = 100 / length;
            }
            table.setWidths(width);// 设置每列所占比例
            table.setWidth(100); // 占页面宽度 90%

            table.setAlignment(Element.ALIGN_CENTER);// 居中显示
            table.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            table.setAutoFillEmptyCells(true); // 自动填满
            // table.setBorderWidth(1); //边框宽度
            // table.setBorderColor(new Color(0, 125, 255)); //边框颜色
            table.setPadding(5);// 衬距
            // table.setSpacing(3);//即单元格之间的间距
            // table.setBorder(2);//边框
            table.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));// 背景颜色

            // 表格的主体
            for (int i = 0; i < comment_trend_tabledata.length; i++) {
                String data = comment_trend_tabledata[i];
                String[] celldata = data.split("xaax");

                for (int j = 0; j < celldata.length; j++) {
                    table.addCell(celldata[j]);
                }
            }
            document.add(table);
        }

        // 第二张图片
        contextString = "\n评论分布 \n\n";

        Image png2 = null;
        ExportPicUtil.addDocument(contextString, context, contextTitleFont, document, png2,
                COMMENT_DISTRIBUTION, contextWidth, imgMap, 1f);

        contextString = "\n    ";
        context = new Paragraph(contextString);
        context.setAlignment(Element.ALIGN_CENTER);
        document.add(context);

        // 添加表格
        if (comment_distribution_tabledata != null) {
            Table table2 = new Table(3);
            int[] width2 = { 30, 30, 40 };
            table2.setWidths(width2);// 设置每列所占比例
            table2.setWidth(100); // 占页面宽度 90%

            table2.setAlignment(Element.ALIGN_CENTER);// 居中显示
            table2.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            table2.setAutoFillEmptyCells(true); // 自动填满
            table2.setPadding(5);// 衬距
            table2.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));// 背景颜色

            // 表格的主体
            for (int i = 0; i < comment_distribution_tabledata.length; i++) {
                String data = comment_distribution_tabledata[i];
                String[] celldata = data.split("xaax");

                for (int j = 0; j < celldata.length; j++) {
                    table2.addCell(celldata[j]);
                }
            }
            document.add(table2);
        }

        // 第三张图片
        contextString = "\n主要口碑 \n\n";

        Image png3 = null;
        ExportPicUtil.addDocument(contextString, context, contextTitleFont, document, png3,
                KOUBEI_INFO, contextWidth, imgMap, 1f);

        contextString = "\n    ";
        context = new Paragraph(contextString);
        context.setAlignment(Element.ALIGN_CENTER);
        document.add(context);

        // 添加表格
        if (koubei_infodata != null) {
            Table table3 = new Table(3);
            int[] width3 = { 30, 30, 40 };
            table3.setWidths(width3);// 设置每列所占比例
            table3.setWidth(100); // 占页面宽度 90%

            table3.setAlignment(Element.ALIGN_CENTER);// 居中显示
            table3.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            table3.setAutoFillEmptyCells(true); // 自动填满
            table3.setPadding(5);// 衬距
            table3.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));// 背景颜色

            // 表格的主体
            for (int i = 0; i < koubei_infodata.length; i++) {
                String data = koubei_infodata[i];
                String[] celldata = data.split("xaax");

                for (int j = 0; j < celldata.length; j++) {
                    table3.addCell(celldata[j]);
                }
            }
            document.add(table3);
        }

        // 第四张图片
        contextString = "\n口碑分析 \n\n";

        Image png4 = null;
        ExportPicUtil.addDocument(contextString, context, contextTitleFont, document, png4,
                KOUBEI_ANALYZE, contextWidth, imgMap, 1f);

        contextString = "\n    ";
        context = new Paragraph(contextString);
        context.setAlignment(Element.ALIGN_CENTER);
        document.add(context);

        // 添加表格
        if (koubei_analyze_tabledata != null) {
            Table table4 = new Table(3);
            int[] width4 = { 30, 30, 40 };
            table4.setWidths(width4);// 设置每列所占比例
            table4.setWidth(100); // 占页面宽度 90%

            table4.setAlignment(Element.ALIGN_CENTER);// 居中显示
            table4.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
            table4.setAutoFillEmptyCells(true); // 自动填满
            table4.setPadding(5);// 衬距
            table4.setBackgroundColor(new Color(TABLE_BG_COLOR, TABLE_BG_COLOR, TABLE_BG_COLOR));// 背景颜色

            // 表格的主体
            for (int i = 0; i < koubei_analyze_tabledata.length; i++) {
                String data = koubei_analyze_tabledata[i];
                String[] celldata = data.split("xaax");

                for (int j = 0; j < celldata.length; j++) {
                    table4.addCell(celldata[j]);
                }
            }
            document.add(table4);
        }

        document.close();
    }

    public static Anchor insertContentisHyperlink(String contentStr, int fontsize, String bold,
            String italic, String url)
            throws DocumentException {
        // 超链接字体设置
        // FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 12, Font.UNDERLINE, Color.blue);
        Chunk paragraph = new Chunk(contentStr);

        Font font = new Font();
        if (-1 != fontsize) {
            font.setSize(fontsize);// 字体大小
        }

        if ("1".equals(bold) && "1".equals(italic)) {
            font.setStyle(Font.BOLD | Font.ITALIC);// 加粗并倾斜

        } else if ("0".equals(bold) && "1".equals(italic)) {
            font.setStyle(Font.ITALIC);// 倾斜
        } else if ("1".equals(bold) && "0".equals(italic)) {
            font.setStyle(Font.BOLD);// 加粗
        }

        // font.setStyle(Font.COURIER);// 默认字体
        paragraph.setFont(font);

        Chunk ddd = new Chunk(contentStr).setAnchor(url);
        ddd.setFont(font);
        Anchor anchor = new Anchor(ddd);
        anchor.setReference(url);

        return anchor;
    }

}
