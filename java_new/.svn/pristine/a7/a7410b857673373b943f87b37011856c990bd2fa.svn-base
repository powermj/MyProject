package com.zhxg.framework.base.http;


/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 请求状态码
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.http.HttpStatusCode.java
 * <p>
 * Author: azmiu
 * <p>
 * Create Date: 2017年2月27日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public enum HttpStatusCode {
    // 200 OK (成功) - 对一次成功的GET, PUT, PATCH 或 DELETE的响应。也能够用于一次未产生创建活动的POST
    HTTP_STATUS_CODE_200("200", "请求处理成功！"),
    // 201 Created (已创建) - 对一次导致创建活动的POST的响应。 同时结合使用一个位置头信息指向新资源的位置
    // 400 Bad Request (错误的请求) - 请求是畸形的, 比如无法解析请求体
    HTTP_STATUS_CODE_400("400", "请求错误！"),
    // 401 Unauthorized (未授权) - 当没有提供或提供了无效认证细节时。如果从浏览器使用API，也可以用来触发弹出一次认证请求
    HTTP_STATUS_CODE_401("401", "用户未认证！"),
    // 403 Forbidden (禁止访问) - 当认证成功但是认证用户无权访问该资源时
    HTTP_STATUS_CODE_403("403", "用户认证成功，但无权访问该资源！"),
    // 404 Not Found (未找到) - 当一个不存在的资源被请求时
    HTTP_STATUS_CODE_404("404", "资源不存在！"),
    // 405 Method Not Allowed (方法被禁止) - 当一个对认证用户禁止的HTTP方法被请求时
    HTTP_STATUS_CODE_405("405", "方法被禁止！"),
    // 410 Gone (已删除) - 表示资源在终端不再可用。当访问老版本API时，作为一个通用响应很有用
    HTTP_STATUS_CODE_410("410", "API接口已删除！"),
    // 415 Unsupported Media Type (不支持的媒体类型) - 如果请求中包含了不正确的内容类型
    HTTP_STATUS_CODE_415("415", "不支持的媒体类型！"),
    // 422 Unprocessable Entity (无法处理的实体) - 出现验证错误时使用
    HTTP_STATUS_CODE_422("422", "无法处理的实体！"),
    // 500 Internal Server Error (内部服务器错误) - 当遇到意外情况时，给出了一个通用的错误信息，并没有更具体的消息是合适的。
    HTTP_STATUS_CODE_500("500", "系统异常！");

    private String code;
    private String message;

    private HttpStatusCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    

}
