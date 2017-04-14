package com.jiang.thinkindler.injector.component;

import android.app.Activity;

import com.jiang.thinkindler.injector.module.FragmentModule;
import com.jiang.thinkindler.injector.scope.FragmentScope;

import dagger.Component;

/**
 * Created by quantan.liu on 2017/3/21.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

}
