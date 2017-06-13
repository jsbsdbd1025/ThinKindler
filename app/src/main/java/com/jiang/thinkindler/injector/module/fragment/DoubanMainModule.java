package com.jiang.thinkindler.injector.module.fragment;

import com.jiang.common.base.irecyclerview.IRecyclerView;
import com.jiang.thinkindler.ui.douban.contract.DoubanMainContract;
import com.jiang.thinkindler.utils.pagelist.PageListHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jiang on 2017/4/14.
 */
@Module
public class DoubanMainModule {

    private DoubanMainContract.View view;
    private IRecyclerView recyclerView;

    public DoubanMainModule(DoubanMainContract.View view, IRecyclerView recyclerView) {
        this.view = view;
        this.recyclerView = recyclerView;
    }

    @Provides
    public DoubanMainContract.View provideView() {
        return view;
    }

    @Provides
    public PageListHelper providePageListHelper() {
        return new PageListHelper(recyclerView);
    }


}
