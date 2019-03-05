package com.zhxg.yqzj.service.report;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.zhxg.framework.base.utils.HttpClientUtils;

public class ReportSignatureComponent extends ReportComponent {

    private static final  String REPORT_LINE_RED = "http://192.168.16.190:81/hengxian/585DEE81-A425-430a-9D16-33094BE87A37.png";
    private static final  String REPORT_LINE_BLACK = "http://192.168.16.190:81/hengxian/585DEE81-A425-430a-9D16-33094BE87A38.png";
    
    /**
     * 报送
     */
    private String submitted;
    /**
     * 抄送
     */
    private String copy;
    /**
     * 是否划线
     */
    private boolean drawLine;
    /**
     * 审核
     */
    private String auditing;
    
    /**
     * 抄送样式
     */
    private ReportStyle copy_style;
    /**
     * 报送样式
     */
    private ReportStyle submitted_style;
    /**
     * 审核样式
     */
    private ReportStyle auditing_style;
    
    
    
    
    
    public String getSubmitted() {
        return submitted;
    }


    
    public void setSubmitted(String submitted) {
        this.submitted = submitted.replace("#nbsp#", " ");
    }


    
    public String getCopy() {
        return copy;
    }


    
    public void setCopy(String copy) {
        this.copy = copy.replace("#nbsp#", " ");
    }


    
    public String getAuditing() {
        return auditing;
    }


    
    public void setAuditing(String auditing) {
        this.auditing = auditing.replace("#nbsp#", " ");
    }
    
    

    
    public boolean isDrawLine() {
        return drawLine;
    }



    
    public void setDrawLine(boolean drawLine) {
        this.drawLine = drawLine;
    }

    

    
    public ReportStyle getCopy_style() {
        return copy_style;
    }



    
    public void setCopy_style(ReportStyle copy_style) {
        this.copy_style = copy_style;
    }



    
    public ReportStyle getSubmitted_style() {
        return submitted_style;
    }



    
    public void setSubmitted_style(ReportStyle submitted_style) {
        this.submitted_style = submitted_style;
    }



    
    public ReportStyle getAuditing_style() {
        return auditing_style;
    }



    
    public void setAuditing_style(ReportStyle auditing_style) {
        this.auditing_style = auditing_style;
    }



    /**
     * 画署名
     * @param doc
     */
    public void draw(XWPFDocument doc) {
        XWPFParagraph para = null;
        if(this.getDirectoryLevel()>0){
            para = doc.createParagraph();
            para.setStyle("标题"+this.getDirectoryLevel());
        }else{
            para = getParagraph(doc);
        }
        if(StringUtils.isNotEmpty(submitted)){
            para.setAlignment(ParagraphAlignment.valueOf(submitted_style.getAlignment()));
            XWPFRun submittedRun = para.createRun();
            this.setContentStyle(submittedRun, submitted,submitted_style);
        }
        
        if(StringUtils.isNotEmpty(copy)){
            XWPFParagraph cpara = doc.createParagraph();
            cpara.setAlignment(ParagraphAlignment.valueOf(copy_style.getAlignment()));
            XWPFRun copyRun = cpara.createRun();
            this.setContentStyle(copyRun,copy,copy_style);
        }
        
        if(drawLine){
            XWPFRun lineRun = doc.createParagraph().createRun();
            this.drawHasTransverse(lineRun);
        }
        if(StringUtils.isNotEmpty(auditing)){
            XWPFParagraph apara = doc.createParagraph();
            apara.setAlignment(ParagraphAlignment.valueOf(auditing_style.getAlignment()));
            XWPFRun auditingRun = apara.createRun();
            this.setContentStyle(auditingRun, auditing,auditing_style);
        }
       
    }

    /**
     * 画横线
     */
    public void drawHasTransverse (XWPFRun run){
        //服务器上获取横线图片
        String filePath = REPORT_LINE_RED;
//        if("black".equals(submitted_style.getHastransverseColor())){
//            filePath = REPORT_LINE_BLACK;
//        }
        InputStream inputStream = HttpClientUtils.getInputStream(filePath);
        try {
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
    
    
    private void setContentStyle(XWPFRun run,String content,ReportStyle style){
        run.setBold(style.getBold() == 1 ? true : false);
        run.setItalic(style.getItalic() == 1 ? true : false);
        run.setFontFamily(style.getFontFamily());
        run.setFontSize(style.getFontSize());
        run.setColor(style.getColor().replace("#", ""));
        this.setText(run, content);
    }
    
    private void setText(XWPFRun run,String content){
        if(content.contains("#{wpcr}#")){
            String[] textArr = content.split("#\\{wpcr\\}#");
            for (int i = 0; i < textArr.length; i++) {
                    run.setText(textArr[i]); 
                    run.addBreak();
            }
        }else{
            run.setText(content); 
        }
    }

}
