package com.jiang.meizi.injector.module.fragment;


import com.jiang.meizi.ui.main.MeiziMainContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jiang on 2017/4/14.
 */
@Module
public class MeiziMainModule {

    private MeiziMainContract.View mView;

    public MeiziMainModule(MeiziMainContract.View view) {
        this.mView = view;
    }

    @Provides
    public MeiziMainContract.View provideView() {
        return mView;
    }


}
