package com.jiang.thinkindler.app;


import com.jiang.common.base.CommonApplication;
import com.jiang.common.utils.LogUtils;
import com.squareup.leakcanary.LeakCanary;

public class BaseApplication extends CommonApplication {

    private static BaseApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.logInit(true);
        LeakCanary.install(this);
    }
}



