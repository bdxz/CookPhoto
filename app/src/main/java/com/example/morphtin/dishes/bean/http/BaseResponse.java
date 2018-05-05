package com.example.morphtin.dishes.bean.http;

/**
 * Created by elevation on 18-5-4.
 */

public class BaseResponse {
    private int code;
    private String msg;

    public BaseResponse() {
    }

    public BaseResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
