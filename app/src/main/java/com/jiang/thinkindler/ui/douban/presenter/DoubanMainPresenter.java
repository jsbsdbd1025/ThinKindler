package com.jiang.thinkindler.ui.douban.presenter;

import com.jiang.thinkindler.data.db.HistoryUtil;
import com.jiang.thinkindler.entity.bean.BookList;
import com.jiang.thinkindler.net.model.DoubanModel;
import com.jiang.thinkindler.rx.BaseCommonObserver;
import com.jiang.thinkindler.rx.BaseObserver;
import com.jiang.thinkindler.rx.RxSchedulers;
import com.jiang.thinkindler.ui.douban.contract.DoubanMainContract;

import javax.inject.Inject;

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
        doSearch("Android");
    }

    @Override
    public void doSearch(String content) {
        if (content.equals(""))
            return;

        HistoryUtil.saveHistory(content);
        doubanModel.searchBooks(content, 0)
                .compose(RxSchedulers.compose())
                .subscribe(new BaseObserver<BookList>() {
                    @Override
                    protected void _onNext(BookList bookList) {
                        mView.returnDatas(bookList.getBooks());
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
