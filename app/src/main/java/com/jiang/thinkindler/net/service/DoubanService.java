package com.jiang.thinkindler.net.service;

import com.jiang.thinkindler.entity.bean.BookList;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jiang on 2017/5/20.
 */

public interface DoubanService {
    @GET("v2/book/search")
    Flowable<BookList> search(@Query("q") String q, @Query("start") int start);
}
