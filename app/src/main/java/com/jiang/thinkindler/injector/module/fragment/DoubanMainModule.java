package com.jiang.thinkindler.injector.module.fragment;

import com.jiang.thinkindler.ui.douban.DoubanMainFragment;
import com.jiang.thinkindler.ui.douban.contract.DoubanMainContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jiang on 2017/4/14.
 */
@Module
public class DoubanMainModule {

    private DoubanMainContract.View view;

    public DoubanMainModule(DoubanMainFragment view) {
        this.view = view;
    }

    @Provides
    public DoubanMainContract.View provideView() {
        return view;
    }

}
