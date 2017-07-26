package com.jiang.douban.injector.module.http;

import com.jiang.thinkindler.net.Api;
import com.jiang.thinkindler.net.ApiType;
import com.jiang.thinkindler.net.model.DoubanModel;
import com.jiang.thinkindler.net.service.DoubanService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by quantan.liu on 2017/4/8.
 */
@Module
public class DoubanHttpModule {

    @Provides
    DoubanService provideDoubanService() {
        return Api.getApi(ApiType.DOUBAN, DoubanService.class);
    }


    @Provides
    DoubanModel provideDoubanModel(DoubanService doubanService) {
        return new DoubanModel(doubanService);
    }
}
