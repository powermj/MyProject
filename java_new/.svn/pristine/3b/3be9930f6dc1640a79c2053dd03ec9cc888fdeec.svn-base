package com.zhxg.fgw;


import java.util.Map;


@SuppressWarnings({"unused", "WeakerAccess"})
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public Result() {}

    public Result(int code, String msg, T data) {
        this.code = code;

        if (msg != null) {
            this.msg = msg;
        }
        if (data != null) {
            this.data = data;
        }
    }

    public Map<String, Object> toMap() {
        return MapResult.of(code, msg, data);
    }

    private static <T> Result result(int code, String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        if (msg != null) {
            result.setMsg(msg);
        }
        if (data != null) {
            result.setData(data);
        }

        return result;
    }

    public static <T> Result ok(T data) {
        return result(0, null, data);
    }

    public static <T> Result ok() {
        return result(0, null, null);
    }

    public static <T> Result fail(String msg) {
        return result(1, msg, null);
    }

    public static <T> Result fail() {
        return result(1, "未知的错误", null);
    }

    public static <T> Result fail(int code) {
        return result(code, null, null);
    }

    public static <T> Result fail(int code, String msg) {
        return result(code, msg, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static String failStr(int code, String msg) {
        if (msg != null) {
            return "{\"code\": " + code + ", \"msg\": \"" + msg + "\"}";
        }

        return "{\"code\": " + code + "}";
    }

    public static String failStr(String msg) {
        return failStr(1, msg);
    }

}
