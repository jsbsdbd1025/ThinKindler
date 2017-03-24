package com.jiang.thinkindler.app;


import com.jiang.common.base.CommonApplication;
import com.squareup.leakcanary.LeakCanary;

public class BaseApplication extends CommonApplication {

    private static BaseApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}



