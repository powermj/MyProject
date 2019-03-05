package com.zhxg.yqzj.service.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.zhxg.framework.base.utils.DateStyle;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.yqzj.dao.v1.AllReportDataDao;
import com.zhxg.yqzj.entities.v1.Info;

public class ReportCustomListComponent extends ReportComponent {
    
    public static Map<String,Integer> censusNumMap = new HashMap<>();
    {
        censusNumMap.put("total", 0);
        censusNumMap.put("1", 0);
        censusNumMap.put("2", 0);
        censusNumMap.put("3", 0);
        censusNumMap.put("网媒", 0);
        censusNumMap.put("论坛", 0);
        censusNumMap.put("博客", 0);
        censusNumMap.put("微博", 0);
        censusNumMap.put("报刊", 0);
        censusNumMap.put("微信", 0);
        censusNumMap.put("视频", 0);
        censusNumMap.put("APP", 0);
        censusNumMap.put("评论", 0);
        censusNumMap.put("小视频", 0);
        censusNumMap.put("其他", 0);
    }
    
    public static Map<String,String> censusFieldMap = new HashMap<>();
    {
        censusFieldMap.put("${total}", "total");
        censusFieldMap.put("${timeslot}", "timeslot");
        censusFieldMap.put("${positiveNum}", "1");
        censusFieldMap.put("${negativeNum}", "2");
        censusFieldMap.put("${neutralNum}", "3");
        censusFieldMap.put("${networkNum}", "网媒");
        censusFieldMap.put("${forumNum}", "论坛");
        censusFieldMap.put("${blogNum}", "博客");
        censusFieldMap.put("${microblogNum}", "微博");
        censusFieldMap.put("${newspapersNum}", "报刊");
        censusFieldMap.put("${wechatNum}", "微信");
        censusFieldMap.put("${videoNum}", "视频");
        censusFieldMap.put("${appNum}", "APP");
        censusFieldMap.put("${commentNum}", "评论");
        censusFieldMap.put("${minVideoNum}", "小视频");
        censusFieldMap.put("${otherNum}", "其他");
    }

    private String customText;

    private AllReportDataDao allReportDao;
    
    private Map<String,Object> paramMap;
    
    public String getCustomText() {
        return customText;
    }

    public void setCustomText(String customText) {
        this.customText = customText.replace("#nbsp#", " ");
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
    
    private List<Info> getList(Map<String,Object> params){
        return allReportDao.getDataForExport_self(params);
    }

    private Map<String,Object> getMap(Map<String,Object> params){
        return allReportDao.getEchartDataDistance_self(params);
    }


    /**
     * 自定义信息列表
     * @param doc
     */
    public void drawCustomList(XWPFDocument doc) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.putAll(this.paramMap);
        List<Info> dataList = this.getList(paramMap);
        int infoIndex = 0;
        for(Info info:dataList){
            XWPFParagraph para = getParagraph(doc);
//            para.setStyle("1");
//            if(index>0){
//                XWPFRun r = para.createRun();
//                r.setBold(true);
//                r.setText(index+"、"+name);
//            }
//            if(style.getWordWrapped() == 1){
//                para.setWordWrapped(true); 
//            }else if(para.isWordWrap()){
//                para.setWordWrapped(false); 
//            }
            XWPFRun run = para.createRun();
            setStyle(run,style,para);
            String text = replaceCustomText(customText,info);
            if(text.contains("#{wpcr}#")){
                String[] textArr = text.split("#\\{wpcr\\}#",text.length());
                for (int i = 0; i < textArr.length; i++) {
                    if(i == 0){
                        run.addBreak();
                        run.setText(++infoIndex+"、"+textArr[i]);
                    }else{
                        run.addBreak();
                        run.setText(textArr[i]); 
                    }
                }
            }else{
                run.setText(++infoIndex+"、"+text); 
            }
        }
      //将信息修改为已入报
      this.setInfoStatus(dataList);
    }

    public void drawCustomSummary(XWPFDocument doc){
        XWPFParagraph para = getParagraph(doc);
        XWPFRun run = para.createRun();
        setStyle(run,style,para);
        customText = this.replaceCustomNum(customText);
        String[] textArr = null;
        if(customText.contains("#{wpcr}#")){
            textArr = customText.split("#\\{wpcr\\}#");
            int breakNum = 0;
            for (String textSplit : textArr) {
                breakNum ++;
                run.setText(textSplit);
                if(breakNum != textArr.length){
                    run.addBreak();
                }
            }
        }else{
            run.setText(customText);
            run.addBreak();
        }
    }

    public String replaceCustomText(String customText,Info info){
        Map<String, String> fieldMap = Info.fieldNameMap;
        for(String key : fieldMap.keySet()){
            if(customText.contains(key)){
                    customText = customText.replace(key, info.getValueByField(fieldMap.get(key), style.getDataFormat()));
                }
            }
        return customText;
    }
    
    public String replaceCustomNum(String customText){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.putAll(this.paramMap);
        List<Info> dataList = this.getList(paramMap);
        censusNumMap.put("total", dataList.size());
        for (Info info : dataList) {
            censusNumMap.put(info.getOrientation(), censusNumMap.get(info.getOrientation().equals("5")?"2":info.getOrientation())+1);
            censusNumMap.put(info.getSourceType(), censusNumMap.get(info.getSourceType())+1);
        }
        for (String key : censusFieldMap.keySet()) {;
            if(customText.contains("${timeslot}") && "${timeslot}".equals(key)){
                Map<String, Object> timeMap = this.getMap(paramMap);
                String minTime = DateUtil.StringToString(timeMap.get("minTime")+"", DateStyle.YYYY_MM_DD_HH_MM_SS);
                String maxTime = DateUtil.StringToString(timeMap.get("maxTime")+"", DateStyle.YYYY_MM_DD_HH_MM_SS);
                customText = customText.replace(key, minTime + "--" +maxTime); 
            }else{
                customText = customText.replace(key, censusNumMap.get(censusFieldMap.get(key))+"");
            }
        }
        return customText;
    }
    
    
    private void setStyle(XWPFRun run,ReportStyle reportStyle,XWPFParagraph para){
        run.setBold(reportStyle.getBold() == 1 ? true : false);
        run.setItalic(reportStyle.getItalic() == 1 ? true : false);
        run.setFontFamily(reportStyle.getFontFamily());
        run.setFontSize(reportStyle.getFontSize());
        run.setColor(reportStyle.getColor().replace("#", ""));
        para.setAlignment(ParagraphAlignment.valueOf(reportStyle.getAlignment()));
    }
    
    private void setInfoStatus(List<Info> dataList){
		Map<String, Object> paramMap = new HashMap<>();
        paramMap.putAll(this.paramMap);
        if(dataList.size() > 0){
            List<String> infoUuidList = new ArrayList<>(dataList.size());
            
            for (Info info : dataList) {
                infoUuidList.add(info.getInfoUuid());
            }
            paramMap.put("infoUuidArr", infoUuidList.toArray()); 
            allReportDao.setInfoStatus_self(paramMap);
        }
    }
}
