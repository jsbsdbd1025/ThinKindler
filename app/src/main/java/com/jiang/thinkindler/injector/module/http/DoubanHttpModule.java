package com.jiang.thinkindler.injector.module.http;

import com.jiang.thinkindler.injector.qualifier.DoubanUrl;
import com.jiang.thinkindler.net.ApiType;
import com.jiang.thinkindler.net.model.DoubanModel;
import com.jiang.thinkindler.net.service.DoubanService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by quantan.liu on 2017/4/8.
 */
@Module
public class DoubanHttpModule extends BaseHttpModule {

    @Singleton
    @Provides
    @DoubanUrl
    Retrofit provideDoubanRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, ApiType.DOUBAN.getUrl());
    }

    @Singleton
    @Provides
    DoubanService provideDoubanService(@DoubanUrl Retrofit retrofit) {
        return retrofit.create(DoubanService.class);
    }

    @Provides
    @Singleton
    DoubanModel provideDoubanModel(DoubanService doubanService) {
        return new DoubanModel(doubanService);
    }
}
