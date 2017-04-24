package com.jiang.thinkindler.rx;

import com.jiang.common.utils.NetWorkUtils;
import com.jiang.thinkindler.app.BaseApplication;

import rx.Subscriber;

/**
 *
 * 请求错误统一处理
 * Created by jiang on 2017/4/24.
 */

public abstract class RxSubScriber<T> extends Subscriber<T> {
    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
            _onError("网络不可用");
        } else if (e instanceof RuntimeException) {
            _onError(e.getMessage());
        }else {
            _onError("请求失败，请稍后再试");
        }
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);
}
