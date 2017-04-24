package com.jiang.thinkindler.net;

import com.jiang.thinkindler.entity.bean.BookList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jiang on 2017/3/24.
 */

public interface ApiService {

    @GET("v2/book/search")
    Observable<BookList> search(@Query("q") String q, @Query("start") int start);
}
