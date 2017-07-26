package com.jiang.douban.rx;

import android.content.Context;
import android.util.Log;

import com.jiang.common.entity.bean.PageList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by jiang on 2017/5/23.
 */

public abstract class PageObserver<T> implements Observer<PageList<T>> {
    private static final String TAG = PageObserver.class.getName();

    private Disposable disposable;
    private Context context;

    @Override
    public void onNext(PageList<T> responseBean) {
        _onNext(responseBean);
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.disposable = d;
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, e.toString());
    }

    @Override
    public void onComplete() {
        Log.e(TAG, "onComplete");
    }

    protected abstract void _onNext(PageList<T> t);

    protected abstract void _onError(String message);
}
