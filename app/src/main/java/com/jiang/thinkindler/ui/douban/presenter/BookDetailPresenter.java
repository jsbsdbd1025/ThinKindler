package com.jiang.thinkindler.ui.douban.presenter;

import com.jiang.common.utils.ToastUtil;
import com.jiang.thinkindler.entity.bean.BookBean;
import com.jiang.thinkindler.net.model.DoubanModel;
import com.jiang.thinkindler.rx.BaseObserver;
import com.jiang.thinkindler.rx.RxSchedulers;
import com.jiang.thinkindler.ui.douban.contract.BookDetailContract;

import javax.inject.Inject;

import static com.jiang.common.widget.multistatuslayout.MultiStatusLayout.STATUS_LOADING;
import static com.jiang.common.widget.multistatuslayout.MultiStatusLayout.STATUS_NORMAL;
import static com.jiang.common.widget.multistatuslayout.MultiStatusLayout.STATUS_RETRY;

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

        mView.setStatus(STATUS_LOADING);
        doubanModel.getDetail(id)
                .compose(RxSchedulers.compose())
                .subscribe(new BaseObserver<BookBean>(mView) {
                    @Override
                    protected void _onNext(BookBean body) {
                        mView.setStatus(STATUS_NORMAL);
                        mView.setupView(body);
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtil.showShort(message);
                        mView.setStatus(STATUS_RETRY);
                    }
                });
    }
}
