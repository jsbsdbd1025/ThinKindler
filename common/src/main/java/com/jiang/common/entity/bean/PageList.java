package com.jiang.thinkindler.entity.bean;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PageList<T> implements Serializable {

    private int count;
    private int start;
    private int total;
    @SerializedName(value = "datas", alternate = {"books"})
    private List<T> datas;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public int nextPage() {
        return start + count;
    }

    public boolean hasmore() {
        return start < total;
    }
}
