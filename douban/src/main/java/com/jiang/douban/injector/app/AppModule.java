package com.jiang.douban.injector.app;


import com.google.gson.Gson;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by quantan.liu on 2017/3/21.
 */
@Module
public class AppModule {

    private final Context context;

    public AppModule(Context application) {
        this.context = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return context;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

}
