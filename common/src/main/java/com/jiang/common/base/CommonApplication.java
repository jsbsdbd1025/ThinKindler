package com.jiang.common.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.support.multidex.MultiDex;

import java.util.Map;

/**
 * Created by jiang on 2017/3/9.
 */

public class CommonApplication extends Application {


    protected static final String TAG = "BaseApplication";
    // 用于存放倒计时时间
    public static Map<String, Long> map;
    private static CommonApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
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


    public static CommonApplication getInstance() {
        return instance;
    }

    /**
     * 分包
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
