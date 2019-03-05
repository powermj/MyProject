package com.zhxg.framework.base.http;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: Restful接口统一返回数据格式
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.http.ApiResponseBody.java
 * <p>
 * Author: azmiu
 * <p>
 * Create Date: 2017年3月17日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
@ApiModel(value = "返回结果")
public class ApiResponseBody {

    @ApiModelProperty(value = "返回状态", required = true)
    private String status;
    @ApiModelProperty(value = "返回状态描述", required = true)
    private String msg;
    private Result result;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result getResult() {
        return this.result;
    }

    public void setResult(Result result) {
        this.result = result;
    }


}
