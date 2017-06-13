package com.jiang.thinkindler.injector.component;

import android.app.Activity;

import com.jiang.thinkindler.injector.module.ActivityModule;
import com.jiang.thinkindler.injector.scope.ActivityScope;

import dagger.Component;

/**
 * Created by quantan.liu on 2017/3/21.
 */
@ActivityScope
@Component(modules = {ActivityModule.class}, dependencies = {AppComponent.class})
public interface ActivityComponent {
    Activity getActivity();
}
