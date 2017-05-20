package com.jiang.thinkindler.ui.douban.presenter;

import com.jiang.common.utils.LogUtils;
import com.jiang.thinkindler.data.db.HistoryUtil;
import com.jiang.thinkindler.entity.bean.BookList;
import com.jiang.thinkindler.rx.HttpSubscriber;
import com.jiang.thinkindler.net.model.DoubanModel;
import com.jiang.thinkindler.ui.douban.contract.DoubanMainContract;

import javax.inject.Inject;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jiang on 2017/4/14.
 */

public class DoubanMainPresenter extends DoubanMainContract.presenter {

    DoubanModel mModel;




    @Inject
    public DoubanMainPresenter(DoubanModel mModel) {
        this.mModel = mModel;
        doSearch("Android");
    }

    @Override
    public void doSearch(String content) {
        if (content.equals(""))
            return;

        HistoryUtil.saveHistory(content);
        mModel.searchBooks(content, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<BookList>() {
                    @Override
                    public void _onNext(BookList bookList) {
                        LogUtils.loge("_onNext");
                    }

                    @Override
                    public void _onError(String message) {
                        LogUtils.loge("_onError");
                    }
                });

    }
}
