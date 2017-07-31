package com.jiang.meizi.net.service;

import com.jiang.meizi.entity.bean.ResponseBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jiang on 2017/7/31.
 */

public interface GankService {

    @GET("/api/data/福利/{pageSize}/{pageNum}")
    Observable<ResponseBean> getMeizi(@Path("pageSize") int pageSize, @Path("pageNum") int pageNum);
}
