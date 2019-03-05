package com.zhxg.yqzj.service.report;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.LineSpacingRule;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGrid;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGridCol;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

import com.zhxg.yqzj.dao.v1.AllReportDataDao;
import com.zhxg.yqzj.dao.v1.ReportDao;
import com.zhxg.yqzj.entities.v1.Condition;
import com.zhxg.yqzj.entities.v1.Info;

public class ReportListComponent extends ReportComponent {
    
    private static String [] titleNum = {"一","二","三","四","五","六","七","八","九","十","十一","十二","十三","十四","十五"};
    
    /**
     * 是否显示表格边框 0 不带边框 1 外边框 2 全边框
     */
    private int hasBoder = 0;

    private int infoIndex = 0;
    
    private ReportData data;
    
    private ReportDao reportDao;
    
    private AllReportDataDao allReportDao;
    
    /**
     * 分组选中字段 
     */
    private List<ReportGroupField> groupFields;
    
    private Condition condition;
    
    private Map<String,Object> paramMap;
    
    /**
     * 表格列表 标题单独在外：1 ，标题在表格 0
     */
    private int singleTitle = 0;
    
    public ReportDao getReportDao() {
        return reportDao;
    }

    public void setReportDao(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public ReportData getData() {
        return data;
    }

    public void setData(ReportData data) {
        this.data = data;
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
    
    
    public int getHasBoder() {
        return hasBoder;
    }

    public void setHasBoder(int hasBoder) {
        this.hasBoder = hasBoder;
    }

    
    public int getSingleTitle() {
        return singleTitle;
    }

    public void setSingleTitle(int singleTitle) {
        this.singleTitle = singleTitle;
    }
    
    public List<ReportGroupField> getGroupFields() {
        return groupFields;
    }

    
    public void setGroupFields(List<ReportGroupField> groupFields) {
        this.groupFields = groupFields;
    }

    private Object[] getSelectParamArr(){
        if(groupFields == null){
            this.groupFields = this.setListValue(sortSelect, sortSelectMap);
        }
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
    
    private List<ReportGroupField> setListValue(String[] sortValue,Map<String,String> sortInfo){
        List<ReportGroupField> groupInfo = new ArrayList<>();
        for (String selectKey : sortValue) {
            ReportGroupField reportGroupField = new ReportGroupField();
            String alais = sortInfo.get(selectKey);
            reportGroupField.setId(selectKey);
            reportGroupField.setName(alais);
            reportGroupField.setAlais(alais);
            groupInfo.add(reportGroupField);
        }
        return groupInfo;
    }

    /**
     * 画数据列表 
     * @param doc
     */
    public void draw(XWPFDocument doc){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.putAll(this.paramMap);
        List<Object> sortArr = null;
        paramMap.put("orderType", orderType);
        List<Info> dataList = null;
        
        if(0 == sortType){
            dataList = getList(paramMap);
            this.drawList(doc, dataList, null, 0);
        }else{
            Object[] sortSelect = this.getSelectParamArr();
            String key = 1 == sortType?"sourceType":2 == sortType ? "orientation" : "classifyIds";
            Object obj = paramMap.get(key);
            int titleIndex = 0;
            if(obj == null){
                for (Object selectKey : sortSelect) {
                    sortArr = new ArrayList<>();
                    sortArr.add(selectKey);
                    paramMap.put(key, sortArr.toArray());
                    dataList = getList(paramMap);
                    this.drawList(doc, dataList,selectKey.toString(),titleIndex++);   
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
                            this.drawList(doc, dataList,selectKey.toString(),titleIndex++); 
                        }
                    }
                }
            }
        }
    }
    
    public  void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {  
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {  
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);  
            if ( cellIndex == fromCell ) {  
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);  
            } else {  
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);  
            }  
        }  
    }


    public void drawListWithBoder(XWPFDocument doc,List<Info> dataList,String selectKey,int titleIndex){
        XWPFParagraph para = getParagraph(doc);
        para.setSpacingBetween(1.5, LineSpacingRule.AUTO);
        if(0 != sortType){
            this.setTitle(para, selectKey,titleIndex);
        }
        
        
        for(Info info:dataList){
            int rownum = 0;
            if(singleTitle==1){
                XWPFParagraph titlePara = doc.createParagraph();
                XWPFRun run = titlePara.createRun();
                setStyle(run,data.getRowList().get(0).getCellList().get(0).getStyle(),titlePara);
                run.setText(++infoIndex+"、");
                run.setText(info.getValueByField("title",data.getRowList().get(0).getCellList().get(0).getStyle().getDataFormat()));
            }
            XWPFTable table = doc.createTable(data.getRowList().size()-singleTitle,6);
            setTableStyle(table);
            //设置指定宽度
            CTTbl ttbl = table.getCTTbl(); 
            CTTblGrid tblGrid = ttbl.addNewTblGrid();
            int[] colWidths = new int[] { 1450,1450,1450,1450,1450,1450};
            for (int i : colWidths) {
                CTTblGridCol gridCol = tblGrid.addNewGridCol();
                gridCol.setW(new BigInteger(i+""));
            }
            a:for(ReportRow row:data.getRowList()){
                XWPFTableRow tablerow = table.getRow(rownum);
                int cellnum = 0;
                for(ReportCell cell:row.getCellList()){
                    if(singleTitle==1&&cell.getField().equals("title")){
                        continue a;
                    }
                    XWPFTableCell tablecell = null;
                    if(row.getCellList().size()==1){
                        mergeCellsHorizontal(table,rownum,cellnum,5);
                        tablecell = tablerow.getCell(cellnum);
                    }else if(row.getCellList().size()==2){
                        mergeCellsHorizontal(table,rownum,cellnum*3,cellnum*3+2);
                        tablecell = tablerow.getCell(cellnum*3);
                    }else if(row.getCellList().size()==3){
                        mergeCellsHorizontal(table,rownum,cellnum*2,cellnum*2+1);
                        tablecell = tablerow.getCell(cellnum*2);
                    }
                    cellnum++;
                    XWPFParagraph cellpara = tablecell.getParagraphs().get(0);
                    cellpara.setSpacingBetween(1.5, LineSpacingRule.AUTO);
                    XWPFRun run = cellpara.createRun();
                    String field = cell.getField();
                    setStyle(run,cell.getStyle(),cellpara);
                    run.setText(cell.getField().equals("title")?++infoIndex+"、"+cell.getFieldName()+"":cell.getFieldName()+"");
                    run.setText(info.getValueByField(field,cell.getStyle().getDataFormat()));
                }
                rownum++;
            }
//            XWPFParagraph titlePara = doc.createParagraph();
//            XWPFRun run = titlePara.createRun();
//            run.addBreak();
        }
        //修改入报信息
        this.setInfoStatus(dataList);
    }
    
    
    
    public void drawList(XWPFDocument doc,List<Info> dataList,String selectKey,int titleIndex){
        
        if(hasBoder > 0 || hasApproval == false){//调用另一个方法画带边框的数据列表
            drawListWithBoder(doc,dataList,selectKey,titleIndex);
            return;
        }
        XWPFParagraph para = getParagraph(doc);
        para.setSpacingBetween(1.5, LineSpacingRule.AUTO);
        if(0 != sortType){
            XWPFRun lineRun = para.createRun();
            lineRun.addBreak();
            this.setTitle(para, selectKey,titleIndex);
        }
        for(Info info:dataList){
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
            for(ReportRow row:data.getRowList()){
                XWPFRun rowrun = para.createRun();
                for(ReportCell cell:row.getCellList()){
                    XWPFRun run = para.createRun();
                    String field = cell.getField();
                    setStyle(run,cell.getStyle(),para);
                    run.setText(cell.getField().equals("title")?++infoIndex+"、"+cell.getFieldName()+"":cell.getFieldName()+"");
                    run.setText(info.getValueByField(field,cell.getStyle().getDataFormat()));
                    if(row.getCellList().indexOf(cell)<row.getCellList().size()-1){
                        run.addBreak();//带竖线的模板一行放不下两个字段，而且也不容易对齐，直接换行
                    }
                }
                rowrun.addBreak();
            }
        }
      //将信息修改为已入报
      this.setInfoStatus(dataList);
    }
    
    private void setStyle(XWPFRun run,ReportStyle reportStyle,XWPFParagraph para){
        run.setBold(reportStyle.getBold() == 1 ? true : false);
        run.setItalic(reportStyle.getItalic() == 1 ? true : false);
        run.setFontFamily(reportStyle.getFontFamily());
        run.setFontSize(reportStyle.getFontSize());
        run.setColor(reportStyle.getColor().replace("#", ""));
        para.setAlignment(ParagraphAlignment.valueOf(reportStyle.getAlignment()));
    }
    
   private void setTitle(XWPFParagraph para,String selectKey,int titleIndex){
       Map<String, String> selectParamMap = this.getSelectParamMap();
       String titleName = selectParamMap.get(selectKey);
       XWPFRun run = para.createRun();
       run.setText(titleNum[titleIndex]+"、"+titleName);
       run.setBold(true);
       run.setItalic(style.getItalic() == 1 ? true : false);
       run.setColor(style.getColor().replace("#", ""));
       run.setFontSize(style.getFontSize());
       run.setFontFamily(style.getFontFamily());
   }
   
   private void setTableStyle(XWPFTable table){
       
       CTTblBorders borders = table.getCTTbl().getTblPr().addNewTblBorders();
       String innerBoder = "none";
       String outerBoder = "none";
       if(hasBoder>1){
           innerBoder = "single";
       }
       if(hasBoder>0){
           outerBoder = "single";
       }
       
       CTBorder hBorder = borders.addNewInsideH();
       hBorder.setVal(STBorder.Enum.forString(innerBoder)); // 线条类型
       hBorder.setSz(new BigInteger("5")); // 线条大小
       hBorder.setColor("000000"); // 设置颜色

       CTBorder vBorder = borders.addNewInsideV();
       vBorder.setSz(new BigInteger("5"));
       vBorder.setVal(STBorder.Enum.forString(innerBoder));
       vBorder.setColor("000000");
       
       CTBorder lBorder = borders.addNewLeft();
       lBorder.setVal(STBorder.Enum.forString(outerBoder));
       lBorder.setSz(new BigInteger("5"));
       lBorder.setColor("000000");

       CTBorder rBorder = borders.addNewRight();
       rBorder.setVal(STBorder.Enum.forString(outerBoder));
       rBorder.setSz(new BigInteger("5"));
       rBorder.setColor("000000");

       CTBorder tBorder = borders.addNewTop();
       tBorder.setVal(STBorder.Enum.forString(outerBoder));
       tBorder.setSz(new BigInteger("5"));
       tBorder.setColor("000000");

       CTBorder bBorder = borders.addNewBottom();
       bBorder.setVal(STBorder.Enum.forString(outerBoder));
       bBorder.setSz(new BigInteger("5"));
       bBorder.setColor("000000");
       
   }
   
   private void setInfoStatus(List<Info> dataList){
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
