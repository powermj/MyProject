package com.zhxg.yqzj.entities.v1;

public class RegionReportInfo extends AllReportData {
    
    private String regionId;

    private String kvUuid;
    
    
    public String getRegionId() {
        return regionId;
    }



    
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }



    public String getKvUuid() {
        return kvUuid;
    }


    
    public void setKvUuid(String kvUuid) {
        this.kvUuid = kvUuid;
    }
    
}
