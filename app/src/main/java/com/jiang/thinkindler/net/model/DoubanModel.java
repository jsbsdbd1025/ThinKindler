package com.jiang.thinkindler.net.model;

import com.jiang.thinkindler.entity.bean.BookList;
import com.jiang.thinkindler.net.service.DoubanService;

import io.reactivex.Flowable;


/**
 * Created by jiang on 2017/5/20.
 */

public class DoubanModel extends BaseModel {
    private DoubanService mDoubanService;

    public DoubanModel(DoubanService mDoubanService) {
        this.mDoubanService = mDoubanService;
    }

    public Flowable<BookList> searchBooks(String q, int start) {
        return mDoubanService.search(q, start);
    }
}
