package com.jiang.thinkindler.ui.live;

import com.jiang.thinkindler.injector.scope.FragmentScope;
import com.jiang.thinkindler.ui.douban.DoubanMainFragment;
import com.jiang.thinkindler.ui.douban.adapter.BookAdapter;
import com.jiang.thinkindler.ui.douban.contract.DoubanMainContract;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jiang on 2017/4/14.
 */
@FragmentScope
@Module
public class LiveListModule {

    private DoubanMainContract.View view;

    public LiveListModule(DoubanMainFragment view) {
        this.view = view;
    }

    @Provides
    public DoubanMainContract.View provideView() {
        return view;
    }

    @Provides
    @Singleton
    public BookAdapter provideAdapter() {
        return new BookAdapter(new ArrayList());
    }

}
