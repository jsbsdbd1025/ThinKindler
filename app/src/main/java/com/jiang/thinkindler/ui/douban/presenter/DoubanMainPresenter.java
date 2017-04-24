package com.jiang.thinkindler.ui.douban.presenter;

import com.jiang.common.utils.LogUtils;
import com.jiang.thinkindler.data.db.HistoryUtil;
import com.jiang.thinkindler.entity.bean.BookBean;
import com.jiang.thinkindler.entity.bean.BookList;
import com.jiang.thinkindler.injector.scope.FragmentScope;
import com.jiang.thinkindler.net.Api;
import com.jiang.thinkindler.net.ApiType;
import com.jiang.thinkindler.rx.RxHelper;
import com.jiang.thinkindler.rx.RxSubScriber;
import com.jiang.thinkindler.ui.douban.DoubanMainFragment;
import com.jiang.thinkindler.ui.douban.contract.DoubanMainContract;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by jiang on 2017/4/14.
 */

@FragmentScope
public class DoubanMainPresenter extends DoubanMainContract.presenter {

    DoubanMainFragment mView;

    @Inject
    public DoubanMainPresenter(DoubanMainFragment fragment) {
        this.mView = fragment;
        doSearch("Android");
    }

    @Override
    public void doSearch(String content) {
        if (content.equals(""))
            return;

        HistoryUtil.saveHistory(content);

        Api.getDefault(ApiType.DOUBAN).search(content, 0)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<BookList>() {
                    @Override
                    public void call(BookList bookList) {
                        LogUtils.loge("call");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubScriber<BookList>() {
                    @Override
                    public void onCompleted() {
                        LogUtils.loge("onCompleted");
                    }

                    @Override
                    protected void _onNext(BookList bookList) {
                        LogUtils.loge("_onNext");
                        mView.returnDatas(bookList.getBooks());
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.showShortToast(message);
                    }
                });
    }
}
