package com.jiang.common.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.support.multidex.MultiDex;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by jiang on 2017/3/9.
 */

public class CommonApplication extends Application {


    protected static final String TAG = "BaseApplication";

    private static CommonApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();

        CrashReport.initCrashReport(getApplicationContext());
        instance = this;
    }


    public static Context getAppContext() {
        return instance;
    }

    public static Resources getAppResources() {
        return instance.getResources();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    /**
     * 分包
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
