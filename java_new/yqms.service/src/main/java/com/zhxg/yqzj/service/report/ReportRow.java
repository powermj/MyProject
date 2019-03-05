package com.zhxg.yqzj.service.report;

import java.util.List;

public class ReportRow {

    private List<ReportCell> cellList;
    
    private ReportStyle style;

    public List<ReportCell> getCellList() {
        return cellList;
    }

    public void setCellList(List<ReportCell> cellList) {
        this.cellList = cellList;
    }

    public ReportStyle getStyle() {
        return style;
    }

    public void setStyle(ReportStyle style) {
        this.style = style;
    }
    
    
}
