package com.zhxg.yqzj.service.report;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhxg.framework.base.utils.PhantomJSUtil;
import com.zhxg.framework.base.utils.UUIDUtil;
import com.zhxg.yqzj.dao.v1.AllReportDataDao;
import com.zhxg.yqzj.dao.v1.ReportClassifyDao;
import com.zhxg.yqzj.entities.v1.ReportClassify;

public class ReportChartComponent extends ReportComponent {
    
    private static JSONObject chartType = new JSONObject();

    private static JSONObject TIME_ORIENTATION = new JSONObject();

    private static JSONObject TIME_MEDIETYPE = new JSONObject();
    
    private static JSONObject TIME_LABEL = new JSONObject();

    private static JSONObject WEBNAME_TOP10 = new JSONObject();

    private static JSONObject AUTHOR_TOP10 = new JSONObject();

    private static JSONObject HOTWORD_TOP10 = new JSONObject();

    private static JSONObject ORIENTATION = new JSONObject();

    private static JSONObject MEDIETYPE = new JSONObject();
    
    private static JSONObject LABEL = new JSONObject();
    
    private AllReportDataDao allReportDao;
    
    private ReportClassifyDao reportClassifyDao;
    
    private Map<String,Object> paramMap;
    
    private static Map<String,Object> dataMap = new HashMap<String,Object>();

    public AllReportDataDao getAllReportDao() {
        return allReportDao;
    }

    public void setAllReportDao(AllReportDataDao allReportDao) {
        this.allReportDao = allReportDao;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public ReportClassifyDao getReportClassifyDao() {
        return reportClassifyDao;
    }

    public void setReportClassifyDao(ReportClassifyDao reportClassifyDao) {
        this.reportClassifyDao = reportClassifyDao;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    static {
        TIME_ORIENTATION.put("id", 1);
        TIME_ORIENTATION.put("name", "时间-倾向性");

        TIME_MEDIETYPE.put("id", 2);
        TIME_MEDIETYPE.put("name", "时间-媒体类型");
        
        TIME_LABEL.put("id", 3);
        TIME_LABEL.put("name", "时间-内容分类");

        WEBNAME_TOP10.put("id", 4);
        WEBNAME_TOP10.put("name", "来源网站-TOP10");

        AUTHOR_TOP10.put("id", 5);
        AUTHOR_TOP10.put("name", "作者-TOP10");

        HOTWORD_TOP10.put("id", 6);
        HOTWORD_TOP10.put("name", "热词-TOP10");

        ORIENTATION.put("id", 7);
        ORIENTATION.put("name", "倾向性");

        MEDIETYPE.put("id", 8);
        MEDIETYPE.put("name", "媒体类型");
        
        LABEL.put("id", 9);
        LABEL.put("name", "内容分类");

        JSONArray lineArr = new JSONArray();
        lineArr.add(TIME_ORIENTATION);
        lineArr.add(TIME_MEDIETYPE);
        lineArr.add(TIME_LABEL);
        chartType.put("line", lineArr);

        JSONArray barArr = new JSONArray();
        barArr.add(TIME_ORIENTATION);
        barArr.add(MEDIETYPE);
        barArr.add(TIME_LABEL);
//        barArr.add(WEBNAME_TOP10);
//        barArr.add(AUTHOR_TOP10);
//        barArr.add(HOTWORD_TOP10);
        chartType.put("bar", lineArr);

        JSONArray pieArr = new JSONArray();
        pieArr.add(ORIENTATION);
        pieArr.add(MEDIETYPE);
        pieArr.add(LABEL);
//        pieArr.add(WEBNAME_TOP10);
//        pieArr.add(AUTHOR_TOP10);
//        pieArr.add(HOTWORD_TOP10);
        chartType.put("pie", pieArr);
    }

    private String group;
    private String field;
    private String union;
    private String order;
    private String limit;
    private String title;
    
    private List<Map<String, Object>> getData() {
        Map<String, Object> distinceMap = allReportDao.getEchartDataDistance_self(paramMap);
        if(distinceMap==null){
            return new ArrayList<Map<String, Object>>();
        }
        int distance = Integer.parseInt(distinceMap.get("distance").toString());
        String maxTime = distinceMap.get("maxTime").toString();
        String minTime = distinceMap.get("minTime").toString();
        String format = "yyyy-MM-dd";
        int groupTime = 10;
        if(distance>24){
            maxTime = maxTime.substring(0,10);
            minTime = minTime.substring(0,10);
        }else{
            groupTime = 13;
            format = "yyyy-MM-dd HH";
            maxTime = maxTime.substring(0,13);
            minTime = minTime.substring(0,13);
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar endTime = Calendar.getInstance();
        try {
            endTime.setTime(sdf.parse(maxTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar beginTime = Calendar.getInstance();
        try {
            beginTime.setTime(sdf.parse(minTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        switch (chartTypeId) {
            case 1: {
                String[] row = new String[]{"正面","负面","中性"};
                title = "倾向性统计";
                group = "left(publishTime,"+groupTime+"),orientation";
                field = "left(publishTime,"+groupTime+") col,case orientation when 1 then '正面' when 2 then '负面' else '中性' end row,count(1) count";
                order = " col ,count desc";
                StringBuilder sb = new StringBuilder();
                while(endTime.after(beginTime)){
                    for(String r:row){
                        sb.append(" union (SELECT '"+sdf.format(beginTime.getTime())+"' as col,'"+r+"' as row,0 as count)");
                    }
                    if(distance>24){
                        beginTime.add(Calendar.DAY_OF_MONTH, 1);
                    }else{
                        beginTime.add(Calendar.HOUR, 1);
                    }
                }
                union = sb.toString();
                break;
            }
            case 2: {
                String[] row = new String[]{"网媒","论坛","博客","微博","报刊","微信","视频","APP","评论","其他"};
                title = "媒体统计";
                group = "left(publishTime,"+groupTime+"),sourceType";
                field = "left(publishTime,"+groupTime+") col,case sourceType when '01' then '网媒' when '02' then '论坛' when '03' then '博客' when '04' then '微博' "
                        + " when '05' then '报刊' when '06' then '微信' when '07' then '视频' when '09' then 'APP' when '10' then '评论'"
                        + "    else '其他' end row,count(1) count";
                order = "col";
                StringBuilder sb = new StringBuilder();
                while(endTime.after(beginTime)){
                    for(String r:row){
                        sb.append(" union (SELECT '"+sdf.format(beginTime.getTime())+"' as col,'"+r+"' as row,0 as count)");
                    }
                    if(distance>24){
                        beginTime.add(Calendar.DAY_OF_MONTH, 1);
                    }else{
                        beginTime.add(Calendar.HOUR, 1);
                    }
                }
                union = sb.toString();
                break;
            }
            case 3: {
                List<ReportClassify> l = reportClassifyDao.getReportClassifys(paramMap);
                title = "内容分类统计";
                group = "left(publishTime,"+groupTime+"),classifyId";
                StringBuilder sb = new StringBuilder("left(publishTime,"+groupTime+") col,case classifyId ");
                for(ReportClassify rc:l){
                    sb.append(" when "+rc.getId()+" then '"+rc.getClassifyName()+"'");
                }
                sb.append(" else '未分类' end row,count(1) count");
                field = sb.toString();
                order = "col";
                
                StringBuilder s = new StringBuilder();
                while(endTime.after(beginTime)){
                    for(ReportClassify rc:l){
                        s.append(" union (SELECT '"+sdf.format(beginTime.getTime())+"' as col,'"+rc.getClassifyName()+"' as row,0 as count)");
                        s.append(" union (SELECT '"+sdf.format(beginTime.getTime())+"' as col,'未分类' as row,0  as count)");
                    }
                    if(distance>24){
                        beginTime.add(Calendar.DAY_OF_MONTH, 1);
                    }else{
                        beginTime.add(Calendar.HOUR, 1);
                    }
                }
                union = s.toString();
                break;
            }
            case 4: {
                title = "来源网站";
                group = "webName";
                field = "webName row,webName col,count(1) count";
                order = "count desc";
                limit = "10";
                break;
            }
            case 7: {
                title = "倾向性统计";
                group = "orientation";
                field = "case orientation when 1 then '正面' when 2 then '负面' else '中性' end row,count(1) count";
                order = "count desc";
                break;
            }
            case 8: {
                title = "媒体统计";
                group = "sourceType";
                field = "case sourceType when '01' then '网媒' when '02' then '论坛' when '03' then '博客' when '04' then '微博' "
                        + " when '05' then '报刊' when '06' then '微信' when '07' then '视频' when '09' then 'APP' when '10' then '评论'"
                        + "  else '其他' end row,count(1) count";
                order = "count desc";
                break;
            }
            case 9: {
                List<ReportClassify> l = reportClassifyDao.getReportClassifys(paramMap);
                title = "内容分类统计";
                group = "classifyId";
                StringBuilder sb = new StringBuilder("case classifyId ");
                for(ReportClassify rc:l){
                    sb.append(" when "+rc.getId()+" then '"+rc.getClassifyName()+"'");
                }
                sb.append(" else '未分类' end row,count(1) count");
                field = sb.toString();
                order = "count desc";
                break;
            }
        }
        paramMap.put("group", group);
        paramMap.put("field", field);
        paramMap.put("limit", limit);
        paramMap.put("order", order);
        paramMap.put("union", union);
        return allReportDao.getEchartDataList_self(paramMap);
        
    }

    private String type = "bar";// line bar pie

    private int chartTypeId;// 1-7

    private String text;
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static JSONObject getChartType() {
        return chartType;
    }

    public static void setChartType(JSONObject chartType) {
        ReportChartComponent.chartType = chartType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getChartTypeId() {
        return chartTypeId;
    }

    public void setChartTypeId(int chartTypeId) {
        this.chartTypeId = chartTypeId;
    }

    public void draw(XWPFDocument doc) {
        XWPFParagraph p = doc.createParagraph();
        XWPFRun run = p.createRun();
        byte[] chartFile = null;
        if(type.equals("line")){
            chartFile = createChartLine();
        }else if(type.equals("pie")){
            chartFile = createChartPancake();
        }else{
            chartFile = createChartBar();
        }
        try {
//            run.addPicture(new FileInputStream(chartFile), 6, "", Units.toEMU(410), Units.toEMU(300));
            run.addPicture(new ByteArrayInputStream(chartFile), 6, "", Units.toEMU(410), Units.toEMU(300));
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 生成柱状图
    public File createChartBar1() {
        CategoryDataset dataSet = getBarDataSet();
        JFreeChart chart = ChartFactory.createBarChart(title+"统计", title, "数量", dataSet, PlotOrientation.VERTICAL, true, false, false);
        // 获取图表区域对象
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.white);
        // 水平底部列表
        CategoryAxis domainAxis = plot.getDomainAxis();
        // 水平底部标题
        domainAxis.setLabelFont(new Font("宋体", Font.BOLD, 10));
        // 垂直标题
        domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 10));
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        ValueAxis rangeAxis = plot.getRangeAxis();// 获取柱状
        rangeAxis.setLabelFont(new Font("宋体", Font.BOLD, 10));
        chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 10));
        // 设置标题字体
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 15));

        String filename = UUIDUtil.getUuid() + ".png";
        File file = new File(filename);
        try {
            ChartUtilities.saveChartAsPNG(file, chart, 410, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    // 生成饼状图
    public File createChartPancake1() {
        DefaultPieDataset data = getPieDataSet();
        JFreeChart chart = ChartFactory.createPieChart(title+"统计", data, true, false, false);
        PiePlot pieplot = (PiePlot) chart.getPlot();
        pieplot.setBackgroundPaint(Color.white);
        
        DecimalFormat df = new DecimalFormat("0.00%");
        NumberFormat nf = NumberFormat.getNumberInstance();
        StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);
        // 设置饼图显示百分比
        pieplot.setLabelGenerator(sp1);

        // 没有数据的时候显示的内容
        pieplot.setNoDataMessage("无数据显示");
        pieplot.setCircular(false);
        pieplot.setLabelGap(0.02D);
        // 设置不显示空值
        pieplot.setIgnoreNullValues(true);
        // 设置不显示负值
        pieplot.setIgnoreZeroValues(true);

        chart.getTitle().setFont(new Font("黑体", Font.BOLD, 15));
        PiePlot piePlot = (PiePlot) chart.getPlot();
        // 解决乱码
        piePlot.setLabelFont(new Font("宋体", Font.PLAIN, 10));
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 10));


        String filename = UUIDUtil.getUuid() + ".png";
        File file = new File(filename);
        try {
            ChartUtilities.saveChartAsPNG(file, chart, 410, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
    
    public byte[] createChartLine() {
        List<Map<String,Object>> list = getData();
        String imgStr= PhantomJSUtil.dealLineData(JSON.toJSONString(list),title);
        return PhantomJSUtil.saveSurfModelUrlToImg(imgStr,"line");
    }
    
    public byte[] createChartBar() {
        List<Map<String,Object>> list = getData();
        String imgStr= "";
        if(chartTypeId==8 || chartTypeId == 9){
            imgStr = PhantomJSUtil.dealBarData2(JSON.toJSONString(list),title);
        }else{
            imgStr = PhantomJSUtil.dealBarData(JSON.toJSONString(list),title);
        }
        return PhantomJSUtil.saveSurfModelUrlToImg(imgStr,"bar");
    }
    public byte[] createChartPancake() {
        List<Map<String,Object>> list = getData();
        String imgStr= PhantomJSUtil.dealPieData(JSON.toJSONString(list),title);
        return PhantomJSUtil.saveSurfModelUrlToImg(imgStr,"pie");
    }
    
    // 生成折线图
    public File createChartLine1() {
        CategoryDataset dataSet = getLineDataSet();
        JFreeChart chart = ChartFactory.createLineChart(title+"统计", "时间", "数量", dataSet, PlotOrientation.VERTICAL, true, true, false);
        Font titleFont = new Font("宋体", Font.BOLD, 15);
        Font contentFont = new Font("宋体",Font.BOLD,10);
        // 设置标题字体  
        chart.getTitle().setFont(titleFont);
        chart.getLegend().setItemFont(titleFont);
        
        //获取绘图区对象  
        CategoryPlot plot = chart.getCategoryPlot();  
        plot.setBackgroundPaint(Color.white);  
        // 设置水平方向背景线颜色 
        plot.setRangeGridlinePaint(Color.black); 
        // 设置是否显示水平方向背景线,默认值为true 
        plot.setRangeGridlinesVisible(true); 
        // 设置垂直方向背景线颜色  
        plot.setDomainGridlinePaint(Color.black);
        // 设置是否显示垂直方向背景线,默认值为false
        plot.setDomainGridlinesVisible(true);  
          
        // 设置横轴字体
        CategoryAxis domainAxis = plot.getDomainAxis();     
        domainAxis.setLabelFont(contentFont);  
        domainAxis.setTickLabelFont(contentFont); 
        // 左边距 边框距离  
        domainAxis.setLowerMargin(0.01);
        // 右边距 边框距离,防止最后边的一个数据靠近了坐标轴。
        domainAxis.setUpperMargin(0.06);  
        domainAxis.setMaximumCategoryLabelLines(2);  
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
          
        ValueAxis rangeAxis = plot.getRangeAxis();  
        rangeAxis.setLabelFont(contentFont);   
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());//Y轴显示整数  
        rangeAxis.setAutoRangeMinimumSize(1);   //最小跨度  
        rangeAxis.setUpperMargin(0.18);//上边距,防止最大的一个数据靠近了坐标轴。     
        rangeAxis.setLowerBound(0);   //最小值显示0  
        rangeAxis.setAutoRange(false);   //不自动分配Y轴数据  
        rangeAxis.setTickMarkStroke(new BasicStroke(1.6f));     // 设置坐标标记大小  
        rangeAxis.setTickMarkPaint(Color.BLACK);     // 设置坐标标记颜色  
  
          
          
        // 获取折线对象  
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();  
        BasicStroke realLine = new BasicStroke(1.8f); // 设置实线  
        for (int i = 0; i < dataSet.getRowCount(); i++) {
            renderer.setSeriesStroke(i, realLine);
        }
        
        plot.setNoDataMessage("无对应的数据，请重新查询。");  
        plot.setNoDataMessageFont(titleFont);//字体的大小  
        plot.setNoDataMessagePaint(Color.RED);//字体颜色  
        
        String filename = UUIDUtil.getUuid()+".png";
        File file = new File(filename);
        try {
            ChartUtilities.saveChartAsPNG(file,chart,410,300);
       } catch (IOException e) {
           e.printStackTrace();
       }
        return file;
    }

    private CategoryDataset getBarDataSet() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Map<String, Object>> list = getData();
        for(Map<String,Object> map:list){
            dataset.addValue(Integer.parseInt(map.get("count")+"") , map.get("row")+"", map.get("col")+"");
        }
        return dataset;
    }
    
    private DefaultPieDataset getPieDataSet() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        List<Map<String, Object>> list = getData();
        for(Map<String,Object> m:list){
            String name = m.get("row") + "";
            int value = Integer.parseInt(m.get("count") + "");
            dataset.setValue(name, value);
        }
        return dataset;
    }
    
    @SuppressWarnings("unchecked")
    private CategoryDataset getLineDataSet(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        List<Map<String,Object>> list = getData();
        
        for (Map<String, Object> map : list) {
            dataset.addValue(Integer.parseInt(map.get("count")+"") , map.get("row")+"", map.get("col")+"");
        }
        if(type.equals("line")){
            List<String> columnKeys = dataset.getColumnKeys();
            List<String> rowKeys = dataset.getRowKeys();
            for (String col : columnKeys) {
                for (String row : rowKeys) {
                    Number value = dataset.getValue(row, col);
                    if(value == null){
                        dataset.addValue(0, row, col);
                    }
                }
            }
        }
        return dataset;
    }
    
}
