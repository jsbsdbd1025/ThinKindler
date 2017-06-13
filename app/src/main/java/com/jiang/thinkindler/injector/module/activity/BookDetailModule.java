package com.jiang.thinkindler.injector.module.activity;

import com.jiang.thinkindler.ui.douban.contract.BookDetailContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jiang on 2017/4/14.
 */
@Module
public class BookDetailModule {

    private BookDetailContract.View view;

    public BookDetailModule(BookDetailContract.View view) {
        this.view = view;
    }

    @Provides
    public BookDetailContract.View provideView() {
        return view;
    }

}
