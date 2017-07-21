package com.jiang.thinkindler.net.service;

import com.jiang.thinkindler.entity.bean.BookBean;
import com.jiang.thinkindler.entity.bean.PageList;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by jiang on 2017/5/20.
 */

public interface DoubanService {

    @FormUrlEncoded
    @POST("v2/book/search")
    Observable<PageList<BookBean>> search(@Field("q") String q
            , @Field("start") int start
            , @Field("fields") String fileds);

    @GET("v2/book/{id}")
    Observable<BookBean> detail(@Path("id") String id);

}
