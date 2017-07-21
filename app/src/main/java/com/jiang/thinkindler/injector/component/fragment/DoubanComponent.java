package com.jiang.thinkindler.injector.component.fragment;

import com.jiang.thinkindler.injector.component.AppComponent;
import com.jiang.thinkindler.injector.module.fragment.DoubanMainModule;
import com.jiang.thinkindler.injector.module.http.DoubanHttpModule;
import com.jiang.thinkindler.injector.scope.FragmentScope;
import com.jiang.thinkindler.ui.douban.DoubanMainFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jiang on 2017/4/14.
 */
@FragmentScope
@Component(modules = {DoubanMainModule.class, DoubanHttpModule.class}, dependencies = AppComponent.class)
public interface DoubanComponent {
    void inject(DoubanMainFragment fragment);
}
