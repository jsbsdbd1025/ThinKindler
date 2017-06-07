package com.jiang.thinkindler.ui.douban.presenter;

import com.jiang.thinkindler.data.db.HistoryUtil;
import com.jiang.thinkindler.entity.bean.BookBean;
import com.jiang.thinkindler.entity.bean.PageList;
import com.jiang.thinkindler.net.model.DoubanModel;
import com.jiang.thinkindler.rx.BaseObserver;
import com.jiang.thinkindler.rx.MyDefaultObserver;
import com.jiang.thinkindler.rx.RxSchedulers;
import com.jiang.thinkindler.ui.douban.contract.DoubanMainContract;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import javax.inject.Inject;

import io.reactivex.observers.DefaultObserver;

/**
 * Created by jiang on 2017/4/14.
 */

public class DoubanMainPresenter implements DoubanMainContract.Presenter {

    protected DoubanMainContract.View mView;
    protected DoubanModel doubanModel;

    @Inject
    public DoubanMainPresenter(DoubanMainContract.View view, DoubanModel doubanModel) {
        this.mView = view;
        this.doubanModel = doubanModel;
    }

    @Override
    public void doSearch(String content) {
        if (content.equals(""))
            return;

        HistoryUtil.saveHistory(content);
        doubanModel.searchBooks(content, 0)
                .compose(RxSchedulers.compose())
                .subscribe(new BaseObserver<PageList<BookBean>>(mView) {
                    @Override
                    protected void _onNext(PageList<BookBean> pageList) {
                        mView.returnDatas(pageList.getDatas());
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }

    @Override
    public void start() {
        doSearch("Android");
    }
}
