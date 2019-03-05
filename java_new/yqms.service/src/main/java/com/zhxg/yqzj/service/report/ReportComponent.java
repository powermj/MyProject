package com.zhxg.yqzj.service.report;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblLayoutType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STStyleType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblLayoutType;

import com.alibaba.fastjson.JSON;

public class ReportComponent {
    
    protected int id;
    
    protected boolean hasApproval;
    
    protected boolean hasTransverse;
    
    protected int index;
    
    protected String name;
    
    protected ReportStyle style;
    
    protected ReportStyle timestype;
    
    protected int directoryLevel ;
    
    protected int sortType;
    
    protected String[] sortSelect;
    
    protected Map<String,String> sortSelectMap;
    
    protected String[] sortNoSelect;
    
    protected Map<String,String> sortNoSelectMap;

    protected int orderType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHasApproval() {
        return hasApproval;
    }

    public void setHasApproval(boolean hasApproval) {
        this.hasApproval = hasApproval;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReportStyle getStyle() {
        return style;
    }

    public void setStyle(ReportStyle style) {
        this.style = style;
    }
    
    public int getDirectoryLevel() {
        return directoryLevel;
    }

    public void setDirectoryLevel(int directoryLevel) {
        this.directoryLevel = directoryLevel;
    }
    
    public boolean isHasTransverse() {
        return hasTransverse;
    }

    
    public void setHasTransverse(boolean hasTransverse) {
        this.hasTransverse = hasTransverse;
    }
    
    
    public int getOrderType() {
        return orderType;
    }

    
    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public int getSortType() {
        return sortType;
    }

    
    public void setSortType(int sortType) {
        this.sortType = sortType;
    }

    public String[] getSortSelect() {
        return sortSelect;
    }

    
    public void setSortSelect(String sortSelect) {
        this.sortSelect = StringUtils.isEmpty(sortSelect) ? null : sortSelect.split(",");
    }

    
    public String[] getSortNoSelect() {
        return sortNoSelect;
    }

    
    public void setSortNoSelect(String sortNoSelect) {
        this.sortNoSelect = StringUtils.isEmpty(sortNoSelect) ? null : sortNoSelect.split(",");
    }

    
    public Map<String, String> getSortSelectMap() {
        return sortSelectMap;
    }
    public ReportStyle getTimestype() {
        return timestype;
    }

    
    public void setTimestype(ReportStyle timestype) {
        this.timestype = timestype;
    }


    
    
    public Map<String, String> getSortNoSelectMap() {
        return sortNoSelectMap;
    }
    
    @SuppressWarnings("unchecked")
    public void setSortSelectMap(String sortSelectMap) {
        this.sortSelectMap = JSON.parseObject(sortSelectMap, Map.class);
    }
    @SuppressWarnings("unchecked")
    public void setSortNoSelectMap(String sortNoSelectMap) {
        this.sortNoSelectMap = JSON.parseObject(sortNoSelectMap, Map.class);;
    }

    protected XWPFParagraph getParagraph(XWPFDocument doc) {
        addCustomHeadingStyle(doc,"标题1",1);
        addCustomHeadingStyle(doc,"标题2",1);
        
        XWPFParagraph para = doc.createParagraph();
//      List<XWPFParagraph> paras = doc.getParagraphs();
//      if (paras.isEmpty()) {
//          para = doc.createParagraph();
//      }else{
//          para = doc.getLastParagraph();
//      }
      if (hasApproval) {
          List<XWPFTable> tables = doc.getTables();
          if(tables.isEmpty()){
              initTable(doc);
          }
          //initTable(doc,table,tables.size());
          XWPFTableRow row = tables.get(tables.size() - 1).getRow(0);
          XWPFTableCell cell = row.getCell(0);
          para = cell.getParagraphArray(0);  
//        para = cell.addParagraph();
      }
      return para;
    }
    
    protected void initTable(XWPFDocument doc){
        XWPFTable table = doc.createTable();
        CTTblBorders borders = table.getCTTbl().getTblPr().addNewTblBorders();
        CTBorder hBorder = borders.addNewInsideH();
        hBorder.setVal(STBorder.Enum.forString("none")); // 线条类型
        hBorder.setSz(new BigInteger("1")); // 线条大小
        hBorder.setColor("000000"); // 设置颜色

        CTBorder vBorder = borders.addNewInsideV();
        vBorder.setSz(new BigInteger("15"));
        vBorder.setVal(STBorder.Enum.forString("single"));
        vBorder.setColor("FF0000");
        

        CTBorder lBorder = borders.addNewLeft();
        lBorder.setVal(STBorder.Enum.forString("none"));
        lBorder.setSz(new BigInteger("1"));
        lBorder.setColor("000000");

        CTBorder rBorder = borders.addNewRight();
        rBorder.setVal(STBorder.Enum.forString("none"));
        rBorder.setSz(new BigInteger("1"));
        rBorder.setColor("000000");

        CTBorder tBorder = borders.addNewTop();
        tBorder.setVal(STBorder.Enum.forString("none"));
        tBorder.setSz(new BigInteger("1"));
        tBorder.setColor("000000");

        CTBorder bBorder = borders.addNewBottom();
        bBorder.setVal(STBorder.Enum.forString("none"));
        bBorder.setSz(new BigInteger("1"));
        bBorder.setColor("000000");
        
//        CTTbl ttbl = table.getCTTbl();
//        CTTblGrid tblGrid = ttbl.addNewTblGrid();
//        int[] colWidths = new int[] { 6500, 2000 };
//        for (int i : colWidths) {
//            CTTblGridCol gridCol = tblGrid.addNewGridCol();
//            gridCol.setW(new BigInteger(i + ""));
//        }
        
        XWPFTableRow row = table.getRow(0);
        XWPFTableCell cell1 = row.getCell(0);
        cell1.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(7000L));
        XWPFTableCell cell2 = row.createCell();
        cell2.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2500L));
        CTTbl ttbl = table.getCTTbl();
        CTTblPr tblPr = ttbl.getTblPr() == null ? ttbl.addNewTblPr() : ttbl.getTblPr();
        CTTblLayoutType t = tblPr.isSetTblLayout()?tblPr.getTblLayout() : tblPr.addNewTblLayout();
        t.setType(STTblLayoutType.FIXED);
        
        XWPFParagraph para = cell2.getParagraphArray(0);
        para.setAlignment(ParagraphAlignment.valueOf(1));
        XWPFRun run = para.createRun();
        run.setBold(false);
        run.setItalic(false);
        run.setFontFamily("宋体");
        run.setFontSize(18);
        run.setColor("000000");
        run.setText("领导批示：");
    }
    
    /**
     * 增加自定义标题样式。这里用的是stackoverflow的源码
     * 
     * @param docxDocument 目标文档
     * @param strStyleId 样式名称
     * @param headingLevel 样式级别
     */
    private static void addCustomHeadingStyle(XWPFDocument docxDocument, String strStyleId, int headingLevel) {
 
        CTStyle ctStyle = CTStyle.Factory.newInstance();
        ctStyle.setStyleId(strStyleId);
 
        CTString styleName = CTString.Factory.newInstance();
        styleName.setVal(strStyleId);
        ctStyle.setName(styleName);
 
        CTDecimalNumber indentNumber = CTDecimalNumber.Factory.newInstance();
        indentNumber.setVal(BigInteger.valueOf(headingLevel));
 
        // lower number > style is more prominent in the formats bar
        ctStyle.setUiPriority(indentNumber);
 
        CTOnOff onoffnull = CTOnOff.Factory.newInstance();
        ctStyle.setUnhideWhenUsed(onoffnull);
 
        // style shows up in the formats bar
        ctStyle.setQFormat(onoffnull);
 
        // style defines a heading of the given level
        CTPPr ppr = CTPPr.Factory.newInstance();
        ppr.setOutlineLvl(indentNumber);
        ctStyle.setPPr(ppr);
 
        XWPFStyle style = new XWPFStyle(ctStyle);
 
        // is a null op if already defined
        XWPFStyles styles = docxDocument.createStyles();
 
        style.setType(STStyleType.PARAGRAPH);
        styles.addStyle(style);
 
    }
    

}
