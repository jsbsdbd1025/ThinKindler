package com.jiang.media.base;

import com.jiang.common.base.BaseView;
import com.jiang.common.rx.BaseObserver;


/**
 * Created by jiang on 2017/5/23.
 */

public abstract class DoubanObserver<T> extends BaseObserver<T> {
    private static final String TAG = DoubanObserver.class.getName();

    public DoubanObserver(BaseView baseImpl) {
        super(baseImpl);
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    protected abstract void _onNext(T body);


}
