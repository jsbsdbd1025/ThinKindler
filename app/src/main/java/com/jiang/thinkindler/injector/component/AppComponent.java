package com.jiang.thinkindler.injector.component;


import com.jiang.thinkindler.app.BaseApplication;
import com.jiang.thinkindler.injector.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by quantan.liu on 2017/3/21.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    BaseApplication getContext();  // 提供App的Context
}
