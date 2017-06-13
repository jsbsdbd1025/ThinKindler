package com.jiang.thinkindler.ui.douban.presenter;

import com.jiang.common.utils.ToastUtil;
import com.jiang.thinkindler.entity.bean.BookBean;
import com.jiang.thinkindler.net.model.DoubanModel;
import com.jiang.thinkindler.rx.BaseObserver;
import com.jiang.thinkindler.rx.RxSchedulers;
import com.jiang.thinkindler.ui.douban.contract.BookDetailContract;

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
                .subscribe(new BaseObserver<BookBean>(mView) {
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
