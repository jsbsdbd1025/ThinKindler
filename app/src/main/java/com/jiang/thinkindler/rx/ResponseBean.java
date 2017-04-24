package com.jiang.thinkindler.rx;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by jiang on 2017/4/24.
 */

public class ResponseBean<T> {

    /**
     * 请求返回请求码
     */
    private int success;
    /**
     * 请求返回的数据
     */
    private T body;

    /**
     * 请求返回信息
     */
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success == 1;
    }

    @Override
    public String toString() {
        String response = "{\n" +
                "  \"success\": " + success + ",\n" +
                "  \"msg\": \"" + msg + "\",\n" +
                (body != null ? ("  \"body\": \n" + body.toString() + "  \n") : "") +
                "}";
        return response;
    }
}
