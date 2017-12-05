package com.jiang.media.net;

import com.jiang.media.entity.BiliBiliBannerBean;
import com.jiang.media.entity.BiliBiliResponse;
import com.jiang.media.entity.RankInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by hcc on 2016/9/22 18:40
 * 100332338@qq.com
 * <p>
 * 排行榜相关api
 */

public interface BiliBiliService {

    /**
     * 全区排行榜数据请求
     */
    @GET("index/rank/{type}")
    Observable<RankInfo> getAllareasRanks(@Path("type") String type);


    /**
     * 首页推荐banner
     */
    @GET("x/banner?plat=4&build=411007&channel=bilih5")
    Observable<BiliBiliResponse<List<BiliBiliBannerBean>>> getRecommendedBannerInfo();
}
