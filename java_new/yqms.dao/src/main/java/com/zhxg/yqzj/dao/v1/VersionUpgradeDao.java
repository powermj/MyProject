package com.zhxg.yqzj.dao.v1;

import java.util.Map;

public interface VersionUpgradeDao{
    Map<String, Object>  updateAppVersion(Map<String, Object> params);

}
