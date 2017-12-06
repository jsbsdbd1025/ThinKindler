package com.jiang.douban.net.model;

import com.jiang.common.entity.bean.BookBean;
import com.jiang.common.entity.bean.PageList;
import com.jiang.common.net.BaseModel;
import com.jiang.douban.net.service.DoubanService;

import io.reactivex.Observable;


/**
 * Created by jiang on 2017/5/20.
 */

public class DoubanModel extends BaseModel {

    private static final String BOOK_LIST = "id,title,image,images";

    private DoubanService mDoubanService;

    public DoubanModel(DoubanService mDoubanService) {
        this.mDoubanService = mDoubanService;
    }

    public Observable<PageList<BookBean>> searchBooks(String q, int start) {
        return mDoubanService.search(q, start, BOOK_LIST);
    }

    public Observable<BookBean> getDetail(String id) {
        return mDoubanService.detail(id);
    }

    public boolean check(int a, int b) {
        return a == b;
    }
}
