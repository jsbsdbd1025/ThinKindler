package com.jiang.thinkindler.injector.module.fragment;

import com.jiang.thinkindler.entity.bean.BookBean;
import com.jiang.thinkindler.ui.douban.DoubanMainFragment;
import com.jiang.thinkindler.ui.douban.adapter.BookAdapter;
import com.jiang.thinkindler.ui.douban.presenter.DoubanMainPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jiang on 2017/4/14.
 */
@Module
public class DoubanMainModule {

    private final DoubanMainFragment fragment;

    private final List<BookBean> datas;

    public DoubanMainModule(DoubanMainFragment fragment, List<BookBean> datas) {
        this.fragment = fragment;
        this.datas = datas;
    }

    @Provides
    @Singleton
    public BookAdapter provideAdapter() {
        return new BookAdapter(datas);
    }

    @Provides
    @Singleton
    public DoubanMainPresenter providePresenter() {
        return new DoubanMainPresenter(fragment);
    }
}
