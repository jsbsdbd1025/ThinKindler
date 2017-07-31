package com.jiang.douban.ui.detail;

import com.jiang.common.entity.bean.BookBean;
import com.jiang.common.rx.RxSchedulers;
import com.jiang.common.utils.ToastUtil;
import com.jiang.douban.base.DoubanObserver;
import com.jiang.douban.net.model.DoubanModel;

import javax.inject.Inject;

/**
 * Created by jiang on 2017/4/14.
 */

public class BookDetailPresenter implements BookDetailContract.Presenter {

    protected BookDetailContract.View mView;
    protected DoubanModel doubanModel;

    @Inject
    public BookDetailPresenter(BookDetailContract.View view, DoubanModel doubanModel) {
        this.mView = view;
        this.doubanModel = doubanModel;
        start();

    }

    @Override
    public void start() {
        getDetail(mView.getId());
    }

    @Override
    public void getDetail(String id) {

        doubanModel.getDetail(id)
                .compose(RxSchedulers.compose())
                .subscribe(new DoubanObserver<BookBean>(mView) {
                    @Override
                    protected void _onNext(BookBean body) {
                        mView.setupView(body);
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtil.showShort(message);
                    }
                });
    }
}
