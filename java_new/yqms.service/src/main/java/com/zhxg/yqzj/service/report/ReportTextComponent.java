package com.zhxg.yqzj.service.report;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.zhxg.framework.base.utils.HttpClientUtils;
import com.zhxg.yqzj.entities.v1.ReportVariable;

public class ReportTextComponent extends ReportComponent {

    private static final  String REPORT_LINE_RED = "http://192.168.16.190:81/hengxian/585DEE81-A425-430a-9D16-33094BE87A37.png";
    private static final  String REPORT_LINE_BLACK = "http://192.168.16.190:81/hengxian/585DEE81-A425-430a-9D16-33094BE87A38.png";
    
    private String text = "";
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text.replace("#nbsp#", " ");
    }

    
    /**
     * 画内容
     * @param doc
     */
    public void draw(XWPFDocument doc,ReportVariable reportVariable) {
        XWPFParagraph para = null;
        if(this.getDirectoryLevel()>0){
            para = doc.createParagraph();
            para.setStyle("标题"+this.getDirectoryLevel());
        }else{
            para = getParagraph(doc);
        }
//        if(index>0){
//            XWPFRun r = para.createRun();
//            r.setBold(true);
//            r.setText(index+"、"+name);
//            r.addBreak();
//        }
        para.setAlignment(ParagraphAlignment.valueOf(style.getAlignment()));
        XWPFRun run = para.createRun();
        run.setBold(style.getBold() == 1 ? true : false);
        run.setItalic(style.getItalic() == 1 ? true : false);
        run.setFontFamily(style.getFontFamily());
        run.setFontSize(style.getFontSize());
        run.setColor(style.getColor().replace("#", ""));
        //计算报告期数
        text = this.countReportNum(text,reportVariable);
        String[] textArr = null;
        if(text.contains("#{wpcr}#")){
            textArr = text.split("#\\{wpcr\\}#");
            for (String textSplit : textArr) {
                run.setText(textSplit.trim());
                run.addBreak();
            }
        }else{
            run.setText(text);
            run.addBreak(); 
        }
        // Borders border = Borders.DOUBLE;
        // para.setBorderRight(border );
        // para.setIndentFromRight(2000);
    }


    public String countReportNum(String reportText,ReportVariable reportVariable){
        if(reportText.contains("${initValue}")){
            String value = reportVariable.getValue();
            reportText = reportText.replace("${initValue}", value);
        }
        if(reportText.contains("${initTotal}")){
            String total = reportVariable.getTotal()+"";
            reportText = reportText.replace("${initTotal}", total);
        }
        return reportText;
    }
    
    /**
     * 画横线
     */
    public void drawHasTransverse (XWPFDocument doc){
        //服务器上获取横线图片
        String filePath = REPORT_LINE_RED;
        if("black".equals(style.getHastransverseColor())){
            filePath = REPORT_LINE_BLACK;
        }
        InputStream inputStream = HttpClientUtils.getInputStream(filePath);
        try {
            XWPFParagraph p = doc.createParagraph();
            XWPFRun run = p.createRun();
            run.addPicture(inputStream, 6, "", Units.toEMU(450), Units.toEMU(2));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }finally {
 
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    public void drawAuthor(XWPFDocument doc){
        XWPFParagraph para = doc.createParagraph();
        XWPFRun run1 = para.createRun();
        run1.setText(text);
        run1.setBold(style.getBold() == 1 ? true : false);
        run1.setItalic(style.getItalic() == 1 ? true : false);
        run1.setFontFamily(style.getFontFamily());
        run1.setFontSize(style.getFontSize());
        run1.setColor(style.getColor().replace("#", ""));
        
        XWPFRun run2 = para.createRun();
        SimpleDateFormat sdf = new SimpleDateFormat(timestype.getDataFormat());
        String time = sdf.format(new Date());
        run2.setBold(timestype.getBold() == 1 ? true : false);
        run2.setItalic(timestype.getItalic() == 1 ? true : false);
        run2.setFontFamily(timestype.getFontFamily());
        run2.setFontSize(timestype.getFontSize());
        run2.setColor(timestype.getColor().replace("#", ""));
        
        if(time.contains("AM")||time.contains("PM")){
            GregorianCalendar ca = new GregorianCalendar();
            if(ca.get(GregorianCalendar.AM_PM) == 0){
                time = time.replace("AM", "上午");
            }else{
                time = time.replace("PM", "下午");
            }
        }
        
        
        int tabNum = ((65-time.getBytes().length) - text.getBytes().length) < 8 ? 1 : ((65-time.getBytes().length) - text.getBytes().length)/8;
        for (int i = 0; i < tabNum; i++) {
            run1.addTab();
        }
        
        
        run2.setText(time);
        
        
    }

}
