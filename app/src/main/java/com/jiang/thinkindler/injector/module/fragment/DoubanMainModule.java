package com.jiang.thinkindler.injector.module.fragment;

import com.jiang.common.base.irecyclerview.IRecyclerView;
import com.jiang.thinkindler.ui.douban.contract.DoubanMainContract;
import com.jiang.thinkindler.utils.pagelist.PageListHelper;

import javax.inject.Singleton;

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
