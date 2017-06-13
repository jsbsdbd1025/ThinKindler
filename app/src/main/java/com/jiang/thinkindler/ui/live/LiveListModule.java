package com.jiang.thinkindler.ui.live;

import com.jiang.thinkindler.injector.scope.FragmentScope;
import com.jiang.thinkindler.ui.douban.DoubanMainFragment;
import com.jiang.thinkindler.ui.douban.contract.DoubanMainContract;

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


}
