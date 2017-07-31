package com.jiang.meizi.injector.component.fragment;

import com.jiang.common.injector.scope.FragmentScope;
import com.jiang.meizi.injector.module.fragment.MeiziMainModule;
import com.jiang.meizi.injector.module.http.GankHttpModule;
import com.jiang.meizi.ui.main.MeiziMainFragment;

import dagger.Component;

/**
 * Created by jiang on 2017/4/14.
 */
@FragmentScope
@Component(modules = {MeiziMainModule.class, GankHttpModule.class})
public interface MeiziComponent {
    void inject(MeiziMainFragment fragment);
}
