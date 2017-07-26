package com.jiang.douban.injector.app;


import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by quantan.liu on 2017/3/21.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Context getContext();  // 提供App的Context
}
