package com.zhxg.yqzj.service.impl.v1;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zhxg.framework.base.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhxg.framework.base.constants.Constant;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.yqzj.dao.v1.EventAnalysisDataDao;
import com.zhxg.yqzj.entities.v1.EventAnalysisData;
import com.zhxg.yqzj.service.v1.ExportReportService;

@Service
public class ExportReportServiceImpl extends BaseServiceImpl<EventAnalysisData>
        implements ExportReportService {

    private final static Logger log = LoggerFactory.getLogger(ExportReportServiceImpl.class);
    private static final String IMAGE_START_KEY = "";
    // 舆情专报 monitorname
    private static final String MONITORNAME = "monitorname";
    private static final String SPLITONE = "xaax";
    private static final String SPLITTWO = "aa";
    // 图片名称
    // 反战趋势图1
    private static final String DEVELOPMENT_TREND_ONEPIC = "developmentTrendOnePic";
    // 调性分析图1
    private static final String TONAL_ANALYSIS_ONEPIC = "tonalAnalysisOnePic";
    // 调性分析图2
    private static final String TONAL_ANALYSIS_TWOPIC = "tonalAnalysisTwoPic";
    // 关键词云图1
    private static final String KEY_WORD_ONEPIC = "keyWordOnePic";
    // 微博分析--大V分布图1
    private static final String MICROBLOG_BIGV_ONEPIC = "microblogBigvOnePic";
    // 微博分析--地域图1
    private static final String MICROBLOG_REGION_ONEPIC = "microblogRegionOnePic";
    // 微博分析--水军图1
    private static final String MICROBLOG_NAVY_ONEPIC = "microblogNavyOnePic";
    // 微博分析--情感图1
    private static final String MICROBLOG_EMOTION_ONEPIC = "microblogEmotionOnePic";
    // 媒体分析--媒体来源图1
    private static final String MEDIA_SOURCE_ONEPIC = "mediaSourceOnePic";
    // 媒体分析--媒体活跃度图1
    private static final String MEDIA_ACTIVITY_ONEPIC = "mediaActivityOnePic";
    // 观点分析--网民观点图1
    private static final String VIEWPOINT_NETIZEN_ONEPIC = "viewpointNetizenOnePic";

    @Autowired
    private EventAnalysisDataDao eventAnalysisDao;

    @Override
    protected BaseDao<EventAnalysisData> getBaseDao() {
        return this.eventAnalysisDao;
    }

    /**
     * 事件分析导出doc
     * 
     * @param request
     * @return String
     * @throws UnsupportedEncodingException
     */
    @Override
    public String exportReportInfo(HttpServletRequest request, Map<String, String> map, Map<String, Object> paramMap) {
        // 文档名称
        String monitorName = map.get(MONITORNAME);
        // 获取媒体活跃度事件ID
        String eventId = map.get("eventid");
        paramMap.put("eventId", eventId);
        // 处理文档开头基本信息
        // 事件分析--监测基本信息
        String event_monitor_base = map.get("event_monitor_base");
        String[] event_monitor_basedata = null;
        if (StringUtils.isNotBlank(event_monitor_base)) {
            // 基本信息
            event_monitor_basedata = HtmlFilter.filterall(event_monitor_base).split(SPLITONE);
        }
        // 处理文档事件概述信息
        // 事件分析--事件概述
        String event_summary_info = map.get("event_summary_info");

        // 处理文档首发媒体信息--表格传参为二维数组
        // 事件分析--首发媒体信息
        String first_media_info = map.get("first_media_info");
        String[] first_media_arr = null;
        String[][] first_media_table = null;
        if (StringUtils.isNotBlank(first_media_info)) {
            first_media_arr = HtmlFilter.filterall(first_media_info).split(SPLITONE);// 事件分析--首发媒体信息
            // 获取表格对应的二维数组
            first_media_table = setArrValue(first_media_arr, first_media_arr.length,
                    first_media_arr[0].split(SPLITTWO).length);// --表格
        }

        // 处理文档事件脉络信息
        String event_vein_info = map.get("event_vein_info");
        String[] event_vein_arr = null;
        String[][] event_vein_table = null;
        if (StringUtils.isNotBlank(event_vein_info)) {
            // 事件分析--事件脉络信息
            event_vein_arr = HtmlFilter.filterall(event_vein_info).split(SPLITONE);
            // 获取表格对应的二维数组
            event_vein_table = setArrValue(event_vein_arr, event_vein_arr.length,
                    event_vein_arr[0].split(SPLITTWO).length);
        }

        // 处理文档发展趋势信息 发展趋势正文下有一张图片--developmentTrendOnePic
        String development_trend_base = map.get("development_trend_base");

        // 处理文档调性分析信息 调性分析下有两张图片tonalAnalysisOnePic tonalAnalysisTwoPic
        String tonal_analysis_base = map.get("tonal_analysis_base");

        // 处理文档关键词云信息 关键词云下有一张图片keyWordOnePic
        // 概述信息
        String key_word_base = map.get("key_word_base");
        // 关键词云表格信息
        String key_word_info = map.get("key_word_info");
        String[] key_word_table = null;
        if (StringUtils.isNotBlank(key_word_info)) {
            // 高频词 表格
            key_word_table = HtmlFilter.filterall(key_word_info).split(SPLITONE);
        }

        // 处理文档微博分析信息
        // 概述信息
        String microblog_analysis_base = map.get("microblog_analysis_base");
        // 大V分布信息 图片+表格 图片：microblogAnalysisOnePic
        String microblog_bigv_info = map.get("microblog_bigv_info");
        String[] microblog_bigv_arr = null;
        String[][] microblog_bigv_table = null;
        if (StringUtils.isNotBlank(microblog_bigv_info)) {
            microblog_bigv_arr = HtmlFilter.filterWord(microblog_bigv_info).split(SPLITONE);
            // 获取表格对应的二维数组
            microblog_bigv_table = setArrValue(microblog_bigv_arr, microblog_bigv_arr.length,
                    microblog_bigv_arr[0].split(SPLITTWO).length);
        }
        // 影响力排行信息
        // 政府表格
        // 政府影响力排名
        String microblog_government_info = map.get("microblog_government_info");
        String[] microblog_government_arr = null;
        String[][] microblog_government_table = null;
        if (StringUtils.isNotBlank(microblog_government_info)) {
            microblog_government_arr = HtmlFilter.filterall(microblog_government_info).split(SPLITONE);
            // 获取表格对应的二维数组
            microblog_government_table = setArrValue(microblog_government_arr, microblog_government_arr.length,
                    microblog_government_arr[0].split(SPLITTWO).length);
        }
        // 媒体表格
        // 媒体影响力排名
        String microblog_media_info = map.get("microblog_media_info");
        String[] microblog_media_arr = null;
        String[][] microblog_media_table = null;
        if (StringUtils.isNotBlank(microblog_media_info)) {
            microblog_media_arr = HtmlFilter.filterall(microblog_media_info).split(SPLITONE);
            // 获取表格对应的二维数组
            microblog_media_table = setArrValue(microblog_media_arr, microblog_media_arr.length,
                    microblog_media_arr[0].split(SPLITTWO).length);
        }
        // 名人表格
        // 名人影响力排名
        String microblog_celebrity_info = map.get("microblog_celebrity_info");
        String[] microblog_celebrity_arr = null;
        String[][] microblog_celebrity_table = null;
        if (StringUtils.isNotBlank(microblog_celebrity_info)) {
            microblog_celebrity_arr = HtmlFilter.filterall(microblog_celebrity_info).split(SPLITONE);
            // 获取表格对应的二维数组
            microblog_celebrity_table = setArrValue(microblog_celebrity_arr, microblog_celebrity_arr.length,
                    microblog_celebrity_arr[0].split(SPLITTWO).length);
        }
        // 传播途径 概述+表格
        // 概述
        String microblog_spred_base = map.get("microblog_spred_base");
        String[] microblog_spred_baseData = null;
        if (StringUtils.isNotBlank(microblog_spred_base)) {
            microblog_spred_baseData = HtmlFilter.filterall(microblog_spred_base).split(SPLITONE);
        }
        // 表格信息
        String microblog_spred_info = map.get("microblog_spred_info");
        String[] microblog_spred_arr = null;
        String[][] microblog_spred_table = null;
        if (StringUtils.isNotBlank(microblog_spred_info)) {
            microblog_spred_arr = HtmlFilter.filterall(microblog_spred_info).split(SPLITONE);
            // 获取表格对应的二维数组
            microblog_spred_table = setArrValue(microblog_spred_arr, microblog_spred_arr.length,
                    microblog_spred_arr[0].split(SPLITTWO).length);
        }
        // 博主地域 图片+表格 图片：microblogRegionOnePic
        String microblog_region_leftInfo = map.get("microblog_region_leftInfo");
        String[] microblog_region_leftArr = null;
        String[][] microblog_region_leftTable = null;
        if (StringUtils.isNotBlank(microblog_region_leftInfo)) {
            microblog_region_leftArr = HtmlFilter.filterall(microblog_region_leftInfo).split(SPLITONE);
            // 获取表格对应的二维数组
            microblog_region_leftTable = setArrValue(microblog_region_leftArr, microblog_region_leftArr.length,
                    microblog_region_leftArr[0].split(SPLITTWO).length);
        }
        String microblog_region_rightInfo = map.get("microblog_region_rightInfo");
        String[] microblog_region_rightArr = null;
        String[][] microblog_region_rightTable = null;
        if (StringUtils.isNotBlank(microblog_region_rightInfo)) {
            microblog_region_rightArr = HtmlFilter.filterall(microblog_region_rightInfo).split(SPLITONE);
            // 获取表格对应的二维数组
            microblog_region_rightTable = setArrValue(microblog_region_rightArr, microblog_region_rightArr.length,
                    microblog_region_rightArr[0].split(SPLITTWO).length);
        }
        // 水军分析 图片：microblogNavyOnePic
        // 情感分析 图片：microblogEmotionOnePic

        // 处理文档媒体分析信息 概述+两个图片+一个表格
        // 概述信息
        String media_analysis_base = map.get("media_analysis_base");
        // 活跃媒体概述信息
        String media_active_base = map.get("media_active_base");
        // 媒体分析的表格数据需要查库查询出来
        List<Map<String, Object>> subList = this.eventAnalysisDao.exportWebName_other(paramMap);
        // 媒体分析表格只取10条数据
        int size = subList.size();
        List<Map<String, Object>> mediaList = null;
        if (size >= 10) {
            mediaList = subList.subList(0, 10);
        } else {
            mediaList = subList;
        }

        for (Map<String, Object> mediaMap : mediaList) {
            mediaMap.put("sourceType", SourceTypeUtil.getSourceName(mediaMap.get("sourceType").toString()));
        }
        /*
         * if (StringUtils.isNotBlank(media_analysis_info)) {
         * media_analysis_arr = HtmlFilter.filterall(media_analysis_info).split(SPLITONE);
         * // 获取表格对应的二维数组
         * media_analysis_table = setArrValue(media_analysis_arr, media_analysis_arr.length,
         * media_analysis_arr[0].split(SPLITTWO).length);
         * }
         */
        // 处理文档观点分析信息 一个图片+三个表格 图片：viewpointNetizenOnePic
        
        //网民观点概述信息
        String viewpoint_netizen_base = map.get("viewpoint_netizen_base");
        // 重点微博表格--政府
        String viewpoint_government_info = map.get("viewpoint_government_info");
        String[] viewpoint_government_arr = null;
        String[][] viewpoint_government_table = null;
        if (StringUtils.isNotBlank(viewpoint_government_info)) {
            viewpoint_government_arr = HtmlFilter.filterall(viewpoint_government_info).split(SPLITONE);
            // 获取表格对应的二维数组
            viewpoint_government_table = setArrValue(viewpoint_government_arr, viewpoint_government_arr.length,
                    viewpoint_government_arr[0].split(SPLITTWO).length);
        }

        // 重点微博表格--媒体
        String viewpoint_media_info = map.get("viewpoint_media_info");
        String[] viewpoint_media_arr = null;
        String[][] viewpoint_media_table = null;
        if (StringUtils.isNotBlank(viewpoint_media_info)) {
            viewpoint_media_arr = HtmlFilter.filterall(viewpoint_media_info).split(SPLITONE);
            // 获取表格对应的二维数组
            viewpoint_media_table = setArrValue(viewpoint_media_arr, viewpoint_media_arr.length,
                    viewpoint_media_arr[0].split(SPLITTWO).length);
        }

        // 重点微博表格--名人
        String viewpoint_celebrity_info = map.get("viewpoint_celebrity_info");
        String[] viewpoint_celebrity_arr = null;
        String[][] viewpoint_celebrity_table = null;
        if (StringUtils.isNotBlank(viewpoint_celebrity_info)) {
            viewpoint_celebrity_arr = HtmlFilter.filterall(viewpoint_celebrity_info).split(SPLITONE);
            // 获取表格对应的二维数组
            viewpoint_celebrity_table = setArrValue(viewpoint_celebrity_arr, viewpoint_celebrity_arr.length,
                    viewpoint_celebrity_arr[0].split(SPLITTWO).length);
        }

        // 获取对应图片并按名称存储图片
        Map<String, String> imgMap = ExportPicUtil.getParamsMapByKeysAndStartKey(map, IMAGE_START_KEY, request,
                DEVELOPMENT_TREND_ONEPIC, TONAL_ANALYSIS_ONEPIC, TONAL_ANALYSIS_TWOPIC, KEY_WORD_ONEPIC,
                MICROBLOG_BIGV_ONEPIC, MICROBLOG_REGION_ONEPIC, MICROBLOG_NAVY_ONEPIC, MICROBLOG_EMOTION_ONEPIC,
                MEDIA_SOURCE_ONEPIC, MEDIA_ACTIVITY_ONEPIC, VIEWPOINT_NETIZEN_ONEPIC);
        // this.setParams(imgMap, request, TREND_INFO, TREND_INFO_TOTAL);
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyyMMddHHmmss");
        String datetime = tempDate.format(new java.util.Date());
        String fileName = Constant.MODULE_BROADCASTANALYZE + "/" + datetime + "舆情专报——事件分析.docx";
        try {
            // 生成内容
            /*AnalyzeExportUtil.createEventContext(request, monitorName, fileName, imgMap, event_monitor_basedata,
                    event_summary_info, first_media_table, event_vein_table, development_trend_base,
                    tonal_analysis_base, key_word_base, key_word_table, microblog_analysis_base,
                    microblog_bigv_table,
                    microblog_government_table, microblog_media_table, microblog_celebrity_table,
                    microblog_spred_baseData,
                    microblog_spred_table, microblog_region_leftTable, microblog_region_rightTable,
                    media_analysis_base,
                    mediaList, viewpoint_government_table, viewpoint_media_table,
                    viewpoint_celebrity_table,media_active_base,viewpoint_netizen_base);*/
            AnalyzePOIExportUtil.createEventContext( fileName, imgMap, event_monitor_basedata,
                    event_summary_info, first_media_table, event_vein_table, development_trend_base,
                    tonal_analysis_base, key_word_base, key_word_table, microblog_analysis_base,
                    microblog_bigv_table,
                    microblog_government_table, microblog_media_table, microblog_celebrity_table,
                    microblog_spred_baseData,
                    microblog_spred_table, microblog_region_leftTable, microblog_region_rightTable,
                    media_analysis_base,
                    mediaList, viewpoint_government_table, viewpoint_media_table,
                    viewpoint_celebrity_table,media_active_base,viewpoint_netizen_base);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "http://" + PropertiesUtil.getValue("file.server.host") + "/" + fileName;
    }

    // 二维数组赋值
    protected String[][] setArrValue(String[] infodata, int line, int column) {
        String[][] infoTwo = new String[line][column];
        for (int i = 0; i < line; i++) {
            String[] one = infodata[i].split(SPLITTWO);
            for (int j = 0; j < column; j++) {
                infoTwo[i][j] = one[j];
            }
        }
        return infoTwo;
    }

}
