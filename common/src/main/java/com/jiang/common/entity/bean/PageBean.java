package com.jiang.thinkindler.entity.bean;

import java.util.List;

/**
 * Created by jiang on 2017/3/24.
 */

public class PageBean<T> {


    /**
     * start : 0
     * count : 10
     * total : 30
     */

    private int start;
    private int count;
    private int total;
    private List<T> books;


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

    public List<T> getBooks() {
        return books;
    }

    public void setBooks(List<T> books) {
        this.books = books;
    }
}
