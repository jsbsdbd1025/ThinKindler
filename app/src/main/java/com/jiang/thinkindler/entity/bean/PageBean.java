package com.jiang.thinkindler.entity.bean;

/**
 * Created by jiang on 2017/3/24.
 */

public class PageBean {


    /**
     * start : 0
     * count : 10
     * total : 30
     */

    private int start;
    private int count;
    private int total;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
