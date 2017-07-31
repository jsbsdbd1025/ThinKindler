package com.jiang.douban.ui.main;

import com.jiang.common.entity.bean.BookBean;
import com.jiang.common.entity.bean.PageList;
import com.jiang.common.utils.LogUtils;
import com.jiang.douban.base.DoubanObserver;
import com.jiang.douban.data.db.HistoryUtil;
import com.jiang.douban.net.model.DoubanModel;
import com.jiang.common.rx.BaseObserver;
import com.jiang.common.rx.RxSchedulers;

import javax.inject.Inject;

/**
 * Created by jiang on 2017/4/14. b
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

        LogUtils.loge("doSearch");
        HistoryUtil.saveHistory(content);

        doubanModel.searchBooks(content, start)
                .compose(RxSchedulers.compose())
                .subscribe(new DoubanObserver<PageList<BookBean>>(mView) {
                    @Override
                    protected void _onNext(PageList<BookBean> pageList) {
//                        mView.setStatus(STATUS_NORMAL);
//                        if (pageList.getDatas().size() == 0) {
//                            mView.setStatus(STATUS_ERROR);
//                        }
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
