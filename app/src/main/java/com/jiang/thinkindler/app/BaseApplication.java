package com.jiang.thinkindler.app;


import android.content.Context;

import com.jiang.common.base.CommonApplication;
import com.jiang.common.utils.LogUtils;
import com.squareup.leakcanary.LeakCanary;

public class BaseApplication extends CommonApplication {


    public static BaseApplication get(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.logInit(true);
        LeakCanary.install(this);

    }

}



