package com.jiang.media.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by knowing on 2017/12/5.
 */

public class BiliBiliResponse<T> {


    /**
     * code : 0
     * data :
     */


    private int code;
    @SerializedName(value = "result", alternate = "data")
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
