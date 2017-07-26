package com.jiang.douban.rx;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by jiang on 2017/5/23.
 */

public abstract class BaseCommonObserver<T> implements Observer<ResponseBean<T>> {
    private static final String TAG = BaseCommonObserver.class.getName();

    private Disposable disposable;
    private Context context;

    @Override
    public void onNext(ResponseBean<T> responseBean) {
        if (responseBean.isSuccess()) {
            _onNext(responseBean.getBody());
        } else {
            _onError(responseBean.getMsg());
        }
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

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);
}
