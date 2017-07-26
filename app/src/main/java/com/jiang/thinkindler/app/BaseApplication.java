package com.jiang.thinkindler.app;


import android.content.Context;

import com.jiang.common.base.CommonApplication;
import com.jiang.common.utils.LogUtils;
import com.jiang.thinkindler.injector.app.AppComponent;
import com.jiang.thinkindler.injector.app.AppModule;
import com.jiang.thinkindler.injector.app.DaggerAppComponent;
import com.squareup.leakcanary.LeakCanary;

public class BaseApplication extends CommonApplication {

    private AppComponent appComponent;

    public static BaseApplication get(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.logInit(true);
        LeakCanary.install(this);

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

}



