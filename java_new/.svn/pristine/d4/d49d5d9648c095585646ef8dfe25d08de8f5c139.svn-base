package com.zhxg.yqzj.service.report;

import org.apache.commons.lang3.StringUtils;

public class ReportStyle {
    
    /**
     * 1：加粗 ，0 否
     */
    private int bold;
    
    /**
     * 字符间距
     */
    private int characterSpacing;
    
    /**
     * 颜色：FF0000
     */
    private String color;
    
    /**
     * 字体
     */
    private String fontFamily = "宋体";
    
    /**
     * 字体大小
     */
    private int fontSize =12;
    
    /**
     * 1：倾斜，0 否
     */
    private int italic;
    
    /**
     * 1 左对齐，2居中对齐 ，3 右对齐，4 分散对齐
     */
    private int alignment = 1;
    
    /**
     * 垂直对齐方式
     */
    private int verticalAlignment;
    
    /**
     * 1 自动换行,0 否
     */
    private int wordWrapped;
    
    /**
     * 编辑人颜色
     */
    private String authorColor;
    
    /**
     * 横线颜色 red--红色,black--黑色
     */
    private String hastransverseColor = "red";
    
    private String dataFormat = "yyyy-MM-dd HH:mm:ss";

    public int getBold() {
        return bold;
    }

    public void setBold(int bold) {
        this.bold = bold;
    }

    public int getCharacterSpacing() {
        return characterSpacing;
    }

    public void setCharacterSpacing(int characterSpacing) {
        this.characterSpacing = characterSpacing;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getItalic() {
        return italic;
    }

    public void setItalic(int italic) {
        this.italic = italic;
    }

    public int getAlignment() {
        return alignment;
    }

    public void setAlignment(int alignment) {
        this.alignment = alignment;
    }

    public int getVerticalAlignment() {
        return verticalAlignment;
    }

    public void setVerticalAlignment(int verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public int getWordWrapped() {
        return wordWrapped;
    }

    public void setWordWrapped(int wordWrapped) {
        this.wordWrapped = wordWrapped;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        if(StringUtils.isNotBlank(dataFormat)){
            this.dataFormat = dataFormat.replace("#nbsp#", " ");
        }
    }

    
    public String getAuthorColor() {
        return authorColor;
    }

    
    public void setAuthorColor(String authorColor) {
        this.authorColor = authorColor;
    }

    
    public String getHastransverseColor() {
        return hastransverseColor;
    }

    
    public void setHastransverseColor(String hastransverseColor) {
        this.hastransverseColor = hastransverseColor;
    }
    
    
}
