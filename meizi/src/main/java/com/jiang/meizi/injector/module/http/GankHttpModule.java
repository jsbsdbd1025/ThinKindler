package com.jiang.meizi.injector.module.http;

import com.jiang.common.net.Api;
import com.jiang.common.net.ApiType;
import com.jiang.meizi.net.model.GankModel;
import com.jiang.meizi.net.service.GankService;

import dagger.Module;
import dagger.Provides;

;

/**
 * Created by quantan.liu on 2017/4/8.
 */
@Module
public class GankHttpModule {

    @Provides
    GankService provideDoubanService() {
        return Api.getApi(ApiType.GANK, GankService.class);
    }


    @Provides
    GankModel provideDoubanModel(GankService doubanService) {
        return new GankModel(doubanService);
    }
}
