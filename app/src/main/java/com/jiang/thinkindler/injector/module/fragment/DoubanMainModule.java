package com.jiang.thinkindler.injector.module.fragment;

import com.jiang.thinkindler.ui.douban.adapter.BookAdapter;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jiang on 2017/4/14.
 */
@Module
public class DoubanMainModule {
    @Provides
    @Singleton
    public BookAdapter provideAdapter() {
        return new BookAdapter(new ArrayList());
    }

}
