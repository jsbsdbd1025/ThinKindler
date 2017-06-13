package com.jiang.thinkindler.ui.douban.presenter;

import com.jiang.thinkindler.data.db.HistoryUtil;
import com.jiang.thinkindler.entity.bean.BookBean;
import com.jiang.thinkindler.entity.bean.PageList;
import com.jiang.thinkindler.net.model.DoubanModel;
import com.jiang.thinkindler.rx.BaseObserver;
import com.jiang.thinkindler.rx.RxSchedulers;
import com.jiang.thinkindler.ui.douban.contract.DoubanMainContract;
import com.jiang.thinkindler.utils.pagelist.PageListHelper;
import com.jiang.thinkindler.utils.pagelist.PageListListener;

import javax.inject.Inject;

/**
 * Created by jiang on 2017/4/14.
 */

public class DoubanMainPresenter implements DoubanMainContract.Presenter {

    protected DoubanMainContract.View mView;
    protected DoubanModel doubanModel;

    protected PageListHelper pageListHelper;

    @Inject
    public DoubanMainPresenter(DoubanMainContract.View view, DoubanModel doubanModel, PageListHelper pageListHelper) {
        this.mView = view;
        this.doubanModel = doubanModel;
        this.pageListHelper = pageListHelper;
        pageListHelper.setPageListListener(pageListListener);
    }

    PageListListener pageListListener = start -> doSearch(mView.getSearchContent(), start);

    @Override
    public void doSearch(String content, int start) {
        if (content.equals("")) {
            pageListHelper.stopLoading();
            return;
        }

        HistoryUtil.saveHistory(content);

        doubanModel.searchBooks(content, start)
                .compose(RxSchedulers.compose())
                .subscribe(new BaseObserver<PageList<BookBean>>(mView) {
                    @Override
                    protected void _onNext(PageList<BookBean> pageList) {
                        pageListHelper.setPageBean(pageList);
                        pageListHelper.stopLoading();
                        if (pageListHelper.isRefresh()) {
                            pageListHelper.clear();
                        }
                        mView.returnDatas(pageList.getDatas());
                    }

                    @Override
                    protected void _onError(String message) {
                        pageListHelper.stopLoading();
                    }
                });
    }

    @Override
    public void start() {
    }
}
