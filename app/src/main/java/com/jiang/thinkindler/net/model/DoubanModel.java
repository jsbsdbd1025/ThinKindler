package com.jiang.thinkindler.net.model;

import com.jiang.thinkindler.entity.bean.BookBean;
import com.jiang.thinkindler.entity.bean.PageList;
import com.jiang.thinkindler.net.service.DoubanService;

import io.reactivex.Observable;


/**
 * Created by jiang on 2017/5/20.
 */

public class DoubanModel extends BaseModel {

    private static final String BOOK_LIST = "id,title,image";

    private DoubanService mDoubanService;

    public DoubanModel(DoubanService mDoubanService) {
        this.mDoubanService = mDoubanService;
    }

    public Observable<PageList<BookBean>> searchBooks(String q, int start) {
        return mDoubanService.search(q, start, BOOK_LIST);
    }
}
