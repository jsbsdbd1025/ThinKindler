package com.jiang.thinkindler.ui.live;

import com.jiang.thinkindler.net.model.DoubanModel;

import javax.inject.Inject;

/**
 * Created by jiang on 2017/5/23.
 */

public class LiveLivePresenter implements LiveListContract.Presenter {

    protected LiveListContract.View mView;
    protected DoubanModel doubanModel;

    @Inject
    public LiveLivePresenter(LiveListContract.View view, DoubanModel doubanModel) {
        this.mView = view;
        this.doubanModel = doubanModel;
    }

    @Override
    public void loadData() {

    }

    @Override
    public void start() {

    }
}
