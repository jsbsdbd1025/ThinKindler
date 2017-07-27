package com.jiang.douban.injector.module.fragment;


import com.jiang.douban.ui.main.DoubanMainContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jiang on 2017/4/14.
 */
@Module
public class DoubanMainModule {

    private DoubanMainContract.View view;

    public DoubanMainModule(DoubanMainContract.View view) {
        this.view = view;
    }

    @Provides
    public DoubanMainContract.View provideView() {
        return view;
    }


}
