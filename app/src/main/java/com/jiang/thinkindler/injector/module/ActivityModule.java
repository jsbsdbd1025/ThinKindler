package com.jiang.thinkindler.injector.module;

import android.app.Activity;

import com.jiang.thinkindler.base.BasePresenter;
import com.jiang.thinkindler.injector.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by quantan.liu on 2017/3/21.
 */

@Module
public class ActivityModule {
    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }

}
