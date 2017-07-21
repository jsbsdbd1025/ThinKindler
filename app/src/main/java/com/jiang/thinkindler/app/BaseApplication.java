package com.jiang.thinkindler.app;


import android.content.Context;

import com.jiang.common.base.CommonApplication;
import com.jiang.common.utils.LogUtils;
import com.jiang.thinkindler.injector.component.AppComponent;
import com.jiang.thinkindler.injector.component.DaggerAppComponent;
import com.jiang.thinkindler.injector.module.AppModule;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

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

        CrashReport.initCrashReport(getApplicationContext());

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

}



