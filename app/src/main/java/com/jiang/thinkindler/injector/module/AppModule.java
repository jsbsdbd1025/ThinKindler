package com.jiang.thinkindler.injector.module;


import android.content.Context;

import com.jiang.common.utils.ToastUtil;
import com.jiang.thinkindler.app.BaseApplication;

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
    ToastUtil provideToastUtil(Context context) {
        return new ToastUtil(context);
    }

}
