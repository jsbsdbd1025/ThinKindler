package com.jiang.douban.injector.component.fragment;

import com.jiang.common.injector.scope.FragmentScope;
import com.jiang.douban.injector.module.fragment.DoubanMainModule;
import com.jiang.douban.injector.module.http.DoubanHttpModule;
import com.jiang.douban.ui.main.DoubanMainFragment;

import dagger.Component;

/**
 * Created by jiang on 2017/4/14.
 */
@FragmentScope
@Component(modules = {DoubanMainModule.class, DoubanHttpModule.class})
public interface DoubanComponent {
    void inject(DoubanMainFragment fragment);
}
