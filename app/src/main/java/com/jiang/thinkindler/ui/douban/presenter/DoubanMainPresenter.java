package com.jiang.thinkindler.ui.douban.presenter;

import com.jiang.thinkindler.data.db.HistoryUtil;
import com.jiang.thinkindler.entity.bean.BookBean;
import com.jiang.thinkindler.entity.bean.PageList;
import com.jiang.thinkindler.net.model.DoubanModel;
import com.jiang.thinkindler.rx.BaseObserver;
import com.jiang.thinkindler.rx.RxSchedulers;
import com.jiang.thinkindler.ui.douban.contract.DoubanMainContract;
import com.jiang.thinkindler.utils.pagelist.PageListListener;

import javax.inject.Inject;

import static com.jiang.common.widget.multistatuslayout.MultiStatusLayout.STATUS_ERROR;
import static com.jiang.common.widget.multistatuslayout.MultiStatusLayout.STATUS_LOADING;
import static com.jiang.common.widget.multistatuslayout.MultiStatusLayout.STATUS_NORMAL;

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
    public void doSearch(String content, int start) {
        if (content.equals("")) {
            return;
        }

        mView.setStatus(STATUS_LOADING);
        HistoryUtil.saveHistory(content);

        doubanModel.searchBooks(content, start)
                .compose(RxSchedulers.compose())
                .subscribe(new BaseObserver<PageList<BookBean>>(mView) {
                    @Override
                    protected void _onNext(PageList<BookBean> pageList) {
                        mView.setStatus(STATUS_NORMAL);
                        if (pageList.getDatas().size() == 0) {
                            mView.setStatus(STATUS_ERROR);
                        }
                        mView.returnDatas(true, pageList.getDatas());
                    }

                    @Override
                    protected void _onError(String message) {
                    }
                });
    }

    @Override
    public void start() {
    }

    public boolean check(int a, int b) {
        return doubanModel.check(a, b);
    }
}
